/**
 * 背景颜色操作
 */

var BackColor = {
    selectedArea:''
};

layui.use(['upload','layer','form','colorpicker'], function(){
    var layer = layui.layer;
    var colorpicker = layui.colorpicker;

    $(function () {
        initEvent();
    });

    // 在页面任意位置点击而触发此事件
    function initEvent(){
        $("#designDiv").click(function(e) {

            $(".design_selected").removeClass("design_selected");
            if(e.target.classList.contains("area")){
                // $(e.target).css("box-shadow"," 0 0 15px #03a9f4");
                $(e.target).addClass("design_selected");
                BackColor.selectedArea = $(e.target);
            }else{
                BackColor.selectedArea = "";
            }

            //选中组件改变字体
            TextSettingLabel.clickEvent(e.target);
        });

        //渲染
        colorpicker.render({
            elem: '#design_bgColor'  //绑定元素
            // ,color:bgColor
            ,color:"#2A7BC1"
            ,predefine: true
            ,colors:AddTextbox.colors
            // ,colors: ['#fff','#001b3a','#2A7BC1','#F00','#0F0','#00F','rgb(255, 69, 0)']
            ,change: function(color){
                if(BackColor.selectedArea){
                    BackColor.selectedArea.css("background-color",color);
                }else{
                    $("#design_back_div").css("background-color",color);
                }
            }
            ,done: function(color){
                if(BackColor.selectedArea){
                    BackColor.selectedArea.css("background-color",color);
                }else{
                    $("#design_back_div").css("background-color",color);
                }
            }
        });
    }

    // //根据点击的区域的design_selected来判断
    // BackColor.changeColor = function () {
    //
    //     if(BackColor.selectedArea){
    //         // $("#changeBackColor").attr("id","full");
    //         $("#full").show().trigger("click");
    //         // $("#full").attr("id","changeBackColor");
    //     }else{
    //         layer.msg("请先选择一个背景区域");
    //         // $("#full").preventDefault();
    //     }
    // }



});

