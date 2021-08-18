package vn.mog.ewallet.operation.web.controller.customer.sale_switching.manage;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.AssignAgentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system.FindAgentRequest;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@Controller
@RequestMapping(value = "/customer/sale_switching/manage")
public class SaleSwitchingManageController extends AbstractController {
  private static final Logger LOG = LogManager.getLogger(SaleSwitchingManageController.class);
  public static final String URI_CUSTOMER_SALE_SWITCHING_MANAGE = "/customer/sale_switching/manage";
  public static final String URI_CUSTOMER_SALE_SWITCHING_MANAGE_LIST = "/customer/sale_switching/manage/list";

  public static final String PATH_CUSTOMER_SALE_SWITCHING_MANAGE_LIST = "/customer_sale_switching/list";

  @GetMapping(value = "/list")
  public String list(HttpServletRequest request, ModelMap model) throws FrontEndException {
    try{
      String msisdn = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      String page = StringUtils.trimToEmpty(request.getParameter("page"));
      int limit = 50;
      if(msisdn != null && !msisdn.isEmpty()){
        FindAgentRequest findAgentRequest = new FindAgentRequest();
        findAgentRequest.setMsisdn(msisdn);
        if (page != null && !page.isEmpty()) {
          findAgentRequest.setOffset(Integer.parseInt(page) * limit);
          findAgentRequest.setLimit(limit);
        } else {
          findAgentRequest.setOffset(0);
          findAgentRequest.setLimit(limit);
        }
        FindUmgrCustomerResponse findCustomerResponse = customerEndpoint.findAgentsByMsisdnCustomerSystem(findAgentRequest);
        if(findCustomerResponse == null){
          List<Customer> customers = new ArrayList<>();
          model.put("customers", customers);
        } else {
          model.put("totalPages", findCustomerResponse.getTotal() / limit);
          model.put("customers", findCustomerResponse.getCustomers());
        }
      }
    } catch (Exception e){
      LOG.error(e.getMessage(), e);
    }
    return PATH_CUSTOMER_SALE_SWITCHING_MANAGE_LIST;
  }

  @PostMapping(value = "/switching-manage")
  public ResponseEntity<?> switchingManage(ModelMap model, HttpServletRequest request) throws FrontEndException {
    AssignAgentResponse assignAgentResponse = new AssignAgentResponse();
    String[] agentIds = request.getParameterValues("agentIds[]");
    String saleId = request.getParameter("saleId");

    if(agentIds == null || agentIds.length == 0){
      assignAgentResponse.setStatus(new Status(400, "Dữ liệu không hợp lệ"));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(assignAgentResponse);
    }
    if(saleId == null || saleId.isEmpty()){
      assignAgentResponse.setStatus(new Status(400, "Bạn chưa chọn người nhận quản lý khách hàng"));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(assignAgentResponse);
    }

    List<Long> agents = new ArrayList<>();
    for(String agent: agentIds){
      agents.add(Long.valueOf(agent));
    }

    try{
      AssignAgentRequest assignAgentRequest = new AssignAgentRequest();
      assignAgentRequest.setAgentIds(agents);
      assignAgentRequest.setSaleId(Long.valueOf(saleId));
      assignAgentResponse = customerEndpoint.assignAgentsCustomerSystem(assignAgentRequest);
      if(assignAgentResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(assignAgentResponse);
      }
    } catch (Exception e){
      LOG.error(e.getMessage(), e);
      assignAgentResponse.setStatus(new Status(400, e.getMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(assignAgentResponse);
    }
    return ResponseEntity.ok(assignAgentResponse);

  }

}
