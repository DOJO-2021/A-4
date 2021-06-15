package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;


public class UserDao {
	// ログインできるならtrueを返す☆
	public User isLoginOK(String nickname, String pw) {
		Connection conn = null;
		User user = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する☆
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SELECT文を準備する☆
			String sql = "select * from USER where NICKNAME = ? and PASSWORD = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, nickname);
			pStmt.setString(2, pw);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setNickname(rs.getString("nickname"));
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
		return user;
	}

	//確認用メソッド
	public boolean selectUser(String nickname,String question, String answer) {
		Connection conn = null;
		boolean selectUser = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select count(*) from USER where NICKNAME = ? and QUESTION = ? and ANSWER = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, nickname);
			pStmt.setString(2, question);
			pStmt.setString(3, answer);


			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ニックネーム、秘密の質問、答えが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				selectUser = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IllegalStateException e) {
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
		return selectUser;
	}

	//再設定
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
			public boolean update(String nickname,String new_password) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

					// SQL文を準備する
					String sql = "update USER  set PASSWORD=? where NICKNAME=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる

					if (new_password != null) {
						pStmt.setString(1, new_password);
					}
					else {
						pStmt.setString(1, null);
					}
					pStmt.setString(2, nickname);

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

		//確認用メソッド 新規登録の際、同じニックネームがいないか確認する
		public boolean checkNickname(String nickname) {
				Connection conn = null;
				boolean checkNickname = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

					// SQL文を準備する
					String sql = "select count(*) from USER where NICKNAME = ?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, nickname);


					// SELECT文を実行し、結果表を取得する
					ResultSet rs = pStmt.executeQuery();

					// ニックネーム、秘密の質問、答えが一致するユーザーがいたかどうかをチェックする
					rs.next();
					if (rs.getInt("count(*)") == 0) {
						checkNickname = true;
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				catch(IllegalStateException e) {
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
				return checkNickname;
			}

	// 新規登録
	//引数infoで指定されたレコードを登録し、成功したらtrueを返す

	// public boolean insert(User info) {
	 public boolean insert(String nickname, String password, String question, String answer) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する	ユーザーを新規登録する
			String sql = "insert into User values (null, ?, ?, ?, ?)";	// USER_IDは自動採番
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			if (nickname != null) {
				pStmt.setString(1, nickname);
			}
			else {
				pStmt.setString(1, null);
			}
			if (password != null) {
				pStmt.setString(2, password);
			}
			else {
				pStmt.setString(2, null);
			}
			if (question != null) {
				pStmt.setString(3, question);
			}
			else {
				pStmt.setString(3, null);
			}
			if (answer != null) {
				pStmt.setString(4, answer);
			}
			else {
				pStmt.setString(4, null);
			}

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {	//登録した件数
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

