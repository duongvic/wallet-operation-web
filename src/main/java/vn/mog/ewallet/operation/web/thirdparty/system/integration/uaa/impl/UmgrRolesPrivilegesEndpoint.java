package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.impl;


import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_NOTIFICATION_SERVICE_API_ENDPOINT;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.AuthServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.BroadcastNotificationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.impl.NotificationEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.AddUmgrCustomerRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrPrivilegeStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrPrivilegeStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrRoleStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.ChangeUmgrRoleStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRolePrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.CreateUmgrRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrRolePrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.DeleteUmgrRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerPrivilegesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerPrivilegesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrCustomerRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrPrivilegesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrPrivilegesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolePrivilegesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolePrivilegesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetUmgrRolesResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.RemoveUmgrCustomerPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.RemoveUmgrCustomerPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.RemoveUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.RemoveUmgrCustomerRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrCustomerRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrPrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrPrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRolePrivilegeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRolePrivilegeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.UpdateUmgrRoleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.IUmgrRolesPrivilegesEndpoint;
import vn.mog.framework.common.utils.Utils;

@Slf4j
@Component
public class UmgrRolesPrivilegesEndpoint implements IUmgrRolesPrivilegesEndpoint {

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-getUmgrRoles}")
  private String UMGR_ROLE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-changeUmgrRoleStatus}")
  private String UMGR_CHANGE_ROLE_STATUS;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-updateUmgrRole}")
  private String UMGR_UPDATE_ROLE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-getUmgrRolePrivileges}")
  private String UMGR_GET_ROLE_PRIVILEGES;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-getUmgrPrivileges}")
  private String UMGR_PRIVILEGES_GET;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-createUmgrRolePrivilege}")
  private String UMGR_CREATE_ROLE_PRIVILEGE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-updateUmgrRolePrivilege}")
  private String UMGR_UPDATE_ROLE_PRIVILEGE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-deleteUmgrRolePrivilege}")
  private String UMGR_DELETE_ROLE_PRIVILEGES;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-createUmgrRole}")
  private String UMGR_ROLE_CREATE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-updateUmgrPrivilege}")
  private String UMGR_PRIVILEGES_UPDATE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-findUmgrCustomers}")
  private String UMGR_CUSTOMER_FIND;

  @Value("${platform.backend.uaa.service.3rd.url.system-getAllCustomerTypes}")
  private String UMGR_CUSTOMER_TYPES_GET;

  @Value("${platform.backend.uaa.service.3rd.url.system-getBlacklistReasons}")
  private String UMGR_BLACK_LIST_REASON_GET;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-changeUmgrPrivilegeStatus}")
  private String UMGR_CHANGE_PRIVILEGE_STATUS;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-createUmgrPrivilege}")
  private String ADD_UMGR_PRIVILEGE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-addUmgrCustomerRole}")
  private String UMGR_CUSTOMER_ADD_ROLE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-removeUmgrCustomerRole}")
  private String UMGR_CUSTOMER_REMOVE_ROLE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-getUmgrCustomerRoles}")
  private String UMGR_CUSTOMER_GET_ROLE;

  @Value("${platform.backend.uaa.service.3rd.url.umgrroles-updateUmgrCustomerRole}")
  private String UMGR_CUSTOMER_UPDATE_ROLE;

  @Autowired
  AuthServerAPIClient authServerAPIClient;

  @Override
  public FindUmgrCustomerResponse findUmgrCustomers(FindUmgrCustomerRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CUSTOMER_FIND;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, FindUmgrCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public AddUmgrCustomerRoleResponse addUmgrCustomerRole(AddUmgrCustomerRoleRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CUSTOMER_ADD_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, AddUmgrCustomerRoleResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public RemoveUmgrCustomerRoleResponse removeUmgrCustomerRole(
      RemoveUmgrCustomerRoleRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CUSTOMER_REMOVE_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, RemoveUmgrCustomerRoleResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public AddUmgrCustomerPrivilegeResponse addUmgrCustomerPrivilege(
      AddUmgrCustomerPrivilegeRequest request) {
    return null;
  }

  @Override
  public RemoveUmgrCustomerPrivilegeResponse removeUmgrCustomerPrivilege(
      RemoveUmgrCustomerPrivilegeRequest request) {
    return null;
  }

  @Override
  public GetUmgrCustomerPrivilegesResponse getUmgrCustomerPrivileges(
      GetUmgrCustomerPrivilegesRequest request) {
    return null;
  }

  @Override
  public GetUmgrCustomerRolesResponse getUmgrCustomerRoles(GetUmgrCustomerRolesRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CUSTOMER_GET_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient.postForEntity(url, request, uriVariables,
          GetUmgrCustomerRolesResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public CreateUmgrPrivilegeResponse createUmgrPrivilege(CreateUmgrPrivilegeRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = ADD_UMGR_PRIVILEGE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient.postForEntity(url, request, uriVariables,
          CreateUmgrPrivilegeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateUmgrPrivilegeResponse updateUmgrPrivilege(UpdateUmgrPrivilegeRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_PRIVILEGES_UPDATE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, UpdateUmgrPrivilegeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public DeleteUmgrPrivilegeResponse deleteUmgrPrivilege(DeleteUmgrPrivilegeRequest request) {
    return null;
  }

  @Override
  public GetUmgrPrivilegesResponse getUmgrPrivileges(GetUmgrPrivilegesRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_PRIVILEGES_GET;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, GetUmgrPrivilegesResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public CreateUmgrRoleResponse createUmgrRole(CreateUmgrRoleRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_ROLE_CREATE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, CreateUmgrRoleResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateUmgrRoleResponse updateUmgrRole(UpdateUmgrRoleRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_UPDATE_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, UpdateUmgrRoleResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public DeleteUmgrRoleResponse deleteUmgrRole(DeleteUmgrRoleRequest request) {
    return null;
  }

  @Override
  public GetUmgrRolesResponse getUmgrRoles(GetUmgrRolesRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, GetUmgrRolesResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public CreateUmgrRolePrivilegeResponse createUmgrRolePrivilege(
      CreateUmgrRolePrivilegeRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CREATE_ROLE_PRIVILEGE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, CreateUmgrRolePrivilegeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public DeleteUmgrRolePrivilegeResponse deleteUmgrRolePrivilege(
      DeleteUmgrRolePrivilegeRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_DELETE_ROLE_PRIVILEGES;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, DeleteUmgrRolePrivilegeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public GetUmgrRolePrivilegesResponse getUmgrRolePrivileges(GetUmgrRolePrivilegesRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_GET_ROLE_PRIVILEGES;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, GetUmgrRolePrivilegesResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateUmgrCustomerRoleResponse updateUmgrCustomerRole(
      UpdateUmgrCustomerRoleRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CUSTOMER_UPDATE_ROLE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, UpdateUmgrCustomerRoleResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public ChangeUmgrRoleStatusResponse changeUmgrRoleStatus(ChangeUmgrRoleStatusRequest request)
      throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CHANGE_ROLE_STATUS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, ChangeUmgrRoleStatusResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public ChangeUmgrPrivilegeStatusResponse changeUmgrPrivilegeStatus(
      ChangeUmgrPrivilegeStatusRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_CHANGE_PRIVILEGE_STATUS;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient.postForEntity(url, request, uriVariables,
          ChangeUmgrPrivilegeStatusResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public UpdateUmgrRolePrivilegeResponse updateUmgrRolePrivilege(
      UpdateUmgrRolePrivilegeRequest request) throws Exception {
    Map<String, String> uriVariables = null;
    String url = UMGR_UPDATE_ROLE_PRIVILEGE;
    try {
      log.info("REQ-URL: {}", url);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return authServerAPIClient
          .postForEntity(url, request, uriVariables, UpdateUmgrRolePrivilegeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }

  }

  @Override
  public RemoveUmgrCustomerRoleResponse deleteUmgrCustomerRole(
      RemoveUmgrCustomerRoleRequest request) {
    return null;
  }

  @Override
  public RemoveUmgrCustomerPrivilegeResponse deleteUmgrCustomerPrivilege(
      RemoveUmgrCustomerPrivilegeRequest request) {
    return null;
  }
}
