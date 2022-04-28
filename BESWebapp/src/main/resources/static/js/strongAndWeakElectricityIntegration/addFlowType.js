/**
 * 区域管理
 */
var AddFlowType = {

};

layui.use(['layer','form','jquery'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    var addFlowTypeIndex,editFlowTypeIndex;

    //新增弹窗
    AddFlowType.openAddFlowTypeWin = function(){
        addFlowTypeIndex = layer.open({
            type: 1,
            title:"新增流程图",
            area:['400px','300px'],
            content: $('#design_addFlowTypeWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddFlowType.closeAddFlowTypeWin();
                return false;
            }
        });
    }


    //编辑弹窗
    AddFlowType.openEditFlowTypeWin = function(){
        $('#edit_flowTypeName').val($('#flowName').val());
        editFlowTypeIndex = layer.open({
            type: 1,
            title:"编辑流程图",
            area:['400px','300px'],
            content: $('#design_editFlowTypeWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddFlowType.closeEditFlowTypeWin();
                return false;
            }
        });
    }

    //取消按钮--关闭编辑流程图页面
    AddFlowType.closeEditFlowTypeWin= function(){
        layer.close(editFlowTypeIndex);
    }

    //取消按钮--关闭添加流程图页面
    AddFlowType.closeAddFlowTypeWin = function(){
        layer.close(addFlowTypeIndex);
        $("#add_flowTypeName").val("");
    }
  /*新增流程图节点*/
    AddFlowType.addFlowType = function(){
        if($("#add_flowTypeName").val()){
            $.ajax({
                url: _ctx + "/view/strongAndWeakElectricityIntegration/flowChart/addFlowType",
                type    : "post",
                dataType: 'json',
                async:false,
                data : {
                    f_name : $("#add_flowTypeName").val()
                },
                success : function(result) {
                    if(result.status  =="1"){
                        var select= document.getElementById("selFlowOptions");
                        var objOption = document.createElement("OPTION");
                        objOption.value = result.data.f_id;
                        objOption.text =result.data.f_name;
                        select.add(objOption);
                        layer.close(addFlowTypeIndex);
                    }
                }
            });
        }else{
            layer.msg("流程图名称不能为空",{icon:0});
            return;
        }
    }
    /**
     * 下拉框改变事件
     */
    AddFlowType.selFlowOptions = function(){
        $("#areaId_lct").val($("#selFlowOptions").val());
        $("#flowName").val($("#selFlowOptions").find("option:selected").text());
        if($("#selFlowOptions").val()){
            var areaId=$("#areaId_lct").val();
            if($("#designFlow_mode").css("display") === "none"){
                //设计模式下
                var path = "view";
                $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
                $("#addFlow_type").show();//添加流程图类型按钮显示
                $("#editFlow_type").show();//编辑流程图类型按钮显示
                $("#delFlow_type").show();//删除流程图类型按钮显示
            }else{
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+areaId;
                $("#design_frame_two").attr("src",src);
                $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
                $("#delFlow_type").hide();//删除流程图类型按钮隐藏
            }

        }

    }

    /**
     * 初始化加载
     */
    $(function(){
        $.ajax({
            url: _ctx + "/view/strongAndWeakElectricityIntegration/flowChart/getFlowTypeInfo",
            type    : "post",
            dataType: 'json',
            async:false,
            success : function(result) {
                if(result.status  =="1"){
                    if(result.list.length>0){
                        for(var i =0;i<result.list.length;i++){
                            var option = document.createElement("option");
                            //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                            $(option).val(result.list[i].f_id);
                            //给option的text赋值,这就是你点开下拉框能够看到的东西
                            $(option).text(result.list[i].f_name);
                            //获取select 下拉框对象,并将option添加进select
                            $('#selFlowOptions').append(option);
                            $("#areaId_lct").val(result.list[0].f_id);
                            $("#flowName").val(result.list[0].f_name);
                        }
                    }

                }
            }
        });
        var areaId=$("#areaId_lct").val();


        var sbgl = $("#leftMenu").text();
        if (sbgl.indexOf("设备管理") != -1) {

            if($("#designFlow_mode").css("display") === "none"){
                //设计模式下
                var path = "view";
                $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
                //DesignFlowArea.saveDesignContent(nodeId);
                $("#addFlow_type").show();//添加流程图类型按钮显示
                $("#editFlow_type").show();//编辑流程图类型按钮显示
                $("#delFlow_type").show();//删除流程图类型按钮显示
            }else{
                path = "displayView";
                var src = _ctx+"/view/pageDesign/"+path+"?areaId="+areaId;
                $("#design_frame_two").attr("src",src);
                $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
                $("#delFlow_type").hide();//删除流程图类型按钮隐藏
                /*$("#areaId").val(nodeId);*/
            }
        } else {//如果是用户登录
            path = "displayView";
            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+areaId;
            $("#design_frame_two").attr("src",src);
            $("#addFlow_type").hide();//添加流程图类型按钮隐藏
            $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
            $("#delFlow_type").hide();//删除流程图类型按钮隐藏
        }



    });

    AddFlowType.editFlowType = function(){
        if( $('#edit_flowTypeName').val()){
            $.ajax({
                url: _ctx + "/view/strongAndWeakElectricityIntegration/flowChart/editFlowType",
                type    : "post",
                dataType: 'json',
                async:false,
                data : {
                    f_id:$("#areaId_lct").val(),
                    f_name : $("#edit_flowTypeName").val()
                },
                success : function(result) {
                    if(result.status  =="1"){
                        if(result.list.length>0){
                            document.getElementById("selFlowOptions").options.length = 0;//清除所有的option选项
                            for(var i =0;i<result.list.length;i++){
                                var option = document.createElement("option");
                                //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                                $(option).val(result.list[i].f_id);
                                //给option的text赋值,这就是你点开下拉框能够看到的东西
                                $(option).text(result.list[i].f_name);
                                //获取select 下拉框对象,并将option添加进select
                                $('#selFlowOptions').append(option);
                                $("#areaId_lct").val(result.list[0].f_id);
                                $("#flowName").val($("#edit_flowTypeName").val());
                            }
                            var select = document.getElementById("selFlowOptions");
                            for(var i=0; i<select.options.length; i++){
                                if(select.options[i].innerHTML == $("#edit_flowTypeName").val()){
                                    select.options[i].selected = true;
                                    break;
                                }
                            }
                        }
                        layer.close(editFlowTypeIndex);
                    }
                }
            });
        }else{
            layer.msg("流程图名称不能为空",{icon:0});
            return;
        }
    }
    /**
     * 删除流程图
     */
    AddFlowType.openDelFlowTypeWin = function(){
        //删除
        layer.confirm('确定删除该流程图吗?', {title:'删除提示'}, function(index){
        $.ajax({
            url: _ctx + "/view/strongAndWeakElectricityIntegration/flowChart/deleteFlowType",
            type    : "post",
            dataType: 'json',
            async:false,
            data : {
                f_id:$("#areaId_lct").val(),
            },
            success : function(result) {
                if(result.status  =="1"){
                    document.getElementById("selFlowOptions").options.length = 0;//清除所有的option选项
                    if(result.list.length>0){
                        for(var i =0;i<result.list.length;i++){
                            var option = document.createElement("option");
                            //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                            $(option).val(result.list[i].f_id);
                            //给option的text赋值,这就是你点开下拉框能够看到的东西
                            $(option).text(result.list[i].f_name);
                            //获取select 下拉框对象,并将option添加进select
                            $('#selFlowOptions').append(option);
                            $("#areaId_lct").val(result.list[0].f_id);
                            $("#flowName").val(result.list[0].f_name);
                        }
                        var areaId = result.list[0].f_id;
                        if($("#designFlow_mode").css("display") === "none"){
                            //设计模式下
                            var path = "view";
                            $("#design_frame_two").prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
                            //DesignFlowArea.saveDesignContent(nodeId);
                            $("#addFlow_type").show();//添加流程图类型按钮显示
                            $("#editFlow_type").show();//编辑流程图类型按钮显示
                            $("#delFlow_type").show();//删除流程图类型按钮显示
                        }else{
                            path = "displayView";
                            var src = _ctx+"/view/pageDesign/"+path+"?areaId="+areaId;
                            $("#design_frame_two").attr("src",src);
                            $("#addFlow_type").hide();//添加流程图类型按钮隐藏
                            $("#editFlow_type").hide();//编辑流程图类型按钮隐藏
                            $("#delFlow_type").hide();//删除流程图类型按钮隐藏
                            /*$("#areaId").val(nodeId);*/
                        }
                    }

                }
            }
        });


            layer.close(index);
        });
    }



});