//ノート詳細用
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

@WebServlet("/Note_detail")
public class Note_detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
	    if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
				}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
	    if (session.getAttribute("user") == null) {
	    	response.sendRedirect("/ShareNote/Login");
			return;
		}

		// 詳細を表示しているノートと同じタグの「こちらもおすすめ」ノートを検索する
	    FavoritesDao fDao = new FavoritesDao();
	    request.setCharacterEncoding("UTF-8");
	    String tag = request.getParameter("tag");
	    //System.out.println(tag + "a");


	    // FavoritesDaoから「こちらもおすすめ」を検索するメソッドを呼ぶ
	    List<Favorites> favoritesList = fDao.selectLatestUpload(tag);
	    request.setAttribute("RecommendedList", favoritesList);

	 // ノート詳細ページにフォワードする
	 	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note_detail.jsp");
	 	dispatcher.forward(request, response);

	}

}
