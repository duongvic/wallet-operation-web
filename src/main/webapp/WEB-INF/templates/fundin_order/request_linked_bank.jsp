<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include_page/taglibs.jsp" %>

<div class="panel-body report_search_form pb-none pt-sm linked_bank div_fundin" ${styleDiv == 'linked_bank' ? '' : 'style=\"display: none;\"'} >
  <div class="toggle setupsv toggle-customer" data-plugin-toggle="">
    <section class="toggle active">
      <label class="alert-success">
        <img src="<c:url value="/assets/development/static/images/fundorder/i_api_otp_a.png"/>" class="img-responsive toggle-nav-img" alt="" style="width: 25px;margin-left: -4px">
        <span class="toggle-nav-el"><spring:message code="fundorder.request.linked.bank"/></span>
      </label>
      <div class="toggle-content">
        <div class="col-xs-12"><strong><spring:message code="common.choose.link.bank" /></strong></div>
        <div class="col-lg-2 col-md-6 col-xs-6 ">
          <a href="#" class="i-flag" id="image_linked_bank" style="width: 100%">
            <div class="fg-vn">
              <img src="<c:url value="/assets/development/static/images/fundin/tpbank-logo.png"/>" class="img-responsive" alt="">
            </div>
            <p class="mb-none"><strong><spring:message code="common.linked.bank"/></strong></p>
          </a>
        </div>

        <c:choose>
          <c:when test="${existLinkBank eq true}">
            <div class="col-xs-12 infoLinkedBank" style="display: none">
              <p class="mb-none"><strong><spring:message code="common.guide.line.fundin.via.bank.transfer"/></strong></p>

              <div class="tabs">
                <ul class="nav nav-tabs" style="padding-left: 0px">
                  <li class="active" id="tab_hdsd_naptien"><a href="#hdsd_naptien" data-toggle="tab"><spring:message code="common.guide.line.fundin.from.Web"/></a></li>
                  <li id="tab_hdsd_naptien_app"><a href="#hdsd_naptien_app" data-toggle="tab"><spring:message code="common.guide.line.fundin.from.App"/></a></li>
                </ul>
              </div>
              <div class="image_hdsd_naptien"><img src="<c:url value="/assets/development/static/images/fundin/hdsd_naptien.png"/>" class="img-responsive" alt=""></div>
              <div class="image_hdsd_naptien_app" style="display: none"><img src="<c:url value="/assets/development/static/images/fundin/hdsd_naptien_app.png"/>" class="img-responsive" alt=""></div>
            </div>
          </c:when>
          <c:otherwise>
            <div class="col-xs-12 infoLinkedBank" style="display: none">
              <p class="mb-none"><strong><spring:message code="common.you.have.not.linked.wallet.with.bank.account"/> </strong></p>
              <img src="<c:url value="/assets/development/static/images/fundin/hdsd_lienket.png"/>" class="img-responsive" alt="">
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </section>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function () {
    $("#image_linked_bank").click(function () {
      $("div.infoLinkedBank").show();
      return false;
    });
    $("#tab_hdsd_naptien").click(function () {
      $("#tab_hdsd_naptien").addClass("active");
      $("#tab_hdsd_naptien_app").removeClass("active");
      $("div.image_hdsd_naptien").show();
      $("div.image_hdsd_naptien_app").hide();
      return false;
    });
    $("#tab_hdsd_naptien_app").click(function () {
      $("#tab_hdsd_naptien_app").addClass("active");
      $("#tab_hdsd_naptien").removeClass("active");
      $("div.image_hdsd_naptien").hide();
      $("div.image_hdsd_naptien_app").show();
      return false;
    });
  });
</script>