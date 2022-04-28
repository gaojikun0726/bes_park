var PageDesign = {};
layui.use(['layer','element','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //新增按钮弹窗,修改按钮弹窗
    var addBtnIndex,editBtnIndex;
    var editBtnJquery;
    //设备树
    var tree;

    $(function() {
        getContent();
        $("#design_toolbar.toolbar").draggable();
        $(".design_toolbar_div").draggable();
        // $(".design_attr_circle").draggable();
        PageDesign.toolbarEvent();
    });

    $(document).click(function(e) { // 在页面任意位置点击而触发此事件
        $(e.target).attr("class");       // e.target表示被点击的目标
        var domVal = $(e.target)[0].innerText;
        var idVal = $(e.target).attr("id");
        if(typeof(domVal) != undefined&&domVal!=""){
            $("#designTextValue").val(domVal);
            $("#designIdValue").val(idVal);
        }
    })


    //查询html
    function getContent(){
        var areaId = $("#areaId").val();
        $.ajax({
            url     : _ctx + '/view/pageDesign/queryPageInfo',
            type    : "post",
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            data:JSON.stringify({areaId:areaId}),
            success : function(result) {
                if(result && result.map && result.map.html){
                  if(areaId.indexOf("_xtnx")>0){//系统能效页面
                      $("#bottomContent").css('display','block');
                      $("#topContent").css('display','block');
                      $("#design_svg").css('display','none');
                      $("#design_area_demo").html(result.map.html);



                      EnergyEfficiencyEvent.systemEnergyEfficiencyRenderChartBar("0");
                     EnergyEfficiencyEvent.showSystemEnergyEfficiencyCop();
                   /* $("#ynzb").append("<img style ='margin-top:7%; margin-left: 14%;' src=\""+_ctx+"/static/pageDesign/icon/toolbar/123456.png\">");*/

                      /*if(!$('#xtzll').length){
                          $("#ynzb").append(" <button id = 'xtzll' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 118px; height: 64px; top: 24%; left: 26%; border-radius: 29%;background-color: #ec8f2a;\" type=button\">系统制冷量</button>\n");
                      }if(!$('#cop').length){
                          $("#ynzb").append(" <button id = 'cop' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 68px; height:58px; top:9%; left: 48%;color: #ffffff; box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #2aa4ec;\" type=button\">COP</button>\n");
                      }if(!$('#wtfchw').length){
                          $("#ynzb").append(" <button id = 'wtfchw' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 68px; height:58px; top:24%; left: 53%; color: #ffffff;box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #ec8f2a;\" type=button\">WTFchw</button>\n");
                      }if(!$('#wtfcw').length){
                          $("#ynzb").append(" <button id = 'wtfcw' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 68px; height:58px; top:38%; left: 48%;color: #ffffff; box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #2aa4ec;\" type=button\">WTFcw</button>\n");
                      }if(!$('#xthdl').length){
                          $("#ynzb").append(" <button id = 'xthdl' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 118px; height:64px; top:9%; left: 78%; color: #ffffff;box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #31bb31;\" type=button\">系统耗电量</button>\n");
                      }if(!$('#lwbzhdl').length){
                          $("#ynzb").append(" <button id = 'lwbzhdl' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 118px; height:64px; top:24%; left: 82%; color: #ffffff;box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #31bb31;\" type=button\">冷温泵总耗电量</button>\n");
                      }if(!$('#lrbzhdl').length){
                          $("#ynzb").append(" <button id = 'lrbzhdl' class=\"design_initial_position design_energyEfficiency_btn\"  style =\" width: 118px; height:64px; top:38%; left: 78%; color: #ffffff;box-shadow: 2px 2px 6px;border-radius: 33%;background-color: #31bb31;\" type=button\">冷热泵总耗电量</button>\n");
                      }*/
                     // $("#selEqEnergyEfficiencyOptions").css('display','block');
                     /* $(".design_toolbar_div").css('display','none');*/
                    }else{
                      $("#bottomContent").css('display','none');
                      $("#topContent").css('display','none');
                        $("#design_area_demo").html(result.map.html);
                   }
                    var graphList = result.list;
                    if(graphList && graphList.length){
                        showGraph.initGraph(graphList);
                    }
                }
                initEffect();
            },
            error : function(result) {
                console.log(result)
            }
        });
    }


    /**
     * 表格单元格或背景区域--子元素拖动效果
     * @param $parent
     */
    function childrenDrag($parent){
        $parent.find(".design_debug_btn").draggable({containment: "parent"});
        $parent.find(".design_channel_btn").draggable({containment: "parent"});
        $parent.find(".design_scene_btn").draggable({containment: "parent"});
        $parent.find(".design_point_btn").draggable({containment: "parent"});
        $parent.find(".design_img").draggable({containment: "parent"});
        $parent.find(".design_textbox").draggable({containment: "parent"});
        $parent.find(".design_label").draggable({containment: "parent"});
    }



    //添加效果
    function initEffect(){
        $( ".design_area .design_debug_btn" ).draggable();
        $(".design_area .design_channel_btn").draggable();
        $(".design_area .design_scene_btn").draggable();
        $(".design_area .design_point_btn").draggable();
        $(".design_area .design_img").draggable();
        $( ".design_area .area" ).draggable().resizable({handles:"all"});
        $(".design_area .design_textbox").draggable().resizable({handles:"all"});
        $(".design_area .design_label").draggable();
        //改为右键菜单选择是否固定或可拖动
        $(".design_area .design_cad")
            .draggable({disabled:true})
            .resizable({handles:"all",disabled:true});
        $(".design_area .design_table_div")
            .draggable({disabled:false})
            .resizable({handles:"n,e"});
        //拖动范围限制在td边框内
        childrenDrag($(".design_area .design_table_td"));
        //拖动范围限制在背景区域内
        childrenDrag($(".design_area .area"));

        //添加温控器拖动效果
        PageDesign.initConditioner();
        AttributeWin.initEvent();

        //加载文本框数据，方便用户区分不同的文本框（显示文本可以看出调整字号效果）
        TextboxEvent.initTextboxNew();
        // $( ".design_area .area" ).draggable().resizable({ disabled: true });
    //   $( ".selector" ).resizable( "disable" );

        CopyPaste.allCopyEvent();
        //表格合并单元格选中事件
        AddTable.selectMoreCells();
        //加载放大组件
        enlargePage.initEnlargeSlide();
    }

    /**
     * 添加温控器拖动效果
     */
    PageDesign.initConditioner = function(){
        var $demo = $("#design_area_demo");
        $demo.find(".design_low_conditioner").draggable();
        $demo.find(".design_middle_conditioner").draggable();
    }


    /**
     * 保存之前需要移除某些元素
     */
    PageDesign.beforeSave = function(){
        var $parent = $("#designDiv #design_area_demo");
        //如果打开着右键菜单，再点击保存，那么该元素就会多一个样式类“context-menu-active”，
        //下次再在此元素上右键会无效
        //所以保存之前先移除该class
        $parent.find(".context-menu-active").removeClass("context-menu-active");
        //去掉 ui-resizable 拖动尺寸子元素，避免重复添加导致拖动尺寸无效
        $parent.find(".area  div.ui-resizable-handle").remove();
        $parent.find(".design_textbox").html("");
        $parent.find(".design_cad div.ui-resizable-handle").remove();
        $parent.find(".design_table_div div.ui-resizable-handle").remove();

        //去除选中的样式
        $parent.find(".design_selected").removeClass("design_selected");
        $parent.find(".design_table td.selected").removeClass("selected");
    }

    /**
     * 将工具栏保存按钮移除的部分样式添加回来
     */
    PageDesign.afterSave = function(){
        var $parent = $("#designDiv #design_area_demo");
        $parent.find(".area").draggable().resizable({handles:"all"});
        $parent.find(".design_textbox").draggable().resizable({handles:"all"});
        // $parent.find(".design_cad").resizable();
        $parent.find(".design_table_div").draggable().resizable({handles:"n,e"});

    }

    /**
     * 保存设计的html
     */
    PageDesign.saveContent = function (){
        //保存之前需要移除某些元素
        PageDesign.beforeSave();

        var content = $("#design_area_demo").html();
        var areaId = $("#areaId").val();
        //获得svg图形数据
        var totalData = getAllGraph();
        $.ajax({
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


//生成16位随机数
    PageDesign.getUUID = function () {
        return 'xxxx-xxxx-xxxx-xxxx-xxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }


    /**
     * 统计所有的图形数据
     */
     function getAllGraph(){
        var lineArray = [];
        var circleArray = [];
        var rectArray = [];
        var array = $("#design_svg line.design_shape");
        for(var i = 0; i < array.length; i++){
            var x1 = $(array[i]).attr("x1");
            var y1 = $(array[i]).attr("y1");
            var x2 = $(array[i]).attr("x2");
            var y2 = $(array[i]).attr("y2");
            var line = {x1:x1,y1:y1,x2:x2,y2:y2};
            lineArray.push(line);
        }
        var circles = $("#design_svg circle.design_shape");
        for(var k = 0; k < circles.length; k++){
            var cx = $(circles[k]).attr("cx");
            var cy = $(circles[k]).attr("cy");
            var r = $(circles[k]).attr("r");
            var circle = {cx:cx,cy:cy,r:r};
            circleArray.push(circle);
        }
        var rects = $("#design_svg rect.design_shape");
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

    /**
     * 点击工具栏图标--显示更多
     */
    PageDesign.showMore = function(){
        $(".moreToolDiv").show();
        $("#showMore").hide();
        $("#hideMore").show();
    }

    /**
     * 点击工具栏图标--收起
     */
    PageDesign.hideMore = function(){
        $(".moreToolDiv").hide();
        $("#showMore").show();
        $("#hideMore").hide();
    }

    /**
     * 工具栏相关事件
     */
    PageDesign.toolbarEvent = function(){
        form.on('switch(switch)', function(data){
            $(".design_toolbar_div .toolItem span").toggle();
        });
    }

});

    // DesignView.openAddBtnWin = function(title){
    //     if(!title){
    //         title = "新增按钮";
    //     }
    //     addBtnIndex = layer.open({
    //         type: 1,
    //         title:title,
    //         area:['300px','200px'],
    //         content: $('#design_addBtnWin') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    //     });
    // }
    //
    //
    // //添加调试按钮
    // DesignView.addDebugBtn = function (){
    //     var btnName = $("#design_add_btn_name").val();
    //     var btnWidth = $("#design_add_btn_width").val();
    //     var btnHeight = $("#design_add_btn_height").val();
    //     var style = 'style="width:'+btnWidth+'px;height:'+btnHeight+'px;"';
    //     $("#design_area_demo").append('<a class="designBtn design_initial_position design_debug_btn" '+style+' type="button">'+btnName+'</a>');
    //     $( ".design_area .design_debug_btn" ).draggable();
    //     //    .prop("draggable",true)
    //     layer.close(addBtnIndex);
    //     DesignView.clearAddBtnWin();
    // }
    //
    // //清空添加按钮弹窗
    // DesignView.clearAddBtnWin = function(){
    //     $("#design_add_btn_name").val("");
    //     $("#design_add_btn_width").val("");
    //     $("#design_add_btn_height").val("");
    // }
    //
    // //修改按钮弹窗
    // DesignView.openEditBtnWin = function(){
    //     editBtnIndex = layer.open({
    //         type: 1,
    //         title:title,
    //         area:['300px','200px'],
    //         content: $('#design_editBtnWin') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    //     });
    // }
    //
    // //修改调试按钮
    // DesignView.editDebugBtn = function ($btn){
    //     var btnName = $("#design_edit_btn_name").val();
    //     var btnWidth = $("#design_edit_btn_width").val();
    //     var btnHeight = $("#design_edit_btn_height").val();
    //     $btn.css("width",btnWidth+"px");
    //     $btn.css("height",btnHeight+"px");
    //     $btn.text(btnName);
    //     layer.close(editBtnIndex);
    //     // DesignView.clearAddBtnWin();
    // }







// /**
//  * 添加单通道按钮
//  */
// function addChannelBtn(){
//     $("#design_area_demo").append('<a class="designBtn design_initial_position design_channel_btn" type="button">单通道按钮</a>');
//     $( ".design_area .design_channel_btn" ).draggable();
// }

// /**
//  * 添加场景按钮
//  */
// function addSceneBtn(){
//     $("#design_area_demo").append('<a class="designBtn design_initial_position design_scene_btn" type="button">场景按钮</a>');
//     $( ".design_area .design_scene_btn" ).draggable();
// }

// /**
//  * 添加点位置按钮
//  */
// function addPointBtn(){
//     $("#design_area_demo").append('<a class="designBtn design_initial_position design_point_btn" type="button">点位置按钮</a>');
//     $( ".design_area .design_point_btn" ).draggable();
// }




// /**
//  * 添加文本框
//  */
// function addTextbox(){
//     $("#design_area_demo").append('<div class="design_initial_position design_textbox"></div>');
//     $( ".design_area .design_textbox" ).draggable().resizable({handles:"all"});
// }



// /**
//  * 添加标签
//  * */
// function addLabel(){
//     $("#design_area_demo").append('<label class="design_initial_position design_label"></label>');
//     $( ".design_area .design_label" ).draggable().resizable({handles:"all"});
// }
// /**
//  * 添加背景区域
//  */
// function addDiv(){
//     $("#design_area_demo").append('<div class="area"></div>');
//     $( ".design_area .area" ).draggable().resizable({handles:"all"});
// }


//
// /**
//  * 图片上传，input[type=file]
//  */
// function handleUploadImg(){
//     //
//     // var file = $("#design_upload_img").val();
//     var file = $('#design_upload_img')[0].files[0];
//     var reader = new FileReader();
//     reader.readAsDataURL(file); // 读出 base64
//     reader.onloadend = function () {
//         // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
//         var codeResult = reader.result;
//         // 下面逻辑处理
//         $("#design_area_demo").append('<img class="design_img design_initial_position" src="'+codeResult+'">');
//         $(".design_img").draggable();
//     };
//     // var codeResult = $.base64('encode', file);
//
// }
//
//
// /**
//  * 添加背景图片
//  */
// function handleUploadBackImg(){
//     var file = $('#design_upload_back_img')[0].files[0];
//     var reader = new FileReader();
//     reader.readAsDataURL(file); // 读出 base64
//     reader.onloadend = function () {
//         // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
//         var codeResult = reader.result;
//         // 下面逻辑处理
//         //
//         $("#design_back_div").css("background","url("+codeResult+") no-repeat 100% 100% " );
//     };
// }

