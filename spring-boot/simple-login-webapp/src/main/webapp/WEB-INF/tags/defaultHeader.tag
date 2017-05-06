<%@tag description="Default header template" pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#">Home</a>
                </li>
                <li>
                    <a id="logout-link" href="#logout"
                       onclick="document.getElementById('logout-form').submit();">Logout</a>
                </li>
            </ul>
            <form id="logout-form" action="${pageContext.request.contextPath}/login" method="get">
                <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                <input type="hidden" name="logout"/>
            </form>
        </div>
    </div>
</nav>