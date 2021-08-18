<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form name="detail" action="getDetail" method="post" >
        <input type="hidden" name="poMerchantId" value=""/>
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only"><spring:message code="popup.header.icon.close"/></span>
          </button>
          <h4 class="modal-title" id="myModalLabel"><spring:message code="service.exportcard.list.popup.detail.title"/></h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.codempo"/></label>
            <div class="col-md-6">
              <p class="primary_color poMerchantCode"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.merchant"/></label>
            <div class="col-md-6">
              <p class="primary_color merchant"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.keyholder"/></label>
            <div class="col-md-6">
              <p class="primary_color key-holder"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.totalmoney"/></label>
            <div class="col-md-6">
              <p class="primary_color card-am"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.quantify"/></label>
            <div class="col-md-6">
              <p class="primary_color total-am"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.perbalance"/></label>
            <div class="col-md-6">
              <p class="primary_color pre-balance"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.postbalance"/></label>
            <div class="col-md-6">
              <p class="primary_color post-balance"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.time"/></label>
            <div class="col-md-6">
              <p class="primary_color time"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-4 control-label"><spring:message code="service.exportcard.list.popup.detail.row.status"/></label>
            <div class="col-md-6">
              <p class="primary_color status"></p>
            </div>
          </div>

          <div class="title-bo-box"><spring:message code="service.exportcard.list.popup.detail.sub.title"/></div>
          <div class="bo-box" id="bo-box">
            <div class="table-responsive qlsp">
              <table class="table table-bordered table-striped mb-none datatable-default">
                <thead>
                <tr>
                  <th class="stt"><spring:message code="service.exportcard.list.popup.detail.sub.no"/></th>
                  <th><spring:message code="service.exportcard.list.popup.detail.sub.telco"/></th>
                  <th><spring:message code="service.exportcard.list.popup.detail.sub.facevalue"/></th>
                  <th class="p-id"><spring:message code="service.exportcard.list.popup.detail.sub.quantity"/></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">
            <spring:message code="service.exportcard.list.popup.detail.sub.cancel"/>
          </button>
        </div>
      <sec:csrfInput />
      </form>
    </div>
  </div>
</div>
<script>
  $(document).ready(function () {
    $('a.detail-link').click(function () {
      ResetAll('detail');
      $('form[name=detail] table.datatable-default tbody tr').remove();
      $('form[name=detail] input[name=poMerchantId]').val($(this).attr("poMerchantId"));

      $.post($('form[name=detail]').attr('action'), $('form[name=detail]').serialize(), function (json) {
        if (json.status.code == 0) {

          var poItem = json.purchaseOrder;
          if (poItem != null) {
            var date = new Date(poItem.createdTime)
            var creationDate = date.getHours() + ":" + date.getMinutes() + " " + date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
            $('form[name=detail] .poMerchantCode').html(poItem.poCode);
            $('form[name=detail] .card-am').html(poItem.totalValue.toLocaleString('de-DE') + ' VND');
            $('form[name=detail] .total-am').html(poItem.totalQuantity.toLocaleString('de-DE'));
            if (poItem.preBalance != null)
              $('form[name=detail] .pre-balance').html(poItem.preBalance.toLocaleString('de-DE'));
            if (poItem.postBalance != null)
              $('form[name=detail] .post-balance').html(poItem.postBalance.toLocaleString('de-DE'));
            $('form[name=detail] .time').html(creationDate);

            //$('form[name=detail] .status').html(poItem.status);
            if (poItem.status == 0)
              $('form[name=detail] .status').html('<spring:message code="popup.mpo.detai.status.save"/>');
            else if (poItem.status == 1)
              $('form[name=detail] .status').html('<spring:message code="popup.mpo.detai.status.fail"/>');
            else if (poItem.status == 2)
              $('form[name=detail] .status').html('<spring:message code="popup.mpo.detai.status.success"/>');
            $('form[name=detail] .merchant').html(poItem.merchantName);
            $('form[name=detail] .key-holder').html(poItem.keyHolder);
          }

          var mpoDetailItem = poItem.purchaseOrderDetails;
          if (mpoDetailItem != null) {
            var tbody = $('form[name=detail] table.datatable-default tbody');
            tbody.html('');
            var i = 1;
            for (var item in mpoDetailItem) {
              var tr = '<tr><td>' + i + '</td><td>' + mpoDetailItem[item].cardType + '</td>'
                  + '<td>' + mpoDetailItem[item].faceValue.toLocaleString('de-DE') + '</td>'
                  + '<td>' + mpoDetailItem[item].quantity.toLocaleString('de-DE') + '</td></tr>';
              tbody.append(tr);
              i++;
            }
          }
        }
        else {
          $.MessageBox({message: json.status.code.value});
        }
      });

      function ResetAll(formName) {
        $('form[name=' + formName + '] input[name=poMerchantId]').val('');
        $('form[name=' + formName + '] .poMerchantCode').html('');
        $('form[name=' + formName + '] .card-am').html('');
        $('form[name=' + formName + '] .total-am').html('');
        $('form[name=' + formName + '] .time').html('');
        $('form[name=' + formName + '] .note').html('');
        $('form[name=' + formName + '] .status').html('');
        $('form[name=' + formName + '] .merchant').html('');
        $('form[name=' + formName + '] .key-holder').html('');
      };
    });
  });
</script>