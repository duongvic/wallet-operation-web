<%@ page import="vn.mog.ewallet.operation.web.constant.SharedConstants" %>
<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>

<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <!-- Basic -->
  <meta charset="UTF-8">
  <title><spring:message code="service.exportcard.create.title.page"/></title>
  <jsp:include page="../include_page/head.jsp"/>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/epin.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
  </jsp:include>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.validate-min.js'/>"></script>
</head>

<body>

<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <c:set var="locale" value="${pageContext.response.locale}"/>
  <c:set var="urlEpinPOList" value="<%=EpinPurchaseOrderController.EPIN_PO_LIST%>"/>
  <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perExportEpin"/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="merchantpo" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="pl-md ">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"><i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="service.exportcard.create.navigate.service"/></span></li>
                <li><span><c:choose>
                  <c:when test="${perExportEpin eq true}">
                    <a href="<c:url value="${urlEpinPOList}"/>" id="hight-title" class="hight-title"> <spring:message code="service.exportcard.create.navigate.exportcard"/> </a>
                  </c:when>
                  <c:otherwise>
                  <a href="<c:url value="${urlEpinPOList}?search=&range=&search=Search&status=1&status=2&multiselect=1&multiselect=2"/>" id="hight-title" class="hight-title">
                    <spring:message code="service.exportcard.create.navigate.exportcard"/>
                  </a>
                  </c:otherwise>
                </c:choose></span></li>
                <li><span class="nav-active"><spring:message code="service.exportcard.create.navigate.creatempo"/></span></li>
              </ol>
            </div>
          </div>
        </div>
      </header>

      <jsp:include page="../include_page/message.jsp"/>

      <!-- start: page -->
      <div class="content-body-wrap">
        <div class="container-fluid">
          <div class="h4 mb-md">
            <spring:message code="service.exportcard.create.subnavigate.title"/>
          </div>

          <section class="panel search_payment panel-default">
            <div class="panel-body pt-none pb-none">
              <div class="wizard-tabs mt-none mb-none">
                <ul class="wizard-steps">
                  <li class="col-xs-4 pl-none pr-none active"><a class="text-center"> <span class="badge hidden-xs">1</span>&nbsp;<spring:message code="service.exportcard.epin.stepone.request"/> </a></li>
                  <li class="col-xs-4 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">2</span>&nbsp;<spring:message code="service.exportcard.epin.steptwo.approve"/> </a></li>
                  <li class="col-xs-4 pl-none pr-none"><a class="text-center"> <span class="badge hidden-xs">3</span>&nbsp;<spring:message code="service.exportcard.epin.stepthree.export"/> </a></li>
                </ul>
              </div>

              <div class="tab-content">
                <section id="tab1" class="tab-pane active">
                  <section class="panel">
                    <div class="panel-body report_search_form pb-none pt-none">
                      <div class="row">
                        <form id="tblInputTel">
                          <div class="tab-content">
                            <div class="form-group">
                              <label class="col-md-1 control-label" style="margin-left: 1em" for="note">*<spring:message code="service.exportcard.create.note"/></label>
                              <div class="col-md-4 tertiary_color" id="note">
                                <spring:message code="service.exportcard.create.guilde"/>&nbsp;
                                <i id="noteRequest" class="fa fa-question-circle text-muted m-xs"
                                   data-toggle="popover"
                                   data-trigger="hover"
                                   data-placement="top"
                                   data-content='<spring:message code="service.exportcard.create.guilde.content"/>'
                                   data-html="true"
                                   data-original-title=""
                                   rel="popover"
                                   title=""></i>
                              </div>
                            </div>
                            <div class="panel-body report_search_form pb-none">

                              <div class="form-group telco gr-logo">

                                <div class="col-xs-0 col-sm-1 image-empty">
                                  <label for="rd15" class="lb-image">
                                    <div class="sprite-card-line_none_100_40"></div>
                                    <br/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd1" class="lb-image">
                                    <div class="sprite-card-viettel_100_40"></div>
                                    <br/>
                                    <input id="rd1" name="telco" value="VIETTEL" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd2" class="lb-image">
                                    <div class="sprite-card-mobifone_100_40"></div>
                                    <br/>
                                    <input id="rd2" name="telco" value="MOBIFONE" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd3" class="lb-image">
                                    <div class="sprite-card-vnphone_100_40"></div>
                                    <br/>
                                    <input id="rd3" name="telco" value="VINAPHONE" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd4" class="lb-image">
                                    <div class="sprite-card-vnmobile_100_40"></div>
                                    <br/>
                                    <input id="rd4" name="telco" value="VNMOBILE" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd15" class="lb-image">
                                    <div class="sprite-card-zing_100_40"></div>
                                    <br/>
                                    <input id="rd15" name="telco" value="ZING" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-0 col-sm-1 image-empty">
                                  <label for="rd15" class="lb-image">
                                    <div class="sprite-card-line_none_100_40"></div>
                                    <br/>
                                  </label>
                                </div>

                                <div class="col-xs-0 col-sm-1 image-empty">
                                  <label for="rd15" class="lb-image">
                                    <div class="sprite-card-line_none_100_40"></div>
                                    <br/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd11" class="lb-image">
                                    <div class="sprite-card-garena_100_40"></div>
                                    <br/>
                                    <input id="rd11" name="telco" value="GARENA" type="radio" class="rd-img"/>
                                  </label>
                                </div>
                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd12" class="lb-image">
                                    <div class="sprite-card-gate_100_40"></div>
                                    <br/>
                                    <input id="rd12" name="telco" value="GATE" type="radio" class="rd-img"/>
                                  </label>
                                </div>
                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd13" class="lb-image">
                                    <div class="sprite-card-vcoin_100_40"></div>
                                    <br/>
                                    <input id="rd13" name="telco" value="VCOIN" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd14" class="lb-image">
                                    <div class="sprite-card-gmobile_100_40"></div>
                                    <br/>
                                    <input id="rd14" name="telco" value="GMOBILE" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                  <label for="rd16" class="lb-image">
                                    <div class="sprite-card-oncash_100_40"></div>
                                    <br/>
                                    <input id="rd16" name="telco" value="ONCASH" type="radio" class="rd-img"/>
                                  </label>
                                </div>

                                <div class="col-xs-0 col-sm-1 image-empty">
                                  <label for="rd15" class="lb-image">
                                    <div class="sprite-card-line_none_100_40"></div>
                                    <br/>
                                  </label>
                                </div>
                              </div>

                              <div class="clearfix"></div>

                              <div class="col-xs-12 col-sm-6 form-group textTelco mt-lg">
                                <!-- 10000 -->
                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">10000</span> </label>
                                <div class="col-xs-6 mb-xs"><input type="text" name="tc_am10" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am10"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <!-- 10000 -->
                                <label class="col-xs-2 mb-xs control-label mb-xs"> <span class="fr">20000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am20" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am20"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">30000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am30" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am30"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">50000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am50" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am50"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                              </div>

                              <div class="col-xs-12 col-sm-6 form-group textTelco mt-lg">
                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">100000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am100" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am100"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">200000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am200" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am200"> <spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">300000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am300" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am300"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                                <label class="col-xs-2 mb-xs control-label"> <span class="fr">500000</span> </label>
                                <div class="col-xs-6 mb-xs "><input type="text" name="tc_am500" disabled autocomplete="off" class="form-control po-line inputtelco"></div>
                                <label class="col-xs-4 mb-xs pl-none pr-none control-label secondary_color tc_am500"><spring:message code="service.exportcard.create.card.notavailable"/></label>

                              </div>

                              <div class="col-xs-12 report_search_bt pb-none pt-md">
                                <a href="#" class="fr add-tel-po"><i class="fa fa-plus"></i>&nbsp;<spring:message code="service.exportcard.create.card.add"/></a>
                              </div>
                            </div>
                          </div>
                          <sec:csrfInput />
                        </form>
                        <sec:authorize access="hasAnyRole('MERCHANT','CUSTOMER')" var="perNextCreatMPO">
                        <c:url var="epinPoConUri" value="<%=EpinPurchaseOrderController.EPIN_PO_CONTROLLER%>"/>
                        <form name="telco" action="${epinPoConUri}/request-po" method="post" >
                          </sec:authorize>

                          <input type="hidden" name="totalTelco" id="totalTelco" value="${totalTelco}"/>
                          <input type="hidden" name="poCode" value=""/>
                          <input type="hidden" name="totalQuantity" value=""/>
                          <section class="panel panel-default po-detail-line">
                            <%-- b-pocode,Mã-PO code --%>
                            <div class="pl-xs" style="font-size: 13px;">
                              <b class="b-pocode" hidden></b>
                              <spring:message code="service.exportcard.create.table.header.total"/>&nbsp;<b class="b-money primary_color">0</b> <b class="primary_color">VND</b>&nbsp;|&nbsp;
                              <spring:message code="service.exportcard.create.table.header.quantity"/>&nbsp;<b class="b-quantity primary_color">0</b>
                            </div>

                            <div class="panel-body">
                              <div class="table-responsive">
                                <table class="table table-bordered table-striped mb-none" id="tableMPOdetail">
                                  <thead>
                                  <tr>
                                    <th><spring:message code="service.exportcard.create.table.column.no"/></th>
                                    <th style="width:10%"><spring:message code="service.exportcard.create.table.column.cardtype"/></th>
                                    <th class="text-right"><spring:message code="service.exportcard.create.table.column.facevalue"/></th>
                                    <th class="text-right"><spring:message code="service.exportcard.create.table.column.quantity"/></th>
                                    <th class="text-center"><spring:message code="service.exportcard.create.table.column.status"/></th>
                                    <th class="text-center"><spring:message code="service.exportcard.create.table.column.action"/></th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  <spring:message code="service.exportcard.create.card.available" var="langAvailable"/>
                                  <c:forEach var="item" items="${purchaseOrderDetails }" varStatus="varStatus">
                                    <tr class="${item.cardType }">
                                      <input type="hidden" name="telco_${varStatus.index +1}" value="${item.cardType }"/>
                                      <input type="hidden" name="value_${varStatus.index +1}" value="${item.faceValue }"/>
                                      <input type="hidden" name="quantity_${varStatus.index +1}" value="${item.quantity }"/>
                                      <input type="hidden" name="status_${varStatus.index +1}" value="${item.status }"/>
                                      <td class="stt">${varStatus.index +1}</td>
                                      <td>${item.cardType }</td>
                                      <td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>
                                      <td class="text-right">${ewallet:formatNumber(item.quantity)}</td>
                                      <td class="text-center ${item.status eq langAvailable ? 'primary_color' : 'secondary_color' }">${item.status }</td>
                                      <td class="action_icon text-center">
                                        <a href="javascript:;"><i class="fa fa-times"></i></a>
                                      </td>
                                    </tr>
                                  </c:forEach>
                                  </tbody>
                                </table>
                              </div>
                            </div>
                          </section>
                          <div class="row content-body-wrap">
                            <div class="col-md-12 col-lg-12 col-xl-12">
                              <div class="panel-body edit_profile">
                                <spring:message code="service.exportcard.otp.waiting" var="waiting"/>
                                <c:choose>
                                  <c:when test="${perNextCreatMPO eq true}">
                                    <button type="submit" class="mt-md btn btn-success fr po-next-step" id="btnNextStep"
                                            data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}"><spring:message code="service.exportcard.create.btn.next"/>&nbsp;<i class="fa fa-arrow-right"></i></button>
                                  </c:when>
                                  <c:otherwise>
                                    <button type="button" onclick="disagree()" class="mt-md btn btn-success fr po-next-step"><spring:message code="service.exportcard.create.btn.next"/></button>
                                  </c:otherwise>
                                </c:choose>
                              </div>
                            </div>
                          </div>
                      </div>
                      <c:if test="${perNextCreatMPO eq true}">
                        <sec:csrfInput />
                        </form>
                      </c:if>
                    </div>
                  </section>
                </section>
              </div>
              <div id="tab2" class="tab-pane"></div>
            </div>
          </section>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
  <!-- end: page -->
</section>
<jsp:include page="../include_page/js_footer.jsp"/>

<!-- action btn-add-po click -->
<script type="text/javascript">

  function formatNumberSeparator(x, locale) {
    var locale = "vi";
    var separator = ",";
    if (locale == "vi") {
      separator = ".";
    }
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, separator);
  }

  function getValueFromName(name) {
    if (name == 'tc_am10') return '10000';
    else if (name == 'tc_am20') return '20000';
    else if (name == 'tc_am30') return '30000';
    else if (name == 'tc_am50') return '50000';
    else if (name == 'tc_am100') return '100000';
    else if (name == 'tc_am200') return '200000';
    else if (name == 'tc_am300') return '300000';
    else if (name == 'tc_am500') return '500000';
  }

  function getNameFromValue(value) {
    if (value == '10000') return 'tc_am10';
    else if (value == '20000') return 'tc_am20';
    else if (value == '30000') return 'tc_am30';
    else if (value == '50000') return 'tc_am50';
    else if (value == '100000') return 'tc_am100';
    else if (value == '200000') return 'tc_am200';
    else if (value == '300000') return 'tc_am300';
    else if (value == '500000') return 'tc_am500';
  }

  $(document).ready(function () {

    $('.add-tel-po').click(function () {
      if ($('#tblInputTel').valid()) {
        if ($('.telco input[name=telco]:checked').length > 0) {
          var telco = $('.telco input[name=telco]:checked').val();
          $('tr.' + telco).remove();
          renameTable();
          var i = $('.po-detail-line table tbody tr:last td:first').text();
          if (i == null || i == '') i = 0;
          var poLine = "";
          var ac = '<td class="action_icon text-center">' + '<a href="javascript:;"><i class="fa fa-times"></i></a>' + '</td>';
          var totalQuantity = 0;
          $('.inputtelco').each(function (index, value) {
            var amount = $(this).val().trim();
            var value = getValueFromName($(this).attr('name'));
            if (amount != '0' && amount != '') {
              totalQuantity += parseInt(amount);
              i++;
              var status = $("." + $(this).attr('name')).html();
              poLine += '<tr class="' + telco + '">';
              poLine += '<td class="stt">' + i + '</td>' +
                  '<td>' + telco + '<input type="hidden" name="telco_' + i + '" value="' + telco + '" /></td>' +
                  '<td class="text-right">' + formatNumberSeparator(value, "${locale}") + '<input type="hidden" name="value_' + i + '" value="' + value + '" /></td>' +
                  '<td class="text-right">' + formatNumberSeparator(amount, "${locale}") + '<input type="hidden" name="quantity_' + i + '" value="' + amount + '" /></td>' +
                  '<td class="text-center ' + $("." + $(this).attr('name')).attr('class') + '">' + status + '<input type="hidden" name="status_' + i + '" value="' + status + '" /></td>' +
                  ac;
              poLine += '</tr>';
            }
          });
          var curQuantity = parseInt($('input[name=totalQuantity]').val());
          var nameUser = '${userLogin.username}';
          var maxQuantity = 0;

          if (nameUser == 'Zota_ZTA') {
            maxQuantity = 30000;
          } else {
            maxQuantity = <%=SharedConstants.QUANTITY_MPO_MAX_ZTA%>;
          }

          if (totalQuantity + curQuantity > maxQuantity) {
            $.MessageBox({message: 'Cho phép xuất tối đa ' + maxQuantity + ' thẻ mỗi lần!'});
            return false;
          } else {
            $('.po-detail-line table tbody').append(poLine);
          }
          var istt = renameTable();
          $('form[name=telco] input[name=totalTelco]').val(istt);

        } else {
          $.MessageBox({message: '<spring:message code="popup.message.warning.card.choose"/>'});
        }
        return false;
      }
    });
    $(document).on('click', 'td.action_icon a', function () {
      $(this).closest('tr').remove();
      $('form[name=telco] input[name=totalTelco]').val(renameTable());
    });
    //truong hop back
    renameTable();
    function renameTable() {
      var istt = 0;
      var totalQuatity = 0;
      var totalMoney = 0;
      $('.po-detail-line table tbody tr').each(function (index, value) {
        istt = index + 1;
        $(value).find('td.stt').html(istt);
        $(value).find('input[name*=value_]').attr('name', 'value_' + istt);
        $(value).find('input[name*=telco_]').attr('name', 'telco_' + istt);
        $(value).find('input[name*=quantity_]').attr('name', 'quantity_' + istt);
        $(value).find('input[name*=status_]').attr('name', 'status_' + istt);
        var quantity = parseInt($(value).find('input[name*=quantity_]').val());
        var money = parseInt($(value).find('input[name*=value_]').val()) * quantity;
        totalQuatity += parseInt($(value).find('input[name*=quantity_]').val());
        totalMoney += money;
      });
      $("b.b-quantity").html(formatNumberSeparator(totalQuatity, "${locale}"));
      $('input[name=totalQuantity]').val(totalQuatity);
      $("b.b-money").html(formatNumberSeparator(totalMoney, "${locale}"));
      var d = new Date();
      var month = d.getMonth() + 1;
      var textMoney = totalMoney / 1000;
      var pocode = "" + d.getFullYear() + month + d.getDate()
          + d.getHours() + d.getMinutes() + '-' + totalQuatity + '-' + textMoney;
      $("b.b-pocode").html(pocode);
      $("input[name=poCode]").val(pocode);
      return istt;
    }

    $('input[name=telco]').click(function () {
      $("#mloading").modal('toggle');
      $('form#tblInputTel input[name*=tc_am]').prop('disabled', false);
      $('form#tblInputTel label[class*=tc_am]').removeClass('primary_color');
      $('form#tblInputTel label[class*=tc_am]').addClass('secondary_color');
      //$('form#tblInputTel label[class*=tc_am]').html('(Not available)');
      $('form#tblInputTel label[class*=tc_am]').html('<spring:message code="card.notavailable"/>');
      $("div.textTelco input").val('');
      var telco = $(this).val();
      $.post('getAvailabelCard', {cardtype: telco}, function (data) {
        var cardAvailable = data;
        if (cardAvailable != null) {
          $.each(cardAvailable, function (inx, val) {
            if (val > 0) {
              var name = getNameFromValue(inx);
              //$('form#tblInputTel input[name='+name+']').prop('disabled', false);
              $('form#tblInputTel label.' + name).removeClass('secondary_color');
              $('form#tblInputTel label.' + name).addClass('primary_color');
              $('form#tblInputTel label.' + name).html('<spring:message code="popup.message.warning.card.available"/>');
            }
          });
        }
        $("#mloading").modal('toggle');
      });
      $('tr.' + telco).each(function (index, value) {
        var faceValue = "";
        var quantity = "";
        $(value).find('input[type=hidden]').each(function (index1, value1) {
          if ($(value1).attr("name").includes("value_"))
            faceValue = $(value1).val();
          if ($(value1).attr("name").includes("quantity_"))
            quantity = $(value1).val();
        });
        var nameInput = getNameFromValue(faceValue);
        $('input[name=' + nameInput + ']').val(quantity);
      });
    });

    $('form[name="telco"]').submit(function () {
      var rowCount = $('#tableMPOdetail tr').length;
      if (rowCount < 2) {
        $.MessageBox({message: '<spring:message code="popup.message.warning.card.choose.quantity"/>'});
        return false;
      }
      $('#btnNextStep').button('loading');
    });

    $.validator.addMethod("inputdigits", $.validator.methods.digits, "Hãy nhập số nguyên (Không nhập các ký tự đặc biệt (\<\>\?\.\:\"\(\),cách trống).)");
    $.validator.addClassRules("inputtelco", {inputdigits: true});

    $("div.report_search_form span.fr").each(function () {
      val = $(this).html();
      $(this).html(formatNumberSeparator(val, "${locale}"));
    });
  });
</script>

</body>
</html>
