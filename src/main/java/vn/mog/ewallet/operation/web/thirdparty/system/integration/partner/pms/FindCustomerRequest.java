package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class FindCustomerRequest implements Serializable {

  private String name;
  private Set<String> msisdns;
  private String cif;
  private String email;
  private String quickSearch;
  private List<Integer> customerTypes;
  private List<Integer> blacklistReasons;
  private List<Long> customerIds;
  private List<Long> creators;
  private Boolean bolActive;
  private Date fromDate;
  private Date toDate;
  private String accessKey;
  private String sign;
  private Long timestamp;

  private Integer offset;
  private Integer limit;

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public List<Integer> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<Integer> customerTypes) {
    this.customerTypes = customerTypes;
  }

  public List<Integer> getBlacklistReasons() {
    return blacklistReasons;
  }

  public void setBlacklistReasons(List<Integer> blacklistReasons) {
    this.blacklistReasons = blacklistReasons;
  }

  public Boolean getBolActive() {
    return bolActive;
  }

  public void setBolActive(Boolean bolActive) {
    this.bolActive = bolActive;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getMsisdns() {
    return msisdns;
  }

  public void setMsisdns(Set<String> msisdns) {
    this.msisdns = msisdns;
  }

  public List<Long> getCreators() {
    return creators;
  }

  public void setCreators(List<Long> creators) {
    this.creators = creators;
  }
}

