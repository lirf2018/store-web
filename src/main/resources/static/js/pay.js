function closeToAddrDiv() {
    layer.closeAll();
}

//去付款(选择支付方式)
function chosePayWay(orderNo) {
    payOrderNo = orderNo;
    showPayWay();
}


var html3 = $("#html3").html();//支付html
var payOrderNo = "";//订单号

//选择支付方式
function showPayWay() {
    $("#html3").html("");
    layer.open({
        type: 1
        , content: html3
        , anim: 'up'
        , shadeClose: false
        //,style: 'position:fixed; bottom:0; left:0; width: 100%; height: 200px; padding:10px 0; border:none;line-height: 10px'
        , style: 'position:fixed; bottom:0; left:0; width: 100%; border:none;'
    });
    $(".layui-m-layer *").css("-webkit-box-sizing", "inherit")
    $(".layui-m-layer *").css("box-sizing", "inherit")
}

//生成支付中心参数
function submitPayCenterData(payWay) {
    var data = {
        "payWay": payWay,
        "orderNo": payOrderNo
    }
    if (payWay == '' || payOrderNo == '') {
        console.log("缺少支付中心必须参数")
        return showDialog('支付失败');
    }
    $.ajax({
        url: "order/createPayCenter",//生成支付中心必须参数
        type: 'POST',
        data: data,// 要提交的表单 ,
        async: false,
        cache: false,
        dataType: "json",
        success: function (data) {
            window.location.href = "http://lirf-shop.51vip.biz/pay-center/pay/payEnter?base64Str=" + data.values;
        }
    });
}