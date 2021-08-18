package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SpecialProviderAccount;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class SpecialProviderAccountGetResponseType extends MobiliserResponseType
    implements Serializable {
  private static final long serialVersionUID = 1L;

  private SpecialProviderAccount specialProviderAccount;

  public SpecialProviderAccount getSpecialProviderAccount() {
    return specialProviderAccount;
  }

  public void setSpecialProviderAccount(SpecialProviderAccount specialProviderAccount) {
    this.specialProviderAccount = specialProviderAccount;
  }
}
