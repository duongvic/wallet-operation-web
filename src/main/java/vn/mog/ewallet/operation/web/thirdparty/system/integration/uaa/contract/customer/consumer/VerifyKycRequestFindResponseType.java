package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerVerifyKycRequest;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class VerifyKycRequestFindResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<CustomerVerifyKycRequest> customerVerifyRequests;
  private Long total;

  public List<CustomerVerifyKycRequest> getCustomerVerifyRequests() {
    return customerVerifyRequests;
  }

  public void setCustomerVerifyRequests(List<CustomerVerifyKycRequest> customerVerifyRequests) {
    this.customerVerifyRequests = customerVerifyRequests;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

}
