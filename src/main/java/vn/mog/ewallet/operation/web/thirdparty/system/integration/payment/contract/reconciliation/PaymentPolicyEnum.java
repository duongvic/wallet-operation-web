package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

public enum PaymentPolicyEnum {
  PAYMENT_POLICY_1_1,
  PAYMENT_POLICY_7_1,
  PAYMENT_POLICY_15_1,
  PAYMENT_POLICY_30_1,
  ;

  public static String toShortName(PaymentPolicyEnum paymentPolicyEnum) {
    if (PAYMENT_POLICY_1_1.equals(paymentPolicyEnum)) {
      return "T1+1";
    }
    if (PAYMENT_POLICY_7_1.equals(paymentPolicyEnum)) {
      return "T7+1";
    }
    if (PAYMENT_POLICY_15_1.equals(paymentPolicyEnum)) {
      return "T15+1";
    }
    if (PAYMENT_POLICY_30_1.equals(paymentPolicyEnum)) {
      return "T30+1";
    }
    return paymentPolicyEnum.name();
  }
}
