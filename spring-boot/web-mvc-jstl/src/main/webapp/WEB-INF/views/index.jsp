<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
      <t:header/>
    </jsp:attribute>
    <jsp:body>
        <main class="container" role="main">
            <div class="jumbotron">
                <h1><spring:message code="index.page.title"/></h1>
            </div>
        </main>
    </jsp:body>
</t:layout>
