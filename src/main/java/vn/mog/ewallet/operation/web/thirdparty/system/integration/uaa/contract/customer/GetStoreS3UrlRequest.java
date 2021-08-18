package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

public class GetStoreS3UrlRequest extends GetStoreS3UrlRequestType {

  private Long customerId;
  private String cif;

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
}
