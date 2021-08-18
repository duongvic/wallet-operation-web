package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system;

import java.util.List;

public class FindLocationRequest extends FindLocationRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public FindLocationRequest(List<String> codes) {
    this.codes = codes;
  }

  public FindLocationRequest() {

  }
}
