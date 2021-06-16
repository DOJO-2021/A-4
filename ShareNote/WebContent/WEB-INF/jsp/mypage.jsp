<!-- マイページjsp / 初期の見た目とif文を記述 -->
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

<!-- サイドメニュー -->
<h2>${sessionScope.nickname}さんのマイページ</h2>
<form method="GET" action="/ShareNote/Note_upload">
	<table>
	<tr>
		<td align="center"><input type="submit" name="page_switch" value="ノートのアップロード"></td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Mynote_list">
	<tr>
		<td align="center"><input type="submit" name="page_switch" value="マイノート一覧"></td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Favorites_list">
	<tr>
		<td align="center"><input type="submit" name="page_switch" value="お気に入り一覧"></td>
	</tr>
</form>
	<tr></tr>
	<tr>
		<td align="center"><input type="submit" name="page_switch" value="ログアウト"></td>
	</tr>
	</table>
</form>

<!-- マイページ初期状態 -->
<%if(request.getAttribute("isInitial").equals("yes")) { %>
	<p>最近アップロードしたノート</p>
	<!-- アップロードしたノートが0件のとき -->
	<%if(false) { %>
		<p>登録されているノートはありません</p>
	<!-- アップロードしたノートが1件以上のとき -->
	<% } else { %>
		<c:forEach var="e" items="${latestUploadNoteList}" >
			<form method="POST" action="/ShareNote/Edit">
			<table border="1">
			<tr>
				<td rowspan="3">${e.image_files}<input type="hidden" name="image_files" value="${e.image_files}"></td>
				<td>${e.year}<input type="hidden" name="year" value="${e.year}"></td>
				<td rowspan="2">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
				<td rowspan="2" align="center"><input type="submit" name="edit" value="編集"></td>
			</tr>
			<tr>
				<td>${favorites_num}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">${e.tag}</td>
				<td><input type="submit" name="download" value="ダウンロード"></td>
			</tr>
			</table>
			</form>
		</c:forEach>
	<% }%>

	<p>最近お気に入りしたノート</p>
	<!-- お気に入りしたノートが0件のとき -->
	<%if(false) { %>
		<p>登録されているノートはありません</p>
	<!-- お気に入りしたノートが1件以上のとき -->
	<% } else { %>
		<!-- 試しにforの外に書いてみる -->
		<form method="POST" action="/ShareNote/Note_detail">
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
		<table>
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
	</c:forEach>
	<%} %>

<!-- マイページ初期状態以外 -->
<%} else if(request.getAttribute("isInitial").equals("no")) {%>
	<!-- 「ノートをアップロード」が押されたとき -->
	<% if(request.getParameter("page_switch").equals("ノートのアップロード")) { %>
		<jsp:include page="/WEB-INF/jsp/note_upload.jsp"/>

	<!-- 「マイノート一覧」が押されたとき -->
	<%} else if(request.getParameter("page_switch").equals("マイノート一覧")) { %>
		<jsp:include page="/WEB-INF/jsp/mynote_list.jsp"/>

	<!-- 「お気に入り一覧」が押されたとき -->
	<%} else if(request.getParameter("page_switch").equals("お気に入り一覧")) { %>
		<jsp:include page="/WEB-INF/jsp/favorites_list.jsp"/>
	<%} %>
<%} %>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>