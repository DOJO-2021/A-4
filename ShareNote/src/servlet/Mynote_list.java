//マイノート一覧表
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

import dao.NoteDao;
import model.Note;
import model.User;

@WebServlet("/Mynote_list")
public class Mynote_list extends HttpServlet {
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
		NoteDao nDao = new NoteDao();

		// NoteDaoのuser_idからノート情報を検索するメソッドを呼ぶ
		List<Note> mynoteList = nDao.selectMynote(user.getUser_id());
		request.setAttribute("noteList", mynoteList);

		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		String page_switch = "マイノート一覧";
		request.setAttribute("page_switch", page_switch);
		// マイノート一覧ページをインクルードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		User user = (User) session.getAttribute("user");
		// ログインしているユーザーのノート情報を検索する
		NoteDao nDao = new NoteDao();

		// NoteDaoのuser_idからノート情報を検索するメソッドを呼ぶ
		List<Note> mynoteList = nDao.selectMynote(user.getUser_id());
		request.setAttribute("noteList", mynoteList);

		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
		request.setAttribute("isInitial", isInitial);
		String page_switch = "マイノート一覧";
		request.setAttribute("page_switch", page_switch);
		
		// マイノート一覧ページをインクルードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}
}
