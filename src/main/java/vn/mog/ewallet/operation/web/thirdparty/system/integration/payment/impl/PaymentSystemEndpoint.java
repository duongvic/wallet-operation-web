package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IPaymentSystemEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class PaymentSystemEndpoint implements IPaymentSystemEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.system-createTransactionRule}")
  private String URI_CREATE_TRANSACTION_RULE;

  @Value("${platform.backend.payment.service.3rd.url.system-removeTransactionRule}")
  private String URI_REMOVE_TRANSACTION_RULE;

  @Value("${platform.backend.payment.service.3rd.url.system-updateTransactionRule}")
  private String URI_UPDATE_TRANSACTION_RULE;

  @Value("${platform.backend.payment.service.3rd.url.transaction-hold-approve-to-success}")
  private String URI_CHANGE_STATUS_TRANSACTION_HOLD;

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public CreateTransactionRuleResponse createTransactionRule(CreateTransactionRuleRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_CREATE_TRANSACTION_RULE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreateTransactionRuleResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdateTransactionRuleResponse updateTransactionRule(UpdateTransactionRuleRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_TRANSACTION_RULE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdateTransactionRuleResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public RemoveTransactionRuleResponse removeTransactionRule(RemoveTransactionRuleRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_REMOVE_TRANSACTION_RULE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, RemoveTransactionRuleResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public TransactionHoldApproveToSuccessResponse changeStatusTxtHold(
      TransactionHoldApproveToSuccessRequest request) throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_CHANGE_STATUS_TRANSACTION_HOLD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, TransactionHoldApproveToSuccessResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
