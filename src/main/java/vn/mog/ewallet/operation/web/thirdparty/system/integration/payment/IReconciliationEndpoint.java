package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.PricingDTO;

public interface IReconciliationEndpoint {

  GeneralResponse<Object> findReconciliations(SearchReconciliationForm request) throws FrontEndException;
  GeneralResponse<Object> changeWorkflowReconciliations(WorkflowActionRequest request) throws FrontEndException;
  GeneralResponse<Object> getReconciliation(Long id) throws FrontEndException;
  GeneralResponse<Object> exportExcelReconciliation(Long id) throws FrontEndException;
  GeneralResponse<Object> deleteReconciliation(Long id) throws FrontEndException;
  GeneralResponse<Object> updateReconciliation(ReconciliationForm request) throws Exception;
  GeneralResponse<Object> generateReconciliationByOps(GenerateReconciliationRequest request) throws Exception;
  GeneralResponse<Object> getProfile() throws Exception;
  GeneralResponse<Object> getProfileById(Long id) throws Exception;
  GeneralResponse<Object> searchProfile(SearchProfileForm request) throws Exception;
  PricingDTO searchPricing(ServicePricingForm request) throws Exception;
}
