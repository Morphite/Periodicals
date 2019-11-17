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

<h3 class="mt-3 font-weight-normal text-center"><fmt:message key="title.admin.panel" bundle="${bundle}"/></h3>

<div class="container d-flex flex-column flex-md-row justify-content-around" style="color: #5a6268">


    <a class="py-2 d-none d-md-inline-block" href="period?command=edition_admin_list">
        <button class=" btn btn-dark btn-lg"><fmt:message key="menu.item1" bundle="${bundle}"/></button>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="period?command=theme_admin_list">
        <button class=" btn btn-dark btn-lg"><fmt:message key="menu.item2" bundle="${bundle}"/></button>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="period?command=sub_list">
        <button class=" btn btn-dark btn-lg"><fmt:message key="menu.item3" bundle="${bundle}"/></button>
    </a>
    <a class="py-2 d-none d-md-inline-block" href="period?command=user_list">
        <button class=" btn btn-dark btn-lg"><fmt:message key="title.admin.panel.users" bundle="${bundle}"/></button>
    </a>

</div>


<%@include file="/jsp/footer.jsp" %>


</body>
</html>
