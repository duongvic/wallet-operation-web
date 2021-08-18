<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 12/15/2020
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<select name="paymentChannelId" id="paymentChannelId" class="form-control">
    <c:forEach var="item" items="${paymentChannels}">
        <option ${item.code eq param.paymentChannelId ? 'selected' : ''} value="${item.code}">${item.displayText}</option>
    </c:forEach>
</select>
