package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type;

public enum BlackListReasonType {

  ACTIVE(0, "Active"),
  WRONG_PWOVER_TIMES(1, "3 times wrong MPIN / PW"),
  DEVICE_LOST(2, "Device lost"),
  PAYMENT_DISPUTE(3, "Payment dispute"),
  FRAUD_SUSPICION(4, "Fraud suspicion"),
  REGISTRATION_PENDING(5, "Registration Pending");

  public int code;
  public String displayText;

  BlackListReasonType(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static BlackListReasonType getBlackListReasonType(int value) {
    for (BlackListReasonType state : BlackListReasonType
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
