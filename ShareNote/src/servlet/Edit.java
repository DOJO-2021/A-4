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
				}
			}

			//編集ボタンが押されたとき
			else if(edit.equals("編集を完了")) {
				Part image_part = request.getPart("image_files");
				image_files = this.getFileName(image_part);
				Part text_part = request.getPart("text_files");
				text_files =this.getFileName(text_part);
				String[] arrayTag = request.getParameterValues("tag");
				String nullTag = request.getParameter("tag");
				System.out.println(request.getParameter("pre_image_files"));
				System.out.println(image_files);
				System.out.println(request.getParameter("pre_text_files"));
				System.out.println(text_files);

				//必須項目が空欄だったらエラーメッセージを持って帰ってもらう
				if(title.equals("") || nullTag == null || (image_files.equals("")&&text_files.equals(""))) {
					String errMsg = "未入力の項目があります";
					request.setAttribute("errMsg", errMsg);
					this.doGet(request, response);
				}

				//現在時刻から年だけを取得（できれば4月はじまりが良き！今のところ普通の年）
				Date date = new Date();
		        ZoneId timeZone = ZoneId.systemDefault();
		        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
		        int year = getLocalDate.getYear();

				//タグをスペース区切りで収納
		        String tag = "";
		        for (String values : arrayTag) {
		        	tag += values + " ";
		        }

		        //乱数生成(同名ファイル対策)
		        Random rand = new Random();
		        Long num = rand.nextLong();

		        //画像ファイルを、乱数を付加してローカルに保存
		        if(!(image_files.equals(""))) {
					image_files = num + image_files;
					image_part.write(image_files);
		        }

		        //テキストファイルを、乱数を付加してローカルに保存
		        if(!(text_files.equals(""))) {
					text_files = num + text_files;
					text_part.write(text_files);
		        }

		        //DB更新をdaoにお任せ
				NoteDao nDao = new NoteDao();
				//成功したら、編集画面にリダイレクト
				if(nDao.updateNote(note_id, image_files, text_files,year, title, public_select, tag)) {
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
