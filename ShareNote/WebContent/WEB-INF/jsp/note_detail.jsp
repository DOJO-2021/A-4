<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>mynote++</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<h2>ノート詳細</h2>
<form method="POST" name="form" action="/ShareNote/Note_detail">
	<table align="center" border="1">
	<tr>
		<td rowspan="3">${param.image_files}ノート画像</td>
		<td>${param.year}年度</td>
		<td align="center" colspan="2">${param.nickname}ニックネーム</td>
	</tr>
	<tr>
		<td align="center" colspan="3">${param.title}タイトル</td>
	</tr>
	<tr>
		<td>${param.tag}タグ</td>
</form>
<td><a href="javascript:;" id="saveCheckbox" onclick="valueChange(event)">
 <input type="hidden" name="count" value="${param.count}" id="count">
<input type="hidden" name="note_id" value="${param.note_id}" id="note_id">
<img src="/ShareNote/images/0.png" id="gazo"></a></td>



<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
					  <c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
				</c:choose>
	</tr>
	</table>

<!-- お気に入り一覧から遷移してきたとき、お気に入り一覧に戻す -->
<a href="javascript:history.go(-1)">1つ前のページに戻る</a>

<hr>
<h3>こちらもおすすめ</h3>
<!-- 試しにforの外に書いてみる -->
<!--
	<form method="POST" action="/ShareNote/Note_detail">
		<table align="center" border="1">
		<tr>
			<td rowspan="2">${e.note_files}ノート画像</td>
			<td>${e.title}タイトル</td>
			<td><input type="submit" name="detail" value="詳細"></td>
		</tr>
		<tr>
			<td>${e.tag}タグ</td>
			<td><input type="submit" name="download" value="ダウンロード"></td>
		</tr>
		</table>
	</form>
-->
	<!-- 試しにforの外に書いてみる -->

<c:forEach var="e" items="${RecommendedList}">
	<table border="1">
		<form method="POST" action="/ShareNote/Note_detail">

		<tr>
			<td rowspan="3">${e.image_files}</td>
			<td>${e.year}年度</td>
			<td align="center" colspan="2">${e.nickname}</td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
		</tr>

		<tr>
			<td>${e.title}</td>
		</tr>
		<tr>
			<td>${e.tag}</td>
			<c:choose><c:when test="${empty e.text_files}"><td><a href="/ShareNote/upload_files/${e.image_files}" download>ダウンロード</a></td></c:when>
				<c:otherwise><td><a href="/ShareNote/upload_files/${e.text_files}" download>ダウンロード</a></td></c:otherwise>
			</c:choose>
		</tr>
		</form>
	</table>
</c:forEach>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>


<p id="msg"></p>

<script>
var cnt =0;
function valueChange(event){
	//画像を配列に格納する
 	var pics_src = new Array("images/0.png","images/1.png");



	//画像番号用のグローバル変数
//	var cnt = document.getElementById("count");

	//画像番号を進める
	  if (cnt == 1) {
		  cnt = 0;

	  }else if (cnt == 0) {
		  cnt = 1;
	  }
	  //画像を切り替える
	  document.getElementById("gazo").src=pics_src[cnt];
	  let note_id = document.getElementById("note_id").value;
	  alert(note_id);
	$.ajax({
		type:'post',
		url: '/ShareNote/Favorites_button',
		data: {
				note_id : note_id,
				cnt : cnt

			  }
	});
	alert("owata");

}

let saveCheckbox = document.getElementById('saveCheckbox');
saveCheckbox.addEventListener('change', valueChange);
let msg = document.getElementById('msg');
<!--inner-->
</script>

</script>
</html>