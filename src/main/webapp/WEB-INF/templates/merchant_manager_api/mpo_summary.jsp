<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="sumary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
        </button>
        <h4 class="modal-title" id="myModalLabel"><spring:message code="service.exportcard.list.popup.summary.title"/></h4>
      </div>

      <div class="modal-body">

        <form name="sumary" class="mpo-summary" action="getSumary" method="post" >
          <input type="hidden" name="poMerchantId" value=""/>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="service.exportcard.list.popup.summary.row.code"/></label>
            <div class="col-md-6 pl-none">
              <p class="primary_color mPOcode fr form-control-static"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="service.exportcard.list.popup.summary.row.totalmoney"/></label>
            <div class="col-md-6 pl-none">
              <p class="primary_color totalMoney fr"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="service.exportcard.list.popup.summary.row.quantitycard"/></label>
            <div class="col-md-6 pl-none">
              <p class="primary_color totalQuantity  fr"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="service.exportcard.list.popup.summary.row.commission"/></label>
            <div class="col-md-6 pl-none">
              <p class="primary_color commission fr"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 control-label pl-none"><spring:message code="service.exportcard.list.popup.summary.row.moneypay"/></label>
            <div class="col-md-6 pl-none">
              <p class="primary_color moneyPay fr"></p>
            </div>
          </div>

          <div class="title-bo-box pl-none ml-none"><spring:message code="service.exportcard.list.popup.summary.sub.title"/></div>
          <div class="table-responsive">
            <table class="table table-bordered table-responsive table-striped mb-none datatable-default">
              <thead>
              <tr>
                <th class="stt"><spring:message code="service.exportcard.list.popup.summary.sub.no"/></th>
                <th><spring:message code="service.exportcard.list.popup.summary.sub.cardtype"/></th>
                <th class="text-right"><spring:message code="service.exportcard.list.popup.summary.sub.facevalue"/></th>
                <th class="text-right p-id"><spring:message code="service.exportcard.list.popup.summary.sub.quantity"/></th>
                <th class="text-center"><spring:message code="service.exportcard.list.popup.summary.sub.status"/></th>
              </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        <sec:csrfInput />
        </form>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="service.exportcard.list.popup.summary.sub.cancel"/></button>
        <button type="button" class="btn btn-success confirmRequest" data-loading-text="<i class='fa fa-spinner fa-spin '></i> <spring:message code="common.waitting.pl"/>"><spring:message code="service.exportcard.list.popup.summary.sub.next"/></button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $('a.sumary-link').click(function () {
      $('button.confirmRequest').prop('disabled', false);
      $('button.confirmRequest').button('loading');
      $('button.confirmRequest').show();

      $('form[name=sumary] .mPOcode').html($(this).attr("poCode"));
      $('form[name=sumary] .commission').html('');
      $('form[name=sumary] .moneyPay').html('');
      $('form[name=sumary] .totalMoney').parent().parent().removeClass('alert alert-danger');
      $('form[name=sumary] .totalMoney').addClass('primary_color');
      $('form[name=sumary] .totalMoney').removeClass('secondary_color');
      $('form[name=sumary] .totalQuantity ').html('');
      $('form[name=sumary] .totalMoney').html('');
      $('form[name=sumary] table.datatable-default tbody tr').remove();
      $('form[name=sumary] input[name=poMerchantId]').val($(this).attr("poMerchantId"));

      $.post($('form[name=sumary]').attr('action'), $('form[name=sumary]').serialize(),
        function (json) {
          var disabled = false;
          $('form[name=sumary] .commission').html(json.totalDiscountAmount.toLocaleString('de-DE') + ' VND');
          $('form[name=sumary] .moneyPay').html((json.totalMoney - json.totalDiscountAmount).toLocaleString('de-DE') + ' VND');
          $('form[name=sumary] .totalQuantity ').html(json.totalQuantity.toLocaleString('de-DE'));
          $('form[name=sumary] .totalMoney').html(json.totalMoney.toLocaleString('de-DE') + ' VND');
          var currentPay = json.balance - json.totalMoney;
          if (currentPay < 0) {
            disabled = true;
            $('form[name=sumary] .totalMoney').parent().parent().addClass('alert alert-danger');
            $('form[name=sumary] .totalMoney').addClass('secondary_color');
            $('form[name=sumary] .totalMoney').removeClass('primary_color');
          }
          var mpoDetailItem = json.purchaseOrderDetails;
          if (mpoDetailItem != null) {
            var tbody = $('form[name=sumary] table.datatable-default tbody');
            tbody.html('');
            var i = 1;
            for (var item in mpoDetailItem) {
              var classStatus = "secondary_color";
              //if (mpoDetailItem[item].status == '(Available)')
              if (mpoDetailItem[item].status == '<spring:message code="card.available"/>')
                classStatus = "primary_color";
              else {
                disabled = true;
              }
              var tr = '<tr>' +
                  '<td>' + i + '</td>' +
                  '<td>' + mpoDetailItem[item].cardType + '</td>' +
                  '<td class="text-right">' + mpoDetailItem[item].faceValue.toLocaleString('de-DE') + '</td>' +
                  '<td class="text-right">' + mpoDetailItem[item].quantity.toLocaleString('de-DE') + '</td>' +
                  '<td class="text-center ' + classStatus + '">' + mpoDetailItem[item].status + '</td>' +
                  '</tr>';
              tbody.append(tr);
              i++;
            }
            if (disabled) {
              $('button.confirmRequest').prop('disabled', true);
              $('button.confirmRequest').hide();
            }
            else {
              $('button.confirmRequest').button('reset');
            }
          }
        });
    });
  });
</script>
