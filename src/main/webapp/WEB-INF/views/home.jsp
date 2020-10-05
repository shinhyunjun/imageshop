<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1>
    <spring:message code="common.homeWelcome"/>
</h1>
<p>The time on the server is ${serverTime}</p>

