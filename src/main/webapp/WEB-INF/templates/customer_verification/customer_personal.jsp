<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycRequestStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-body" id="id-form-personal-information">
    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 control-label nowrap">Tên đầy đủ :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" name="kyc-displayName" id="kyc-displayName"
                           class="form-control w100" value="${customerVerifyKyc.identity.fullname}">
                </div>
            </div>

        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 control-label nowrap"><spring:message code="label.gender"/>
                    :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <select class="form-control w100" data-plugin-selectTwo id="kyc-gender"
                            name="kyc-gender" required>
                        <option value="0" ${customerVerifyKyc.identity.genderId eq 0 ? 'selected' : ''}>Nam</option>
                        <option value="1" ${customerVerifyKyc.identity.genderId eq 1 ? 'selected' : ''}>Nữ</option>
                        <option value="2" ${customerVerifyKyc.identity.genderId eq 2 ? 'selected' : ''}>Khác</option>
                    </select>
                    <%--<c:choose>--%>
                    <%--<c:when test="${customerVerifyKyc.identity.genderId == 0}">Nam</c:when>--%>
                    <%--<c:when test="${customerVerifyKyc.identity.genderId == 1}">Nữ</c:when>--%>
                    <%--<c:otherwise>Khác<c:out--%>
                    <%--value="${customerVerifyKyc.identity.genderId}"/></c:otherwise>--%>
                    <%--</c:choose>--%>
                    <%--<input type="hidden" id="hidden_gender" value="${customerVerifyKyc.identity.genderId}">--%>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">Ngày sinh :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" id="kyc-dateOfBirth" name="kyc-dateOfBirth"
                           data-inputmask="'alias': 'date'"
                           pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                           class="form-control w100" value="${customerVerifyKyc.textDateOfBirth()}">
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 control-label nowrap">ID Number :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" id="kyc_idNumber" name="kyc_idNumber"
                           data-inputmask="'mask': '9', 'repeat': 12, 'greedy' : false"
                           class="form-control w100" value=" ${id_number}">

                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">Date of issue
                    :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" id="kyc_dateOfIssue" name="kyc_dateOfIssue"
                           data-inputmask="'alias': 'date'"
                           pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                           class="form-control w100" value="${date_of_issue}">
                </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 control-label nowrap">Place of issue :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" id="kyc_placeOfIssue" name="kyc_placeOfIssue"
                           class="form-control w100" value="${place_of_issue}">
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">Permanent
                    Resident:</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <textarea id="kyc_permanentResident" name="kyc_permanentResident"
                              rows="4"
                              class="form-control w100">${customerVerifyKyc.permanentResident}</textarea>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 col-md-5 col-lg-4 control-label nowrap">City :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <select required class="form-control w100" data-plugin-selectTwo
                            id="kyc_current_province_selected"
                            name="kyc_current_province">
                        <option value="" disabled selected><spring:message
                                code="select.province.city"/></option>
                        <c:forEach items="${lstProvince}" var="item">
                            <option
                                    value="${item.code}" ${customer_verify_kyc_city eq item.code ? 'selected' : ''}>${item.name}</option>
                        </c:forEach>
                    </select>


                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <label class="col-sm-4 control-label nowrap">District :</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <select required class="form-control w100" data-plugin-selectTwo
                            id="kyc_current_district_selected"
                            name="kyc_current_district">
                        <option value="" disabled selected><spring:message
                                code="select.district"/></option>
                        <c:forEach items="${lstDistrict}" var="item">
                            <option
                                    value="${item.code}" ${customer_verify_kyc_district eq item.code ? 'selected' : ''}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" id="hidden_current_province_selected" value="">
            <input type="hidden" id="hidden_current_district_selected" value="">
        </div>
    </div>

    <%--<div class="row">--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-12 col-md-6">--%>

            <%--</div>--%>
            <%--<div class="col-sm-12 col-md-6 text-right">--%>
                <%--<input type="hidden" name="customerId" value="${customerId}">--%>
                <%--<input type="hidden" name="requestKycId" value="${requestKycId}">--%>
                <%--<c:set var="approvedKycRequest" value="<%=KycRequestStatus.APPROVED.getCode()%>"/>--%>
                <%--<c:set var="staffRejectKycRequest"--%>
                       <%--value="<%=KycRequestStatus.STAFF_REJECTED.getCode()%>"/>--%>
                <%--<c:if test="${customerVerifyKyc.requestStatusId != approvedKycRequest && customerVerifyKyc.requestStatusId != staffRejectKycRequest}">--%>
                    <%--<button class="btn btn-sm btn-success" type="button" id="btn_edt"--%>
                            <%--data-loading-text="<i class='fa fa-pencil'></i> ${waitting}"><i--%>
                            <%--class="fa fa-pencil"></i>&nbsp;Chỉnh sửa--%>
                    <%--</button>--%>
                    <%--<button type="button" class="btn btn-sm btn-primary" id="btn_update"--%>
                            <%--action="update"--%>
                            <%--data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">--%>
                        <%--<i class="fa fa-pencil"></i>&nbsp;Lưu--%>
                    <%--</button>--%>
                <%--</c:if>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>