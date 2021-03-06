<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:loginHeader/>
    </jsp:attribute>

    <jsp:body>
        <main class="container login-container" role="main">
            <div class="starter-template">
                <h3><spring:message code="login.page.title"/></h3>
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
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="login.invalid.credentials"/>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success" role="alert">
                        <spring:message code="logout.successful"/>
                    </div>
                </c:if>
            </div>
        </main>
    </jsp:body>

</t:defaultPage>
