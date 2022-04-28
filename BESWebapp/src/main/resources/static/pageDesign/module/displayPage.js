var DisplayPage = {};

layui.use(['layer','form'], function(){
var layer = layui.layer;
var form = layui.form;

    var debugWinIndex;

    $(function() {
        //取消所有订阅事件
        // parent.PubSub.unsubscribe();

        DisplayPage.getContent();
        // Transform.transformEvent();
    });


//查询html
    DisplayPage.getContent = function(){
        var areaId = $("#areaId").val();
        $.ajax({
            url     : _ctx + '/view/pageDesign/queryPageInfo',
            type    : "post",
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            data:JSON.stringify({areaId:areaId}),
            success : function(result) {
                if(result && result.map && result.map.html){
                    if(areaId.indexOf("_xtnx")>0){
                        $("#bottomContent").css('display','block');
                        $("#topContent").css('display','block');
                        $("#design_svg").css('display','none');
                        $("#design_area_demo").html(result.map.html);
                        EnergyEfficiencyEvent.systemEnergyEfficiencyRenderChartBar("1");//展示设备能耗占比
                        //EnergyEfficiencyEvent.showSystemEnergyEfficiencyCop();//展示系统cop
                       //$("#selEqEnergyEfficiencyOptions").css('display','block');
                    }else{
                        $("#bottomContent").css('display','none');
                        $("#topContent").css('display','none');
                        $("#design_area_demo").html(result.map.html);
                    }
                    if(result.list && result.list.length){
                        var graphList = result.list;
                        showGraph.displayGraph(graphList);
                    }
                    form.render();
                    DebugEvent.initButtonEvent();
                    PointEvent.initButtonEvent();
                    TempIconEvent.initIconSettingEvent();
                    TempListEvent.initListSettingEvent();
                    //初始化所有的点位置按钮
                    PointEvent.initBtn();
                    ChannelEvent.initBtn();
                    ChannelEvent.initButtonEvent();
                    SceneEvent.initBtn();
                    SceneEvent.initButtonEvent();
                    TextboxEvent.initTextboxNew();
                    LabelEvent.initLabelNew();
                    ImgEvent.initImg();
                    ImgEvent.initButtonEvent();
                    FlowEvent.initFlowLabel();
                    EnergyEfficiencyEvent.initEnergyEfficiencyBtn();
                    EnergyEfficiencyEvent.initTableListDataInfo();
                    showConditioner.initAll();
                    showMiddleConditioner.initAll();
                    //窗帘
                    CurtainEvent.initAll();
                    //组件关联点的订阅事件
                    realTimeData.allControlRegister();

                    //移除组件的title
                    DisplayPage.removeTitle();
                }
            },
            error : function(result) {
                console.log(result)
            }
        });
    }

    /**
     * 移除组件的title
     */
    DisplayPage.removeTitle = function(){
        var $parent = $("#design_area_demo");
        $parent.find(".design_debug_btn,.design_channel_btn,.design_scene_btn,.design_point_btn").removeAttr("title");
        $parent.find(".design_img,.design_textbox,.design_label").removeAttr("title");
        $parent.find(".design_low_conditioner,.design_middle_conditioner").removeAttr("title");
    }

});

