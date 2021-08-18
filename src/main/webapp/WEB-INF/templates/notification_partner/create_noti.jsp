<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel-title">
    <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-6 text-left">
            <h4 style="margin-bottom: 1.5rem">Push Notification</h4>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-6 text-right">
            <button class="btn btn-sm btn-success" id="btn-submit" type="button"><spring:message
                    code="label.button.notification"/></button>
            <button class="btn btn-sm btn-default" id="btn-reset" type="button" onclick="resetForm()"><spring:message
                    code="label.reset"/></button>
        </div>
    </div>
</div>
<div class="panel-body">
    <div class="row">
        <div class="form-group col-md-6 col-lg-6 col-sm-12">
            <label class="col-sm-12 col-md-4 col-lg-4">
                <spring:message code="label.message.type"/>&nbsp;*
            </label>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <select class="form-control w100" id="event">
                    <option value="" disabled selected><spring:message code="label.message.type"/></option>
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
                <spring:message code="transaction.api.detail.servicetype"/>&nbsp;*
            </label>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <select class="form-control w100" id="serviceType">
                    <option value="" disabled selected><spring:message
                            code="transaction.api.detail.servicetype"/></option>
                    <c:forEach var="serviceTypeEnum" items="${serviceTypeEnumList}">
                        <option value="${serviceTypeEnum}">
                            <spring:message code="${serviceTypeEnum.getName()}"/>
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
                    <option value="" selected disabled><spring:message code="label.service.type"/></option>
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
                <input type="text" class="form-control" id="serviceDetailName" disabled>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-lg-6 col-sm-12">
            <label class="col-sm-12 col-md-4 col-lg-4">
                <spring:message code="label.from.date"/>
            </label>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <input class="selector form-control" id="fromDate" style="background-color: white">
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
                <input class="selector form-control" id="toDate" style="background-color: white">
                <i class="fa fa-clock-o clock"></i>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-lg-6 col-sm-12">
            <label class="col-sm-12 col-md-4 col-lg-4">
                <spring:message code="label.message.heading"/>&nbsp;*
            </label>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <input type="text" class="form-control" id="subject">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-lg-6 col-sm-12">
            <label class="col-sm-12 col-md-4 col-lg-4">
                <spring:message code="label.message.content"/>&nbsp;*
            </label>
            <div class="col-sm-12 col-md-8 col-lg-8">
                <textarea class="form-control" style="min-height: 170px" id="content"><spring:message
                        code="label.message.content"/></textarea>
            </div>
        </div>
    </div>
</div>
<%--https://flatpickr.js.org/examples/#datetime--%>
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