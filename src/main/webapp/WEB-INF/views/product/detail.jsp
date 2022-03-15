<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/8/2022
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="row">
    <div class="col-sm-5 text-center">
        <img class="detail-img" src="/static/images/products/${prod.image}" alt="">
    </div>
    <div class="col-sm-7">
        <ul class="detail-info">
            <li>Name: ${prod.name}</li>
            <li>Unit price: <f:formatNumber value="${prod.unitPrice}" pattern="#,###.00" /> VND </li>
            <li>Product date: <f:formatDate value="${prod.productDate}"  pattern="dd-MM-yyyy"/> </li>
            <li>Category: ${prod.category.nameVN}</li>
            <li>Quantity: ${prod.quantity}</li>
            <li>Discount: <f:formatNumber value="${prod.discount}"  type="percent" /></li>
            <li>View count: ${prod.viewCount}</li>
            <li>Available: ${prod.isAvailable?'Yes':'No'}</li>
            <li>Special: ${prod.isSpecial?'Yes':'No'}</li>
        </ul>
    </div>
</div>

<div class="text-justify">${prod.description}</div>

<h3>CAC MAT HANG LIEN QUAN</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#tab1">CUNG LOAI</a></li>
    <li><a data-toggle="tab" href="#tab2">YEU THICH</a></li>
    <li><a data-toggle="tab" href="#tab3">DA XEM</a></li>
</ul>

<div class="tab-content">
    <div id="tab1" class="tab-pane fade in active">
       <div>
           <c:forEach var="item" items="${list}">
               <a href="/product/detail/${item.id}">
                   <img class="thumb-img" src="/static/images/products/${item.image}" alt="">
               </a>
           </c:forEach>
       </div>
    </div>
    <div id="tab2" class="tab-pane fade">
        <div>
            <c:forEach var="item" items="${favo_list}">
                <a href="/product/detail/${item.id}">
                    <img class="thumb-img" src="/static/images/products/${item.image}" alt="">
                </a>
            </c:forEach>
        </div>
    </div>
    <div id="tab3" class="tab-pane fade">
        <div>
            <c:forEach var="item" items="${viewed_list}">
                <a href="/product/detail/${item.id}">
                    <img class="thumb-img" src="/static/images/products/${item.image}" alt="">
                </a>
            </c:forEach>
        </div>
    </div>
</div>

