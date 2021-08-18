package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

public enum ProfileType {
  CONSUMER("consumer"),
  SYSTEM_USER("system-user");

  public String name;

  ProfileType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
