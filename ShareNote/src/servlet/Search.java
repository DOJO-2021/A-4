//検索用
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

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}


		// 検索画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/ShareNote/Login");
			return;
		}

		//list作成
		request.setCharacterEncoding("UTF-8");

		String nickname = request.getParameter("keyword");
		String title = request.getParameter("keyword");

		String[] arrayTag = request.getParameterValues("tag"); //タグは配列で取得

		String matching = request.getParameter("matching");
		String tag = "";
		if(arrayTag!=null) {
	    for (String values : arrayTag) {

	 		tag += values + " ";
		 }
		}



	    String order=request.getParameter("sort");
	    if(order.equals("新着順")) {
	    	order="n.note_id ASC";
	    }if(order.equals("お気に入り順")){
	    	order="n.favorites_num DESC";
	    }

		// 検索処理を行う
	    NoteDao nDao= new NoteDao();
		List<Note> noteList;


		try {
			// タグ検索が完全一致だった場合

			if (matching.equals("matching")) {
				noteList = nDao.searchMatching(nickname, title, tag , order);

		// タグ検索が完全一致ではなかった場合
				} else {

					noteList = nDao.search(nickname, title, tag , order);
				}
		}
		catch(NullPointerException e){
			noteList = nDao.search(nickname, title, tag ,order);
		}


		// 検索結果をリクエストスコープに格納する
		request.setAttribute("noteList", noteList);

		//検索結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);


	}


}
