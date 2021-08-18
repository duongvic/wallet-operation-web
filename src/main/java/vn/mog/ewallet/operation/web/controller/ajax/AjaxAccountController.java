package vn.mog.ewallet.operation.web.controller.ajax;

import java.util.Collections;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.ErrorConstant;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.AccessTokenObject;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.CustomerRole;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.ElementList;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.constant.AccessKey;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.AccessTokenPms;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GetAccessTokenRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.ProfileType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.service.AccessTokenService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.security.api.CallerInformation;
import vn.mog.framework.security.impl.CallerUtilsImpl;

@RestController
@RequestMapping(value = "/ajax-acc-controller")
public class AjaxAccountController extends AbstractController {
  private static final Logger LOG = LogManager.getLogger(AjaxController.class);

  @RequestMapping(value = "/checkExist", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> checkExist(HttpServletRequest request) throws Exception {

    AccessTokenObject accessTokenObject = getInfoUser();
    LOG.info(accessTokenObject.toString());

    FindCustomerRequest findCustomerRequest = new FindCustomerRequest();
    findCustomerRequest.setAccessKey(AccessKey.accessKey);
    Long timeStamp = new Date().getTime();
    findCustomerRequest.setSign(getSign(timeStamp, AccessKey.accessKey, AccessKey.secretKey));
    findCustomerRequest.setTimestamp(timeStamp);
    findCustomerRequest.setMsisdns(Collections.singleton(accessTokenObject.getPhone()));

    try {
      GeneralResponse<Object> listSystemUserRes =
          systemUserEndpoint.findAccountFromZota(findCustomerRequest);
      System.out.println("listSystemUserRes.getMsg() = " +listSystemUserRes.getCode()+" "+ listSystemUserRes.getMsg());
      if (!listSystemUserRes.getCode().equals(ErrorConstant.SUCCESS.code())) {
        return ResponseEntity.badRequest().body(listSystemUserRes.getCode() +" "+listSystemUserRes.getMsg());
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      System.out.println("e.getMessage() = " + e.getMessage());
      return ResponseEntity.badRequest().body("e.getMessage() = " + e.getMessage());
    }
    return ResponseEntity.ok(null);
  }

  @RequestMapping(value = "/staff/add", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> addAccount(HttpServletRequest request) throws Exception {

    //      thông tin cá nhân
    GeneralResponse<Object> cusRes = null;
    try {

      AccessTokenObject accessTokenObject = getInfoUser();
      Customer customer = new Customer();
      customer.setActive(true);
      customer.setNotificationModeId(0);
      customer.setDisplayName(accessTokenObject.getFullname());
      customer.setMsisdn(accessTokenObject.getPhone());
      customer.setEmail(accessTokenObject.getEmail());
      if(accessTokenObject.getType() == CustomerType.ID_ADMIN){
        customer.setCustomerType(new CustomerType(1));
      }
      if(accessTokenObject.getType() == CustomerType.ID_STAFF){
        customer.setCustomerType(new CustomerType(2));
      }

      CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
      createCustomerRequest.setAccessKey(AccessKey.accessKey);
      Long timeStamp = new Date().getTime();
      createCustomerRequest.setSign(getSign(timeStamp, AccessKey.accessKey, AccessKey.secretKey));
      createCustomerRequest.setTimestamp(timeStamp);
      createCustomerRequest.setCustomer(customer);

      cusRes = systemUserEndpoint.createAccount(createCustomerRequest);
      if (!cusRes.getCode().equals(ErrorConstant.SUCCESS.code())) {
        System.out.println("add customer ops pms = " + cusRes.getCode()+" "+cusRes.getMsg());
        return ResponseEntity.badRequest().body("add customer ops pms = " + cusRes.getCode()+" "+cusRes.getMsg());
      }
      System.out.println("add customer ops pms = "+ cusRes.getCode()+" "+cusRes.getMsg());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      System.out.println("e.getMessage() = " + e.getMessage());
      return ResponseEntity.badRequest().body("e.getMessage() = " + e.getMessage());
    }
    return ResponseEntity.ok(null);
  }

  @RequestMapping(value = "/staff/checkPermission", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> checkPermission(HttpServletRequest request) throws Exception {
    GeneralResponse<Object> tokenRes = null;
    try {
      CallerUtilsImpl callerUtil = new CallerUtilsImpl();
      CallerInformation callerInformation = callerUtil.getCallerInformation();
      String accessToken = callerInformation.getAccessToken();
      String profile = ProfileType.SYSTEM_USER.getName();

      GetAccessTokenRequest accessTokenRequest = new GetAccessTokenRequest();
      accessTokenRequest.setProfile(profile);
      accessTokenRequest.setZotaToken(accessToken);
      tokenRes = accessTokenEndpoint.getAccessTokenPMS(accessTokenRequest);

      if (tokenRes.getCode().equals("0")) {
        System.out.println("tokenRes. = " + tokenRes.getCode()+" "+tokenRes.getMsg());
        AccessTokenPms accessTokenPms =
            Utils.responseToObject(tokenRes.getData(), AccessTokenPms.class);
        Long id = accessTokenPms.getId();
        String accessTokenPMS =
            accessTokenPms.getToken_type() + " " + accessTokenPms.getAccess_token();
        if (hasRole(id, accessTokenPMS) == true) {
          return ResponseEntity.ok(null);
        } else {
          return ResponseEntity.badRequest().body(null);
        }
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      System.out.println("e.getMessage() = " + e.getMessage());
      return ResponseEntity.badRequest().body(null);
    }
    return ResponseEntity.badRequest().body(null);
  }

  public boolean hasRole(Long id, String accessToken) {
    FindCustomerRoleRequest findCustomerRoleRequest = new FindCustomerRoleRequest();
    findCustomerRoleRequest.setCustomerIds(Collections.singleton(id));
    try {
      GeneralResponse<Object> roleRes =
          systemUserEndpoint.findAllCustomerRole(findCustomerRoleRequest, accessToken);

      if (roleRes.getCode().equals(ErrorConstant.SUCCESS.code())) {
        ElementList<CustomerRole> elementList =
            Utils.responseToElementList(roleRes.getData(), CustomerRole.class);
        LOG.info("role pms ops: ", roleRes.getCode()+" "+roleRes.getMsg()+" "+roleRes.getData());
        System.out.println("role pms ops: "+ roleRes.getCode()+" "+roleRes.getMsg()+" "+roleRes.getData());
        if (elementList.getTotal() == 0) {
          return false;
        }
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return false;
    }
    return true;
  }

  public AccessTokenObject getInfoUser(){
    CallerUtilsImpl callerUtil = new CallerUtilsImpl();
    CallerInformation callerInformation = callerUtil.getCallerInformation();
    String accessToken = callerInformation.getAccessToken();
    String ob = AccessTokenService.toJson(accessToken);
    AccessTokenObject accessTokenObject = AccessTokenService.jsonToObject(ob);
    System.out.println("accessTokenObject.toString() = " + accessTokenObject.toString());
    LOG.info("user info", accessTokenObject.toString());
    return accessTokenObject;
  }



}
