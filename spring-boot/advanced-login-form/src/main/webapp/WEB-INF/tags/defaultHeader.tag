<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<header class="header">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            <spring:message code="navbar.brand"/>
        </a>
        <div id="navbar-toggle">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">
                        <spring:message code="navbar.link.home"/>
                    </a>
                </li>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/users">
                            <spring:message code="navbar.link.users"/>
                        </a>
                    </li>
                </security:authorize>
                <li class="nav-item">
                    <a id="logout-link" class="nav-link" href="${pageContext.request.contextPath}/logout">
                        <spring:message code="navbar.link.logout"/>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
