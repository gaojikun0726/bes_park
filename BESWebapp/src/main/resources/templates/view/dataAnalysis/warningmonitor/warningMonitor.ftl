<#--
          报警监控页面
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
    .information-model-warningMonitor{
        /*background: rgba(22, 142, 169, 0.39);*/
        border-bottom: solid #001b3a;
        height: 3.5vh;
        display: flex;
        align-items: center;
        /*border-top:solid #acbac3 1px;*/
        /*background: #eeeeee;*/
        /*border-bottom: solid #acbac3 1px;*/
        margin-bottom: 1px;
        /*border-left: solid #acbac3 1px;*/
    }
    .tree_Subtitle_warningMonitor{
        margin-left: 70px;
    }
</style>
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">

<div class="leftarea information_left">
    <div class="information-model-warningMonitor">
            <span class="tree_Subtitle_warningMonitor"> &nbsp;告警警报>>>
            </span>
        <a href="javascript:void(-1);" onclick="dataannlysis_warningmonitor_warningMonitor.alarmAudioSwitchHandle(this)" class="btn btn-sm btn-primary no-margins toLeft">
            关闭
        </a>
    </div>
    <div class="information-model">
            <span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;筛选条件>>>
            </span>
    </div>
    <div class="search_qstjfx_style">

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">园区名称:</span>
            <div id='monitor_park_group'></div>
        </div>
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">开始时间:</span>
            <input id="wainingreal_start_time" class="input-datecheck" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>


        <div class="search_waininginfo_div_style" >
            <span class="zl_sxtj_span">结束时间:</span>
            <input id="wainingreal_end_time"  class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
        </div>
        
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警类型:</span>
            <div id="warningreal_type_group" ></div>
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报警等级:</span>
            <div id="warningreal_level_group" ></div>
        </div>
        
        <div style="float: right;margin-right: 3vh;margin-top:10px" >
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_warningmonitor_warningMonitor.wainingData_search()">
                <i class="fa fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_warningmonitor_warningMonitor.reset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </button>
        </div>
    </div>
</div>
<!-- 右侧内容模块start -->
<div class="information_right">
        <!---分页列表----->
        <div class="ibox" id="besWainingInfoReal" style="height:90%">
        </div>
</div>
<#--<div class="information_right" style="width:100%!important;height:100%!important">-->

<#--    <div class="information-model">-->
<#--			<span class="Subtitle">-->
<#--                <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;告警警报>>-->
<#--			</span>-->
<#--        <!-- 增加按钮 &ndash;&gt;-->
<#--        <a href="javascript:void(-1);" onclick="dataannlysis_warningmonitor_warningMonitor.alarmAudioSwitchHandle(this)" class="btn btn-primary toLeft">-->
<#--            关闭-->
<#--        </a>-->

<#-- &lt;#&ndash;       <!-- 搜索框 &ndash;&gt;-->
<#--        <div class="zc_search find">-->
<#--            <input type="text" class="find-style"  id="euserkeywords" name="euserkeywords" placeholder="登录账号、用户姓名、公司名称">-->
<#--            <button id="queryuserBtn"onclick="usermanage_user.search_euser()"><i class="fa fa-search" aria-hidden="true"></i></button>-->
<#--        </div>&ndash;&gt;-->
<#--    </div>-->

<#--        <!---分页列表---&ndash;&gt;-->
<#--        <div class="ibox" id="besWainingInfoReal" style="height:90%">-->
<#--        </div>-->
<#--</div>-->
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">

    var dataannlysis_warningmonitor_warningMonitor = (function($, window, document, undefined) {

        var fQybh = "";//园区编号
        var type = ""; //报警类型
        var level = ""; //报警等级
        var dealState = "1"; //报警状态
        var timeout;//定时器
        /**
         * 初始化筛选条件
         */
         function get_CurrentDate(){
        	//获取当前时间
             var date = getCurrentDate();
             //起始时间和默认时间
             $('#wainingreal_start_time').val(getFormatDate(date));
             $('#wainingreal_end_time').val(getFormatDate(date));
         }
         
        //园区下拉列表
         /*function  get_yqtree_sub() {
            var park_id=[];
            var park_val=[];
            $.issp_ajax({
                type: "get",
                async:false,
                url: '${ctx}/view/basedatamanage/energyinformation/park_tree',
                success: function (data) {
                     if(data.list.length > 0 )
                     {
                         for (var i = 0; i < data.list.length; i++) {
                             var obj = data.list[i];
                             park_id.push(obj.f_yqbh);
                             park_val.push(obj.f_yqmc);
                         }
                         fQybh=data.list[0].f_yqbh;
                         park_group_select('#monitor_park_group',park_id,park_val);
                     }
                },
                error: function (data) {
                    swal( data.msg,"", "error");
                }
            });
        }*/

        //报警信息类型下拉列表
        function getAlarmType()
        {
            var type_id=[];
            var type_val=[];
            $.issp_ajax({
                type: "post",
                async:false,
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
                            type_group_select('#warningreal_type_group',type_id,type_val);
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
                async:false,
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
                                level_group_select('#warningreal_level_group',level_id,level_val);
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
        $(function () {//定时器,单独走一个查询的方法
            timeout  = setInterval(function () {
                dataannlysis_warningmonitor_warningMonitor.wainingData_search_data();
            }, 300 * 1000);

        })


        return{


            get_yqtree_sub:function () {
                var park_id=[];
                var park_val=[];
                $.issp_ajax({
                    type: "get",
                    async:false,
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
                            dataannlysis_warningmonitor_warningMonitor.park_group_select('#monitor_park_group',park_id,park_val);
                        }
                    },
                    error: function (data) {
                        swal( data.msg,"", "error");
                    }
                });
        },

            //所属园区selected
            park_group_select:function(id,idArray,valArray){
            //选择园区下拉列表 空间选择
            $(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: dataannlysis_warningmonitor_warningMonitor.parkChange,
            });
        },

        //园区改变事件
       parkChange: function (sp){
            fQybh=sp.id;//每次改变赋值给tableType

        },

            /* 筛选 */
            wainingData_search:function () {
                // var pagenum = dataAnalysis_warningMonitor_page.getPageNum();
                var startTime = $("#wainingreal_start_time").val();
                var endTime = $("#wainingreal_end_time").val();
                var date1 = new Date(startTime.replace(/-/g,"/"));
                var date2 = new Date(endTime.replace(/-/g,"/"));
                if(date2.getTime()<date1.getTime()){
                    swal( "开始时间不能大于结束时间！","", "warning");
                    return false;
                }
                var aaa = fQybh;
                $.issp_ajax({
                    url:"${ctx }/view/dataAnalysises/wainingInfo/getWarningRealInfoData",
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
                        $('#besWainingInfoReal').html(data);
                    },
                    error:function(data) {
                        swal( data.msg,"", "error");
                    }
                });
                // var aa = setTimeout(dataannlysis_warningmonitor_warningMonitor.wainingData_search_data,"60*1000");
                // if (document.getElementById('besWainingInfoReal') === null){
                //     clearTimeout(aa);
                // }
            },
            wainingData_search_data:function () {//单独的查询的方法,给定时器使用
                //获取当前页数
                var pagenum = dataAnalysis_warningMonitor_page.getPageNum();
                var startTime = $("#wainingreal_start_time").val();
                var endTime = $("#wainingreal_end_time").val();
                $.issp_ajax({
                    url:"${ctx }/view/dataAnalysises/wainingInfo/getWarningRealInfoData",
                    type:"post",
                    data:{
                        fYqbh : fQybh,
                        startTime : startTime,
                        endTime : endTime,
                        fType : type,
                        fLevel : level,
                        fOpearation :dealState,
                        pageNum : pagenum,
                    },

                    success: function(data) {
                        $('#besWainingInfoReal').html(data);
                        if (document.getElementById('besWainingInfoReal') === null){
                            clearTimeout(timeout);
                        };
                    },
                    error:function(data) {
                        swal( data.msg,"", "error");
                    }
                });
            },
            pageInit:function()
            {
            	get_CurrentDate();
                dataannlysis_warningmonitor_warningMonitor.get_yqtree_sub();
                getAlarmType();
                getAlarmLevel();
                setTimeout(dataannlysis_warningmonitor_warningMonitor.wainingData_search,"100");
            },
            reset:function()
            {
                $("#wainingreal_start_time").val("");
                $("#wainingreal_end_time").val("");
                dataannlysis_warningmonitor_warningMonitor.get_yqtree_sub();
                getAlarmType();
                getAlarmLevel();
               // dataannlysis_warningmonitor_warningMonitor.wainingData_search();
                //$('#besWainingInfoReal').html("");
            },
            alarmAudioSwitchHandle: function (obj) {

                console.log(obj.text)

                if(switchState){
                    switchState = false;
                    $('#alarm_audio').html('');
                    obj.text = '开启';
                }else {
                    switchState = true;
                    obj.text = '关闭';
                }

              /*  $('#alarm_audio').html(
                        '      <audio id="alarm_audio" autoplay="autoplay" loop="loop">\n' +
                        '            <source src="static/audio/alarm.mp3" type="audio/mpeg">\n' +
                        '        </audio>')*/

            }
        }


    })(jQuery, window, document);
    dataannlysis_warningmonitor_warningMonitor.pageInit();


</script>