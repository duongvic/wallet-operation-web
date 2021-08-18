package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class Address implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;
  protected int addressType;
  protected String addressName;
  protected int addressStatus;
  protected Integer gender;
  protected String title;
  protected String firstName;
  protected String middleName;
  protected String lastName;
  protected String company;
  protected String position;
  protected String street1; // địa chỉ cửa hàng
  protected String street2;
  protected String houseNumber;
  protected String city;
  protected String state;
  protected String region;
  protected String zip;
  protected String country;
  protected String privatePhone;
  protected String businessPhone; // sdt cửa hàng
  protected String fax;
  protected String email;
  protected String url;
  protected String company2;
  protected String companyShortName;
  protected String province;
  protected String district;
  protected String companyProduct;
  protected String commune;
  protected String remark;
  protected Date created;

  protected OutletStoreType outletStoreType; // nhóm cửa hàng
  protected String storeDescription;

  protected Date addressSince;
  protected String alias;  // tên cửa hàng


  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }

  public int getAddressType() {
    return this.addressType;
  }

  public void setAddressType(int value) {
    this.addressType = value;
  }

  public String getAddressName() {
    return addressName;
  }

  public void setAddressName(String addressName) {
    this.addressName = addressName;
  }


  public int getAddressStatus() {
    return this.addressStatus;
  }

  public void setAddressStatus(int value) {
    this.addressStatus = value;
  }

  public Integer getGender() {
    return this.gender;
  }

  public void setGender(Integer value) {
    this.gender = value;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String value) {
    this.firstName = value;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName(String value) {
    this.middleName = value;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String value) {
    this.lastName = value;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(String value) {
    this.company = value;
  }

  public String getPosition() {
    return this.position;
  }

  public void setPosition(String value) {
    this.position = value;
  }

  public String getStreet1() {
    return this.street1;
  }

  public void setStreet1(String value) {
    this.street1 = value;
  }

  public String getStreet2() {
    return this.street2;
  }

  public void setStreet2(String value) {
    this.street2 = value;
  }

  public String getHouseNumber() {
    return this.houseNumber;
  }

  public void setHouseNumber(String value) {
    this.houseNumber = value;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String value) {
    this.city = value;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String value) {
    this.state = value;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String value) {
    this.zip = value;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String value) {
    this.country = value;
  }

  public String getPrivatePhone() {
    return this.privatePhone;
  }

  public void setPrivatePhone(String value) {
    this.privatePhone = value;
  }

  public String getBusinessPhone() {
    return this.businessPhone;
  }

  public void setBusinessPhone(String value) {
    this.businessPhone = value;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String value) {
    this.fax = value;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String value) {
    this.email = value;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String value) {
    this.url = value;
  }

  public String getCompany2() {
    return this.company2;
  }

  public void setCompany2(String value) {
    this.company2 = value;
  }

  public String getCompanyShortName() {
    return this.companyShortName;
  }

  public void setCompanyShortName(String value) {
    this.companyShortName = value;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }

  public Date getAddressSince() {
    return this.addressSince;
  }

  public void setAddressSince(Date value) {
    this.addressSince = value;
  }

  public String getAlias() {
    return this.alias;
  }

  public void setAlias(String value) {
    this.alias = value;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCompanyProduct() {
    return companyProduct;
  }

  public void setCompanyProduct(String companyProduct) {
    this.companyProduct = companyProduct;
  }

  public String getCommune() {
    return commune;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public OutletStoreType getOutletStoreType() {
    return outletStoreType;
  }

  public void setOutletStoreType(
      OutletStoreType outletStoreType) {
    this.outletStoreType = outletStoreType;
  }

  public String getStoreDescription() {
    return storeDescription;
  }

  public void setStoreDescription(String storeDescription) {
    this.storeDescription = storeDescription;
  }

  public String textOutLetStoreType() {
    if(this.outletStoreType != null){
      return String.valueOf(OutletStoreType.getOutLetStoreType(this.outletStoreType.getCode()));
    }else {
      return StringUtils.EMPTY;
    }

  }
}
