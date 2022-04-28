
<#----
@description 用户数据权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/11/24
---->

<!--用户列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择用户名称>>>
		</span>
	</div>
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_user_Keywords" name="search_user_Keywords" placeholder="用户编号、名称">
			  <button  onclick="dataauthoritymanage_usersjqx.search_user_usersjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esuser_showtable" style="height: 92%;">
	</div>
</div>
<!--数据字典列表  -->
<div class="leftarea information_left"style="margin-left: 3px;border-left: solid 1px #366c90;">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择数据权限名称>>>
		</span>
	</div>
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_sjqx_Keywords" name="search_sjqx_Keywords" placeholder="数据权限名称">
			  <button  onclick="dataauthoritymanage_usersjqx.search_sjqx_usersjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="essjqx_showtable" style="height: 92%;">
	</div>
</div>
    
<!-- 用户数据权限列表 -->
<div class="information_right twoSub">
   <div class="information_size">
   		<div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;用户数据权限列表>>>
			</span>
			<a href="javascript:void(-1);" onclick="dataauthoritymanage_usersjqx.execute_sjqx()" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
		</div>
		<div id="esUserSjqxManageTable" style="height: 92%;">	</div>
   </div>
</div>
<style>
.tabulator-col-title{
		display:flex;
		justify-content:center;
		align-items:center;
		}

</style>

 <script type="text/javascript">
 ;var dataauthoritymanage_usersjqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	
	var oldData=null;//改变之前加载过来的表格数据
	var savesink = false;//保存标识，true表示以已完成保存操作，false表示未点击保存按钮
	var $usersjqxstatus = new Array();   //定义数组
	$usersjqxstatus[0] = null;//用户编码
	$usersjqxstatus[1] = null;//数据权限编号
	$usersjqxstatus[2] = null;//数据表名
	$usersjqxstatus[3] = null;//主键
	$usersjqxstatus[4] = null;//权限标志
	$usersjqxstatus[5] = null;//名称字段
	var currentsjqxtb = "";//当前表
	var columnFiled= new Array(); //定义表格-列字段数组 
	var columnName= new Array(); //定义表格-列标题数组 
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-group '><button>全选</button></div>"
	};
	//展现用户数据权限列表
	function startShowUserSjqx(){
		
		$(".userbz[type='checkbox']").removeAttr("checked");
		var yhbh = $usersjqxstatus[0];//用户编号
		var qxbh = $usersjqxstatus[1];//权限编号
		var tabn = $usersjqxstatus[2];//数据表名
		var bhzd = $usersjqxstatus[3];//主键
		var qxbz = $usersjqxstatus[4];//权限标志
		var mczd = $usersjqxstatus[5];//名称字段
		
		
		if( yhbh != null && qxbh != null && tabn != null && bhzd != null){
			
			$("#esUserSjqxManageTable").tabulator("clearData");
			var newColumns = [
							{title:"序号",width:80,formatter:"rownum",align:"center",frozen:false,headerSort:false},
							{title:"用户编号",width:80, field:"f_yhbh",align:"center", sorter:"string",editor:false,headerSort:false},
							{title:"权限编号", field:"f_qxbh",align:"center", sorter:"string",editor:false,headerSort:false},
							{title:"数据编号", field:"f_sjbh",align:"center",sorter:"string",editor:false,headerSort:false},
							{title:"数据名称", field:"f_sjmc",align:"left",sorter:"string",editor:false,headerSort:false},
							{title:"权限标志", field:"f_qxbz",align:"center",sorter:"string",editor:false,headerSort:false},
							//{title:"全部<input id=\"id_f_g0\" style='margin-top: 1px' type='checkbox' class='userbz' onclick=\"dataauthoritymanage_usersjqx.user_transmitField('f_g0')\">", field:"f_g0",width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false},
							]
			for (var i = 0; i < columnFiled.length; i++) {
				newColumns[i+5] = {title:columnName[i]+"<input id='id_"+columnFiled[i]+"' style='margin-top: 1px' type='checkbox' class='userbz' onclick=\"dataauthoritymanage_usersjqx.user_transmitField('"+columnFiled[i]+"')\">", field:columnFiled[i],width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false};
			}
			$("#esUserSjqxManageTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列 */
			//$("#esUserSjqxManageTable").tabulator("setData", _ctx+'/view/usersjqx/usersjqx_list?f_yhbh='+yhbh+'&f_qxbh='+qxbh+'&f_tabn='+tabn+'&f_bhzd='+bhzd+'&f_qxbz='+qxbz+'&currentsjqxtb='+currentsjqxtb+'&mczd='+mczd);	
			$.ajax({
        	    url: _ctx + "/view/usersjqx/usersjqx_list",
        	    contentType: "application/json; charset=utf-8",
        	    type: "get",
        	    data: {
        	    	f_yhbh:yhbh,
        	    	f_qxbh:qxbh,
        	    	f_tabn:tabn,
        	    	f_bhzd:bhzd,
        	    	f_qxbz:qxbz,
        	    	currentsjqxtb:currentsjqxtb,
        	    	mczd:mczd
        	    },
        	    beforeSend: function () { 
    				showLoad();	             
    			},
				success: function(result) {
					if(result.status == "1"){
						if(result.hasOwnProperty("list")){
				            $("#esUserSjqxManageTable").tabulator("setData", result.list);
						}else{
				            $("#esUserSjqxManageTable").tabulator("setData", []);
						}
						oldData = $("#esUserSjqxManageTable").tabulator("getData");//作为原始数据
					}else{
						 swal( result.msg,"", "warning");
					}
	            },
	            complete: function () {
					hiddenLoad();
				},
	            error: function(result) {
	          	    swal( "查询失败","", "error");
	            }
    	   });
		}
	}
	//操作验证,判断是否为保存后跳转
	function dealData(data){
		var bool = true;
		 for (var i = 0; i < data.length; i++) {
			var newobj = data[i];//改变之后的表格数据
			var oldobj = oldData[i];//改变之前的表格数据
			for (var j = 0; j < columnFiled.length; j++) {
				if(newobj["f_g"+(j+1)] != oldobj["f_g"+(j+1)]){
					bool = false;
					break;
				}
				
			}
		}
		 return bool;
	}
	//创建并设置table属性-用户列表
	$("#esuser_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
	//	{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,headerSort:false,align:"center"},
	//	{title:"用户编号", field:"f_yhbh",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"用户名称", field:"f_name",sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var yhbh = _curRow.getData().f_yhbh;
        	if($usersjqxstatus[0] != yhbh){
        		if(savesink == false){
	        		var bool = dealData($("#esUserSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$usersjqxstatus[0] = yhbh;
  			        	startShowUserSjqx();
		    		}else{
		    			swal({ 
		   				 title: "是否将更改保存？", 
		   				  //text: "功能权限数据已做更改，建议保存！", 
		   				  type: "warning",
		   				  showCancelButton: true, 
		   				  //confirmButtonColor: "#DD6B55",
		   				  confirmButtonText: "确定", 
		   				  cancelButtonText: "取消",
		   				  closeOnConfirm: false, 
		   				  closeOnCancel: true	
		   				},
		   				  function(isConfirm){
		   					  if (isConfirm) { 
		   						dataauthoritymanage_usersjqx.execute_sjqx();
		   						$usersjqxstatus[0] = yhbh;
		   						startShowUserSjqx();
		   					  } else { 
		   						$usersjqxstatus[0] = yhbh;
		   			        	startShowUserSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$usersjqxstatus[0] = yhbh;
		        	startShowUserSjqx();
        		}
   			}
		}
	});
	//创建并设置table属性-数据权限列表
	$("#essjqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		{title:"权限名称", field:"f_qxmc",width:110, sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"表名", field:"f_tabn", sorter:"string",align:"center",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var qxbh = _curRow.getData().f_qxbh;//权限编号
        	if($usersjqxstatus[1] != qxbh){
        		if(savesink == false){
	        		var bool = dealData($("#esUserSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$usersjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
			        	$usersjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
			        	$usersjqxstatus[3] = _curRow.getData().f_bhzd;//主键
			        	$usersjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
			        	$usersjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
			        	currentsjqxtb = _curRow.getData().f_user_qxb;//当前数据权限存储表
			        	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
			        	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
			        	splitStr(f_qxbz,f_qxsm);//处理权限标识
			        	startShowUserSjqx();
		    		}else{
		    			swal({ 
		   				 title: "是否将更改保存？", 
		   				  type: "warning",
		   				  showCancelButton: true, 
		   				  confirmButtonText: "确定", 
		   				  cancelButtonText: "取消",
		   				  closeOnConfirm: false, 
		   				  closeOnCancel: true	
		   				},
		   				  function(isConfirm){
		   					  if (isConfirm) { 
		   						dataauthoritymanage_usersjqx.execute_sjqx();
		   						$usersjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   			        	$usersjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   			        	$usersjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   			        	$usersjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   			        	$usersjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		   			        	currentsjqxtb = _curRow.getData().f_user_qxb;//当前数据权限存储表
		   			        	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   			        	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   			        	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   						startShowUserSjqx();
		   					  } else { 
		   						$usersjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   			        	$usersjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   			        	$usersjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   			        	$usersjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   			        	$usersjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		   			        	currentsjqxtb = _curRow.getData().f_user_qxb;//当前数据权限存储表
		   			        	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   			        	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   			        	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   			        	startShowUserSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$usersjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		        	$usersjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		        	$usersjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		        	$usersjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		        	$usersjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		        	currentsjqxtb = _curRow.getData().f_user_qxb;//当前数据权限存储表
		        	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		        	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		        	splitStr(f_qxbz,f_qxsm);//处理权限标识
		        	startShowUserSjqx();
        		}
        	}
    	},
	});
	//处理权限标识
	function splitStr(f_qxbz,f_qxsm){
		var qxbzStrs= new Array();
    	var qxsmStrs= new Array();
		qxbzStrs = f_qxbz.split("/"); //字符分割 
    	qxsmStrs = f_qxsm.split("/"); //字符分割 
    	columnName = [];
    	columnFiled = [];
    	var count = 0;
    	for (var i = 0; i < qxbzStrs.length; i++) {
			if(qxbzStrs[i] == "1"){
				columnFiled[count] = "f_g"+(i+1);
				columnName[count] = qxsmStrs[i];
				count++;
			}
		}
		
	};
	$(function (){
		$.ajax({
			type:"get",
			url:_ctx+'/view/usersjqx/user_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$usersjqxstatus[0] = result.list[0].f_yhbh;
			        	startShowUserSjqx();
						$("#esuser_showtable").tabulator("setData",result.list);
						var firstNode = $("#esuser_showtable").tabulator("getRows");
						$("#esuser_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
		$.ajax({
			type:"get",
			url:_ctx+'/view/usersjqx/sjqx_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$usersjqxstatus[1] = result.list[0].f_qxbh;//获取权限编号
			        	$usersjqxstatus[2] = result.list[0].f_tabn;//获取数据表名
			        	$usersjqxstatus[3] = result.list[0].f_bhzd;//主键
			        	$usersjqxstatus[4] = result.list[0].f_qxbz;//权限标志
			        	$usersjqxstatus[5] = result.list[0].f_mczd;//名称字段
						currentsjqxtb = result.list[0].f_user_qxb;
						var f_qxbz = result.list[0].f_qxbz;
						var f_qxsm = result.list[0].f_qxsm;
						splitStr(f_qxbz,f_qxsm);//处理权限标识
			        	startShowUserSjqx();
						$("#essjqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#essjqx_showtable").tabulator("getRows");
						$("#essjqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
	})
	
	
	//创建并设置table属性
	$("#esUserSjqxManageTable").tabulator({
		height:"100%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:false,
		tooltipsHeader: false,
		movableColumns:false,
		//pagination:"local",
		//paginationSize:7,
		columns:[
		{title:"序号",width:50,formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"用户编号", field:"f_yhbh",width:80,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"权限编号", field:"f_qxbh",width:80,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"数据编号", field:"f_sjbh",width:80,align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"数据名称", field:"f_sjmc",width:80,align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"权限标志", field:"f_qxbz",width:80,align:"center",sorter:"string",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row; 
    	},
	});
	
	$(window).resize(function(){
		$("#esUserSjqxManageTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间-数据权限字典
	$("#search_sjqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_usersjqx.search_sjqx_usersjqx();//触发该事件
					    } 
					  })
					});
	//触发搜索的回车时间-用户
	$("#search_user_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_usersjqx.search_user_usersjqx();//触发该事件
					    } 
					  })
					});
	return{
	//搜索——用户
	search_user_usersjqx:function(){
		var user_keywords = $("#search_user_Keywords").val();
		$("#esuser_showtable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+user_keywords);
	},
	//搜索——数据权限字典
	search_sjqx_usersjqx:function(){
		var sjqx_keywords = $("#search_sjqx_Keywords").val();
		$("#essjqx_showtable").tabulator("setData", _ctx+'/view/usersjqx/data_sjqx_search?keywords='+sjqx_keywords);
	},
	//全选
	user_transmitField:function(fg){
		
		var bool = $("#id_"+fg).is(':checked');
		var f_g = fg;
		var tabulator_data = $("#esUserSjqxManageTable").tabulator("getData");
		if(bool == true){
		for (var i = 0; i < tabulator_data.length; i++) {
			switch(f_g)
			{
			case "f_g0":
				tabulator_data[i].f_g0 = true;
			  break;
			case "f_g1":
				tabulator_data[i].f_g1 = true;
			  break;
			case "f_g2":
				tabulator_data[i].f_g2 = true;
			  break;
			case "f_g3":
				tabulator_data[i].f_g3 = true;
			  break;
			case "f_g4":
				tabulator_data[i].f_g4 = true;
			  break;
			case "f_g5":
				tabulator_data[i].f_g5 = true;
			  break;
			case "f_g6":
				tabulator_data[i].f_g6 = true;
			  break;
			case "f_g7":
				tabulator_data[i].f_g7 = true;
			  break;
			case "f_g8":
				tabulator_data[i].f_g8 = true;
			  break;
			default:
				break;
			}
		}
		$("#esUserSjqxManageTable").tabulator("setData", tabulator_data);
	}else{
		for (var i = 0; i < tabulator_data.length; i++) {
			switch(f_g)
			{
			case "f_g0":
				tabulator_data[i].f_g0 = false;
			  break;
			case "f_g1":
				tabulator_data[i].f_g1 = false;
			  break;
			case "f_g2":
				tabulator_data[i].f_g2 = false;
			  break;
			case "f_g3":
				tabulator_data[i].f_g3 = false;
			  break;
			case "f_g4":
				tabulator_data[i].f_g4 = false;
			  break;
			case "f_g5":
				tabulator_data[i].f_g5 = false;
			  break;
			case "f_g6":
				tabulator_data[i].f_g6 = false;
			  break;
			case "f_g7":
				tabulator_data[i].f_g7 = false;
			  break;
			case "f_g8":
				tabulator_data[i].f_g8 = false;
			  break;
			default:
				break;
			}
		}
		$("#esUserSjqxManageTable").tabulator("setData", tabulator_data);
		}
	},
	
	createTable:function(){
		var  aa = $("#esUserSjqxManageTable").tabulator("getData");
		$("#esUserSjqxManageTable").tabulator("clearData");
		var newColumns = [
						{title:"序号",formatter:"rownum",align:"center",frozen:false,headerSort:false},
						{title:"用户编号", field:"f_yhbh",align:"center", sorter:"string",editor:false,headerSort:false},
						{title:"权限编号", field:"f_qxbh",align:"right", sorter:"string",editor:false,headerSort:false},
						{title:"数据编号", field:"f_sjbh",align:"right",sorter:"string",editor:false,headerSort:false},
						{title:"权限标志", field:"f_qxbz",align:"center",sorter:"string",editor:false,headerSort:false},
						]
		$("#esUserSjqxManageTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列
		$("#esUserSjqxManageTable").tabulator("setData", aa);
	},
	
	//执行保存
	execute_sjqx:function(){
		$(".userbz[type='checkbox']").removeAttr("checked");
		var tabulator_data = $("#esUserSjqxManageTable").tabulator("getData");
		var newTabData = new Array();//创建数据权限数组
		for (var i = 0; i < tabulator_data.length; i++) {
			var initfg0 = "1";//默认初始f_g0变量值为1
			var temobj = tabulator_data[i];
			for (var k = 1; k < columnFiled.length+1; k++) {
				temobj["f_g"+k] = temobj["f_g"+k] == true ? '1' : '0';
			}
			for (var j = 1; j < columnFiled.length+1; j++) {
				if(temobj["f_g"+j] == "0"){//循环判断f_g1/2/...当值为0时;f_g0=0;停止循环
					initfg0 = "0";
					break;
				}
			}
			temobj["f_g0"] = initfg0;
			newTabData[i] = temobj;
		}
			 $.ajax({
				  url: _ctx + "/view/usersjqx/user_sjqx_add",
			      contentType: "application/json; charset=utf-8",
		          type: "post",
		          async: false,
		          data:JSON.stringify(newTabData),
		          beforeSend: function () { 
		 				showLoad();	             
		 		  },
			      success: function(data) {
				         if (data.status == '1') {
				           savesink = true;
				           swal({
					        	title : data.msg,// 展示的标题
					   			text : "",// 内容
					   			type: "success",
					   			showCloseButton : false, // 展示关闭按钮
					   			allowOutsideClick : false,
					   			showConfirmButton : false,
					   			timer: 1000
					   		});
				         } else{
				           swal( data.msg, "", "error");
				         }
				       },
				       complete: function () {
							hiddenLoad();
						},
				       error: function(data) {
				       	 swal( data.msg,"", "error");
				       }
				 });
			}
		}
 })(jQuery, window, document);
 </script>