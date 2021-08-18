package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ContractProfile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconciliationForm implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "Id")
  private Long id;

  @ApiModelProperty(value = "title")
  private String title;

  @ApiModelProperty(value = "description")
  private String description;

  @ApiModelProperty(value = "contractProfile")
  private ContractProfile contract;

  @ApiModelProperty(notes = "type of the customer")
  private CustomerTypeEnum customerType;

  @ApiModelProperty(notes = "id of the customer")
  private Long customerId;

  @ApiModelProperty(notes = "name of the customer")
  private String customerName;

  @ApiModelProperty(value = "paymentPolicy")
  private PaymentPolicyEnum paymentPolicy;

  @ApiModelProperty(value = "cycle")
  private Integer cycle;

  @ApiModelProperty(value = "year")
  private Integer year;

  @ApiModelProperty(value = "month")
  private Integer month;

  @ApiModelProperty(value = "dtYear")
  private Integer dtYear;

  @ApiModelProperty(value = "dtMonth")
  private Integer dtMonth;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss Z",
      timezone = "Asia/Ho_Chi_Minh")
  @ApiModelProperty(notes = "fromDate  of the reconciliation")
  private Date fromDate;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss Z",
      timezone = "Asia/Ho_Chi_Minh")
  @ApiModelProperty(notes = "toDate  of the reconciliation")
  private Date toDate;

  @ApiModelProperty(notes = "stage  of the reconciliation")
  private ReconciliationFlowStageEnum stage;

  @ApiModelProperty(notes = "remark  of the reconciliation")
  private String remark;

  private Set<ReconciliationElementForm> reconciliationElements;

  @ApiModelProperty(notes = "the amount of money in balance when it is started a cycle")
  private Long openingBalance;

  @ApiModelProperty(notes = "sum of fund in amount in a cycle")
  private Long sumOfFundInAmount;

  @ApiModelProperty(notes = "sum of fund out amount in a cycle")
  private Long sumOfFundOutAmount;

  private Set<ReconciliationRevertElementForm> reconciliationRevertElements;

  @ApiModelProperty(notes = "is a official reconciliation")
  private Boolean bolOfficial;
}
