package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateFundOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected FundOrder fundOrder;
  protected Long customerId;
  protected List<FundOrderAttachment> attachments;

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
}
