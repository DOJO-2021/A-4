<!-- 検索jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mynote++</title>
<link rel="stylesheet" href="/ShareNote/css/common.css">
</head>

<body>
<div class="wrapper">
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="header-fixed">
<div class="search-whole">
<div class="search-title">
検索
</div>

<form method="POST" action="/ShareNote/Search" name="form">

	<table align="center">

			<tr>
				<td colspan="2" style="padding-bottom:5px;"><label><input type="checkbox" name="matching" value="matching" style="transform:scale(1.3);">タグの完全一致</label><td>
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
				<td><label><input type="checkbox" name="tag" value="jQuery" onClick="DisChecked()">jQuery</label></td>
				<td><label><input type="checkbox" name="tag" value="other" onClick="DisChecked()">その他</label></td>
				<td><label><input type="checkbox" name="all" onClick="AllChecked();" />全て選択</label></td>
			</tr>

			<tr class="table-word">
				<td colspan="4" align="center"><label><input type="text" name="keyword" placeholder="キーワード（タイトル・ニックネーム）検索" style="width:22em;"></label></td>
			</tr>
			<tr class="table-word2">
			 	<td colspan="4" align="center"><label><select name="sort" style="width:22em;">
						<option selected value ="新着順">新着順</option>
						<option value ="お気に入り数順">お気に入り数順</option>
					</select>
					</label>
				</td>
			</tr>
			<tr class="table-button">
				<td colspan="4" align="center"><label><input type="submit" name="search" value="検索" ></label></td>
			</tr>
	</table>
</form>
</div>
</div>
</div>
<br><br>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
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
</script>
</html>