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
			//System.out.println(rs);
			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			while (rs.next()) {
				Note note = new Note(
						rs.getInt("note_id"),
						rs.getInt("user_id"),
						rs.getString("image_files"),
						rs.getString("text_files"),
						rs.getInt("year"),
						rs.getString("title"),
						rs.getInt("public_select"),
						rs.getInt("favorites_num"),
						rs.getString("tag")
						);
				noteList.add(note);
			}
			System.out.println(noteList);
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
				Note note = new Note(
						rs.getInt("note_id"),
						rs.getInt("user_id"),
						rs.getString("image_files"),
						rs.getString("text_files"),
						rs.getInt("year"),
						rs.getString("title"),
						rs.getInt("public_select"),
						rs.getInt("favorites_num"),
						rs.getString("tag")
						);
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



//ノートアップロード画面
//ノートをアップロードする


//マイノート一覧
//マイノートを表示する


//編集画面
//ノートの編集をする


//ノートの削除をする


//検索画面
//検索内容にあった検索をする


//ノート詳細
//こちらもおススメを表示する


}
