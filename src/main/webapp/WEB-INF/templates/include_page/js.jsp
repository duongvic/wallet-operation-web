<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>

<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.min.js'/>"></script>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/bootstrap/js/bootstrap.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/bootstrap.min.js'/>"></script>

<c:if test="${param.selectLib eq 'true'}">
  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/bootstrap-multiselect.min.js'/>"></script>

  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/select2/4.0.1/select2.full.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/select2.full.min.js'/>"></script>
</c:if>

<!-- Theme Initialization Files -->
<%--<script type="text/javascript" src="<c:url value='/assets/javascripts/theme-min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/theme-min.js'/>" ></script>

<%--<script type="text/javascript" src="<c:url value='/assets/javascripts/theme.init-min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/theme.init-min.js'/>" ></script>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/panel_tool/plugins.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/plugins-min.js'/>"></script>

<%--<script type="text/javascript" src="<c:url value='/assets/development/static/js/oneweek/jquery.scrollTo.min.js'/>" async></script>--%>

<c:if test="${param.tableLib eq 'true'}">
  <!-- Advanced table -->
  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery-datatables/media/js/jquery.dataTables.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.dataTables.min.js'/>" ></script>

  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery-datatables-bs3/assets/js/datatables.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/datatables.min.js'/>" ></script>

  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery-datatables/extras/TableTools/js/dataTables.tableTools.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/dataTables.tableTools.min.js'/>" ></script>
</c:if>


<c:if test="${param.dateLib eq 'true'}">
  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/daterangepicker/moment-min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/moment-min.js'/>"></script>

  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/daterangepicker/daterangepicker.min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/daterangepicker.min.js'/>" ></script>
</c:if>


<c:if test="${param.switchLib eq 'true'}">
  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/ios7-switch/ios7-switch-min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/ios7-switch-min.js'/>" ></script>

  <%--<script type="text/javascript" src="<c:url value='/assets/vendor/switchery/switchery-min.js'/>"></script>--%>
  <script type="text/javascript" src="<c:url value='/assets/development/static/js/switchery-min.js'/>"></script>
</c:if>
<script>
  var ctx = "${pageContext.request.contextPath}";
  $(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
      beforeSend: function(xhr) {
        xhr.setRequestHeader(header, token);
      }
    });
  });
</script>