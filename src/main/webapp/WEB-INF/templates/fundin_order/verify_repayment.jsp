<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 4/8/2020
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType" %>
<%@ page
        import="vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="stage.finance.manager.verify.repayment.done"/></title>
    <%--<jsp:include page="../include_page/head.jsp"/>--%>
    <jsp:include page="../include_page/js.jsp"/>
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
    <style>
        .daterangepicker :hover {
            cursor: pointer;
        }
        td.disabled {
            opacity: 0.5;
        }
    </style>
</head>

<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <c:set var="finance_approve"
           value="<%= FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_APPROVE.code %>"/>
    <c:set var="finance_submit"
            value="<%= FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_REJECTED.code%>"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="orderfund_in" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="fundin.header"/> </span></li>
                                <li><span><a href="#" id="hight-title"
                                             class="hight-title"><spring:message
                                        code="fundin.request"/></a></span></li>
                                <li><span class="nav-active">
                                     <spring:message code="stage.finance.manager.verify.repayment.done"/>
                                    </span>
                                </li>
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
                            <%--<div class="h4 mb-md">--%>
                                        <%--<spring:message--%>
                                                <%--code="fundin.order.step.approve"/>&nbsp;<span class="primary_color">(&nbsp;<spring:message--%>
                                            <%--code="${fundInOrders.getTextOrderChannel()}"/>&nbsp;)</span>--%>
                            <%--</div>--%>
                        </div>
                        <div class="tab-content">
                            <section class="panel search_payment panel-default">
                                <form action="" method="post"  enctype="multipart/form-data">
                                    <input type="hidden" name="id" value="${id}"/>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.account"/> </label>
                                            <div class="col-md-5">${fundInOrders.username }</div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.channel"/> </label>
                                            <div class="col-md-5">${fundInOrders.orderChannel }</div>
                                        </div>

                                        <c:set var="channelBank"
                                               value="<%= FundOrderChannelType.BANK_TRANSFER.code %>"/>
                                        <c:if test="${fundInOrders.orderChannel eq channelBank }">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><spring:message
                                                        code="fundin.order.bank"/> </label>
                                                <div class="col-md-5">${fundInOrders.bankName }</div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><spring:message
                                                        code="fundin.order.commandCode"/> </label>
                                                <div class="col-md-5">${fundInOrders.commandCode }</div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label"><spring:message
                                                        code="fundin.order.syntax"/> </label>
                                                <div class="col-md-5">${fundInOrders.syntax }</div>
                                            </div>
                                        </c:if>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.request"/> </label>
                                            <div class="col-md-5">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.fee"/> </label>
                                            <div class="col-md-6">${ewallet:formatNumber(fundInOrders.fee)}&nbsp;(VNĐ)</div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.amount"/> </label>
                                            <div class="col-md-6">${ewallet:formatNumber(fundInOrders.amount)}&nbsp;(VNĐ)</div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.balance"/> </label>
                                            <div class="col-md-6">${ewallet:formatNumber(customerCurrentBalance)}&nbsp;(VNĐ)</div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.formStock"/> </label>
                                            <div class="col-md-6">
                                                <c:forEach var="item" items="${attachments }">
                                                    <p><img alt=""
                                                            src="data:image/png;base64, <c:out value='${item.imageBase64}'/>"
                                                            style="max-width: 90%;"></p> <br/>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <!-- div show file -->
                                        <div class="form-group">
                                            <div class="col-md-3"></div>
                                            <div class="col-md-6 fileshow"></div>
                                        </div>

                                        <%--trạng thái nạp chịu--%>

                                        <c:choose>
                                            <c:when test="${fundInOrders.isDept eq true}">

                                                <div class="form-group">
                                                    <label class="col-md-3 control-label"><spring:message
                                                            code="label.pay"/> : </label>
                                                    <div class="col-md-6">
                                                        <p style="color: red"><spring:message
                                                                code="popup.button.yes"/></p>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label"><spring:message
                                                            code="label.pay"/> : </label>
                                                    <div class="col-md-6">
                                                        <p style="color: red"><spring:message
                                                                code="popup.button.no"/></p>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>


                                        <%--ngày dự kiến trả nợ--%>
                                        <div class="form-group">
                                                <c:if test="${fundInOrders.isDept eq true}">
                                                    <label class="col-md-3 control-label">
                                                        <spring:message
                                                                code="label.fundin.request.expected.date.pay"/> :
                                                    </label>
                                                    <div class="col-md-2" style="display: flex">
                                                        <fmt:formatDate pattern="dd/MM/yyyy"
                                                                        value="${fundInOrders.expectedRepaymentDate}"/>
                                                    </div>
                                                </c:if>
                                        </div>
                                        <%--ngày trả nợ thực tế--%>
                                        <c:if test="${fundInOrders.stage == finance_approve }">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">
                                                    <spring:message
                                                            code="label.fundin.request.date.pay"/>&nbsp(*) :
                                                </label>
                                                <div class="col-md-2" style="display: flex">
                                                    <input type="text" autocomplete="off"
                                                           placeholder="dd/mm/yyyy"
                                                           class="form-control single-date-picker"
                                                           name="payDate" id="payDate"><i
                                                        class="fa fa-2x fa-calendar"
                                                        style="margin: 2px"></i>
                                                </div>
                                            </div>
                                        </c:if>

                                        <%--chứng từ--%>
                                            <c:if test="${fundInOrders.stage == finance_approve }">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label"><spring:message
                                                            code="label.document"/>&nbsp(*)</label>
                                                    <div class="col-md-6">
                                                        <input type="file" name="fileUpload" id="fileUpload" class="inputfile"/>
                                                    </div>
                                                </div>
                                            </c:if>


                                        <div class="form-group">
                                            <label class="col-md-3 control-label"><spring:message
                                                    code="fundin.order.reason"/>&nbsp(*)</label>
                                            <div class="col-md-5"><textarea rows="5" name="remark"
                                                                            class="form-control">${fundInOrders.info}</textarea>
                                            </div>
                                        </div>

                                        <div class="alert alert-default mb-none mt-md p-sm"
                                             style="display: flex">
                                            <div class="checkbox-custom checkbox-success">
                                                <input type="checkbox" name="ckaccess"
                                                       id="checkboxExample3">
                                                <label for="checkboxExample3"><spring:message
                                                        code="fundin.order.confirm"/> </label>
                                            </div>
                                        </div>

                                        <spring:message code="common.btn.processing.submit"
                                                        var="waitting"/>
                                        <div class="form-group pull-right mt-md">
                                            <input type="hidden" name="action"/>
                                            <c:if test="${fundInOrders.stage == finance_approve }">
                                            <button type="submit" action="reject"
                                                    class="btn btn-danger"
                                                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                                                <i class="fa fa-times-circle"
                                                   aria-hidden="true"></i>&nbsp;<spring:message
                                                    code="common.btn.reject"/></button>

                                                <button type="submit" action="approve"
                                                        id="btnApprove" class="btn btn-success"
                                                        data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                                                    <i class="fa fa-arrow-right"
                                                       aria-hidden="true"></i>&nbsp;<spring:message
                                                        code="common.btn.approve"/></button>
                                            </c:if>
                                            <c:if test="${fundInOrders.stage == finance_submit}">
                                                <button type="submit" action="submit"
                                                        id="btnApprove" class="btn btn-success"
                                                        data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}">
                                                    <i class="fa fa-arrow-right"
                                                       aria-hidden="true"></i>&nbsp;<spring:message
                                                        code="common.btn.approve"/></button>
                                            </c:if>
                                        </div>
                                    </div>
                                    <sec:csrfInput/>
                                </form>
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
        $.MessageBox({message: "<spring:message code="common.not.confirm.the.action"/>"});
        return false;
      }

      $("input[name=action]").val($(this).attr("action"));
      if ($("form input[name=action]").val() == 'reject') {
        if ($("form textarea[name=remark]").val().length < 1) {
          $.MessageBox({message: "<spring:message code="common.fill.in.the.reason"/>"});
          return false;
        }
      }
    });

    var nowDate = new Date('<fmt:formatDate pattern="yyyy,MM,dd" value="${fundInOrders.createdTime}"/>');
    var createdDate = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 12, 00);
    var payDate = $('#payDate');
    payDate.daterangepicker({
      autoUpdateInput: false,
      singleDatePicker: true,
      showDropdowns: true,
      minDate: createdDate,
      locale: {
        format: 'DD/MM/YYYY'
      }
    }, function (chosen_date) {
      payDate.val(chosen_date.format('DD/MM/YYYY'));
    });

  });

</script>
</body>
</html>

