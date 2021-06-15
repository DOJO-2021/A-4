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

		User user =userDao.isLoginOK(nickname, password);
		HttpSession session = request.getSession();
		//ろぐいんok
		if(user!=null) {
			session.setAttribute("user",user);
		//	User user2 = (User)session.getAttribute("user");
		//	System.out.println(user2.getUser_id());
		//	${sessionScope.user.user_id}
			response.sendRedirect("/ShareNote/Mypage");
		}else {
			String errMsg = "ニックネームまたはパスワードが違います";
			request.setAttribute("errMsg", errMsg);

			this.doGet(request, response);

		}
		// ログイン成功
//		if (userDao.isLoginOK(nickname, password)) {
//			// nicknameからuser_idを持ってくる
//			int user_id = userDao.selectUser_id(nickname);
//
//			// セッションスコープにuser_idとnicknameを格納する
//			User user = new User();
//			HttpSession session = request.getSession();
//			session.setAttribute("nickname", nickname);
//			session.setAttribute("user_id", user_id);
//			session.setAttribute("loginUser", user);
//
//			// マイページサーブレットにリダイレクト
//			response.sendRedirect("/ShareNote/Mypage");
//		// ログイン失敗
//		} else {
///*			if(nickname.equals("") || password.trim().equals("")) {
//				String errMsg = "入力箇所が抜けています";
//				request.setAttribute("errMsg", errMsg);
//
//				this.doGet(request, response);
//			} else {
//*/
//				String errMsg = "ニックネームまたはパスワードが違います";
//				request.setAttribute("errMsg", errMsg);
//
//				this.doGet(request, response);
//
//		}

	}

}
