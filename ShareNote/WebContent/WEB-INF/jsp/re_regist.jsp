<!-- 再設定jsp -->
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
<div class="Reregist_logo">
<img src="/ShareNote/images/logo1.png"  alt="ロゴ">
</div>

<div class="regist-whole">

<div class="Reregist-title">
${msg}
パスワードの再設定
</div>

<form method="POST" action="/ShareNote/Re_regist">
	<table align="center" class="Reregisttable">
		<tr class="regist1">
			<th>ニックネーム</th>
			<td><input type="text" name="nickname" value="${param.nickname}"></td>
		</tr>

		<tr class="regist1">
			<th>秘密の質問</th>
		<td><select  name="question">
			<option value="好きな食べ物は？">好きな食べ物は？</option>
			<option value="初めて飼ったペットの名前は？">初めて飼ったペットの名前は？</option>
			<option value="初めて行った海外は？">初めて行った海外は？</option>
			</select>
			</td>
		</tr>
		<tr class="regist1">
			<th>答え</th>
			<td><input type="text" name="answer"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="errMsg">${errMsg}</td>
		</tr>
		<tr class="regist1">
			<th>新しいパスワード</th>
			<td><input type="password" name="password" placeholder="5文字以上16文字以内"></td>
			<td>※半角英数字と記号のみ使用可</td>
		</tr>
		<tr class="regist1">
			<th>新しいパスワード(確認)</th>
			<td><input type="password" name="password2"></td>
			<td class="errMsg">${errMsg2}<br>${errMsg3}<br>${errMsg4}</td>
		</tr>
	</table>

		<div class="Reregist_button">
		<input type="submit" name="re_regist" value="登録">
		</div>

		<div class="Reregist_link">
		<a href="Login">ログイン画面に戻る</a>
		</div>

</form>
</div>
</div>
<br>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
