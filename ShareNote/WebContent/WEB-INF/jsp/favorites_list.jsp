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
<jsp:include page="/WEB-INF/jsp/header.jsp"/>


<!-- 初期状態 -->
<p>お気に入り一覧</p>

	<!-- 試しにforの外に書いてみる -->
	<form method="POST" action="/ShareNote/Edit">
	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}ノート画像</td>
		<td>${e.year}年度</td>
		<td rowspan="2">${e.title}タイトル</td>
		<td rowspan="2" align="center"><input type="submit" name="edit" value="編集"></td>
	</tr>
	<tr>
		<td>${favorites_num}お気に入り数</td>
	</tr>
	<tr>
		<td colspan="2" align="center">${e.tag}タグ</td>
		<td><input type="submit" name="download" value="ダウンロード"></td>
	</tr>
	</table>
	</form>
	<!-- 試しにforの外に書いてみるここまで -->


	<c:forEach var="e" items="${cardList}" >
	<form method="POST" action="/ShareNote/Edit">
	<table border="1">
	<tr>
		<td rowspan="3">${e.image_files}ノート画像</td>
		<td>${e.year}年度</td>
		<td colspan="2">${e.title}タイトル</td>
		<td colspan="2"><input type="submit" name="edit" value="編集"></td>
	</tr>
	<tr>
		<td>${favorites_num}お気に入り数</td>
	</tr>
	<tr>
		<td>${e.tag}タグ</td>
		<td><input type="submit" name="download" value="ダウンロード"></td>
	</tr>
	</table>
	</form>
</c:forEach>


</body>
</html>