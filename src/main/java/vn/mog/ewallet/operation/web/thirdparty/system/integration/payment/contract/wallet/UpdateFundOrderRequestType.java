package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateFundOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected FundOrder fundOrder;
  protected List<FundOrderAttachment> attachments;
  protected Long customerId;
  protected String bankCodeSOF;

  public FundOrder getFundOrder() {
    return fundOrder;
  }

  public void setFundOrder(FundOrder fundOrder) {
    this.fundOrder = fundOrder;
  }

  public List<FundOrderAttachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    return attachments;
  }

  public void setAttachments(List<FundOrderAttachment> attachments) {
    this.attachments = attachments;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getBankCodeSOF() {
    return bankCodeSOF;
  }

  public void setBankCodeSOF(String bankCodeSOF) {
    this.bankCodeSOF = bankCodeSOF;
  }
}
