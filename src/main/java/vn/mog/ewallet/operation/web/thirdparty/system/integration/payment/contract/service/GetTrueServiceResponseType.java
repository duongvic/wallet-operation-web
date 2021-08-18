package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.ArrayList;
import java.util.List;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTrueServiceResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected CommissionFee service;
  protected List<CommissionFee> childrenServices;

  public CommissionFee getService() {
    return service;
  }

  public void setService(CommissionFee service) {
    this.service = service;
  }

  public List<CommissionFee> getChildrenServices() {
    if (childrenServices == null) {
      childrenServices = new ArrayList<>();
    }
    return childrenServices;
  }

  public void setChildrenServices(List<CommissionFee> childrenServices) {
    this.childrenServices = childrenServices;
  }
}
