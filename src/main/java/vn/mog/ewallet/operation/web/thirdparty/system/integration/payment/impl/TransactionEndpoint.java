package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.ITransactionEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.*;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class TransactionEndpoint implements ITransactionEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.system-findTransactionRules}")
  private String URL_FIND_TRANSACTION_RULE;

  @Value("${platform.backend.payment.service.3rd.url.system-getTransactionRule}")
  private String URL_GET_TRANSACTION_RULE;

  @Value("${platform.backend.historycal.service.3rd.url.transaction-exportTransactionLog}")
  private String URI_EXPORT_TXN;

  @Value("${platform.backend.historycal.service.3rd.url.transaction-findTransactions}")
  private String URI_FIND_TRANS;

  @Value("${platform.backend.historycal.service.3rd.url.transaction-getTransaction}")
  private String URI_GET_TRANS;

  @Value("${platform.backend.payment.service.3rd.url.transaction-customer-statement}")
  private String URI_STATEMENT_USER;

  @Value("${platform.backend.payment.service.3rd.url.transaction-customer-statement-export}")
  private String URI_EXPORT_STATEMENT_USER;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-order-create}")
  private String URI_CREATE_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-order-get}")
  private String URI_GET_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-order-find}")
  private String URI_FIND_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-workflow-submit}")
  private String URI_SUBMIT_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-workflow-reject}")
  private String URI_REJECT_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.transaction-reversal-workflow-approve}")
  private String URI_APPROVE_ORDER_REVERSAL;

  @Value("${platform.backend.payment.service.3rd.url.ptu-clearing-make-phone-topup}")
  private String URI_PHONE_TOPUP_TRANS_ON_HOLD;

  @Value("${platform.backend.payment.service.3rd.url.bill-clearing-make-bill-payment}")
  private String URI_BILL_PAYMENT_TRANS_ON_HOLD;

  @Value("${platform.backend.payment.service.3rd.url.ptu-clearing-make-phone-topup-by-batch}")
  private String URI_PHONE_TOPUP_TRANS_ON_HOLD_BY_BATCH;

  @Value("${platform.backend.payment.service.3rd.url.ptu-clearing-make-bill-payment-by-batch}")
  private String URI_BILL_PAYMENT_TRANS_ON_HOLD_BY_BATCH;

  @Autowired OAuth2RestTemplate restTemplate;

  public GetTransactionResponse getTransaction(GetTransactionRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT + URI_GET_TRANS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetTransactionResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  public FindTransactionsResponse findTransactions(FindTransactionsRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT + URI_FIND_TRANS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindTransactionsResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindTransactionRuleResponse findTransactionRules(FindTransactionRuleRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URL_FIND_TRANSACTION_RULE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindTransactionRuleResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetTransactionRuleResponse getTransactionRule(GetTransactionRuleRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URL_GET_TRANSACTION_RULE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetTransactionRuleResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ExportTransactionLogResponse exportTransaction(ExportTransactionLogRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_HISTORYCAL_SERVICE_API_ENDPOINT + URI_EXPORT_TXN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ExportTransactionLogResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetStatementOfCustomerResponse statementUser(GetStatementOfCustomerRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_STATEMENT_USER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetStatementOfCustomerResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ExportExcelFileCustomerStatementResponse exportStatementUser(
      ExportExcelFileCustomerStatementRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_EXPORT_STATEMENT_USER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ExportExcelFileCustomerStatementResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateTransactionReversalOrderResponse createOrderReversal(
      CreateTransactionReversalOrderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_CREATE_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreateTransactionReversalOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetTransactionReversalOrderResponse getOrderReversal(
      GetTransactionReversalOrderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetTransactionReversalOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindTransactionReversalOrderResponse findOrderReversal(
      FindTransactionReversalOrderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindTransactionReversalOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public TransactionRevertFlowSubmitResponse submitReversal(
      TransactionRevertFlowSubmitRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_SUBMIT_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, TransactionRevertFlowSubmitResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public TransactionRevertFlowRejectResponse rejectReversal(
      TransactionRevertFlowRejectRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_REJECT_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, TransactionRevertFlowRejectResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public TransactionRevertFlowApproveResponse approveReversal(
      TransactionRevertFlowApproveRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_APPROVE_ORDER_REVERSAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, TransactionRevertFlowApproveResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public PhoneTopupTransactionOnHoldResponse getPhoneTopupTransactionOnHold(
      PhoneTopupTransactionOnHoldRequest request) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_PHONE_TOPUP_TRANS_ON_HOLD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, PhoneTopupTransactionOnHoldResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public BillPaymentTransactionOnHoldResponse getBillPaymentTransactionOnHold(
          BillPaymentTransactionOnHoldRequest request) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_BILL_PAYMENT_TRANS_ON_HOLD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, BillPaymentTransactionOnHoldResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }


  @Override
  public PhoneTopupTransactionOnHoldByBatchResponse getPhoneTopupTransactionOnHoldBatch(
      PhoneTopupTransactionOnHoldByBatchRequest request) throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_PHONE_TOPUP_TRANS_ON_HOLD_BY_BATCH;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, PhoneTopupTransactionOnHoldByBatchResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public BillPaymentTransactionOnHoldByBatchResponse getBillPaymentTransactionOnHoldBatch(BillPaymentTransactionOnHoldByBatchRequest request) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_BILL_PAYMENT_TRANS_ON_HOLD_BY_BATCH;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, BillPaymentTransactionOnHoldByBatchResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
