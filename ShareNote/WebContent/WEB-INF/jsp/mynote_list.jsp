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

<p>マイノート一覧</p>

<c:forEach var="e" items="${noteList}" >
	<table border="1">
		<form method="POST" action="/ShareNote/Edit">
			<tr>
				<td rowspan="3">${e.image_files}<input type="hidden" name="image_files" value="${e.image_files}"></td>
				<td>${e.year}年度<input type="hidden" name="year" value="${e.year}"></td>
				<td rowspan="2">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
				<td rowspan="2" align="center"><input type="submit" name="edit" value="編集"></td>
			</tr>
			<tr>
				<td>${e.favorites_num}<input type="hidden" name="favorites_num" value="${e.favorites_num}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
				<!-- <td><input type="submit" name="download" value="ダウンロード"></td> -->
				<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
							  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
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