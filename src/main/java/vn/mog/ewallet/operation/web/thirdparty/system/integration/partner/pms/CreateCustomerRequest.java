package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.Customer;

public class CreateCustomerRequest {
    private String accessKey;
    private String sign;
    private Customer customer;
    private Long timestamp;

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(
      Customer customer) {
    this.customer = customer;
  }
}
