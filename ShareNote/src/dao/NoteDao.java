//Note関連のデータアクセス用DAO
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

//マイページ画面
	//マイページに最近アップロードしたノートを3件ほど表示する
	public List<String> selectLatestUpload(String nickname) {
		//接続されるとConnectionオブジェクトが入る→切断するときに必要
		Connection conn = null;
		//検索結果を入れる配列を用意
		List<String> cardList = new ArrayList<String>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する☆
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/ShareNote", "sa", "");

			// SQL文を準備する
			String sql = "select count(*) from USER where NICKNAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, nickname);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする	（javaの構文で返すため書き換え）
			// rs.next():次の要素があったらtrue
			while (rs.next()) {
				String card = new String(
				);
				cardList.add(card);
			}
		}

		//例外
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
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
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}



//マイページに最近お気に入り登録したノートを3件ほど表示する


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
