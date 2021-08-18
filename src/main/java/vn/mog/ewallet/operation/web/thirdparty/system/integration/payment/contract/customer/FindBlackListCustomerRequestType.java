package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.BlockType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.QuickSearchType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindBlackListCustomerRequestType extends MobiliserRequestType {

  private String quickSearch;
  private QuickSearchType quickSearchType;
  private List<String> cifs;
  private List<String> phoneNumbers;
  private List<String> fullNames;
  private List<Integer> customerType;
  private List<BlockType> blockTypes;

  private List<String> serviceTypes;
  private List<String> providerCodes;
  private List<String> serviceCodes;

  private Integer offset;
  private Integer limit;

  public FindBlackListCustomerRequestType() {
    super();
  }

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public QuickSearchType getQuickSearchType() {
    return quickSearchType;
  }

  public void setQuickSearchType(QuickSearchType quickSearchType) {
    this.quickSearchType = quickSearchType;
  }

  public List<String> getCifs() {
    return cifs;
  }

  public void setCifs(List<String> cifs) {
    this.cifs = cifs;
  }

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(List<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public List<String> getFullNames() {
    return fullNames;
  }

  public void setFullNames(List<String> fullNames) {
    this.fullNames = fullNames;
  }

  public List<Integer> getCustomerTypes() {
    return customerType;
  }

  public void setCustomerTypes(List<Integer> customerType) {
    this.customerType = customerType;
  }

  public List<BlockType> getBlockTypes() {
    return blockTypes;
  }

  public void setBlockTypes(List<BlockType> blockTypes) {
    this.blockTypes = blockTypes;
  }

  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<String> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
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
}
