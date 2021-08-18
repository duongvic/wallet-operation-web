<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ewallet" uri="https://processing.function.template/service/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="allStatus" value=","/>
<c:forEach var="st" items="${paramValues.provider}">
  <c:set var="allStatus" value="${allStatus}${st},"/>
</c:forEach>

<select class="form-control hidden" name="provider" multiple="multiple" id="provider">
  <c:forEach var="item" items="${providerTypes}">
    <c:set var="status2" value=",${item},"/>
    <option ${fn:contains(allStatus, status2)?'selected':'' } value="${item}">${ewallet:getProviderBizCode(item)}</option>
  </c:forEach>
</select>
