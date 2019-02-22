<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <nav class="navbar navbar-dark bg-primary">
          <div class="navbar-brand">
              <h1><spring:message code="navbar.brand"/></h1>
          </div>
      </nav>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <h3><spring:message code="login.title"/></h3>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="username"><spring:message code="login.form.username"/></label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group">
                        <label for="password"><spring:message code="login.form.password"/></label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-success"><spring:message code="login.form.button"/></button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <c:if test="${param.error != null}">
                    <h4 style="color: darkred"><spring:message code="login.invalid.credentials"/></h4>
                </c:if>
                <c:if test="${param.logout != null}">
                    <h4 style="color: darkgreen"><spring:message code="logout.successful"/></h4>
                </c:if>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
