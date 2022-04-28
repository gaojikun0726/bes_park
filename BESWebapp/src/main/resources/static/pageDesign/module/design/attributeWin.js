/**
 * 属性页控制
 */
var AttributeWin = {};
layui.use(['layer','colorpicker'], function(){
    var colorpicker = layui.colorpicker;
    var layer = layui.layer;
    var editJquery;

    $(function(){
        initToggleTitle();
    })

    /**
     * 点击切换组件属性和关联配置
     */
    function initToggleTitle(){
        // $("#attrTitle").click(function(){
        //     $("#attrDiv").show();
        //     $("#relativeDiv").hide();
        // });
        // $("#relativeTitle").click(function(){
        //     $("#attrDiv").hide();
        //     $("#relativeDiv").show();
        //     AttributeWin.initRelativeWin();
        // });

        $(".attr_close").click(function(){
            $("#attributeWin").hide();
            $(".design_attr_circle").show();
        });
        $(".design_attr_circle").click(function(){
            $("#attributeWin").show();
            $(".design_attr_circle").hide();
        });
    }

    /**
     * 监听事件
     */
     AttributeWin.initEvent = function(){
        $("#design_area_demo .design_initial_position,#design_area_demo .area,#design_area_demo .design_img")
            .mouseup(AttributeWin.clickEvent)
            // .mousemove(function (e) {})
            .on( "drag", function( event, ui ) {
                editJquery = $(event.target);
                // var width = editJquery.css("width");
                // var w = Number(width.replace("px",""));
                // var w_p = editJquery.parent().css("width");
                // w_p = Number(w_p.replace("px",""));
                // var left = ( w_p - w )/2;
                // editJquery.css("left",left+"px");
                // event.target.style.left = left +"px";
                var l = editJquery.css("left");
                l = l.replace("px","");
                $("#moduleLeft").val(l);
                editJquery.css("margin-left","").css("margin-right","");
                // var left = editJquery.css("margin-left");
                // if(left){
                //     editJquery.css("left",left).css("margin-left","").css("margin-right","");
                //     $("#moduleLeft").val(left.replace("px",""));
                // }
                $("#moduleLayout").val("");
                // event.preventDefault();
            } );
        //     .draggable(
        //     {start: function( event, ui ) {
        //
        //         }}
        // );
        $("#moduleName").change(function(){
            var value = $(this).val();
            editJquery.text(value);
        });

        $("#moduleWidth").change(function(){
            var value = $(this).val();
            editJquery.css("width",value+"px");
        });

        $("#moduleHeight").change(function(){
            var value = $(this).val();
            editJquery.css("height",value + "px");
        });
        $("#moduleLeft").change(function(){
            var value = $(this).val();
            editJquery.css("left",value + "px");
        });
        $("#moduleTop").change(function(){
            var value = $(this).val();
            editJquery.css("top",value + "px");
        });
        $("#moduleFontSize").change(function () {
            var value = $(this).val();
            editJquery.css("font-size",value + "px");
        });
        $("#moduleBgColor").change(function(){
            var value = $(this).val();
            editJquery.css("background-color",value);
        });

        $("#moduleLayout").change(function(){
            var value = $(this).val();
            if(value === "1"){
                //居中对齐
                editJquery.css("left","0").css("right","0").css("margin-left","auto").css("margin-right","auto");
                $("#moduleLeft").val("");
            }else if(value === "2"){
                //左对齐
                editJquery.css("left","0").css("right","").css("margin-left","").css("margin-right","");
                $("#moduleLeft").val("0");
            }else if(value === "3"){
                //右对齐
                editJquery.css("left","").css("right","0").css("margin-left","").css("margin-right","");
                $("#moduleLeft").val("");
            }else{
                var left = editJquery.css("margin-left");
                if(left){
                    editJquery.css("left",left).css("margin-left","").css("margin-right","");
                    $("#moduleLeft").val(left.replace("px",""));
                }

            }
        });

        // $("#design_area_demo .design_initial_position").on( "dragstop", function( event, ui ) {
        //     $("#moduleLayout").val("");
        // });

         //渲染
         colorpicker.render({
             elem: '#moduleBgColor'  //绑定元素
             // ,color:bgColor
             ,color:"#2A7BC1"
             ,predefine: true
             ,colors:AddTextbox.colors
             // ,colors: ['#fff','#2A7BC1','#F00','#0F0','#00F','rgb(255, 69, 0)']
             ,change: function(color){
                 editJquery.css("background-color",color);
                 // $("#moduleBgColor").val(color);
             }
             ,done: function(color){
                 editJquery.css("background-color",color);
                 //譬如你可以在回调中把得到的 color 赋值给表单
             }
         });
    }
    /**
     * 按钮点击事件，加载属性页数据
     */
    AttributeWin.clickEvent = function(){
        editJquery = $(this);
        var name = $(this).text();
        var width = $(this).css("width");
        var height = $(this).css("height");
        var left = $(this).css("left");
        var top = $(this).css("top");
        var fontSize = $(this).css("font-size");
        var bgColor = $(this).css("background-color");
        width = width.replace("px","");
        height = height.replace("px","");
        left = left.replace("px","");
        top = top.replace("px","");
        fontSize = fontSize.replace("px","");
        if($(".design_attr_circle").css("display") === "none"){
            $("#attributeWin").show();
        }else{
            $("#attributeWin").hide();
        }
        $("#moduleName").val(name);
        $("#moduleWidth").val(width);
        $("#moduleHeight").val(height);
        $("#moduleLeft").val(left);
        $("#moduleTop").val(top);
        $("#moduleFontSize").val(fontSize);
        $("#moduleBgColor .layui-colorpicker-trigger-span").css("background-color",bgColor);
        // $("#moduleLayout").val("");
        setLayoutSelect();
    }

    /**
     * 布局方式赋值
     */
    function setLayoutSelect(){
        var left = editJquery.css("left");
        var top = editJquery.css("top");
        var right = editJquery.css("right");
        left = left.replace("px","");
        top = top.replace("px","");
        right = right.replace("px","");
        var marginLeft = editJquery.css("margin-left");
        var marginRight = editJquery.css("margin-right");
        if((left === "" || left === "0") && (right === "" || right === "0") && (marginLeft === marginRight)){
            //居中对齐
            $("#moduleLayout").val("1");
        }else if(left === "0" && marginLeft === "" && marginRight === ""){
            //左对齐
            $("#moduleLayout").val("2");
        }else if(left === "" && right === "0" && marginLeft === "" && marginRight === ""){
            //右对齐
            $("#moduleLayout").val("3");
        }else{
            $("#moduleLayout").val("");
        }

    }

    // //关联点弹窗
    // AttributeWin.initRelativeWin = function(){
    //     var dataId = editJquery.attr("data-id");
    //     if(dataId){
    //         $("#design_btn_relative_id").val(dataId);
    //         $("#design_btn_relative_name").val(dataId);
    //     }
    // }
    // //关闭并清空关联点弹窗
    // AttributeWin.clearRelativeWin = function(){
    //     $("#design_btn_relative_id").val("");
    //     $("#design_btn_relative_name").val("");
    // }
    //
    // //关联点弹窗--关联按钮点击事件
    // AttributeWin.relativeBtn = function () {
    //     editJquery.attr("data-id",$("#design_btn_relative_id").val());
    // }
    //
    // //关联弹窗--选择点按钮点击事件
    // AttributeWin.choosePoint = function () {
    //     PointTree.$nodeId = $("#design_btn_relative_id");
    //     PointTree.$nodeName = $("#design_btn_relative_name");
    //     PointTree.openPointTreeWin();
    // }
});



