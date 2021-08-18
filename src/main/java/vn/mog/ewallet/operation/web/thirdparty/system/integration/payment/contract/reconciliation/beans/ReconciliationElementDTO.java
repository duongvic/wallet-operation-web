package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
import vn.mog.framework.common.utils.Utils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconciliationElementDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "Id")
  private Long id;

  @ApiModelProperty(notes = "reconciliationId", example = "0")
  private Long reconciliationId;

  @ApiModelProperty(value = "customerType (Required)", example = "AGENT")
  private CustomerTypeEnum customerType;

  @ApiModelProperty(notes = "id of the customer")
  private Long customerId;

  @ApiModelProperty(notes = "type of the service", example = "PHONE_TOPUP")
  private ServiceTypeEnum serviceType;

  @ApiModelProperty(notes = "id of the service")
  private Long serviceId;

  @ApiModelProperty(notes = "code of the service", example = "06010103")
  private String serviceCode;

  @ApiModelProperty(notes = "name of the service", example = "06010103")
  private String serviceName;

  @ApiModelProperty(notes = "quantity of the product has pay via this service")
  private Long quantity;

  @ApiModelProperty(notes = "unit price of the product has pay via this service")
  private Long unitPrice;

  @ApiModelProperty(notes = "unit fee fixed amount of the product has pay via this service")
  private Long unitFeeFixedAmount;

  @ApiModelProperty(notes = "unit fee percent rate of the product has pay via this service")
  private Double unitFeePercentRate;

  @ApiModelProperty(notes = "unit commission fixed amount of the product has pay via this service")
  private Long unitCommissionFixedAmount;

  @ApiModelProperty(notes = "unit commission percent rate of the product has pay via this service")
  private Double unitCommissionPercentRate;

  @ApiModelProperty(notes = "number of transaction to make payment", example = "1")
  private Long numOfTrans;

  @ApiModelProperty(notes = "sum of request amount to make payment", example = "100000")
  private Long sumOfRequestAmount;

  @ApiModelProperty(notes = "sum of fee amount to make payment", example = "10000")
  private Long sumOfFeeAmount;

  @ApiModelProperty(notes = "sum of commission amount to make payment", example = "5000")
  private Long sumOfCommissionAmount;

  @ApiModelProperty(notes = "sum of cashback amount to make payment", example = "0")
  private Long sumOfCashbackAmount;

  @ApiModelProperty(notes = "sum of net amount to make payment", example = "100000")
  private Long sumOfNetAmount;

  @ApiModelProperty(notes = "short name of the service", example = "Viettel")
  private String serviceShortName;

  public ReconciliationElementDTO(
      CustomerTypeEnum customerType,
      Long customerId,
      ServiceTypeEnum serviceType,
      String serviceCode,
      Long unitPrice,
      Long unitFeeFixedAmount,
      Double unitFeePercentRate,
      Long unitCommissionFixedAmount,
      Double unitCommissionPercentRate,
      Long quantity,
      Long numOfTrans,
      Long sumOfRequestAmount,
      Long sumOfFeeAmount,
      Long sumOfCommissionAmount,
      Long sumOfCashbackAmount) {
    this.customerType = customerType;
    this.customerId = customerId;
    this.serviceType = serviceType;
    this.serviceCode = serviceCode;

    this.unitPrice = unitPrice;
    this.quantity = quantity;

    this.unitFeeFixedAmount = unitFeeFixedAmount;
    this.unitFeePercentRate = unitFeePercentRate;

    this.unitCommissionFixedAmount = unitCommissionFixedAmount;
    this.unitCommissionPercentRate = unitCommissionPercentRate;

    this.numOfTrans = numOfTrans;
    this.sumOfRequestAmount = sumOfRequestAmount;
    this.sumOfFeeAmount = sumOfFeeAmount;
    this.sumOfCommissionAmount = sumOfCommissionAmount;
    this.sumOfCashbackAmount = sumOfCashbackAmount;
  }

  public Long getTotalTransactionFee() {
    try {
      return Utils.convertToZeroIfNull(numOfTrans) * Utils.convertToZeroIfNull(sumOfFeeAmount);
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalUnitFeeFixedAmount() {
    try {
      return Utils.convertToZeroIfNull(numOfTrans) * Utils.convertToZeroIfNull(unitFeeFixedAmount);
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getEntitledFee() {
    try {
      return Utils.convertToZeroIfNull(numOfTrans) * Utils.convertToZeroIfNull(sumOfCommissionAmount);
    } catch (Exception e) {

    }
    return 0L;
  }
}
