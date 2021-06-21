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
				<td><label><input type="checkbox" name="matching" value="matching">完全一致</label><td>
			</tr>
			<tr>
				<td><label><input type="checkbox" name="tag" value="HTML">HTML</label></td>
				<td><label><input type="checkbox" name="tag" value="CSS">CSS</label></td>
				<td><label><input type="checkbox" name="tag" value="JavaScript">JavaScript</label></td>
				<td><label><input type="checkbox" name="tag" value="Java">Java</label></td>
			</tr>
			<tr>
				<td><label><input type="checkbox" name="tag" value="SQL">SQL</label></td>
				<td><label><input type="checkbox" name="tag" value="jsp">jsp</label></td>
				<td><label><input type="checkbox" name="tag" value="Servlet">Servlet</label></td>
				<td><label><input type="checkbox" name="tag" value="DAO">DAO</label></td>
			</tr>
			<tr>
				<td><label><input type="checkbox" name="tag" value="jQuery">jQuery</label></td>
				<td><label><input type="checkbox" name="tag" value="other">その他</label></td>
				<td><label><input type="checkbox" name="all_select" value="all_select">全て選択</label></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><label><input type="text" name="keyword" placeholder="キーワード（タイトル・ニックネーム）検索"></label></td>
			</tr>
			<tr>
			 	<td colspan="4" align="center"><label><select name="sort">
						<option  selected value ="新着順">新着順</option>
						<option  value ="お気に入り順">お気に入り数順</option>
					</select>
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><label><input type="submit" name="search" value="検索" ></label></td>
			</tr>
	</table>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>