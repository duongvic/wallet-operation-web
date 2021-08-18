<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="allStage" value=","/>
<c:forEach var="st" items="${paramValues.stage}">
  <c:set var="allStage" value="${allStage}${st},"/>
</c:forEach>

<c:set var="listStage" value="<%= FundOrderFlowStageType.values() %>"/>
<select class="form-control multiple-select hidden" multiple="multiple" id="stage" name="stage">
  <c:forEach var="colStage" items="${listStage }">
    <c:set var="status2" value=",${colStage.value()},"/>
    <option ${fn:contains(allStage, status2)?'selected':'' } value="${colStage.value()}"><spring:message code="${colStage.displayText()}"/></option>
  </c:forEach>
</select>