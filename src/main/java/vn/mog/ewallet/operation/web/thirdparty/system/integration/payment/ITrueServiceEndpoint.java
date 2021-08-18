package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;


import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixResponse;


public interface ITrueServiceEndpoint {

  FindTrueServiceResponse findTrueServices(FindTrueServiceRequest request) throws FrontEndException;

  ChangeTrueServiceStatusResponse changeTrueServiceStatus(ChangeTrueServiceStatusRequest request) throws FrontEndException;

  CreateTrueServiceResponse createTrueService(CreateTrueServiceRequest request) throws FrontEndException;

  UpdateTrueServiceResponse updateTrueService(UpdateTrueServiceRequest request) throws FrontEndException;

  DeleteTrueServiceResponse deleteTrueService(DeleteTrueServiceRequest request) throws FrontEndException;

  GetTrueServiceResponse getTrueService(GetTrueServiceRequest request) throws FrontEndException;

  GetTrueServiceOperationResponse getTrueServiceOperation(GetTrueServiceOperationRequest request) throws FrontEndException;

  GetTrueServiceTransactionRuleMatrixResponse getTrueServiceTransactionRuleMatrix(GetTrueServiceTransactionRuleMatrixRequest request)
      throws FrontEndException;

  UpdateTrueServiceTransactionRuleMatrixResponse updateTrueServiceMatrix(UpdateTrueServiceTransactionRuleMatrixRequest request) throws FrontEndException;

  UpdateTrueServiceAttributeResponse updateTrueServiceAttribute(UpdateTrueServiceAttributeRequest request) throws FrontEndException;
}
