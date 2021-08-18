<%@ page import="vn.mog.ewallet.operation.web.contract.TypicalImage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
  <h4 style="margin-bottom: 1.5rem"><spring:message code="account.identity.info"/></h4>
  <div class="panel-tools hidden">
    <a class="mb-xs mt-xs btn btn-success" style="line-height: 28px" href="#"><i class="fa fa-plus"></i>&nbsp;</a>
  </div>
</div>

<div class="panel-body">

  <div class="row">
    <!-- identity info -->
    <div class="col-sm-6">
      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <input type="hidden" id="identityId" name="identityId" value="${identityDataForm.id}">
        <input type="hidden" id="identityCusId" name="identityCusId" value="${identityDataForm.customerId}">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.type"/>&nbsp;*</label>
        <div class="col-sm-8 double-input">
          <select class="form-control w100" data-plugin-selectTwo id="identity-type" name="identity-type"
                  style="width: 100%">
            <option value="1" ${identityDataForm.identityType eq 1 ? 'selected' : ''}>Căn cước công dân</option>
            <!-- Citizenship Card -->
            <option value="7" ${identityDataForm.identityType eq 7 ? 'selected' : ''}>Hộ chiếu</option><!-- Passport -->
            <option value="2" ${identityDataForm.identityType eq 2 ? 'selected' : ''}>Chứng minh thư</option>
            <!-- Identity Card -->
          </select>
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.number"/>&nbsp;*</label>
        <div class="col-sm-8 double-input">
          <input type="text" name="identity-number" id="identity-number" class="form-control w100"
                 value="${identityDataForm.identityNumber}" placeholder="Identity number" style="width: 100%;">
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.issue.date"/>&nbsp;*</label>
        <div class="col-sm-8 double-input">
          <input class="form-control" id="issue-date" data-inputmask="'alias': 'date'" name="issue-date"
                 placeholder="<spring:message code="account.identity.issue.date" />"
                 title="<spring:message code="account.identity.issue.date" />"
                 value="${identityDataForm.textIssueDate()}"
                 pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}">
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message
            code="account.identity.expire.date"/>&nbsp;*</label>
        <div class="col-sm-8 double-input">
          <input class="form-control" id="expire-date" data-inputmask="'alias': 'date'" name="expire-date"
                 placeholder="<spring:message code="account.identity.expire.date" />"
                 title="<spring:message code="account.identity.expire.date" />"
                 value="${identityDataForm.textExpireDate()}"
                 pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}">
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message
            code="account.identity.placeof.issue"/>&nbsp;*</label>
        <div class="col-sm-8 double-input">
          <input type="text" name="place-of-issue" id="place-of-issue" class="form-control"
                 value="${identityDataForm.placeOfIssue}" placeholder="Place of issue" style="width: 100%;">
        </div>
      </div>
    </div>

    <!-- identity images -->
    <div class="col-sm-6">
      <div class="col-sm-12 form-group">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.images"/></label>
        <div class="col-sm-8 double-input">
          <div class="row">
            <c:set var="imageDefault"><c:url value="/assets/images/ava-blank.png"/></c:set>
            <style type="text/css">
              .img-identity {
                margin-bottom: 5px;
                height: 140px;
                width: 100%
              }

              img:hover {
                cursor: pointer;
              }

              .gb_La {
                background: rgba(134, 129, 129, 0.54);
                bottom: 5px;
                color: #fff;
                font-size: 9px;
                font-weight: bold;
                left: 15px;
                line-height: 9px;
                position: absolute;
                padding: 7px 0;
                text-align: center;
                width: 229px;
              }

              .gb_La:hover {
                cursor: pointer;
              }
            </style>
            <div class="col-sm-6">
              <div id="div-front-image">
                <img class="img-identity" alt=""
                     id="front-image"
                     src="${identityDataForm.getSrcFrontFaceImage() eq null ? imageDefault : identityDataForm.getSrcFrontFaceImage()}"
                     title="${(isCustomerAgent || isMerchantUser) ? 'Cập nhập ảnh mặt trước' : ''}">
              </div>
              <c:if test="${isCustomerAgent || isMerchantUser}">
                <span class="gb_La" id="lb-front-image">Cập nhập ảnh mặt trước</span>
                <input type="hidden" id="identityIds_0" name="identityIds[0]"
                       value="${identityDataForm.frontFaceImage.id}">
                <input type="file" id="input-front-image" name="files[0]" accept="image/*" class="hidden"
                       data-image-type="<%=TypicalImage.FRONT_FACE.code%>">
                <br><span id="front-avatar-status" style=""><%--Loading new avatar...--%></span>
              </c:if>

            </div>

            <div class="col-sm-6">
              <div id="div-back-image">
                <img class="img-identity" alt=""
                     id="back-image"
                     src="${identityDataForm.getSrcBackFaceImage() eq null ? imageDefault : identityDataForm.getSrcBackFaceImage()}"
                     title="${(isCustomerAgent || isMerchantUser) ? 'Cập nhập ảnh mặt sau' : ''}">
              </div>
              <c:if test="${isCustomerAgent || isMerchantUser}">
                <span class="gb_La" id="lb-back-image">Cập nhập ảnh mặt sau</span>
                <input type="hidden" id="identityIds_1" name="identityIds[1]"
                       value="${identityDataForm.backFaceImage.id}">
                <input type="file" id="input-back-image" name="files[1]" accept="image/*" class="hidden"
                       data-image-type="<%=TypicalImage.BACK_FACE.code%>">
                <br><span id="back-avatar-status" style=""><%--Loading new avatar...--%></span>
              </c:if>
            </div>

            <div class="col-sm-6">
              <div id="div-selfie-image">
                <img class="img-identity" alt=""
                     id="selfie-image"
                     src="${identityDataForm.getSrcSelfieFaceImage() eq null ? imageDefault : identityDataForm.getSrcSelfieFaceImage()}"
                     title="${(isCustomerAgent || isMerchantUser) ? 'Cập nhập ảnh selfie' : ''}">
              </div>
              <c:if test="${isCustomerAgent || isMerchantUser}">
                <span class="gb_La" id="lb-selfie-image">Cập nhập ảnh selfie</span>
                <input type="hidden" id="identityIds_2" name="identityIds[2]"
                       value="${identityDataForm.selfieImage.id}">
                <input type="file" id="input-selfie-image" name="files[2]" accept="image/*" class="hidden"
                       data-image-type="<%=TypicalImage.SELFIE.code%>">
                <br><span id="selfie-avatar-status" style=""><%--Loading new avatar...--%></span>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <%-- Thông tin doanh nghiệp--%>
  <div class="row">
    <div class="col-sm-6">
      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <input type="hidden" id="identityIdEnterprise" name="identityIdEnterprise" value="${identityDataForm.id}">
        <input type="hidden" id="identityCusIdEnterprise" name="identityCusIdEnterprise"
               value="${identityDataForm.customerId}">
        <label class="col-sm-4 control-label nowrap"><spring:message code="label.company.name"/>&nbsp;*</label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <input type="text" class="form-control"
                 name="company-identicated-name"
                 id="company-identicated-name"
                 value=""
                 disabled>
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">
          <spring:message code="label.business.registration.certificate"/>&nbsp*
        </label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <input type="text" class="form-control"
                 name="company-identicated-type"
                 id="company-identicated-type"
                 value=""
                 disabled>
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.issue.date"/>&nbsp;*</label>
        <div class="col-sm-8">
          <input class="form-control" id="issue-date-enterprise" data-inputmask="'alias': 'date'" name="issue-date"
                 placeholder="<spring:message code="account.identity.issue.date" />"
                 title="<spring:message code="account.identity.issue.date" />"
                 value="${identityDataForm.textIssueDate()}"
                 pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}">
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-sm-4 control-label nowrap"><spring:message
            code="account.identity.placeof.issue"/>&nbsp;*</label>
        <div class="col-sm-8">
          <input type="text" name="place-of-issue" id="place-of-issue-enterprise" class="form-control"
                 value="${identityDataForm.placeOfIssue}" placeholder="Place of issue" style="width: 100%;">
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Người
          đại diện&nbsp*</label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <input type="text" class="form-control"
                 name="company-identicated-representator"
                 id="company-identicated-representator"
                 disabled>
        </div>
      </div>

      <div class="col-sm-12 form-group" style="margin-bottom: 10px;">
        <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Chức
          danh&nbsp*</label>
        <div class="col-md-8 col-lg-8 col-sm-8">
          <input type="text" class="form-control"
                 name="company-identicated-position"
                 id="company-identicated-position"
                 disabled>
        </div>
      </div>
    </div>

    <!-- identity images -->
    <div class="col-sm-6">
      <div class="col-sm-12 form-group">
        <label class="col-sm-4 control-label nowrap"><spring:message code="account.identity.images"/></label>
        <div class="col-sm-8 double-input">
          <div class="row">
            <c:set var="imageDefault"><c:url value="/assets/images/ava-blank.png"/></c:set>
            <style type="text/css">
              .img-identity {
                margin-bottom: 5px;
                height: 140px;
                width: 100%
              }

              img:hover {
                cursor: pointer;
              }

              .gb_La {
                background: rgba(134, 129, 129, 0.54);
                bottom: 5px;
                color: #fff;
                font-size: 9px;
                font-weight: bold;
                left: 15px;
                line-height: 9px;
                position: absolute;
                padding: 7px 0;
                text-align: center;
                width: 229px;
              }

              .gb_La:hover {
                cursor: pointer;
              }
            </style>

            <div class="col-sm-6">
              <div id="div-selfie-image">
                <img class="img-identity" alt=""
                     id="selfie-image-enterprise"
                     src="${identityDataForm.getSrcSelfieFaceImage() eq null ? imageDefault : identityDataForm.getSrcSelfieFaceImage()}"
                     title="${(isCustomerAgent || isMerchantUser) ? 'Cập nhập ảnh selfie' : ''}">
              </div>
              <c:if test="${isCustomerAgent || isMerchantUser}">
                <span class="gb_La" id="lb-selfie-image">Cập nhập ảnh selfie</span>
                <input type="hidden" id="identityIds_2" name="identityIds[2]"
                       value="${identityDataForm.selfieImage.id}">
                <input type="file" id="input-selfie-image" name="files[2]" accept="image/*" class="hidden"
                       data-image-type="<%=TypicalImage.SELFIE.code%>">
                <br><span id="selfie-avatar-status" style=""><%--Loading new avatar...--%></span>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <%--<div class="row">--%>
  <%--<div class="form-group">--%>
  <%--<div class="col-sm-12 text-right">--%>
  <%--<c:if test="${isCustomerAgent || isMerchantUser}">--%>
  <%--<button class="btn btn-sm btn-success" type="button" id="edit_btn" name="edit" onclick="editAccountIdentity()" value="create"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="common.btn.edit"/></button>--%>
  <%--<button class="btn btn-sm btn-primary" type="submit" id="save-btn-identity" value="save-customer-identity" name="btn-action" disabled=""><i class="fa fa-save"></i>&nbsp;<spring:message code="common.btn.save"/> </button>--%>
  <%--</c:if>--%>
  <%--</div>--%>
  <%--</div>--%>
  <%--</div>--%>
</div>
<script type='text/javascript' src='${contextPath}/assets/jQuery-File-Upload/vendor/jquery.ui.widget.js'></script>
<script type='text/javascript' src='${contextPath}/assets/jQuery-File-Upload/jquery.iframe-transport.js'></script>
<script type='text/javascript' src='${contextPath}/assets/jQuery-File-Upload/jquery.fileupload.js'></script>
<script type='text/javascript' src="${contextPath}/assets/Load-Image/load-image.all.min.js"></script>
<script type="text/javascript">
  $(document).ready(function () {

    $("#div-front-image").click(function () {
      $("#input-front-image").trigger('click');
    });

    $("#div-back-image").click(function () {
      $("#input-back-image").trigger('click');
    });

    $("#div-selfie-image").click(function () {
      $("#input-selfie-image").trigger('click');
    });

    $('#input-front-image').on('change', function (event) {
      var urlForm = $(this).data('url');
      var imageType = $(this).data('image-type');
      var objProfileImage = $("#front-image");
      excuteUpdateImage(objProfileImage, urlForm, imageType, event, 'front-image');
    });

    $('#input-back-image').on('change', function (event) {
      var urlForm = $(this).data('url');
      var imageType = $(this).data('image-type');
      var objProfileImage = $("#back-image");
      excuteUpdateImage(objProfileImage, urlForm, imageType, event, 'back-image');
    });

    $('#input-selfie-image').on('change', function (event) {
      var urlForm = $(this).data('url');
      var imageType = $(this).data('image-type');
      var objProfileImage = $("#selfie-image");
      excuteUpdateImage(objProfileImage, urlForm, imageType, event, 'selfie-image');
    });

    function excuteUpdateImage(objProfileImage, urlForm, imageType, event, idImageTag) {

      files = event.target.files;
      event.stopPropagation(); // Stop stuff happening
      event.preventDefault(); // Totally stop stuff happening

      //validate size

      var avatarStatus;
      var divImage;
      if (imageType === 0) {
        avatarStatus = $("#front-avatar-status");
        divImage = $('#div-front-image');
      } else if (imageType === 1) {
        avatarStatus = $("#back-avatar-status");
        divImage = $('#div-back-image');
      } else if (imageType === 2) {
        avatarStatus = $("#selfie-avatar-status");
        divImage = $('#div-selfie-image');
      }

      avatarStatus.text("Đang tải ...");
      objProfileImage.css("opacity", "0.4");
      objProfileImage.css("filter", "alpha(opacity=40);");

      var loadingImage = loadImage(
        event.target.files[0],
        function (img) {
          divImage.empty();
          divImage.append(img);
        },
        {
          maxWidth: 228.83,
          canvas: true,
          pixelRatio: window.devicePixelRatio,
          downsamplingRatio: 0.5,
          orientation: true
        }
      );
      if (!loadingImage) {
        // Alternative code ...
      }

      avatarStatus.text("");
      objProfileImage.css("opacity", "");
      objProfileImage.css("filter", "");
    }

  });
</script>