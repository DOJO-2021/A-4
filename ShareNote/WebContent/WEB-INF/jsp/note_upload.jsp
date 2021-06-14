<!-- ノートのアップロードjsp / マイページjspにインクルード -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>


<!-- 初期状態 -->
<p>ノートのアップロード</p>

	<form method="POST" action="/ShareNote/Edit">
	<table border="1">
	<tr>
		<td>1.ファイル登録（いずれか必須）</td>
	</tr>
	<tr>
		<td>  画像ファイル（.jpg、.png）はこちら</td>
		<td>2.タイトルの記入（必須）</td>
	</tr>
	<tr>
		<td rowspan="4">${e.image_files}ノート画像</td>

		<td rowspan="3">${e.title}タイトル</td>
	</tr>
	<tr>
		<td>3.タグの選択（必須）</td>
	</tr>
	<tr>
		<td><input type="checkbox" name="tag" value="linux">HTML</td>
		<td><input type="checkbox" name="tag" value="linux">CSS</td>
		<td><input type="checkbox" name="tag" value="linux">JavaScript</td>
		<td><input type="checkbox" name="tag" value="linux">Java</td>
	</tr>
	<tr>
		<td><input type="checkbox" name="tag" value="linux">SQL</td>
		<td><input type="checkbox" name="tag" value="linux">jsp</td>
		<td><input type="checkbox" name="tag" value="linux">Servlet</td>
		<td><input type="checkbox" name="tag" value="linux">DAO</td>
	</tr>
	<tr>
		<td><input type="checkbox" name="tag" value="linux">jQuery</td>
		<td><input type="checkbox" name="tag" value="linux">その他</td>
		<td><input type="checkbox" name="tag_select" value="linux">全て選択</td>
	</tr>
	<tr>

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


<c:forEach var="e" items="${cardList}" >
	<form method="POST" action="/ShareNote/Edit">

	</form>

</c:forEach>

</body>
</html>