package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;


public class TransactionRule implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private ServiceType serviceType;
  private String code;
  private String name;
  private List<TransactionRuleStep> steps;

  public TransactionRule() {
  }

  public TransactionRule(Long id, ServiceType serviceType, String code, String name) {
    this.id = id;
    this.serviceType = serviceType;
    this.code = code;
    this.name = name;
  }

  public TransactionRule(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
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

  public boolean isSetSteps() {
    return steps != null;
  }

  public List<TransactionRuleStep> getSteps() {
    if (steps == null) {
      steps = Collections.emptyList();
    }
    return steps;
  }

  public void setSteps(List<TransactionRuleStep> steps) {
    this.steps = steps;
  }

  @Override
  public String toString() {
    StringBuffer rs = new StringBuffer();
    rs.append("ID: ").append(getId()).append("\n");
    rs.append("ServiceType: ").append(serviceType.name()).append("\n");
    rs.append("Code: ").append(code).append("\n");
    rs.append("Name: ").append(name).append("\n");
    if (isSetSteps()) {
      rs.append("-----------------").append("\n");
      for (TransactionRuleStep ruleStep : steps) {
        rs.append(ruleStep.toString()).append("\n");
        rs.append("-----------------").append("\n");
      }
    }
    return rs.toString();
  }
}
