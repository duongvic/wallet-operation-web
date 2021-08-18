package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;


public class CreateCustomerResponse extends CreateCustomerResponseType {

  protected Long customerId;
  protected String cif;

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }
}
