<!-----角色功能权限审核---->

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
			  <input type="text" class="find-style"  id="sh_search_roleGnqx_Keywords" name="sh_search_roleGnqx_Keywords" placeholder="角色编号、名称">
			  <button  onclick="funauthoritymanage_userrolegnqxsh.search_role_rolegnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="sh_esrole_gnqx_showtable" >
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a href="javascript:void(-1);" onclick="funauthoritymanage_userrolegnqxsh.rolegnqxsh_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;审核
        	</a>
   		</div>
   		<div class="information-model">
   			<div  style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="sh_rolegnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_userrolegnqxsh.rolegnqxsh_change_mk()">
		            </select>
	     		</div>
         	</div>  
   		</div>
		<div id="sh_esrolegnzd_tree" style="width: 20%;height:92%;;overflow: auto;"></div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var funauthoritymanage_userrolegnqxsh = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var rshCurrentRolebh = null;//当前选择角色编号
	var rshCurrentMkid = null;//当前选择模块guid
	var rshBeforeRolebh = null;//跳转未审核前角色编号
	var rshBeforeMkid = null;//跳转未审核前模块guid
	var rshWholeCheckedGnbh = [];//treeview勾选信息
	
	//展现角色功能权限树
	function startShowRoleGnqxSh(){
		var rolebh = rshCurrentRolebh;//角色编号
		var guid = rshCurrentMkid;//模块编号
		if( rolebh != null && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/userrolegnqxsh/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_rolebh:rolebh,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		            $('#sh_esrolegnzd_tree').treeview({
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
		         	rshWholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#sh_esrolegnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#sh_esrolegnzd_tree').treeview("checkNode", $checknode[0]);
		            	}
		            }else{
		            	rshWholeCheckedGnbh = [];
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
		var checkedGnbh = $('#sh_esrolegnzd_tree').treeview('getChecked');
		 var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "rolegnqxsh_root"){
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
	//比较数组
	function compare(a1,b1,flag){
		var a=d=a1;
		var b=e=b1;
		var c=[];
		var addstr=[];
		var dels=[];
		a.sort();
		b.sort();
		var i=0;
		var j=0;
		while(i<a.length&&j<b.length){
			if(a[i]<b[j]){
				c.push(a[i]);
				i++;
			}else if(b[j]<a[i]){
				c.push(b[j]);
				j++;
			}else{
				i++;
				j++;
			}
		}
		while(i<a.length){
			c.push(a[i]);
			i++;
		}
		while(j<b.length){
			c.push(b[j]);
			j++;
		}
		if(!flag){
		for(var i=0;i<c.length;i++){
			for(var j=0;j<e.length;j++){
			if(e[j]==c[i]){
				addstr.push(e[j]);
				}
			}
		}
		return addstr;
		}
		else{
		for (var i = 0; i < c.length; i++) {
	            for (var j = 0; j < d.length; j++) {
	                if (d[j] == c[i]) {
	                    dels.push(d[j]);
	                }
	            }
	        }
			
		return dels;
		}
	}

	//创建并设置table属性-角色列表
	$("#sh_esrole_gnqx_showtable").tabulator({
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
        	rshCurrentRolebh = _curRow.getData().f_rolebh;//获取当前选中角色编号
        	var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		rshBeforeRolebh = rshCurrentRolebh;
        	}
		}	
	});
	//验证是否已审核功能权限
	 function gnqx_validate(){
		var ifsetvalue = null;
		if(rshCurrentRolebh!=null  &&  rshCurrentMkid!=null){
		var gnqx_change = gnqx_getCheck();//改变后的勾选值
		if(gnqx_change.length<1 && rshWholeCheckedGnbh.length<1){
			startShowRoleGnqxSh();//执行切换
			ifsetvalue=0;
		}
		else if(gnqx_change.length > 0  && rshWholeCheckedGnbh.length<1){
			sweetAlertfun();//确认审核
			ifsetvalue = 1;
		}
		else if(rshWholeCheckedGnbh.length>0  &&  gnqx_change.length<1 ){
			sweetAlertfun();//确认审核
			ifsetvalue = 1;
		}
		else if(rshWholeCheckedGnbh.length>0 &&  gnqx_change.length>0){
				//少的部分
				var delstr=compare(gnqx_change,rshWholeCheckedGnbh,0);//flag传1即arr1比arr2多的部分，0反之
				
				if(delstr.length>0){
					sweetAlertfun();//确认审核
					ifsetvalue = 1;
				}else{
					ifsetvalue = 0;	
					startShowRoleGnqxSh();
				}
			}
		}
		return ifsetvalue;
	} 
	
	function sweetAlertfun(){
			swal({ 
				 title: "是否按照授权变动审核？", //是否将更改审核？
				 // text: "功能权限数据已做更改，建议审核！", 
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
						  funauthoritymanage_userrolegnqxsh.rolegnqxsh_save(0);
					  } else { 
						  rshWholeCheckedGnbh = [];//清空全局变量存放勾选信息
					   	  startShowRoleGnqxSh();
					  }
	        });
			
			
	}
	//回车触发搜索角色
	$("#sh_search_roleGnqx_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  funauthoritymanage_userrolegnqxsh.search_role_rolegnqx();//触发该事件
		    } 
	  })
	});
	return{
		//select改变选择option
		rolegnqxsh_change_mk:function(){
			var mkbhtem = $("#sh_rolegnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			rshCurrentMkid = mkbh;
			
			var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		rshBeforeMkid = rshCurrentMkid;
        	}
		},
		//搜索角色
		search_role_rolegnqx:function(){
			var role_keywords = $("#sh_search_roleGnqx_Keywords").val();
			$("#sh_esrole_gnqx_showtable").tabulator("setData", _ctx+'/view/userrole/user_role_search?keywords='+role_keywords);
			rshCurrentRolebh = null;//恢复默认为空
			rshBeforeRolebh = null;//恢复默认为空
			//$("#esusergnzd_tree").empty();//清空树
		},
		//加载模块下拉列表 
		rolegnqxsh_loadmkmclist:function(){
			$.ajax({
		 	    url: _ctx + "/view/userrolegnqxsh/module_list",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	rshCurrentMkid = result[0].guid;
			    	rshBeforeMkid = result[0].guid;
			    	startShowRoleGnqxSh();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("sh_rolegnqx_mk_group");
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
		rolegnqxsh_loadRoleshlist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/userrolegnqxsh/userrole_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							rshCurrentRolebh = result.list[0].f_rolebh;
							rshBeforeRolebh = result.list[0].f_rolebh;
							startShowRoleGnqxSh();
							$("#sh_esrole_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#sh_esrole_gnqx_showtable").tabulator("getRows");
							$("#sh_esrole_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		//审核
		rolegnqxsh_save:function(mark){//mark为标识：当mark为1时，为点击审核按钮操作；当mark为0时，为切换被动审核
			var rolebhTem = null;//角色编号
			var guidTem = null;//模块编号
			if(mark==0){
				rolebhTem = rshBeforeRolebh;
				guidTem = rshBeforeMkid;		
			}else{
				rolebhTem = rshCurrentRolebh;
				guidTem = rshCurrentMkid;
			}
			
		var finalResult = gnqx_getCheck();//得到勾选值
		//少的部分
		var delstr=compare(finalResult,rshWholeCheckedGnbh,0);//flag传1即arr1比arr2多的部分，0反之
		var result = rshWholeCheckedGnbh.filter(function(item1) {
		    return delstr.every(function(item2) {
		        return item2 !== item1
		    })
		});
		
	        		 $.ajax({
	        		     async : false,
        	             cache : false,
	        	         type: "post",
	        		     traditional: true,
	        		     data:{   
	        	        	 f_rolebh:rolebhTem,
	        	        	 mkguid:guidTem,
	        	       	  	 f_gnzds:result
	        	         },
	        		     url: _ctx + "/view/userrolegnqxsh/role_gnqx_add",
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
	        		 rshBeforeRolebh = rshCurrentRolebh;
	        		 rshBeforeMkid = rshCurrentMkid;
	        		 startShowRoleGnqxSh();
		    		 rshWholeCheckedGnbh = gnqx_getCheck();//得到勾选值
		},
		pageInit : function(){
			funauthoritymanage_userrolegnqxsh.rolegnqxsh_loadmkmclist();//默认加载模块列表
			funauthoritymanage_userrolegnqxsh.rolegnqxsh_loadRoleshlist();//默认加载角色列表
		}
	}
	
 })(jQuery, window, document);	
 funauthoritymanage_userrolegnqxsh.pageInit();
 </script>