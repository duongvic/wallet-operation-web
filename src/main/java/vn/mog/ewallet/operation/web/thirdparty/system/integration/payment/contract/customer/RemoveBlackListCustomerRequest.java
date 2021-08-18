package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RemoveBlackListCustomerRequest extends UpdateBlackListCustomerRequestType {

  @JsonIgnore
  private String command = DELETE;

  public RemoveBlackListCustomerRequest() {
    super();
  }

  @Override
  public String getCommand() {
    return command;
  }

}
