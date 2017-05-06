<%@tag description="Default page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Simple Login WebApp</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>

</head>
<body>
<jsp:invoke fragment="header"/>

<jsp:doBody/>

<jsp:invoke fragment="footer"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.2.0/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>