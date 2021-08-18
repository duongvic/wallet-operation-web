<%@ page import="vn.mog.ewallet.operation.web.controller.epin.EpinPurchaseOrderController" %>
<%@ include file="../include_page/taglibs.jsp" %>

<section class="panel panel-default">
    <div class="panel-title pl-none">
        <h4 class="fl"><spring:message code="transaction.api.detail.transAttribute"/></h4>
        <ul class="panel-tools fl tool-filter">
            <li><a class="icon minimise-tool"><i class="fa fa-chevron-down text-xs"></i></a></li>
        </ul>
        <div class="clr"></div>
    </div>
    <div class="panel-body report_search_form">
        <c:choose>
            <c:when test="${transaction.serviceType eq 'EPIN'}">
                <spring:message var="colAttribute1" code="transaction.api.table.softPinAttribute2"/>
                <spring:message var="colAttribute2" code="transaction.api.table.softPinAttribute3"/>
                <spring:message var="colAttribute3" code="transaction.api.table.softPinAttribute1"/>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
                <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="0" end="2">
                    <div class="form-group mb-small">
                        <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                        ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                        <div class="col-sm-9 col-md-10 text-normal">${attribute.transactionAttributeValue}</div>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${transaction.serviceType eq 'PHONE_TOPUP'}">
                <spring:message var="colAttribute1" code="transaction.api.table.topUpAttribute3"/>
                <spring:message var="colAttribute2" code="transaction.api.table.topUpAttribute2"/>
                <spring:message var="colAttribute3" code="transaction.api.table.topUpAttribute1"/>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
                <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="0" end="2">
                    <div class="form-group mb-small">
                        <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                        ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                        <div class="col-sm-9 col-md-10 text-normal">${attribute.transactionAttributeValue}</div>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${transaction.serviceType eq 'EXPORT_EPIN'}">
                <spring:message var="colAttribute1" code="transaction.api.table.exportSoAttribute1"/>
                <spring:message var="colAttribute2" code="transaction.api.table.exportSoAttribute2"/>
                <spring:message var="colAttribute3" code="transaction.api.table.exportSoAttribute3"/>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
                <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="0" end="0">

                    <div class="form-group mb-small">
                        <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                        ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                        <div class="col-sm-9 col-md-10 text-normal">
                            <c:choose>
                                <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                    <a href="<%=request.getContextPath()%><%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>?poCode=${attribute.transactionAttributeValue}"
                                       class="link-active">${attribute.transactionAttributeValue}</a>
                                </c:when>
                                <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </c:when>

            <c:when test="${transaction.serviceType eq 'BILL_PAYMENT'}">
                <spring:message var="colAttribute1" code="transaction.api.table.billPaymentAttribute1"/>
                <spring:message var="colAttribute2" code="transaction.api.table.billPaymentAttribute2"/>
                <spring:message var="colAttribute3" code="transaction.api.table.billPaymentAttribute3"/>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
                <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="0" end="2">
                    <div class="form-group mb-small">
                        <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                        ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                        <div class="col-sm-9 col-md-10 text-normal">${attribute.transactionAttributeValue}</div>
                    </div>
                </c:forEach>
            </c:when>

            <c:otherwise>

                <spring:message var="colAttribute1" code="transaction.api.table.attribute1"/>
                <spring:message var="colAttribute2" code="transaction.api.table.attribute2"/>
                <spring:message var="colAttribute3" code="transaction.api.table.attribute3"/>
                <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>

                <c:choose>
                    <c:when test="${transaction.attributes.size() > 3}">
                        <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="0"
                                   end="2">
                            <div class="form-group mb-small">
                                <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                        ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                                ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                                <div class="col-sm-9 col-md-10 text-normal">
                                    <c:choose>
                                        <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                            <a href="<%=request.getContextPath()%><%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>?poCode=${attribute.transactionAttributeValue}"
                                               class="link-active">${attribute.transactionAttributeValue}</a>
                                        </c:when>
                                        <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow">

                            <div class="form-group mb-small">
                                <label class="col-sm-3 col-md-2 control-label">${(statusRow.index + 1) eq 1 ? colAttribute1 :
                                        ((statusRow.index + 1) eq 2 ? colAttribute2 :
                                                ((statusRow.index + 1) eq 3 ? colAttribute3 : colAttributeOther))}</label>
                                <div class="col-sm-9 col-md-10 text-normal"><c:choose>
                                    <c:when test="${attribute.transactionAttributeType eq 'EPIN_PURCHASE_ORDER_CODE'}">
                                        <a href="<%=request.getContextPath()%><%=EpinPurchaseOrderController.EPIN_PO_DETAIL%>?poCode=${attribute.transactionAttributeValue}"
                                           class="link-active">${attribute.transactionAttributeValue}</a>
                                    </c:when>
                                    <c:otherwise>${attribute.transactionAttributeValue}</c:otherwise>
                                </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${transaction.attributes.size() eq 1}">
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute2}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute3}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                            </c:when>
                            <c:when test="${transaction.attributes.size() eq 2}">
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute3}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                            </c:when>
                            <c:when
                                    test="${transaction.attributes.size() eq 3}"><!-- nothing --></c:when>
                            <c:otherwise>
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute1}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute2}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                                <div class="form-group mb-small">
                                    <label class="col-sm-3 col-md-2 control-label">${colAttribute3}</label>
                                    <div class="col-sm-9 col-md-10 text-normal"></div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

            </c:otherwise>
        </c:choose>

        <!-- thong tin them -->

        <spring:message var="colAttributeOther" code="transaction.api.table.attributeOther"/>
        <c:choose>
            <c:when test="${transaction.attributes.size() > 3}">
                <div class="form-group mb-small">
                    <label class="col-sm-3 col-md-2 control-label">${colAttributeOther}</label>
                    <div class="col-sm-9 col-md-10 text-normal">
                        <c:forEach var="attribute" items="${transaction.attributes}" varStatus="statusRow" begin="3">
                            ${attribute.transactionAttributeType}_${attribute.transactionAttributeValue}
                            <br/>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

        <c:if test="${transaction.extraInfo}">
            <spring:message var="colExtraInfo" code="label.extra.info"/>
            <div class="form-group mb-small">
                <label class="col-sm-3 col-md-2 control-label">${ewallet:toUpperCase(colExtraInfo)}</label>
                <div class="col-sm-9 col-md-10 text-normal">
                        ${transaction.extraInfo}
                </div>
            </div>
        </c:if>

    </div>
</section>