package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum CardType {

  // TelCard
  VTM("VTM", "VIETTEL"),
  VMS("VMS", "MOBIFONE"),
  VNP("VNP", "VINAPHONE"),
  VNM("VNM", "VNMOBILE"),
  GMOBILE("GMOBILE", "GMOBILE"),
  BEELINE("BEELINE","BEELINE"),

  // GameCard
  GATE("GATE", "GATE"),
  VCOIN("VCOIN", "VCOIN"),
  ZING("ZING", "ZING"),
  GARENA("GARENA", "GARENA"),
  ONCASH("ONCASH", "ONCASH"),
  BIT("BIT", "BIT"),
  VCARD("VCARD", "VCARD"),
  VINPLAY("VINPLAY", "VINPLAY"),
  ANPAY("ANPAY", "ANPAY"),
  MGC("MGC", "MEGACARD"),
  APPOTA("APPOTA","APPOTA"),
  SOHA("SOHA","SOHA"),
  FUNCARD("FUNCARD","FUNCARD"),
  SOFTNYX("SOFTNYX","SOFTNYX"),
  SCOIN("SCOIN","SCOIN"),


  // Data Card
  DT_VNP("DT_VNP", "DATA_VINAPHONE"),
  DT_VMS("DT_VMS", "DATA_MOBIFONE"),
  DT_VTM("DT_VTM", "DATA_VIETTEL");


  public String code;
  public String name;

  CardType(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static CardType getCardType(String label) {
    for (CardType cardType : CardType.values()) {
      if (cardType.name().equalsIgnoreCase(label) || cardType.code.equalsIgnoreCase(label) || cardType.name.equalsIgnoreCase(label)) {
        return cardType;
      }
    }
    return null;
  }

  // @Override
  /*
   * public String toString() { return name; }
   */

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}
