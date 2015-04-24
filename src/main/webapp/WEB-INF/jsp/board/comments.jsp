<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody id="tbody">
<c:if test="${comments.content eq null }">
	<tr>
		<td>
			댓글이 아직 없습니다.
		</td>
	</tr>
</c:if>
<c:forEach var="comment" items="${comments.content}">
		<tr id="${comment.id}">
			<td>
				<div>${comment.nick } | 16-04-16 16:48 | <a class="btn_modify">수정</a> | <a class="btn_delete">삭제</a></div>
				<div><a class="like">좋아요</a> | <a class="dislike">싫어요</a></div>
				<div>${comment.comments }</div>
			</td>
		</tr>
</c:forEach>
</tbody>
<tfoot id="tfoot">
	<tr>
		<td colspan="5">
			<c:url var="pageurl" value="/ajax/comments/${comments.boardArticleId}" />
			${comments.num_comments}개의 댓글
			<c:if test="${comments.previous}"><a href="${pageurl }/${comments.beginPage+1}">이전</a></c:if>
			<c:forEach var="numPage" items="${comments.pages }">
				<a onclick="listComments(${numPage})">${numPage }</a>
			</c:forEach>
			<c:if test="${comments.next}"><a href="${pageurl }/${comments.endPage+1}">다음</a></c:if>
			
		</td>
	</tr>
</tfoot>