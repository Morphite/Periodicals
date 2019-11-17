<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>
<html>
<head>
    <title><fmt:message key="title.profile" bundle="${bundle}"/></title>
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


<div class="profile container mt-5">

    <div class="row">
        <div class="col">
            <ul class="list-group">
                <li class="list-group-item list-group-item-secondary"><fmt:message key="label.user.menu.profile-info" bundle="${bundle}"/></li>
                <li class="list-group-item">ID: <c:out value="${ user.id }"/></li>
                <li class="list-group-item"><fmt:message key="label.admin.user.login" bundle="${bundle}"/>: <c:out
                        value="${ user.login }"/></li>
                <li class="list-group-item"><fmt:message key="label.admin.user.email" bundle="${bundle}"/>: <c:out
                        value="${ user.email }"/></li>
                <li class="list-group-item"><fmt:message key="label.admin.user.name" bundle="${bundle}"/>: <c:out
                        value="${ user.name }"/></li>
                <li class="list-group-item"><fmt:message key="label.admin.user.surname" bundle="${bundle}"/>: <c:out
                        value="${ user.surname }"/></li>

                <button type="button" data-toggle="modal" data-target="#editModal-<c:out value="${user.id}"/>" class="btn btn-success mt-2">
                    <fmt:message key="action.edit" bundle="${ bundle }"/></button>
                <c:choose>
                    <c:when test="${ not empty errorUserEdit }">
                        <br/>
                        <span style="color: #ff0000;">${errorUserEdit}</span>
                        <br/>
                        <br/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>


                <div class="modal fade" id="editModal-<c:out value="${user.id}"/>" tabindex="-1" role="dialog"
                     aria-labelledby="editModalLabel"
                     aria-hidden="true">

                    <form action="period" method="post">

                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h5 class="modal-title" id="editModalLabel"><fmt:message
                                            key="title.admin.edit-user" bundle="${ bundle }"/></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group col-md-6">
                                        <input type="hidden" name="command" value="edit_user_profile">

                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputLogin"><fmt:message key="label.admin.user.login" bundle="${bundle}"/></label>
                                                <input type="text" name="login" pattern="([A-ZА-Яa-zа-я0-9_-]){3,16}" class="form-control" id="inputLogin" value="<c:out value="${user.login}"/>"
                                                       placeholder=<fmt:message key="label.admin.user.login" bundle="${bundle}"/> required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="profileInputEmail">Email</label>
                                                <input type="email" name="email" class="form-control" id="profileInputEmail" value="<c:out value="${user.email}"/>" placeholder=<fmt:message
                                                        key="label.admin.user.email" bundle="${bundle}"/> required>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputName"><fmt:message key="label.admin.user.name" bundle="${bundle}"/></label>
                                                <input type="text" name="name" pattern="([A-ZА-Яa-zа-я]){2,25}" class="form-control" id="inputName" value="<c:out value="${user.name}"/>" placeholder=<fmt:message
                                                        key="label.admin.user.name" bundle="${bundle}"/> required>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="inputSurname"><fmt:message key="label.admin.user.surname" bundle="${bundle}"/></label>
                                                <input type="text" name="surname" pattern="([A-ZА-Яa-zа-я]){2,25}" class="form-control" id="inputSurname" value="<c:out value="${user.surname}"/>" placeholder=<fmt:message
                                                        key="label.admin.user.surname" bundle="${bundle}"/> required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="checkbox" id="finalCheck" required>
                                                <label class="form-check-label" for="finalCheck">
                                                    <fmt:message key="label.admin.users.edit.confirm" bundle="${bundle}"/>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                                            key="action.cancel" bundle="${ bundle }"/></button>
                                    <button type="submit" class="btn btn-success"><fmt:message key="action.edit"
                                                                                               bundle="${ bundle }"/></button>

                                </div>
                            </div>
                        </div>
                    </form>
                </div>


            </ul>
        </div>

        <div class="col">
            <ul class="list-group">
                <li class="list-group-item list-group-item-secondary"><fmt:message key="label.admin.user.balance"
                                                                                   bundle="${ bundle }"/></li>
                <li class="list-group-item"><c:out value="${ user.balance }"/></li>
                <button type="button" data-toggle="modal" data-target="#deleteModal-<c:out value="${user.id}"/>"
                        class="btn btn-success mt-2">
                    <fmt:message key="action.add" bundle="${ bundle }"/></button>
                <c:choose>
                    <c:when test="${ not empty errorBalanceAddMessage }">
                        <br/>
                        <span style="color: #ff0000;">${errorBalanceAddMessage}</span>
                        <br/>
                        <br/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>


                <div class="modal fade" id="deleteModal-<c:out value="${user.id}"/>" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
                     aria-hidden="true">

                    <form action="period" method="post">

                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">

                                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                            key="modal.title.addmoney" bundle="${ bundle }"/></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group col-md-6">
                                        <label for="inputMoney"><fmt:message key="label.user.menu.money"
                                                                             bundle="${bundle}"/></label>
                                        <input type="number" min="10" max="5000" name="money" class="form-control" id="inputMoney"
                                               placeholder=<fmt:message key="label.user.menu.money"
                                                                        bundle="${bundle}"/>>
                                        <input type="hidden" name="command" value="add_money">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                                            key="action.cancel" bundle="${ bundle }"/></button>
                                    <button type="submit" class="btn btn-success"><fmt:message key="action.add"
                                                                                               bundle="${ bundle }"/></button>

                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </ul>
        </div>
    </div>


    <div class="row">
        <div class="col">
            <form class="mx-n3 mt-4" action="period" method="post">
                <input type="hidden" name="command" value="change_pass">
                <div class="form-group col-md-6">
                    <label for="oldPass"><fmt:message key="label.change.password.old" bundle="${bundle}"/></label>
                    <input type="password" pattern="([A-ZА-Яa-zа-я0-9_-]){8,20}" name="oldPass" class="form-control"
                           id="oldPass"
                           placeholder=<fmt:message key="label.change.password.old" bundle="${bundle}"/>>
                    <label for="newPass"><fmt:message key="label.change.password.new" bundle="${bundle}"/></label>
                    <input type="password" pattern="([A-ZА-Яa-zа-я0-9_-]){8,20}" name="newPass" class="form-control"
                           id="newPass"
                           placeholder=<fmt:message key="label.change.password.new" bundle="${bundle}"/>>
                    <button type="submit" class="btn btn-success form-button mt-2"><fmt:message key="action.change"
                                                                                                bundle="${ bundle }"/></button>
                </div>
            </form>
            <c:choose>
                <c:when test="${ not empty passChange }">
                    <br/>
                    <span style="color: #00ff48;">${passChange}</span>
                    <br/>
                    <br/>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


</div>


<%@include file="/jsp/footer.jsp" %>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>
