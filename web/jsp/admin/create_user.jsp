<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<html>
<head>
    <title><fmt:message key="title.admin.panel" bundle="${bundle}"/></title>

    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%@include file="/jsp/header.jsp" %>

<div class="container">
    <h3 class="mt-4 text-center"><fmt:message key="title.admin.create-user" bundle="${bundle}"/></h3>

    <form action="period" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="create_user">

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputLogin"><fmt:message key="label.admin.user.login" bundle="${bundle}"/></label>
                <input type="text" pattern="([A-ZА-Яa-zа-я0-9_-]){3,16}" name="login" class="form-control" id="inputLogin"
                       placeholder=<fmt:message key="label.admin.user.login" bundle="${bundle}"/>>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPass"><fmt:message key="label.password" bundle="${bundle}"/></label>
                <input type="password" pattern="([A-ZА-Яa-zа-я0-9_-]){8,20}" name="pass" class="form-control" id="inputPass"
                       placeholder=<fmt:message key="label.password" bundle="${bundle}"/>>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName"><fmt:message key="label.admin.user.name" bundle="${bundle}"/></label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder=<fmt:message
                        key="label.admin.user.name" bundle="${bundle}"/>>
            </div>
            <div class="form-group col-md-6">
                <label for="inputSurname"><fmt:message key="label.admin.user.surname" bundle="${bundle}"/></label>
                <input type="text" name="surname" class="form-control" id="inputSurname" placeholder=<fmt:message
                        key="label.admin.user.surname" bundle="${bundle}"/>>
            </div>
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input name="admin" class="form-check-input" type="checkbox" id="adminCheck" value="true">
                <label class="form-check-label" for="adminCheck">
                    <fmt:message key="label.admin.user.admin" bundle="${bundle}"/>
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input name="ban" class="form-check-input" type="checkbox" id="banCheck" value="true">
                <label class="form-check-label" for="banCheck">
                    <fmt:message key="label.admin.user.ban" bundle="${bundle}"/>
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="email" name="email" class="form-control" id="inputEmail" placeholder=<fmt:message
                    key="label.admin.user.email" bundle="${bundle}"/>>
        </div>
        <div class="form-group">
            <label for="inputBal"><fmt:message key="label.admin.user.balance" bundle="${bundle}"/></label>
            <input type="number" min="1" max="10000" name="balance" class="form-control" id="inputBal" placeholder=<fmt:message
                    key="label.admin.user.balance" bundle="${bundle}"/>>
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="finalCheck" required>
                <label class="form-check-label" for="finalCheck">
                    <fmt:message key="label.admin.users.create.confirm" bundle="${bundle}"/>
                </label>
            </div>
        </div>
        <div class="control-buttons">
            <button type="submit" class="btn btn-success form-button"><fmt:message key="action.create"
                                                                                   bundle="${ bundle }"/></button>
            <button type="button" class="btn btn-outline-dark form-button" onclick="history.back()"><fmt:message
                    key="action.cancel" bundle="${ bundle }"/></button>
        </div>
        <c:choose>
            <c:when test="${ not empty errorUserCreate }">
                <br/>
                <span style="color: #ff0000;">${errorUserCreate}</span>
                <br/>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </form>
</div>


<%@include file="/jsp/footer.jsp" %>

</body>
</html>
