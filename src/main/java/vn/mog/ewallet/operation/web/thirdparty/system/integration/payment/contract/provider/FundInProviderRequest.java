package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class FundInProviderRequest extends MobiliserRequestType {

  private long providerId;
  private long amount;
  private String remark;

}
