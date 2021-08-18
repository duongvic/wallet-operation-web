<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 2/22/2021
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:set var="allServiceCode" value=","/>
<c:forEach var="st" items="${paramValues.serviceCodes}">
    <c:set var="allServiceCode" value="${allServiceCode}${st},"/>
</c:forEach>

<select class="form-control hidden" name="serviceCodes" id="serviceCodes" multiple="multiple">
    <c:forEach var="serviceCode" items="${serviceCodes}">
        <c:set var="serviceCode2" value=",${serviceCode},"/>
        <option ${fn:contains(allServiceCode, serviceCode2)?'selected':'' } value="${serviceCode}">${ewallet:getTopupViettelNameByCode(serviceCode)}</option>
    </c:forEach>
</select>
