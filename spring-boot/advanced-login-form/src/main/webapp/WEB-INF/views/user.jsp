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
                <h4>Users</h4>
                <ol>
                    <c:forEach var="user" items="${users}">
                        <li>
                            <ul>
                                <li>${user.username}</li>
                                <li>${user.password}</li>
                                <li>${user.salt}</li>
                                <li>${user.firstName}</li>
                                <li>${user.lastName}</li>
                                <li>${user.email}</li>
                                <ul>
                                    <c:forEach var="role" items="${user.roles}">
                                        <li>${role.role}</li>
                                    </c:forEach>
                                </ul>
                            </ul>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </jsp:body>

</t:defaultPage>
