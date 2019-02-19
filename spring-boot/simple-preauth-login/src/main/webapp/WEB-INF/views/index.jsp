<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <security:authorize access="isAuthenticated()">
                    <h3>Hello <i><security:authentication property="principal.username"/></i>!</h3>
                </security:authorize>
                <h3><spring:message code="main.title"/></h3>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
