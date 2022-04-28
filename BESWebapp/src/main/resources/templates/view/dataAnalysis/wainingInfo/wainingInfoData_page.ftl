
<#--
    报警信息查询页面
-->
<!---分页列表开始----->
<div style="height: 100%;">
    <div id="besWainingInfoTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
    <form action="${ctx }/view/dataAnalysises/wainingInfo/getWarningInfoData"
          id="besWainingInfoForm">
        <!-- 查询条件，用隐藏表单域 -->

        <input type="hidden" value="${ftype!}" id = "wainingInfofType" name="fType" />
        <input type="hidden" value="${state!}" id = "wainingInfofOpearation" name="fOpearation" />
        <input type="hidden" value="${endtime!}" id = "wainingInfoendTime" name="endTime" />
        <input type="hidden" value="${starttime!}" id = "wainingInfostartTime"  name="startTime" />
        <input type="hidden" value="${flevel!}" id = "wainingInfofLevel" name="fLevel" />
        <input type="hidden" value="${fyqbh!}" id = "wainingInfofYqbh" name="fYqbh" />

		<#assign formId = "besWainingInfoForm">
        <!-- formId: 分页控件表单ID -->
		<#assign showPageId = "besWainingInfoRecords">
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
        <!-- 分页控键 -->
    </form>
</div>
<script>
    ;
    var dataAnalysis_wainingInfo_page = (function($, window, document, undefined) {
        var _curRow = null;
        var _ctx = "${ctx}";

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
        var dealFunction = function(cell, formatterParams) { //plain text value
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
        $("#besWainingInfoTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:true,
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
                // width:300,
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
            },{
                title: "实际值",
                field: "factualValue",
                sorter: "string",
                editor: false,
                // width:100,
                align: "center",
                headerSort: false,
                formatter : factualValue
            }, {
                title: "计划值",
                field: "fplanValue",
                sorter: "string",
                editor: false,
                // width:130,
                align: "center",
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
                title: "报警时间",
                field: "fatime",
                sorter: "string",
                editor: false,
                align: "center",
                // width:200,
                headerSort: false
            },/*, {
                title: "是否处理",
                field: "fopearation",
                sorter: "string",
                editor: false,
                align: "center",
                formatter: dealFunction,
                headerSort: false
            }, {
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
                $("#besWainingInfoTable").tabulator("selectRow", 1);

            },
        });
        $("#besWainingInfoTable").tabulator("setData",'${dataset}');
        $(window).resize(function() {
            $("#besWainingInfoTable").tabulator("redraw");
        });
        return {

            pageInit : function(){
            }
        }
    })(jQuery, window, document);
    dataAnalysis_wainingInfo_page.pageInit();

</script>