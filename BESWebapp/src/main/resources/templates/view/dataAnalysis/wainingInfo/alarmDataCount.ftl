<#-------------------
描述：   报警统计
作者：	liuzhen
时间：	2018/11/30
-------------------->


<style>
.alarm_count-content {
	width: 100%;
	clear: both;
	position: relative;
	overflow: hidden;
}
.zl_sxtj_span{width:10vh;}
.alarm_export-pdf {
	width: 95%;
	height: 100%;
	padding: 0 15px;
	margin: 0 auto;
	overflow: hidden;
}

.alarm_pdf-down-left {
	float: left;
	margin-top: 10vh;
    width: 40%;

}

.alarm_pdf-down-title {
	padding-left: 20px;
	font-size: 16px;
    margin-top: 12vh
}

.alarm_pdf-down-right {
	float: right;
	width: 45%;
 	height: 70vh;
 	margin-top: 6vh;
}

.alarm_totala{
	height: 100px;
    width: 100px;
    text-align: center;
    position: absolute;
    top: 28%;
    left: 34%;
    margin-left: -48px;
}

.search_alarm_count_div_style {
    display: flex;
    align-items: center;
    margin-right: 3vh;
    float: right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 5px 0px 1px 0px;
}

.search_alarm_count_style {
    width: 100%;
}

</style>

<div   class="leftarea information_left">
    <div  class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;筛选条件>>>
		</span>
    </div>
    <div class="search_alarm_count_style">
        <div class="search_alarm_count_div_style">
            <span class="zl_sxtj_span">园区名称:</span>
            <div id='alarmData_sydw'></div>
        </div>

        <div class="search_alarm_count_div_style">
            <span class="zl_sxtj_span">报警状态:</span>
            <div id='alarmData_state'></div>
        </div>

        <div class="search_alarm_count_div_style">
            <span class="zl_sxtj_span">起始时间:</span>
            <input id="alarmCount_start_time" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true})" />
        </div>


        <div class="search_alarm_count_div_style" >
            <span class="zl_sxtj_span">终止时间:</span>
            <input id="alarmCount_end_time" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true})" />
        </div>
    </div>
        <div class="search_alarm_count_div_style">
            <div style="float:right;margin-top: 5px">
                <!-- 统计按钮 -->
                <button type="button" class="btn btn-sm btn-primary no-margins" style="float:left;" onclick="dataAnalysis_wainingInfo_alarmDataCount.searcAlarmCount();">
                    <i class="fa fa-pie-chart"></i>&nbsp;统计
                </button>
                <button type="button" class="btn btn-sm btn-primary no-margins" style="float:left;" onclick="dataAnalysis_wainingInfo_alarmDataCount.reset()">
                    <i class="fa fa-refresh"></i>&nbsp;重置
                </button>
            </div>
        </div>

    </div>
</div>
<div class="information_right">
    <!-- -------------------------------------------------------------------- ----------------------------------------------------------------------------->
    <div class="alarm_count-content">
        <div class="alarm_export-pdf">

            <div class="alarm_pdf-up">
                <div class="alarm_pdf-up-header">
                    <div class="alarm_pdf-up-header-title"><!-- 设备运行状态统计 --> </div><!-- <span class="fa fa-map-marker" style="font-size: 16px"></span> -->
                </div>

            </div>
            <div class="alarm_pdf-down">
                <div class="alarm_totala"></div>

                <div class="alarm_pdf-down-left">
                    <div class="alarm_pdf-down-title">报警信息分布</div>
                    <div id="alarm_count_pie" style="width: 40vw; height: 40vh;"></div>
                </div>
                <div id="alarmInfo"  class="alarm_pdf-down-right">
                </div>
            </div>
        </div>
    </div>

</div>
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
;
var dataAnalysis_wainingInfo_alarmDataCount = (function($, window,document, undefined) {

    var yqbh ="";//园区编号
    var _ctx = '${ctx}';
    var park_id=[];//园区下拉表id
    var park_val=[];//园区下拉表值
    var typeid = "";//故障类型id
    var typeName = "";//故障类型名称
    var dealState = "";//报警状态

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

    //园区改变事件
    function parkChange(sp){
        yqbh=sp.id;//每次改变赋值给tableType
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

    //园区下拉列表
    function  get_yqtree_sub() {

        $.issp_ajax({
            type: "get",
            url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
            async:false,
            success: function (data) {
                if (data.hasOwnProperty("list"))
                {
                    if(data.list.length > 0 )
                    {
                        for (var i = 0; i < data.list.length; i++) {
                            var obj = data.list[i];
                            park_id.push(obj.f_yqbh);
                            park_val.push(obj.f_yqmc);
                        }

                        yqbh=data.list[0].f_yqbh;
                        park_group_select('#alarmData_sydw',park_id,park_val);
                    }
                }
                else
                {
                    park_id.push("");
                    park_val.push("");
                    park_group_select('#alarmData_sydw',park_id,park_val);
                }


            },
            error: function (data) {
                swal( data.msg,"", "error");
            }
        });
    }

    //时间js----判断时间条件
    function timeFormat(){
        var startTime = $('#alarmCount_start_time').val();
        var endTime = $('#alarmCount_end_time').val();
        var date1 = new Date(startTime.replace(/-/g,"/"));
        var date2 = new Date(endTime.replace(/-/g,"/"));
        if(date2.getTime()<date1.getTime()){
            swal( "开始时间不能大于结束时间！","", "warning");
            return false;
        }
        return true;
    }

    //加载饼状图
    function loadPie(array){
        var dom = document.getElementById("alarm_count_pie");
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
        myChart.on('click',function(a,b){
             typeid = a.data['typeId'];
             typeName =a.name;
            dataAnalysis_wainingInfo_alarmDataCount.searcAlarmLineCount(typeid,typeName);
        })
        dataAnalysis_wainingInfo_alarmDataCount.searcAlarmLineCount("",typeName);
    }

    //加载折线图
    function showLineChart(data,typeId,sbmc){
        data.data = data.list;
        var m = new Map();
        m.put("故障", "count");
        m.put("正常", "normolCount");

        $('#alarmInfo').setLineData({
            'title': sbmc+"趋势分析",//data.data[0].fDcmc+"趋势分析",
            'title_fontSize':16,
            'title_color':'#8fe3f7',
            'xData': 'F_ALERT_TIME',
            'legendMap': m,
            'minInterval':'1',
            // 'barGap' : 0,
            'title_x' : 'left',
            'trigger' : 'axis',
            'areaStyle' : null,//区域填充
            'showSeriesLable' : false,//折点上是否显示数据
            'boundaryGap' : false,
            'gridRight' : '8%',//右边距离
            'gridLeft' : '8%',//左边距离
            'xAxis_interval': 'auto',//x轴-间隔
            'line_color' : ['#fe5958','#ff80fd','#fdfe1f'] ,//折线颜色
            'dataZoom_disabled' : false,//鼠标滚轮
            'yaxisLine_color' : '#8fe3f7',//y轴线条颜色
            'xaxisLine_color' : '#8fe3f7',//x轴线条颜色
            'showMinLabel':'',
            'showMaxLabel':'',
        },data);
    }


    return{
        //统计报警信息状态
        searcAlarmCount:function(){

            if(!timeFormat())
            {
              return;
            }

            $.issp_ajax({
                url :"${ctx}/view/dataAnalysises/wainingInfo/getWarningDataCount",
                type: "post",
                dataType: "json",
                async:false,
                data : {
                   fYqbh:yqbh,
                    startTime:$("#alarmCount_start_time").val(),
                    endTime:$("#alarmCount_end_time").val(),
                    fOpearation:dealState,
                },

                success : function(result)
                {

                    loadPie(result.list);

                },

                error : function(result) {
                    swal(result.msg, "", "error");
                }
            });
        },
        //获取折线图所需数据
    searcAlarmLineCount:function(typeId,typeName){

        $.issp_ajax({
            url :"${ctx}/view/dataAnalysises/wainingInfo/getWarningLineData",
            type: "post",
            dataType: "json",
            async:false,
            data : {
                fYqbh:yqbh,
                startTime:$("#alarmCount_start_time").val(),
                endTime:$("#alarmCount_end_time").val(),
                fType:typeId,
                fOpearation:dealState,
            },

            success : function(result)
            {

                if(result.hasOwnProperty('list') && result.list.length>0){
                    showLineChart(result,typeId,typeName);//加载折线图
                }else{
                    result.list=[];
                    showLineChart(result,typeId,typeName);//加载折线图
                }
            },

            error : function(result) {
                swal(result.msg, "", "error");
            }
        });
    },
        pageInit:function()
		{
            //获取当前时间
            var date = getCurrentDate();
            //起始时间和默认时间
            $('#alarmCount_start_time').val(getFormatDate(date));
            $('#alarmCount_end_time').val(getFormatDate(date));
            var deal_state_group_id=['','1','0'];
            var deal_state_group_val=['全部','未处理','已处理'];
            deal_state_group_select('#alarmData_state',deal_state_group_id,deal_state_group_val);
            get_yqtree_sub();
            dataAnalysis_wainingInfo_alarmDataCount.searcAlarmCount();
		},
        reset:function()
        {
           $("#alarmCount_start_time").val("");
           $("#alarmCount_end_time").val("");
           //重新组织数据，实现重置效果
           var park_id_list=[""];
           var park_val_list=[""];

            for(var i =0; i<park_id.length;i++){
                park_id_list.push(park_id[i]);
                park_val_list.push(park_val[i]);

            }

            park_group_select('#alarmData_sydw',park_id_list,park_val_list);

            var deal_state_group_id=[' ',' ','1','0'];
            var deal_state_group_val=[' ','全部','未处理','已处理'];
            deal_state_group_select('#alarmData_state',deal_state_group_id,deal_state_group_val);
        }
	}

})(jQuery, window, document);
dataAnalysis_wainingInfo_alarmDataCount.pageInit();
</script>