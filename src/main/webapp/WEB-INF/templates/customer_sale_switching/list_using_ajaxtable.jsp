<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 5/16/2020
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SenderType" %>
<%@ page
    import="static vn.mog.ewallet.operation.web.controller.customer.sale_switching.manage.SaleSwitchingManageController.URI_CUSTOMER_SALE_SWITCHING_MANAGE_LIST" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="label.switching.manage"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="dataTableCssV2" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_service.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
    <jsp:param name="jqueryV2" value="true"/>
    <jsp:param name="dataTableV2" value="true"/>
  </jsp:include>
  <link rel="stylesheet"
        href="<c:url value="/assets/stylesheets/datatable-update/select.dataTables.min.css"/>">
  <link rel="stylesheet"
        href="<c:url value="/assets/stylesheets/datatable-update/buttons.dataTables.min.css"/>">
  <style>
    .bgCheckbox {
      background-color: #8cbfe45e !important;
    }

    /*table.dataTable tr th.select-checkbox.selected::after {*/
    /*content: "✔";*/
    /*margin-top: -11px;*/
    /*margin-left: -4px;*/
    /*text-align: center;*/
    /*text-shadow: rgb(176, 190, 217) 1px 1px, rgb(176, 190, 217) -1px -1px, rgb(176, 190, 217) 1px -1px, rgb(176, 190, 217) -1px 1px;*/
    /*}*/
  </style>
</head>

<body>
<section class="body">
  <!--        ///////   header ////////-->
  <jsp:include page="../include_page/header.jsp"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="cus-sale-switching" name="nav"/>
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
                <li><span class=""><spring:message code="label.switching.manage"/></span>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message_new.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">

          <div class="form-inline">
            <div class="pull-left h4 mb-md mt-md">
              <spring:message code="label.switching.manage"/>
            </div>
            <div class="fr form-responsive">
            </div>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">
              <form action="" method="GET" id="tbl-filter" class="mb-md">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="form-group ml-none mr-none">
                  <div class="input-group input-group-icon">
                    <span class="input-group-addon">
                      <span class="icon" style="opacity: 0.4"><i
                          class="fa fa-search-minus"></i></span>
                    </span>
                    <input type="tel" id="search" name="quickSearch"
                           class="form-control"
                           placeholder="Tim kiếm theo số điện thoại sale"
                           pattern="[0-9]{10}"
                           oninvalid="this.setCustomValidity('Sai định dạng cho phép!')"
                           oninput="this.setCustomValidity('')"
                           value="${param.quickSearch}" required/>
                  </div>
                </div>
                <spring:message code="select.status" var="langStatus"/>
                <div class="form-inline">
                  <div class="pull-right form-responsive bt-plt">
                    <button type="submit" class="btn btn-primary ml-tiny"><i
                        class="fa fa-search"></i>&nbsp; <spring:message
                        code="common.btn.search"/></button>
                  </div>
                  <div class="clearfix"></div>
                </div>
              </form>

              <%--<div class="table-responsive">--%>
              <c:if test="${customers ne null}">
                <table class="table table-hover table-bordered dataTable table-striped w-full "
                       id="example" style="width: 100%">
                  <thead>
                  <tr>
                    <th></th>
                    <th>STT</th>
                    <th>ID</th>
                    <th>MÃ KH</th>
                    <th><spring:message code="lable.last.first.name"/></th>
                    <th><spring:message code="transaction.export.detail.summary.row.phone"/></th>
                    <th><spring:message code="system.service.popup.update.lable.customerType"/></th>
                    <th><spring:message code="label.wallet.type"/></th>
                    <th><spring:message code="select.positionList"/></th>
                    <th><spring:message code="setting.account.tbl.col.blacklist.reason"/></th>
                    <th><spring:message code="transfer.detail.createdAt"/></th>
                    <th><spring:message code="label.manage"/></th>
                  </tr>
                  </thead>

                  <tbody>
                  <c:forEach var="item" items="${customers}"
                             varStatus="rowId">
                    <tr>
                      <td></td>
                      <td><span id="row${rowId.count}" class="rowid">
                                <c:out value="${rowId.count}"/>
                            </span></td>
                      <td>${item.id}</td>
                      <td>${item.cif}</td>
                      <td>${item.displayName}</td>
                      <td>${item.msisdn}</td>
                      <td>${item.customerType.name}</td>
                      <td>${item.walletTypeId eq 1 ? 'ACCOUNT' : 'WALLET'}</td>
                      <td>N/A</td>
                      <td>${item.description}</td>
                      <td><fmt:formatDate pattern="HH:mm:ss yyyy-MM-dd"
                                          value="${item.created}"/></td>
                      <td>${item.parentName}</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </c:if>
              <%--</div>--%>

              <!-- Modal resend -->
              <c:if test="${customers ne null && fn:length(customers) > 0}">
                <div class="m-xs"></div>
                <jsp:include page="change_manager_modal.jsp"/>
              </c:if>
              <!-- Modal resend -->
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
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selVerifiableSatus" value="true"/>
  <jsp:param name="selCustomerType" value="true"/>
  <jsp:param name="selKycType" value="true"/>
</jsp:include>
</body>
<script
    src="<c:url value="/assets/javascripts/datatable-update/dataTables.select.min.js"/>"></script>
<script
    src="<c:url value="/assets/javascripts/datatable-update/dataTables.buttons.min.js"/>"></script>
<script>
  var idColumn = 2;
  $(document).ready(function () {
    // Do this before you initialize any of your modals when using select2 inside modal
    $.fn.modal.Constructor.prototype.enforceFocus = function () {
    };
    // end

    var table = $('#example').DataTable({
      dom: 'Bfrtip',
      buttons: [
        'selectAll',
        'selectNone'
      ],
      select: true,
      select: {
        style: 'multi'
      },
      columnDefs: [{
        orderable: false,
        className: 'select-checkbox',
        targets: 0
      }],
      "searching": false,
      scrollX: "true",
      "pageLength": 50,
      language: {
        buttons: {
          selectAll: "Chọn tất cả",
          selectNone: "Hủy chọn tất cả"
        },
        "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
        "sLengthMenu": "_MENU_ ${paging_records}",
        "search": "${search_showing}",
        "paginate": {
          "previous": "${paging_previous}",
          "next": "${paging_next}"
        },
        "emptyTable": "<spring:message code="table.basic.msg.empty"/>"
      }
    });
    $('#example').css("font-size", "14px");
    table.columns.adjust().draw();

    table.on('select', function (e, dt, type, indexes) {
      if (type === 'row') {
        var data = table.rows(indexes).data();
        // do something with the ID of the selected items
        data.map(function (value) {
          ids.add(value[idColumn]);
        })
      }
    });

    table.on('deselect', function (e, dt, type, indexes) {
      if (type === 'row') {
        var data = table.rows(indexes).data();
        // do something with the ID of the selected items
        data.map(function (value) {
          ids.delete(value[idColumn]);
        })
      }
    });
  });
</script>
</html>

