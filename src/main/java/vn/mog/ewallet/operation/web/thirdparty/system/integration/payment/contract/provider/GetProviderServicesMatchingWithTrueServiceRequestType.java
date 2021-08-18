package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetProviderServicesMatchingWithTrueServiceRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  protected List<Integer> rankingGroupIds;
  
  protected List<String> serviceCodes;

  protected Boolean includeInactive;

  public List<Integer> getRankingGroupIds() {
    return rankingGroupIds;
  }

  public void setRankingGroupIds(List<Integer> rankingGroupIds) {
    this.rankingGroupIds = rankingGroupIds;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
  }

  public Boolean getIncludeInactive() {
    return includeInactive;
  }

  public void setIncludeInactive(Boolean includeInactive) {
    this.includeInactive = includeInactive;
  }
}
