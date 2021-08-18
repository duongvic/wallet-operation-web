package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryByDateDTO extends SummaryDTO {

  private static final long serialVersionUID = 1L;
  
  @JsonIgnore
  @ApiModelProperty(value = "statDate")
  private Date statDate;

}
