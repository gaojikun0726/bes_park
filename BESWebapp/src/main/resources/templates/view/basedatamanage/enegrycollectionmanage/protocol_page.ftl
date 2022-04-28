   <div style="height:95%">
		<div id="ProtocolTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/basedatamanage/enegrycollectionmanage/getProtocolList" id="ProtocolPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "ProtocolPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "Protocol_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
var basedatamanage_enegrycollectionmanage_protocol_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
	//alert(pageNum); 
	//新建一个tableDate数组，用来给tabulator表格填充数据
	var tableDate = new Array();
		
        <#list page.list as Protocol >  
        	var rownum = (pageNum-1)*10+j  ;      			
			var arr = {f_id:rownum,
					fTxxybh:"${Protocol.fTxxybh}",
					fType:"${Protocol.fType }",
					fCrdate:"${Protocol.fCrdate}",
					fChdate:"${Protocol.fChdate}"};					
			tableDate[i] = arr;
			i++;	
			j++;				
        </#list>    
	
	//设置格式
    	var optIconFunction = function(cell, formatterParams){ //plain text value
    		var Protocolid = cell.getRow().getData().fTxxybh;
    		return "<div class='btn-group '>"
    			+"<button class='btn btn-white btn-sm edit' data-id="+ Protocolid + " data-toggle='modal' data-target='#modal-form-editProtocol'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + Protocolid + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};

    	//创建并设置table属性
    	$("#ProtocolTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:true,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",width:50,formatter:"rownum",frozen:false,align : "center",sorter:"number",headerSort:false},
    			{title:"通讯协议编号", field:"fTxxybh", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"通讯协议类型", field:"fType",sorter:"string",editor:false,align:"left",headerSort:false},
    			{title:"创建日期", field:"fCrdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"修改日期", field:"fChdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#ProtocolTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#ProtocolTable").tabulator("setData",tableDate);

	})(jQuery, window, document);
</script>