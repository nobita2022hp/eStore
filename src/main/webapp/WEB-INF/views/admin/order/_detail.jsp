<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<table class="table table-hover">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Unit price</th>
            <th>Discount</th>
            <th>Quantity</th>
            <th>Amount</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="e" items="${details}">
            <tr>
                <td>${e.product.id}</td>
                <td>${e.product.name}</td>
                <td>${e.unitPrice}</td>
                <td>${e.discount}</td>
                <td>${e.quantity}</td>
                <td>${e.unitPrice * e.quantity * (1 - e.discount)}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

