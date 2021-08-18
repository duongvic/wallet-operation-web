package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.DataServiceAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IReconciliationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.PricingDTO;
import vn.mog.framework.common.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT;

@Slf4j
@Service
public class ReconciliationEndpoint implements IReconciliationEndpoint {

  @Value("${platform.backend.zpay.data.service.3rd.url.searchReconciliation}")
  private String URI_FIND_RECONCILIATIONS;

  @Value("${platform.backend.zpay.data.service.3rd.url.getReconciliation}")
  private String URI_GET_RECONCILIATION;

  @Value("${platform.backend.zpay.data.service.3rd.url.exportExcelReconciliation}")
  private String URI_EXPORT_EXCEL_RECONCILIATION;

  @Value("${platform.backend.zpay.data.service.3rd.url.updateReconciliation}")
  private String URI_UPDATE_RECONCILIATION;

  @Value("${platform.backend.zpay.data.service.3rd.url.generateReconciliationByOps}")
  private String URI_GENERATE_RECONCILIATION_BY_OPS;

  @Value("${platform.backend.zpay.data.service.3rd.url.deleteReconciliation}")
  private String URI_DELETE_RECONCILIATION;

  @Value("${platform.backend.zpay.data.service.3rd.url.actionWorkflowReconciliation}")
  private String URI_ACTION_WORKFLOW_RECONCILIATION;

  @Value("${platform.backend.zpay.data.service.3rd.url.getProfile}")
  private String URI_GET_PROFILE;

  @Value("${platform.backend.zpay.data.service.3rd.url.getProfileById}")
  private String URI_GET_PROFILE_BY_ID;

  @Value("${platform.backend.zpay.data.service.3rd.url.searchProfile}")
  private String URI_SEARCH_PROFILE;

  @Value("${platform.backend.zpay.data.service.3rd.url.getPricingService}")
  private String URI_GET_PRICING;

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Autowired
  DataServiceAPIClient dataServiceAPIClient;

  @Override
  public GeneralResponse<Object> findReconciliations(SearchReconciliationForm request) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_FIND_RECONCILIATIONS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> changeWorkflowReconciliations(WorkflowActionRequest request) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_ACTION_WORKFLOW_RECONCILIATION;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      GeneralResponse<Object> res = restTemplate
              .postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
      log.info("RES-BODY: {}", Utils.objectToJson(res));
      return res;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getReconciliation(Long id) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_GET_RECONCILIATION;
      Map<String, Long> uriVariables = new HashMap<>();
      uriVariables.put("id", id);
      log.info("REQ-URL: {}", endpoint);
      return restTemplate
              .getForEntity(endpoint, GeneralResponse.class, uriVariables)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> exportExcelReconciliation(Long id) throws FrontEndException {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_EXPORT_EXCEL_RECONCILIATION;
      Map<String, Long> uriVariables = new HashMap<>();
      uriVariables.put("id", id);
      log.info("REQ-URL: {}", endpoint);
      GeneralResponse<Object> res = restTemplate
              .getForEntity(endpoint, GeneralResponse.class, uriVariables)
              .getBody();
      log.info("RES-BODY: {}", Utils.objectToJson(res));
      return res;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> deleteReconciliation(Long id) throws FrontEndException {
    try {
      String endpoint = URI_DELETE_RECONCILIATION;
      log.info("REQ-URL: {}", endpoint);

      Map<String, String> uriVariables = new HashMap<>();
      uriVariables.put("id", String.valueOf(id));

      GeneralResponse<Object> res = dataServiceAPIClient.deleteForEntity(endpoint, uriVariables, GeneralResponse.class);
      log.info("RES-BODY: {}", Utils.objectToJson(res));
      return res;
    }catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> updateReconciliation(ReconciliationForm request) throws Exception {
    try {
      String endpoint = URI_UPDATE_RECONCILIATION;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));

      GeneralResponse<Object> res = dataServiceAPIClient
              .putForEntity(endpoint, request, GeneralResponse.class);
      log.info("RES-BODY: {}", Utils.objectToJson(res));
      return res;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> generateReconciliationByOps(GenerateReconciliationRequest request) throws Exception {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_GENERATE_RECONCILIATION_BY_OPS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      GeneralResponse<Object> res =  restTemplate
              .postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
      log.info("RES-BODY: {}", Utils.objectToJson(res));
      return res;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getProfile() throws Exception {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_GET_PROFILE;
      log.info("REQ-URL: {}", endpoint);
      return restTemplate
              .getForEntity(endpoint, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getProfileById(Long id) throws Exception {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_GET_PROFILE_BY_ID;
      log.info("REQ-URL: {}", endpoint);

      Map<String, Long> uriVariables = new HashMap<>();
      uriVariables.put("id", id);

      return restTemplate
              .getForEntity(endpoint, GeneralResponse.class, uriVariables)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> searchProfile(SearchProfileForm request) throws Exception {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_SEARCH_PROFILE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public PricingDTO searchPricing(ServicePricingForm request) throws Exception {
    try {
      String endpoint =
              PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + URI_GET_PRICING;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
              .postForEntity(endpoint, request, PricingDTO.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
