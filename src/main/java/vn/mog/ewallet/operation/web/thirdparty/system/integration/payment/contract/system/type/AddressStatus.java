package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum AddressStatus {

  VALID_STATUS(0),
  NOT_VERIFIED_STATUS(1),
  INVALID_STATUS(2);

  private Integer id;

  AddressStatus(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
