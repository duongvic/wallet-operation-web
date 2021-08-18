package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.Notification;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class BroadcastNotificationRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private Notification notification;
  private Boolean sendNoti; // Sent Notification to Customer // default = true
  private Boolean needSave; // Need store on database

  public Notification getNotification() {
    return notification;
  }

  public void setNotification(Notification notification) {
    this.notification = notification;
  }

  public Boolean getSendNoti() {
    return sendNoti;
  }

  public void setSendNoti(Boolean sendNoti) {
    this.sendNoti = sendNoti;
  }

  public Boolean getNeedSave() {
    return needSave;
  }

  public void setNeedSave(Boolean needSave) {
    this.needSave = needSave;
  }
}
