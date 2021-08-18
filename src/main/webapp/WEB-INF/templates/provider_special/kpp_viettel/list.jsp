<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/12/2021
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../include_page/taglibs.jsp" %>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="system.account.list.title.page"/></title>
    <jsp:include page="../../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../../include_page/js_service.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="switchLib" value="true"/>
    </jsp:include>
    <style>
        th {
            text-transform: uppercase;
        }

        #myTable_filter {
            width: 20%;
        }
    </style>
</head>

<body>
<section class="body">
    <jsp:include page="../../include_page/header.jsp"/>
    <jsp:include page="../../include_component/constant_application.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../../include_page/navigation.jsp">
            <jsp:param value="kppviettel" name="nav"/>
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
                                        code="provider.list.providerProfile"/></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="menu.left.provider.special"/></span></li>
                                <li><span><spring:message
                                        code="setting.account.list.subnavigate"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../../include_page/message_new.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="tabs">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#" style="text-transform: uppercase;">
                                    <spring:message code="account.list.accountManagement"/>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content pl-none pr-none">
                            <div id="tab1" class="tab-pane active">
                                <section class="panel search_payment panel-default">
                                    <div class="panel-body pt-none">

                                        <div class="form-inline">
                                            <div class="fr form-responsive">
                                                <button type="button" class="mb-xs mt-xs btn btn-success btn-add">
                                                    <i class="fa fa-plus"></i>&nbsp;<spring:message
                                                        code="system.service.navigate.btn.create"/>
                                                </button>
                                            </div>
                                        </div>

                                        <section class="panel search_payment panel-default">
                                            <div class="panel-body pt-none">
                                                <section class="panel search_payment panel-default">
                                                    <div class="panel-body">
                                                        <div class="clearfix"></div>
                                                        <div class="pull-left mt-sm"
                                                             style="line-height: 30px;">
                                                        </div>
                                                        <div class="clr"></div>
                                                        <div class="container-fluid">
                                                            <table class="table table-bordered table-responsive table-striped mb-none"
                                                                   id="myTable" width="100%" style="margin-top: 0px">
                                                                <thead>
                                                                <th>#</th>
                                                                <th>Account name</th>
                                                                <th>Password</th>
                                                                <th>Phone number</th>
                                                                <th>Serial number</th>
                                                                <th>Balance</th>
                                                                <th>Status</th>
                                                                <th class="center">Action</th>
                                                                </thead>
                                                                <tbody>
                                                                <c:forEach var="kppVT"
                                                                           items="${kppVTs}" varStatus="loop">
                                                                    <tr>
                                                                        <td>${loop.index}</td>
                                                                        <td>${kppVT.accountName}</td>
                                                                        <td></td>
                                                                        <td>${kppVT.phoneNumber}</td>
                                                                        <td>${kppVT.serialNumber}</td>
                                                                        <td style="text-align: right">${ewallet:formatNumber(kppVT.amount)}</td>
                                                                        <td style="text-align: center">
                                                                            <label class="switch"
                                                                                   style="margin: 0 3px;"
                                                                                   data-placement="top"
                                                                                   title=""
                                                                                   data-original-title=""
                                                                                   onclick="changeStatus('${kppVT.accountName}', '${kppVT.status}')">
                                                                                <input type="checkbox" ${kppVT.status == '1' ? "checked" : ""}
                                                                                       disabled>
                                                                                <span class="slider round"></span>
                                                                            </label>
                                                                        </td>
                                                                        <td>
                                                                            <div class="center">
                                                                                <a class="mr-xs"
                                                                                   title="<spring:message code="label.delete.account.customer"/>"
                                                                                   onclick="deleteAccount('${kppVT.accountName}')"><i
                                                                                        class="fa fa-trash"
                                                                                        style="color: red; font-size: 2rem"></i></a>
                                                                                <a class="mr-xs"
                                                                                   title="<spring:message code="common.detail"/>"
                                                                                   onclick="editUser('${kppVT.accountName}', '${kppVT.phoneNumber}', '${kppVT.serialNumber}', '${kppVT.status}', '${kppVT.amount}')"><i
                                                                                        class="fa fa-info-circle"
                                                                                        style="font-size: 2rem"></i></a>

                                                                                <a title="<spring:message code="label.verify.captcha"/>"
                                                                                   onclick="verifyCaptcha('${kppVT.accountName}')">
                                                                                    <img style="margin-bottom: 7px"
                                                                                         src="<c:url value="/assets/images/recaptcha_48dp.png"/>"
                                                                                         width="20px" height="auto">
                                                                                </a>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>
                                        </section>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="create_or_update_modal.jsp"/>
            <jsp:include page="verify_captcha.jsp"/>
            <!-- end: page -->
        </section>
        <jsp:include page="../../include_page/footer.jsp"/>

    </div>
</section>
<jsp:include page="../../include_page/js_footer.jsp"/>
<script type="text/javascript"
        src="<c:url value='/assets/javascripts/settings-custom.js'/>"></script>
<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<spring:message code="data.table.header.paging.records" var="paging_records"/>

<script>
    $.fn.dataTable.ext.errMode = 'throw';
    $(document).ready(function () {
        $('#myTable').dataTable({
            language: {
                "sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",
                "sLengthMenu": "_MENU_ ${paging_records}",
                "paginate": {
                    "previous": "${paging_previous}",
                    "next": "${paging_next}"
                }
            },
            pageLength: 1000,
            dom: 'Bfrtip',
            scrollX: true,
        });
    });

    $(document).ajaxStart(function (event, xhr, settings) {
        document.querySelector('button').disabled = true;
        document.querySelector('a').disabled = true;
    });
    $(document).ajaxComplete(function (event, xhr, settings) {
        document.querySelector('button').disabled = false;
        document.querySelector('a').disabled = false;
    });

    $(".btn-add").click(function () {
        document.getElementById("myForm").reset();
        $("#formType").val("create");
        $("#password-group").css("display", "");
        $("#balance-group").css("display", "none");
        $("#modalTitle").text("<spring:message code="common.btn.add"/>");
        $("#myModal").modal('show');
    });

    function editUser(accountName, phoneNumber, serialNumber, status, amount) {
        document.getElementById("myForm").reset();
        $("#accountName").val(accountName);
        $("#phoneNumber").val(phoneNumber);
        $("#serialNumber").val(serialNumber);
        $("#status").val(status);
        $("#formType").val("update");
        $("#password-group").css("display", "none");
        $("#balance-group").css("display", "");
        $("#balance").html(amount);
        $("#modalTitle").text("<spring:message code="common.detail"/>");
        $("#myModal").modal('show');
    }

    function verifyCaptcha(accountName) {
        document.getElementById("captchaForm").reset();
        getCaptcha(accountName);
        $("#accountName2").val(accountName);
        $("#captchaModal").modal("show");
    }

    function getCaptcha(accountName) {
        $.ajax({
            url: ctx + '/ajax-controller/kppvietel/account/capcha/get',
            method: 'POST',
            data: {
                accountName: accountName,
            },
            success: function (result) {
                let imgSrc = "data:image/jpeg;base64," + result.capchaInByte;
                $("#captchaImg").attr("src", imgSrc);
            },
            error: function (result) {
                $.MessageBox({message: result.responseText});
            }
        });
    }

    function deleteAccount(accountName) {
        $.MessageBox({
                buttonDone: '<spring:message code="popup.button.yes"/>',
                buttonFail: '<spring:message code="popup.button.no"/>',
                message: '<spring:message code="label.delete.account.question"/> ?'
            }
        ).done(function () {
            $.ajax({
                url: ctx + '/ajax-controller/kppvietel/delete/account',
                method: 'POST',
                data: {
                    accountName: accountName,
                },
                success: function (result) {
                    $.MessageBox({message: "Success!"});
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                },
                error: function (result) {
                    $.MessageBox({message: result.responseText});
                }
            });
        })
    }

    function changeStatus(accountName, status) {
        $.MessageBox({
                buttonDone: '<spring:message code="popup.button.yes"/>',
                buttonFail: '<spring:message code="popup.button.no"/>',
                message: '<spring:message code="label.change.status.account.question"/> ?'
            }
        ).done(function () {
            let sttChange = status === '1' ? '0' : '1';
            $.ajax({
                url: ctx + '/ajax-controller/kppvietel/change-stt/account',
                method: 'POST',
                data: {
                    accountName: accountName,
                    status: sttChange,
                },
                success: function (result) {
                    $.MessageBox({message: "Success!"});
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                },
                error: function (result) {
                    $.MessageBox({message: result.responseText});
                }
            });
        })
    }
</script>
</body>
</html>

