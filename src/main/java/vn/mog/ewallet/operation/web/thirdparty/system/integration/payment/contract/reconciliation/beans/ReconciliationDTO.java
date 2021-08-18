package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.PaymentPolicyEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconciliationFlowStageEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
import vn.mog.framework.common.utils.Utils;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReconciliationDTO implements Serializable {

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

  private Map<ServiceTypeEnum, List<ReconciliationElementDTO>> elementGroupByServiceType;

  private Long sumOfNetAmount = 0L;

  @ApiModelProperty(notes = "the amount of money in balance when it is started a cycle")
  private Long openingBalance = 0L;

  @ApiModelProperty(notes = "sum of fund in amount in a cycle")
  private Long sumOfFundInAmount = 0L;

  @ApiModelProperty(notes = "sum of fund out amount in a cycle")
  private Long sumOfFundOutAmount = 0L;

  private Set<ReconciliationRevertElementDTO> reconciliationRevertElements;

  @ApiModelProperty(notes = "is a official reconciliation")
  private Boolean bolOfficial;

  public String geKeyArr() {
    try {
      String keyArr = ", ";
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        keyArr += "%" + entry.getKey().name() + "%";
      }
      return keyArr;
    } catch (Exception e) {

    }
    return StringUtils.EMPTY;
  }

  public Long getTotalQuantity(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long quantity = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            quantity += Utils.convertToZeroIfNull(reconciliationElementDTO.getQuantity());
          }
          return quantity;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalRequestAmount(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long amount = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            amount += Utils.convertToZeroIfNull(reconciliationElementDTO.getSumOfRequestAmount());
          }
          return amount;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalFeeAmount(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long amount = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            amount += Utils.convertToZeroIfNull(reconciliationElementDTO.getSumOfFeeAmount());
          }
          return amount;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalFeeFixedAmount(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long amount = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            amount += Utils.convertToZeroIfNull(reconciliationElementDTO.getTotalUnitFeeFixedAmount());
          }
          return amount;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalCommissionAmount(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long amount = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            amount += Utils.convertToZeroIfNull(reconciliationElementDTO.getSumOfCommissionAmount());
          }
          return amount;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalNetAmount(ServiceTypeEnum serviceType) {
    try {
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          Long amount = 0L;
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            amount += Utils.convertToZeroIfNull(reconciliationElementDTO.getSumOfNetAmount());
          }
          return amount;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getAmountPartnerSpentInPeriod() {
    try {
      return getTotalRequestAmount(ServiceTypeEnum.BILL_PAYMENT)
              + getTotalFeeAmount(ServiceTypeEnum.BILL_PAYMENT)
              - getTotalCommissionAmount(ServiceTypeEnum.BILL_PAYMENT);
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalExtantAmount() {
    try {
      Long totalExtantAmount = 0L;
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        totalExtantAmount += getExtantBalance(entry.getKey());
      }
      return totalExtantAmount;
    } catch (Exception e) {
    }
    return 0L;
  }

  public Long getTotalRevert(ServiceTypeEnum serviceType) {
    try {
      Long totalRevert = 0L;
      for (ReconciliationRevertElementDTO reconciliationRevertElementDTO : reconciliationRevertElements) {
        if (reconciliationRevertElementDTO.getServiceType().name().equals(serviceType.name())) {
          totalRevert += Utils.convertToZeroIfNull(reconciliationRevertElementDTO.getRevertAmount());
        }
      }
      return totalRevert;
    } catch (Exception e) {
    }
    return 0L;
  }

  public Long getTotalTransactionFee(ServiceTypeEnum serviceType) {
    try {
      Long totalTransactionFee = 0L;
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            totalTransactionFee += Utils.convertToZeroIfNull(reconciliationElementDTO.getTotalTransactionFee());
          }
          return totalTransactionFee;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalEntitledFee(ServiceTypeEnum serviceType) {
    try {
      Long totalEntitledFee = 0L;
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            totalEntitledFee += Utils.convertToZeroIfNull(reconciliationElementDTO.getEntitledFee());
          }
          return totalEntitledFee;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalTransactionValue(ServiceTypeEnum serviceType) {
    try {
      Long totalTransactionValue = 0L;
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            totalTransactionValue += Utils.convertToZeroIfNull(reconciliationElementDTO.getSumOfRequestAmount());
          }
          return totalTransactionValue;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getTotalNumOfTransaction(ServiceTypeEnum serviceType) {
    try {
      Long totalNumOfTransaction = 0L;
      for (Map.Entry<ServiceTypeEnum, List<ReconciliationElementDTO>> entry : elementGroupByServiceType.entrySet()) {
        if (entry.getKey().name().equals(serviceType.name())) {
          for (ReconciliationElementDTO reconciliationElementDTO : entry.getValue()) {
            totalNumOfTransaction += Utils.convertToZeroIfNull(reconciliationElementDTO.getNumOfTrans());
          }
          return totalNumOfTransaction;
        }
      }
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getExtantBalance(ServiceTypeEnum serviceType) {
    try {
      return getTotalNetAmount(serviceType) - getTotalRevert(serviceType);
    } catch (Exception e) {

    }
    return 0L;
  }

  public Long getEndingBalance() {
    try {
      Long totalExtantAmount = getTotalExtantAmount();
      Long amount = Utils.convertToZeroIfNull(openingBalance)
              + Utils.convertToZeroIfNull(sumOfFundInAmount)
              - Utils.convertToZeroIfNull(sumOfFundOutAmount)
              - Utils.convertToZeroIfNull(totalExtantAmount)
              + getTotalCommissionAmount(ServiceTypeEnum.BILL_PAYMENT);
      return amount;
    } catch (Exception e) {
    }
    return 0L;
  }

  public Long getEndingBalanceBillPayment() {
    try {
      Long totalExtantAmount = getTotalExtantAmount();
      Long amount = Utils.convertToZeroIfNull(openingBalance)
              + Utils.convertToZeroIfNull(sumOfFundInAmount)
              - Utils.convertToZeroIfNull(sumOfFundOutAmount)
              - Utils.convertToZeroIfNull(totalExtantAmount)
              + getTotalCommissionAmount(ServiceTypeEnum.BILL_PAYMENT);
      return amount;
    } catch (Exception e) {
    }
    return 0L;
  }


}
