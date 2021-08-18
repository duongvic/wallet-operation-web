<%@ page import="vn.mog.ewallet.operation.web.contract.TypicalImage" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem">Thông tin thêm</h4>

    <div class="panel-tools hidden">
        <sec:authorize access="hasAnyRole('ADMIN_OPERATION')">
            <a class="mb-xs mt-xs btn btn-success" style="line-height: 28px" href="#">
                <i class="fa fa-plus"></i>&nbsp;</a>
        </sec:authorize>
    </div>
</div>
<div class="panel-body">
    <div class="row">
        <style type="text/css">
            .img-identity {
                margin-bottom: 5px;
                height: 140px;
            }

            img:hover {
                cursor: pointer;
            }

            .gb_La {
                background: rgba(134, 129, 129, 0.54);
                /*bottom: 5px;*/
                color: #fff;
                font-size: 9px;
                font-weight: bold;
                left: 15px;
                line-height: 9px;
                position: absolute;
                padding: 7px 0;
                text-align: center;
                width: 140px;
            }

            .gb_La:hover {
                cursor: pointer;
            }
        </style>

        <div class="form-group">
            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-4 col-lg-4  col-sm-4 control-label nowrap">Ảnh đại diện&nbsp;
                    *</label>

                <div id="div-profie-image" class="col-md-8 col-lg-8  col-sm-8 double-input">
                    <img class="img-identity" alt=""
                         id="profie-image" src="${img_profile}"/>
                    </br>
                    <span class="gb_La" id="lb-profie-image">Cập nhập ảnh profile</span>
                </div>


                <input type="hidden" id="identityIds_3" name="img_profile[0]"
                       value="${img_profile}">
                <input type="file" id="input-profie-image" name="img_profile" accept="image/*"
                       class="hidden"
                       data-image-type="<%=TypicalImage.IMAGE_PROFILE.code%>">
                <br><span id="profie-avatar-status"
                          style=""><%--Loading new avatar...--%></span>


            </div>
            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"></label>
                <div class="col-md-7 col-lg-8 col-sm-8"></div>
            </div>
        </div>
    </div>

    </br>

    <div class="row">
        <div class="">
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Quốc tịch</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
                    <input type="text" id="account-country" name="account-country"
                           class="form-control w100"
                           value="${account_object.country}"/>
                </div>
            </div>
            <div class="col-md-6 col-lg-6 col-sm-12  form-group">
                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap">Ngày sinh</label>
                <div class="col-md-7 col-lg-8 col-sm-8">
                    <input type="text" id="account-dateofbirth" name="account-dateofbirth"
                           data-inputmask="'alias': 'date'"
                           pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}"
                           class="form-control w100" value="${account_object.textDateOfBirth()}">
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Giới thiệu</label>
                <div class="col-md-8 col-lg-8 col-sm-8">
          <textarea class="form-control w100" id="account-about" name="account-about"
                    maxlength="255">${account_object.description}</textarea>
                </div>
            </div>

            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-5 col-lg-4 col-sm-4 control-label nowrap"><spring:message
                        code="label.gender"/></label>
                <div class="col-md-7 col-lg-8 col-sm-8">
                    <select class="form-control w100" data-plugin-selectTwo id="account-gender"
                            name="account-gender" required>
                        <option value="0" ${account_object.gender eq 0 ? 'selected' : ''}>Nam
                        </option>
                        <option value="1" ${account_object.gender eq 1 ? 'selected' : ''}>Nữ
                        </option>
                        <option value="2" ${account_object.gender eq 2 ? 'selected' : ''}>Khác
                        </option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-4 col-lg-4 col-sm-4 control-label nowrap">Địa chỉ hiện
                    tại</label>
                <div class="col-md-7 col-lg-8  col-sm-8">
                    <input type="text" id="account-living-address" name="account-living-address"
                           value="${account_object.livingAddress}" class="form-control w100">
                </div>
            </div>

            <div class="col-md-6 col-lg-6 col-sm-12">
                <label class="col-md-5 col-lg-4  col-sm-4 control-label">Địa chỉ khai báo</label>
                <div class="col-md-7 col-lg-8  col-sm-8">
                    <c:if
                            test="${account_object.addresses ne null && fn:length(account_object.addresses) gt 0 }">
                        <c:forEach var="itemAddress" items="${account_object.addresses}">
                            <input type="text" id="account-nameAddr" name="account-nameAddr"
                                   class="form-control w100" value="${itemAddress.street1}">
                        </c:forEach>
                    </c:if>
                    <c:if test="${account_object.addresses eq null}">
                        <input type="text" id="account-nameAddr" name="account-nameAddr"
                               class="form-control w100" value=""/>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group">
            <div class="col-sm-12 text-right">
                <button class="btn btn-sm btn-success ${'edit' eq edit_type ? '' : 'hidden'}"
                        type="button"
                        id="edit_btn" name="edit" onclick="editAccountPersonal()" value="create">
                    <i class="fa fa-pencil"></i>&nbsp;<spring:message code="common.btn.edit"/>
                </button>
                <button class="btn btn-sm btn-primary" type="submit" value="save-account-personal"
                        name="btn-action"
                        id="btn-save-personal" ${'edit' eq edit_type ? 'disabled' : ''}>
                    <i class="fa fa-save"></i>&nbsp;<spring:message code="common.btn.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript'
        src='${contextPath}/assets/jQuery-File-Upload/vendor/jquery.ui.widget.js'></script>
<script type='text/javascript'
        src='${contextPath}/assets/jQuery-File-Upload/jquery.iframe-transport.js'></script>
<script type='text/javascript'
        src='${contextPath}/assets/jQuery-File-Upload/jquery.fileupload.js'></script>
<script type='text/javascript'
        src="${contextPath}/assets/Load-Image/load-image.all.min.js"></script>
<script type="text/javascript">
  $(document).ready(function () {
    $("#div-profie-image").click(function () {
      $("#input-profie-image").trigger('click');
    });

    $('#input-profie-image').on('change', function (event) {
      var urlForm = $(this).data('url');
      var imageType = $(this).data('image-type');
      var objProfileImage = $("#profie-image");
      excuteUpdateImage(objProfileImage, urlForm, imageType, event, 'profie-image');
    });

    function excuteUpdateImage(objProfileImage, urlForm, imageType, event, idImageTag) {

      files = event.target.files;
      event.stopPropagation(); // Stop stuff happening
      event.preventDefault(); // Totally stop stuff happening

      //validate size

      var avatarStatus;
      var divImage;
      if (imageType === 3) {
        avatarStatus = $("#profie-avatar-status");
        divImage = $('#div-profie-image');
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