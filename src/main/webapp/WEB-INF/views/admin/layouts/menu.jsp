<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/home/index">Trang chu</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Quan ly<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/admin/category/index">Loai</a></li>
                    <li><a href="/admin/product/index">San pham</a></li>
                    <li><a href="/admin/customer/index">Khach hang</a></li>
                    <li><a href="/admin/order/index">Don hang</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Thong ke<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/admin/inventory/index">Ton kho theo loai</a></li>
                    <li><a href="/admin/revenue/category">Doanh so theo loai</a></li>
                    <li><a href="/admin/revenue/customer">Doanh so theo khach hang</a></li>
                    <li><a href="/admin/revenue/year">Doanh so theo nam</a></li>
                    <li><a href="/admin/revenue/month">Doanh so theo thang</a></li>
                    <li><a href="/admin/revenue/quarter">Doanh so theo quy</a></li>
                </ul>
            </li>
            <li><a href="/home/feedback">Tai khoan</a></li>
        </ul>
    </div>
</nav>
