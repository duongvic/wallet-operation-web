package vn.mog.ewallet.operation.web.controller.provider;

import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.provider.beans.ProviderProfileDataRow;
import vn.mog.ewallet.operation.web.exception.FrontEndException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderProfileResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderProfile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.CardType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.*;

@Controller
@RequestMapping(value = "/provider/profile-manager")
public class ProfileProviderController extends AbstractController {

  public static final String PROFILE_MANAGER_LIST = "/provider/profile-manager/list";
  private static final Logger log = Logger.getLogger(ProfileProviderController.class);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + ADMIN_OPERATION + "','"
      + FINANCESUPPORT_LEADER + "','" + FA_MANAGER + "','" + FINANCE + "','"
      + SALESUPPORT + "','" + SALESUPPORT_MANAGER + "','" + SALESUPPORT_LEADER + "','"
      + SALE_DIRECTOR + "', '"
      + RECONCILIATION + "', '" + RECONCILIATION_LEADER + "','" + CUSTOMERCARE + "','" + CUSTOMERCARE_MANAGER + "')")
  public String profileProviderManager(HttpServletRequest request, ModelMap model)
      throws FrontEndException {


    String param_providerCode = request.getParameter("provider");
    String param_providerName = request.getParameter("providerName");

    Long total = 0L;
    Integer offset = 0;
    Integer limit = 50;
    if (request.getParameter("d-48498-p") != null) {
      Integer p = Integer.parseInt(request.getParameter("d-48498-p"));
      offset = limit * (p - 1);
    }

    List<ProviderProfileDataRow> providerProfileDataRows = new ArrayList<>();
    try {
      model.put("listProvider", ProviderCode.PAYMENT_PROVIDER_CODES);

      FindProviderProfileRequest requestProfile = new FindProviderProfileRequest();
      requestProfile.setProviderCode(param_providerCode);
      FindProviderProfileResponse responseProfile = providerEndpoint.findProviderProfiles(requestProfile);

      System.out.println("======================================================================");

      List<ProviderProfile> providerProfiles = new ArrayList<>(responseProfile.getProviderProfiles());
      if (providerProfiles != null && !providerProfiles.isEmpty()) {
        for (ProviderProfile providerProfile : providerProfiles) {
          // Provider Code
          String providerCode = providerProfile.getProviderCode();
          String providerName = providerProfile.getProviderName();
          // Discount Rate
          Map<String, Map<String, Map<Long, Double>>> discountRates = providerProfile
              .getDiscountRates();
          if (discountRates != null) {
            for (String serviceType : discountRates.keySet()) {
              // Service
              Map<String, Map<Long, Double>> services = discountRates.get(serviceType);
              String nameService ="";
              for (String service : services.keySet()) {
                if (!service.equalsIgnoreCase("DEFAULT")) {

                  if(serviceType != "BILL_PAYMENT"){
                     nameService = String.valueOf(service != "VTC" ? CardType.getCardType(service).getName() : "VCOIN");
                  }
                  Map<Long, Double> faceValueDiscountRates = services.get(service);
                  for (Long faceValue : faceValueDiscountRates.keySet()) {
                    Double discountRate = faceValueDiscountRates.get(faceValue);
                    ProviderProfileDataRow providerProfileDataRow = new ProviderProfileDataRow(
                        providerCode, providerName, serviceType, service, nameService,faceValue, discountRate, 0L);
                    System.out.println(providerProfileDataRow);
                    providerProfileDataRows.add(providerProfileDataRow);
                  }
                }
              }
            }
          }

          // Discount Amount
          Map<String, Map<String, Map<Long, Long>>> discountFixedAmounts = providerProfile
              .getDiscountFixedAmounts();
          if (discountFixedAmounts != null) {
            for (String serviceType : discountFixedAmounts.keySet()) {
              // Service
              Map<String, Map<Long, Long>> services = discountFixedAmounts.get(serviceType);
              String nameService ="";
              for (String service : services.keySet()) {
                if (!service.equalsIgnoreCase("DEFAULT")) {
                  if(serviceType != "BILL_PAYMENT"){
                    nameService = String.valueOf(service != "VTC" ? CardType.getCardType(service).getName() : "VCOIN");
                  }
                  // FaceValue & CapitalValue
                  Map<Long, Long> faceValueDiscountFixedAmounts = services.get(service);
                  for (Long faceValue : faceValueDiscountFixedAmounts.keySet()) {
                    Long discountFixedAmount = faceValueDiscountFixedAmounts.get(faceValue);
                    ProviderProfileDataRow providerProfileDataRow = new ProviderProfileDataRow(
                        providerCode, providerName, serviceType, service, nameService, faceValue, 0.0d, discountFixedAmount);
                    System.out.println(providerProfileDataRow);
                    providerProfileDataRows.add(providerProfileDataRow);
                  }
                }
              }
            }
          }
        }
      }

      System.out.println(providerProfileDataRows);

      /*change name service bill payment front codename to string */
      FindTrueServiceRequest findTrueServiceRequest = new FindTrueServiceRequest();
      findTrueServiceRequest.setServiceTypes(Collections.singletonList("BILL_PAYMENT"));
      FindTrueServiceResponse ftsResponse = trueServiceEndpoint
          .findTrueServices(findTrueServiceRequest);

      for (ProviderProfileDataRow provider : providerProfileDataRows) {
        for (TrueService trueService : ftsResponse.getServices()) {
          if (provider.getService().equals(trueService.getServiceCode())) {
            provider.setService(trueService.getServiceShortName());
            break;
          }
        }
      }
      /*end*/

    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }

    model.put("list", providerProfileDataRows);
    model.put("provider", param_providerCode);
    model.put("providerName", param_providerName);
    model.put("pagesize", limit);
    model.put("offset", offset);
    model.put("total", providerProfileDataRows.size() > 0 ? providerProfileDataRows.size() : total.intValue());
    return "/profile_provider/profile_provider";
  }

}
