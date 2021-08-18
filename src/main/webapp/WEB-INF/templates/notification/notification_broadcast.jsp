<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 6/28/2019
  Time: 9:14 AM
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

</head>
<body>
<section class="body">
    <!--        ///////   header ////////-->
    <jsp:include page="../include_page/header.jsp"/>
    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="cus-type-broadcast" name="nav"/>
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
                                    <!-- form search -->
                                    <form action="${contextPath}/notification/broadcast"
                                          id="form" method="post"
                                          enctype="multipart/form-data">
                                        <jsp:include page="create_noti.jsp"/>
                                        <input type="hidden" name="action"/>
                                        <sec:csrfInput/>
                                    </form>
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
