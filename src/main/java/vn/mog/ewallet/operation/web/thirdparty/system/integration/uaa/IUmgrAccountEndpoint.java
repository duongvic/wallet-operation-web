package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerResponse;

public interface IUmgrAccountEndpoint {

  GetCustomerResponse getCustomer(GetCustomerRequest request) throws Exception;

  GetFullCustomerResponse getFullCustomer(GetFullCustomerRequest request) throws Exception;

  UpdateCustomerAttributeResponse saveOrUpdateAttribute(UpdateCustomerAttributeRequest request)
      throws Exception;

  DeleteCustomerAttributeResponse deleteCustomerAttribute(
      DeleteCustomerAttributeRequest request) throws Exception;

  UpdateCustomerResponse updateCustomer(UpdateCustomerRequest request) throws Exception;

  GetGoogleAuthenticatorInfoResponse getGoogleAuthenticatorInfo(
      GetGoogleAuthenticatorInfoRequest request) throws Exception;

  GenerateGoogleAuthenticatorResponse generateGoogleAuthenticatorInfo(
      GenerateGoogleAuthenticatorRequest request) throws Exception;

  ResetCredentialResponse resetCredential(ResetCredentialRequest request) throws Exception;

  CreateCustomerResponse createCustomer(CreateCustomerRequest request) throws Exception;

  UpdateAddressResponse createCustomerAddress(UpdateAddressRequest request) throws Exception;


  GetAttachmentResponse getAttachment(GetAttachmentRequest request) throws Exception;

  GetStoreS3UrlResponse getImageProfile(GetStoreS3UrlRequest request) throws Exception;

  FindEntityEventStoreByDateResponse findEventStore(FindEntityEventStoreByDateRequest request)
      throws Exception;

  SaleGetListAgentResponse saleGetAgents(SaleGetListAgentRequest request) throws Exception;

  UpdateImageProfileCustomerResponse updateProfileImage(
      UpdateImageProfileCustomerRequest request) throws Exception;

  /*BLOCK DICH VU CUSTOMER*/
  FindBlackListCustomerResponse findBlockCustomer(FindBlackListCustomerRequest request)
      throws Exception;

  AddBlackListCustomerResponse addBlock (AddBlackListCustomerRequest request) throws Exception;

 RemoveBlackListCustomerResponse removeBlockProviderService (RemoveBlackListCustomerRequest request) throws Exception;

  FindProviderByBlockCustomerResponse  findProviderByBlockCustomer (FindProviderByBlockCustomerRequest request) throws  Exception;

  FindProviderServicesByBlockCustomerResponse findProviderServiceByBlockCustomer (FindProviderServicesByBlockCustomerRequest request) throws  Exception;

  FindTrueServiceByBlockCustomerResponse findTrueServiceByBlockCustomer (FindTrueServiceByBlockCustomerRequest request) throws  Exception;

}
