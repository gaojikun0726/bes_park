/**
 * 区域管理
 */
var DesignFlowArea = {
    /*$tree:$("#areaTree"),*/
    infoIndex:"",
    sortIndex:""


};

layui.use(['layer','form','jquery'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    var nodeId ="";
    var nodeName = ""

    /**
     * 初始化加载
     */
    $(function(){
        if($("#areaId").val()){
            nodeId = $("#areaId").val();
            nodeName = $("#flowName").val();

            var sbgl = $("#leftMenu").text();
            if (sbgl.indexOf("设备管理") != -1) {
                if($("#designFlow_mode").css("display") === "none"){
                    //设计模式下
                    var path = "view";
                    DesignFlowArea.saveDesignContent(nodeId);
                    $("#addFlow_type").show();//添加流程图类型按钮显示
                }else{//编辑模式
                    path = "displayView";
                    var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                    $("#design_frame_two").attr("src",src);
                    $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                    /*$("#areaId").val(nodeId);*/
                }

            }else {//如果是用户登录
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                $("#design_frame_two").attr("src",src);
                $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                $("#runFlow_mode").hide();
            }


        }else{
            $.ajax({
                url: _ctx + "/view/strongAndWeakElectricityIntegration/flowChart/getFlowTypeInfo",
                type    : "post",
                dataType: 'json',
                async:false,
                success : function(result) {
                    if(result.status  =="1"){
                        if(result.list.length>0){
                                $("#areaId").val(result.list[0].f_id);
                                $("#flowName").val(result.list[0].f_name);
                                 nodeId =$("#areaId").val();
                                 nodeName =$("#flowName").val();
                        }

                    }
                }
            });

            var sbgl = $("#leftMenu").text();
            if (sbgl.indexOf("设备管理") != -1) {

                if($("#designFlow_mode").css("display") === "none"){
                    //设计模式下
                    var path = "view";
                    DesignFlowArea.saveDesignContent(nodeId);
                    $("#addFlow_type").show();//添加流程图类型按钮隐藏
                }else{
                    path = "displayView";
                    var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                    $("#design_frame_two").attr("src",src);
                    $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                    /*$("#areaId").val(nodeId);*/
                }
            } else {//如果是用户登录
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                $("#design_frame_two").attr("src",src);
                $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                $("#runFlow_mode").hide();
            }
        }


    });


    /**
     * 切换之前是否保存页面
     */
    DesignFlowArea.saveDesignContent = function(nodeId){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId_lct").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignFlowArea.changeFrame(nodeId);
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignFlowArea.changeFrame(nodeId);

        });
    }

    /**
     * 切换frame路径
     */
    DesignFlowArea.changeFrame = function(nodeId){
        //var areaId = nodeId;
        $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+nodeId);
        $("#areaId").val(nodeId);
        $("#flowName").val(nodeName);
    }




    /**
     * 刷新页面或关闭页面的浏览器监听事件
     */
    /*如果是设计模式下，离开页面之前浏览器给出提示*/
    window.onunload = function(event){
        if($("#designFlow_mode").css("display") === "none"){
            //如果是设计模式下
            event.preventDefault();
            return false;
        }

    }

    window.onbeforeunload = function(event){

        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {
            if($("#designFlow_mode").css("display") === "none"){
                //如果是设计模式下
                event.preventDefault();
                return false;
            }
        }

    }

});