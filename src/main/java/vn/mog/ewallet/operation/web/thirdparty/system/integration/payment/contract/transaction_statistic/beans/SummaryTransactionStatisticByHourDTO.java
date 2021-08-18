package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans;

import java.io.Serializable;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SummaryTransactionStatisticByHourDTO implements Serializable{

  private static final long serialVersionUID = 1L;
  private Map<String,SummaryDTO> summaryByStatus;
  private Map<String,SummaryDTO> summaryByServiceType;
  private Map<String,SummaryDTO> summaryByCustomerType;
  private Map<String,SummaryDTO> summaryByCustomer;
  private Map<String,SummaryDTO> summaryByProvider;
  private Map<String,SummaryDTO> summaryByHour;
//  private List<TransactionStatisticByHourDTO> details;
}
