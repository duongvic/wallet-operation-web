<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_DETAIL" %>
<%@ include file="../include_page/taglibs.jsp" %>

<style>
    .levelTwoWarning {
        background-color: bisque !important;
    }

    .levelThreeWarning {
        background-color: #ffff007d !important;
    }

    .levelFourWarning {
        background-color: gold !important;
    }

    #phoneTopup:hover {
        cursor: pointer;
    }
</style>

<spring:message var="colStt" code="transaction.api.table.stt"/>
<spring:message var="colRId" code="transaction.api.table.request_id"/>
<spring:message var="colMerchant" code="transaction.api.table.merchant"/>
<spring:message var="colCustomer" code="transaction.api.table.customer"/>
<spring:message var="colProvider"
                code="transaction.api.table.provider"/>
<spring:message var="colCustomerType"
                code="transaction.api.table.customer.type"/>
<spring:message var="colPaymentMethod" code="transaction.api.table.payment.method"/>
<spring:message var="colTransID"
                code="transaction.api.table.transaction-id"/>
<spring:message var="colTime" code="transaction.api.table.time"/>
<spring:message var="colShortService" code="transaction.api.table.service"/>
<spring:message var="colService" code="transaction.api.table.service.type"/>
<spring:message var="colType" code="transaction.api.table.type"/>
<spring:message var="colValue" code="transaction.api.table.value"/>
<spring:message var="colQTy" code="transaction.api.table.qty"/>
<spring:message var="colSubject" code="transaction.api.table.subject"/>
<spring:message var="colCapitalVal"
                code="transaction.api.table.capitalVal"/>
<spring:message var="colGrossProfit"
                code="transaction.api.table.grossProfit"/>
<spring:message var="colAmount" code="transaction.api.table.amount"/>
<spring:message var="colRealAmount"
                code="transaction.api.table.realamount"/>

<spring:message var="colCommission"
                code="transaction.api.table.commission"/>
<spring:message var="colFee" code="transaction.api.table.fee"/>
<spring:message var="colBalBf" code="transaction.api.table.balance-bf"/>
<spring:message var="colBalAf" code="transaction.api.table.balance-af"/>
<spring:message var="colStatus" code="transaction.api.table.status"/>
<spring:message var="colAction" code="transaction.api.table.action"/>
<spring:message var="colCurrency"
                code="transaction.api.table.currency"/>
<spring:message var="actViewDetail"
                code="transaction.table.action.viewdetail"/>
<spring:message var="actViewReveral"
                code="transaction.table.action.reversalTxn"/>
<spring:message var="colTraceNo" code="transaction.api.table.trace.no.id"/>
<spring:message var="colExtraInfo" code="label.extra.info"/>


<c:url var="urlEpinPOdetail"
       value="<%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>"/>

<display:table name="transactions" id="item"
               requestURI="list"
               pagesize="${pagesize}" partialList="true"
               size="total"
               sort="page"
               class="table table-bordered table-striped mb-none">

    <%@ include file="../include_component/display_table.jsp" %>

    <%--Class for transation status--%>
    <c:choose>
        <c:when test="${TxnStatus.FAILED eq item.transactionStatus}">
            <c:set var="backgroundClass" value="levelTwoWarning"/>
        </c:when>
        <c:when test="${TxnStatus.HOLD eq item.transactionStatus}">
            <c:set var="backgroundClass" value="levelThreeWarning"/>
        </c:when>
        <c:when test="${TxnStatus.WAITTING eq item.transactionStatus}">
            <c:set var="backgroundClass" value="levelFourWarning"/>
        </c:when>
        <c:otherwise>
            <c:set var="backgroundClass" value=""/>
        </c:otherwise>
    </c:choose>

    <display:column title="${colStt}" class="${backgroundClass}">
                    <span id="row${list.id}" class="rowid">
                        <c:out value="${offset + item_rowNum}"/>
                    </span>
    </display:column>

    <display:column title="${colTime}" property="creationDate"
                    format="{0,date,HH:mm:ss dd/MM/yyyy}"
                    class="${backgroundClass}"/>

    <%--Action--%>
    <display:column title="${colAction}" class="action_icon center ${backgroundClass}"
                    headerClass="center">
        <a class="detail-link link-active"
           href="${contextPath}<%=TRANSACTION_DETAIL%>?txnId=${item.id}"
           title="${actViewDetail}" txnId="${item.id}">
            <i class="fa fa-info-circle "></i>
        </a>


        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','CUSTOMERCARE_MANAGER','CUSTOMERCARE')"
                       var="perReversal"/>
        <c:if test="${(item.transactionStatus eq 10 || item.transactionStatus eq 9) && perReversal}">
            <spring:url var="reversalURI"
                        value="${ctx}/service/reversal-history/request?txnId=${item.id}"/>
            <a href="${reversalURI}" class="reversal-link link-active"
               title="${actViewReveral}">
                <i class="fa fa-arrow-circle-left"
                   aria-hidden="true"></i>
            </a>
        </c:if>

        <%--topup nạp điện thoại khách hàng--%>
        <c:set var="phoneTopup" value="<%=ServiceType.PHONE_TOPUP.name()%>"/>
        <c:if test="${item.transactionStatus eq 9 && item.serviceType eq phoneTopup}">
            <label id="phoneTopup"
                   onclick="openModal(${item.id}, '${item.traceNo}')">
                <i style="margin-left: 5px; font-size: 22px; color: grey"
                   class="fa fa-2x fa-money"
                   title="<spring:message code='topup.fundin.phone'/>"></i>
            </label>
        </c:if>

        <%--thanh toán bill khách hàng--%>
        <c:set var="billPayment" value="<%=ServiceType.BILL_PAYMENT.name()%>"/>
        <c:if test="${item.transactionStatus eq 9 && item.serviceType eq billPayment}">
            <label id="billPayment"
                   onclick="openModalBill(${item.id}, '${item.traceNo}')">
                <i style="margin-left: 5px; font-size: 22px; color: grey"
                   class="fa fa-2x fa-money"
                   title="<spring:message code='topup.bill.payment'/>"></i>
            </label>
        </c:if>
    </display:column>
    <%--//end action--%>

    <display:column title="${colTransID}" class="${backgroundClass}">
        <a class="app-name detail-link link-active" href="${contextPath}<%=TRANSACTION_DETAIL%>?txnId=${item.id}"
           txnId="${item.id}">${item.id}</a>
    </display:column>

    <display:column title="${colRId}" class="${backgroundClass}">
        ${fn:substring(item.orderId, 0, 16)}<br/>
        ${fn:substring(item.orderId, 16, item.orderId.length())}
    </display:column>

    <display:column title="${colCustomer}" class="${backgroundClass}"
                    property="${null ne item.payerFullname ? 'payerFullname' : 'payeeFullname'}"/>

    <display:column title="${colCustomerType}" class="${backgroundClass}">
        ${item.getOwnerCustomerTypeName()}
    </display:column>

    <display:column title="CHANNEL" class="${backgroundClass}">
        ${item.paymentChannelId}
    </display:column>

    <c:choose>
        <c:when test="${'transaction_wallet' eq param.transactionType}">
            <display:column title="${colPaymentMethod}" class="${backgroundClass}">
                <spring:message code="${item.getWalletSourceOfFund()}"/>
            </display:column>
        </c:when>
        <c:otherwise>
            <display:column title="${colPaymentMethod}" class="${backgroundClass}">
                ${item.getSourceOfFundName()}
            </display:column>
        </c:otherwise>
    </c:choose>

    <sec:authorize
            access="!hasAnyRole('SALE_EXCUTIVE')">
        <display:column title="${colProvider}" class="${backgroundClass}">
            ${ewallet:getProviderBizCode(item.providerCode)}
        </display:column>
    </sec:authorize>


    <display:column title="${colTraceNo}" class="${backgroundClass}">
        ${item.traceNo}
    </display:column>


    <!-- STAFF, service, gia tri giao dich, cappital value, real amount -->
    <!-- MERCHANT, CUSTOMER gia tri giao dich, discount, real amount -->

    <sec:authorize
            access="hasAnyRole('ADMIN_OPERATION','STAFF','MERCHANT','CUSTOMER')">
        <display:column title="${colShortService}" class="${backgroundClass}">
            <spring:message code="${item.serviceShortName}"/>
        </display:column>
    </sec:authorize>

    <sec:authorize
            access="hasAnyRole('ADMIN_OPERATION','STAFF','MERCHANT','CUSTOMER')">
        <display:column title="${colService}" class="${backgroundClass}">
            <spring:message code="${item.getService()}"/>
        </display:column>
    </sec:authorize>

    <display:column title="${colAmount}" class="col-number-header ${backgroundClass}"
                    headerClass="col-number-header"
                    value="${ewallet:formatNumber(item.amount)}"/>

    <display:column title="${colRealAmount}" class="col-number-header ${backgroundClass}"
                    headerClass="col-number-header">
        ${item.getTransactionType()}&nbsp;${ewallet:formatNumber(item.realAmount)}
    </display:column>

    <c:if test="${'transaction_wallet' ne param.transactionType}">
        <sec:authorize
                access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                          'SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER', 'SALESUPPORT',
                                          'FINANCE',
                                          'RECONCILIATION','RECONCILIATION_LEADER')">
            <display:column title="${colGrossProfit }"
                            class="col-number-header ${backgroundClass}"
                            headerClass="col-number-header"
                            value="${ewallet:formatNumber(item.grossProfit)}"/>
            <display:column title="${colCapitalVal }"
                            class="col-number-header ${backgroundClass}"
                            headerClass="col-number-header"
                            value="${ewallet:formatNumber(item.capitalValue)}"/>
            <display:column title="${colCommission}"
                            class="col-number-header ${backgroundClass}"
                            headerClass="col-number-header">
                <c:if test="${item.commision != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.commision)}
            </display:column>
        </sec:authorize>
    </c:if>

    <sec:authorize
            access="hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR',
                                          'SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER', 'SALESUPPORT',
                                          'FINANCE',
                                          'RECONCILIATION','RECONCILIATION_LEADER')">
        <display:column title="${colFee}" class="col-number-header ${backgroundClass}"
                        headerClass="col-number-header">${ewallet:formatNumber(item.fee)}</display:column>
    </sec:authorize>

    <display:column title="${colBalBf}" class="col-number-header ${backgroundClass}"
                    headerClass="col-number-header">
        <c:if test="${item.preBalance != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.preBalance)}
    </display:column>

    <display:column title="${colBalAf}" class="col-number-header ${backgroundClass}"
                    headerClass="col-number-header">
        <c:if test="${item.postBalance != null}">+</c:if>&nbsp;${ewallet:formatNumber(item.postBalance)}
    </display:column>

    <display:column title="${colStatus}" class="${backgroundClass}">
        <spring:message code="${item.getStatus()}"/>
    </display:column>

    <!-- thong tin them -->
    <c:choose>
        <c:when test="${service.size() eq 1}">
            <!-- nothing -->
        </c:when>
        <c:otherwise>
            <spring:message var="colAttributeOther"
                            code="transaction.api.table.attributeOther"/>
            <c:choose>
                <c:when test="${item.attributes.size() > 3}">
                    <display:column title="${colAttributeOther}" class="${backgroundClass}">
                        <c:forEach var="attribute"
                                   items="${item.attributes}"
                                   varStatus="statusRow" begin="3">
                            ${attribute.transactionAttributeType}_${attribute.transactionAttributeValue}
                            <br/>
                        </c:forEach>
                    </display:column>
                </c:when>
                <c:otherwise>
                    <display:column title="${colAttributeOther}" class="${backgroundClass}"/>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>


    <!-- start transaction Attribute -->
    <c:choose>
        <c:when test="${service.size() eq 1}">
            <c:choose>
                <c:when test="${service[0] eq 'EPIN'}">
                    <spring:message var="colAttribute1"
                                    code="transaction.api.table.softPinAttribute1"/>
                    <spring:message var="colAttribute2"
                                    code="transaction.api.table.softPinAttribute2"/>
                    <spring:message var="colAttribute3"
                                    code="transaction.api.table.softPinAttribute3"/>
                    <spring:message var="colAttributeOther"
                                    code="transaction.api.table.attributeOther"/>
                    <c:forEach var="attribute"
                               items="${item.attributes}"
                               varStatus="statusRow" begin="0" end="2">
                        <display:column title="${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                                  ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                                  ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}"
                                        class="${backgroundClass}"
                                        value="${attribute.transactionAttributeValue}"/>
                    </c:forEach>
                </c:when>
                <c:when test="${service[0] eq 'TOPUP'}">
                    <spring:message var="colAttribute1"
                                    code="transaction.api.table.topUpAttribute1"/>
                    <spring:message var="colAttribute2"
                                    code="transaction.api.table.topUpAttribute2"/>
                    <spring:message var="colAttribute3"
                                    code="transaction.api.table.topUpAttribute3"/>
                    <spring:message var="colAttributeOther"
                                    code="transaction.api.table.attributeOther"/>
                    <c:forEach var="attribute"
                               items="${item.attributes}"
                               varStatus="statusRow" begin="0" end="1">
                        <display:column title="${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                                ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                                ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}"
                                        class="${backgroundClass}"
                                        value="${attribute.transactionAttributeValue}"/>
                    </c:forEach>
                </c:when>
                <c:when test="${service[0] eq 'EXPORT_EPIN'}">
                    <spring:message var="colAttribute1"
                                    code="transaction.api.table.exportSoAttribute1"/>
                    <spring:message var="colAttribute2"
                                    code="transaction.api.table.exportSoAttribute2"/>
                    <spring:message var="colAttribute3"
                                    code="transaction.api.table.exportSoAttribute3"/>
                    <spring:message var="colAttributeOther"
                                    code="transaction.api.table.attributeOther"/>
                    <c:forEach var="attribute"
                               items="${item.attributes}"
                               varStatus="statusRow" begin="0" end="0">
                        <display:column title="${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                                    ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                                    ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}"
                                        class="${backgroundClass}">
                            <c:choose>
                                <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                    <a href="<c:url value="<%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>"/>?poCode=${attribute.transactionAttributeValue}"
                                       class="link-active">${attribute.transactionAttributeValue}</a>
                                </c:when>
                                <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                            </c:choose>
                        </display:column>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <spring:message var="colAttribute1"
                            code="transaction.api.table.attribute1"/>
            <spring:message var="colAttribute2"
                            code="transaction.api.table.attribute2"/>
            <spring:message var="colAttribute3"
                            code="transaction.api.table.attribute3"/>
            <spring:message var="colAttributeOther"
                            code="transaction.api.table.attributeOther"/>

            <c:choose>
                <c:when test="${item.attributes.size() > 3}">
                    <c:forEach var="attribute"
                               items="${item.attributes}"
                               varStatus="statusRow" begin="0" end="2">
                        <display:column title="${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                      ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                      ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}"
                                        class="${backgroundClass}">
                            <c:choose>
                                <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                    <a href="${urlEpinPOdetail}?poCode=${attribute.transactionAttributeValue}"
                                       class="link-active">${attribute.transactionAttributeValue}</a>
                                </c:when>
                                <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                            </c:choose>
                        </display:column>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="attribute"
                               items="${item.attributes}"
                               varStatus="statusRow">
                        <display:column title="${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                      ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                      ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}"
                                        class="${backgroundClass}">
                            <c:choose>
                                <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                    <a href="${urlEpinPOdetail}?poCode=${attribute.transactionAttributeValue}"
                                       style="color: #0088CC;">${attribute.transactionAttributeValue}</a>
                                </c:when>
                                <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                            </c:choose>
                        </display:column>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${item.attributes.size() eq 1}">
                            <display:column title="${colAttribute2}" class="${backgroundClass}"/>
                            <display:column title="${colAttribute3}" class="${backgroundClass}"/>
                        </c:when>
                        <c:when test="${item.attributes.size() eq 2}">
                            <display:column title="${colAttribute3}" class="${backgroundClass}"/>
                        </c:when>
                        <c:when test="${item.attributes.size() eq 3}"><!-- nothing --></c:when>
                        <c:otherwise>
                            <display:column title="${colAttribute1}" class="${backgroundClass}"/>
                            <display:column title="${colAttribute2}" class="${backgroundClass}"/>
                            <display:column title="${colAttribute3}" class="${backgroundClass}"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <!-- end transaction Attribute -->
    <display:column title="${ewallet:toUpperCase(colExtraInfo)}" class="${backgroundClass}">
        <spring:message code="${item.extraInfo}"/>
    </display:column>
</display:table>