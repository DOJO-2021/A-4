package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	// ログインできるならtrueを返す☆
	public boolean isLoginOK(String nickname, String pw) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する☆
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SELECT文を準備する☆
			String sql = "select count(*) from USER where NICKNAME = ? and PASSWORD = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, nickname);
			pStmt.setString(2, pw);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}
	//再設定
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
			public boolean update(String new_password) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

					// SQL文を準備する
					String sql = "update IDPW set pw=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					if (new_password.getPassword() != null) {
						pStmt.setString(1, new_password.getPassword());
					}
					else {
						pStmt.setString(1, "null");
					}

					// SQL文を実行する
					if (pStmt.executeUpdate() == 1) {
						result = true;
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				finally {
					// データベースを切断
					if (conn != null) {
						try {
							conn.close();
						}
						catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				// 結果を返す
				return result;
			}
}
