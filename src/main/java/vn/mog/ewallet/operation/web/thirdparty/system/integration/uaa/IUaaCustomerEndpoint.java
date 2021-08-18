package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa;


import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeBlacklistReasonResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeCredentialResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.FindAgentRequest;

public interface IUaaCustomerEndpoint {

  GetCustomerResponse getCustomer(GetCustomerRequest request) throws FrontEndException;

  FindFullCustomerResponse findCustomers(FindFullCustomerRequest request) throws FrontEndException;

  GetCustomerResponse getCustomerById(GetCustomerRequest request) throws Exception;


  /*Customer*/
  GetBlacklistReasonResponse getBlacklistReason(GetBlacklistReasonRequest request) throws Exception;

  ChangeBlacklistReasonResponse changeBlacklistReasonResponse(ChangeBlacklistReasonRequest request) throws Exception;

  GetAllCustomerTypeResponse getAllCustomerTypes(GetAllCustomerTypeRequest request) throws Exception;

  GetCustomerAttributeTypeResponse getCustomerAttributeTypes(GetCustomerAttributeTypeRequest request) throws Exception;

  ChangeCredentialResponse changePassWord(ChangeCredentialRequest request) throws Exception;

  FindUmgrCustomerResponse findAgentsByMsisdnCustomerSystem(FindAgentRequest request) throws Exception;

  AssignAgentResponse assignAgentsCustomerSystem(AssignAgentRequest request) throws Exception;


}
