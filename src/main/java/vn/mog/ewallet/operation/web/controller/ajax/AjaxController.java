package vn.mog.ewallet.operation.web.controller.ajax;

import static vn.mog.ewallet.operation.web.controller.notification.NotificationPartnerController.NOTIFICATION_TYPE;
import static vn.mog.ewallet.operation.web.exception.MessageNotify.ERROR_CODE;
import static vn.mog.ewallet.operation.web.exception.MessageNotify.ERROR_NAME;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.account.StaffAccountController;
import vn.mog.ewallet.operation.web.controller.account.WalletAccountController;
import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerRequest;
import vn.mog.ewallet.operation.web.controller.balance.SummaryTransactionByCustomerResponse;
import vn.mog.ewallet.operation.web.controller.balance.contract.SummaryTransaction;
import vn.mog.ewallet.operation.web.exception.ErrorConstant;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindPartnerNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.FindPartnerNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.Notification;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.bean.NotificationDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.EventEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.SenpayActionEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.PricingDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.ChangeCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrPrivilegeStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrRoleStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DataTableResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteCustomerAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GenerateGoogleAuthenticatorResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetGoogleAuthenticatorInfoResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.RemoveUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.SendUserAccountInforNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrCustomerRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CredentialType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRolePrivilege;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SaleGetListAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.contract.base.MobiliserResponseType;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

import static vn.mog.ewallet.operation.web.exception.MessageNotify.*;

@RestController
@RequestMapping(value = "/ajax-controller")
public class AjaxController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AjaxController.class);

  /*Account*/

  @RequestMapping(value = "/account/changeBlackList", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeBlackList(HttpServletRequest request) throws Exception {
    String blacklistReasonType = request.getParameter("blacklistReasonType");
    String customerId = request.getParameter("customerId");
    String remark = request.getParameter("remark");

    ChangeBlacklistReasonRequest changeBlacklistReasonRequest = new ChangeBlacklistReasonRequest();
    changeBlacklistReasonRequest.setBlacklistReasonType(
        NumberUtil.convertToInt(blacklistReasonType));
    changeBlacklistReasonRequest.setCustomerId(NumberUtil.convertToLong(customerId));
    changeBlacklistReasonRequest.setRemark(remark);

    return ResponseEntity.ok(
        customerEndpoint.changeBlacklistReasonResponse(changeBlacklistReasonRequest));
  }

  @RequestMapping(value = "/account/resendInfo", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> resendInfo(HttpServletRequest request) throws Exception {

    String customerId = request.getParameter("customerId");

    SendUserAccountInforNotificationRequest sendNotiReq =
        new SendUserAccountInforNotificationRequest();
    sendNotiReq.setCustomerId(NumberUtil.convertToLong(customerId));

    return ResponseEntity.ok(paymentCustomerEndpoint.sendAccountInfoNotification(sendNotiReq));
  }

  @RequestMapping(value = "/role/change-status-role", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeStatusRole(HttpServletRequest request) throws Exception {
    String roleName = request.getParameter("roleName");
    String active = request.getParameter("active");

    ChangeUmgrRoleStatusRequest changeUmgrRoleStatusRequest = new ChangeUmgrRoleStatusRequest();
    changeUmgrRoleStatusRequest.setRoleId(roleName);
    changeUmgrRoleStatusRequest.setActive(active.charAt(0));

    return ResponseEntity.ok(
        umgrRolesPrivilegesEndpoint.changeUmgrRoleStatus(changeUmgrRoleStatusRequest));
  }

  @RequestMapping(value = "/privilege/change-status-privilege", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeStatusPrivilege(HttpServletRequest request) throws Exception {
    String privilegeName = request.getParameter("privilegeName");
    String active = request.getParameter("active");

    ChangeUmgrPrivilegeStatusRequest changeUmgrPrivilegeRequest =
        new ChangeUmgrPrivilegeStatusRequest();
    changeUmgrPrivilegeRequest.setPrivilegeId(privilegeName);
    changeUmgrPrivilegeRequest.setActive(active.charAt(0));

    return ResponseEntity.ok(
        umgrRolesPrivilegesEndpoint.changeUmgrPrivilegeStatus(changeUmgrPrivilegeRequest));
  }

  @RequestMapping(value = "/role/update-role", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> updateRole(HttpServletRequest request) throws Exception {
    String roleName = request.getParameter("roleName");
    String roleDescription = request.getParameter("roleDescription");

    UmgrRole role = new UmgrRole();
    role.setRole(roleName);
    role.setDescription(roleDescription);
    UpdateUmgrRoleRequest updateUmgrRoleRequest = new UpdateUmgrRoleRequest();
    updateUmgrRoleRequest.setRole(role);

    return ResponseEntity.ok(umgrRolesPrivilegesEndpoint.updateUmgrRole(updateUmgrRoleRequest));
  }

  @RequestMapping(value = "/role/{roleId}/grantPrivilege", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> updateRole(
      HttpServletRequest request, @PathVariable("roleId") String roleId) throws Exception {
    MobiliserResponseType response = new MobiliserResponseType();
    UmgrRolePrivilege rolePrivilege = new UmgrRolePrivilege();
    rolePrivilege.setRole(roleId);
    rolePrivilege.setValidFrom(
        DateUtil.stringToDate(request.getParameter("validFrom"), "MM/dd/yyyy"));
    rolePrivilege.setValidTo(DateUtil.stringToDate(request.getParameter("validTo"), "MM/dd/yyyy"));

    String isEdit = request.getParameter("isEdit");
    if (StringUtils.isEmpty(isEdit) || "null".equalsIgnoreCase(isEdit)) {
      rolePrivilege.setPrivilege(request.getParameter("privilege"));
      CreateUmgrRolePrivilegeRequest createUmgrRolePrivilegeRequest =
          new CreateUmgrRolePrivilegeRequest();
      createUmgrRolePrivilegeRequest.setRolePrivilege(rolePrivilege);

      response =
          umgrRolesPrivilegesEndpoint.createUmgrRolePrivilege(createUmgrRolePrivilegeRequest);
    } else {
      rolePrivilege.setPrivilege(request.getParameter("privilege_id"));
      UpdateUmgrRolePrivilegeRequest updateUmgrRolePrivilegeRequest =
          new UpdateUmgrRolePrivilegeRequest();
      updateUmgrRolePrivilegeRequest.setRolePrivilege(rolePrivilege);

      response =
          umgrRolesPrivilegesEndpoint.updateUmgrRolePrivilege(updateUmgrRolePrivilegeRequest);
    }

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/role/remove-role-privilege", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> removeRolePrivilege(HttpServletRequest request) throws Exception {
    String roleId = request.getParameter("roleId");
    String privilegeId = request.getParameter("privilegeId");

    DeleteUmgrRolePrivilegeRequest deleteUmgrRolePrivilegeRequest =
        new DeleteUmgrRolePrivilegeRequest();
    deleteUmgrRolePrivilegeRequest.setRoleId(roleId);
    deleteUmgrRolePrivilegeRequest.setPrivilegeId(privilegeId);

    MobiliserResponseType response =
        umgrRolesPrivilegesEndpoint.deleteUmgrRolePrivilege(deleteUmgrRolePrivilegeRequest);

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/account/{accountId}/grantRole", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> customerGrantRole(
      HttpServletRequest request, @PathVariable("accountId") String accountId) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    CustomerRole customerRole = new CustomerRole();
    customerRole.setCustomerId(Long.parseLong(accountId));
    customerRole.setRole(request.getParameter("role"));
    customerRole.setValidFromDate(sdf.parse(request.getParameter("validFrom")));
    customerRole.setValidToDate(sdf.parse(request.getParameter("validTo")));

    String roleId = request.getParameter("role_id");
    if (StringUtils.isNotBlank(roleId)) {
      customerRole.setId(Long.parseLong(roleId));
      UpdateUmgrCustomerRoleRequest updateUmgrCustomerRoleRequest =
          new UpdateUmgrCustomerRoleRequest();
      updateUmgrCustomerRoleRequest.setCustomerRole(customerRole);
      UpdateUmgrCustomerRoleResponse response =
          umgrRolesPrivilegesEndpoint.updateUmgrCustomerRole(updateUmgrCustomerRoleRequest);
      if (response != null && response.getStatus() != null && response.getStatus().getCode() == 0) {
        return ResponseEntity.ok(response);
      } else {
        throw new Exception(response.getStatus().getValue());
      }
    } else {
      AddUmgrCustomerRoleRequest addUmgrCustomerRoleRequest = new AddUmgrCustomerRoleRequest();
      addUmgrCustomerRoleRequest.setCustomerRole(customerRole);
      AddUmgrCustomerRoleResponse response =
          umgrRolesPrivilegesEndpoint.addUmgrCustomerRole(addUmgrCustomerRoleRequest);
      if (response != null && response.getStatus() != null && response.getStatus().getCode() == 0) {
        return ResponseEntity.ok(response);
      } else {
        throw new Exception(response.getStatus().getValue());
      }
    }
  }

  @RequestMapping(value = "/account/remove-customer-role", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> customerDeleteRole(HttpServletRequest request) throws Exception {
    String roleId = request.getParameter("roleName");

    RemoveUmgrCustomerRoleRequest removeUmgrCustomerRoleRequest =
        new RemoveUmgrCustomerRoleRequest();
    removeUmgrCustomerRoleRequest.setId(Long.parseLong(roleId));

    return ResponseEntity.ok(
        umgrRolesPrivilegesEndpoint.removeUmgrCustomerRole(removeUmgrCustomerRoleRequest));
  }

  @RequestMapping(value = "/consumer/getGoogleAuthenticatorInfo", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> getGoogleAuthenticatorInfo(HttpServletRequest request) throws Exception {
    GetGoogleAuthenticatorInfoResponse response;
    GetGoogleAuthenticatorInfoRequest getGoogleAuthenticatorInfoRe =
        new GetGoogleAuthenticatorInfoRequest();
    getGoogleAuthenticatorInfoRe.setCustomerId(Long.parseLong(request.getParameter("account_id")));

    response = umgrAccountEndpoint.getGoogleAuthenticatorInfo(getGoogleAuthenticatorInfoRe);
    response.setUrl(URLDecoder.decode(response.getUrl(), "UTF-8"));

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/consumer/resetGoogleAuthenticatorInfo", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> resetGoogleAuthenticatorInfo(HttpServletRequest request)
      throws Exception {
    GenerateGoogleAuthenticatorResponse response;
    GenerateGoogleAuthenticatorRequest generateGoogleAuthenticatorRequest =
        new GenerateGoogleAuthenticatorRequest();
    generateGoogleAuthenticatorRequest.setCustomerId(
        Long.parseLong(request.getParameter("account_id")));

    response =
        umgrAccountEndpoint.generateGoogleAuthenticatorInfo(generateGoogleAuthenticatorRequest);

    if (response != null && response.getStatus() != null && response.getStatus().getCode() == 0) {
      return ResponseEntity.ok(getGoogleAuthenticatorInfo(request));
    } else if (response != null && response.getStatus() != null) {
      throw new Exception(response.getStatus().getValue());
    } else {
      throw new Exception("No response");
    }
  }

  @RequestMapping(
      value = "/consumer/{account_id}/deleteCustomerAttribute",
      method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> setGoogleAuthenticatorInfo(
      HttpServletRequest request, @PathVariable("account_id") String accountId) throws Exception {
    DeleteCustomerAttributeResponse response;
    DeleteCustomerAttributeRequest deleteCustomerAttributeRequest =
        new DeleteCustomerAttributeRequest();
    deleteCustomerAttributeRequest.setCustomerId(Long.parseLong(accountId));
    deleteCustomerAttributeRequest.setCustomerAttributeTypeId(
        Integer.parseInt(request.getParameter("attribute_id")));

    response = umgrAccountEndpoint.deleteCustomerAttribute(deleteCustomerAttributeRequest);

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/customer/find-customers", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findUmgrCustomers(HttpServletRequest request) throws Exception {

    FindUmgrCustomerRequest findUmgrCustomerRequest = new FindUmgrCustomerRequest();

    String fullTextSearch = request.getParameter("fullTextSearch");
    String paramCustomerType = request.getParameter("customerType");
    String paramRoleList = request.getParameter("roleList");
    String paramBlackList = request.getParameter("blackList");
    String paramCustomerTypeSystem = request.getParameter("customerTypeSystem");
    String paramWalletType = request.getParameter("walletTypeIds");
    String paramUserType = request.getParameter("userTypeIds");
    String paramPosition = request.getParameter("positionList");

    if (StringUtils.isNotEmpty(paramCustomerType)) {
      findUmgrCustomerRequest.setCustomerTypes(
          Collections.singletonList(Integer.parseInt(paramCustomerType)));
    } else {
      List<Integer> accountTypes = new ArrayList<>();
      if (StaffAccountController.SYSTEM_STAFF.equals(paramCustomerTypeSystem)) {
        accountTypes.add(CustomerType.ID_STAFF);
        accountTypes.add(CustomerType.ID_ADMIN);
        accountTypes.add(CustomerType.ID_SYSTEM);
      } else if (WalletAccountController.SYSTEM_WALLET.equals(paramCustomerTypeSystem)) {
        accountTypes.add(CustomerType.ID_POOL);
        accountTypes.add(CustomerType.ID_SOF);
        accountTypes.add(CustomerType.ID_ZOTA);
      }
      findUmgrCustomerRequest.setCustomerTypes(accountTypes);
    }

    if (StringUtils.isNotEmpty(paramWalletType)) {
      findUmgrCustomerRequest.setWalletTypeIds(
          Collections.singletonList(Integer.parseInt(paramWalletType)));
    } else {
      List<Integer> lstWalletType = new ArrayList<>();
      lstWalletType.add(CustomerType.ACCOUNT);
      lstWalletType.add(CustomerType.WALLET);
      findUmgrCustomerRequest.setWalletTypeIds(lstWalletType);
    }

    if (StringUtils.isNotEmpty(paramUserType)) {
      findUmgrCustomerRequest.setUserTypeIds(
          Collections.singletonList(Integer.parseInt(paramUserType)));
    } else {
      findUmgrCustomerRequest.setUserTypeIds(UserType.LIST_USER_TYPE_ID);
    }

    if (StringUtils.isNotEmpty(paramPosition)) {
      findUmgrCustomerRequest.setJobPositionIds(
          Collections.singletonList(Integer.parseInt(paramPosition)));
    }

    if (StringUtils.isNotEmpty(paramRoleList)) {
      findUmgrCustomerRequest.setUmgrRoleIds(Collections.singletonList(paramRoleList));
    }

    if (StringUtils.isNotEmpty(paramBlackList)) {
      findUmgrCustomerRequest.setBlacklistReasons(
          Collections.singletonList(Integer.parseInt(paramBlackList)));
    }

    if (StringUtils.isNotEmpty(fullTextSearch)) {
      findUmgrCustomerRequest.setQuickSearch(fullTextSearch);
    }

    findUmgrCustomerRequest.setOffset(Integer.parseInt(request.getParameter("start")));
    findUmgrCustomerRequest.setLimit(Integer.parseInt(request.getParameter("length")));

    FindUmgrCustomerResponse findUmgrCustomerResponse =
        umgrRolesPrivilegesEndpoint.findUmgrCustomers(findUmgrCustomerRequest);

    DataTableResponse<Customer> response = new DataTableResponse<>();
    response.setRecordsTotal(findUmgrCustomerResponse.getTotal());
    response.setRecordsFiltered(findUmgrCustomerResponse.getTotal());
    response.setDataList(findUmgrCustomerResponse.getCustomers());

    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/change-password", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changPassWord(HttpServletRequest request) throws Exception {
    String currentPassword = request.getParameter("currentPassword");
    String newPassword = request.getParameter("newPassword");
    ChangeCredentialRequest changeCredentialRequest = new ChangeCredentialRequest();
    changeCredentialRequest.setCredentialType(CredentialType.BY_PASSWORD);
    changeCredentialRequest.setOldCredential(currentPassword);
    changeCredentialRequest.setNewCredential(newPassword);

    return ResponseEntity.ok(customerEndpoint.changePassWord(changeCredentialRequest));
  }

  @RequestMapping(value = "/get-location/{parentCode}/{locationType}", method = RequestMethod.GET)
  public Collection<Location> getLocation(
      HttpServletRequest request,
      @PathVariable("parentCode") String parentCode,
      @PathVariable("locationType") Integer locationType)
      throws Exception {
    FindLocationRequest findLocationRequest = new FindLocationRequest();
    findLocationRequest.setParentCode(parentCode);
    findLocationRequest.setLocationType(locationType);
    findLocationRequest.setCodes(null);
    FindLocationResponse findLocationBaseRespType =
        authSystemEndpoint.getLocation(findLocationRequest);

    return findLocationBaseRespType.getLocations();
  }

  /* SenPay*/
  @RequestMapping(value = "/sendPay/changeStatus", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senpayAccountStatusChange(HttpServletRequest request) throws Exception {
    String customerId = request.getParameter("accountId");
    String active = request.getParameter("active");

    SpecialProviderAccountChangeStatusRequest changeStatusRequest =
        new SpecialProviderAccountChangeStatusRequest();
    if (StringUtils.isNotEmpty(active)) {
      if (active.equals("1")) {
        changeStatusRequest.setStatus(0);
      } else {
        changeStatusRequest.setStatus(1);
      }
    }
    changeStatusRequest.setAccountName(customerId);
    changeStatusRequest.setProviderCode(ProviderCode.BILL_SENPAY);

    SpecialProviderAccountChangeStatusResponse response =
        specialProviderSenpayEndpoint.providerSpecialAccoutChangeStatus(changeStatusRequest);
    return ResponseEntity.ok(response);
  }

  /* SenPay Tool*/
  @RequestMapping(value = "/sendPay-tool/changeStatus", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senpayToolAccountStatusChange(HttpServletRequest request) throws Exception {
    String customerId = request.getParameter("accountId");
    String active = request.getParameter("active");

    SpecialProviderAccountChangeStatusRequest changeStatusRequest =
            new SpecialProviderAccountChangeStatusRequest();
    if (StringUtils.isNotEmpty(active)) {
      if (active.equals("1")) {
        changeStatusRequest.setStatus(0);
      } else {
        changeStatusRequest.setStatus(1);
      }
    }
    changeStatusRequest.setAccountName(customerId);
    changeStatusRequest.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);

    SpecialProviderAccountChangeStatusResponse response =
            billSenpayToolEndpoint.providerSpecialAccoutChangeStatus(changeStatusRequest);
    return ResponseEntity.ok(response);
  }

  /* SenPay*/
  @RequestMapping(value = "/sendPay/delete", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senPayAccountDelete(HttpServletRequest request) throws Exception {
    String customerId = request.getParameter("accountId");

    SpecialProviderAccountChangeRequest deleteRequest = new SpecialProviderAccountChangeRequest();
    deleteRequest.setAccountName(customerId);
    deleteRequest.setProviderCode(ProviderCode.BILL_SENPAY);

    SpecialProviderAccountChangeResponse response =
        specialProviderSenpayEndpoint.providerSpecialAccountDelete(deleteRequest);
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/sendPay-tool/delete", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senPayToolAccountDelete(HttpServletRequest request) throws Exception {
    String customerId = request.getParameter("accountId");

    SpecialProviderAccountChangeRequest deleteRequest = new SpecialProviderAccountChangeRequest();
    deleteRequest.setAccountName(customerId);
    deleteRequest.setProviderCode(ProviderCode.BILL_SENPAY_TOOL);

    SpecialProviderAccountChangeResponse response =
            billSenpayToolEndpoint.providerSpecialAccountDelete(deleteRequest);
    return ResponseEntity.ok(response);
  }


  @RequestMapping(value = "/sendPay/province/get", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senpayProvinceGet(HttpServletRequest request) throws Exception {
    // TODO: ProviderCode.BILL_SENPAY
    try {
      SpecialProviderAllowedProvinceGetResponse response =
          specialProviderSenpayEndpoint.allowedProvinceGet();
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.ok(ErrorConstant.GENERAL_ERROR.message());
    }
  }

  @RequestMapping(value = "/sendPay/province/change", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> senpayProvinceChange(HttpServletRequest request) throws Exception {
    // TODO: ProviderCode.BILL_SENPAY
    try {
      String province = request.getParameter("province");
      String regionCode = request.getParameter("regionCode");
      String actionType = request.getParameter("actionType");

      SpecialProviderAllowedProviderChangeRequest specialProviderAllowedProviderChangeRequest =
          new SpecialProviderAllowedProviderChangeRequest();
      specialProviderAllowedProviderChangeRequest.setProviderCode(ProviderCode.BILL_SENPAY);
      specialProviderAllowedProviderChangeRequest.setProvince(province);
      specialProviderAllowedProviderChangeRequest.setRegionCode(regionCode);
      specialProviderAllowedProviderChangeRequest.setActionType(
          SenpayActionEnum.valueOf(actionType));

      SpecialProviderAllowedProvinceChangeResponse response =
          specialProviderSenpayEndpoint.allowedProvinceChange(
              specialProviderAllowedProviderChangeRequest);
      if (response.getStatus().getCode() != SUCCESS_CODE) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
      }
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  // get All cusomer
  @RequestMapping(value = "/all/account", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> allAccount(HttpServletRequest request) throws Exception {
    String search = StringUtils.trimToEmpty(request.getParameter("search"));
    String[] cusId = request.getParameterValues("cusId[]");
    vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans
            .Customer
        custom =
            new vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet
                .beans.Customer();
    GetCustomerRequest requestCus = new GetCustomerRequest();
    if (search != null && !search.isEmpty()) {
      requestCus.setMsisdn(search);
    }
    if (cusId != null && cusId.length > 0) {
      requestCus.setCustomerId(Long.valueOf(cusId[0]));
    }

    GetCustomerResponse responseCus = walletCustomerEndpoint.getCustomer(requestCus);
    if (responseCus != null && responseCus.getStatus().getCode() == 0) {
      custom = responseCus.getCustomer();
    }
    return ResponseEntity.ok(custom);
  }

  @RequestMapping(value = "/changeStatus/hold/success", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeStatusHoldSuccess(HttpServletRequest request) throws Exception {
    String txtId = String.valueOf(request.getParameter("txtId"));
    TransactionHoldApproveToSuccessResponse response =
        new TransactionHoldApproveToSuccessResponse();
    if (txtId == null || txtId.isEmpty()) {
      MobiliserResponseType errorRes = new MobiliserResponseType();
      errorRes.setStatus(new Status(ERROR_CODE, ERROR_NAME));
      return ResponseEntity.badRequest().body(errorRes);
    }

    try {
      TransactionHoldApproveToSuccessRequest requestTxt =
          new TransactionHoldApproveToSuccessRequest();
      requestTxt.setTxnId(Long.valueOf(txtId));

      response = paymentSystemEndpoint.changeStatusTxtHold(requestTxt);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/sendEmail/report/balance/customer", method = RequestMethod.GET)
  @ResponseBody
  public String sendEmailReportBalanceCus() throws Exception {
    String response = sofCashEndpoint.sendEmailReportBalanceCustomer();
    return response;
  }

  @RequestMapping(value = "/sendEmail/report/balance/provider", method = RequestMethod.GET)
  @ResponseBody
  public String sendEmailReportBalanceProvider() throws Exception {
    String result = "";
    String response = sofCashEndpoint.sendEmailReportBalanceCardStore();
    String response1 = sofCashEndpoint.sendEmailReportBalanceCardStoreOffline();
    String response2 = sofCashEndpoint.sendEmailReportBalanceCardStoreN02();
    if (response.equalsIgnoreCase(response1) && response.equalsIgnoreCase(response2)) {
      result = "OK";
    }
    return result;
  }

  @RequestMapping(value = "/find-customers", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findCustomers(HttpServletRequest request) throws Exception {
    String search = StringUtils.trimToEmpty(request.getParameter("search"));
    String type = request.getParameter("customerType");
    if (search == null || search.isEmpty()) {
      return ResponseEntity.badRequest().body(new FindFullCustomerResponse().getCustomers());
    }
    FindFullCustomerRequest fcRequest = new FindFullCustomerRequest();
    fcRequest.setTextSearch(search);
    if (type != null && !type.isEmpty()) {
      fcRequest.setCustomerTypes(Collections.singletonList(Integer.valueOf(type)));
    }
    try {
      FindFullCustomerResponse fcResponse = walletCustomerEndpoint.findCustomers(fcRequest);
      return ResponseEntity.ok(fcResponse.getCustomers());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new FindFullCustomerResponse().getCustomers());
    }
  }

  @RequestMapping(value = "/v2/find-customers", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findCustomersV2(HttpServletRequest request) throws Exception {
    String search = StringUtils.trimToEmpty(request.getParameter("search"));
    String type = request.getParameter("customerType");
    //if (search == null || search.isEmpty()) {
    //  return ResponseEntity.badRequest().body(new FindFullCustomerResponse().getCustomers());
    //}
    String page = request.getParameter("page");
    Integer limit = 20;
    FindFullCustomerRequest fcRequest = new FindFullCustomerRequest();
    fcRequest.setOffset(Integer.parseInt(page) * limit);
    fcRequest.setLimit(limit);
    fcRequest.setTextSearch(search);
    if (type != null && !type.isEmpty()) {
      fcRequest.setCustomerTypes(Collections.singletonList(Integer.valueOf(type)));
    }
    try {
      FindFullCustomerResponse fcResponse = walletCustomerEndpoint.findCustomers(fcRequest);
      return ResponseEntity.ok(fcResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new FindFullCustomerResponse());
    }
  }

  @RequestMapping(value = "/sale/find-customers", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findCustomersBySale(HttpServletRequest request) throws Exception {
    String search = StringUtils.trimToEmpty(request.getParameter("search"));
    if (search == null || search.isEmpty()) {
      return ResponseEntity.badRequest().body(new SaleGetListAgentResponse().getCustomers());
    }
    SaleGetListAgentRequest saleGetListAgentRequest = new SaleGetListAgentRequest();
    saleGetListAgentRequest.setQuickSearch(search);
    try {
      SaleGetListAgentResponse saleGetListAgentResponse =
          umgrAccountEndpoint.saleGetAgents(saleGetListAgentRequest);
      return ResponseEntity.ok(saleGetListAgentResponse.getCustomers());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new SaleGetListAgentResponse().getCustomers());
    }
  }

  @RequestMapping(value = "/transaction/summary-by-customer", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> transactionSummaryByCustomer(HttpServletRequest request)
      throws Exception {
    DataTableResponse<SummaryTransaction> response = new DataTableResponse<>();
    SummaryTransactionByCustomerRequest summaryTransactionByCustomerRequest =
        new SummaryTransactionByCustomerRequest();
    SummaryTransactionByCustomerResponse summaryTransactionByCustomerResponse = null;

    List<Long> customerIds = null;
    List customerList;
    try {
      String[] paramIds = request.getParameterValues("quickSearch[]");

      if (paramIds != null && paramIds.length > 0) {
        summaryTransactionByCustomerRequest.setCustomerIds(NumberUtil.convertListToLong(paramIds));
      } else {
        List<String> rolesOfCaller = getRolesOfCaller();
        customerList = getListSaleByParent(rolesOfCaller);
        // If Caller is Sale, show all Caller's agents' transactions
        // Get list Agents' Id to show Transaction
        // Sale doesn't have own wallet, so no need to show Sale's transactions
        if (!rolesOfCaller.contains(RoleConstants.ADMIN_OPERATION)
            && (customerIds == null || customerIds.isEmpty())
            && customerList != null) {
          customerIds = new ArrayList<>();
          try {
            if (customerList.size() > 0) {
              for (Customer customer : (List<Customer>) customerList) {
                customerIds.add(customer.getId());
              }
            } else {
              customerIds.add(callerUtil.getCallerInformation().getActorId());
            }

          } catch (ClassCastException e) {
            customerIds = null;
          }
        }
        if (customerIds != null) {
          customerIds.remove(null);
        }
        summaryTransactionByCustomerRequest.setCustomerIds(customerIds);
      }

      summaryTransactionByCustomerRequest.setFromDate(Utils.getStartOfMonth(new Date()));
      summaryTransactionByCustomerRequest.setToDate(Utils.getEndOfMonth(new Date()));

      summaryTransactionByCustomerRequest.setOffset(
          Integer.parseInt(request.getParameter("start")));
      summaryTransactionByCustomerRequest.setLimit(
          Integer.parseInt(request.getParameter("length")));

      summaryTransactionByCustomerResponse =
          walletEndpoint.transactionSummaryByCustomer(summaryTransactionByCustomerRequest);

      if (summaryTransactionByCustomerResponse.getTransactions().isEmpty()) {
        response.setDataList(Collections.EMPTY_LIST);
        response.setRecordsTotal(0L);
        response.setRecordsFiltered(0L);
      } else {
        response.setDataList(
            (List<SummaryTransaction>) summaryTransactionByCustomerResponse.getTransactions());
        response.setRecordsTotal((long) summaryTransactionByCustomerResponse.getTotalRecords());
        response.setRecordsFiltered((long) summaryTransactionByCustomerResponse.getTotalRecords());
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      return ResponseEntity.ok(summaryTransactionByCustomerResponse);
    } catch (Exception e) {
      return ResponseEntity.ok(e);
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/find-reconciliation", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> findReconciliation(HttpServletRequest request) throws Exception {
    DataTableResponse<CustomerReconcileSummary> response = new DataTableResponse<>();
    ReconcileCustomerFindResponse reconcileCustomerFindResponse = null;
    try {
      String[] paramIds = request.getParameterValues("quickSearch[]");
      String paramDateRange = request.getParameter("range");
      String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
      String isBalanceNotMatch = request.getParameter("isBalanceNotMatch");

      ReconcileCustomerFindRequest reconcileCustomerFindRequest =
          new ReconcileCustomerFindRequest();
      if (paramIds != null && paramIds.length > 0) {
        reconcileCustomerFindRequest.setCustomerIds(NumberUtil.convertListToLong(paramIds));
      }

      /*logic đối soát: mặc đinh là ngày hôm qua nếu param.range null*/
      if (paramDateRange != null && !paramDateRange.isEmpty()) {
        Date[] dates = parseDateRange(paramDateRange, false);
        Date fromDate = dates[0];
        Date endDate = dates[1];

        reconcileCustomerFindRequest.setFromDate(fromDate);
        reconcileCustomerFindRequest.setEndDate(endDate);
      } else {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        OffsetDateTime odtStart = today.minusDays(1).atTime(OffsetTime.MIN);
        OffsetDateTime odtStop = today.minusDays(1).atTime(OffsetTime.MAX);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String defaultFromDate = odtStart.format(dateTimeFormatter);
        String defaultEndDate = odtStop.format(dateTimeFormatter);

        reconcileCustomerFindRequest.setFromDate(simpleDateFormat.parse(defaultFromDate));
        reconcileCustomerFindRequest.setEndDate(simpleDateFormat.parse(defaultEndDate));
      }

      if (isBalanceNotMatch != null && !isBalanceNotMatch.isEmpty()) {
        reconcileCustomerFindRequest.setIsBalanceNotMatch(Boolean.valueOf(isBalanceNotMatch));
      }
      reconcileCustomerFindRequest.setCustomerTypeIds(
          Collections.singletonList(CustomerType.ID_MERCHANT));
      reconcileCustomerFindRequest.setOffset(Integer.parseInt(request.getParameter("start")));
      reconcileCustomerFindRequest.setLimit(Integer.parseInt(request.getParameter("length")));

      String str = Utils.objectToJson(reconcileCustomerFindRequest);
      reconcileCustomerFindResponse =
          walletEndpoint.findReconciCustomerSummaryFind(reconcileCustomerFindRequest);

      if (reconcileCustomerFindResponse.getCustomerReconcileSummaries().isEmpty()) {
        response.setDataList(Collections.EMPTY_LIST);
        response.setRecordsTotal(0L);
        response.setRecordsFiltered(0L);
      } else {
        response.setDataList(reconcileCustomerFindResponse.getCustomerReconcileSummaries());
        response.setRecordsTotal((long) reconcileCustomerFindResponse.getTotal());
        response.setRecordsFiltered((long) reconcileCustomerFindResponse.getTotal());
      }

    } catch (HttpServerErrorException | HttpClientErrorException e) {
      return ResponseEntity.ok(reconcileCustomerFindResponse);
    } catch (Exception e) {
      return ResponseEntity.ok(e);
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/kppvietel/create-or-update/account", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> createOrUpdateKppVT(HttpServletRequest request) throws Exception {
    SpecialProviderAccountChangeResponse res = new SpecialProviderAccountChangeResponse();
    try {
      String formType = request.getParameter("formType");
      String accountName = request.getParameter("accountName");
      String phoneNumber = request.getParameter("phoneNumber");
      String serialNumber = request.getParameter("serialNumber");
      String status = request.getParameter("status");
      String password = request.getParameter("password");

      SpecialProviderAccountChangeRequest spacReq = new SpecialProviderAccountChangeRequest();
      spacReq.setAccountName(accountName != null && !accountName.isEmpty() ? accountName : null);
      spacReq.setPassword(password != null && !password.isEmpty() ? password : null);
      spacReq.setPhoneNumber(phoneNumber != null && !phoneNumber.isEmpty() ? phoneNumber : null);
      spacReq.setSerialNumber(
          serialNumber != null && !serialNumber.isEmpty() ? serialNumber : null);
      spacReq.setStatus(status != null && !status.isEmpty() ? Integer.valueOf(status) : null);
      if (formType != null && formType.equals("create")) {
        res = specialProviderKppViettelEndpoint.providerSpecialAccountAdd(spacReq);
      } else {
        res = specialProviderKppViettelEndpoint.providerSpecialAccountUpdate(spacReq);
      }
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(Utils.objectToJson(res));
        return ResponseEntity.badRequest().body(Utils.objectToJson(res));
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/kppvietel/change-stt/account", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeSttKppVT(HttpServletRequest request) throws Exception {
    SpecialProviderAccountChangeStatusResponse res =
        new SpecialProviderAccountChangeStatusResponse();
    try {
      String accountName = request.getParameter("accountName");
      String status = request.getParameter("status");
      SpecialProviderAccountChangeStatusRequest spacReq =
          new SpecialProviderAccountChangeStatusRequest();
      spacReq.setAccountName(accountName != null && !accountName.isEmpty() ? accountName : null);
      spacReq.setStatus(status != null && status.equals("1") ? 1 : 0);
      res = specialProviderKppViettelEndpoint.providerSpecialAccoutChangeStatus(spacReq);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(Utils.objectToJson(res));
        return ResponseEntity.badRequest().body(Utils.objectToJson(res));
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/kppvietel/delete/account", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> deleteKppVT(HttpServletRequest request) throws Exception {
    SpecialProviderAccountChangeResponse res = new SpecialProviderAccountChangeResponse();
    try {
      String accountName = request.getParameter("accountName");

      SpecialProviderAccountChangeRequest spacReq = new SpecialProviderAccountChangeRequest();
      spacReq.setAccountName(accountName != null && !accountName.isEmpty() ? accountName : null);
      res = specialProviderKppViettelEndpoint.providerSpecialAccountDelete(spacReq);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(Utils.objectToJson(res));
        return ResponseEntity.badRequest().body(Utils.objectToJson(res));
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/kppvietel/account/capcha/get", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> kppViettelAccountCapchaGet(HttpServletRequest request) throws Exception {
    SpecialProviderAccountCapchaGetResponse res = new SpecialProviderAccountCapchaGetResponse();
    try {
      String accountName = request.getParameter("accountName");

      SpecialProviderAccountCapchaGetRequest spacReq = new SpecialProviderAccountCapchaGetRequest();
      spacReq.setAccountName(accountName != null && !accountName.isEmpty() ? accountName : null);

      res = specialProviderKppViettelEndpoint.providerSpecialAccountCapchaGet(spacReq);

      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(Utils.objectToJson(res));
        return ResponseEntity.badRequest().body(Utils.objectToJson(res));
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/kppvietel/account/capcha/enter", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> kppViettelAccountCapchaEnter(HttpServletRequest request) throws Exception {
    SpecialProviderAccountCapchaEnterResponse res = new SpecialProviderAccountCapchaEnterResponse();
    try {
      String accountName = request.getParameter("accountName");
      String capcha = request.getParameter("capcha");

      SpecialProviderAccountCapchaEnterRequest spacReq = new SpecialProviderAccountCapchaEnterRequest();
      spacReq.setAccountName(accountName != null && !accountName.isEmpty() ? accountName : null);
      spacReq.setCapcha(capcha);

      res = specialProviderKppViettelEndpoint.providerSpecialAccountCapchaEnter(spacReq);

      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(Utils.objectToJson(res));
        return ResponseEntity.badRequest().body(Utils.objectToJson(res));
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }
  @RequestMapping(value = "/partner/notifications", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> getPartnerNotificationList(HttpServletRequest request) throws Exception {
    DataTableResponse<NotificationDTO> response = new DataTableResponse<>();
    try {
      String[] eventEnums = request.getParameterValues("eventEnums[]");
      String requestId = request.getParameter("requestId");
      String sort = request.getParameter("sort");

      List<EventEnum> eventEnumList = new ArrayList<>();
      if (eventEnums != null && eventEnums.length > 0) {
        for (String s : eventEnums) {
          if (!s.isEmpty()) {
            eventEnumList.add(EventEnum.valueOf(s));
          }
        }
      }

      FindPartnerNotificationRequest findNotiReq = new FindPartnerNotificationRequest();
      findNotiReq.setOffset(Integer.parseInt(request.getParameter("start")));
      findNotiReq.setLimit(Integer.parseInt(request.getParameter("length")));
      findNotiReq.setEventEnums(eventEnums != null && eventEnums.length > 0 ? eventEnumList : null);
      findNotiReq.setRequestId(requestId != null && !requestId.isEmpty() ? requestId : null);
      findNotiReq.setDirection(
          sort != null && !sort.isEmpty() ? Sort.Direction.valueOf(sort) : null);
      findNotiReq.setSortField(sort != null && !sort.isEmpty() ? "requestTime" : null);
      GeneralResponse<Object> res = notificationEndpoint.getMessages(findNotiReq);
      FindPartnerNotificationResponse findPartnerNotificationResponse =
          Utils.responseToObject(res.getData(), FindPartnerNotificationResponse.class);
      response.setRecordsTotal(findPartnerNotificationResponse.getTotal());
      response.setRecordsFiltered(findPartnerNotificationResponse.getTotal());
      response.setDataList(findPartnerNotificationResponse.getNotificationDTOs());
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/partner/notification/send", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> sendPartnerNotification(HttpServletRequest request) throws Exception {
    GeneralResponse<Object> response = null;
    try {
      String content = request.getParameter("content");
      String event = request.getParameter("event");
      String fromDate = request.getParameter("fromDate");
      String toDate = request.getParameter("toDate");
      String requestId = request.getParameter("requestId");
      String service = request.getParameter("service");
      String serviceType = request.getParameter("serviceType");
      String serviceDetailName = request.getParameter("serviceDetailName");
      String signature = request.getParameter("signature");
      String subject = request.getParameter("subject");
      String refRequestId = request.getParameter("refRequestId");

      NotificationDTO notificationDTO = new NotificationDTO();
      notificationDTO.setFromDate(
          fromDate != null && !fromDate.isEmpty()
              ? DateUtil.stringToDate(fromDate, "dd/MM/yyyy HH:mm:ss")
              : null);
      notificationDTO.setToDate(
          toDate != null && !toDate.isEmpty()
              ? DateUtil.stringToDate(toDate, "dd/MM/yyyy HH:mm:ss")
              : null);
      notificationDTO.setContent(content != null && !content.isEmpty() ? content : null);
      notificationDTO.setRefRequestId(
          refRequestId != null && !refRequestId.isEmpty() ? refRequestId : null);
      notificationDTO.setEvent(event != null && !event.isEmpty() ? EventEnum.valueOf(event) : null);
      notificationDTO.setRequestId(requestId != null && !requestId.isEmpty() ? requestId : null);
      notificationDTO.setService(
          service != null && !service.isEmpty() ? ServiceEnum.valueOf(service) : null);
      notificationDTO.setServiceType(
          serviceType != null && !serviceType.isEmpty()
              ? ServiceTypeEnum.valueOf(serviceType)
              : null);
      notificationDTO.setServiceDetailName(
          serviceDetailName != null && !serviceDetailName.isEmpty() ? serviceDetailName : null);
      notificationDTO.setSignature(signature != null && !signature.isEmpty() ? signature : null);
      notificationDTO.setSubject(subject != null && !subject.isEmpty() ? subject : null);
      notificationDTO.setRequestTime(new Date());
      notificationDTO.setSignature("SHA256(|requestId|event|serviceType|service|)");

      response = notificationEndpoint.postMessage(notificationDTO);
      if (response.getCode().equals("200")) {
        // success
      } else {
        return ResponseEntity.badRequest().body(response);
      }
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest()
          .body(Utils.jsonToObject(e.getResponseBodyAsString(), GeneralResponse.class));
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e);
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/reconciliation/workflow/action", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> changeWorkflowReconciliation(HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      String id = request.getParameter("id");
      String remark = request.getParameter("remark");
      String action = request.getParameter("action");

      WorkflowActionRequest workflowActionRequest = new WorkflowActionRequest();
      Long callerId = callerUtil.getCallerId();
      workflowActionRequest.setCallerId(callerId);
      workflowActionRequest.setWorkflowActionType(WorkFlowActionTypeEnum.valueOf(action));
      workflowActionRequest.setEntityId(Long.valueOf(id));
      workflowActionRequest.setRemark(remark);
      res = reconciliationEndpoint.changeWorkflowReconciliations(workflowActionRequest);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/pricing/search-by-code", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> searchPricingByCode(HttpServletRequest request) throws Exception {
    PricingDTO res = null;
    try {
      String serviceCode = request.getParameter("serviceCode");
      ServicePricingForm servicePricingForm = new ServicePricingForm();
      servicePricingForm.setServiceCode(StringUtils.isNotBlank(serviceCode) ? serviceCode : null);
      servicePricingForm.setCustomerId(1L);
      servicePricingForm.setCustomerTypeEnum(CustomerTypeEnum.MERCHANT);
      res = reconciliationEndpoint.searchPricing(servicePricingForm);
      String str = "";
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/search/true-service", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> searchTrueService(HttpServletRequest request) throws Exception {
    FindTrueServiceResponse res = null;
    try {
      String searchByNameOrCode = request.getParameter("search");
      FindTrueServiceRequest ftsRequest = new FindTrueServiceRequest();
      ftsRequest.setQuickSearch(StringUtils.isNotBlank(searchByNameOrCode) ? searchByNameOrCode: null);
      ftsRequest.setActive(StringUtil.CHAR_YES);
      res = trueServiceEndpoint.findTrueServices(ftsRequest);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }
}
