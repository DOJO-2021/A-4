//マイページ用
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Mypage")
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}
		// メニューページにフォワードする
		String isInitial = "yes"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);

	}

	// ノートをアップロードボタン・マイノート一覧ボタン・お気に入り一覧ボタンが押されたとき
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");

		// マイページにフォワードする
		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

}
