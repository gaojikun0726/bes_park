
<!-- <div style="height:calc(100% -60px)"> -->
<div style="height:100%">
    <div id="battery_alarm_warnTable"></div> <!-- battery_alarm_warnTable -->
</div>

<!---告警模态框分页表单----->
<div style="height:15%" id="warnIconModalForm">
    <form action="${ctx }/view/dataAnalysises/wainingInfo/loadAlarmByRecoverState" id="alarmWarnIconForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" id="warnModalPageNum" value="${pageNum! }" id="pageNum" name="pageNum" />
        <input type="hidden" value="${pageSize! }" id="equipmentsAlarmWarnModelpageSize" name="equipmentsAlarmWarnModelpageSize" />
        <input type="hidden" value="${f_operation! }" id="f_operation" name="f_operation" />
        <input type="hidden" value="${type!}" id="type" name="type">
        <#assign formId = "alarmWarnIconForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "battery_alarm_warn_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/><!-- 分页控键 -->
    </form>
</div>
<script type="text/javascript">
    ;
    var equipmentalarm_upsalarm_battery_alarm_warn_modal = (function($, window, document, undefined) {
        var _ctx = "${ctx}";
        var _curRow = null;
        var optIconFunction = function(cell, formatterParams) { //plain text value
            var fguid = cell.getRow().getData().fguid;
            return "<div class='btn-group '>"
                + "<button class='btn btn-white btn-sm edit' data-id='"+ fguid + " '  onClick='equipmentalarm_upsalarm_battery_alarm_warn_modal.Dispose(&#39;"+fguid+"&#39;);' ><i class='fa fa-pencil'></i>处理</button>"
        };
        var color = function (cell) {
            var value = cell.getValue();
            if (value === "紧急"){
                return "<span style='color:red; font-weight:bold;'>" + value + "</span>";
            }else if (value === "重要"){
                return "<span style='color:yellow; font-weight:bold;'>" + value + "</span>";
            }else if(value === "一般"){
                return "<span style='color:blue; font-weight:bold;'>" + value + "</span>";
            }else {
                return value;
            }
        };
        var factualValue = function (cell) {
            var value = cell.getValue();
            if (value == undefined){
                return "<span style='font-weight:bold;'>" + '/' + "</span>";
            }else {
                return value;
            }
        };
        var fplanValue = function (cell) {
            var value = cell.getValue();
            if (value == undefined){
                return "<span style='font-weight:bold;'>" + '/' + "</span>";
            }else {
                return value;
            }
        };
        var XLIDZH = function(cell, formatterParams){
            var xlid = cell.getRow().getData().f_xlid;
            var zh=cell.getRow().getData().f_zh;
            return  xlid+"("+zh+")";
        };
        var pageNum = $("#warnModalPageNum").val();//刷新页面使用(其他页面获取值)
        try {
            $("#battery_alarm_warnTable").tabulator({
                height : "100%",
                layout : "widthGrow",//fitColumns
                columnVertAlign : "bottom",
                tooltips : true,
                selectable : 1,
                movableColumns : false,
                columns : [ {title : "序号",field : "id",formatter : "rownum",frozen : false,align : "center",headerSort : false,tooltipsHeader : false,tooltip : false,},
                    { title : "报警名称 ",field : "falarmName",sorter : "string",width : 150,align : "center",editor : false,headerSort : false,},
                    {
                        title: "所属园区",
                        field: "fyqbh",
                        sorter: "string",
                        editor: false,
                        align: "center",
                        headerSort: false
                    },
                    {title : "实际值 ",field : "factualValue",sorter : "string",width : 70,align : "center",editor : false,headerSort : false,formatter : factualValue},
                    {title : "计划值 ",field : "fplanValue",sorter : "string",width : 100,align : "center",editor : false,headerSort : false,formatter : fplanValue},
                    // {title : "报警类型",field : "ftype",sorter : "string",width : 150,align : "center",editor : false,headerSort : false,},
                    // {title : "报警位置",field : "floction",sorter : "string",width : 150,align : "center",editor : false,headerSort : false},
                    {title : "提示信息",field : "ftipInfo",sorter : "string",width : 210,align : "center",editor : false,headerSort : false,},
                    {title : "开始报警时间",field : "fcrdate",sorter : "date",width : 150,align : "center",editor : false,headerSort : false,},
                    {title : "最后报警时间",field : "fatime",sorter : "date",width : 150,align : "center",editor : false,headerSort : false,},
                    {title : "报警等级 ",field : "levelName",sorter : "string",align : "center",editor : false,headerSort : false,formatter : color},
                    // {title : "处理意见",field : "f_suggestion",sorter:"string",visible:false,align:"left",editor : false,headerSort : false,},
                    /*{
                        title: "操作",
                        field: "opt",
                        tooltip: false,
                        tooltipsHeader: false,
                        align: "center",
                        width: 70,
                        formatter: optIconFunction,
                        headerSort: false
                    },*/
                ],
                rowClick : function(e, row) {
                    $("#battery_alarm_warnTable").tabulator("selectRow", 1);
                    _curRow = row;
                },
            });
            $("#battery_alarm_warnTable").tabulator("setData", '${dataset}'==''?[]:'${dataset}');//弹框请求数据
            //$("#warningInfoCount").text($("#battery_alarm_warnTable").tabulator("getData").length);//及时刷新告警图标条数,只是展示当前页面的页数

            return{
                getCurRow : function(e, row) {
                    $("#battery_alarm_warnTable").tabulator("selectRow", 1);
                    return _curRow;
                },
                getPageNum : function() {
                    return pageNum;
                },
                Dispose : function(fguid){
                    swal({
                            title : "您确定要处理吗?",
                            text : "本条信息将转交报警查询！",
                            type : "warning",
                            showCancelButton : true,
                            confirmButtonColor : "#1ab394",
                            confirmButtonText : "确定",
                            closeOnConfirm : false
                        },
                        function() {
                            $.issp_ajax({
                                url: _ctx + "/view/dataAnalysises/wainingInfo/WarningDsipose",
                                type: "post",
                                data:{
                                    "fguid":fguid
                                },
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
                                        top_alarm_warn_modal.refreshWarnModalData();//重新刷新报警处理表格
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
            }
        }
        catch(e){

        };
    })(jQuery, window, document);
</script>