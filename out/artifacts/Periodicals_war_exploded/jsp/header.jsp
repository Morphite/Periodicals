<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="bundle"/>

<head>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<header>
    <nav class="site-header sticky-top py-1">
        <div class="container d-flex flex-column flex-md-row justify-content-between">
            <a class="py-2" href="period?command=forward&page=main">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="d-block mx-auto">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="14.31" y1="8" x2="20.05" y2="17.94"></line>
                    <line x1="9.69" y1="8" x2="21.17" y2="8"></line>
                    <line x1="7.38" y1="12" x2="13.12" y2="2.06"></line>
                    <line x1="9.69" y1="16" x2="3.95" y2="6.06"></line>
                    <line x1="14.31" y1="16" x2="2.83" y2="16"></line>
                    <line x1="16.62" y1="12" x2="10.88" y2="21.94"></line>
                </svg>
            </a>
            <c:choose>
                <c:when test="${ not empty user }">
                    <a class="py-2 d-none d-md-inline-block" href="period?command=edition_list"><fmt:message key="menu.item1"
                                                                                   bundle="${bundle}"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="#"><fmt:message key="menu.item2"
                                                                                   bundle="${bundle}"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="period?command=subs_by_user"><fmt:message key="menu.item3"
                                                                                   bundle="${bundle}"/></a>
                    <a class="py-2 d-none d-md-inline-block" href="period?command=page_profile"><fmt:message key="menu.item4"
                                                                                   bundle="${bundle}"/></a>
                </c:when>
                <c:otherwise>
                    <a class="py-2 d-none d-md-inline-block" href="period?command=forward&page=login"><fmt:message
                            key="title.login" bundle="${bundle}"/></a>
                </c:otherwise>
            </c:choose>

            <div>
                <div class="dropdown">
                    <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="title.lang" bundle="${bundle}"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item bg-dark" href="period?command=english_language">EN</a>
                        <a class="dropdown-item bg-dark" href="period?command=russian_language">RU</a>
                    </div>
                </div>
            </div>
            <c:if test="${admin}">
                <div class="nav-admin py-2 d-none d-md-inline-block" style="color: white">
                    <a href="period?command=forward&page=admin_panel"><fmt:message key="title.admin" bundle="${bundle}"/><i class="fa fa-user ml-1" aria-hidden="true"></i></a>
                </div>
            </c:if>
            <c:choose>
                <c:when test="${ not empty user }">
                    <div class="nav-admin py-2 d-none d-md-inline-block" style="color: white">
                        <a href="period?command=logout"><fmt:message key="action.logout" bundle="${bundle}"/></a>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>

</header>