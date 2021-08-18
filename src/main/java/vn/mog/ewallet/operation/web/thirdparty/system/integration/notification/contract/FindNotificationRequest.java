package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindNotificationRequest extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private String receiver; // (M)
  private Integer receiverTypeId; 
  private List<String> notificationTypes;
  private Date fromDate;
  private Date toDate;
  private Integer offset; // (M)
  private Integer limit; // (M)

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public Integer getReceiverTypeId() {
    return receiverTypeId;
  }

  public void setReceiverTypeId(Integer receiverTypeId) {
    this.receiverTypeId = receiverTypeId;
  }

  public List<String> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<String> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }
}
