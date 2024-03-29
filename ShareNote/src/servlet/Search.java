//検索用
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoritesDao;
import dao.NoteDao;
import model.Note;
import model.User;

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

		String[] titles=title.split(" |　",0);
		String[] nicknames=nickname.split(" |　",0);
		String keyword="";
		if(!(title.equals(""))) {
		if(titles.length==0) {
			keyword="";
		}
		if(titles.length==1) {
		keyword="AND(nickname LIKE '%"+nicknames[0]+"%' OR title LIKE '%"+titles[0]+"%')";
		}
		if(titles.length>1) {
			keyword="AND((nickname LIKE '%"+nicknames[0]+"%' OR title LIKE '%"+titles[0]+"%')";
			for(int i=1;i<titles.length; i++) {
			keyword += " OR(nickname LIKE '%"+nicknames[i]+ "%' OR title LIKE '%"+titles[i]+"%'))";
			}
		}
//		else {
//			keyword="AND(nickname LIKE % OR title LIKE % )";
//		}

		}
		System.out.println(keyword);




	    String order=request.getParameter("sort");
	    if(order.equals("新着順")) {
	    	order="n.note_id DESC";
	    }if(order.equals("お気に入り数順")){
	    	order="n.favorites_num DESC";
	    }

		// 検索処理を行う
	    NoteDao nDao= new NoteDao();
		List<Note> noteList;
		List<Note> hitList;




		try {
			// タグ検索が完全一致だった場合

			if (matching.equals("matching")) {
				noteList = nDao.searchMatching(nicknames, titles, tag , order,keyword);
				hitList=nDao.searchHitMatching(nicknames, titles, tag, keyword);


		// タグ検索が完全一致ではなかった場合
				} else {

					noteList = nDao.search(nicknames, titles, tag , order,keyword);
					hitList=nDao.searchHit(nicknames, titles, tag, keyword);

				}
		}
		catch(NullPointerException e){
			noteList = nDao.search(nicknames, titles, tag ,order,keyword);
			hitList=nDao.searchHit(nicknames, titles, tag, keyword);

		}

		//System.out.println(noteCount);



		// 検索結果をリクエストスコープに格納する
		request.setAttribute("noteList", noteList);
		request.setAttribute("hitList",hitList);

		// お気に入りボタンを表示するメソッドを呼ぶ
		FavoritesDao fDao = new FavoritesDao();
		// お気に入りしているかどうかの判断をする

		//お気に入りかどうかの判断の配列
		ArrayList<Integer> isFavoritesList = new ArrayList<Integer>();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		int count;
		for(Note n : noteList) {
		   int note_id = n.getNote_id();
		   if(fDao.selectFavorites(user_id, note_id)) {
			   count = 1;
		   } else {
			   count = 0;
		   }
		   isFavoritesList.add(count);
		}
		for(Integer e : isFavoritesList) {
			System.out.println(e);
		}
		request.setAttribute("isFavoritesList", isFavoritesList);

		//検索結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search_result.jsp");
		dispatcher.forward(request, response);
		//doGet(request, response);


	}


}
