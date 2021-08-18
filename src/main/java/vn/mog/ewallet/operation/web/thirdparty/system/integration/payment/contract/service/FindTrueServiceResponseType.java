package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.ArrayList;
import java.util.List;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTrueServiceResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<CommissionFee> services;
  protected Long total;
  protected Long all;

  public List<CommissionFee> getServices() {
    if (services == null) {
      services = new ArrayList<>();
    }
    return services;
  }

  public void setServices(List<CommissionFee> services) {
    this.services = services;
  }

  public Long getTotal() {
    if (total == null) {
      total = 0L;
    }
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    if (all == null) {
      all = 0L;
    }
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }
}
