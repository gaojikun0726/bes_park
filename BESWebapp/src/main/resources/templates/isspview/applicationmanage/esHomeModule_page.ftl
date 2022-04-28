
   <div style="height:95%">
		<div id="esHomeModuleTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/esHomeModule/showPage" id="esHomeModulePageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="esHomeModule_pageSize" name="esHomeModule_pageSize" />
				<#assign formId = "esHomeModulePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "esHomeModule_dataCenter"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
	var isspview_applicationmanage_esHomeModulePage = (function($, window, document, undefined){
		var _ctx = '${ctx}';	
		var _curRow = null;
		var i = 0;
		var j = 1;
		var pageNum = '${page.pageNum}';
		//alert(pageNum); 
		//新建一个tableDate数组，用来给tabulator表格填充数据
		/* var tableDate = new Array();
			
	        <#list page.list as esHomeModule >  
	        	var rownum = (pageNum-1)*10+j  ;      			
				var arr = {guid:"${esHomeModule.guid}",f_mkbh:"${esHomeModule.f_mkbh}",f_mkmc:"${esHomeModule.f_mkmc }",
						f_crdate:"${esHomeModule.f_crdate}",f_chdate:"${esHomeModule.f_chdate}",f_css_class:"${esHomeModule.f_css_class}",f_showleftmenus:"${esHomeModule.f_showleftmenus}"};					
				tableDate[i] = arr;
				i++;	
				j++;				
	        </#list>    
 */

		
		//设置格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
		var f_type = cell.getRow().getData().guid;	
			return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#editEsHomeModuleForm'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
		};
		
		var showLeftMenusFormat = function(cell,formatterParams){
			var f_showleftmenus = cell.getRow().getData().f_showleftmenus;
			if(f_showleftmenus == null || f_showleftmenus == "" || f_showleftmenus == undefined){
				return '';
			}
			
			if(f_showleftmenus == '0'){
				return "不显示";
			}
			
			if(f_showleftmenus == '1'){
				return "显示";
			}
		}

			//创建并设置table属性
		$("#esHomeModuleTable").tabulator({
			height:"100%",
			layout:"fitColumns",//fitColumns  fitDataFill
			columnVertAlign:"bottom", //align header contents to bottom of cell
			tooltips:true,
			// selectable : 1,
			movableColumns:false,
			
			columns:[
			{title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false}, //frozen column
			{title:"ID",field:"guid",width:70,frozen:false,sorter:"number",align:"center",visible:false,headerSort:false}, //frozen column
			{title:"系统模块编号", field:"f_mkbh",sorter:"string",editor:false,align:"left",headerSort:false}, //never hide this column
			{title:"系统模块名称", field:"f_mkmc",sorter:"string",editor:false,align:"left",headerSort:false}, //hide this column first 
			{title:"系统图标", field:"f_css_class",sorter:"string",align:"left",editor:false,headerSort:false},
			{title:"排序", field:"f_sort",sorter:"string",align:"center",editor:false},
			{title:"是否显示左侧菜单", field:"f_showleftmenus",sorter:"string",align:"left",editor:false,formatter:showLeftMenusFormat,headerSort:false},
			{title:"创建时间", field:"f_crdate",sorter:"string",align:"center",editor:false,headerSort:false},
			{title:"修改时间", field:"f_chdate",sorter:"string",align:"center",editor:false,headerSort:false},
			{title:"操作", field:"opt",align:"left",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false},
			],
			rowClick:function(e, row){
	        	_curRow = row;
	    	},
		});
	$("#esHomeModuleTable").tabulator("setData",'${dataset}');
	return {
		getCurRow : function(){
			return _curRow;
		}
	}
	})(jQuery, window, document);
</script>