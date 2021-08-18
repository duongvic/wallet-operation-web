package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

public class GetFullCustomerRequest extends GetFullCustomerRequestType {

  private static final long serialVersionUID = 1L;

  public GetFullCustomerRequest(Long customerId) {
    this.customerId = customerId;
  }
}
