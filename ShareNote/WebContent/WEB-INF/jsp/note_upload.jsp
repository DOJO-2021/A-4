<!-- ノートのアップロードjsp / マイページjspにインクルード -->
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
<!-- タグのチェックボックスのための処理 -->
<!-- 各タグの名前で、初期値0の変数を用意 -->
<c:set var="HTML" value="0"/>
<c:set var="CSS" value="0"/>
<c:set var="JavaScript" value="0"/>
<c:set var="Java" value="0"/>
<c:set var="SQL" value="0"/>
<c:set var="jsp" value="0"/>
<c:set var="Servlet" value="0"/>
<c:set var="DAO" value="0"/>
<c:set var="jQuery" value="0"/>
<c:set var="その他" value="0"/>

<!-- タグの配列を回し、一致したら上の変数を1に上書き -->
<c:forEach var="tag" items="${tags}">
	<c:if test="${tag == 'HTML'}">
		<c:set var="HTML" value="1"/>
	</c:if>
	<c:if test="${tag == 'CSS'}">
		<c:set var="CSS" value="1"/>
	</c:if>
	<c:if test="${tag == 'JavaScript'}">
		<c:set var="JavaScript" value="1"/>
	</c:if>
	<c:if test="${tag == 'Java'}">
		<c:set var="Java" value="1"/>
	</c:if>
	<c:if test="${tag == 'SQL'}">
		<c:set var="SQL" value="1"/>
	</c:if>
	<c:if test="${tag == 'jsp'}">
		<c:set var="jsp" value="1"/>
	</c:if>
	<c:if test="${tag == 'Servlet'}">
		<c:set var="Servlet" value="1"/>
	</c:if>
	<c:if test="${tag == 'DAO'}">
		<c:set var="DAO" value="1"/>
	</c:if>
	<c:if test="${tag == 'jQuery'}">
		<c:set var="jQuery" value="1"/>
	</c:if>
	<c:if test="${tag == 'その他'}">
		<c:set var="その他" value="1"/>
	</c:if>
</c:forEach>

<div class="smalltitle">
<p>ノートのアップロード ${dbEerrMsg}</p>
</div>

	<form method="POST" name="form" action="/ShareNote/Note_upload" enctype="multipart/form-data">
	<table border="1" class="up_note">
	<tr>
		<td class="guide">1.ファイル登録（いずれか必須）</td>
		<td colspan="4" class="guide">2.タイトルの記入（必須）</td>
	</tr>
	<tr>
		<td class="file-guide1" >  画像ファイル（.jpg、.png）はこちら</td>
		<td rowspan="2" colspan="4" height="70em"><input type="text" name="title" value="${param.title}" maxlength="50" style="width:30em;"></td>
	</tr>
	<tr>
		<td rowspan="5"><canvas id="preview"></canvas></td>


	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="4" class="guide">3.タグの選択（必須）</td>
	</tr>
	<tr>
			<td><label><input type="checkbox" name="tag" value="HTML" onClick="DisChecked()"
			<c:if test="${HTML == '1'}">checked</c:if>>HTML</label></td>
		<td><label><input type="checkbox" name="tag" value="CSS" onClick="DisChecked()"
			<c:if test="${CSS == '1'}">checked</c:if>>CSS</label></td>
		<td><label><input type="checkbox" name="tag" value="JavaScript" onClick="DisChecked()"
			<c:if test="${JavaScript == '1'}">checked</c:if>>JavaScript</label></td>
		<td><label><input type="checkbox" name="tag" value="Java" onClick="DisChecked()"
			<c:if test="${Java == '1'}">checked</c:if>>Java</label></td>
	</tr>
	<tr>
		<td><label><input type="checkbox" name="tag" value="SQL" onClick="DisChecked()"
			<c:if test="${SQL == '1'}">checked</c:if>>SQL</label></td>
		<td><label><input type="checkbox" name="tag" value="jsp" onClick="DisChecked()"
			<c:if test="${jsp == '1'}">checked</c:if>>jsp</label></td>
		<td><label><input type="checkbox" name="tag" value="Servlet" onClick="DisChecked()"
			<c:if test="${Servlet == '1'}">checked</c:if>>Servlet</label></td>
		<td><label><input type="checkbox" name="tag" value="DAO" onClick="DisChecked()"
			<c:if test="${DAO == '1'}">checked</c:if>>DAO</label></td>
	</tr>
	<tr class="tag3">
		<td><input type="file" name="image_files" accept="image/jpeg, image/png" onchange="previewImage(this);">${image_files}</td>
		<td><label><input type="checkbox" name="tag" value="jQuery" onClick="DisChecked()"
			<c:if test="${jQuery == '1'}">checked</c:if>>jQuery</label></td>
		<td><label><input type="checkbox" name="tag" value="その他" onClick="DisChecked()"
			<c:if test="${その他 == '1'}">checked</c:if>>その他</label></td>
		<td><label><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</label></td>
	</tr>
	<tr>
		<td class="file-guide2">テキストファイル（.docx、.txt）はこちら</td>
		<td  colspan="4" class="guide" >4.公開設定</td>
	</tr>
	<tr class="tag3">
		<td><input type="file" name="text_files" accept=" .docx, .txt"></td>
		<td colspan="4"><label><input type="radio" name="public_select" value="1" checked>公開</label>
			<label><input type="radio" name="public_select" value="0">非公開</label></td>
	</tr>
	</table>
		<p>${errMsg}</p>
		<div align="center">
		<input type="submit" name="upload" value="アップロード" >
		</div>
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
    var checks = document.form.tag;
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

	//画像のプレビューを表示する
	function previewImage(obj){

		var fileReader = new FileReader();

		// 読み込み後に実行する処理
		fileReader.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas = document.getElementById('preview');
			var ctx = canvas.getContext('2d');
			var image = new Image();
			image.src = fileReader.result;
			console.log(fileReader.result) // ← (確認用)

			image.onload = (function () {
				canvas.width = image.width;
				canvas.height = image.height;
				ctx.drawImage(image, 0, 0);
			});
		});
		// 画像読み込み
		fileReader.readAsDataURL(obj.files[0]);
		console.log(fileReader.result) // ← (確認用)null
	}
</script>
</html>