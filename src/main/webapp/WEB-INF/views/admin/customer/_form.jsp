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
        <sform:form action="${base}/index" modelAttribute="entity" enctype="multipart/form-data">

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Id</label>
                    <sform:input path="id" class="form-control" readonly="${!empty entity.id}" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Password</label>
                    <sform:input path="password" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Full name</label>
                    <sform:input path="fullName" class="form-control" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Email address</label>
                    <sform:input path="email" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Is activated</label>
                    <div class="form-control">
                        <sform:radiobutton path="isActivated" value="true" label="Yes"/>
                        <sform:radiobutton path="isActivated" value="false" label="No"/>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label>Is admin</label>
                    <div class="form-control">
                        <sform:radiobutton path="isAdmin" value="true" label="Admin"/>
                        <sform:radiobutton path="isAdmin" value="false" label="User"/>
                    </div>
                </div>
            </div>

           <div class="row">
               <div class="form-group col-sm-12">
                   <label>Photo</label>
                   <input type="file" name="photo_file" class="form-control" />
                   <sform:hidden path="photo" />
               </div>
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

