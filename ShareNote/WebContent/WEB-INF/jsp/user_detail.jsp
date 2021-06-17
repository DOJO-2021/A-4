<!-- ユーザー詳細jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<!-- サイドメニュー -->
<h2>${nickname}さんのマイページ</h2>

<c:forEach var="e" items="${Note}">
	<form method="POST" action="/ShareNote/Note_detail">
	<table border="1">
		<tr>
			<td rowspan="3">${e.image_files}<input type="hidden" name="image_files" value="${e.image_files} "></td>
			<td>${e.year}<input type="hidden" name="title" value="${e.year} "></td>
			<td>${e.nickname}<input type="hidden" name="title" value="${e.nickname} "></td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
			</tr>
			<tr>
			<td colspan="2">${e.title}<input type="hidden" name="title" value="${e.title} "></td>
			</tr>

		<tr>
			<td>${e.tag}<input type="hidden" name="tag" value="${e.tag} "></td>
			<!-- <td><input type="submit" name="download" value="ダウンロード"></td> -->
			<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
					  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
				</c:choose>
			<td align="center"><input type="submit" name="favorite" value="★"></td>
		</tr>
		</table>
		<hr>
		</form>
</c:forEach>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
