package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.Notification;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindNotificationResponse extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private List<Notification> notifications;

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }
}
