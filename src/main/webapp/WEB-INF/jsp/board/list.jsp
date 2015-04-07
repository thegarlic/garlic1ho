<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link rel="stylesheet" href='<c:url value="/assets/css/style_board_responsive.css"></c:url>' type="text/css" />
</head>
<body>
	<div class="container">
		<div id="board">
	<table id="table_board" class="list board_table">
		<%-- <colgroup>
			<col width="150">
			<col width="50%">
			<col width="350">
			<col width="100">
			<col width="150">
		</colgroup> --%>
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
				<c:if test="${empty articles.content}">
					<tr>
						<td colspan="5">게시글이 없습니다. <a href="<c:url value="/${article.boardName }board/init"/>">세팅할까요?</a></td>
					</tr>
				</c:if>
				<c:forEach var="article" items="${articles.content }">
				
				<tr>
					<td>${article.id }</td>
					<td><a href='<c:url value="/${article.boardName }board/${article.id }"/>'>${article.title }</a></td>
					<td>${article.usernick }</td>
					<td>${article.modificationTime}</td>
					<td>${article.num_read }</td>
				</tr>
				</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<c:if test="${articles.previous}"><a href="#">이전</a></c:if>
					<c:forEach var="numPage" items="${articles.pages }">
						<a href="<c:url value="/${boardName }board/${numPage }page"/>">${numPage }</a>
					</c:forEach>
					<c:if test="${articles.next}"><a href="#">다음</a></c:if>
					<a href='<c:url value="/${boardName }board/write"/>'>글쓰기</a>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

</div>
</body>
</html>