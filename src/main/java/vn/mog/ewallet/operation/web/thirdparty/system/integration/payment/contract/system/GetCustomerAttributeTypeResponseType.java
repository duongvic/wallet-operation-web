package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.Collections;
import java.util.List;
import vn.mog.framework.contract.base.KeyValue;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerAttributeTypeResponseType extends MobiliserResponseType {

  protected List<KeyValue> customerAttributeTypes;

  public List<KeyValue> getCustomerAttributeTypes() {
    return customerAttributeTypes == null ? Collections.EMPTY_LIST : customerAttributeTypes;
  }

  public void setCustomerAttributeTypes(List<KeyValue> customerAttributeTypes) {
    this.customerAttributeTypes = customerAttributeTypes;
  }

}
