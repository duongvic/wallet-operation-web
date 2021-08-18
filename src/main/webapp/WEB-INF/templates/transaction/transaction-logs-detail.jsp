<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_LIST" %>
<%@ page
        import="static vn.mog.ewallet.operation.web.controller.translog.TransactionLogController.TRANSACTION_CONTROLLER" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <!-- Basic -->
    <meta charset="UTF-8">
    <title><spring:message code="transaction.api.navigate.transaction.detail"/></title>
    <jsp:include page="../include_page/head.jsp"/>
    <jsp:include page="../include_page/js.jsp">
        <jsp:param name="tableLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <jsp:include page="../include_component/constant_application.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="hisTxn" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->


        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                        <form action="<%=TRANSACTION_LIST%>" method="GET" id="search-transaction">
                            <input type="hidden" name="quickSearch" value="${quickSearch}">
                            <c:forEach var="st" items="${customerIds}">
                                <input type="hidden" name="customerIds" value="${st}">
                                <input type="hidden" name="multiselect" value="${st}">
                            </c:forEach>
                            <c:forEach var="st" items="${txnStatusIds}">
                                <input type="hidden" name="txnStatusIds" value="${st}">
                            </c:forEach>
                            <input type="hidden" name="type" value="${type}">
                            <c:forEach var="st" items="${service}">
                                <input type="hidden" name="service" value="${st}">
                            </c:forEach>
                            <c:forEach var="st" items="${provider}">
                                <input type="hidden" name="provider" value="${st}">
                            </c:forEach>

                            <input type="hidden" name="range" value="${range}">
                            <input type="hidden" name="d-49489-p" value="${numberPage}">

                            <div class="page-header-left">
                                <ol class="breadcrumbs">
                                    <li><a href="#"> <i class="fa fa-home"></i></a></li>
                                    <li><span><spring:message
                                            code="transaction.api.navigate.transaction"/></span>
                                    </li>
                                    <li><span><a href="#" id="hight-title"
                                                 class="hight-title"><spring:message
                                            code="transaction.api.navigate.apitransaction"/></a></span>
                                    </li>
                                    <li><span class="nav-active"><spring:message
                                            code="transaction.api.navigate.transaction.detail"/> </span>
                                    </li>
                                </ol>
                            </div>
                        </form>
                    </div>
                </div>
            </header>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="h4 mb-md">
                        <spring:message code="transaction.api.navigate.transaction.detail"/>
                    </div>

                    <jsp:include page="transaction-logs-detail-common.jsp"/>
                    <jsp:include page="transaction-logs-detail-attribute.jsp"/>

                    <c:if test="${transaction.isBuyCard()}">
                        <section class="panel panel-default">
                            <div class="panel-title pl-none">
                                <h4 class="fl"><spring:message
                                        code="transaction.api.detail.list-item"/></h4>
                                <ul class="panel-tools fl tool-filter">
                                    <li><a class="icon minimise-tool"><i
                                            class="fa fa-chevron-down text-xs"></i></a></li>
                                </ul>
                                <div class="clr"></div>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive qlsp no-per-page">
                                    <table class="table table-bordered table-striped mb-small mt-none">
                                        <thead>
                                        <tr>
                                            <th class="stt"><spring:message
                                                    code="transaction.api.detail.table.no"/></th>
                                            <th style="width:250px"><spring:message
                                                    code="transaction.api.detail.table.serial"/></th>
                                            <th><spring:message
                                                    code="transaction.api.detail.table.expiry"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${transaction.serials }" var="serial"
                                                   varStatus="var2">
                                            <tr>
                                                <td>${var2.index + 1 }</td>
                                                <td>${serial.serial }</td>
                                                <td><fmt:formatDate value="${serial.expiredDate }"
                                                                    pattern="dd/MM/yyyy"/></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </section>
                    </c:if>


                    <sec:authorize access="hasAnyRole('ADMIN_OPERATION',
                        'FINANCE',
                        'SALESUPPORT_LEADER', 'SALESUPPORT',
                        'RECONCILIATION','RECONCILIATION_LEADER',
                        'CUSTOMERCARE_MANAGER',
                        'SALE_ASM','SALE_DIRECTOR',
                        'TECH_SUPPORT')">
                        <jsp:include page="transaction-logs-detail-event.jsp"/>
                    </sec:authorize>
                </div>
            </div>
        </section>


        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script>

  <%--function openModal(txId) {--%>
    <%--$('#txId').text(txId);--%>
    <%--$('#txIdInput').val(txId);--%>
    <%--document.getElementById("myForm").reset();--%>
    <%--$('#napDTKH').modal('show');--%>
  <%--}--%>

  <%--function confirmTopup() {--%>
    <%--if($('#topup-confirm').is(':checked')){--%>
      <%--handleClick();--%>
    <%--} else {--%>
      <%--$.MessageBox("<spring:message code="common.not.confirm.the.action"/>")--%>
    <%--}--%>
  <%--}--%>

  <%--function handleClick() {--%>
    <%--var provider = $( "select#provider option:checked" ).val();--%>
    <%--$.ajax({--%>
      <%--method:'POST',--%>
      <%--url: "${contextPath}<%=TRANSACTION_CONTROLLER%>/get-phone-topup-transaction-on-hold",--%>
      <%--data: {--%>
        <%--txId : $('#txIdInput').val(),--%>
        <%--provider: provider,--%>
        <%--noteContent: $('#note-content').val()--%>
      <%--},--%>
      <%--success: function (data) {--%>
        <%--$.MessageBox("Success");--%>

        <%--setTimeout(function(){--%>
          <%--location.reload(true);--%>
        <%--}, 1000);--%>
      <%--},--%>
      <%--error: function (data) {--%>
        <%--if(data.responseJSON)--%>
          <%--if(data.responseJSON.status)--%>
            <%--$.MessageBox(data.responseJSON.status.value);--%>
        <%--else $.MessageBox(data.responseJSON.message);--%>

        <%--setTimeout(function(){--%>
          <%--location.reload(true);--%>
        <%--}, 1000);--%>
      <%--}--%>
    <%--})--%>
  <%--}--%>


  $(document).ready(function () {
    /*$('#hight-title').click(function () {
      $('#search-transaction').submit();
    });*/
  });

  $('#btnUpdateTxtHold').click(function () {
    var txtId = '${transaction.id}';
    console.log('txtId=', txtId);
    var urlChangeStatus = ctx + '/ajax-controller/changeStatus/hold/success';
    $.MessageBox({
          buttonDone: '<spring:message code="popup.button.yes"/>',
          buttonFail: '<spring:message code="popup.button.no"/>',
          message: '<spring:message code="popup.message.change.status.hold.success"/>'
        }
    ).done(function () {
      $.ajax({
        type: 'POST',
        url: urlChangeStatus,
        data: {
          txtId: txtId,
        },
        timeout: 60000,
        success: function (data) {
          if (data == null || data == "") {
            $.MessageBox({message: 'API BackEnd Change status failed!'})
          } else {
            if (data.status.code == 0) {
              //success
              $.MessageBox({message: 'Success'})
              location.reload();
            } else {
              $.MessageBox({message: 'API BackEnd Change status failed!'})
            }
          }
        }
      });
    })
    return false;
  });
</script>
</body>
</html>