package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;

public class UpdateAddressRequest extends UpdateAddressRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Address address;

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address value) {
    this.address = value;
  }

}
