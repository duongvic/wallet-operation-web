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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vn.mog.ewallet.operation.web.contract.AttachmentType;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrPrivilegesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrPrivilegesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolePrivilegesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolePrivilegesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressStatus;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrPrivilege;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRolePrivilege;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.CreateCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.UpdateImageProfileCustomerResponse;
import vn.mog.ewallet.operation.web.utils.CustomerUtil;
import vn.mog.framework.common.utils.SessionUtil;

/**
 * Created by duongnh on 7/10/18.
 */

@Controller
@RequestMapping(value = "/staff-account/manage")
public class StaffAccountController extends AbstractAccountController {

  /*ACCOUNT*/
  public static final String ACCOUNT_MANAGE_CONTROLLER = "/staff-account/manage";
  public static final String ACCOUNT_MANAGE_LIST = ACCOUNT_MANAGE_CONTROLLER + "/list";
  public static final String ACCOUNT_MANAGE_DETAIL = ACCOUNT_MANAGE_CONTROLLER + "/details";
  /*CUSTOMER*/
  public static final String CUSTOMER_MANAGE_CONTROLLER = "/customer/manage";
  public static final String CUSTOMER_MANAGE_LIST = CUSTOMER_MANAGE_CONTROLLER + "/list";

  /*ROLE*/
  public static final String ACCOUNT_MANAGE_ROLE_LIST = ACCOUNT_MANAGE_CONTROLLER + "/role-list";
  public static final String ACCOUNT_MANAGE_ROLE_DETAIL =
      ACCOUNT_MANAGE_CONTROLLER + "/detail-role";
  /*QUYỀN*/
  public static final String ACCOUNT_MANAGE_PRIVILEGE_LIST =
      ACCOUNT_MANAGE_CONTROLLER + "/list-privilege";
  public static final String ACCOUNT_MANAGE_PRIVILEGE_ADD =
      ACCOUNT_MANAGE_CONTROLLER + "/privilege-add";
  public static final String ACCOUNT_MANAGE_PRIVILEGE_UPDATE =
      ACCOUNT_MANAGE_CONTROLLER + "/privilege-update";
  public static final String SYSTEM_STAFF = "SYSTEM_STAFF";
  private static final Logger LOG = LogManager.getLogger(StaffAccountController.class);
  /*REDIRECT URL*/
  private static final String REDIRECT = "redirect:";
  public static final String REDIRECT_ACCOUNT_MANAGE_ADD =
      REDIRECT + ACCOUNT_MANAGE_CONTROLLER + "/add";
  public static final String REDIRECT_ACCOUNT_MANAGE_UPDATE =
      REDIRECT + ACCOUNT_MANAGE_CONTROLLER + "/updateRule";
  public static final String REDIRECT_ACCOUNT_MANAGE_ROLE_ADD =
      REDIRECT + ACCOUNT_MANAGE_CONTROLLER + "/addRole";
  public static final String REDIRECT_ACCOUNT_MANAGE_ROLE_UPDATE =
      REDIRECT + ACCOUNT_MANAGE_CONTROLLER + "/updateRole";
  private static final String CUSTOMER_TYPE_SYSTEM_ATTRIBUTE = "CUSTOMER_TYPE_SYSTEM_ATTRIBUTE";
  private String codeErr;
  private Customer mCustomer;

  @GetMapping(value = "/list")
//    @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    FindUmgrCustomerResponse fUmgrCusResponse = null;
    try {
      // Xử lý dữ liệu đầu vào

      //get customer type
      getCustomerTypeSystemStaffAcc(request, response, map);

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

    map.put("customerTypeSystem", SYSTEM_STAFF);
    String customerType = request.getParameter("customerType");
    if (StringUtils.isNotBlank(customerType)) {
      map.put("customerType", customerType);
    }
    SessionUtil.setAttribute(CUSTOMER_TYPE_SYSTEM_ATTRIBUTE, SYSTEM_STAFF);

    map.put("codeErr", request.getParameter("codeErr"));
    map.put("mesErr", "label.message.label");
    return "/account_manage/list";
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
    return "/account_manage/edit_account";
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
           /*save img profile*/
          Attachment attachment = new Attachment();
          MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
          List<MultipartFile> multiparts = multiPartRequest.getFiles("img_profile");
          if (multiparts != null) {
            for (MultipartFile multipart : multiparts) {
              if (multipart.getOriginalFilename() != null) {
//                String fileName = multipart.getOriginalFilename();
//                byte[] bytes = multipart.getBytes();
                UpdateImageProfileCustomerRequest updateImageProfileCustomerRequest = new UpdateImageProfileCustomerRequest();
                attachment.setCustomerId(accountId);
                attachment.setName(multipart.getOriginalFilename());
                attachment.setContentType(multipart.getContentType());
                attachment.setContent(encodeRotateImage(multipart));
                attachment.setAttachmentType(AttachmentType.ImageProfile.code);
                updateImageProfileCustomerRequest.setAttachment(attachment);

                UpdateImageProfileCustomerResponse updateImageProfileCustomerResponse = umgrAccountEndpoint
                    .updateProfileImage(updateImageProfileCustomerRequest);

                if (updateImageProfileCustomerResponse == null
                    || updateImageProfileCustomerResponse.getStatus() == null
                    || updateImageProfileCustomerResponse.getStatus().getCode() != 0) {
                  String erro =  updateImageProfileCustomerResponse.getStatus().getValue();
                  map.put(MessageNotify.mesErr,erro);
                  map.put(MessageNotify.codeErr, MessageNotify.ERROR_CODE);
                  throw new Exception(erro);
                }
//                else {
//                  GetStoreS3UrlRequest imageReq = new GetStoreS3UrlRequest();
//                  imageReq.setCustomerId(accountId);
//                  String imageDefault = null;
//
//                  GetStoreS3UrlResponse imageRes = umgrAccountEndpoint.getImageProfile(imageReq);
//                  if (imageRes.getUsedS3()) {
//                    map.put("img_profile",
//                        StringUtils.isEmpty(imageRes.getS3Url()) ? imageDefault
//                            : imageRes.getS3Url());
//                  } else {
//                    map.put("img_profile",
//                        imageRes.getAttachment() != null ? imageRes.getAttachment().getBase64Image()
//                            : imageDefault);
//                  }
//                }
              }
            }
          }
        } else if ("save-account-info".equals(btnAction)) {
          saveAccountInfo(request, customer);
        }
        UpdateCustomerRequest updateCustomerReq = new UpdateCustomerRequest(customer);
        UpdateCustomerResponse updateCustomerRes = umgrAccountEndpoint
            .updateCustomer(updateCustomerReq);
        if (updateCustomerRes == null || updateCustomerRes.getStatus() == null) {
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
    return "/account_manage/edit_account";
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
        throw new Exception("Can not Update Customer");
      }
      if (0 != createCustomerResponse.getStatus().getCode()) {
        codeErr = createCustomerResponse.getStatus().getValue();
        throw new Exception(createCustomerResponse.getStatus().getValue());
      }

      map.put("codeErr", "");
      map.put("mesErr", "label.message.create.account");
      map.put("edit_type", "add");
      return REDIRECT + ACCOUNT_MANAGE_DETAIL + "/" + customerId;
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
      codeErr = ex.getMessage();
      map.put("wallet_type", walletType);
      map.put("user_type", userType);
      return REDIRECT + "/staff-account/manage/add";
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
    return "/account_manage/reset_password";
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


  @GetMapping(value = "/role-list")
//    @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String listRole(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    try {
      String roleIdSearchParameter = request.getParameter("role-name");
      String status = request.getParameter("status");

      int offset = 0;
      int limit = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      GetUmgrRolesRequest getUmgrRolesRequest = new GetUmgrRolesRequest();
      getUmgrRolesRequest.setRoleIdSearchParameter(roleIdSearchParameter);
      getUmgrRolesRequest.setActive(status != null ? status.charAt(0) : null);
      getUmgrRolesRequest.setLimit(limit);
      getUmgrRolesRequest.setOffset(offset);

      GetUmgrRolesResponse getUmgrRolesResponse = getDataRoleList(roleIdSearchParameter, status,
          limit,
          offset);
      List<UmgrRole> roles = getUmgrRolesResponse.getRoles();

      map.put("listRole", roles);
      map.put("total", getUmgrRolesResponse.getTotal());
      map.put("role-name", roleIdSearchParameter);
      map.put("status", status);
      map.put("offset", offset);
      map.put("pagesize", getUmgrRolesResponse.getAll());

      return "/account_manage/role/role_list";
    } catch (Exception e) {
      map.put("codeError", e.getMessage());
    }
    return "/account_manage/role/role_list";
  }

  @PostMapping(value = "/role-edit/{roleId}")
  public String editRolePost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("roleId") String roleId) {
    try {
      String editInput = request.getParameter("edit-input");
      String description = request.getParameter("remark");

      UmgrRole role = new UmgrRole();
      role.setRole(roleId);
      role.setDescription(description);

      if ("edit".equalsIgnoreCase(editInput)) {
        UpdateUmgrRoleRequest updateUmgrRoleRequest = new UpdateUmgrRoleRequest();
        updateUmgrRoleRequest.setRole(role);

        umgrRolesPrivilegesEndpoint.updateUmgrRole(updateUmgrRoleRequest);

      } else {
        CreateUmgrRoleRequest createUmgrRoleRequest = new CreateUmgrRoleRequest();
        createUmgrRoleRequest.setRole(role);
        umgrRolesPrivilegesEndpoint.createUmgrRole(createUmgrRoleRequest);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    map.put("edit", "edit");

    return editRole(request, response, map, roleId);
  }

  @GetMapping(value = "/role-edit/{roleId}")
  public String editRole(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("roleId") String roleId) {
    try {
      int offset = 0;
      int limit = 1;

      GetUmgrRolesRequest getUmgrRolesRequest = new GetUmgrRolesRequest();
      getUmgrRolesRequest.setRoleIdSearchParameter(roleId);
      getUmgrRolesRequest.setLimit(limit);
      getUmgrRolesRequest.setOffset(offset);

      GetUmgrRolesResponse getUmgrRolesResponse = umgrRolesPrivilegesEndpoint
          .getUmgrRoles(getUmgrRolesRequest);
      List<UmgrRole> roles = getUmgrRolesResponse.getRoles();

      if (roles != null && !roles.isEmpty()) {
        map.put("roleId", roles.get(0).getRole());
        map.put("roleDescription", roles.get(0).getDescription());
      }

      GetUmgrRolePrivilegesRequest getUmgrRolePrivilegesRequest = new GetUmgrRolePrivilegesRequest();
      getUmgrRolePrivilegesRequest.setRoleId(roleId);
      GetUmgrRolePrivilegesResponse getUmgrRolePrivilegesResponse = umgrRolesPrivilegesEndpoint
          .getUmgrRolePrivileges(getUmgrRolePrivilegesRequest);

      List<UmgrRolePrivilege> rolePrivileges = getUmgrRolePrivilegesResponse.getRolePrivileges();
      map.put("rolePrivileges", rolePrivileges);

      GetUmgrPrivilegesRequest getUmgrPrivilegesRequest = new GetUmgrPrivilegesRequest();
      getUmgrPrivilegesRequest.setActive('Y');
      getUmgrPrivilegesRequest.setOffset(0);
      getUmgrPrivilegesRequest.setLimit(0);
      GetUmgrPrivilegesResponse getUmgrPrivilegesResponse = umgrRolesPrivilegesEndpoint
          .getUmgrPrivileges(getUmgrPrivilegesRequest);

      List<UmgrPrivilege> privileges = getUmgrPrivilegesResponse.getPrivileges();

      if (privileges != null && !privileges.isEmpty()) {
        map.put("privileges", privileges);
      }

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    map.put("edit", "edit");

    return "/account_manage/role/role_edit";
  }

  @GetMapping(value = "/role-add")
  public String addRole(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
    map.put("edit", "add");

    return "/account_manage/role/role_edit";
  }

  /*QUYỀN*/
  @GetMapping(value = "/list-privilege")
//    @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "')")
  public String listPrivilege(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws FrontEndException {
    try {
      String privilegeSearchParameter = request.getParameter("privilege_name");
      String status = request.getParameter("status");

      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      GetUmgrPrivilegesRequest getUmgrPrivilegesRequest = new GetUmgrPrivilegesRequest();
      getUmgrPrivilegesRequest.setPrivilegeIdSearchParameter(
          StringUtils.isNotEmpty(privilegeSearchParameter) ? privilegeSearchParameter : null);
      getUmgrPrivilegesRequest.setActive(StringUtils.isNotEmpty(status) ? status.charAt(0) : null);
      getUmgrPrivilegesRequest.setLimit(limit);
      getUmgrPrivilegesRequest.setOffset(offset);

      GetUmgrPrivilegesResponse getUmgrPrivilegesResponse =
          umgrRolesPrivilegesEndpoint.getUmgrPrivileges(getUmgrPrivilegesRequest);
      List<UmgrPrivilege> privilegees = getUmgrPrivilegesResponse.getPrivileges();

      map.put("privilege_name", privilegeSearchParameter);
      map.put("status", status);
      map.put("listPrivilege", privilegees);
      map.put("total", getUmgrPrivilegesResponse.getTotal());
      map.put("offset", offset);
      map.put("pagesize", getUmgrPrivilegesResponse.getAll());

      return "/account_manage/privilege/privilege_list";
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/privilege/privilege_list";
  }

  @GetMapping(value = "/privilege-add")
  public String createPrivilege(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws FrontEndException {
    try {
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/privilege/privilege_add";
  }

  @PostMapping(value = "/privilege-add")
  public String savePrivilege(HttpServletRequest request, HttpServletResponse response,
      ModelMap map)
      throws FrontEndException {
    try {
      String privilegeName = request.getParameter("privilege_name");
      String description = request.getParameter("description");
      Character active = 'Y';
      UmgrPrivilege privilege = new UmgrPrivilege(privilegeName, description, active);

      CreateUmgrPrivilegeRequest createUmgrPrivilegeRequest = new CreateUmgrPrivilegeRequest();
      createUmgrPrivilegeRequest.setPrivilege(privilege);
      CreateUmgrPrivilegeResponse createUmgrPrivilegeResponse =
          umgrRolesPrivilegesEndpoint.createUmgrPrivilege(createUmgrPrivilegeRequest);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/privilege/privilege_add";
  }

  @GetMapping(value = "/privilege/details/{privilege_name}")
  public String updatePrivilege(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("privilege_name") String privilegeName)
      throws FrontEndException {
    try {

      int offset = 0;
      int limit = 1;

      GetUmgrPrivilegesRequest getUmgrPrivilegesRequest = new GetUmgrPrivilegesRequest();
      getUmgrPrivilegesRequest.setPrivilegeIdSearchParameter(privilegeName);
      getUmgrPrivilegesRequest.setActive(null);
      getUmgrPrivilegesRequest.setLimit(limit);
      getUmgrPrivilegesRequest.setOffset(offset);

      GetUmgrPrivilegesResponse getUmgrPrivilegesResponse =
          umgrRolesPrivilegesEndpoint.getUmgrPrivileges(getUmgrPrivilegesRequest);
      List<UmgrPrivilege> privilegees = getUmgrPrivilegesResponse.getPrivileges();

      if (privilegees != null && !privilegees.isEmpty()) {
        map.put("privilege_description", privilegees.get(0).getDescription());
      }

      map.put("privilege_name", privilegeName);

      return "/account_manage/privilege/privilege_edit";
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/privilege/privilege_add";
  }

  @PostMapping(value = "/privilege/details/{privilege_name}")
  public String updatePrivilegePost(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, @PathVariable("privilege_name") String privilegeName)
      throws FrontEndException {
    try {
      String description = request.getParameter("description");
      if (request.getParameter("edit_type") != null && description != null) {

        if ("edit_privilege".equals(request.getParameter("edit_type"))) {
          UpdateUmgrPrivilegeRequest updateUmgrPrivilegeRequest = new UpdateUmgrPrivilegeRequest();

          UmgrPrivilege umgrPrivilege = new UmgrPrivilege();
          umgrPrivilege.setPrivilege(privilegeName);
          umgrPrivilege.setDescription(description);

          updateUmgrPrivilegeRequest.setPrivilege(umgrPrivilege);

          umgrRolesPrivilegesEndpoint.updateUmgrPrivilege(updateUmgrPrivilegeRequest);
        } else {
          Character active = 'Y';
          UmgrPrivilege privilege = new UmgrPrivilege(privilegeName, description, active);

          CreateUmgrPrivilegeRequest createUmgrPrivilegeRequest = new CreateUmgrPrivilegeRequest();
          createUmgrPrivilegeRequest.setPrivilege(privilege);
          umgrRolesPrivilegesEndpoint.createUmgrPrivilege(createUmgrPrivilegeRequest);
        }
      }

      return updatePrivilege(request, response, map, privilegeName);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/privilege/privilege_add";
  }

  @GetMapping(value = "/change-password")
  public String changePassWord(HttpServletRequest request, HttpServletResponse response)
      throws FrontEndException {
    try {
      return "/account_manage/change_password";
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/account_manage/change_password";
  }

//  private String encodeRotateImage(MultipartFile file) {
//    try {
//      if (file != null) {
//        byte[] fileBytes = file.getBytes();
//        byte[] rotateBytes = ImageOrientationUtil
//            .rotateCorrectImage(file.getInputStream(), fileBytes);
//        return Base64.getEncoder().encodeToString(rotateBytes == null ? fileBytes : rotateBytes);
//      }
//    } catch (IOException e) {
//      LOG.error("IOException", e);
//    }
//    return null;
//  }
}