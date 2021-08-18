package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProviderHealthy implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private HealthyState state;
  private Map<String, HealthyState> details = new HashMap<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HealthyState getState() {
    return state;
  }

  public void setState(HealthyState state) {
    this.state = state;
  }

  public Map<String, HealthyState> getDetails() {
    return details;
  }

  public void setDetails(Map<String, HealthyState> details) {
    this.details = details;
  }

  public enum HealthyState {
    GREEN, YELLOW, RED
  }
}
