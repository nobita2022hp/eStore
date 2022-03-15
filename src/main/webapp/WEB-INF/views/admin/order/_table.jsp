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
            <th>Customer</th>
            <th>Order date</th>
            <th>Shipping address</th>
            <th>Total amount</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="e" items="${list}">
            <tr>
                <td>${e.id}</td>
                <td>${e.customer.id}</td>
                <td>${e.orderDate}</td>
                <td>${e.address}</td>
                <td>${e.amount}</td>
                <td>
                    <a class="btn btn-sm btn-info" href="${base}/edit/${e.id}">Edit</a>
                    <a class="btn btn-sm btn-danger" href="${base}/delete/${e.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

