<#-------------------
描述：  环比预警报警
作者：	liuzhen
时间：	2018/12/4
-------------------->


<style>
    .hb_alarm_count-content {
        width: 100%;
        clear: both;
        position: relative;
        overflow: hidden;
    }
    .zl_sxtj_span{width:10vh;}
    .hb_alarm_export-pdf {
        width: 95%;
        height: 100%;
        padding: 0 15px;
        margin: 0 auto;
        overflow: hidden;
    }

    .hb_alarm_pdf-down-left {
        float: left;
        margin-top: 10vh;
        width: 40%;

    }

    .hb_alarm_pdf-down-title {
        padding-left: 20px;
        font-size: 16px;
        margin-top: 12vh
    }

    .hb_alarm_pdf-down-right {
        float: right;
        width: 45%;
        height: 70vh;
        margin-top: 6vh;
    }

    .hb_alarm_totala{
        height: 100px;
        width: 100px;
        text-align: center;
        position: absolute;
        top: 28%;
        left: 34%;
        margin-left: -48px;
    }

    .search_hb_alarm_count_div_style {
        display: flex;
        align-items: center;
        margin-right: 3vh;
        float: right;
        white-space: nowrap;
        text-overflow: ellipsis;
        padding: 5px 0px 1px 0px;
    }

    .search_hb_alarm_count_style {
        width: 100%;
    }

</style>

<div   class="leftarea information_left" >
    <div  class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;筛选条件>>>
		</span>
    </div>
    <div class="search_hb_alarm_count_style">

        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">日期范围:</span>
            <div id='alarm_date_group'></div>
        </div>

        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">对比形式:</span>
            <div  id="alarm_compare_group"></div>
        </div>
        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">园区名称:</span>
            <div id='hb_alarmData_sydw'></div>
        </div>

        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">报警状态:</span>
            <div id='alarmData_hb_state'></div>
        </div>

        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">起始时间:</span>
            <input  id="hb_alarmCount_start_time" type="text"  name="start" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,onpicked:dataAnalysis_wainingInfo_hb_alarmDataCount.compareChange})" class="input-datecheck"  />
        </div>


        <div class="search_hb_alarm_count_div_style" >
            <span class="zl_sxtj_span">终止时间:</span>
            <input  id="hb_alarmCount_end_time" type="text"  name="end"  onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,onpicked:dataAnalysis_wainingInfo_hb_alarmDataCount.compareChange})" class="input-datecheck" >

        </div>

        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">同期起始:</span>
            <input id="alarm_sametime_start_time" disabled="disabled" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})">
        </div>


        <div class="search_hb_alarm_count_div_style">
            <span class="zl_sxtj_span">同期终止:</span>
            <input id="alarm_sametime_end_time" disabled="disabled"  class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',firstDayOfWeek:1,readOnly:true})">
        </div>
        <div class="search_hb_alarm_count_div_style">
            <div style="float:right;margin-top: 5px">
                <!-- 统计按钮 -->
                <button type="button" class="btn btn-sm btn-primary no-margins" style="float:left;" onclick="dataAnalysis_wainingInfo_hb_alarmDataCount.searchb_alarmCount();">
                    <i class="fa fa-pie-chart"></i>&nbsp;统计
                </button>
                <button type="button" class="btn btn-sm btn-primary no-margins" style="float:left;" onclick="dataAnalysis_wainingInfo_hb_alarmDataCount.reset()">
                    <i class="fa fa-refresh"></i>&nbsp;重置
                </button>
            </div>
        </div>

    </div>
</div>
<div class="information_right">
    <!-- -------------------------------------------------------------------- ----------------------------------------------------------------------------->
    <div class="hb_alarm_count-content">
        <div class="hb_alarm_export-pdf">

            <div class="hb_alarm_pdf-up">
                <div class="hb_alarm_pdf-up-header">
                    <div class="hb_alarm_pdf-up-header-title"><!-- 设备运行状态统计 --> </div><!-- <span class="fa fa-map-marker" style="font-size: 16px"></span> -->
                </div>

            </div>
            <div class="hb_alarm_pdf-down">
                <div class="hb_alarm_totala"></div>

                <div class="hb_alarm_pdf-down-left">
                    <div class="hb_alarm_pdf-down-title">报警信息分布</div>
                    <div id="hb_alarm_count_pie" style="width: 40vw; height: 40vh;"></div>
                </div>
                <div id="hb_alarmInfo"  class="hb_alarm_pdf-down-right">
                </div>
            </div>
        </div>
    </div>

</div>
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
    ;
    var dataAnalysis_wainingInfo_hb_alarmDataCount = (function($, window,document, undefined) {

        var yqbh ="";//园区编号
        var _ctx = '${ctx}';
        var park_id=[];//园区下拉表id
        var park_val=[];//园区下拉表值
        var typeid = "";//故障类型id
        var typeName = "";//故障类型名称
        var compareType ="0";//对比方式

        var alarm_start_time ="";//开始时间
        var alarm_end_time ="";//结束时间
        var dealState = "";//报警状态
        var range = "0"; //日期范围


        //所属园区selected
        function park_group_select(id,idArray,valArray){
            //选择电表下拉列表 空间选择
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: parkChange,
            });
        }

        //状态selected
        function deal_state_group_select(id,idArray,valArray){
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: stateChange,
            });
        }

        //状态改变事件
        function stateChange(sp){
            dealState=sp.id;
        }

        //对比方式selected
        function alarm_compare_group_select(id,idArray,valArray){
            //选择电表下拉列表 空间选择
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: alarmChange,
            });
        }

        //园区改变事件--对比形式
        function alarmChange(sp){
            compareType=sp.id;
            dataAnalysis_wainingInfo_hb_alarmDataCount.compareChange();
        }

        //园区改变事件
        function parkChange(sp){
            yqbh=sp.id;//每次改变赋值给tableType
        }
        //获取园区下拉列表数据
        function  get_yqtree_sub() {

            $.issp_ajax({
                type: "get",
                url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
                async:false,
                success: function (data) {
                    if(data.hasOwnProperty("list"))
                    {
                        if(data.list.length > 0 )
                        {
                            for (var i = 0; i < data.list.length; i++) {
                                var obj = data.list[i];
                                park_id.push(obj.f_yqbh);
                                park_val.push(obj.f_yqmc);
                            }

                            yqbh=data.list[0].f_yqbh;
                            park_group_select('#hb_alarmData_sydw',park_id,park_val);
                        }
                    }
                    else
                    {
                        park_id.push("");
                        park_val.push("");
                        park_group_select('#hb_alarmData_sydw',park_id,park_val);

                    }


                },
                error: function (data) {
                    swal( data.msg,"", "error");
                }
            });
        }





        //时间js----判断时间条件
        function timeFormat(){
            var startTime = $('#hb_alarmCount_start_time').val();
            var endTime = $('#hb_alarmCount_end_time').val();
            var date1 = new Date(startTime.replace(/-/g,"/"));
            var date2 = new Date(endTime.replace(/-/g,"/"));
            if(date2.getTime()<date1.getTime()){
                swal( "开始时间不能大于结束时间！","", "warning");
                return false;
            }
            return true;
        }

        //实例化日期范围selected
        function alarm_date_group_select(id,idArray,valArray){
            //选择电表下拉列表 空间选择
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                callBack: timeChange,
                liveSearch:false

            });
        }

        //实现日期范围和时间颗粒相关联
        function timeChange(sp){
            $("#hb_alarmCount_start_time,#hb_alarmCount_end_time").removeAttr("cursor").css("cursor","not-allowed");
            range = sp.id;
            $('#hb_alarmCount_start_time').attr('disabled', 'disabled');
            $('#hb_alarmCount_end_time').attr('disabled', 'disabled');
            switch (range) {
                case '0':
                    var today = getCurrentDate();
                    $('#hb_alarmCount_start_time').val(getFormatDate(today));
                    $('#hb_alarmCount_end_time').val(getFormatDate(today));
                    break;
                case '1':
                    var thisWeek=getCurrentWeek();
                    $('#hb_alarmCount_start_time').val(getFormatDate(thisWeek[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(thisWeek[1]));
                    break;

                case '2':
                    var thismouth=getCurrentMonth();
                    $('#hb_alarmCount_start_time').val(getFormatDate(thismouth[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(thismouth[1]));
                    break;

                case '3':
                    var thisSeason=getCurrentSeason();
                    $('#hb_alarmCount_start_time').val(getFormatDate(thisSeason[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(thisSeason[1]));
                    break;

                case '4':
                    var thisYear=getCurrentYear();
                    $('#hb_alarmCount_start_time').val(getFormatDate(thisYear[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(thisYear[1]));
                    break;

                case '5':
                    var lastDay = getPreviousDate();
                    $('#hb_alarmCount_start_time').val(getFormatDate(lastDay));
                    $('#hb_alarmCount_end_time').val(getFormatDate(lastDay));
                    break;

                case '6':
                    var lastWeek = getPreviousWeek();
                    $('#hb_alarmCount_start_time').val(getFormatDate(lastWeek[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(lastWeek[1]));
                    break;

                case '7':
                    var lastMouth = getPreviousMonth();
                    $('#hb_alarmCount_start_time').val(getFormatDate(lastMouth[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(lastMouth[1]));
                    break;

                case '8':
                    var lastSeason = getPreviousSeason();
                    $('#hb_alarmCount_start_time').val(getFormatDate(lastSeason[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(lastSeason[1]));
                    break;

                case '9':
                    var lastYear = getPreviousYear();
                    $('#hb_alarmCount_start_time').val(getFormatDate(lastYear[0]));
                    $('#hb_alarmCount_end_time').val(getFormatDate(lastYear[1]));
                    break;
                case '10':
                    $("#hb_alarmCount_start_time,#hb_alarmCount_end_time").css("cursor","default");
                    $('#hb_alarmCount_start_time').attr('disabled', false);
                    $('#hb_alarmCount_end_time').attr('disabled', false);
                    break;
            }
            dataAnalysis_wainingInfo_hb_alarmDataCount.compareChange();

        }

        //加载饼状图
        function loadPie(array){
            var dom = document.getElementById("hb_alarm_count_pie");
            var myChart = echarts.init(dom);
            option = null;
            var num = 0;
            if(array!=null && array.length!=0 )
            {
                for(var i =0;i<array.length;i++)
                {
                    num = num + parseInt(array[i]["value"]);
                }
            }

            if(num ==0)
            {
                array=[];
            }


            option = {
                title : {text: '',subtext: '', x:'center'},
                tooltip : {trigger: 'item',formatter: ""},
                legend: {orient: 'vertical',left: 'right', data: []},
                title: {text:'总报警数'+num,left:'center',top:'45%',
                    textStyle:{color:'#8fe3f7',align:'center'}
                },
                color:['#749F83', '#CA8622','#D48265','#61A0A8'],
                series : [
                    {
                        name: '',
                        type: 'pie',
                        radius : '85%',
                        radius: ['50%', '85%'],
                        label: {
                            normal: {
                                formatter: '{a|{a}}  {b|{b}：}  {per|{d}%}  ',
                                backgroundColor: '',
                                borderColor: '',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    a: {
                                        fontSize: 11
                                    },
                                    hr: {
                                        fontSize: 11
                                    },
                                    b: {
                                        fontSize: 11,
                                        lineHeight: 25
                                    },
                                    per: {
                                        color: '',
                                        backgroundColor: '',
                                        padding: [2, 4],
                                        borderRadius: 2
                                    }
                                }
                            }
                        },
                        data:array,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(1, 2, 0, 1.5)'
                            }
                        }
                    }

                ]
            };

            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }

        }

        function  showBarData(legendMap,legend_data,data)
        {
            var dom = document.getElementById("hb_alarmInfo");
            var myChart = echarts.init(dom, "light");
            myChart.clear();
            result_data = {
                data: data.list
            }
            $('#hb_alarmInfo').setBarData({
                'title': "趋势统计分析",
                'title_fontSize':15,
                'xData': 'alrmTime',
                'legendMap': legendMap,
                'legend_data' :legend_data,
                'barGap' : '10%',
                'title_x' : 'left',
                'trigger' : 'axis',
                'xaxisLine_color' : '#8fe3f7',//x轴线条颜色
                'yaxisLine_color' : '#8fe3f7',//y轴线条颜色
                'boundaryGap' : false,
                'bar_color' :['#EE9201','#29AAE3','#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3'],
                'showSeriesLable' : false, // 柱形图顶部数据
                'dataZoom_disabled' : false,//鼠标滚轮

            },result_data);
        }

        function searchbAlarmBarCount(){


            $.issp_ajax({
                url :"${ctx}/view/dataAnalysises/wainingInfo/getHbWarningBarData",
                type: "post",
                dataType: "json",
                data : {
                    fYqbh:yqbh,
                    startTime:$("#hb_alarmCount_start_time").val(),
                    endTime:$("#hb_alarmCount_end_time").val(),
                    sameStartTime:$("#alarm_sametime_start_time").val(),
                    sameEndTime:$("#alarm_sametime_end_time").val(),
                    fOpearation:dealState,
                    comType:compareType,
                },

                success : function(result)
                {
                    var m = new Map();
                    m.clear();
                    for(var i =0;i<result.map.legendCount.length;i++)
                    {
                        m.put(result.map.legendData[i],result.map.legendCount[i]);
                    }

                    showBarData(m,result.map.legendData,result);

                },

                error : function(result) {
                    swal(result.msg, "", "error");
                }
            });
        }




        return{
            //对比形式变化
            compareChange:function(){
                alarm_start_time = $('#hb_alarmCount_start_time').val();//本期开始时间
                alarm_end_time = $('#hb_alarmCount_end_time').val();//本期结束时间

                var start = new Date(alarm_start_time);
                var end = new Date(alarm_end_time);

                if(alarm_start_time !="" && alarm_end_time !="")
                {
                    // 同比分析
                    if (compareType == 0) {
                        start.setFullYear(start.getFullYear() - 1);
                        $('#alarm_sametime_start_time').val(getFormatDate(start));
                        end.setFullYear(end.getFullYear() - 1);
                        $('#alarm_sametime_end_time').val(getFormatDate(end));

                    }
                    // 环比分析
                    if (compareType == 1) {
                        // var t = 2 * start.getTime() - end.getTime();

                        if (start.getTime() == end.getTime()) {
                            //本天、上天
                            start.setTime(start.getTime() - 24 * 60 * 60
                                    * 1000);
                            end.setTime(start.getTime());
                        }else if (range == "3") {
                            var lastSeason = getPreviousSeason();
                            start = lastSeason[0];
                            end = lastSeason[1];
                        } else if (range == "4") {
                            start.setFullYear(start.getFullYear() - 1);
                            end.setFullYear(end.getFullYear() - 1);
                        } else if (range == "7") {
                            var lastTwoMonth = getPreviousTwoMonth();
                            start = lastTwoMonth[0];
                            end = lastTwoMonth[1];
                        } else if (range == "8") {
                            var lastTwoSeason = getPreviousTwoSeason();
                            start = lastTwoSeason[0];
                            end = lastTwoSeason[1];
                        } else if (range == "9") {
                            lastTwoSeason = getTwoPreviousYear();
                            start = lastTwoSeason[0];
                            end = lastTwoSeason[1];
                        }  else if(range === "2"){
                            //本月
                            end.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                            start.setMonth(start.getMonth() - 1);
                            // end.setTime(start.getTime());
                            // start.setTime(t);
                        }else if(range === "1" || range === "6"){
                            //本周、上周
                            end.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                            start.setTime(start.getTime() - 7 * 24 * 60 * 60 * 1000);
                        }
                        $('#alarm_sametime_start_time').val(getFormatDate(start));
                        $('#alarm_sametime_end_time').val(getFormatDate(end));
                    }
                }
            },
            //统计报警信息状态
            searchb_alarmCount:function(){

                if(!timeFormat())
                {
                    return;
                }
                searchbAlarmBarCount();
                $.issp_ajax({
                    url :"${ctx}/view/dataAnalysises/wainingInfo/getHbWarningDataCount",
                    type: "post",
                    dataType: "json",
                    data : {
                        fYqbh:yqbh,
                        startTime:$("#hb_alarmCount_start_time").val(),
                        endTime:$("#hb_alarmCount_end_time").val(),
                        sameStartTime:$("#alarm_sametime_start_time").val(),
                        sameEndTime:$("#alarm_sametime_end_time").val(),
                        fOpearation:dealState,
                    },

                    success : function(data)
                    {

                        loadPie(data.list);

                    },

                    error : function(data) {
                        swal(data.msg, "", "error");
                    }
                });

            },

            pageInit:function()
            {
                $("#alarm_sametime_start_time,#alarm_sametime_end_time").removeAttr("cursor").css("cursor","not-allowed");
                var alarm_compare_group_id=['0','1'];
                var alarm_compare_group_val=['同比','环比'];
                alarm_compare_group_select('#alarm_compare_group',alarm_compare_group_id,alarm_compare_group_val);
                var deal_state_group_id=[' ','1','0'];
                var deal_state_group_val=['全部','未处理','已处理'];
                deal_state_group_select('#alarmData_hb_state',deal_state_group_id,deal_state_group_val);

                var alarm_group_id=['2','0','1','3','4','5','6','7','8','9','10'];
                var alarm_group_val=['本月','本日','本周','本季','本年','上天','上周','上月','上季','上年','自定义'];
                alarm_date_group_select('#alarm_date_group',alarm_group_id,alarm_group_val);
                get_yqtree_sub();
                setTimeout(dataAnalysis_wainingInfo_hb_alarmDataCount.searchb_alarmCount,"100");
                searchbAlarmBarCount();
            },
            reset:function()
            {
                $("#hb_alarmCount_start_time").val("");
                $("#hb_alarmCount_end_time").val("");
                $('#alarm_sametime_start_time').val("");
                $('#alarm_sametime_end_time').val("");
                //重新组织数据，实现重置效果
                var park_id_list=[""];
                var park_val_list=[""];

                for(var i =0; i<park_id.length;i++){
                    park_id_list.push(park_id[i]);
                    park_val_list.push(park_val[i]);

                }

                var alarm_compare_group_id=['','0','1'];
                var alarm_compare_group_val=['','同比','环比'];
                alarm_compare_group_select('#alarm_compare_group',alarm_compare_group_id,alarm_compare_group_val);
                park_group_select('#hb_alarmData_sydw',park_id_list,park_val_list);

                var deal_state_group_id=['',' ','1','0'];
                var deal_state_group_val=['','全部','未处理','已处理'];
                deal_state_group_select('#alarmData_hb_state',deal_state_group_id,deal_state_group_val);

            }
        }


    })(jQuery, window, document);
    dataAnalysis_wainingInfo_hb_alarmDataCount.pageInit();
</script>
