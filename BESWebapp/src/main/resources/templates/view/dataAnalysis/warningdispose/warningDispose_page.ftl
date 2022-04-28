<#--
          报警处理
-->

<!---分页列表开始----->
<div style="height: 100%;">
    <div id="besWainingDisposeTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
    <form action="${ctx }/view/dataAnalysises/wainingInfo/getWarningDisposeInfoData"
          id="besWainingDisposeForm">
        <!-- 查询条件，用隐藏表单域 -->

        <input type="hidden" value="${ftype!}" id="warningDisposefType" name="fType" />
        <input type="hidden" value="${state!}" id="fOpearation" name="fOpearation" />
        <input type="hidden" value="${endtime!}" id="endTime" name="endTime" />
        <input type="hidden" value="${starttime!}" id="startTime" name="startTime" />
        <input type="hidden" value="${flevel!}" id="fLevel" name="fLevel" />
        <input type="hidden" value="${fyqbh!}" id="fYqbh" name="fYqbh" />
        <#assign formId = "besWainingDisposeForm">
        <!-- formId: 分页控件表单ID -->
        <#assign showPageId = "besWainingInfoDispose">
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
        <!-- 分页控键 -->
    </form>
</div>
<script>
    ;
    var dataAnalysis_warningDispose_page = (function($, window, document, undefined) {
    	var _ctx = "${ctx}";
        var _curRow = null;
        var ListData;
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
                    + "<button class='btn btn-white btn-sm edit' data-id='"+ fguid + " '  onClick='dataAnalysis_warningDispose_page.Dispose(&#39;"+fguid+"&#39;);' ><i class='fa fa-pencil'></i>处理</button>"
        };
        $("#besWainingDisposeTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:true,
            movableColumns:true,
            selectable:true,//使行可选
            columns: [
                // {formatter:"rowSelection",titleFormatter:"rowSelection",hozAlign:" center",headerSort:false,cellClick:function (e,cell) {
                //     cell.getRow().toggleSelect();
                //     }
                // },
                {
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
                width:150,
                headerSort: false
            }, {
                title: "实际值",
                field: "factualValue",
                sorter: "string",
                editor: false,
                width:80,
                align: "center",
                headerSort: false,
                formatter : factualValue
            }, {
                title: "计划值",
                field: "fplanValue",
                sorter: "string",
                editor: false,
                width:120,
                align: "center",
                headerSort: false,
                formatter : fplanValue
            }, {
                title: "提示信息",
                field: "ftipInfo",
                sorter: "string",
                editor: false,
                align: "center",
                width:300,
                headerSort: false,
            }, {
                title: "报警时间",
                field: "fatime",
                sorter: "string",
                editor: false,
                align: "center",
                width:200,
                headerSort: false
            }, /*{
                title: "报警类型",
                field: "typeName",
                sorter: "string",
                editor: true,
                align: "center",
                    formatter:"tickCross", sorter:"boolean",
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
                {
                    title: "操作",
                    field: "opt",
                    tooltip: false,
                    tooltipsHeader: false,
                    align: "center",
                    width: 150,
                    formatter: optIconFunction,
                    headerSort: false
                },
            ],
            rowSelectionChanged:function(data, rows){
            //根据选择更改更新选择的行计数器
                ListData = data;
            // document.getElementById("select-stats").innerHTML = data.length;
        },
            rowClick : function(e, row) {
                _curRow = row;
                $("#besWainingDisposeTable").tabulator("selectRow", 1);

            },
        });
        $("#besWainingDisposeTable").tabulator("setData",'${dataset}');
        $(window).resize(function() {
            $("#besWainingDisposeTable").tabulator("redraw");
        });
        return {
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
            DisposeSize : function(){
        	    if (ListData.length == 0){
                   /* swal({
                        title: "请选择",// 展示的标题
                        text: "",// 内容
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1500,
                    });*/
        	        return;
                }
                swal({
                        title : "您确定要处理吗?",
                        text : "这些信息将转交报警查询！",
                        type : "warning",
                        showCancelButton : true,
                        confirmButtonColor : "#1ab394",
                        confirmButtonText : "确定",
                        closeOnConfirm : false
                    },
                    function() {
                        $.ajax({
                            url: _ctx + "/view/dataAnalysises/wainingInfo/WarningDsiposeList",
                            type: "post",
                            contentType:'application/json;charset=UTF-8',// 核心
                            dataType:"json",
                            data:JSON.stringify(
                                ListData
                            ),
                        //     data : {
                        //         listData : ListData
                        // },
                        //     data: $.toJSON(ListData),
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
            pageInit : function(){
            }
        }
    })(jQuery, window, document);
</script>