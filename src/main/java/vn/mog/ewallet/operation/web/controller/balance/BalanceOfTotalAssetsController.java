package vn.mog.ewallet.operation.web.controller.balance;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.FindBankAccountsRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.FindBankAccountsResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.BankHistory;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.CreateBankHistoryRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.CreateBankHistoryResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.BankCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.TransactionType;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.contract.base.KeyValue;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import vn.mog.framework.contract.base.MobiliserResponseType.Status;


@Controller
@RequestMapping(value = "/wallet/balance/total-assets")
public class BalanceOfTotalAssetsController extends AbstractController {

  public static final String BALANCE_TOTAL_ASSETS_CONTROLLER = "/wallet/balance/total-assets";
  public static final String BALANCE_TOTAL_OF_ASSETS_HISTORY = BALANCE_TOTAL_ASSETS_CONTROLLER + "/history";

  //-------------------HISTORY---------------------------------------
  public static final String BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE =
      BALANCE_TOTAL_OF_ASSETS_HISTORY + "/create";
  public static final String BALANCE_TOTAL_OF_ASSETS_HISTORY_DETAIL =
      BALANCE_TOTAL_OF_ASSETS_HISTORY + "/detail";
  public static final String BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT =
      BALANCE_TOTAL_OF_ASSETS_HISTORY + "/edit";

  public static final String PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY =
      "/balance/act_balance_total_assets_history";
  public static final String PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE =
      PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY + "/create";
  public static final String PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_DETAIL =
      PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY + "/detail";
  public static final String PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT =
      PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY + "/edit";


  //---------------------SỐ DƯ-----------------------------------------
  public static final String BALANCE_TOTAL_OF_ASSETS_STATICS_DETAIL =
      BALANCE_TOTAL_ASSETS_CONTROLLER + "/detail-static";

  public static final String PATH_BALANCE_TOTAL_OF_ASSETS_DETAIL =
      "/balance/act_balance_total_assets/detail";
  //--------------------------------------------------------------------

  private static final Logger logger = LogManager.getLogger(BalanceOfTotalAssetsController.class);

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',"
      + "'ACCOUNTING',"
      + "'SALESUPPORT_MANAGER' , 'SALESUPPORT', 'SALE_EXCUTIVE','SALE_AGENT',"
      + "'CUSTOMERCARE_MANAGER',"
      + "'RECONCILIATION_LEADER','RECONCILIATION')")
  public String balanceTotalOfAssets(HttpServletRequest request, ModelMap map) throws Exception {

    try {
      map.put("listBank", getProfileBank());
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
    return "/balance/balance_total_assets";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/history", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',"
      + "'ACCOUNTING',"
      + "'SALESUPPORT_MANAGER' , 'SALESUPPORT', 'SALE_EXCUTIVE','SALE_AGENT',"
      + "'CUSTOMERCARE_MANAGER',"
      + "'RECONCILIATION_LEADER','RECONCILIATION')")
  public String balanceTotalOfAssetsHistory(HttpServletRequest request, ModelMap map) throws Exception {
    try {
      map.put("listBank", getProfileBank());
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
    return "/balance/balance_total_assets_history";
  }


  private List<KeyValue> getProfileBank() {
    try {
      return stockEndpoint.getBankCode();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }

//
//  private List<BankProfile> getBankAccount() {
//    try {
//      FindBankProfileRequest profileRequest = new FindBankProfileRequest();
//      return walletEndpoint.getProfileBanks(profileRequest).getBankProfiles();
//    } catch (FrontEndException e) {
//      logger.error(e.getMessage(), e);
//      return Collections.emptyList();
//    }
//  }

  @RequestMapping(value = "/findBankAccount", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findBankAccount(HttpServletRequest request, ModelMap map) throws FrontEndException {

    try {
      int offset = 0;
      int limit = 20;

      String[] paramBankCodes = request.getParameterValues(("prmBankCodes[]"));

      FindBankAccountsRequest findBankAccountsRequest = new FindBankAccountsRequest();

      List<BankCode> lstString = new ArrayList<>();
      if (paramBankCodes != null && paramBankCodes.length > 0 && StringUtils.isNotEmpty(paramBankCodes[0])) {
        for (String string : paramBankCodes) {
          lstString.add(BankCode.valueOf(string));
        }
        findBankAccountsRequest.setBankCodes(lstString);
      } else {
        findBankAccountsRequest.setBankCodes(BankCode.BANK_CODES_ENUM);
      }

      findBankAccountsRequest.setLimit(limit);
      findBankAccountsRequest.setOffset(offset);


      FindBankAccountsResponse findBankAccountsResponse = stockEndpoint.findBankAccount(findBankAccountsRequest);

//      map.put("listBankAccount", findBankAccountsResponse.getBankAccounts());
      return ResponseEntity.ok(findBankAccountsResponse);

    } catch (Exception e) {
      logger.error("findBankAccountStock", e);
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @RequestMapping(value = "/history/create", method = RequestMethod.GET)
  public String create(HttpServletRequest request, ModelMap model) {
    model.put("listBank", getProfileBank());
    return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE;
  }

  @RequestMapping(value = "/history/create/using-post", method = RequestMethod.POST)
  public String createByPost(HttpServletRequest request, ModelMap model) {
//    ----------------------------------------------------------
    String createDate = request.getParameter("create-date");
    String createTime = request.getParameter("create-time");
    String bank = request.getParameter("bankCodes");
    String bankNumber = request.getParameter("bankAccountIds");
    String balanceChange = request.getParameter("balance-change");
    String moneys = StringUtils.trimToEmpty(request.getParameter("moneys"));
    String balance = StringUtils.trimToEmpty(request.getParameter("balance"));
    String bankRef = StringUtils.trimToEmpty(request.getParameter("bankRef"));
    String note = StringUtils.trimToEmpty(request.getParameter("note"));

    String txnDt = createDate + " " + createTime;
    Date txnDate = DateUtil.stringToDate(txnDt, "dd/MM/yyyy HH:mm:ss");
    TransactionType fluctuation = null;
    String increase = "CREDIT";
    if (balanceChange.equals(increase)) {
      fluctuation = TransactionType.CREDIT;
    } else {
      fluctuation = TransactionType.DEBIT;
    }
    moneys = moneys.replace(".", "");
    balance = balance.replace(".", "");
//    --------------------------------------------------------------
    CreateBankHistoryRequest createBankHistoryRequest = new CreateBankHistoryRequest();
    BankHistory bankHistory = new BankHistory();
    bankHistory.setTxnDate(txnDate);
    bankHistory.setBankAccount(bankNumber);
    bankHistory.setBankCode(BankCode.valueOf(bank));
    bankHistory.setTxnType(fluctuation);
    bankHistory.setAmount(Long.valueOf(moneys));
    bankHistory.setPostBalance(Long.valueOf(balance));
    if (bankRef != null && !bankRef.isEmpty()) {
      bankHistory.setBankRefTxn(bankRef);
    }
    bankHistory.setInfo(note);
    createBankHistoryRequest.setBankHistory(bankHistory);
//    ----------------------------------------------------------------
    try {
      CreateBankHistoryResponse createBankHistoryResponse = stockEndpoint
          .createBankHistory(createBankHistoryRequest);
      if (createBankHistoryResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        model.put("codeErr", createBankHistoryResponse.getStatus().getCode());
        model.put("mesErr", createBankHistoryResponse.getStatus().getValue());
        return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE;
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      String error = e.getResponseBodyAsString();
      logger.error(error, e);
      model.put("codeErr", 1);
      model.put("mesErr", error);
      return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      model.put("codeErr", 1);
      model.put("mesErr", e.getMessage());
      return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_CREATE;
    }

    return "redirect:" + BALANCE_TOTAL_OF_ASSETS_HISTORY + "?codeErr=0&mesErr=Success";
  }

  @RequestMapping(value = "/findBankAccountOnBank", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findBankAccountOnBank(HttpServletRequest request)
      throws FrontEndException {

    try {
      int offset = 0;
      int limit = 20;

      String[] paramBankCodes = request.getParameterValues(("bankCode"));
      String[] paramBankAcc = request.getParameterValues(("bankAcc"));

      FindBankAccountsRequest findBankAccountsRequest = new FindBankAccountsRequest();

      List<BankCode> lstString = new ArrayList<>();
      if (paramBankCodes != null && paramBankCodes.length > 0 && StringUtils
          .isNotEmpty(paramBankCodes[0])) {
        for (String string : paramBankCodes) {
          lstString.add(BankCode.valueOf(string));
        }
        findBankAccountsRequest.setBankCodes(lstString);
      } else {
        findBankAccountsRequest.setBankCodes(BankCode.BANK_CODES_ENUM);
      }
      if (paramBankAcc != null && paramBankAcc.length > 0 && StringUtils
          .isNotEmpty(paramBankAcc[0])) {
        List<String> bankAccs = new ArrayList<>();
        bankAccs.add(paramBankAcc[0]);
        findBankAccountsRequest.setBankAccounts(bankAccs);
      }

      findBankAccountsRequest.setLimit(limit);
      findBankAccountsRequest.setOffset(offset);

      FindBankAccountsResponse findBankAccountsResponse = stockEndpoint
          .findBankAccount(findBankAccountsRequest);

      return ResponseEntity.ok(findBankAccountsResponse);

    } catch (Exception e) {
      logger.error("findBankAccountStock", e);
      FindBankAccountsResponse findBankAccountsResponse = new FindBankAccountsResponse();
      findBankAccountsResponse.setBankAccounts(Collections.emptyList());
      Status status = new Status(400, e.getMessage());
      findBankAccountsResponse.setStatus(status);
      return ResponseEntity.ok(findBankAccountsResponse);
    }
  }

  @RequestMapping(value = "/detail-static/{bankCode}/{bankAccount}", method = RequestMethod.GET)
  public String detailStatics(HttpServletRequest request, ModelMap model,
                              @PathVariable("bankCode") String bankCode, @PathVariable("bankAccount") String bankAccount) {
    try {

      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      List<String> paramBankCodes = Arrays.asList(bankCode);
      List<String> paramBankAccount = Arrays.asList(bankAccount);

      BankBalanceStatisticRequest balanceStatisticRequest = new BankBalanceStatisticRequest();

      Date[] dates = parseDateRange(paramDateRange, false);
      balanceStatisticRequest.setBeginDatePeriod(dates[0]);
      balanceStatisticRequest.setEndDatePeriod(dates[1]);

      int offset = 0;
      int limit = 20;
      int totalFiltered = 0;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      balanceStatisticRequest.setOffset(offset);
      balanceStatisticRequest.setLimit(limit);


      List<BankCode> lstString = new ArrayList<>();
      if (paramBankCodes != null) {
        for (String string : paramBankCodes) {
          lstString.add(BankCode.valueOf(string));
        }
        balanceStatisticRequest.setBankCodes(lstString);
      } else {
        balanceStatisticRequest.setBankCodes(BankCode.BANK_CODES_ENUM);
      }

      if (paramBankAccount != null) {
        balanceStatisticRequest.setBankAccounts(Arrays.asList(bankAccount));
      }


      BankBalanceStatisticResponse bankBalanceStatisticResponse = stockEndpoint.findBankBlanceStatics(balanceStatisticRequest);
      if (bankBalanceStatisticResponse.getStatus().getCode() == 0) {
        model.put("detailBankBalanceStatistic", bankBalanceStatisticResponse.getBankHistoryStatistics());
        model.put("total", totalFiltered);
        model.put("totalBalanceBeginOfPeriod", bankBalanceStatisticResponse.getTotalBalanceBeginOfPeriod().intValue());
        model.put("totalBalanceEndOfPeriod", bankBalanceStatisticResponse.getTotalBalanceEndOfPeriod().intValue());
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return PATH_BALANCE_TOTAL_OF_ASSETS_DETAIL;
  }

  @RequestMapping(value = "/history/detail/{id}", method = RequestMethod.GET)
  public String detailHistory(ModelMap model, @PathVariable("id") Long id) {

    try {
      GetBankHistoryDetailRequest getBankHistoryDetailRequest = new GetBankHistoryDetailRequest();
      getBankHistoryDetailRequest.setIdTxn(id);
      GetBankHistoryDetailResponse getBankHistoryDetailResponse = stockEndpoint
          .getBankAccount(getBankHistoryDetailRequest);
      BankHistory bankHistory = getBankHistoryDetailResponse.getBankHistory();
      model.put("bankHistory", bankHistory);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_DETAIL;
  }

  @RequestMapping(value = "/history/edit/{id}", method = RequestMethod.GET)
  public String edit(ModelMap model, @PathVariable("id") Long id) {

    try {
      GetBankHistoryDetailRequest getBankHistoryDetailRequest = new GetBankHistoryDetailRequest();
      getBankHistoryDetailRequest.setIdTxn(id);
      GetBankHistoryDetailResponse getBankHistoryDetailResponse = stockEndpoint
          .getBankAccount(getBankHistoryDetailRequest);
      BankHistory bankHistory = getBankHistoryDetailResponse.getBankHistory();
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      String strDate = dateFormat.format(bankHistory.getTxnDate());
      String[] dateAndTime = strDate.split(" ");
      model.put("txnDate", dateAndTime[0]);
      model.put("txnTime", dateAndTime[1]);
      model.put("bankHistory", bankHistory);
      model.put("listBank", getProfileBank());
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT;
  }

  @RequestMapping(value = "/history/edit/{id}", method = RequestMethod.POST)
  public String editByPost(HttpServletRequest request, ModelMap model,
                           @PathVariable("id") Long id) {
//    ----------------------------------------------------------
    String createDate = request.getParameter("create-date");
    String createTime = request.getParameter("create-time");
    String bank = request.getParameter("bankCodes");
    String bankNumber = request.getParameter("bankAccountIds");
    String balanceChange = request.getParameter("balance-change");
    String moneys = StringUtils.trimToEmpty(request.getParameter("moneys"));
    String balance = StringUtils.trimToEmpty(request.getParameter("balance"));
    String bankRef = StringUtils.trimToEmpty(request.getParameter("bankRef"));
    String note = StringUtils.trimToEmpty(request.getParameter("note"));

    String txnDt = createDate + " " + createTime;
    Date txnDate = DateUtil.stringToDate(txnDt, "dd/MM/yyyy HH:mm:ss");
    TransactionType fluctuation = null;
    String increase = "CREDIT";
    if (balanceChange.equals(increase)) {
      fluctuation = TransactionType.CREDIT;
    } else {
      fluctuation = TransactionType.DEBIT;
    }
    moneys = moneys.replace(".", "");
    balance = balance.replace(".", "");
//    --------------------------------------------------------------
    UpdateBankHistoryRequest updateBankHistoryRequest = new UpdateBankHistoryRequest();
    BankHistory bankHistory = new BankHistory();
    bankHistory.setId(id);
    bankHistory.setTxnDate(txnDate);
    bankHistory.setBankAccount(bankNumber);
    bankHistory.setBankCode(BankCode.valueOf(bank));
    bankHistory.setTxnType(fluctuation);
    bankHistory.setAmount(Long.valueOf(moneys));
    bankHistory.setPostBalance(Long.valueOf(balance));
    if (bankRef != null && !bankRef.isEmpty()) {
      bankHistory.setBankRefTxn(bankRef);
    }
    bankHistory.setInfo(note);
    updateBankHistoryRequest.setBankHistory(bankHistory);
//    -------------------------------------------------------------------
    try {
      UpdateBankHistoryResponse updateBankHistoryResponse = stockEndpoint
          .updateBankAccount(updateBankHistoryRequest);
      if (updateBankHistoryResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        model.put("codeErr", updateBankHistoryResponse.getStatus().getCode());
        model.put("mesErr", updateBankHistoryResponse.getStatus().getValue());
        edit(model, id);
        return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT;
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      String error = e.getResponseBodyAsString();
      logger.error(error, e);
      model.put("codeErr", 1);
      model.put("mesErr", error);
      edit(model, id);
      return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      model.put("codeErr", 1);
      model.put("mesErr", e.getMessage());
      edit(model, id);
      return PATH_BALANCE_TOTAL_OF_ASSETS_HISTORY_EDIT;
    }

    return "redirect:" + BALANCE_TOTAL_OF_ASSETS_HISTORY + "?codeErr=0&mesErr=Success";
  }
}