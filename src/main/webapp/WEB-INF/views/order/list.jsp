<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Order list</h2>

<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Order date</th>
            <th>Address</th>
            <th>Amount</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="o" items="${orders}">
            <tr>
                <td>${o.id}</td>
                <td>${o.orderDate}</td>
                <td>${o.address}</td>
                <td>${o.amount}</td>
                <td>
                    <a href="/order/detail/${o.id}" class="btn btn-sm btn-warning">
                        Detail
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

