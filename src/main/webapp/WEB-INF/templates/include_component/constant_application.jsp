<%@ page import="vn.mog.ewallet.operation.web.controller.customer.CustomerAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.translog.TransactionLogController" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletTransferOrderFlowStage" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.StaffAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.account.WalletAccountController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.customer.CustomerBlockController" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.reconciliation.ReconciliationV1Controller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="BANK_TRANSFER" value="<%=FundOrderChannelType.BANK_TRANSFER.code%>" scope="application"/>
<c:set var="CASH_ON_HAND" value="<%=FundOrderChannelType.CASH_ON_HAND.code%>" scope="application"/>
<c:set var="LINK_BANK" value="<%=FundOrderChannelType.LINK_BANK.code%>" scope="application"/>

<!-- Fund Order -->
<c:set var="fundOrderInit" value="<%=FundOrderFlowStageType.FUND_ORDER_INIT.code%>" scope="application"/>
<c:set var="saleExcutiveReject" value="<%=FundOrderFlowStageType.SALE_EXCUTIVE_REJECTED.code%>" scope="application"/>
<c:set var="saleExcutiveVerify" value="<%=FundOrderFlowStageType.SALE_EXCUTIVE_VERIFY.code%>" scope="application"/>


<c:set var="financeSupportReject" value="<%=FundOrderFlowStageType.FINANCE_SUPPORT_REJECTED.code%>" scope="application"/>
<c:set var="financeSupportVerify" value="<%=FundOrderFlowStageType.FINANCE_SUPPORT_VERIFY.code%>" scope="application"/>

<c:set var="financeManagerRejected" value="<%=FundOrderFlowStageType.FINANCE_MANAGER_REJECTED.code%>" scope="application"/>
<c:set var="financeMamagerApprove" value="<%=FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code%>" scope="application"/>

<c:set var="financeManagerRepaymentRejected" value="<%=FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_REJECTED.code%>" scope="application"/>
<c:set var="financeManagerRepaymentApprove" value="<%=FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_APPROVE.code%>" scope="application"/>

<c:set var="fundOrderFinished" value="<%=FundOrderFlowStageType.FUND_ORDER_FINISHED.code%>" scope="application"/>


<!-- wallet transfer -->
<c:set var="walletTransferInit" value="<%=WalletTransferOrderFlowStage.OPERATION_INIT%>" scope="application"/>

<c:set var="financeRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_REJECTED%>" scope="application"/>
<c:set var="financeReadyToVerified" value="<%=WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED%>" scope="application"/>

<c:set var="financeLeaderRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED%>" scope="application"/>
<c:set var="financeLeaderReadyToReviewed" value="<%=WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED%>" scope="application"/>

<c:set var="financeManagerRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED%>" scope="application"/>
<c:set var="financeManagerReadyToApproved" value="<%=WalletTransferOrderFlowStage.FINANCE_MANAGER_READY_TO_APPROVED%>" scope="application"/>

<c:set var="walletTransferFinished" value="<%=WalletTransferOrderFlowStage.FINISHED%>" scope="application"/>

<c:set var="txnControlUri" scope="application">${contextPath}<%=TransactionLogController.TRANSACTION_CONTROLLER%></c:set>
<c:set var="prefixSearchChildUrl" scope="application">${contextPath}<%=TransactionLogController.TRANSACTION_LIST%></c:set>
<c:set var="CustomerManageListAllUrl" scope="application">${contextPath}<%=CustomerAccountController.CUSTOMER_MANAGE_LIST_ALL%></c:set>
<c:set var="CustomerManageListUrl" scope="application">${contextPath}<%=CustomerAccountController.CUSTOMER_MANAGE_LIST%></c:set>
<c:set var="AccountManageListUrl" scope="application">${contextPath}<%=StaffAccountController.ACCOUNT_MANAGE_LIST%></c:set>
<c:set var="WalletAccountManageListUrl" scope="application">${contextPath}<%=WalletAccountController.WALLET_ACCOUNT_MANAGE_LIST%></c:set>

<c:set var="BlockCusListAllUrl" scope="application">${contextPath}<%=CustomerBlockController.CUS_BLOCK_LIST_ALL%></c:set>
<c:set var="BlockCusListUrl" scope="application">${contextPath}<%=CustomerBlockController.CUS_BLOCK_LIST%></c:set>
<c:set var="BlockCusListAddUrl" scope="application">${contextPath}<%=CustomerBlockController.CUS_BLOCK_LIST_ADD%></c:set>

<%--Reconcile--%>
<c:set var="prefixSearchReconciliationdUrl" scope="application">${contextPath}<%=ReconciliationV1Controller.RECON_LIST%></c:set>
<c:set var="ReconciExportControlUri" scope="application">${contextPath}<%=ReconciliationV1Controller.RECON_EXPORT%></c:set>


<c:set var="CANCELLED" value="<%=TxnStatus.CANCELLED%>" scope="application"/>
<c:set var="CLOSED" value="<%=TxnStatus.CLOSED%>" scope="application"/>
<c:set var="FAILED" value="<%=TxnStatus.FAILED%>" scope="application"/>
<c:set var="INITIAL" value="<%=TxnStatus.INITIAL%>" scope="application"/>
<c:set var="OPENED" value="<%=TxnStatus.OPENED%>" scope="application"/>
<c:set var="REVERSED" value="<%=TxnStatus.REVERSED%>" scope="application"/>
<c:set var="HOLD" value="<%=TxnStatus.HOLD%>" scope="application"/>

<c:set var="cusTypeCustomer" scope="application"><%=CustomerType.ID_CUSTOMER%></c:set>
<c:set var="cusTypeMerchant" scope="application"><%=CustomerType.ID_MERCHANT%></c:set>
<c:set var="cusTypeAgent" scope="application"><%=CustomerType.ID_AGENT%></c:set>
<c:set var="cusTypeProvider" scope="application"><%=CustomerType.ID_PROVIDER%></c:set>
<c:set var="cusTypePropertyManager" scope="application"><%=CustomerType.ID_PROPERTY_MANAGER%></c:set>

<c:set var="cusTypeStaff" scope="application"><%=CustomerType.ID_STAFF%></c:set>
<c:set var="cusTypeAdmin" scope="application"><%=CustomerType.ID_ADMIN%></c:set>





