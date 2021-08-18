package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.Date;

@SuppressWarnings("serial")
public class FindEntityEventStoreByDateRequest extends FindEntityEventStoreByDateRequestType {

  private Long customerId;
  private Date eventDate;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

}
