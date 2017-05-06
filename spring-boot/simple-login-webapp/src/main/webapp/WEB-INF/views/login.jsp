<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <nav class="navbar navbar-inverse">
          <div class="container">
              <div class="navbar-header">
                  <a class="navbar-brand" href="#">Spring Boot</a>
              </div>
              <div id="navbar" class="collapse navbar-collapse">
              </div>
          </div>
      </nav>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <h1>Login</h1>
                <c:if test="${param.error != null}">
                    <h4 style="color: darkred"><spring:message code="login.invalid.credentials"/></h4>
                </c:if>
                <c:if test="${param.logout != null}">
                    <h4 style="color: darkgreen"><spring:message code="logout.successful"/></h4>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="username">UserName:</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-success">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
