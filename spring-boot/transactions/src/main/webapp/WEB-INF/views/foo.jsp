<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <main class="container" role="main">
            <div class="starter-template">
                <div class="jumbotron">
                    <h1><spring:message code="foo.page.title"/></h1>
                    <form:form action="${pageContext.request.contextPath}/foo" method="post" modelAttribute="foo">
                        <div class="form-group">
                            <label for="data">
                                <spring:message code="create.foo.form.input"/>
                            </label>
                            <input type="text" class="form-control" id="data" name="data">
                        </div>
                        <button type="submit" class="btn btn-success">
                            <spring:message code="create.foo.form.button"/>
                        </button>
                    </form:form>
                    <p>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="id-col" scope="col">ID</th>
                            <th scope="col">Data</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <th class="id-col" scope="row">${item.id}</th>
                                <td>${item.data}</td>
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
