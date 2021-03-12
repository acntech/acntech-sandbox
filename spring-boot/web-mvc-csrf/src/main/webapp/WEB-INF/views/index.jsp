<%@ page contentType="text/html;charset=UTF-8" %>
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
                    <h1><spring:message code="index.page.title"/></h1>
                </div>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4><spring:message code="section.heading.greeting"/></h4>
                        </div>
                        <div class="panel-body">
                            <form:form method="POST" action="${pageContext.request.contextPath}">
                                <div class="form-group">
                                    <input type="text" id="name" name="name"/>
                                    <label for="name"><spring:message code="form.input.greeting.name"/></label>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">
                                        <spring:message code="form.button.greeting.submit"/>
                                    </button>
                                </div>
                            </form:form>
                            <c:if test="${not empty name}">
                                <h5><spring:message code="section.body.greeting.hello"/> ${name}!</h5>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </jsp:body>

</t:defaultPage>
