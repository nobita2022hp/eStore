<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>

<h2 class="alert alert-danger">Revenue by quarter</h2>
<table class="table table-hover">
    <thead>
        <tr>
            <th>Quarter</th>
            <th>Quantity</th>
            <th>Revenue</th>
            <th>Min price</th>
            <th>Max price</th>
            <th>Average</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="e" items="${data}">
            <tr>
                <td>
                    <f:formatNumber value="${e[0]}" pattern="#,###" />
                </td>
                <td>${e[1]}</td>
                <td>
                    $ <f:formatNumber value="${e[2]}" pattern="#,###.00" />
                </td>
                <td>
                    $ <f:formatNumber value="${e[3]}" pattern="#,###.00" />
                </td>
                <td>
                    $ <f:formatNumber value="${e[4]}" pattern="#,###.00" />
                </td>
                <td>
                    $ <f:formatNumber value="${e[5]}" pattern="#,###.00" />
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>