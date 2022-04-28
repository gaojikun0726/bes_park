<!----
@description 用户功能权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/09/06
---->

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
			  <input type="text" class="find-style"  id="search_gnqx_user_Keywords" name="search_gnqx_user_Keywords" placeholder="用户编号、名称">
			  <button  onclick="funauthoritymanage_usergnqx.search_user_usersjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esuser_gnqx_showtable" ></div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a id="add_user_gnqx" href="javascript:void(-1);" onclick="funauthoritymanage_usergnqx.usergnqx_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
   		</div>
   		<div class="information-model">
   			<div id="usergnqx_mkid" style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="usergnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_usergnqx.usergnqx_change_mk()">
		            </select>
	     		</div>
         	</div>  
   		</div>
		<div id="esusergnzd_tree"style="width: 20%;height:92%;;overflow: auto;"></div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var funauthoritymanage_usergnqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var currentYhbh = null;//当前选择用户编号
	var currentMkid = null;//当前选择模块guid
	var beforeYhbh = null;//跳转未保存前用户编号
	var beforeMkid = null;//跳转未保存前模块guid
	var wholeCheckedGnbh = [];//treeview勾选信息
	
	
	
	//展现用户功能权限树
	function startShowUserGnqx(){
		var yhbh = currentYhbh;//用户编号
		var guid = currentMkid;//模块编号
		if( yhbh != null && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/usergnqx/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_yhbh:yhbh,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		            $('#esusergnzd_tree').treeview({
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
				         	var $checknode = $('#esusergnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#esusergnzd_tree').treeview("checkNode", $checknode[0]);
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
		var checkedGnbh = $('#esusergnzd_tree').treeview('getChecked');
		var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "usergnqx_root"){
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
	$("#esuser_gnqx_showtable").tabulator({
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
        	currentYhbh = _curRow.getData().f_yhbh;//获取当前选中用户编号
        	var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		beforeYhbh = currentYhbh;
        	}
		}	
	});
	//验证是否已保存功能权限
	 function gnqx_validate(){
		var ifsetvalue = null;
		if(currentYhbh!=null  &&  currentMkid!=null){
		var gnqx_change = gnqx_getCheck();//改变后的勾选值
		if(gnqx_change.length<1 && wholeCheckedGnbh.length<1){
			startShowUserGnqx();//执行切换
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
					startShowUserGnqx();
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
	function  bijiao(wholeCheckedGnbh){
		
	};
	function sweetAlertfun(){
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
						  funauthoritymanage_usergnqx.usergnqx_save(0);
					  } else { 
						  wholeCheckedGnbh = [];//清空全局变量存放勾选信息
					   	  startShowUserGnqx();
					  }
	        });
			
			
	}
	//回车触发搜索用户
	$("#search_gnqx_user_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  funauthoritymanage_usergnqx.search_user_usersjqx();//触发该事件
		    } 
	  })
	});
	return{
		//搜索用户
		search_user_usersjqx:function(){
			var user_keywords = $("#search_gnqx_user_Keywords").val();
			$("#esuser_gnqx_showtable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+user_keywords);
			currentYhbh = null;//恢复默认为空
			beforeYhbh = null;//恢复默认为空
			//$("#esusergnzd_tree").empty();//清空树
		},
		//select改变选择option
		usergnqx_change_mk:function(){

			var mkbhtem = $("#usergnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			currentMkid = mkbh;
			
			var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		beforeMkid = currentMkid;
        	}
		},
		//加载模块下拉列表 
		usergnqx_loadmkmclist:function(){
			$.ajax({
		 	    url: _ctx + "/view/usergnqx/module_list",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	currentMkid = result[0].guid;
			    	beforeMkid = result[0].guid;
			    	startShowUserGnqx();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("usergnqx_mk_group");
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
		usergnqx_loadUserlist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/usergnqx/user_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							currentYhbh = result.list[0].f_yhbh;
							beforeYhbh = result.list[0].f_yhbh;
							//$beforesave[0] = result.list[0].f_yhbh;
							startShowUserGnqx();
							$("#esuser_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#esuser_gnqx_showtable").tabulator("getRows");
							$("#esuser_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		//保存
		usergnqx_save:function(mark){//mark为标识：当mark为1时，为点击保存按钮操作；当mark为0时，为切换被动保存
			var yhbhTem = null;//用户编号
			var guidTem = null;//模块编号
			if(mark==0){
				yhbhTem = beforeYhbh;
				guidTem = beforeMkid;		
			}else{
				yhbhTem = currentYhbh;
				guidTem = currentMkid;
			}
		var finalResult = gnqx_getCheck();//得到勾选值
	        		 $.ajax({
	        		     async : false,
        	             cache : false,
	        	         type: "post",
	        		     traditional: true,
	        		     data:{   
	        	        	 f_yhbh:yhbhTem,
	        	        	 mkguid:guidTem,
	        	       	  	 f_gnzds:finalResult,
	        	       	  	 init_gnzds:wholeCheckedGnbh
	        	         },
	        		     url: _ctx + "/view/usergnqx/user_gnqx_add",
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
	        		 beforeYhbh = currentYhbh;
	        		 beforeMkid = currentMkid;
	        		 startShowUserGnqx();
		    		 wholeCheckedGnbh = finalResult;
		},
		pageInit : function(){
			funauthoritymanage_usergnqx.usergnqx_loadmkmclist();//默认加载模块列表
			funauthoritymanage_usergnqx.usergnqx_loadUserlist();//默认加载用户列表
		}
	}
	
 })(jQuery, window, document);	
 funauthoritymanage_usergnqx.pageInit();
 </script>