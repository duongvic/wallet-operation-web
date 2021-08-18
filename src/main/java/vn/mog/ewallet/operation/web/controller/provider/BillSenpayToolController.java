package vn.mog.ewallet.operation.web.controller.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SpecialProviderAccount;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.VoucherRange;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionCancel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE_MANAGER;

@Controller
@RequestMapping(value = "/provider/special/bill_senpay_tool")
public class BillSenpayToolController extends AbstractController {

  public static final String PROVIDER_SPECIAL_CONTROLLER = "/provider/special/bill_senpay_tool";
  public static final String PROVIDER_SPECIAL_LIST = PROVIDER_SPECIAL_CONTROLLER + "/list";

  public static final String PROVIDER_SPECIAL_DETAIL = PROVIDER_SPECIAL_CONTROLLER + "/details";

  public static final String PROVIDER_SPECIAL_RECONCILIATION_DATE =
          PROVIDER_SPECIAL_CONTROLLER + "/reconciliation-date";
  private static final Logger LOG = LogManager.getLogger(ProviderSpecialSenpayController.class);

  private String codeErr;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
          "hasAnyRole('"
                  + RoleConstants.ADMIN_OPERATION
                  + "', '"
                  + RoleConstants.CUSTOMERCARE_MANAGER
                  + "', '"
                  + RoleConstants.CUSTOMERCARE
                  + "')")
  public String search(HttpServletRequest request, ModelMap model) throws FrontEndException {
    try {
      SpecialProviderAllAccountFindRequest specialProviderAllAccountFindRequest =
              new SpecialProviderAllAccountFindRequest();

      String search = StringUtils.trimToEmpty(request.getParameter("search"));
      if (search == "") {
        search = null;
      }

      String active = request.getParameter("active");
      if (StringUtils.isNotEmpty(active)) {
        if (active.equals("0")) {
          specialProviderAllAccountFindRequest.setActive(false);
        } else if (active.equals("1")) {
          specialProviderAllAccountFindRequest.setActive(true);
        }
      }

      int offset = 0;
      int limit = 100;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      specialProviderAllAccountFindRequest.setTextSearch(search);
      specialProviderAllAccountFindRequest.setLimit(limit);
      specialProviderAllAccountFindRequest.setOffset(offset);

      SpecialProviderAllAccountFindResponse fwtResponse =
              billSenpayToolEndpoint.providerSpecialAccountFind(
                      specialProviderAllAccountFindRequest);
      List<SpecialProviderAccount> objCus = new ArrayList<>();
      Integer totalList = 0;
      Long totalOfTotalAmount = 0L;
      if (fwtResponse != null && fwtResponse.getStatus().getCode() == 0) {
        objCus = fwtResponse.getSpecialProviderAccounts();
        totalList = fwtResponse.getTotal();
        totalOfTotalAmount = fwtResponse.getTotalOfTotalAmount();
      }

      model.put("list", objCus);
      model.put("pagesize", limit);
      model.put("offset", offset);
      model.put("total", totalList);
      model.put("totalOfTotalAmount", totalOfTotalAmount);
      model.put("quickSearch", search);

    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/provider_special/bill_senpay_tool/list";
  }

  @RequestMapping(value = "/by-province/list", method = RequestMethod.GET)
  @PreAuthorize(
          "hasAnyRole('"
                  + RoleConstants.ADMIN_OPERATION
                  + "', '"
                  + RoleConstants.CUSTOMERCARE_MANAGER
                  + "', '"
                  + RoleConstants.CUSTOMERCARE
                  + "')")
  public String searchSpecialProviderAllowedProvinces(HttpServletRequest request, ModelMap model)
          throws FrontEndException {
    try {
      SpecialProviderAllowedProvinceGetResponse response =
              billSenpayToolEndpoint.allowedProvinceGet();
      model.put("specialProviderAllowedProvinces", response.getSpecialProviderAllowedProvinces());
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      String str = e.getResponseBodyAsString();
      LOG.error(str, e);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/provider_special/bill_senpay_tool/special_provider_allowed_province";
  }

  @RequestMapping(value = "/reconciliation-date", method = RequestMethod.GET)
  @PreAuthorize(
          "hasAnyRole('"
                  + RoleConstants.ADMIN_OPERATION
                  + "','"
                  + RoleConstants.SALESUPPORT_MANAGER
                  + "')")
  public String searchReconciliationDate(HttpServletRequest request, ModelMap model)
          throws FrontEndException {
    try {

      // ---------table 2-------------
      String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String[] paramPayerId = request.getParameterValues("sourceAccount");
      String[] paramPayeeId = request.getParameterValues("targetAccount");
      // String[] status = request.getParameterValues("procces");

      TransactionReimRequest fwtRequest = new TransactionReimRequest();
      Date[] dates = parseDateRange(paramDateRange, true);
      fwtRequest.setFromDate(dates[0]);
      fwtRequest.setEndDate(dates[1]);

      int offset2 = 0;
      int limit2 = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset2 = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset2 > 0) {
          offset2 = (offset2 - 1) * limit2;
        }
      }

      if (paramPayerId != null
              && paramPayerId.length > 0
              && StringUtils.isNotEmpty(paramPayerId[0])) {
        fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
      }

      if (paramPayeeId != null
              && paramPayeeId.length > 0
              && StringUtils.isNotEmpty(paramPayeeId[0])) {
        fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
      }

      fwtRequest.setSearchText(quickSearch);
      fwtRequest.setLimit(limit2);
      fwtRequest.setOffset(offset2);

      List<TransactionCancel> tmp = new ArrayList<>();
      tmp.add(
              new TransactionCancel(
                      1L,
                      new Date(),
                      "service0",
                      "merchant0",
                      "requester0",
                      1L,
                      1L,
                      1L,
                      1L,
                      1L,
                      0,
                      "approver0",
                      0));
      tmp.add(
              new TransactionCancel(
                      1L,
                      new Date(),
                      "service1",
                      "merchant1",
                      "requester1",
                      1L,
                      1L,
                      1L,
                      1L,
                      1L,
                      1,
                      "approver1",
                      1));
      tmp.add(
              new TransactionCancel(
                      1L,
                      new Date(),
                      "service2",
                      "merchant2",
                      "requester2",
                      1L,
                      1L,
                      1L,
                      1L,
                      1L,
                      2,
                      "approver2",
                      2));

      // static
      model.put("statuses", TransactionCancel.Status.list());
      // map.put("processes", TransactionCancel.Process.list());
      model.put("transCancels", tmp);
      model.put("pagesize2", limit2);
      model.put("offset2", offset2);
      model.put("total2", tmp.size());
      List<Customer> sourceMerchants = null;
      List<Customer> targetMerchants = null;
      model.put("sourceMerchants", sourceMerchants);
      model.put("targetMerchants", targetMerchants);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
    return "/provider_special/bill_senpay_tool/list-reconciliation-date";
  }

  @GetMapping(value = "/details/{accountId}")
  @PreAuthorize(
          "hasAnyRole('"
                  + ADMIN_OPERATION
                  + "', '"
                  + CUSTOMERCARE
                  + "', '"
                  + CUSTOMERCARE_MANAGER
                  + "')")
  public String accountDetail(
          HttpServletRequest request,
          HttpServletResponse response,
          ModelMap model,
          @PathVariable("accountId") String accountId)
          throws FrontEndException {
    try {

      getAccountProviderSpecial(request, response, model, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      codeErr = e.getMessage();
      model.put("codeErr", codeErr);
      model.put("mesErr", codeErr);
    }

    //    model.put("codeErr", codeErr);
    model.put("edit_type", "edit");
    codeErr = StringUtils.EMPTY;
    return "/provider_special/bill_senpay_tool/edit_account";
  }

  @PostMapping(value = "/details/{accountId}")
  @PreAuthorize(
          "hasAnyRole('"
                  + ADMIN_OPERATION
                  + "', '"
                  + CUSTOMERCARE
                  + "', '"
                  + CUSTOMERCARE_MANAGER
                  + "')")
  public String accountDetailUpdate(
          HttpServletRequest request,
          HttpServletResponse response,
          ModelMap map,
          @PathVariable("accountId") String accountId)
          throws FrontEndException {

    try {
      String editType = request.getParameter("edit_type");
      if ("edit".equals(editType)) {
        // Update Customer's info
        SpecialProviderAccount customer =
                getAccountProviderSpecial(request, response, map, accountId);

        String btnAction = request.getParameter("btn-action");
        if ("save-account-info".equals(btnAction)) {
          String financeVoucherCode5M = request.getParameter("financeVoucherCode5M");
          String financeVoucherCode500K = request.getParameter("financeVoucherCode500K");
          String financeVoucherCode = request.getParameter("financeVoucherCode");

          SpecialProviderAccountChangeRequest updateCustomerReq =
                  new SpecialProviderAccountChangeRequest();
          updateCustomerReq.setAccountName(accountId);
          updateCustomerReq.setPassword(customer.getPassword());
          updateCustomerReq.setPaymentPass(customer.getPaymentPass());
          updateCustomerReq.setStatus(Integer.valueOf(request.getParameter("isActive")));
          updateCustomerReq.setSerialNumber(request.getParameter("serialNumber"));

          String[] listVoucher = request.getParameter("list-voucher").split(",");
          String[] listFinanceVoucher = request.getParameter("list-finance-voucher").split(",");

          List<VoucherRange> voucherRanges = new ArrayList<>();
          if (listVoucher != null && !listVoucher[0].isEmpty()) {
            for (String str : listVoucher) {
              String amount = request.getParameter("amount-" + str);
              ;
              String voucher = request.getParameter("voucher-" + str);
              ;
              VoucherRange voucherRange =
                      new VoucherRange(
                              amount != null && !amount.isEmpty()
                                      ? Long.valueOf(amount.replaceAll("\\.", ""))
                                      : null,
                              voucher != null && !voucher.isEmpty() ? voucher : null);
              voucherRanges.add(voucherRange);
            }
            updateCustomerReq.setVoucherRanges(voucherRanges);
          }

          if (listFinanceVoucher != null && !listFinanceVoucher[0].isEmpty()) {
            List<VoucherRange> financeVoucherRanges = new ArrayList<>();
            for (String str : listFinanceVoucher) {
              String amount = request.getParameter("finance-amount-" + str);
              String voucher = request.getParameter("finance-voucher-" + str);
              VoucherRange voucherRange =
                      new VoucherRange(
                              amount != null && !amount.isEmpty()
                                      ? Long.valueOf(amount.replaceAll("\\.", ""))
                                      : null,
                              voucher != null && !voucher.isEmpty() ? voucher : null);
              financeVoucherRanges.add(voucherRange);
            }
            updateCustomerReq.setFinanceVoucherRanges(financeVoucherRanges);
          }

          SpecialProviderAccountChangeResponse updateCustomerRes =
                  billSenpayToolEndpoint.providerSpecialAccountUpdate(updateCustomerReq);
          if (updateCustomerRes == null || updateCustomerRes.getStatus() == null) {
            throw new Exception("Can not Update account senPay");
          }
          if (0 != updateCustomerRes.getStatus().getCode()) {
            codeErr = updateCustomerRes.getStatus().getValue();
            throw new Exception(updateCustomerRes.getStatus().getValue());
          }
          map.put("codeErr", "");
          map.put("mesErr", "label.message.label");
        }
      }

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      codeErr = e.getMessage();
      map.put("mesErr", codeErr);
      map.put("codeErr", codeErr);
    }
    return accountDetail(request, response, map, accountId);
  }

  @GetMapping(value = "/add")
  @PreAuthorize(
          "hasAnyRole('"
                  + ADMIN_OPERATION
                  + "', '"
                  + CUSTOMERCARE
                  + "', '"
                  + CUSTOMERCARE_MANAGER
                  + "')")
  public String addAccount(HttpServletRequest request, HttpServletResponse response, ModelMap map)
          throws FrontEndException {
    //
    //    try {
    //      getInfoAddAccount(request, response, map, paraCusTypeSystem, walletType, userType,
    // mCustomer);
    //    } catch (Exception ex) {
    //      LOG.error(ex.getMessage());
    //    }

    //
    map.put("edit_type", "add");
    //    map.put("codeErr", codeErr);
    //    map.put("mesErr", codeErr);
    codeErr = StringUtils.EMPTY;
    //    mCustomer = null;
    return "/provider_special/bill_senpay_tool/edit_account";
  }

  @PostMapping(value = "/add")
  @PreAuthorize(
          "hasAnyRole('"
                  + ADMIN_OPERATION
                  + "', '"
                  + CUSTOMERCARE
                  + "', '"
                  + CUSTOMERCARE_MANAGER
                  + "')")
  public String addAccountPost(
          HttpServletRequest request, HttpServletResponse response, ModelMap map)
          throws FrontEndException {
    //    codeErr = StringUtils.EMPTY;

    SpecialProviderAccount customer = new SpecialProviderAccount();
    String customerId = request.getParameter("phone");

    try {
      String financeVoucherCode5M = request.getParameter("financeVoucherCode5M");
      String financeVoucherCode500K = request.getParameter("financeVoucherCode500K");
      String financeVoucherCode = request.getParameter("financeVoucherCode");

      SpecialProviderAccountChangeRequest createCustomerRequest =
              new SpecialProviderAccountChangeRequest();

      createCustomerRequest.setAccountName(customerId);
      createCustomerRequest.setPassword(request.getParameter("password"));
      createCustomerRequest.setPaymentPass(request.getParameter("paymentPass"));
      createCustomerRequest.setStatus(Integer.parseInt(request.getParameter("isActive")));
      createCustomerRequest.setSerialNumber(request.getParameter("serialNumber"));

      String[] listVoucher = request.getParameter("list-voucher").split(",");
      String[] listFinanceVoucher = request.getParameter("list-finance-voucher").split(",");

      List<VoucherRange> voucherRanges = new ArrayList<>();
      if (listVoucher != null && !listVoucher[0].isEmpty()) {
        for (String str : listVoucher) {
          String amount = request.getParameter("amount-" + str);
          String voucher = request.getParameter("voucher-" + str);
          VoucherRange voucherRange =
                  new VoucherRange(
                          amount != null && !amount.isEmpty()
                                  ? Long.valueOf(amount.replaceAll("\\.", ""))
                                  : null,
                          voucher != null && !voucher.isEmpty() ? voucher : null);
          voucherRanges.add(voucherRange);
        }
        createCustomerRequest.setVoucherRanges(voucherRanges);
      }

      if (listFinanceVoucher != null && !listFinanceVoucher[0].isEmpty()) {
        List<VoucherRange> financeVoucherRanges = new ArrayList<>();
        for (String str : listFinanceVoucher) {
          String amount = request.getParameter("finance-amount-" + str);
          String voucher = request.getParameter("finance-voucher-" + str);
          VoucherRange voucherRange =
                  new VoucherRange(
                          amount != null && !amount.isEmpty()
                                  ? Long.valueOf(amount.replaceAll("\\.", ""))
                                  : null,
                          voucher != null && !voucher.isEmpty() ? voucher : null);
          financeVoucherRanges.add(voucherRange);
        }
        createCustomerRequest.setFinanceVoucherRanges(financeVoucherRanges);
      }

      SpecialProviderAccountChangeResponse createCustomerResponse =
              billSenpayToolEndpoint.providerSpecialAccountAdd(createCustomerRequest);

      if (createCustomerResponse == null || createCustomerResponse.getStatus() == null) {
        throw new Exception("Can not Update Customer");
      }
      if (0 != createCustomerResponse.getStatus().getCode()) {
        codeErr = createCustomerResponse.getStatus().getValue();
        throw new Exception(createCustomerResponse.getStatus().getValue());
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      codeErr = ex.getMessage();
      map.put("codeErr", codeErr);
      map.put("mesErr", codeErr);
      return "redirect:" + "/provider/special/add";
    }
    map.put("codeErr", "");
    map.put("mesErr", "label.message.create.account");
    map.put("edit_type", "add");
    return accountDetail(request, response, map, customerId);
  }

  public List<VoucherRange> sortingByAmount(List<VoucherRange> voucherRanges) {
    try {
      return voucherRanges.stream()
              .sorted(Comparator.comparingLong(VoucherRange::getAmount))
              .collect(Collectors.toList());
    } catch (Exception e) {
    }
    return voucherRanges;
  }

  private SpecialProviderAccount getAccountProviderSpecial(
          HttpServletRequest request, HttpServletResponse response, ModelMap model, String accountId)
          throws FrontEndException {
    SpecialProviderAccount customer = new SpecialProviderAccount();
    try {
      SpecialProviderAccountGetRequest fullCustomerReq = new SpecialProviderAccountGetRequest();
      fullCustomerReq.setAccountName(accountId);
      SpecialProviderAccountGetResponse fullCustomerRes =
              billSenpayToolEndpoint.providerSpecialAccountGet(fullCustomerReq);

      if (fullCustomerRes == null || fullCustomerRes.getStatus() == null) {
        throw new Exception("Can not get details account sendPay");
      }
      if (0 != fullCustomerRes.getStatus().getCode()) {
        codeErr = fullCustomerRes.getStatus().getValue();
        throw new Exception(fullCustomerRes.getStatus().getValue());
      }
      customer = fullCustomerRes.getSpecialProviderAccount();
      customer.setFinanceVoucherRanges(sortingByAmount(customer.getFinanceVoucherRanges()));
      customer.setVoucherRanges(sortingByAmount(customer.getVoucherRanges()));
      model.put("account_object", customer);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return customer;
  }
}
