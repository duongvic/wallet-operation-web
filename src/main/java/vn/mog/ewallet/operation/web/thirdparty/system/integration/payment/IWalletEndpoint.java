package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerRequest;
import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerResponse;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerDetailRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerDetailResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankProfileResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundInRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundInResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundOutRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundOutResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowRejectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowSubmitProcessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowSubmitProcessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowSubmitProcessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowSubmitProcessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.WalletTransferRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.WalletTransferResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmResponse;

public interface IWalletEndpoint {

  /**
   * Business
   */
  long getBalanceUser() throws FrontEndException;

  FundInResponse confirmFundInOtp(FundInRequest request) throws FrontEndException;

  FundOutResponse confirmFundOutOtp(FundOutRequest request) throws FrontEndException;

  /**
   * *** Fund Order ********
   */
  FindFundOrderResponse findFundOrders(FindFundOrderRequest request) throws FrontEndException;

  CreateFundOrderResponse createFundOrder(CreateFundOrderRequest request) throws FrontEndException;

  CreateFundOrderAttachmentResponse createFundOrderAttachment(
      CreateFundOrderAttachmentRequest request) throws FrontEndException;

  UpdateFundOrderResponse updateFundOrder(UpdateFundOrderRequest request) throws FrontEndException;

  GetFundOrderResponse getFundOrder(GetFundOrderRequest request) throws FrontEndException;

  FundOrderFlowApproveResponse approveFundOrder(FundOrderFlowApproveRequest request)
      throws FrontEndException;

  FundOrderFlowRejectResponse rejectFundOrder(FundOrderFlowRejectRequest request)
      throws FrontEndException;

  FundOrderFlowSubmitProcessResponse submitFundOrder(FundOrderFlowSubmitProcessRequest request)
      throws FrontEndException;

  GetOTPConfirmResponse getOTPConfirm(GetOTPConfirmRequest request) throws FrontEndException;

  /** ********* System Get Bank Zo-Ta ********** */
  FindBankProfileResponse getProfileBanks(FindBankProfileRequest request) throws FrontEndException;

  /** ********* System Get list Bank ********** */
  FindBankResponse findBanks(FindBankRequest request) throws FrontEndException;

  FindCustomerBankDirectResponse findBankDirects(FindCustomerBankDirectRequest request)
      throws FrontEndException;

  FindCustomerResponse findBalanceCustomer(FindCustomerRequest request) throws FrontEndException;

  /*--- WALLET TRANSFER ORDER ---*/
  FindWalletTransferOrderResponse findWalletTransferOrder(FindWalletTransferOrderRequest request);

  CreateWalletTransferOrderResponse createWalletTransferOrder(
      CreateWalletTransferOrderRequest request);

  UpdateWalletTransferOrderResponse updateWalletTransferOrder(
      UpdateWalletTransferOrderRequest request);

  GetWalletTransferOrderResponse getWalletTransferOrder(GetWalletTransferOrderRequest request);

  OrderFlowSubmitProcessResponse orderFlowSubmitProccess(OrderFlowSubmitProcessRequest request);

  GetOTPConfirmResponse getWalletTransferOrderOTPConfirm(GetOTPConfirmRequest request);

  /*--- WALLET ORDER flow ---*/

  OrderFlowApproveResponse approveWalletTransferOrder(OrderFlowApproveRequest request);

  OrderFlowRejectResponse rejectWalletTransferOrder(OrderFlowRejectRequest request);

  WalletTransferResponse orderConfirmOTP(WalletTransferRequest request);

  /*--- WALLET REIM ---*/
  TransactionReimResponse findReim(TransactionReimRequest request) throws FrontEndException;

  /* RECONCILIATION*/

  ReconcileTransactionFindResponse findReconci(ReconcileTransactionFindRequest request)
      throws FrontEndException;

  ReconcileCustomerFindResponse findReconciCustomerSummaryFind(ReconcileCustomerFindRequest request)
      throws FrontEndException;

  ReconcileCustomerDetailResponse findReconciCustomerSummaryGet(
      ReconcileCustomerDetailRequest request)
      throws FrontEndException;

  ReconcileTransactionExportResponse findReconciCustomerSummaryExport(
      ReconcileTransactionExportRequest request)
      throws FrontEndException;

  ReconcileTransactionExportResponse exportReconci(ReconcileTransactionExportRequest request)
      throws FrontEndException;

  //THONG KE DOANH SO CUA CAC AGENT CON THEO CHA
  SummaryTransactionByCustomerResponse transactionSummaryByCustomer (SummaryTransactionByCustomerRequest request) throws FrontEndException;
}
