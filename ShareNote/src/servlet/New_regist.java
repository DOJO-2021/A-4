package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

@WebServlet("/New_regist")
public class New_regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regist.jsp");
				dispatcher.forward(request, response);
			}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");

		// 新規登録処理
		UserDao bDao = new UserDao();
		// 新規登録が成功
		User user = new User();
		if (bDao.insert( 0, user.getNickname(), user.getPassword(), user.getQuestion(), user.getAnswer())) {
			request.setAttribute("msg","ユーザー登録が完了しました");

		// 新規登録が失敗
		}else {
			request.setAttribute("errMsg4","ユーザー登録が失敗しました");
		}

	}

}
