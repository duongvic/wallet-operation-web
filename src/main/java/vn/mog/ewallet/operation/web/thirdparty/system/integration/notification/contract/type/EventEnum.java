package vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventEnum {
  MAINTAIN_START("MAINTAIN_START", "label.maintain_start"),
  MAINTAIN_STOP("MAINTAIN_STOP", "label.maintain_stop"),
  PROMOTION_START("PROMOTION_START", "label.promotion_start"),
  PROMOTION_STOP("PROMOTION_STOP", "label.promotion_stop");

  private String code;
  private String name;
}
