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


<!-- 初期状態 -->
<p>ノートのアップロード</p>

	<form method="POST" name="form" action="/ShareNote/Edit">
	<table border="1">
	<tr>
		<td>1.ファイル登録（いずれか必須）</td>
	</tr>
	<tr>
		<td>  画像ファイル（.jpg、.png）はこちら</td>
		<td colspan="4">2.タイトルの記入（必須）</td>
	</tr>
	<tr>
		<td rowspan="5">${e.image_files}ノート画像</td>

		<td rowspan="2" colspan="4" height="80em">${e.title}タイトル</td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="4">3.タグの選択（必須）</td>
	</tr>
   	<tr>
		<td><input type="checkbox" name="tag" value="linux1" onClick="DisChecked()">HTML</td>
		<td><input type="checkbox" name="tag" value="linux2" onClick="DisChecked()">CSS</td>
		<td><input type="checkbox" name="tag" value="linux3" onClick="DisChecked()">JavaScript</td>
		<td><input type="checkbox" name="tag" value="linux4" onClick="DisChecked()">Java</td>
	</tr>
	<tr>
		<td><input type="checkbox" name="tag" value="linux5" onClick="DisChecked()">SQL</td>
		<td><input type="checkbox" name="tag" value="linux6" onClick="DisChecked()">jsp</td>
		<td><input type="checkbox" name="tag" value="linux7" onClick="DisChecked()">Servlet</td>
		<td><input type="checkbox" name="tag" value="linux8" onClick="DisChecked()">DAO</td>
	</tr>

	<tr>
		<td><input type="file" name="datafile" accept="image/jpeg, image/png"></td>
		<td><input type="checkbox" name="tag" value="linux9" onClick="DisChecked()">jQuery</td>
		<td><input type="checkbox" name="tag" value="linux10" onClick="DisChecked()">その他</td>
		<td><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</td>
	</tr>
	<tr>
		<td>テキストファイル（.docx、.txt）はこちら</td>
		<td>4.公開設定</td>
	</tr>
	<tr>
		<td><input type="file" name="datafile" accept=" .docx, .txt"></td>
		<td><input type="radio" name="public" value="open"> 公開<input type="radio" name="public" value="close"> 非公開</td>
	</tr>
	</table>
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

</script>
</html>