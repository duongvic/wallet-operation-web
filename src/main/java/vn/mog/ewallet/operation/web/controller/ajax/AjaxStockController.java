package vn.mog.ewallet.operation.web.controller.ajax;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DataTableResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.beans.BankCode;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@RestController
@RequestMapping(value = "ajax-stock-controller")
public class AjaxStockController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AjaxStockController.class);

  @RequestMapping(value = "/find-total-asset-statics", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findTotalAsset(HttpServletRequest request) throws Exception {
    Date fromDate = null;
    Date endDate = null;

    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("date"));
    String[] paramBankCodes = request.getParameterValues("bankCodes[]");
    String[] paramBankAccounts = request.getParameterValues("bankAccounts[]");

    BankBalanceStatisticRequest balanceStatisticRequest = new BankBalanceStatisticRequest();

    if (StringUtils.isNotBlank(paramDateRange)) {
      Date[] dates = parseDateRange(paramDateRange, false);
      fromDate =dates[0];
      endDate = dates[1];
    }
    balanceStatisticRequest.setBeginDatePeriod(fromDate);
    balanceStatisticRequest.setEndDatePeriod(endDate);

    List<BankCode> lstString = new ArrayList<>();
    if (paramBankCodes != null && paramBankCodes.length > 0 && StringUtils
        .isNotEmpty(paramBankCodes[0])) {
      for (String string : paramBankCodes) {
        lstString.add(BankCode.valueOf(string));
      }
      balanceStatisticRequest.setBankCodes(lstString);
    } else {
      balanceStatisticRequest.setBankCodes(BankCode.BANK_CODES_ENUM);
    }

    if (paramBankAccounts != null && paramBankAccounts.length > 0 && StringUtils
        .isNotEmpty(paramBankAccounts[0])) {
      balanceStatisticRequest.setBankAccounts(Arrays.asList(paramBankAccounts));
    }
    balanceStatisticRequest.setOffset(Integer.parseInt(request.getParameter("start")));
    balanceStatisticRequest.setLimit(Integer.parseInt(request.getParameter("length")));

    BankBalanceStatisticResponse bankBalanceStatisticResponse = stockEndpoint.findBankBlanceStatics(balanceStatisticRequest);
    DataTableResponse<BankHistoryStatistic> response = new DataTableResponse<>();
    response.setRecordsTotal(bankBalanceStatisticResponse.getTotalFiltered());
    response.setRecordsFiltered(bankBalanceStatisticResponse.getTotalFiltered());
    response.setDataList((List<BankHistoryStatistic>) bankBalanceStatisticResponse.getBankHistoryStatistics());

    response.setTotal1(bankBalanceStatisticResponse.getTotalBalanceBeginOfPeriod());
    response.setTotal2(bankBalanceStatisticResponse.getTotalBalanceEndOfPeriod());

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/find-total-asset-history", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findTotalAssetHistory(HttpServletRequest request) throws Exception {
    Date fromDate = null;
    Date endDate = null;

    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("date"));
    String[] paramBankCodes = request.getParameterValues("bankCodes[]");
    String[] paramBankAccounts = request.getParameterValues("bankAccounts[]");

    FindBankHistoryRequest bankHistoryRequest = new FindBankHistoryRequest();

    if (StringUtils.isNotBlank(paramDateRange)) {
      Date[] dates = parseDateRange(paramDateRange, true);
      fromDate =dates[0];
      endDate = dates[1];
    }
    bankHistoryRequest.setFromDate(fromDate);
    bankHistoryRequest.setEndDate(endDate);

    List<BankCode> lstString = new ArrayList<>();
    if (paramBankCodes != null && paramBankCodes.length > 0 && StringUtils
        .isNotEmpty(paramBankCodes[0])) {
      for (String string : paramBankCodes) {
        lstString.add(BankCode.valueOf(string));
      }
      bankHistoryRequest.setBankCodes(lstString);
    } else {
      bankHistoryRequest.setBankCodes(BankCode.BANK_CODES_ENUM);
    }

    if (paramBankAccounts != null && paramBankAccounts.length > 0 && StringUtils
        .isNotEmpty(paramBankAccounts[0])) {
      bankHistoryRequest.setBankAccounts(Arrays.asList(paramBankAccounts));
    }
    bankHistoryRequest.setOffset(Integer.parseInt(request.getParameter("start")));
    bankHistoryRequest.setLimit(Integer.parseInt(request.getParameter("length")));

    FindBankHistoryResponse bankHistoryResponse = stockEndpoint.findBankHistories(bankHistoryRequest);
    DataTableResponse<BankHistory> response = new DataTableResponse<>();
    response.setRecordsTotal(bankHistoryResponse.getTotalFiltered());
    response.setRecordsFiltered(bankHistoryResponse.getTotalFiltered());
    response.setDataList((List<BankHistory>) bankHistoryResponse.getBankHistories());
    response.setTotal1(bankHistoryResponse.getTotalCreditAmount());
    response.setTotal2(bankHistoryResponse.getTotalDebitAmount());

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/delete-balance-history/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> deleteBalanceHistory(@PathVariable("id") Long id) throws Exception {
    DeleteBankHistoryResponse deleteBankHistoryResponse = new DeleteBankHistoryResponse();
    try {
      DeleteBankHistoryRequest deleteBankHistoryRequest = new DeleteBankHistoryRequest();
      deleteBankHistoryRequest.setId(id);
      deleteBankHistoryResponse = stockEndpoint.deleteBankAccount(deleteBankHistoryRequest);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      DeleteBankHistoryResponse errRes = new DeleteBankHistoryResponse();
      Status status = new Status(e.getStatusCode().value(), e.getResponseBodyAsString());
      errRes.setStatus(status);
      ResponseEntity.ok(deleteBankHistoryResponse);
    } catch (Exception e) {
      DeleteBankHistoryResponse errRes = new DeleteBankHistoryResponse();
      Status status = new Status(400, e.getMessage());
      errRes.setStatus(status);
      ResponseEntity.ok(deleteBankHistoryResponse);
    }
    return ResponseEntity.ok(deleteBankHistoryResponse);
  }
}
