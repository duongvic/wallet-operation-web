<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="report.mpo.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <jsp:include page="../include_page/js_merchant.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="dateLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <style>
    .modal-header { padding-bottom: 1px; } .table-responsive { display: flex; font-size: 14px; border-bottom: 1px solid #e5e5e5; } .row-B { background-color: #e6f9e4; }
  </style>
</head>
<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>
  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="reconciliation-mpoReport" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="report.mpo.navigate.reconciliation"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="report.mpo.subnavigate.title"/>
          </div>
          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none">

              <form action="" method="get" id="tbl-filter">

                <spring:message code="select.choose.all" var="langChooseAll"/>
                <spring:message code="select.merchant" var="langMerchant"/>
                <spring:message code="select.facevalue" var="langFacevalue"/>
                <spring:message code="select.cardtype" var="langCardtype"/>
                <spring:message code="report.mpo.search.btn.search" var="btnSearch"/>
                <spring:message code="report.mpo.search.btn.export" var="btnExport"/>
                <spring:message code="select.status" var="langStatus"/>
                <spring:message code="select.choose.all" var="langChooseAll"/>

                <div class="form-inline">

                  <jsp:include page="../include_component/date_range.jsp"/>
                  <div class='pull-right form-responsive bt-plt'>
                    <jsp:include page="../include_component/search_customer.jsp"/>

                    <select class="form-control multiple-select" id="status" name="status">
                      <c:choose>
                        <c:when test="${not empty listStatus}">
                          <c:forEach var="item" items="${listStatus}">
                            <option value="${item.value()}" ${item.cssValue(status)}>
                              <spring:message code="${item.displayText()}"/>
                            </option>
                          </c:forEach>
                        </c:when>
                        <c:otherwise>
                          <option value="">N/A</option>
                        </c:otherwise>
                      </c:choose>
                    </select>
                    <input type="submit" value="${btnSearch}" class="btn btn-success "/>
                  </div>
                </div>
              </form>

              <div class="clearfix"></div>

              <div class="panel-body">
                <c:if test="${totalMpo > 0}">

                  <div class="panel-title">
                    <div class="modal-header">
                      <h4 class="modal-title">
                        <b><spring:message code="report.mpo.summary.title"/></b>
                      </h4>
                    </div>
                    <div class="modal-body" style="font-size: 14px;">
                      <div class="form-group">
                        <label class="col-md-3"> <spring:message
                            code="report.mpo.summary.date.range"/>
                        </label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color" align="right">${searchRange}</p>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="col-md-3"><spring:message
                            code="report.mpo.summary.merchant"/></label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color" align="right">${totalMerchant}</p>
                        </div>
                      </div>

                      <div class="form-group">
                        <label class="col-md-3"><spring:message
                            code="report.mpo.summary.total.mpo"/></label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color" align="right">${totalMpo}</p>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-md-3"><spring:message
                            code="report.mpo.summary.totalQuantity"/></label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color" align="right">${totalMpoCardQuantity}</p>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-md-3"><spring:message
                            code="report.mpo.summary.totalFacevalue"/></label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color total-am" align="right">${totalMpoFaceValue}</p>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-md-3"><spring:message
                            code="report.mpo.summary.totalFacevalue.text"/></label>
                        <div class="col-md-6 " style="font-weight: 600">
                          <p class="primary_color total-am" align="right">
                            <c:out value="${ewallet:numberToText(totalMpoFaceValue)}"></c:out>
                          </p>
                        </div>
                      </div>
                    </div>


                    <div class="modal-header">
                      <h4 class="modal-title">
                        <b><spring:message code="report.mpo.detail.title"/></b>
                      </h4>
                    </div>


                    <c:set var="tQuantity" value="0" scope="page"/>
                    <c:set var="tFacevalue" value="0" scope="page"/>
                    <c:forEach items="${hashMap}" var="itemHash">
                      <div class="table-responsive qlsp">

                        <table class="table table-bordered table-striped mb-none">
                          <thead>
                          <tr>
                            <th><c:out
                                value="${ewallet:getNameTelco(itemHash.key) }"></c:out></th>
                            <th style="text-align: right; width: 30%;"><spring:message
                                code="report.mpo.table.column.cardtype"/></th>
                            <th style="text-align: right; width: 30%;"><spring:message
                                code="report.mpo.table.column.quantity"/></th>
                            <th style="text-align: right; width: 30%;"><spring:message
                                code="report.mpo.table.column.facevalue"/></th>

                          </tr>
                          </thead>
                          <tbody>
                          <c:set var="sumQuantity" value="0" scope="page"/>
                          <c:set var="sumFacevalue" value="0" scope="page"/>
                          <c:forEach items="${itemHash.value}" var="item"
                                     varStatus="var2">
                            <tr
                                class="${(var2.index == 0 || var2.index % 2 == 0) ? 'row-A' : 'row-B' }">
                              <c:if test="${var2.index == 0 }">
                                <td rowspan="100"></td>
                              </c:if>

                              <td style="text-align: right;">${ewallet:formatNumber(item.faceValue)}</td>
                              <td style="text-align: right;">
                                  ${ewallet:formatNumber(item.quantity)}
                                <c:set var="sumQuantity" value="${sumQuantity + item.quantity}" scope="page"/>
                              </td>
                              <td style="text-align: right;">
                                  ${ewallet:formatNumber(item.faceValue * item.quantity)}
                                <c:set var="sumFacevalue" value="${sumFacevalue + (item.faceValue * item.quantity)}" scope="page"/></td>
                            </tr>
                          </c:forEach>
                          <tr>
                            <c:set var="tQuantity" value="${tQuantity + sumQuantity}" scope="page"/>
                            <c:set var="tFacevalue" value="${tFacevalue + sumFacevalue}" scope="page"/>
                            <td></td>
                            <td style="text-align: right;"><b>${ewallet:formatNumber(sumQuantity)}</b></td>
                            <td style="text-align: right;"><b>${ewallet:formatNumber(sumFacevalue)}</b></td>
                          </tr>
                          </tbody>

                        </table>
                      </div>
                    </c:forEach>

                    <div class="table-responsive qlsp no-per-page">
                      <table
                          class="table table-bordered table-striped mb-none mt-none">
                        <tbody>
                        <tr class="row-A">
                          <td><spring:message code="report.mpo.detail.total"/></td>
                          <td style="text-align: right; width: 30%;">&nbsp;</td>
                          <td style="text-align: right; width: 30%;"><b>${ewallet:formatNumber(tQuantity)}</b></td>
                          <td style="text-align: right; width: 30%;"><b>${ewallet:formatNumber(tFacevalue)}</b>
                          </td>
                        </tr>
                        <tr class="row-B">
                          <td style="width: 20%;"><spring:message text="" code="report.mpo.detail.total.text"/></td>
                          <td style="text-align: right; width: 80%;" colspan="3"><b><c:out value="${ewallet:numberToText(tFacevalue)}"></c:out> </b></td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </c:if>
              </div>
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
<jsp:include page="../include_component/search_form_commons.jsp">
  <jsp:param name="selCustomer" value="true"/>
</jsp:include>
<jsp:include page="../include_page/date_picker.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    var href = document.URL + '';
    if (href.indexOf('?') > 0) {
      href += '&export=true';
    } else {
      href += '?export=true';
    }
    $('.export_link').attr('href', href);
    $('.export_link')
    .click(function () {
      $.MessageBox({
        buttonDone: '<spring:message code="popup.button.yes"/>',
        buttonFail: '<spring:message code="popup.button.no"/>',
        message: '<spring:message code="popup.message.confirm.download.file"/>'
      }).done(function () {
        return true;
      });
      return false;
    });
  });
</script>
</body>

</html>


