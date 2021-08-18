package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;


import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderServiceStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderServiceStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ChangeProviderStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesMatchingWithTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesMatchingWithTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreResponse;


public interface IProviderEndpoint {

  FindProviderResponse findProviders(FindProviderRequest request) throws FrontEndException;

  FindProviderProfileResponse findProviderProfiles(FindProviderProfileRequest request)
      throws FrontEndException;

  ChangeProviderStatusResponse changeProviderStatus(ChangeProviderStatusRequest request) throws FrontEndException;

  UpdateProviderResponse updateProvider(UpdateProviderRequest request) throws FrontEndException;

  GetProviderResponse getProvider(GetProviderRequest request) throws FrontEndException;
  
  GetProviderServicesMatchingWithTrueServiceResponse getProviderServicesMatchingWithTrueService(
      GetProviderServicesMatchingWithTrueServiceRequest request) throws FrontEndException;

  ChangeProviderServiceStatusResponse changeProviderServiceStatus(ChangeProviderServiceStatusRequest request) throws FrontEndException;

  GetProviderServicesByProviderCodeResponse getProviderServicesOfProvider(GetProviderServicesByProviderCodeRequest request) throws FrontEndException;
  
  UpdateProviderServiceRankingScoreResponse updateProviderServiceRankingScore(UpdateProviderServiceRankingScoreRequest request) throws FrontEndException;
  
  ResetProviderServiceRankingScoreResponse resetProviderServiceRankingScore(ResetProviderServiceRankingScoreRequest request) throws FrontEndException;
  
  FundInProviderResponse fundInProvider(FundInProviderRequest request) throws FrontEndException;
}
