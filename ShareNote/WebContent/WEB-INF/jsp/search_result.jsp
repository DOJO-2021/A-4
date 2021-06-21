<!-- 検索結果jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />


<h2>検索結果</h2>
<form method="POST" action="/ShareNote/Search">
	<table align="center">
			<tr>
				<td><label><input type="checkbox" name="matching" value="matching">完全一致</label><td>
			</tr>
			<tr>
				<td><label><input type="checkbox" name="tag" value="HTML">HTML</label>></td>
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
				<td colspan="4" align="center"><label><input type="text" name="keyword" placeholder="キーワード（タイトル・ニックネーム）検索" ></label></td>
			</tr>
			<tr>
			 	<td colspan="4" align="center"><label><select name="sort">
						<option value ="新着順">新着順</option>
						<option value="お気に入り順">お気に入り順</option>
					</select>
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" name="search" value="検索" ></td>
			</tr>
	</table>
</form>
<form action ="POST"  >
	<select name=sort>
	<option value="新着順">新着順</option>
	<option value="お気に入り順">お気に入り順</option>
	</select>
</form>ヒット数：
<c:forEach var ="e" items="${noteList}">


<table border="1">
		<tr>
			<td rowspan="3">${e.image_files }</td>
			<td>
			${e.year}年度
			</td>
			<td>
			${e.nickname}
			</td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
			</tr>
			<tr>
			<td colspan="2">${e.title}</td>
			</tr>

		<tr>
			<td>${e.tag}</td>
			<!--  <td><input type="submit" name="download" value="ダウンロード"></td> -->
			<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
					  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
				</c:choose>
			<td align="center"><input type="submit" name="favorite" value="★"></td>
		</tr>
		</table>
		<hr>

</c:forEach>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>

</html>