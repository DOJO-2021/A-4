<!-- 編集画面jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>
<form method="POST" name="form" action="/ShareNote/Edit">
<p>ノートの編集</p>
	<table border="1">
	<tr>
		<td>1.ファイルの変更</td>
	</tr>
	<tr>
		<td>  画像ファイル（.jpg、.png）はこちら</td>
		<td colspan="1">2.タイトルの変更</td>
	</tr>
	<tr>
		<td rowspan="5" height="300em" width="600em">${param.image_files}</td>
		<td colspan="1">${param.year}</td>
		<td rowspan="2" colspan="3" height="80em" width="500em"><input type ="text" name="title" value="${param.title}"></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="4">3.タグの変更</td>
	</tr>
   	<tr>
		<td><input type="checkbox" name="tag" value="linux1" onClick="DisChecked()" <c:if test="${param.tag == 'HTML'}">checked</c:if>>HTML</td>
		<td><input type="checkbox" name="tag" value="linux2" onClick="DisChecked()"<c:if test="${param.tag == 'CSS'}">checked</c:if>>CSS</td>
		<td><input type="checkbox" name="tag" value="linux3" onClick="DisChecked()" <c:if test="${param.tag == 'JavaScript'}">checked</c:if>>JavaScript</td>
		<td><input type="checkbox" name="tag" value="linux4" onClick="DisChecked()" <c:if test="${param.tag == 'Java'}">checked</c:if>>Java</td>
	</tr>
	<tr>
		<td><input type="checkbox" name="tag" value="linux5" onClick="DisChecked()" <c:if test="${param.tag == 'SQL'}">checked</c:if>>SQL</td>
		<td><input type="checkbox" name="tag" value="linux6" onClick="DisChecked()" <c:if test="${param.tag == 'JSP'}">checked</c:if>>jsp</td>
		<td><input type="checkbox" name="tag" value="linux7" onClick="DisChecked()"<c:if test="${param.tag == 'Servlet'}">checked</c:if>> Servlet</td>
		<td><input type="checkbox" name="tag" value="linux8" onClick="DisChecked()"<c:if test="${param.tag == 'DAO'}">checked</c:if>>DAO</td>
	</tr>

	<tr>
		<td><input type="file" name="datafile" accept="image/jpeg, image/png"></td>
		<td><input type="checkbox" name="tag" value="linux9" onClick="DisChecked()" <c:if test="${param.tag == ''}">checked</c:if>>jQuery</td>
		<td><input type="checkbox" name="tag" value="linux10" onClick="DisChecked()">その他</td>
		<td><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</td>
	</tr>
	<tr>
		<td>テキストファイル（.docx、.txt）はこちら</td>
		<td>4.公開設定の変更</td>
	</tr>
	<tr>
		<td><input type="file" name="datafile" accept=" .docx, .txt">${param.text_files}</td>
		<td><input type="radio" name="public" value="open"<c:if test="${param.public_select == 1}">checked</c:if>> 公開<input type="radio" name="public" value="close"<c:if test="${param.public_select == 0}">checked</c:if>> 非公開</td>
	</tr>
	</table>
		<input type="submit" name="edit" value="ノート削除">
		<input type="submit" name="edit" value="編集を完了">
		<a href="#" onclick="window.history.go(-1); return false;">マイノート一覧へ戻る</a>
</form>

</body>
<script>
  // 「全て選択」チェックで全てにチェック付く
  function AllChecked(){
    var all = document.form.all.checked;
    for (var i=0; i<document.form.tag.length; i++){
      document.form.tag[i].checked = all;
    }
  }

  // 一つでもチェックを外すと「全て選択」のチェック外れる
  function DisChecked(){
    var checks = document.form.test;
    var checksCount = 0;
    for (var i=0; i<checks.length; i++){
      if(checks[i].checked == false){
        document.form.all.checked = false;
      }else{
        checksCount += 1;
        if(checksCount == checks.length){
          document.form.all.checked = true;
        }
      }
    }
  }

</script>
</html>