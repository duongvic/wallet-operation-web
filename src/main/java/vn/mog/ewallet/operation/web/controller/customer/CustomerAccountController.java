package vn.mog.ewallet.operation.web.controller.customer;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.*;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType.LIST_USER_TYPE_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.contract.form.CustomerDataForm;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.UpdateIdentityResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CredentialType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.Identity;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressStatus;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.UpdateAddressResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.OutletStoreType;
import vn.mog.ewallet.operation.web.utils.CustomerUtil;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.contract.base.KeyValue;

/** Created by duongnh on 7/10/18. */
@Controller
@RequestMapping(value = "/customer/manage")
public class CustomerAccountController extends AbstractAccountController {

  /*ACCOUNT*/
  public static final String CUSTOMER_MANAGE_CONTROLLER = "/customer/manage";
  public static final String CUSTOMER_MANAGE_LIST = CUSTOMER_MANAGE_CONTROLLER + "/list";
  public static final String CUSTOMER_MANAGE_LIST_ALL = CUSTOMER_MANAGE_CONTROLLER + "/list-all";
  public static final String CUSTOMER_MANAGE_DETAIL = CUSTOMER_MANAGE_CONTROLLER + "/details";

  public static final String SPECIAL_CHARACTER = "(!@#$%^&*()_+)";
  private static final Logger LOG = LogManager.getLogger(CustomerAccountController.class);
  private String codeErr;
  private String mesErr;
  private Customer mCustomer;

  @RequestMapping(value = "/list-all", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT'"
          + ",'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String listALl(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    FindUmgrCustomerResponse fUmgrCusResponse = null;
    List<Customer> listAccount;
    try {
      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      // get customer type
      getCustomerTypeAcc(request, response, map);

      // get list role
      getRoleList(request, response, map);

      // get blackList
      getBlackListReason(map);

      // get position
      getPositions(map);

      /* Tạo request & set params vào request*/
      FindUmgrCustomerRequest fUmgrCusRequest = new FindUmgrCustomerRequest();

      String fullTextSearch = request.getParameter("fullTextSearch");
      String paramCustomerType = request.getParameter("customerType");
      String paramRoleList = request.getParameter("roleList");
      String paramBlackList = request.getParameter("blackList");
      String paramWalletType = request.getParameter("walletTypeCb");
      String paramUserType = request.getParameter("userTypeCb");
      String paramPosition = request.getParameter("positionList");
      String paymentChannelId = request.getParameter("paymentChannelId");

      fUmgrCusRequest.setBizChannelIds(StringUtils.isNotBlank(paymentChannelId) ? Collections.singletonList(paymentChannelId) : null);
      ArrayList<Integer> lstCustometTypeId =
          new ArrayList<Integer>(
              Arrays.asList(ID_CUSTOMER, ID_MERCHANT, ID_PROVIDER, ID_AGENT, ID_PROPERTY_MANAGER));
      if (StringUtils.isNotEmpty(paramCustomerType)) {
        fUmgrCusRequest.setCustomerTypes(
            Collections.singletonList(Integer.parseInt(paramCustomerType)));
      } else {
        fUmgrCusRequest.setCustomerTypes(lstCustometTypeId);
      }

      if (StringUtils.isNotEmpty(paramRoleList)) {
        fUmgrCusRequest.setUmgrRoleIds(Collections.singletonList(paramRoleList));
      }
      if (StringUtils.isNotEmpty(paramBlackList)) {
        fUmgrCusRequest.setBlacklistReasons(
            Collections.singletonList(Integer.parseInt(paramBlackList)));
      }

      if (StringUtils.isEmpty(paramUserType)) {
        fUmgrCusRequest.setUserTypeIds(UserType.LIST_USER_TYPE_ID);
      } else {
        fUmgrCusRequest.setUserTypeIds(Arrays.asList(Integer.parseInt(paramUserType)));
      }

      if (StringUtils.isEmpty(paramWalletType)) {
        List<Integer> lstWalletType = new ArrayList<>();
        lstWalletType.add(CustomerType.ACCOUNT);
        lstWalletType.add(CustomerType.WALLET);
        fUmgrCusRequest.setWalletTypeIds(lstWalletType);
      } else {
        fUmgrCusRequest.setWalletTypeIds(Arrays.asList(Integer.parseInt(paramWalletType)));
      }

      if (StringUtils.isNotEmpty(paramPosition)) {
        fUmgrCusRequest.setJobPositionIds(
            Collections.singletonList(Integer.parseInt(paramPosition)));
      }

      fUmgrCusRequest.setQuickSearch(fullTextSearch);
      fUmgrCusRequest.setFromDate(null);
      fUmgrCusRequest.setToDate(null);
      fUmgrCusRequest.setLimit(limit);
      fUmgrCusRequest.setOffset(offset);

      fUmgrCusResponse = umgrRolesPrivilegesEndpoint.findUmgrCustomers(fUmgrCusRequest);
      if (fUmgrCusResponse != null && fUmgrCusResponse.getStatus().getCode() == 0) {
        listAccount = fUmgrCusResponse.getCustomers();
        map.put("total", (int) fUmgrCusResponse.getTotal());
      } else {
        listAccount = new ArrayList<>();
      }
      map.put("list", listAccount);
      map.put("pagesize", limit);
      map.put("offset", offset);

      map.put("fullTextSearch", fullTextSearch);
      map.put("customerType", paramCustomerType);
      map.put("roleList", paramRoleList);
      map.put("blackList", paramBlackList);
      map.put("walletTypeCb", paramWalletType);
      map.put("userTypeCb", paramUserType);
      map.put("positionList", paramPosition);
      map.put(
          "paymentChannels",
          new PaymentChannel[] {PaymentChannel.ZOTA, PaymentChannel.SAPO, PaymentChannel.ZOTAHOME});

    } catch (Exception e) {
      map.put("codeError", e.getMessage());
      LOG.error(e.getMessage(), e);
    }
    map.put("codeErr", request.getParameter("codeErr"));
    map.put("mesErr", "label.message.label");

    return "/customer_manage/list_all";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER', 'SALESUPPORT'"
          + ",'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    FindUmgrCustomerResponse fUmgrCusResponse = null;
    List<Customer> listAccount;
    try {
      // Xử lý dữ liệu đầu vào
      Long total = 0L;
      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      // get customer type
      getCustomerTypeAcc(request, response, map);

      // get list role
      getRoleList(request, response, map);

      // get blackList
      getBlackListReason(map);

      // get position
      getPositions(map);

      /* Tạo request & set params vào request*/
      FindUmgrCustomerRequest fUmgrCusRequest = new FindUmgrCustomerRequest();

      String fullTextSearch = request.getParameter("fullTextSearch");
      String paramCustomerType = request.getParameter("customerType");
      String paramRoleList = request.getParameter("roleList");
      String paramBlackList = request.getParameter("blackList");
      String paramWalletType = request.getParameter("walletTypeCb");
      String paramUserType = request.getParameter("userTypeCb");
      String paramPosition = request.getParameter("positionList");
      String paymentChannelId = request.getParameter("paymentChannelId");

      fUmgrCusRequest.setBizChannelIds(StringUtils.isNotBlank(paymentChannelId) ? Collections.singletonList(paymentChannelId) : null);

      if (StringUtils.isNotEmpty(paramCustomerType)) {
        fUmgrCusRequest.setCustomerTypes(
            Collections.singletonList(Integer.parseInt(paramCustomerType)));
      } else {
        fUmgrCusRequest.setCustomerTypes(CustomerType.CUSTOMER_TYPE_SYSTEM_ACCOUNT);
      }

      if (StringUtils.isNotEmpty(paramRoleList)) {
        fUmgrCusRequest.setUmgrRoleIds(Collections.singletonList(paramRoleList));
      }
      if (StringUtils.isNotEmpty(paramBlackList)) {
        fUmgrCusRequest.setBlacklistReasons(
            Collections.singletonList(Integer.parseInt(paramBlackList)));
      }

      if (StringUtils.isEmpty(paramUserType)) {
        fUmgrCusRequest.setUserTypeIds(LIST_USER_TYPE_ID);
      } else {
        fUmgrCusRequest.setUserTypeIds(Collections.singletonList(Integer.parseInt(paramUserType)));
      }

      if (StringUtils.isNotEmpty(paramPosition)) {
        fUmgrCusRequest.setJobPositionIds(
            Collections.singletonList(Integer.parseInt(paramPosition)));
      }

      if (StringUtils.isEmpty(paramWalletType)) {
        List<Integer> lstWalletType = new ArrayList<>();
        lstWalletType.add(CustomerType.ACCOUNT);
        lstWalletType.add(CustomerType.WALLET);
        fUmgrCusRequest.setWalletTypeIds(lstWalletType);
      } else {
        fUmgrCusRequest.setWalletTypeIds(Arrays.asList(Integer.parseInt(paramWalletType)));
      }

      fUmgrCusRequest.setQuickSearch(fullTextSearch);
      fUmgrCusRequest.setFromDate(null);
      fUmgrCusRequest.setToDate(null);
      fUmgrCusRequest.setLimit(limit);
      fUmgrCusRequest.setOffset(offset);

      fUmgrCusResponse = umgrRolesPrivilegesEndpoint.findUmgrCustomers(fUmgrCusRequest);
      if (fUmgrCusResponse != null && fUmgrCusResponse.getStatus().getCode() == 0) {
        listAccount = fUmgrCusResponse.getCustomers();
      } else {
        listAccount = new ArrayList<>();
      }
      map.put("list", listAccount);
      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("total", (int) fUmgrCusResponse.getTotal());

      map.put("fullTextSearch", fullTextSearch);
      map.put("customerType", paramCustomerType);
      map.put("roleList", paramRoleList);
      map.put("blackList", paramBlackList);
      map.put("walletTypeCb", paramWalletType);
      map.put("userTypeCb", paramUserType);
      map.put("positionList", paramPosition);
      map.put(
          "paymentChannels",
          new PaymentChannel[] {PaymentChannel.ZOTA, PaymentChannel.SAPO, PaymentChannel.ZOTAHOME});

    } catch (Exception e) {
      map.put("codeError", fUmgrCusResponse.getStatus().getValue());
      LOG.error(e.getMessage(), e);
    }
    map.put("codeErr", request.getParameter("codeErr"));
    map.put("mesErr", "label.message.label");
    return "/customer_manage/list";
  }

  @GetMapping(value = "/details/{accountId}")
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT'"
          + ",'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String accountDetail(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap model,
      @PathVariable("accountId") Long accountId)
      throws FrontEndException {
    try {
      // get blackList
      getBlackListReason(model);
      // get position
      getPositions(model);

      // get province
      model.put("lstProvince", getProvince());
      model.put("lstDistrict", getDistrict(null));

      model.put("notification_mode_ids", CustomerType.NOTIFICATION_MODE_IDS);
      model.put("customer_type_ids", CustomerType.USER_CUSTOMER_TYPES);

      GetUmgrCustomerRolesRequest customerRolesReq = new GetUmgrCustomerRolesRequest(accountId);
      GetUmgrCustomerRolesResponse customerRolesRes =
          umgrRolesPrivilegesEndpoint.getUmgrCustomerRoles(customerRolesReq);
      List<CustomerRole> umgrRoles = customerRolesRes.getUmgrRoles();
      model.put("customer_roles", umgrRoles);
      model.put("isCustomerAgent", customerRolesRes.isCustomerAgent());
      model.put("isMerchantUser", customerRolesRes.isMerchantUser());

      if (customerRolesRes.isMerchantUser() || customerRolesRes.isFinanceUser()) {
        model.put("serviceTypes", ServiceType.TRANSACTION_RULES);
      } else {
        model.put("serviceTypes", ServiceType.AGENT_SERVICE_RULES);
      }

      GetCustomerAttributeTypeRequest cusAttTypeReq = new GetCustomerAttributeTypeRequest();
      GetCustomerAttributeTypeResponse cusAttTypeRes =
          customerEndpoint.getCustomerAttributeTypes(cusAttTypeReq);
      List<KeyValue> customerAttributeTypes = cusAttTypeRes.getCustomerAttributeTypes();
      model.put("customer_attribute_types", customerAttributeTypes);

      GetUmgrRolesResponse getUmgrRolesResponse = getDataRoleList(null, "Y", 0, 0);
      List<UmgrRole> roles = getUmgrRolesResponse.getRoles();
      model.put("list_role", roles);

      String serviceType = request.getParameter("serviceTypeIds");
      String paymentChannelId = request.getParameter("paymentChannelId");
      fetchFullInfoCustomerPutToView(
          model, accountId, customerAttributeTypes, serviceType, paymentChannelId);
      fetchImageProfileCustomer(request.getContextPath(), model, accountId);
      fetchActivityLoginCustomerPutToView(model, accountId);

      String editType = request.getParameter("edit_type");
      if ("edit".equals(editType)) {
        if (model.get("mesErr") == null || StringUtils.EMPTY.equals(model.get("mesErr"))) {
          model.put("mesErr", "label.message.label");
          model.put("codeErr", MessageNotify.SUCCESS_CODE);
          codeErr = StringUtils.EMPTY;
        }
      } else if ("add".equals(editType)) {
        if (model.get("mesErr") == null || StringUtils.EMPTY.equals(model.get("mesErr"))) {
          model.put("mesErr", "label.message.create.account");
          model.put("codeErr", MessageNotify.SUCCESS_CODE);
          codeErr = StringUtils.EMPTY;
        }
      } else {
        if (model.get("mesErr") == null
            || StringUtils.EMPTY.equals(model.get("mesErr"))
            || "null".equals((model.get("mesErr")))) {
          model.remove("mesErr");
          model.remove("codeErr");
          codeErr = StringUtils.EMPTY;
        }
      }

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      model.put(MessageNotify.codeErr, MessageNotify.ERROR_NAME);
      model.put(MessageNotify.mesErr, e.getMessage());
    }

    model.put("edit_type", "edit");
    model.put("customerTypes", CustomerType.USER_CUSTOMER_TYPES);

    return "/customer_manage/edit_account";
  }

  @RequestMapping(value = "/details/{accountId}", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String accountDetailUpdate(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute("customerDataForm") CustomerDataForm customerDataForm,
      ModelMap map,
      @PathVariable("accountId") Long accountId)
      throws FrontEndException {

    try {
      String editType = request.getParameter("edit_type");
      String paraStreet1 = request.getParameter("account-nameAddr");

      String paramStreet1Store = request.getParameter("street1Store");
      String paramAliasStore = request.getParameter("aliasStore");
      String paramBusinessPhoneStore = request.getParameter("businessPhoneStore");
      String paramOutLetStoreType = request.getParameter("outletStoreType");

      if ("edit".equals(editType)) {
        // Update Customer's info
        GetFullCustomerResponse customerRes = fetchCustomer(accountId);
        Customer customer = customerRes.getCustomer();

        // Cập nhật addresses
        List<Address> lstAddress = customerRes.getCustomer().getAddresses();
        Address address = new Address();
        Address address2 = new Address();

        String btnAction = request.getParameter("btn-action");
        boolean isUpdatingInfoCustomer =
            "save-account-personal".equals(btnAction)
                || "save-account-info".equals(btnAction)
                || "save-account-store".equals(btnAction);
        if ("save-account-personal".equals(btnAction)) {

          customer.setCountry(request.getParameter("account-country"));
          customer.setDateOfBirth(
              DateUtil.stringToDate(request.getParameter("account-dateofbirth"), "dd/MM/yyyy"));
          customer.setDescription(request.getParameter("account-about"));
          customer.setGender(NumberUtil.convertToInt(request.getParameter("account-gender")));
          customer.setLivingAddress(request.getParameter("account-living-address"));
          customer.setProvince(request.getParameter("current_province"));
          customer.setDistrict(request.getParameter("current_district"));

          if (lstAddress == null) {
            List<Address> lstAddressNew = new ArrayList<>();
            address.setAddressType(AddressType.RESIDENCE_ADDRESS.getId());
            address.setAddressStatus(AddressStatus.VALID_STATUS.getId());
            address.setStreet1(paraStreet1);

            lstAddressNew.add(address);
            customer.setAddresses(lstAddressNew);
          } else {
            for (Address item : lstAddress) {
              if (item.getAddressType() == AddressType.RESIDENCE_ADDRESS.getId()) {
                item.setAddressType(item.getAddressType());
                item.setAddressStatus(item.getAddressStatus());
                item.setStreet1(paraStreet1);
                customer.setAddresses(lstAddress);
              } else {
                List<Address> lstAddressNew = new ArrayList<>();
                Address addressNew = new Address();
                addressNew.setAddressType(AddressType.RESIDENCE_ADDRESS.getId());
                addressNew.setAddressStatus(AddressStatus.VALID_STATUS.getId());
                addressNew.setStreet1(paraStreet1);

                lstAddressNew.add(addressNew);
                customer.setAddresses(lstAddressNew);
              }
            }
          }

          if (StringUtils.isNotEmpty(customer.getDisplayName())
              && (customer.getFirstName() == null || customer.getLastName() == null)) {
            customer.setFirstName(customer.getDisplayName().split(StringUtil.SPACE_SYMBOL)[0]);
            customer.setLastName(customer.getDisplayName().split(StringUtil.SPACE_SYMBOL)[1]);
          }

        } else if ("save-account-store".equals(btnAction)) {
          if (lstAddress == null) {
            List<Address> lstAddressNew = new ArrayList<>();
            address2.setAddressType(AddressType.OUTLET_ADDRESS.getId());
            address2.setAlias(paramAliasStore);
            address2.setBusinessPhone(paramBusinessPhoneStore);
            address2.setOutletStoreType(OutletStoreType.getOutLetStoreType(paramOutLetStoreType));
            address2.setStreet1(paramStreet1Store);

            lstAddressNew.add(address2);
            customer.setAddresses(lstAddressNew);
          } else {
            for (Address item : lstAddress) {
              if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getId()) {
                item.setAlias(paramAliasStore);
                item.setBusinessPhone(paramBusinessPhoneStore);
                item.setOutletStoreType(OutletStoreType.getOutLetStoreType(paramOutLetStoreType));
                item.setStreet1(paramStreet1Store);
                customer.setAddresses(lstAddress);
              } else {
                List<Address> lstAddressNew = new ArrayList<>();
                Address addressNew = new Address();
                addressNew.setAddressType(AddressType.OUTLET_ADDRESS.getId());
                addressNew.setOutletStoreType(
                    OutletStoreType.getOutLetStoreType(paramOutLetStoreType));
                addressNew.setStreet1(paramStreet1Store);
                addressNew.setAlias(paramAliasStore);
                addressNew.setBusinessPhone(paramBusinessPhoneStore);
                lstAddressNew.add(addressNew);
                customer.setAddresses(lstAddressNew);
              }
            }
          }
        } else if ("save-account-info".equals(btnAction)) {
          int blockReason = Integer.parseInt(request.getParameter("blackList"));
          customer.setActive(0 == blockReason);
          customer.setBlackListReason(blockReason);
          customer.setMsisdn(request.getParameter("phone"));
          customer.setNotificationModeId(Integer.parseInt(request.getParameter("notification")));
          CustomerType customerType = new CustomerType();
          Integer customerTypeId = Integer.parseInt(request.getParameter("customerType"));
          customerType.setId(customerTypeId);
          customerType.setName(FULL_CUSTOMER_TYPES.get(customerTypeId));
          customer.setCustomerType(customerType);
          customer.setFirstName(request.getParameter("first-name"));
          customer.setLastName(request.getParameter("last-name"));
          customer.setDisplayName(
              request.getParameter("last-name") + " " + request.getParameter("first-name"));
          customer.setEmail(request.getParameter("email"));

          customer.setWalletTypeId(CustomerUtil.getWalletType(customerTypeId));
          customer.setUserTypeId(CustomerUtil.getUserType(customerTypeId));
          customer.setJobPosition(request.getParameter("positionList"));
          customer.setDescription(request.getParameter("description"));

        } else if ("save-customer-identity".equals(btnAction)) {
          long identityId = NumberUtil.convertToLong(request.getParameter("identityId"));
          long identityCusId = NumberUtil.convertToLong(request.getParameter("identityCusId"));
          identityCusId = identityCusId == 0L ? accountId : identityCusId;
          int identityTpe = NumberUtil.convertToInt(request.getParameter("identity-type"));
          String identityNumber = request.getParameter("identity-number");
          Date issueDate = DateUtil.stringToDate(request.getParameter("issue-date"), "dd/MM/yyyy");
          Date expireDate =
              DateUtil.stringToDate(request.getParameter("expire-date"), "dd/MM/yyyy");
          String placeOfIssue = request.getParameter("place-of-issue");

          Identity identity = new Identity();
          identity.setId(identityId == 0L ? null : identityId);
          identity.setCustomerId(identityCusId);
          identity.setIdentityType(identityTpe);
          identity.setStatus(3);
          identity.setIssueCountry("vietnam");
          identity.setActive(Boolean.TRUE);
          identity.setFullname(
              customer.getLastName() + SharedConstants.SPACE + customer.getFirstName());
          identity.setGenderId(customer.getGender());
          identity.setIdentity(identityNumber);
          identity.setDateIssued(issueDate);
          identity.setDateExpires(expireDate);
          identity.setIssuePlace(placeOfIssue);

          MultipartFile[] files = customerDataForm.getFiles();
          List<MultipartFile> listFile =
              (files != null) ? Arrays.asList(files) : Arrays.asList(null, null, null);
          MultipartFile frontImage = listFile.get(0);
          MultipartFile backImage = listFile.get(1);
          MultipartFile selfieImage = listFile.get(2);

          UpdateIdentityRequest updateCusKycReq = new UpdateIdentityRequest();
          if (frontImage != null && !StringUtils.EMPTY.equals(frontImage.getOriginalFilename())) {
            updateCusKycReq.setFrontName(frontImage.getOriginalFilename());
            updateCusKycReq.setFrontContentType(frontImage.getContentType());
            updateCusKycReq.setFrontContent(encodeRotateImage(frontImage));
          }

          if (backImage != null && !StringUtils.EMPTY.equals(backImage.getOriginalFilename())) {
            updateCusKycReq.setBackName(backImage.getOriginalFilename());
            updateCusKycReq.setBackContentType(backImage.getContentType());
            updateCusKycReq.setBackContent(encodeRotateImage(backImage));
          }

          if (selfieImage != null && !StringUtils.EMPTY.equals(selfieImage.getOriginalFilename())) {
            updateCusKycReq.setSelfieName(selfieImage.getOriginalFilename());
            updateCusKycReq.setSelfieContentType(selfieImage.getContentType());
            updateCusKycReq.setSelfieContent(encodeRotateImage(selfieImage));
          }

          updateCusKycReq.setIdentity(identity);

          UpdateIdentityResponse updateCusKycRes =
              customerKycEndpoint.updateIdentity(updateCusKycReq);
          if (updateCusKycRes == null
              || updateCusKycRes.getStatus() == null
              || updateCusKycRes.getStatus().getCode() > 0) {
            map.put(MessageNotify.mesErr, "Can not Update Customer Identity");
            map.put(MessageNotify.codeErr, MessageNotify.ERROR_CODE);
            throw new Exception("Can not Update Customer Identity");
            // return accountDetail(request, response, map, accountId);

          }
        }

        if (isUpdatingInfoCustomer) {
          UpdateCustomerRequest updateCustomerReq = new UpdateCustomerRequest(customer);
          UpdateCustomerResponse updateCustomerRes =
              umgrAccountEndpoint.updateCustomer(updateCustomerReq);

          if (updateCustomerRes == null || updateCustomerRes.getStatus() == null) {
            throw new Exception("Can not Update Customer");
          }
          if (0 != updateCustomerRes.getStatus().getCode()) {
            throw new Exception(updateCustomerRes.getStatus().getValue());
          }
        }

      } else {
        // Update Customer's attribute
        saveUpdateCustomerAttribute(request, accountId);
      }

    } catch (Exception e) {
      LOG.error("Error !!", e);
      map.put(MessageNotify.mesErr, e.getMessage());
      map.put(MessageNotify.codeErr, MessageNotify.ERROR_CODE);
    }

    return accountDetail(request, response, map, accountId);
  }

  //  private String encodeRotateImage(MultipartFile file) {
  //    try {
  //      if (file != null) {
  //        byte[] fileBytes = file.getBytes();
  //        byte[] rotateBytes = ImageOrientationUtil
  //            .rotateCorrectImage(file.getInputStream(), fileBytes);
  //        return Base64.getEncoder().encodeToString(rotateBytes == null ? fileBytes :
  // rotateBytes);
  //      }
  //    } catch (IOException e) {
  //      LOG.error("IOException", e);
  //    }
  //    return null;
  //  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String addAccount(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    mCustomer = null;
    String walletType = request.getParameter("wallet_type");
    String userType = request.getParameter("user_type");
    try {
      // get blackList
      getBlackListReason(map);

      // get position
      getPositions(map);

      // get province
      map.put("lstProvince", getProvince());
      map.put("lstDistrict", getDistrict(null));

      map.put("notification_mode_ids", CustomerType.NOTIFICATION_MODE_IDS);

      map.put("customer_type_ids", CustomerType.USER_CUSTOMER_TYPES);

      map.put("edit_type", "add");
      map.put("wallet_type", walletType);
      map.put("user_type", userType);

      map.put("account_object", mCustomer);
      mCustomer = null;

      map.put("codeErr", codeErr);
      map.put("mesErr", codeErr);
      codeErr = StringUtils.EMPTY;
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      map.put("codeErr", MessageNotify.ERROR_CODE);
      map.put("mesErr", ex.getMessage());
    }
    map.put("customerType", request.getParameter("customerType"));

    // codeErr = StringUtils.EMPTY;

    return "/customer_manage/edit_account";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('ADMIN_OPERATION','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String addAccountPost(
      HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    // codeErr = StringUtils.EMPTY;
    String userType = "N/A";
    String walletType = "N/A";
    String paramPosition = request.getParameter("positionList");
    String paramDescription = request.getParameter("description");
    String paramProvince = request.getParameter("current_province");
    String paramDistrict = request.getParameter("current_district");

    String paramAliasStore = request.getParameter("aliasStore");
    String paramBusinessPhoneStore = request.getParameter("businessPhoneStore");
    String paramStreet1Store = request.getParameter("street1Store");
    String paramOutLetStoreType = request.getParameter("outletStoreType");
    String cusType = request.getParameter("customerType");

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
      } catch (NumberFormatException e) {
        // codeErr = e.getMessage();
        LOG.error(e.getMessage(), e);
        map.put("codeErr", MessageNotify.ERROR_CODE);
        map.put("mesErr", e.getMessage());
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

      customer.setProvince(paramProvince);
      map.put("current_province", paramProvince);

      customer.setDistrict(paramDistrict);
      map.put("current_district", paramDistrict);

      customer.setUserTypeId(CustomerUtil.getUserType(customerTypeId));
      Integer userTypeId = customer.getUserTypeId();
      userType = UserType.USER_TYPE_IDS.get(userTypeId);
      map.put("user_type", userType);

      customer.setWalletTypeId(CustomerUtil.getWalletType(customerTypeId));
      Integer walletTypeId = customer.getWalletTypeId();
      walletType = CustomerType.WALLET_TYPE_IDS.get(walletTypeId);
      map.put("wallet_type", walletType);

      // -----Address
      Address address = new Address();
      Address address2 = new Address();

      List<Address> lstAddress = new ArrayList<>();
      address.setAddressType(AddressType.RESIDENCE_ADDRESS.getId());
      address.setAddressStatus(AddressStatus.VALID_STATUS.getId());
      address.setStreet1(request.getParameter("account-nameAddr"));

      address2.setAddressType(AddressType.OUTLET_ADDRESS.getId());
      address2.setAlias(paramAliasStore);
      address2.setBusinessPhone(paramBusinessPhoneStore);
      address2.setOutletStoreType(OutletStoreType.getOutLetStoreType(paramOutLetStoreType));
      address2.setStreet1(paramStreet1Store);

      lstAddress.add(address);
      lstAddress.add(address2);
      customer.setAddresses(lstAddress);
      // ---- end Address

      mCustomer = customer;
      createCustomerRequest.setCustomer(customer);

      createCustomerRequest.setNotificationMode(request.getParameter("notification"));
      CreateCustomerResponse createCustomerResponse =
          umgrAccountEndpoint.createCustomer(createCustomerRequest);
      Long customerId = createCustomerResponse.getCustomerId();

      UpdateAddressRequest updateAddressRequest = new UpdateAddressRequest();
      address2.setCustomerId(customerId);
      updateAddressRequest.setAddress(address2);
      UpdateAddressResponse updateAddressResponse =
          umgrAccountEndpoint.createCustomerAddress(updateAddressRequest);

      if (createCustomerResponse == null || createCustomerResponse.getStatus() == null) {
        throw new Exception("Can not Update Customer");
      }
      if (0 != createCustomerResponse.getStatus().getCode()) {
        // codeErr = createCustomerResponse.getStatus().getValue();
        codeErr = MessageNotify.ERROR_NAME;
        //        map.put("mesErr", createCustomerResponse.getStatus().getValue());
        throw new Exception(createCustomerResponse.getStatus().getValue());
      }
      map.put("codeErr", "");
      map.put("mesErr", "label.message.create.account");
      map.put("edit_type", "add");
      return "redirect:" + CUSTOMER_MANAGE_DETAIL + "/" + customerId;
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      codeErr = ex.getMessage();
      map.put("codeErr", codeErr);
      map.put("mesErr", ex.getMessage());

      map.put("wallet_type", walletType);
      map.put("user_type", userType);
      return "redirect:" + "/customer/manage/add?customerType=" + cusType;
    }
  }

  /*  ACCOUNT*/
  @RequestMapping(value = "/reset-password/{accountId}", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "')")
  private String resetPassword(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("accountId") String accountId) {
    try {
      getInfoResetPassword(request, response, map, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/customer_manage/reset_password";
  }

  @RequestMapping(value = "/reset-password/{accountId}", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "')")
  public String resetPasswordPost(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @PathVariable("accountId") String accountId) {
    try {
      functionResetPassWord(request, response, map, accountId);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return resetPassword(request, response, map, accountId);
  }

  private String generateCredential(Customer customer, Integer credentialType) {
    String defaultCredential = null;
    if (CredentialType.BY_PIN.equals(credentialType)) {
      defaultCredential = RandomStringUtils.random(6, false, true);

    } else if (CredentialType.BY_PASSWORD.equals(credentialType)) {
      //			//int minimumCredentialLength = SharedConstants.DEFAULT_CREDENTIAL_LENGTH_MIN;
      int minimumCredentialLength = 8;
      String letters =
          RandomStringUtils.randomAlphabetic(new Double(Math.random() * 2 + 2).intValue());
      letters = letters.toLowerCase();
      letters = letters.replace(letters.charAt(0), Character.toUpperCase(letters.charAt(0)));
      String specialCharacters = RandomStringUtils.random(1, SPECIAL_CHARACTER);
      String numbers =
          RandomStringUtils.randomNumeric(
              minimumCredentialLength - letters.length() - specialCharacters.length());
      StringBuilder sb = new StringBuilder();
      sb.append(letters).append(specialCharacters).append(numbers);
      List<Character> characterCredentialL = new ArrayList<>();
      char[] characterCredentialArr = sb.toString().toCharArray();
      for (char c : characterCredentialArr) {
        characterCredentialL.add(c);
      }
      Collections.shuffle(characterCredentialL);
      sb = new StringBuilder();
      for (char c : characterCredentialL) {
        sb.append(c);
      }
      defaultCredential = sb.toString();
    }

    return defaultCredential;
  }

  private GetCustomerResponse getCustomer(long customerId) throws Exception {
    GetCustomerRequest getCustomerRequest = new GetCustomerRequest();
    getCustomerRequest.setCustomerId(customerId);
    GetCustomerResponse getCustomerResponse = umgrAccountEndpoint.getCustomer(getCustomerRequest);
    if (getCustomerResponse == null || getCustomerResponse.getStatus() == null) {
      throw new Exception("Can not get Customer");
    }
    if (0 != getCustomerResponse.getStatus().getCode()) {
      throw new Exception(getCustomerResponse.getStatus().getValue());
    }

    return getCustomerResponse;
  }

  @Override
  protected void getRoleList(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    List<UmgrRole> lst = new ArrayList<>();

    // Role for Customer
    UmgrRole umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.CUSTOMER);
    umgrRole.setDescription(RoleConstants.CUSTOMER);
    lst.add(umgrRole);

    // Role for Merchant
    umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.MERCHANT);
    umgrRole.setDescription(RoleConstants.MERCHANT);
    lst.add(umgrRole);

    // Role for Agent
    umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.AGENT);
    umgrRole.setDescription(RoleConstants.AGENT);
    lst.add(umgrRole);

    // Role for Provider
    umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.TELCO);
    umgrRole.setDescription(RoleConstants.TELCO);
    lst.add(umgrRole);
    umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.AGGREATOR);
    umgrRole.setDescription(RoleConstants.AGGREATOR);
    lst.add(umgrRole);
    umgrRole = new UmgrRole();
    umgrRole.setRole(RoleConstants.BILLER);
    umgrRole.setDescription(RoleConstants.BILLER);
    lst.add(umgrRole);

    map.put("listRoles", lst);
  }
}
