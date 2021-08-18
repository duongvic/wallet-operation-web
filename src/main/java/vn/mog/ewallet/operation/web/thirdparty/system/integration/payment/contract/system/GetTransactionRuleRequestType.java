package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTransactionRuleRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
