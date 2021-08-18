<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="allStatus" value=","/>
<c:forEach var="st" items="${paramValues.eventTypeId}">
  <c:set var="allStatus" value="${allStatus}${st},"/>
</c:forEach>

<select class="form-control" id="eventTypeId" name="eventTypeId">
  <c:forEach var="item" items="${eventTypes}">
    <c:set var="status2" value=",${item},"/>
    <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.key}">${item.value}</option>
  </c:forEach>
</select>
