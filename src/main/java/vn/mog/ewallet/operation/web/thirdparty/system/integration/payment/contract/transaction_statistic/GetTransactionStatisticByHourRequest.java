package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.*;

@Getter
@Setter
public class GetTransactionStatisticByHourRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  
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
  
  @ApiModelProperty(value = "StatDate (Required)", example = "9")
  private int statHour;
}
