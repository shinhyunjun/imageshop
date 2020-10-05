<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
</head>

<script src="http://code.jquery.com/jquery-3.1.1.js"></script>

<body>

<tiles:insertAttribute name="header"  />

<tiles:insertAttribute name="menu" />

<div align="center">
    <tiles:insertAttribute name="content" />
</div>

<tiles:insertAttribute name="footer" />

</body>
</html>
