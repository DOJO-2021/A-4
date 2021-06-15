//マイノート一覧表
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@WebServlet("/Mynote_list")
public class Mynote_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		UserDao userDao = new UserDao();
		//int user_id = userDao.selectUser_id("nickname");
		int u = (int) session.getAttribute("user_id");
		User user = (User) session.getAttribute("loginUser");



		// マイノート一覧ページをインクルードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mynote_list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	RequestDispatcher dispatcher = request.getRequestDispatcher("/ShareNote/Edit");
	dispatcher.forward(request, response);

	}
}
