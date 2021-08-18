package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestCreateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestCreateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateResponse;

public interface ICustomerKycEndpoint {

  VerifyKycRequestCreateResponse createVerifyKycRequest(VerifyKycRequestCreateRequest request) throws FrontEndException;

  VerifyKycRequestFindResponse findVerifyKycRequest(VerifyKycRequestFindRequest request) throws FrontEndException;

  VerifyKycRequestStatusUpdateResponse updateVerifyKycRequestStatus(VerifyKycRequestStatusUpdateRequest request) throws FrontEndException;

  VerifyKycRequestStatusUpdateResponse updateVerifyKycEnterpriseRequestStatus(
      VerifyKycRequestStatusUpdateRequest request) throws FrontEndException;

  VerifyKycRequestGetDetailResponse getVerifyKycRequestGetDetail(VerifyKycRequestGetDetailRequest request) throws FrontEndException;

  UpdateIdentityResponse updateIdentity(UpdateIdentityRequest request) throws FrontEndException;

  VerifyKycRequestChangeResponse updateKYC (VerifyKycRequestChangeRequest request) throws FrontEndException;

  VerifyKycRequestChangeResponse updateEnterpriseKYC(VerifyKycRequestChangeRequest request)
      throws FrontEndException;



}
