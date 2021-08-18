<%@ page import="static vn.mog.ewallet.operation.web.controller.fundout.FundOutOrderController.FUND_OUT_ORDER_CONTROLLER" %>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>


<div class="panel-body report_search_form pb-none pt-sm cash_on_hand div_fundin" ${styleDiv == 'cash_on_hand' ? '' : 'style=\"display: none;\"'} >
  <c:url var="urlFundOutOrderCon" value="<%=FUND_OUT_ORDER_CONTROLLER%>"/>
  <form action="${urlFundOutOrderCon}/cash-on-hand" method="post" id="cashOnHand" name="cashOnHand" enctype="multipart/form-data">

    <div class="toggle setupsv toggle-customer" data-plugin-toggle="">
      <section class="toggle active">
        <label class="alert-success">
          <img src="<c:url value="/assets/development/static/images/fundorder/i_api_otp_a.png"/>" class="img-responsive toggle-nav-img" alt="" style="width: 25px;margin-left: -4px">
          <span class="toggle-nav-el"><spring:message code="request.cashonHand"/></span>
        </label>
        <div class="toggle-content">
          <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.cashonHand.customer"/>
                <spans style="color: red">*</spans>
              </label>
              <div class="col-lg-5 col-md-9 pl-none">
                <select name="customerId" id="customerIdCashOn" data-plugin-selectTwo required class="form-control " style="width: 100%">
                  <%--<option value=""><spring:message code="request.cashonHand.selectPartner"/></option>--%>
                  <%--<c:forEach var="item" items="${customers}">--%>
                    <%--<option value="${item.id}" ${item.id eq param.customerId ? 'selected' : ''}>${item.fullName} ( ${item.cif })</option>--%>
                  <%--</c:forEach>--%>
                </select>
              </div>
            </div>
          </sec:authorize>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.cashonHand.amount"/>
              <spans style="color: red">*</spans>
            </label>
            <div class="col-lg-5 col-md-9 pl-none">
              <div class="input-group mb-md">
                <input type="text" name="amount" id="amountCashOn" autocomplete="off" class="form-control textNumber" required value="${amount}">
                <span class="input-group-addon">VNĐ</span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.cashonHand.fee"/></label>
            <div class="col-lg-5 col-md-9 pl-none">
              <span class="primary_color">0</span><span class="primary_color"> (VNĐ)</span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.cashonHand.totalAmount"/></label>
            <div class="col-lg-5 col-md-9 pl-none">
              <span class="primary_color totalamount">${amount}</span><span class="primary_color"> (VNĐ)</span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.cashonHand.notable"/></label>
            <div class="col-lg-5 col-md-9 pl-none"><textarea rows="5" name="remark" class="form-control">${remark}</textarea></div>
          </div>

          <div class="form-group col-xs-12 col-sm-12 col-md-8 pl-none">
            <spring:message code="common.btn.processing.submit" var="waitting"/>
            <input type="hidden" name="action" value=""/>
            <button type="button" action="submit" class="btn btn-success pull-right ml-xs"
                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.request"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>

            <button type="button" action="save" class="btn btn-primary pull-right"
                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/></button>
          </div>
        </div>
      </section>
    </div>
    <sec:csrfInput/>
  </form>
</div>
<script type="text/javascript">
  $(document).ready(function () {

    $('form[name=cashOnHand] button:button').click(function () {

      var action = $(this).attr("action");
      if (action.length > 0) {
        <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
        if ($('#customerIdCashOn').val().length < 1) {
          $.MessageBox({message: 'Chọn khách hàng !'});
          return false;
        }
        </sec:authorize>

        if ($('#amountCashOn').val().length < 1) {
          $.MessageBox({message: 'Nhập số tiền !'});
          return false;
        }

        $('form[name="cashOnHand"] input[name=action]').val(action);
        $(this).button('loading');
        $('#cashOnHand').submit();
      }
    });

    $('#customerIdCashOn').select2({
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