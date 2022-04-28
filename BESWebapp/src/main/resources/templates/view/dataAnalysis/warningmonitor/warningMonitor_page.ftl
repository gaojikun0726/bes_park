<#--
          报警监控页面
-->

<!---分页列表开始----->
<div style="height: 100%;">
    <div id="besWainingRealTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
    <form action="${ctx }/view/dataAnalysises/wainingInfo/getWarningRealInfoData"
          id="besWainingRealForm">
        <!-- 查询条件，用隐藏表单域 &ndash;&gt;-->
        <input type="hidden" id="warnModalPageNum" value="${pageNum! }"  name="warnModalPageNum" />
        <input type="hidden" value="${ftype!}" id="warningMonitorfType" name="fType" />
        <input type="hidden" value="${state!}" id="warningMonitorfOpearation" name="fOpearation" />
        <input type="hidden" value="${endtime!}" id="warningMonitorendTime" name="endTime" />
        <input type="hidden" value="${starttime!}" id="warningMonitorstartTime" name="startTime" />
        <input type="hidden" value="${flevel!}" id="warningMonitorfLevel" name="fLevel" />
        <input type="hidden" value="${fyqbh!}" id="warningMonitorfYqbh" name="fYqbh" />

        <#assign formId = "besWainingRealForm">
        <!-- formId: 分页控件表单ID -->
        <#assign showPageId = "besWainingInfoReal">
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
        <!-- 分页控键 -->
    </form>
</div>
<script>
    ;
    var dataAnalysis_warningMonitor_page = (function($, window, document, undefined) {
        var _curRow = null;
        var _fZdmc = null;
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
        var optIconFunction = function(cell, formatterParams) { //plain text value
            var fguid = cell.getRow().getData().fguid;
            return "<div class='btn-group '>"
                    + "<button class='btn btn-white btn-sm edit' data-id='"+ fguid + " ' data-toggle='modal' data-target='#modal-discharge'><i class='fa fa-pencil'></i>处理</button>"
        };
        var pageNum = $("#warnModalPageNum").val();//刷新页面使用(其他页面获取值)
        $("#besWainingRealTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:true,
            movableColumns:true,
            columns: [/*{
                title: "序号",
                field: "row",
                formatter: "rownum",
                sorter: "number",
                align: "center",
                headerSort: false,
            },*/ {
                title: "报警位置",
                field: "floction",
                sorter: "string",
                editor: false,
                // width:0,
                align: "center",
                headerSort: false
            }, {
                title: "报警名称",
                field: "falarmName",
                sorter: "string",
                editor: false,
                align: "center",
                // width:150,
                headerSort: false
            }, {
                title: "所属园区",
                field: "fyqbh",
                sorter: "string",
                editor: false,
                align: "center",
                // width:150,
                headerSort: false
            }, {
                title: "实际值",
                field: "factualValue",
                sorter: "string",
                editor: false,
                align: "center",
                // width:80,
                headerSort: false,
                formatter : factualValue
            }, {
                title: "计划值",
                field: "fplanValue",
                sorter: "string",
                editor: false,
                align: "center",
                // width:80,
                headerSort: false,
                formatter : fplanValue
            }, {
                title: "提示信息",
                field: "ftipInfo",
                sorter: "string",
                editor: false,
                align: "center",
                // width:300,
                headerSort: false,
            }, {
                title: "开始报警时间",
                field: "fcrdate",
                sorter: "string",
                editor: false,
                align: "center",
                width:130,
                headerSort: false
            },{
                title: "最后报警时间",
                field: "fatime",
                sorter: "string",
                editor: false,
                align: "center",
                width:130,
                headerSort: false
            },/*, {
                title: "报警类型",
                field: "typeName",
                sorter: "string",
                editor: false,
                align: "center",
                headerSort: false
            },*/
                {
                    title: "报警等级",
                    field: "levelName",
                    sorter: "string",
                    editor: false,
                    align: "center",
                   
                    headerSort: false,
                    formatter : color
                },
                /*{
                    title: "操作",
                    field: "opt",
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    width: 150,
                    formatter: optIconFunction,
                    headerSort: false
                },*/
            ],
            rowClick : function(e, row) {
                _curRow = row;
                $("#besWainingRealTable").tabulator("selectRow", 1);

            },
        });

        $("#besWainingRealTable").tabulator("setData",'${dataset}');
        $(window).resize(function() {
            $("#besWainingRealTable").tabulator("redraw");
        });
        return {
            getPageNum : function() {
                return pageNum;
            },
            pageInit : function(){
            }
        }
    })(jQuery, window, document);
</script>