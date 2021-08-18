<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 6/29/2019
  Time: 10:06 AM
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
    <%--<title>Customer Verification Detail</title--%>>
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

</head>
<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-type-noti-ls" name="nav"/>
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
                                        code="menu.left.customer.notification.list"/></span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <div class="content-body-wrap">
                <div class="container-fluid">
                    <div class="table-responsive">

                        <spring:message var="colCreateTime"
                                        code="setting.account.tbl.col.created.at"/>
                        <spring:message var="colHeading"
                                        code="table.column.heading"/>
                        <spring:message var="colContent"
                                        code="table.column.content"/>
                        <spring:message var="colReceiverType"
                                        code="table.column.receiver.type"/>
                        <spring:message var="colResend"
                                        code="table.column.resend"/>

                        <display:table name="nofitications" id="item"
                                       requestURI="list"
                                       pagesize="${pagesize}"
                                       partialList="true"
                                       size="total"
                                       sort="page"
                                       class="table table-bordered table-responsive table-striped mb-none">

                            <%@ include
                                    file="../include_component/display_table.jsp" %>

                            <display:column title="STT"
                                            headerClass="fit_to_content"
                                            class="right">
                                    <span id="id${item.notificationId}" class="rowid">
                                    <c:out value="${offset + item_rowNum}"/>
                                    </span>
                            </display:column>
                            <display:column
                                    title="${colCreateTime}"
                                    style="vertical-align: middle">${item.createdTime}</display:column>
                            <display:column
                                    title="${colHeading}"
                                    style="vertical-align: middle">${item.heading}</display:column>
                            <display:column
                                    title="${colContent}"
                                    style="vertical-align: middle">${item.content}</display:column>
                            <display:column
                                    title="${colReceiverType}"
                                    style="vertical-align: middle">
                                <c:if test="${item.receiverTypeId eq 3}">
                                    MERCHANT
                                </c:if>
                                <c:if test="${item.receiverTypeId eq 2}">
                                    AGENT
                                </c:if>
                            </display:column>

                            <c:url value="/notification/broadcast"
                                   var="url">
                                <c:param name="paramHeading" value="${item.heading}"/>
                                <c:param name="paramContent" value="${item.content}"/>
                            </c:url>
                            <display:column title="${colResend}" headerClass="action_icon right"
                                            class="action_icon right">
                                <a href="${url}"
                                   class="reversal-link link-active" title="Gửi lại">
                                    <i class="fa fa-arrow-circle-right"
                                       aria-hidden="true"></i>
                                </a>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
</body>
</html>
