package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.io.Serializable;

public class UpdateAddressResponse extends UpdateAddressResponseType implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long addressId;

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }
}

