<%--
  Created by IntelliJ IDEA.
  User: hiep
  Date: 1/12/2021
  Time: 9:50 AM
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
                                    <div class="panel-title">
                                        <div class="row">
                                            <div class="col-lg-2 col-md-2 col-sm-6 text-left">
                                                <h4 style="margin-bottom: 1.5rem">Push Notification</h4>
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
                                                    <p><spring:message code="${notification.getEvent().getName()}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="transaction.api.detail.servicetype"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p><spring:message
                                                            code="${notification.getServiceType().getName()}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.service.type"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p><spring:message
                                                            code="${notification.getService().getName()}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row hidden" id="detailNameRow">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.service.detail.name"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p>${notification.serviceDetailName}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.from.date"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
                                                                       value="${notification.fromDate}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.to.date"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
                                                                       value="${notification.toDate}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.message.heading"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p>${notification.subject}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-md-6 col-lg-6 col-sm-12">
                                                <label class="col-sm-12 col-md-4 col-lg-4">
                                                    <spring:message code="label.message.content"/>
                                                </label>
                                                <div class="col-sm-12 col-md-8 col-lg-8">
                                                    <p>${notification.content}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
</body>
</html>

