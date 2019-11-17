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

    <link rel="icon" href="/book2.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="/book2.ico">
</head>
<body>

<%@include file="/jsp/header.jsp" %>

<form action="period" method="post" class="form-signin mt-5 border-dark rounded">
    <div class="text-center">
        <img class="mb-4" src="/book2.ico" alt="" width="72" height="72">
    </div>
    <h1 class="h3 mb-3 font-weight-normal text-center"><fmt:message key="action.login" bundle="${bundle}"/></h1>
    <input type="text" id="inputEmail" name="login" class="form-control" placeholder=<fmt:message key="label.login" bundle="${bundle}"/>  required autofocus>
    <input type="password" id="inputPassword" name="pass" class="form-control" placeholder=<fmt:message key="label.password" bundle="${bundle}"/> required>
    <input type="hidden" name="command" value="login">
    <div class="checkbox mb-3">
        <div class="text-center">
            <%--<button type="button" data-toggle="modal" data-target="#passModal" class="btn btn-success mt-2">--%>
                <%--<fmt:message key="label.forgotpass" bundle="${ bundle }"/></button>--%>
            <a href="#passModal" data-toggle="modal" style="color: #6c757d "><fmt:message key="label.forgotpass" bundle="${bundle}"/></a>

        </div>
    </div>
    <c:choose>
        <c:when test="${ not empty errorLoginPage }">
            <br/>
            <span style="color: #ff0000;">${errorLoginPage}</span>
            <br/>
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${ not empty emailSendMessage }">
            <br/>
            <span style="color: #00ff18;">${emailSendMessage}</span>
            <br/>
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <button class="btn btn-lg btn-dark btn-block" type="submit"><fmt:message key="action.login" bundle="${bundle}"/></button>
    <a href="period?command=forward&page=register"><button class="regButton text-center btn btn-lg btn-dark btn-block mt-2" type="button"><fmt:message key="action.register" bundle="${bundle}"/> </button></a>
</form>

<div class="modal fade" id="passModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">

    <form action="period" method="post">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">

                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                            key="modal.title.pass" bundle="${ bundle }"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group col-md-6">
                        <label for="inputMoney"><fmt:message key="label.login"
                                                             bundle="${bundle}"/></label>
                        <input type="text" name="login" class="form-control" id="inputMoney"
                               placeholder=<fmt:message key="label.login"
                                                        bundle="${bundle}"/>>
                        <input type="hidden" name="command" value="forgot_pass">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="action.cancel" bundle="${ bundle }"/></button>
                    <button type="submit" class="btn btn-success"><fmt:message key="action.send"
                                                                               bundle="${ bundle }"/></button>

                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp" %>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>