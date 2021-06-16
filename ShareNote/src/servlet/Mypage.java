//マイページ用
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

import dao.FavoritesDao;
import dao.NoteDao;
import model.Note;
import model.User;

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
		// パラメータを取得
		request.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();

		NoteDao nDao = new NoteDao();
		FavoritesDao fDao = new FavoritesDao();

		// 最近アップロードしたノートを取ってくる
		List<Note> latestUploadNoteList = nDao.selectLatestUpload(user_id);

		String uploadMsg = null;

		// latestUploadNoteListが空の場合、メッセージも追加してListを持って帰る
		if(latestUploadNoteList.size() == 0) {
			uploadMsg = "登録されているノートはありません。";
		}
		request.setAttribute("uploadMsg", uploadMsg);
		request.setAttribute("latestUploadNoteList", latestUploadNoteList);

		// 最近お気に入りしたノートを持ってくる
		/*List<Note> latestFavoritesNoteList = fDao.selectLatestFavorites(user_id);

		String favoritesMsg = null;

		// latestFavoritesNoteListが空の場合、メッセージも追加してListを持って帰る
		if(latestFavoritesNoteList.size() == 0) {
			favoritesMsg = "登録されているノートはありません。";
		}
		request.setAttribute("favoritesMsg", favoritesMsg);
		request.setAttribute("latestFavoritesNoteList", latestFavoritesNoteList);*/


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

//		// マイページにフォワードする
//		String isInitial = "no"; //マイページが初期状態かどうか判別するための変数
//		request.setAttribute("isInitial", isInitial);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);
	}

}
