package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.AuthServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl.NotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeBlacklistReasonResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeCredentialResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaCustomerEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.FindAgentRequest;
import vn.mog.framework.common.utils.Utils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentResponse;

@Slf4j
@Service
public class CustomerUaaEndpoint implements IUaaCustomerEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.customer-getCustomer}")
  private String URI_GET_CUSTOMER;

  @Value("${platform.backend.payment.service.3rd.url.customer-findCustomers}")
  private String URI_FIND_CUSTOMERS;

  @Value("${platform.backend.uaa.service.3rd.url.system-getAllCustomerTypes}")
  private String URI_GET_ALL_CUSTOMER_TYPE;

  @Value("${platform.backend.uaa.service.3rd.url.system-getBlacklistReasons}")
  private String URI_GET_BLACK_LIST_REASON;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-changeBlacklistReasons}")
  private String URI_CHANGE_BLACK_LIST_REASON;

  @Value("${platform.backend.uaa.service.3rd.url.security-changeCredential}")
  private String URI_CHANGE_PASSWORD;

  @Value("${platform.backend.uaa.service.3rd.url.system-getCustomerAttributeTypes}")
  private String URI_GET_CUSTOMER_ATTRIBUTE_TYPE;

  @Value("${platform.backend.uaa.service.3rd.url.customer-system-find-agents-by-msisdn}")
  private String URI_CUSTOMER_SYSTEM_FIND_AGENTS_BY_MSISDN;

  @Value("${platform.backend.uaa.service.3rd.url.customer-system-assign-agents}")
  private String URI_CUSTOMER_SYSTEM_ASSIGN_AGENTS;

  @Autowired AuthServerAPIClient uaaServerApiClient;

  @Autowired OAuth2RestTemplate restTemplate;

  /** ??? */
  @Override
  public GetCustomerResponse getCustomer(GetCustomerRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetCustomerResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  /** ??? */
  @Override
  public FindFullCustomerResponse findCustomers(FindFullCustomerRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_CUSTOMERS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindFullCustomerResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetCustomerResponse getCustomerById(GetCustomerRequest request) throws FrontEndException {
    try {
      String endpoint = URI_GET_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(endpoint, request, GetCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetBlacklistReasonResponse getBlacklistReason(GetBlacklistReasonRequest request)
      throws Exception {
    try {
      String endpoint = URI_GET_BLACK_LIST_REASON;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(endpoint, request, GetBlacklistReasonResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ChangeBlacklistReasonResponse changeBlacklistReasonResponse(
      ChangeBlacklistReasonRequest request) throws Exception {
    try {
      String endpoint = URI_CHANGE_BLACK_LIST_REASON;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(
          endpoint, request, ChangeBlacklistReasonResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetAllCustomerTypeResponse getAllCustomerTypes(GetAllCustomerTypeRequest request)
      throws Exception {
    try {
      String endpoint = URI_GET_ALL_CUSTOMER_TYPE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(endpoint, request, GetAllCustomerTypeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetCustomerAttributeTypeResponse getCustomerAttributeTypes(
      GetCustomerAttributeTypeRequest request) throws Exception {
    try {
      String endpoint = URI_GET_CUSTOMER_ATTRIBUTE_TYPE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(
          endpoint, request, GetCustomerAttributeTypeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ChangeCredentialResponse changePassWord(ChangeCredentialRequest request) throws Exception {
    try {
      String endpoint = URI_CHANGE_PASSWORD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(endpoint, request, ChangeCredentialResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindUmgrCustomerResponse findAgentsByMsisdnCustomerSystem(FindAgentRequest request) throws Exception {
    try {
      String endpoint = URI_CUSTOMER_SYSTEM_FIND_AGENTS_BY_MSISDN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", request);
      return uaaServerApiClient.postForEntity(endpoint, request, FindUmgrCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public AssignAgentResponse assignAgentsCustomerSystem(AssignAgentRequest request)
      throws Exception {
    try {
      String endpoint = URI_CUSTOMER_SYSTEM_ASSIGN_AGENTS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return uaaServerApiClient.postForEntity(endpoint, request, AssignAgentResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
