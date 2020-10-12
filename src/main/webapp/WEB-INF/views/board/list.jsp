<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h2><spring:message code="board.header.list" /></h2>

<sec:authorize access="hasRole('ROLE_MEMBER')">
    <a href="register"><spring:message code="action.new" /></a>
</sec:authorize>

<table border="1">
    <tr>
        <th align="center" width="80"><spring:message code="board.no" /></th>
        <th align="center" width="320"><spring:message code="board.title" /></th>
        <th align="center" width="100"><spring:message code="board.writer" /></th>
        <th align="center" width="180"><spring:message code="board.regdate" /></th>
    </tr>
    <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="4">
                    <spring:message code="common.listEmpty" />
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${list}" var="board">
                <tr>
                    <td align="center">${board.boardNo}</td>
                    <td align="left"><a href='/board/read?boardNo=${board.boardNo}'>${board.title}</a></td>
                    <td align="right">${board.writer}</td>
                    <td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regDate}" /></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<script>
    var result = "${msg}";

    if (result === "SUCCESS") {
        alert("<spring:message code='common.processSuccess' />");
    }
</script>
