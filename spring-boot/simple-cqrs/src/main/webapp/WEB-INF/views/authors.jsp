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
                <h1><spring:message code="main.title"/></h1>
            </div>
            <h2><spring:message code="main.heading.authors"/></h2>

            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><spring:message code="section.heading.create.author"/></h4>
                    </div>
                    <div class="panel-body">
                        <form:form method="POST" action="${pageContext.request.contextPath}/authors" modelAttribute="author">
                            <div class="form-group">
                                <form:label path="firstName"><spring:message code="form.input.author.firstName"/>:</form:label>
                                <form:input class="form-control" path="firstName"/>
                            </div>
                            <div class="form-group">
                                <form:label path="lastName"><spring:message code="form.input.author.lastName"/>:</form:label>
                                <form:input class="form-control" path="lastName"/>
                            </div>
                            <div class="form-group">
                                <form:label path="biography"><spring:message code="form.input.author.biography"/>:</form:label>
                                <form:input class="form-control" path="biography"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default"><spring:message code="form.button.create.author"/></button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <br/>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4><spring:message code="section.heading.find.authors"/></h4>
                        </div>
                        <div class="panel-body">
                            <c:if test="${not empty authors}">
                                <table class="table table-striped">
                                    <tr>
                                        <th><spring:message code="form.input.author.id"/></th>
                                        <th><spring:message code="form.input.author.firstName"/></th>
                                        <th><spring:message code="form.input.author.lastName"/></th>
                                        <th><spring:message code="form.input.author.biography"/></th>
                                    </tr>
                                    <c:forEach var="a" items="${authors}">
                                        <tr>
                                            <td>${a.id}</td>
                                            <td>${a.firstName}</td>
                                            <td>${a.lastName}</td>
                                            <td>${a.biography}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <form:form method="GET" action="${pageContext.request.contextPath}/authors">
                                <input type="hidden" name="list" value="authors"/>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default"><spring:message code="form.button.find.authors"/></button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
