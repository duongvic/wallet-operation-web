<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_AGENT" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_PROVIDER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.*" %>
<%@ include file="../../include_page/taglibs.jsp" %>

<spring:message code="label.search.recon" var="textPlaceHolder" scope="request"/>
<spring:message code="daterange.locale.fulltime" var="fullTime"/>
<c:set var="CUSTOMER" value="<%=ID_CUSTOMER%>"/>
<c:set var="AGENT" value="<%=ID_AGENT%>"/>
<c:set var="MERCHANT" value="<%=ID_MERCHANT%>"/>
<c:set var="PROVIDER" value="<%=ID_PROVIDER%>"/>
<c:set var="PROPERTY_MANAGER" value="<%=ID_PROPERTY_MANAGER%>"/>

<section class="panel search_payment panel-default">
    <div class="panel-body pt-none">
        <%--<form action="" method="GET" id="tbl-filter" class="mb-md">--%>
        <%--<div class="form-group ml-none mr-none">--%>
        <%--<div class="input-group input-group-icon">--%>
        <%--<span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i--%>
        <%--class="fa fa-search-minus"></i></span></span>--%>
        <%--<input type="text" id="quickSearch" name="quickSearch" class="form-control"--%>
        <%--placeholder="${textPlaceHolder}" value="${param.quickSearch }"/>--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<div class="form-inline">--%>

        <%--<jsp:include page="../../include_component/date_range.jsp"/>--%>

        <%--<div class='pull-right form-responsive bt-plt'>--%>

        <%--<input name="idOwnerCustomerTypes" type="hidden"--%>
        <%--value="${param.idOwnerCustomerTypes}"/>--%>

        <%--<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message--%>
        <%--code="transaction.api.button.search"/></button>--%>
        <%--<a href="${ReconciExportControlUri}" id="export_do"--%>
        <%--class="btn btn-default export_link"><i--%>
        <%--class="fa fa-download "></i>&nbsp;<spring:message--%>
        <%--code="transaction.log.export"/></a>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="clearfix"></div>--%>
        <%--</form>--%>
        <form>
            <div class="form-group col-sm-8" id="left">
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="label.customer.cif"/>:
                    </label>
                    <div class="col-sm-8">
                        ${customer.cif}
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="lable.last.first.name"/>:
                    </label>
                    <div class="col-sm-8">
                        ${customer.name}
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="transaction.export.detail.summary.row.phone"/>:
                    </label>
                    <div class="col-sm-8">
                        ${customer.msisdn}
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="setting.account.notification.mode.EMAIL"/>:
                    </label>
                    <div class="col-sm-8">
                        <%--${customer.email}--%>
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="system.service.popup.update.lable.customerType"/>:
                    </label>
                    <div class="col-sm-8">
                        <c:choose>
                            <c:when test="${customer.customerType == CUSTOMER}">CUSTOMER</c:when>
                            <c:when test="${customer.customerType == AGENT}">AGENT</c:when>
                            <c:when test="${customer.customerType == MERCHANT}">MERCHANT</c:when>
                            <c:when test="${customer.customerType == PROVIDER}">PROVIDER</c:when>
                            <c:when test="${customer.customerType == PROPERTY_MANAGER}">PROPERTY_MANAGER</c:when>
                        </c:choose>
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label">
                        <spring:message code="label.recon.balance"/> :
                    </label>
                    <div class="col-sm-8">
                        <c:choose>
                            <c:when test="${empty param.range}">
                                ${date}
                            </c:when>
                            <c:otherwise>
                                ${param.range eq 'Tat ca' ? fullTime : param.range}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </form>
        <div class="clearfix"></div>

            <%--<c:if test="${transactions ne null && transactions.size() > 0}">--%>
            <div class="panel-body pt-none pb-none">
                <div class="form-group total-reconciliation">
                    <h4><strong><spring:message code="report.reconciliation"/></strong></h4></br>

                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong><spring:message code="report.recon.beginning.balance"/> </strong></p>
                            <p class="mb-none"><strong>${ewallet:formatNumber(firstPreBalance)}</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong>+</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong><spring:message code="report.recon.loaded.period"/> </strong></p>
                            <p class="mb-none"><strong>${ewallet:formatNumber(totalCashIn)}</strong></p>

                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong>-</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong><spring:message code="report.recon.text.in.the.period"/> </strong></p>
                            <p class="mb-none"><strong>${ewallet:formatNumber(totalCashOut)}</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong>=</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong><spring:message code="report.recon.end.balance"/> </strong></p>
                            <p class="mb-none"><strong>${ewallet:formatNumber(endBalance)}</strong></p>
                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <c:choose>
                                <c:when test="${endBalance lt lastPostBalance}">
                                    <p class="mb-none"><strong><</strong></p>
                                </c:when>
                                <c:when test="${endBalance gt lastPostBalance}">
                                    <p class="mb-none"><strong>></strong></p>
                                </c:when>
                                <c:otherwise><p class="mb-none"><strong>=</strong></p></c:otherwise>
                            </c:choose>

                        </a>
                    </div>
                    <div class="col-sm-3 col-lg-3 center col-respon">
                        <a href="#" class="reconciliation-flag">
                            <p class="mb-none"><strong><spring:message code="report.recon.balance.of.the.end.transaction"/> </strong></p>
                            <p class="mb-none"><strong>${ewallet:formatNumber(lastPostBalance)}</strong></p>
                        </a>
                    </div>
                </div>
            </div>
            <%--</c:if>--%>

        <div class="clearfix"></div>
        </br>

        <jsp:include page="reconciliation_table.jsp">
            <jsp:param name="transactionType" value="${param.transactionType}"/>
        </jsp:include>
    </div>
</section>