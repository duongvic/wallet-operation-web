<%@ page import="vn.mog.ewallet.operation.web.controller.fundin.FundInOrderController" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="panel-body report_search_form pb-none pt-sm bank_tranfer div_fundin" ${styleDiv == 'bank_tranfer' ? '' : 'style=\"display: none;\"'} >
  <div class="toggle setupsv toggle-customer" data-plugin-toggle="">
    <section class="toggle active">
      <label class="alert-success">
        <img src="<c:url value="/assets/development/static/images/i_bank_charging.png"/>" class="img-responsive toggle-nav-img" alt="">
        <span class="toggle-nav-el"><spring:message code="fundin.order.request.banktransfer"/></span>
      </label>

      <div class="toggle-content" display: block;>
        <div class="wizard-tabs mt-xs">
          <ul class="wizard-steps">
            <li class="col-xs-6 pl-none pr-none active"><a href="#tabRequestBank" data-toggle="tab" class="text-center bank-backstep"><span class="badge hidden-xs">1</span>&nbsp;<spring:message code="common.btn.request"/> </a></li>
            <li class="col-xs-6 pl-none pr-none"><a href="#tabSubmitBank" data-toggle="tab" class="text-center bank-nextstep"><span class="badge hidden-xs">2</span>&nbsp;<spring:message code="fundin.order.requestBankTrasfer.step.two"/> </a></li>
          </ul>
        </div>

        <div class="tab-content pl-none">
          <%-- ---------------tab request bank------------ --%>
          <div id="tabRequestBank" class="tab-pane active">
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bankname"/>
                <spans style="color: red">*</spans>&nbsp;</label>
              <div class="col-md-5 pl-none">
                <select name="bank" data-plugin-selectTwo class="form-control " style="width: 100%">
                  <option value=""><spring:message code="request.BankTransfer.select.bank"/></option>
                  <c:forEach var="item" items="${listBank }">
                    <option branch="${item.bankBranch}"
                            number="${item.bankAccountNumber}"
                            account="${item.bankAccountName }"
                            value="${item.bankCode}" ${item.bankCode eq param.bank ? 'selected' : ''}>${item.bankName}
                      (${item.bankAbbrName })
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"></label>
              <div><a href="#" class="col-lg-5 col-md-9 pl-none link-bank"><spring:message code="request.BankTransfer.bankWebsite"/></a></div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.accountNo"/></label>
              <div class="col-lg-5 col-md-9 pl-none"><p class="number">0</p></div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.accountName"/></label>
              <div class="col-lg-5 col-md-9 pl-none"><span class="name"></span></div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bank"/></label>
              <div class="col-lg-5 col-md-9 pl-none"><span class="bank_name"></span></div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bankBranch"/></label>
              <div class="col-lg-5 col-md-9 pl-none"><span class="branch"></span></div>
            </div>
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.content"/></label>
              <div class="col-lg-5 col-md-9 pl-none">
                <p>ZTA_TPGW_Số điện thoại_Mã khách hàng</p>
                <p class="primary_color">(Example: ZTA_TPGW_0973666888_abc123)</p>
              </div>
            </div>

            <div class="form-group">
              <div class="col-xs-12 col-sm-12 col-md-8 pl-none">
                <button type="submit" class="btn btn-success pull-right" id="bank_next"><spring:message code="common.btn.next"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>
              </div>
            </div>
          </div>

          <%-- ---------------tab submitrequest bank------------ --%>
          <c:url var="urlFundInOrder" value="<%=FundInOrderController.FUND_IN_ORDER_CONTROLLER%>"/>

          <div id="tabSubmitBank" class="tab-pane">
            <form action="${urlFundInOrder}/bank-tranfer" method="post" id="fBankTranfer" name="bankTranfer" enctype="multipart/form-data">
              <input type="hidden" name="bank" value=""/>
              <div class="form-group">
                <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bank"/></label>
                <div class="col-lg-5 col-md-9 pl-none">
                  <span class="primary_color bank_name"/>
                </div>
              </div>

              <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
                <div class="form-group">
                  <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.customer"/>
                    <spans style="color: red">*</spans>&nbsp;</label>
                  <div class="col-md-5 pl-none">
                    <select name="customerId" id="customerId" required data-plugin-selectTwo class="form-control " style="width: 100%">
                      <%--<option value=""><spring:message code="common.choose.customer"/></option>--%>
                      <%--<c:forEach var="item" items="${customers}">--%>
                        <%--<option value="${item.id}" ${item.id eq param.customerId ? 'selected' : ''}>${item.fullName}--%>
                          <%--( ${item.cif })--%>
                        <%--</option>--%>
                      <%--</c:forEach>--%>
                    </select>
                  </div>
                </div>
              </sec:authorize>

              <div class="form-group">
                <label class="col-md-3 control-label pl-none">Channel
                  <spans style="color: red">*</spans>&nbsp;</label>
                <div class="col-md-5 pl-none">
                  <select class="form-control" name="channel" id="channel" style="width: 100%">
                    <option value="ZOTA">ZOTA Channel (default)</option>
                    <option value="SAPO">SAPO Channel</option>
                    <option value="ZOTAHOME">ZOTAHOME Channel</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.amount"/>
                  <spans style="color: red">*</spans>&nbsp;</label>
                <div class="col-md-5 pl-none">
                  <div class="input-group mb-md">
                    <input type="text" name="amount" id="amountBt" autocomplete="off" class="form-control textNumber" required value="${amount}">
                    <span class="input-group-addon">VNĐ</span>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.commandCode"/></label>
                <div class="col-md-5 pl-none">
                  <input type="text" name="code" autocomplete="off" class="form-control" value="${code }">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.content"/></label>
                <div class="col-lg-5 col-md-9 pl-none">
                  <p></p>
                </div>
              </div>
              <div class="form-group mb-none">
                <label class="col-md-3 control-label pl-none"><spring:message code="common.choose.file"/></label>
                <div class="col-lg-5 col-md-9 pl-none">
                  <input type="file" name="fileUpload" id="file-2" class="inputfile inputfile-2" data-multiple-caption="{count} files selected" multiple/>
                  <label for="file-2"><i class="fa fa-upload" aria-hidden="true"></i><span><spring:message code="common.btn.choose.file"/> </span></label>
                  <p><strong class="secondary_color"><5mb </strong>(*.png,*.jpe,*.jpeg,*.jpg)</p>
                </div>
              </div>

              <sec:authorize access="hasAnyRole('ADMIN_OPERATION','MERCHANT','CUSTOMER','SALE_EXCUTIVE','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')">
                <spring:message code="common.btn.processing.submit" var="waitting"/>
                <input type="hidden" name="action" value=""/>
                <div class="form-group col-xs-12 col-sm-12 col-md-8 pl-none">
                  <button type="button" class="btn btn-danger " id="bank_back"><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.back"/></button>
                  <button type="button" action="submit" class="btn btn-success pull-right ml-xs"
                          data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.submit"/> &nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>

                  <button type="button" action="save" class="btn btn-primary pull-right"
                          data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/></button>
                </div>
              </sec:authorize>
              <sec:csrfInput/>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</div>

<script type="text/javascript">
  $(document).ready(function () {

    $("#tabRequestBank select[name=bank]").on('change', function () {
      var selected = $(this).find("option:selected");
      resetBankForm();
      $("#tabRequestBank .number").html(selected.attr("number"));
      $("#tabRequestBank .name").html(selected.attr("account"));
      $("#tabRequestBank .bank_name").html(selected.text());
      $("#tabRequestBank .link-bank").html(selected.text() + " website");
      $("#tabRequestBank .branch").html(selected.attr("branch"));
      $("#tabSubmitBank .bank_name").html(selected.text());
      $("form[name=bankTranfer] input[name=bank]").val(selected.val());
    });
    FindSelect();

    function FindSelect() {
      var selectBank = $("#tabRequestBank select[name=bank]");
      var selected = $(selectBank).find("option:selected");
      resetBankForm();
      $("#tabRequestBank .number").html(selected.attr("number"));
      $("#tabRequestBank .name").html(selected.attr("account"));
      $("#tabRequestBank .bank_name").html(selected.text());
      $("#tabRequestBank .link-bank").html(selected.text() + " website");
      $("#tabRequestBank .branch").html(selected.attr("branch"));
      $("#tabSubmitBank .bank_name").html(selected.text());
      $("form[name=bankTranfer] input[name=bank]").val(selected.val());
    }

    function resetBankForm() {
      $("#tabRequestBank .number").html("");
      $("#tabRequestBank .name").html("");
      $("#tabRequestBank .bank_name").html("");
      $("#tabRequestBank .link-bank").html("");
      $("#tabRequestBank .branch").html("");
      $("#tabSubmitBank .bank_name").html("");
      $("form[name=bankTranfer] input[name=bank]").val("");
    }

    $("#bank_back").click(function () {
      $(".bank-backstep").click();
    });
    $("#bank_next").click(function () {
      $(".bank-nextstep").click();
    });
    $(".bank-nextstep").click(function () {
      if ($("form[name=bankTranfer] input[name=bank]").val().length < 1) {
        $.MessageBox({message: 'Chọn ngân hàng !'});
        return false;
      }
    });

    $('form[name="bankTranfer"] button:button').click(function () {
      var action = $(this).attr("action");
      if (action.length > 0) {
        <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
        if ($('#customerId').val().length < 1) {
          $.MessageBox({message: 'Chọn khách hàng !'});
          return false;
        }
        </sec:authorize>
        if ($('#amountBt').val().length < 1) {
          $.MessageBox({message: 'Nhập số tiền !'});
          return false;
        }

        $('form[name="bankTranfer"] input[name=action]').val(action);
        $(this).button('loading');
        $('#fBankTranfer').submit();
      }
    });

    $('#customerId').select2({
      width: "100%",
      dropdownAutoWidth: true,
      ajax: {
        url: ctx + "/ajax-controller/find-customers",
        dataType: 'json',
        type: "POST",
        data: function (params) {
          var query = {
            search: params.term,
            type: 'public'
          }

          // Query parameters will be ?search=[term]&type=public
          return query;
        },
        // Additional AJAX parameters go here; see the end of this chapter for the full code of this example
        processResults: function (data) {
          console.log(data);
          // Transforms the top-level key of the response object from 'items' to 'results'
          var retVal = [];
          for (var i = 0; i < data.length; i++) {
            var lineObj = {
              id: data[i].id,
              text: data[i].fullName + '(SĐT: '+data[i].msisdn+','+ ' cif: '+data[i].cif+')'
            };
            retVal.push(lineObj);
          }

          return {
            // results: data.items
            results: retVal

          };
        }
      },
      placeholder: 'Tìm kiếm khách hàng bằng SDT',
      minimumInputLength: 3,
      language: {
        inputTooShort: function () {
          return 'Nhập bằng hoặc nhiều hơn 3 kí tự';
        }
      }
    });
  });
</script>