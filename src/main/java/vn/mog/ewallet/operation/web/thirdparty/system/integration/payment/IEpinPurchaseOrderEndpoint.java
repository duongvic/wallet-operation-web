package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardResponse;
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


public interface IEpinPurchaseOrderEndpoint {

  CheckEpinPurchaseOrderResponse checkEpinPurchaseOrder(CheckEpinPurchaseOrderRequest request) throws FrontEndException;

  CreateEpinPurchaseOrderResponse createEpinPO(CreateEpinPurchaseOrderRequest request) throws FrontEndException;

  UpdateEpinPurchaseOrderResponse updateEpinPO(UpdateEpinPurchaseOrderRequest request) throws FrontEndException;

  GetEpinPurchaseOrderResponse getEpinPO(GetEpinPurchaseOrderRequest request) throws FrontEndException;

  FindEpinPurchaseOrderResponse findEpinPOs(FindEpinPurchaseOrderRequest request) throws FrontEndException;

  GetEpinPurchaseOrderAttachmentResponse getMPOAttachment(GetEpinPurchaseOrderAttachmentRequest request) throws FrontEndException;

  GetEpinPurchaseOrderOTPConfirmResponse getOTPConfirm(GetEpinPurchaseOrderOTPConfirmRequest request) throws FrontEndException;

  GetAvailableCardResponse getAvailableCard(GetAvailableCardRequest request) throws FrontEndException;

  BuyCardOfflineConfirmResponse buyCardOfflineConfirm(BuyCardOfflineConfirmRequest request) throws FrontEndException;

  GetReportEpinPurchaseOrderResponse getReportEpin(GetReportEpinPurchaseOrderRequest request) throws FrontEndException;

  OrderFlowApproveResponse epinPurchaseOrderWorkflowApporve(OrderFlowApproveRequest request) throws FrontEndException;

  OrderFlowRejectResponse epinPurchaseOrderWorkflowReject(OrderFlowRejectRequest request) throws FrontEndException;

  ExportEpinPurchaseOrderFormResponse exportEpoNewCreate (ExportEpinPurchaseOrderFormRequest request) throws Exception;

}
