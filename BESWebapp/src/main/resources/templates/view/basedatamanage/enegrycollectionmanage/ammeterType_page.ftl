   <div style="height:95%">
		<div id="AmmeterTypeTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/basedatamanage/enegrycollectionmanage/getAmmeterTypeList" id="AmmeterTypePageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "AmmeterTypePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "AmmeterType_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var dataAnalysis_ammeterType_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
	//alert(pageNum); 
	//新建一个tableDate数组，用来给tabulator表格填充数据
	var tableDate = new Array();
        <#list page.list as AmmeterType >  
        	var rownum = (pageNum-1)*10+j  ;      			
			var arr = {f_id:rownum,
					fLxbh:"${AmmeterType.fLxbh}",
					fLxmc:"${AmmeterType.fLxmc }",
					fCrdate:"${AmmeterType.fCrdate}",
					fChdate:"${AmmeterType.fChdate}"};					
			tableDate[i] = arr;
			i++;	
			j++;				
        </#list>    

	
    	var optIconFunction = function(cell, formatterParams){ //plain text value
    		var AmmeterTypeid = cell.getRow().getData().fLxbh;
    		return "<div class='btn-group '>"
    			+"<button class='btn btn-white btn-sm edit' data-id="+ AmmeterTypeid + " data-toggle='modal' data-target='#modal-form-editAmmeterType'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + AmmeterTypeid + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};

    	//创建并设置table属性
    	$("#AmmeterTypeTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:true,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",width:50,formatter:"rownum",frozen:false,align : "center",sorter:"number",headerSort:false},
    			{title:"类型编号", field:"fLxbh", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"类型名称", field:"fLxmc",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"创建日期", field:"fCrdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"修改日期", field:"fChdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#AmmeterTypeTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#AmmeterTypeTable").tabulator("setData",tableDate);

    	return{
            //导出
            exp_excel : function(Object){
                var alias = new Array();
                // excel的列头
                alias.push('类型编号');
                alias.push('类型名称');
                // 数据List中的Map的key值.
                var names = new Array();
                names.push('F_LXBH');
                names.push('F_LXMC');
                //报表名称
                var exportName = "电表类型定义";
                //数据json内容
                var tablename = 'bes_ammeter_type' ;
                var data = {
                    alias : JSON.stringify(alias),
                    names : JSON.stringify(names),
                    tablename,tablename,
                };
                var _url = "${ctx}/expExcel/exportExcel";
                doExp(_url,exportName,"${ctx}",data);//导出excel并下载

            },
		}
    	
    })(jQuery, window, document);
</script>