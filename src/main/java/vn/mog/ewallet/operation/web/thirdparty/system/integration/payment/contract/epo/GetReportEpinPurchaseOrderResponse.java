package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderDetail;


@SuppressWarnings("serial")
public class GetReportEpinPurchaseOrderResponse extends GetReportEpinPurchaseOrderResponseType {

  private Long quantityOfEpin;
  private Long totalOfCardQuantities;
  private Long totalOfFaceValues;
  private Long totalOfCommisions;
  private Long totalOfFees;

  private List<EpinPurchaseOrderDetail> epinPurchaseOrderDetails;

  public GetReportEpinPurchaseOrderResponse() {

  }

  public List<EpinPurchaseOrderDetail> getEpinPurchaseOrderDetails() {
    if (epinPurchaseOrderDetails == null) {
      epinPurchaseOrderDetails = new ArrayList<>();
    }
    return epinPurchaseOrderDetails;
  }

  public void setEpinPurchaseOrderDetails(List<EpinPurchaseOrderDetail> epinPurchaseOrderDetails) {
    this.epinPurchaseOrderDetails = epinPurchaseOrderDetails;
  }

  public Long getQuantityOfEpin() {
    return quantityOfEpin;
  }

  public void setQuantityOfEpin(Long quantityOfEpin) {
    this.quantityOfEpin = quantityOfEpin;
  }

  public Long getTotalOfCardQuantities() {
    return totalOfCardQuantities;
  }

  public void setTotalOfCardQuantities(Long totalOfCardQuantities) {
    this.totalOfCardQuantities = totalOfCardQuantities;
  }

  public Long getTotalOfFaceValues() {
    return totalOfFaceValues;
  }

  public void setTotalOfFaceValues(Long totalOfFaceValues) {
    this.totalOfFaceValues = totalOfFaceValues;
  }

  public Long getTotalOfCommisions() {
    return totalOfCommisions;
  }

  public void setTotalOfCommisions(Long totalOfCommisions) {
    this.totalOfCommisions = totalOfCommisions;
  }

  public Long getTotalOfFees() {
    return totalOfFees;
  }

  public void setTotalOfFees(Long totalOfFees) {
    this.totalOfFees = totalOfFees;
  }
}
