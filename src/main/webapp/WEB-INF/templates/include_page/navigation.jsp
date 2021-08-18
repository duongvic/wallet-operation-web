<%@ page import="vn.mog.ewallet.operation.web.constant.SharedConstants" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.StaffAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.WalletAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.balance.BalanceSoftCashController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.balance.BalanceSoftCashStatisticsController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.customer.CustomerAccountController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.customer.commission.fee.CommissionFeeController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.customer.request.kyc.CustomerRequestKycController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.dashboard.DashboardController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.fundout.FundOutOrderController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.p2p.P2pTransferController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.provider.ProviderController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV2Controller" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.service.TrueServiceController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.statement.StatementController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.FundinSofController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.transfer.WalletTransferController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionLogController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionReimController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.translog.TransactionWalletUserController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderAPIController" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController.TRANS_REVERSAL_LIST" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionReversalController.TRANS_REVERSAL_LIST_TXN_REQUEST" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.customer.CustomerBlockController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.notification.NotificationController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.provider.ProviderSpecialSenpayController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.balance.BalanceTableMoneyController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.balance.BalanceOfTotalAssetsController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.customer.sale_switching.manage.SaleSwitchingManageController" %>
<%@ page
        import="vn.mog.ewallet.operation.web.controller.agentSalesStatistics.AgentSalesStatisticsController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.notification.NotificationPartnerController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.provider.ProviderSpecialKppViettelController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.provider.BillSenpayToolController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.dashboard.TransactionDashboardController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV3Controller" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../include_page/taglibs.jsp" %>


<%String menu = (String) request.getSession().getAttribute("menu");%>
<%if (menu != null) {%>
<c:set var="menu" value="<%=menu%>" scope="page"/>
<%} else {%>
<c:set var="menu" value="${menu}" scope="page"/>
<%}%>

<c:set var="urlDashboard">${contextPath}<%=DashboardController.DASHBOARD_LIST%>?menu=${menu}</c:set>

<c:set var="urlEpin">${contextPath}<%=EpinPurchaseOrderController.EPIN_PO_LIST%>?menu=${menu}
</c:set>

<c:set var="urlEpinAPI">${contextPath}<%=EpinPurchaseOrderAPIController.EPIN_PO_LIST%>?menu=${menu}
</c:set>

<c:set var="urlEpinDoc">${contextPath}<%=EpinPurchaseOrderController.EPIN_PO_API%>?menu=${menu}
</c:set>

<c:set
        var="urlBalanceMonitoring">${contextPath}<%=BalanceSoftCashStatisticsController.BALANCE_MONITORING%>?menu=${menu}
</c:set>
<c:set
        var="urlBalanceTotalAssets">${contextPath}<%=BalanceOfTotalAssetsController.BALANCE_TOTAL_ASSETS_CONTROLLER%>?menu=${menu}
</c:set>
<c:set
        var="urlBalanceTableMoney">${contextPath}<%=BalanceTableMoneyController.BALANCE_TABLE_MONEY%>?menu=${menu}
</c:set>
<c:set
        var="urlBalanceCustomer">${contextPath}<%=BalanceSoftCashStatisticsController.BALANCE_CUSTOMER_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlBalanceSoftOfCash">${contextPath}<%=BalanceSoftCashController.BALANCE_SOFT_OF_CASH_LIST%>?menu=${menu}
</c:set>

<c:set var="urlFundIn">${contextPath}<%=FundInController.FUND_IN_HISTORY_LIST
        + "?" + FundInController.FUND_IN_HISTORY_DEFAULT_FILTER%>&menu=${menu}</c:set>
<c:set var="urlFundInOrder">${contextPath}<%=FundInOrderController.FUND_IN_ORDER_LIST%>?menu=${menu}
</c:set>

<c:set var="urlFundOut">${contextPath}<%=FundOutController.FUND_OUT_HISTORY_LIST
        + "?" + FundOutController.FUND_OUT_HISTORY_DEFAULT_FILTER%>&menu=${menu}
</c:set>
<c:set
        var="urlFundOutOrder">${contextPath}<%=FundOutOrderController.FUND_OUT_ORDER_LIST%>?menu=${menu}
</c:set>

<c:set var="urlProvider">${contextPath}<%=ProviderController.PROVIDER_LIST%>?menu=${menu}</c:set>
<c:set
        var="urlProviderService">${contextPath}<%=ProviderController.PROVIDER_SERVICE_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlPtuService">${contextPath}<%=ProviderController.PTU_SERVICE_MATCHING%>?menu=${menu}
</c:set>

<c:set
        var="urlProviderSpecial">${contextPath}<%=ProviderSpecialSenpayController.PROVIDER_SPECIAL_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlBillSenpayTool">${contextPath}<%=BillSenpayToolController.PROVIDER_SPECIAL_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlKppViettel">${contextPath}<%=ProviderSpecialKppViettelController.PROVIDER_SPECIAL_LIST%>?menu=${menu}
</c:set>

<c:set var="urlRecon">${contextPath}<%=ReconciliationV2Controller.RECON_LIST_ALL%>?menu=${menu}
</c:set>

<c:set var="urlReconByTxn">${contextPath}<%=ReconciliationV3Controller.RECON_LIST%>?menu=${menu}
</c:set>
<c:set var="urlReconProfile">${contextPath}<%=ReconciliationV3Controller.PROFILE_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlAgentSalesStatistics">${contextPath}<%=AgentSalesStatisticsController.SALES_STATISTICS_LIST_ALL%>?menu=${menu}
</c:set>

<c:set var="urlTrueService">${contextPath}<%=TrueServiceController.TRUE_SERVICE_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlTrueServiceOperation">${contextPath}<%=TrueServiceController.TRUE_SERVICE_OPERATION%>?menu=${menu}
</c:set>


<c:set
        var="urlWalletTransferHistory">${contextPath}<%=WalletTransferController.TRANSFER_WALLET_HISTORY_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlFundTransferHistory">${contextPath}<%=FundinSofController.FUND_SOF_TRANSFER_HISTORY_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlFundinSofBalanceRequest">${contextPath}<%=FundinSofController.FUND_SOF_REQUEST_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlTransferWalletBalanceRequest">${contextPath}<%=WalletTransferController.TRANSFER_WALLET_REQUEST_LIST%>?menu=${menu}
</c:set>
<c:set var="urlP2pTransfer">${contextPath}<%=P2pTransferController.P2P_TRANSFER_URL%>?menu=${menu}
</c:set>


<c:set
        var="urlTransHistory">${contextPath}<%=TransactionLogController.TRANSACTION_LIST_ALL/*
        + "?menu=" + menu + "&" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/%>
</c:set>
<c:set var="urlTransDashBoard">${contextPath}<%=TransactionDashboardController.DASH_BOARD%>?menu=${menu}
</c:set>
<c:set var="urlRuleTrans">${contextPath}<%=TransactionRuleController.TRANS_RULE_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlUserWalletTransactionList">${contextPath}<%=
TransactionWalletUserController.TRANSACTION_WALLET_USER_LIST
        + "?menu=" + menu + "&"
        + TransactionWalletUserController.TRANSACTION_WALLET_DEFAULT_FILTER%>
</c:set>


<c:set
        var="subpathEpinStore"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_URL%>
</c:set>
<c:set var="urlStoreMonitoring"
       value="${subpathEpinStore}/provider/cardstore/dashboard?menu=${menu}"/>
<c:set var="urlStoreCardManagement"
       value="${subpathEpinStore}/provider/card-manager/list?menu=${menu}"/>
<c:set var="urlPurchaseOrder"
       value="${subpathEpinStore}/provider/purchase-order/list?menu=${menu}"/>
<c:set var="urlSpecialCustomer"
       value="${subpathEpinStore}/provider/special-account/manage/list?menu=${menu}"/>
<c:set var="urlProfileProviderCardStore"
       value="${subpathEpinStore}/provider/profile-manager/list?menu=${menu}"/>


<c:set
        var="subpathEpinStoreN02"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_N02_URL%>
</c:set>
<c:set var="urlStoreN02Monitoring"
       value="${subpathEpinStoreN02}/provider/cardstore/dashboard?menu=${menu}"/>
<c:set var="urlStoreN02CardManagement"
       value="${subpathEpinStoreN02}/provider/card-manager/list?menu=${menu}"/>
<c:set var="urlN02PurchaseOrder"
       value="${subpathEpinStoreN02}/provider/purchase-order/list?menu=${menu}"/>
<c:set var="urlN02SpecialCustomer"
       value="${subpathEpinStoreN02}/provider/special-account/manage/list?menu=${menu}"/>
<c:set var="urlProfileProviderCardStoreN02"
       value="${subpathEpinStoreN02}/provider/profile-manager/list?menu=${menu}"/>

<c:set
        var="subpathEpinStoreOffline"><%=SharedConstants.WEB_DOMAIN_PLATFORM_OPERATION_SUBPATH_EPIN_STORE_OFFLINE_URL%>
</c:set>
<c:set var="urlStoreOffLineMonitoring"
       value="${subpathEpinStoreOffline}/provider/cardstore/dashboard?menu=${menu}"/>
<c:set var="urlStoreOffLineCardManagement"
       value="${subpathEpinStoreOffline}/provider/card-manager/list?menu=${menu}"/>
<c:set var="urlPurchaseOrderOffLine"
       value="${subpathEpinStoreOffline}/provider/purchase-order/list?menu=${menu}"/>
<c:set var="urlSpecialCustomerOffLine"
       value="${subpathEpinStoreOffline}/provider/special-account/manage/list?menu=${menu}"/>
<c:set var="urlProfileProviderCardStoreOffline"
       value="${subpathEpinStoreOffline}/provider/profile-manager/list?menu=${menu}"/>

<%--STAFF ACCOUNT MANAGER--%>
<c:set
        var="urlStaffAccount">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlStaffAccountRoleList">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_ROLE_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlStaffAccountPrivilegeList">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_PRIVILEGE_LIST%>?menu=${menu}
</c:set>

<%--WALLET ACCOUNT MANAGER--%>
<c:set
        var="urlWalletAccount">${contextPath}<%=WalletAccountController.WALLET_ACCOUNT_MANAGE_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlCustomerAccount">${contextPath}<%=CustomerAccountController.CUSTOMER_MANAGE_LIST%>?menu=${menu}
</c:set>


<%--CUSTOMER MANAGER--%>
<c:set
        var="urlCustomerAccountAgent">${contextPath}/customer/manage/list?menu=cus&customerType=2"
</c:set>
<c:set
        var="urlSettingCustomerList">${contextPath}<%=CustomerAccountController.CUSTOMER_MANAGE_LIST_ALL%>?menu=${menu}
</c:set>
<c:set
        var="urlVerifiCustomerList">${contextPath}<%=CustomerRequestKycController.CUSTOMER_VERIFY_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlCustomerFeeStructure">${contextPath}<%=CommissionFeeController.CUSTOMER_FEE_STRUCTURE_TYPE%>?menu=${menu}&customerTypeId=${cusTypeCustomer}

</c:set>

<c:set
        var="urlBlockCustomerList">${contextPath}<%=CustomerBlockController.CUS_BLOCK_LIST_ALL%>?menu=${menu}
</c:set>

<c:set
        var="urlCustomerSaleSwitchingManage">${contextPath}<%=SaleSwitchingManageController.URI_CUSTOMER_SALE_SWITCHING_MANAGE_LIST%>?menu=${menu}
</c:set>

<c:set
        var="urlNotificationBroadcast">${contextPath}<%=NotificationController.NOTIFICATION_BROADCAST%>?menu=${menu}
</c:set>

<c:set
        var="urlNotificationPartnerBroadcast">${contextPath}<%=NotificationPartnerController.NOTIFICATION_BROADCAST%>?menu=${menu}
</c:set>

<c:set
        var="urlNotificationList">${contextPath}<%=NotificationController.NOTIFICATION_FIND%>?menu=${menu}
</c:set>

<c:set
        var="urlNotificationPartnerList">${contextPath}<%=NotificationPartnerController.NOTIFICATION_FIND%>?menu=${menu}
</c:set>

<c:set
        var="urlSpecifiedCommissionFeeCustomers">${contextPath}<%=CommissionFeeController.SPECIFIED_COMMISSION_FEE_CUSTOMERS%>?menu=${menu}
</c:set>


<c:set var="urlStatement">${contextPath}<%=StatementController.STATEMENT_LIST%>?menu=${menu}</c:set>


<%--Reversal Txn--%>
<c:set var="urlReversalTxn">${contextPath}<%=TRANS_REVERSAL_LIST%>?menu=${menu}</c:set>
<c:set var="urlReversalTxnRequest">${contextPath}<%=TRANS_REVERSAL_LIST_TXN_REQUEST%>?menu=${menu}
</c:set>
<%--Reim --%>
<c:set var="urlReimTxn">${contextPath}<%=TransactionReimController.TRANS_REIM_LIST%>?menu=${menu}
</c:set>
<c:set
        var="urlReimTxnRequest">${contextPath}<%=TransactionReimController.TRANS_REIM_LIST_TXN_REQUEST%>?menu=${menu}
</c:set>


<sec:authorize
        access="hasAnyRole('FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
        var="permisFinance"/>

<aside id="sidebar-left" class="sidebar-left">
    <div class="sidebar-header">
        <div class="sidebar-title">Menu</div>
        <div class="sidebar-toggle hidden-xs" data-target="html"
             data-fire-event="sidebar-left-toggle" data-toggle-class="sidebar-left-collapsed">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
        </div>
    </div>

    <div class="nano" style="overflow-y: auto; -webkit-overflow-scrolling: touch;">
        <div class="nano-content">
            <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-main">

                    <c:if test="${empty menu || menu == 'all' || menu == 'ser'}">
                        <sec:authorize var="admin" access="hasAnyRole('ADMIN_OPERATION', 'STAFF')"/>
                        <sec:authorize var="other" access="hasAnyRole('ADMIN_OPERATION','STAFF','SALE_ASM','SALESUPPORT_MANAGER','SALESUPPORT_LEADER','SALESUPPORT',
                                             'MERCHANT','CUSTOMER')"/>
                        <c:choose>
                            <c:when test="${admin}">
                                <li data-nav-group="Service"
                                    class="${param.nav == 'txnDashBoard' ? 'nav-active nav-expanded' : '' }">
                                    <a href="${urlTransDashBoard}">
                                        <i class=""><img
                                                src="${contextPath}/assets/images/icon/menu/overview.png"
                                                class="icon-menu-left"></i>
                                        <span><spring:message code="menu.left.dashboard"/></span>
                                    </a>
                                </li>
                            </c:when>
                            <c:when test="${other}">
                                <li data-nav-group="Service"
                                    class="${param.nav == 'txnDashBoard' ? 'nav-active nav-expanded' : '' }">
                                    <a href="${urlTransHistory}">
                                        <i class=""><img
                                                src="${contextPath}/assets/images/icon/menu/overview.png"
                                                class="icon-menu-left"></i>
                                        <span><spring:message code="menu.left.dashboard"/></span>
                                    </a>
                                </li>
                            </c:when>
                        </c:choose>
                        <%--<sec:authorize access="hasAnyRole('ADMIN_OPERATION')">--%>
                        <%--<li data-nav-group="Service"--%>
                        <%--class="${param.nav == 'txnDashBoard' ? 'nav-active nav-expanded' : '' }">--%>
                        <%--<a href="${urlTransDashBoard}">--%>
                        <%--<i class=""><img--%>
                        <%--src="${contextPath}/assets/images/icon/menu/overview.png"--%>
                        <%--class="icon-menu-left"></i>--%>
                        <%--<span><spring:message code="menu.left.dashboard"/></span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                        <%--</sec:authorize>--%>

                        <%--<sec:authorize access="hasAnyRole('ADMIN_OPERATION',--%>
                        <%--'FINANCE','FA_MANAGER',--%>
                        <%--'SALE_EXCUTIVE','SALE_DIRECTOR','SALESUPPORT_MANAGER','SALESUPPORT_LEADER','SALESUPPORT',--%>
                        <%--'MERCHANT','CUSTOMER')">--%>
                        <%--<li data-nav-group="Service"--%>
                        <%--class="${param.nav =='dashboard' ? 'nav-active':'' }">--%>
                        <%--<a href="${urlDashboard}">--%>
                        <%--<i class=""><img--%>
                        <%--src="${contextPath}/assets/images/icon/menu/overview.png"--%>
                        <%--class="icon-menu-left"></i>--%>
                        <%--<span><spring:message code="menu.left.dashboard"/></span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                        <%--</sec:authorize>--%>

                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','STAFF','SALE_ASM','SALESUPPORT_MANAGER','SALESUPPORT_LEADER','SALESUPPORT',
                                             'MERCHANT','CUSTOMER')">
                            <li data-nav-group="Service"
                                class="${param.nav == 'hisTxn' ? 'nav-active nav-expanded' : '' }">
                                <a href="${urlTransHistory}">
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/transaction.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.transaction"/></span>
                                </a>
                            </li>
                        </sec:authorize>

                        <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')"
                                       var="perExportEpin"/>

                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT','SALESUPPORT_MANAGER','RECONCILIATION','RECONCILIATION_LEADER',
                                'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">

                            <li data-nav-group="Service" class="nav-parent ${param.nav =='merchantpo' || param.nav =='merchantpoAPI'
                                                            ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/service.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.service"/></span>
                                </a>

                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'merchantpo' ? 'nav-active' : ''}">
                                        <c:choose>
                                            <c:when test="${perExportEpin eq true}">
                                                <a href="${urlEpin}">
                                                   <span><spring:message
                                                           code="menu.left.service.submenu.exportcard"/></span>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${urlEpin}&status=1&status=2">
                                                  <span><spring:message
                                                          code="menu.left.service.submenu.exportcard"/></span>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>

                                    <li class="${param.nav =='merchantpoAPI' ? 'nav-active' : ''}">
                                        <c:choose>
                                            <c:when test="${perExportEpin eq true}">
                                                <a href="${urlEpinAPI}">
                                                   <span><spring:message
                                                           code="menu.left.service.submenu.exportcard.API"/></span>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${urlEpinAPI}&status=1&status=2">
                                                   <span><spring:message
                                                           code="menu.left.service.submenu.exportcard.API"/></span>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>

                                        <%--<li class="${param.nav =='api' ? 'nav-active' : ''}">--%>
                                        <%--<a href="${urlEpinDoc}"> <spring:message--%>
                                        <%--code="menu.left.service.submenu.apidoc"/> </a>--%>
                                        <%--</li>--%>
                                </ul>
                            </li>


                        </sec:authorize>

                        <%--Reversal Txn--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                            'CUSTOMERCARE','CUSTOMERCARE_MANAGER','RECONCILATION LEADER','RECONCILIATION')">
                            <li data-nav-group="Service" class="nav-parent ${param.nav =='reversalTxn' ||
                                                               param.nav =='reversalTxnRequest' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/reversal.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message
                                            code="menu.service.reversal.transaction"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'reversalTxn' ? 'nav-active':'' }">
                                        <a href="${urlReversalTxn}">
                                            <span><spring:message
                                                    code="menu.service.reversal.txnHistory"/></span>
                                        </a>
                                    </li>
                                    <li class="${param.nav == 'reversalTxnRequest' ? 'nav-active':'' }">
                                        <a href="${urlReversalTxnRequest}"><span><spring:message
                                                code="menu.service.reversal.txnRequest"/></span></a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>

                        <%--DOI SOAT TT DICH VU--%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','RECONCILIATION_LEADER','RECONCILIATION', 'MERCHANT')">

                            <li data-nav-group="Service"
                                class="nav-parent ${param.nav =='reconciliation-profile' || param.nav =='reconciliation' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class="fa fa-balance-scale"></i>
                                    <span><spring:message code="menu.left.reconciliation"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='reconciliation' ? 'nav-active' : '' }">
                                        <a href="${urlReconByTxn}&month=<%=LocalDate.now().getMonth().getValue()%>&year=<%=new Date().getYear() + 1900%>">
                                            <span><spring:message code="label.recon.by.payment.service"/></span>
                                        </a>
                                    </li>
                                    <sec:authorize
                                            access="hasAnyRole('ADMIN_OPERATION','RECONCILIATION_LEADER','RECONCILIATION')">
                                        <li class="${param.nav =='reconciliation-profile' ? 'nav-active' : '' }">
                                            <a href="${urlReconProfile}">
                                                <span><spring:message code="label.recon.profile"/></span>
                                            </a>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </li>
                        </sec:authorize>
                    </c:if>

                    <c:if test="${empty menu  || menu == 'all' || menu == 'wal'}">

                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION')">
                            <li class="">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/poolInitiate.png"
                                            class="icon-menu-left"></i>
                                    <span>Pool Initiate</span>
                                </a>
                            </li>
                        </sec:authorize>

                        <%--Kiểm soát tiền--%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','ACCOUNTING','SALESUPPORT_MANAGER' , 'SALESUPPORT',
                                'SALE_EXCUTIVE','SALE_AGENT',
                               'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE', 'CUSTOMERCARE_MANAGER','CUSTOMERCARE','RECONCILIATION_LEADER','RECONCILIATION')">
                            <li class="nav-parent ${param.nav =='balance-cust' ||param.nav == 'balance-mo' || param.nav == 'balance-table-money' ? 'nav-active nav-expanded':'' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/balanceMonitor.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message
                                            code="menu.left.balance.monitoring"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <sec:authorize
                                            access="hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING')">
                                        <li class="${param.nav == 'balance-mo' ? 'nav-active':'' }">
                                            <a href="${urlBalanceMonitoring}">
                                           <span><spring:message
                                                   code="menu.left.balance.monitoring.submenu.dashboard"/></span>
                                            </a>
                                        </li>
                                    </sec:authorize>
                                    <sec:authorize
                                            access="hasAnyRole('ADMIN_OPERATION','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                                            'ACCOUNTING','CUSTOMERCARE_MANAGER',
                                            'SALESUPPORT_MANAGER', 'SALE_AGENT',
                                            'RECONCILIATION_LEADER','RECONCILIATION')">
                                        <li class="${param.nav == 'balance-cust' ? 'nav-active':'' }">
                                            <a href="${urlBalanceSoftOfCash}">
                                           <span><spring:message
                                                   code="menu.wallet.balance.monitoring.balanceCustomer"/></span>
                                            </a>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </li>
                        </sec:authorize>

                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT',
                               'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE','RECONCILIATION', 'RECONCILIATION_LEADER')">
                            <li class="${param.nav == 'wallTrans' ? 'nav-active' : ''}">
                                <a href="${urlUserWalletTransactionList}">
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cashIn.png"
                                            class="icon-menu-left"></i>
                                    <span>Zo-Vip Transaction</span>
                                </a>
                            </li>
                        </sec:authorize>

                        <!-- fund in -->
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','ACCOUNTING',
                                             'SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT' ,
                                              'CUSTOMER','MERCHANT',
                                              'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                            <li data-nav-group="Wallet" class="nav-parent ${param.nav == 'fund_in' ||
                                                             param.nav == 'orderfund_in' ? 'nav-active nav-expanded':'' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cashIn.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.fundin"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'fund_in' ? 'nav-active':'' }">
                                        <a href="${urlFundIn}"><span><spring:message
                                                code="menu.left.fundin.submenu.history"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'orderfund_in' ? 'nav-active':'' }">
                                        <a href="${urlFundInOrder}"><span><spring:message
                                                code="menu.left.fundin.submenu.request"/></span></a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <!-- /fund in -->

                        <!-- fund out -->
                        <%--'SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT' ,--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                                             'FA_MANAGER','FINANCE','ACCOUNTING',
                                             'CUSTOMER','MERCHANT',
                                             'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                            <li data-nav-group="Wallet" class="nav-parent ${param.nav =='fund_out' ||
                                                             param.nav == 'orderfund_out' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cashOut.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.fundout"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'fund_out' ? 'nav-active':'' }">
                                        <a href="${urlFundOut}">
                                           <span><spring:message
                                                   code="menu.left.fundout.submit.history"/></span>
                                        </a>
                                    </li>
                                    <li class="${param.nav == 'orderfund_out' ? 'nav-active':'' }">
                                        <a href="${urlFundOutOrder}">
                                           <span><spring:message
                                                   code="menu.left.fundout.submenu.request"/></span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <!-- fund out -->

                        <%-- P2P Stransfer --%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING',
                                 'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                            <li class="${param.nav =='p2p' ? 'nav-active' : '' }">
                                <a href="${urlP2pTransfer}">
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/fundTransfer.png"
                                            class="icon-menu-left"></i>
                                    <span>P2P Ttransfer</span>
                                </a>
                            </li>
                        </sec:authorize>
                        <%-- /P2P Stransfer --%>

                        <%-- Money Transfer --%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','FINANCE','FINANCE_LEADER','FA_MANAGER','ACCOUNTING',
                                 'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                            <li data-nav-group="Wallet" class="nav-parent ${param.nav == 'wallet_transaction'||
                                                              param.nav == 'WALLET_TRANSFER'
                                                              ? 'nav-active nav-expanded':'' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/fundTransfer.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.wallet.transfer"/></span>
                                </a>
                                <ul class="nav nav-children">

                                    <sec:authorize
                                            access="hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING', 'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                                        <li class="${param.nav == 'wallet_transaction' ? 'nav-active':'' }">
                                            <a href="${urlWalletTransferHistory}">
                        <span><spring:message
                                code="menu.left.wallet.transfer.submenu.transfer.history"/></span>
                                            </a>
                                        </li>
                                    </sec:authorize>

                                    <li class="${param.nav == 'WALLET_TRANSFER' ? 'nav-active' : ''}">
                                        <a href="${urlTransferWalletBalanceRequest}">
                      <span><spring:message
                              code="menu.wallet.balance.request.transfer.wallet"/></span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li data-nav-group="Wallet" class="nav-parent ${param.nav == 'fund_transaction'||
                                                              param.nav == 'FUND_TRANSFER'
                                                              ? 'nav-active nav-expanded':'' }">
                                <sec:authorize
                                        access="hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING', 'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                                    <a>
                                        <i class=""><img
                                                src="${contextPath}/assets/images/icon/menu/fundTransfer.png"
                                                class="icon-menu-left"></i>
                                        <span><spring:message
                                                code="menu.left.fundin.sof.transfer"/></span>
                                    </a>
                                </sec:authorize>
                                <ul class="nav nav-children">


                                    <li class="${param.nav == 'fund_transaction' ? 'nav-active':'' }">
                                        <a href="${urlFundTransferHistory}">
                        <span><spring:message
                                code="menu.left.wallet.transfer.submenu.transfer.history"/></span>
                                        </a>
                                    </li>


                                    <li class="${param.nav == 'FUND_TRANSFER' ? 'nav-active' : ''}">
                                        <a href="${urlFundinSofBalanceRequest}">
                                            <span><spring:message
                                                    code="menu.wallet.balance.request.fundin.sof"/></span>
                                        </a>
                                    </li>

                                </ul>
                            </li>


                        </sec:authorize>
                        <%-- /Money Transfer --%>


                        <%-- Statement Menu --%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','CUSTOMERCARE_MANAGER','CUSTOMERCARE','SALESUPPORT_MANAGER','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')">
                            <li class="${param.nav =='statement' ? 'nav-active' : '' }">
                                <a href="${urlStatement}">
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/statement.png"
                                            class="icon-menu-left"></i>
                                    <span>Statement</span>
                                </a>
                            </li>
                        </sec:authorize>
                        <%-- /Statement Menu --%>


                        <%-- DOI SOAT ZO VIP--%>
                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','RECONCILIATION_LEADER','RECONCILIATION', 'CUSTOMERCARE_MANAGER','FA_MANAGER')">

                            <li data-nav-group="reconcil"
                                class="nav-parent ${param.nav =='reconciliation-mpoReport' || param.nav =='reconciliation' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class="fa fa-balance-scale"></i>
                                    <span><spring:message code="menu.left.reconciliation"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='reconciliation-mpoReport' ? 'nav-active' : '' }">
                                        <a href="${urlRecon}">
                                            <span><spring:message code="label.recon.by.zovip.account"/></span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <%--END DOI SOAT--%>


                        <%-- THỐNG KÊ DOANH SỐ ĐẠI LÝ--%>
                        <sec:authorize
                                access="hasAnyRole('SALE_EXCUTIVE')">
                            <li class="${param.nav =='agent-sales-statistics' ? 'nav-active' : '' }">
                                <a href="${urlAgentSalesStatistics}">
                                    <i class="fa fa-balance-scale"></i>
                                    <span><spring:message code="menu.left.agent.sales.statistics"/></span>
                                </a>
                            </li>

                        </sec:authorize>
                        <%--END THỐNG KÊ DOANH SỐ ĐẠI LÝ--%>

                    </c:if>

                    <c:if test="${empty menu || menu == 'all' || menu == 'pro'}">

                        <%--card store online--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                     'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                    'SALESUPPORT', 'SALESUPPORT_LEADER','SALESUPPORT_MANAGER',
                    'SALE_DIRECTOR','SALE_MANAGER',
                    'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                    'RECONCILIATION_LEADER','RECONCILIATION_MANAGER','RECONCILIATION')">
                            <li data-nav-group="Provider"
                                class="nav-parent ${param.nav =='dashboardcs' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cardStore.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.card.store"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'store_monitoring' ? 'nav-active':'' }">
                                        <a href="${urlStoreMonitoring}">
                                            <span><spring:message
                                                    code="menu.provider.cardStore.storeMonitoring"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'card_item' ? 'nav-active':'' }">
                                        <a href="${urlStoreCardManagement}"><span><spring:message
                                                code="menu.provider.cardStore.cardItem"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'purchase_order' ? 'nav-active':'' }">
                                        <a href="${urlPurchaseOrder}"><span><spring:message
                                                code="menu.provider.cardStore.purchaseOrder"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'special_customer' ? 'nav-active':'' }">
                                        <a href="${urlSpecialCustomer}"><span><spring:message
                                                code="menu.left.manage.special.customer"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'profile_provider' ? 'nav-active':'' }">
                                        <a href="${urlProfileProviderCardStore}"><span><spring:message
                                                code="label.profile.provider"/></span></a>
                                    </li>

                                </ul>
                            </li>
                        </sec:authorize>
                        <%--/card store online--%>

                        <%--card store online N02--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                     'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                    'SALESUPPORT', 'SALESUPPORT_LEADER','SALESUPPORT_MANAGER',
                    'SALE_DIRECTOR','SALE_MANAGER',
                    'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                    'RECONCILIATION_LEADER','RECONCILIATION_MANAGER','RECONCILIATION')">
                            <li data-nav-group="Provider"
                                class="nav-parent ${param.nav =='dashboardcs' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cardStore.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message
                                            code="menu.left.card.store.special"/></span>
                                    <span class="glyphicon glyphicon-star"
                                          style="color: #ffcc00"></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'store_monitoring' ? 'nav-active':'' }">
                                        <a href="${urlStoreN02Monitoring}">
                                            <span><spring:message
                                                    code="menu.provider.cardStore.storeMonitoring"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'card_item' ? 'nav-active':'' }">
                                        <a href="${urlStoreN02CardManagement}"><span><spring:message
                                                code="menu.provider.cardStore.cardItem"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'purchase_order' ? 'nav-active':'' }">
                                        <a href="${urlN02PurchaseOrder}"><span><spring:message
                                                code="menu.provider.cardStore.purchaseOrder"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'special_customer' ? 'nav-active':'' }">
                                        <a href="${urlN02SpecialCustomer}"><span><spring:message
                                                code="menu.left.manage.special.customer"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'profile_provider_n02' ? 'nav-active':'' }">
                                        <a href="${urlProfileProviderCardStoreN02}"><span><spring:message
                                                code="label.profile.provider"/></span></a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <%--/card store online N02--%>

                        <%--card store off line--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                    'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',
                    'SALESUPPORT', 'SALESUPPORT_LEADER','SALESUPPORT_MANAGER',
                    'SALE_DIRECTOR','SALE_MANAGER',
                    'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                    'RECONCILIATION_LEADER','RECONCILIATION_MANAGER','RECONCILIATION')">
                            <li data-nav-group="Provider"
                                class="nav-parent ${param.nav =='dashboardcs' ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cardStore.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.card.store.two"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'store_monitoring_off_line' ? 'nav-active':'' }">
                                        <a href="${urlStoreOffLineMonitoring}">
                                           <span><spring:message
                                                   code="menu.provider.cardStore.storeMonitoring.off.line"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'card_item_off_line' ? 'nav-active':'' }">
                                        <a href="${urlStoreOffLineCardManagement}">
                                           <span><spring:message
                                                   code="menu.provider.cardStore.cardItem.off.line"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'purchase_order_off_line' ? 'nav-active':'' }">
                                        <a href="${urlPurchaseOrderOffLine}">
                                          <span><spring:message
                                                  code="menu.provider.cardStore.purchaseOrder.off.line"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'special_customer_offLine' ? 'nav-active':'' }">
                                        <a href="${urlSpecialCustomerOffLine}">
                                           <span><spring:message
                                                   code="menu.left.manage.special.customer"/></span></a>
                                    </li>
                                    <li class="${param.nav == 'profile_provider_offline' ? 'nav-active':'' }">
                                        <a href="${urlProfileProviderCardStoreOffline}"><span><spring:message
                                                code="label.profile.provider"/></span></a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <%--/card store off line--%>

                        <%--NHÀ CUNG CẤP--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                                              'SALESUPPORT','SALESUPPORT_LEADER','SALESUPPORT_MANAGER',
                                              'TECHSUPPORT','CUSTOMERCARE','CUSTOMERCARE_MANAGER',
                                              'CUSTOMERCARE_MANAGER','CUSTOMERCARE',
                                               'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')">
                            <li class="nav-parent ${param.nav == 'provider' || param.nav == 'provider-service' || param.nav == 'ptu-service'? 'nav-active nav-expanded':'' }"
                                data-nav-group="Provider">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/provider.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.provider"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav == 'provider' ? 'nav-active':'' }">
                                        <a href="${urlProvider}"><span><spring:message
                                                code="menu.left.provider"/></span></a>
                                    </li>
                                        <%--                                    <li class="${param.nav == 'provider-service' ? 'nav-active':'' }">--%>
                                        <%--                                        <a href="${urlProviderService}"><span><spring:message--%>
                                        <%--                                                code="menu.left.provider.service"/></span></a>--%>
                                        <%--                                    </li>--%>
                                    <li class="${param.nav == 'ptu-service' ? 'nav-active':'' }">
                                        <a href="${urlPtuService}"><span><spring:message
                                                code="menu.left.ptu.service"/></span></a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>

                        <%--Hỗ trợ vận hành--%>
                        <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER')">
                            <li data-nav-group="Provider" class="nav-parent ${param.nav == 'balance-total-assets'||
                                                              param.nav == 'balance-table-money'
                                                              ? 'nav-active nav-expanded':'' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/cardStore.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.operational.support"/></span>
                                </a>
                                <ul class="nav nav-children">
                                        <%--Số dư tổng tài sản--%>
                                    <li class="${param.nav == 'balance-total-assets' ? 'nav-active':'' }">
                                        <a href="${urlBalanceTotalAssets}">
                                            <span><spring:message code="label.balance.of.total.assets"/></span>
                                        </a>
                                    </li>

                                        <%--end--%>
                                    <li class="${param.nav == 'balance-table-money' ? 'nav-active':'' }">
                                        <a href="${urlBalanceTableMoney}">
                                            <span><spring:message code="label.table.money.coordination"/></span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <%--end Hỗ trợ vận hành--%>

                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION','CUSTOMERCARE','CUSTOMERCARE_MANAGER')">
                            <li class="nav-parent ${param.nav == 'bill-senpay-tool' || param.nav == 'kppviettel' || param.nav == 'provider-special' ? 'nav-active nav-expanded':'' }"
                                data-nav-group="Provider">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/provider.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.provider.special"/></span>
                                    <span class="glyphicon glyphicon-star" style="color: #ffcc00"></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='provider-special' ? 'nav-active' : ''}">
                                        <a href="${urlProviderSpecial}">Senpay</a>
                                    </li>
                                    <li class="${param.nav =='bill-senpay-tool' ? 'nav-active' : ''}">
                                        <a href="${urlBillSenpayTool}">Senpay Tool <span
                                                class="label label-info">New</span></a>
                                    </li>
                                    <li class="${param.nav =='kppviettel' ? 'nav-active' : ''}">
                                        <a href="${urlKppViettel}">KPP Viettel</a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                    </c:if>

                    <%------menu left setting account ------------%>
                    <sec:authorize access="hasAnyRole('ADMIN_OPERATION')" var="hasRoleAdminOperation"/>
                    <sec:authorize access="hasAnyRole('CUSTOMERCARE_MANAGER')" var="hasRoleCustomerCareManager"/>
                    <c:if test="${empty menu || menu == 'all' || menu == 'setting'}">
                        <%--Tài khoản--%>
                        <c:choose>
                            <c:when test="${hasRoleAdminOperation}">
                                <li data-nav-group="SettingAccount" class="nav-parent ${param.nav =='set-account' ||
                                                                    param.nav =='wallet-account'  ? 'nav-active nav-expanded' : '' }">
                                    <a>
                                        <i class=""><img
                                                src="${contextPath}/assets/images/icon/menu/customer.png"
                                                class="icon-menu-left"></i>
                                        <span><spring:message
                                                code="menu.setting.account.management.list"/></span>
                                    </a>
                                    <ul class="nav nav-children">
                                        <li class="${param.nav =='set-account' ? 'nav-active' : ''}">
                                            <a href="${urlStaffAccount}"><spring:message
                                                    code="menu.setting.staff.account.management.list"/></a>
                                        </li>
                                        <li class="${param.nav =='wallet-account' ? 'nav-active' : ''}">
                                            <a href="${urlWalletAccount}"><spring:message
                                                    code="menu.setting.wallet.account.management.list"/></a>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:when test="${hasRoleCustomerCareManager}">
                                <li data-nav-group="SettingAccount" class="nav-parent ${param.nav =='set-account' ||
                                                                    param.nav =='wallet-account'  ? 'nav-active nav-expanded' : '' }">
                                    <a>
                                        <i class=""><img
                                                src="${contextPath}/assets/images/icon/menu/customer.png"
                                                class="icon-menu-left"></i>
                                        <span><spring:message
                                                code="menu.setting.account.management.list"/></span>
                                    </a>
                                    <ul class="nav nav-children">
                                        <li class="${param.nav =='set-account' ? 'nav-active' : ''}">
                                            <a href="${urlStaffAccount}"><spring:message
                                                    code="menu.setting.staff.account.management.list"/></a>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>
                        </c:choose>
                        <%--Hệ thống--%>
                        <li data-nav-group="Service" class="nav-parent ${param.nav =='set-role' ||
                                                               param.nav =='set-privilege'||
                                                               param.nav =='service' ||
                                                               param.nav =='ser-config' ||
                                                               param.nav =='serOperation' ||
                                                               param.nav =='tranRule' ? 'nav-active nav-expanded' : '' }">
                            <a>
                                <i class=""><img
                                        src="${contextPath}/assets/images/icon/menu/system.png"
                                        class="icon-menu-left"></i>
                                <span><spring:message code="menu.left.system"/></span>
                            </a>

                            <ul class="nav nav-children">
                                <sec:authorize
                                        access="hasAnyRole('ADMIN_OPERATION')">
                                    <li class="${param.nav =='set-role' ? 'nav-active' : ''}">
                                        <a href="${urlStaffAccountRoleList}"><spring:message
                                                code="menu.seting.account.management.role"/></a>
                                    </li>
                                    <li class="${param.nav =='set-privilege' ? 'nav-active' : ''}">
                                        <a href="${urlStaffAccountPrivilegeList}"><spring:message
                                                code="menu.setting.account.privilege"/></a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                                'SALESUPPORT_LEADER', 'SALESUPPORT', 'SALESUPPORT_MANAGER',
                                'RECONCILIATION','RECONCILIATION_LEADER',
                                'FINANCE',
                                'SALE_DIRECTOR','SALE_ASM' )">
                                    <li class="${param.nav =='service' ? 'nav-active' : ''}">
                                        <a href="${urlTrueService}"><spring:message
                                                code="menu.service.management.list"/></a>
                                    </li>
                                </sec:authorize>

                                <sec:authorize
                                        access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER','SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')">
                                    <li class="${param.nav =='serOperation' ? 'nav-active' : ''}">
                                        <a href="${urlTrueServiceOperation}"><spring:message
                                                code="menu.left.system.submenu.service.operation"/></a>
                                    </li>
                                </sec:authorize>

                                <sec:authorize
                                        access="hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING')">
                                    <li class="${param.nav == 'tranRule' ? 'nav-active':'' }">
                                        <a href="${urlRuleTrans}">
                                                <span><spring:message
                                                        code="menu.left.wallet.transfer.submenu.transaction.rule"/></span>
                                        </a>
                                    </li>
                                </sec:authorize>
                            </ul>
                        </li>
                        <%--end Hệ thống--%>
                    </c:if>
                    <%------/menu left setting ------------%>

                    <%------menu left customer manager ------------%>
                    <c:if test="${empty menu || menu == 'all' || menu == 'cus'}">
                        <li data-nav-group="customer-management" class="nav-parent ${param.nav =='set-cus' || param.nav =='cus-sale-switching'
                                                                          || param.nav =='cus-verifi' ||   param.nav =='cus-block' ? 'nav-active nav-expanded' : '' }">
                            <a>
                                <i class=""><img
                                        src="${contextPath}/assets/images/icon/menu/customer.png"
                                        class="icon-menu-left"></i>
                                <span><spring:message code="menu.left.customer.manager"/></span>
                            </a>

                            <sec:authorize
                                    access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')"
                                    var="permisAll"/>
                            <sec:authorize
                                    access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
                                    var="permisSale"/>
                            <ul class="nav nav-children">
                                <li class="${param.nav =='set-cus' ? 'nav-active' : ''}">
                                    <c:if test="${(permisAll)||(permisFinance)}">
                                        <a href="${urlSettingCustomerList}"><spring:message
                                                code="menu.left.customer.management.list"/></a>
                                    </c:if>
                                    <c:if test="${(permisSale eq true)}">
                                        <a href="${urlCustomerAccountAgent}"><spring:message
                                                code="menu.left.customer.management.list"/></a>
                                    </c:if>
                                </li>
                            </ul>


                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER','SALESUPPORT',
                            'CUSTOMERCARE','CUSTOMER','CUSTOMERCARE_MANAGER')">
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='cus-verifi' ? 'nav-active' : ''}">
                                        <a href="${urlVerifiCustomerList}"><spring:message
                                                code="menu.left.customer.verification.list"/></a>
                                    </li>
                                </ul>
                            </sec:authorize>

                            <sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER','SALESUPPORT',
                            'CUSTOMERCARE','CUSTOMER','CUSTOMERCARE_MANAGER')">
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='cus-block' ? 'nav-active' : ''}">
                                        <a href="${urlBlockCustomerList}"><spring:message
                                                code="menu.left.customer.block.list"/></a>
                                    </li>
                                </ul>
                            </sec:authorize>
                            <sec:authorize
                                    access="hasAnyRole('ROLE_CUSTOMERCARE', 'ROLE_CUSTOMERCARE_MANAGER', 'ROLE_ADMIN_OPERATION')">
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='cus-sale-switching' ? 'nav-active' : ''}">
                                        <a href="${urlCustomerSaleSwitchingManage}"><spring:message
                                                code="label.switching.manage"/></a>
                                    </li>
                                </ul>
                            </sec:authorize>
                        </li>

                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION', 'SALE_DIRECTOR', 'SALESUPPORT_MANAGER', 'RECONCILIATION_LEADER', 'RECONCILIATION')">
                            <li data-nav-group="customer-management"
                                class="nav-parent ${(param.nav =='cus-type-fee' || param.nav =='cus-type-special-fee') ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/feeStructure.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message code="menu.left.fee.structure"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li class="${param.nav =='cus-type-fee' ? 'nav-active' : ''}">
                                        <a href="${urlCustomerFeeStructure}">
                                            <spring:message
                                                    code="menu.left.fee.structure.customer"/>
                                        </a>
                                    </li>
                                    <li class="${param.nav =='cus-type-special-fee' ? 'nav-active' : ''}">
                                        <a href="${urlSpecifiedCommissionFeeCustomers}">
                                            <spring:message
                                                    code="menu.left.special.fee.structure.customer"/>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>

                        <sec:authorize
                                access="hasAnyRole('ADMIN_OPERATION', 'SALESUPPORT_MANAGER')">
                            <li data-nav-group="customer-management"
                                class="nav-parent ${(param.nav =='cus-type-broadcast-partner' || param.nav =='cus-type-noti-partner-ls' || param.nav =='cus-type-broadcast' || param.nav =='cus-type-noti-ls') ? 'nav-active nav-expanded' : '' }">
                                <a>
                                    <i class=""><img
                                            src="${contextPath}/assets/images/icon/menu/notification.png"
                                            class="icon-menu-left"></i>
                                    <span><spring:message
                                            code="menu.left.customer.notification"/></span>
                                </a>
                                <ul class="nav nav-children">
                                    <li data-nav-group="internal"
                                        class="nav-parent ${(param.nav =='cus-type-broadcast' || param.nav =='cus-type-noti-ls') ? 'nav-expanded' : '' }">
                                        <a class="${(param.nav =='cus-type-broadcast' || param.nav =='cus-type-noti-ls') ? 'nav-active' : '' }">
                                            <span><spring:message code="label.internal"/></span>
                                        </a>
                                        <ul class="nav nav-children">
                                            <li class="${param.nav =='cus-type-broadcast' ? 'nav-active' : ''}">
                                                <a href="${urlNotificationBroadcast}">
                                                    <spring:message
                                                            code="menu.left.customer.notification.send"/> </a>
                                            </li>
                                            <li class="${param.nav =='cus-type-noti-ls' ? 'nav-active' : ''}">
                                                <a href="${urlNotificationList}">
                                                    <spring:message
                                                            code="menu.left.customer.notification.list"/> </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li data-nav-group="partner"
                                        class="nav-parent ${(param.nav =='cus-type-broadcast-partner' || param.nav =='cus-type-noti-partner-ls') ? 'nav-expanded' : '' }">
                                        <a class="${(param.nav =='cus-type-broadcast-partner' || param.nav =='cus-type-noti-partner-ls') ? 'nav-active' : '' }">
                                            <span><spring:message code="label.partner"/></span>
                                        </a>
                                        <ul class="nav nav-children">
                                            <li class="${param.nav =='cus-type-broadcast-partner' ? 'nav-active' : ''}">
                                                <a href="${urlNotificationPartnerBroadcast}">
                                                    <spring:message
                                                            code="menu.left.customer.notification.send"/></a>
                                            </li>
                                            <li class="${param.nav =='cus-type-noti-partner-ls' ? 'nav-active' : ''}">
                                                <a href="${urlNotificationPartnerList}">
                                                    <spring:message
                                                            code="menu.left.customer.notification.list"/></a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <%--</sec:authorize>--%>
                    </c:if>
                    <%------/menu left customer manager ------------%>


                    <%--/Reversal Txn--%>

                    <%--Reim Begin--%>
                    <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
                    <%--<li data-nav-group="Service" class="nav-parent ${param.nav =='reimTxn' ||--%>
                    <%--param.nav =='reimTxnRequest' ? 'nav-active nav-expanded' : '' }">--%>
                    <%--<a>--%>
                    <%--<i class="fa fa-history"></i>--%>
                    <%--<span><spring:message code="menu.service.reim.transaction"/></span>--%>
                    <%--</a>--%>
                    <%--<ul class="nav nav-children">--%>
                    <%--<li class="${param.nav == 'reimTxn' ? 'nav-active':'' }">--%>
                    <%--<a href="${urlReimTxn}"><span><spring:message--%>
                    <%--code="menu.service.reim.txnHistory"/></span></a>--%>
                    <%--</li>--%>
                    <%--<li class="${param.nav == 'reimTxnRequest' ? 'nav-active':'' }">--%>
                    <%--<a href="${urlReimTxnRequest}"><span><spring:message--%>
                    <%--code="menu.service.reim.txnRequest"/></span></a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <%--</sec:authorize>--%>
                    <%--/Reim END--%>
                </ul>
            </nav>
        </div>
    </div>
</aside>