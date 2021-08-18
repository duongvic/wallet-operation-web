<%@ include file="../include_page/taglibs.jsp" %>

<section class="panel panel-default">
  <div class="panel-title pl-none">
    <h4 class="fl">
      <spring:message code="transaction.api.detail.info"/>
    </h4>
    <ul class="panel-tools fl tool-filter">
      <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
    </ul>
    <div class="clr"></div>
  </div>
  <div class="panel-body report_search_form">
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.tran-id"/></label>
      <div class="col-sm-9 col-md-10 text-normal">${transaction.id }</div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.tran-username"/> </label>
      <div class="col-sm-9 col-md-10 text-normal"> ${transaction.payeeUsername}</div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.time"/></label>
      <div class="col-sm-9 col-md-10 text-normal">
        <fmt:formatDate value="${transaction.creationDate }" pattern="HH:mm dd/MM/yyyy"/>
      </div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.type"/></label>
      <div class="col-sm-9 col-md-10 text-normal"><spring:message code="${transaction.getService()}"/></div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.channel"/></label>
      <div class="col-sm-9 col-md-10 text-normal">
        <%--<spring:message code="${transaction.orderChannel}"/>--%>
        <spring:message code="${transaction.textOrderChannel()}"/>
      </div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.amount"/></label>
      <div class="col-sm-9 col-md-10 text-normal primary_color">${ewallet:formatNumber(transaction.realAmount)}VND</div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.commission"/></label>
      <div class="col-sm-9 col-md-10 text-normal primary_color">${ewallet:formatNumber(transaction.commision)}VND</div>
    </div>
    <div class="form-group mb-none">
      <label class="col-sm-3 col-md-2 control-label"><spring:message code="transaction.api.detail.fee"/></label>
      <div class="col-sm-9 col-md-10 text-normal primary_color">${ewallet:formatNumber(transaction.fee)}VND</div>
    </div>
  </div>
</section>