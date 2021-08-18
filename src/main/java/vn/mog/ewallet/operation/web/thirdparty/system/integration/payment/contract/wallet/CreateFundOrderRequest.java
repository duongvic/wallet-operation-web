package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;

public class CreateFundOrderRequest extends CreateFundOrderRequestType {

  private static final long serialVersionUID = 1L;

  public CreateFundOrderRequest(FundOrder fundOrder, Long customerId) {
    this.fundOrder = fundOrder;
    this.customerId = customerId;
  }

  public CreateFundOrderRequest() {

  }

  public CreateFundOrderRequest(FundOrder fundOrder, Long customerId, List<FundOrderAttachment> attachments) {
    this.fundOrder = fundOrder;
    this.attachments = attachments;
    this.customerId = customerId;
  }

  public CreateFundOrderRequest(FundOrder fundOrder) {
    this.fundOrder = fundOrder;
  }
}
