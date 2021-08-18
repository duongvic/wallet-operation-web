<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="allStatus" value=","/>
<c:forEach var="st" items="${paramValues.sourceOfFundTypeIds}">
  <c:set var="allStatus" value="${allStatus}${st},"/>
</c:forEach>

<select class="form-control hidden" name="sourceOfFundTypeIds" multiple="multiple" id="sourceOfFundTypeIds">
  <c:forEach var="item" items="${sourceOfFundTypes}">
    <c:set var="status2" value=",${item.key},"/>
    <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item.key}">${item.value}</option>
  </c:forEach>
</select>
