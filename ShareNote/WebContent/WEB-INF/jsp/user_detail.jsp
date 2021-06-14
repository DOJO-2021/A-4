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


	<form method="POST" action="/ShareNote/Note_detail">
	<table border="1">
		<tr>
			<td rowspan="3">${e.image_files}ノート画像</td>
			<td>
			${e.year}年度
			</td>
			<td>
			${e.nickname}ニックネーム
			</td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
			</tr>
			<tr>
			<td colspan="2">${e.title}タイトル</td>
			</tr>

		<tr>
			<td>${e.tag}タグ</td>
			<td><input type="submit" name="download" value="ダウンロード"></td>
			<td><input type="submit" name="favorite" value="★"></td>
		</tr>
		</table>
		<hr>
		</form>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
