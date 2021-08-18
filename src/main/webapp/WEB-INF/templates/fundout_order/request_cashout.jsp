<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="fundout.order.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <jsp:include page="../include_page/js.jsp"/>

</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="orderfund_out" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><a href="list" id="hight-title" class="hight-title"><spring:message code="fundout.title.page"/> </a></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid pt-md">
          <div class="mb-md">
            <div class="wizard-tabs">
              <ul class="wizard-steps">
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">1</span>&nbsp; <spring:message code="common.btn.request"/> </a></li>
                <li class="col-xs-3 pl-none pr-none active"><a class="text-center"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="common.btn.submit"/></a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">3</span>&nbsp; <spring:message code="common.btn.verify"/> </a></li>
                <li class="col-xs-3 pl-none pr-none"><a class="text-center"><span class="badge hidden-xs">4</span>&nbsp; <spring:message code="common.btn.approve"/> </a></li>
              </ul>
              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="background-color: white;">
                <div class="page-header-left header-title">
                  <spring:message code="fundin.order.requestCashIn"/>&nbsp;<span class="primary_color">( <spring:message code="${fundInOrders.getTextOrderChannel()}" /> )</span>
                </div>
              </div>
            </div>
            <div class="tab-content">
              <section class="panel search_payment panel-default">
                <form action="" method="post"  enctype="multipart/form-data">
                  <div class="panel-body">
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.account"/></label>
                      <div class="col-md-3">${fundInOrders.username}</div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.amount"/></label>
                      <div class="col-md-3">${ewallet:formatNumber(fundInOrders.amount)} (VNĐ)
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="request.transfer.fee"/></label>
                      <div class="col-md-6">${ewallet:formatNumber(fundInOrders.fee)} (VNĐ)
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="request.transfer.totalAmount"/></label>
                      <div class="col-md-6">${ewallet:formatNumber(fundInOrders.amount)} (VNĐ)
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.fee"/></label>
                      <div class="col-md-6">${ewallet:formatNumber(customerCurrentBalance)} (VNĐ)
                      </div>
                    </div>

                    <div class="form-group mb-none">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.formStock"/></label>
                      <div class="col-md-6">
                        <input type="file" name="fileUpload" id="file-2" class="inputfile inputfile-2" data-multiple-caption="{count} files selected" multiple/>
                        <label for="file-2"><i class="fa fa-upload" aria-hidden="true"></i> <span><spring:message code="common.btn.choose.file"/> </span></label>
                        <p><strong class="secondary_color"><5mb </strong>(*.png,*.jpe,*.jpeg,*.jpg)</p>
                      </div>
                    </div>
                    <!-- div show file -->
                    <div class="form-group">
                      <div class="col-md-3"></div>
                      <div class="col-md-6 fileshow">
                        <c:forEach var="item" items="${attachments }">
                          <p>${item.name }</p>
                        </c:forEach>
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-md-3"></div>
                      <div class="col-md-6">
                        <c:forEach var="item" items="${attachments }">
                          <p class="primary_color">
                            <img alt="" src="data:image/png;base64, <c:out value='${item.imageBase64}'/>" style="max-width: 90%;">
                          </p>
                          <br/>
                        </c:forEach>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-3 control-label"><spring:message code="fundin.order.notable"/></label>
                      <div class="col-md-3">
                        <textarea rows="5" name="remark" class="form-control">${fundInOrders.info }</textarea>
                      </div>
                    </div>
                    <div class="alert alert-default mb-none mt-md p-sm">
                      <div class="checkbox-custom checkbox-success">
                        <input type="checkbox" name="ckaccess" id="checkboxExample3">
                        <label for="checkboxExample3"><spring:message code="fundin.order.confirm"/></label>
                      </div>

                    </div>
                    <div class="report_search_bt">
                      <input type="hidden" name="action" value=""/>
                      <spring:message code="common.btn.processing.submit" var="waitting"/>
                      <button type="submit" action="submit" class="btn btn-success ml-md fr"
                              data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.submit"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>

                      <button type="submit" action="save" class="btn btn-primary ml-md fr"
                              data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/></button>

                      <button type="submit" action="reject" class="btn btn-danger ml-md fr"
                              data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-times-circle" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.reject"/></button>
                    </div>
                  </div>
                  <sec:csrfInput/>
                </form>
                <div class="clearfix"></div>
              </section>
            </div>
          </div>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {

    $('form button:submit').click(function () {

      if (!$('#checkboxExample3').is(':checked')) {
        $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/> "});
        return false;
      }
      if ($("form input[name=action]").val() == 'reject') {
        if ($("form textarea[name=remark]").val().length < 1) {
          $.MessageBox({message: "<spring:message code="common.fill.in.the.reason"/>"});
          return false;
        }
      }

      $("input[name=action]").val($(this).attr("action"));
      $(this).button('loading');
    });
    $('form input:file').change(function (click) {
      var files = $(this)[0].files;
      var exts = ['png', 'jpg', 'jpe', 'jpeg'];
      var checkExt = true;
      if (files.length > 0) {
        for (var i = 0; i < files.length; i++) {
          var get_ext = files[i].name.split('.');
          // reverse name to check extension
          get_ext = get_ext.reverse();
          if ($.inArray(get_ext[0].toLowerCase(), exts) < 0) {
            checkExt = false;
          }
        }
        if (!checkExt) {
          $.MessageBox({message: '<spring:message code="common.upload.file.not.format"/>'});
          $(this).val('');
        }
      }
    });
    var inputs = document.querySelectorAll('.inputfile');
    Array.prototype.forEach.call(inputs, function (input) {
      var label = input.nextElementSibling, labelVal = label.innerHTML;
      input.addEventListener('change', function (e) {
        $('div.fileshow').html('');
        var fileName = '';
        if (this.files && this.files.length > 1) {
          fileName = ( this.getAttribute('data-multiple-caption') || '' ).replace('{count}', this.files.length);
        } else {
          fileName = e.target.value.split('\\').pop();
        }
        if (fileName) {
          label.querySelector('span').innerHTML = fileName;
        } else {
          label.innerHTML = labelVal;
        }
        if (this.files && this.files.length > 0) {
          $.each(e.target.files, function (idx, elm) {
            var size = elm.size / 1024;
            $('div.fileshow').append('<p class="primary_color">' + elm.name + ' - size : ' + size + 'KB</p>')
          });
        }
      });
    });
  });
</script>
</body>
</html>
