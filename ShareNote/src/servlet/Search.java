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

		String[] tags = request.getParameterValues("tag"); //タグは配列で取得
		request.setAttribute("tags", tags);

		String matching = request.getParameter("matching");
		String tag = "";
		if(tags!=null) {
	    for (String values : tags) {

	 		tag += values + " ";
		 }
		}
		String[] titles=title.split(" ",0);
		String[] nicknames=nickname.split(" ",0);
		String keyword="AND(nickname LIKE ? OR title LIKE ? )";
		if(titles.length>1) {
			for(int i=1;i<titles.length-1; i++) {
			keyword += "AND(nickname LIKE ? OR title LIKE ? ) ";
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
		List<Note> hitList;



		try {
			// タグ検索が完全一致だった場合

			if (matching.equals("matching")) {
				noteList = nDao.searchMatching(nickname, title, tag , order);
				hitList=nDao.searchHitMatching(nickname, title, tag);


		// タグ検索が完全一致ではなかった場合
				} else {

					noteList = nDao.search(nickname, title, tag , order,keyword);
					hitList=nDao.searchHit(nickname, title, tag);

				}
		}
		catch(NullPointerException e){
			noteList = nDao.search(nickname, title, tag ,order,keyword);
			hitList=nDao.searchHit(nickname, title, tag);

		}

		//System.out.println(noteCount);



		// 検索結果をリクエストスコープに格納する
		request.setAttribute("noteList", noteList);
		request.setAttribute("hitList",hitList);

		//検索結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);


	}


}
