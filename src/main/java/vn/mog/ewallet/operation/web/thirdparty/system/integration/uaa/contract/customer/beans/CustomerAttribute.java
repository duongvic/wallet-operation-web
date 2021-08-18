package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;

public class CustomerAttribute implements Serializable {

  public static final String EXPORT_FILE_PHONE = "EXPORT_FILE_PHONE";
  private static final long serialVersionUID = 1L;
  protected long customerId;
  protected String customerAttributeTypeId;
  protected String value;

  protected Date created;

  public long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public String getCustomerAttributeTypeId() {
    return this.customerAttributeTypeId;
  }

  public void setCustomerAttributeTypeId(String customerAttributeTypeId) {
    this.customerAttributeTypeId = customerAttributeTypeId;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }
}
