<%@ include file="../include_page/taglibs.jsp" %>
<section class="panel panel-default">
  <div class="panel-title">
    <h4 class="fl">
      <spring:message code="transaction.api.detail.basicInfo"/>
    </h4>
    <ul class="panel-tools fl tool-filter">
      <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
    </ul>
    <div class="clr"></div>
  </div>
  <div class="panel-body report_search_form">
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.transactionID"/></label>
      <div class="col-sm-9 col-md-10 text-normal">${transaction.id }</div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.time"/></label>
      <div class="col-sm-9 col-md-10 text-normal"><fmt:formatDate value="${transaction.lastEventTime }" pattern="HH:mm dd-MM-yyyy"/></div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.initiate.transfer.transferAmount"/></label>
      <div class="col-sm-9 col-md-10 text-primary">${ewallet:formatNumber(transaction.realAmount)}&nbsp;VND</div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.createdAt"/></label>
      <div class="col-sm-9 col-md-10 text-normal"><fmt:formatDate value="${transaction.creationDate }" pattern="HH:mm dd/MM/yyyy"/></div>
    </div>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.createdRemark"/></label>
      <div class="col-sm-9 col-md-10 text-normal">${transaction.orderInfo }</div>
    </div>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.approvedRemark"/></label>
      <div class="col-sm-9 col-md-10 text-normal">${transaction.orderInfo }</div>
    </div>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transfer.detail.status"/></label>
      <div class="col-sm-9 col-md-10 text-normal"><spring:message code="${transaction.getStatus()}"/></div>
    </div>
  </div>
</section>