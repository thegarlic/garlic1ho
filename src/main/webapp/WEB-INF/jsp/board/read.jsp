<%@ page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 읽기</title>
<link rel="stylesheet" href='<c:url value="/assets/css/style_board.css"></c:url>' type="text/css" />


</head>

<body>
<input type="hidden" id="hiddenAddress" value="<c:url value="/ajax/comments/" />"/>
	<div class="container">
		<div id="board">
	<table id="table_board" class="read">
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
			<td> ${article.content }  </td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="2">
				<p style="text-align: center;">
					<a href="<c:url value="/${article.boardName }board"/>">[목록]</a>
                   <c:if test="${article.userid eq null}">
                       <a href='<c:url value='/${article.boardName }board/${article.id }/update' />'>[수정]</a>
                       <a href='<c:url value='/${article.boardName }board/${article.id }/del' />'>[삭제]</a>
                   </c:if>
                    <sec:authorize access="isAuthenticated()">
                    <sec:authentication var="userid" property="principal.id" />
                    <c:if test="${article.userid eq userid}">
                        <a href='<c:url value='/${article.boardName }board/${article.id }/update' />'>[수정]</a>
                        <a href='<c:url value='/${article.boardName }board/${article.id }/del' />'>[삭제]</a>
                    </c:if>
                    </sec:authorize>
				</p>
			</td>
		</tr>
	</tfoot>
</table>

<table id="table_board" class="reply">
	<colgroup>
		<col width="150">
		<col width="80%">
	</colgroup>
	<thead id="thead">
		<tr>
		<th>
			<c:url var="formUrl" value="/ajax/comments/${article.id}" />
			<form id="replyFrm" action='${formUrl }' method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<textarea class="txtArea_guest" rows="5" name="comments"></textarea><br>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.firstName"/>님께서
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <input type="text" name="nick">님께서
                    <input type="password" name="password"> 비밀번호로
                </sec:authorize>
				<input type="button" onclick="createComments()" value="댓글" />다십니다^^
                <sec:authorize access="isAuthenticated()">
				    <input type="checkbox" name="secret" value="1"><span style="font-size:16px;">비밀로요♥</span>
                </sec:authorize>
			</form>
		</th>
	</tr>
	</thead>
	
	
</table>


</div>
</div>

<!-- script -->
<script type="text/javascript" src="<c:url value="/assets/jquery/dist/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/ajaxComment.js"/>"></script>
</body>
</html>