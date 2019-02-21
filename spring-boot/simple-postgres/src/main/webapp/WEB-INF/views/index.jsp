<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <h1><spring:message code="main.title"/></h1>
                <ul>
                    <c:forEach var="d" items="${data}">
                        <li>${d.id} - ${d.bar}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
