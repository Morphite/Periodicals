
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="resources.text" var="rb"/>

<footer class="page-footer container-fluid py-3 fixed-bottom">
    <div class="row">
        <div class="col-12 col-md">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="d-block mb-2"><circle cx="12" cy="12" r="10"></circle><line x1="14.31" y1="8" x2="20.05" y2="17.94"></line><line x1="9.69" y1="8" x2="21.17" y2="8"></line><line x1="7.38" y1="12" x2="13.12" y2="2.06"></line><line x1="9.69" y1="16" x2="3.95" y2="6.06"></line><line x1="14.31" y1="16" x2="2.83" y2="16"></line><line x1="16.62" y1="12" x2="10.88" y2="21.94"></line></svg>
            <small class="d-block mb-3 text-muted">&copy; 2019-2020</small>
        </div>
        <div class="col-6 col-md">
            <h5><fmt:message key="footer.features.1" bundle="${bundle}"/></h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.2" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.3" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.4" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.5" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.6" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.features.7" bundle="${bundle}"/></a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5><fmt:message key="footer.resources" bundle="${bundle}"/></h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="period?command=edition_list"><fmt:message key="menu.item1" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="period?command=subs_by_user"><fmt:message key="menu.item3" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="period?command=page_profile"><fmt:message key="menu.item4" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="period?command=forward&page=main"><fmt:message key="title.main" bundle="${bundle}"/></a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5><fmt:message key="footer.about.1" bundle="${bundle}"/></h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#"><fmt:message key="footer.about.2" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.about.3" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.about.4" bundle="${bundle}"/></a></li>
                <li><a class="text-muted" href="#"><fmt:message key="footer.about.5" bundle="${bundle}"/></a></li>
            </ul>
        </div>
    </div>
</footer>