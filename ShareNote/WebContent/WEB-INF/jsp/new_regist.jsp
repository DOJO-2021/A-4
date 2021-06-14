<!-- 新規登録jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>
${errMsg4}
<h2>ユーザー登録</h2>
<form method="POST" action="/ShareNote/New_regist">
	<table align="center">
	    <tr>
			<th>ニックネーム</th>
			<td><input type="text" name="nickname" placeholder="重複不可"></td>
			<td>${errMsg1}</td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="password" name="password" placeholder="5文字以上16文字以内"></td>
			<td>※半角英数字と記号のみ使用可</td>
		</tr>
		<tr>
			<th>パスワード(確認)</th>
			<td><input type="password" name="password_check"></td>
			<td>${errMsg2}</td>
		</tr>
		<tr>
			<th>秘密の質問</th>
			<td>
				<select name="question">
					<option value="好きな食べ物は？">好きな食べ物は？</option>
					<option value="初めて飼ったペットの名前は？">初めて飼ったペットの名前は？</option>
					<option value="初めて行った海外は？">初めて行った海外は？</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>答え</th>
			<td><input type="text" name="answer"></td>
		</tr>
		<tr>
			<td colspan="2">${errMsg3}</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" name="login" value="登録" ></td>
		</tr>
		<tr>
			<td><a href="Login.java">ログイン画面に戻る</a></td>
		</tr>
	</table>
</form>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>