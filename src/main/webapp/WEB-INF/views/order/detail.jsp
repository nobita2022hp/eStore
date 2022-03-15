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

<h2>Order info</h2>

<sform:form modelAttribute="order" >
    <div class="form-group">
        <label>Order ID</label>
        <sform:input type="text" path="id" class="form-control" readonly="true"/>
    </div>
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
        <sform:input type="text" path="address" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Total amount</label>
        <sform:input type="number" path="amount" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Notes</label>
        <sform:textarea path="description" rows="3" class="form-control" readonly="true"/>
    </div>
</sform:form>

<h2>Order detail</h2>

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
    <c:forEach var="d" items="${details}">
        <tr>
            <td>${d.product.id}</td>
            <td>${d.product.name}</td>
            <td>${d.unitPrice}</td>
            <td>${d.discount}</td>
            <td>${d.quantity}</td>
            <td>${d.quantity * d.unitPrice * (1 - d.discount)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

