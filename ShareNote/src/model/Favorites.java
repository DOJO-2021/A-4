//お気に入り登録関連のデータ操作モデル
package model;
import java.io.Serializable;

public class Favorites implements Serializable {
	//フィールド
private int favorites_id;
private int note_id;
private int favorites_flag;
private String nickname;
private String image_files;
private String text_files;
private int year;
private String title;
private String tag;
private int favorites_num;
private int favoritesCount;

//getter・setter

public int getFavoritesCount() {
	return favoritesCount;
}
public void setFavoritesCount(int favoritesCount) {
	this.favoritesCount = favoritesCount;
}
public int getFavorites_id() {
	return favorites_id;
}
public void setFavorites_id(int favorites_id) {
	this.favorites_id = favorites_id;
}
public int getNote_id() {
	return note_id;
}
public void setNote_id(int note_id) {
	this.note_id = note_id;
}
public int getFavorites_flag() {
	return favorites_flag;
}
public void setFavorites_flag(int favorites_flag) {
	this.favorites_flag = favorites_flag;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
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
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}
public int getFavorites_num() {
	return favorites_num;
}
public void setFavorites_num(int favorites_num) {
	this.favorites_num = favorites_num;
}



}
