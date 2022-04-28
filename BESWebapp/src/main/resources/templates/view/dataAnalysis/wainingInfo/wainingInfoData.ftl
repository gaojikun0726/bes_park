<#--
    报警信息查询页面
    author:liuzhen
    time:2018/11/29
-->
<style>
    .search_waininginfo_div_style{
        display: flex;
        align-items: center;
        margin-right: 3vh;
        float:right;
        white-space: nowrap;
        text-overflow: ellipsis;
        padding: 7px 0px 1px 0px;
    }
</style>
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">

<div class="leftarea information_left" >
    <div class="information-model">
			<span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;筛选条件>>>
			</span>
    </div>
    <div class="search_qstjfx_style">

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">园区名称:</span>
            <div id='park_group'></div>
        </div>
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">开始时间:</span>
            <input id="waininginfo_start_time" class="input-datecheck" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>


        <div class="search_waininginfo_div_style" >
            <span class="zl_sxtj_span">结束时间:</span>
            <input id="waininginfo_end_time"  class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">处理状态:</span>
            <div id="waining_deal_state_group"  ></div>
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警类型:</span>
            <div id="warning_type_group" ></div>
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警等级:</span>
            <div id="warning_level_group" ></div>
        </div>

        <div style="float: right;margin-right: 3vh;margin-top:10px" >
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_waininginfo_wainingdata.wainingData_search()">
                <i class="fa fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_waininginfo_wainingdata.reset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </button>
        </div>
    </div>
</div>
<!-- 右侧内容模块start -->
<div class="information_right">
        <!---分页列表----->
        <div class="ibox" id="besWainingInfoRecords" style="height:90%">
        </div>
</div>
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
    ;
    var dataannlysis_waininginfo_wainingdata = (function($, window, document, undefined) {

        var fQybh = "";//园区编号
        var type = ""; //报警类型
        var level = ""; //报警等级
        var dealState = ""; //报警状态
        var park_id=[];//园区下拉列表ID
        var park_val=[];//园区下拉列表值
        var level_id=['qb'];//等级下拉列表id
        var level_val=['全部'];//等级下拉列表值
        var type_id=[];//类型下拉列表id
        var type_val=[];//类型下拉列表值

        /**
		 * 初始化筛选条件
         */
        function defaultValue()
		{
            var deal_state_group_id=[' ','1','0'];
            var deal_state_group_val=['全部','未处理','已处理'];
            deal_state_group_select('#waining_deal_state_group',deal_state_group_id,deal_state_group_val);

            //获取当前时间
            var date = getCurrentDate();
            //起始时间和默认时间
            $('#waininginfo_start_time').val(getFormatDate(date));
            $('#waininginfo_end_time').val(getFormatDate(date));
		}


         //园区下拉列表
         function  get_yqtree_sub() {

            $.issp_ajax({
                type: "get",
                url:'${ctx}/view/basedatamanage/energyinformation/park_tree',
                success: function (data) {
                    if(data.hasOwnProperty("list")){
                        if(data.list.length > 0 )
                        {
                            park_id.push('');
                            park_val.push("全部");
                            for (var i = 0; i < data.list.length; i++) {
                                var obj = data.list[i];
                                park_id.push(obj.f_yqbh);
                                park_val.push(obj.f_yqmc);
                            }
                            fQybh=data.list[0].f_yqbh;
                            park_group_select('#park_group',park_id,park_val);
                        }
                    }
                    else{
                        park_id.push("");
                        park_val.push("");
                        park_group_select('#park_group',park_id,park_val);
                    }

                },
                error: function (data) {
                    swal( data.msg,"", "error");
                }
            });
        }

        //报警信息类型下拉列表
        function getAlarmType()
        {

            $.issp_ajax({
                type: "post",
                url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmType",
                success: function (result)
                {
                    if(result.hasOwnProperty("list"))
                    {
                        if(result.list.length >0)
                        {
                            for (var i = 0; i <result.list.length; i++) {
                                var obj = result.list[i];
                                if (typeof(obj.alarmNum)=="undefined" ){
                                    type_id.push(0);
                                    type_val.push(obj.alarmTypeName);
                                }else {
                                    type_id.push(obj.alarmNum);
                                    type_val.push(obj.alarmTypeName);
                                }

                            }
                            if (typeof(result.list[0].alarmNum) == 'undefined' ){
                                type = 0;
                            }else {
                                type = result.list[0].alarmNum;
                            }
                            type_group_select('#warning_type_group',type_id,type_val);
                        }
                    }
                    else {
                        type_id.push("");
                        type_val.push("");
                        type_group_select('#warning_type_group',type_id,type_val);
                    }
                },
                error: function(result) {
                    swal( result.msg,"", "error");
                }
            });
        }

        //获取报警等级数据下拉列表
        function getAlarmLevel()
        {

            $.issp_ajax({
                type: "post",
                url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmLevel",
                dataLevel: "json",
                traditional: true,
                success: function (result)
                {
                    if(result.hasOwnProperty("list"))
                    {
                        if(result.list.length>0)
                        {
                                for (var i = 0; i <result.list.length; i++) {
                                    var obj = result.list[i];
                                    level_id.push(obj.alarmLevelNum);
                                    level_val.push(obj.alarmLevelName);
                                }
                                level=result.list[0].alarmLevelNum;
                                level_group_select('#warning_level_group',level_id,level_val);
                        }
                    }
                    else {
                        level_id.push("");
                        level_val.push("");
                        level_group_select('#warning_level_group',level_id,level_val);
                    }

                },
                error: function(result) {
                    swal( result.msg,"", "error");
                }
            });
        }

        //所属类型selected
        function type_group_select(id,idArray,valArray){
            //所属类型下拉列表
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: typeChange,
            });
        }
        //园区改变事件
        function typeChange(sp){
            type=sp.id;//每次改变赋值给tableType
        }

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
            fQybh=sp.id;//每次改变赋值给tableType
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
            dealState=sp.id;//每次改变赋值给tableType
        }

        //等级selected
        function level_group_select(id,idArray,valArray){
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: levelChange,
            });
        }

        //等级改变事件
        function levelChange(sp){
           level=sp.id;//每次改变赋值给tableType
        }

        return{

            /* 筛选 */
            wainingData_search:function () {
                var startTime=$("#waininginfo_start_time").val();
                var endTime=$("#waininginfo_end_time").val();
                var date1 = new Date(startTime.replace(/-/g,"/"));
                var date2 = new Date(endTime.replace(/-/g,"/"));
                if(date2.getTime()<date1.getTime()){
                    swal( "开始时间不能大于结束时间！","", "warning");
                    return false;
                }
                $.issp_ajax({
                    url:"${ctx }/view/dataAnalysises/wainingInfo/getWarningInfoData",
                    <#--url:"${ctx }/alarm/getHistoryAlarmData",-->
                    type:"post",
                    data:{
                         fYqbh : fQybh,
                         startTime : startTime,
                         endTime : endTime,
                         fType : type,
                         fLevel : level,
                         fOpearation :dealState.trim(),
                    },

                    success: function(data) {
                        $('#besWainingInfoRecords').html(data);
                    },
                    error:function(data) {
                        swal( data.msg,"", "error");
                    }
                });
            },
            pageInit:function()
            {
                defaultValue();
                get_yqtree_sub();
                getAlarmType();
                getAlarmLevel();
                setTimeout(dataannlysis_waininginfo_wainingdata.wainingData_search,"100");
            },
            reset:function()
            {
                $("#waininginfo_start_time").val("");
               $("#waininginfo_end_time").val("");
                //$('#besWainingInfoRecords').html("");
                var deal_state_group_id=['',' ','1','0'];
                var deal_state_group_val=['','全部','未处理','已处理'];
                deal_state_group_select('#waining_deal_state_group',deal_state_group_id,deal_state_group_val);
                var park_id_list = [""];
                var park_val_list = [""];
                for(var i =0;i<park_id.length;i++)
                {
                    park_id_list.push(park_id[i]);
                    park_val_list.push(park_val[i]);
                }
                park_group_select('#park_group',park_id_list,park_val_list);

                var level_id_list = [""];
                var level_val_list = [""];
                for(var i =0;i<level_id.length;i++)
                {
                    level_id_list.push(level_id[i]);
                    level_val_list.push(level_val[i]);
                }
                level_group_select('#warning_level_group',level_id_list,level_val_list);

                var type_id_list = [""];
                var type_val_list = [""];
                for(var i =0;i<type_id.length;i++)
                {
                    type_id_list.push(type_id[i]);
                    type_val_list.push(type_val[i]);
                }
                type_group_select('#warning_type_group',type_id_list,type_val_list);

            }
		}

    })(jQuery, window, document);
    dataannlysis_waininginfo_wainingdata.pageInit();



</script>
