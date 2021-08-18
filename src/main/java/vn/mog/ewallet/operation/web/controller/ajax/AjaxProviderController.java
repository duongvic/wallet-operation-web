package vn.mog.ewallet.operation.web.controller.ajax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FundInProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderServicesByProviderCodeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.ResetProviderServiceRankingScoreResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.UpdateProviderServiceRankingScoreResponse;

@RestController
@RequestMapping(value = "/ajax/provider")
public class AjaxProviderController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AjaxProviderController.class);

  @RequestMapping(value = "/get/{providerCode}", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  @ResponseBody
  public ResponseEntity<?> getProviderService(@PathVariable("providerCode") String providerCode) {
    GetProviderServicesByProviderCodeResponse response;
    try {
      GetProviderServicesByProviderCodeRequest request =
          new GetProviderServicesByProviderCodeRequest();
      request.setProviderCode(providerCode);
      response = providerEndpoint.getProviderServicesOfProvider(request);
      if (response.getStatus().getCode() != 0) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    return ResponseEntity.ok(response);
  }

  //  UpdateProviderServiceRankingScoreRequest
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  @ResponseBody
  public ResponseEntity<?> updateProviderService(
      @ModelAttribute("UpdateProviderServiceRankingScoreRequest")
          UpdateProviderServiceRankingScoreRequest updateProviderServiceRankingScoreRequest) {
    UpdateProviderServiceRankingScoreResponse response = null;
    try {
      response =
          providerEndpoint.updateProviderServiceRankingScore(
              updateProviderServiceRankingScoreRequest);
      if (response.getStatus().getCode() != 0) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/reset/{providerCode}", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  @ResponseBody
  public ResponseEntity<?> resetProviderService(@PathVariable("providerCode") String providerCode) {
    ResetProviderServiceRankingScoreResponse response = null;
    try {
      ResetProviderServiceRankingScoreRequest request =
          new ResetProviderServiceRankingScoreRequest();
      request.setProviderCode(providerCode);
      response = providerEndpoint.resetProviderServiceRankingScore(request);
      if (response.getStatus().getCode() != 0) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/fundIn", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.STAFF
          + "', '"
          + RoleConstants.ADMIN_OPERATION
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT')")
  @ResponseBody
  public ResponseEntity<?> fundInProvider(
      @ModelAttribute FundInProviderRequest fundInProviderRequest) {
    FundInProviderResponse response = null;
    try {
      response = providerEndpoint.fundInProvider(fundInProviderRequest);
      if (response.getStatus().getCode() != 0) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    return ResponseEntity.ok(response);
  }
}
