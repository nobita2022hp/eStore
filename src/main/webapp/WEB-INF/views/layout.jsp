<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/7/2022
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online Shopping Mall</title>
    <tiles:insertAttribute name="head" />

</head>
<body>
    <div class="container">
        <header class="row">
            <h1 class="alert alert-success">Online Shopping Mall</h1>
        </header>

        <nav class="row">
            <tiles:insertAttribute name="menu" />
        </nav>

        <div class="row content">
            <article class="col-sm-9">
                <tiles:insertAttribute name="body" />
            </article>
            <aside class="col-sm-3 sidenav>
                <tiles:insertAttribute name="aside" />
            </aside>
        </div>

       <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>
