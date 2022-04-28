/**
 * 字体放大
 */
var CopyModuleLabel = {};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
   var addModuleIndex;

    $(function() {
        getModuleContent();

    });

//查询模板编辑html
        function getModuleContent(){
            var areaId = $("#moduleAreaId").val();
            $.ajax({
                url     : _ctx + '/view/pageDesign/queryPageInfo',
                type    : "post",
                dataType:'json',
                contentType : "application/json; charset=utf-8",
                data:JSON.stringify({areaId:areaId}),
                success : function(result) {
                    if(result && result.map && result.map.html){
                        $("#designModule_area_demo").html(result.map.html);
                        initEffect();
                    }
                },
                error : function(result) {
                    console.log(result)
                }
            });

        }

    //添加可拖动效果
    function initEffect(){
        $(".design_area .design_debug_btn" ).draggable();
        $(".design_area .design_channel_btn").draggable();
        $(".design_area .design_scene_btn").draggable();
        $(".design_area .design_point_btn").draggable();
        $(".design_area .design_img").draggable();
        $( ".design_area .area" ).draggable().resizable({handles:"all"});
        $(".design_area .design_textbox").draggable().resizable({handles:"all"});
        $(".design_area .design_label").draggable();
    }

    CopyModuleLabel.openCopyModuleWin = function(){
        $("#designModule_win_btn_div").css("display","block");
        $("#designModuleDiv").css("display","block");
        $("#moduleToolDiv").css("display","block");
        $("#designModuleDiv").prop("src",_ctx + "/view/pageDesign/moduleView");
        $("#symbolChildPage").val("moduleChildPage");

        addModuleIndex = layer.open({
                type: 1,
                title:"模板",
                area:['900px','700px'],
                content: $('#designModuleDiv'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    CopyModuleLabel.closeWin();
                    return false;
                }
            });
    }
         //模板保存按钮
        CopyModuleLabel.confirmBtn = function(flag){
            //去掉 ui-resizable 拖动尺寸子元素，避免重复添加导致拖动尺寸无效
            $("#designModuleDiv #designModule_area_demo .area").html("");
            $("#designModuleDiv #designModule_area_demo .design_textbox").html("");
            var content = $("#designModule_area_demo").html();
            var areaId = $("#moduleAreaId").val();
            $.ajax({
                url     : _ctx + '/view/pageDesign/savePageInfo',
                type    : "post",
                data    : JSON.stringify({
                    areaId:areaId,
                    html:content,
                    graphs:""
                }),
                dataType:'json',
                contentType : "application/json; charset=utf-8",
                success : function(result) {
                    if(result && result.status === "1"){
                        if(flag=="1"){
                            layer.msg("复制成功",{icon:1});
                        }else{
                            layer.msg("保存成功",{icon:1});
                        }
                    }else{
                        if(flag=="1"){
                            layer.msg("复制失败",{icon:2});
                        }else{
                            layer.msg("复制成功",{icon:2});
                        }
                    }

                },

                error : function(result) {
                    layer.msg("保存失败",{icon:2});
                }
            });


        }
        //复制模板
        CopyModuleLabel.copyModuleBtn = function(){
            CopyModuleLabel.confirmBtn("1");
            $("#symbolChildPage").val("");
            var links =document.getElementById("designModule_area_demo").getElementsByTagName("a");
            var childNodes =document.getElementById("designModule_area_demo").childNodes;//div下所有子元素
           for(var i = 3;i<childNodes.length;i++){
               childNodes[i].setAttribute("id",PageDesign.getUUID());//修改元素id
               $("#design_area_demo").append(childNodes[i].outerHTML);
           }
            initEffect();
            layer.close(addModuleIndex);
            $("#moduleToolDiv").css("display","none");
            $("#designModule_win_btn_div").css("display","none");

        }
       //取消
        CopyModuleLabel.closeWin = function(){
            layer.close(addModuleIndex);
            $("#moduleToolDiv").css("display","none");
            $("#designModule_win_btn_div").css("display","none");
            $("#symbolChildPage").val("");

        }













});