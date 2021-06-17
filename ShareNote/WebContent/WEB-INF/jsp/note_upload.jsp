<!-- ノートのアップロードjsp / マイページjspにインクルード -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>


<p>ノートのアップロード ${dbEerrMsg}</p>

	<form method="POST" name="form" action="/ShareNote/Note_upload" enctype="multipart/form-data">
	<table border="1">
	<tr>
		<td>1.ファイル登録（いずれか必須）</td>
	</tr>
	<tr>
		<td>  画像ファイル（.jpg、.png）はこちら</td>
		<td colspan="4">2.タイトルの記入（必須）</td>
	</tr>
	<tr>
		<td rowspan="5"><canvas id="preview" style="max-width:200px;"></canvas></td>

		<td rowspan="2" colspan="4" height="80em"><input type="text" name="title"></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="4">3.タグの選択（必須）</td>
	</tr>
   	<tr>
		<td><label><input type="checkbox" name="tag" value="HTML" onClick="DisChecked()">HTML</label></td>
		<td><label><input type="checkbox" name="tag" value="CSS" onClick="DisChecked()">CSS</label></td>
		<td><label><input type="checkbox" name="tag" value="JavaScript" onClick="DisChecked()">JavaScript</label></td>
		<td><label><input type="checkbox" name="tag" value="Java" onClick="DisChecked()">Java</label></td>
	</tr>
	<tr>
		<td><label><input type="checkbox" name="tag" value="SQL" onClick="DisChecked()">SQL</label></td>
		<td><label><input type="checkbox" name="tag" value="jsp" onClick="DisChecked()">jsp</label></td>
		<td><label><input type="checkbox" name="tag" value="Servlet" onClick="DisChecked()">Servlet</label></td>
		<td><label><input type="checkbox" name="tag" value="DAO" onClick="DisChecked()">DAO</label></td>
	</tr>

	<tr>
		<td><input type="file" name="image_files" accept="image/jpeg, image/png" onchange="previewImage(this);"></td>
		<td><label><input type="checkbox" name="tag" value="jQuery" onClick="DisChecked()">jQuery</label></td>
		<td><label><input type="checkbox" name="tag" value="その他" onClick="DisChecked()">その他</label></td>
		<td><label><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</label></td>
	</tr>
	<tr>
		<td>テキストファイル（.docx、.txt）はこちら</td>
		<td>4.公開設定</td>
	</tr>
	<tr>
		<td><input type="file" name="text_files" accept=" .docx, .txt"></td>
		<td><label><input type="radio" name="public_select" value="1" checked>公開</label>
			<label><input type="radio" name="public" value="0">非公開</label></td>
	</tr>
	</table>
		<p>${errMsg}</p>
		<input type="submit" name="upload" value="アップロード">
	</form>


<c:forEach var="e" items="${cardList}" >
 	<form method="POST" action="/ShareNote/Edit">

	</form>

</c:forEach>

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