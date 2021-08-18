<%@ page import="vn.mog.ewallet.operation.web.controller.service.TrueServiceController" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceLevel" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoServiceType" %>
<%@ page import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<c:set var="telcoTypes" value="<%=TelcoType.values()%>" scope="request"/>
<c:set var="telcoServiceTypes" value="<%=TelcoServiceType.values()%>" scope="request"/>
<c:set var="serviceLevels" value="<%=ServiceLevel.values()%>" scope="request"/>
<c:set var="FULL_CUSTOMER_TYPES" value="<%=CustomerType.FULL_CUSTOMER_TYPES%>" scope="request"/>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="system.service.list.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_service.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="switchLib" value="true"/>
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <style>
    .checkbox-info label:before {border: 1px solid #ecd6d6}
    .treetable-expanded > td:first-child, .treetable-collapsed > td:first-child {padding-left: 2em;}
    .treetable-expanded > td:first-child > .treetable-expander, .treetable-collapsed > td:first-child > .treetable-expander {top: 0.05em;position: relative;margin-left: -0.5em;margin-right: 0.25em;}
    .treetable-expanded .treetable-expander, .treetable-expanded .treetable-expander {width: 1em;height: 1em;cursor: pointer;position: relative;display: inline-block;}
    .treetable-depth-1 > td:first-child {padding-left: 3em;}
    .treetable-depth-2 > td:first-child {padding-left: 4.5em;}
    .treetable-depth-3 > td:first-child {padding-left: 6em;}
  </style>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="cus-type-special-fee" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="system.service.list.navigate.system"/></span></li>
                <li><span class="nav-active"><spring:message code="system.service.list.navigate.service"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->

      <spring:message code="select.choose.all" var="langChooseAll"/>
      <spring:message code="select.service.type" var="langServicetype"/>
      <spring:message code="system.service.list.form.search.placeholder" var="placeholder"/>

      <div class="content-body-wrap">

        <div class="container-fluid">
          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md"><spring:message code="system.service.list.subnavigate.service"/></div>
            <%--<div class="fr form-responsive">--%>
              <%--<sec:authorize access="hasAnyRole('ADMIN_OPERATION','SALESUPPORT_LEADER', 'SALESUPPORT')">--%>
                <%--<a class="mb-xs mt-xs btn btn-success" data-toggle="modal" data-target="#add"><i class="fa fa-plus"></i>&nbsp;<spring:message code="system.service.navigate.btn.create"/></a>--%>
              <%--</sec:authorize>--%>
            <%--</div>--%>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <form action="" method="GET" id="tbl-filter">

                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon"><span class="icon" style="opacity: 0.4"><i class="fa fa-search-minus"></i></span></span>
                    <input type="text" id="search" name="search" class="form-control" placeholder="${placeholder}" value="${param.search}"/>
                  </div>
                </div>

                <div class="form-inline">
                  <jsp:include page="../include_component/date_range.jsp"/>

                  <div class='pull-right form-responsive bt-plt'>

                    <select class="form-control" name="status" style="font: inherit;height: 36px;">
                      <option value=""><spring:message code="select.service.status"/></option>
                      <option value="true" ${param.status eq 'true' ? 'selected' : '' }><spring:message code="select.service.active"/></option>
                      <option value="false" ${param.status eq 'false' ? 'selected' : '' }><spring:message code="select.service.inactive"/></option>
                    </select>

                    <jsp:include page="../include_component/search_service_type_one.jsp">
                      <jsp:param name="enableFiltering" value="false"/>
                    </jsp:include>

                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;<spring:message code="system.service.list.search.btn.search"/></button>
                    <%--<a href="?" class="btn nomal_color_bk bt-cancel"><spring:message code="system.service.list.search.btn.cancel"/></a>--%>
                  </div>

                </div>
                <div class="clearfix"></div>
              </form>

              <section class="panel search_payment panel-default">
                <div class="panel-body">
                  <div class="clearfix"></div>
                  <div class="pull-left mt-sm"
                       style="line-height: 30px;">
                  </div>
                  <div class="clr"></div>

                  <spring:message var="colServiceType"
                                  code="setting.account.tbl.col.status"/>
                  <spring:message var="colCode"
                                  code="setting.account.tbl.col.account.id"/>
                  <spring:message var="colName"
                                  code="setting.account.tbl.col.full.name"/>
                  <spring:message var="colPhone"
                                  code="setting.account.tbl.col.phone"/>
                  <spring:message var="colCustomerType"
                                  code="setting.account.tbl.col.customer.type"/>
                  <spring:message var="colWalletType"
                                  code="setting.account.tbl.col.wallet.type"/>
                  <spring:message var="colBlackListR"
                                  code="setting.account.tbl.col.blacklist.reason"/>
                  <spring:message var="colPositions"
                                  code="settion.account.tbl.col.position.list"/>
                  <spring:message var="colCreateTime"
                                  code="setting.account.tbl.col.created.at"/>
                  <spring:message var="colAction"
                                  code="setting.account.tbl.col.action"/>

                  <spring:message var="actionResetPass"
                                  code="label.reset.password"/>
                  <spring:message var="actionResendInfo"
                                  code="label.resend.info"/>
                  <spring:message var="actionChangeStatus"
                                  code="account.dialog.change.black.list.reason"/>

                  <div class="table-responsive">
                    <display:table name="listCustomer" id="item"
                                   requestURI="/customer/fee-type/specified/customers"
                                   pagesize="${pagesize}"
                                   partialList="true"
                                   size="total"
                                   sort="page"
                                   class="table table-bordered table-responsive table-striped mb-none">

                      <%@ include file="../include_component/display_table.jsp" %>

                      <display:column title="#"
                                      headerClass="fit_to_content"
                                      class="right">
                                    <span id="id${item.id}" class="rowid">
                                    <c:out value="${offset + item_rowNum}"/>
                                    </span>
                      </display:column>

                      <display:column
                              title="${colCode}"
                              style="vertical-align: middle"><a
                              class="detail-link link-active"
                              id="customer-link-${offset + item_rowNum}"
                              href="${contextPath}/customer/manage/details/${item.id}">${item.cif}</a></display:column>

                      <display:column title="${colName}">
                        <div class="user-info-box">
                          <div class="avt">
                            <img src="${contextPath}/assets/images/man.svg"
                                 class="img-circle list-user-avatar"
                                 data-id="${offset + item_rowNum}"
                                 data-toggle="modal">
                          </div>
                          <div class="user-info">
                            <p class="user-name">
                              <b>
                                <a id="customer-link-${offset + item_rowNum}"
                                   href="${contextPath}/customer/manage/details/${item.id}">
                                  <span class="last-name">${item.fullName}</span>
                                  <span class="first-name"></span>
                                </a>
                              </b>
                            </p>
                            <p>${item.email}</p>
                          </div>
                        </div>
                      </display:column>

                      <display:column
                              title="${colPhone}"
                              style="vertical-align: middle">${item.msisdn}</display:column>

                      <display:column
                              title="${colCustomerType}"
                              style="vertical-align: middle">${FULL_CUSTOMER_TYPES.get(item.customerType)}</display:column>

                      <display:column
                              title="${colWalletType}"
                              style="vertical-align: middle">
                        <c:if test="${item.walletType eq null}">
                          <div class="wallet-type]">N/A</div>
                        </c:if>
                        <c:if test="${item.walletType ne null && item.walletType != '' }">
                          <spring:message
                                  code="label.walletType.${item.walletType}"/>
                        </c:if>
                      </display:column>

                    </display:table>
                  </div>
                </div>
              </section>
            </div>
          </section>
        </div>
      </div>
      <!-- end: page -->
    </section>
    <jsp:include page="../include_page/footer.jsp"/>

  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<jsp:include page="../include_page/date_picker.jsp">
  <jsp:param name="isFullTime" value="true"/>
  <jsp:param name="autoFilterDate" value="true"/>
</jsp:include>

<script type="text/javascript">

  $(document).ready(function () {
    $('#table').treeTable({
      startCollapsed: false
    });

    $('<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>').insertAfter(".treetable-expander");
    $('.switch input[name=switch]').each(function () {
      var item = document.querySelector('#' + $(this).attr('id'));
      var color = '#64bd63';
      var switchery = new Switchery(item, {color: color, size: 'small'});
      if ($(this).disabled) {
        switchery.disable();
      }
    });
  });

  function createParentServiceCode(parentServiceId, serviceType, serviceLevel, serviceCode) {
    $('#parentServiceCodeEdit').html('refresh', true);
    var urlParentServicdCodeEdit = ctx + '/service/service-profile/listFeeEdit';
    $.ajax({
      type: 'POST',
      url: urlParentServicdCodeEdit,
      data: {
        parentServiceId: parentServiceId, serviceType: serviceType, serviceLevel: serviceLevel, serviceCode: serviceCode
      },
      success: function (data) {
        var listData = data.split(';');
        if (listData != null && listData != "" && listData instanceof Array) {
          var trueServices = JSON.parse(listData[0]);

          function formatResult(node) {
            var $result = $('<span style="padding-left:' + (20 * node.level) + 'px;">'
                + '<span style="color: #428bca;" class="icon node-icon glyphicon glyphicon-bookmark"></span>'
                + '&nbsp;' + node.text + ' - ' + node.code + '</span>');
            return $result;
          }

          $("#parentServiceCodeEdit").select2({
            dropdownParent: $("#edit"),
            placeholder: '<spring:message code="system.service.popup.create.lable.selectParentServiceCode"/>',
            width: "100%",
            data: trueServices,
            formatSelection: function (item) {
              return item.text + ' - ' + item.code
            },
            formatResult: function (item) {
              return item.id
            },
            templateResult: formatResult
          });
          $("#select2-parentServiceCodeEdit-container").html(listData[1]);
          $("#parentServiceCodeEdit").val(listData[2]);
        }
      }
    });
  }
</script>
</body>
</html>
