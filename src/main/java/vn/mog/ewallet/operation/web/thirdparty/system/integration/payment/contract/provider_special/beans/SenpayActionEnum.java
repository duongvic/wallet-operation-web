package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

public enum SenpayActionEnum {

  ADD("add", "Thêm"),
  UPDATE("update", "Cập nhật"),
  DELETE("delete", "Xóa");

  private String code;
  private String message;

  private SenpayActionEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
