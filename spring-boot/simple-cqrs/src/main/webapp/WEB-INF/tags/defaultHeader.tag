<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/authors">
                        <spring:message code="navbar.link.authors"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/books">
                        <spring:message code="navbar.link.books"/>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
