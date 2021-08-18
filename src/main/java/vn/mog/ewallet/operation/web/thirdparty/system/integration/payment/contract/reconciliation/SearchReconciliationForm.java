package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
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
public class SearchReconciliationForm extends BaseSearchForm {

  String searchCustomer;
  List<CustomerTypeEnum> customerTypes;
  List<Long> customerIds;
  List<PaymentPolicyEnum> paymentPolicies;
  List<ServiceTypeEnum> serviceTypes;
  Integer month;
  Integer year;
  List<ReconciliationFlowStageEnum> stages;
  Integer cycle;
  Boolean bolOfficial;
}
