<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/home/index">
                <stag:message code="lyt.menu.home" />
            </a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/home/about">About us</a></li>
            <li><a href="/home/contact">Contact us</a></li>
            <li><a href="/home/feedback">Feedback</a></li>
            <li><a href="/home/faq">FAQs</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Account<span class="caret"></span></a>
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <ul class="dropdown-menu">
                            <li><a href="/account/login">Dang nhap</a></li>
                            <li><a href="/account/register">Dang ky</a></li>
                            <li><a href="/account/forgot">Quen mat khau</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="dropdown-menu">
                            <li><a href="/account/logout">Dang xuat</a></li>
                            <li><a href="/account/change">Doi mat khau</a></li>
                            <li><a href="/account/edit">Update account</a></li>
                            <li><a href="/order/list">Don hang</a></li>
                            <li><a href="/order/items">Cac hang da mua</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#" data-lang="vi">Tieng viet</a></li>
            <li><a href="#" data-lang="en">English</a></li>
        </ul>
    </div>
</nav>

<script>
    $(function () {
        $('a[data-lang]').click(function () {
           let lang = $(this).attr("data-lang");
           $.ajax({
               url: "/home/language?lang=" + lang,
               success: function () {
                   location.reload();
               }
           });

           return false;
        });
    })
</script>
