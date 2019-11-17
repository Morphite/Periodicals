<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<html>
<head>
    <title><fmt:message key="title.admin.panel.subs" bundle="${bundle}"/></title>

    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%@include file="/jsp/header.jsp" %>

<h3 class="mt-3 mb-3 font-weight-normal text-center"><fmt:message key="title.admin.panel.subs"
                                                                  bundle="${bundle}"/></h3>

<table class="list-table table table-hover text-center">
    <thead class="thead-dark">
    <tr>
        <th><fmt:message key="label.admin.user.id" bundle="${ bundle }"/></th>
        <th><fmt:message key="label.title" bundle="${ bundle }"/></th>
        <th><fmt:message key="label.theme" bundle="${ bundle }"/></th>
        <th><fmt:message key="label.date" bundle="${ bundle }"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sub" items="${subList}">
        <tr>
            <td><c:out value="${sub.id}"/></td>
            <td><c:out value="${sub.edition.title}"/></td>
            <td><c:out value="${sub.edition.theme.name}"/></td>
            <td><c:out value="${sub.date}"/></td>
            <td>
                <button type="button" data-toggle="modal" data-target="#deleteModal-<c:out value="${sub.id}"/>"
                        class="btn btn-outline-danger">
                    <fmt:message key="action.unsub" bundle="${ bundle }"/></button>

                <div class="modal fade" id="deleteModal-<c:out value="${sub.id}"/>" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="action.unsub"
                                                                                            bundle="${ bundle }"/></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <fmt:message key="modal.text.unsub.sub" bundle="${ bundle }"/><c:out
                                    value="${sub.id}"/>?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                                        key="action.cancel" bundle="${ bundle }"/></button>
                                <a href="period?command=unsub&sub_id=<c:out value="${sub.id}"/>">
                                    <button type="button" class="btn btn-danger"><fmt:message key="action.unsub"
                                                                                              bundle="${ bundle }"/></button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="/jsp/footer.jsp" %>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>
