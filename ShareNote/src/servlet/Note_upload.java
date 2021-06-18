//ノートのアップロード用
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
import model.User;

@WebServlet("/Note_upload")
//↓これ絶対入れてね！！
						   //C:\\pleiades\\workspace\\A-4\\ShareNote\\WebContent\\upload_files
@MultipartConfig(location = "C:\\pleiades\\workspace\\A-4\\ShareNote\\WebContent\\upload_files") // アップロードファイルの一時的な保存先

public class Note_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		String page_switch = "ノートのアップロード";
		request.setAttribute("page_switch", page_switch);
		// マイページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
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
		User user = (User)session.getAttribute("user");
		int user_id = (int)(user.getUser_id());

		//String image_files = request.getParameter("image_files"); //ファイルはパラメータで持ってこれない
		Part image_part = request.getPart("image_files"); // getPartでファイルの色んな情報を取得
											//↑jspの"name"部分
		String image_files = this.getFileName(image_part); //file名だけ取得
		//String text_files = request.getParameter("text_files");
		Part text_part = request.getPart("text_files");
		String text_files =this.getFileName(text_part);

		String title = request.getParameter("title");
		int public_select = Integer.parseInt(request.getParameter("public_select"));

		String[] arrayTag = request.getParameterValues("tag"); //タグは配列で取得
		String nullTag = request.getParameter("tag"); //タグが空欄かどうか判別するための変数

		//必須項目が空欄だったらエラーメッセージを持って帰ってもらう
		//(タイトルが空欄 or タグが空欄 or 画像ファイルが空欄かつテキストファイルが空欄)
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

		//タグをカンマ区切りで収納
        String tag = "";
        for (String values : arrayTag) {
        	tag += values + ",";
        }

        //乱数生成(同名ファイル対策)
        Random rand = new Random();
        Long num = rand.nextLong();

        //画像ファイルを、ローカルに保存して名前にパスと乱数を付加
        if(!(image_files.equals(""))) {
			//サーバの指定のファイルパスへファイルを保存
	        //場所はクラス名の上(25行目)に指定してある
			image_part.write(image_files);
			//パスと乱数付加
			image_files = "ShareNote/upload_files/" + num + image_files;
        }

        //テキストファイルを、名前にパスと乱数を付加してローカルに保存
        if(!(text_files.equals(""))) {
			text_part.write(text_files);
			text_files ="ShareNote/upload_files/" +  num + text_files;
        }

        //DB登録をdaoにお任せ
		NoteDao nDao = new NoteDao();
		//成功したら、マイページにリダイレクト
		if(nDao.insertNote(user_id, image_files, text_files, year, title, public_select, tag)) {
			String msg = "ノートをアップロードしました";
			request.setAttribute("msg", msg);
			response.sendRedirect("/ShareNote/Mypage");
		}
		//失敗したら、エラーメッセージを持って帰ってもらう
		else {
			String dbErrMsg = "ノートのアップロードに失敗しました。";
			request.setAttribute("dbEerrMsg", dbErrMsg);
			this.doGet(request, response);
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