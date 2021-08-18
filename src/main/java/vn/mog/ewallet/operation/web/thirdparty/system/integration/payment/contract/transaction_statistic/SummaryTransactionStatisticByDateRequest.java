package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ProviderCodeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.SourceOfFundTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.StatusEnum;

@Getter
@Setter
public class SummaryTransactionStatisticByDateRequest implements Serializable {

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
  
  @ApiModelProperty(value = "fromDate (Required)")
  private Date fromDate;
  
  @ApiModelProperty(value = "toDate (Required)")
  private Date toDate;
}