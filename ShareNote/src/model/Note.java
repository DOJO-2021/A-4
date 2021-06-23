//Note関連のデータ操作モデル
package model;
import java.io.Serializable;

public class Note implements Serializable {
	//フィールド
	private int note_id;
	private int user_id;
	private String image_files;
	private String text_files;
	private int year;
	private String nickname;
	private String title;
	private int public_select;
	private int favorites_num;
	private String tag;
	private String order;
	private int noteCount;
	private int isFavorites;

	//getter・setter

	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getImage_files() {
		return image_files;
	}
	public void setImage_files(String image_files) {
		this.image_files = image_files;
	}
	public String getText_files() {
		return text_files;
	}
	public void setText_files(String text_files) {
		this.text_files = text_files;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPublic_select() {
		return public_select;
	}
	public void setPublic_select(int public_select) {
		this.public_select = public_select;
	}
	public int getFavorites_num() {
		return favorites_num;
	}
	public void setFavorites_num(int favorites_num) {
		this.favorites_num = favorites_num;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
	public int getIsFavorites() {
		return isFavorites;
	}
	public void setIsFavorites(int isFavorites) {
		this.isFavorites = isFavorites;
	}
}
