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

    /**
     * 初始化加载
     */
    $(function(){
        nodeId ="20200819-a6c9-4156-a856-ab31299d9d4e";
        if($("#designFlow_mode").css("display") === "none"){
            //设计模式下
            var path = "view";
            DesignFlowArea.saveDesignContent(nodeId);
        }else{
            path = "displayView";
            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+nodeId;
            $("#design_frame_two").attr("src",src);
            /*$("#areaId").val(nodeId);*/
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
        var areaId = nodeId;
        $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+nodeId);
        $("#areaId_lct").val(nodeId);
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
        if($("#designFlow_mode").css("display") === "none"){
            //如果是设计模式下
            event.preventDefault();
            return false;
        }
    }

});