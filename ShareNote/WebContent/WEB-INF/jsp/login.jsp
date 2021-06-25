<!-- ログインjsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ShareNote/css/common.css">
<title>mynote++</title>
<!--
<style>

*{

outline: 1px solid #ff0000;

}

</style>
-->
</head>
<body>
<div class="login-wrapper">
<div class="login_logo">
<img src="/ShareNote/images/logo1.png"  alt="ロゴ">
</div>

<div class="login-whole">
<div class="login-title">
${msg}
ログイン
</div>
<form method="POST" action="/ShareNote/Login">
	<table align="center" class="logintable">
		<tr class="login1">
			<th>ニックネーム</th>
			<td><input type="text" name="nickname" placeholder="重複不可" value="${param.nickname}"></td>
		</tr>
		<tr class="login2">
			<th>パスワード</th>
			<td><input type="password" name="password" placeholder="5文字以上16文字以内"></td>
		</tr>
		<tr>
			<td colspan="2" class="login_errMsg">${errMsg}</td>
		</tr>
		</table>

		<div class="login_button">
		<input type="submit" name="login" value="ログイン" ><br>
		</div>

		<div class="login_link">

		<div class="login_link1">
		<a href="New_regist">新規登録</a><br>
		</div>

		<div class="login_link2">
		<a href="Re_regist">パスワードを忘れた方</a><br>
		</div>

		</div>


</form>
</div>
</div>
<br><br><br>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
