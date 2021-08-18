package vn.mog.ewallet.operation.web.controller.provider;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE_MANAGER;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAccountGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllAccountFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.SpecialProviderAllowedProvinceGetResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SpecialProviderAccount;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionCancel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;

@Controller
@RequestMapping(value = "/provider/special/ptu_kpp_viettel")
public class ProviderSpecialKppViettelController extends AbstractController {

  public static final String PROVIDER_SPECIAL_CONTROLLER = "/provider/special/ptu_kpp_viettel";
  public static final String PROVIDER_SPECIAL_LIST = PROVIDER_SPECIAL_CONTROLLER + "/list";

  public static final String PROVIDER_SPECIAL_DETAIL = PROVIDER_SPECIAL_CONTROLLER + "/details";

  private static final Logger LOG = LogManager.getLogger(ProviderSpecialKppViettelController.class);

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
      SpecialProviderAllAccountFindResponse fwtResponse =
          specialProviderKppViettelEndpoint.providerSpecialAccountFind(
              specialProviderAllAccountFindRequest);
      model.put("kppVTs", fwtResponse.getSpecialProviderAccounts());
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/provider_special/kpp_viettel/list";
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

      // getAccountProviderSpecial(request, response, model, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      codeErr = e.getMessage();
      model.put("codeErr", codeErr);
      model.put("mesErr", codeErr);
    }

    //    model.put("codeErr", codeErr);
    model.put("edit_type", "edit");
    codeErr = StringUtils.EMPTY;
    return "/provider_special/edit_account";
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
        //        SpecialProviderAccount customer =
        //            getAccountProviderSpecial(request, response, map, accountId);

        String btnAction = request.getParameter("btn-action");
        if ("save-account-info".equals(btnAction)) {
          SpecialProviderAccountChangeRequest updateCustomerReq =
              new SpecialProviderAccountChangeRequest();
          updateCustomerReq.setAccountName(accountId);
          updateCustomerReq.setPassword(request.getParameter("password"));
          updateCustomerReq.setPhoneNumber(request.getParameter("phoneNumber"));
          updateCustomerReq.setSerialNumber(request.getParameter("serialNumber"));
          updateCustomerReq.setStatus(Integer.valueOf((request.getParameter("status"))));

          SpecialProviderAccountChangeResponse updateCustomerRes =
              specialProviderKppViettelEndpoint.providerSpecialAccountUpdate(updateCustomerReq);
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
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
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
    return "/provider_special/edit_account";
  }

  @PostMapping(value = "/add")
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String addAccountPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    //    codeErr = StringUtils.EMPTY;

    SpecialProviderAccount customer = new SpecialProviderAccount();
    String customerId = request.getParameter("phone");

    try {
      SpecialProviderAccountChangeRequest createCustomerRequest =
          new SpecialProviderAccountChangeRequest();

      createCustomerRequest.setAccountName(request.getParameter("accontName"));
      createCustomerRequest.setPassword(request.getParameter("password"));
      createCustomerRequest.setPhoneNumber(request.getParameter("phoneNumber"));
      createCustomerRequest.setSerialNumber(request.getParameter("serialNumber"));
      createCustomerRequest.setStatus(Integer.valueOf((request.getParameter("status"))));

      SpecialProviderAccountChangeResponse createCustomerResponse =
          specialProviderKppViettelEndpoint.providerSpecialAccountAdd(createCustomerRequest);

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

  //  private SpecialProviderAccount getAccountProviderSpecial(
  //      HttpServletRequest request, HttpServletResponse response, ModelMap model, String
  // accountId)
  //      throws FrontEndException {
  //    SpecialProviderAccount customer = new SpecialProviderAccount();
  //    try {
  //      SpecialProviderAccountGetRequest fullCustomerReq = new SpecialProviderAccountGetRequest();
  //      fullCustomerReq.setAccountName(accountId);
  //      SpecialProviderAccountGetResponse fullCustomerRes =
  //          specialProviderKppViettelEndpoint.providerSpecialAccountGet(fullCustomerReq);
  //
  //      if (fullCustomerRes == null || fullCustomerRes.getStatus() == null) {
  //        throw new Exception("Can not get details account sendPay");
  //      }
  //      if (0 != fullCustomerRes.getStatus().getCode()) {
  //        codeErr = fullCustomerRes.getStatus().getValue();
  //        throw new Exception(fullCustomerRes.getStatus().getValue());
  //      }
  //      customer = fullCustomerRes.getSpecialProviderAccount();
  //      model.put("account_object", customer);
  //    } catch (Exception ex) {
  //      LOG.error(ex.getMessage(), ex);
  //    }
  //    return customer;
  //  }
}
