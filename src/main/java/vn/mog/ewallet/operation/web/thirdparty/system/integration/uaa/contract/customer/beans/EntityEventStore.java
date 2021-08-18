package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EntityEventStore implements Serializable {

  protected Long id;

  private String eventName;
  private String eventType;

  private String eventData;

  private String eventPublisher;

  private String eventChannel;

  private Date eventTimestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public String getEventData() {
    return eventData;
  }

  public void setEventData(String eventData) {
    this.eventData = eventData;
  }

  public String getEventPublisher() {
    return eventPublisher;
  }

  public void setEventPublisher(String eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public String getEventChannel() {
    return eventChannel;
  }

  public void setEventChannel(String eventChannel) {
    this.eventChannel = eventChannel;
  }

  public Date getEventTimestamp() {
    return eventTimestamp;
  }

  public void setEventTimestamp(Date eventTimestamp) {
    this.eventTimestamp = eventTimestamp;
  }
}
