package vn.mog.ewallet.operation.web.controller.account;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.FULL_CUSTOMER_TYPES;

import java.util.ArrayList;
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
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressStatus;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.utils.CustomerUtil;
import vn.mog.framework.common.utils.SessionUtil;

@Controller
@RequestMapping(value = "/wallet-account/manage")
public class WalletAccountController extends AbstractAccountController {

  public static final String WALLET_ACCOUNT_MANAGE_CONTROLLER = "/wallet-account/manage";
  public static final String WALLET_ACCOUNT_MANAGE_LIST =
      WALLET_ACCOUNT_MANAGE_CONTROLLER + "/list";
  public static final String WALLET_ACCOUNT_MANAGE_DETAIL =
      WALLET_ACCOUNT_MANAGE_CONTROLLER + "/details";
  public static final String SYSTEM_WALLET = "SYSTEM_WALLET";
  private static final Logger LOG = LogManager.getLogger(WalletAccountController.class);
  private static final String REDIRECT_WALLET_ACCOUNT_MANAGE_ADD = "wallet-account/manage/add";
  private static final String CUSTOMER_TYPE_SYSTEM_ATTRIBUTE = "CUSTOMER_TYPE_SYSTEM_ATTRIBUTE";


  private String codeErr;
  private Customer mCustomer;

  @GetMapping(value = "/list")
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    FindUmgrCustomerResponse fUmgrCusResponse = null;
    try {

      //get customer type
      getCustomerTypeSystemWalletAcc(request, response, map);

      //get list role
      getRoleList(request, response, map);

      //get blackList
      getBlackListReason(map);
      
      //get position
      getPositions(map);
      
    } catch (Exception e) {
      map.put("codeError", fUmgrCusResponse.getStatus().getValue());
      LOG.error(e.getMessage(), e);
    }

    map.put("customerTypeSystem", SYSTEM_WALLET);
    String customerType = request.getParameter("customerType");
    if (StringUtils.isNotBlank(customerType)) {
      map.put("customerType", customerType);
    }
    map.put("codeErr", request.getParameter("codeErr"));
    map.put("mesErr", "label.message.label");
    SessionUtil.setAttribute(CUSTOMER_TYPE_SYSTEM_ATTRIBUTE, SYSTEM_WALLET);
    return "/wallet_account_manage/list";
  }


  @GetMapping(value = "/details/{accountId}")
//    @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String accountDetail(HttpServletRequest request, HttpServletResponse response,
      ModelMap model, @PathVariable("accountId") Long accountId) throws FrontEndException {
    try {
      getAccountDetail(request, response, model, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      codeErr = e.getMessage();
    }

    model.put("codeErr", codeErr);
    model.put("edit_type", "edit");
    codeErr = StringUtils.EMPTY;
    return "/wallet_account_manage/edit_account";
  }


  @PostMapping(value = "/details/{accountId}")
//    @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String accountDetailUpdate(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("accountId") Long accountId) throws FrontEndException {

    try {
      String editType = request.getParameter("edit_type");
      if ("edit".equals(editType)) {
        //Update Customer's info
        GetFullCustomerResponse customerRes = fetchCustomer(accountId);
        Customer customer = customerRes.getCustomer();
        String btnAction = request.getParameter("btn-action");
        if ("save-account-personal".equals(btnAction)) {
          saveAccountPersonal(request, customer, customerRes);
        } else if ("save-account-info".equals(btnAction)) {
          saveAccountInfo(request, customer);
        }
        UpdateCustomerRequest updateCustomerReq = new UpdateCustomerRequest(customer);
        UpdateCustomerResponse updateCustomerRes = umgrAccountEndpoint
            .updateCustomer(updateCustomerReq);
        if (updateCustomerRes == null || updateCustomerRes.getStatus() == null) {
          codeErr = updateCustomerRes.getStatus().getValue();
          throw new Exception("Can not Update Customer");
        }
        if (0 != updateCustomerRes.getStatus().getCode()) {
          codeErr = updateCustomerRes.getStatus().getValue();
          throw new Exception(updateCustomerRes.getStatus().getValue());
        }
      } else {
        //Update Customer's attribute
        saveUpdateCustomerAttribute(request, accountId);
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
    String walletType = request.getParameter("wallet_type");
    String userType = request.getParameter("user_type");
    String paraCusTypeSystem = SessionUtil.getAttribute(CUSTOMER_TYPE_SYSTEM_ATTRIBUTE).toString();
    try {
      getInfoAddAccount(request, response, map, paraCusTypeSystem, walletType, userType, mCustomer);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
    map.put("customerType", request.getParameter("customerType"));

    map.put("codeErr", codeErr);
    map.put("mesErr", codeErr);
    codeErr = StringUtils.EMPTY;
    mCustomer = null;
    return "/wallet_account_manage/edit_account";
  }

  @PostMapping(value = "/add")
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String addAccountPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws FrontEndException {
    codeErr = StringUtils.EMPTY;
    String userType = "N/A";
    String walletType = "N/A";
    String paramPosition = request.getParameter("positionList");
    String paramDescription = request.getParameter("description");
    try {
      CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
      Customer customer = new Customer();
      Integer blockReason = Integer.parseInt(request.getParameter("blackList"));
      customer.setMsisdn(request.getParameter("phone"));
      customer.setBlackListReason(blockReason);
      customer.setActive(0 == blockReason);
      String notification = request.getParameter("notification");
      try {
        customer.setNotificationModeId(Integer.parseInt(notification));
      } catch (NumberFormatException ex) {
        codeErr = ex.getMessage();
        LOG.error(ex.getMessage(), ex);
      }
      CustomerType customerType = new CustomerType();
      Integer customerTypeId = Integer.parseInt(request.getParameter("customerType"));
      customerType.setId(customerTypeId);
      customerType.setName(FULL_CUSTOMER_TYPES.get(customerTypeId));
      customer.setCustomerType(customerType);
      customer.setFirstName(request.getParameter("first-name"));
      customer.setLastName(request.getParameter("last-name"));
      customer.setEmail(request.getParameter("email"));
      String cif = request.getParameter("cif");
      if (StringUtils.isNotBlank(cif)) {
        customer.setCif(cif);
      }

      customer.setJobPosition(paramPosition);
      map.put("positionList", paramPosition);

      customer.setDescription(paramDescription);
      map.put("description", paramDescription);

      customer.setUserTypeId(CustomerUtil.getUserType(customerTypeId));
      Integer userTypeId = customer.getUserTypeId();
      if (userTypeId != null && UserType.USER_TYPE_IDS.containsKey(userTypeId)) {
        userType = UserType.USER_TYPE_IDS.get(userTypeId);
      }
      map.put("user_type", userType);

      customer.setWalletTypeId(CustomerUtil.getWalletType(customerTypeId));
      Integer walletTypeId = customer.getWalletTypeId();
      if (walletTypeId != null && UserType.USER_TYPE_IDS.containsKey(walletTypeId)) {
        walletType = CustomerType.WALLET_TYPE_IDS.get(walletTypeId);
      }
      map.put("wallet_type", walletType);

      //Address
      Address address = new Address();
      List<Address> lstAddress = new ArrayList<>();
      address.setAddressType(AddressType.RESIDENCE_ADDRESS.getId());
      address.setAddressStatus(AddressStatus.VALID_STATUS.getId());
      address.setStreet1(request.getParameter("account-nameAddr"));
      lstAddress.add(address);
      customer.setAddresses(lstAddress);

      mCustomer = customer;

      createCustomerRequest.setCustomer(customer);

      CreateCustomerResponse createCustomerResponse = umgrAccountEndpoint
          .createCustomer(createCustomerRequest);
      Long customerId = createCustomerResponse.getCustomerId();

      if (createCustomerResponse == null || createCustomerResponse.getStatus() == null) {
        throw new Exception("Can not Update Customer`");
      }
      if (0 != createCustomerResponse.getStatus().getCode()) {
        codeErr = createCustomerResponse.getStatus().getValue();
        throw new Exception(createCustomerResponse.getStatus().getValue());
      }

      map.put("codeErr", "");
      map.put("mesErr", "label.message.create.account");
      map.put("edit_type", "add");
      return "redirect:" + WALLET_ACCOUNT_MANAGE_DETAIL + "/" + customerId;
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      codeErr = ex.getMessage();
      map.put("wallet_type", walletType);
      map.put("user_type", userType);
      return "redirect:" + "/wallet-account/manage/add";
    }
  }

  /*  ACCOUNT*/
  @GetMapping(value = "/reset-password/{accountId}")
  private String resetPassword(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("accountId") String accountId) {
    try {
      getInfoResetPassword(request, response, map, accountId);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

    }

    return "/wallet_account_manage/reset_password";
  }

  @PostMapping(value = "/reset-password/{accountId}")
  public String resetPasswordPost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("accountId") String accountId) {
    try {
      functionResetPassWord(request, response, map, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }

    return resetPassword(request, response, map, accountId);
  }

}
