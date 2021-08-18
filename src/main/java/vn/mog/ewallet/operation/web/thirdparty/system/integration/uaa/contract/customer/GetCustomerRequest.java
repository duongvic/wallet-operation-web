package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

public class GetCustomerRequest extends GetCustomerRequestType {

  private static final long serialVersionUID = 1L;

  public GetCustomerRequest(Long customerId) {
    this.customerId = customerId;
  }

  public GetCustomerRequest() {

  }
}
