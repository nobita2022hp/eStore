<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<table class="table table-hover">
    <thead>
        <tr>
            <th>ID</th>
            <th>Full name</th>
            <th>Email</th>
            <th>Is activated</th>
            <th>Role</th>
            <th></th>
        </tr>
    </thead>
    <tbody id="tbody">
<%--        <c:forEach var="e" items="${list}">--%>
<%--            <tr>--%>
<%--                <td>${e.id}</td>--%>
<%--                <td>${e.fullName}</td>--%>
<%--                <td>${e.email}</td>--%>
<%--                <td>${e.isActivated?'Yes':'No'}</td>--%>
<%--                <td>${e.isAdmin?'Admin':'User'}</td>--%>
<%--                <td class="text-center">--%>
<%--                    <a class="btn btn-sm btn-info" href="${base}/edit/${e.id}">Edit</a>--%>
<%--                    <a class="btn btn-sm btn-danger" href="${base}/delete/${e.id}">Delete</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
    </tbody>
</table>

<ul class="pager">
    <li><a href="#"><i class="glyphicon glyphicon-hand-up"></i></a></li>
    <li><a href="#"><i class="glyphicon glyphicon-hand-left"></i></a></li>
    <li><a href="#"><span id="pager-info">5/15</span></a></li>
    <li><a href="#"><i class="glyphicon glyphicon-hand-right"></i></a></li>
    <li><a href="#"><i class="glyphicon glyphicon-hand-down"></i></a></li>
</ul>

<script>
    $(function () {
        let pageN0 = 0;
        let pageCount = 0;
        let pageSize = 5;
        $.ajax({
            url: '/pager/customer/page-count/' + pageSize,
            success: function (response) {
                pageCount = response;
                $(".pager a:eq(0)").click();
            }
        });

       $(".pager a:eq(0)").click(function () {
           pageN0 = 0;
           let info = {
               pageNo: pageN0,
               pageSize: pageSize
           };

           $.ajax({
               url: '/pager/customer/page',
               data: info,
               success: fnSuccess
           });

           return false;
       });

        $(".pager a:eq(1)").click(function () {
            if (pageN0 > 0){
                pageN0--;
                let info = {
                    pageNo: pageN0,
                    pageSize: pageSize
                };

                $.ajax({
                    url: '/pager/customer/page',
                    data: info,
                    success: fnSuccess
                });
            }

            return false;
        });

        $(".pager a:eq(3)").click(function () {
            if (pageN0 < pageCount - 1){
                pageN0++;
                let info = {
                    pageNo: pageN0,
                    pageSize: pageSize
                };

                $.ajax({
                    url: '/pager/customer/page',
                    data: info,
                    success: fnSuccess
                });
            }

            return false;
        });

        $(".pager a:eq(4)").click(function () {
            pageN0 = pageCount - 1;
            let info = {
                pageNo: pageN0,
                pageSize: pageSize
            };

            $.ajax({
                url: '/pager/customer/page',
                data: info,
                success: fnSuccess
            });

            return false;
        });

        function fnSuccess (response) {
            $("#tbody").html("");
            $(response).each(function (index, user) {
                let tr = $("<tr/>");
                console.log(index, user);
                $("<td/>").html(user.id).appendTo(tr);
                $("<td/>").html(user.fullName).appendTo(tr);
                $("<td/>").html(user.email).appendTo(tr);
                $("<td/>").html(user.isActivated?'Yes':'No').appendTo(tr);
                $("<td/>").html(user.isAdmin?'Admin':'User').appendTo(tr);

                let s = `<td class="text-center">
                            <a class="btn btn-sm btn-info" href="/admin/customer/edit/`+ user.id +`">Edit</a>
                            <a class="btn btn-sm btn-danger" href="/admin/customer/delete/`+ user.id +`">Delete</a>
                        </td>`;
                $("<td/>").html(s).appendTo(tr);
                tr.appendTo("#tbody");

                $("#pager-info").html((pageN0 + 1) + '/' + pageCount);
            });
        }
    });
</script>