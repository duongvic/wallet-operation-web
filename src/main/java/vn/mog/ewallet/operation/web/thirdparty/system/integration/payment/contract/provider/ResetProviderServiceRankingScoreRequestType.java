package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ResetProviderServiceRankingScoreRequestType extends MobiliserRequestType
    implements Serializable {
  private static final long serialVersionUID = 1L;
  protected String providerCode;
  protected String remark;

  public String getProviderCode() {
    return providerCode;
  }
  
  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
