<%@ tag description="Default header template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><spring:message code="navbar.brand"/></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#"><spring:message code="navbar.link.home"/></a>
                </li>
                <li>
                    <a id="logout-link" href="#logout" onclick="document.getElementById('logout-form').submit();">
                        <spring:message code="navbar.link.logout"/>
                    </a>
                </li>
            </ul>
            <form id="logout-form" action="${pageContext.request.contextPath}/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</nav>