package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;


public enum AddressType {
  POSTAL_AND_DELIVERY(0), DELIVERY_ONLY(1), OUTLET_ADDRESS(2), THIRD_PARTY_ADDRESS(3),
  SP_TECHNICAL_CONTACT(4), SP_OPERATIONS_CONTACT(5), BUSINESS_CONTACT(6), LIVING_CONTACT(7),
  RESIDENCE_ADDRESS(8);

  private Integer id;

  AddressType(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}

