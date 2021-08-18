package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IBillSenpayToolEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.common.utils.Utils;

import java.util.Collections;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT;

@Slf4j
@Service
public class BillSenpayToolEndpoint implements IBillSenpayToolEndpoint {

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-find}")
  private String URI_FIND_PROVIDER_SPECIAL_ACCOUNT;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-status-change}")
  private String URI_CHANGE_STATUS_PROVIDER_SPECIAL_ACCOUNT;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-get}")
  private String URI_GET_PROVIDER_SPECIAL_ACCOUNT;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-add}")
  private String URI_ADD_PROVIDER_SPECIAL_ACCOUNT;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-update}")
  private String URI_UPDATE_PROVIDER_SPECIAL_ACCOUNT;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-account-delete}")
  private String URI_DELETE_PROVIDER_SPECIAL_ACCOUNT;
  // -----

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-province}")
  private String URI_PROVINCE_GET;

  @Value("${platform.backend.internal.provider.service.3rd.url.bill_sen_tool.provider-special-province-change}")
  private String URI_PROVINCE_CHANGE;

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Override
  public SpecialProviderAllAccountFindResponse providerSpecialAccountFind(
          SpecialProviderAllAccountFindRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
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
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
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

  @Override
  public SpecialProviderAccountGetResponse providerSpecialAccountGet(
          SpecialProviderAccountGetRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
      String endpoint =
              PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_GET_PROVIDER_SPECIAL_ACCOUNT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, SpecialProviderAccountGetResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAccountChangeResponse providerSpecialAccountAdd(
          SpecialProviderAccountChangeRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
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
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
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
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
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
  public SpecialProviderAllowedProvinceGetResponse allowedProvinceGet() throws FrontEndException {
    try {
      String endpoint = PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_PROVINCE_GET;
      log.info("REQ-URL: {}", endpoint);
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
      headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
      HttpEntity entityReq = new HttpEntity(null, headers);
      return restTemplate
              .exchange(
                      endpoint, HttpMethod.GET, entityReq, SpecialProviderAllowedProvinceGetResponse.class)
              .getBody();
      //          .getForEntity(endpoint, SpecialProviderAllowedProvinceGetResponse.class)
      //          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SpecialProviderAllowedProvinceChangeResponse allowedProvinceChange(
          SpecialProviderAllowedProviderChangeRequest request) throws FrontEndException {
    try {
      request.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);
      String endpoint = PLATFORM_INTERNAL_PROVIDER_SERVICE_API_ENDPOINT + URI_PROVINCE_CHANGE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, SpecialProviderAllowedProvinceChangeResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
