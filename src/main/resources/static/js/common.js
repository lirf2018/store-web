function showDialog(msg) {
    layer.open({
        content: msg
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
    });
}

//金额格式转换 保留2位小数
function parsePrice(price) {
    price = price + "";
    var priceArray = price.split(".");
    if (priceArray.length == 1) {
        return price + ".00";
    } else if (priceArray.length == 2) {
        var end = priceArray[1];
        if(end.length == 1){
            return price + "0";
        }
    }
    return price;
}