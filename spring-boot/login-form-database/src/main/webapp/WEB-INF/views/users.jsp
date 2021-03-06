<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:defaultPage>

    <jsp:attribute name="header">
      <t:defaultHeader/>
    </jsp:attribute>

    <jsp:body>
        <main class="container" role="main">
            <div class="starter-template">
                <h3><spring:message code="users.page.title"/></h3>
                <security:authorize access="hasRole('ADMIN')">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Username</th>
                            <th scope="col">Password</th>
                            <th scope="col">Email</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Roles</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.password}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${fn:join(user.roles, ', ')}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </security:authorize>
                <security:authorize access="!hasRole('ADMIN')">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="access.not.authorized"/>
                    </div>
                </security:authorize>
            </div>
        </main>
    </jsp:body>

</t:defaultPage>
