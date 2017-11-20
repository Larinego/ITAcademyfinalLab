$(document).ready(function () {
    $('.addProductBtn').click(function () {
        addProduct($(this));
    });
    $('.reduceProductBtn').click(function () {
        reduceProduct($(this));
    });
    /*$('.addOrderBtn').click(function () {
        $.ajax({
            type: 'get',
            url: contextUrl + '/addOrder'
        });
    });*/
});

function addProduct(element) {
    var productId = $(element).attr('id');
    var supplier = $("#productSupplier"+productId)["0"].innerText;
    var model = $("#productModel"+productId)["0"].innerText;
    var price = $("#productPrice"+productId)["0"].innerText;
    var product = {
        productId:productId,
        supplier: supplier,
        model: model,
        price: price
    };
    var json = JSON.stringify(productId);
    console.log(json);
    $.ajax({
        url:contextUrl + '/incrementProductInBasket',
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: json, //Stringified Json Object
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser
        processData:false, //To avoid making query String instead of JSON
        success: function(resposeJsonObject){
            $('#productCount'+productId).text(resposeJsonObject);
        }
    });
}

function reduceProduct(element) {
    var productId = $(element).attr('id');
    var supplier = $("#productSupplier"+productId)["0"].innerText;
    var model = $("#productModel"+productId)["0"].innerText;
    var price = $("#productPrice"+productId)["0"].innerText;
    var product = {
        productId:productId,
        supplier: supplier,
        model: model,
        price: price
    };
    var json = JSON.stringify(productId);
    console.log(json);
    $.ajax({
        url:contextUrl + '/decrementProductInBasket',
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data: json, //Stringified Json Object
        async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
        cache: false,    //This will force requested pages not to be cached by the browser
        processData:false, //To avoid making query String instead of JSON
        success: function(resposeJsonObject){
            $('#productCount'+productId).text(resposeJsonObject);
        }
    }).done(function (data) {
        $('#productCount'+productId).text(data);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}

