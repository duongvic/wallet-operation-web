<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_CUSTOMER" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_MERCHANT" %>
<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.Location" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.ID_PROPERTY_MANAGER" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
  <!-- Basic -->
  <meta charset="UTF-8"/>
  <title><spring:message code="setting.account.role.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_service.jsp">
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
  </jsp:include>
  <c:set var="TYPE_CUSTOMER" value="<%=ID_CUSTOMER%>"/>
  <c:set var="TYPE_MERCHANT" value="<%=ID_MERCHANT%>"/>
  <c:set var="TYPE_PROPERTY_MANAGER" value="<%=ID_PROPERTY_MANAGER%>"/>


  <spring:message code="select.district" var="label_countyDistrict"/>
  <c:set var="DISTRICT" value="<%=Location.DISTRICT%>" scope="application"/>
  <c:set var="COMMUNE" value="<%=Location.COMMUE%>" scope="application"/>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="set-cus" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span class="nav-active"><spring:message
                    code="common.customer"/></span>
                <li><span class=""><spring:message
                    code="menu.left.customer.management.list"/></span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>


      <jsp:include page="../include_page/message_new.jsp"/>

      <sec:authorize
          access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER' , 'SALESUPPORT', 'CUSTOMERCARE_MANAGER', 'CUSTOMERCARE')"
          var="permisAdminOperation"/>
      <sec:authorize
          access="hasAnyRole('SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM')"
          var="permisSale"/>
      <sec:authorize
          access="hasAnyRole('FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')"
          var="permisFinance"/>
      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="form-inline hidden">
            <div class="pull-left h4 mb-md mt-md panel-title"><spring:message
                code="system.account.info.page.title"/></div>
            <div class="pull-right form-responsive mb-10">
              <%--<sec:authorize access="hasRole('ADMIN_OPERATION')">--%>
              <%--</sec:authorize>--%>
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">
              <spring:message code="label.un.authenticated" var="unAuthenticated"/>
              <spring:message code="labe.authenticated" var="authenticated"/>
              <div class="tab-content">
                <div id="tab-list-customer" class="tab-pane active">
                  <!-- form search -->
                  <form id="${'edit' eq edit_type ? 'form-account-detail' : 'form-account-create'}"
                        method="POST"
                        class="panel panel-default"
                        onsubmit="return saveAttribute()"
                        enctype="multipart/form-data"
                        modelAttribute="customerDataForm"
                        action="${contextPath}${'edit' eq edit_type ? '/customer/manage/details/'.concat(account_object.id) : '/customer/manage/add'}">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input type="hidden" name="accountId" id="accountId"
                           value="${account_object.id}">
                    <input type="hidden" id="edit_type" name="edit_type"
                           value="${edit_type}">


                    <!-- persional more -->
                    <jsp:include page="edit_account_info.jsp">
                      <jsp:param name="customerType" value="${customerType}"/>
                    </jsp:include>

                    <!-- Thông tin cửa hàng  -->
                    <c:if test="${isPM ne true}">
                      <jsp:include page="../customer_manage/edit_account_store_info.jsp"/>

                      <!-- Thông tin người QL -->
                      <sec:authorize access="!hasAnyRole('SALE_EXCUTIVE')">
                        <jsp:include page="edit_account_parent_info.jsp">
                          <jsp:param name="customerType" value="${customerType}"/>
                        </jsp:include>
                      </sec:authorize>
                    </c:if>

                    <c:if test="${(permisAdminOperation)}">
                      <jsp:include page="../customer_manage/edit_account_personal.jsp"/>
                      <hr/>

                      <c:if test="${isPM eq true && account_object.kycStatus eq 4}">
                        <jsp:include page="../customer_manage/edit_account_property_manager_identity.jsp"/>
                      </c:if>

                      <c:if test="${isPM ne true}">
                        <!-- identity info not PM -->
                        <jsp:include page="../customer_manage/edit_account_identity.jsp"/>
                      </c:if>

                    </c:if>


                    <jsp:include
                        page="../customer_manage/edit_account_commission_fee.jsp"/>

                    <!-- list attribute -->
                    <jsp:include
                        page="../customer_manage/edit_account_attribute.jsp"/>
                    <!-- /list attribute -->

                    <jsp:include
                        page="../customer_manage/edit_account_log_history.jsp"/>
                  </form>
                  <!-- end form search -->
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
      <jsp:include page="../include_component/action_grant_role.jsp"/>
      <jsp:include page="../include_component/action_grant_delete.jsp"/>
      <jsp:include page="../include_component/google_authenticator.jsp"/>
      <spring:message code="system.specified.commission.fee.popup.update.title" var="changeFeeTitle"/>
      <jsp:include page="../include_component/change_service_commission_fee_modal.jsp">
        <jsp:param name="title" value="${changeFeeTitle}"/>
        <jsp:param name="actionURI" value="/customer/specified-commission-fee/update"/>
      </jsp:include>
      <spring:message code="system.specified.commission.fee.popup.reset.title" var="resetFeeTitle"/>
      <jsp:include page="../include_component/reset_service_commission_fee_modal.jsp">
        <jsp:param name="title" value="${resetFeeTitle}"/>
        <jsp:param name="actionURI" value="/customer/specified-commission-fee/delete"/>
      </jsp:include>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>

<script type="text/javascript"
        src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<script src="${contextPath}/assets/jquery.inputmask.bundle.min.js"></script>
<jsp:include page="../fee_structure/service_tree_lib.jsp"/>


<script type="text/javascript">
  (function ($) {
    'use strict';
    $(document).ready(function () {
      $(":input").inputmask();

      var customerTypeTag = $("#customerType");
      selectCustomerType(customerTypeTag.val());

      /*getLocation*/
      $('#current_province_selected').on("change", function () {
        var value = jQuery(this).val();
        if (value != null && value !== undefined && value !== '') {

          getLocation(value, '${DISTRICT}', 'current_district_selected',
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

    var $limitNum = 255;
    $('textarea[name="account-about"]').keydown(function () {
      var $this = $(this);
      if ($this.val().length > $limitNum) {
        $this.val($this.val().substring(0, $limitNum));
      }
    });
  }(jQuery));

  jQuery('.dataTables_length').css('display', 'none');
  jQuery('.dataTables_filter').css('display', 'none');

  function editAccount() {
    jQuery('#first-name').attr('disabled', false);
    jQuery('#last-name').attr('disabled', false);
    jQuery('#customerType').attr('disabled', false);
    jQuery('#notification').attr('disabled', false);
    jQuery('#phone').attr('disabled', false);
    jQuery('#email').attr('disabled', false);
    jQuery('#blackList').attr('disabled', false);
    jQuery('#positionList').attr('disabled', false);
    jQuery('#description').attr('disabled', false);
    jQuery('#chuKiDoiSoat').attr('disabled', false);
    jQuery('#edit_type').attr('disabled', false);
    jQuery('#save_btn').attr('disabled', false);

    var kycPM = '${account_object.kycStatus}';
    if (kycPM == 4) {
      jQuery('#first-name').attr('disabled', true);
      jQuery('#firstNameHidden').attr('disabled', false);
      jQuery('#last-name').attr('disabled', true);
      jQuery('#lastNameHidden').attr('disabled', false);
      jQuery('#phone').attr('disabled', true);
      jQuery('#phoneHidden').attr('disabled', false);
      // jQuery('#email').attr('disabled', true);
      jQuery('#emailHidden').attr('disabled', false);
    }
  }

  function editAccountParent() {

  }

  function editAccountStore() {
    $('#save_btn_store').attr('disabled', false);
    $('#aliasStore').attr('disabled', false);
    $('#businessPhoneStore').attr('disabled', false);
    $('#street1Store').attr('disabled', false);
    $('#outletStoreType').attr('disabled', false);

    jQuery('#edit_type').attr('disabled', false);
  }

  function editAccountPersonal() {
    $('#btn-save-personal').attr('disabled', false);
    $('#account-nameAddr').attr('disabled', false);
    $('#account-living-address').attr('disabled', false);
    $('#account-gender').attr('disabled', false);
    $('#account-about').attr('disabled', false);
    $('#account-dateofbirth').attr('disabled', false);
    $('#account-country').attr('disabled', false);
    $('#current_province_selected').attr('disabled', false);
    $('#current_district_selected').attr('disabled', false);

    jQuery('#edit_type').attr('disabled', false);
  }

  function editAccountIdentity() {
    <c:if test="${permisAdminOperation}">
    $('#edit_type').attr('disabled', false);
    </c:if>
    $('#identityId').attr('disabled', false);
    $('#identityCusId').attr('disabled', false);

    $('#identityIds_0').attr('disabled', false);
    $('#identityIds_1').attr('disabled', false);
    $('#identityIds_2').attr('disabled', false);

    $('#save-btn-identity').attr('disabled', false);
    $('#identity-type').attr('disabled', false);
    $('#identity-number').attr('disabled', false);
    $('#issue-date').attr('disabled', false);
    $('#expire-date').attr('disabled', false);
    $('#place-of-issue').attr('disabled', false);

    $('#input-front-image').attr('disabled', false);
    $('#input-back-image').attr('disabled', false);
    $('#input-selfie-image').attr('disabled', false);

  }

  $(document).ready(function () {
    var customerTypeElem = $('#customerType');
    changeViewsByCustomerType(customerTypeElem.val());

    customerTypeElem.change(function () {
      changeViewsByCustomerType($(this).val());
    });

    $('#serviceTypeIds').attr('disabled', false);

    $('#serviceTypeIds').change(function () {
      var id = $('#serviceTypeIds').val();
      var detailURL = '';
      /*if (window.location.search.indexOf("?") >= 0) {
        detailURL = window.location.pathname + window.location.search + '&serviceTypeIds=' + id;
      } else {
        detailURL = window.location.pathname + '?' + 'serviceTypeIds=' + id;
      }*/
      if (window.location.search.indexOf("?") >= 0) {
        if (window.location.search.indexOf("serviceTypeIds") >= 0) {
          detailURL = window.location.pathname + replaceUrlParam(window.location.search,
            'serviceTypeIds', id);
        } else {
          detailURL = window.location.pathname + window.location.search + '&serviceTypeIds=' + id;
        }

      } else {
        detailURL = window.location.pathname + '?serviceTypeIds=' + id;
      }
      window.location.href = detailURL;
    });
  });

  function changeViewsByCustomerType(customerTypeValue) {
    var emailTag = $('#email');
    var emailTitle = $('#email-title');
    var attributeList = $('#attribute_list');
    if ('${TYPE_CUSTOMER}' == customerTypeValue) {
      emailTag.attr('required', false);
      emailTitle.html('Thư điện tử');
    } else {
      emailTag.attr('required', true);
      emailTitle.html('Thư điện tử *');
    }
    if ('${TYPE_MERCHANT}' == customerTypeValue) {
      attributeList.removeClass("hidden");
    } else {
      attributeList.addClass("hidden");
    }
  }
</script>

</body>

</html>
