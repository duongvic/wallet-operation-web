package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KppViettelActionEnum {

  ADD("add", "Thêm"),
  UPDATE("update", "Cập nhật"),
  DELETE("delete", "Xóa");

  private String code;
  private String message;
}
