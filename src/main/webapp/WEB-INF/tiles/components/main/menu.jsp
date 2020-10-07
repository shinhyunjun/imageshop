<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div align="center">
    <table>
        <tr>
            <td width="80"><a href="/"><spring:message code="header.home" /></a></td>
            <td width="120"><a href="/codegroup/list"><spring:message code="menu.codegroup.list" /></a></td>
            <td width="120"><a href="/codedetail/list"><spring:message code="menu.codedetail.list" /></a></td>

            <!-- 회원관리를 메뉴에 추가-->
            <td width="120"><a href="/user/list"><spring:message code="menu.user.admin" /></a></td>
        </tr>
    </table>
</div>
<hr>
