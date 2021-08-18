package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

public enum KycRequestStatus {

  INIT(0, "Init"),
  SELF_CANCEL(1, "Self Cancel"),
  WAITING_APPROVEMENT(2, "Waiting for Approvement"),
  STAFF_REJECTED(3, "Staff Reject"),
  APPROVED(4, "Apporved");

  private int code;
  private String name;

  KycRequestStatus(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static KycRequestStatus getKycRequestStatus(String label) {
    for (KycRequestStatus item : KycRequestStatus.values()) {
      if (item.name.equalsIgnoreCase(label) || String.valueOf(item.code).equalsIgnoreCase(label)) {
        return item;
      }
    }
    return null;
  }


  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
