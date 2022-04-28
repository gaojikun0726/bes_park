<!---分页列表开始----->
<div style="height: 100%;">
    <div id="besSysWainingInfoTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
    <form action="${ctx }/view/dataAnalysises/wainingInfo/getSysWarningInfoData"
          id="besSysWainingInfoForm">
        <!-- 查询条件，用隐藏表单域 -->

        <input type="hidden" value="${ftype!}" id="sysfType" name="fType" />
        <input type="hidden" value="${state!}" id="sysfOpearation" name="fOpearation" />
        <input type="hidden" value="${endtime!}" id="sysendTime" name="endTime" />
        <input type="hidden" value="${starttime!}" id="sysstartTime" name="startTime" />
        <input type="hidden" value="${flevel!}" id="sysfLevel" name="fLevel" />
        <input type="hidden" value="${fyqbh!}" id="sysfYqbh" name="fYqbh" />

		<#assign formId = "besSysWainingInfoForm">
        <!-- formId: 分页控件表单ID -->
		<#assign showPageId = "bessystemWaininginfoRecords">
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
        <!-- 分页控键 -->
    </form>
</div>
<script>
    ;
    var dataAnalysis_systemWainingInfo_page = (function($, window, document, undefined) {
        var _curRow = null;

        var dealSysFunction = function(cell, formatterParams) { //plain text value
            var fopearation = cell.getRow().getData().fopearation;
           if(fopearation =='1')
           {
             return '未处理';
           }
           else
           {
               return '已处理';
           }
        };

        $("#besSysWainingInfoTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:false,
            movableColumns:true,
            columns: [{
                title: "序号",
                field: "row",
                formatter: "rownum",
                sorter: "number",
                align: "center",
                headerSort: false,
            }, {
                title: "报警位置",
                field: "floction",
                sorter: "string",
                editor: false,
                width:200,
                align: "center",
                headerSort: false,
            }, {
                title: "报警名称",
                field: "falarmName",
                sorter: "string",
                editor: false,
                align: "center",
                width:150,
                headerSort: false
            }, {
                title: "所属园区",
                field: "fyqbh",
                sorter: "string",
                editor: false,
                align: "center",
                headerSort: false
            }, {
                title: "实际值",
                field: "factualValue",
                sorter: "string",
                editor: false,
                align: "center",
                headerSort: false,
            }, {
                title: "计划值",
                field: "fplanValue",
                sorter: "string",
                editor: false,
                align: "center",
                headerSort: false
            }, {
                title: "提示信息",
                field: "ftipInfo",
                sorter: "string",
                editor: false,
                align: "center",
                width:150,
                headerSort: false,
            }, {
                title: "报警时间",
                field: "fatime",
                sorter: "string",
                editor: false,
                align: "center",
                width:200,
                headerSort: false
            }, {
                title: "是否处理",
                field: "fopearation",
                sorter: "string",
                editor: false,
                align: "center",
                formatter: dealSysFunction,
                headerSort: false
            },
                {
                    title: "报警等级",
                    field: "levelName",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
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
                $("#besSysWainingInfoTable").tabulator("selectRow", 1);

            },
        });
        $("#besSysWainingInfoTable").tabulator("setData",'${dataset}');
        $(window).resize(function() {
            $("#besSysWainingInfoTable").tabulator("redraw");
        });
        return {

            pageInit : function(){

            }
        }
    })(jQuery, window, document);
    dataAnalysis_systemWainingInfo_page.pageInit();
</script>