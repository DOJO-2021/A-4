//お気に入り登録関連のデータアクセス用DAO
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SN;
public class FavoritesDao {

//ノート詳細
//こちらもおススメを表示する

//お気に入り登録してあるノートを引っ張てくる
	public List<SN> select(SN param) {
		Connection conn = null;
		List<SN> cardList = new ArrayList<SN>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select f. from favorites_flag WHERE ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getCompany() != null) {
				pStmt.setString(1, "%" + param.getCompany() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			if (param.getName() != null) {
				pStmt.setString(2, "%" + param.getName() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}
			if (param.getFurigana() != null) {
				pStmt.setString(3, "%" + param.getFurigana() + "%");
			}
			else {
				pStmt.setString(3, "%");
			}
			if (param.getAddress() != null) {
				pStmt.setString(4, "%" + param.getAddress() + "%");
			}
			else {
				pStmt.setString(4, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				SN card = new SN(
				rs.getInt(""),
				rs.getString(""),
				rs.getString(""),
				rs.getString("NICKNAME"),
				rs.getString("IMAGE_FILES"),
				rs.getString("TEXT_FILES"),
				rs.getInt("YEAR"),
				rs.getString("TITLE"),
				rs.getString("TAG"),
				rs.getInt("FAVORITES_FLAG")
				);
				cardList.add(card);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

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
		String sql = "insert into favorites values (nul, user_id = ?, note_id = ?, 1)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, user_id);
		pStmt.setInt(2, note_id);
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

	}catch (SQLException e){
		e.printStackTrace();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally {
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
		String sql = "update favorites set favorites_flag = 0 where note_id = ?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, note_id);
		// SELECT文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

	}catch (SQLException e){
		e.printStackTrace();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally {
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