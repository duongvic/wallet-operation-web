package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa;


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

public interface IUmgrRolesPrivilegesEndpoint {

  FindUmgrCustomerResponse findUmgrCustomers(FindUmgrCustomerRequest request) throws Exception;

  //@RolesAllowed({"WS_ADD_ROLE"})
  AddUmgrCustomerRoleResponse addUmgrCustomerRole(AddUmgrCustomerRoleRequest request)
      throws Exception;

  //@RolesAllowed({"WS_REMOVE_ROLE"})
  RemoveUmgrCustomerRoleResponse removeUmgrCustomerRole(
      RemoveUmgrCustomerRoleRequest request) throws Exception;

  //@RolesAllowed({"WS_ADD_PRIVILEGE"})
  AddUmgrCustomerPrivilegeResponse addUmgrCustomerPrivilege(
      AddUmgrCustomerPrivilegeRequest request);

  //@RolesAllowed({"WS_REMOVE_PRIVILEGE"})
  RemoveUmgrCustomerPrivilegeResponse removeUmgrCustomerPrivilege(
      RemoveUmgrCustomerPrivilegeRequest request);

  //@RolesAllowed({"WS_GET_PRIVILEGE"})
  GetUmgrCustomerPrivilegesResponse getUmgrCustomerPrivileges(
      GetUmgrCustomerPrivilegesRequest request);

  //@RolesAllowed({"WS_GET_ROLE"})
  GetUmgrCustomerRolesResponse getUmgrCustomerRoles(
      GetUmgrCustomerRolesRequest request) throws Exception;

  //@RolesAllowed({"WS_CREATE_UMGR_PRIVILEGE"})
  CreateUmgrPrivilegeResponse createUmgrPrivilege(CreateUmgrPrivilegeRequest request)
      throws Exception;

  //@RolesAllowed({"WS_UPDATE_UMGR_PRIVILEGE"})
  UpdateUmgrPrivilegeResponse updateUmgrPrivilege(UpdateUmgrPrivilegeRequest request)
      throws Exception;

  //@RolesAllowed({"WS_DELETE_UMGR_PRIVILEGE"})
  DeleteUmgrPrivilegeResponse deleteUmgrPrivilege(DeleteUmgrPrivilegeRequest request);

  //@RolesAllowed({"WS_GET_UMGR_PRIVILEGE"})
  GetUmgrPrivilegesResponse getUmgrPrivileges(GetUmgrPrivilegesRequest request) throws Exception;

  //@RolesAllowed({"WS_CREATE_UMGR_ROLE"})
  CreateUmgrRoleResponse createUmgrRole(CreateUmgrRoleRequest request) throws Exception;

  //@RolesAllowed({"WS_UPDATE_UMGR_ROLE"})
  UpdateUmgrRoleResponse updateUmgrRole(UpdateUmgrRoleRequest request) throws Exception;

  //@RolesAllowed({"WS_DELETE_UMGR_ROLE"})
  DeleteUmgrRoleResponse deleteUmgrRole(DeleteUmgrRoleRequest request);

  //@RolesAllowed({"WS_GET_UMGR_ROLE"})
  GetUmgrRolesResponse getUmgrRoles(GetUmgrRolesRequest request) throws Exception;

  //@RolesAllowed({"WS_CREATE_UMGR_ROLE_PRIVILEGE"})
  CreateUmgrRolePrivilegeResponse createUmgrRolePrivilege(
      CreateUmgrRolePrivilegeRequest request) throws Exception;

  //@RolesAllowed({"WS_DELETE_UMGR_ROLE_PRIVILEGE"})
  DeleteUmgrRolePrivilegeResponse deleteUmgrRolePrivilege(
      DeleteUmgrRolePrivilegeRequest request) throws Exception;

  //@RolesAllowed({"WS_GET_UMGR_ROLE_PRIVILEGE"})
  GetUmgrRolePrivilegesResponse getUmgrRolePrivileges(
      GetUmgrRolePrivilegesRequest request) throws Exception;

  UpdateUmgrCustomerRoleResponse updateUmgrCustomerRole(UpdateUmgrCustomerRoleRequest request)
      throws Exception;

  ChangeUmgrRoleStatusResponse changeUmgrRoleStatus(ChangeUmgrRoleStatusRequest request)
      throws Exception;

  ChangeUmgrPrivilegeStatusResponse changeUmgrPrivilegeStatus(
      ChangeUmgrPrivilegeStatusRequest request) throws Exception;

  UpdateUmgrRolePrivilegeResponse updateUmgrRolePrivilege(
      UpdateUmgrRolePrivilegeRequest request) throws Exception;

  RemoveUmgrCustomerRoleResponse deleteUmgrCustomerRole(RemoveUmgrCustomerRoleRequest request);

  RemoveUmgrCustomerPrivilegeResponse deleteUmgrCustomerPrivilege(
      RemoveUmgrCustomerPrivilegeRequest request);
}
