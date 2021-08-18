<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="allTagetMerchant" value=","/>
<c:forEach var="st" items="${paramValues.targetMerchant}">
  <c:set var="allTagetMerchant" value="${allTagetMerchant}${st},"/>
</c:forEach>
<select class="form-control multiple-select hidden" multiple="multiple" id="targetMerchant" name="targetMerchant">
  <c:choose>
    <c:when test="${not empty targetMerchants && targetMerchants.size() > 0 }">
      <c:forEach var="item" items="${targetMerchants}">
        <option value="${item.id}" ${fn:contains(allTagetMerchant, item.id) ? 'selected' : ''}>${item.fullName}</option>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <option value="">N/A</option>
    </c:otherwise>
  </c:choose>
</select>