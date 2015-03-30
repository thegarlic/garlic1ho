<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 읽기</title>
<link rel="stylesheet" href='<c:url value="/assets/css/style_board.css"></c:url>' type="text/css" />
</head>
<body>
	<div class="container">
		<div id="board">
	<table id="table_board" class="writeForm">
	<colgroup>
		<col width="150">
		<col width="80%">
	</colgroup>
	<thead>
		<tr>
			<th colspan="2">글 읽기</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>제목</td>
			<td>${article.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${article.usernick }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${article.modificationTime}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td> ${article.content } </td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2">
				<p style="text-align: center;">
					<a href="<c:url value="/${article.boardName }board"/>">[목록]</a>
					<a href='<c:url value='/${article.boardName }board/${article.id }/update' />'>[수정]</a>
					<a href='<c:url value='/${article.boardName }board/${article.id }/del' />'>[삭제]</a>
				</p>
			</td>
		</tr>
	</tfoot>
</table>
</div>

</div>
</body>
</html>