<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<h2>Login</h2>
<h4>${message}</h4>
<form action="/account/login" method="post">
    <div class="form-group">
        <label>Username</label>
        <input type="text" name="id" class="form-control" value="${uid}"/>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" name="pw" class="form-control" value="${pwd}"/>
    </div>
    <div class="form-group">
        <div class="form-control">
            <input type="checkbox" name="rm" />
            <label>Remember me?</label>
        </div>
    </div>
    <div class="form-group">
        <button class="btn btn-default">Login</button>
    </div>
</form>

