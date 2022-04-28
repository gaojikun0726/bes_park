var ChangeSkin = {};
layui.use('layer', function(){
    var layer = layui.layer;


    //换肤时，改变设计器的皮肤颜色（设计器页面内容与index.ftl不在同一页面内，需要单独设置）
    ChangeSkin.changeDesignSkin = function(color){
        //将皮肤值保存到session中
        $.ajax({
            url : _ctx+"/view/pageDesign/setSkinColor",
            type : "post",
            data : {
                skinColor:color,
            },
            dataType:'json',
            // contentType : "application/json; charset=utf-8",
            success : function(result){
                //${Session.skinColor}值发生变化，设计器页面需要刷新来改变皮肤值
                var $AreaTree = $("#areaTree");
                if($AreaTree){
                    var treeNode = $AreaTree.jstree(true).get_selected(true); //获取所有选中的节点对象
                    if(treeNode && treeNode.length > 0){
                        var selectedId = treeNode[0].id;
                        if($("#design_mode").css("display") === "none"){
                            var path = "view";
                            ChangeSkin.saveDesignContent(selectedId);
                        }else{
                            path = "displayView";
                            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+selectedId;
                            $("#design_frame_one").attr("src",src);
                            // $("#areaId").val(selectedId);
                        }

                    }
                }

            }});
    }

    ChangeSkin.setSkinColor = function(){
        // if(!skinColorvalue){
        //     skinColorvalue = "blue";
        // }
        // //将皮肤值保存到session中
        // $.ajax({
        //     url : _ctx+"/view/pageDesign/setSkinColor",
        //     type : "post",
        //     data : {
        //         skinColor:skinColorvalue,
        //     },
        //     dataType:'json',
        //     // contentType : "application/json; charset=utf-8",
        //     success : function(result){
        // 	}});
    }


    /**
     * 切换之前是否保存页面
     */
    ChangeSkin.saveDesignContent = function(nodeId){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                ChangeSkin.changeFrame(nodeId);
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            ChangeSkin.changeFrame(nodeId);

        });
    }

    /**
     * 切换frame路径
     */
    ChangeSkin.changeFrame = function(nodeId){
        var areaId = nodeId;
        $("#design_frame_one").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
    }

});