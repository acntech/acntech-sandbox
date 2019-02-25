<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="starter-template">
                <h3><spring:message code="main.title"/></h3>
                <c:if test="${message != null}">
                    <h4>${message}</h4>
                </c:if>
                <form action="${pageContext.request.contextPath}" method="post">
                    <div class="form-group">
                        <label for="firstName"><spring:message code="form.firstName"/></label>
                        <input type="text" class="form-control" id="firstName" name="firstName">
                    </div>
                    <button type="submit" class="btn btn-success"><spring:message code="form.button"/></button>
                </form>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
