<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <main class="container" role="main">
            <div class="starter-template">
                <div class="jumbotron">
                    <h1><spring:message code="index.page.title"/></h1>
                    <p>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="id-col" scope="col">#</th>
                            <th scope="col">Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="d" items="${data}">
                            <tr>
                                <th class="id-col" scope="row">${d.id}</th>
                                <td>${d.bar}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </p>
                </div>
            </div>
        </main>
    </jsp:body>

</t:defaultPage>
