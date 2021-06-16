//お気に入り登録関連のデータアクセス用DAO
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Favorites;

public class FavoritesDao {
//マイページに最近お気に入り登録したノートを3件ほど表示する
	public List<Favorites> selectLatestUpload(int user_id) {
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;
		//検索結果を入れる配列を用意
		List<Favorites> favoritesList = new ArrayList<Favorites>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select * from NOTE where USER_ID = ? order by NOTE_ID desc limit 3";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println(rs);

			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Favorites favorites = new Favorites();
				favorites.setFavorites_id(rs.getInt("favorites_id"));
				favorites.setNote_id(rs.getInt("note_id"));
				favorites.setFavorites_flag(rs.getInt("favorites_flag"));
				favorites.setNickname(rs.getString("nickname"));
				favorites.setImage_files(rs.getString("image_files"));
				favorites.setText_files(rs.getString("text_files"));
				favorites.setYear(rs.getInt("year"));
				favorites.setTitle(rs.getString("title"));
				favorites.setTag(rs.getString("tag"));
				favoritesList.add(favorites);
			}
		}

		//例外
		catch (SQLException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		//例外が起きてもどっちにしろ切断
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					favoritesList = null;
				}
			}
		}

		// 結果を返す
		return favoritesList;
	}



//ノート詳細
//こちらもおススメを3件表示する
	public List<Favorites> selectLatestUpload(Favorites param) {
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;
		//検索結果を入れる配列を用意
		List<Favorites> favoritesList = new ArrayList<Favorites>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			System.out.println(rs);

			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Favorites favorites = new Favorites();
//				favorites.setFavorites_id(rs.getInt("favorites_id"));
//				favorites.setNote_id(rs.getInt("note_id"));
//				favorites.setFavorites_flag(rs.getInt("favorites_flag"));
				favorites.setNickname(rs.getString("nickname"));
				favorites.setImage_files(rs.getString("image_files"));
				favorites.setText_files(rs.getString("text_files"));
				favorites.setYear(rs.getInt("year"));
				favorites.setTitle(rs.getString("title"));
				favorites.setTag(rs.getString("tag"));
				favoritesList.add(favorites);
			}
		}

		//例外
		catch (SQLException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		//例外が起きてもどっちにしろ切断
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					favoritesList = null;
				}
			}
		}

		// 結果を返す
		return favoritesList;
	}
//お気に入り登録してあるノートを引っ張てくる
	public List<Favorites> select(Favorites param) {
		Connection conn = null;
		List<Favorites> favoritesList = new ArrayList<Favorites>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select f.favorites_id, f.note_id, n.user_id, f.favorites_flag, u.nickname, n.image_files, n.text_files, n.year, n.title, n.tag \r\n"
					+ "from ( favorites as f inner join note as n on f.user_id=u.user_id)\r\n"
					+ "inner join user as u on f.note_id=n.note_id\r\n"
					+ " where n.public_select=1 order by favorites_id asc ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Favorites favorites = new Favorites();
				favorites.setFavorites_id(rs.getInt("favorites_id"));
				favorites.setNote_id(rs.getInt("note_id"));
				favorites.setFavorites_flag(rs.getInt("favorites_flag"));
				favorites.setNickname(rs.getString("nickname"));
				favorites.setImage_files(rs.getString("image_files"));
				favorites.setText_files(rs.getString("text_files"));
				favorites.setYear(rs.getInt("year"));
				favorites.setTitle(rs.getString("title"));
				favorites.setTag(rs.getString("tag"));
				favoritesList.add(favorites);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			favoritesList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					favoritesList = null;
				}
			}
		}

		// 結果を返す
		return favoritesList;
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