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


<!-- 試しにforの外に書いてみる -->
<form method="POST" action="/ShareNote/Note_detail">
	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}ノート画像<input type="hidden" name="image_files" value="${e.image_files} "></td>
		<td>${e.year}年度<input type="hidden" name="title" value="${e.year} "></td>
		<td align="center" colspan="2">${e.nickname}ニックネーム<input type="hidden" name="title" value="${e.nickname} "></td>
		<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
	</tr>
	<tr>
		<td align="center" colspan="3">${e.title}タイトル<input type="hidden" name="title" value="${e.title} "></td>
		<td><input type="submit" name="favorite" value="☆"></td>
	</tr>
	<tr>
		<td colspan="3" align="center">${e.tag}タグ<input type="hidden" name="tag" value="${e.tag} "></td>
		<td><input type="submit" name="download" value="ダウンロード"></td>
	</tr>
	</table>
</form>
<!-- 試しにforの外に書いてみるここまで -->



<c:forEach var="e" items="${Favorites}">
<form method="POST" action="/ShareNote/Note_detail">
	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}ノート画像<input type="hidden" name="image_files" value="${e.image_files} "></td>
		<td>${e.year}年度<input type="hidden" name="title" value="${e.year} "></td>
		<td align="center" colspan="2">${e.nickname}ニックネーム<input type="hidden" name="title" value="${e.nickname} "></td>
		<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
	</tr>
	<tr>
		<td align="center" colspan="3">${e.title}タイトル<input type="hidden" name="title" value="${e.title} "></td>
		<td><input type="submit" name="favorite" value="☆"></td>
	</tr>
	<tr>
		<td colspan="3" align="center">${e.tag}タグ<input type="hidden" name="tag" value="${e.tag} "></td>
		<td><input type="submit" name="download" value="ダウンロード"></td>
	</tr>
	</table>
</form>

</c:forEach>

</body>
</html>
