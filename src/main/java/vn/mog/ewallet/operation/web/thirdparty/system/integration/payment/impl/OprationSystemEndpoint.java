package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IEpinPurchaseOrderEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IOperationSystemEndpoint;
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
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.KeyPairItem;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectResponse;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Service
public class OprationSystemEndpoint implements IOperationSystemEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.operation.system.lis-payment-channel}")
  private String URI_LIST_PAYMENT_CHANNEL;

  @Autowired
  OAuth2RestTemplate restTemplate;

  @SuppressWarnings("unchecked")
  @Override
  public List<KeyPairItem> listPaymentChannel() throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_LIST_PAYMENT_CHANNEL;
      log.info("REQ-URL: {}", endpoint);
      return restTemplate.getForEntity(endpoint, List.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
