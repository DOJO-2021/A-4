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

		int user_id = session.getAttribute("user_id");

		// マイノート一覧ページをインクルードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mynote_list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	RequestDispatcher dispatcher = request.getRequestDispatcher("/ShareNote/Edit");
	dispatcher.forward(request, response);

	}
}
