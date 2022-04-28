<#--
          报警处理
    author:yangchao
    time:2018/12/3
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
            <div id='dispose_park_group'></div>
        </div>
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">开始时间:</span>
            <input id="wainingdispose_start_time" class="input-datecheck" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>


        <div class="search_waininginfo_div_style" >
            <span class="zl_sxtj_span">结束时间:</span>
            <input id="wainingdispose_end_time"  class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警类型:</span>
            <div id="warningdispose_type_group" ></div>
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警等级:</span>
            <div id="warningdispose_level_group" ></div>
        </div>
        
        <div style="float: right;margin-right: 3vh;margin-top:10px" >
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_warningdispose_warningDispose.wainingData_search()">
                <i class="fa fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_warningdispose_warningDispose.reset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </button>
        </div>
        <div style="float: right;margin-right: 3vh;margin-top:10px" >
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataAnalysis_warningDispose_page.DisposeSize();">
                <#--<i class="fa fa-filter"></i>-->&nbsp;批量处理
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_warningdispose_warningDispose.DisposeAll();">
               <#-- <i class="fa fa-filter"></i>-->&nbsp;全部处理
            </button>
        </div>
    </div>
</div>
<!-- 右侧内容模块start -->
<div class="information_right">
        <!---分页列表----->
        <div class="ibox" id="besWainingInfoDispose" style="height:90%">
        </div>
</div>
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
    ;
    var dataannlysis_warningdispose_warningDispose = (function($, window, document, undefined) {

        var fQybh = "";//园区编号
        var type = ""; //报警类型
        var level = ""; //报警等级
        var dealState = "1"; //报警状态

        /**
         * 初始化筛选条件
         */
         function get_CurrentDate(){
        	//获取当前时间
             var date = getCurrentDate();
             //起始时间和默认时间
             $('#wainingdispose_start_time').val(getFormatDate(date));
             $('#wainingdispose_end_time').val(getFormatDate(date));
         }

        //园区下拉列表
         function  get_yqtree_sub() {
            var park_id=[];
            var park_val=[];
            $.issp_ajax({
                type: "get",
                url: '${ctx}/view/basedatamanage/energyinformation/park_tree',
                success: function (data) {
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
                         park_group_select('#dispose_park_group',park_id,park_val);
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
            var type_id=[];
            var type_val=[];
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
                            type_group_select('#warningdispose_type_group',type_id,type_val);
                        }
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
             var level_id=['qb'];
            var level_val=['全部'];
            //每次先将数据清空
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
                            if(result.list.length >0)
                            {
                                for (var i = 0; i <result.list.length; i++) {
                                    var obj = result.list[i];
                                    level_id.push(obj.alarmLevelNum);
                                    level_val.push(obj.alarmLevelName);
                                }
                                level=result.list[0].alarmLevelNum;
                                level_group_select('#warningdispose_level_group',level_id,level_val);
                            }

                        }
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
                var startTime=$("#wainingdispose_start_time").val();
                var endTime=$("#wainingdispose_end_time").val();
                var date1 = new Date(startTime.replace(/-/g,"/"));
                var date2 = new Date(endTime.replace(/-/g,"/"));
                if(date2.getTime()<date1.getTime()){
                    swal( "开始时间不能大于结束时间！","", "warning");
                    return false;
                }
                $.issp_ajax({
                    url:"${ctx }/view/dataAnalysises/wainingInfo/getWarningDisposeInfoData",
                    type:"post",
                    data:{
                         fYqbh : fQybh,
                         startTime : startTime,
                         endTime : endTime,
                         fType : type,
                         fLevel : level,
                         fOpearation :dealState,
                    },

                    success: function(data) {
                        $('#besWainingInfoDispose').html(data);
                    },
                    error:function(data) {
                        swal( data.msg,"", "error");
                    }
                });
            },
            DisposeAll : function(){
                swal({
                        title : "您确定要全部处理吗?",
                        text : "这些信息将转交报警查询！",
                        type : "warning",
                        showCancelButton : true,
                        confirmButtonColor : "#1ab394",
                        confirmButtonText : "确定",
                        closeOnConfirm : false
                    },
                    function() {
                        var startTime   =   $("#wainingdispose_start_time").val();
                        var endTime     =   $("#wainingdispose_end_time").val();
                        $.ajax({
                            url: _ctx + "/view/dataAnalysises/wainingInfo/WarningDsiposeAll",
                            type: "post",
                            contentType:'application/json;charset=UTF-8',// 核心
                            dataType:"json",
                            data:JSON.stringify({
                                fYqbh : fQybh,//园区编号
                                startTime : startTime,//开始时间
                                endTime : endTime,//结束时间
                                fType : type,//类型
                                fLevel : level,//等级
                            }),
                            success: function(result) {

                                if(result.status=='1'){//成功!
                                    swal({
                                        title: result.msg,
                                        type: "success",
                                        showCloseButton: false,
                                        allowOutsideClick: false,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                    dataannlysis_warningdispose_warningDispose.wainingData_search();//重新刷新报警处理表格
                                    top_alarm_warn_modal.refreshWarningIcon();//刷新顶部铃铛的数字
                                }else{
                                    swal({
                                        title: result.msg,
                                        type: "error",
                                        showCloseButton: false,
                                        allowOutsideClick: false,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                            }
                        });
                    });

            },
            pageInit:function()
            {
            	get_CurrentDate();
                get_yqtree_sub();
                getAlarmType();
                getAlarmLevel();
                setTimeout(dataannlysis_warningdispose_warningDispose.wainingData_search,"100");
            },
            reset:function()
            {
                $("#wainingdispose_start_time").val("");
               $("#wainingdispose_end_time").val("");
                get_yqtree_sub();
                getAlarmType();
                getAlarmLevel();
                //$('#besWainingInfoDispose').html("");
            }
        }

    })(jQuery, window, document);
    dataannlysis_warningdispose_warningDispose.pageInit();
</script>