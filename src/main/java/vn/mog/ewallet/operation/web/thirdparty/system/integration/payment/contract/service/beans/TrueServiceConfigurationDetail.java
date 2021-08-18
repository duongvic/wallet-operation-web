package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;

public class TrueServiceConfigurationDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private TrueService service;

  private Customer customer;

  private Double commision;

  private Double fee;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TrueService getService() {
    return service;
  }

  public void setService(TrueService service) {
    this.service = service;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Double getCommision() {
    return commision;
  }

  public void setCommision(Double commision) {
    this.commision = commision;
  }

  public Double getFee() {
    return fee;
  }

  public void setFee(Double fee) {
    this.fee = fee;
  }

}
