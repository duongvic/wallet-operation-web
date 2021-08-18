<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
    <meta charset="UTF-8">
    <title><spring:message code="fundout.order.title.page"/></title>
    <jsp:include page="../include_page/head.jsp">
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
    <link rel="stylesheet" href="<c:url value='/assets/development/static/css/fundorder.css'/>" media="none"
          onload="if(media!='all')media='all'">
    <jsp:include page="../include_page/js_merchant.jsp">
        <jsp:param name="selectLib" value="true"/>
    </jsp:include>
</head>

<body>
<section class="body">
    <jsp:include page="../include_page/header.jsp"/>

    <div class="inner-wrapper">
        <!-- start: sidebar -->
        <jsp:include page="../include_page/navigation.jsp">
            <jsp:param value="orderfund_out" name="nav"/>
        </jsp:include>
        <!-- end: sidebar -->
        <section role="main" class="content-body">
            <header class="page-header">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <div class="page-header-left">
                            <ol class="breadcrumbs">
                                <li><a href="#"><i class="fa fa-home"></i></a></li>
                                <li><span><spring:message code="fundout.detai.navigate.fundout"/></span></li>
                                <li><span><a href="#" id="hight-title" class="hight-title"><spring:message
                                        code="fundout.request.fundout"/> </a></span></li>
                                <li><span class="nav-active"><spring:message
                                        code="fundout.order.subnav.btn.create"/> </span></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </header>

            <jsp:include page="../include_page/message.jsp"/>

            <!-- start: page -->
            <div class="content-body-wrap">
                <div class="container-fluid pb-md">
                    <div class="h4 mb-md">
                        <spring:message code="fundout.request.selectMethod"/>
                    </div>

                    <section class="panel search_payment panel-default">
                        <div class="panel-body pt-none pb-none">
                            <div class="form-group">
                                <sec:authorize
                                        access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')">
                                    <div class="col-xs-2 center col-respon">
                                        <a href="#" class="i-flag" id="bank_tranfer">
                                            <div class="fg-vn">
                                                <div class="sprite-fundout-i_bank_charging img-responsive ml-xs"></div>
                                            </div>
                                            <p class="mb-none"><strong><spring:message
                                                    code="fundin.list.bankTransfer"/></strong></p>
                                        </a>
                                    </div>

                                    <div class="col-xs-2 center col-respon">
                                        <a href="#" class="i-flag" id="linked_bank">
                                            <div class="fg-vn">
                                                <div class="sprite-fundout-i_bank_charging img-responsive ml-xs"></div>
                                            </div>
                                            <p class="mb-none"><strong><spring:message
                                                    code="fundin.list.linkedBank"/></strong></p>
                                        </a>
                                    </div>
                                </sec:authorize>
                                <!--Disabled cash on hand-->
                                <%--                <div class="col-xs-2 center col-respon">--%>
                                <%--                  <a href="#" class="i-flag" id="cash_on_hand">--%>
                                <%--                    <div class="fg-vn">--%>
                                <%--                      <div class="sprite-fundout-i_api_otp img-responsive ml-xs"></div>--%>
                                <%--                    </div>--%>
                                <%--                    <p class="mb-none"><strong><spring:message code="fundin.list.cashonHand"/></strong></p>--%>
                                <%--                  </a>--%>
                                <%--                </div>--%>
                                <div class="col-xs-2 center col-respon">
                                    <a href="#" class="i-flag" style="opacity: 0.5; cursor: not-allowed">
                                        <div class="fg-vn">
                                            <div class="sprite-fundout-i_api_otp img-responsive ml-xs"></div>
                                        </div>
                                        <p class="mb-none"><strong><spring:message
                                                code="fundin.list.cashonHand"/></strong></p>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- ----------------bank_tranfer----------------------------   -->
                        <jsp:include page="request_bank_tranfer.jsp"/>

                        <!-- ----------------linked_bank----------------------------   -->
                        <jsp:include page="request_linked_bank.jsp"/>

                        <!-- ----------------cash_on_hand----------------------------   -->
                        <jsp:include page="request_cash_on_hand.jsp"/>


                        <div class="clearfix"></div>
                    </section>
                </div>
            </div>
        </section>
        <jsp:include page="../include_page/footer.jsp"/>
    </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#bank_tranfer").click(function () {
            $("div.div_fundin").hide();
            $("div.bank_tranfer").show();
            return false;
        });
        $("#linked_bank").click(function () {
            $("div.div_fundin").hide();
            $("div.linked_bank").show();
            return false;
        });
        $("#cash_on_hand").click(function () {
            $("div.div_fundin").hide();
            $("div.cash_on_hand").show();
            return false;
        });
        $('form[name=cashOnHand] button:submit').click(function () {
            $("form[name=cashOnHand] input[name=action]").val($(this).attr("action"));
        });
        $('form[name=cashOnHand] input:file').change(function (click) {
            var files = $(this)[0].files;
            var exts = ['png', 'jpg', 'jpe', 'jpeg'];
            var checkExt = true;
            if (files.length > 0) {
                for (var i = 0; i < files.length; i++) {
                    var get_ext = files[i].name.split('.');
                    // reverse name to check extension
                    get_ext = get_ext.reverse();
                    if ($.inArray(get_ext[0].toLowerCase(), exts) < 0) {
                        checkExt = false;
                    }
                }
                if (!checkExt) {
                    $.MessageBox({message: '<spring:message code="common.upload.file.not.format"/>'});
                    $(this).val('');
                }
            }
        });
        var inputs = document.querySelectorAll('form[name=cashOnHand] .inputfile');
        Array.prototype.forEach.call(inputs, function (input) {
            var label = input.nextElementSibling,
                labelVal = label.innerHTML;
            input.addEventListener('change', function (e) {
                $('form[name=cashOnHand] div.fileshow').html('');
                var fileName = '';
                if (this.files && this.files.length > 1) {
                    fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
                } else {
                    fileName = e.target.value.split('\\').pop();
                }
                if (fileName) {
                    label.querySelector('span').innerHTML = fileName;
                } else {
                    label.innerHTML = labelVal;
                }
                if (this.files && this.files.length > 0) {
                    $.each(e.target.files, function (idx, elm) {
                        var size = elm.size / 1024;
                        $('form[name=cashOnHand] div.fileshow').append('<p class="primary_color">' + elm.name + ' - size : ' + size + 'KB</p>')
                    });
                }
            });
        });
    });
</script>
</body>
</html>
