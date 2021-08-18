package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionStatisticByDateDTO implements Serializable{

  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty(value = "Id")
  private Long id;
  
  @ApiModelProperty(value = "customerType (Required)", example = "AGENT")
  private CustomerTypeEnum customerType;
  
  @ApiModelProperty(value = "providerCode (Required)", example = "PTU_C2_SANDBOX")
  private ProviderCodeEnum providerCode;
  
  @ApiModelProperty(value = "serviceType (Required)", example = "PHONE_TOPUP")
  private ServiceTypeEnum serviceType;
  
  @ApiModelProperty(value = "serviceCode (Required)", example = "06010103")
  private String serviceCode;
  
  @ApiModelProperty(value = "sofId (Required)", example = "ZPOINT")
  private SourceOfFundTypeEnum sofId;

  @ApiModelProperty(value = "status (Required)", example = "SUCCESS")
  private StatusEnum status;
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @ApiModelProperty(value = "StatDate (Required)", example = "2021-04-15")
  private Date statDate;
  
  @ApiModelProperty(value = "numOfTrans (Required)", example = "1")
  private Long numOfTrans;
  
  @ApiModelProperty(value = "sumOfRequestAmount (Required)", example = "100000")
  private Long sumOfRequestAmount;
  
  @ApiModelProperty(value = "sumOfFeeAmount (Required)", example = "10000")
  private Long sumOfFeeAmount;
  
  @ApiModelProperty(value = "sumOfCommissionAmount (Required)", example = "5000")
  private Long sumOfCommissionAmount;
  
  @ApiModelProperty(value = "sumOfCashbackAmount (Required)", example = "0")
  private Long sumOfCashbackAmount;
  
  @ApiModelProperty(value = "sumOfNetAmount (Required)", example = "95000")
  private Long sumOfNetAmount;
  
  @ApiModelProperty(value = "sumOfCapitalValue (Required)", example = "90000")
  private Long sumOfCapitalValue;
  
  @ApiModelProperty(value = "sumOfRevenueAmount (Required)", example = "5000")
  private Long sumOfRevenueAmount;
}
