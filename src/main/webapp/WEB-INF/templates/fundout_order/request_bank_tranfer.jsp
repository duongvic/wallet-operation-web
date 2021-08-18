<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="panel-body report_search_form pb-none pt-sm bank_tranfer div_fundin" ${styleDiv == 'bank_tranfer' ? '' : 'style=\"display: none;\"'} >
  <div class="toggle setupsv toggle-customer" data-plugin-toggle="">
    <section class="toggle active">
      <label class="alert-success">
        <img src="<c:url value="/assets/development/static/images/i_bank_charging.png"/>" class="img-responsive toggle-nav-img" alt="">
        <span class="toggle-nav-el"><spring:message code="request.BankTransfer"/></span>
      </label>
      <div class="toggle-content">
        <form action="bank-tranfer" method="post" id="bankTranfer" name="bankTranfer">
          <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
            <div class="form-group">
              <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.merchant"/>
                <spans style="color: red">*</spans>
              </label>
              <div class="col-lg-5 col-md-9 pl-none">
                <select name="customerId" id="customerIdBt" data-plugin-selectTwo required class="form-control " style="width: 100%">
                  <%--<option value=""><spring:message code="request.BankTransfer.selectPartner"/></option>--%>
                  <%--<c:forEach var="item" items="${customers}">--%>
                    <%--<option value="${item.id}" ${item.id eq param.customerId ? 'selected' : ''}>${item.fullName} ( ${item.cif })</option>--%>
                  <%--</c:forEach>--%>
                </select>
              </div>

            </div>
          </sec:authorize>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.amount"/>
              <spans style="color: red">*</spans>
            </label>
            <div class="col-lg-5 col-md-9 pl-none">
              <div class="input-group mb-md">
                <input type="text" name="amount" id="amountBt" autocomplete="off" class="form-control textNumber" required value="${amount}">
                <span class="input-group-addon">VNĐ</span>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bankname"/>
              <spans style="color: red">*</spans>
            </label>
            <div class="col-lg-5 col-md-9 pl-none">
              <select name="bank" id="bankBt" data-plugin-selectTwo required class="form-control " style="width: 100%">
                <option value=""><spring:message code="request.BankTransfer.select.bank"/></option>
                <c:forEach var="item" items="${listBank }">
                  <option value="${item.bankCode}" ${item.bankCode eq param.bank ? 'selected' : ''}>${item.displayName} (${item.bankName})</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.bankBranch"/></label>
            <div class="col-lg-5 col-md-9 pl-none">
              <input type="text" name="branch" value="${branch}" autocomplete="off" class="form-control" required/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.accountNo"/></label>
            <div class="col-lg-5 col-md-9 pl-none">
              <input type="text" name="number" value="${number}" autocomplete="off" class="form-control" required/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="request.BankTransfer.accountName"/></label>
            <div class="col-lg-5 col-md-9 pl-none">
              <input type="text" name="account" value="${account}" autocomplete="off" class="form-control" required/>
            </div>
          </div>

          <div class="form-group col-xs-12 col-sm-12 col-md-8 pl-none">
            <spring:message code="common.btn.processing.submit" var="waitting"/>
            <input type="hidden" name="action" value=""/>
            <button type="button" action="submit" class="btn btn-success pull-right ml-xs"
                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><spring:message code="common.btn.request"/>&nbsp;<i class="fa fa-arrow-right" aria-hidden="true"></i></button>

            <button type="button" action="save" class="btn btn-primary pull-right"
                    data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waitting}"><i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;<spring:message code="common.btn.save"/></button>

          </div>
          <sec:csrfInput/>
        </form>
      </div>
    </section>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $("#tabRequestBank select[name=bank]").on('change', function () {
      var selected = $(this).find("option:selected");
    });
    $('form[name=bankTranfer] button:button').click(function () {

      var action = $(this).attr("action");
      if (action.length > 0) {
        <sec:authorize access="!hasAnyRole('MERCHANT','CUSTOMER')">
          if ($('#customerIdBt').val().length < 1) {
            $.MessageBox({message: 'Chọn khách hàng !'});
            return false;
          }
        </sec:authorize>

        if ($('#amountBt').val().length < 1) {
          $.MessageBox({message: 'Nhập số tiền !'});
          return false;
        }

        if ($('#bankBt').val().length < 1) {
          $.MessageBox({message: 'Chọn ngân hàng !'});
          return false;
        }

        $('form[name="bankTranfer"] input[name=action]').val(action);
        $(this).button('loading');
        $('#bankTranfer').submit();
      }
    });
    $('#customerIdBt').select2({
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