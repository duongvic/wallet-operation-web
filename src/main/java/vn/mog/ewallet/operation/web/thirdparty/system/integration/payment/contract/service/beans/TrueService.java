package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel.LEVEL_0;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel.LEVEL_1;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel.LEVEL_2;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel.LEVEL_3;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel.LEVEL_4;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType;


public class TrueService implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Long id;
  protected TrueService parent;
  protected String parentServiceCode;
  protected String serviceCode;
  protected String serviceName;
  protected String serviceShortName;
  protected String serviceDesc;
  protected String servicePrices;
  protected TelcoType telcoType;
  protected TelcoServiceType telcoServiceType;
  protected ServiceType serviceType;
  protected Character isActorPayee;
  protected Character status;
  protected boolean parentFeeStructureAllowed;
  protected String customerTypeSupported;

  protected Integer level;
  protected Date creationDate;

  protected List<TrueServiceAttribute> attributes;

  public TrueService() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TrueService getParent() {
    return parent;
  }

  public void setParent(TrueService parent) {
    this.parent = parent;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceShortName() {
    return serviceShortName;
  }

  public void setServiceShortName(String serviceShortName) {
    this.serviceShortName = serviceShortName;
  }

  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }

  public String getServicePrices() {
    return servicePrices;
  }

  public void setServicePrices(String servicePrices) {
    this.servicePrices = servicePrices;
  }

  public TelcoType getTelcoType() {
    return telcoType;
  }

  public void setTelcoType(TelcoType telcoType) {
    this.telcoType = telcoType;
  }

  public TelcoServiceType getTelcoServiceType() {
    return telcoServiceType;
  }

  public void setTelcoServiceType(TelcoServiceType telcoServiceType) {
    this.telcoServiceType = telcoServiceType;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public boolean containsPrice(String price) {
    return servicePrices.indexOf(price) != -1;
  }

  public String getParentServiceCode() {
    return parentServiceCode;
  }

  public void setParentServiceCode(String parentServiceCode) {
    this.parentServiceCode = parentServiceCode;
  }

  public Character getStatus() {
    return status;
  }

  public void setStatus(Character status) {
    this.status = status;
  }

  public Integer getLevel() {
    if (level == null) {
      level = -1;
    }
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getTextLevel() {
    if (LEVEL_0.code.equals(level)) {
      return LEVEL_0.name;
    } else if (LEVEL_1.code.equals(level)) {
      return LEVEL_1.name;
    } else if (LEVEL_2.code.equals(level)) {
      return LEVEL_2.name;
    } else if (LEVEL_3.code.equals(level)) {
      return LEVEL_3.name;
    } else if (LEVEL_4.code.equals(level)) {
      return LEVEL_4.name;
    }
    return StringUtils.EMPTY;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Character getIsActorPayee() {
    return isActorPayee;
  }

  public void setIsActorPayee(Character isActorPayee) {
    this.isActorPayee = isActorPayee;
  }

  public boolean isParentFeeStructureAllowed() {
    return this.parentFeeStructureAllowed;
  }

  public void setParentFeeStructureAllowed(boolean value) {
    this.parentFeeStructureAllowed = value;
  }

  public String getCustomerTypeSupported() {
    return customerTypeSupported;
  }

  public void setCustomerTypeSupported(String customerTypeSupported) {
    this.customerTypeSupported = customerTypeSupported;
  }

  public List<TrueServiceAttribute> getAttributes() {
    if (attributes == null) {
      attributes = Collections.emptyList();
    }
    return attributes;
  }

  public void setAttributes(List<TrueServiceAttribute> attributes) {
    this.attributes = attributes;
  }

}
