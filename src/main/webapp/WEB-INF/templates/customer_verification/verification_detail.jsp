<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <title>Customer Verification Detail</title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="tableLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
        <jsp:param name="dateLib" value="true"/>
    </jsp:include>

    <c:set var="DISTRICT" value="<%=Location.DISTRICT%>" scope="application"/>
    <c:set var="COMMUNE" value="<%=Location.COMMUE%>" scope="application"/>
    <link rel="stylesheet" href="<c:url value="/assets/javascripts/zoomjs/css/zoom.css"/>">
</head>

<spring:message code="select.district" var="label_countyDistrict"/>

<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-verifi" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="common.customer"/></span></li>
                                <li><span><spring:message
                                        code="menu.left.customer.verification.list"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="customer.verification.detail"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>


            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">

                    <div class="form-inline hidden">
                        <div class="pull-left h4 mb-md mt-md panel-title">Customer Verification
                            Detail
                        </div>
                        <div class="pull-right form-responsive mb-10"></div>
                    </div>

                    <spring:message code="label.un.authenticated" var="unAuthenticated"/>
                    <spring:message code="labe.authenticated" var="authenticated"/>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <div class="tab-content">
                                <div id="tab-list-customer" class="tab-pane active">
                                    <!-- form search -->
                                    <form action="${contextPath}/customer/verification/submit"
                                          id="formVerifiSub" method="post"
                                          enctype="multipart/form-data">
                                        <jsp:include page="customer_system.jsp"/>

                                        <div class="panel-title">
                                            <h4 style="margin-bottom: 1.5rem">Thông tin xác
                                                thực</h4>
                                            <div class="panel-tools hidden"></div>
                                        </div>
                                        <!-- persional -->
                                        <c:choose>
                                            <c:when test="${ENTERPRISE eq true}">
                                                <%--giao dien xac thuc doanh nghiep--%>
                                                <jsp:include page="customer_enterprise.jsp"/>
                                                <jsp:include page="enterprise_info_kyc.jsp"/>
                                            </c:when>
                                            <c:otherwise>
                                                <jsp:include page="customer_personal.jsp"/>
                                                <jsp:include page="customer_additional.jsp"/>
                                                <hr/>
                                                <!-- identity info -->
                                                <jsp:include page="customer_info_kyc.jsp"/>
                                                <!-- end form search -->
                                            </c:otherwise>
                                        </c:choose>

                                        <input type="hidden" name="senderType" value="${customerVerifyKyc.senderType}">
                                        <input type="hidden" name="action"/>
                                        <sec:csrfInput/>
                                    </form>
                                </div>
                            </div>


                        </div>
                    </section>
                </div>
            </div>
            <!-- end: page -->
        </section>
        <%--<jsp:include page="trans_rule_delete.jsp"/>--%>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>

<script src="<c:url value="/assets/javascripts/zoomjs/js/zoom.js"/>"></script>
<script type="text/javascript">
  $(document).ready(function () {
    $(":input").inputmask();

    $('#id-form-personal-information').find('input, textarea, select').prop('disabled', true);
    $('#enterprise-info').find('input, textarea, select').prop('disabled', true);

    $('#hidden_current_province_selected').val($('#kyc_current_province_selected').val());
    $('#hidden_current_district_selected').val($('#kyc_current_district_selected').val());

    /*getLocation*/
    $('#kyc_current_province_selected').on("change", function () {
      var value = jQuery(this).val();
      if (value != null && value !== undefined && value !== '') {

        getLocation(value, '${DISTRICT}', 'kyc_current_district_selected',
            '<option value="">${label_countyDistrict}</option>');

        <%--getLocation(value, '${COMMUNE}', 'current_commune_selected',--%>
        <%--'<option value="">${label_commune}</option>');--%>
      }
    });

    function getLocation(parent, id, selectionId, defaultTitle) {
      var selection = $('#'.concat(selectionId));
      if (selection != null && selection !== undefined) {
        $.ajax({
          type: 'GET',
          url: ctx + '/ajax-controller/get-location/' + parent + '/' + id,
          contentType: "application/json;charset=utf-8",
          <%--beforeSend: function (xhr) {--%>
          <%--if ("${_csrf.parameterName}" && "${_csrf.token}") {--%>
          <%--xhr.setRequestHeader("${_csrf.parameterName}", "${_csrf.token}");--%>
          <%--}--%>
          <%--},--%>
          dataType: 'json',
          timeout: 60000,
          success: function (data) {
            var htmlCode = defaultTitle;
            for (var i = 0; i < data.length; ++i) {
              htmlCode = htmlCode.concat(
                  "<option value=".concat(data[i].code).concat(">").concat(data[i].name).concat(
                      "</option>"));
            }
            selection.html(htmlCode);
          }
        });
      }
    };
    /* end getLocation*/

  });

  //  function getGender(elem) {
  //    var gender1 = elem.value;
  //    $('#hidden_gender').val(gender1);
  //  }
</script>
</body>

</html>
