//图例功能实现js
$(function(){
    legendControl();
});
//图例控制
function legendControl(){
    $(".legendIcon").click(function(){
        $(this).hide();
        $(".legendDiv").show();
    });
    $(".legendDiv").click(function(){
        $(this).hide();
        $(".legendIcon").show();
    });
}
