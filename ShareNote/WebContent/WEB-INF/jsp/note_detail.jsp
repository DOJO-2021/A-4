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
<div class="header-fixed">
<div class="wrapper">
<h2>ノート詳細</h2>
<form method="POST" name="form" action="/ShareNote/Note_detail">
	<table align="center" border="1" class="notes">
	<tr>
		<td colspan="3"><img src="${param.image_files}" width="850em" height="550em"></td>
	</tr>
	<tr>
		<td class="detail-year">${param.year}年度</td>
		<td align="center" class="detail-nn">${param.nickname}</td>
		<td rowspan="2" class="favorites_button"><a href="javascript:;" id="saveCheckbox" onclick="valueChange(event)">
			<input type="hidden" name="count" value="${count}" id="count">
			<img src="/ShareNote/images/${count}.png" id="gazo" width="100px" height="100px"></a>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="detail-title" align="center">${param.title}</td>
	</tr>
	<tr>
		<td align="center" colspan="2" class="detail-tag">#　${param.tag}</td>
		<c:choose>
			<c:when test="${empty param.text_files}"><td align="center"><a href="${param.image_files}" download>ダウンロード</a></td></c:when>
			<c:otherwise><td align="center"><a href="${param.text_files}" download>ダウンロード</a></td></c:otherwise>
		</c:choose>

	</tr>
	<input type="hidden" name="note_id" value="${param.note_id}" id="note_id">
	</table>
</form>

<!-- お気に入り一覧から遷移してきたとき、お気に入り一覧に戻す -->
<a href="javascript:history.go(-1)">1つ前のページに戻る</a>

<hr>
<h3>こちらもおすすめ</h3>

<c:forEach var="e" items="${RecommendedList}">
	<table border="1" align="center" class="notes">
		<form method="POST" action="/ShareNote/Note_detail">

		<tr>
			<td rowspan="3" class="inf-img"><img src="${e.image_files}" width="200px" height="120px"><input type="hidden" name="image_files" value="${e.image_files}"></td>
			<td class="inf-year">${e.year}年度<input type="hidden" name="year" value="${e.year}"></td>
			<td align="center" class="inf-nn">${e.nickname}<input type="hidden" name="nickname" value="${e.nickname}"></td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
		</tr>

		<tr>
			<td colspan="2" align="center" class="inf-title">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center" class="inf-tag">#　${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
			<c:choose><c:when test="${empty e.text_files}"><td><a href="${e.image_files}" download>ダウンロード</a></td></c:when>
				<c:otherwise><td><a href="${e.text_files}" download>ダウンロード</a></td></c:otherwise>
			</c:choose>
		</tr>
		<input type="hidden" name="note_id" value="${e.note_id}">
		</form>
	</table>
</c:forEach>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>


<p id="msg"></p>
</div>
</div>
</body>
<script>
//var cnt =0;
function valueChange(event){
	//画像を配列に格納する
 	var pics_src = new Array("images/1.png","images/0.png");



	//画像番号用のグローバル変数
	var cnt = document.getElementById("count").value;

	//画像番号を進める
	  if (cnt == 0) {
		  document.getElementById("count").value=1;

	  }else if (cnt == 1) {
		  document.getElementById("count").value=0;
	  }
	  //画像を切り替える
	  document.getElementById("gazo").src=pics_src[cnt];
	  let note_id = document.getElementById("note_id").value;

	$.ajax({
		type:'post',
		url: '/ShareNote/Favorites_button',
		data: {
				note_id : note_id,
				cnt : cnt

			  }
	});


}

let saveCheckbox = document.getElementById('saveCheckbox');
saveCheckbox.addEventListener('change', valueChange);
let msg = document.getElementById('msg');
<!--inner-->
</script>

</script>
</html>