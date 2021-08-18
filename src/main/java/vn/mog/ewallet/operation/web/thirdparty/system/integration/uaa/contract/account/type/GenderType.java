package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type;

public enum GenderType {
  MALE(0, "MALE"), FEMALE(1, "FEMALE"), OTHER(2, "OTHER");

  public String name;
  public int code;

  GenderType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static GenderType getGenderType(String label) {
    for (GenderType genderType : GenderType.values()) {
      if (genderType.name.equalsIgnoreCase(label) || String.valueOf(genderType.code).equals(label)) {
        return genderType;
      }
    }
    return null;
  }
}
