<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header class="header">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <spring:message code="navbar.brand"/>
        </a>
    </nav>
</header>
