<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType" %>
<%@ include file="../include_page/taglibs.jsp" %>

<section class="panel panel-default">
  <div class="panel-title pl-none">
    <h4 class="fl"><spring:message code="transaction.api.detail.info"/></h4>
    <ul class="panel-tools fl tool-filter">
      <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
    </ul>
    <div class="clr"></div>
  </div>

  <div class="panel-body report_search_form">
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.tran-id"/> </label>
      <div class="col-sm-9 col-md-10 text-normal"> ${transaction.id}</div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label">traceNo</label>
      <div class="col-sm-9 col-md-10 text-normal">${transaction.traceNo}</div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.tran-traceNo"/> </label>
      <div class="col-sm-9 col-md-10 text-normal"> ${transaction.orderId}</div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.tran-username"/> </label>
      <div class="col-sm-9 col-md-10 text-normal"> ${transaction.username}</div>
    </div>

    <c:if test="${transaction.getService() eq 'Bill Payment'}">
      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label"><spring:message
            code="transaction.api.detail.customer-msisdn"/></label>
        <div class="col-sm-9 col-md-10 text-normal"> ${customerMsisdsn}</div>
      </div>
    </c:if>

    <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER','SALE_EXCUTIVE')">
      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label"><spring:message
            code="transaction.api.detail.tran-providerCode"/> </label>
        <div class="col-sm-9 col-md-10 text-normal">${ewallet:getProviderBizCode(transaction.providerCode)}</div>
      </div>
    </sec:authorize>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.time"/> </label>
      <div class="col-sm-9 col-md-10 text-normal">
        <fmt:formatDate value="${transaction.creationDate}" pattern="HH:mm:ss dd/MM/yyyy"/>
      </div>
    </div>
    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.type"/> </label>
      <div class="col-sm-9 col-md-10 text-normal"><spring:message
          code="${transaction.getService()}"/></div>
    </div>

    <!-- them moi -->

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.amount"/> </label>
      <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.amount)}
        VND
      </div>
    </div>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.realamount"/> </label>
      <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.realAmount)}
        VND
      </div>
    </div>

    <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                          'SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER', 'SALESUPPORT',
                                          'FINANCE',
                                          'RECONCILIATION','RECONCILIATION_LEADER')">
      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label"><spring:message
            code="transaction.api.detail.grossProfit"/> </label>
        <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.grossProfit)}
          VND
        </div>
      </div>

      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label"><spring:message
            code="transaction.api.detail.capitalValue"/> </label>
        <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.capitalValue)}
          VND
        </div>
      </div>

      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label"><spring:message
            code="transaction.api.detail.commission"/> </label>
        <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.commision)}
          VND
        </div>
      </div>
    </sec:authorize>


    <c:if test="${transaction.serviceType eq 'WALLET_CASH_IN' || transaction.serviceType eq 'WALLET_CASH_OUT'}">
      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label">
          <spring:message code="label.transaction.fee"/>
        </label>
        <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(fee)}
          VND
        </div>
      </div>

      <div class="form-group mb-small">
        <label class="col-sm-3 col-md-2 control-label">
          <spring:message code="label.collection.fee"/>
        </label>
        <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(externalFee)}
          VND
        </div>
      </div>
    </c:if>


    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.preBalance"/> </label>
      <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.preBalance)}
        VND
      </div>
    </div>

    <div class="form-group mb-small">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.postBalance"/> </label>
      <div class="col-sm-9 col-md-10 primary_color">${ewallet:formatNumber(transaction.postBalance)}
        VND
      </div>
    </div>

    <div class="form-group">
      <label class="col-sm-3 col-md-2 control-label"><spring:message
          code="transaction.api.detail.status"/> </label>
      <div class="col-sm-9 col-md-10 text-normal">
        <c:choose>
          <c:when test="${transaction.transactionStatus eq INITIAL or transaction.transactionStatus eq OPENED}">
                        <span style="color: #5bc0de"><spring:message
                            code="${transaction.getStatus()}"/></span>
          </c:when>
          <c:when test="${transaction.transactionStatus eq CANCELLED}"><!-- huy & reversed -->
            <span style="color: #ed9c28"><spring:message
                code="${transaction.getStatus()}"/></span>
          </c:when>
          <c:when test="${transaction.transactionStatus eq REVERSED}"><!-- reversed -->
            <span style="color: #38b449"><spring:message
                code="${transaction.getStatus()}"/></span>
          </c:when>
          <c:when test="${transaction.transactionStatus eq HOLD}"><!-- hold -->
            <span style="color: #38b449"><spring:message
                code="${transaction.getStatus()}"/></span>
          </c:when>
          <c:when test="${transaction.transactionStatus eq FAILED}"><!-- that bai -->
            <span style="color: rgba(255, 0, 0, 0.68)"><spring:message
                code="${transaction.getStatus()}"/></span>
          </c:when>
          <c:otherwise>
            <!-- CLOSE, thanh cong, 38b449 -->
            <span style="color:#38b449;"><spring:message
                code="${transaction.getStatus()}"/></span>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

    <sec:authorize access="hasAnyRole('CUSTOMERCARE',
                        'CUSTOMERCARE_MANAGER',
                        'FINANCE', 'FINANCE_LEADER',
                        'FA_MANAGER','FINANCESUPPORT_LEADER',
                        'FINANCE_FA',
                        'ADMIN_OPERATION')">
      <c:if test="${transaction.transactionStatus eq HOLD}">
        <div class="form-group mb-small">
          <label class="col-sm-3 col-md-2 control-label"></label>
          <div class="col-sm-9 col-md-10 primary_color">
            <button class="btn btn-sm btn-primary" id="btnUpdateTxtHold"><spring:message
                code="btn.update.transaction"/></button>

              <%--topup nạp điện thoại khách hàng--%>
            <c:set var="phoneTopup" value="<%=ServiceType.PHONE_TOPUP.name()%>"/>
            <c:if test="${transaction.serviceType eq phoneTopup}">
              <button class="btn btn-sm btn-primary" id="btnTopupHold"
                      onclick="openModal(${transaction.id}, '${transaction.traceNo}')"><spring:message
                  code="topup.fundin.phone"/></button>
            </c:if>

              <%--thanh toán bill khách hàng--%>
            <c:set var="billPayment" value="<%=ServiceType.BILL_PAYMENT.name()%>"/>
            <c:if test="${transaction.serviceType eq billPayment}">
              <button class="btn btn-sm btn-primary" id="btnBillHold"
                      onclick="openModalBill(${transaction.id}, '${transaction.traceNo}')"><spring:message
                  code="topup.bill.payment"/></button>
            </c:if>
          </div>
        </div>
      </c:if>
    </sec:authorize>
  </div>
</section>

<%--modal phone topup on hold --%>
<%@ include file="topup-modal.jsp" %>

<%--modal bill payment on hold --%>
<%@ include file="topup-bill-modal.jsp" %>
