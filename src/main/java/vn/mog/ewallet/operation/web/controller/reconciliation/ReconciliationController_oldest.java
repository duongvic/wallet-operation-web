//package vn.mog.ewallet.operation.web.controller.reconciliation;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import vn.mog.ewallet.operation.web.constant.RoleConstants;
//import vn.mog.ewallet.operation.web.controller.AbstractController;
//import vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderStatus;
//import vn.mog.ewallet.operation.web.exception.FrontEndException;
//import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetReportEpinPurchaseOrderRequest;
//import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetReportEpinPurchaseOrderResponse;
//import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderDetail;
//import vn.mog.framework.common.utils.NumberUtil;
//
//@Controller
//@RequestMapping(value = "/wallet/balance-deduction-reconcilation-report")
//public class ReconciliationController_Copy extends AbstractController {
//
//  public static final String RECON_CONTROLLER = "/wallet/balance-deduction-reconcilation-report";
//  public static final String RECON_MERCHANT_LIST = RECON_CONTROLLER + "/merchant/list";
//  private static final Logger LOG = LogManager.getLogger(ReconciliationController_Copy.class);
//  private static final Integer TEN = 10000;
//  private static final Integer TWENTY = 20000;
//  private static final Integer THIRTY = 30000;
//  private static final Integer FIFTY = 50000;
//  private static final Integer HUNDRED = 100000;
//  private static final Integer TWO_HUNDRED = 200000;
//  private static final Integer THREE_HUNDRED = 300000;
//  private static final Integer FIVE_HUNDRED = 500000;
//
//  @RequestMapping(value = "/merchant/list", method = RequestMethod.GET)
//  @PreAuthorize(
//      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.ADMIN_OPERATION + "','" + "','" + RoleConstants.RECONCILIATION_LEADER + "','" + RoleConstants.RECONCILIATION + "')")
//  public String merchantLogs(HttpServletRequest request, ModelMap model) throws FrontEndException {
//
//    String paramRangeDate = StringUtils.trimToEmpty(request.getParameter("range"));
//    String[] paramCustomerIds = request.getParameterValues("customerId");
//    String paramStatus = StringUtils.trimToEmpty(request.getParameter("status"));
//    Date[] dates = parseDateRange(paramRangeDate, true);
//
//    GetReportEpinPurchaseOrderRequest epinPOrderRequest = new GetReportEpinPurchaseOrderRequest();
//    epinPOrderRequest.setFromDate(dates[0]);
//    epinPOrderRequest.setEndDate(dates[1]);
//
//    if (paramCustomerIds != null && paramCustomerIds.length > 0 && StringUtils.isNotEmpty(paramCustomerIds[0])) {
//      epinPOrderRequest.setCustomerIds(NumberUtil.convertListToLong(paramCustomerIds));
//    }
//
//    if (StringUtils.isNotEmpty(paramStatus)) {
//      epinPOrderRequest.setStatusIds(Collections.singletonList(paramStatus));
//    }
//
//    GetReportEpinPurchaseOrderResponse epinPOrderResponse = epinEndpoint.getReportEpin(epinPOrderRequest);
//    List<EpinPurchaseOrderDetail> epinPoDetails = epinPOrderResponse.getEpinPurchaseOrderDetails();
//
//    HashMap<String, List<EpinPurchaseOrderDetail>> hashMap = new HashMap<>();
//    List<EpinPurchaseOrderDetail> tempDetails;
//
//    for (EpinPurchaseOrderDetail item : epinPoDetails) {
//      String cardType = item.getCardType();
//      if (hashMap.containsKey(cardType)) {
//        try {
//
//          List<EpinPurchaseOrderDetail> merchantPurchaseOrderDetailItems = new ArrayList<>(hashMap.get(cardType));
//
////          List<EpinPurchaseOrderDetail> merchantPurchaseOrderDetailItems = hashMap.get(cardType);
//          merchantPurchaseOrderDetailItems.add(item);
//        }catch (Exception e){
//          LOG.error(e.getMessage());
//        }
//
//      } else {
//        tempDetails = Collections.singletonList(item);
//        hashMap.put(cardType, tempDetails);
//      }
//    }
//
//    for (Map.Entry<String, List<EpinPurchaseOrderDetail>> item : hashMap.entrySet()) {
//      List<EpinPurchaseOrderDetail> tempEpins = item.getValue();
//      String cardType = item.getKey();
//      List<EpinPurchaseOrderDetail> epinPurchaseOrderDetails = initDefaultCharRowItem(tempEpins, cardType);
//      if (!epinPurchaseOrderDetails.isEmpty()) {
//        EpinPurchaseOrderDetail.order(epinPurchaseOrderDetails);
//      }
//      hashMap.put(cardType, epinPurchaseOrderDetails);
//    }
//
//    int totalItem = 1;
//    long totalFacevalue = 0;
//    long totalQuantity = 0;
//    for (EpinPurchaseOrderDetail item : epinPoDetails) {
//      totalItem++;
//      totalFacevalue = totalFacevalue + item.getFaceValue();
//      totalQuantity = totalQuantity + item.getQuantity();
//    }
//
//    StringBuilder sb = new StringBuilder();
//    if (paramCustomerIds != null) {
//      boolean first = true;
//      for (String item : paramCustomerIds) {
//        if (first) {
//          sb.append(item);
//          first = false;
//        } else {
//          sb.append(", " + item);
//        }
//      }
//    }
//
//    model.put("hashMap", hashMap);
//    model.put("totalItem", NumberUtil.formatNumber(totalItem));
//    model.put("totalFacevalue", NumberUtil.formatNumber(totalFacevalue));
//    model.put("totalQuantity", NumberUtil.formatNumber(totalQuantity));
//
//    model.put("status", paramStatus);
//
//    model.put("totalMerchant", sb.toString());
//    model.put("searchRange", paramRangeDate);
//    model.put("totalMpo", epinPOrderResponse.getQuantityOfEpin());
//    model.put("totalMpoCardQuantity", NumberUtil.formatNumber(epinPOrderResponse.getTotalOfCardQuantities()));
//    model.put("totalMpoFaceValue", NumberUtil.formatNumber(epinPOrderResponse.getTotalOfFaceValues()) + " VND");
//
//    model.put("customers", cacheDataUtil.getCustomersByBiz());
//    model.put("statuses", EpinPurchaseOrderStatus.values());
//    model.put("listStatus", EpinPurchaseOrderStatus.values());
//
//    return "/reconciliation/merchant-report";
//  }
//
//  private List<EpinPurchaseOrderDetail> initDefaultCharRowItem(List<EpinPurchaseOrderDetail> tempDetails, String cardType) {
//
//    HashMap<Integer, EpinPurchaseOrderDetail> hashfullTempDetail = new HashMap<>();
//
//    List<EpinPurchaseOrderDetail> epinPoDetails = new ArrayList<>();
//
//    hashfullTempDetail.put(TEN, new EpinPurchaseOrderDetail(cardType, TEN, 0, 0L));
//    hashfullTempDetail.put(TWENTY, new EpinPurchaseOrderDetail(cardType, TWENTY, 0, 0L));
//    hashfullTempDetail.put(THIRTY, new EpinPurchaseOrderDetail(cardType, THIRTY, 0, 0L));
//    hashfullTempDetail.put(FIFTY, new EpinPurchaseOrderDetail(cardType, FIFTY, 0, 0L));
//    hashfullTempDetail.put(HUNDRED, new EpinPurchaseOrderDetail(cardType, HUNDRED, 0, 0L));
//    hashfullTempDetail.put(TWO_HUNDRED, new EpinPurchaseOrderDetail(cardType, TWO_HUNDRED, 0, 0L));
//    hashfullTempDetail.put(THREE_HUNDRED, new EpinPurchaseOrderDetail(cardType, THREE_HUNDRED, 0, 0L));
//    hashfullTempDetail.put(FIVE_HUNDRED, new EpinPurchaseOrderDetail(cardType, FIVE_HUNDRED, 0, 0L));
//
//    for (EpinPurchaseOrderDetail item : tempDetails) {
//      if (TEN.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(TEN, item);
//
//      } else if (TWENTY.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(TWENTY, item);
//
//      } else if (THIRTY.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(THIRTY, item);
//
//      } else if (FIFTY.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(FIFTY, item);
//
//      } else if (HUNDRED.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(HUNDRED, item);
//
//      } else if (TWO_HUNDRED.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(TWO_HUNDRED, item);
//
//      } else if (THREE_HUNDRED.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(THREE_HUNDRED, item);
//
//      } else if (FIVE_HUNDRED.equals(item.getFaceValue())) {
//        hashfullTempDetail.put(FIVE_HUNDRED, item);
//      }
//    }
//
//    for (Map.Entry<Integer, EpinPurchaseOrderDetail> item : hashfullTempDetail.entrySet()) {
//      epinPoDetails.add(item.getValue());
//    }
//    return epinPoDetails;
//  }
//}
