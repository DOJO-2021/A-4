<!-- 検索結果jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

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


<h2>検索結果</h2>
<form method="POST" action="/ShareNote/Search">
	<table align="center">
			<tr>
				<td><label><input type="checkbox" name="matching" value="matching" <c:if test="${param.matching == 'matching'}">checked</c:if>>完全一致</label><td>
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
			<tr>
				<td><label><input type="checkbox" name="tag" value="jQuery" onClick="DisChecked()"
					<c:if test="${jQuery == '1'}">checked</c:if>>jQuery</label></td>
				<td><label><input type="checkbox" name="tag" value="その他" onClick="DisChecked()"
					<c:if test="${その他 == '1'}">checked</c:if>>その他</label></td>
				<td><label><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</label></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><label><input type="text" name="keyword" value="${param.keyword}" placeholder="キーワード（タイトル・ニックネーム）検索" ></label></td>
			</tr>
			<tr>
			 	<td colspan="4" align="center"><label><select name="sort">
						<option value ="新着順" <c:if test="${param.sort == '新着順'}">selected</c:if>>新着順</option>
						<option value="お気に入り順" <c:if test="${param.sort == 'お気に入り順'}">selected</c:if>>お気に入り順</option>
					</select>
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" name="search" value="検索" ></td>
			</tr>
	</table>
</form>
<!--
<form action ="POST"  >
	<select name=sort>
	<option value="新着順">新着順</option>
	<option value="お気に入り順">お気に入り順</option>
	</select>
-->
<c:forEach var ="c" items="${hitList}">
ヒット数：${c.noteCount}
</c:forEach>

<c:forEach var ="e" items="${noteList}">
<table border="1">
	<form method="POST" action="/ShareNote/Note_detail">
		<tr>
			<td rowspan="3"><img src="${e.image_files}"><input type="hidden" name="image_files" value="${e.image_files}"></td>
			<td>
			${e.year}年度<input type="hidden" name="year" value="${e.year}">
			</td>
			<td>
			${e.nickname}<input type="hidden" name="nickname" value="${e.nickname}">
			</td>
			<td rowspan="2" align="center"><input type="submit" name="detail" value="詳細"></td>
			</tr>
			<tr>
			<td colspan="2">${e.title}<input type="hidden" name="title" value="${e.title}"></td>
			</tr>

		<tr>
			<td>${e.tag}<input type="hidden" name="tag" value="${e.tag}"></td>
			<!--  <td><input type="submit" name="download" value="ダウンロード"></td> -->
			<c:choose><c:when test="${empty e.text_files}"><td><a href="${e.image_files}" download>ダウンロード</a></td></c:when>
					  <c:otherwise><td><a href="${e.text_files}" download>ダウンロード</a></td></c:otherwise>
				</c:choose>
			<td align="center"><input type="submit" name="favorite" value="★"></td>
		</tr>
		</form>
		</table>
		<hr>

</c:forEach>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
<script language="JavaScript" type="text/javascript">
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