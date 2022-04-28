var SaveDesign = {};
layui.use('layer', function(){
    var layer = layui.layer;


    /**
     * 保存之前需要移除某些元素
     * @param $content
     */
    SaveDesign.beforeSave = function($content){
        var selector = "#designDiv #design_area_demo";
        //如果打开着右键菜单，再点击保存，那么该元素就会多一个样式类“context-menu-active”，
        //下次再在此元素上右键会无效
        //所以保存之前先移除该class
        $content.find(selector).find(".context-menu-active").removeClass("context-menu-active");
        //去掉 ui-resizable 拖动尺寸子元素，避免重复添加导致拖动尺寸无效
        $content.find(selector).find(".area  div.ui-resizable-handle").remove();
        $content.find(selector).find(".design_textbox").html("");
        $content.find(selector).find(".design_cad div.ui-resizable-handle").remove();
        $content.find(selector).find(".design_table_div div.ui-resizable-handle").remove();

        //去除选中的样式
        $content.find(".design_selected").removeClass("design_selected");
        $content.find(".design_table td.selected").removeClass("selected");
    }

    /**
     * 保存设计的html
     */
    SaveDesign.saveContent = function (areaId){
        if(areaId.indexOf("_lct")>0){//流程图页面
            var $content = $("#design_frame_two").contents();
        }else if(areaId.indexOf("_xtnx")>0){//系统能效页面
            var $content = $("#design_frame_energyEfficiency").contents();
        }else{
            var $content = $("#design_frame_one").contents();
        }

        //保存之前需要移除某些元素
        SaveDesign.beforeSave($content);

        var content = $content.find("#design_area_demo").html();
        // var content = $("#designDiv").html();
        //var areaId = $("#areaId").val();

        //获得svg图形数据
        var totalData = getAllGraph();
        SaveDesign.method = $.ajax({
            url     : _ctx + '/view/pageDesign/savePageInfo',
            type    : "post",
            data    : JSON.stringify({
                areaId:areaId,
                html:content,
                graphs:totalData
            }),
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            success : function(result) {
                if(result && result.status === "1"){
                    layer.msg("保存成功",{icon:1});
                }else{
                    layer.msg("保存失败",{icon:2});
                }

            },

            error : function(result) {
                layer.msg("保存失败",{icon:2});
            }
        });
    }


    /**
     * 统计所有的图形数据
     */
    function getAllGraph(){
        var lineArray = [];
        var circleArray = [];
        var rectArray = [];
        var $content = $("#design_frame_one").contents();
        var array = $content.find("#design_svg line.design_shape");
        for(var i = 0; i < array.length; i++){
            var x1 = $(array[i]).attr("x1");
            var y1 = $(array[i]).attr("y1");
            var x2 = $(array[i]).attr("x2");
            var y2 = $(array[i]).attr("y2");
            var line = {x1:x1,y1:y1,x2:x2,y2:y2};
            lineArray.push(line);
        }
        var circles = $content.find("#design_svg circle.design_shape");
        for(var k = 0; k < circles.length; k++){
            var cx = $(circles[k]).attr("cx");
            var cy = $(circles[k]).attr("cy");
            var r = $(circles[k]).attr("r");
            var circle = {cx:cx,cy:cy,r:r};
            circleArray.push(circle);
        }
        var rects = $content.find("#design_svg rect.design_shape");
        for(var j = 0; j < rects.length; j++){
            var x = $(rects[j]).attr("x");
            var y = $(rects[j]).attr("y");
            var width = $(rects[j]).attr("width");
            var height = $(rects[j]).attr("height");
            var rect = {x:x,y:y,width:width,height:height};
            rectArray.push(rect);
        }
        var totalData = {};
        totalData.lines = lineArray;
        totalData.rects = rectArray;
        totalData.circles = circleArray;
        return totalData;

    }

});