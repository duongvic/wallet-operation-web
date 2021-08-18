package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetProviderServicesMatchingWithTrueServiceResponseType extends MobiliserResponseType
    implements Serializable {
  private static final long serialVersionUID = 1L;

  protected Map<String, Map<String, List<ProviderService>>> matrixMap;
  
  public Map<String, Map<String, List<ProviderService>>> getMatrixMap() {
    return matrixMap;
  }
  
  public void setMatrixMap(Map<String, Map<String, List<ProviderService>>> matrixMap) {
    this.matrixMap = matrixMap;
  }
}
