<%@ page import="static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.SESSION_SERVICE_TYPE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  String vServiceType = (String) request.getSession().getAttribute(SESSION_SERVICE_TYPE);
  if (vServiceType != null) {
%>
<c:set var="allStatus" value="<%=vServiceType%>"/>
<%} else {%>

<c:set var="allStatus" value=","/>
<c:forEach var="st" items="${paramValues.serviceTypeIds}">
  <c:set var="allStatus" value="${allStatus}${st},"/>
</c:forEach>
<%}%>



<select name="serviceTypeIds" id="serviceTypeIds" class="form-control">
  <c:forEach var="item" items="${serviceTypes}">
    <c:set var="status2" value="${item.key},"/>
    <option ${item.key eq allStatus ? 'selected' : ''} value="${item.key}"><spring:message code="${item.value}"/></option>
  </c:forEach>
</select>
