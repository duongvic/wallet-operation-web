package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class SpecialProviderAccountGetRequestType extends MobiliserRequestType
    implements Serializable {
  private static final long serialVersionUID = 1L;

  private ProviderCode providerCode;
  private String accountName;

  public ProviderCode getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(ProviderCode providerCode) {
    this.providerCode = providerCode;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
}
