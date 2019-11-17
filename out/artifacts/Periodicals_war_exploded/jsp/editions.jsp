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

<div class="d-flex justify-content-center flex-wrap">

    <c:if test="${ not empty errorSub }">
        <br/>
        <span style="color: #ff0000;">${errorSub}</span>
        <br/>
        <br/>
    </c:if>

    <div>
        <div class="dropdown mt-2">
            <a class="btn btn-info dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="action.sort" bundle="${bundle}"/>
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <c:choose>
                    <c:when test="${empty crit}">
                        <a class="dropdown-item" href="period?command=sort&crit=name&order=true"><fmt:message key="label.sort.name" bundle="${bundle}"/></a>
                        <a class="dropdown-item" href="period?command=sort&crit=theme&order=true"><fmt:message key="label.sort.theme" bundle="${bundle}"/></a>
                        <a class="dropdown-item" href="period?command=sort&crit=price&order=true"><fmt:message key="label.sort.price" bundle="${bundle}"/></a>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${'name' == crit}">
                                <c:choose>
                                    <c:when test="${order}">
                                        <a class="dropdown-item" href="period?command=sort&crit=name&order=false"><fmt:message key="label.sort.name" bundle="${bundle}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="dropdown-item" href="period?command=sort&crit=name&order=true"><fmt:message key="label.sort.name" bundle="${bundle}"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item" href="period?command=sort&crit=name&order=true"><fmt:message key="label.sort.name" bundle="${bundle}"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${'theme' == crit}">
                                <c:choose>
                                    <c:when test="${order}">
                                        <a class="dropdown-item" href="period?command=sort&crit=theme&order=false"><fmt:message key="label.sort.theme" bundle="${bundle}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="dropdown-item" href="period?command=sort&crit=theme&order=true"><fmt:message key="label.sort.theme" bundle="${bundle}"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item" href="period?command=sort&crit=theme&order=true"><fmt:message key="label.sort.theme" bundle="${bundle}"/></a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${'price' == crit}">
                                <c:choose>
                                    <c:when test="${order}">
                                        <a class="dropdown-item" href="period?command=sort&crit=price&order=false"><fmt:message key="label.sort.price" bundle="${bundle}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="dropdown-item" href="period?command=sort&crit=price&order=true"><fmt:message key="label.sort.price" bundle="${bundle}"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item" href="period?command=sort&crit=price&order=true"><fmt:message key="label.sort.price" bundle="${bundle}"/></a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

        <div class="dropdown mt-1">
            <a class="btn btn-info dropdown-toggle" href="#" role="button" id="dropdownSearch" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="label.search" bundle="${bundle}"/>
            </a>

            <div class="dropdown-menu" aria-labelledby="dropdownSearch">
                <form class="form-inline" style="width: 16rem">
                    <input type="hidden" name="command" value="search">
                    <input class="form-control mr-sm-2" name="searchStr" type="text" placeholder="<fmt:message key="label.search" bundle="${bundle}"/>" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit"><i class="fa fa-search" aria-hidden="true" style="font-size: 22px;"></i></button>
                </form>
            </div>
        </div>

        <div class="dropdown mt-1">
            <a class="btn btn-info dropdown-toggle" href="#" role="button" id="dropdownTheme" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="label.sort.theme" bundle="${bundle}"/>
            </a>

            <div class="dropdown-menu" aria-labelledby="dropdownTheme">
                <form class="form-inline" action="period" method="post" style="width: 12rem">
                    <input type="hidden" name="command" value="editions_by_theme">

                    <select class="form-control mr-2" id="selectTheme" name="theme_id" title="themeSelect">
                        <c:forEach var="theme" items="${themes}">
                            <c:choose>
                                <c:when test="${ prevThemeId == theme.id }">
                                    <option selected value=<c:out value="${theme.id}"/>><c:out
                                            value="${theme.name}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option value=<c:out value="${theme.id}"/>><c:out value="${theme.name}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <button class="btn btn-outline-success" type="submit"><i class="fa fa-search" aria-hidden="true" style="font-size: 22px;"></i></button>
                </form>
            </div>
        </div>





    </div>


    <c:forEach var="edit" items="${editions}">
        <form action="period" method="post">
            <div class="card mx-1 my-2" style="width: 18rem;">
                <img src="<c:out value="${edit.theme.imgPath}"/>" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${edit.title}"/></h5>
                    <small class="card-text font-weight-bold"><fmt:message key="label.publisher" bundle="${bundle}"/>: <c:out value="${edit.publisher}"/></small>
                    <h6 class="card-text mt-2"><c:out value="${edit.theme.name}"/></h6>
                    <c:set var="match" value="false"/>
                    <c:set var="sub_id" value="0"/>
                    <c:forEach var="sub" items="${subs}">
                        <c:if test="${edit.id == sub.edition.id}">
                            <c:set var="match" value="true"/>
                            <c:set var="sub_id" value="${sub.id}"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${match}">
                            <input type="hidden" name="command" value="unsubscribe">
                            <input type="hidden" name="sub_id" value="<c:out value="${sub_id}"/>">

                            <button type="submit" class="btn btn-warning"><fmt:message key="action.unsub" bundle="${bundle}"/></button>
                            <br>
                            <a href="period?command=read_edition&id=<c:out value="${edit.id}"/>" class="btn btn-info mt-1"><fmt:message key="action.read" bundle="${bundle}"/></a>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" value="subscribe">
                            <input type="hidden" name="user_id" value="<c:out value="${user.id}"/>">
                            <input type="hidden" name="edition_id" value="<c:out value="${edit.id}"/>">
                            <input type="hidden" name="price" value="<c:out value="${edit.price}"/>">
                            <button type="submit" class="btn btn-success"><fmt:message key="action.sub" bundle="${bundle}"/> <c:out value="${edit.price}"/>$</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form>
    </c:forEach>

</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.js"></script>

</body>
</html>
