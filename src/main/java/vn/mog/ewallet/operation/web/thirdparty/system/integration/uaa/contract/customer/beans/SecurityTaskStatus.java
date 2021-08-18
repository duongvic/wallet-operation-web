package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

public enum SecurityTaskStatus {

  INCOMPLETE(0, "Incomplete"), COMPLETE(1, "Complete");

  private final String name;
  private final int code;

  SecurityTaskStatus(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static SecurityTaskStatus getSecurityTaskStatus(String label) {
    for (SecurityTaskStatus item : SecurityTaskStatus.values()) {
      if (item.name.equalsIgnoreCase(label) || String.valueOf(item.code).equalsIgnoreCase(label)) {
        return item;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public int getCode() {
    return code;
  }
}
