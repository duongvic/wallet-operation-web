<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TransactionCustomerType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="allIdOwnerCustomerTypes" value=","/>
<c:forEach var="ch" items="${paramValues.idOwnerCustomerTypes}">
  <c:set var="allIdOwnerCustomerTypes" value="${allIdOwnerCustomerTypes}${ch},"/>
</c:forEach>
<c:set var="IdOwnerCustomerType" value="<%= TransactionCustomerType.values() %>"/>

<select class="form-control hidden" multiple="multiple" id="idOwnerCustomerTypes" name="idOwnerCustomerTypes">
  <c:forEach var="colIdOwnerCustomerType" items="${IdOwnerCustomerType}">
    <option ${fn:contains(allIdOwnerCustomerTypes, colIdOwnerCustomerType)?'selected':'' } value="${colIdOwnerCustomerType}">${colIdOwnerCustomerType.getDisplayText()}</option>
  </c:forEach>
</select>
