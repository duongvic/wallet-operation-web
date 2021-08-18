package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IProviderEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderServiceStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderServiceStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesMatchingWithTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesMatchingWithTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class ProviderEndpoint implements IProviderEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.provider-changeServiceStatus}")
  private String URI_UPDATE_STATUS_PROVIDER_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.provider-findProviders}")
  private String URI_FIND_PROVIDERS;

  @Value("${platform.backend.payment.service.3rd.url.provider-changeStatus}")
  private String URI_UPDATE_STATUS_PROVIDER;

  @Value("${platform.backend.payment.service.3rd.url.provider-updateProvider}")
  private String URI_UPDATE_PROVIDER;

  @Value("${platform.backend.payment.service.3rd.url.provider-getProvider}")
  private String URI_GET_PROVIDER;
  
  @Value("${platform.backend.payment.service.3rd.url.provider-getProviderServicesMatchingWithTrueService}")
  private String URI_GET_PROVIDER_SERVICES_MATCHING_SERVICES;
  
  @Value("${platform.backend.payment.service.3rd.url.provider-getProviderServicesOfProvider}")
  private String URI_GET_PROVIDER_SERVICES_OF_PROVIDER;
  
  @Value("${platform.backend.payment.service.3rd.url.provider-updateProviderServiceRankingScore}")
  private String URI_UPDATE_RANKING_SCORE_OF_PROVIDER_SERVICE;
  
  @Value("${platform.backend.payment.service.3rd.url.provider-resetProviderServiceRankingScore}")
  private String URI_RESET_RANKING_SCORE_OF_PROVIDER_SERVICE;

  @Value("${platform.backend.payment.service.3rd.url.provider-provider-profile-find}")
  private String URI_FIND_PROVIDER_PROFILES;

  @Value("${platform.backend.payment.service.3rd.url.provider-fundIn}")
  private String URI_FUNDIN_PROVIDER;

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public FindProviderResponse findProviders(FindProviderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_PROVIDERS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindProviderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindProviderProfileResponse findProviderProfiles(FindProviderProfileRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_PROVIDER_PROFILES;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, FindProviderProfileResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ChangeProviderStatusResponse changeProviderStatus(ChangeProviderStatusRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_STATUS_PROVIDER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ChangeProviderStatusResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdateProviderResponse updateProvider(UpdateProviderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_PROVIDER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, UpdateProviderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetProviderResponse getProvider(GetProviderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_PROVIDER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetProviderResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
  
  @Override
  public GetProviderServicesMatchingWithTrueServiceResponse getProviderServicesMatchingWithTrueService(
      GetProviderServicesMatchingWithTrueServiceRequest request) throws FrontEndException {
    // TODO Auto-generated method stub
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_PROVIDER_SERVICES_MATCHING_SERVICES;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetProviderServicesMatchingWithTrueServiceResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ChangeProviderServiceStatusResponse changeProviderServiceStatus(
      ChangeProviderServiceStatusRequest request) throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_STATUS_PROVIDER_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ChangeProviderServiceStatusResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
  
  @Override
  public GetProviderServicesByProviderCodeResponse getProviderServicesOfProvider(
      GetProviderServicesByProviderCodeRequest request) throws FrontEndException {
    // TODO Auto-generated method stub
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_PROVIDER_SERVICES_OF_PROVIDER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetProviderServicesByProviderCodeResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
  
  @Override
  public UpdateProviderServiceRankingScoreResponse updateProviderServiceRankingScore(
      UpdateProviderServiceRankingScoreRequest request) throws FrontEndException {
    // TODO Auto-generated method stub
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_RANKING_SCORE_OF_PROVIDER_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdateProviderServiceRankingScoreResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
  
  @Override
  public ResetProviderServiceRankingScoreResponse resetProviderServiceRankingScore(
      ResetProviderServiceRankingScoreRequest request) throws FrontEndException {
    // TODO Auto-generated method stub
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_RESET_RANKING_SCORE_OF_PROVIDER_SERVICE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ResetProviderServiceRankingScoreResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FundInProviderResponse fundInProvider(FundInProviderRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FUNDIN_PROVIDER;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FundInProviderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
