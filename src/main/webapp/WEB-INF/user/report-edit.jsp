`<%@ page contentType="text/html; charset=ISO-8859-5" pageEncoding="ISO-8859-5" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.my.persistence.entity.TaxPeriod" %>
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

<%@include file="user-navbar.html" %>

<div class="container">
    <div class="row justify-content-center align-items-center">
        <div class="col-10 col-md-4">
            <div class="container alert alert-primary">
                <div style="text-align: center">
                    <h1><fmt:message key="user.report.form.edit.title"/></h1>
                </div>

                <div class="form-group">
                    <label for="taxRate"><fmt:message key="user.report.comment"/>:<br/></label>
                    ${sessionScope.reportById.comment}
                </div>


                <div>
                    <form action="${pageContext.request.contextPath}/user/report-edit" method="POST">
                        <div class="form-group">
                            <label for="income"><fmt:message key="user.report.income"/>, $</label>
                            <input type="text" id="income" name="income" class="form-control"
                                   value="${sessionScope.reportById.income}" required/>
                            <c:if test="${requestScope.fields.hasErrors('isIncomeInvalid')}">
                                <div class="alert alert-error">
                                    <div class="alert alert-danger" role="alert">
                                        <div style="text-align: center" class="error-invalid">
                                            <fmt:message key="report.invalid.income"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="taxRate"><fmt:message key="user.report.rate"/>, %:</label>
                            <input type="text" id="taxRate" name="taxRate" class="form-control"
                                   value="${sessionScope.reportById.taxRate}" required/>
                            <c:if test="${requestScope.fields.hasErrors('isTaxRateInvalid')}">
                                <div class="alert alert-error">
                                    <div class="alert alert-danger" role="alert">
                                        <div style="text-align: center" class="error-invalid">
                                            <fmt:message key="report.invalid.rate"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="period"><fmt:message key="user.report.period"/>: </label>

                            <select id="period" name="period" class="form-select" aria-label="Default select example">
                                <option selected value=""><fmt:message key="reports.search.select.period"/></option>
                                <c:forEach var="period" items="${TaxPeriod.values()}">
                                    <option label="<fmt:message key="reports.period.${period}"/>"
                                            value="${period}"
                                            <c:if test="${period == param.get('period')}">selected </c:if>>
                                        <fmt:message key="reports.period.${period.name()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <c:if test="${requestScope.fields.hasErrors('isPeriodInvalid')}">
                                <div class="alert alert-error">
                                    <div class="alert alert-danger" role="alert">
                                        <div style="text-align: center" class="error-invalid">
                                            <fmt:message key="report.invalid.period"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="year"><fmt:message key="user.report.year"/>:</label>
                            <input type="text" id="year" name="year" class="form-control"
                                   value="${sessionScope.reportById.year}" required/>
                            <c:if test="${requestScope.fields.hasErrors('isYearInvalid')}">
                                <div class="alert alert-error">
                                    <div class="alert alert-danger" role="alert">
                                        <div style="text-align: center" class="error-invalid">
                                            <fmt:message key="report.invalid.year"/>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <div style="text-align: center">
                                <input type="submit" class="btn btn-primary center-block"
                                       value="<fmt:message key="user.report.form.apply"/>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
