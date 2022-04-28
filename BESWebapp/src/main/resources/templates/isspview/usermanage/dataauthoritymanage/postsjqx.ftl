<#----
@description 岗位数据权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/11/24
---->


<#--岗位列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择岗位名称>>>
		</span>
	</div>
	<div class="information-model">
		
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_post_Keywords" name="search_post_Keywords" placeholder="岗位编号、名称">
			  <button  onclick="dataauthoritymanage_postsjqx.search_post_data()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
		
		
	</div>
	<div id="espost_showtable" >
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
			  <input type="text" class="find-style"  id="search_postsjqx_Keywords" name="search_postsjqx_Keywords" placeholder="数据权限名称">
			  <button  onclick="dataauthoritymanage_postsjqx.search_sjqx_postsjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
		
		
		
	</div>
	<div id="esGwsjqx_showtable">
	</div>

</div>
    
<#-- 岗位数据权限列表 -->
<div class="information_right twoSub">
   <div class="information_size">
   		<div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;岗位数据权限列表>>>
			</span>
			<a href="javascript:void(-1);" id="addPostSjqx" onclick="dataauthoritymanage_postsjqx.execute_post_sjqx()" class="btn btn-primary toLeft"  >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
		</div>
		<div id="esPostSjqxManageTable"></div>
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
 ;var dataauthoritymanage_postsjqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var savesink = false;//保存标识，true表示以已完成保存操作，false表示未点击保存按钮
	var oldPostData=null;//改变之前加载过来的表格数据
	
	var $postsjqxstatus = new Array();
	$postsjqxstatus[0] = null;//岗位编号
	$postsjqxstatus[1] = null;//权限编号
	$postsjqxstatus[2] = null;//数据表名
	$postsjqxstatus[3] = null;//主键
	$postsjqxstatus[4] = null;//权限标志
	$postsjqxstatus[5] = null;//数据名称
	
	var currentsjqxtb = "";//当前表
	var postcolumnFiled= new Array(); //定义表格-列字段数组 
	var postcolumnName= new Array(); //定义表格-列标题数组
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-post '><button>全选</button></div>"
	};
	//展现岗位数据权限列表
	function startShowPostSjqx(){
		$(".postbz[type='checkbox']").removeAttr("checked");
		var gwguid = $postsjqxstatus[0];//岗位编号
		var qxbh = $postsjqxstatus[1];//权限编号
		var tabn = $postsjqxstatus[2];//数据表名
		var bhzd = $postsjqxstatus[3];//主键
		var qxbz = $postsjqxstatus[4];//权限标志
		var mczd = $postsjqxstatus[5];//数据名称
		if( gwguid != null && qxbh != null && tabn != null && bhzd != null){
			$("#esPostSjqxManageTable").tabulator("clearData");
			var newColumns = [
				{title:"序号",width:80,formatter:"rownum",align:"center",frozen:false,headerSort:false},
				{title:"岗位编号",width:120, field:"f_gwguid",align:"center", sorter:"string",editor:false,headerSort:false},
				{title:"权限编号", field:"f_qxbh",align:"center", sorter:"string",editor:false,headerSort:false},
				{title:"数据编号", field:"f_sjbh",align:"left",sorter:"string",editor:false,headerSort:false},
				{title:"数据名称", field:"f_sjmc",align:"left",sorter:"string",editor:false,headerSort:false},
				{title:"权限标志", field:"f_qxbz",align:"center",sorter:"string",editor:false,headerSort:false},
				]
			for (var i = 0; i < postcolumnFiled.length; i++) {
				newColumns[i+5] = {title:postcolumnName[i]+"<input id='gwid_"+postcolumnFiled[i]+"' type='checkbox' style='margin-top: 1px' class='rolebz' onclick=\"dataauthoritymanage_postsjqx.post_transmitField('"+postcolumnFiled[i]+"')\">", field:postcolumnFiled[i],width:80,formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false};
			}
			$("#esPostSjqxManageTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列 */
			$.ajax({
        	    url: _ctx + "/view/postsjqx/postsjqx_list",
        	    contentType: "application/json; charset=utf-8",
        	    type: "get",
        	    data: {
        	    	f_gwguid:gwguid,
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
				            $("#esPostSjqxManageTable").tabulator("setData", result.list);
						}else{
				            $("#esPostSjqxManageTable").tabulator("setData", []);
						}
						oldPostData = $("#esPostSjqxManageTable").tabulator("getData");//作为原始数据
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
	$("#espost_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
	//	{title:"岗位编号", field:"f_gwbh", sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"岗位名称", field:"f_gwmc",sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	var gwguid = _curRow.getData().f_guid;
        	if($postsjqxstatus[0] != gwguid){
        		
        		if(savesink == false){
	        		var bool = dealData($("#esRoleSjqxManageTable").tabulator("getData"));
	        		if(bool == true){
	        			$postsjqxstatus[0] = gwguid;
	        			startShowPostSjqx();
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
		   						dataauthoritymanage_postsjqx.execute_post_sjqx();
		   						$postsjqxstatus[0] = gwguid;
		   						startShowPostSjqx();
		   					  } else { 
		   						$postsjqxstatus[0] = gwguid;
		   						startShowPostSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$postsjqxstatus[0] = gwguid;
        			startShowPostSjqx();
        		}
        	}
    	},
	});
	//创建并设置table属性-数据权限列表
	$("#esGwsjqx_showtable").tabulator({
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
        	var qxbh = _curRow.getData().f_qxbh;
        	if($postsjqxstatus[1] != qxbh){
        		if(savesink == false){
	        		var bool = dealData($("#esRoleSjqxManageTable").tabulator("getData"));
		    		if(bool == true){
		    			$postsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		            	$postsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		            	$postsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		            	$postsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		            	$postsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		            	currentsjqxtb = _curRow.getData().f_gw_qxb; //当前岗位数据权限存储表
		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限说明
		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		            	startShowPostSjqx();
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
		   						dataauthoritymanage_postsjqx.execute_post_sjqx();
		   						$postsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$postsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$postsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$postsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$postsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		   		            	currentsjqxtb = _curRow.getData().f_gw_qxb; //当前岗位数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限说明
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowPostSjqx();
		   					  } else { 
		   						$postsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
		   		            	$postsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
		   		            	$postsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
		   		            	$postsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
		   		            	$postsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
		   		            	currentsjqxtb = _curRow.getData().f_gw_qxb; //当前岗位数据权限存储表
		   		            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
		   		            	var f_qxsm =  _curRow.getData().f_qxsm;//权限说明
		   		            	splitStr(f_qxbz,f_qxsm);//处理权限标识
		   		            	startShowPostSjqx();
		   					  }
		   	        	});
		    		}
        		}else{
        			$postsjqxstatus[1] = _curRow.getData().f_qxbh;//获取权限编号
	            	$postsjqxstatus[2] = _curRow.getData().f_tabn;//获取数据表名
	            	$postsjqxstatus[3] = _curRow.getData().f_bhzd;//主键
	            	$postsjqxstatus[4] = _curRow.getData().f_qxbz;//权限标志
	            	$postsjqxstatus[5] = _curRow.getData().f_mczd;//数据名称
	            	currentsjqxtb = _curRow.getData().f_gw_qxb; //当前岗位数据权限存储表
	            	var f_qxbz =  _curRow.getData().f_qxbz;//权限标志
	            	var f_qxsm =  _curRow.getData().f_qxsm;//权限说明
	            	splitStr(f_qxbz,f_qxsm);//处理权限标识
	            	startShowPostSjqx();
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
    	postcolumnName = [];
    	postcolumnFiled = [];
    	var count = 0;
    	for (var i = 0; i < qxbzStrs.length; i++) {
			if(qxbzStrs[i] == "1"){
				postcolumnFiled[count] = "f_g"+(i+1);
				postcolumnName[count] = qxsmStrs[i];
				count++;
			}
		}
	};
	//操作验证,判断是否为保存后跳转
	function dealData(data){
		var bool = true;
		 for (var i = 0; i < data.length; i++) {
			var newobj = data[i];//改变之后的表格数据
			var oldobj = oldPostData[i];//改变之前的表格数据
			for (var j = 0; j < postcolumnFiled.length; j++) {
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
			url:_ctx+'/view/postsjqx/post_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$postsjqxstatus[0] = result.list[0].f_guid;
						startShowPostSjqx();
						$("#espost_showtable").tabulator("setData",result.list);
						var firstNode = $("#espost_showtable").tabulator("getRows");
						$("#espost_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
		$.ajax({
			type:"get",
			url:_ctx+'/view/postsjqx/sjqx_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						$postsjqxstatus[1] = result.list[0].f_qxbh;//获取权限编号
			        	$postsjqxstatus[2] = result.list[0].f_tabn;//获取数据表名
			        	$postsjqxstatus[3] = result.list[0].f_bhzd;//主键
			        	$postsjqxstatus[4] = result.list[0].f_qxbz;//权限标志
			        	$postsjqxstatus[5] = result.list[0].f_mczd;//数据名称
			        	
			        	currentsjqxtb = result.list[0].f_role_qxb;
						var f_qxbz = result.list[0].f_qxbz;
						var f_qxsm = result.list[0].f_qxsm;
						splitStr(f_qxbz,f_qxsm);//处理权限标识
			        	
			        	startShowPostSjqx();
						$("#esGwsjqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esGwsjqx_showtable").tabulator("getRows");
						$("#esGwsjqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
	})
	//创建并设置table属性
	$("#esPostSjqxManageTable").tabulator({
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
		{title:"岗位Guid", field:"f_gwguid",width:90,align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"权限编号", field:"f_qxbh",width:80,align:"right", sorter:"string",editor:false,headerSort:false},
		{title:"数据编号", field:"f_sjbh",width:80,align:"right",sorter:"string",editor:false,headerSort:false},
		{title:"权限标志", field:"f_qxbz",width:80,align:"center",sorter:"string",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row; 
    	},
	});
	
	$(window).resize(function(){
		$("#esPostSjqxManageTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间-数据权限字典
	$("#search_postsjqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_postsjqx.search_sjqx_postsjqx();//触发该事件
					    } 
					  })
					});
	//触发搜索的回车时间-用户组
	$("#search_post_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_postsjqx.search_post_data();//触发该事件
					    } 
					  })
					});
	
	return{
		//搜索——数据权限字典
		search_sjqx_postsjqx:function(){
			var postsjqx_keywords = $("#search_postsjqx_Keywords").val();
			$("#esGwsjqx_showtable").tabulator("setData", _ctx+'/view/postsjqx/data_sjqx_search?keywords='+postsjqx_keywords);
		},
		//搜索——岗位
		search_post_data:function(){
			var post_keywords = $("#search_post_Keywords").val();
			$("#espost_showtable").tabulator("setData", _ctx+'/view/userpost/user_post_search?keywords='+post_keywords);
		},
		//全选
		post_transmitField:function(fg){
			var post_bool = $("#gwid_"+fg).is(':checked');
			var post_fg = fg;
			var post_data = $("#esPostSjqxManageTable").tabulator("getData");
			if(post_bool == true){
			for (var i = 0; i < post_data.length; i++) {
				switch(post_fg)
				{
				case "f_g0":
					post_data[i].f_g0 = true;
				  break;
				case "f_g1":
					post_data[i].f_g1 = true;
				  break;
				case "f_g2":
					post_data[i].f_g2 = true;
				  break;
				case "f_g3":
					post_data[i].f_g3 = true;
				  break;
				case "f_g4":
					post_data[i].f_g4 = true;
				  break;
				case "f_g5":
					post_data[i].f_g5 = true;
				  break;
				case "f_g6":
					post_data[i].f_g6 = true;
				  break;
				case "f_g7":
					post_data[i].f_g7 = true;
				  break;
				case "f_g8":
					post_data[i].f_g8 = true;
				  break;
				default:
					break;
				}
			}
			$("#esPostSjqxManageTable").tabulator("setData", post_data);
		}else{
			for (var i = 0; i < post_data.length; i++) {
				switch(post_fg)
				{
				case "f_g0":
					post_data[i].f_g0 = false;
				  break;
				case "f_g1":
					post_data[i].f_g1 = false;
				  break;
				case "f_g2":
					post_data[i].f_g2 = false;
				  break;
				case "f_g3":
					post_data[i].f_g3 = false;
				  break;
				case "f_g4":
					post_data[i].f_g4 = false;
				  break;
				case "f_g5":
					post_data[i].f_g5 = false;
				  break;
				case "f_g6":
					post_data[i].f_g6 = false;
				  break;
				case "f_g7":
					post_data[i].f_g7 = false;
				  break;
				case "f_g8":
					post_data[i].f_g8 = false;
				  break;
				default:
					break;
				}
			}
			$("#esPostSjqxManageTable").tabulator("setData", post_data);
			}
		},
		//执行保存
		execute_post_sjqx:function(){
			$(".postbz[type='checkbox']").removeAttr("checked");
			var gwtabulator_data = $("#esPostSjqxManageTable").tabulator("getData");
			var newPostTabData = new Array();//创建数据权限数组
			for (var i = 0; i < gwtabulator_data.length; i++) {
				var initfg0 = "1";//默认初始f_g0变量值为1
				var temobj = gwtabulator_data[i];
				for (var k = 1; k < postcolumnFiled.length+1; k++) {
					temobj["f_g"+k] = temobj["f_g"+k] == true ? '1' : '0';
				}
				for (var j = 1; j < postcolumnFiled.length+1; j++) {
					if(temobj["f_g"+j] == "0"){//循环判断f_g1/2/...当值为0时;f_g0=0;停止循环
						initfg0 = "0";
						break;
					}
				}
				temobj["f_g0"] = initfg0;
				newPostTabData[i] = temobj;
			}
				 $.ajax({
				      url: _ctx + "/view/postsjqx/post_sjqx_add",
				      contentType: "application/json; charset=utf-8",
			          type: "post",
			          async:false,
			          data:JSON.stringify(
			        	newPostTabData
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
			},
	}
 })(jQuery, window, document);
 </script>