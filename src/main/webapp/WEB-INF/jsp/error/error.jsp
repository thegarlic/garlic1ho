<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<tr>
						<td colspan="5">에러
							에러메시지 : <c:out value="${requestScope['javax.servlet.error.message']}"/><br>
							에러코드 : <c:out value="${requestScope['javax.servlet.error.status_code']}"/> <br>
							예외내용  : <c:out value="${requestScope['javax.servlet.error.exception']}"/><br><Br>
						</td>
					</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<a href="<c:url value="/${article.boardName }board"/>">[목록]</a>
					<input type="button" value="돌아가기" onclick="history.go(-1)" />
					<a href='<c:url value="/${boardName }board/write"/>'>글쓰기</a>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

</div>
</body>
</html>