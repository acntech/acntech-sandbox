<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <nav class="navbar navbar-dark bg-primary">
        <div class="navbar-brand">
            <h1><spring:message code="navbar.brand"/></h1>
        </div>
        <div id="navbar-toggle" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">
                        <spring:message code="navbar.link.home"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a id="logout-link" class="nav-link" href="#logout"
                       onclick="document.getElementById('logout-form').submit();">
                        <spring:message code="navbar.link.logout"/>
                    </a>
                </li>
            </ul>
            <form id="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </nav>
</div>