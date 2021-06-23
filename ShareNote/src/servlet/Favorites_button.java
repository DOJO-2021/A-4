//お気に入りボタン用
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoritesDao;
import model.User;

@WebServlet("/Favorites_button")
public class Favorites_button extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//お気に入りボタンを押してお気に入りするとき
		// リクエストパラメータを取得する
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		System.out.println(user_id+"←ゆーざーあいでー");
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		System.out.println(note_id+"←のーとあいでー");
		String cnt = request.getParameter("cnt");
		int num = Integer.parseInt(cnt);
		System.out.println(num+"←if文判定");
		//お気に入り登録する
		FavoritesDao fDao = new FavoritesDao();
			if (num == 1) {
			//if (画像が0なら) {	// 登録成功
				fDao.isFavoriteRegist(user_id,note_id);
			}
			//お気に入りボタンを押してお気に入り解除するとき
			else {
				fDao.isFavoriteRelease(note_id, note_id);

			}
		System.out.println(num);
		request.setAttribute("count", num);
	}
}
