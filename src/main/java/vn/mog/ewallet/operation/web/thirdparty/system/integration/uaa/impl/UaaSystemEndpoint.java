package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl.NotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaSystemEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllOccupationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllOccupationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class UaaSystemEndpoint implements IUaaSystemEndpoint, InitializingBean {

  @Value("${platform.backend.uaa.service.3rd.url.system-getLocation}")
  private String URI_SYSTEM_GET_LOCATION;

  @Value("${platform.backend.uaa.service.3rd.url.system-occupations}")
  private String URI_SYSTEM_OCCUPATIONS;

  @Value("${platform.backend.uaa.service.3rd.url.system-positions}")
  private String URI_SYSTEM_POSITIONS;

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.restTemplate == null) {
      throw new IllegalStateException("OAuth2RestTemplate is required");
    }
  }

  @Override
  public FindLocationResponse getLocation(FindLocationRequest request) {
    String url =
        SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_SYSTEM_GET_LOCATION;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, FindLocationResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetAllOccupationResponse getAllOccupations(GetAllOccupationRequest request) {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_SYSTEM_OCCUPATIONS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, GetAllOccupationResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetAllPositionResponse getAllPositions(GetAllPositionRequest request) {
    String url = SharedConstants.PLATFORM_BACKEND_UAA_SERVICE_API_ENDPOINT + URI_SYSTEM_POSITIONS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(url, request, GetAllPositionResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
