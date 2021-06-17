<!-- ノート詳細jsp -->
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

<h2>ノート詳細</h2>
<form method="POST" name="form" action="/ShareNote/Note_detail">
	<table align="center" border="1">
	<tr>
		<td rowspan="3">${param.image_files}ノート画像</td>
		<td>${param.year}年度</td>
		<td align="center" colspan="2">${param.nickname}ニックネーム</td>
	</tr>
	<tr>
		<td align="center" colspan="3">${param.title}タイトル</td>
	</tr>
	<tr>
		<td>${param.tag}タグ</td>
		<td><input type="submit" name="favorite" value="☆"></td>
		<td><input type="submit" name="download" value="ダウンロード"></td>
	</tr>
	</table>
</form>

<!-- お気に入り一覧から遷移してきたとき、お気に入り一覧に戻す -->
<%if(true) { %>
<a href="/ShareNote/Favorites_list">1つ前のページに戻る</a>
<!-- 検索結果画面から遷移してきたとき、検索結果画面に戻す -->
<%} else { %>
<a href="/ShareNote/Search_result">1つ前のページに戻る</a>
<%} %>

<hr>

<h3>こちらもおすすめ</h3>

	<!-- 試しにforの外に書いてみる -->
	<form method="POST" action="/ShareNote/Note_detail">
		<table align="center" border="1">
		<tr>
			<td rowspan="2">${e.note_files}ノート画像</td>
			<td>${e.title}タイトル</td>
			<td><input type="submit" name="detail" value="詳細"></td>
		</tr>
		<tr>
			<td>${e.tag}タグ</td>
			<td><input type="submit" name="download" value="ダウンロード"></td>
		</tr>
		</table>
	</form>
	<!-- 試しにforの外に書いてみる -->

<c:forEach var="e" items="${cardList}">
	<form method="POST" action="/ShareNote/Note_detail">
		<table width="10px">
		<tr>
			<td rowspan="2">${e.note_files}ノート画像</td>
			<td>${e.title}</td>
			<td><input type="submit" name="detail" value="詳細"></td>
		</tr>
		<tr>
			<td>${e.tag}</td>
			<td><input type="submit" name="download" value="ダウンロード"></td>
		</tr>
		</table>
	</form>
</c:forEach>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>