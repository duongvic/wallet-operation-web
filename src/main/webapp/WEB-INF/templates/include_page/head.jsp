<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<meta name="keywords" content="Zo-Ta Operation">
<meta name="description" content="Zo-Ta Operation">
<meta name="author" content="Zo-Ta">

<sec:csrfMetaTags />

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="${contextPath}/assets/favicon.ico" rel="icon" type="image/x-icon"/>

<!-- Web Fonts -->
<link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css" media="none" onload="if(media!='all')media='all'">
<%--<link href="<c:url value='/assets/development/static/css/font-awesome.min.css'/>" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/font-awesome.min.css'/>" media="none" onload="if(media!='all')media='all'">

<!-- Vendor CSS -->
<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/bootstrap/css/bootstrap.min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/bootstrap.min.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/magnific-popup/magnific-popup-min.css'/>">--%>
<%--<link rel="stylesheet" href="<c:url value='/assets/development/static/css/oneweek/magnific-popup-min.css'/>" media="none" onload="if(media!='all')media='all'">--%>

<!-- Specific Page Vendor CSS -->
<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/jquery-ui-1.10.4.custom.min.css'/>" media="none" onload="if(media!='all')media='all'">
<link rel="stylesheet" href="${contextPath}/assets/vendor/font-simpleline/css/simple-line-icons.css?v=2.1" />


<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/morris/morris.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/morris.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/pnotify/pnotify.custom.css'/>"/>--%>
<%--<link rel="stylesheet" href="<c:url value='/assets/development/static/css/oneweek/pnotify.custom.css'/>" media="none" onload="if(media!='all')media='all'">--%>

<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/messagebox.css'/>"/>--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/messagebox.css'/>" media="none" onload="if(media!='all')media='all'">



<%-- -----------select------------- --%>
<c:if test="${param.selectLib eq 'true'}">
<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/bootstrap-multiselect.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/vendor/select2/4.0.1/select2.min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/select2.min.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/skins/1pay_skin/bootstrap-select.min.css'/>" type="text/css">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/bootstrap-select.min.css'/>" media="none" onload="if(media!='all')media='all'">
</c:if>



<%--   CUSTOEM THEME    --%>
<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/skins/1pay_skin/navbar-min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/navbar-min.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/theme-min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/theme-min.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/skins/1pay_skin/zota-min.css'/>">--%>
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/zota-min.css'/>" media="none" onload="if(media!='all')media='all'">

<%--<link rel="stylesheet" href="<c:url value='/assets/stylesheets/new-custom-min.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/oneday.1.0.6/new-custom-min.css'/>"> into zota-min
<link rel="stylesheet" href="<c:url value='/assets/vendor/panel_tool/panel_tool-min.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/oneday.1.0.6/panel_tool-min.css'/>"> into navbar-min
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/oneday.1.0.6/daterangepicker_custom-min.css'/>" media="none" onload="if(media!='all')media='all'">--%>

<c:if test="${param.dateLib eq 'true'}">
  <%--<link rel="stylesheet" href="<c:url value='/assets/vendor/bootstrap-datepicker/css/datepicker3-min.css'/>">--%>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/datepicker3-min.css'/>" media="none" onload="if(media!='all')media='all'">

  <%--<link rel="stylesheet" href="<c:url value='/assets/vendor/jquery-datatables-bs3/assets/css/datatables-min.css'/>">--%>
  <c:choose>
    <c:when test="${param.dataTableCssV2 eq true}">
      <%--update datatable 18/5/2020--%>
      <link rel="stylesheet" href="<c:url value="/assets/stylesheets/datatable-update/jquery.dataTables.min.css"/>">
      <style>
        .dataTables_wrapper.no-footer .dataTables_scrollBody {
          border-bottom: none !important;
        }
      </style>
      <%--end--%>
    </c:when>
    <c:otherwise>
      <%--old version datatable--%>
      <link rel="stylesheet" href="<c:url value='/assets/development/static/css/datatables-min.css'/>" media="none" onload="if(media!='all')media='all'">
    </c:otherwise>
  </c:choose>


  <%--<link rel="stylesheet" href="<c:url value='/assets/vendor/daterangepicker/daterangepicker-min.css'/>" type="text/css" media="all">--%>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/daterangepicker-min.css'/>" media="none" onload="if(media!='all')media='all'">
</c:if>


<c:if test="${param.switchLib eq 'true'}">
  <%--<link rel="stylesheet" href="<c:url value='/assets/vendor/switchery/switchery.css'/>"/>--%>
  <link rel="stylesheet" href="<c:url value='/assets/development/static/css/switchery.css'/>" media="none" onload="if(media!='all')media='all'">
</c:if>

<!-- dev custom -->
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/dev-theme-custom.1.0.0.css'/>" media="none" onload="if(media!='all')media='all'">
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/dev-responsive.css'/>" media="none" onload="if(media!='all')media='all'">
<link rel="stylesheet" href="<c:url value='/assets/development/static/css/switch-custom.css'/>" media="none" onload="if(media!='all')media='all'">
