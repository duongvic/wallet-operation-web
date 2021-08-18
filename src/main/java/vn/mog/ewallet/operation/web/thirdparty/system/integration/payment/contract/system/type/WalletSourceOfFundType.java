package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

/*
 * Backend had not return yet, so temporary get from OrderChannel
 */
public enum WalletSourceOfFundType {

  BANK_TRANSFER("BANK_TRANSFER", "channel.bank.transfer"),
  PAYMENT_GATEWAY("PAYMENT_GATEWAY", "channel.payment.gateway"),
  CASH_ON_HAND("CASH_ON_HAND", "channel.cash.on.hand");

  private String code;
  private String displayText;

  WalletSourceOfFundType(String value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static WalletSourceOfFundType getWalletSourceOfFund(String value) {
    for (WalletSourceOfFundType state : WalletSourceOfFundType.values()) {
      if (state.code.equalsIgnoreCase(value)) {
        return state;
      }
    }
    return null;
  }

  public String getCode() {
    return code;
  }

  public String getDisplayText() {
    return displayText;
  }
}
