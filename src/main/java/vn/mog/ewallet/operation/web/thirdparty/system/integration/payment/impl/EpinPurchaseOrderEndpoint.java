package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.BuyCardOfflineConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.BuyCardOfflineConfirmResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CheckEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CheckEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CreateEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CreateEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.ExportEpinPurchaseOrderFormRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.ExportEpinPurchaseOrderFormResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.FindEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.FindEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderOTPConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderOTPConfirmResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetReportEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetReportEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.UpdateEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.UpdateEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class EpinPurchaseOrderEndpoint implements IEpinPurchaseOrderEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.customer-checkMerchantPurchaseOrder}")
  private String URI_CHECK_WALLET_EPIN;

  @Value("${platform.backend.payment.service.3rd.url.customer-createMerchantPO}")
  private String URI_CREATE_EPIN;

  @Value("${platform.backend.payment.service.3rd.url.customer-updateMerchantPO}")
  private String URI_UPDATE_EPIN;

  @Value("${platform.backend.payment.service.3rd.url.customer-getMerchantPO}")
  private String URI_GET_EPIN;

  @Value("${platform.backend.payment.service.3rd.url.customer-getMPOAttachment}")
  private String URI_GET_EPIN_ATTACHMENT;

  @Value("${platform.backend.payment.service.3rd.url.customer-findMerchantPO}")
  private String URI_FIND_EPIN;

  @Value("${platform.backend.payment.service.3rd.url.customer-getOTPConfirm}")
  private String URI_GET_OTP_CONFIRM;

  @Value("${platform.backend.payment.service.3rd.url.customer-getAvailableCard}")
  private String URI_GET_AVAILABLE_CARD;

  @Value("${platform.backend.payment.service.3rd.url.customer-buyCardOfflineConfirm}")
  private String URI_BUY_CARD_OFFLINE_CONFIRM;

  @Value("${platform.backend.payment.service.3rd.url.customer-getMerchantPOReport}")
  private String URI_GET_EPIN_REPORT;

  @Value("${platform.backend.payment.service.3rd.url.customer-epinPurchaseOrder-workflow-approve}")
  private String URI_EPIN_FLOW_APPROVE;

  @Value("${platform.backend.payment.service.3rd.url.customer-epinPurchaseOrder-workflow-reject}")
  private String URI_EPIN_FLOW_REJECT;

  @Value("${platform.backend.payment.service.3rd.url.customer-epinPurchaseOrder-exportRequestForm}")
  private String URI_EXPORT_NEW_EPIN;

  @Autowired OAuth2RestTemplate restTemplate;

  @Override
  public CheckEpinPurchaseOrderResponse checkEpinPurchaseOrder(
      CheckEpinPurchaseOrderRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_CHECK_WALLET_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CheckEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public CreateEpinPurchaseOrderResponse createEpinPO(CreateEpinPurchaseOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_CREATE_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, CreateEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public UpdateEpinPurchaseOrderResponse updateEpinPO(UpdateEpinPurchaseOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_UPDATE_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, UpdateEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetEpinPurchaseOrderResponse getEpinPO(GetEpinPurchaseOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetEpinPurchaseOrderAttachmentResponse getMPOAttachment(
      GetEpinPurchaseOrderAttachmentRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_EPIN_ATTACHMENT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetEpinPurchaseOrderAttachmentResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetEpinPurchaseOrderOTPConfirmResponse getOTPConfirm(
      GetEpinPurchaseOrderOTPConfirmRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_OTP_CONFIRM;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetEpinPurchaseOrderOTPConfirmResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetAvailableCardResponse getAvailableCard(GetAvailableCardRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_AVAILABLE_CARD;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetAvailableCardResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public BuyCardOfflineConfirmResponse buyCardOfflineConfirm(BuyCardOfflineConfirmRequest request)
      throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_BUY_CARD_OFFLINE_CONFIRM;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, BuyCardOfflineConfirmResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindEpinPurchaseOrderResponse findEpinPOs(FindEpinPurchaseOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetReportEpinPurchaseOrderResponse getReportEpin(GetReportEpinPurchaseOrderRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_EPIN_REPORT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, GetReportEpinPurchaseOrderResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public OrderFlowApproveResponse epinPurchaseOrderWorkflowApporve(OrderFlowApproveRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_EPIN_FLOW_APPROVE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, OrderFlowApproveResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public OrderFlowRejectResponse epinPurchaseOrderWorkflowReject(OrderFlowRejectRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_EPIN_FLOW_REJECT;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, OrderFlowRejectResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public ExportEpinPurchaseOrderFormResponse exportEpoNewCreate(
      ExportEpinPurchaseOrderFormRequest request) throws Exception {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_EXPORT_NEW_EPIN;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, ExportEpinPurchaseOrderFormResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
