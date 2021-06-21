//お気に入りボタン用
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FavoritesDao;

@WebServlet("/Favorites_button")
public class Favorites_button extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//お気に入りボタンを押してお気に入りするとき
				// リクエストパラメータを取得する☆
				request.setCharacterEncoding("UTF-8");
//				int favorites_id = Integer.parseInt(request.getParameter("favorites_id"));
//				System.out.println(request.getParameter("user_id"));
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				int note_id = Integer.parseInt(request.getParameter("note_id"));
//				int favorites_flag = Integer.parseInt(request.getParameter("favorites_flag"));
				System.out.println(request.getParameter("image"));
				//お気に入り登録する
				FavoritesDao fDao = new FavoritesDao();
				if (request.getParameter("count").equals("0")) {
					//if (画像が0なら) {	// 登録成功
					fDao.isFavoriteRegist(user_id, note_id);
				}
					//お気に入りボタンを押してお気に入り解除するとき
				else {
					fDao.isFavoriteRelease(note_id);

				}
/*
			String str = request.getParameter("str");
			int num = Integer.parseInt(str);
			System.out.println(num);
*/
		}

	}
