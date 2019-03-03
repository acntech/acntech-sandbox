<%@ tag description="Default page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="navbar.brand"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/default.css"/>
</head>
<body>
<jsp:invoke fragment="header"/>

<jsp:doBody/>

<jsp:invoke fragment="footer"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/default.js"></script>
</body>
</html>