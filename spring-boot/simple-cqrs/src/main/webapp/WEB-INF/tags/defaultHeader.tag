<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <nav class="navbar navbar-dark bg-primary">
        <div class="navbar-brand">
            <h1><spring:message code="navbar.brand"/></h1>
        </div>
        <div id="navbar-toggle" class="collapse navbar-collapse">
            <ul class="nav navbar-nav mr-auto">
                <li>
                    <a href="#" onclick="location.href='${pageContext.request.contextPath}/';">
                        <spring:message code="navbar.link.home"/>
                    </a>
                </li>
                <li>
                    <a href="#authors"
                       onclick="location.href='${pageContext.request.contextPath}/authors';">
                        <spring:message code="navbar.link.authors"/>
                    </a>
                </li>
                <li>
                    <a href="#books" onclick="location.href='${pageContext.request.contextPath}/books';">
                        <spring:message code="navbar.link.books"/>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>