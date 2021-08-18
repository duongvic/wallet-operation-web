package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;


import vn.mog.framework.common.utils.NumberUtil;


public enum FaceValueType {
  TEN(10000, "10.000"), TWENTY(20000, "20.000"), THIRTY(30000, "30.000"), FIFTY(50000, "50.000"), HUNDRED(100000, "100.000"), TWO_HUNDRED(200000,
      "200.000"), THREE_HUNDRED(300000, "300.000"), FIVE_HUNDRED(500000, "500.000");

  public int code;
  public String name;

  FaceValueType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static boolean existFaceValue(int label) {
    for (FaceValueType item : FaceValueType.values()) {
      if ((item.getCode() == label)) {
        return true;
      }
    }
    return false;
  }

  public static FaceValueType getFaceValueType(String label) {
    for (FaceValueType item : FaceValueType.values()) {
      if (item.getCode() == NumberUtil.convertToInt(label)) {
        return item;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public Integer getCode() {
    return code;
  }
}
