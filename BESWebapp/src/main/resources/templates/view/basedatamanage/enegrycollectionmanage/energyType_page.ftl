   <div style="height:95%">
		<div id="EnergyTypeTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/basedatamanage/enegrycollectionmanage/getEnergyTypeList" id="EnergyTypePageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "EnergyTypePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "EnergyType_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var basedatamanage_enegrycollectionmanage_energyType_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';

    	var optIconFunction = function(cell, formatterParams){ //plain text value
    		var EnergyTypeid = cell.getRow().getData().fGuid;
    		return "<div class='btn-group '>"
    			+"<button class='btn btn-white btn-sm edit' data-id="+ EnergyTypeid + " data-toggle='modal' data-target='#modal-form-editEnergyType'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + EnergyTypeid + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};

    	//创建并设置table属性
    	$("#EnergyTypeTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:false,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",align:"center",width:70,formatter:"rownum",frozen:false,sorter:"number"},
    			{title:"能源编号", field:"fNybh", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"能源名称", field:"fNymc",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"单价", field:"fPrice",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"耗煤量", field:"fCoalAmount",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"二氧化碳", field:"fCo2",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"单位", field:"fUnit",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"创建日期", field:"fCrdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"修改日期", field:"fChdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#EnergyTypeTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#EnergyTypeTable").tabulator("setData",'${dataset}');
    	return {
    		//导出
            exp_excel : function(Object){
                var alias = new Array();
                // excel的列头
                alias.push('能源编号');
                alias.push('能源名称');
                alias.push('单价');
                alias.push('煤耗量');
                alias.push('二氧化碳');
                alias.push('单位');
                // 数据List中的Map的key值.
                var names = new Array();
                names.push('F_NYBH');
                names.push('F_NYMC');
                names.push('F_PRICE');
                names.push('F_COAL_AMOUNT');
                names.push('F_CO2');
                names.push('F_UNIT');
                //报表名称
                var exportName = "能耗类型定义";
                //表名
                var tablename = "bes_energy_type";
                //数据json内容
                var data = {
                    alias : JSON.stringify(alias),
                    names : JSON.stringify(names),
                    tablename : tablename,
                };
                //统一导出excel接口
                var _url = "${ctx}/expExcel/exportExcel";
                doExp(_url,exportName,"${ctx}",data);//导出excel并下载
            },
    		get_curRow : function(){
    			return _curRow;
    		}
    	}    	
})(jQuery, window, document);
</script>