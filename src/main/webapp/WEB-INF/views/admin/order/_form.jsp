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
                <label>Order Id</label>
                <sform:input path="id" class="form-control" readonly="true" placeholder="Auto number" />
            </div>
            <div class="form-group">
                <label>Customer</label>
                <sform:input path="customer.id" class="form-control" readonly="true"/>
            </div>
            <div class="form-group">
                <label>Order date</label>
                <sform:input path="orderDate" class="form-control" />
            </div>
            <div class="form-group">
                <label>Shipping address</label>
                <sform:input path="address" class="form-control" />
            </div>
            <div class="form-group">
                <label>Total amount</label>
                <sform:input path="amount" class="form-control" />
            </div>
            <div class="form-group">
                <label>Description</label>
                <sform:textarea path="description" class="form-control" />
            </div>
            <div class="form-group">
                <button class="btn btn-primary" formaction="${base}/create">Create</button>
                <button class="btn btn-warning" formaction="${base}/update">Update</button>
                <button class="btn btn-danger" formaction="${base}/delete">Delete</button>
                <a class="btn btn-default" href="${base}/index">Reset</a>
            </div>
        </sform:form>
        <c:if test="${!empty details}">
            <jsp:include page="_detail.jsp" />
        </c:if>
    </div>
</div>

