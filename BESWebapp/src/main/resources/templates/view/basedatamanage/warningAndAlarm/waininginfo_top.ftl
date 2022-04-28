<style>
    .search_waininginfo_div_style{
        display: flex;
        align-items: center;
        margin-right: 3vh;
        float:right;
        white-space: nowrap;
        text-overflow: ellipsis;
        padding: 0px 0px 1px 0px;
    }
    .search_waininginfo_div_style_waring{
        display: flex;
        align-items: center;
        margin-right: 3vh;
        float:left;
        white-space: nowrap;
        text-overflow: ellipsis;
        padding: 0px 0px 1px 0px;
    }
</style>
<!----故障信息模态框开始--->
<div class="modal fade" id="warnInfoModal" tabindex="-2" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:60vw;height: 88vh;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button" onclick="top_alarm_warn_modal.refreshWarningIcon()">×</button>
<#--                <h4>故障告警</h4>-->
                <div class="search_waininginfo_div_style_waring">故障告警</div>
                <div class="search_waininginfo_div_style">
                    <span class="zl_sxtj_span">报警类型:</span>
                    <div id="warningdispose_type_group_top" ></div>
                </div>
            </div>

            <div class="modal-body" style="height:94%;">
                <div class="ibox" id="battery_alarm_warn_ibox" style="height:87%">
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>
</div>
<!----故障信息模态框结束--->
<script type="text/javascript">
    ;
    var top_alarm_warn_modal = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var issp_top_clock = '';//告警图标刷新定时任务id
        var iconRefreshTime = 10000;//单位：毫秒(默认10秒)
        var warnModalUpFlg = false;//告警详情弹框是都更新
        var type = ""; //报警类型
        /* 设置刷新报警条数刷新定时任务 */
        function setRefreshWarningIconTimeTask(){
            $.ajax({
                url:_ctx + '/isspview/systemparameters/globalVarByKey',
                contentType : "application/json; charset=utf-8",
                type : "get",
                data:{
                    key:'battery_alarm_refresh_time',
                },
                success : function(data){
                    if(data.status=='1'){//从数据中获取刷新时间
                        var count = parseFloat(data.data);
                        iconRefreshTime = 1000 * 60 * count;
                    }
                    //定时刷新页面(默认10秒)
                    issp_top_clock = window.setInterval("top_alarm_warn_modal.refreshWarningIcon()",iconRefreshTime);
                },
                error:function(data){
                },
            });
        };
        function getWarnInfo(){//获取告警详细信息
            $.ajax({
                url : _ctx+'/view/dataAnalysises/wainingInfo/loadAlarmByRecoverState',
                type : "post",
                data : {
                    type : type,//报警类型
                    f_operation : '1',//1:未处理 0:已处理
                    // bars:$("#equipmentsAlarmWarnModelpageSize").val(),
                },
                success : function(data) {
                    $('#battery_alarm_warn_ibox').html(data);
                },
                error : function(XMLHttpRequest,textStatus, errorThrown) {
                    toastr.error('', '查询失败');
                }
            });
        };
        $(function() {
            top_alarm_warn_modal.refreshWarningIcon();//刷新报警条数信息
            // issp_top_clock = window.setInterval("top_alarm_warn_modal.refreshWarningIcon()",60000);
            $("#warningIcon").click(function(){//给告警图标添加点击事件
                //每次先将数据清空
                $("#alarmLevelTable").tabulator('setData',[]);

                $('#warnInfoModal').modal('show');//告警详细信息弹框展示
                getAlarmType();
                // getWarnInfo();//加载获取告警详细信息
            });
        });

        //设置告警详细信息弹框布局
        $('#warnInfoModal').on('show.bs.modal', function () {
            //模态框拖动********************
            $(this).draggable({
                handle:".modal-header"
            });
            $(this).css("overflow","hidden");
            //*************************************
            $(this).css('display', 'block');
            $(this).css('margin-left', '-20vw');
            $(this).find('.modal-dialog').css({
                'margin-top': '2%'
            });
        });
        //告警信息弹框打开，设置定时任务
        $('#warnInfoModal').on('shown.bs.modal', function () {
            warnModalUpFlg = true;
        });
        //告警信息弹框关闭
        $('#warnInfoModal').on('hidden.bs.modal', function () {
            warnModalUpFlg = false;
        });

        //报警信息类型下拉列表
        function getAlarmType()
        {
            var type_id=[];
            var type_val=[];
            $.ajax({
                type: "post",
                url:_ctx+'/view/basedatamanage/warningAndAlarm/getAlarmType',
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

                            type_group_select('#warningdispose_type_group_top',type_id,type_val);
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
        //报警类型改变事件
        function typeChange(sp){
            type=sp.id;////报警类型
            getWarnInfo();//改变后刷新表格
        }

        return{
            refreshWarningIcon : function(){//刷新报警条数信息
                // if($("#warningIcon").length==0){//页面关闭后
                //     clearInterval(issp_top_clock); //清除js定时器
                //     return;
                // }
                $.ajax({//查询当前报警表中有没有数据,如果有未处理的数据,则将页面铃铛变成红色闪烁
                    url : _ctx+'/view/dataAnalysises/wainingInfo/noRecoverCount',
                    type : "get",
                    data : {
                        f_operation : '1',//1:未处理 0:已处理
                        bars:$("#equipmentsAlarmWarnModelpageSize").val(),
                    },
                    success : function(data) {
                        if(data.status=='1'){
                            var warnCount = parseInt(data.data);
                            if(warnCount!= 0){
                                $("#warningIcon").attr("class","earlyWarning_red");
                                $("#warningInfoCount").text(warnCount);
                            }else{
                                $("#warningIcon").attr("class","earlyWarning_blue");
                                $("#warningInfoCount").text(warnCount);
                            }
                        }
                    },
                    error : function(data) {
                    }
                });
                if(warnModalUpFlg){//更新告警详情弹框内的数据
                    top_alarm_warn_modal.refreshWarnModalData();
                }
            },
            refreshWarnModalData : function(){//刷新告警弹框详细信息
                var pageN = equipmentalarm_upsalarm_battery_alarm_warn_modal.getPageNum();
                $.ajax({
                    url : _ctx+'/view/dataAnalysises/wainingInfo/loadAlarmByRecoverState',
                    type : "post",
                    data : {
                        //pageNum:pageN,
                        bars:$("#equipmentsAlarmWarnModelpageSize").val(),
                        f_operation : '1',// 1:未处理 0:已处理
                        type : type,//报警类型
                    },
                    success : function(data) {
                        $('#battery_alarm_warn_ibox').html(data);
                    },
                    error : function(XMLHttpRequest,textStatus, errorThrown) {
                        toastr.error('', '查询失败');
                    }
                });
            },
            pageInit : function () {
                getAlarmType();
            },
        }
    })(jQuery, window, document);
    // top_alarm_warn_modal.pageInit();
</script>