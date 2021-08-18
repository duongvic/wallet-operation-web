<%@ page import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_CONTROLLER" %>
<%@ include file="../include_page/taglibs.jsp" %>

<section class="panel search_payment panel-default">
  <div class="panel-title pl-none">
    <h4 class="fl"><spring:message code="transaction.api.detail.list-event"/></h4>
    <ul class="panel-tools fl tool-filter">
      <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
    </ul>
    <div class="clr"></div>
  </div>
  <div class="panel-body">
    <div class="table-responsive qlsp no-per-page">
      <table class="table table-bordered table-striped mb-small mt-none">
        <thead>
        <tr>
          <th class="stt"><spring:message code="transaction.api.detail.list-event.no"/></th>
          <th><spring:message code="transaction.api.detail.list-event.startDate"/></th>
          <th><spring:message code="transaction.api.detail.list-event.actorId"/></th>
          <th><spring:message code="transaction.api.detail.list-event.actortype"/></th>
          <th><spring:message code="transaction.api.detail.list-event.eventdata"/></th>
          <th><spring:message code="transaction.api.detail.list-event.eventtype"/></th>
          <th><spring:message code="transaction.api.detail.list-event.errorcode"/></th>
          <th><spring:message code="transaction.api.detail.list-event.errormessage"/></th>

          <th><spring:message code="transaction.api.detail.list-event.original.errorcode"/></th>
          <th><spring:message code="transaction.api.detail.list-event.original.errormessage"/></th>

          <th><spring:message code="transaction.api.detail.list-event.txnRef"/></th>
          <th><spring:message code="transaction.api.detail.list-event.executionTime"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${transactionEvents}" var="serial" varStatus="var2">
          <tr>
            <td>${var2.index + 1 }</td>
            <td><fmt:formatDate value="${serial.startTime}" pattern="HH:mm:ss dd/MM/yyyy"/></td>
            <td>${serial.actorId }</td>
            <td><spring:message code="${ewallet:getProviderBizCode(serial.actorType)}"/></td>
            <%--<td> <spring:message code="${serial.textActorType()}"/></td>--%>
            <td>${serial.eventData }</td>
            <td>${serial.eventType }</td>
            <td>${serial.errorCode }</td>
            <td>${serial.errorMessage }</td>
            <td>${serial.originalErrorCode }</td>
            <td>${serial.originalErrorMessage }</td>
            <td>
              <c:if test="${serial.getExtTxnRefId() ne ''}">
                <a href="<%=request.getContextPath()%><%=TRANSACTION_CONTROLLER%>/detail?txnId=${serial.getExtTxnRefId()}">${serial.getExtTxnRefId()}</a>
              </c:if>
            </td>
            <td>${ewallet:formatNumber(serial.endTime.getTime() - serial.startTime.getTime())}&nbsp;(ms)</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</section>