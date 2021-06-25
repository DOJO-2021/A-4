<!-- 新規登録jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ShareNote/css/common.css">
<title>mynote++</title>
</head>
<body>
<div class="regist_wrapper">
<div class="login_logo">
<img src="/ShareNote/images/logo1.png"  alt="ロゴ">
</div>

<div class="regist-whole">

<div class="regist-title">
<p class="errMsg">${errMsg6}</p>
ユーザー登録
</div>

<form method="POST" action="/ShareNote/New_regist" id='form' name="inform">
	<table align="center" class="registtable">
	    <tr>
			<th>ニックネーム</th>
			<td><input type="text" name="nickname" placeholder="重複不可" id="nickname" value="${param.nickname}"></td>
			<td class="errMsg">${errMsg1}</td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="password" name="password" placeholder="5文字以上16文字以内" id="password"></td>
			<td>※半角英数字と記号のみ使用可</td>
		</tr>
		<tr>
			<th>パスワード(確認)</th>
			<td><input type="password" name="password_check" id="passconf"></td>
			<td class="errMsg">${errMsg2}<br>${errMsg3}<br>${errMsg4}</td>
		</tr>
		<tr>
			<th>秘密の質問</th>
			<td style="text-align:center;">
				<select name="question">
					<option value="好きな食べ物は？">好きな食べ物は？</option>
					<option value="初めて飼ったペットの名前は？">初めて飼ったペットの名前は？</option>
					<option value="初めて行った海外は？">初めて行った海外は？</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>答え</th>
			<td><input type="text" name="answer" id="answer"></td>
		</tr>
		<tr>
			<td><div style = "color:#ff0000" id = "errMsg"></div></td>
		</tr>
		</table>

			<input type="submit" name="login" value="登録" onclick="return onRegist()"  >


			<a href="Login">ログイン画面に戻る</a>


</form>
<br><br>
</div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<script src="script/common.js"></script>
</body>
</html>
