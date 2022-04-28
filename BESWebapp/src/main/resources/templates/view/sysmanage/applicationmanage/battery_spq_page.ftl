
<div style="height: calc(100% - 60px)">
	<div id="spqTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
	<form
		action="${ctx }/adapter/battery_spq_page"
		id="battery_spq_PageForm">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords! }" name="keywords" />
		<#assign formId = "battery_spq_PageForm">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "spq_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>

<script type="text/javascript">
;
var sysmanage_applicationmanage_battery_spq_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
	//alert(pageNum); 
	//新建一个tableDate数组，用来给tabulator表格填充数据
	var tableDate = new Array();
		
        <#list page.list as equipmentType >  
        	var rownum = (pageNum-1)*10+j  ;      			
			var arr = {f_id:rownum,fGuid:"${equipmentType.fGuid }",
					   fSbmc:"${equipmentType.fSbmc }",
					   fManufacturer:"${equipmentType.fManufacturer }",
					   fIp:"${equipmentType.fIp }",
					   fPort:"${equipmentType.fPort }",
					   fClasspath:"${equipmentType.fClasspath}",
					   fAdaptermc:"${equipmentType.fAdaptermc}",	
				       fCrdate:"${equipmentType.fCrdate}",
					   fChdate:"${equipmentType.fChdate}"}				
			tableDate[i] = arr;
			i++;	
			j++;				
        </#list>    


	
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var roleGuid = cell.getRow().getData().fGuid;
		return "<div class='btn-group '>"
		<!--+"<button class='btn btn-white btn-sm roleInclude' data-id=" + roleGuid + " data-toggle='modal' data-target='#includeAdapter'><i class='fa fa-user'></i>  包含用户</button>"-->
		+"<button class='btn btn-white btn-sm edit' data-id="+ roleGuid + " data-toggle='modal' data-target='#editAdapterForm'><i class='fa fa-pencil' ></i> 编辑</button>"
		+"<button class='btn btn-white btn-sm delete' data-id=" + roleGuid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};
	

		//创建并设置table属性
	$("#spqTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"设备名称", field:"fSbmc", width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"服务提供厂商", field:"fManufacturer",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"服务IP地址", field:"fIp", width:150,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"服务端口", field:"fPort",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"适配器处理类", field:"fClasspath",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"适配器名称", field:"fAdaptermc",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		 {title:"操作", field:"opt",tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		], 
		rowClick:function(e, row){
        	$("#spqTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	
	//填充数据
	//$("#spqTable").tabulator("setData", _ctx+'/adapter/getAllAdapter');
	
	/* $(window).resize(function(){
		$("#spqTable").tabulator("redraw");
	}); */
	
	
	//return{
			/* getCurRow:function(e, row){
	        	$("#spqTable").tabulator("selectRow", 1);
	        	return _curRow; */
	        	
	    	//},
	    	 /*  pageInit : function(){
	    		  sysmanage_applicationmanage_battery_spq_page.getCurRow();
	    	 		
	    	} */
	    	
		}
	);
   	$("#spqTable").tabulator("setData",tableDate);
})(jQuery, window, document);
</script>