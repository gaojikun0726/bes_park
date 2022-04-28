<#----
@description 用户组数据权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/11/24
---->

<#--用户组列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择用户组名称>>>
		</span>
	</div>
<#-- 	<div class="information-model">
		
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_group_Keywords" name="search_group_Keywords" placeholder="用户组编号、名称">
			  <button  onclick="dataauthoritymanage_groupsjqx.search_group_data()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
		
		
	</div> -->
	<div id="esgroup_showtable" >
	</div>
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
			  <input type="text" class="find-style"  id="search_groupsjqx_Keywords" name="search_groupsjqx_Keywords" placeholder="数据权限名称">
			  <button  onclick="dataauthoritymanage_groupsjqx.search_sjqx_groupsjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
		
		
		
	</div>
	<div id="esGsjqx_showtable">
	</div>

</div>
    
<#-- 用户组数据权限列表 -->
<div class="information_right twoSub">
   <div class="information_size">
   		<div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;用户组数据权限列表>>>
			</span>
			<a href="javascript:void(-1);" id="addGroupSjqx" onclick="dataauthoritymanage_groupsjqx.execute_group_sjqx()" class="btn btn-primary toLeft"  >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
		</div>
		<div id="esGroupSjqxManageTable"></div>
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
 ;var dataauthoritymanage_groupsjqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var oldGroupData=null;//改变之前加载过来的表格数据
	var savesink = false;//保存标识，true表示以已完成保存操作，false表示未点击保存按钮
	var $groupsjqxstatus = new Array();
	$groupsjqxstatus[0] = null;//用户组编号
	$groupsjqxstatus[1] = null;//权限编号
	$groupsjqxstatus[2] = null;//数据表名
	$groupsjqxstatus[3] = null;//主键
	$groupsjqxstatus[4] = null;//权限标志
	$groupsjqxstatus[5] = null;//数据名称
	
	var currentsjqxtb = "";//当前表
	var groupcolumnFiled= new Array(); //定义表格-列字段数组 
	var groupcolumnName= new Array(); //定义表格-列标题数组
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-group '><button>全选</button></div>"
	};
	//展现用户数据权限列表
	function startShowGroupSjqx(){
		$(".groupbz[type='checkbox']").removeAttr("checked");
		var zbh = $groupsjqxstatus[0];//用户组编号
		var qxbh = $groupsjqxstatus[1];//权限编号
		var tabn = $groupsjqxstatus[2];//数据表名
		var bhzd = $groupsjqxstatus[3];//主键
		var qxbz = $groupsjqxstatus[4];//权限标志
		var mczd = $groupsjqxstatus[5];//数据名称
		if( zbh != null && qxbh != null && tabn != null && bhzd != null){
			$("#esGroupSjqxManageTable").tabulator("clearData");
			var newColumns = [
				{title:"序号",width:80,formatter:"rownum",align:"center",frozen:false,headerSort:false},
				{title:"用户组编号",width:120, field:"f_zbh",align:"center", sorter:"string",editor:false,headerSort:false},
				{title:"权限编号", field:"f_qxbh",align:"center", sorter:"string",editor:false,headerSort:false},
				{title:"数据编号", field:"f_sjbh",align:"center",sorter:"string",editor:false,headerSort:false},
				{title:"数据名称", field:"f_sjmc",align:"left",sorter:"string",editor:false,headerSort:false},
				{title:"权限标志", field:"f_qxbz",align:"center",sorter:"string",editor:false,headerSort:false},
				]
			for (var i = 0; i < groupcolumnFiled.length; i++) {
				newColumns[i+5] = {title:groupcolumnName[i]+"<input id='zbh_"+groupcolumnFiled[i]+"' type='checkbox' style='margin-top: 1px' class='rolebz' onclick=\"dataauthoritymanage_groupsjqx.group_transmitField('"+groupcolumnFiled[i]+"')\">", field:groupcolumnFiled[i],width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false};
			}
			$("#esGroupSjqxManageTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列 */
			//$("#esGroupSjqxManageTable").tabulator("setData", _ctx+'/view/groupsjqx/groupsjqx_list?f_zbh='+zbh+'&f_qxbh='+qxbh+'&f_tabn='+tabn+'&f_bhzd='+bhzd+'&f_qxbz='+qxbz+'&currentsjqxtb='+currentsjqxtb+'&mczd='+mczd);	
				$.ajax({
	        	    url: _ctx + "/view/groupsjqx/groupsjqx_list",
	        	    contentType: "application/json; charset=utf-8",
	        	    type: "get",
	        	    data: {
	        	    	f_zbh:zbh,
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
					            $("#esGroupSjqxManageTable").tabulator("setData", result.list);
							}else{
					            $("#esGroupSjqxManageTable").tabulator("setData", []);
							}
							oldGroupData = $("#esGroupSjqxManageTable").tabulator("getData");//作为原始数据
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
	
	//创建并设置table属性-用户列表
	$("#esgroup_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		//{title:"用户组编号", field:"f_zbh", sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"用户组名称", field:"f_zmc", sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var zbh = _curRow.getData().f_zbh;
        	if($groupsjqxstatus[0] != zbh){
        		if(savesink == false){
	        		var bool = dealData($("#esGroupSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$groupsjqxstatus[0] = zbh;
		    			startShowGroupSjqx();
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
		   						dataauthoritymanage_groupsjqx.execute_group_sjqx();
		   						$groupsjqxstatus[0] = zbh;
		   						startShowGroupSjqx();
		   					  } else { 
		   						$groupsjqxstatus[0] = zbh;
		   						startShowGroupSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$groupsjqxstatus[0] = zbh;
	    			startShowGroupSjqx();
        		}
        	}
    	},
	});
	//创建并设置table属性-数据权限列表
	$("#esGsjqx_showtable").tabulator({
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
        	if($groupsjqxstatus[1] != qxbh){
        		if(savesink == false){
	        		var bool = dealData($("#esRoleSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$groupsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		            	$groupsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		            	$groupsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		            	$groupsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		            	$groupsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		            	currentsjqxtb = _curRow.getData().f_yhz_qxb; //当前用户组数据权限存储表
		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		            	startShowGroupSjqx();
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
		   						dataauthoritymanage_groupsjqx.execute_group_sjqx();
		   						$groupsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$groupsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$groupsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$groupsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$groupsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		   		            	currentsjqxtb = _curRow.getData().f_yhz_qxb; //当前用户组数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowGroupSjqx();
		   					  } else { 
		   						$groupsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$groupsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$groupsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$groupsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$groupsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		   		            	currentsjqxtb = _curRow.getData().f_yhz_qxb; //当前用户组数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowGroupSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$groupsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
	            	$groupsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
	            	$groupsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
	            	$groupsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
	            	$groupsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
	            	currentsjqxtb = _curRow.getData().f_yhz_qxb; //当前用户组数据权限存储表
	            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
	            	var f_qxsm =  _curRow.getData().f_qxsm;//权限标志
	            	splitStr(f_qxbz,f_qxsm);//处理权限标识
	            	startShowGroupSjqx();
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
    	groupcolumnName = [];
    	groupcolumnFiled = [];
    	var count = 0;
    	for (var i = 0; i < qxbzStrs.length; i++) {
			if(qxbzStrs[i] == "1"){
				groupcolumnFiled[count] = "f_g"+(i+1);
				groupcolumnName[count] = qxsmStrs[i];
				count++;
			}
		}
	};
	//操作验证,判断是否为保存后跳转
	function dealData(data){
		var bool = true;
		 for (var i = 0; i < data.length; i++) {
			var newobj = data[i];//改变之后的表格数据
			var oldobj = oldGroupData[i];//改变之前的表格数据
			for (var j = 0; j < groupcolumnFiled.length; j++) {
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
		        type: "post",
		        url: _ctx + "/view/usergroup/ugroup_tree",
		        dataType: "json",
		        async:false,
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
	        	 //初始加载根节点
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
		            $('#esgroup_showtable').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
	 	               onNodeSelected: function (event, nodeData) {
	 	            	  $('#esgroup_showtable').treeview('clearSearch');//清除搜索选中高亮
	 	            	  $groupsjqxstatus[0] = nodeData.id;
	 	            	  startShowGroupSjqx();
		              
		                } 
		            });
		            $groupsjqxstatus[0] = result.list[0].id;
		            startShowGroupSjqx(); 
		        	$("#esgroup_showtable").treeview('collapseAll');
		            var firstNode = $("#esgroup_showtable").treeview('findNodes',[result.list[0].id,'id']);
		            $("#esgroup_showtable").treeview('expandNode',firstNode);
		        	$("#esgroup_showtable").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
	            	}
	              }
		        },
		        complete: function () {
					hiddenLoad();
				},
		        error: function (nodeData) {
		            swal( nodeData.msg,"", "error");
		        }
		    });
		$.ajax({
			type:"get",
			url:_ctx+'/view/groupsjqx/sjqx_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$groupsjqxstatus[1] = result.list[0].f_qxbh;//获取权限编号
			        	$groupsjqxstatus[2] = result.list[0].f_tabn;//获取数据表名
			        	$groupsjqxstatus[3] = result.list[0].f_bhzd;//主键
			        	$groupsjqxstatus[4] = result.list[0].f_qxbz;//权限标志
			        	$groupsjqxstatus[5] = result.list[0].f_mczd;//名称字段
			        	
			        	currentsjqxtb = result.list[0].f_role_qxb; //当前用户组数据权限存储表
			        	var f_qxbz =  result.list[0].f_qxbz;//权限标志
			        	var f_qxsm =  result.list[0].f_qxsm;//权限标志
			        	splitStr(f_qxbz,f_qxsm);//处理权限标识 
			        	startShowGroupSjqx();
						$("#esGsjqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esGsjqx_showtable").tabulator("getRows");
						$("#esGsjqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
	})
	//创建并设置table属性
	$("#esGroupSjqxManageTable").tabulator({
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
		{title:"用户组编号", field:"f_zbh",width:90,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"权限编号", field:"f_qxbh",width:80,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"数据编号", field:"f_sjbh",width:80,align:"center",sorter:"string",editor:false,headerSort:false},
		{title:"数据名称", field:"f_sjmc",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"权限标志", field:"f_qxbz",width:80,align:"center",sorter:"string",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row; 
    	},
	});

	$(window).resize(function(){
		$("#esGroupSjqxManageTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间-数据权限字典
	$("#search_groupsjqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_groupsjqx.search_sjqx_groupsjqx();//触发该事件
					    } 
					  })
					});
	//触发搜索的回车时间-用户组
	$("#search_group_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_groupsjqx.search_group_data();//触发该事件
					    } 
					  })
					});
	return{
		//搜索——用户组
		search_group_data:function(){
			var group_keywords = $("#search_group_Keywords").val();
			$("#esgroup_showtable").tabulator("setData", _ctx+'/view/usergroup/user_group_treegrid?f_zmc='+group_keywords);
		},
		//搜索——数据权限字典
		search_sjqx_groupsjqx:function(){
			var groupsjqx_keywords = $("#search_groupsjqx_Keywords").val();
			$("#esGsjqx_showtable").tabulator("setData", _ctx+'/view/groupsjqx/data_sjqx_search?keywords='+groupsjqx_keywords);
		},
		//执行保存
		execute_group_sjqx:function(){
			$(".groupbz[type='checkbox']").removeAttr("checked");
			var gptabulator_data = $("#esGroupSjqxManageTable").tabulator("getData");
			var newGroupTabData = new Array();//创建数据权限数组
			for (var i = 0; i < gptabulator_data.length; i++) {
				var initfg0 = "1";//默认初始f_g0变量值为1
				var temobj = gptabulator_data[i];
				for (var k = 1; k < groupcolumnFiled.length+1; k++) {
					temobj["f_g"+k] = temobj["f_g"+k] == true ? '1' : '0';
				}
				
				for (var j = 1; j < groupcolumnFiled.length+1; j++) {
					if(temobj["f_g"+j] == "0"){//循环判断f_g1/2/...当值为0时;f_g0=0;停止循环
						initfg0 = "0";
						break;
					}
				}
				temobj["f_g0"] = initfg0;
				newGroupTabData[i] = temobj;
			} 
				$.ajax({
				      url: _ctx + "/view/groupsjqx/group_sjqx_add",
				      contentType: "application/json; charset=utf-8",
			          type: "post",
			          async: false,
			          data:JSON.stringify(
			             newGroupTabData
			          ),
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
			//}
		},
		//全选
		group_transmitField:function(fg){
			var group_bool = $("#zbh_"+fg).is(':checked');
			var group_fg = fg;
			var group_data = $("#esGroupSjqxManageTable").tabulator("getData");
			if(group_bool == true){
			for (var i = 0; i < group_data.length; i++) {
				switch(group_fg)
				{
				case "f_g0":
					group_data[i].f_g0 = true;
				  break;
				case "f_g1":
					group_data[i].f_g1 = true;
				  break;
				case "f_g2":
					group_data[i].f_g2 = true;
				  break;
				case "f_g3":
					group_data[i].f_g3 = true;
				  break;
				case "f_g4":
					group_data[i].f_g4 = true;
				  break;
				case "f_g5":
					group_data[i].f_g5 = true;
				  break;
				case "f_g6":
					group_data[i].f_g6 = true;
				  break;
				case "f_g7":
					group_data[i].f_g7 = true;
				  break;
				case "f_g8":
					group_data[i].f_g8 = true;
				  break;
				default:
					break;
				}
			}
			$("#esGroupSjqxManageTable").tabulator("setData", group_data);
		}else{
			for (var i = 0; i < group_data.length; i++) {
				switch(group_fg)
				{
				case "f_g0":
					group_data[i].f_g0 = false;
				  break;
				case "f_g1":
					group_data[i].f_g1 = false;
				  break;
				case "f_g2":
					group_data[i].f_g2 = false;
				  break;
				case "f_g3":
					group_data[i].f_g3 = false;
				  break;
				case "f_g4":
					group_data[i].f_g4 = false;
				  break;
				case "f_g5":
					group_data[i].f_g5 = false;
				  break;
				case "f_g6":
					group_data[i].f_g6 = false;
				  break;
				case "f_g7":
					group_data[i].f_g7 = false;
				  break;
				case "f_g8":
					group_data[i].f_g8 = false;
				  break;
				default:
					break;
				}
			}
			$("#esGroupSjqxManageTable").tabulator("setData", group_data);
			}
		},
	}
 })(jQuery, window, document);

	
 </script>