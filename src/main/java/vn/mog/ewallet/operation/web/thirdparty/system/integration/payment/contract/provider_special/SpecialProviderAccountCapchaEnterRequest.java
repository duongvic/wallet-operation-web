package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Getter
@Setter
public class SpecialProviderAccountCapchaEnterRequest extends MobiliserRequestType {

  private ProviderCode providerCode;
  private String accountName;
  private String capcha;
}
