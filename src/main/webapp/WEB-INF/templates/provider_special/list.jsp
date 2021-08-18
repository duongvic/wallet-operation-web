<%@ page
        import="static vn.mog.ewallet.operation.web.constant.SharedConstants.SEN_PAY_TRANSACTION" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<spring:message code="label.u.are.perform.inactive" var="lblTxtInActive"/>
<spring:message code="label.u.are.perform.active" var="lblTxtActive"/>
<c:set var="SEN_PAY_TRANSACTION" value="<%=SEN_PAY_TRANSACTION%>"/>
<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="provider.title.header"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>


    <style>
        .levelTwoWarning {
            background-color: #ff2514 !important;
        }
    </style>

</head>

<sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="roleAdmin"/>
<sec:authorize access="hasAnyRole('ADMIN_OPERATION', 'ROLE_CUSTOMERCARE_MANAGER')" var="roleCustomerCareManager"/>
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="provider-special" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span class="nav-active"><spring:message
                                        code="provider.list.providerProfile"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.provider.special"/></span></li>
                                <li><span><spring:message
                                        code="setting.account.list.subnavigate"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>
            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="h4 mb-md"></div>
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">

                            <form action="" method="GET" id="search-provider">
                                <input type="hidden" name="pProService" value="">
                                <spring:message code="label.search.by.phone"
                                                var="placeholderSearchPhone"/>

                                <div>
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a
                                                href="${pageContext.request.contextPath}/provider/special/bill_senpay/list">QL
                                            TÀI KHOẢN </a></li>
                                        <li>
                                            <a style="text-transform: uppercase;" href="${pageContext.request.contextPath}/provider/special/bill_senpay/by-province/list">
                                                <spring:message code="label.province.bill"/>
                                            </a></li>
                                        <li><a data-toggle="tab" href="#tab_menu1">ĐỐI SOÁT THEO
                                            NGÀY</a></li>
                                        <li><a data-toggle="tab" href="#tab_menu2">ĐỐI SOÁT THEO
                                            TK</a></li>

                                    </ul>

                                    <!-- Tab panes -->
                                    <div class="tab-content">

                                        <%--TAB 1--%>
                                        <div class="tab-pane active" id="tab_qltk">

                                            <section class="panel search_payment panel-default">
                                                <div class="panel-body pt-none">
                                                    <form action="" method="GET" id="tbl-filter"
                                                          class="mb-md">

                                                        <div class="pull-right form-responsive bt-plt">
                                                            <sec:authorize
                                                                    access="hasAnyRole('ADMIN_OPERATION', 'CUSTOMERCARE', 'CUSTOMERCARE_MANAGER')">
                                                                <a class="mb-xs mt-xs btn btn-success"
                                                                   href="${contextPath}/provider/special/bill_senpay/add"><i
                                                                        class="fa fa-plus"></i>&nbsp;<spring:message
                                                                        code="system.service.navigate.btn.create"/></a>
                                                            </sec:authorize>
                                                        </div>

                                                        <div class="clearfix"></div>

                                                        <div class="input-group input-group-icon"
                                                             style="margin-bottom: 1rem;">
                                                            <span class="input-group-addon">
                                                              <span class="icon"
                                                                    style="opacity: 0.4"><i
                                                                      class="fa fa-search-minus"></i></span>
                                                            </span>
                                                            <input type="text" id="search"
                                                                   name="search"
                                                                   class="form-control"
                                                                   placeholder="${placeholderSearchPhone}"
                                                                   value="${param.search }"/>
                                                        </div>

                                                        <div class="clearfix"></div>
                                                        <div class="form-inline">
                                                            <select name="active" id="active"
                                                                    class="form-control">
                                                                <option value=""><spring:message
                                                                        code="select.status"/></option>
                                                                <option value="1" ${param.active eq "1" ? "selected" : "" }>
                                                                    <spring:message
                                                                            code="status.on"/></option>
                                                                <option value="0" ${param.active eq "0" ? "selected" : "" }>
                                                                    <spring:message
                                                                            code="status.off"/></option>
                                                            </select>
                                                            <div class='pull-right form-responsive'>
                                                                <button type="submit"
                                                                        class="btn btn-primary ml-tiny">
                                                                    <i
                                                                            class="fa fa-search"></i>&nbsp;
                                                                    <spring:message
                                                                            code="common.btn.search"/>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>


                                                    <div class="pull-left mt-sm"
                                                         style="line-height: 30px;">
                                                        <spring:message
                                                                code="label.totalOfTotalAmount"/>
                                                        &nbsp;<span
                                                            class="primary_color text-semibold">${ewallet:formatNumber(totalOfTotalAmount)} VND</span>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <sec:authorize
                                                            access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER', 'SALESUPPORT_MANAGER' , 'SALESUPPORT')"
                                                            var="perActionSwitch"/>

                                                    <spring:message var="colTransaction"
                                                                    code="table.senpay.transaction"/>
                                                    <spring:message var="colPhone"
                                                                    code="setting.account.tbl.col.phone"/>
                                                    <spring:message var="colTotalBalance"
                                                                    code="table.total.balance"/>
                                                    <spring:message var="colStatus"
                                                                    code="setting.account.tbl.col.status"/>
                                                    <spring:message var="colAction"
                                                                    code="setting.account.tbl.col.action"/>
                                                    <spring:message var="actionChangeStatus"
                                                                    code="label.change.status"/>

                                                    <display:table name="list" id="item"
                                                                   requestURI="list"
                                                                   pagesize="${pagesize}"
                                                                   partialList="true"
                                                                   size="total"
                                                                   sort="page"
                                                                   class="table table-bordered table-striped mb-none">

                                                        <%@ include
                                                                file="../include_component/display_table.jsp" %>

                                                        <display:column title="STT">
                                                            <span id="row${item.accountName}"
                                                                  class="rowid">
                                                              <c:out value="${offset + item_rowNum}"/>
                                                            </span>
                                                        </display:column>
                                                        <display:column title="${colPhone}"
                                                                        property="accountName"/>
                                                        <display:column title="${colTotalBalance}"
                                                                        class="right"
                                                                        headerClass="center">
                                                            ${ewallet:formatNumber(item.totalAmount)}
                                                        </display:column>

                                                        <c:choose>
                                                            <c:when test="${item.dailyRequestCount/SEN_PAY_TRANSACTION eq 1}">
                                                                <display:column title="${colTransaction}"
                                                                                class="levelTwoWarning">
                                                                    ${item.dailyRequestCount}/${SEN_PAY_TRANSACTION}
                                                                </display:column>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <display:column title="${colTransaction}">
                                                                    ${item.dailyRequestCount}/${SEN_PAY_TRANSACTION}
                                                                </display:column>
                                                            </c:otherwise>
                                                        </c:choose>


                                                        <display:column title="${colStatus}"
                                                                        class="center"
                                                                        headerClass="center">
                                                            <input type="hidden"
                                                                   id="user-${item.accountName}-blackListReason"
                                                                   value="${item.status}">
                                                            <label class="switch" style="
                                            margin: 0 3px;
                                            "
                                                                   data-toggle="popover"
                                                                   data-trigger="hover"
                                                                   data-placement="top"
                                                                   title=""
                                                                   data-content="${actionChangeStatus}"
                                                                   for="chk-blacklist-status-${item.accountName}">
                                                                <input id="chk-blacklist-status-${item.accountName}"
                                                                       type="checkbox" ${1 eq item.status ? 'checked' : ''}
                                                                <c:if test="${roleCustomerCareManager}">
                                                                       onclick="return blackListAccountSenPay('${item.accountName}',${item.status})">
                                                                </c:if>
                                                                <c:if test="${!roleCustomerCareManager}">
                                                                    disabled
                                                                    onclick="return blackListAccountSenPay('${item.accountName}',${item.status})">
                                                                </c:if>
                                                                <span class="slider round"></span>
                                                            </label>
                                                        </display:column>

                                                        <display:column title="${colAction}"
                                                                        class="action_icon center"
                                                                        headerClass="center">
                                                            <%--<a href="#" class="detail-link link-active"--%>
                                                            <%--title="<spring:message code="common.title.edit.information"/>"--%>
                                                            <%--displayName="${item.accountName}"--%>
                                                            <%--totalAmount ="${item.totalAmount}">--%>
                                                            <%--<i class="fa fa-pencil"></i>--%>
                                                            <%--</a>--%>
                                                            <sec:authorize
                                                                    access="hasAnyRole('ADMIN_OPERATION', 'CUSTOMERCARE', 'CUSTOMERCARE_MANAGER')">
                                                                <a href="#"
                                                                   class="delete-account-senPay-link link-active"
                                                                   title="<spring:message code="label.delete.account"/>"
                                                                   displayName="${item.accountName}"
                                                                   totalAmount="${item.totalAmount}">
                                                                    <i class="fa fa-trash"></i>
                                                                </a>
                                                            </sec:authorize>

                                                            <a href="${contextPath}/provider/special/bill_senpay/details/${item.accountName}"
                                                               class="link-active"
                                                               title="<spring:message code="common.title.view.detail"/>"
                                                               txnId="${item.accountName}">
                                                                <i class="fa fa-info-circle "></i>
                                                            </a>


                                                        </display:column>
                                                    </display:table>
                                                </div>
                                            </section>

                                        </div>

                                        <%--TAB 2--%>

                                        <%--TAB 3--%>
                                        <div class="tab-pane" id="tab_menu1">
                                            <%--<div id="table-dstn">--%>
                                            <%--<div class="clearfix"></div>--%>

                                            <%--<spring:message var="colStt" code="reim.table.no"/>--%>
                                            <%--<spring:message var="colTransID"--%>
                                            <%--code="reim.table.transaction.id"/>--%>
                                            <%--<spring:message var="colTime"--%>
                                            <%--code="reim.table.time"/>--%>
                                            <%--<spring:message var="colSourceAccount"--%>
                                            <%--code="reim.table.source.account"/>--%>
                                            <%--<spring:message var="colTargetAccount"--%>
                                            <%--code="reim.table.target.account"/>--%>
                                            <%--<spring:message var="colTransferAmount"--%>
                                            <%--code="reim.table.transfer.amount"/>--%>
                                            <%--<spring:message var="colProccessing"--%>
                                            <%--code="reim.table.proccessing"/>--%>

                                            <%--<spring:message var="colTransReference"--%>
                                            <%--code="reim.table.transaction.reference"/>--%>
                                            <%--<spring:message var="colCreateAt"--%>
                                            <%--code="reim.table.created.at"/>--%>
                                            <%--<spring:message var="colCreateBy"--%>
                                            <%--code="reim.table.created.by"/>--%>
                                            <%--<spring:message var="colApproedBy"--%>
                                            <%--code="reim.table.approed.by"/>--%>
                                            <%--<spring:message var="colStatus"--%>
                                            <%--code="reim.table.status"/>--%>
                                            <%--<spring:message var="colAction"--%>
                                            <%--code="transaction.api.table.action"/>--%>

                                            <%--<display:table name="transCancels" id="item"--%>
                                            <%--requestURI="list"--%>
                                            <%--pagesize="${pagesize2}"--%>
                                            <%--partialList="true"--%>
                                            <%--size="total2"--%>
                                            <%--sort="page"--%>
                                            <%--class="table table-bordered table-striped mb-none">--%>

                                            <%--<%@ include--%>
                                            <%--file="../include_component/display_table.jsp" %>--%>

                                            <%--<display:column title="STT">--%>
                                            <%--<span id="row${item.id}" class="rowid">--%>
                                            <%--<c:out value="${offset2 + item_rowNum}"/>--%>
                                            <%--</span>--%>

                                            <%--</display:column>--%>

                                            <%--<display:column title="${colTransID}">--%>
                                            <%--<a class="app-name detail-link link-active"--%>
                                            <%--href="#" txnId="${item.id}">--%>
                                            <%--${fn:substring(item.id, 0, 16)}--%>
                                            <%--<br/>--%>
                                            <%--${fn:substring(item.id, 16, 32)}--%>
                                            <%--</a>--%>
                                            <%--</display:column>--%>

                                            <%--<display:column title="${colSourceAccount}"--%>
                                            <%--property="service"/>--%>
                                            <%--<display:column title="${colTargetAccount}"--%>
                                            <%--property="merchant"/>--%>
                                            <%--<display:column title="${colTime}"--%>
                                            <%--property="createdDate"--%>
                                            <%--format="{0,date,HH:mm:ss dd/MM/yyyy}"/>--%>
                                            <%--<display:column--%>
                                            <%--title="${colTransferAmount}">${ewallet:formatNumber(amount)}</display:column>--%>

                                            <%--<display:column title="${colCreateAt}"--%>
                                            <%--property="createdDate"--%>
                                            <%--format="{0,date,HH:mm:ss dd/MM/yyyy}"/>--%>
                                            <%--<display:column title="${colCreateBy}"--%>
                                            <%--property="createdDate"--%>
                                            <%--format="{0,date,HH:mm:ss dd/MM/yyyy}"/>--%>
                                            <%--<display:column--%>
                                            <%--title="${colApproedBy}">${ewallet:formatNumber(approver)}</display:column>--%>
                                            <%--<display:column--%>
                                            <%--title="${colStatus}">${item.getStatusCode(item.status)}</display:column>--%>


                                            <%--<display:column title="${colAction}"--%>
                                            <%--class="action_icon center"--%>
                                            <%--headerClass="center">--%>
                                            <%--<a href="#" class="detail-link link-active"--%>
                                            <%--title="<spring:message code="common.title.view.detail"/>"--%>
                                            <%--txnId="${item.id}">--%>
                                            <%--<i class="fa fa-info-circle "></i>--%>
                                            <%--</a>--%>
                                            <%--</display:column>--%>
                                            <%--</display:table>--%>
                                            <%--</div>--%>
                                        </div>

                                        <%--TAB 4--%>
                                        <div class="tab-pane" id="tab_menu2">.ĐỐI SOÁT TÀI KHOẢN..
                                        </div>

                                    </div>

                                </div>

                            </form>


                        </div>
                    </section>
                </div>
            </div>

            <jsp:include page="action_change_status_acount_senPay.jsp"/>
            <jsp:include page="action_delete_acount_senPay.jsp"/>

            <!-- end: page -->
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>


<script type="text/javascript"
        src="<c:url value='/assets/javascripts/provider-special-custom.js'/>"></script>

<script type="text/javascript">
    $(document).ready(function () {

    });

    function blackListAccountSenPay(displayName, status) {
        //init
        var blackListReasonValue = $('#user-'.concat(displayName).concat('-blackListReason')).val();

        jQuery('#msg-blacklist').text('');
        if (blackListReasonValue == 1) {
            jQuery('.lbl-text-active').text('${lblTxtInActive} ' + displayName);
        } else {
            jQuery('.lbl-text-active').text('${lblTxtActive} ' + displayName);
        }

        jQuery('#remark-modal-account-list').val('');
        jQuery('#wrapper-chk-customer-confirm-senpay').show();
        jQuery('#chk-customer-confirm-senpay').attr('checked', false);
        jQuery('#btn-account-blacklist-save').attr('disabled', false);

        var actionBlackList = jQuery('#action-blacklist');
        actionBlackList.find('#account-id').val(displayName);
        actionBlackList.find('#blacklist-id').val(blackListReasonValue);
        actionBlackList.find('#displayName-id').val(displayName);
        actionBlackList.find('#blackListModal').val(blackListReasonValue).trigger(
            'change');
        actionBlackList.modal('show');
        return false;
    }

    /*delete */
    $('a.delete-account-senPay-link').click(function () {
        var displayName = $(this).attr("displayName");
        var totalAmount = formatCurrency($(this).attr("totalAmount")) + ' VND';

        jQuery('#wrapper-chk-customer-confirm-senpay-delete').show();
        jQuery('#chk-customer-confirm-senpay-delete').attr('checked', false);
        jQuery('#btnDelete-account-senPay-save').attr('disabled', false);

        var actionDelete = jQuery('#action-delete-account-senPay');
        actionDelete.find('#totalAmount-id').val(totalAmount);
        actionDelete.find('#displayName-id').val(displayName);
        actionDelete.find('#account-id-sendPay').val(displayName);
        actionDelete.modal('show');
        return false;
    });

    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href") // activated tab
    });

</script>

</body>

</html>
