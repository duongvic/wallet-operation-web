package vn.mog.ewallet.operation.web.controller;


import static org.apache.commons.lang.StringUtils.EMPTY;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.fundin.FundInController;
import vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController;
import vn.mog.ewallet.operation.web.controller.provider.ProviderController;
import vn.mog.ewallet.operation.web.controller.service.beans.TrueServiceTree;
import vn.mog.ewallet.operation.web.controller.translog.TransactionLogController;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.INotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.IAccessTokenEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.ISystemUserEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.ISofCashEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.IStockEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.ICustomerKycEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaCustomerEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUaaSystemEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUmgrAccountEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUmgrRolesPrivilegesEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.utils.CacheDataUtil;
import vn.mog.ewallet.operation.web.utils.ValidationUtil;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.HmacSHA256;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.security.api.CallerInformation;
import vn.mog.framework.security.api.ICallerUtils;


//@CrossOrigin // So that javascript can be hosted elsewhere
public abstract class AbstractController {

  public static final String PATH_ERROR = "/error";

  public static final Map<String, String> TRANSACTION_INQUIRY_STATUSES = new LinkedHashMap<>();
  public static final Map<String, String> WALLET_TRANSFER_STYPES = new LinkedHashMap<>();

  public static final Map<String, String> ACTOR_TYPES = new LinkedHashMap<>();

  protected static final String BTN_REQUEST = "request";
  protected static final String BTN_BACK = "back";
  protected static final String BTN_CANCEL = "cancel";
  protected static final String BTN_SUBMIT = "submit";
  protected static final String BTN_APPROVE = "approve";
  protected static final String BTN_REJECT = "reject";
  protected static final String BTN_SAVE = "save";
  protected static final String BTN_DELETE = "delete";
  protected static final String BTN_CONFIRM_OTP = "Approve";
  protected static final String BTN_EXPORT = "export";

  private static final Logger LOG = LogManager.getLogger(AbstractController.class);

  static {
    TRANSACTION_INQUIRY_STATUSES.put("INIT", "Init");
    TRANSACTION_INQUIRY_STATUSES.put("INPROCESS", "Inprocess");
    TRANSACTION_INQUIRY_STATUSES.put("RESOLVED", "Resolved");

    ACTOR_TYPES.put(StringUtils.EMPTY, "Specific");
    ACTOR_TYPES.put("PAYER", "Payer");
    ACTOR_TYPES.put("PAYEE", "Payee");

    WALLET_TRANSFER_STYPES.put("WALLET_TRANSFER", "Wallet Transfer");
    WALLET_TRANSFER_STYPES.put("FUND_TRANSFER", "Fund Transfer");
  }

  @Autowired
  protected IPaymentCustomerEndpoint walletCustomerEndpoint;

  //uaa
  @Autowired
  protected IUaaCustomerEndpoint customerEndpoint;
  @Autowired
  protected IUaaSystemEndpoint authSystemEndpoint;
  @Autowired
  protected IUmgrRolesPrivilegesEndpoint umgrRolesPrivilegesEndpoint;
  @Autowired
  protected IUmgrAccountEndpoint umgrAccountEndpoint;
  @Autowired
  protected ICustomerKycEndpoint customerKycEndpoint;

  //payment center
  @Autowired
  protected IPaymentCustomerEndpoint paymentCustomerEndpoint;
  @Autowired
  protected IEpinPurchaseOrderEndpoint epinEndpoint;
  @Autowired
  protected IProviderEndpoint providerEndpoint;
  @Autowired
  protected ISpecialProviderSenpayEndpoint specialProviderSenpayEndpoint;
  @Autowired
  protected IBillSenpayToolEndpoint billSenpayToolEndpoint;
  @Autowired
  protected ISpecialProviderKppViettelEndpoint specialProviderKppViettelEndpoint;
  @Autowired
  protected ITransactionEndpoint transactionEndpoint;
  @Autowired
  protected IPaymentSystemEndpoint paymentSystemEndpoint;
  @Autowired
  protected IWalletEndpoint walletEndpoint;
  @Autowired
  protected ITrueServiceEndpoint trueServiceEndpoint;
  @Autowired
  protected ITransactionStatisticEndpoint transactionStatisticEndpoint;
  @Autowired
  protected IReconciliationEndpoint reconciliationEndpoint;

  //Soft of cash
  @Autowired
  protected ISofCashEndpoint sofCashEndpoint;

  @Autowired
  protected IStockEndpoint stockEndpoint;

  @Autowired
  protected ICallerUtils callerUtil;
  @Autowired
  protected CacheDataUtil cacheDataUtil;
  @Autowired
  protected ValidationUtil validation;
  @Autowired
  ResourceUrlProvider resourceUrlProvider;

  //Notification service
  @Autowired
  protected INotificationEndpoint notificationEndpoint;

  @Autowired
  protected ISystemUserEndpoint systemUserEndpoint;

  @Autowired
  protected IAccessTokenEndpoint accessTokenEndpoint;


  @ModelAttribute("urls")
  public ResourceUrlProvider urls() {
    return this.resourceUrlProvider;
  }

  String redirectWalletRole() {
    Set<String> roles = WebUtil.getRolesOfUserLogin();

    boolean isAdmin = roles.contains(RoleConstants.ADMIN_OPERATION);
    boolean isFinance = roles.contains(RoleConstants.FINANCE) || roles.contains(
        RoleConstants.FINANCE_LEADER) || roles.contains(RoleConstants.FA_MANAGER);
    boolean isPlusCustomer = roles.contains(RoleConstants.MERCHANT) || roles.contains(
        RoleConstants.CUSTOMER) || roles.contains(RoleConstants.TELCO) || roles.contains(
        RoleConstants.AGGREATOR)
        || roles.contains(RoleConstants.BILLER);

    if (isFinance || isAdmin) {
      return FundInOrderController.FUND_IN_ORDER_LIST + "?menu=wal";

    } else if (isPlusCustomer) {
      return FundInController.FUND_IN_HISTORY_LIST
          + "?menu=wal" + "&" + FundInController.FUND_IN_HISTORY_DEFAULT_FILTER;

    } else {
      return TransactionLogController.TRANSACTION_LIST_ALL/*
          + "?menu=ser" + "&" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;
    }
  }

  String redirectProviderRole() {
    Set<String> roles = WebUtil.getRolesOfUserLogin();

    boolean isAdmin = roles.contains(RoleConstants.ADMIN_OPERATION);
    boolean isSaleSupport = roles.contains(RoleConstants.SALESUPPORT_MANAGER) || roles.contains(
        RoleConstants.SALESUPPORT_LEADER) || roles.contains(RoleConstants.SALESUPPORT);

    if (isSaleSupport || isAdmin) {
      return ProviderController.PROVIDER_LIST + "?menu=pro";

    } else {
      return TransactionLogController.TRANSACTION_LIST_ALL/*
          + "?menu=ser" + "&" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;
    }
  }

  protected String redirectServiceRole() {
    Set<String> roles = WebUtil.getRolesOfUserLogin();

    boolean isDefault = roles.contains(RoleConstants.ADMIN_OPERATION) || roles.contains(
        RoleConstants.SALE_DIRECTOR) || roles.contains(RoleConstants.SALE_ASM);
    boolean isReconciliation = roles.contains(RoleConstants.RECONCILIATION) || roles.contains(
        RoleConstants.RECONCILIATION_LEADER) || roles.contains(RoleConstants.ACCOUNTING)
        || roles.contains(RoleConstants.ACCOUNTING_LEADER);
    boolean isBusinessOperation =
        roles.contains(RoleConstants.CUSTOMERCARE_MANAGER) || roles.contains(
            RoleConstants.SALE_EXCUTIVE) || roles.contains(RoleConstants.TECH_SUPPORT);

    if (isDefault || isBusinessOperation || isReconciliation) {
      return TransactionLogController.TRANSACTION_LIST_ALL/*
          + "?menu=ser" + "&" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;

    } else {
      return TransactionLogController.TRANSACTION_LIST_ALL/*
          + "?menu=ser" + "&" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;
    }
  }


  protected ResponseEntity responseAjax(int coce, String value) {
    if (coce == 0) {
      return ResponseEntity.ok(new AjaxResponse(0, "Yêu cầu của bạn được thực hiện thành công!"));
    } else {
      return ResponseEntity.ok(new AjaxResponse(coce, value));
    }
  }

  public Date[] parseDateRange(String dateRange, boolean isDefaultFulltime) {
    try {
      Date currentDate = new Date();
      if (StringUtils.isNotBlank(dateRange)) {
        String fulltime = validation.notify("daterange.locale.fulltime.nosign");
        if (fulltime.equals(dateRange)) {
          return new Date[]{null, null};
        } else {
          String[] range = dateRange.split("-");
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          Date fromDate = df.parse(StringUtils.trimToEmpty(range[0]));
          Date endDate = df.parse(StringUtils.trimToEmpty(range[1]));
          return new Date[]{fromDate, endDate};
        }
      } else if (StringUtils.isBlank(dateRange) && isDefaultFulltime) {
        return new Date[]{null, null};
      } else {
        return new Date[]{DateUtil.getStartOfDay(currentDate), DateUtil.getEndOfDay(currentDate)};
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return new Date[]{null, null};
  }

  protected <T> List<T> getUploadAttachments(HttpServletRequest request, Object T)
      throws IOException {
    List<T> attachments = new ArrayList<>();
    MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
    List<MultipartFile> multiparts = multiPartRequest.getFiles("fileUpload");
    if (multiparts != null) {
      for (MultipartFile multipart : multiparts) {
        if (multipart != null && multipart.getBytes() != null && multipart.getBytes().length > 0) {

          String fileName = multipart.getOriginalFilename();
          byte[] bytes = multipart.getBytes();
          String contentType = multipart.getContentType();

          if (T instanceof FundOrderAttachment) {

            FundOrderAttachment attachment = (FundOrderAttachment) T;
            attachment.setName(fileName);
            attachment.setContentType(contentType);
            attachment.setContent(bytes);
            attachments.add((T) attachment);

          } else if (T instanceof WalletTransferOrderAttachment) {

            WalletTransferOrderAttachment attachment = new WalletTransferOrderAttachment();
            attachment.setName(fileName);
            attachment.setContentType(contentType);
            attachment.setContent(bytes);
            attachments.add((T) attachment);
          }
        }
      }
    }
    return attachments;
  }

  /**
   * Service
   */
  protected <T> List<TrueServiceTree> genderTrueServiceTree(List<T> services) {
    try {

      List<TrueServiceTree> tree = new ArrayList<>();
      TrueServiceTree node;
      TrueServiceTree preNode = new TrueServiceTree();

      String tempPathTreePoint, tempPathTreeUnder;

      CommissionFee trueService;
      for (T tempService : services) {
        trueService = (CommissionFee) tempService;

        node = new TrueServiceTree(trueService).extendCommistionFee(tempService);

        if (trueService.getLevel().equals(ServiceLevel.LEVEL_0.code)) {
          node.setPathTreeUnder(trueService.getServiceCode());
          node.setPathTreePoint(trueService.getServiceCode());

        } else {

          if (trueService.getLevel() > preNode.getLevel()) {
            node.setPathTreeUnder(
                preNode.getPathTreeUnder() + StringUtil.UNDER_LINE + trueService.getServiceCode());
            node.setPathTreePoint(
                preNode.getPathTreePoint() + StringUtil.POINT + trueService.getServiceCode());

            node.setPathParentUnder(preNode.getPathTreeUnder());
            node.setPathParentPoint(preNode.getPathTreePoint());

          } else if (trueService.getLevel().equals(preNode.getLevel())) {
            node.setPathTreeUnder(preNode.getPathParentUnder() + StringUtil.UNDER_LINE + trueService
                .getServiceCode());
            node.setPathTreePoint(
                preNode.getPathParentPoint() + StringUtil.POINT + trueService.getServiceCode());

            node.setPathParentUnder(preNode.getPathParentUnder());
            node.setPathParentPoint(preNode.getPathParentPoint());

          } else if (trueService.getLevel() < preNode.getLevel()) {
            tempPathTreeUnder = preNode.getPathParentUnder()
                .replace(StringUtil.UNDER_LINE + preNode.getParentServiceCode(), EMPTY);
            tempPathTreePoint = preNode.getPathParentPoint()
                .replace(StringUtil.POINT + preNode.getParentServiceCode(), EMPTY);

            node.setPathTreeUnder(tempPathTreeUnder + "_" + trueService.getServiceCode());
            node.setPathTreePoint(tempPathTreePoint + "." + trueService.getServiceCode());

            node.setPathParentUnder(tempPathTreeUnder);
            node.setPathParentPoint(tempPathTreePoint);
          }

        }
        preNode = node;
        tree.add(node);
      }
      return tree;
    } catch (RuntimeException e) {
      LOG.error("Service Tree", e);
      SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(MessageNotify.ERROR_CODE, "Dữ liệu ZO-TA SERVICE bị lỗi"));
      return Collections.emptyList();
    }
  }


  public List<String> getRolesOfCaller() {
    try {
      CallerInformation callerInformation = callerUtil.getCallerInformation();
      if (callerInformation != null) {
        return callerInformation.getActorPrivs();
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return null;
  }

  public List getListSaleByParent(List<String> customerRoleList) {
    List<Customer> customerList = null;
    try {
      // list Agents of Sale
//      if (customerRoleList.contains(RoleConstants.ADMIN_OPERATION) ||
//          customerRoleList.contains(RoleConstants.CUSTOMERCARE_MANAGER) ||
//          customerRoleList.contains(RoleConstants.CUSTOMERCARE) ||
//          customerRoleList.contains(RoleConstants.SALESUPPORT_MANAGER) ||
//          customerRoleList.contains(RoleConstants.SALESUPPORT) ||
//          customerRoleList.contains(RoleConstants.RECONCILIATION_LEADER) ||
//          customerRoleList.contains(RoleConstants.RECONCILIATION) ||
//          customerRoleList.contains(RoleConstants.FINANCE) ||
//          customerRoleList.contains(RoleConstants.FINANCESUPPORT_LEADER) ||
//          customerRoleList.contains(RoleConstants.FINANCE_SUPPORT) ||
//          customerRoleList.contains(RoleConstants.FINANCE_LEADER) ||
//          customerRoleList.contains(RoleConstants.FA_MANAGER) ||
//          customerRoleList.contains(RoleConstants.ACCOUNTING) ||
//          customerRoleList.contains(RoleConstants.ACCOUNTING_LEADER)) {
//        return cacheDataUtil.getCustomersByBiz();
//      } else if (isSale(customerRoleList)) {

        if (isSale(customerRoleList)) {
        SaleGetListAgentResponse saleGetListAgentResponse = umgrAccountEndpoint
            .saleGetAgents(new SaleGetListAgentRequest());
        if (saleGetListAgentResponse != null) {
          customerList = saleGetListAgentResponse.getCustomers();
        }
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
    return customerList;
  }
  
  public boolean isSale(List<String> customerRoleList) {
    if (customerRoleList.contains(RoleConstants.SALE_DIRECTOR)
        || customerRoleList.contains(RoleConstants.SALE_EXCUTIVE)
        || customerRoleList.contains(RoleConstants.SALE_SUPERVISOR)
        || customerRoleList.contains(RoleConstants.SALE_ASM)
        || customerRoleList.contains(RoleConstants.SALE_RSM))
      return true;
    return false;
  }

  public String getSign(Long timeStamp, String accessKey, String secretKey){
    String data = timeStamp + "|" + accessKey;
    HmacSHA256 hmacSHA256 = HmacSHA256.getInstance(secretKey);
    String hash = hmacSHA256.sign(data);
    return hash;
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  public Date convertToDateViaInstant(LocalDate dateToConvert) {
    return java.util.Date.from(dateToConvert.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
  }

  public String getHourViaString(Date date){
    String hh = date.getHours() < 10 ? "0" + date.getHours() : String.valueOf(date.getHours());
    String mm = date.getMinutes() < 10 ? "0" + date.getMinutes() : String.valueOf(date.getMinutes());
    String ss = date.getSeconds() < 10 ? "0" + date.getSeconds() : String.valueOf(date.getSeconds());
    return hh+":"+mm+":"+ss;
  }

  public String getHourViaStringForCurrentDate(Date date){
    String hh = date.getHours() < 10 ? "0" + (date.getHours() - 1) : String.valueOf(date.getHours() - 1);
    String mm = date.getMinutes() < 10 ? "0" + (date.getMinutes()) : String.valueOf(date.getMinutes());
    String ss = date.getSeconds() < 10 ? "0" + (date.getSeconds()): String.valueOf(date.getSeconds());
    return hh+":"+mm+":"+ss;
  }

}
