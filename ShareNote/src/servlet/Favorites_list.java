//お気に入り一覧用
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoritesDao;
import model.Favorites;
import model.User;

@WebServlet("/Favorites_list")
public class Favorites_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		User user = (User) session.getAttribute("user");
		// ログインしているユーザーのノート情報を検索する
		FavoritesDao fDao = new FavoritesDao();

		// NoteDaoのuser_idからノート情報を検索するメソッドを呼ぶ
		List<Favorites> favoritesList = fDao.select(user.getUser_id());
		request.setAttribute("latestFavoritesList", favoritesList);

		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		String page_switch = "お気に入り一覧";
		request.setAttribute("page_switch", page_switch);
		request.setAttribute("count1", 1);
		request.setAttribute("count2", 2);
		request.setAttribute("count3", 7);

		// お気に入り一覧ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//	RequestDispatcher dispatcher = request.getRequestDispatcher("/ShareNote/Edit");
	//	dispatcher.forward(request, response);
	}
}

