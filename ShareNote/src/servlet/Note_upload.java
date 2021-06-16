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

import dao.NoteDao;
import model.User;

@WebServlet("/Note_upload")
//↓これ絶対入れてね！！
@MultipartConfig(location = "C:\\pleiades\\workspace\\Nyample\\WebContent\\images") // アップロードファイルの一時的な保存先

public class Note_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		// ノートのアップロードページをインクルードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note_upload.jsp");
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
		//if()

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
        System.out.println(image_files);
        System.out.println(text_files);

        //画像ファイル・テキストファイルをローカルに保存


        //DB登録をdaoにお任せ
		NoteDao nDao = new NoteDao();
		nDao.insertNote(user_id, image_files, text_files, year, title, public_select, tag);



	}
}