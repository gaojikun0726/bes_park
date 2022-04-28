<!-----用户功能权限审核---->


<!--用户列表  -->
<div class="leftarea information_left" >
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择用户名称>>>
		</span>
	</div>
	<!-- 搜索框 -->
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="sh_search_gnqx_user_Keywords" name="sh_search_gnqx_user_Keywords" placeholder="用户编号、名称">
			  <button  onclick="authoritymanage_usergnqxsh.search_user_usersjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="sh_esuser_gnqx_showtable" ></div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a  href="javascript:void(-1);" onclick="authoritymanage_usergnqxsh.usergnqxsh_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;审核 
        	</a>
   		</div>
   		<div class="information-model">
   			<div id="sh_usergnqx_mkid" style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="sh_usergnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="authoritymanage_usergnqxsh.usergnqx_change_mk()">
		            </select>
	     		</div>
         	</div>  
   		</div>
		<div id="sh_esusergnzd_tree"style="width: 20%;height:92%;;overflow: auto;"></div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var authoritymanage_usergnqxsh = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var shcurrentYhbh = null;//当前选择用户编号
	var shcurrentMkid = null;//当前选择模块guid
	var shbeforeYhbh = null;//跳转未审核前用户编号
	var shbeforeMkid = null;//跳转未审核前模块guid
	var shwholeCheckedGnbh = [];//treeview勾选信息
	
	//展现用户功能权限树
	function startShowUserGnqxSh(){
		var yhbh = shcurrentYhbh;//用户编号
		var guid = shcurrentMkid;//模块编号
		if( yhbh != null && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/usergnqxsh/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_yhbh:yhbh,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		            $('#sh_esusergnzd_tree').treeview({
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
		         	shwholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#sh_esusergnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#sh_esusergnzd_tree').treeview("checkNode", $checknode[0]);
		            	}
		            }else{
		            	shwholeCheckedGnbh = [];
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
		var checkedGnbh = $('#sh_esusergnzd_tree').treeview('getChecked');
		var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "usergnqxsh_root"){
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
	
	//创建并设置table属性-用户列表
	$("#sh_esuser_gnqx_showtable").tabulator({
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
		{title:"用户名称", field:"f_name", sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	shcurrentYhbh = _curRow.getData().f_yhbh;//获取当前选中用户编号
        	var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		shbeforeYhbh = shcurrentYhbh;
        	}
		}	
	});
	//验证是否已审核功能权限
	 function gnqx_validate(){
		var ifsetvalue = null;
		if(shcurrentYhbh!=null  &&  shcurrentMkid!=null){
		var gnqx_change = gnqx_getCheck();//改变后的勾选值
		if(gnqx_change.length<1 && shwholeCheckedGnbh.length<1){
			startShowUserGnqxSh();//执行切换
			ifsetvalue=0;
		}
		else if(gnqx_change.length > 0  && shwholeCheckedGnbh.length<1){
			sweetAlertfun();//确认审核
			ifsetvalue = 1;
		}
		else if(shwholeCheckedGnbh.length>0  &&  gnqx_change.length<1 ){
			sweetAlertfun();//确认审核
			ifsetvalue = 1;
		}
		else if(shwholeCheckedGnbh.length>0 &&  gnqx_change.length>0){
			//少的部分
			var delstr=compare(gnqx_change,shwholeCheckedGnbh,0);//flag传1即arr1比arr2多的部分，0反之
			
			if(delstr.length>0){
				sweetAlertfun();//确认审核
				ifsetvalue = 1;
			}else{
				ifsetvalue = 0;	
				startShowUserGnqxSh();
			}
		}
	}
		return ifsetvalue;
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
	
	function sweetAlertfun(){
			swal({ 
				 title: "是否按照授权变动审核？", //是否将更改审核，并通过审核？
				  //text: "功能权限数据已做更改，建议审核！", 
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
						  authoritymanage_usergnqxsh.usergnqxsh_save(0);
					  } else { 
						  shwholeCheckedGnbh = [];//清空全局变量存放勾选信息
					   	  startShowUserGnqxSh();
					  }
	        });
			
			
	}
	//回车触发搜索用户
	$("#sh_search_gnqx_user_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  authoritymanage_usergnqxsh.search_user_usersjqx();//触发该事件
		    } 
	  })
	});
	return{
		//搜索用户
		search_user_usersjqx:function(){
			var user_keywords = $("#sh_search_gnqx_user_Keywords").val();
			$("#sh_esuser_gnqx_showtable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+user_keywords);
			shcurrentYhbh = null;//恢复默认为空
			shbeforeYhbh = null;//恢复默认为空
			//$("#esusergnzd_tree").empty();//清空树
		},
		//select改变选择option
		usergnqx_change_mk:function(){
			var mkbhtem = $("#sh_usergnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			shcurrentMkid = mkbh;
			
			var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		shbeforeMkid = shcurrentMkid;
        	}
		},
		//加载模块下拉列表 
		usergnqxsh_loadmkmclist:function(){
			$.ajax({
		 	    url: _ctx + "/view/usergnqxsh/module_list",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	shcurrentMkid = result[0].guid;
			    	shbeforeMkid = result[0].guid;
			    	startShowUserGnqxSh();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("sh_usergnqx_mk_group");
			    		var obj=document.createElement("option");
			    		obj.value=result[i].mkmc;
			    		obj.text=result[i].mkmc;
			    		obj.id="usergnqx"+result[i].guid;
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
		//加载用户列表 
		usergnqxsh_loadUsershlist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/usergnqxsh/user_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							shcurrentYhbh = result.list[0].f_yhbh;
							shbeforeYhbh = result.list[0].f_yhbh;
							//$beforesave[0] = result.list[0].f_yhbh;
							startShowUserGnqxSh();
							$("#sh_esuser_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#sh_esuser_gnqx_showtable").tabulator("getRows");
							$("#sh_esuser_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		//审核
		usergnqxsh_save:function(mark){//mark为标识：当mark为1时，为点击审核按钮操作；当mark为0时，为切换被动审核
			var yhbhTem = null;//用户编号
			var guidTem = null;//模块编号
			if(mark==0){
				yhbhTem = shbeforeYhbh;
				guidTem = shbeforeMkid;		
			}else{
				yhbhTem = shcurrentYhbh;
				guidTem = shcurrentMkid;
			}
		var finalResult = gnqx_getCheck();//得到勾选值
		//少的部分
		var delstr=compare(finalResult,shwholeCheckedGnbh,0);//flag传1即arr1比arr2多的部分，0反之
		var result = shwholeCheckedGnbh.filter(function(item1) {
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
	        	        	 f_yhbh:yhbhTem,
	        	        	 mkguid:guidTem,
	        	       	  	 f_gnzds:result,
	        	       	  	 init_gnzds:shwholeCheckedGnbh
	        	         },
	        		     url: _ctx + "/view/usergnqxsh/user_gnqx_add",
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
	        		 shbeforeYhbh = shcurrentYhbh;
	        		 shbeforeMkid = shcurrentMkid;
	        		 startShowUserGnqxSh();
		    		 shwholeCheckedGnbh = gnqx_getCheck();
		},
		pageInit : function(){
			authoritymanage_usergnqxsh.usergnqxsh_loadmkmclist();//默认加载模块列表
			authoritymanage_usergnqxsh.usergnqxsh_loadUsershlist();//默认加载用户列表
		}
	}
	
 })(jQuery, window, document);	
 authoritymanage_usergnqxsh.pageInit();
 </script>