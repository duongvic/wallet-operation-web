package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummaryDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "numOfTrans (Required)", example = "1")
  protected Long numOfTrans = 0L;

  @ApiModelProperty(value = "sumOfRequestAmount (Required)", example = "100000")
  protected Long sumOfRequestAmount = 0L;

  @ApiModelProperty(value = "sumOfFeeAmount (Required)", example = "10000")
  protected Long sumOfFeeAmount = 0L;

  @ApiModelProperty(value = "sumOfCommissionAmount (Required)", example = "5000")
  protected Long sumOfCommissionAmount = 0L;

  @ApiModelProperty(value = "sumOfCashbackAmount (Required)", example = "0")
  protected Long sumOfCashbackAmount = 0L;

  @ApiModelProperty(value = "sumOfNetAmount (Required)", example = "95000")
  protected Long sumOfNetAmount = 0L;

  @ApiModelProperty(value = "sumOfCapitalValue (Required)", example = "90000")
  protected Long sumOfCapitalValue = 0L;
  
  @ApiModelProperty(value = "sumOfRevenueAmount (Required)", example = "5000")
  protected Long sumOfRevenueAmount = 0L;
}
