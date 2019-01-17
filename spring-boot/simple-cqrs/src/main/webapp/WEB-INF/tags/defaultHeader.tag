<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" onclick="location.href='${pageContext.request.contextPath}/';"><spring:message code="navbar.brand"/></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#" onclick="location.href='${pageContext.request.contextPath}/';"><spring:message code="navbar.link.home"/></a>
                </li>
                <li>
                    <a href="#authors" onclick="location.href='${pageContext.request.contextPath}/authors';"><spring:message code="navbar.link.authors"/></a>
                </li>
                <li>
                    <a href="#books" onclick="location.href='${pageContext.request.contextPath}/books';"><spring:message code="navbar.link.books"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>