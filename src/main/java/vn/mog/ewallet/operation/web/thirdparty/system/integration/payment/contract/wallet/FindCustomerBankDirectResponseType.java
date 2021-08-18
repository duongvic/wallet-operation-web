package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.CustomerBankDirect;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindCustomerBankDirectResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<CustomerBankDirect> bankDirects;

  public List<CustomerBankDirect> getBankDirects() {
    return bankDirects;
  }

  public void setBankDirects(List<CustomerBankDirect> bankDirects) {
    this.bankDirects = bankDirects;
  }
}
