//再設定用
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

@WebServlet("/Re_regist")
public class Re_regist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パスワード再設定ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/re_regist.jsp");
		dispatcher.forward(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String nickname = request.getParameter("nickname");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String new_password = request.getParameter("password");
		String new_password2 = request.getParameter("password2");
		String regex_AlphaNum = "^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$" ; // 半角英数字と記号

		//パスワードを再設定

		UserDao uDao = new UserDao();
		//ユーザー確認  ニックネームと秘密の質問とその回答が一致していることを確認
		if(uDao.selectUser(nickname,question,answer)==true) {

			int errCount = 0;

			if(!this.checkLogic(regex_AlphaNum, new_password)) {
				//使用不可能文字の処理
				String errMsg2="使用できない文字が含まれています";
				request.setAttribute("errMsg2", errMsg2);
				errCount += 1;
			}

			if(new_password.length()<5||new_password.length()>17||new_password=="") {
				//文字列の長さチェック
				String errMsg3="5文字以上16文字以内で入力してください";
				request.setAttribute("errMsg3", errMsg3);
				errCount += 1;
			}

			if(!new_password.equals(new_password2)) {
				//パスワード確認不一致
				String errMsg4="パスワードが一致しません";
				request.setAttribute("errMsg4", errMsg4);
				errCount += 1;
			}

			if(errCount!=0) {
				//新規登録画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/re_regist.jsp");
				dispatcher.forward(request, response);

			}else if(uDao.update(nickname,new_password)) {
				//パスワード再設定成功
				request.setAttribute("msg","パスワードを再設定しました");
				//ログインページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}


		// 登録されているユーザー情報の中に一致するものがなかった場合
		}else {
			String errMsg = "ユーザーが見つかりません。";
			request.setAttribute("errMsg", errMsg);
			this.doGet(request, response);
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
