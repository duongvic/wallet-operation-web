package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.ISpecialProviderKppViettelEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaEnterRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaEnterResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaGetRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountCapchaGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class SpecialProviderKppViettelEndpoint implements ISpecialProviderKppViettelEndpoint {

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-find}")
  private String URI_FIND_PROVIDER_SPECIAL_ACCOUNT;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-status-change}")
  private String URI_CHANGE_STATUS_PROVIDER_SPECIAL_ACCOUNT;

  //
  // @Value("${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-get}")
  //  private String URI_GET_PROVIDER_SPECIAL_ACCOUNT;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-add}")
  private String URI_ADD_PROVIDER_SPECIAL_ACCOUNT;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-update}")
  private String URI_UPDATE_PROVIDER_SPECIAL_ACCOUNT;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-delete}")
  private String URI_DELETE_PROVIDER_SPECIAL_ACCOUNT;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-capcha-get}")
  private String URI_PROVIDER_SPECIAL_ACCOUNT_CAPCHA_GET;

  @Value(
      "${platform.backend.internal.provider.service.3rd.url.ptu_kpp_viettel.provider-special-account-capcha-enter}")
  private String URI_PROVIDER_SPECIAL_ACCOUNT_CAPCHA_ENTER;
  // -----

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public SpecialProviderAllAccountFindResponse providerSpecialAccountFind(
      SpecialProviderAllAccountFindRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
      String endpoint =
          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_FIND_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SpecialProviderAllAccountFindResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAccountChangeStatusResponse providerSpecialAccoutChangeStatus(
      SpecialProviderAccountChangeStatusRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
      String endpoint =
          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT
              + URI_CHANGE_STATUS_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SpecialProviderAccountChangeStatusResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  //  @Override
  //  public SpecialProviderAccountGetResponse providerSpecialAccountGet(
  //      SpecialProviderAccountGetRequest request) throws FrontEndException {
  //    try {
  //      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
  //      String endpoint =
  //          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_GET_PROVIDER_SPECIAL_ACCOUNT;
  //      log.info("REQ-URL: {}", endpoint);
  //      log.info("REQ-BODY: {}", Utils.objectToJson(request));
  //      return restTemplate
  //          .postForEntity(endpoint, request, SpecialProviderAccountGetResponse.class)
  //          .getBody();
  //    } catch (Exception e) {
  //      log.error(e.getMessage(), e);
  //      throw e;
  //    }
  //  }

  @Override
  public SpecialProviderAccountChangeResponse providerSpecialAccountAdd(
      SpecialProviderAccountChangeRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
      String endpoint =
          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_ADD_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SpecialProviderAccountChangeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAccountChangeResponse providerSpecialAccountUpdate(
      SpecialProviderAccountChangeRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
      String endpoint =
          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_UPDATE_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SpecialProviderAccountChangeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAccountChangeResponse providerSpecialAccountDelete(
      SpecialProviderAccountChangeRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
      String endpoint =
          PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_DELETE_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SpecialProviderAccountChangeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAccountCapchaGetResponse providerSpecialAccountCapchaGet(
      SpecialProviderAccountCapchaGetRequest request) throws FrontEndException {
    request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
    String endpoint =
        PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_PROVIDER_SPECIAL_ACCOUNT_CAPCHA_GET;
    log.info("REQ-URL: {}", endpoint);
    log.info("REQ-BODY: {}", Utils.objectToJson(request));
    return restTemplate
        .postForEntity(endpoint, request, SpecialProviderAccountCapchaGetResponse.class)
        .getBody();
  }

  @Override
  public SpecialProviderAccountCapchaEnterResponse providerSpecialAccountCapchaEnter(
      SpecialProviderAccountCapchaEnterRequest request) throws FrontEndException {
    request.setProviderCode(ProviderCode.PTU_C1_VIETTEL_KPP);
    String endpoint =
        PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_PROVIDER_SPECIAL_ACCOUNT_CAPCHA_ENTER;
    log.info("REQ-URL: {}", endpoint);
    log.info("REQ-BODY: {}", Utils.objectToJson(request));
    return restTemplate
        .postForEntity(endpoint, request, SpecialProviderAccountCapchaEnterResponse.class)
        .getBody();
  }
}
