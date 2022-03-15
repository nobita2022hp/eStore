<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 3/9/2022
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send to friend</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Receiver email</label>
                    <input type="email" id="email" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Comment</label>
                    <textarea id="comments" rows="3" class="form-control"></textarea>
                </div>
                <input type="hidden" id="id">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-default btn-send">Send</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(".btn-send").click(function(){
        let form = {
          id:$("#myModal #id").val(),
          from: "henrylarkson102@gmail.com",
          to:$("#myModal #email").val(),
          body:$("#myModal #comments").val(),
        };

        $.ajax({
            url: "/product/send-to-friend/",
            data: form,
            success: function (response) {
                if (response){
                    $("[data-dismiss]").click();
                    alert("da gui email OK");
                }else {
                    alert("da gui email NOT OK");
                }
            }
        });
    });
</script>
