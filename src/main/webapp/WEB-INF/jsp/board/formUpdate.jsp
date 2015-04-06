<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %><%@ 
taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>

<!-- include libries(jQuery, bootstrap, fontawesome) -->
<link href="<c:url value="/assets/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet"> 
<link href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
<!-- include summernote css-->
<link href="<c:url value="/assets/summernote/dist/summernote.css"/>" rel="stylesheet">
<link rel="stylesheet" href='<c:url value="/assets/css/style_board.css"></c:url>' type="text/css" />
</head>
<body>
	<div class="container">
		<div id="board">
	<c:url var="urlForm" value="/${updateArticle.boardName }board/${updateArticle.id }" />
	<form:form action='${urlForm}' method="post" name="myform" commandName="updateArticle">
	<table id="table_board" class="writeForm">
		<colgroup>
			<col width="150">
			<col width="80%">
		</colgroup>
		<thead>
			<tr>
				<th colspan="2">글쓰기</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" size="20" value="<c:out value="${updateArticle.title}" />"><br></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${updateArticle.usernick }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" id="summernote"  rows="5" cols="40">${updateArticle.content}</textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<p style="text-align: center;">
						<input type="submit" value="전송" />
						<input type="button" value="돌아가기" onclick="history.go(-1)" />
					</p>
				</td>
			</tr>
		</tfoot>
	</table>
</form:form>
</div>

</div>


<script src="<c:url value="/assets/jquery/dist/jquery.min.js"/>"></script> 
<script src="<c:url value="/assets/bootstrap/dist/js/bootstrap.min.js"/>"></script> 
<script src="<c:url value="/assets/summernote/dist/summernote.min.js"/>"></script>
<script>
$(document).ready(function() {
	$('#summernote').summernote();
});
</script>
</body>
</html>