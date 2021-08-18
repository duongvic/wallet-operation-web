<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 4/26/2021
  Time: 3:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../include_page/taglibs.jsp" %>
<form action="" method="GET" id="tbl-filter" class="mb-md">
    <select class="js-data-example-ajax-account" multiple="multiple" name="customerIds"
            style="margin-bottom: 6px">
    </select>
    <div class="form-inline" style="margin-top: 6px">
        <jsp:include page="../include_component/date_range.jsp"/>
        <jsp:include page="../include_component/search_service_type_multiple.jsp">
            <jsp:param name="enableFiltering" value="false"/>
        </jsp:include>
        <jsp:include page="../include_component/search_customer_type.jsp"/>
        <jsp:include page="../include_component/search_provider_type.jsp"/>
        <jsp:include page="../include_component/search_transaction_status.jsp"/>

        <a class="btn btn-default" type="button" href="${contextPath}/service/transaction-dashboard/index">
            <spring:message code="common.btn.cancel"/>
        </a>
        <button class="btn btn-primary" type="submit">
            <spring:message code="transaction.log.search"/>
        </button>
    </div>
</form>
