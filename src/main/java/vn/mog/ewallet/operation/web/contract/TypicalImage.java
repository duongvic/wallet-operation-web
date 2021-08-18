package vn.mog.ewallet.operation.web.contract;

public enum TypicalImage {
  FRONT_FACE(0, "FRONT_FACE"),
  BACK_FACE(1, "BACK_FACE"),
  SELFIE(2, "SELFIE"),
  IMAGE_PROFILE(3, "IMAGE_PROFILE");

  public int code;
  public String name;


  TypicalImage(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static TypicalImage validTypical(int type) {
    for (TypicalImage item : TypicalImage.values()) {
      if (item.code == type) {
        return item;
      }
    }
    return null;
  }
}
