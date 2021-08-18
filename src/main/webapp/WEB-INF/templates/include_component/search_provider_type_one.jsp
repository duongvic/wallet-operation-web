
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:set var="allProviderService" value=","/>
<c:forEach var="stProviderService" items="${paramValues.provider}">
  <c:set var="allProviderService"
         value="${allProviderService}${stProviderService},"/>
</c:forEach>

<select class="form-control hidden" id="provider"
        name="provider">
  <option value = "" ><spring:message code="select.provider"/></option>
  <c:choose>
    <c:when test="${not empty providerTypes && providerTypes.size() > 0 }">
      <c:forEach var="item" items="${providerTypes}">
        <option value="${item.providerCode}" ${fn:contains(allProviderService, item.providerCode) ? 'selected' : ''}>${item.providerBizCode}</option>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <option value="">N/A</option>
    </c:otherwise>
  </c:choose>
</select>


