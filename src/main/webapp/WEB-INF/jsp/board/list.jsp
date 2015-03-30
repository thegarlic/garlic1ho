<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link rel="stylesheet" href='<c:url value="/assets/css/style_board.css"></c:url>' type="text/css" />
</head>
<body>
	<div class="container">
		<div id="board">
	<table id="table_board" class="list">
		<colgroup>
			<col width="150">
			<col width="50%">
			<col width="350">
			<col width="100">
			<col width="150">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>닉네임</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="article" items="${articles.content }">
				<tr>
					<td>${article.id }</td>
					<td><a href="#">${article.title }</a></td>
					<td>${article.usernick }</td>
					<td><joda:format value="${article.modificationTime}" style="SM" pattern="yy/M/d hh:mm" /></td>
					<td>${article.num_read }</td>
				</tr>
				</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<a href="#">이전</a>
					<a href="#">1</a>
					<a href="#">다음</a>
					<a href="#">글쓰기</a>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

</div>
</body>
</html>