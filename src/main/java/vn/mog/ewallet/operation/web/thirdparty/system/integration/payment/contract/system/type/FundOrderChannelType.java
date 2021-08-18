package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum FundOrderChannelType {

  BANK_TRANSFER("BANK_TRANSFER", "channel.bank.transfer"), LINK_BANK("LINK_BANK", "channel.link.bank"),
  /*
   * ONEPAY_OFFICE("ONEPAY_OFFICE", "Zo-Ta Office"),
   * PAYMENT_GATEWAY("PAYMENT_GATEWAY", "Payment Gateway"),
   */
  CASH_ON_HAND("CASH_ON_HAND", "channel.cash.on.hand");

  public String code;
  public String displayText;

  FundOrderChannelType(String value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static FundOrderChannelType getFundOrderChannel(String value) {
    for (FundOrderChannelType state : FundOrderChannelType.values()) {
      if (state.code.equalsIgnoreCase(value)) {
        return state;
      }
    }
    return null;
  }

  public static String getLangFundOrderChannel(String value) {
    for (FundOrderChannelType state : FundOrderChannelType.values()) {
      if (state.code.equalsIgnoreCase(value)) {
        return state.displayText;
      }
    }
    return null;
  }

  public String value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }
}
