package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetAllCustomerTypeRequestType extends MobiliserRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private List<Integer> customerTypeIds;

  public List<Integer> getCustomerTypeIds() {
    return customerTypeIds;
  }

  public void setCustomerTypeIds(List<Integer> customerTypeIds) {
    this.customerTypeIds = customerTypeIds;
  }


}
