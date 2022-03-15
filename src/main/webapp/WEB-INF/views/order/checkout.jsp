<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<h2>Shopping cart</h2>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Unit price</th>
        <th>Discount</th>
        <th>Quantity</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${sessionScope['scopedTarget.cartService'].items}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.unitPrice}</td>
            <td>${p.discount}</td>
            <td>${p.quantity}</td>
            <td>${p.quantity * p.unitPrice * (1 - p.discount)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Checkout</h2>
<h4>${message}</h4>

<sform:form action="/order/checkout" modelAttribute="order" >
    <div class="form-group">
        <label>Customer</label>
        <sform:input type="text" path="customer.id" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Order date</label>
        <sform:input type="text" path="orderDate" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Shipping address</label>
        <sform:input type="text" path="address" class="form-control" />
    </div>
    <div class="form-group">
        <label>Total amount</label>
        <sform:input type="number" path="amount" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Notes</label>
        <sform:textarea path="description" rows="3" class="form-control" />
    </div>
    <div class="form-group">
        <button class="btn btn-default">Purchase</button>
    </div>
</sform:form>

