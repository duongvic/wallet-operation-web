package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.framework.contract.base.KeyValue;

public class GetBlacklistReasonResponse extends GetBlacklistReasonResponseType {

  private List<KeyValue> blacklistReasons;

  public List<KeyValue> getBlacklistReasons() {
    return blacklistReasons;
  }

  public void setBlacklistReasons(List<KeyValue> blacklistReasons) {
    this.blacklistReasons = blacklistReasons;
  }

}
