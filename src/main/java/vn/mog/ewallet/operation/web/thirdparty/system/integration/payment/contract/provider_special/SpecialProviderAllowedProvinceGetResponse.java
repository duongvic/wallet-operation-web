package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SpecialProviderAllowedProvince;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Getter
@Setter
public class SpecialProviderAllowedProvinceGetResponse extends MobiliserResponseType {

  private List<SpecialProviderAllowedProvince> specialProviderAllowedProvinces;
}
