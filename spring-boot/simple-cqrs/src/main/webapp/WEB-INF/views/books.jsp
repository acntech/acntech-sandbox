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
        <main class="container" role="main">
            <div class="starter-template">
                <div class="jumbotron">
                    <h1><spring:message code="books.page.title"/></h1>
                </div>
            </div>
        </main>
    </jsp:body>

</t:defaultPage>
