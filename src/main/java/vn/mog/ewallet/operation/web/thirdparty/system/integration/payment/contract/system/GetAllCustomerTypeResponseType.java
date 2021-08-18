package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetAllCustomerTypeResponseType extends MobiliserResponseType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  protected List<CustomerType> customerTypes;

  public List<CustomerType> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<CustomerType> customerTypes) {
    this.customerTypes = customerTypes;
  }

}
