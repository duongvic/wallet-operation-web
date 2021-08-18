package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserRequestType;

import java.util.List;


public class SummaryBalanceRequestType extends MobiliserRequestType {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  protected List<Integer> customerTypeIds;
  
  public List<Integer> getCustomerTypeIds() {
    return customerTypeIds;
  }
  
  public void setCustomerTypeIds(List<Integer> customerTypeIds) {
    this.customerTypeIds = customerTypeIds;
  }

}
