package vn.mog.ewallet.operation.web.controller.account;

import static vn.mog.ewallet.operation.web.controller.account.StaffAccountController.SYSTEM_STAFF;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.CUSTOMER_TYPE_IDS_WALLET;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.CUSTOMER_TYPE_SYSTEM_ACCOUNT;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.CUSTOMER_TYPE_SYSTEM_STAFF;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.CUSTOMER_TYPE_SYSTEM_WALLET;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_ADMIN;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_MERCHANT;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_POOL;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_SALE;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_SOF;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_STAFF;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_SYSTEM;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_ZOTA;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.STAFF_CUSTOMER_TYPES;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.EventType.BALANCE_EVENT_TYPES;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType.FULL_SERVICE_TYPES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import vn.mog.ewallet.operation.web.contract.AttachmentType;
import vn.mog.ewallet.operation.web.contract.TypicalImage;
import vn.mog.ewallet.operation.web.contract.form.IdentityDataForm;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.service.TrueServiceController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetAllCustomerTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetBlacklistReasonResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetCustomerAttributeTypeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ResetCredentialResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateCustomerAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CredentialType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressStatus;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.AddressType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindEntityEventStoreByDateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetStoreS3UrlResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerAttribute;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Identity;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.GetAllPositionResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Position;
import vn.mog.ewallet.operation.web.utils.CustomerUtil;
import vn.mog.ewallet.operation.web.utils.ImageOrientationUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.contract.base.KeyValue;

public class AbstractAccountController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AbstractAccountController.class);

  private static final String CUSTOMER_TYPE_SYSTEM_ATTRIBUTE = "CUSTOMER_TYPE_SYSTEM_ATTRIBUTE";


  protected GetFullCustomerResponse fetchCustomer(Long accountId) throws Exception {
    GetFullCustomerRequest getCustomerRequest = new GetFullCustomerRequest(accountId);
    GetFullCustomerResponse getCustomerResponse = umgrAccountEndpoint
        .getFullCustomer(getCustomerRequest);
    if (getCustomerResponse == null || getCustomerResponse.getStatus() == null) {
      throw new Exception("Can not get Customer");
    }
    if (0 != getCustomerResponse.getStatus().getCode()) {
      throw new Exception(getCustomerResponse.getStatus().getValue());
    }

    return getCustomerResponse;
  }

  protected void fetchFullInfoCustomerPutToView(ModelMap model, long customerId,
      List<KeyValue> customerAttributeTypes, String serviceType, String paymentChannelId) throws Exception {
    GetFullCustomerRequest fullCustomerReq = new GetFullCustomerRequest(customerId);
    GetFullCustomerResponse fullCustomerRes = umgrAccountEndpoint.getFullCustomer(fullCustomerReq);

    if (fullCustomerRes == null || fullCustomerRes.getStatus() == null) {
      throw new Exception("Can not get Customer");
    }
    if (0 != fullCustomerRes.getStatus().getCode()) {
      throw new Exception(fullCustomerRes.getStatus().getValue());
    }

    Customer customer = fullCustomerRes.getCustomer();
    Boolean isPM = false;
    if(customer.getCustomerType().getId() == CustomerType.ID_PROPERTY_MANAGER){
      isPM = true;
    }
    model.put("isPM", isPM);

    model.put("account_object", customer);

    if (customer.getAddresses() != null) {
      List<Address> lstAddress = customer.getAddresses();
      for (Address item : lstAddress) {
        if (item.getAddressType() == AddressType.RESIDENCE_ADDRESS.getId()) {
          model.put("account-nameAddr", item.getStreet1());
        } else if (item.getAddressType() == AddressType.OUTLET_ADDRESS.getId()) {
          model.put("outletStoreType", item.textOutLetStoreType());
          model.put("street1Store", item.getStreet1());
          model.put("aliasStore", item.getAlias());
          model.put("businessPhoneStore", item.getBusinessPhone());
        }
      }
    }
    //--
    Integer userTypeId = customer.getUserTypeId();
    String userType = "N/A";
    if (userTypeId != null && UserType.USER_TYPE_IDS.containsKey(userTypeId)) {
      userType = UserType.USER_TYPE_IDS.get(userTypeId);
    }
    model.put("user_type", userType);

    //--
    Integer walletTypeId = customer.getWalletTypeId();
    String walletType = "N/A";
    if (walletTypeId != null && CustomerType.WALLET_TYPE_IDS.containsKey(walletTypeId)) {
      walletType = CustomerType.WALLET_TYPE_IDS.get(walletTypeId);
    }
    model.put("wallet_type", walletType);

    String position = customer.getJobPosition();
    if (position != null) {
      Integer paramPosition = Integer.parseInt(position);
      model.put("positionList", paramPosition);
    } else {
      getPositions(model);
    }

    String province = customer.getProvince();
    String paramProvince = province;
    if (province != null) {
      model.put("current_province", paramProvince);
      model.put("lstDistrict", getDistrict(paramProvince));

      model.put("current_district", customer.getDistrict());

    } else {
      //get province
      model.put("lstProvince", getProvince());
      model.put("lstDistrict", getDistrict(null));
    }

    //---
    List<CustomerAttribute> customerAttrs = fullCustomerRes.getAttributes();
    List<CustomerAttribute> editableCustomerAttrs = new ArrayList<>();

    for (KeyValue attrType : customerAttributeTypes) {
      for (CustomerAttribute cusAttr : customerAttrs) {
        if (attrType.getKey().equals(cusAttr.getCustomerAttributeTypeId())) {
          editableCustomerAttrs.add(cusAttr);
          break;
        }
      }
    }

    model
        .put("customer_attributes", editableCustomerAttrs.isEmpty() ? null : editableCustomerAttrs);

    //--

    List<Identity> identities = fullCustomerRes.getIdentities();

    fetchIdentityPutToModel(model, identities, customerId);

    fetchSpecifiedCommissionFeeOfCustomer(model, customerId, customer.getCustomerType(),
        serviceType, paymentChannelId);

  }

  private void fetchSpecifiedCommissionFeeOfCustomer(ModelMap model, Long accountId,
      CustomerType customerType, String serviceType, String paymentChannelId) {

    FindSpecifiedCommissionFeeRequest specifiedFeeReq = new FindSpecifiedCommissionFeeRequest();
    specifiedFeeReq.setCustomerId(accountId);
    specifiedFeeReq.setCustomerType(customerType.getId());
    specifiedFeeReq.setPaymentChannelId(paymentChannelId);

    if (StringUtils.isBlank(serviceType)) {
      String sServiceType = (String) SessionUtil
          .getAttribute(TrueServiceController.SESSION_SERVICE_TYPE);
      if (sServiceType == null || "undefined".equals(sServiceType)) {
        specifiedFeeReq.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
      } else {
        specifiedFeeReq.setServiceTypes(Collections.singletonList(sServiceType));
      }
    } else {
      SessionUtil.setAttribute(TrueServiceController.SESSION_SERVICE_TYPE, serviceType);
      specifiedFeeReq.setServiceTypes(Collections.singletonList(serviceType));
    }

    FindSpecifiedCommissionFeeResponse specifiedFeeRes = paymentCustomerEndpoint
        .findSpecifiedCommissionFees(specifiedFeeReq);

    if (null != specifiedFeeRes && null != specifiedFeeRes.getSpecifiedCommissionFees()) {
      model.put("customerTypeFees", specifiedFeeRes.getSpecifiedCommissionFees());
      model.put("services",
          genderTrueServiceTree(new ArrayList<>(specifiedFeeRes.getSpecifiedCommissionFees())));
      model.put("totalServices", specifiedFeeRes.getAll());
    } else {
      model.put("customerTypeFees", null);
      model.put("services", null);
      model.put("totalServices", 0);
    }
  }

  private void fetchIdentityPutToModel(ModelMap model, List<Identity> identities, long customerId)
      throws Exception {

    GetAttachmentRequest attachmentReq = new GetAttachmentRequest();
    attachmentReq.setCustomerId(customerId);
    attachmentReq.setTypeIds(AttachmentType.ATTACHMENT_TYPE_IDENTITYS);

    GetAttachmentResponse attachmentRes = umgrAccountEndpoint.getAttachment(attachmentReq);
    List<Attachment> attachments = attachmentRes.getAttachments();
    for (Attachment item : attachments) {
      if (item.getContent() != null) {
        item.setContent(ImageOrientationUtil.resizeImage(item.getContent(), 0, 140));
      }
    }

    IdentityDataForm identityDataForm = new IdentityDataForm();
    identityDataForm.setIdentities(identities);

    for (Attachment item : attachments) {
      if (item.getName().startsWith(TypicalImage.FRONT_FACE.name)) {
        identityDataForm.setFrontFaceImage(item);
      } else if (item.getName().startsWith(TypicalImage.BACK_FACE.name)) {
        identityDataForm.setBackFaceImage(item);
      } else if (item.getName().startsWith(TypicalImage.SELFIE.name)) {
        identityDataForm.setSelfieImage(item);
      }
    }

    model.put("identityDataForm", identityDataForm);
  }


  protected void fetchActivityLoginCustomerPutToView(ModelMap model, Long accountId)
      throws Exception {

    Calendar calReturn30days = Calendar.getInstance();
    calReturn30days.add(Calendar.DATE, -20);

    FindEntityEventStoreByDateRequest eventReq = new FindEntityEventStoreByDateRequest();
    eventReq.setCustomerId(accountId);
    eventReq.setEventDate(calReturn30days.getTime());

    FindEntityEventStoreByDateResponse eventRes = umgrAccountEndpoint.findEventStore(eventReq);
    model.put("accessHistories", eventRes.getAccessHistorys());

  }

  protected void fetchImageProfileCustomer(String contextPath, ModelMap model, Long accountId)
      throws Exception {

    GetStoreS3UrlRequest imageReq = new GetStoreS3UrlRequest();
    imageReq.setCustomerId(accountId);
    String imageDefault = contextPath + "/assets/images/ava-blank.png";

    GetStoreS3UrlResponse imageRes = umgrAccountEndpoint.getImageProfile(imageReq);
    if (imageRes.getUsedS3()) {
      model.put("img_profile",
          StringUtils.isEmpty(imageRes.getS3Url()) ? imageDefault : imageRes.getS3Url());
    } else {
      model.put("img_profile",
          imageRes.getAttachment() != null ? imageRes.getAttachment().getBase64Image()
              : imageDefault);
    }

  }


  protected void getCustomerType(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, List<Integer> condition) throws Exception {
    try {
      List<CustomerType> lst = new ArrayList<>();
      List<CustomerType> finalLst = new ArrayList<>();
      GetAllCustomerTypeRequest getAllCusTyeRequest = new GetAllCustomerTypeRequest();
      getAllCusTyeRequest.setCustomerTypeIds(condition);
      GetAllCustomerTypeResponse getAllCusTypeResponse = null;
      getAllCusTypeResponse = customerEndpoint.getAllCustomerTypes(getAllCusTyeRequest);
      if (getAllCusTypeResponse.getStatus().getCode() == 0) {
        lst = getAllCusTypeResponse.getCustomerTypes();
        for (CustomerType item : lst) {
          if (CustomerType.ID_ZOTA == item.getId()) {
            CustomerType customerType = new CustomerType(item.getId(),
                CustomerType.FULL_CUSTOMER_TYPES.get(CustomerType.ID_ZOTA), item.getRole(),
                item.isInternal());
            finalLst.add(customerType);
          } else {
            finalLst.add(item);
          }
        }
        map.put("listCusType", finalLst);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  protected void getCustomerTypeSystemStaffAcc(HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map) throws Exception {
    getCustomerType(request, response, map, CUSTOMER_TYPE_SYSTEM_STAFF);
  }

  protected void getCustomerTypeSystemWalletAcc(HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getCustomerType(request, response, map, CUSTOMER_TYPE_SYSTEM_WALLET);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  protected void getCustomerTypeAcc(HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map) throws Exception {
    try {
      getCustomerType(request, response, map, CUSTOMER_TYPE_SYSTEM_ACCOUNT);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  //lấy danh sách list Role combo
  protected void getRoleList(HttpServletRequest request, HttpServletResponse response,
      ModelMap map) throws Exception {
    try {

      List<UmgrRole> lst = new ArrayList<>();
      GetUmgrRolesRequest requestUmgrRole = new GetUmgrRolesRequest();
      GetUmgrRolesResponse responseUmgrRole = null;
      responseUmgrRole = umgrRolesPrivilegesEndpoint.getUmgrRoles(requestUmgrRole);
      if (responseUmgrRole.getStatus().getCode() == 0) {
        lst = responseUmgrRole.getRoles();

        map.put("listRoles", lst);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  protected void getBlackListReason(
      ModelMap map) throws Exception {
    try {

      List<KeyValue> lst = new ArrayList<>();
      GetBlacklistReasonRequest getBlacklistReasonRequest = new GetBlacklistReasonRequest();
      GetBlacklistReasonResponse getBlacklistReasonResponse = null;
      getBlacklistReasonResponse = customerEndpoint.getBlacklistReason(getBlacklistReasonRequest);
      if (getBlacklistReasonResponse.getStatus().getCode() == 0) {
        lst = getBlacklistReasonResponse.getBlacklistReasons();

        map.put("listBlackReason", lst);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  protected void getPositions(ModelMap map) throws Exception {
    try {
      List<Position> lsPositions = new ArrayList<>();
      GetAllPositionRequest getAllPositionRequest = new GetAllPositionRequest();
      GetAllPositionResponse getAllPositionResponse = new GetAllPositionResponse();
      getAllPositionResponse = authSystemEndpoint.getAllPositions(getAllPositionRequest);
      if (getAllPositionResponse.getStatus().getCode() == 0) {
        lsPositions = getAllPositionResponse.getPositions();
        map.put("listPositions", lsPositions);
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  // lấy thông tin thành phố
  public Collection<Location> getProvince() throws Exception {
    return getLocation(null, Location.CITY);
  }

  // lấy thông tin Quận/Huyện
  public Collection<Location> getDistrict(String parentId) throws Exception {
    return getLocation(parentId, Location.DISTRICT);
  }

  // lấy thông tin Phường/Xã
  public Collection<Location> getCommune(String parentId) throws Exception {
    return getLocation(parentId, Location.COMMUE);
  }


  public Collection<Location> getLocation(String parentCode, Integer locationTypeId)
      throws Exception {
    FindLocationResponse findLocationBaseRespType = new FindLocationResponse();
    try {
      FindLocationRequest findLocationRequest = new FindLocationRequest();
      findLocationRequest.setParentCode(parentCode);
      findLocationRequest.setLocationType(locationTypeId);
      findLocationRequest.setCodes(null);
      findLocationBaseRespType = authSystemEndpoint.getLocation(findLocationRequest);
      return findLocationBaseRespType.getLocations();
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
    return findLocationBaseRespType.getLocations();
  }

  public List<Customer> getCustomers() {
    try {
      FindFullCustomerRequest ffcRequest = new FindFullCustomerRequest();
      ffcRequest.setCustomerTypes(Arrays
          .asList(ID_MERCHANT, ID_SALE, ID_ZOTA, ID_POOL, ID_SOF, ID_STAFF, ID_ADMIN,
              ID_SYSTEM));
      return customerEndpoint.findCustomers(ffcRequest).getCustomers();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  public Customer getCustomerById(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String accountId) throws Exception {
    Customer custom = new Customer();
    try {
      GetCustomerResponse responseCus = null;
      GetCustomerRequest requestCus = new GetCustomerRequest();
      requestCus.setCustomerId(NumberUtil.convertToLong(accountId));
      responseCus = customerEndpoint.getCustomerById(requestCus);
      if (responseCus != null && responseCus.getStatus().getCode() == 0) {
        custom = responseCus.getCustomer();
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return custom;
  }

  /*ROLE*/
  //lấy danh sách data role
  public GetUmgrRolesResponse getDataRoleList(String roleIdSearchParameter, String status,
      Integer limit,
      Integer offset) throws Exception {
    GetUmgrRolesResponse rolesResponse = new GetUmgrRolesResponse();
    try {
      GetUmgrRolesRequest getUmgrRolesRequest = new GetUmgrRolesRequest();
      getUmgrRolesRequest.setRoleIdSearchParameter(roleIdSearchParameter);
      getUmgrRolesRequest.setActive(status != null ? status.charAt(0) : null);
      getUmgrRolesRequest.setLimit(limit);
      getUmgrRolesRequest.setOffset(offset);
      rolesResponse = umgrRolesPrivilegesEndpoint.getUmgrRoles(getUmgrRolesRequest);
    } catch (Exception ex) {

      LOG.error(ex.getMessage());
    }
    return rolesResponse;
  }

  public void getAccountDetail(HttpServletRequest request, HttpServletResponse response,
      ModelMap model, Long accountId) throws FrontEndException {
    try {
      GetCustomerAttributeTypeRequest attributeTypeReq = new GetCustomerAttributeTypeRequest();
      GetCustomerAttributeTypeResponse attributeTypeRes = customerEndpoint
          .getCustomerAttributeTypes(attributeTypeReq);
      List<KeyValue> customerAttributeTypes = attributeTypeRes.getCustomerAttributeTypes();

      String serviceType = request.getParameter("serviceTypeIds");
      String paymentChannelId = request.getParameter("paymentChannelId");
      fetchFullInfoCustomerPutToView(model, accountId, customerAttributeTypes, serviceType, paymentChannelId);
      fetchImageProfileCustomer(request.getContextPath(), model, accountId);
      fetchActivityLoginCustomerPutToView(model, accountId);

      //get blackList
      getBlackListReason(model);

      getPositions(model);

      model.put("notification_mode_ids", CustomerType.NOTIFICATION_MODE_IDS);

      Map<Integer, String> customerTypeId = new LinkedHashMap<>();
      if (SYSTEM_STAFF.equals(SessionUtil.getAttribute(CUSTOMER_TYPE_SYSTEM_ATTRIBUTE))) {
        customerTypeId.putAll(STAFF_CUSTOMER_TYPES);
      } else {
        customerTypeId.putAll(CUSTOMER_TYPE_IDS_WALLET);
      }
      model.put("customer_type_ids", customerTypeId);

      GetUmgrCustomerRolesRequest getUmgrCustomerRolesRequest = new GetUmgrCustomerRolesRequest();
      getUmgrCustomerRolesRequest.setCustomerId(accountId);

      GetUmgrCustomerRolesResponse getUmgrCustomerRolesResponse = umgrRolesPrivilegesEndpoint
          .getUmgrCustomerRoles(getUmgrCustomerRolesRequest);
      model.put("customer_roles", getUmgrCustomerRolesResponse.getUmgrRoles());

      model.put("customer_attribute_types", customerAttributeTypes);

      GetUmgrRolesResponse getUmgrRolesResponse = getDataRoleList(null, "Y", 0, 0);
      List<UmgrRole> roles = getUmgrRolesResponse.getRoles();
      model.put("list_role", roles);

      String editType = request.getParameter("edit_type");
      if ("add".equals(editType)) {
        if (model.get("mesErr") == null || StringUtils.EMPTY.equals((String) model.get("mesErr"))) {

          model.put("mesErr", "label.message.create.account");
        }
      } else if ("edit".equals(editType)) {
        if (model.get("mesErr") == null || StringUtils.EMPTY.equals((String) model.get("mesErr"))) {
          model.put("mesErr", "label.message.label");
        }
      }
    } catch (Exception ex) {

    }
  }

  /*RESET PASSWORD*/
  public void getInfoResetPassword(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String accountId) {
    try {
      Customer customer = getCustomerById(request, response, map, accountId);
      Integer paramNotificationMode = customer.getNotificationModeId();
      String paramEmail = customer.getEmail();
      String paramPhoneNumber = customer.getMsisdn();
      String paramAccount = paramEmail;
      if (customer.getDisplayName() != null) {
        paramAccount = paramEmail + "-" + customer.getDisplayName();
      } else if (customer.getFirstName() != null && customer.getLastName() != null) {
        paramAccount = paramEmail + "-" + customer.getFirstName() + " " + customer.getLastName();
      } else if (customer.getFirstName() != null) {
        paramAccount = paramEmail + "-" + customer.getFirstName();
      } else if (customer.getLastName() != null) {
        paramAccount = paramEmail + "-" + customer.getLastName();
      }

      map.put("account", paramAccount);
      map.put("notificationMode", paramNotificationMode);
      map.put("email", paramEmail);
      map.put("phoneNumber", paramPhoneNumber);
      map.put("accountId", accountId);
      map.put("notification_mode_ids", CustomerType.NOTIFICATION_MODE_IDS);
    } catch (Exception e) {
      map.put("codeErr", 1);
      map.put("mesErr", "response.status.value.failed");
    }
  }


  public void functionResetPassWord(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String accountId) {
    try {
      ResetCredentialRequest resetCredentialRequest = new ResetCredentialRequest();
      resetCredentialRequest.setCustomerId(Long.parseLong(accountId));
      resetCredentialRequest.setCredentialType(CredentialType.BY_PASSWORD);
      resetCredentialRequest
          .setNotificationMode(Integer.parseInt(request.getParameter("notification")));
      resetCredentialRequest.setRemark(request.getParameter("remark"));

      ResetCredentialResponse resetCredentialResponse = umgrAccountEndpoint
          .resetCredential(resetCredentialRequest);
      if (resetCredentialResponse == null || resetCredentialResponse.getStatus() == null) {
        throw new Exception("No response");
      } else if (resetCredentialResponse.getStatus().getCode() != 0) {
        map.put("codeErr", resetCredentialResponse.getStatus().getCode());
        throw new Exception(resetCredentialResponse.getStatus().getValue());
      }

      map.put("codeErr", 0);
      map.put("mesErr", "response.status.value.success");
    } catch (Exception e) {
      map.put("codeErr", 1);
      map.put("mesErr", "response.status.value.failed");
    }
  }

  public void saveAccountPersonal(HttpServletRequest request, Customer customer,
      GetFullCustomerResponse customerRes) {
    customer.setCountry(request.getParameter("account-country"));
    try {
      customer.setDateOfBirth(
          DateUtils.parseDate(request.getParameter("account-dateofbirth"), "dd/MM/yyyy"));
    } catch (Exception e) {
    }
    customer.setDescription(request.getParameter("account-about"));
    customer.setGender(NumberUtil.convertToInt(request.getParameter("account-gender")));
    customer.setLivingAddress(request.getParameter("account-living-address"));

    //Cập nhật addresses
    List<Address> lstAddress = customerRes.getCustomer().getAddresses();
    Address address = new Address();
    if (lstAddress == null) {
      List<Address> lstAddressNew = new ArrayList<>();
      address.setAddressType(AddressType.RESIDENCE_ADDRESS.getId());
      address.setAddressStatus(AddressStatus.VALID_STATUS.getId());
      address.setStreet1(request.getParameter("account-nameAddr"));
      lstAddressNew.add(address);
      customer.setAddresses(lstAddressNew);
    } else {
      for (Address item : lstAddress) {
        item.setAddressType(item.getAddressType());
        item.setAddressStatus(item.getAddressStatus());
        item.setStreet1(request.getParameter("account-nameAddr"));
      }
      customer.setAddresses(lstAddress);
    }

    if (StringUtils.isNotEmpty(customer.getDisplayName()) && (customer.getFirstName() == null
        || customer.getLastName() == null)) {
      customer.setFirstName(customer.getDisplayName().split(StringUtil.SPACE_SYMBOL)[0]);
      customer.setLastName(customer.getDisplayName().split(StringUtil.SPACE_SYMBOL)[1]);
    }
  }


  public void saveAccountInfo(HttpServletRequest request, Customer customer) {
    Integer blockReason = Integer.parseInt(request.getParameter("blackList"));
    Integer customerTypeId = Integer.parseInt(request.getParameter("customerType"));
    String positionList = request.getParameter("positionList");

    CustomerType customerType = new CustomerType();
    customerType.setId(customerTypeId);
    customerType.setName(CUSTOMER_TYPE_IDS_WALLET.get(customerTypeId));

    customer.setActive(0 == blockReason);
    customer.setBlackListReason(blockReason);
    customer.setMsisdn(request.getParameter("phone"));
    customer.setNotificationModeId(Integer.parseInt(request.getParameter("notification")));
    customer.setCustomerType(customerType);
    customer.setFirstName(request.getParameter("first-name"));
    customer.setLastName(request.getParameter("last-name"));
    customer.setDisplayName(
        request.getParameter("last-name") + " " + request.getParameter("first-name"));
    customer.setEmail(request.getParameter("email"));
    customer.setWalletTypeId(CustomerUtil.getWalletType(customerTypeId));
    customer.setUserTypeId(CustomerUtil.getUserType(customerTypeId));
    customer.setJobPosition(positionList);
    customer.setDescription(request.getParameter("description"));
  }

  public void saveUpdateCustomerAttribute(HttpServletRequest request, Long accountId) {
    String attributeCount = request.getParameter("count-element-atb");
    List<CustomerAttribute> customerAttributeList = new ArrayList<>();
    for (int i = 1; i <= Integer.parseInt(attributeCount); ++i) {
      CustomerAttribute customerAttribute = new CustomerAttribute();
      customerAttribute.setCustomerId(accountId);
      customerAttribute.setCustomerAttributeTypeId(request.getParameter("accountAttributeId" + i));
      customerAttribute.setValue(request.getParameter("accountAttributeValue" + i));
      customerAttribute.setCreated(new Date());

      customerAttributeList.add(customerAttribute);
    }
    UpdateCustomerAttributeRequest updateCusAttReq = new UpdateCustomerAttributeRequest();
    updateCusAttReq.setCustomerId(accountId);
    updateCusAttReq.setCustomerAttributes(customerAttributeList);
    try {
      umgrAccountEndpoint.saveOrUpdateAttribute(updateCusAttReq);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  /*Add account*/
  public void getInfoAddAccount(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, String paraCusTypeSystem, String walletType, String userType,
      Customer mCustomer) {

    try {
      //get blackList
      getBlackListReason(map);

      getPositions(map);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    Map<Integer, String> customerTypeId = new LinkedHashMap<>();
    if (SYSTEM_STAFF.equals(paraCusTypeSystem)) {
      customerTypeId.putAll(STAFF_CUSTOMER_TYPES);
    } else if (WalletAccountController.SYSTEM_WALLET.equals(paraCusTypeSystem)) {
      customerTypeId.putAll(CUSTOMER_TYPE_IDS_WALLET);
    }
    map.put("notification_mode_ids", CustomerType.NOTIFICATION_MODE_IDS);
    map.put("customer_type_ids", customerTypeId);
    map.put("edit_type", "add");
    map.put("wallet_type", walletType);
    map.put("user_type", userType);
    map.put("account_object", mCustomer);
    mCustomer = null;
  }

  public void putParamTranRuleToRequest(ModelMap map) {
    map.put("eventTypes", BALANCE_EVENT_TYPES);
    map.put("customers", getCustomers());
    map.put("actorTypes", ACTOR_TYPES);
    map.put("serviceTypes", FULL_SERVICE_TYPES);
  }

  public String encodeRotateImage(MultipartFile file) {
    try {
      if (file != null) {
        byte[] fileBytes = file.getBytes();
        byte[] rotateBytes = ImageOrientationUtil
            .rotateCorrectImage(file.getInputStream(), fileBytes);
        return Base64.getEncoder().encodeToString(rotateBytes == null ? fileBytes : rotateBytes);
      }
    } catch (IOException e) {
      LOG.error("IOException", e);
    }
    return null;
  }
}
