package vn.mog.ewallet.operation.web.controller.service.beans;

import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.framework.common.utils.StringUtil;


public class TransactionRuleMatrix {

  private Long id;
  private String serviceType;
  private String code;
  private String name;
  private String existMatrix;

  public TransactionRuleMatrix() {
  }

  public TransactionRuleMatrix(TransactionRule item, Long transactionRuleMapped) {
    this.id = item.getId();
    this.serviceType = item.getServiceType().toString();
    this.code = item.getCode();
    this.name = item.getName();
    if (this.id.equals(transactionRuleMapped)) {
      this.existMatrix = StringUtil.CHECKED;
    } else {
      this.existMatrix = StringUtils.EMPTY;
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExistMatrix() {
    return existMatrix;
  }

  public void setExistMatrix(String existMatrix) {
    this.existMatrix = existMatrix;
  }
}
