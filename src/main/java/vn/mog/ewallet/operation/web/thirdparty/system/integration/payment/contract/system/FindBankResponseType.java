package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.Bank;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindBankResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  List<Bank> banks;

  public List<Bank> getBanks() {
    return banks;
  }

  public void setBanks(List<Bank> banks) {
    this.banks = banks;
  }
}
