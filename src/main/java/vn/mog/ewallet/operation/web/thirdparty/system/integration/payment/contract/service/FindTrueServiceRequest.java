package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.List;

public class FindTrueServiceRequest extends FindTrueServiceRequestType {

  private static final long serialVersionUID = 1L;

  public FindTrueServiceRequest() {

  }

  public FindTrueServiceRequest(List<String> serviceTypes, Integer level) {
    this.serviceTypes = serviceTypes;
    this.level = level;
  }

}
