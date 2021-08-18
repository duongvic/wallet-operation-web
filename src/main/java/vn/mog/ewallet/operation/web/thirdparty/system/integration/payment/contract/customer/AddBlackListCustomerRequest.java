package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddBlackListCustomerRequest extends UpdateBlackListCustomerRequestType {

  @JsonIgnore
  private String command = ADD;

  public AddBlackListCustomerRequest() {
    super();
  }

  @Override
  public String getCommand() {
    return command;
  }
}
