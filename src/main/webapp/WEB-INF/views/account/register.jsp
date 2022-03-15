<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>

<h2>Register</h2>
<h4>${message}</h4>

<sform:form action="/account/register" method="post"
            modelAttribute="form" enctype="multipart/form-data">
    <div class="form-group">
        <label>
            <stag:message code="user.id" />
        </label>
        <sform:input type="text" path="id" class="form-control" />
        <sform:errors path="id" />
    </div>
    <div class="form-group">
        <label>
            <stag:message code="user.pw" />
        </label>
        <sform:input type="password" path="password" class="form-control" />
        <sform:errors path="password" />
    </div>
    <div class="form-group">
        <label>Full name</label>
        <sform:input type="text" path="fullName" class="form-control" />
        <sform:errors path="fullName" />
    </div>
    <div class="form-group">
        <label>Email address</label>
        <sform:input type="email" path="email" class="form-control" />
        <sform:errors path="email" />
    </div>
    <div class="form-group">
        <label>Photo</label>
        <input type="file" name="photo_file" />
        <sform:hidden path="photo" class="form-control" />
    </div>
    <div class="form-group">
        <button class="btn btn-default">Register</button>
    </div>
</sform:form>

