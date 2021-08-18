package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.Arrays;
import java.util.List;

public enum ProviderGroup {
  EPIN_01("01-EPIN"),
  PHONE_TOPUP_02("02-PHONE_TOPUP"),
  EXPORT_EPIN_03("03-EXPORT_EPIN"),
  BILL_PAYMENT_04("04-BILL_PAYMENT"),
  UNDEFINED_05("05-UNDEFINED");

  public String code;

  public static List<ProviderGroup> PROVIDER_GROUPS = Arrays.asList(ProviderGroup.values());

  ProviderGroup(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
