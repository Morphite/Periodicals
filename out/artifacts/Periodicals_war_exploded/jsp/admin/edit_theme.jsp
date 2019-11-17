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
    <h3 class="mt-4 text-center"><fmt:message key="title.admin.edit-theme" bundle="${bundle}"/></h3>

    <form action="period" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="edit_theme">
        <input type="hidden" name="id" value="<c:out value="${themeForEdit.id}"/>" >

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName"><fmt:message key="label.name" bundle="${bundle}"/></label>
                <input type="text" pattern="([A-ZА-Яa-zа-я0-9_- ]){3,45}" name="name" class="form-control" id="inputName" value="<c:out value="${themeForEdit.name}"/>"
                       placeholder=<fmt:message key="label.name" bundle="${bundle}"/> required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputImgPath"><fmt:message key="label.imgPath" bundle="${bundle}"/></label>
                <input type="text" pattern="([A-ZА-Яa-zа-я0-9_- ]){5,45}" name="imgPath" class="form-control" id="inputImgPath" value="<c:out value="${themeForEdit.imgPath}"/>"
                       placeholder=<fmt:message key="label.imgPath" bundle="${bundle}"/> required>
            </div>
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="finalCheck" required>
                <label class="form-check-label" for="finalCheck">
                    <fmt:message key="label.admin.edit.edit.confirm" bundle="${bundle}"/>
                </label>
            </div>
        </div>
        <div class="control-buttons">
            <button type="submit" class="btn btn-success form-button"><fmt:message key="action.edit"
                                                                                   bundle="${ bundle }"/></button>
            <button type="button" class="btn btn-outline-dark form-button" onclick="history.back()"><fmt:message
                    key="action.cancel" bundle="${ bundle }"/></button>
        </div>
        <c:choose>
            <c:when test="${not empty errorThemeEdit}">
                <br/>
                <span style="color: #ff0000;">${errorThemeEdit}</span>
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
