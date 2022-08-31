<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta charset="UTF-8">
    <title><fmt:message key="inspector.user.info.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
</head>


<body>

<%@include file="inspector-navbar.html" %>

<div class="container">
    <div style="text-align: center">
        <h1><fmt:message key="inspector.user.info.title"/></h1>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <td class="text-center" colspan="2">
                <c:if test="${requestScope.UserNotFoundException != null}">
                    <span><c:out value="${requestScope.UserNotFoundException}"/></span>
                </c:if>
                <c:if test="${requestScope.errorInvalidParam != null}">
                    <span><c:out value="${requestScope.errorInvalidParam}"/></span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${requestScope.userDTO != null}">
        <tr>
            <td><b><fmt:message key="user.info.fullname"/></b></td>
            <td><span>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName}</span></td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.email"/></b></td>
            <td><span>${requestScope.userDTO.email}</span></td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.tin"/></b></td>
            <td><span>${requestScope.userDTO.tin}</span></td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.registration.date"/></b></td>
            <td><span>${requestScope.userDTO.dateOfRegistration}</span></td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.tin"/></b></td>
            <td><span>${requestScope.userDTO.ipn}</span></td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.entrepreneur"/></b></td>
            <td>
                <span>
                    <fmt:message key="user.data.entrepreneur.name.${requestScope.userDTO.personality}"/>
                </span>
            </td>
        </tr>
        <tr>
            <td><b><fmt:message key="user.info.address"/></b></td>
            <td><span>${requestScope.userDTO.address}</span></td>
        </tr>
        </tbody>
        </c:if>
    </table>
    <div>
        <form action="${pageContext.request.contextPath}/inspector/reports" method="GET">
            <input type="hidden" id="userId" name="userId" value="${requestScope.userDTO.userId}"/>
            <button type="submit" class="btn btn-outline-primary">
                <fmt:message key="inspector.user.reports"/>
            </button>
        </form>
    </div>

</div>
</body>
</html>
