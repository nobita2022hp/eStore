$(document).ready(function () {
    $("tr[data-id] input").on("input", function () {
        let id = $(this).closest("tr").attr("data-id");
        let price = $(this).closest("tr").attr("data-price");
        let discount = $(this).closest("tr").attr("data-discount");
        let quantity = $(this).val();

        $.ajax({
            url: `/cart/update/${id}/${quantity}`,
            success: function (response) {
                $("#cart-cnt").html(response[0]);
                $("#cart-amt").html(response[1]);
            }
        });

        let amt = quantity * price * (1 - discount);
        $(this).closest("tr").find(".amt").html(amt);
    });

    $(".btn-cart-remove").click(function () {
        let id = $(this).closest("tr").attr("data-id");
        $.ajax({
            url: "/cart/remove/" + id,
            success: function (response) {
                $("#cart-cnt").html(response[0]);
                $("#cart-amt").html(response[1]);
            }
        });

        $(this).closest("tr").remove();
    });

    $(".btn-star").click(function () {
       let id = $(this).closest("div").attr("data-id");

        $.ajax({
            url: "/product/add-to-favo/" + id,
            success: function (response) {
                if (response)
                    alert("da them OK: " + id);
                else
                    alert("da co san: " + id);
            },
            error: function (response) {
                alert("da them NOT OK: " + id);
            }
        });
    });

    $(".btn-open-dialog").click(function () {
        let id = $(this).closest("div").attr("data-id");
        $("#myModal #id").val(id);
    });

    $(".btn-add-to-cart").click(function () {
        let id = $(this).closest("div").attr("data-id");
        $.ajax({
            url: "/cart/add/" + id,
            success: function (response) {
                $("#cart-cnt").html(response[0]);
                $("#cart-amt").html(response[1]);
            }
        });


        let img = $(this).closest(".thumbnail").find("a>img");
        let options = {
            to: '#cart-img',
            className: 'cart-fly'
        };

        let cart_css = '.cart-fly{background-image: url("'
            +img.attr("src")
            +'");background-size: 100% 100%;}';
        $("style#cart_css").html(cart_css);
        img.effect('transfer', options, 1000);
    });

    $(".btn-cart-clear").click(function () {
        $.ajax({
            url: "/cart/clear",
            success: function (response) {
                $("#cart-cnt").html(0);
                $("#cart-amt").html(0);
                $("table>tbody").html("");
            }
        });
    });
})