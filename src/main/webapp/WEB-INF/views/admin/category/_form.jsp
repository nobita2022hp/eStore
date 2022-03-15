<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<div class="panel panel-default">
    <div class="panel-body">
        <sform:form action="${base}/index" modelAttribute="entity">
            <div class="form-group">
                <label>Id</label>
                <sform:input path="id" class="form-control" />
            </div>
            <div class="form-group">
                <label>Name</label>
                <sform:input path="name" class="form-control" />
            </div>
            <div class="form-group">
                <label>Name VN</label>
                <sform:input path="nameVN" class="form-control" />
            </div>
            <div class="form-group">
                <button class="btn btn-primary" formaction="${base}/create">Create</button>
                <button class="btn btn-warning" formaction="${base}/update">Update</button>
                <button class="btn btn-danger" formaction="${base}/delete">Delete</button>
                <a class="btn btn-default" href="${base}/index">Reset</a>
            </div>
        </sform:form>
    </div>
</div>

