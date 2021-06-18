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
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<form method="POST" name="form" action="/ShareNote/Edit" enctype="multipart/form-data">
<p>ノートの編集 ${msg} ${dbErrMsg}</p>
	<input type="hidden" name="note_id" value="${param.note_id}">
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
		<td><label><input type="checkbox" name="tag" value="HTML" onClick="DisChecked()" <c:if test="${param.tag == 'HTML'}">checked</c:if>>HTML</label></td>
		<td><label><input type="checkbox" name="tag" value="CSS" onClick="DisChecked()"<c:if test="${param.tag == 'CSS'}">checked</c:if>>CSS</label></td>
		<td><label><input type="checkbox" name="tag" value="JavaScript" onClick="DisChecked()" <c:if test="${param.tag == 'JavaScript'}">checked</c:if>>JavaScript</label></td>
		<td><label><input type="checkbox" name="tag" value="Java" onClick="DisChecked()" <c:if test="${param.tag == 'Java'}">checked</c:if>>Java</label></td>
	</tr>
	<tr>
		<td><label><input type="checkbox" name="tag" value="SQL" onClick="DisChecked()" <c:if test="${param.tag == 'SQL'}">checked</c:if>>SQL</label></td>
		<td><label><input type="checkbox" name="tag" value="jsp" onClick="DisChecked()" <c:if test="${param.tag == 'JSP'}">checked</c:if>>jsp</label></td>
		<td><label><input type="checkbox" name="tag" value="Servlet" onClick="DisChecked()"<c:if test="${param.tag == 'Servlet'}">checked</c:if>> Servlet</label></td>
		<td><label><input type="checkbox" name="tag" value="DAO" onClick="DisChecked()"<c:if test="${param.tag == 'DAO'}">checked</c:if>>DAO</label></td>
	</tr>

	<tr>
		<td><label><input type="file" name="image_files" accept="image/jpeg, image/png"></label></td>
		<td><label><input type="checkbox" name="tag" value="jQuery" onClick="DisChecked()" <c:if test="${param.tag == ''}">checked</c:if>>jQuery</label></td>
		<td><label><input type="checkbox" name="tag" value="その他" onClick="DisChecked()">その他</label></td>
		<td><label><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</label></td>
	</tr>
	<tr>
		<td>テキストファイル（.docx、.txt）はこちら</td>
		<td>4.公開設定の変更</td>
	</tr>
	<tr>
		<td><input type="file" name="text_files" accept=" .docx, .txt">${param.text_files}</td>
		<td><label><input type="radio" name="public_select" value="1"<c:if test="${param.public_select == 1}">checked</c:if>> 公開</label>
		<label><input type="radio" name="public_select" value="0"<c:if test="${param.public_select == 0}">checked</c:if>> 非公開</label></td>
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