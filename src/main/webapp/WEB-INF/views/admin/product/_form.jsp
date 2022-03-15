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
                    <sform:input path="id" class="form-control" readonly="true" placeholder="Auto number" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Name</label>
                    <sform:input path="name" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Unit price</label>
                    <sform:input path="unitPrice" class="form-control" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Quantity</label>
                    <sform:input path="quantity" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Discount</label>
                    <sform:input path="discount" class="form-control" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Product date</label>
                    <sform:input path="productDate" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>Is special</label>
                    <div class="form-control">
                        <sform:radiobutton path="isSpecial" value="true" label="Yes"/>
                        <sform:radiobutton path="isSpecial" value="false" label="No"/>
                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <label>Is available</label>
                    <div class="form-control">
                        <sform:radiobutton path="isAvailable" value="true" label="Yes"/>
                        <sform:radiobutton path="isAvailable" value="false" label="No"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-sm-6">
                    <label>View count</label>
                    <sform:input path="viewCount" class="form-control" />
                </div>
                <div class="form-group col-sm-6">
                    <label>Category</label>
                    <sform:select path="category.id" class="form-control">
                        <sform:options items="${cates}" itemLabel="nameVN" itemValue="id" />
                    </sform:select>
                </div>
            </div>
           <div class="row">
               <div class="form-group col-sm-12">
                   <label>Image</label>
                   <input type="file" name="image_file" class="form-control" />
                   <sform:hidden path="image" />
               </div>
           </div>

            <div class="row">
                <div class="form-group col-sm-12">
                    <label>Description</label>
                    <sform:textarea rows="3" path="description" class="form-control" />
                </div>
            </div>

            <div class="form-group col-sm-12">
                <button class="btn btn-primary" formaction="${base}/create">Create</button>
                <button class="btn btn-warning" formaction="${base}/update">Update</button>
                <button class="btn btn-danger" formaction="${base}/delete">Delete</button>
                <a class="btn btn-default" href="${base}/index">Reset</a>
            </div>
        </sform:form>
    </div>
</div>

<script type="text/javascript">
    //let x;
    bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>