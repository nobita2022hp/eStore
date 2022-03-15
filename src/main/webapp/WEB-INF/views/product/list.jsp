<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>
    List products of
    <c:if test="${!empty categoryName}">
        ${categoryName}
    </c:if>
    <c:if test="${!empty keywords}">
        ${keywords}
    </c:if>
</h2>
<c:forEach var="p" items="${list}">
    <div class="col-sm-4">
        <div class="thumbnail">
            <a href="/product/detail/${p.id}">
                <img class="estore-prod" src="/static/images/products/${p.image}">
            </a>
            <div class="caption">
                <p>${p.name}</p>
                <div class="pull-right"  data-id="${p.id}">
                    <button class="btn btn-sm btn-danger">
                        <i class="glyphicon glyphicon-shopping-cart btn-add-to-cart"></i>
                    </button>
                    <button  class="btn btn-sm btn-warning btn-star">
                        <i class="glyphicon glyphicon-star"></i>
                    </button>
                    <button class="btn btn-sm btn-success btn-open-dialog" data-toggle="modal" data-target="#myModal">
                        <i class="glyphicon glyphicon-envelope"></i>
                    </button>
                </div>
                <p>${p.unitPrice}</p>
            </div>
        </div>
    </div>
</c:forEach>

<jsp:include page="dialog.jsp" />

