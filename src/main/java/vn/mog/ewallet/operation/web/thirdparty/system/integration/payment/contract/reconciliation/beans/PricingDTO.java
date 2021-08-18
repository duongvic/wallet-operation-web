package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingDTO implements Serializable {
  private static final long serialVersionUID = 1L;

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

  @ApiModelProperty(notes = "name of the service", example = "Phone Topup Viettel 10K")
  private String serviceName;

  @ApiModelProperty(notes = "unit price of the product has pay via this service")
  private Long unitPrice = 0L;

  @ApiModelProperty(notes = "unit fee fixed amount of the product has pay via this service")
  private Long unitFeeFixedAmount = 0L;

  @ApiModelProperty(notes = "unit fee percent rate of the product has pay via this service")
  private Double unitFeePercentRate = 0d;

  @ApiModelProperty(notes = "unit commission fixed amount of the product has pay via this service")
  private Long unitCommissionFixedAmount = 0L;

  @ApiModelProperty(notes = "unit commission percent rate of the product has pay via this service")
  private Double unitCommissionPercentRate = 0d;
}
