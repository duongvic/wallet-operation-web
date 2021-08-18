package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

public class GetUmgrCustomerRolesRequest extends GetUmgrCustomerRolesRequestType {

  private static final long serialVersionUID = 1L;

  public GetUmgrCustomerRolesRequest() {
  }

  public GetUmgrCustomerRolesRequest(long customerId) {
    this.customerId = customerId;
  }
}
