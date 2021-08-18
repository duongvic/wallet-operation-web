<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 3/2/2021
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="allChannel" value=","/>
<c:forEach var="ch" items="${paramValues.channel}">
    <c:set var="allChannel" value="${allChannel}${ch},"/>
</c:forEach>
<c:set var="listChannel" value="<%= PaymentChannel.values() %>"/>

<select class="form-control multiple-select hidden" multiple="multiple" id="channels" name="channels">
    <c:forEach var="colChannel" items="${listChannel}">
        <c:set var="status2" value=",${colChannel},"/>
        <option ${fn:contains(allChannel, status2)?'selected':'' } value="${colChannel}"><spring:message code="${colChannel.displayText}"/></option>
    </c:forEach>
</select>
