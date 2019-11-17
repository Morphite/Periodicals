<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>
<html>
<head>
    <title><fmt:message key="title.login" bundle="${bundle}"/></title>
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

<form action="period" method="post" class="form-signin mt-3 border border-dark rounded">
    <div class="text-center">
        <img class="mb-4" src="/book.ico" alt="" width="72" height="72">
    </div>
    <h1 class="h3 mb-3 font-weight-normal text-center"><fmt:message key="title.register" bundle="${bundle}"/></h1>
    <input type="text" aria-describedby="logHelp" pattern="([A-ZА-Яa-zа-я0-9_-]){3,16}" id="inputLogin" name="login" class="form-control" placeholder=<fmt:message key="label.form.register.login" bundle="${bundle}"/>  required autofocus>
    <small id="logHelp" class="form-text text-muted my-1">
        Min 3 chars. Max - 16. RU, EN letters, 0-9 and _, -.
    </small>
    <input type="password" aria-describedby="passHelp" pattern="([A-ZА-Яa-zа-я0-9_-]){8,20}" id="inputPassword" name="pass" class="form-control" placeholder=<fmt:message key="label.form.register.password" bundle="${bundle}"/> required>
    <small id="passHelp" class="form-text text-muted my-1">
        Min 8 chars. Max - 20. RU, EN letters, 0-9 and _, -.
    </small>
    <input type="email" id="inputEmail" name="email" class="form-control" placeholder=<fmt:message key="label.form.register.email" bundle="${bundle}"/> required>
    <input type="text" aria-describedby="nHelp" pattern="([A-ZА-Яa-zа-я]){2,25}" id="inputName" name="name" class="form-control" placeholder=<fmt:message key="label.form.register.name" bundle="${bundle}"/> required>
    <small id="nHelp" class="form-text text-muted my-1">
        Min 2 chars. Max - 25. RU, EN letters.
    </small>
    <input type="text" aria-describedby="surHelp" pattern="([A-ZА-Яa-zа-я]){2,25}" id="inputSurname" name="surname" class="form-control" placeholder=<fmt:message key="label.form.register.surname" bundle="${bundle}"/> required>
    <small id="surHelp" class="form-text text-muted my-1">
        Min 2 chars. Max - 25. RU, EN letters.
    </small>
    <input type="hidden" name="command" value="register">
    <div class="checkbox mb-3 mt-2">
        <div class="text-center">
            <a href="" style="color: #6c757d ">Forgot password?</a>
        </div>
    </div>
    <c:choose>
        <c:when test="${ not empty errorRegisterMessage }">
            <br/>
            <span style="color: #ff0000;">${errorRegisterMessage}</span>
            <br/>
            <br/>
        </c:when>
        <c:otherwise>
            <br/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${ not empty wrongAction }">
            <br/>
            ${wrongAction}
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${ not empty nullPage }">
            <br/>
            ${nullPage}
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <button class="btn btn-lg btn-dark btn-block" type="submit"><fmt:message key="action.register" bundle="${bundle}"/> </button>
</form>


<%@include file="footer.jsp" %>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>