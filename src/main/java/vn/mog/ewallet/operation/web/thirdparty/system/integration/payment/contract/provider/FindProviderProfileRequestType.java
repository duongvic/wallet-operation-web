package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class FindProviderProfileRequestType extends MobiliserRequestType {

  private String providerCode;
}
