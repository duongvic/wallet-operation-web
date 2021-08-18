package vn.mog.ewallet.operation.web.controller.customer.request.kyc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SenderType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.AttachmentContentType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerVerifyKycRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Identity;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycProcessAction;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestChangeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestGetDetailResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer.VerifyKycRequestStatusUpdateResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.FindLocationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.Utils;

@Controller
@RequestMapping(value = "/customer/verification")
public class CustomerRequestKycController extends AbstractAccountController {

  private static final Logger LOG = LogManager.getLogger(CustomerRequestKycController.class);
  public static final String CUSTOMER_VERIFY_CONTROLLER = "/customer/verification";
  public static final String CUSTOMER_VERIFY_LIST = CUSTOMER_VERIFY_CONTROLLER + "/list";

  private static final String CUSTOMER_OBJ_ATTRIBUTE = "CUSTOMER_OBJ";
  private static final String DATE_TIME_FORMAT = "dd/MM/yyyy";

  private static final String SESSION_MESSAGE_NOTIFY = "message_notify";

  @GetMapping(value = "/list")
  public String list(HttpServletRequest request, ModelMap map) throws FrontEndException {
    List<CustomerVerifyKycRequest> customerVerifyRequests = new ArrayList<>();
    try {
      int total = 0;
      int offset = 0;
      int limit = 20;
      String paramText = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String[] kycRequestStatus = request.getParameterValues("kycRequestStatus");
      String kycType = request.getParameter("kycType");

      VerifyKycRequestFindRequest verifyKycReq = new VerifyKycRequestFindRequest();

      Date[] dates = parseDateRange(paramDateRange, true);
      verifyKycReq.setFromDate(dates[0]);
      verifyKycReq.setToDate(dates[1]);

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      if (kycRequestStatus != null && kycRequestStatus.length > 0 && StringUtils
          .isNotEmpty(kycRequestStatus[0])) {
        verifyKycReq.setKycRequestStatusId(NumberUtil.convertListToInt(kycRequestStatus));
        map.put("kycRequestStatus", NumberUtil.convertListToInt(kycRequestStatus));
      }
      verifyKycReq.setMsisdn(StringUtils.isEmpty(paramText) ? null : paramText);
      if (kycType != null && !kycType.isEmpty()) {
        verifyKycReq.setKycTypes(Collections.singletonList(KycType.valueOf(kycType)));
      }
      verifyKycReq.setOffSet(offset);
      verifyKycReq.setLimit(limit);


      VerifyKycRequestFindResponse verifyKycRes = customerKycEndpoint
          .findVerifyKycRequest(verifyKycReq);
      if (verifyKycRes != null && verifyKycRes.getStatus().getCode() == 0) {
        customerVerifyRequests = verifyKycRes.getCustomerVerifyRequests();
        total = verifyKycRes.getTotal().intValue();
      }


      map.put("list", customerVerifyRequests);

      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("total", total);

    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }

    return "/customer_verification/list";
  }

  @GetMapping(value = "/detail/{customerId}/{requestKycId}")
  public String detailVerification(HttpServletRequest request,
                                   @PathVariable(value = "customerId") Long customerId,
                                   @PathVariable(value = "requestKycId") Long requestKycId,
                                   ModelMap model)
      throws Exception {

    //get province
    model.put("lstProvince", getProvince());

    GetFullCustomerRequest fullCustomerReq = new GetFullCustomerRequest(customerId);
    GetFullCustomerResponse fullCustomerRes = umgrAccountEndpoint.getFullCustomer(fullCustomerReq);
    Customer customer = fullCustomerRes.getCustomer();
    model.put("customer", customer);
    SessionUtil.setAttribute(CUSTOMER_OBJ_ATTRIBUTE, customer);

    VerifyKycRequestGetDetailRequest vkrReq = new VerifyKycRequestGetDetailRequest(requestKycId);
    VerifyKycRequestGetDetailResponse vkrRes = customerKycEndpoint
        .getVerifyKycRequestGetDetail(vkrReq);
    CustomerVerifyKycRequest customerVerifyKycRequest = vkrRes.getCustomerVerifyKycRequest();
    if (customerVerifyKycRequest.getKycType() != null && customerVerifyKycRequest.getKycType()
        .name().equals(
            KycType.ENTERPRISE.name())) {
      model.put("ENTERPRISE", true);
    }
    model.put("customerVerifyKyc", customerVerifyKycRequest);

    model.put("customerId", customerId);
    model.put("requestKycId", requestKycId);

    Identity identity = customerVerifyKycRequest.getIdentity();
    model.put("id_number", identity.getIdentity());
    model.put("place_of_issue", identity.getIssuePlace());
    model.put("date_of_issue", identity.textDateOfIssuedDate());

    //if (identity.getIssuePlace() != null) {
    //FindLocationRequest placeReq = new FindLocationRequest(Collections.singletonList(identity.getIssuePlace()));
    //FindLocationResponse placeRes = authSystemEndpoint.getLocation(placeReq);
    //List<Location> locations = placeRes.getLocations();
    //model.put("place_of_issue", locations.isEmpty() ? identity.textDateOfIssuedDate() : locations.get(0).getName());
    //}
    String region = customerVerifyKycRequest.getProvince();
    if (region != null) {
      FindLocationRequest regionReq = new FindLocationRequest(Collections.singletonList(region));
      FindLocationResponse regionRes = authSystemEndpoint.getLocation(regionReq);
      List<Location> regionlocations = regionRes.getLocations();
      model.put("customer_verify_kyc_city",
          regionlocations.isEmpty() ? customerVerifyKycRequest.getProvince()
              : regionlocations.get(0).getCode());
    }
    model.put("lstDistrict", getDistrict(region));

    FindLocationRequest locationDistrictReq = new FindLocationRequest(
        Collections.singletonList(customerVerifyKycRequest.getDistrict()));
    FindLocationResponse locationDistrictRes = authSystemEndpoint.getLocation(locationDistrictReq);
    List<Location> districtLocations = locationDistrictRes.getLocations();
    model.put("customer_verify_kyc_district",
        districtLocations.isEmpty() ? customerVerifyKycRequest.getDistrict()
            : districtLocations.get(0).getCode());

    FindLocationRequest locationProvinceReq = new FindLocationRequest(
        Collections.singletonList(customer.getProvince()));
    FindLocationResponse locationProvinceRes = authSystemEndpoint.getLocation(locationProvinceReq);
    List<Location> provincelocations = locationProvinceRes.getLocations();
    model.put("customer_province",
        provincelocations.isEmpty() ? customer.getProvince() : provincelocations.get(0).getName());

    String jobOccupation = customer.getJobOccupation();
    if (jobOccupation != null) {
      FindLocationRequest jobOccupationReq = new FindLocationRequest(
          Collections.singletonList(jobOccupation));
      FindLocationResponse jobOccupationRes = authSystemEndpoint.getLocation(jobOccupationReq);
      List<Location> jobOccupationlocations = jobOccupationRes.getLocations();
      model.put("customer_jobOccupation",
          jobOccupationlocations.isEmpty() ? customer.getJobOccupation()
              : jobOccupationlocations.get(0).getName());
    }

    String jobPosition = customer.getJobPosition();
    if (jobPosition != null) {
      FindLocationRequest jobPositionReq = new FindLocationRequest(
          Collections.singletonList(jobPosition));
      FindLocationResponse jobPositionRes = authSystemEndpoint.getLocation(jobPositionReq);
      List<Location> jobPositionlocations = jobPositionRes.getLocations();
      model.put("customer_jobPosition", jobPositionlocations.isEmpty() ? customer.getJobPosition()
          : jobPositionlocations.get(0).getName());
    }

    MessageNotify message = (MessageNotify) SessionUtil.getAttribute(SESSION_MESSAGE_NOTIFY);
    if (message != null) {
      model.put(MessageNotify.codeErr, message.getCode());
      model.put(MessageNotify.mesErr, message.getMessage());
      SessionUtil.removeAttribute(SESSION_MESSAGE_NOTIFY);
    }

    String frontImage = "data:" + customerVerifyKycRequest.getFrontContentType() + ";base64,"
        + customerVerifyKycRequest.getFrontContent();
    model.put("frontImage", frontImage);

    String backImage = "data:" + customerVerifyKycRequest.getBackContentType() + ";base64,"
        + customerVerifyKycRequest.getBackContent();
    model.put("backImage", backImage);

    String selfieImage = "data:" + customerVerifyKycRequest.getSelfieContentType() + ";base64,"
        + customerVerifyKycRequest.getSelfieContent();
    model.put("selfieImage", selfieImage);

    String identityImgContent =
        "data:" + customerVerifyKycRequest.getIdentityImgContentType() + ";base64,"
            + customerVerifyKycRequest.getIdentityImgContent();
    model.put("identityImgContent", identityImgContent);
    return "/customer_verification/verification_detail";
  }

  @PostMapping(value = "/submit")
  public String submitVerification(HttpServletRequest request) throws Exception {
    long requestKycId = NumberUtil.convertToLong(request.getParameter("requestKycId"));
    long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    String kycType = request.getParameter("kycType");
    try {
      if ("update".equals(action)) {

        if (StringUtils.isNotBlank(kycType) && kycType.equals(KycType.ENTERPRISE.name())) {
          updateEnterprise(requestKycId, customerId, request);
        } else {
          updateInfoKYC(requestKycId, customerId, request);
        }

      } else {
        VerifyKycRequestStatusUpdateRequest vkycReq = new VerifyKycRequestStatusUpdateRequest();
        vkycReq.setRequestId(requestKycId);
        vkycReq.setRemark(remark);
        vkycReq.setAction("reject".equals(action) ? KycProcessAction.REJECT.getName()
            : ("approve".equals(action) ? KycProcessAction.APPROVE.getName() : null));

        VerifyKycRequestStatusUpdateResponse vkycRes = customerKycEndpoint
            .updateVerifyKycRequestStatus(vkycReq);
        SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY,
            new MessageNotify(vkycRes.getStatus().getCode(), vkycRes.getStatus().getValue()));
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
      return "redirect:/customer/verification/detail/" + customerId + "/" + requestKycId
          + "?codeErr=1&mesErr=" + ex.getMessage();
    }

    return "redirect:/customer/verification/detail/" + customerId + "/" + requestKycId;
  }


  private void updateInfoKYC(Long requestKycId, Long customerId, HttpServletRequest request)
      throws Exception {

    String genderId = request.getParameter("kyc-gender");
    String kycDateOfBirth = request.getParameter("kyc-dateOfBirth");
    String kycIdNumer = request.getParameter("kyc_idNumber");
    String kycDateOfIssue = request.getParameter("kyc_dateOfIssue");
    String kycPlaceOfIssue = request.getParameter("kyc_placeOfIssue");
    String kycDisplayName = request.getParameter("kyc-displayName");
    String kycPpermanentResident = request.getParameter("kyc_permanentResident");
    String kycCurrentProvince = request.getParameter("kyc_current_province");
    String kycCurrentDistrict = request.getParameter("kyc_current_district");
    String senderType = request.getParameter("senderType");

    VerifyKycRequestGetDetailRequest vkrReq = new VerifyKycRequestGetDetailRequest(requestKycId);
    VerifyKycRequestGetDetailResponse vkrRes = customerKycEndpoint
        .getVerifyKycRequestGetDetail(vkrReq);

    Identity identity = vkrRes.getCustomerVerifyKycRequest().getIdentity();
    identity.setId(requestKycId);
    identity.setCustomerId(customerId);
    identity.setIdentity(kycIdNumer);
    if (!Objects.isNull(kycDateOfIssue) && !kycDateOfIssue.isEmpty())
      identity.setDateIssued(new SimpleDateFormat(DATE_TIME_FORMAT).parse(kycDateOfIssue));
    identity.setIssuePlace(kycPlaceOfIssue);
    identity.setFullname(kycDisplayName);
    identity.setGenderId(Integer.valueOf(genderId));

    CustomerVerifyKycRequest customerVerifyKycRequest = vkrRes.getCustomerVerifyKycRequest();

    VerifyKycRequestChangeRequest vkycChange = new VerifyKycRequestChangeRequest();
    VerifyKycRequestChangeResponse vkycChangeRespon;
    try {
      if (!Objects.isNull(kycDateOfBirth) && !kycDateOfBirth.isEmpty())
        customerVerifyKycRequest
            .setDateOfBirth(new SimpleDateFormat(DATE_TIME_FORMAT).parse(kycDateOfBirth));
      if (!Objects.isNull(kycPpermanentResident) && !kycPpermanentResident.isEmpty())
        customerVerifyKycRequest.setPermanentResident(kycPpermanentResident);
      customerVerifyKycRequest.setProvince(kycCurrentProvince);
      customerVerifyKycRequest.setDistrict(kycCurrentDistrict);
      customerVerifyKycRequest.setId(requestKycId);
      //ảnh

      MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
      List<MultipartFile> multipartsF = multiPartRequest.getFiles("front-image");
      if (multipartsF != null) {
        for (MultipartFile multipart : multipartsF) {
          if (multipart.getOriginalFilename() != null) {
            customerVerifyKycRequest.setFrontName(multipart.getOriginalFilename());
            customerVerifyKycRequest.setFrontContentType(multipart.getContentType());
            customerVerifyKycRequest.setFrontContent(encodeRotateImage(multipart));
          }
        }
      }

      List<MultipartFile> multipartsB = multiPartRequest.getFiles("back-image");
      if (multipartsB != null) {
        for (MultipartFile multipart : multipartsB) {
          if (multipart.getOriginalFilename() != null) {
            customerVerifyKycRequest.setBackName(multipart.getOriginalFilename());
            customerVerifyKycRequest.setBackContentType(multipart.getContentType());
            customerVerifyKycRequest.setBackContent(encodeRotateImage(multipart));
          }
        }
      }

      List<MultipartFile> multipartsS = multiPartRequest.getFiles("selfie-image");
      if (multipartsS != null) {
        for (MultipartFile multipart : multipartsS) {
          if (multipart.getOriginalFilename() != null) {
            customerVerifyKycRequest.setSelfieName(multipart.getOriginalFilename());
            customerVerifyKycRequest.setSelfieContentType(multipart.getContentType());
            customerVerifyKycRequest.setSelfieContent(encodeRotateImage(multipart));
          }
        }
      }

//      if (frontImage != null && !StringUtils.EMPTY.equals(frontImage.getOriginalFilename())) {
//        customerVerifyKycRequest.setFrontName(frontImage.getOriginalFilename());
//        customerVerifyKycRequest.setFrontContentType(frontImage.getContentType());
//        customerVerifyKycRequest.setFrontContent(encodeRotateImage(frontImage));
//      }

//      if (backImage != null && !StringUtils.EMPTY.equals(backImage.getOriginalFilename())) {
//        customerVerifyKycRequest.setBackName(backImage.getOriginalFilename());
//        customerVerifyKycRequest.setBackContentType(backImage.getContentType());
//        customerVerifyKycRequest.setBackContent(encodeRotateImage(backImage));
//      }

//      if (selfieImage != null && !StringUtils.EMPTY.equals(selfieImage.getOriginalFilename())) {
//        customerVerifyKycRequest.setSelfieName(selfieImage.getOriginalFilename());
//        customerVerifyKycRequest.setSelfieContentType(selfieImage.getContentType());
//        customerVerifyKycRequest.setSelfieContent(encodeRotateImage(selfieImage));
//      }

      customerVerifyKycRequest.setIdentity(identity);
      if (senderType != null && !senderType.isEmpty())
        customerVerifyKycRequest.setSenderType(SenderType.valueOf(senderType));
      vkycChange.setCustomerVerifyRequest(customerVerifyKycRequest);
      vkycChangeRespon = customerKycEndpoint.updateKYC(vkycChange);

      SessionUtil.setAttribute(SESSION_MESSAGE_NOTIFY,
          new MessageNotify(vkycChangeRespon.getStatus().getCode(),
              vkycChangeRespon.getStatus().getValue()));
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  public void updateEnterprise(Long requestKycId, Long customerId, HttpServletRequest request)
      throws Exception {
    try {
      String companyName = StringUtils.trimToEmpty(request.getParameter("companyName"));
      String representativeName = request.getParameter("representativeName");
      String crn = request.getParameter("crn");
      String representativePosition = StringUtils
          .trimToEmpty(request.getParameter("representativePosition"));
      String crnDateIssued = StringUtils.trimToEmpty(request.getParameter("crnDateIssued"));
      String crnIssuePlace = StringUtils.trimToEmpty(request.getParameter("crnIssuePlace"));
      MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) request;
      List<MultipartFile> multipartsS = multiPartRequest.getFiles("selfie-image");

      VerifyKycRequestChangeRequest verifyKycRequestChangeRequest = new VerifyKycRequestChangeRequest();
      CustomerVerifyKycRequest customerVerifyKycRequest = new CustomerVerifyKycRequest();

      customerVerifyKycRequest.setId(requestKycId);
      customerVerifyKycRequest.setCustomerId(customerId);
      customerVerifyKycRequest.setKycType(KycType.ENTERPRISE);
      if (multipartsS != null) {
        for (MultipartFile multipart : multipartsS) {
          if (multipart.getOriginalFilename() != null) {
            customerVerifyKycRequest.setIdentityImgName(multipart.getOriginalFilename());
            customerVerifyKycRequest.setIdentityImgContentType(AttachmentContentType.IMAGE);
            customerVerifyKycRequest.setIdentityImgContent(encodeRotateImage(multipart));
          }
        }
      }

      if (companyName != null && !companyName.isEmpty()) {
        customerVerifyKycRequest.setCompanyName(companyName);
      } else {
        throw new Exception("require company name!");
      }

      if (representativeName != null && !representativeName.isEmpty()) {
        customerVerifyKycRequest.setRepresentativeName(representativeName);
      } else {
        throw new Exception("require representative name!");
      }

      if (crn != null && !crn.isEmpty()) {
        customerVerifyKycRequest.setCrn(crn);
      } else {
        throw new Exception("require crn!");
      }

      if (representativePosition != null && !representativePosition.isEmpty()) {
        customerVerifyKycRequest.setRepresentativePosition(representativePosition);
      } else {
        throw new Exception("require representative position!");
      }

      if (crnDateIssued != null && !crnDateIssued.isEmpty()) {
        customerVerifyKycRequest
            .setCrnDateIssued(new SimpleDateFormat(DATE_TIME_FORMAT).parse(crnDateIssued));
      } else {
        throw new Exception("require crn date issued!");
      }

      if (crnIssuePlace != null && !crnIssuePlace.isEmpty()) {
        customerVerifyKycRequest.setCrnIssuePlace(crnIssuePlace);
      } else {
        throw new Exception("require crn issue place");
      }

      verifyKycRequestChangeRequest.setCustomerVerifyRequest(customerVerifyKycRequest);

      VerifyKycRequestChangeResponse verifyKycRequestChangeResponse
          = customerKycEndpoint.updateEnterpriseKYC(verifyKycRequestChangeRequest);

      if (verifyKycRequestChangeResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.info(Utils.objectToJson(verifyKycRequestChangeResponse));
      }
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    }
  }
}
