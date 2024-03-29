//Note関連のデータアクセス用DAO
package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Note;

public class NoteDao {


//マイページ画面
	//マイページに最近アップロードしたノートを3件ほど表示する
	public List<Note> selectLatestUpload(int user_id) {
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;
		//検索結果を入れる配列を用意
		List<Note> noteList = new ArrayList<Note>();

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

			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Note note = new Note();
				note.setNote_id(rs.getInt("note_id"));
				note.setUser_id(rs.getInt("user_id"));
				note.setImage_files(rs.getString("image_files"));
				note.setText_files(rs.getString("text_files"));
				note.setYear(rs.getInt("year"));
				note.setTitle(rs.getString("title"));
				note.setPublic_select(rs.getInt("public_select"));
				note.setFavorites_num(rs.getInt("favorites_num"));
				note.setTag(rs.getString("tag"));
				noteList.add(note);
			}
		}

		//例外
		catch (SQLException e) {
			e.printStackTrace();
			noteList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			noteList = null;
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
					noteList = null;
				}
			}
		}

		// 結果を返す
		return noteList;
	}


	//マイノート一覧にアップロードしたノート全てを表示する
	public List<Note> selectMynote(int user_id) {
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;
		//検索結果を入れる配列を用意
		List<Note> mynoteList = new ArrayList<Note>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select * from NOTE where USER_ID = ? order by NOTE_ID desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			//System.out.println(rs);
			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Note note = new Note();
				note.setNote_id(rs.getInt("note_id"));
				note.setUser_id(rs.getInt("user_id"));
				note.setImage_files(rs.getString("image_files"));
				note.setText_files(rs.getString("text_files"));
				note.setYear(rs.getInt("year"));
				note.setTitle(rs.getString("title"));
				note.setPublic_select(rs.getInt("public_select"));
				note.setFavorites_num(rs.getInt("favorites_num"));
				note.setTag(rs.getString("tag"));
				mynoteList.add(note);
			}
		}

		//例外
		catch (SQLException e) {
			e.printStackTrace();
			mynoteList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			mynoteList = null;
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
					mynoteList = null;
				}
			}
		}

		// 結果を返す
		return mynoteList;
	}


	//ノートをアップロードする(成功でtrue,失敗でfalseを返す)
	public boolean insertNote(int user_id, String image_files, String text_files, int year, String title, int public_select, String tag) {
		//戻り値を設定
		boolean result = false;
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			//ファイル名にパスを追加
			if(!(image_files.equals(""))) {
				image_files = "/ShareNote/upload_files/" + image_files;
			}
			if(!(text_files.equals(""))) {
				text_files = "/ShareNote/upload_files/" + text_files;
			}

			// SQL文を準備する
			String sql = "insert into NOTE values(null, ?, ?, ?, ?, ?, ?, default, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			pStmt.setString(2, image_files);
			pStmt.setString(3, text_files);
			pStmt.setInt(4, year);
			pStmt.setString(5, title);
			pStmt.setInt(6, public_select);
			pStmt.setString(7, tag);

			// SQL文を実行し、結果表を取得する
			if(pStmt.executeUpdate() == 1) {
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


//編集画面
	//ノートの編集をする(成功でtrue,失敗でfalseを返す)
	public boolean updateNote(int note_id, String image_files, String text_files,
			int year, String title, int public_select, String tag) {
		//戻り値を設定
		boolean result = false;
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			//ファイル名にパスを追加
//			if(!(image_files.equals(""))) {
//				image_files = "/ShareNote/upload_files/" + image_files;
//			}
//			if(!(text_files.equals(""))) {
//				text_files = "/ShareNote/upload_files/" + text_files;
//			}

			// SQL文を準備する
			String sql = "UPDATE note SET image_files = ?, text_files = ?, year = ?, title = ?, public_select = ?, tag = ? WHERE note_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, image_files);
			pStmt.setString(2, text_files);
			pStmt.setInt(3, year);
			pStmt.setString(4, title);
			pStmt.setInt(5, public_select);
			pStmt.setString(6, tag);
			pStmt.setInt(7, note_id);

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


	//ノートの削除をする(成功でtrue,失敗でfalseを返す)
	public boolean deleteNote(int note_id) {
		//戻り値を設定
		boolean result = false;
		//接続されるとConnectionオブジェクトが入る
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM note WHERE note_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, note_id);

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


//検索画面
	//検索内容にあった検索をする
	public List<Note> search(String[] nicknames, String[] titles, String tag, String order, String keyword) {
			Connection conn = null;
			List<Note> noteList = new ArrayList<Note>();
			int noteCount;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");


				// SQL文を準備する
				String sql = "select n.note_id, n.image_files, n.text_files, u.nickname, n.year, n.title, n.public_select ,n.favorites_num, n.tag from note as n inner join user as u on n.user_id=u.user_id WHERE tag LIKE ? "+keyword + " AND public_select = 1 ORDER BY "+order;
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる

				if (tag != null) {
					pStmt.setString(1, "%" + tag + "%");
				}
				else {
					pStmt.setString(1, "%");
				}



				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();



				// 結果表をコレクションにコピーする
				while (rs.next()) {
							Note note = new Note();
							note.setNote_id(rs.getInt("note_id"));

							note.setImage_files(rs.getString("image_files"));
							note.setText_files(rs.getString("text_files"));
							note.setYear(rs.getInt("year"));
							note.setNickname(rs.getString("nickname"));
							note.setTitle(rs.getString("title"));
							note.setPublic_select(rs.getInt("public_select"));
							note.setFavorites_num(rs.getInt("favorites_num"));
							note.setTag(rs.getString("tag"));

							noteList.add(note);
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
			return noteList;
		}

	//検索内容にあった検索をする  タグの完全一致が選択されているとき
	public List<Note> searchMatching(String[] nicknames, String[] titles, String tag, String order, String keyword) {
			Connection conn = null;
			List<Note> noteList = new ArrayList<Note>();


			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

				// SQL文を準備する
				String sql = "select  n.note_id, n.image_files, n.text_files, u.nickname, n.year, n.title, n.public_select ,n.favorites_num, n.tag from note as n inner join user as u on n.user_id=u.user_id WHERE tag = ? "+keyword+" AND public_select = 1 ORDER BY "+order;
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (tag != null) {
					pStmt.setString(1, tag);
				}
				else {
					pStmt.setString(1, "%");
				}

//

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
							Note note = new Note();
							note.setNote_id(rs.getInt("note_id"));
							note.setImage_files(rs.getString("image_files"));
							note.setText_files(rs.getString("text_files"));
							note.setYear(rs.getInt("year"));
							note.setNickname(rs.getString("nickname"));
							note.setTitle(rs.getString("title"));
							note.setPublic_select(rs.getInt("public_select"));
							note.setFavorites_num(rs.getInt("favorites_num"));
							note.setTag(rs.getString("tag"));
							noteList.add(note);
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
			return noteList;
	}
	//検索件数表示
	public List<Note> searchHit(String[] nicknames, String[] titles, String tag, String keyword) {
		Connection conn = null;
		List<Note> hitList = new ArrayList<Note>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select  count(*) as count from note as n inner join user as u on n.user_id = u.user_id WHERE tag like ? "+keyword+"AND public_select = 1 ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			if (tag != null) {
				pStmt.setString(1, "%"+tag+"%");
			}
			else {
				pStmt.setString(1, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Note note = new Note();
				 note.setNoteCount(rs.getInt("count"));
				hitList.add(note);
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



		return hitList;
	}
	public List<Note> searchHitMatching(String[] nicknames, String[] titles, String tag, String keyword) {
		Connection conn = null;
		List<Note> hitList = new ArrayList<Note>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select  count(*) as count from note as n inner join user as u on n.user_id = u.user_id WHERE tag = ? "+keyword+" AND public_select = 1 ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			if (tag != null) {
				pStmt.setString(1, tag);
			}
			else {
				pStmt.setString(1, "%");
			}




			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Note note = new Note();
				 note.setNoteCount(rs.getInt("count"));
				hitList.add(note);
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



		return hitList;
	}


//ノート詳細
//こちらもおススメを表示する


}
