/**
 * 区域管理
 */
var DesignEnergyEfficiencyArea = {
    /*$tree:$("#areaTree"),*/
    infoIndex:"",
    sortIndex:""


};

layui.use(['layer','form','jquery'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    var nodeId ="daf-a6c9-4156-a856-ab31299d9d4e_xtnx";

    /**
     * 初始化加载
     */
    $(function(){
        //nodeId ="daf-a6c9-4156-a856-ab31299d9d4e_xtnx";

        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {

            if($("#designEnergyEfficiency_mode").css("display") === "none"){
                //设计模式下
                var path = "view";
                DesignEnergyEfficiencyArea.saveDesignContent(nodeId);
            }else{
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
                $("#design_frame_energyEfficiency").attr("src",src);
            }

        } else {

            path = "displayView";
            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
            $("#design_frame_energyEfficiency").attr("src",src);
        }

    });


    /**
     * 切换之前是否保存页面
     */
    DesignEnergyEfficiencyArea.saveDesignContent = function(nodeId){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            $("#areaId_xtnx").val("daf-a6c9-4156-a856-ab31299d9d4e_xtnx");
            SaveDesign.saveContent($("#areaId_xtnx").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                DesignEnergyEfficiencyArea.changeFrame(nodeId);
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            DesignEnergyEfficiencyArea.changeFrame(nodeId);

        });
    }

    /**
     * 切换frame路径
     */
    DesignEnergyEfficiencyArea.changeFrame = function(nodeId){
        var areaId = nodeId;
        $("#design_frame_energyEfficiency").prop("src",_ctx + "/view/pageDesign/view?areaId="+nodeId);
        $("#areaId").val(nodeId);
    }




    /**
     * 刷新页面或关闭页面的浏览器监听事件
     */
    /*如果是设计模式下，离开页面之前浏览器给出提示*/
    window.onunload = function(event){
        if($("#designEnergyEfficiency_mode").css("display") === "none"){
            //如果是设计模式下
            event.preventDefault();
            return false;
        }

    }

    window.onbeforeunload = function(event){

        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {
            if($("#designEnergyEfficiency_mode").css("display") === "none"){
                //如果是设计模式下
                event.preventDefault();
                return false;
            }
        }
    }

});