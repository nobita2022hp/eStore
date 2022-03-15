<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Forgot password</h2>
<h4>${message}</h4>
<form action="/account/forgot" method="post">
    <div class="form-group">
        <label>Username</label>
        <input type="text" name="id" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Email address</label>
        <input type="email" name="email" class="form-control"/>
    </div>
    <div class="form-group">
        <button class="btn btn-default">Get password</button>
    </div>
</form>
<c:if test="${not empty message}">
    <a href="/account/login">Click here to login</a>
</c:if>

