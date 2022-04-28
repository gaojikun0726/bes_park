
   <div style="height:95%">
		<div id="mtTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/basedatamanage/eqmanage/moduleType_page" id="mtPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "mtPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "moduleType_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var basedatamanage_eqmanage_moduleType_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	//var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
	//新建一个tableDate数组，用来给tabulator表格填充数据
	var tableDate = new Array();
	//sellist用来存放‘模块点集合’的下拉框列表数据
	var sellist = new Array();
	
        <#list page.list as moduleType >  
        	var rownum = (pageNum-1)*10+j  ;      			
			var arr = {f_id:rownum,
					   fModuleType:"${moduleType.fModuleType}",
					   fTypeCode:"${moduleType.fTypeCode}",
					   fDescription:"${moduleType.fDescription }",
					   fPointAmount:"${moduleType.fPointAmount}",
					   fPointTypeCl:"${moduleType.fPointTypeCl}"};					
			tableDate[i] = arr;
			 	
			var sb='${moduleType.sb}';			
			//var obj = eval('(' + sb + ')');
			sellist[rownum] = sb;
			//转换成json对象(tabulator中option的格式)
			//var jsonStrd = JSON.stringify(sellist[rownum]);	
			i++;	
			j++;
        </#list>    
	
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fModuleType = cell.getRow().getData().fModuleType;	
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ fModuleType + " data-toggle='modal' data-target='#modal-form-editmoduletype'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id="+ fModuleType + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	
	//创建并设置table属性
	$("#mtTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltipGenerationMode:"hover",
		movableColumns:true,
		date:tableDate,
		
		columns:[
		{title:"序号",field:"f_id",align:"center",width:70,frozen:false,sorter:"number",align:"center"}, //frozen column
		{title:"模块型号", field:"fModuleType",align:"center",sorter:"string",headerSort:false,editor:false}, //never hide this column
		{title:"类型代码", field:"fTypeCode",align:"center",sorter:"string",headerSort:false,editor:false}, //never hide this column
		{title:"模块描述", field:"fDescription",align:"center",sorter:"string",headerSort:false,editor:false}, //hide this column first
		{title:"模块点数", field:"fPointAmount",align:"center",sorter:"string",align:"center",headerSort:false,editor:false},
 		//鼠标悬浮在该单元格时，显示详细模块类型
   		{title:"模块点集合", field:"fPointTypeCl",align:"center",headerSort:false,tooltip:function(cell){
 		var fPointTypeCl = cell.getRow().getData().fPointTypeCl;
 		var id=cell.getRow().getData().f_id;
 		var str = sellist[id];
 		var result = str.replace(/","/g,'\r\n');
 		result = result.replace('{"','');
 		result = result.replace('",}','');
 		return result;
 		}},
 
		{title:"操作", field:"opt",width:200,align:"center",headerSort:false,tooltip:false,tooltipsHeader:false,formatter:optIconFunction},
		],
		
	});
	$("#mtTable").tabulator("setData",tableDate);
	return {
		get_curRow : function() {
			return _curRow;
		}
	}
	
})(jQuery, window, document);
</script>