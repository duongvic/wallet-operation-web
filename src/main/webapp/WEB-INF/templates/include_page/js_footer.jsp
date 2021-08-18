<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.browser.mobile.js'/>" async></script>
<%--<script type="text/javascript" src="<c:url value='/assets/vendor/modernizr/modernizr.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/modernizr-min.js'/>" async></script>

<!-- Specific Page Vendor -->

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/nanoscroller/nanoscroller.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/nanoscroller.min.js'/>" async></script>

<!-- Theme Base, Slide -->
<%--<script type="text/javascript" src="<c:url value='/assets/vendor/pnotify/pnotify.custom.js'/>"></script>--%>
<%--<script type="text/javascript" src="<c:url value='/assets/development/static/js/oneweek/pnotify.custom.js'/>" async></script>--%>
<%--<script type="text/javascript" src="<c:url value=""/>/assets/vendor/owl-carousel/owl.carousel.min.js"></script>--%>

<%--<script type="text/javascript" src="<c:url value='/assets/javascripts/bootstrap-notify.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/bootstrap-notify.min.js'/>"></script>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery-placeholder/jquery.placeholder-min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.placeholder-min.js'/>"></script>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery/messagebox.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/messagebox.min.js'/>"></script>


<%--<script type="text/javascript" src="<c:url value='/assets/vendor/navbar-animate/jquery.bootstrap-dropdown-on-hover.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.bootstrap-dropdown-on-hover.min.js'/>"></script>

<%--<script type="text/javascript" src="<c:url value='/assets/vendor/magnific-popup/magnific-popup-min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/magnific-popup-min.js'/>" async></script>--%>

<!-- i18n -->
<%--<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.i18n-min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.i18n.messagestore-min.js'/>" async></script>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/jquery.i18n.parser-min.js'/>" async></script>
<script type="text/javascript" src="<c:url value='/assets/development/ewallet_i18n.js'/>" async></script>--%>

<%--<script type="text/javascript" src="<c:url value='/assets/development/static/js/oneday.1.0.6/analytics.js'/>" async></script>--%>
<script type="text/javascript" src="<c:url value='/assets/development/static/js/common.js'/>" async></script>
<%--<script type="text/javascript" src="<c:url value='/assets/vendor/jquery/jquery.validate-min.js'/>"></script>--%>
<script type="text/javascript"
        src="<c:url value='/assets/javascripts/commonJS/format.currency.js'/>"></script>
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

    var searchUrl = location.search;
    if (searchUrl.indexOf('?') > -1) {
      if (searchUrl.indexOf('lang=en') > -1) {
        $("#langEn").attr("href", searchUrl);
        $("#langVi").attr("href", searchUrl.replace('lang=en', "lang=vi"));
      } else if (searchUrl.indexOf('lang=vi') > -1) {
        $("#langEn").attr("href", searchUrl.replace('lang=vi', "lang=en"));
        $("#langVi").attr("href", searchUrl);
      } else {
        $("#langEn").attr("href", searchUrl + '&lang=en');
        $("#langVi").attr("href", searchUrl + '&lang=vi');
      }
    } else {
      $("#langEn").attr("href", searchUrl + '?lang=en');
      $("#langVi").attr("href", searchUrl + '?lang=vi');
    }


    /*Focus link click url*/
    $('.epinStoreHref').on('click', function() {
      var menuClick = $(this).data("menu-click");
      setCookie("menu-click", menuClick, 1, "/store")
    });
    // $('.epinStoreHref').on('click', function() {
    //   var menuClick = $(this).data("menu-click");
    //   setCookie("menu-click", menuClick, 4, "/store")
    // });

    $('.offEpinStoreHref').on('click', function() {
      var menuClick = $(this).data("menu-click");
      setCookie("menu-click", menuClick, 1, "/store-offline")
    });

    $('.n02EpinStoreHref').on('click', function() {
      var menuClick = $(this).data("menu-click");
      setCookie("menu-click", menuClick, 1, "/store-n02")
    });

    $('.saleCenterHref').on('click', function() {
      var menuClick = $(this).data("menu-click");
      setCookie("menu-click", menuClick, 1, "/sale-center")
    });


    $('.purchaseCenterHref').on('click', function() {
      var menuClick = $(this).data("menu-click");
      setCookie("menu-click", menuClick, 1, "/purchase-center")
    });

    /*  *Focus link click url*!/ */
  });

  function setCookie(name, value, days, path) {
    var expires = "";
    if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=" + path;
  }

  function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
  }

  function eraseCookie(name) {
    document.cookie = name + '=; Max-Age=-99999999;';
  }

  function replaceUrlParam(url, paramName, paramValue) {
    if (paramValue == null) {
      paramValue = '';
    }
    var pattern = new RegExp('\\b(' + paramName + '=).*?(&|#|$)');
    if (url.search(pattern) >= 0) {
      return url.replace(pattern, '$1' + paramValue + '$2');
    }
    url = url.replace(/[?#]$/, '');
    return url + (url.indexOf('?') > 0 ? '&' : '?') + paramName + '=' + paramValue;
  }
</script>
