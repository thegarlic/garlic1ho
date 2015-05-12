<%@ page contentType="text/html; charset=UTF-8" %><%@
        taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@
        taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글 쓰기</title>

    <!-- include libries(jQuery, bootstrap, fontawesome) -->
    <link href="<c:url value="/assets/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <!-- include summernote css-->
    <link href="<c:url value="/assets/summernote/dist/summernote.css"/>" rel="stylesheet">
    <link rel="stylesheet" href='<c:url value="/assets/css/style_board.css"></c:url>' type="text/css" />
</head>
<body>
<c:if test="${isPassword == false }"><script>alert("비밀번호가 맞지 않습니다.");</script></c:if>
<div class="container">
    <div id="board">
        <form method="post" action="<c:url value='/${article.boardName }board/${article.id }/${FormType}' />">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table id="table_board" class="writeForm">
                <colgroup>
                    <col width="150">
                    <col width="80%">
                </colgroup>
                <thead>
                <tr>
                    <th colspan="2">비밀번호 확인란</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>비밀번호</td>
                        <td><input type="password" name="password" size="20"><br></td>
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
        </form>
    </div>

</div>
<script src="<c:url value="/assets/jquery/dist/jquery.min.js"/>"></script>
<script src="<c:url value="/assets/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/summernote/dist/summernote.min.js"/>"></script>
<script>
    $(document).ready(function() {
        $('#summernote').summernote({
            height: 300,   //set editable area's height
            focus: true    //set focus editable area after Initialize summernote
        });
    });
</script>
</body>
</html>