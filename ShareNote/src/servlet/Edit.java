//編集用
package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.NoteDao;

@WebServlet("/Edit")
@MultipartConfig(location = "C:\\pleiades\\workspace\\A-4\\ShareNote\\WebContent\\upload_files") // アップロードファイルの一時的な保存先

public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}
		// 編集画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}
		//パラメータを取得
		request.setCharacterEncoding("UTF-8");
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String image_files = request.getParameter("image_files");
		String text_files = request.getParameter("text_files");
		String title = request.getParameter("title");
		int public_select = Integer.parseInt(request.getParameter("public_select"));
		String edit = request.getParameter("edit");

		//マイページで編集ボタンが押されたとき
		if(edit.equals("編集")) {
			String tag = request.getParameter("tag");
			String[] tags = tag.split(" "); //タグを分割して配列に格納
			request.setAttribute("tags", tags);
			request.setAttribute("image_files",image_files);
			request.setAttribute("text_files", text_files);
			this.doGet(request, response);
		}

		//編集画面でどちらかのボタンが押されたとき
		else {
			//削除ボタンが押されたとき
			if(edit.equals("ノート削除")) {
				//DB削除をDAOにお任せ
				NoteDao nDao = new NoteDao();
				//成功したら、マイページにリダイレクト
				if(nDao.deleteNote(note_id)) {
					String msg = "削除が完了しました";
					request.setAttribute("msg", msg);
					String isInitial = "yes"; //マイページが初期状態かどうか判別するための変数
					request.setAttribute("isInitial", isInitial);
					response.sendRedirect("/ShareNote/Mypage");
				}
				//失敗したら、エラーメッセージを持って帰ってもらう
				else {
					String dbErrMsg = "削除に失敗しました。";
					request.setAttribute("dbErrMsg", dbErrMsg);
					this.doGet(request, response);
					return;
				}
			}

			//編集ボタンが押されたとき
			else if(edit.equals("編集を完了")) {
				Part image_part = request.getPart("image_files");
				image_files = this.getFileName(image_part);      //編集後の画像ファイル
				Part text_part = request.getPart("text_files");
				text_files =this.getFileName(text_part);         //編集後のテキストファイル
				String[] tags = request.getParameterValues("tag");
				String nullTag = request.getParameter("tag");
				String pre_image_files = request.getParameter("pre_image_files");//編集前の画像ファイル
				String pre_text_files = request.getParameter("pre_text_files");  //編集前のテキストファイル
				System.out.println(pre_text_files);

				//必須項目が空欄のときのエラー処理
				//編集前後の画像ファイル・テキストファイルすべてが空欄だったら何もせず帰ってもらう
				if(image_files.equals("")&&text_files.equals("")&&pre_image_files.equals("")&&pre_text_files.equals("")) {
					String errMsg = "未入力の項目があります";
					request.setAttribute("errMsg", errMsg);
					this.doGet(request, response);
					return;
				}
				//タイトルかタグが空欄だったら編集前のファイルを持って帰ってもらう
				else if(title.equals("") || nullTag == null) {
					image_files = pre_image_files;
					text_files = pre_text_files;
					request.setAttribute("tags", tags);
					request.setAttribute("image_files", image_files);
					request.setAttribute("text_files", text_files);
					String errMsg = "未入力の項目があります";
					request.setAttribute("errMsg", errMsg);
					this.doGet(request, response);
					return;
				}

				//現在時刻から年だけを取得（できれば4月はじまりが良き！今のところ普通の年）
				Date date = new Date();
		        ZoneId timeZone = ZoneId.systemDefault();
		        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
		        int year = getLocalDate.getYear();

				//タグをスペース区切りで収納
		        String tag = "";
		        for (String values : tags) {
		        	tag += values + " ";
		        }

	        //ファイルの処理
		        //乱数生成(同名ファイル対策)
		        Random rand = new Random();
		        Long num = rand.nextLong();

		        //編集後の画像ファイルが空欄だったら、編集前の画像ファイルをDBに送る
		        if(image_files.equals("")) {
		        	image_files = pre_image_files;
		        } else {
			        //画像ファイルを、乱数を付加してローカルに保存してパス追加
					image_files = num + image_files;
					image_part.write(image_files);
					image_files = "/ShareNote/upload_files/" + image_files;
		        }
				//編集後のテキストファイルが空欄だったら、編集前のテキストファイルをDBに送る
		        if(text_files.equals("")) {
		        	text_files = pre_text_files;
		        } else {
			        //テキストファイルを、乱数を付加してローカルに保存パス追加
					text_files = num + text_files;
					text_part.write(text_files);
					text_files = "/ShareNote/upload_files/" + text_files;
		        }
	        //ファイルの処理ここまで

		        //DB更新をdaoにお任せ
				NoteDao nDao = new NoteDao();
				//成功したら、編集画面にフォワード
				if(nDao.updateNote(note_id, image_files, text_files,year, title, public_select, tag)) {
					request.setAttribute("image_files", image_files);
					request.setAttribute("text_files", text_files);
					request.setAttribute("tags", tags);
					String msg = "編集が完了しました";
					request.setAttribute("msg", msg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
					dispatcher.forward(request, response);
				}
				//失敗したら、エラーメッセージを持って帰ってもらう
				else {
					String dbErrMsg = "編集に失敗しました。";
					request.setAttribute("dbEerrMsg", dbErrMsg);
					this.doGet(request, response);
					return;
				}
			}
		}
	}

	//ファイルの名前を取得してくるクラス
	private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
		return name;
	}


}
