<%@ page contentType="text/html; charset=ISO-8859-5" pageEncoding="ISO-8859-5" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="login.login"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
</head>
<body class="h-100">
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <div class="navbar-nav ml-auto">
        <div class="form-group row">
            <div class="col-md-5">
                <a href="?lang=en">
                    <fmt:message key="lang.en"/>
                </a>
            </div>
            <div class="col-md-5">
                <a href="?lang=ua">
                    <fmt:message key="lang.ua"/>
                </a>
            </div>
        </div>
    </div>
</nav>
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
            <div class="container alert alert-primary">
                <form class="form-example" name="f" action="login" method="post">

                    <div style="text-align: center">
                        <h1><fmt:message key="login.login"/></h1>
                    </div>

                    <div class="form-group">
                        <label for="email"><fmr:message key="data.input.email"/></label>
                        <input type="text" id="email" name="email" class="form-control"
                               placeholder="<fmt:message key="data.input.email"/>"
                               value="${requestScope.email}">
                    </div>

                    <div class="form-group">
                        <label for="password"><fmr:message key="data.input.password"/></label>
                        <input type="password" id="password" name="password" class="form-control"
                               placeholder="<fmt:message key="data.input.password"/>">
                    </div>

                    <div class="col text-center">
                        <button type="submit" style="width: 140px" class="btn btn-primary btn-lg">
                            <fmt:message key="login.login"/>
                        </button>
                    </div>
                </form>
                <c:if test="${requestScope.exceptionLogin != null}">
                    <div class="alert alert-error">
                        <div class="alert alert-danger" role="alert">
                            <div style="text-align: center" class="error-invalid">
                                    ${requestScope.exceptionLogin}
                            </div>
                        </div>
                    </div>
                </c:if>
                <a href="registration"><fmt:message key="login.notRegistered"/></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
