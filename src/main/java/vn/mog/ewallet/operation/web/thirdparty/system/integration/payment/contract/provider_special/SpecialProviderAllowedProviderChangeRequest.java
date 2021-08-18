package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SenpayActionEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialProviderAllowedProviderChangeRequest extends MobiliserRequestType {

  private ProviderCode providerCode;
  private String province;
  private String regionCode;
  private SenpayActionEnum actionType;
}
