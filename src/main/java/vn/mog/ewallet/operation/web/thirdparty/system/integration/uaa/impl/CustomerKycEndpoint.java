package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl.NotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.ICustomerKycEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestCreateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestCreateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class CustomerKycEndpoint implements ICustomerKycEndpoint {

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-create-request-kyc}")
  private String URI_CONSUMER_KYC_CREATE_REQUEST;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-find-list-kyc-request}")
  private String URI_CONSUMER_KYC_FIND_LIST;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-update-process-kyc}")
  private String URI_CONSUMER_KYC_UPDATE_PROCESS;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-enterprise-kyc-update-process}")
  private String URI_CONSUMER_KYC_ENTERPRISE_UPDATE_PROCESS;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-view-request-detail}")
  private String URI_CONSUMER_KYC_VIEW_REQUEST_DETAIL;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-update-identity}")
  private String URI_CONSUMER_KYC_UPDATE_IDENTITY;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-kyc-update-kyc-request}")
  private String URI_CONSUMER_KYC_UPDATE_KYC_REQUEST;

  @Value("${platform.backend.uaa.service.3rd.url.consumer-enterprise-kyc-update}")
  private String URI_CONSUMER_KYC_ENTERPRISE_UPDATE_KYC_REQUEST;

  @Override
  public VerifyKycRequestCreateResponse createVerifyKycRequest(VerifyKycRequestCreateRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_CREATE_REQUEST;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestCreateResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public VerifyKycRequestFindResponse findVerifyKycRequest(VerifyKycRequestFindRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_FIND_LIST;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestFindResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public VerifyKycRequestStatusUpdateResponse updateVerifyKycRequestStatus(VerifyKycRequestStatusUpdateRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_UPDATE_PROCESS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestStatusUpdateResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public VerifyKycRequestStatusUpdateResponse updateVerifyKycEnterpriseRequestStatus(
      VerifyKycRequestStatusUpdateRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT
        + URI_CONSUMER_KYC_ENTERPRISE_UPDATE_PROCESS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestStatusUpdateResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public VerifyKycRequestGetDetailResponse getVerifyKycRequestGetDetail(VerifyKycRequestGetDetailRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_VIEW_REQUEST_DETAIL;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestGetDetailResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }


  @Override
  public UpdateIdentityResponse updateIdentity(UpdateIdentityRequest request) throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_UPDATE_IDENTITY;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, UpdateIdentityResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public VerifyKycRequestChangeResponse updateKYC(VerifyKycRequestChangeRequest request)
      throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_CONSUMER_KYC_UPDATE_KYC_REQUEST;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestChangeResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public VerifyKycRequestChangeResponse updateEnterpriseKYC(VerifyKycRequestChangeRequest request)
      throws FrontEndException {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT
        + URI_CONSUMER_KYC_ENTERPRISE_UPDATE_KYC_REQUEST;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, VerifyKycRequestChangeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
