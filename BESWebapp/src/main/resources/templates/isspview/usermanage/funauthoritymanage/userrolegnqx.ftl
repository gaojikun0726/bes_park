<!----
@description 角色功能权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/09/06
---->


<!--角色列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择角色名称>>>
		</span>
	</div>
	<!-- 搜索框 -->
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_roleGnqx_Keywords" name="search_roleGnqx_Keywords" placeholder="角色编号、名称">
			  <button  onclick="funauthoritymanage_userrolegnqx.search_role_rolegnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esrole_gnqx_showtable" >
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a href="javascript:void(-1);" onclick="funauthoritymanage_userrolegnqx.rolegnqx_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
   		</div>
   		<div class="information-model">
   			<div id="rolegnqx_mkid" style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="rolegnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_userrolegnqx.rolegnqx_change_mk()">
		            </select>
	     		</div>
         	</div>  
   		</div>
		<div id="esrolegnzd_tree" style="width: 20%;height:92%;;overflow: auto;"></div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var funauthoritymanage_userrolegnqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var currentRolebh = null;//当前选择角色编号
	var currentMkid = null;//当前选择模块guid
	var beforeRolebh = null;//跳转未保存前角色编号
	var beforeMkid = null;//跳转未保存前模块guid
	var wholeCheckedGnbh = [];//treeview勾选信息
	
	//展现角色功能权限树
	function startShowRoleGnqx(){
		var rolebh = currentRolebh;//角色编号
		var guid = currentMkid;//模块编号
		if( rolebh != null && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/userrolegnqx/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_rolebh:rolebh,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		            $('#esrolegnzd_tree').treeview({
		                data: result.map[0],         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		                 showIcon: false,
		                showCheckbox:true, 
		                hierarchicalCheck:true,//级联勾选 
		            });
		           var gouxuan = result.map[1];
		            if(gouxuan != undefined){
		         	wholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#esrolegnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#esrolegnzd_tree').treeview("checkNode", $checknode[0]);
		            	}
		            }else{
		            	wholeCheckedGnbh = [];
		            }
		        },
		        complete: function () {
					hiddenLoad();
				},
		        error: function (nodeData) {
		            swal( nodeData.msg,"", "error");
		        }
		    });
		}
	}
	//勾选的值
	function gnqx_getCheck(){
		var checkedGnbh = $('#esrolegnzd_tree').treeview('getChecked');
		var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "rolegnqx_root"){
				pidArray.push(pid);
			}
				treeArray.push(nodetreeId);
		}
		 var result = [], hash = {};//定义新数组，去重pid->父功能编号集合
		    for (var i = 0, elem; (elem = pidArray[i]) != null; i++) {
		        if (!hash[elem]) {
		            result.push(elem);
		            hash[elem] = true;
		        }
		    }
		 for (var i = 0; i < result.length; i++) {
			 treeArray.push(result[i]);
		}
		 var finalResult = [], finalHash = {};//定义新数组，接收所有功能编号
		    for (var i = 0, elem; (elem = treeArray[i]) != null; i++) {
		        if (!finalHash[elem]) {
		        	finalResult.push(elem);
		            finalHash[elem] = true;
		        }
		    }
		 return finalResult;
	}
	
	//创建并设置table属性-角色列表
	$("#esrole_gnqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		resizableColumns:false,
		selectable:1,
		selectablePersistence:false,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		{title:"角色名称", field:"f_rolemc", sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	currentRolebh = _curRow.getData().f_rolebh;//获取当前选中角色编号
        	var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		beforeRolebh = currentRolebh;
        	}
		}	
	});
	//验证是否已保存功能权限
	 function gnqx_validate(){
		var ifsetvalue = null;
		if(currentRolebh!=null  &&  currentMkid!=null){
		var gnqx_change = gnqx_getCheck();//改变后的勾选值
		if(gnqx_change.length<1 && wholeCheckedGnbh.length<1){
			startShowRoleGnqx();//执行切换
			ifsetvalue=0;
		}
		else if(gnqx_change.length > 0  && wholeCheckedGnbh.length<1){
			sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0  &&  gnqx_change.length<1 ){
			sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0 &&  gnqx_change.length>0){
			if(wholeCheckedGnbh.length != gnqx_change.length){
				sweetAlertfun();//确认保存
				ifsetvalue = 1;
			}else{
				/* if(wholeCheckedGnbh.join() == gnqx_change.join()){
					startShowRoleGnqx();
					ifsetvalue = 0;
				}else{
					sweetAlertfun();//确认保存
					ifsetvalue = 1;
				} */
				var tem="";
				for (var i = 0; i < wholeCheckedGnbh.length; i++) {
					tem = tem + ","+wholeCheckedGnbh[i];
				}
				var sink = true;//标记是否相等
				for (var i = 0; i < gnqx_change.length; i++) {
					if(wholeCheckedGnbh.indexOf(gnqx_change[i]) < 0 ){//不包含
						sink=false;
						break;
					}
				}
				if(sink==true){
					startShowRoleGnqx();
					ifsetvalue = 0;
				}else{
					sweetAlertfun();//确认保存
					ifsetvalue = 1;
				}
			}
		}
	}
		return ifsetvalue;
} 
	function sweetAlertfun(){
			swal({ 
				 title: "是否将更改保存？", 
				 // text: "功能权限数据已做更改，建议保存！", 
				  type: "warning",
				  showCancelButton: true, 
				 // confirmButtonColor: "#DD6B55",
				  confirmButtonText: "确定", 
				  cancelButtonText: "取消",
				  closeOnConfirm: false, 
				  closeOnCancel: true	
			},
				  function(isConfirm){ 
					  if (isConfirm) { 
						  funauthoritymanage_userrolegnqx.rolegnqx_save(0);
					  } else { 
						  wholeCheckedGnbh = [];//清空全局变量存放勾选信息
					   	  startShowRoleGnqx();
					  }
	        });
			
			
	}
	//回车触发搜索角色
	$("#search_roleGnqx_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  funauthoritymanage_userrolegnqx.search_role_rolegnqx();//触发该事件
		    } 
	  })
	});
	return{
		//select改变选择option
		rolegnqx_change_mk:function(){
			var mkbhtem = $("#rolegnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			currentMkid = mkbh;
			
			var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		beforeMkid = currentMkid;
        	}
		},
		//搜索角色
		search_role_rolegnqx:function(){
			var role_keywords = $("#search_roleGnqx_Keywords").val();
			$("#esrole_gnqx_showtable").tabulator("setData", _ctx+'/view/userrole/user_role_search?keywords='+role_keywords);
			currentRolebh = null;//恢复默认为空
			beforeRolebh = null;//恢复默认为空
			//$("#esusergnzd_tree").empty();//清空树
		},
		//加载模块下拉列表 
		rolegnqx_loadmkmclist:function(){
			$.ajax({
		 	    url: _ctx + "/view/userrolegnqx/module_list",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	currentMkid = result[0].guid;
			    	beforeMkid = result[0].guid;
			    	startShowRoleGnqx();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("rolegnqx_mk_group");
			    		var obj=document.createElement("option");
			    		obj.value=result[i].mkmc;
			    		obj.text=result[i].mkmc;
			    		obj.id="rolegnqx"+result[i].guid;
			    		x.appendChild(obj);
			    	}
		        },
		        complete: function () {
					hiddenLoad();
				},
		        error: function(result) {
		          	  alert("获取模块列表失败！");
		        }
		   });
		},
		//加载角色列表 
		rolegnqx_loadRolelist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/userrolegnqx/userrole_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							currentRolebh = result.list[0].f_rolebh;
							beforeRolebh = result.list[0].f_rolebh;
							startShowRoleGnqx();
							$("#esrole_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#esrole_gnqx_showtable").tabulator("getRows");
							$("#esrole_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		//保存
		rolegnqx_save:function(mark){//mark为标识：当mark为1时，为点击保存按钮操作；当mark为0时，为切换被动保存
			var rolebhTem = null;//角色编号
			var guidTem = null;//模块编号
			if(mark==0){
				rolebhTem = beforeRolebh;
				guidTem = beforeMkid;		
			}else{
				rolebhTem = currentRolebh;
				guidTem = currentMkid;
			}
		var finalResult = gnqx_getCheck();//得到勾选值
	        		 $.ajax({
	        		     async : false,
        	             cache : false,
	        	         type: "post",
	        		     traditional: true,
	        		     data:{   
	        	        	 f_rolebh:rolebhTem,
	        	        	 mkguid:guidTem,
	        	       	  	 f_gnzds:finalResult,
	        	       	  	 init_gnzds:wholeCheckedGnbh
	        	         },
	        		     url: _ctx + "/view/userrolegnqx/role_gnqx_add",
	        	         beforeSend: function () { 
	        	 				showLoad();	             
	        	 			},
	        		      success: function(data) {
        			           swal({
        				        	title : data.msg,// 展示的标题
        				   			text : "",// 内容
        				   			type: "success",
        				   			showCloseButton : false, // 展示关闭按钮
        				   			allowOutsideClick : false,
        				   			showConfirmButton : false,
        				   			timer: 1000
        				   		});
        			       },
       			         complete: function () {
	        						hiddenLoad();
	        					},
	        			  error: function(data) {
	        			       	  swal( data.msg,"", "error");
	        			    }
       			     });
	        		 beforeRolebh = currentRolebh;
	        		 beforeMkid = currentMkid;
	        		 startShowRoleGnqx();
		    		 wholeCheckedGnbh = finalResult;
		},
		pageInit : function(){
			funauthoritymanage_userrolegnqx.rolegnqx_loadmkmclist();//默认加载模块列表
			funauthoritymanage_userrolegnqx.rolegnqx_loadRolelist();//默认加载角色列表
		}
	}
	
 })(jQuery, window, document);	
 funauthoritymanage_userrolegnqx.pageInit();
 </script>