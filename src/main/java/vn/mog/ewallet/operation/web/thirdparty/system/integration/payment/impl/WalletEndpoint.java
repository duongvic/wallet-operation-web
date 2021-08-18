package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerRequest;
import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerResponse;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IWalletEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerDetailRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerDetailResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileCustomerFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
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
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Slf4j
@Service
public class WalletEndpoint implements IWalletEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-workflow-reject}")
  private String URI_ORDER_FLOW_REJECT_RESPONSE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-workflow-approve}")
  private String URI_ORDER_FLOW_APPROVE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-getOTPConfirm}")
  private String URI_ORDER_GET_OTP_CONFIRM;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-get}")
  private String URI_ORDER_GET_WALLET_TRANSFER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-update}")
  private String URI_ORDER_UPDATE_WALLET_TRANSFER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-create}")
  private String URI_ORDER_CREATE_WALLET_TRANSFER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-find}")
  private String URI_ORDER_FIND_WALLET_TRANSFER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-walletTransfer}")
  private String URI_ORDER_CONFIRM_OTP;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-workflow-submit}")
  private String URI_ORDER_FLOW_SUBMIT_PROCCESS;

  @Value("${platform.backend.payment.service.3rd.url.wallet-transferOrder-find}")
  private String URI_ORDER_FIND_REIM_TRANSFER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-balanceInquiry}")
  private String URI_BALANCE_INQUIRY;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundIn}")
  private String URI_FUND_IN;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOut}")
  private String URI_FUND_OUT;

  @Value("${platform.backend.payment.service.3rd.url.wallet-findCustomer}")
  private String URI_FIND_BALANCE_CUSTOMER;

  @Value("${platform.backend.payment.service.3rd.url.wallet-bank-findBankDirects}")
  private String URI_FIND_CUSTOMER_BANK_DIRECT;

  @Value("${platform.backend.payment.service.3rd.url.wallet-findBanks}")
  private String URI_FIND_BANK;

  @Value("${platform.backend.payment.service.3rd.url.wallet-findBankProfiles}")
  private String URI_FIND_BANK_PROFILE;

  @Value("${platform.backend.payment.service.3rd.url.reconcile-find}")
  private String URI_ORDER_FIND_RECONCILE;

  @Value("${platform.backend.payment.service.3rd.url.reconcile-customer-summary-find}")
  private String URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_FIND;

  @Value("${platform.backend.payment.service.3rd.url.reconcile-customer-summary-get}")
  private String URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_GET;

  @Value("${platform.backend.payment.service.3rd.url.reconcile-customer-summary-export}")
  private String URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_EXPORT;

  @Value("${platform.backend.payment.service.3rd.url.reconcile-export}")
  private String URI_ORDER_EXPORT_RECONCILE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-find}")
  private String URI_FUND_ORDER_FIND;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-create}")
  private String URI_FUND_ORDER_CREATE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-attachment-create}")
  private String URI_FUND_ORDER_ATTACHMENT_CREATE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-update}")
  private String URI_FUND_ORDER_UPDATE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-get}")
  private String URI_FUND_ORDER_GET;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-workflow-approve}")
  private String URI_FUND_ORDER_WORKFLOW_APPROVE;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-workflow-reject}")
  private String URI_FUND_ORDER_WORKFLOW_REJECT;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-workflow-submit}")
  private String URI_FUND_ORDER_WORKFLOW_SUBMIT;

  @Value("${platform.backend.payment.service.3rd.url.wallet-fundOrder-getOTPConfirm}")
  private String URI_FUND_ORDER_GET_OTP_CONFIRM;

  @Value("${platform.backend.payment.service.3rd.url.transaction-summary-by-customer}")
  private String URI_TRANSACTION_SUMMARY_BY_CUSTOMER;

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public long getBalanceUser() throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_BALANCE_INQUIRY;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", "");
      return restTemplate.postForEntity(endpoint, new MobiliserRequestType(), Long.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundInResponse confirmFundInOtp(FundInRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_IN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FundInResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundOutResponse confirmFundOutOtp(FundOutRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_OUT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FundOutResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindFundOrderResponse findFundOrders(FindFundOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_FIND;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindFundOrderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateFundOrderResponse createFundOrder(CreateFundOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_CREATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, CreateFundOrderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateFundOrderAttachmentResponse createFundOrderAttachment(
      CreateFundOrderAttachmentRequest request) throws FrontEndException {
    // TODO Auto-generated method stub
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_ATTACHMENT_CREATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreateFundOrderAttachmentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdateFundOrderResponse updateFundOrder(UpdateFundOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_UPDATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, UpdateFundOrderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetFundOrderResponse getFundOrder(GetFundOrderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_GET;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetFundOrderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundOrderFlowApproveResponse approveFundOrder(FundOrderFlowApproveRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_WORKFLOW_APPROVE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FundOrderFlowApproveResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundOrderFlowRejectResponse rejectFundOrder(FundOrderFlowRejectRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_WORKFLOW_REJECT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FundOrderFlowRejectResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundOrderFlowSubmitProcessResponse submitFundOrder(
      FundOrderFlowSubmitProcessRequest request) throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_WORKFLOW_SUBMIT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FundOrderFlowSubmitProcessResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetOTPConfirmResponse getOTPConfirm(GetOTPConfirmRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUND_ORDER_GET_OTP_CONFIRM;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetOTPConfirmResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindBankProfileResponse getProfileBanks(FindBankProfileRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_BANK_PROFILE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindBankProfileResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindBankResponse findBanks(FindBankRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_BANK;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindBankResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindCustomerBankDirectResponse findBankDirects(FindCustomerBankDirectRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_CUSTOMER_BANK_DIRECT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindCustomerBankDirectResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindCustomerResponse findBalanceCustomer(FindCustomerRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_BALANCE_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindCustomerResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindWalletTransferOrderResponse findWalletTransferOrder(
      FindWalletTransferOrderRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FIND_WALLET_TRANSFER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindWalletTransferOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateWalletTransferOrderResponse createWalletTransferOrder(
      CreateWalletTransferOrderRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_CREATE_WALLET_TRANSFER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreateWalletTransferOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdateWalletTransferOrderResponse updateWalletTransferOrder(
      UpdateWalletTransferOrderRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_UPDATE_WALLET_TRANSFER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdateWalletTransferOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetWalletTransferOrderResponse getWalletTransferOrder(
      GetWalletTransferOrderRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_GET_WALLET_TRANSFER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetWalletTransferOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public OrderFlowSubmitProcessResponse orderFlowSubmitProccess(
      OrderFlowSubmitProcessRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FLOW_SUBMIT_PROCCESS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, OrderFlowSubmitProcessResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetOTPConfirmResponse getWalletTransferOrderOTPConfirm(GetOTPConfirmRequest request) {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_GET_OTP_CONFIRM;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetOTPConfirmResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public OrderFlowApproveResponse approveWalletTransferOrder(OrderFlowApproveRequest request) {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FLOW_APPROVE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, OrderFlowApproveResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public OrderFlowRejectResponse rejectWalletTransferOrder(OrderFlowRejectRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FLOW_REJECT_RESPONSE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, OrderFlowRejectResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public WalletTransferResponse orderConfirmOTP(WalletTransferRequest request) {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_CONFIRM_OTP;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, WalletTransferResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public TransactionReimResponse findReim(TransactionReimRequest request) {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FIND_REIM_TRANSFER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, TransactionReimResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ReconcileTransactionFindResponse findReconci(ReconcileTransactionFindRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_FIND_RECONCILE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ReconcileTransactionFindResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ReconcileCustomerFindResponse findReconciCustomerSummaryFind(
      ReconcileCustomerFindRequest request) throws FrontEndException {
    try {
      ReconcileCustomerFindResponse reconcileCustomerFindResponse = null;
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT
          + URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_FIND;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      long prevTime = new Date().getTime();
      reconcileCustomerFindResponse = restTemplate
          .postForEntity(endpoint, request, ReconcileCustomerFindResponse.class)
          .getBody();
      long afterTime = new Date().getTime();
      log.info("=================take time to call backend: {}", afterTime - prevTime);
      return reconcileCustomerFindResponse;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ReconcileCustomerDetailResponse findReconciCustomerSummaryGet(
      ReconcileCustomerDetailRequest request) throws FrontEndException {
    try {
      ReconcileCustomerDetailResponse reconcileCustomerDetailResponse = null;
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT
          + URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_GET;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      long prevTime = new Date().getTime();
      reconcileCustomerDetailResponse = restTemplate
          .postForEntity(endpoint, request, ReconcileCustomerDetailResponse.class)
          .getBody();
      long afterTime = new Date().getTime();
      log.info("=================take time to call backend: {}", afterTime - prevTime);
      return reconcileCustomerDetailResponse;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ReconcileTransactionExportResponse findReconciCustomerSummaryExport(
      ReconcileTransactionExportRequest request) throws FrontEndException {
    try {
      ReconcileTransactionExportResponse reconcileTransactionExportResponse = null;
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT
          + URI_ORDER_FIND_RECONCILE_CUSTOMER_SUMMARY_EXPORT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      long prevTime = new Date().getTime();
      reconcileTransactionExportResponse = restTemplate
          .postForEntity(endpoint, request, ReconcileTransactionExportResponse.class)
          .getBody();
      long afterTime = new Date().getTime();
      log.info("=================take time to call backend: {}", afterTime - prevTime);
      return reconcileTransactionExportResponse;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ReconcileTransactionExportResponse exportReconci(ReconcileTransactionExportRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_ORDER_EXPORT_RECONCILE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ReconcileTransactionExportResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SummaryTransactionByCustomerResponse transactionSummaryByCustomer(SummaryTransactionByCustomerRequest request) throws FrontEndException {
    try {
      SummaryTransactionByCustomerResponse summaryTransactionByCustomerResponse = null;
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT
          + URI_TRANSACTION_SUMMARY_BY_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      long prevTime = new Date().getTime();
      summaryTransactionByCustomerResponse = restTemplate
          .postForEntity(endpoint, request, SummaryTransactionByCustomerResponse.class)
          .getBody();
      long afterTime = new Date().getTime();
      log.info("=================take time to call backend: {}", afterTime - prevTime);
      return summaryTransactionByCustomerResponse;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
