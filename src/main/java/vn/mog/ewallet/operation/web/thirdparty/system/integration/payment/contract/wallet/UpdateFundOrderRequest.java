package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;

public class UpdateFundOrderRequest extends UpdateFundOrderRequestType {

  private static final long serialVersionUID = 1L;

  public UpdateFundOrderRequest() {
  }

  public UpdateFundOrderRequest(FundOrder fundOrder) {
    this.fundOrder = fundOrder;

  }

  public UpdateFundOrderRequest(FundOrder fundOrder, List<FundOrderAttachment> attachments) {
    this.fundOrder = fundOrder;
    this.attachments = attachments;
  }

  public UpdateFundOrderRequest(FundOrder fundOrder, Long customerId, List<FundOrderAttachment> attachments) {
    this.fundOrder = fundOrder;
    this.attachments = attachments;
    this.customerId = customerId;
  }
}
