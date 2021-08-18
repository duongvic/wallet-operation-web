<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/12/2021
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include_page/taglibs.jsp" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">
<head>
    <!-- Basic -->
    <meta charset="UTF-8"/>
    <%--<title>Customer Verification Detail</title>--%>
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
    <link rel="stylesheet" href="<c:url value="/assets/javascripts/flatpickr/flatpickr.css"/>">
    <style>
        .clock {
            position: relative;
            left: 95%;
            bottom: 30px;
        }
    </style>
</head>
<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-type-noti-partner-ls" name="nav"/>
        </jsp:include>
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="common.customer"/></span></li>
                                <li><span><spring:message
                                        code="menu.left.customer.notification"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.customer.notification.send"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message_new.jsp"/>

            <div class="content-body-wrap">
                <div class="container-fluid">
                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none">
                            <div class="tab-content">
                                <div id="tab-list-customer" class="tab-pane active">
                                    <form id="form">
                                        <div class="panel-title">
                                            <div class="row">
                                                <div class="col-lg-2 col-md-2 col-sm-6 text-left">
                                                    <h4 style="margin-bottom: 1.5rem">Push Notification</h4>
                                                </div>
                                                <div class="col-lg-10 col-md-10 col-sm-6 text-right">
                                                    <button class="btn btn-sm btn-success" id="btn-submit"
                                                            type="button">
                                                        <spring:message
                                                                code="label.button.notification"/></button>
                                                    <button class="btn btn-sm btn-default" id="btn-reset" type="button"
                                                            onclick="resetForm()"><spring:message
                                                            code="label.reset"/></button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.message.type"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <select class="form-control w100" id="event">
                                                            <option value="" disabled selected><spring:message
                                                                    code="label.message.type"/></option>
                                                            <c:forEach var="eventEnum" items="${eventEnumList}">
                                                                <option value="${eventEnum}">
                                                                    <spring:message code="${eventEnum.getName()}"/>
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="transaction.api.detail.servicetype"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <select class="form-control w100" id="serviceType">
                                                            <option value="" disabled selected><spring:message
                                                                    code="transaction.api.detail.servicetype"/></option>
                                                            <c:forEach var="serviceTypeEnum"
                                                                       items="${serviceTypeEnumList}">
                                                                <option value="${serviceTypeEnum}">
                                                                    <spring:message
                                                                            code="${serviceTypeEnum.getName()}"/>
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.service.type"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <select class="form-control w100" id="service">
                                                            <option value="" selected disabled><spring:message
                                                                    code="label.service.type"/></option>
                                                            <c:forEach var="serviceEnum" items="${serviceEnumList}">
                                                                <option value="${serviceEnum}">
                                                                    <spring:message code="${serviceEnum.getName()}"/>
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row hidden" id="detailNameRow">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.service.detail.name"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <input type="text" class="form-control" id="serviceDetailName"
                                                               disabled>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.message.heading"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <input type="text" class="form-control" id="subject">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.from.date"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <input class="selector form-control" id="fromDate"
                                                               style="background-color: white">
                                                        <i class="fa fa-clock-o clock"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.to.date"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <input class="selector form-control" id="toDate"
                                                               style="background-color: white">
                                                        <i class="fa fa-clock-o clock"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        <spring:message code="label.message.content"/>
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                <textarea class="form-control" style="min-height: 170px" id="content"><spring:message
                        code="label.message.content"/></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                    <label class="col-sm-12 col-md-4 col-lg-4">
                                                        refRequestId
                                                    </label>
                                                    <div class="col-sm-12 col-md-8 col-lg-8">
                                                        <p>${refRequestId}</p>
                                                        <input type="hidden" id="refRequestId" value="${refRequestId}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <h4><spring:message code="label.previous.notification"/></h4>
                                <div class="table-responsive">
                                    <spring:message var="colRequestId" code="label.notification.code"/>
                                    <spring:message var="colCreateTime"
                                                    code="setting.account.tbl.col.created.at"/>
                                    <spring:message var="colHeading"
                                                    code="table.column.heading"/>
                                    <spring:message var="colContent"
                                                    code="table.column.content"/>
                                    <spring:message var="serviceLbl"
                                                    code="menu.left.service"/>
                                    <spring:message var="serviceTypeLbl"
                                                    code="transaction.api.table.service.type"/>
                                    <spring:message var="colUpdate"
                                                    code="label.update.notification"/>
                                    <spring:message var="colAct" code="provider.service.table.action"/>
                                    <spring:message var="colRefRequestId" code="label.notification.reference.code"/>
                                    <spring:message var="colEventType" code="label.message.type"/>
                                    <table class="table table-bordered table-responsive table-striped mb-none"
                                           id="myTable">
                                        <thead>
                                        <th>${colCreateTime}</th>
                                        <th>${colEventType}</th>
                                        <th>${serviceLbl}</th>
                                        <th>${serviceTypeLbl}</th>
                                        <th>${colHeading}</th>
                                        <th>${colContent}</th>
                                        <th>${colRequestId}</th>
                                        <th>${colRefRequestId}</th>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${notifications}" var="notification">
                                            <tr>
                                                <td><fmt:formatDate value="${notification.requestTime}"
                                                                    pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                                <td><spring:message code="${notification.getEvent().getName()}"/></td>
                                                <td><spring:message code="${notification.getService().getName()}"/></td>
                                                <td><spring:message
                                                        code="${notification.getServiceType().getName()}"/></td>
                                                <td>${notification.subject}</td>
                                                <td>${notification.content}</td>
                                                <td>${notification.requestId}</td>
                                                <td>${notification.refRequestId}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script src="<c:url value="/assets/javascripts/flatpickr/flatpickr.js"/>"></script>
<script src="<c:url value="/assets/javascripts/flatpickr/vn.js"/>"></script>
<script>
    $(document).ready(function () {
        $(".selector").flatpickr({
            enableTime: true,
            dateFormat: "d/m/Y H:i:ss",
            locale: "vn"
        });
    });

    $("#btn-submit").click(function () {
        let event = $("#event").val();
        let service = $("#service").val();
        let serviceDetailName = $("#serviceDetailName").val();
        let subject = $("#subject").val();
        let fromDate = $("#fromDate").val();
        let toDate = $("#toDate").val();
        let content = $("#content").val();
        let serviceType = $("#serviceType").val();
        let signature = $("#signature").val();
        let refRequestId = $("#refRequestId").val();

        $.ajax({
            type: 'POST',
            url: ctx + "/ajax-controller/partner/notification/send",
            data: {
                content: content,
                event: event,
                fromDate: fromDate,
                toDate: toDate,
                service: service,
                serviceType: serviceType,
                serviceDetailName: serviceDetailName,
                signature: signature,
                subject: subject,
                refRequestId: refRequestId,
            },
            success: function () {
                $.MessageBox({message: "Success!"}).done(function () {
                    location.reload();
                });
            },
            error: function (data) {
                if (data.responseJSON?.status?.value) {
                    $.MessageBox({message: data.responseJSON.status.value});
                } else {
                    $.MessageBox({message: data.responseJSON.code + " " + data.responseJSON.msg});
                }
            }
        })
    })

    function resetForm() {
        document.getElementById("form").reset();
    };

    $('#event').change(function () {
        $('#subject').val($('#event option:selected').text().trim());
        generateMsg();
    })

    $('#fromDate').change(function () {
        generateMsg();
    })

    $('#toDate').change(function () {
        generateMsg();
    })

    $('#serviceType').change(function () {
        generateMsg();
    })

    $('#serviceDetailName').keyup(function () {
        generateMsg();
    })
    $('#service').change(function () {
        var service = $('#service').val();
        if (service === 'OTHER') {
            $('#detailNameRow').removeClass("hidden");
            $('#serviceDetailName').removeAttr("disabled");
        } else {
            if ($('#detailNameRow').hasClass("hidden") && $('#serviceDetailName').attr("disabled")) {
                //do nothing
            } else {
                $('#detailNameRow').addClass("hidden");
                $('#serviceDetailName').attr("disabled", true);
            }
        }
        generateMsg();
    })

    function generateMsg() {
        let event = $('#event option:selected').text().trim();
        let fromDate = $('#fromDate').val();
        let serviceType = $('#serviceType option:selected').text().trim();
        let toDate = $('#toDate').val();
        let serviceName = null;
        let serviceCode = $('#service option:selected').val();
        if (serviceCode === 'OTHER') {
            serviceName = $('#serviceDetailName').val().trim();
        } else {
            serviceName = $('#service option:selected').text().trim();
        }
        let content = event + " " + serviceType + " " + serviceName;
        if (fromDate) {
            if (content.includes(fromDate)) {
                //do nothing
            } else {
                content += " <spring:message code="label.from"/> " + fromDate;
            }
        }
        if (fromDate && toDate) {
            if (content.includes(toDate)) {
                //do nothing
            } else {
                content += " <spring:message code="label.to"/> " + toDate;
            }
        }
        $('#content').val(content);
    }

    $(document).ajaxStart(function (event, xhr, settings) {
        document.getElementById("btn-submit").innerHTML = "Loading...";
        document.getElementById("btn-submit").disabled = true;
        document.getElementById("btn-reset").innerHTML = "Loading...";
        document.getElementById("btn-reset").disabled = true;
    });
    $(document).ajaxComplete(function (event, xhr, settings) {
        document.getElementById("btn-submit").innerHTML = "<spring:message code="label.button.notification"/>";
        document.getElementById("btn-submit").disabled = false;
        document.getElementById("btn-reset").innerHTML = "<spring:message code="label.reset"/>";
        document.getElementById("btn-reset").disabled = false;
    });
</script>
</body>
</html>

