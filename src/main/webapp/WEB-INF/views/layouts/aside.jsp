<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>

<c:set var="cart" value="${sessionScope['scopedTarget.cartService']}" />
<c:set var="lang" value="${pageContext.response.locale.language}" />

<div class="panel panel-default">
    <div class="panel-heading">
        <stag:message code="lyt.cart.title" />
    </div>
    <div class="panel-body">
        <img id="cart-img" src="/static/images/cart.jpg" alt="" class="col-sm-5"/>
        <ul class="col-sm-7">
            <li><b id="cart-cnt">${cart.count}</b> mat hang</li>
            <li>
                <b id="cart-amt">
                    <f:formatNumber value="${cart.amount}" pattern="#,###.00" />
                </b> VND</li>
            <li>
                <a href="/cart/view">Xem gio hang</a>
            </li>
        </ul>

        <div class="panel-heading">TIM KIEM</div>
        <div class="panel-body">
            <form action="/product/list-by-keywords" method="post">
                <input value="${param.keywords}" type="text" name="keywords" class="form-control" placeholder="Keywords" />
            </form>
        </div>

        <div class="panel-heading">
            <stag:message code="lyt.cate.title" />
        </div>
        <div class="panel-body">
            <div class="list-group">
                <c:forEach items="${cates}" var="ca">
                    <a href="/product/list-by-category/${ca.id}" class="list-group-item">
                            ${lang == 'vi' ? ca.nameVN : ca.name}
                    </a>
                </c:forEach>
            </div>
        </div>

        <div class="panel-heading">DAC BIET</div>
        <div class="panel-body">
            <div class="list-group">
                <a href="/product/list-by-special/0" class="list-group-item">Hang moi</a>
                <a href="/product/list-by-special/1" class="list-group-item">Ban chay</a>
                <a href="/product/list-by-special/2" class="list-group-item">Xem nhieu</a>
                <a href="/product/list-by-special/3" class="list-group-item">Giam gia</a>
            </div>
        </div>
    </div>
</div>

<style id="cart_css">

</style>




