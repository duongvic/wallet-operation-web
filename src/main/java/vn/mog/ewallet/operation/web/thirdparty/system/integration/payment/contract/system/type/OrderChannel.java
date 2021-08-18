package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum OrderChannel {

  API("API", "Api"),
  AGENT_SITE("AGENT_SITE", "Agent Site"),
  OPS_SITE("OPS_SITE", "Operation Site"),
  BANK_TRANSFER("channel.bank.transfer", "Bank Transfer"),
  LINK_BANK("LINK_BANK", "Link Bank"),
  ONEPAY_OFFICE("ONEPAY_OFFICE", "Zo-Ta Office"),
  BANK_GATEWAY("BANK_GATEWAY", "Bank Gateway"), // Gọi qua Bank Gateway
  PAYMENT_GATEWAY("PAYMENT_GATEWAY", "Payment Gateway"), // Gọi qua Payment Gateway
  CASH_ON_HAND("channel.cash.on.hand", "Cash On Hand");

  public String code;
  public String displayText;

  OrderChannel(String value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static OrderChannel getFundOrderChannel(String value) {
    for (OrderChannel state : OrderChannel.values()) {
      if (state.code.equalsIgnoreCase(value)) {
        return state;
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
