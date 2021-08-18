package vn.mog.ewallet.operation.web.controller.reconciliation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.AttachmentDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ProfileDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ReconciliationDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ReconciliationElementDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl.WalletEndpoint;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.Utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconciliationFlowStageEnum.STAGES;
import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum.PAYMENT_TYPES;
import static vn.mog.ewallet.operation.web.utils.WebUtil.MERCHANT_DEFAULT;

@Controller
@RequestMapping(value = "/wallet/reconcilation")
public class ReconciliationV3Controller extends AbstractController {

  public static final String RECON_CONTROLLER = "/wallet/reconcilation";
  public static final String RECON_LIST = RECON_CONTROLLER + "/list";
  public static final String PROFILE_LIST = RECON_CONTROLLER + "/profile/list";
  public static final String RECON_EXPORT = RECON_CONTROLLER + "/export";
  public static final String RECON_DETAIL = RECON_CONTROLLER + "/detail";
  private static final Logger LOG = LogManager.getLogger(ReconciliationV3Controller.class);

  @Autowired
  WalletEndpoint walletEndpoint;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map)
          throws Exception {
    try {
      map.put("serviceTypes", PAYMENT_TYPES);
      map.put("paymentPolicies", Arrays.asList(PaymentPolicyEnum.values()));
      map.put("stages", STAGES);
      map.put("months", getMonths());
      map.put("currentYear", new Date().getYear() + 1900);
      map.put("currentDate", new Date());
      ////////////////////////////////////
      String[] searchCustomer = request.getParameterValues("searchCustomer");
      String paymentPolicieTabs = request.getParameter("paymentPolicieTab");
      String stageTab = request.getParameter("stageTab");
      String[] serviceTypes = request.getParameterValues("serviceTypes");
      String[] paymentPolicySelects = request.getParameterValues("paymentPolicies");
      String month = request.getParameter("month");
      String year = request.getParameter("year");
      String cycle = request.getParameter("cycle");
      String[] stages = request.getParameterValues("stages");
      String bolOfficial = request.getParameter("bolOfficial");
      String queryString = getQuery(request.getQueryString());

      SearchReconciliationForm searchReconciliationForm = new SearchReconciliationForm();

      List<Long> customerIds = null;
      if (searchCustomer != null && searchCustomer.length > 0) {
        customerIds = Arrays.stream(searchCustomer).map(Long::parseLong).collect(Collectors.toList());
        searchReconciliationForm.setCustomerIds(customerIds);
      }

      searchReconciliationForm.setCustomerTypes(Collections.singletonList(CustomerTypeEnum.valueOf("MERCHANT")));

      if (paymentPolicieTabs != null && !paymentPolicieTabs.isEmpty()) {
        //PaymentPolicyEnum
        searchReconciliationForm.setPaymentPolicies(Collections.singletonList(PaymentPolicyEnum.valueOf(paymentPolicieTabs)));
      } else {
        if (paymentPolicySelects != null && paymentPolicySelects.length > 0) {
          List<PaymentPolicyEnum> paymentPolicyEnums = new ArrayList<>();
          for (String paymentPolicy : paymentPolicySelects) {
            paymentPolicyEnums.add(PaymentPolicyEnum.valueOf(paymentPolicy));
          }
          searchReconciliationForm.setPaymentPolicies(paymentPolicyEnums);
        }
      }

      if (stageTab != null && !stageTab.isEmpty()) {
        List<ReconciliationFlowStageEnum> reconciliationFlowStageEnums = new ArrayList<>();

        switch (stageTab) {
          case "even":
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.OPERATION_STAFF_SUBMITED);
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.OPERATION_MANAGER_APPROVED);
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.MERCHANT_CONFIRMED);
            searchReconciliationForm.setStages(reconciliationFlowStageEnums);
            break;
          case "odd":
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.OPERATION_MANAGER_REJECTED);
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.MERCHANT_REJECTED);
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.FINANCE_MANAGER_REJECTED);
            searchReconciliationForm.setStages(reconciliationFlowStageEnums);
            break;
          case "8":
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.FINISHED);
            searchReconciliationForm.setStages(reconciliationFlowStageEnums);
            break;
          case "0":
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.INIT);
            searchReconciliationForm.setStages(reconciliationFlowStageEnums);
            break;
        }

      } else {
        if (stages != null && stages.length > 0) {
          List<ReconciliationFlowStageEnum> reconciliationFlowStageEnums = new ArrayList<>();
          for (String stage : stages) {
            reconciliationFlowStageEnums.add(ReconciliationFlowStageEnum.valueOf(stage));
          }
          searchReconciliationForm.setStages(reconciliationFlowStageEnums);
        }
      }

      if (serviceTypes != null && serviceTypes.length > 0) {
        List<ServiceTypeEnum> serviceTypeEnums = new ArrayList<>();
        for (String serviceType : serviceTypes) {
          serviceTypeEnums.add(ServiceTypeEnum.valueOf(serviceType));
        }
        searchReconciliationForm.setServiceTypes(serviceTypeEnums);
      }

      searchReconciliationForm.setMonth(month != null && !month.isEmpty() && !month.equals("0") ? Integer.valueOf(month) : null);
      searchReconciliationForm.setYear(year != null && !year.isEmpty() && !year.equals("0") ? Integer.valueOf(year) : null);
      searchReconciliationForm.setCycle(cycle != null && !cycle.isEmpty() && !cycle.equals("0") ? Integer.valueOf(cycle) : null);
      searchReconciliationForm.setBolOfficial(bolOfficial != null && !bolOfficial.isEmpty() ? Boolean.valueOf(bolOfficial) : null);

      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }
      Integer page = offset / limit;
      searchReconciliationForm.setPage(page + 1);
      searchReconciliationForm.setPageSize(limit);

      GeneralResponse<Object> res = reconciliationEndpoint.findReconciliations(searchReconciliationForm);
      ServiceMultiResult<ReconciliationDTO> result = Utils.responseToServiceMultiResult(res.getData(), ReconciliationDTO.class);
      map.put("reconciliations", result.getList());
      map.put("reconciliationsJSON", Utils.objectToJson(result.getList()));
      map.put("total", result.getTotal() != null ? result.getTotal() : 0);
      map.put("offset", offset);
      map.put("pagesize", limit);
      map.put("queryString", queryString);
      map.put("stagesQuery", Arrays.toString(stages));
      map.put("paymentPolicySelectsQuery", Arrays.toString(paymentPolicySelects));

      GeneralResponse<Object> profileRes = reconciliationEndpoint.getProfile();
      List<ProfileDTO> profileDTOS = Utils.responseToListObject(profileRes.getData(), ProfileDTO.class);
      map.put("profileDTOS", profileDTOS);
      map.put("customerIds", StringUtils.join(customerIds, "|"));

    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/list";
  }

  @RequestMapping(value = "/profile/list", method = RequestMethod.GET)
  public String profileList(HttpServletRequest request, HttpServletResponse response, ModelMap map)
          throws Exception {
    try {
      String cif = request.getParameter("fullTextSearch");
      String[] paymentPolicies = request.getParameterValues("paymentPolicies");
      String[] serviceTypes = request.getParameterValues("serviceTypes");
      SearchProfileForm searchProfileForm = new SearchProfileForm();
      searchProfileForm.setSearchProfile(StringUtils.isBlank(cif) ? null : cif);
      if (paymentPolicies != null && paymentPolicies.length > 0 && !paymentPolicies[0].equals("all")) {
        List<PaymentPolicyEnum> paymentPolicyEnums = new ArrayList<>();
        for (String paymentPolicy : paymentPolicies) {
          paymentPolicyEnums.add(PaymentPolicyEnum.valueOf(paymentPolicy));
        }
        searchProfileForm.setPaymentPolicies(paymentPolicyEnums);
      }

      if (serviceTypes != null && serviceTypes.length > 0 && !serviceTypes[0].equals("all")) {
        List<ServiceTypeEnum> paymentPolicyEnums = new ArrayList<>();
        for (String serviceType : serviceTypes) {
          paymentPolicyEnums.add(ServiceTypeEnum.valueOf(serviceType));
        }
        searchProfileForm.setServiceTypes(paymentPolicyEnums);
      }

      GeneralResponse<Object> res = reconciliationEndpoint.searchProfile(searchProfileForm);
      List<ProfileDTO> profileDTOS = Utils.responseToServiceMultiResult(res.getData(), ProfileDTO.class).getList();
      map.put("profileDTOS", profileDTOS);

      map.put("serviceTypes", ServiceTypeEnum.RECONCILIATION);
    } catch (Exception e) {

    }
    return "/reconciliation/v3/profile/list";
  }

  @RequestMapping(value = "/profile/detail/{id}", method = RequestMethod.GET)
  public String profileList(@PathVariable("id") Long id, ModelMap map)
          throws Exception {
    try {
      GeneralResponse<Object> res = reconciliationEndpoint.getProfileById(id);
      ProfileDTO profileDTO = Utils.responseToObject(res.getData(), ProfileDTO.class);
      map.put("profileDTO", profileDTO);
    } catch (Exception e) {

    }
    return "/reconciliation/v3/profile/detail";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, ModelMap map)
          throws Exception {
    try {
      String tab = request.getParameter("tab");
      map.put("reconcil", getReconciliationDTO(id));
      map.put("reconcilJSON", Utils.objectToJson(getReconciliationDTO(id)));
      map.put("currentYear", new Date().getYear() + 1900);
      map.put("months", getMonths());
      map.put("tab", tab != null && !tab.isEmpty() ? tab : StringUtils.EMPTY);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/edit/edit";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {

      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
      map.put("totalURL", true);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/detail";
  }

  @RequestMapping(value = "/detail/epin/{id}", method = RequestMethod.GET)
  public String detailEpin(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {

      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
      map.put("epinURL", true);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/detail_epin";
  }

  @RequestMapping(value = "/detail/export-epin/{id}", method = RequestMethod.GET)
  public String detailExportEpin(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {

      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
      map.put("exportEpinURL", true);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/detail_export_epin";
  }

  @RequestMapping(value = "/detail/topup/{id}", method = RequestMethod.GET)
  public String detailTopup(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {

      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
      map.put("topupURL", true);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/detail_topup";
  }

  @RequestMapping(value = "/detail/bill-payment/{id}", method = RequestMethod.GET)
  public String detailBillPayment(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {

      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
      map.put("topupURL", true);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/detail_bill_payment";
  }

  @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
  public String print(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {
      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/print/print_detail";
  }

  @RequestMapping(value = "/print/epin/{id}", method = RequestMethod.GET)
  public String printEpin(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {
      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/print/print_epin";
  }

  @RequestMapping(value = "/print/export-epin/{id}", method = RequestMethod.GET)
  public String printExportEpin(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {
      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/print/print_export_epin";
  }

  @RequestMapping(value = "/print/topup/{id}", method = RequestMethod.GET)
  public String printTopup(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {
      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/print/print_topup";
  }

  @RequestMapping(value = "/print/bill-payment/{id}", method = RequestMethod.GET)
  public String printBillPayment(@PathVariable("id") Long id, ModelMap map) throws Exception {
    try {
      ReconciliationDTO reconciliationDTO = getReconciliationDTO(id);
      putDetailToMap(reconciliationDTO, map);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/reconciliation/v3/detail/print/print_bill_payment";
  }

  @RequestMapping(value = "/export/{id}", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
    try {
      GeneralResponse<Object> res = reconciliationEndpoint.exportExcelReconciliation(id);
      AttachmentDTO attachmentDTO = Utils.responseToObject(res.getData(), AttachmentDTO.class);
      WebUtil.exportFile(response, attachmentDTO);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  public void putDetailToMap(ReconciliationDTO reconciliationDTO, ModelMap map) throws Exception {
    map.put("reconcil", reconciliationDTO);
    map.put("paymentPolicyName", getPaymentPolicyNameByEnum(reconciliationDTO.getPaymentPolicy().name()));
    map.put("paymentPolicyType", getPaymentPolicyTypeByEnum(reconciliationDTO.getPaymentPolicy().name()));
    map.put("dateStringByFromDateAndToDate", getDateStringByFromDateAndToDate(reconciliationDTO.getFromDate(), reconciliationDTO.getToDate()));
  }

  public ReconciliationDTO getReconciliationDTO(Long id) throws Exception {
    GeneralResponse<Object> res = reconciliationEndpoint.getReconciliation(id);
    ReconciliationDTO reconciliationDTO = Utils.responseToObject(res.getData(), ReconciliationDTO.class);
    return reconciliationDTO;
  }

  public List<Integer> getMonths() {
    List<Integer> months = new ArrayList<>();

    //LocalDate currentdate = LocalDate.now();
    //int currentMonth = currentdate.getMonth().getValue();
    //int previousMonth = currentMonth - 1;

    //if (previousMonth != 0) {
    //  months.add(previousMonth);
    //}

    //months.add(currentMonth);

    for (int i = 1; i < 13; i++) {
      //if (i != currentMonth && i != previousMonth) {
      months.add(i);
      //}
    }

    return months;
  }

  public String getQuery(String queryString) {
    String[] querys = queryString.split("&");

    String myQuery = "";
    for (String query : querys) {
      if (query.contains("paymentPolicieTab") || query.contains("stageTab")) {

      } else {
        myQuery += query + "&";
      }
    }
    return myQuery;
  }

  public String getPaymentPolicyNameByEnum(String paymentPolicyEnum) {
    switch (paymentPolicyEnum) {
      case "PAYMENT_POLICY_1_1":
        return "Đối soát thanh toán chu kỳ 1_1";
      case "PAYMENT_POLICY_7_1":
        return "Đối soát thanh toán chu kỳ 7_1";
      case "PAYMENT_POLICY_15_1":
        return "Đối soát thanh toán chu kỳ 15_1";
      case "PAYMENT_POLICY_30_1":
        return "Đối soát thanh toán chu kỳ 30_1";
    }
    return paymentPolicyEnum;
  }

  public String getPaymentPolicyTypeByEnum(String paymentPolicyEnum) {
    switch (paymentPolicyEnum) {
      case "PAYMENT_POLICY_1_1":
        return "1_1";
      case "PAYMENT_POLICY_7_1":
        return "7_1";
      case "PAYMENT_POLICY_15_1":
        return "15_1";
      case "PAYMENT_POLICY_30_1":
        return "30_1";
    }
    return paymentPolicyEnum;
  }

  public String getDateStringByFromDateAndToDate(Date fromDate, Date toDate) {

    String fromDtStr = getHourViaString(fromDate, "from");
    String toDtStr = getHourViaString(toDate, "to");

    return fromDtStr + " đến hết " + toDtStr;

  }

  public String getHourViaString(Date dt, String type) {
    String hh = String.valueOf(dt.getHours()).length() > 1 ? String.valueOf(dt.getHours()) : "0" + dt.getHours();
    String mm = String.valueOf(dt.getMinutes()).length() > 1 ? String.valueOf(dt.getMinutes()) : "0" + dt.getMinutes();
    String ss = String.valueOf(dt.getSeconds()).length() > 1 ? String.valueOf(dt.getSeconds()) : "0" + dt.getSeconds();

    String hourViaString = hh + " giờ " + mm + " phút " + ss + " giây ";
    String dateStr = DateUtil.dateToString("dd/MM/yyyy", dt);
    if (type.equals("from")) {
      return hourViaString + "ngày " + dateStr;
    } else {
      return "23 giờ 59 phút 59 giây " + "ngày " + dateStr;
    }
  }
}
