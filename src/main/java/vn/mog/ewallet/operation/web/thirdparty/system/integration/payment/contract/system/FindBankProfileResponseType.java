package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.BankProfile;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindBankProfileResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<BankProfile> bankProfiles;

  public List<BankProfile> getBankProfiles() {
    return bankProfiles;
  }

  public void setBankProfiles(List<BankProfile> bankProfiles) {
    this.bankProfiles = bankProfiles;
  }

}
