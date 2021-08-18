package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.ISofCashEndpoint;
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
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.security.api.CallerInformation;

@Slf4j
@Service
public class SofCashEndpoint implements ISofCashEndpoint {

  @Autowired OAuth2RestTemplate restTemplate;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-get}")
  private String URI_PI_GET;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-get-pis-of-customer}")
  private String URI_PI_GET_PIS_OF_CUSTOMER;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-best-pi-of-customer}")
  private String URI_PI_BEST_PIS_OF_CUSTOMER;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-balance-inquiry}")
  private String URI_PI_BALANCE_INQUIRY;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-create}")
  private String URI_PI_CREATE;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-update}")
  private String URI_PI_UPDATE;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-delete}")
  private String URI_PI_DELETE;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-search}")
  private String URI_PI_SEARCH;

  @Value("${platform.backend.sofcash.service.3rd.url.payment-authorise}")
  private String URI_PAYMENT_AUTHORISE;

  @Value("${platform.backend.sofcash.service.3rd.url.payment-authorisation-cancel}")
  private String URI_PAYMENT_AUTHORISATION_CANCEL;

  @Value("${platform.backend.sofcash.service.3rd.url.payment-capture}")
  private String URI_PAYMENT_CAPTURE;

  @Value("${platform.backend.sofcash.service.3rd.url.payment-capture-cancel}")
  private String URI_PAYMENT_CAPTURE_CANCEL;

  @Value("${platform.backend.sofcash.service.3rd.url.report-stats-balances}")
  private String URI_REPORT_STATS_BALANCES;

  @Value("${platform.backend.sofcash.service.3rd.url.report-customer-balances}")
  private String URI_REPORT_CUSTOMER_BALANCES;

  // -------send-email-report balance----------------
  @Value(
      "${platform.backend.centralization.job.service.3rd.url.send-email-report-customer-balances}")
  private String URI_SEND_EMAIL_REPORT_CUSTOMER_BALANCES;

  @Value(
      "${platform.backend.centralization.job.service.3rd.url.send-email-report-card-store-balances}")
  private String URI_SEND_EMAIL_REPORT_CARD_STORE_BALANCES;

  @Value(
      "${platform.backend.centralization.job.service.3rd.url.send-email-report-card-store-n02-balances}")
  private String URI_SEND_EMAIL_REPORT_CARD_STORE_N02_BALANCES;

  @Value(
      "${platform.backend.centralization.job.service.3rd.url.send-email-report-offline-card-store-balances}")
  private String URI_SEND_EMAIL_REPORT_CARD_STORE_OFFLINE_BALANCES;
  // --------------------------------------------------------

  @Override
  public GetPaymentInstrumentResponse getPaymentInstrument(
      GetPaymentInstrumentRequest request, CallerInformation callerInformation) throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_GET;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetPaymentInstrumentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetPaymentInstrumentsOfCustomerResponse getPaymentInstrumentsOfCustomer(
      GetPaymentInstrumentsOfCustomerRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_GET_PIS_OF_CUSTOMER;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetPaymentInstrumentsOfCustomerResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetBestPaymentInstrumentOfCustomerResponse getBestPaymentInstrumentOfCustomer(
      GetBestPaymentInstrumentOfCustomerRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_BEST_PIS_OF_CUSTOMER;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetBestPaymentInstrumentOfCustomerResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public BalanceInquiryResponse getPaymentInstrumentBalance(
      BalanceInquiryRequest request, CallerInformation callerInformation) throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_BALANCE_INQUIRY;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, BalanceInquiryResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreatePaymentInstrumentResponse createPaymentInstrument(
      CreatePaymentInstrumentRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_CREATE;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreatePaymentInstrumentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdatePaymentInstrumentResponse updatePaymentInstrument(
      UpdatePaymentInstrumentRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_UPDATE;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdatePaymentInstrumentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public DeletePaymentInstrumentResponse deletePaymentInstrument(
      DeletePaymentInstrumentRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_DELETE;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, DeletePaymentInstrumentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SearchPaymentInstrumentResponse searchPaymentInstruments(
      SearchPaymentInstrumentRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PI_SEARCH;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SearchPaymentInstrumentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public AuthorisationResponse authorisation(
      AuthorisationRequest request, CallerInformation callerInformation) throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PAYMENT_AUTHORISE;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, AuthorisationResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public AuthorisationCancelResponse authorisationCancel(
      AuthorisationCancelRequest request, CallerInformation callerInformation) throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PAYMENT_AUTHORISATION_CANCEL;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, AuthorisationCancelResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CaptureResponse capture(CaptureRequest request, CallerInformation callerInformation)
      throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PAYMENT_CAPTURE;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, CaptureResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CaptureCancelResponse captureCancel(
      CaptureCancelRequest request, CallerInformation callerInformation) throws Exception {
    // ----------------------------------
    try {
      String requestURI = URI_PAYMENT_CAPTURE_CANCEL;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, CaptureCancelResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public StatsBalanceResponse statsBalance(StatsBalanceRequest request) throws FrontEndException {
    try {
      String requestURI = URI_REPORT_STATS_BALANCES;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, StatsBalanceResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public SummaryBalanceResponse customerBalances(SummaryBalanceRequest request)
      throws FrontEndException {
    try {
      String requestURI = URI_REPORT_CUSTOMER_BALANCES;
      String endpoint = PLATFORM_BACKEND_SOFCASH_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, SummaryBalanceResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public String sendEmailReportBalanceCustomer() throws FrontEndException {
    try {
      String requestURI = URI_SEND_EMAIL_REPORT_CUSTOMER_BALANCES;
      String endpoint = PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", "");
      return restTemplate.getForEntity(endpoint, String.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public String sendEmailReportBalanceCardStore() throws FrontEndException {
    try {
      String requestURI = URI_SEND_EMAIL_REPORT_CARD_STORE_BALANCES;
      String endpoint = PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", "");
      return restTemplate.getForEntity(endpoint, String.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public String sendEmailReportBalanceCardStoreN02() throws FrontEndException {
    try {
      String requestURI = URI_SEND_EMAIL_REPORT_CARD_STORE_N02_BALANCES;
      String endpoint = PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", "");

      return restTemplate.getForEntity(endpoint, String.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public String sendEmailReportBalanceCardStoreOffline() throws FrontEndException {
    try {
      String requestURI = URI_SEND_EMAIL_REPORT_CARD_STORE_OFFLINE_BALANCES;
      String endpoint = PLATFORM_BACKEND_CENTRALIZATION_JOB_SERVICE_API_ENDPOINT + requestURI;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", "");

      return restTemplate.getForEntity(endpoint, String.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
