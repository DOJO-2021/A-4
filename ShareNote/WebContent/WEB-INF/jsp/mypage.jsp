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
			<div class="image1">
			<input type="hidden" name="count1" value="${count1}" id="count1">
			<input type="image" src="images/button${count1}.png"  name="page_switch" value="ノートのアップロード" id="noteUpload" onclick="note(upload)">

			</div>
		</td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Mynote_list" name="inform2">
	<tr>
		<td align="center">
		<div class="image2">
		<input type="hidden" name="count2" value="${count2}" id="count2">
		<input type="image" src="images/button${count2}.png" name="page_switch" value="マイノート一覧" id="noteList" onclick="note(list)">

		</div>
		</td>
	</tr>
</form>

<form method="GET" action="/ShareNote/Favorites_list" name="inform3">
	<tr>
		<td align="center">
		<div class="image3">
		<input type="hidden" name="count3" value="${count3}" id="count3">
		<input type="image" src="images/button${count3}.png" name="page_switch" value="お気に入り一覧" id="noteFavorites" onclick="note(favorites)">

		</td>
		</div>
	</tr>
</form>

<form method="GET" action="/ShareNote/Logout" name="inform4">
	<tr>
		<td align="center">
		<div class="image4">
		<input type="image" src="images/button4.png"  name="page_switch" value="ログアウト" onclick="document.inform4.para.value='ログアウト'">
		<input type="hidden"  name="para" value="">
		</td>
		</div>
	</tr>
	</table>
</form>
</div>
<!-- マイページ初期状態 -->


<div class="information">
<%if(request.getAttribute("isInitial").equals("yes")) { %>
	<div class="title2">
	<p>最近アップロードしたノート</p>
	</div>
	<p>${uploadMsg}</p>
	<c:forEach var="e" items="${latestUploadNoteList}">
		<table border="1" class="notes">
			<form method="POST" action="/ShareNote/Edit">
				<tr>
					<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}">
					<c:if test="${!empty e.text_files}"><img src="/ShareNote/images/text_icon.png" width="20px"></c:if>
					</td>
					<td class="inf-year">${e.year}<input type="hidden" name="year" value="${e.year}"></td>
					<td rowspan="2" align="center" class="inf-title">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
					<td rowspan="2" align="center"><input type="submit" name="edit" value="編集"></td>
				</tr>
				<tr>
					<c:choose><c:when test = "${0 != e.public_select }"><td class="fav-num"><span class="fav">★</span>${e.favorites_num}</td></c:when>
							  <c:otherwise><td>非公開</td></c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2" align="center" class="inf-tag">#　${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
					<!--  <td><input type="submit" name="download" value="ダウンロード"></td> -->
					<c:choose><c:when test="${empty e.text_files}"><td><a href="${e.image_files}" download>ダウンロード</a></td></c:when>
							  <c:otherwise><td><a href="${e.text_files}" download>ダウンロード</a></td></c:otherwise>
						</c:choose>
				</tr>
				<input type="hidden" name="note_id" value="${e.note_id}">
				<input type="hidden" name="public_select" value="${e.public_select}">
				<input type="hidden" name="text_files" value="${e.text_files}">
			</form>
		</table>
		<br>
	</c:forEach>

<div class="subfavorite">
<div class="title2">
<p>最近お気に入りしたノート</p>
</div>
</div>
	<p>${favoritesMsg}</p>
	<c:forEach var="e" items="${latestFavoritesList}" >
		<table border="1" class="notes">
			<form method="POST" action="/ShareNote/Note_detail">
				<tr>
					<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}"></td>
					<td class="inf-year">${e.year}<input type="hidden" name="year" value="${e.year}"></td>
					<td class="inf-nn" align="center">
					${e.nickname}<input type="hidden" name="nickname" value="${e.nickname} ">
					<input type="hidden" name="note_id" value="${e.note_id }">
					</td>
					<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="inf-title">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="inf-tag">#　${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
					<!-- <td><input type="submit" name="download" value="ダウンロード"></td> -->
					<c:choose><c:when test="${empty e.text_files}"><td><a href="${e.image_files}" download>ダウンロード</a></td></c:when>
					 	 <c:otherwise><td><a href="${e.text_files}" download>ダウンロード</a></td></c:otherwise>
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