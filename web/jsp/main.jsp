<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<html>
<head>
    <title><fmt:message key="title.main" bundle="${bundle}"/> </title>

    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%@include file="header.jsp"%>


<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
    <div class="col-md-5 p-lg-5 mx-auto my-5">
        <h1 class="display-4 font-weight-normal"><fmt:message key="title.welcome" bundle="${ bundle }"/> <c:out value="${user.login}"/>!</h1>
        <p class="lead font-weight-normal"><fmt:message key="label.welcome" bundle="${ bundle }"/></p>
        <a class="btn btn-outline-secondary" href="period?command=edition_list"><fmt:message key="btn.toedition" bundle="${ bundle }"/></a>
    </div>
    <div class="product-device shadow-sm d-none d-md-block"></div>
    <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>

    <c:choose>
        <c:when test="${ not empty emptyPage }">
            <br/>
            <span style="color: #ff0000;">${emptyPage}</span>
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="footer.jsp"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>
