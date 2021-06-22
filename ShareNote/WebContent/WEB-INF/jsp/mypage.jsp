<!-- マイページjsp / 初期の見た目とif文を記述 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
<link rel="stylesheet" href="/ShareNote/css/common.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="whole">

<div class="menu">
<!-- サイドメニュー -->
<h2>${nickname}さんのマイページ</h2>
<form method="GET" action="/ShareNote/Note_upload" name="inform1">
	<table>
	<tr>
		<td align="center">
			<input type="image" src="images/button1.png" height="60"  name="page_switch" value="ノートのアップロード" onclick="document.inform1.para.value='ノートのアップロード'">
			<input type="hidden"  name="para" value="">
		</td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Mynote_list" name="inform2">
	<tr>
		<td align="center">
		<input type="image" src="images/button2.png"  height="60" name="page_switch" value="マイノート一覧" onclick="document.inform2.para.value='マイノート一覧'">
		<input type="hidden"  name="para" value="">
		</td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Favorites_list" name="inform3">
	<tr>
		<td align="center">
		<input type="image" src="images/button3.png" height="60" name="page_switch" value="お気に入り一覧" onclick="document.inform2.para.value='お気に入り一覧'">
		<input type="hidden"  name="para" value="">
		</td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Logout" name="inform4">
	<tr>
		<td align="center">
		<input type="image" src="images/button4.png" height="60" name="page_switch" value="ログアウト" onclick="document.inform4.para.value='ログアウト'">
		<input type="hidden"  name="para" value="">
		</td>
	</tr>
	</table>
</form>
</div>
<!-- マイページ初期状態 -->


<div class="information">
<%if(request.getAttribute("isInitial").equals("yes")) { %>
	<p>最近アップロードしたノート</p>
	<p>${uploadMsg}</p>
	<c:forEach var="e" items="${latestUploadNoteList}">
		<table border="1" class="notes">
			<form method="POST" action="/ShareNote/Edit">
				<tr>
					<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}"></td>
					<td class="inf-year">${e.year}<input type="hidden" name="year" value="${e.year}"></td>
					<td rowspan="2" class="inf-title">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
					<td rowspan="2" align="center"><input type="submit" name="edit" value="編集"></td>
				</tr>
				<tr>
					<td class="fav-num">お気に入り数${e.favorites_num}</td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="inf-tag">${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
					<!--  <td><input type="submit" name="download" value="ダウンロード"></td> -->
					<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
							  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
						</c:choose>
				</tr>
				<input type="hidden" name="note_id" value="${e.note_id}">
				<input type="hidden" name="public_select" value="${e.public_select}">
				<input type="hidden" name="text_files" value="${e.text_files}">
			</form>
		</table>
		<br>
	</c:forEach>

	<p>最近お気に入りしたノート</p>
	<p>${favoritesMsg}</p>
	<c:forEach var="e" items="${latestFavoritesList}" >
		<table border="1" class="notes">
			<form method="POST" action="/ShareNote/Note_detail">
				<tr>
					<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}"></td>
					<td class="inf-year">${e.year}<input type="hidden" name="year" value="${e.year}"></td>
					<td class="inf-nn">${e.nickname}<input type="hidden" name="nickname" value="${e.nickname} "></td>
					<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
				</tr>
				<tr>
					<td colspan="2" class="inf-title">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="inf-tag">${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
					<!-- <td><input type="submit" name="download" value="ダウンロード"></td> -->
					<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
					 	 <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
					</c:choose>
				</tr>
			</form>
		</table>
		<br>
	</c:forEach>

<!-- マイページ初期状態以外 -->
<%} else if(request.getAttribute("isInitial").equals("no")) {%>
	<!-- 「ノートをアップロード」が押されたとき -->
	<% if(request.getAttribute("page_switch").equals("ノートのアップロード")) { %>
		<jsp:include page="/WEB-INF/jsp/note_upload.jsp"/>

	<!-- 「マイノート一覧」が押されたとき -->
	<%} else if(request.getAttribute("page_switch").equals("マイノート一覧")) { %>
		<jsp:include page="/WEB-INF/jsp/mynote_list.jsp"/>

	<!-- 「お気に入り一覧」が押されたとき -->
	<%} else if(request.getAttribute("page_switch").equals("お気に入り一覧")) { %>
		<jsp:include page="/WEB-INF/jsp/favorites_list.jsp"/>
	<%} %>
<%} %>
</div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>