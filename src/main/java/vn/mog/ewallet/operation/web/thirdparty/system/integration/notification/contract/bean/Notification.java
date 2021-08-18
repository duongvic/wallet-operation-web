package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Notification implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long notificationId;

  private String sender;
  private String receiver;  // phoneNumber
  private Long receiverId;  // customerId
  private Integer receiverTypeId;   // customerTypeId
  private String notificationType;
  private String heading;
  private String content;
  private String detailContentUrl;
  private String language;
  private Map<String, String> data;

  private Date createdTime;
  private Date lastUpdatedTime;

  public Notification() {
    super();
  }

  public Long getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public Integer getReceiverTypeId() {
    return receiverTypeId;
  }

  public void setReceiverTypeId(Integer receiverTypeId) {
    this.receiverTypeId = receiverTypeId;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public String getHeading() {
    return heading;
  }

  public void setHeading(String heading) {
    this.heading = heading;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  public String getDetailContentUrl() {
    return detailContentUrl;
  }

  public void setDetailContentUrl(String detailContentUrl) {
    this.detailContentUrl = detailContentUrl;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(Date lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public Map<String, String> getData() {
    return data;
  }

  public void setData(Map<String, String> data) {
    this.data = data;
  }
}
