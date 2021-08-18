package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceTypeEnum {
  PHONE_TOPUP("PHONE_TOPUP", "label.phone_topup"),
  EPIN("EPIN", "label.epin"),
  BILL_PAYMENT("BILL_PAYMENT", "label.bill_payment"),
  ALL("ALL", "label.all_of_service");

  private String code;
  private String name;
}
