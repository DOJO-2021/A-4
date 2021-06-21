<!-- マイノート一覧jsp / マイページ一覧にインクルード -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>

<!-- 初期状態 -->
<p>お気に入り一覧</p>
<!--
<form method="POST" action="/ShareNote/Note_detail">
<!-- 試しにforの外に書いてみる -->
<!--	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}ノート画像<input type="hidden" name="image_files" value="${e.image_files} "></td>
		<td>${e.year}年度<input type="hidden" name="title" value="${e.year} "></td>
		<td align="center" colspan="2">${e.nickname}ニックネーム<input type="hidden" name="title" value="${e.nickname} "></td>
		<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
	</tr>
	<tr>
		<td align="center" colspan="3">${e.title}タイトル<input type="hidden" name="title" value="${e.title} "></td>
	</tr>
	<tr>
		<td colspan="3" align="center">${e.tag}タグ<input type="hidden" name="tag" value="${e.tag} "></td>
</form>
		<td><input type="submit" name="download" value="ダウンロード"></td>
<form method="POST" action="/ShareNote/Favorites_button">

<td><id="gazo"><input type="submit" name="favorite" value="☆" onclick="changeIMG()" src="0.png"></id></td>

</form>
	</tr>
	</table>
-->
<!-- 試しにforの外に書いてみるここまで -->


<c:forEach var="e" items="${latestFavoritesList}">
<form method="POST" action="/ShareNote/Note_detail">
	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}<input type="hidden" name="image_files" value="${e.image_files} "></td>
		<td>${e.year}年度<input type="hidden" name="year" value="${e.year} "></td>
		<td align="center" colspan="2">${e.nickname}<input type="hidden" name="nickname" value="${e.nickname} "></td>
		<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
	</tr>
	<tr>
		<td align="center" colspan="3">${e.title}<input type="hidden" name="title" value="${e.title} "></td>
<!--
</form>
		<td><id="gazo"><input type="image" name="favorite" onclick="changeIMG()" src="0.png"></id></td>
	</tr>
<form method="POST" action="/ShareNote/Note_detail">
-->
	<tr>
		<td colspan="3" align="center">${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
		<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
					  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
				</c:choose>
	</tr>
<!--
	<tr>
	<td><id="gazo"><input type="image" name="favorite" onclick="changeIMG()" src="0.png"></id></td>
	</tr>
-->
	</table>
</form>

</c:forEach>

</body>
<script>

//画像を配列に格納する
var img = new Array();

img[0] = new Image();
img[0].src = "images/0.png";
img[1] = new Image();
img[1].src = "images/1.png";


//画像番号用のグローバル変数
var cnt=0;


//画像切り替え関数
function changeIMG(){

  //画像番号を進める
  if (cnt == 1)
  { cnt=0; }
  else
  { cnt++; }

  //画像を切り替える
  document.getElementById("gazo").src=img[cnt].src;
}
</script>
</html>
