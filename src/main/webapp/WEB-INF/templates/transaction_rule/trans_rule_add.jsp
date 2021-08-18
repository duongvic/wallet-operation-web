<%@ page import="static vn.mog.ewallet.operation.web.controller.translog.TransactionRuleController.TRANS_RULE_CONTROLLER" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<!doctype html>
<html class="fixed" lang="${pageContext.response.locale.language}">

<head>
  <meta charset="UTF-8">
  <title><spring:message code="system.tranrule.add.title.page"/></title>
  <jsp:include page="../include_page/head.jsp">
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/trans_rule_add.css'/>" media="none" onload="if(media!='all')media='all'">
  <jsp:include page="../include_page/js.jsp">
    <jsp:param name="tableLib" value="true"/>
    <jsp:param name="selectLib" value="true"/>
  </jsp:include>
</head>

<body>
<section class="body">
  <jsp:include page="../include_page/header.jsp"/>

  <c:url var="urlTransRuleContro" value='<%=TRANS_RULE_CONTROLLER%>'/>

  <div class="inner-wrapper">
    <!-- start: sidebar -->
    <jsp:include page="../include_page/navigation.jsp">
      <jsp:param value="tranRule" name="nav"/>
    </jsp:include>
    <!-- end: sidebar -->
    <section role="main" class="content-body">
      <header class="page-header">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="page-header-left">
              <ol class="breadcrumbs">
                <li><a href="#"> <i class="fa fa-home"></i></a></li>
                <li><span><spring:message code="movement.rule.navigate.movement.rule"/></span></li>
                <li><span><spring:message code="movement.rule.navigate.movement.rule.list"/></span></li>
                <c:choose>
                  <c:when test="${transRule.id > 0}">
                    <li><span class="nav-active"><spring:message code="system.tranrule.navigate.tranrule.update"/></span></li>
                  </c:when>
                  <c:otherwise>
                    <li><span class="nav-active"><spring:message code="system.tranrule.navigate.tranrule.add"/></span></li>
                  </c:otherwise>
                </c:choose>
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
            <spring:message code="system.tranrule.navigate.tranrule.add"/>
          </div>

          <form class="table-tranrule-add" action="${urlTransRuleContro}/updateTxnRule" method="post" >
            <section class="panel panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.tranrule.detail.info"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>

              <div class="panel-body report_search_form">

                <input type="hidden" id="transRuleId" name="transRuleId" value="${transRule.id}" class="form-control">

                <div class="form-group mb-small">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.servicetype"/>&nbsp;<span style="color: red">*</span></label>
                  <div class="col-sm-5 col-md-5 col-lg-3 text-normal">
                    <c:set var="serviceTypeId" value="${transRule.serviceType}"/>
                    <select class="form-control" id="serviceTypeId" name="serviceTypeId">
                      <option value=""><spring:message code="system.trans.rule.add.choose.servicetype"/></option>
                      <c:forEach var="item" items="${serviceTypes}">
                        <option value="${item.key}" ${item.key eq serviceTypeId ? 'selected' : ''}>${item.value}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>

                <div class="form-group mb-small">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.rule-code"/>&nbsp;<span style="color: red">*</span></label>
                  <div class="col-sm-5 col-md-5 col-lg-3 text-normal">
                    <input type="text" id="ruleCode" name="ruleCode" value="${transRule.code}" class="form-control">
                  </div>
                </div>

                <div class="form-group mb-small">
                  <label class="col-sm-3 col-md-2 control-label"><spring:message code="system.tranrule.detail.rule-name"/>&nbsp;<span style="color: red">*</span></label>
                  <div class="col-sm-5 col-md-5 col-lg-3 text-normal">
                    <input type="text" id="ruleName" name="ruleName" value="${transRule.name}" class="form-control">
                  </div>
                </div>
              </div>
            </section>


            <section class="panel search_payment panel-default">
              <div class="panel-title">
                <h4 class="fl"><spring:message code="system.tranrule.table.balance.movement.rule"/></h4>
                <ul class="panel-tools fl tool-filter">
                  <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
                </ul>
                <div class="clr"></div>
              </div>
              <div class="panel-body">
                <div class="table-responsive qlsp no-per-page">

                  <table id="tblTransRuleUpdate" class="table table-bordered table-striped mb-small mt-none">
                    <thead>
                    <tr>
                      <th class="stt"><spring:message code="system.tranrule.table.col.step"/></th>
                      <th class="stt"><spring:message code="system.trans.rule.table.col.order"/>&nbsp;<span style="color: red">*</span></th>
                      <th><spring:message code="system.tranrule.table.col.source"/></th>
                      <th><spring:message code="system.tranrule.table.col.destination"/></th>
                      <th><spring:message code="system.tranrule.table.col.event.type"/></th>
                      <th><spring:message code="system.tranrule.table.col.sofId"/></th>
                      <th class="center"><spring:message code="system.tranrule.table.col.action"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:choose>
                      <c:when test="${numberRow > 0}">
                        <input type="hidden" id="numberRow" name="numberRow" value="${numberRow}">

                        <c:set var="row" value="0"/>
                        <c:forEach var="ruleStep" items="${transRuleSteps}">
                          <tr id="rangeStep${row}">
                            <td class="center">${row + 1}</td>
                            <td class="center">
                              <input type="number" min="1" class="form-control" id="order${row}" name="order${row}" value="${ruleStep.order}">
                            </td>
                            <td class="form-inline">
                              <select id="sourceActor${row}" name="sourceActor${row}" class="form-control" onchange="changeSourceActor(${row})">
                                <c:forEach var="item" items="${actorTypes}">
                                  <option value="${item.key}" ${item.key eq ruleStep.source ? 'selected' : ''}>${item.value}</option>
                                </c:forEach>
                              </select>
                              <select id="sourceAccount${row}" name="sourceAccount${row}" class="cl-65" data-plugin-selectTwo ${(ruleStep.source eq 'PAYER' || ruleStep.source eq 'PAYEE') ? 'disabled="disabled"' : ''}>
                                <option value="0"><spring:message code="system.trans.rule.add.choose.account"/></option>
                                <c:forEach var="item" items="${customers}">
                                  <option value="${item.cif}" ${item.cif eq ruleStep.source ? 'selected' : ''}>${item.fullName}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td class="form-inline">
                              <select id="desActor${row}" name="desActor${row}" class="form-control" onchange="changeDesActor(${row})">
                                <c:forEach var="item" items="${actorTypes}">
                                  <option value="${item.key}" ${item.key eq ruleStep.dest ? 'selected' : ''}>${item.value}</option>
                                </c:forEach>
                              </select>
                              <select id="desAccount${row}" name="desAccount${row}" class="cl-65" data-plugin-selectTwo ${(ruleStep.dest eq 'PAYER' || ruleStep.dest eq 'PAYEE') ? 'disabled="disabled"' : ''}>
                                <option value="0"><spring:message code="system.trans.rule.add.choose.account"/></option>
                                <c:forEach var="item" items="${customers}">
                                  <option value="${item.cif}" ${item.cif eq ruleStep.dest ? 'selected' : ''}>${item.fullName}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td class="center">
                              <select class="form-control" id="eventTypeId${row}" name="eventTypeId${row}">
                                <c:forEach var="item" items="${eventTypes}">
                                  <option ${item.key eq ruleStep.eventType ? 'selected' : '' } value="${item.key}">${item.value}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td class="center">
                              <select class="form-control" id="sofTypeId${row}" name="sofTypeId${row}">
                                <c:forEach var="item" items="${sofTypes}">
                                  <option ${item.key eq ruleStep.sourceOfFundType ? 'selected' : '' } value="${item.key}">${item.value}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td class="action_icon center">
                              <a href="#" class="link-delete" onclick="removeTransRole(${row})" title="Xóa thông tin dịch vụ">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                              </a>
                            </td>
                          </tr>
                          <c:set var="row" value="${row + 1}"/>
                        </c:forEach>
                      </c:when>
                      <c:otherwise>
                        <input type="hidden" id="numberRow" name="numberRow" value="0">
                      </c:otherwise>
                    </c:choose>
                    </tbody>
                  </table>
                  <a id="add_row" class="btn btn-primary pull-right">&nbsp;<spring:message code="button.add.step"/></a>
                </div>
              </div>
            </section>
            <div class="pull-right mb-md" style="margin-top: 1em;">
              <button type="button" class="btn btn-danger"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="button.cancel"/></button>
              <button type="submit" name="action" value="submit" class="btn btn-success"><spring:message code="button.save"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
            </div>
          <sec:csrfInput /></form>
        </div>
      </div>
    </section>
    <jsp:include page="../include_page/footer.jsp"/>
  </div>
</section>
<jsp:include page="../include_page/js_footer.jsp"/>
<script type="text/javascript">
  $(document).ready(function () {
    var optionActorTypes = '';
    <c:forEach var ="item" items = "${actorTypes}" >
    optionActorTypes = optionActorTypes + '<option value="${item.key}">${item.value}</option>';
    </c:forEach>

    var optionCustomers = '<option value="0"><spring:message code="system.trans.rule.add.choose.account"/></option>';
    <c:forEach var ="item" items = "${customers}" >
    optionCustomers = optionCustomers + '<option value="${item.cif}">${item.fullName}</option>';
    </c:forEach >

    var optionEvenTypes = '';
    <c:forEach var ="item" items = "${eventTypes}" >
    optionEvenTypes = optionEvenTypes + '<option value="${item.key}">${item.value}</option>';
    </c:forEach >

    var optionSofTypes = '';
    <c:forEach var ="item" items = "${sofTypes}" >
    optionSofTypes = optionSofTypes + '<option value="${item.key}">${item.value}</option>';
    </c:forEach >

    $("#add_row").click(function () {
      var numberRow = $("input[name=numberRow]").val();
      var iplus = parseInt(numberRow) + 1;
      var indexRow = iplus - 1;
      var div = '<tr id="rangeStep' + indexRow + '" class="rangeDefault">' +
          '<td class="center">' + iplus + '</td>' +
          '<td class="center"><input type = "number" min="1" class="form-control" id="order' + indexRow + '" name="order' + indexRow + '"></td>' +
          '<td class="form-inline">' +
          '<select style="margin-right: 5px" id="sourceActor' + indexRow + '" name="sourceActor' + indexRow + '" class="form-control"  onchange="changeSourceActor(' + indexRow + ')">' + optionActorTypes + '</select>' +
          '<select id="sourceAccount' + indexRow + '" name="sourceAccount' + indexRow + '" style="width: 65%">' + optionCustomers + '</select>' +
          '</td>' +
          '<td class="form-inline">' +
          '<select style="margin-right: 5px" id="desActor' + indexRow + '" name="desActor' + indexRow + '" class="form-control" onchange="changeDesActor(' + indexRow + ')">' + optionActorTypes + '</select>' +
          '<select id="desAccount' + indexRow + '" name="desAccount' + indexRow + '"  style="width: 65%">' + optionCustomers + '</select>' +
          '</td>' +
          '<td class="center">' +
          '<select class="form-control" id="eventTypeId' + indexRow + '" name="eventTypeId' + indexRow + '">' + optionEvenTypes + '</select>' +
          '</td>' +
          '<td class="center">' +
          '<select class="form-control" id="sofTypeId' + indexRow + '" name="sofTypeId' + indexRow + '">' + optionSofTypes + '</select>' +
          '</td>' +
          '<td class="action_icon center">' +
          '<a href="#" class="link-delete" onclick="removeTransRole(' + indexRow + ')" title="Xóa thông tin dịch vụ">' +
          '<i class="fa fa-trash" aria-hidden="true"></i>' +
          '</a>' +
          '</td>' +
          '</tr>';
      $('#tblTransRuleUpdate tr:last').after(div);
      $("#sourceAccount" + indexRow).select2();
      $("#desAccount" + indexRow).select2();
      $("input[name=numberRow]").val(iplus);
    });
  });

  function removeTransRole(row) {
    $('#rangeStep' + row).remove();
    //var numberRow = $("input[name=numberRow]").val();
    //$("input[name=numberRow]").val(parseInt(numberRow) - 1);
  }

  function changeSourceActor(row) {
    var sourceActor = $('#sourceActor' + row).val();
    if (sourceActor == 'PAYER' || sourceActor == 'PAYEE') {
      $("#sourceAccount" + row).val("0").trigger("change");
      $("#sourceAccount" + row).attr("disabled", true);
    } else {
      $("#sourceAccount" + row).attr("disabled", false);
    }
  }

  function changeDesActor(row) {
    var desActor = $('#desActor' + row).val();
    if (desActor == 'PAYER' || desActor == 'PAYEE') {
      $("#desAccount" + row).val("0").trigger("change");
      $("#desAccount" + row).attr("disabled", true);
    } else {
      $("#desAccount" + row).attr("disabled", false);
    }
  }
</script>
</body>
</html>
