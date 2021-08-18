package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.NotificationDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;

public interface INotificationEndpoint {

  BroadcastNotificationResponse notificationBroadcast(BroadcastNotificationRequest request)
      throws Exception;

  FindNotificationResponse notificationFind(FindNotificationRequest request) throws Exception;

  GeneralResponse<Object> postMessage(NotificationDTO request) throws Exception;

  GeneralResponse<Object> getMessages(FindPartnerNotificationRequest request) throws Exception;
}
