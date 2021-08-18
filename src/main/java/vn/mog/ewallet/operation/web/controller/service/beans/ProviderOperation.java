package vn.mog.ewallet.operation.web.controller.service.beans;


public class ProviderOperation {

  private String providerCode;
  private String providerName;
  private String providerStatus;

  private ProviderServiceOperation providerServiceOperation;

  public ProviderOperation() {
  }

  public ProviderOperation(String providerCode, String providerName, String providerStatus, ProviderServiceOperation providerServiceOperation) {
    this.providerCode = providerCode;
    this.providerName = providerName;
    this.providerStatus = providerStatus;
    this.providerServiceOperation = providerServiceOperation;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderStatus() {
    return providerStatus;
  }

  public void setProviderStatus(String providerStatus) {
    this.providerStatus = providerStatus;
  }

  public ProviderServiceOperation getProviderServiceOperation() {
    return providerServiceOperation;
  }

  public void setProviderServiceOperation(ProviderServiceOperation providerServiceOperation) {
    this.providerServiceOperation = providerServiceOperation;
  }
}
