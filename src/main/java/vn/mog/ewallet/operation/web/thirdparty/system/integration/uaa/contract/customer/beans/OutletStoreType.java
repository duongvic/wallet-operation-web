package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

public enum OutletStoreType {

  GROCERY("GROCERY","Tạp hóa"),
  SUPERMARKET("SUPERMARKET","Siêu thị"),
  ELECTRONIC_SUPERMARKET("ELECTRONIC_SUPERMARKET","Siêu thị điện máy"),
  BOOKSTORE("BOOKSTORE","Hiệu sách"),
  DRUGSTORE("DRUGSTORE","Hiệu thuốc"),
  CONVENIENCE_STORE("CONVENIENCE_STORE","Cửa hàng tiện lợi"),
  PHONE_SHOP("PHONE_SHOP","Cửa hàng điện thoại"),
  FURNITURE_SHOP("FURNITURE_SHOP","Cửa hàng nội thất"),
  OTHER("OTHER","Khác");

  public String code;
  public String displayText;

  OutletStoreType(String code, String displayText) {
    this.code = code;
    this.displayText = displayText;
  }

  public static OutletStoreType getOutLetStoreType(String code) {
    for (OutletStoreType item : OutletStoreType.values()) {
      if (item.code.equalsIgnoreCase(code)) {
        return item;
      }
    }
    return null;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDisplayText() {
    return displayText;
  }

  public void setDisplayText(String displayText) {
    this.displayText = displayText;
  }
}
