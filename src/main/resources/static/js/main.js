$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    //
    // var productName = $('#produto.name').text();
    // var productDescription = $('#produto.description').text();
    // var productPrice= $('#produto.price').text();
    //
    // var produto = {
    //     "id" : "",
    //     "name" : productName,
    //     "description" : productDescription,
    //     "file" : "",
    //     "price" : productPrice
    // };

    var data = new FormData(form);

    // data.append("produto", produto);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/mypan/admin/addProduct",
        data: data,
        dataType: 'json',
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            window.location.href = "http://localhost:7000/mypan/admin/product/new";
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}