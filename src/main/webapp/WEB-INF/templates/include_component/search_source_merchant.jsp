<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:set var="allsourceMerchant" value=","/>
<c:forEach var="st" items="${paramValues.sourceMerchant}">
  <c:set var="allsourceMerchant" value="${allsourceMerchant}${st},"/>
</c:forEach>

<select class="form-control multiple-select hidden" multiple="multiple" id="sourceMerchant" name="sourceMerchant">
  <c:choose>
    <c:when test="${not empty sourceMerchants && sourceMerchants.size() > 0 }">
      <c:forEach var="item" items="${sourceMerchants}">
        <option value="${item.id}" ${fn:contains(allsourceMerchant, item.id) ? 'selected' : ''}>${item.fullName}</option>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <option value="">N/A</option>
    </c:otherwise>
  </c:choose>
</select>