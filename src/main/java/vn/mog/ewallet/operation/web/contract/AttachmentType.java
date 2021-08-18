package vn.mog.ewallet.operation.web.contract;

import java.util.ArrayList;
import java.util.List;

public enum AttachmentType {
  Contract(0, "Scanned contract"),
  Invoice(1, "Invoice"),
  ScannedRegistrationForm(2, "Scanned registration form"),
  ScannedPassport(3, "Scanned passport"),
  ScannedThumbprints(4, "Scanned thumbprints"),
  Avatar(5, "Avatar"),
  Business(6, "Business"),
  Kyc(7, "Kyc"),
  Device(8, "Device"),
  ScannedIdentityCard(9, "Scanned Identity Card"),
  ScannedCitizenCard(10, "Scanned Citizen Card"),
  ImageProfile(11, "Image profile"),
  ;
  public String name;
  public int code;

  public static final List<Integer> ATTACHMENT_TYPE_IDENTITYS = new ArrayList<>();
  static {
    ATTACHMENT_TYPE_IDENTITYS.add(AttachmentType.ScannedIdentityCard.code);
    ATTACHMENT_TYPE_IDENTITYS.add(AttachmentType.ScannedCitizenCard.code);
    ATTACHMENT_TYPE_IDENTITYS.add(AttachmentType.ScannedPassport.code);
  }



  AttachmentType(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getCode() {
    return code;
  }
}
