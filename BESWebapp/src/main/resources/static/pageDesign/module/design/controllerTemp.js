/**
 * 温控器
 */
var ControllerTempLabel= {};
layui.use(['upload','layer','form'], function(){
    var layer = layui.layer;
    var upload = layui.upload;
    var form = layui.form;
    //温控器信息展示方式选择弹窗
    var controllerTempTypeIndex;

    $(function() {
        //saveTemSettings();

    });



//选择温控器展示类型弹窗
    ControllerTempLabel.addContrTempWin = function(){
        controllerTempTypeIndex = layer.open({
            type: 1,
            title:"展示方式",
            area:['34vw','38vh'],
            // area:['400px','300px'],
            scrollbar: false,
            shadeClose: true,
            fixed:false,
            content: $('#design_type_controller_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                ControllerTempLabel.closeSelectTypeWin();
                return false;
            },
            success:function(){
                form.render();
            }
        });
    }
//确定
    ControllerTempLabel.confirmControllerType = function(){
        var level = $("#design_type_controller_win input[name=conditioner_level]:checked").val();
        if(level === "low"){
            //低档温控器
            ControllerTempLabel.lowConditioner();
        }else if(level === "middle"){
            //中档温控器
            ControllerTempLabel.middleConditionerNew();
        }
    }

    /**
     * 选择低档温控器
     */
    ControllerTempLabel.lowConditioner = function(){
        var type = $("#design_type_controller_win input[name=tempType]:checked").val();
        if(type === "icons"){
            //图标模式，页面上展示低档温控器的小图标
            $("#design_area_demo").append('<div class="design_low_conditioner icon_img design_initial_position">'
                +'<img class="blue_img"  src="'+_ctx+'/static/pageDesign/images/air_conditioner/low/conditioner8.png">'
                +'<img class="gray_img"  src="'+_ctx+'/static/pageDesign/images/air_conditioner/low/conditioner_gray6.png"></div>');
            $(".design_low_conditioner").draggable();
        }else if(type === "listInfo"){
            //列表模式，展示低档温控器的整个图形界面
            $("#design_area_demo").append('<div class="design_low_conditioner list_img design_initial_position"><img  class="bg_img" src="'+_ctx+'/static/pageDesign/images/air_conditioner/low/blue_bg.png"></div>');
            $(".design_low_conditioner").draggable();
        }
        ControllerTempLabel.closeSelectTypeWin();
    }


    /**
     * 选择中档温控器
     */
    ControllerTempLabel.middleConditionerNew = function(){
        var type = $("#design_type_controller_win input[name=tempType]:checked").val();
        if(type === "icons"){
            //图标模式，页面上展示低档温控器的小图标
            $("#design_area_demo").append('<div class="design_middle_conditioner icon_img design_initial_position">'
                +'<img class="blue_img" src="'+_ctx+'/static/pageDesign/images/air_conditioner/middle/conditioner1.png">'
            +'<img class="gray_img" src="'+_ctx+'/static/pageDesign/images/air_conditioner/middle/conditioner_gray3.png"></div>');
            $(".design_middle_conditioner").draggable();
        }else if(type === "listInfo"){
            //列表模式，展示低档温控器的整个图形界面
            $("#design_area_demo").append('<div class="design_middle_conditioner list_img design_initial_position"><img class="bg_img" src="'+_ctx+'/static/pageDesign/images/air_conditioner/middle/temp_box.jpg"></div>');
            $(".design_middle_conditioner").draggable();
        }
        ControllerTempLabel.closeSelectTypeWin();
    }

    /**
     * 选择中档温控器
     */
    ControllerTempLabel.middleConditioner = function(){
        var TypeVal=$('input:radio[name="tempType"]:checked').val();
        if(TypeVal == "icons"){//以图标的形式展示温控器详细信息
            $("#design_area_demo").append("<img class='design_tempImg' src=\""+_ctx+"/static/pageDesign/icon/toolbar/temper.png\"/>");
            var $textbox = $("#design_area_demo").find(".design_tempImg");
            if($("#iconTempModule").length > 0){
            }else{
                $("#design_area_demo").append("<input type = 'text' id = 'iconTempModule'>");
            }
            var sysList="";
            for(var i = 0; i < $textbox.length; i++){
                sysList += $($textbox[i]).data("id")+",";
                $("#iconTempModule").val(sysList);
            }
            $("#iconTempModule").css("display","none");
            $(".design_tempImg").draggable();
            ControllerTempLabel.closeSelectTypeWin();
            // alert($("#iconTempModule").val());
        }else if(TypeVal == "listInfo"){//以列表的形式展示温控器的详细信息+
           var divChild = document.getElementById("settingsTable");
           // var randomId = PageDesign.getUUID();
         /*   $("#settingsTable").css("position","fixed");*/
            $("#settingsTable").css("top","20px");
            $("#design_area_demo").append(divChild.outerHTML);
            $("#design_area_demo").draggable();
            $(".design_div").draggable();
            ControllerTempLabel.closeSelectTypeWin();
        }
    }
    //取消
    ControllerTempLabel.closeSelectTypeWin= function(){
        layer.close(controllerTempTypeIndex);

        $("input[name='conditioner_level'][value='low']").prop("checked","checked");
        $("input[name='tempType'][value='icons']").prop("checked","checked");
        form.render();
    }

});

