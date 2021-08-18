package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum ServiceLevel {
  LEVEL_0(0, "Level 0"), LEVEL_1(1, "Level 1"), LEVEL_2(2, "Level 2"), LEVEL_3(3, "Level 3"), LEVEL_4(4, "Level 4");

  public Integer code;
  public String name;

  ServiceLevel(Integer code, String name) {
    this.code = code;
    this.name = name;
  }

  public static ServiceLevel getServiceLevel(Integer level) {
    for (ServiceLevel item : ServiceLevel.values()) {
      if (item.code.equals(level)) {
        return item;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public Integer getCode() {
    return code;
  }
}
