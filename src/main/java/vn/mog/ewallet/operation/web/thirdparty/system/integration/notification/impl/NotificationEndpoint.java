package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PARTNER_GATEWAY_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.INotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.NotificationDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class NotificationEndpoint implements INotificationEndpoint {

  @Autowired OAuth2RestTemplate restTemplate;

  @Value("${platform.backend.notification.service.3rd.url.notification-broadcast}")
  private String NOTIFICATION_BROADCAST;

  @Value("${platform.backend.notification.service.3rd.url.notification-find}")
  private String NOTIFICATION_FIND;

  @Value("${platform.backend.notification.service.3rd.url.partner.notification-add}")
  private String PARTNER_NOTIFICATION_ADD;

  @Value("${platform.backend.notification.service.3rd.url.partner.notification-query}")
  private String PARTNER_NOTIFICATION_QUERY;

  @Override
  public BroadcastNotificationResponse notificationBroadcast(BroadcastNotificationRequest request)
      throws Exception {
    try {
      String endpoint = PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT + NOTIFICATION_BROADCAST;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, BroadcastNotificationResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindNotificationResponse notificationFind(FindNotificationRequest request)
      throws Exception {
    try {
      String endpoint = PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT + NOTIFICATION_FIND;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindNotificationResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> postMessage(NotificationDTO request) throws Exception {
    try {
      String endpoint = PLATFORM_BACKEND_PARTNER_GATEWAY_API_ENDPOINT + PARTNER_NOTIFICATION_ADD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getMessages(FindPartnerNotificationRequest request)
      throws Exception {
    try {
      String endpoint = PLATFORM_BACKEND_PARTNER_GATEWAY_API_ENDPOINT + PARTNER_NOTIFICATION_QUERY;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
