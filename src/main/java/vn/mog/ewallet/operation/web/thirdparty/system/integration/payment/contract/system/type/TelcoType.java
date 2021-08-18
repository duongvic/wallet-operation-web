package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum TelcoType {

  VTM("VTM", "VIETTEL"), VMS("VMS", "MOBIFONE"), VNP("VNP", "VINAPHONE"), VNM("VNM", "VNMOBILE"), GMOBILE("GMOBILE", "GMOBILE"), // TelCard

  VCOIN("VCOIN", "VCOIN"), GATE("GATE", "GATE"), ZING("ZING", "ZING"), GARENA("GARENA", "GARENA"), ONCASH("ONCASH", "ONCASH"), MGC("MGC",
      "MEGACARD"), BIT("BIT", "BIT"), VCARD("VCARD", "VCARD"), VINPLAY("VINPLAY", "VINPLAY"); // GameCard

  public String code;
  public String name;

  TelcoType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static TelcoType getTelcoType(String label) {
    for (TelcoType telcoType : TelcoType.values()) {
      if (telcoType.name().equalsIgnoreCase(label) || telcoType.code.equalsIgnoreCase(label) || telcoType.name.equalsIgnoreCase(label)) {
        return telcoType;
      }
    }
    return null;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

}
