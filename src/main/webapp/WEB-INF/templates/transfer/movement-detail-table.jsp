<%@ include file="../include_page/taglibs.jsp" %>
<section class="panel panel-default">
  <div class="panel-title pl-none">
    <h4 class="fl">
      <spring:message code="transfer.detail.cashflowInfo"/>
    </h4>
    <ul class="panel-tools fl tool-filter">
      <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
    </ul>
    <div class="clr"></div>
  </div>
  <div class="panel-body report_search_form">
    <c:set var="transactionEvents" value="${transactionEvents }"/>
    <c:set value="${transactionEvents.size() }" var="sizeEvents"/>
    <c:set var="transaction" value="${transaction }"/>
    <table id="table" class="dataTable mb-none no-footer table table-bordered table-striped" style="margin-bottom: 10px !important;">
      <thead>
      <tr>
        <th class="left"><spring:message code="transfer.detail.movement.step"/></th>
        <th class="left"><spring:message code="transfer.detail.movement.srcDir"/></th>
        <th class="left"><spring:message code="transfer.detail.movement.destDir"/></th>
        <th class="col-number-header"><spring:message code="transfer.detail.movement.amount"/></th>
        <th class="left"><spring:message code="transfer.detail.movement.eventType"/></th>
        <th class="left"><spring:message code="transfer.detail.movement.errorCode"/></th>
        <th class="left"><spring:message code="transfer.detail.movement.errorMess"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="trans" begin="0" end="${sizeEvents -1 }">
        <tr>
          <td>${trans + 1 }</td>
          <td>(${transaction.payerId }) ${transaction.payerUsername }</td>
          <td>(${transaction.payeeId }) ${transaction.payeeUsername }</td>
          <td class="col-number-header">${ewallet:formatNumber(transaction.amount)}</td>
          <td>${transactionEvents[trans].eventType }</td>
          <td>${transactionEvents[trans].errorCode }</td>
          <td>${transactionEvents[trans].errorMessage }</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</section>