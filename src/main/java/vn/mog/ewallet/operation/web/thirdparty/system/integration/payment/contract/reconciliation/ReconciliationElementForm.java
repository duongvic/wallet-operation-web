package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconciliationElementForm implements Serializable {

  private static final long serialVersionUID = 1L;

  //  @ApiModelProperty(value = "Id")
  //  private Long id;
  //
  //  @ApiModelProperty(notes = "reconciliationId", example = "0")
  //  private Long reconciliationId;

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
}
