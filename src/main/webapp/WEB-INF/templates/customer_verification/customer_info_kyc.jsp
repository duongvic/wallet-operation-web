<%@ page import="vn.mog.ewallet.operation.web.contract.TypicalImage" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycRequestStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <h4 style="margin-bottom: 1.5rem">Kyc Request</h4>
    <div class="panel-tools hidden">
        <a class="mb-xs mt-xs btn btn-success" style="line-height: 28px" href="#"><i
                class="fa fa-plus"></i>&nbsp;</a>
    </div>
</div>

<div class="panel-body">

        <div class="row">
            <div class="form-group">
                <!-- identity images -->
                <div class="col-sm-12 col-md-12">
                    <label class="col-sm-4 col-md-4 control-label nowrap">Identity images :</label>
                    <div class="form-group col-8 col-sm-8 col-md-8">
                        <c:set var="imageDefault"><c:url
                                value="/assets/images/ava-blank.png"/></c:set>
                        <style type="text/css">
                            .img-identity {
                                margin-bottom: 5px;
                                max-height: 100%;
                                max-width: 100%;
                                min-height: 100%;
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
                                padding: 7px 7px;
                                text-align: center;
                                width: 91.5%;;
                            }

                            .gb_La:hover {
                                cursor: pointer;
                            }
                        </style>

                        <div id="div-front-image-kyc" class="col-sm-12 col-md-4">
                            <div style="margin-bottom: 5px; height: 250px;" class="text-center">
                                <img class="img-identity" alt=""
                                     id="front-image"
                                     data-action="zoom"
                                     src="${frontImage eq null ? imageDefault : frontImage }"
                                     title="Cập nhập ảnh mặt trước'">
                            </div>
                            <span class="gb_La" id="lb-front-image">Cập nhập ảnh mặt trước</span>
                            <input type="file" id="input-front-image-kyc" name="front-image"
                                   accept="image/*" class="hidden"
                                   data-image-type="<%=TypicalImage.FRONT_FACE.code%>">
                            <br>
                        </div>

                        <div id="div-back-image-kyc" class="col-sm-12 col-md-4">
                            <div style="margin-bottom: 5px; height: 250px;" class="text-center">
                                <img class="img-identity" alt=""
                                     id="back-image"
                                     data-action="zoom"
                                     src="${backImage eq null ? imageDefault : backImage}"
                                     title="Cập nhập ảnh mặt sau">
                            </div>
                            <span class="gb_La" id="lb-back-image">Cập nhập ảnh mặt sau</span>
                            <input type="file" id="input-back-image-kyc" name="back-image"
                                   accept="image/*" class="hidden"
                                   data-image-type="<%=TypicalImage.BACK_FACE.code%>">
                            <br>
                        </div>

                        <div id="div-selfie-image-kyc" class="col-sm-12 col-md-4">
                            <div style="margin-bottom: 5px; height: 250px;" class="text-center">
                                <img class="img-identity" alt=""
                                     id="selfie-image"
                                     data-action="zoom"
                                     src="${selfieImage eq null ? imageDefault : selfieImage}"
                                     title="Cập nhập ảnh selfie">
                            </div>
                            <span class="gb_La" id="lb-selfie-image">Cập nhập ảnh selfie</span>
                            <input type="file" id="input-selfie-image-kyc" name="selfie-image"
                                   accept="image/*" class="hidden"
                                   data-image-type="<%=TypicalImage.SELFIE.code%>">
                            <br>
                        </div>
                        <%--<img src="${customerVerifyKyc.existFrontImage() ? imageDefault : customerVerifyKyc.base64FrontImage()}" class="col-sm-12 col-md-4" style="margin-bottom: 5px; height: 140px;" alt="">--%>
                        <%--<img src="${customerVerifyKyc.existBackImage() ? imageDefault : customerVerifyKyc.base64BackImage()}" class="col-sm-12 col-md-4" style="margin-bottom: 5px; height: 140px;" alt="">--%>
                        <%--<img src="${customerVerifyKyc.existSelfieImage() ? imageDefault : customerVerifyKyc.base64SelfieImage()}" class="col-sm-12 col-md-4" style="margin-bottom: 5px; height: 140px;" alt="">--%>
                        <%--<div class="row">

                        </div>--%>
                    </div>
                </div>
            </div>
        </div>


        <!-- identity info -->
        <div class="row">
            <div class="form-group">
                <div class="col-sm-12 col-md-6">
                    <label class="col-sm-4 control-label nowrap">Request create date :</label>
                    <div class="col-sm-8">${customerVerifyKyc.textCreatedDate()}</div>
                </div>
                <div class="col-sm-12 col-md-6">
                    <label class="col-sm-4 control-label nowrap">&nbsp;</label>
                    <div class="col-sm-8 double-input">&nbsp;</div>
                </div>
                <div class="col-sm-12 col-md-6">
                    <label class="col-sm-4 control-label nowrap">Request update Date :</label>
                    <div class="col-sm-8">${customerVerifyKyc.textUpdatedDate()}</div>
                </div>
                <div class="col-sm-12 col-md-6">
                    <label class="col-sm-4 control-label nowrap">&nbsp;</label>
                    <div class="col-sm-8 double-input">&nbsp;</div>
                </div>
                <div class="col-sm-12 col-md-6">
                    <label class="col-sm-4 control-label nowrap">Remark :</label>
                    <div class="col-sm-8"><textarea name="remark"
                                                    style="width: 100%">${customerVerifyKyc.remark}</textarea>
                    </div>
                </div>
            </div>
        </div>

        <spring:message code="common.btn.processing.submit" var="waitting"/>

        <div class="row">
            <div class="form-group">
                <div class="col-sm-12 text-right">
                    <input type="hidden" name="customerId" value="${customerId}">
                    <input type="hidden" name="requestKycId" value="${requestKycId}">
                    <c:set var="approvedKycRequest"
                           value="<%=KycRequestStatus.APPROVED.getCode()%>"/>
                    <c:set var="staffRejectKycRequest"
                           value="<%=KycRequestStatus.STAFF_REJECTED.getCode()%>"/>
                    <c:if test="${customerVerifyKyc.requestStatusId != approvedKycRequest && customerVerifyKyc.requestStatusId != staffRejectKycRequest}">
                        <button type="button" class="btn btn-sm btn-success" id="btn_edt"
                                data-loading-text="<i class='fa fa-pencil'></i> ${waitting}"><i
                                class="fa fa-pencil"></i>&nbsp;Chỉnh sửa
                        </button>
                        <button type="submit" disabled class="btn btn-sm btn-primary"
                                id="btn_update"
                                action="update"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                            <i class="fa fa-pencil"></i>&nbsp;Lưu
                        </button>
                        <button class="btn btn-sm btn-danger" type="submit" id="btn_edit"
                                action="reject"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                            <i class="fa fa-pencil"></i>&nbsp;Từ chối
                        </button>
                        <button class="btn btn-sm btn-success" type="submit" id="btn_save"
                                action="approve"
                                data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                            <i class="fa fa-save"></i>&nbsp;Đồng ý
                        </button>
                    </c:if>


                </div>
            </div>
        </div>
    <%--</form>--%>

    <script src='${contextPath}/assets/jQuery-File-Upload/vendor/jquery.ui.widget.js'></script>
    <script src='${contextPath}/assets/jQuery-File-Upload/jquery.iframe-transport.js'></script>
    <script src='${contextPath}/assets/jQuery-File-Upload/jquery.fileupload.js'></script>
    <script src="${contextPath}/assets/Load-Image/load-image.all.min.js"></script>
    <script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function () {

        $("#btn_edt").click(function () {
          $('#btn_update').attr('disabled', false);
          $('#id-form-personal-information').find('input, textarea, select').prop('disabled',
            false);
          $('#enterprise-info').find('input, textarea, select').prop('disabled',
            false);
          <c:if test="${customerVerifyKyc.senderType eq 'PMS'}">
            $('#kyc-displayName').prop('disabled', true);
            $('#kyc_idNumber').prop('disabled', true);
          </c:if>
        });

        $('form button:submit').click(function () {
          $("input[name=action]").val($(this).attr("action"));
          $(this).button('loading');
          $("#formVerifiSub").submit();
        });

      });

      $(document).on("click", "#input-front-image-kyc", function (e) {
        e.stopPropagation();
      });
      $(document).on("click", "#div-front-image-kyc", function () {
        $('#input-front-image-kyc').trigger('click');
      });

      $(document).on("click", "#input-back-image-kyc", function (e) {
        e.stopPropagation();
      });
      $(document).on("click", "#div-back-image-kyc", function () {
        $('#input-back-image-kyc').trigger('click');
      });

      $(document).on("click", "#input-selfie-image-kyc", function (e) {
        e.stopPropagation();
      });
      $(document).on("click", "#div-selfie-image-kyc", function () {
        $('#input-selfie-image-kyc').trigger('click');
      });


      $('#input-front-image-kyc').on('change', function (event) {
        var imageType = $(this).data('image-type');
        var objProfileImage = $("#front-image");
        executeUpdateImage(objProfileImage, this, imageType, event, 'front-image');
      });

      $('#input-back-image-kyc').on('change', function (event) {
        var imageType = $(this).data('image-type');
        var objProfileImage = $("#back-image");
        executeUpdateImage(objProfileImage, this, imageType, event, 'back-image');
      });

      $('#input-selfie-image-kyc').on('change', function (event) {
        var imageType = $(this).data('image-type');
        var objProfileImage = $("#selfie-image");
        executeUpdateImage(objProfileImage, this, imageType, event, 'selfie-image');
      });

      function executeUpdateImage(objProfileImage, input, imageType, event, idImageTag) {

        files = event.target.files;
        event.stopPropagation(); // Stop stuff happening
        event.preventDefault(); // Totally stop stuff happening

        //validate size

        var avatarStatus;
        var divImage;
        var avatarStatusString;
        if (imageType === 0) {
          avatarStatusString = "Cập nhập ảnh mặt trước";
          avatarStatus = $("#lb-front-image");
          divImage = $('#front-image');
        } else if (imageType === 1) {
          avatarStatusString = "Cập nhập ảnh mặt sau";
          avatarStatus = $("#lb-back-image");
          divImage = $('#back-image');
        } else if (imageType === 2) {
          avatarStatusString = "Cập nhập ảnh selfie";
          avatarStatus = $("#lb-selfie-image");
          divImage = $('#selfie-image');
        }

        avatarStatus.html("Đang tải ...");
        objProfileImage.css("opacity", "0.4");
        objProfileImage.css("filter", "alpha(opacity=40);");

        if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
            divImage.attr('src', e.target.result);
          };

          reader.readAsDataURL(input.files[0]);
        }

        avatarStatus.html(avatarStatusString);
        avatarStatus.attr("width", divImage.attr("width"));
        objProfileImage.css("opacity", "");
        objProfileImage.css("filter", "");
      };
    </script>

</div>