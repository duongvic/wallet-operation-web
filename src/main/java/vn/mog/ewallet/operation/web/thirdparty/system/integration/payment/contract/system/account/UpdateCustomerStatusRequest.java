package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

@SuppressWarnings("serial")
public class UpdateCustomerStatusRequest extends UpdateCustomerStatusRequestType {

  private Long customerId;
  private String cif;
  private boolean active;

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
