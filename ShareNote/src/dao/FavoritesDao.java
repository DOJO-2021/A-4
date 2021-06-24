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
	public List<Favorites> selectLatestFavorites(int user_id) {
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
			String sql = "select f.favorites_id, f.note_id, f.favorites_flag, u.nickname, n.image_files, n.text_files, n.year, n.title, n.tag "
					+ "from ((note as n right join user as u on n.user_id=u.user_id) left join favorites as f on f.note_id=n.note_id ) "
					+ "where f.user_id = ? and n.public_select=1 order by favorites_id desc limit 3";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Favorites favorites = new Favorites();
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
		public List<Favorites> selectLatestUpload(String tag, int user_id, int note_id) {
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
				String sql = "select distinct n.image_files, n.year, u.nickname, n.title, n.tag, n.text_files, n.note_id, n.user_id, n.favorites_num " +
						"from (( note as n left join user as u on n.user_id=u.user_id ) left join favorites as f on n.note_id=f.note_id) " +
						"where tag =? and public_select=1 and n.user_id<>? and n.note_id<>? order by favorites_num limit 3";
				System.out.println(sql+"←sql文");
				System.out.println(tag+"←タグ");
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, tag);
				pStmt.setInt(2, user_id);
				pStmt.setInt(3, note_id);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				System.out.println(rs);

				// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
				while (rs.next()) {
					Favorites favorites = new Favorites();
//					favorites.setFavorites_id(rs.getInt("favorites_id"));
					favorites.setNote_id(rs.getInt("note_id"));
					favorites.setNickname(rs.getString("nickname"));
					favorites.setImage_files(rs.getString("image_files"));
					favorites.setText_files(rs.getString("text_files"));
					favorites.setYear(rs.getInt("year"));
					favorites.setTitle(rs.getString("title"));
					favorites.setTag(rs.getString("tag"));
					favorites.setFavorites_num(rs.getInt("favorites_num"));
					favoritesList.add(favorites);
				}
				System.out.println(favoritesList.size()+"←取ってきたリストの要素数だよ");
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
	public List<Favorites> select(int user_id) {
		Connection conn = null;
		List<Favorites> favoritesList = new ArrayList<Favorites>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select f.favorites_id, f.note_id, f.favorites_flag, u.nickname, n.image_files, n.text_files, n.year, n.title, n.tag "
					+ "from ((note as n right join user as u on n.user_id=u.user_id) left join favorites as f on f.note_id=n.note_id ) "
					+ "where f.user_id = ? and n.public_select=1 order by favorites_id asc ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
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
	public boolean isFavoriteRegist(int user_id , int note_id) {
		boolean result = false;
		Connection conn = null;
		//boolean favoritesRegist = false;
try {
	// JDBCドライバを読み込む
	Class.forName("org.h2.Driver");

	// データベースに接続する
	conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

		// SQL文を準備する
		String sql = "insert into favorites values (null, ?, ?, 1);" +
				"update note set favorites_num=favorites_num+1 where note_id=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, user_id);
		pStmt.setInt(2, note_id);
		pStmt.setInt(3, note_id);
		// SELECT文を実行し、結果表を取得する
		if (pStmt.executeUpdate() == 1) {
			result = true;
		}


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
	return result;
	}

	//お気に入り解除する
	//お気に入り解除したらtrueを返す
		public boolean isFavoriteRelease(int user_id, int note_id) {
			boolean result = false;
			Connection conn = null;
			//boolean favoritesRelease = false;
	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "delete from favorites where user_id = ? and note_id = ? " ;
			String sql2 = "update note set favorites_num=favorites_num-1 where note_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, note_id);
			System.out.println(note_id + "←DAOのnote_id");
			System.out.println(user_id + "←DAOのuser_id");
			// SELECT文を実行し、結果表を取得する
			if (pStmt.executeUpdate() == 1) {
				pStmt = conn.prepareStatement(sql2);
				pStmt.setInt(1, note_id);
				if(pStmt.executeUpdate()==1) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}

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
		return result;
		}


	//ユーザーが登録しているかの判断
	public boolean selectFavorites(int user_id, int note_id) {
		boolean result = false;
		List<Favorites> select = new ArrayList<Favorites>();
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;
		try {
			// JDBCドライバを読み込む

			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select * from favorites where user_id = ? and note_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, note_id);
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			int count1 = 0;
			while (rs.next()) {
				count1 ++;
			}

			if (count1 == 1) {
				result = true;
		}
		}
		//例外
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
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
				}
			}
		}
		// 結果を返す
		return result;
	}
	}