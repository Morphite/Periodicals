<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<html>
<head>
    <title><fmt:message key="menu.item1" bundle="${bundle}"/></title>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%@include file="/jsp/header.jsp" %>

<div class="d-flex justify-content-center">
    <div class="card mx-1 my-2" style="width: 25rem;">
        <img src="<c:out value="${edition.theme.imgPath}"/>" class="card-img-top" alt="...">
        <div class="card-body">
            <h5 class="card-title"><c:out value="${edition.title}"/></h5>
            <small class="card-text font-weight-bold">Publisher: <c:out value="${edition.publisher}"/></small>
            <h6 class="card-text mt-2"><c:out value="${edition.theme.name}"/></h6>
            <a target="_blank" href="<c:out value="${edition.text}"/>"><p class="card-text mt-2"><c:out value="${edition.text}"/></p></a>
            <button type="button" class="btn btn-outline-dark form-button mt-2" onclick="history.back()"><fmt:message
                    key="action.back" bundle="${ bundle }"/></button>
        </div>
    </div>
</div>

<%@include file="/jsp/footer.jsp" %>

</body>
</html>
