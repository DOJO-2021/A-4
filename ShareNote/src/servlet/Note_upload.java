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
		String image_files = request.getParameter("image_files");
		String text_files = request.getParameter("text_files");
		String title = request.getParameter("title");
		int public_select = Integer.parseInt(request.getParameter("public_select"));
		String[] arrayTag = request.getParameterValues("tag"); //タグは配列で取得

		//必須項目が空欄だったらエラーメッセージを持って帰ってもらう
		if(title == "") {
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

        //画像ファイル・テキストファイルの名前を"絶対パス+乱数"に変換
        image_files = "/ShareNote/image_files/"+ num + image_files;
        text_files = "/ShareNote/test_files/" + num + text_files;

        //画像ファイル・テキストファイルをローカルに保存
		Part part = request.getPart("image_files"); // getPartでファイルの色んな情報を取得
									//↑jspの"name"部分
		String image = this.getFileName(part); //file名だけ取得
		request.setAttribute("image", image);
		//サーバの指定のファイルパスへファイルを保存
        //場所はクラス名の上(25行目)に指定してある
		part.write(image);

        //DB登録をdaoにお任せ
		NoteDao nDao = new NoteDao();
		//nDao.insertNote(user_id, image_files, text_files, year, title, public_select, tag);

        //マイページにフォワード
		String isInitial = "yes"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

	//ファイルの名前を取得してくる
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