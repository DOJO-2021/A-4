//ログイン画面用
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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUnickname = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");

		// ログイン処理
		UserDao userDao = new UserDao();
		// ログイン成功
		if (userDao.isLoginOK(nickname, password)) {
			// セッションスコープにnicknameを格納する
			User user = new User();
			HttpSession session = request.getSession();
			session.setAttribute("nickname",user.getNickname());

			// マイページサーブレットにリダイレクト
			response.sendRedirect("/ShareNote/Mypage");
		// ログイン失敗
		} else {

		}

	}

}
