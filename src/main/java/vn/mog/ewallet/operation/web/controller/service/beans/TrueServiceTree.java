package vn.mog.ewallet.operation.web.controller.service.beans;

import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.SpecifiedCommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;

public class TrueServiceTree extends TrueService {
  private String pathTreeUnder;
  private String pathTreePoint;

  private String pathParentUnder;
  private String pathParentPoint;

  private Values commissionFee;
  private Values specifiedCommissionFee;

  private Boolean isUseSpecifiedCommission;
  private String paymentChannelId;

  public TrueServiceTree(CommissionFee commissionFee) {
    this.id = commissionFee.getId();

    this.parent = commissionFee.getParent();
    this.parentServiceCode = commissionFee.getParentServiceCode();
    this.serviceCode = commissionFee.getServiceCode();
    this.serviceName = commissionFee.getServiceName();
    this.serviceShortName = commissionFee.getServiceShortName();
    this.customerTypeSupported = commissionFee.getCustomerTypeSupported();
    this.serviceDesc = commissionFee.getServiceDesc();
    this.servicePrices = commissionFee.getServicePrices();
    this.telcoType = commissionFee.getTelcoType();
    this.telcoServiceType = commissionFee.getTelcoServiceType();
    this.serviceType = commissionFee.getServiceType();
    this.isActorPayee = commissionFee.getIsActorPayee();
    this.status = commissionFee.getStatus();
    this.parentFeeStructureAllowed = commissionFee.isParentFeeStructureAllowed();

    this.level = commissionFee.getLevel();
    this.creationDate = commissionFee.getCreationDate();

    this.attributes = commissionFee.getAttributes();
    this.paymentChannelId =  commissionFee.getPaymentChannelId();
  }

  public TrueServiceTree() {

  }

  public Values getCommissionFee() {
    return commissionFee;
  }

  public void setCommissionFee(Values commissionFee) {
    this.commissionFee = commissionFee;
  }

  public Values getSpecifiedCommissionFee() {
    return specifiedCommissionFee;
  }

  public void setSpecifiedCommissionFee(
      Values specifiedCommissionFee) {
    this.specifiedCommissionFee = specifiedCommissionFee;
  }

  public String getPathTreeUnder() {
    return pathTreeUnder == null ? StringUtils.EMPTY : pathTreeUnder;
  }

  public void setPathTreeUnder(String pathTreeUnder) {
    this.pathTreeUnder = pathTreeUnder;
  }

  public String getPathTreePoint() {
    return pathTreePoint == null ? StringUtils.EMPTY : pathTreePoint;
  }

  public void setPathTreePoint(String pathTreePoint) {
    this.pathTreePoint = pathTreePoint;
  }

  public String getPathParentUnder() {
    return pathParentUnder == null ? StringUtils.EMPTY : pathParentUnder;
  }

  public void setPathParentUnder(String pathParentUnder) {
    this.pathParentUnder = pathParentUnder;
  }

  public String getPathParentPoint() {
    return pathParentPoint == null ? StringUtils.EMPTY : pathParentPoint;
  }

  public void setPathParentPoint(String pathParentPoint) {
    this.pathParentPoint = pathParentPoint;
  }

  public Boolean getIsUseSpecifiedCommission() {
    return isUseSpecifiedCommission;
  }

  public void setIsUseSpecifiedCommission(Boolean useSpecifiedCommission) {
    isUseSpecifiedCommission = useSpecifiedCommission;
  }

  public String getPaymentChannelId() {
    return paymentChannelId;
  }

  public void setPaymentChannelId(String paymentChannelId) {
    this.paymentChannelId = paymentChannelId;
  }

  @SuppressWarnings("Duplicates")
  public <T> TrueServiceTree extendCommistionFee(T serviceItem) {
    Values commissionFeeItem = new Values();
    vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.Values commissionFeeValues = null;
    Values specifiedCommissionFeeItem = new Values();
    vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.Values specifiedCommissionFeeValues = null;

    //USING for case special commission fee
    if (serviceItem instanceof SpecifiedCommissionFee) {
      SpecifiedCommissionFee trueServiceTree = (SpecifiedCommissionFee) serviceItem;

      commissionFeeValues = trueServiceTree.getCommissionFeeValues();
      specifiedCommissionFeeValues = trueServiceTree.getSpecifiedCommissionFeeValues();

      this.setIsUseSpecifiedCommission(trueServiceTree.getIsUseSpecifiedCommission());
    }

    //USING for case commission fee
    else if (serviceItem instanceof CommissionFee) {
      CommissionFee trueServiceTree = (CommissionFee) serviceItem;
      commissionFeeValues = trueServiceTree.getCommissionFeeValues();
    }

    if (commissionFeeValues != null) {
      Double commission = commissionFeeValues.getCommission();
      Long commissionFixedAmount = commissionFeeValues.getCommissionFixedAmount();
      Double fee = commissionFeeValues.getFee();
      Long feeFixedAmount = commissionFeeValues.getFeeFixedAmount();

      commissionFeeItem.setCommission(commission != null ? commission : 0D);
      commissionFeeItem.setCommissionFixedAmount(commissionFixedAmount != null ? commissionFixedAmount : 0L);
      commissionFeeItem.setFee(fee != null ? fee : 0D);
      commissionFeeItem.setFeeFixedAmount(feeFixedAmount != null ? feeFixedAmount : 0L);
    }
    this.setCommissionFee(commissionFeeItem);

    if (specifiedCommissionFeeValues != null) {
      Double commission = specifiedCommissionFeeValues.getCommission();
      Long commissionFixedAmount = specifiedCommissionFeeValues.getCommissionFixedAmount();
      Double fee = specifiedCommissionFeeValues.getFee();
      Long feeFixedAmount = specifiedCommissionFeeValues.getFeeFixedAmount();

      specifiedCommissionFeeItem.setCommission(commission != null ? commission : 0D);
      specifiedCommissionFeeItem.setCommissionFixedAmount(commissionFixedAmount != null ? commissionFixedAmount : 0L);
      specifiedCommissionFeeItem.setFee(fee != null ? fee : 0D);
      specifiedCommissionFeeItem.setFeeFixedAmount(feeFixedAmount != null ? feeFixedAmount : 0L);
    }
    this.setSpecifiedCommissionFee(specifiedCommissionFeeItem);

    return this;
  }
}
