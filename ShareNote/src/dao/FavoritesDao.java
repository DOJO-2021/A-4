//お気に入り登録関連のデータアクセス用DAO
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoritesDao {

//ノート詳細
//こちらもおススメを表示する

//お気に入り登録してあるノートを引っ張てくる


//ノートをお気に入り登録する
	// お気に入り登録したらtrueを返す
	public boolean isFavoriteRegist(int user_id , int note_id , int favorites_flag) {
		Connection conn = null;
		boolean favoritesRegist = false;
try {
	// JDBCドライバを読み込む
	Class.forName("org.h2.Driver");

	// データベースに接続する
	conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

		// SQL文を準備する
		String sql = "insert into favorites values (nul, user_id, note_id, 1)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

	}catch (SQLException e){

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
	return favoritesRegist;
	}

	//お気に入り解除する
//お気に入り解除したらtrueを返す
	public boolean isFavoriteRelease(int user_id , int note_id , int favorites_flag) {
		Connection conn = null;
		boolean favoritesRelease = false;
try {
	// JDBCドライバを読み込む
	Class.forName("org.h2.Driver");

	// データベースに接続する
	conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

		// SQL文を準備する
		String sql = "update favorites set note_id=0 where )";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

	}catch (SQLException e){

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
	return favoritesRelease;
	}
}