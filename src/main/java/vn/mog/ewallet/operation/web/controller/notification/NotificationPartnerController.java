package vn.mog.ewallet.operation.web.controller.notification;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindPartnerNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindPartnerNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.EventEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.TargetEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.framework.common.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/notification-partner")
public class NotificationPartnerController extends AbstractAccountController {
  public static final String NOTIFICATION_BROADCAST = "/notification-partner/broadcast";
  public static final String NOTIFICATION_FIND = "/notification-partner/list";

  public static final String NOTIFICATION_TYPE = "NOTIFICATION_BROADCAST";
  public static final String DEFAULT_LANG = "en";

  private static final Logger LOG = LogManager.getLogger(NotificationPartnerController.class);

  @GetMapping(value = "/broadcast")
  public String notificationBroadCroast(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    List<ServiceTypeEnum> serviceTypeEnumList =
        EnumSet.allOf(ServiceTypeEnum.class).stream().collect(Collectors.toList());
    List<TargetEnum> targetEnumList =
        EnumSet.allOf(TargetEnum.class).stream().collect(Collectors.toList());
    List<EventEnum> eventEnumList =
        EnumSet.allOf(EventEnum.class).stream().collect(Collectors.toList());
    List<ServiceEnum> serviceEnumList =
        EnumSet.allOf(ServiceEnum.class).stream().collect(Collectors.toList());
    map.put("serviceTypeEnumList", serviceTypeEnumList);
    map.put("targetEnumList", targetEnumList);
    map.put("eventEnumList", eventEnumList);
    map.put("serviceEnumList", serviceEnumList);
    return "/notification_partner/notification_broadcast";
  }

  @GetMapping(value = "/broadcast/update/{requestId}")
  public String notificationBroadCroastUpdate(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("requestId") String requestId) {
    List<ServiceTypeEnum> serviceTypeEnumList =
        EnumSet.allOf(ServiceTypeEnum.class).stream().collect(Collectors.toList());
    List<TargetEnum> targetEnumList =
        EnumSet.allOf(TargetEnum.class).stream().collect(Collectors.toList());
    List<EventEnum> eventEnumList =
        EnumSet.allOf(EventEnum.class).stream().collect(Collectors.toList());
    List<ServiceEnum> serviceEnumList =
        EnumSet.allOf(ServiceEnum.class).stream().collect(Collectors.toList());
    map.put("serviceTypeEnumList", serviceTypeEnumList);
    map.put("targetEnumList", targetEnumList);
    map.put("eventEnumList", eventEnumList);
    map.put("serviceEnumList", serviceEnumList);
    map.put("refRequestId", requestId);

    if (requestId != null && !requestId.isEmpty()) {
      try {
        FindPartnerNotificationRequest findNotiReq = new FindPartnerNotificationRequest();
        findNotiReq.setRequestId(requestId);
        GeneralResponse<Object> res = notificationEndpoint.getMessages(findNotiReq);
        FindPartnerNotificationResponse findPartnerNotificationResponse =
            Utils.responseToObject(res.getData(), FindPartnerNotificationResponse.class);
        map.put("notifications", findPartnerNotificationResponse.getNotificationDTOs());
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
      }
    }
    return "/notification_partner/broadcast_update";
  }

  @GetMapping(value = "/broadcast/detail/{requestId}")
  public String notificationBroadCroastDetail(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("requestId") String requestId) {
    if (requestId != null && !requestId.isEmpty()) {
      try {
        FindPartnerNotificationRequest findNotiReq = new FindPartnerNotificationRequest();
        findNotiReq.setRequestId(requestId);
        GeneralResponse<Object> res = notificationEndpoint.getMessages(findNotiReq);
        FindPartnerNotificationResponse findPartnerNotificationResponse =
            Utils.responseToObject(res.getData(), FindPartnerNotificationResponse.class);
        map.put("notification", findPartnerNotificationResponse.getNotificationDTOs().get(0));
      } catch (Exception e) {
        LOG.error(e.getMessage(), e);
      }
    }
    return "/notification_partner/detail";
  }

  @PostMapping(value = "/broadcast")
  public String notificationBroadCroastPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    try {

    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/notification_partner/notification_broadcast";
  }

  @GetMapping(value = "/list")
  public String notificationFind(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    try {
      List<EventEnum> eventEnumList =
          EnumSet.allOf(EventEnum.class).stream().collect(Collectors.toList());
      map.put("eventEnumList", eventEnumList);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/notification_partner/list_notification";
  }
}
