package vn.mog.ewallet.operation.web.controller.notification;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.Notification;
import vn.mog.framework.common.utils.Utils;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController extends AbstractAccountController {

  public static final String NOTIFICATION_BROADCAST = "/notification/broadcast";
  public static final String NOTIFICATION_FIND = "/notification/list";

  public static final String NOTIFICATION_TYPE = "NOTIFICATION_BROADCAST";
  public static final String DEFAULT_LANG = "en";

  private static final Logger LOG = LogManager.getLogger(NotificationController.class);

  @GetMapping(value = "/broadcast")
  public String notificationBroadCroast(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {

    map.put("heading", request.getParameter("paramHeading"));
    String content = request.getParameter("paramContent");
    if (content != null && content.length() > 250) {
      map.put("content", content.substring(0, 250));
    } else {
      map.put("content", content);
    }
    return "/notification/notification_broadcast";
  }

  @PostMapping(value = "/broadcast")
  public String notificationBroadCroastPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    BroadcastNotificationRequest broadCastReq = new BroadcastNotificationRequest();

    String title = request.getParameter("noti_title");
    String content = request.getParameter("content_input");
    Integer receiverTypeId = null;
    String userSendType = request.getParameter("user_send_type");
    if (StringUtils.isNotBlank(userSendType)) {
      receiverTypeId = Integer.valueOf(request.getParameter("user_send_type"));
    }

    Notification notification = new Notification();
    notification.setHeading(title);
    notification.setContent(content);
    notification.setLanguage(DEFAULT_LANG);
    notification.setReceiverTypeId(receiverTypeId);
    notification.setNotificationType(NOTIFICATION_TYPE);
    broadCastReq.setSendNoti(true);
    broadCastReq.setNeedSave(true);
    broadCastReq.setNotification(notification);

    try {
      BroadcastNotificationResponse broadCastRes =
          notificationEndpoint.notificationBroadcast(broadCastReq);

      if (broadCastRes == null || broadCastRes.getStatus() == null) {
        throw new Exception("Can not Update Customer");
      }
      if (0 != broadCastRes.getStatus().getCode()) {
        throw new Exception(broadCastRes.getStatus().getValue());
      }

      map.put("codeErr", MessageNotify.SUCCESS_CODE);
      map.put("mesErr", MessageNotify.SUCCESS_NAME);

    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      map.put("codeErr", ex.getMessage());
      map.put("mesErr", ex.getMessage());
    }
    return "/notification/notification_broadcast";
  }

  @GetMapping(value = "/list")
  public String notificationFind(
      HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    FindNotificationRequest findNotiReq = new FindNotificationRequest();
    FindNotificationResponse findNotiRes = null;

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    findNotiReq.setNotificationTypes(Arrays.asList(NOTIFICATION_TYPE));
    findNotiReq.setOffset(offset);
    findNotiReq.setLimit(limit);
    try {
      findNotiRes = notificationEndpoint.notificationFind(findNotiReq);

      if (findNotiRes.getNotifications() != null) {
        map.put("nofitications", findNotiRes.getNotifications());
      }
      map.put("offset", offset);
      map.put("pagesize", limit);
      map.put(
          "total",
          findNotiRes.getNotifications() != null ? findNotiRes.getNotifications().size() : 0);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/notification/list_notification";
  }
}
