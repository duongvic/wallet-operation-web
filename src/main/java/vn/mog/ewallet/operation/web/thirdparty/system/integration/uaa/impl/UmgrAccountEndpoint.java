package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.AuthServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.PaymentOpServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl.NotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUmgrAccountEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Component
public class UmgrAccountEndpoint implements IUmgrAccountEndpoint {

  @Value("${platform.backend.uaa.service.3rd.url.customer-getCustomer}")
  private String UMGR_CUSTOMER_GET;

  @Value("${platform.backend.uaa.service.3rd.url.customer-saveOrUpdateAttribute}")
  private String UMGR_CUSTOMER_UPDATE_ATTRIBUTE;

  @Value("${platform.backend.uaa.service.3rd.url.customer-deleteCustomerAttribute}")
  private String UMGR_CUSTOMER_DELETE_ATTRIBUTE;

  @Value("${platform.backend.uaa.service.3rd.url.customer-updateCustomer}")
  private String UMGR_CUSTOMER_UPDATE;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-generateGoogleAuthenticatorInfo}")
  private String UMGR_CUSTOMER_GENERATE_GOOGLE_AUTHEN;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-getGoogleAuthenticatorInfo}")
  private String GOOGLE_AUTHENTICATOR_INFO_GET;

  @Value("${platform.backend.uaa.service.3rd.url.security-resetCredential}")
  private String UMGR_RESET_CUSTOMER_CREDENTIAL;

  @Value("${platform.backend.uaa.service.3rd.url.customer-createCustomer}")
  private String CUSTOMER_CREATE;

  @Value("${platform.backend.uaa.service.3rd.url.customer-address-save-or-update}")
  private String CUSTOMER_CREATE_ADDRESS;

  @Value("${platform.backend.uaa.service.3rd.url.customer-getFullCustomer}")
  private String FULL_CUSTOMER_GET;

  @Value("${platform.backend.uaa.service.3rd.url.customer-sale-get-agent-list}")
  private String SALE_GET_AGENT_LIST;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-updateImageProfile}")
  private String UPDATE_IMAGE_PROFILE;

  @Value("${platform.backend.payment.service.3rd.url.customer-blacklist-find}")
  private String FIND_LIST_BLOCK_CUS;

  @Value("${platform.backend.payment.service.3rd.url.customer-blacklist-add}")
  private String ADD_BLOCK_CUS;

  @Value("${platform.backend.payment.service.3rd.url.customer-blacklist-delete}")
  private String REMOVE_BLOCK_CUS;

  @Value("${platform.backend.payment.service.3rd.url.provider-findProvidersByBlockCustomer}")
  private String FIND_PROVIDER_BY_BLOCK_CUSTOMER;

  @Value("${platform.backend.payment.service.3rd.url.provider-findProviderServicesByBlockCustomer}")
  private String FIND_PROVIDER_SERVICE_BY_BLOCK_CUSTOMER;

  @Value("${platform.backend.payment.service.3rd.url.system-findTrueServicesByBlockCustomer}")
  private String FIND_TRUE_SERVICE_BY_BLOCK_CUSTOMER;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-get-attachment}")
  private String URI_CONSUMER_KYC_GET_ATTACHMENT;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-getImageProfileUrl}")
  private String URI_CONSUMER_GET_IMAGE_PROFILE_URL;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-find-event-bydate}")
  private String URI_CONSUMER_FIND_EVENT_BY_DATE;

  @Autowired
  AuthServerAPIClient authServerAPIClient;

  @Autowired
  PaymentOpServerAPIClient paymentOpServerAPIClient;

  @Override
  public GetCustomerResponse getCustomer(GetCustomerRequest request) throws Exception {
    try {
      String endpoint = UMGR_CUSTOMER_GET;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, GetCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetFullCustomerResponse getFullCustomer(GetFullCustomerRequest request) throws Exception {
    try {
      String endpoint = FULL_CUSTOMER_GET;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, GetFullCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateCustomerAttributeResponse saveOrUpdateAttribute(
      UpdateCustomerAttributeRequest request) throws Exception {
    try {
      String endpoint = UMGR_CUSTOMER_UPDATE_ATTRIBUTE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, UpdateCustomerAttributeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public DeleteCustomerAttributeResponse deleteCustomerAttribute(
      DeleteCustomerAttributeRequest request) throws Exception {
    try {
      String endpoint = UMGR_CUSTOMER_DELETE_ATTRIBUTE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, DeleteCustomerAttributeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) throws Exception {
    try {
      String endpoint = UMGR_CUSTOMER_UPDATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, UpdateCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GenerateGoogleAuthenticatorResponse generateGoogleAuthenticatorInfo(
      GenerateGoogleAuthenticatorRequest request) throws Exception {
    try {
      String endpoint = UMGR_CUSTOMER_GENERATE_GOOGLE_AUTHEN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, GenerateGoogleAuthenticatorResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public ResetCredentialResponse resetCredential(ResetCredentialRequest request) throws Exception {
    try {
      String endpoint = UMGR_RESET_CUSTOMER_CREDENTIAL;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, ResetCredentialResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetGoogleAuthenticatorInfoResponse getGoogleAuthenticatorInfo(
      GetGoogleAuthenticatorInfoRequest request) throws Exception {
    try {
      String endpoint = GOOGLE_AUTHENTICATOR_INFO_GET;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, GetGoogleAuthenticatorInfoResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public CreateCustomerResponse createCustomer(CreateCustomerRequest request) throws Exception {
    try {
      String endpoint = CUSTOMER_CREATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient.postForEntity(endpoint, request, uriVariables,
          CreateCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateAddressResponse createCustomerAddress(UpdateAddressRequest request)
      throws Exception {
    try {
      String endpoint = CUSTOMER_CREATE_ADDRESS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient.postForEntity(endpoint, request, uriVariables,
          UpdateAddressResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetAttachmentResponse getAttachment(GetAttachmentRequest request) throws Exception {
    try {
      Map<String, String> uriVariables = null;
      String url = URI_CONSUMER_KYC_GET_ATTACHMENT;
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient.postForEntity(url, request, uriVariables, GetAttachmentResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetStoreS3UrlResponse getImageProfile(GetStoreS3UrlRequest request) throws Exception {
    try {
      String url = URI_CONSUMER_GET_IMAGE_PROFILE_URL;
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient.postForEntity(url, request, uriVariables, GetStoreS3UrlResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public FindEntityEventStoreByDateResponse findEventStore(FindEntityEventStoreByDateRequest request) throws Exception {
    try {
      Map<String, String> uriVariables = null;
      String url = URI_CONSUMER_FIND_EVENT_BY_DATE;
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient.postForEntity(url, request, uriVariables, FindEntityEventStoreByDateResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }


  }

  @Override
  public SaleGetListAgentResponse saleGetAgents(SaleGetListAgentRequest request) throws Exception {
    try {
      String endpoint = SALE_GET_AGENT_LIST;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, SaleGetListAgentResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateImageProfileCustomerResponse updateProfileImage(
      UpdateImageProfileCustomerRequest request) throws Exception {
    try {
      String endpoint = UPDATE_IMAGE_PROFILE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return authServerAPIClient
          .postForEntity(endpoint, request, uriVariables, UpdateImageProfileCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public FindBlackListCustomerResponse findBlockCustomer(FindBlackListCustomerRequest request)
      throws Exception {
    try {
      String endpoint = FIND_LIST_BLOCK_CUS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, FindBlackListCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public AddBlackListCustomerResponse addBlock(AddBlackListCustomerRequest request)
      throws Exception {
    try {
      String endpoint = ADD_BLOCK_CUS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, AddBlackListCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public RemoveBlackListCustomerResponse removeBlockProviderService(
      RemoveBlackListCustomerRequest request) throws Exception {
    try {
      String endpoint = REMOVE_BLOCK_CUS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, RemoveBlackListCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public FindProviderByBlockCustomerResponse findProviderByBlockCustomer(
      FindProviderByBlockCustomerRequest request) throws Exception {
    try {
      String endpoint = FIND_PROVIDER_BY_BLOCK_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, FindProviderByBlockCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public FindProviderServicesByBlockCustomerResponse findProviderServiceByBlockCustomer(
      FindProviderServicesByBlockCustomerRequest request) throws Exception {
    try {
      String endpoint = FIND_PROVIDER_SERVICE_BY_BLOCK_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, FindProviderServicesByBlockCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public FindTrueServiceByBlockCustomerResponse findTrueServiceByBlockCustomer(
      FindTrueServiceByBlockCustomerRequest request) throws Exception {
    try {
      String endpoint = FIND_TRUE_SERVICE_BY_BLOCK_CUSTOMER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      Map<String, String> uriVariables = null;
      return paymentOpServerAPIClient.postForEntity(endpoint, request, uriVariables, FindTrueServiceByBlockCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }


}
