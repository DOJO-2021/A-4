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

<div class="smalltitle">
<p>マイノート一覧  ${msg}</p>
</div>

<c:forEach var="e" items="${noteList}" >
	<table border="1" class="notes">
		<form method="POST" action="/ShareNote/Edit">
			<tr>
				<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}">
				<c:if test="${!empty e.text_files}"><img src="/ShareNote/images/text_icon.png" width="20px"></c:if>
				</td>
				<td class="inf-year">${e.year}年度<input type="hidden" name="year" value="${e.year}"></td>
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
				<!-- <td><input type="submit" name="download" value="ダウンロード"></td> -->
				<c:choose><c:when test="${empty e.text_files}"><td><a href="${e.image_files}" download>ダウンロード</a></td></c:when>
							  <c:otherwise><td class="download"><a href="${e.text_files}" download>ダウンロード</a></td></c:otherwise>
						</c:choose>
			</tr>
			<input type="hidden" name="note_id" value="${e.note_id}">
			<input type="hidden" name="public_select" value="${e.public_select}">
			<input type="hidden" name="text_files" value="${e.text_files}">
		</form>
	</table>
</c:forEach>

</body>
</html>