package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

public class KeyPairItem {
  private String name;
  private String code;

  public KeyPairItem() {}

  public KeyPairItem(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
