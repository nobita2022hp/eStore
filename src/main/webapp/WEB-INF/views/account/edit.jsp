<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>

<h2>Edit profile</h2>
<h4>${message}</h4>

<sform:form action="/account/edit" method="post"
            modelAttribute="form" enctype="multipart/form-data">
    <div class="form-group">
        <label>Username</label>
        <sform:input type="text" path="id" class="form-control" readonly="true"/>
    </div>
    <div class="form-group">
        <label>Full name</label>
        <sform:input type="text" path="fullName" class="form-control" />
    </div>
    <div class="form-group">
        <label>Email address</label>
        <sform:input type="email" path="email" class="form-control" />
    </div>
    <div class="form-group">
        <label>Photo</label>
        <img src="/static/images/customers/${form.photo}" style="width: 80px; height: 90px;" alt="" />
        <input type="file" name="photo_file" />
        <sform:hidden path="photo" class="form-control" />
    </div>
    <div class="form-group">
        <sform:hidden path="password" />
        <sform:hidden path="isActivated" />
        <sform:hidden path="isAdmin" />
        <button class="btn btn-default">Update</button>
    </div>
</sform:form>

