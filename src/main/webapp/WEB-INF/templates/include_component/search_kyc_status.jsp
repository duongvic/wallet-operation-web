
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycRequestStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="allKycTypes" value=","/>
<c:forEach var="stKyc" items="${paramValues.kycRequestStatus}">
  <c:set var="allKycTypes" value="${allKycTypes}${stKyc},"/>
</c:forEach>

<c:set var="listKycType" value="<%= KycRequestStatus.values() %>"/>

<select class="form-control hidden" multiple="multiple" id="kycRequestStatus" name="kycRequestStatus">
  <c:forEach var="item" items="${listKycType}">
    <c:set var="status2" value=",${item.getCode()},"/>
    <option ${fn:contains(allKycTypes, status2)?'selected':'' } value="${item.getCode()}">${item.getName()}</option>
  </c:forEach>
</select>
