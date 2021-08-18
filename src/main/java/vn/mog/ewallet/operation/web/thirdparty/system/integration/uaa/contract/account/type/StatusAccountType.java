package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type;

public enum StatusAccountType {

  ACTIVE(0, "Active"),
  INACTIVE(1, "Inactive");

  public int code;
  public String displayText;

  StatusAccountType(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static StatusAccountType getStatusAccountType(int value) {
    for (StatusAccountType state : StatusAccountType
        .values()) {
      if (state.code == value) {
        return state;
      }
    }
    return null;
  }

  public int value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }
}
