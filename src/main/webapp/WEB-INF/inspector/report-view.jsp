<%@ page contentType="text/html; charset=ISO-8859-5" pageEncoding="ISO-8859-5" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="user.report.data.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
</head>
<body>

<%@include file="inspector-navbar.html" %>

<c:set var="report" value="${sessionScope.report}" scope="session"/>
<div class="container">
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><b><fmt:message key="user.info.fullname"/></b></td>
                <td><span>${report.userDTO.firstName} ${report.userDTO.lastName}</span></td>
            </tr>
            <tr>
                <td><b><fmt:message key="user.info.tin"/></b></td>
                <td><span>${report.userDTO.tin}</span></td>
            </tr>
            <tr>
                <td><b><fmt:message key="user.info.entrepreneur"/></b></td>
                <td><span><fmt:message key="user.data.entrepreneur.name.${report.userDTO.type}"/></span></td>
            </tr>
            <tr>
                <td><p><fmt:message key="report.data.col.income"/>, $</p></td>
                <td><span>${report.income}</span></td>
            </tr>
            <tr>
                <td><p><fmt:message key="report.data.col.rate"/>, %</p></td>
                <td><span>${report.taxRate}</span></td>
            </tr>
            <tr>
                <td><p><fmt:message key="report.data.col.date"/></p></td>
                <td><span>${report.reportDate}</span></td>
            </tr>
            <tr>
                <td><p><fmt:message key="report.data.col.year"/></p></td>
                <td><span>${report.year}</span></td>
            </tr>
            <tr>
                <td><p><fmt:message key="report.data.col.period"/></p></td>
                <td><span><fmt:message key="reports.period.${report.taxPeriod}"/></span></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div>
        <form name="f" action="${pageContext.request.contextPath}/inspector/report-view" method="post">
            <input type="hidden" id="reportId" name="reportId" value="${report.id}">
            <div class="form-group">
                <label for="comment"><fmt:message key="report.data.col.comment"/></label>
                <input type="text" id="comment" name="comment" value="${report.comment}"
                       class="form-control rounded-0"/>

            </div>
            <div>
                <select id="status" name="status">
                    <option value=""><fmt:message key="reports.search.select.status"/></option>
                    <option value="APPROVED"><fmt:message key="reports.status.APPROVED"/></option>
                    <option value="DISAPPROVED"><fmt:message key="reports.status.DISAPPROVED"/></option>
                </select>
            </div>

            <div>
                <button type="submit" class="btn btn-outline-primary">
                    <fmt:message key="inspector.report.view.add.comment"/>
                </button>
            </div>
        </form>
        <c:if test="${requestScope.error != null}">
            <div class="alert alert-danger">
                <label class="validation-message">${requestScope.error}</label>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
