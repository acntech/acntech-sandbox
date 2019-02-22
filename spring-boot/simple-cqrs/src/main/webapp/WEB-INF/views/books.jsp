<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>
    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <h3><spring:message code="main.title"/></h3>
            </div>
            <h5><spring:message code="main.heading.books"/></h5>
        </div>
    </jsp:body>

</t:defaultPage>
