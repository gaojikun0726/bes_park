<#----
@description 角色数据权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/11/24
---->

<#--角色列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择角色名称>>>
		</span>
	</div>
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_role_Keywords" name="search_role_Keywords" placeholder="角色编号、名称">
			  <button  onclick="dataauthoritymanage_rolesjqx.search_role_data()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esrole_showtable" ></div>
</div>
<#--数据字典列表  -->
<div class="leftarea information_left" style="margin-left: 3px;border-left: solid 1px #366c90;">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择数据权限名称>>>
		</span>
	</div>
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_rolesjqx_Keywords" name="search_rolesjqx_Keywords" placeholder="数据权限名称">
			  <button  onclick="dataauthoritymanage_rolesjqx.search_sjqx_rolesjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esRsjqx_showtable">
	</div>

</div>
    
<#-- 角色数据权限列表 -->
<div class="information_right twoSub">
   <div class="information_size">
   		<div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;角色数据权限列表>>>
			</span>
			<a href="javascript:void(-1);" id="addRoleSjqx" onclick="dataauthoritymanage_rolesjqx.execute_role_sjqx()" class="btn btn-primary toLeft"  >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
		</div>
		<div id="esRoleSjqxManageTable"></div>
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
 ;var dataauthoritymanage_rolesjqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var oldRoleData=null;//改变之前加载过来的表格数据
	var savesink = false;//保存标识，true表示以已完成保存操作，false表示未点击保存按钮
	
	var $rolesjqxstatus = new Array();
	$rolesjqxstatus[0] = null;//角色编码
	$rolesjqxstatus[1] = null;//数据权限编号
	$rolesjqxstatus[2] = null;//数据表名
	$rolesjqxstatus[3] = null;//主键
	$rolesjqxstatus[4] = null;//权限标志
	$rolesjqxstatus[5] = null;//名称字段
	
	var currentsjqxtb = "";//当前表
	var rolecolumnFiled= new Array(); //定义表格-列字段数组 
	var rolecolumnName= new Array(); //定义表格-列标题数组 
	//设置格式
	var optIconFunction = function(cell, formatterParams){
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-role '><button>全选</button></div>"
	};
	//展现角色数据权限列表
	function startShowRoleSjqx(){
		$(".rolebz[type='checkbox']").removeAttr("checked");
		var roleguid = $rolesjqxstatus[0];//角色编号
		var qxbh = $rolesjqxstatus[1];//权限编号
		var tabn = $rolesjqxstatus[2];//数据表名
		var bhzd = $rolesjqxstatus[3];//主键
		var qxbz = $rolesjqxstatus[4];//权限标志
		var mczd = $rolesjqxstatus[5];//名称字段
		if( roleguid != null && qxbh != null && tabn != null && bhzd != null){
			$("#esRoleSjqxManageTable").tabulator("clearData");
			var newColumns = [
							{title:"序号",width:80,formatter:"rownum",align:"center",frozen:false,headerSort:false},
							{title:"角色编号",width:120, field:"f_roleguid",align:"center", sorter:"string",editor:false,headerSort:false},
							{title:"权限编号", field:"f_qxbh",align:"center", sorter:"string",editor:false,headerSort:false},
							{title:"数据编号", field:"f_sjbh",align:"center",sorter:"string",editor:false,headerSort:false},
							{title:"数据名称", field:"f_sjmc",align:"left",sorter:"string",editor:false,headerSort:false},
							{title:"权限标志", field:"f_qxbz",align:"center",sorter:"string",editor:false,headerSort:false},
							//{title:"全部 <input id=\"roid_f_g0\" type='checkbox' class='rolebz' onclick=\"dataauthoritymanage_rolesjqx.role_transmitField('f_g0')\">", field:"f_g0",width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false},
							]
			for (var i = 0; i < rolecolumnFiled.length; i++) {
				newColumns[i+5] = {title:rolecolumnName[i]+"<input id='roid_"+rolecolumnFiled[i]+"' type='checkbox' style='margin-top: 1px' class='rolebz' onclick=\"dataauthoritymanage_rolesjqx.role_transmitField('"+rolecolumnFiled[i]+"')\">", field:rolecolumnFiled[i],width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false};
			}
			$("#esRoleSjqxManageTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列 */
			$.ajax({
        	    url: _ctx + "/view/rolesjqx/rolesjqx_list",
        	    contentType: "application/json; charset=utf-8",
        	    type: "get",
        	    data: {
        	    	f_roleguid:roleguid,
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
				            $("#esRoleSjqxManageTable").tabulator("setData", result.list);
						}else{
				            $("#esRoleSjqxManageTable").tabulator("setData", []);
						}
						oldRoleData = $("#esRoleSjqxManageTable").tabulator("getData");//作为原始数据
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
	
	//创建并设置table属性-角色列表
	$("#esrole_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		{title:"角色名称", field:"f_rolemc",sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var roleguid = _curRow.getData().f_rolebh;
        	if($rolesjqxstatus[0] != roleguid){
        		if(savesink == false){
	        		var bool = dealData($("#esRoleSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$rolesjqxstatus[0] = roleguid;
		    			startShowRoleSjqx();
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
		   						dataauthoritymanage_rolesjqx.execute_role_sjqx();
		   						$rolesjqxstatus[0] = roleguid;
		   						startShowRoleSjqx();
		   					  } else { 
		   						$rolesjqxstatus[0] = roleguid;
		   						startShowRoleSjqx();
		   					  }
		   	        	});
		    		}
        		}else{//已保存过了
        			$rolesjqxstatus[0] = roleguid;
	    			startShowRoleSjqx();
        		}
        		
        	}
    	},
	});
	//创建并设置table属性-数据权限列表
	$("#esRsjqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		//{title:"序号",field:"id",width:50,formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"权限名称", field:"f_qxmc",width:110, sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"表名", field:"f_tabn", sorter:"string",align:"center",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var qxbh = _curRow.getData().f_qxbh;
        	if($rolesjqxstatus[1] != qxbh){
        		if(savesink == false){
	        		var bool = dealData($("#esRoleSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$rolesjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		            	$rolesjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		            	$rolesjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		            	$rolesjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		            	$rolesjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		            	currentsjqxtb = _curRow.getData().f_role_qxb;//当前数据权限存储表
		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		            	startShowRoleSjqx();
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
		   						dataauthoritymanage_rolesjqx.execute_role_sjqx();
		   						$rolesjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$rolesjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$rolesjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$rolesjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$rolesjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		   		            	currentsjqxtb = _curRow.getData().f_role_qxb;//当前数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowRoleSjqx();
		   					  } else { 
		   						$rolesjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$rolesjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$rolesjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$rolesjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$rolesjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
		   		            	currentsjqxtb = _curRow.getData().f_role_qxb;//当前数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowRoleSjqx();
		   					  }
		   	        	});
		    		}
		    	}else{
		    		$rolesjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
	            	$rolesjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
	            	$rolesjqxstatus[3] = _curRow.getData().f_bhzd;//主键
	            	$rolesjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
	            	$rolesjqxstatus[5] = _curRow.getData().f_mczd;//名称字段
	            	currentsjqxtb = _curRow.getData().f_role_qxb;//当前数据权限存储表
	            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
	            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
	            	splitStr(f_qxbz,f_qxsm);//处理权限标识
	            	startShowRoleSjqx();
		    	}
        	}
    	},
	});
	//处理权限标识
	function splitStr(f_qxbz,f_qxsm){
		var qxbzStrs= new Array(); //定义一数组 
    	var qxsmStrs= new Array(); //定义一数组 
		qxbzStrs = f_qxbz.split("/"); //字符分割 
    	qxsmStrs = f_qxsm.split("/"); //字符分割 
    	rolecolumnName = [];
    	rolecolumnFiled = [];
    	var count = 0;
    	for (var i = 0; i < qxbzStrs.length; i++) {
			if(qxbzStrs[i] == "1"){
				rolecolumnFiled[count] = "f_g"+(i+1);
				rolecolumnName[count] = qxsmStrs[i];
				count++;
			}
		}
	};
	//操作验证,判断是否为保存后跳转
	function dealData(data){
		var bool = true;
		 for (var i = 0; i < data.length; i++) {
			var newobj = data[i];//改变之后的表格数据
			var oldobj = oldRoleData[i];//改变之前的表格数据
			for (var j = 0; j < rolecolumnFiled.length; j++) {
				if(newobj["f_g"+(j+1)] != oldobj["f_g"+(j+1)]){
					bool = false;
					break;
				}
				
			}
		}
		 return bool;
	}
	$(function (){
		$.ajax({
			type:"get",
			url:_ctx+'/view/rolesjqx/role_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$rolesjqxstatus[0] = result.list[0].f_rolebh;
						startShowRoleSjqx();
						$("#esrole_showtable").tabulator("setData",result.list);
						var firstNode = $("#esrole_showtable").tabulator("getRows");
						$("#esrole_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
		$.ajax({
			type:"get",
			url:_ctx+'/view/rolesjqx/sjqx_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$rolesjqxstatus[1] = result.list[0].f_qxbh;//获取权限编号
			        	$rolesjqxstatus[2] = result.list[0].f_tabn;//获取数据表名
			        	$rolesjqxstatus[3] = result.list[0].f_bhzd;//主键
			        	$rolesjqxstatus[4] = result.list[0].f_qxbz;//权限标志
			        	$rolesjqxstatus[5] = result.list[0].f_mczd;//名称字段
			        	currentsjqxtb = result.list[0].f_role_qxb;
						var f_qxbz = result.list[0].f_qxbz;
						var f_qxsm = result.list[0].f_qxsm;
						splitStr(f_qxbz,f_qxsm);//处理权限标识
			        	startShowRoleSjqx();
						$("#esRsjqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esRsjqx_showtable").tabulator("getRows");
						$("#esRsjqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
	})
	//创建并设置table属性
	$("#esRoleSjqxManageTable").tabulator({
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
		{title:"序号",width:80,formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"角色编号",width:120, field:"f_roleguid",width:90,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"权限编号", field:"f_qxbh",width:80,align:"right", sorter:"string",editor:false,headerSort:false},
		{title:"数据编号", field:"f_sjbh",width:80,align:"right",sorter:"string",editor:false,headerSort:false},
		{title:"数据名称", field:"f_sjmc",width:80,align:"right",sorter:"string",editor:false,headerSort:false},
		{title:"权限标志", field:"f_qxbz",width:80,align:"center",sorter:"string",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row; 
    	},
	});
	
	$(window).resize(function(){
		$("#esRoleSjqxManageTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间-数据权限字典
	$("#search_rolesjqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_rolesjqx.search_sjqx_rolesjqx();//触发该事件
					    } 
					  })
					});
	//触发搜索的回车时间-角色
	$("#search_role_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_rolesjqx.search_role_data();//触发该事件
					    } 
					  })
					});
	return{
		//搜索——角色
		search_role_data:function(){
			var role_keywords = $("#search_role_Keywords").val();
			$("#esrole_showtable").tabulator("setData", _ctx+'/view/userrole/user_role_search?keywords='+role_keywords);
		},
		//搜索——数据权限字典
		search_sjqx_rolesjqx:function(){
			var rolesjqx_keywords = $("#search_rolesjqx_Keywords").val();
			$("#esRsjqx_showtable").tabulator("setData", _ctx+'/view/rolesjqx/data_sjqx_search?keywords='+rolesjqx_keywords);
		},
		//全选
		role_transmitField:function(fg){
			var role_bool = $("#roid_"+fg).is(':checked');
			var role_fg = fg;
			var role_data = $("#esRoleSjqxManageTable").tabulator("getData");
			if(role_bool == true){
			for (var i = 0; i < role_data.length; i++) {
				switch(role_fg)
				{
				case "f_g0":
					role_data[i].f_g0 = true;
				  break;
				case "f_g1":
					role_data[i].f_g1 = true;
				  break;
				case "f_g2":
					role_data[i].f_g2 = true;
				  break;
				case "f_g3":
					role_data[i].f_g3 = true;
				  break;
				case "f_g4":
					role_data[i].f_g4 = true;
				  break;
				case "f_g5":
					role_data[i].f_g5 = true;
				  break;
				case "f_g6":
					role_data[i].f_g6 = true;
				  break;
				case "f_g7":
					role_data[i].f_g7 = true;
				  break;
				case "f_g8":
					role_data[i].f_g8 = true;
				  break;
				default:
					break;
				}
			}
			$("#esRoleSjqxManageTable").tabulator("setData", role_data);
		}else{
			for (var i = 0; i < role_data.length; i++) {
				switch(role_fg)
				{
				case "f_g0":
					role_data[i].f_g0 = false;
				  break;
				case "f_g1":
					role_data[i].f_g1 = false;
				  break;
				case "f_g2":
					role_data[i].f_g2 = false;
				  break;
				case "f_g3":
					role_data[i].f_g3 = false;
				  break;
				case "f_g4":
					role_data[i].f_g4 = false;
				  break;
				case "f_g5":
					role_data[i].f_g5 = false;
				  break;
				case "f_g6":
					role_data[i].f_g6 = false;
				  break;
				case "f_g7":
					role_data[i].f_g7 = false;
				  break;
				case "f_g8":
					role_data[i].f_g8 = false;
				  break;
				default:
					break;
				}
			}
			$("#esRoleSjqxManageTable").tabulator("setData", role_data);
			}
		},
		//执行保存
		execute_role_sjqx:function(){
			$(".rolebz[type='checkbox']").removeAttr("checked");
			var rotabulator_data = $("#esRoleSjqxManageTable").tabulator("getData");
			var newRoleTabData = new Array();//创建数据权限数组
			for (var i = 0; i < rotabulator_data.length; i++) {
				var initfg0 = "1";//默认初始f_g0变量值为1
				var temobj = rotabulator_data[i];
				for (var k = 1; k < rolecolumnFiled.length+1; k++) {
					temobj["f_g"+k] = temobj["f_g"+k] == true ? '1' : '0';
				}
				for (var j = 1; j < rolecolumnFiled.length+1; j++) {
					if(temobj["f_g"+j] == "0"){//循环判断f_g1/2/...当值为0时;f_g0=0;停止循环
						initfg0 = "0";
						break;
					}
				}
				temobj["f_g0"] = initfg0;
				newRoleTabData[i] = temobj;
			}
				$.ajax({
				      url: _ctx + "/view/rolesjqx/role_sjqx_add",
				      contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify(newRoleTabData),
			          async: false,
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
			},
	}
 })(jQuery, window, document);
 </script>