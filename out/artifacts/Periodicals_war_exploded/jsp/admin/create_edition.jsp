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
    <h3 class="mt-4 text-center"><fmt:message key="title.admin.create-edit" bundle="${bundle}"/></h3>

    <form action="period" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="create_edition">

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="selectTheme"><fmt:message key="label.admin.edit.theme" bundle="${bundle}"/></label>
                <select class="form-control" id="selectTheme" name="theme">
                    <c:forEach var="theme" items="${themes}">
                        <option value=<c:out value="${theme.id}"/>><c:out value="${theme.name}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="inputTitle"><fmt:message key="label.admin.edit.title" bundle="${bundle}"/></label>
                <input type="text" pattern="([A-ZА-Яa-zа-я0-9_- ]){3,45}" name="title" class="form-control" id="inputTitle"
                       placeholder=<fmt:message key="label.admin.edit.title" bundle="${bundle}"/> required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPublisher"><fmt:message key="label.admin.edit.publisher" bundle="${bundle}"/></label>
                <input type="text" pattern="([A-ZА-Яa-zа-я0-9_- ]){5,45}" name="publisher" class="form-control" id="inputPublisher" placeholder=<fmt:message
                        key="label.admin.edit.publisher" bundle="${bundle}"/> required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputText"><fmt:message key="label.admin.edit.text" bundle="${bundle}"/></label>
                <input type="text" name="text" pattern="([A-ZА-Яa-zа-я0-9_- ]){10,100}" class="form-control" id="inputText" placeholder=<fmt:message
                        key="label.admin.edit.text" bundle="${bundle}"/> required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrice"><fmt:message key="label.admin.edit.price" bundle="${bundle}"/></label>
            <input type="number" min="1" max="1000" name="price" class="form-control" id="inputPrice" placeholder=<fmt:message
                    key="label.admin.edit.price" bundle="${bundle}"/> required>
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="finalCheck" required>
                <label class="form-check-label" for="finalCheck">
                    <fmt:message key="label.admin.edit.create.confirm" bundle="${bundle}"/>
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
            <c:when test="${ not empty errorEditCreate }">
                <br/>
                <span style="color: #ff0000;">${errorEditCreate}</span>
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
