package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.Date;
import java.util.List;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionTextSearchType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTransactionsRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String textSearch;

  protected List<Integer> statusIds;
  protected List<String> serviceTypeIds;
  protected List<String> serviceCodes;
  protected List<String> providerCodes;
  protected List<String> sourceOfFundTypeIds;
  protected List<String> orderChannelIds;
  protected List<String> paymentChannelIds;
  protected List<Long> idOwners;
  protected List<String> idOwnerCustomerTypes;

  protected List<Long> payerIds;
  protected List<Long> payeeIds;

  protected List<Long> customerIds;
  protected List<String> cifs; // priority : 2st
  protected List<String> phoneNumbers; // priority : 3st

//  private List<TransactionTextSearchType> textSearchTypes;
  private List<String> textSearchTypes;

  protected Date fromDate;
  protected Date endDate;

  protected Integer offset;
  protected Integer limit;

  public List<Long> getPayerIds() {
    return payerIds;
  }

  public void setPayerIds(List<Long> payerIds) {
    this.payerIds = payerIds;
  }

  public List<Long> getPayeeIds() {
    return payeeIds;
  }

  public void setPayeeIds(List<Long> payeeIds) {
    this.payeeIds = payeeIds;
  }

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<Integer> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypeIds(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
  }

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

//  public List<TransactionTextSearchType> getTextSearchTypes() {
//    return textSearchTypes;
//  }
//
//  public void setTextSearchTypes(List<TransactionTextSearchType> textSearchTypes) {
//    this.textSearchTypes = textSearchTypes;
//  }


  public List<String> getTextSearchTypes() {
    return textSearchTypes;
  }

  public void setTextSearchTypes(List<String> textSearchTypes) {
    this.textSearchTypes = textSearchTypes;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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

  public List<String> getSourceOfFundTypeIds() {
    return sourceOfFundTypeIds;
  }

  public void setSourceOfFundTypeIds(List<String> sourceOfFundTypeIds) {
    this.sourceOfFundTypeIds = sourceOfFundTypeIds;
  }

  public List<String> getOrderChannelIds() {
    return orderChannelIds;
  }

  public void setOrderChannelIds(List<String> orderChannelIds) {
    this.orderChannelIds = orderChannelIds;
  }
  
  public List<String> getPaymentChannelIds() {
    return paymentChannelIds;
  }
  
  public void setPaymentChannelIds(List<String> paymentChannelIds) {
    this.paymentChannelIds = paymentChannelIds;
  }

  public List<Long> getIdOwners() {
    return idOwners;
  }

  public void setIdOwners(List<Long> idOwners) {
    this.idOwners = idOwners;
  }

  public List<String> getIdOwnerCustomerTypes() {
    return idOwnerCustomerTypes;
  }

  public void setIdOwnerCustomerTypes(List<String> idOwnerCustomerTypes) {
    this.idOwnerCustomerTypes = idOwnerCustomerTypes;
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
}
