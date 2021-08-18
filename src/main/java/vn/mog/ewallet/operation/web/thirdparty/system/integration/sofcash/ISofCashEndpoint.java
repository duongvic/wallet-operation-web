package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.AuthorisationCancelRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.AuthorisationCancelResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.AuthorisationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.AuthorisationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.CaptureCancelRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.CaptureCancelResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.CaptureRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.CaptureResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.BalanceInquiryRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.BalanceInquiryResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.CreatePaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.CreatePaymentInstrumentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.DeletePaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.DeletePaymentInstrumentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetBestPaymentInstrumentOfCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetBestPaymentInstrumentOfCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetPaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetPaymentInstrumentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetPaymentInstrumentsOfCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.GetPaymentInstrumentsOfCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.SearchPaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.SearchPaymentInstrumentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.UpdatePaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.UpdatePaymentInstrumentResponse;
import vn.mog.framework.security.api.CallerInformation;

import java.util.List;

public interface ISofCashEndpoint {

  GetPaymentInstrumentResponse getPaymentInstrument(GetPaymentInstrumentRequest request,
      CallerInformation callerInformation) throws Exception;

  GetPaymentInstrumentsOfCustomerResponse getPaymentInstrumentsOfCustomer(
      GetPaymentInstrumentsOfCustomerRequest request, CallerInformation callerInformation)
      throws Exception;

  GetBestPaymentInstrumentOfCustomerResponse getBestPaymentInstrumentOfCustomer(
      GetBestPaymentInstrumentOfCustomerRequest request, CallerInformation callerInformation)
      throws Exception;

  BalanceInquiryResponse getPaymentInstrumentBalance(BalanceInquiryRequest request,
      CallerInformation callerInformation) throws Exception;

  CreatePaymentInstrumentResponse createPaymentInstrument(CreatePaymentInstrumentRequest request,
      CallerInformation callerInformation) throws Exception;

  UpdatePaymentInstrumentResponse updatePaymentInstrument(UpdatePaymentInstrumentRequest request,
      CallerInformation callerInformation) throws Exception;

  DeletePaymentInstrumentResponse deletePaymentInstrument(DeletePaymentInstrumentRequest request,
      CallerInformation callerInformation) throws Exception;

  SearchPaymentInstrumentResponse searchPaymentInstruments(SearchPaymentInstrumentRequest request,
      CallerInformation callerInformation) throws Exception;


  AuthorisationResponse authorisation(AuthorisationRequest request,
      CallerInformation callerInformation) throws Exception;

  AuthorisationCancelResponse authorisationCancel(AuthorisationCancelRequest request,
      CallerInformation callerInformation) throws Exception;

  CaptureResponse capture(CaptureRequest request, CallerInformation callerInformation)
      throws Exception;

  CaptureCancelResponse captureCancel(CaptureCancelRequest request,
      CallerInformation callerInformation) throws Exception;

  StatsBalanceResponse statsBalance(StatsBalanceRequest request) throws FrontEndException;

  SummaryBalanceResponse customerBalances(SummaryBalanceRequest request) throws FrontEndException;

  String sendEmailReportBalanceCustomer() throws FrontEndException;

  String sendEmailReportBalanceCardStore() throws FrontEndException;

  String sendEmailReportBalanceCardStoreN02() throws FrontEndException;

  String sendEmailReportBalanceCardStoreOffline() throws FrontEndException;


}
