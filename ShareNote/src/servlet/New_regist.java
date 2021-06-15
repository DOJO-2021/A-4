package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

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
		String password_check = request.getParameter("password_check");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String regex_AlphaNum = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$" ; // 半角英数字と記号



		// 新規登録処理
		UserDao bDao = new UserDao();

		// 同じニックネームが使われていない場合
		if(bDao.checkNickname(nickname)==true) {

			int errCount = 0;
			if(!this.checkLogic(regex_AlphaNum, password)) {
				//使用不可能文字の処理
				request.setAttribute("errMsg2", "使用できない文字が含まれています");
				errCount+=1;
			}

			if(password.length()<5||password.length()>17||password=="") {
				//文字列の長さチェック
				String errMsg3="5文字以上16文字以内で入力してください";
				request.setAttribute("errMsg3", errMsg3);
				errCount+=1;

			}

			if(!password.equals(password_check)) {
			//パスワード確認不一致
				String errMsg4="パスワードが一致しません";
				request.setAttribute("errMsg4", errMsg4);
				errCount+=1;
			}

			if(errCount!=0) {
				//新規登録画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regist.jsp");
				dispatcher.forward(request, response);

			}else if (bDao.insert( nickname, password, question, answer)) {
				request.setAttribute("msg","ユーザー登録が完了しました");
				//ログインページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);

			// 新規登録が失敗
			}else {
				request.setAttribute("errMsg6","ユーザー登録が失敗しました");
				//新規登録画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regist.jsp");
				dispatcher.forward(request, response);
			}

		}else {
			request.setAttribute("errMsg1","そのニックネームは既に使用されています");
			//新規登録画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_regist.jsp");
			dispatcher.forward(request, response);
		}
	}
	public boolean checkLogic(String regex, String target) {
	    boolean result = true;
	    if( target == null || target.isEmpty() ) return false ;
	    // 3. 引数に指定した正規表現regexがtargetにマッチするか確認する
	    Pattern p1 = Pattern.compile(regex); // 正規表現パターンの読み込み
	    Matcher m1 = p1.matcher(target); // パターンと検査対象文字列の照合
	    result = m1.matches(); // 照合結果をtrueかfalseで取得
	    return result;
	  }

}
