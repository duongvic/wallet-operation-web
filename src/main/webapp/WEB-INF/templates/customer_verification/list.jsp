<%@ page
    import="vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SenderType" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="customer.verification.list.title.page"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <jsp:include page="../include_page/js_service.jsp">
        <jsp:param name="dateLib" value="true"/>
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-verifi" name="nav"/>
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
                                        code="menu.left.customer.verification.list"/></span>
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
                            <spring:message code="menu.left.customer.verification.submenu.title"/>
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
                                        <input type="text" id="search" name="quickSearch"
                                               class="form-control"
                                               placeholder="Tim kiếm theo số điện thoại"
                                               value="${param.quickSearch}"/>
                                    </div>
                                </div>

                                <spring:message code="select.status" var="langStatus"/>

                                <div class="form-inline">

                                    <jsp:include page="../include_component/date_range.jsp"/>
                                  <select class="form-control" name="kycType" id="kycType">
                                    <option selected disabled>Loại xác thực</option>
                                    <option
                                        value="INDIVIDUAL" ${param.kycType eq 'INDIVIDUAL' ? 'selected': ''}>
                                      Cá nhân
                                    </option>
                                    <option
                                        value="ENTERPRISE" ${param.kycType eq 'ENTERPRISE' ? 'selected': ''}>
                                      Doanh nghiệp
                                    </option>
                                  </select>

                                    <div class="pull-right form-responsive bt-plt">

                                        <jsp:include page="../include_component/search_kyc_status.jsp"/>
                                        <%--<c:set var="allPayType" value=","/>--%>
                                        <%--<c:forEach var="item"--%>
                                                   <%--items="${paramValues.kycRequestStatus}">--%>
                                            <%--<c:set var="allVerifiableSatus"--%>
                                                   <%--value="${allPayType}${item},"/>--%>
                                        <%--</c:forEach>--%>
                                        <%--<select class="form-control multiple-select hidden"--%>
                                                <%--multiple="multiple" id="kycRequestStatus"--%>
                                                <%--name="kycRequestStatus">--%>
                                            <%--<option value="0" ${fn:contains(allPayType, '0') ? 'selected' : ''}>Init</option>--%>
                                            <%--<option value="1" ${fn:contains(allPayType, '1') ? 'selected' : ''}>Self Cancel</option>--%>
                                            <%--<option value="2" ${fn:contains(allPayType, '2') ? 'selected' : ''}>Waiting for Approvement</option>--%>
                                            <%--<option value="3" ${fn:contains(allPayType, '3') ? 'selected' : ''}>Staff Reject</option>--%>
                                            <%--<option value="4" ${fn:contains(allPayType, '4') ? 'selected' : ''}>Apporved</option>--%>

                                        <%--</select>--%>

                                        <%--<script type="application/javascript">--%>
                                          <%--$('#kycRequestStatus').multiselect({--%>
                                            <%--includeSelectAllOption: true,--%>
                                            <%--selectAllValue: '',--%>
                                            <%--selectAllText: '<spring:message code="select.choose.all"/>',--%>
                                            <%--maxHeight: 400,--%>
                                            <%--dropUp: true,--%>
                                            <%--nonSelectedText: '<spring:message code="select.status"/>',--%>
                                            <%--inheritClass: true,--%>
                                            <%--numberDisplayed: 1--%>
                                          <%--});--%>
                                        <%--</script>--%>

                                        <button type="submit" class="btn btn-primary ml-tiny"><i
                                                class="fa fa-search"></i>&nbsp; <spring:message
                                                code="common.btn.search"/></button>
                                        <button class="btn btn-default nomal_color_bk bt-cancel"
                                                onclick="ClearFields();"><spring:message
                                                code="common.btn.cancel"/></button>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </form>

                            <c:set var="pmsType" value="<%=SenderType.PMS.name()%>"/>
                            <div class="table-responsive">
                                <display:table name="list" id="item"
                                               requestURI="list"
                                               pagesize="${pagesize}"
                                               partialList="true"
                                               size="total"
                                               sort="page"
                                               class="table table-bordered table-responsive table-striped mb-none">

                                    <%@ include file="../include_component/display_table.jsp" %>

                                    <display:column title="STT" headerClass="fit_to_content"
                                                    class="right">
                                      <span id="row${item.id}" class="rowid">
                                        <c:out value="${offset + item_rowNum}"/>
                                      </span>
                                    </display:column>

                                    <display:column title="ID">${item.cif}</display:column>
                                    <display:column title="CREATED DATE" property="created"
                                                    format="{0,date,HH:mm:ss dd/MM/yyyy}"/>
                                    <display:column
                                            title="FULL NAME">${item.identity.fullname}</display:column>
                                    <display:column
                                            title="PHONE NAME">${item.msisdn}</display:column>
                                    <display:column
                                            title="KYC STATUS">
                                      ${item.textKycStatus()}
                                      <c:if test="${item.senderType eq pmsType}">
                                      &nbsp;&nbsp;<span style="background-color:#FF9D00;" class="badge"><spring:message code="label.pms.verified"/></span>
                                      </c:if>
                                    </display:column>
                                    <display:column
                                            title="REQUEST STATUS">${item.textRequestStatusId()}</display:column>
                                    <%--<display:column title="UPDATED DATE">${item.updatedDate}</display:column>--%>
                                  <display:column title="KYC TYPE">
                                    <spring:message code="label.individual" var="individual"/>
                                    <spring:message code="label.enterprise" var="enterprise"/>
                                    ${
                                    item.kycType eq 'ENTERPRISE' ? enterprise : individual
                                        }
                                  </display:column>
                                    <display:column title="REMARK">${item.remark}</display:column>
                                    <display:column title="ACTION" headerClass="action_icon right"
                                                    class="action_icon right">
                                        <a href="${pageContext.request.contextPath}/customer/verification/detail/${item.customerId}/${item.id}"
                                           class="reversal-link link-active" title="Chi tiết">
                                            <i class="fa fa-arrow-circle-right"
                                               aria-hidden="true"></i>
                                        </a>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>


            <!-- Modal resend -->
            <!-- Modal resend -->

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
</html>
