<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div id="mloading" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <spring:message code="common.waitting.pl"/>
      </div>
    </div>
  </div>
</div>

<footer class="main-footer">
  <div class="container-fluid pt-sm">
    <div class="row">
      <div class="col-xs-12 col-md-6 col-md-offset-6">
        <div class="footer-info"><span>© 2019 by Zo-Ta JSC</span></div>
      </div>
    </div>
  </div>
</footer>