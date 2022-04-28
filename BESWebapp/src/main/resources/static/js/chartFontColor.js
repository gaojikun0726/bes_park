/**
 * 根据cookie中背景皮肤值，设置首页的图表的字体颜色，
 * 默认是浅蓝色背景，深色字体
 * 缺点是更改皮肤颜色后，首页需要重新加载【图表需要重新加载才能改变字体颜色】
 */
var chartFont = {
    color: '#333'
};

$(function () {
    var skinColorvalue=$.cookie('skin_color');
    if(skinColorvalue === "blue"){
        chartFont.color = '#fff';
    }else{
        chartFont.color = '#333';
    }
});