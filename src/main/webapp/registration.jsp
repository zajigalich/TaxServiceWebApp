<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page contentType="text/html; charset=ISO-8859-5" pageEncoding="ISO-8859-5" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>

    <title><fmt:message key="registration.registration"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="form-group row">
        <div class="col-md-5">
            <form style="display: inline;" action="${pageContext.request.contextPath}/" method="get">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="main.main"/>
                </button>
            </form>
        </div>
    </div>

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
                <form action="registration" method="post">

                    <div style="text-align: center">
                        <h1><fmt:message key="registration.registration"/></h1>
                    </div>

                    <div class="form-group">
                        <label for="entrepreneur"><fmt:message key="registration.data.entrepreneur"/></label>
                        <select id="entrepreneur" name="entrepreneur">
                            <option value="PHYSICAL_PERSON">
                                <fmt:message key="user.data.entrepreneur.name.PHYSICAL_PERSON"/>
                            </option>
                            <option value="JURIDICAL_PERSON">
                                <fmt:message key="user.data.entrepreneur.name.JURIDICAL_PERSON"/>
                            </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="firstName"><fmt:message key="registration.data.firstName"/> </label>
                        <input id="firstName" name="firstName" type="text" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.firstName"/>">
                    </div>

                    <div class="form-group">
                        <label for="lastName"><fmt:message key="registration.data.lastName"/> </label>
                        <input id="lastName" name="lastName" type="text" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.lastName"/>">
                    </div>

                    <div class="form-group">
                        <label for="email"><fmt:message key="registration.data.email"/> </label>
                        <input id="email" name="email" type="text" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.email"/>">
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="registration.data.password"/> </label>
                        <input id="password" name="password" type="password" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.password"/>">
                    </div>

                    <div class="form-group">
                        <label for="tin"><fmt:message key="registration.data.tin"/> </label>
                        <input id="tin" name="tin" type="text" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.tin"/>">
                    </div>


                    <div class="form-group">
                        <label for="address"><fmt:message key="registration.data.address"/> </label>
                        <input id="address" name="address" type="text" class="form-control"
                               placeholder="<fmt:message key="registration.data.input.address"/>">
                    </div>

                    <div class="col text-center">
                        <button type="submit" style="width: 200px"
                                class="btn btn-primary btn-lg">
                            <fmt:message key="registration.data.register"/>
                        </button>
                    </div>
                    <c:if test="${requestScope.errorUserExists}">
                        <div class="alert alert-error">
                            <div class="alert alert-danger" role="alert">
                                <div style="text-align: center" class="error-invalid">
                                    <fmt:message key="registration.data.user.exists"/>
                                </div>
                            </div>
                        </div>
                        <c:out value="${requestScope.errorUserExists}"/>
                    </c:if>

                    <br/>
                    <div>
                        <a href="login">
                            <fmt:message key="registration.data.link.login"/>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
