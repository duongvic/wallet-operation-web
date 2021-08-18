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
import vn.mog.ewallet.operation.web.thirdparty.system.integration.PaymentOpServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.ITrueServiceEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class TrueServiceEndpoint implements ITrueServiceEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.system-findTrueServices}")
  private String URI_FIND_SERVICES;
  @Value("${platform.backend.payment.service.3rd.url.system-getTrueServiceTransactionRuleMatrix}")
  private String TRANSACTION_RULE_MATRIX;
  @Value("${platform.backend.payment.service.3rd.url.system-updateTrueServiceTransactionRuleMatrix}")
  private String UPDATE_TRUE_SERVICE_MATRIX;
  @Value("${platform.backend.payment.service.3rd.url.system-updateTrueServiceAttribute}")
  private String UPDATE_TRUE_SERVICE_ATTRIBUTE;

  @Value("${platform.backend.payment.service.3rd.url.system-changeTrueServiceStatus}")
  private String CHANGE_TRUE_SERVICE_STATUS;

  @Value("${platform.backend.payment.service.3rd.url.system-createTrueService}")
  private String CREATE_TRUE_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.system-updateTrueService}")
  private String UPDATE_TRUE_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.system-deleteTrueService}")
  private String DELETE_TRUE_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.system-getTrueService}")
  private String GET_TRUE_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.system-getTrueServiceOperation}")
  private String GET_TRUE_SERVICE_OPERATION;

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Autowired
  PaymentOpServerAPIClient paymentServerAPI;

  @Override
  public FindTrueServiceResponse findTrueServices(FindTrueServiceRequest request) throws FrontEndException {
    try {
      String endpoint = URI_FIND_SERVICES;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return paymentServerAPI.postForEntity(endpoint, request, FindTrueServiceResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ChangeTrueServiceStatusResponse changeTrueServiceStatus(ChangeTrueServiceStatusRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + CHANGE_TRUE_SERVICE_STATUS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ChangeTrueServiceStatusResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public CreateTrueServiceResponse createTrueService(CreateTrueServiceRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + CREATE_TRUE_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, CreateTrueServiceResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateTrueServiceResponse updateTrueService(UpdateTrueServiceRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + UPDATE_TRUE_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, UpdateTrueServiceResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public DeleteTrueServiceResponse deleteTrueService(DeleteTrueServiceRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + DELETE_TRUE_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, DeleteTrueServiceResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetTrueServiceResponse getTrueService(GetTrueServiceRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + GET_TRUE_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetTrueServiceResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetTrueServiceOperationResponse getTrueServiceOperation(GetTrueServiceOperationRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + GET_TRUE_SERVICE_OPERATION;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetTrueServiceOperationResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetTrueServiceTransactionRuleMatrixResponse getTrueServiceTransactionRuleMatrix(GetTrueServiceTransactionRuleMatrixRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + TRANSACTION_RULE_MATRIX;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetTrueServiceTransactionRuleMatrixResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateTrueServiceTransactionRuleMatrixResponse updateTrueServiceMatrix(UpdateTrueServiceTransactionRuleMatrixRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + UPDATE_TRUE_SERVICE_MATRIX;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdateTrueServiceTransactionRuleMatrixResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateTrueServiceAttributeResponse updateTrueServiceAttribute(UpdateTrueServiceAttributeRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + UPDATE_TRUE_SERVICE_ATTRIBUTE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, UpdateTrueServiceAttributeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

}
