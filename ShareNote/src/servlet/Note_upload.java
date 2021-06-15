//ノートのアップロード用
package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Note_upload")
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
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		//パラメータを取得
		request.setCharacterEncoding("UTF-8");
		int user_id = (int)session.getAttribute("user_id");
		String image_files = request.getParameter("image_files");
		String text_files = request.getParameter("text_files");
		String title = request.getParameter("title");
		int public_select = Integer.parseInt(request.getParameter("public_select"));
		String tag = request.getParameter("tag");

		//現在時刻から年だけを取得
		Date date = new Date();
        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
        int year = getLocalDate.getYear();

		//タグをカンマ区切りで収納
        System.out.println(tag);


	}
}