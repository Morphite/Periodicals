<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="own" uri="customtags" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<html>
<head>
    <title><fmt:message key="menu.item2" bundle="${bundle}"/></title>

    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<%@include file="/jsp/header.jsp" %>

<h3 class="mt-3 mb-3 font-weight-normal text-center"><fmt:message key="menu.item2"
                                                                  bundle="${bundle}"/></h3>

<own:list-table>
    <own:thead-dark>
    <tr>
        <th><fmt:message key="label.admin.user.id" bundle="${ bundle }"/></th>
        <th><fmt:message key="label.name" bundle="${ bundle }"/></th>
        <th><fmt:message key="label.imgPath" bundle="${ bundle }"/></th>
        <th class="">
            <a href="period?command=forward&page=create_theme">
                <button class="btn btn-outline-success"><fmt:message key="action.create" bundle="${ bundle }"/></button>
            </a>
        </th>
    </tr>
    </own:thead-dark>
    <tbody>
    <c:forEach var="theme" items="${themes}">
        <tr>
            <td><c:out value="${theme.id}"/></td>
            <td><c:out value="${theme.name}"/></td>
            <td><c:out value="${theme.imgPath}"/></td>
            <td>
                <a href="period?command=page_edit_theme&id=<c:out value="${theme.id}"/>">
                    <button class="btn btn-outline-success mb-1"><fmt:message key="action.edit"
                                                                              bundle="${ bundle }"/></button>
                </a>
                <button type="button" data-toggle="modal" data-target="#deleteModal-<c:out value="${theme.id}"/>" class="btn btn-outline-danger">
                    <fmt:message key="action.delete" bundle="${ bundle }"/></button>

                <div class="modal fade" id="deleteModal-<c:out value="${theme.id}"/>" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="modal.title.delete.theme"
                                                                                            bundle="${ bundle }"/></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <fmt:message key="modal.text.delete.theme" bundle="${ bundle }"/><c:out
                                    value="${theme.id}"/>?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                                        key="action.cancel" bundle="${ bundle }"/></button>
                                <a href="period?command=delete_theme&id=<c:out value="${theme.id}"/>">
                                    <button type="button" class="btn btn-danger"><fmt:message key="action.delete"
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
</own:list-table>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>


</body>
</html>
