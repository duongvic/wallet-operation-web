package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;


import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderProfile;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class FindProviderProfileResponseType extends MobiliserResponseType {

  private Collection<ProviderProfile> providerProfiles;
}
