package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum TransactionCustomerType {
  CUSTOMER(1, "Customer"), AGENT(2, "Agent"), MERCHANT(3, "Merchant"), PROVIDER(11, "Provider"), PROPERTY_MANAGER(12, "Property Manager");

  private Integer code;
  private String displayText;

  TransactionCustomerType(Integer code, String displayText) {
    this.code = code;
    this.displayText = displayText;
  }

  public Integer getCode() {
    return code;
  }

  public String getDisplayText() {
    return displayText;
  }

  public static TransactionCustomerType getTransactionCustomerTypeByCode(Integer code) {
    if (code != null) {
      for (TransactionCustomerType transactionCustomerType : TransactionCustomerType.values()) {
        if (code.equals(transactionCustomerType.getCode())) {
          return transactionCustomerType;
        }
      }
    }

    return null;
  }
}
