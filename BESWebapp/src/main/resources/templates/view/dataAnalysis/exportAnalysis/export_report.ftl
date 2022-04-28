<#--
    节能诊断分析页面
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
            <div id='analysis_parkGroup'></div>
        </div>
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">开始时间:</span>
            <input id="analysis_start_time" class="input-datecheck" class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',firstDayOfWeek:1,readOnly:true})" />
        </div>
        <div class="search_waininginfo_div_style" >
            <span class="zl_sxtj_span">结束时间:</span>
            <input id="analysis_end_time"  class="input-datecheck"
                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',firstDayOfWeek:1,readOnly:true})" />
        </div>
        
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">时间类型:</span>
            <div id="analysis_timeType" ></div>
        </div>
        
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">能耗类型:</span>
            <div id="analysis_type" ></div>
        </div>

        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">统计类型:</span>
            <div id="analysis_statisticsType" ></div>
        </div>
        
        <div class="search_waininginfo_div_style">
            <span class="zl_sxtj_span">报表类型:</span>
            <div id="analysis_reportType" ></div>
        </div>
        
        <div style="float: right;margin-right: 3vh;margin-top:10px" >
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_exportAnalysis_export_report.wainingData_search()">
                <i class="fa fa-search"></i>&nbsp;查询
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;"
                    onclick="dataannlysis_exportAnalysis_export_report.exportReport()">
                <i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;导出报表
            </button>
        </div>
        
        <#--<div style="float: right;margin-right: 3vh;margin-top:10px" >-->
            <#--<button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;"-->
                    <#--onclick="dataannlysis_exportAnalysis_export_report.impReport()">-->
                <#--<i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;导入报表-->
            <#--</button>-->
        <#--</div>-->
    </div>
</div>
<!-- 右侧内容模块start -->
<div class="information_right">
        <div class="information-model">
            <span class="Subtitle">
                <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据展示>>>
            </span>
        <#--打印按钮-->
            <a href="javascript:void(-1);" onclick="dataannlysis_exportAnalysis_export_report.print()" class="btn btn-primary toLeft">
                <i class="fa fa-print"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
            </a>
        </div>
        <#--右侧tabel -->
        <div id="exportReportTable" class="Information_area" >
        </div>
</div>
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="import-Model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">导入报表</h4>
            </div>
            <div class="modal-body">
	            <div class="row">
		            <div class="col-md-12">
		                <div class="alert alert-danger" style="font-size:13px;" role="alert">
		                <strong>注 意：</strong><br>
		                &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
		                </div>
		            </div>
	            </div>
				<div class="row"> 
		             <div class="col-xs-12">               
		                 <div class="input-group">
		                    <span class="input-group-addon">模板下载</span>
		                     <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp" onclick="dataannlysis_exportAnalysis_export_report.btn_exp()">下载模板</a>
		                         </span>
		                     </div>
		                </div>
		             </div>                    
		        </div>
				
				<div class="row" id="import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <input id="datecenterImport"  type="file" name="file"  class="file-loading">
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="dataannlysis_exportAnalysis_export_report.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
    ;
    var dataannlysis_exportAnalysis_export_report = (function($, window, document, undefined) {

    var fQybh = "";//园区编号
    var timeType = ""; //时间类型
    var energyType = "";//能耗类型
    var exportType = "";//报表类型
    var exportName = "";//报表名称
    var statisticsType = "";//统计类型
    
    //fileinput 上传插件初始化
   	function initFileinput(){
   		$("#datecenterImport").fileinput({
               language: 'zh', //设置语言
               uploadUrl: '${ctx}/view/dataAnalysis/fileUpload',//上传请求路径
               allowedFileExtensions : ['xls', 'xlsx'],//接收的文件后缀,
               showUpload: true, //是否显示上传按钮
               showCaption: true,//是否显示标题
               browseClass: "btn btn-primary", //按钮样式
               maxFileSize:3072,//最大单个文件上传大小
               maxFileCount:1,//最大上传个数
               showUpload:false,//是否显示上传按钮
               showBrowse:true,//是否显示浏览按钮
               showPreview:false,//是否显示预览区域

               autoReplace:true,//是否自动替换
               showRemove:true,//是否显示移除按钮
               uploadExtraData:function(id,index){
                   return {"fold":"importExcel"};
               },//区分不同的模块：fold：文件夹
               //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取 
               deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
           }).on("fileuploaded",function(){
               clearForm();//清空form表单
           }).on("filebatchuploadsuccess",function(){
               clearForm();//清空form表单
           });
   	}
    
    //清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
        $("#datecenterImport").fileinput("destroy");//销毁fileinput删除上传文件缓存
    	$("#import-Model").modal("hide");//关闭模态框
    	swal({title: "文件上传成功！",type: "success",showCloseButton: true});
    }
    
    //实例化表格
    function inTabulator(result){
    	var list = result.list ;
    	if(list=='undefined'||list==''||list==null){
            $("#exportReportTable").tabulator("setData",[]);
    		return false;
    	}
    	var data = result.data ;
    	//处理表头时间
    	var colums = [];
    	colums.push({title:"父级支路", field:"fPzlmc" ,sorter:"string",editor:false,align:"left",headerSort:false},);
    	colums.push({title:"支路名称", field:"fZlmc" ,sorter:"string",editor:false,align:"left",headerSort:false},);
    	for(var i=0;i<data.length;i++){
    		colums.push({title:"时间"+data[i].substr(5,7), field:data[i] ,sorter:"string",editor:false,align:"right",headerSort:false},);
    	}
    	colums.push({title:"总计", field:"AllFdata" ,sorter:"string",editor:false,align:"right",headerSort:false},);
    	//创建并设置table属性
        $("#exportReportTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:false,
            movableColumns:true,
            columns:colums,
        });
        $("#exportReportTable").tabulator("setColumns",colums);//用新列定义数组覆盖现有列 */
        $("#exportReportTable").tabulator("setData",list); 
    }
    

        /**
         * 初始化筛选条件
         */
         function get_CurrentDate(){
            //获取当前时间
             var date = getCurrentDate();
             //起始时间和默认时间
             $('#analysis_start_time').val(getFormatDate(date).substr(0,7));
             $('#analysis_end_time').val(getFormatDate(date).substr(0,7));
         }
         
        //园区下拉列表
         function get_yqtree_sub() {
		    var park_id = [];
		    var park_val = [];
		    $.issp_ajax({
		        type: "get",
		        url: '${ctx}/view/basedatamanage/energyinformation/park_tree',
		        success: function(data) {
		            if (data.list.length > 0) {
		                for (var i = 0; i < data.list.length; i++) {
		                    var obj = data.list[i];
		                    park_id.push(obj.f_yqbh);
		                    park_val.push(obj.f_yqmc);
		                }
		                fQybh = data.list[0].f_yqbh;
		                park_group_select('#analysis_parkGroup', park_id, park_val);
		            }
		        },
		        error: function(data) {
		            swal(data.msg, "", "error");
		        }
		    });
		}

        //时间类型下拉列表
        function getAnalysis_timeType(){
            var type_id=['2','2','3'];
            var type_val=['月度','季度','年度'];
            time_type_select('#analysis_timeType',type_id,type_val);
        }
        
        //能耗类型
        function getAnalysisType(){
            var type_id = [];
            var type_val = [];
            $.issp_ajax({
                type: "post",
                url: _ctx + "/view/dataAnalysis/zl_tablist",
                success: function (result) {
                    if(result.hasOwnProperty("list")){
                    	for(var i=0;i<result.list.length;i++){
                            var obj=result.list[i];
                            type_id.push(obj.ID);
                            type_val.push(obj.NAME);
                        }
			            type_group_select('#analysis_type',type_id,type_val);
                    }
                }
            });
        }
       //报表类型
        function getAnalysis_reportType(){
             //财务分析报表导出没有数据，去掉此报表
            var type_id=['F_DATA','F_PERMAN_DATA','F_UNITAREA_DATA'];
            var type_val=['能源消耗分析报表','人均能耗分析报表','单位面积能耗分析报表'];
            export_select('#analysis_reportType',type_id,type_val);
        }

        //统计类型
        function getAlarmLevel(){
            var level_id=['zl','fx'];
            var level_val=['支路','分项'];
            statisticsType_select('#analysis_statisticsType',level_id,level_val);
        }

        //时间类型selected
        function time_type_select(id,idArray,valArray){
        	$(id).ISSPSpinnerBox({
                width:'9vw',//下拉列表宽度
                height: '2.9vh',//下拉列表高度
                margLeft:'0px',//margin-left属性
                isHasData:true,
                idArray:idArray,//id
                valArray:valArray,//txt
                isNoSelectedText:false, //是否设置未选中提示文本
                liveSearch:false,
                callBack: timeTypeChange,
            });
        }
        function timeTypeChange(sp){
        	timeType = sp.id;
        }
        
        //能耗类型selected
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
                callBack: energyTypeChange,
            });
        }
        //园区改变事件
        function energyTypeChange(sp){
        	energyType=sp.id;//每次改变赋值给tableType
        }
        
      //报表类型selected
        function export_select(id,idArray,valArray){
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
                callBack: exportChange,
            });
        }
        //报表类型改变事件
        function exportChange(sp){
        	exportType=sp.id;//每次改变赋值给tableType
        	exportName = sp.txt;
        }
        
      //统计类型selected
        function statisticsType_select(id,idArray,valArray){
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
                callBack: statisticsTypeChange,
            });
        }
        //统计类型改变事件
        function statisticsTypeChange(sp){
        	statisticsType=sp.id;//每次改变赋值给tableType
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
        return{
            /* 筛选 */
            wainingData_search:function () {
                var startTime=$("#analysis_start_time").val();//开始时间
                var endTime=$("#analysis_end_time").val();//结束时间
                $.issp_ajax({
                    url:"${ctx}/view/dataAnalysis/getExportReport",
                    type:"post",
                    dataType:"json",
                    data:{
                         fYqbh : fQybh,
                         startTime : startTime,
                         endTime : endTime,
                         timeType:timeType,
                         energyType:energyType,
                         exportType:exportType,
                         statisticsType,statisticsType,
                    },
                    success : function(result) {
                        if(result.status=='1'){
                       	    inTabulator(result);//表格
                        }else{
                            swal("没有搜索到要查找的内容", "", "warning");
                        }
                    },
                    error:function(data) {
                        swal( data.msg,"", "error");
                    }
                });
            },
            //导出excel
            exportReport:function(){
            	var startTime=$("#analysis_start_time").val();//开始时间
                var endTime=$("#analysis_end_time").val();//结束时间
            	var data = {
                    fYqbh : fQybh,
                    startTime : startTime,
                    endTime : endTime,
                    timeType:timeType,
                    energyType:energyType,
                    exportType:exportType,
                    statisticsType:statisticsType,
               };
                var _url = "${ctx}/view/dataAnalysis/exportExcel";
                doExp(_url,exportName,"${ctx}",data);//导出excel并下载
            },
            //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
            btn_exp : function(){
                var fname = "下载模板示例.xls";
                var path = "WEB-INF\\file\\expExcel\\下载模板示例2.xls";
                FileDownload("${ctx}/file/ExpfileDownload",fname,path);
            },
            impReport :function(){
            	//1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
            	$("#import-Model").modal("show");
            	initFileinput();//初始化fileinput
            },
            //导入数据按钮
            btn_import:function(){
            	var filepath = $("#datecenterImport").val();
            	if(filepath!=""){
	            	$("#datecenterImport").fileinput("upload");//上传方法
            	}else{
            		swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
            	}
            },
            pageInit:function(){
                get_CurrentDate();
                get_yqtree_sub();
                getAnalysis_timeType();
                getAlarmLevel();
                getAnalysisType();
                getAnalysis_reportType();
            },
            //打印按钮
            print :function() {
                $("#exportReportTable").printThis({});
            }
        }
    })(jQuery, window, document);
    dataannlysis_exportAnalysis_export_report.pageInit();


</script>