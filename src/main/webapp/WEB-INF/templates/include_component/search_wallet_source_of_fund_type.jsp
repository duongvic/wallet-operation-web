<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletSourceOfFundType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="allWalletSOF" value=","/>
<c:forEach var="ch" items="${paramValues.orderChannelIds}">
  <c:set var="allWalletSOF" value="${allWalletSOF}${ch},"/>
</c:forEach>
<c:set var="listWalletSOF" value="<%= WalletSourceOfFundType.values() %>"/>

<select class="form-control hidden" multiple="multiple" id="walletSourceOfFundIds" name="orderChannelIds">
  <c:forEach var="colWalletSourceOfFund" items="${listWalletSOF}">
    <c:set var="status2" value=",${colWalletSourceOfFund},"/>
    <option ${fn:contains(allWalletSOF, status2)?'selected':'' } value="${colWalletSourceOfFund}"><spring:message code="${colWalletSourceOfFund.getDisplayText()}"/></option>
  </c:forEach>
</select>
