package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.*;

@Getter
@Setter
public class SummaryTransactionStatisticByHourRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @ApiModelProperty(value = "customerTypes (Optional)")
  private List<CustomerTypeEnum> customerTypes;
  
  @ApiModelProperty(value = "customerIds (Optional)")
  private List<Long> customerIds;
  
  @ApiModelProperty(value = "providerCodes (Optional)")
  private List<ProviderCodeEnum> providerCodes;
  
  @ApiModelProperty(value = "serviceTypes (Optional)")
  private List<ServiceTypeEnum> serviceTypes;
  
  @ApiModelProperty(value = "serviceCodes (Optional)")
  private List<String> serviceCodes;
  
  @ApiModelProperty(value = "sofIds (Optional)")
  private List<SourceOfFundTypeEnum> sofIds;

  @ApiModelProperty(value = "status (Optional)")
  private List<StatusEnum> status;
  
  @ApiModelProperty(value = "statDate (Required)")
  private Date statDate;
  
  @ApiModelProperty(value = "fromHour (Required)")
  private int fromHour = 0;
  
  @ApiModelProperty(value = "toHour (Required)")
  private int toHour = 23;
}
