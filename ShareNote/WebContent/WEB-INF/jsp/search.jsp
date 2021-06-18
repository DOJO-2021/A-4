<!-- 検索jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2>検索</h2>
<form method="POST" action="/ShareNote/Search">
	<table align="center">
			<tr>
				<td><input type="checkbox" name="matching" value="matching">完全一致<td>
			</tr>
			<tr>
				<td><input type="checkbox" name="tag" value="HTML">HTML</td>
				<td><input type="checkbox" name="tag" value="CSS">CSS</td>
				<td><input type="checkbox" name="tag" value="JavaScript">JavaScript</td>
				<td><input type="checkbox" name="tag" value="Java">Java</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="tag" value="SQL">SQL</td>
				<td><input type="checkbox" name="tag" value="jsp">jsp</td>
				<td><input type="checkbox" name="tag" value="Servlet">Servlet</td>
				<td><input type="checkbox" name="tag" value="DAO">DAO</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="tag" value="jQuery">jQuery</td>
				<td><input type="checkbox" name="tag" value="other">その他</td>
				<td><input type="checkbox" name="all_select" value="all_select">全て選択</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="text" name="title" placeholder="キーワード（タイトル・ニックネーム）検索"></td>
			</tr>
			<tr>
			 	<td colspan="4" align="center"><select name="sort">
						<option  selected value ="新着順">新着順</option>
						<option  value ="お気に入り順">お気に入り数順</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" name="search" value="検索" ></td>
			</tr>
	</table>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>