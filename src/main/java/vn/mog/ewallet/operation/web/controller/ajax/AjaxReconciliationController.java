package vn.mog.ewallet.operation.web.controller.ajax;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ReconciliationDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ReconciliationElementDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.beans.ReconciliationRevertElementDTO;
import vn.mog.framework.common.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/ajax-reconciliation-controller")
public class AjaxReconciliationController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AjaxReconciliationController.class);

  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> updateReconciliation(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      String description = request.getParameter("description");
      String title = request.getParameter("title");
      String cycle = request.getParameter("cycle");
      String month = request.getParameter("month");
      String year = request.getParameter("year");
      String bolOfficial = request.getParameter("bolOfficial");

      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      List<ReconciliationElementDTO> reconciliationElementDTOS = new ArrayList<>();
      List<ReconciliationElementForm> reconciliationElementForms = new ArrayList<>();
      reconciliationDTO.getElementGroupByServiceType().forEach((serviceType, reconciliationElementDTOList) -> reconciliationElementDTOS.addAll(reconciliationElementDTOList));
      for (ReconciliationElementDTO elementDTO : reconciliationElementDTOS) {
        ReconciliationElementForm reconciliationElementForm = new ReconciliationElementForm();
        BeanUtils.copyProperties(reconciliationElementForm, elementDTO);
        reconciliationElementForms.add(reconciliationElementForm);
      }
      reconciliationForm.setReconciliationElements(new HashSet<>(reconciliationElementForms));

      reconciliationForm.setDescription(description != null && !description.isEmpty() ? description : null);
      reconciliationForm.setTitle(title != null && !title.isEmpty() ? title : null);
      reconciliationForm.setCycle(cycle != null && !cycle.isEmpty() ? Integer.valueOf(cycle) : null);
      reconciliationForm.setYear(year != null && !year.isEmpty() ? Integer.valueOf(year) : null);
      reconciliationForm.setMonth(month != null && !month.isEmpty() ? Integer.valueOf(month) : null);
      reconciliationForm.setBolOfficial(Boolean.valueOf(bolOfficial));

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> deleteReconciliation(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      res = reconciliationEndpoint.deleteReconciliation(id);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/update-element/{recolId}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> updateReconciliationElement(@PathVariable("recolId") Long id, @ModelAttribute ReconciliationElementDTO reconciliationElementDTO, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      List<ReconciliationElementDTO> reconciliationElementDTOS = new ArrayList<>();
      List<ReconciliationElementForm> reconciliationElementForms = new ArrayList<>();
      reconciliationDTO.getElementGroupByServiceType().forEach((serviceType, reconciliationElementDTOList) -> reconciliationElementDTOS.addAll(reconciliationElementDTOList));
      for (ReconciliationElementDTO elementDTO : reconciliationElementDTOS) {
        ReconciliationElementForm reconciliationElementForm = new ReconciliationElementForm();

        if (elementDTO.getId().longValue() == reconciliationElementDTO.getId().longValue()) {
          BeanUtils.copyProperties(reconciliationElementForm, reconciliationElementDTO);
        } else {
          BeanUtils.copyProperties(reconciliationElementForm, elementDTO);
        }

        reconciliationElementForms.add(reconciliationElementForm);
      }
      reconciliationForm.setReconciliationElements(new HashSet<>(reconciliationElementForms));

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/update-revert-element/{recolId}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> updateReconciliationRevertElement(@PathVariable("recolId") Long id, @ModelAttribute ReconciliationRevertElementDTO ReconciliationRevertElementDTO, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      Set<ReconciliationRevertElementDTO> reconciliationRevertElementDTOS = reconciliationDTO.getReconciliationRevertElements();
      Set<ReconciliationRevertElementForm> reconciliationRevertElementForms = new HashSet<>();
      for(ReconciliationRevertElementDTO elementDTO: reconciliationRevertElementDTOS) {
        ReconciliationRevertElementForm reconciliationRevertElementForm = new ReconciliationRevertElementForm();
        if(elementDTO.getId().longValue() == ReconciliationRevertElementDTO.getId().longValue()) {
          BeanUtils.copyProperties(reconciliationRevertElementForm, ReconciliationRevertElementDTO);
        } else {
          BeanUtils.copyProperties(reconciliationRevertElementForm, elementDTO);
        }
        reconciliationRevertElementForms.add(reconciliationRevertElementForm);
      }
      reconciliationForm.setReconciliationRevertElements(reconciliationRevertElementForms);

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/create-element/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> createReconciliationElement(@PathVariable("id") Long id, @ModelAttribute ReconciliationElementForm reconciliationElementForm, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      List<ReconciliationElementDTO> reconciliationElementDTOS = new ArrayList<>();
      List<ReconciliationElementForm> reconciliationElementForms = new ArrayList<>();
      reconciliationDTO.getElementGroupByServiceType().forEach((serviceType, reconciliationElementDTOList) -> reconciliationElementDTOS.addAll(reconciliationElementDTOList));
      for (ReconciliationElementDTO elementDTO : reconciliationElementDTOS) {
        ReconciliationElementForm elementForm = new ReconciliationElementForm();
        BeanUtils.copyProperties(elementForm, elementDTO);
        reconciliationElementForms.add(elementForm);
      }
      reconciliationElementForms.add(reconciliationElementForm);
      reconciliationForm.setReconciliationElements(new HashSet<>(reconciliationElementForms));

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/create-revert-element/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> createReconciliationRevertElement(@PathVariable("id") Long id, @ModelAttribute ReconciliationRevertElementForm ReconciliationRevertElementForm, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      Set<ReconciliationRevertElementDTO> reconciliationRevertElementDTOS = reconciliationDTO.getReconciliationRevertElements();
      Set<ReconciliationRevertElementForm> reconciliationRevertElementForms = new HashSet<>();
      for(ReconciliationRevertElementDTO reconciliationRevertElementDTO: reconciliationRevertElementDTOS) {
          ReconciliationRevertElementForm reconciliationRevertElementForm = new ReconciliationRevertElementForm();
          BeanUtils.copyProperties(reconciliationRevertElementForm, reconciliationRevertElementDTO);
          reconciliationRevertElementForms.add(reconciliationRevertElementForm);
      }

      reconciliationRevertElementForms.add(ReconciliationRevertElementForm);
      reconciliationForm.setReconciliationRevertElements(reconciliationRevertElementForms);

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/delete-element/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> deleteReconciliationElement(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      String elementId = request.getParameter("elementId");

      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      List<ReconciliationElementDTO> reconciliationElementDTOS = new ArrayList<>();
      List<ReconciliationElementForm> reconciliationElementForms = new ArrayList<>();
      reconciliationDTO.getElementGroupByServiceType().forEach((serviceType, reconciliationElementDTOList) -> reconciliationElementDTOS.addAll(reconciliationElementDTOList));
      for (ReconciliationElementDTO elementDTO : reconciliationElementDTOS) {
        if (elementDTO.getId().longValue() != Long.valueOf(elementId).longValue()) {
          ReconciliationElementForm reconciliationElementForm = new ReconciliationElementForm();
          BeanUtils.copyProperties(reconciliationElementForm, elementDTO);
          reconciliationElementForms.add(reconciliationElementForm);
        }
      }
      reconciliationForm.setReconciliationElements(new HashSet<>(reconciliationElementForms));

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/delete-revert-element/{id}", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> deleteReconciliationRevertElement(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      String elementId = request.getParameter("elementId");

      GeneralResponse<Object> reconciliationData = reconciliationEndpoint.getReconciliation(id);
      ReconciliationDTO reconciliationDTO = Utils.responseToObject(reconciliationData.getData(), ReconciliationDTO.class);

      ReconciliationForm reconciliationForm = new ReconciliationForm();
      BeanUtils.copyProperties(reconciliationForm, reconciliationDTO);

      Set<ReconciliationRevertElementDTO> reconciliationRevertElementDTOS = reconciliationDTO.getReconciliationRevertElements();
      Set<ReconciliationRevertElementForm> reconciliationRevertElementForms = new HashSet<>();
      for(ReconciliationRevertElementDTO reconciliationRevertElementDTO: reconciliationRevertElementDTOS){
        if(reconciliationRevertElementDTO.getId().longValue() != Long.valueOf(elementId).longValue()){
          ReconciliationRevertElementForm reconciliationRevertElementForm = new ReconciliationRevertElementForm();
          BeanUtils.copyProperties(reconciliationRevertElementForm, reconciliationRevertElementDTO);
          reconciliationRevertElementForms.add(reconciliationRevertElementForm);
        }
      }

      reconciliationForm.setReconciliationRevertElements(reconciliationRevertElementForms);

      res = reconciliationEndpoint.updateReconciliation(reconciliationForm);
      ReconciliationDTO result = Utils.responseToObject(res.getData(), ReconciliationDTO.class);

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(value = "/generate-reconciliation/by-ops", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> generateReconciliationByOps(HttpServletRequest request) throws Exception {
    GeneralResponse<Object> res = null;
    try {
      String merchantId = request.getParameter("merchantId");
      String cycleType = request.getParameter("cycleType");
      String yr = request.getParameter("yr");
      String mnth = request.getParameter("mnth");
      String cyl = request.getParameter("cyl");

      GenerateReconciliationRequest generateReconciliationRequest = new GenerateReconciliationRequest();
      generateReconciliationRequest.setCustomerId(StringUtils.isNotBlank(merchantId) ? Long.valueOf(merchantId): null);
      generateReconciliationRequest.setPaymentPolicy(StringUtils.isNotBlank(cycleType)? PaymentPolicyEnum.valueOf(cycleType): null);
      generateReconciliationRequest.setYear(StringUtils.isNotBlank(yr)? Integer.valueOf(yr): null);
      generateReconciliationRequest.setMonth(StringUtils.isNotBlank(mnth)? Integer.valueOf(mnth): null);
      generateReconciliationRequest.setCycle(StringUtils.isNotBlank(cyl)? Integer.valueOf(cyl): null);

      res = reconciliationEndpoint.generateReconciliationByOps(generateReconciliationRequest);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
      return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(res);
  }
}
