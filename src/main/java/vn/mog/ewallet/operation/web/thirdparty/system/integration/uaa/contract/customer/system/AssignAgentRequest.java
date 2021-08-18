package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class AssignAgentRequest extends MobiliserRequestType {

  private Long saleId;
  private List<Long> agentIds;

  public Long getSaleId() {
    return saleId;
  }

  public void setSaleId(Long saleId) {
    this.saleId = saleId;
  }

  public List<Long> getAgentIds() {
    return agentIds;
  }

  public void setAgentIds(List<Long> agentIds) {
    this.agentIds = agentIds;
  }
}
