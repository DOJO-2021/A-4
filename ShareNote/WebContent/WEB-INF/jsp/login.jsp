<!-- ログインjsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>
<img src="/ShareNote/images/logo1.png"  alt="ロゴ">
${msg}
<h2 align="center">ログイン</h2>
<form method="POST" action="/ShareNote/Login">
	<table align="center">
		<tr>
			<th>ニックネーム</th>
			<td><input type="text" name="nickname" placeholder="重複不可" required></td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="password" name="password" placeholder="5文字以上16文字以内" required></td>
		</tr>
		<tr>
			<td colspan="2">${errMsg}</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" name="login" value="ログイン" ></td>
		</tr>
		<tr>
			<td><a href="New_regist">新規登録</a></td>
			<td><a href="Re_regist">パスワードを忘れた方</a></td>
		</tr>
	</table>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
