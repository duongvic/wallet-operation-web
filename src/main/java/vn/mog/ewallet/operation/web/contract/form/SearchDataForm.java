package vn.mog.ewallet.operation.web.contract.form;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel;

public class SearchDataForm implements Serializable {

  private String quickSearch;
  private List<Long> customerIds;
  private List<Integer> txnStatusIds;
  private List<String> serviceTypeIds;
  private String range;
  private List<String> provider;
  private List<String> sourceOfFundTypeIds;
  protected List<String> orderChannelIds;
  protected List<Long> idOwners;
  protected List<String> idOwnerCustomerTypes;

  private List<Long> payerIds;
  private List<Long> payeeIds;
  private List<Long> paytypes;
  private Integer customerTypeId;
  private String paymentChannelId;

  private List<String> textSearchTypes;

  public List<String> getTextSearchTypes() {
    return textSearchTypes;
  }

  public void setTextSearchTypes(List<String> textSearchTypes) {
    this.textSearchTypes = textSearchTypes;
  }

  public Boolean isActorPayer() {
    if (paytypes == null || paytypes.size() >= 2) {
      return null;
    } else {
      return paytypes.get(0).equals(1L);
    }
  }


  public List<Long> getPaytypes() {
    return paytypes == null ? Collections.emptyList() : paytypes;
  }

  public void setPaytypes(List<Long> paytypes) {
    this.paytypes = paytypes;
  }

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = StringUtils.trimToEmpty(quickSearch)
        .replaceAll("\\s+", " ");
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds == null ? Collections.emptyList() : customerIds;
  }

  public List<Integer> getTxnStatusIds() {
    return txnStatusIds;
  }

  public void setTxnStatusIds(List<Integer> txnStatusIds) {
    this.txnStatusIds = txnStatusIds == null ? Collections.emptyList() : txnStatusIds;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds == null ? Collections.emptyList() : serviceTypeIds;
  }

  public void setServiceTypeIds(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds == null ? Collections.emptyList() : serviceTypeIds;
  }

  public String getRange() {
    return range;
  }

  public void setRange(String range) {
    this.range = range == null ? StringUtils.EMPTY : range.trim();
  }

  public List<String> getProvider() {
    return provider;
  }

  public void setProvider(List<String> provider) {
    this.provider = provider == null ? Collections.emptyList() : provider;
  }

  public List<String> getSourceOfFundTypeIds() {
    return sourceOfFundTypeIds;
  }

  public void setSourceOfFundTypeIds(List<String> sourceOfFundTypeIds) {
    this.sourceOfFundTypeIds = sourceOfFundTypeIds == null ? Collections.emptyList() : sourceOfFundTypeIds;
  }

  public List<String> getOrderChannelIds() {
    return orderChannelIds;
  }

  public void setOrderChannelIds(List<String> orderChannelIds) {
    this.orderChannelIds = orderChannelIds;
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

  public Integer getCustomerTypeIdOrDefault() {
    return customerTypeId == null ? CustomerType.ID_CUSTOMER : customerTypeId;
  }

  public String getNameCustomerTypeId() {
    return CustomerType.USER_CUSTOMER_TYPES.getOrDefault(this.customerTypeId, StringUtils.EMPTY);
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public String getPaymentChannelId() {
    return paymentChannelId;
  }

  public void setPaymentChannelId(String paymentChannelId) {
    this.paymentChannelId = paymentChannelId;
  }

  public String getPaymentChannelIdOrDefault() {
    return StringUtils.isBlank(paymentChannelId) ? PaymentChannel.ZOTA.getCode() : paymentChannelId;
  }

}
