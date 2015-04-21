<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody id="tbody">
<c:if test="${comments eq null }">
	<tr>
		<td>
			댓글이 아직 없습니다.
		</td>
	</tr>
</c:if>
<c:forEach var="comment" items="${comments}">
		<tr id="${comment.id } ">
			<td>
				<div>${comment.nick } | 16-04-16 16:48 | 수정 | <a class="btn_delete">삭제</a></div>
				<div>좋아요 | 싫어요</div>
				<div>${comment.comments }</div>
			</td>
		</tr>
</c:forEach>
</tbody>
<tfoot id="tfoot">
	<tr>
		<td colspan="5">
			1개의 댓글 | 
			<a href="#">이전</a>
			<a href="#">1</a>
			<a href="#">다음</a>
		</td>
	</tr>
</tfoot>