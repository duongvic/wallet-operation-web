
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="allBlockType" value=","/>
<c:forEach var="stBlock" items="${paramValues.blockType}">
    <c:set var="allBlockType" value="${allBlockType}${stBlock},"/>
</c:forEach>

<select class="form-control hidden" name="blockType" id="blockType">
    <c:forEach var="item" items="${listBlockTypes}">
        <c:set var="statusBlock" value=",${item.key},"/>
        <option ${fn:contains(allBlockType, statusBlock)?'selected':'' } value="${item.key}"><spring:message code="${item.value}"/></option>
    </c:forEach>
</select>

