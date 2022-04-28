<!-----内容区域---->
<!----
@description 用户组功能权限
@author gongfanfei
@createData:2018/04/25
@editdate:2018/09/06
@editdate:2018/09/27
---->

<!--用户组列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择用户组名称>>>
		</span>
	</div>
	<!-- 搜索框 -->
	<!-- <div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_groupGnqx_Keywords" name="search_groupGnqx_Keywords" placeholder="用户组编号、名称">
			  <button  onclick="funauthoritymanage_usergroupgnqx.search_group_groupgnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div> -->
	<div id="esgroup_gnqx_showtable" >
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a href="javascript:void(-1);" onclick="funauthoritymanage_usergroupgnqx.groupgnqx_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
   		</div>
   		<div class="information-model">
   			<div id="groupgnqx_mkid" style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="groupgnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_usergroupgnqx.groupgnqx_change_mk()">
		            </select>   
	     		</div>
         	</div>  
   		</div>
		<div id="esgroupgnzd_tree" style="width: 20%;height:96%;;overflow: auto;"></div>
	</div>
</div>



 <script type="text/javascript">
 ;
 var funauthoritymanage_usergroupgnqx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	
	var currentGroupbh = null;//当前选择用户组编号
	var currentMkid = null;//当前选择模块guid
	var beforeGroupbh = null;//跳转未保存前用户组编号
	var beforeMkid = null;//跳转未保存前模块guid
	var wholeCheckedGnbh = [];//treeview勾选信息

	//展现用户组功能权限树
	function startShowGroupGnqx(){
		var zbh = currentGroupbh;//用户组编号
		var guid = currentMkid;//模块编号
		if( zbh != null  && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/usergroupgnqx/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_zbh:zbh,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		           console.log(result);
		        	$('#esgroupgnzd_tree').treeview({
		                data: result.map[0],
		                highlightSelected: true,//是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		                 showIcon: false,
		                showCheckbox:true, 
		                hierarchicalCheck:true,//级联勾选 
		            });
		            var gouxuan = result.map[1];
		            if(gouxuan != undefined){
		            	//alert(2.1)
		         	wholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#esgroupgnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#esgroupgnzd_tree').treeview("checkNode", $checknode[0]);
		            	}
		            }else{
		            	//alert(2.2)
		            	wholeCheckedGnbh = [];//
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
	function group_gnqx_getCheck(){
		var checkedGnbh = $('#esgroupgnzd_tree').treeview('getChecked');
		var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "groupgnqx_root"){
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
	//创建并设置table属性-用户组列表
/* 	$("#esgroup_gnqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		movableColumns:false,
		resizableColumns:false,
		columns:[
			{title:"用户组名称", field:"f_zmc",sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	currentGroupbh = _curRow.getData().f_zbh;//获取当前选中的用户组编号 
        	
        	var issave = group_gnqx_validate();
        	if(issave == 0){//执行切换
        		beforeGroupbh = currentGroupbh;
        	}
        	
        	
    	},
	}); */
	
	//验证是否已保存功能权限
	function group_gnqx_validate(){
		var ifsetvalue = null;
		if(currentGroupbh!=null  &&  currentMkid!=null){
		var group_gnqx_change = group_gnqx_getCheck();//改变后的勾选值
		if(group_gnqx_change.length<1 && wholeCheckedGnbh.length<1){
			startShowGroupGnqx();//执行切换
			ifsetvalue=0;
		}
		else if(group_gnqx_change.length > 0  && wholeCheckedGnbh.length<1){
			group_sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0  &&  group_gnqx_change.length<1 ){
			group_sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0 &&  group_gnqx_change.length>0){
			if(wholeCheckedGnbh.length != group_gnqx_change.length){
				group_sweetAlertfun();//确认保存
				ifsetvalue = 1;
			}else{
				
				var tem="";
				for (var i = 0; i < wholeCheckedGnbh.length; i++) {
					tem = tem + ","+wholeCheckedGnbh[i];
				}
				var sink = true;//标记是否相等
				for (var i = 0; i < group_gnqx_change.length; i++) {
					if(wholeCheckedGnbh.indexOf(group_gnqx_change[i]) < 0 ){//不包含
						sink=false;
						break;
					}
				}
				if(sink==true){
					startShowGroupGnqx();
					ifsetvalue = 0;
				}else{
					group_sweetAlertfun();//确认保存
					ifsetvalue = 1;
				}
			}
		}
	}
		return ifsetvalue;
		
	}	
	
	
	function group_sweetAlertfun(){
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
						  funauthoritymanage_usergroupgnqx.groupgnqx_save(0);
					  } else { 
						  wholeCheckedGnbh = [];//清空全局变量存放勾选信息
					    //swal("取消成功！", "功能权限已还原","success"); 
					    startShowGroupGnqx();
					  }
	        });
			
			
	}
	//回车触发搜索用户组
	$("#search_groupGnqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  funauthoritymanage_usergroupgnqx.search_group_groupgnqx();//触发该事件
					    } 
					  })
					});

	return{

	//搜索用户组
	search_group_groupgnqx:function(){
		var group_keywords = $("#search_groupGnqx_Keywords").val();
		$("#esgroup_gnqx_showtable").tabulator("setData", _ctx+'/view/usergroup/user_group_treegrid?f_zmc='+group_keywords);
		currentGroupbh = null;//恢复默认为空
		beforeGroupbh = null;//恢复默认为空
    	//$("#esgroupgnzd_tree").empty(); //清空树
	},
	
	//加载用户组列表 
/* 	groupgnqx_loadGrouplist:function(){
		$.ajax({
			type:"get",
			url:_ctx+'/view/usergroupgnqx/usergroup_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						currentGroupbh = result.list[0].f_zbh;
						beforeGroupbh = result.list[0].f_zbh;
						startShowGroupGnqx();
						$("#esgroup_gnqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esgroup_gnqx_showtable").tabulator("getRows");
						$("#esgroup_gnqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
	},
	 */
	 groupgnqx_loadGrouplist:function(){
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
	            $('#esgroup_gnqx_showtable').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	               onNodeSelected: function (event, nodeData) {
	            	  $('#esgroup_gnqx_showtable').treeview('clearSearch');//清除搜索选中高亮
	            	  currentGroupbh = nodeData.id;
	  				  beforeGroupbh = nodeData.id;
	  				  startShowGroupGnqx();
	              
	                } 
	            });
	            currentGroupbh = result.list[0].id;
				beforeGroupbh = result.list[0].id;
				startShowGroupGnqx(); 
	        	$("#esgroup_gnqx_showtable").treeview('collapseAll');
	            var firstNode = $("#esgroup_gnqx_showtable").treeview('findNodes',[result.list[0].id,'id']);
	            $("#esgroup_gnqx_showtable").treeview('expandNode',firstNode);
	        	$("#esgroup_gnqx_showtable").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
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
	 },
	//加载模块下拉列表 
	groupgnqx_loadmkmclist:function(){
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
		    	startShowGroupGnqx();
		    	for(var i = 0;i < result.length; i++) {
		    		var x=document.getElementById("groupgnqx_mk_group");
		    		var obj=document.createElement("option");
		    		obj.value=result[i].mkmc;
		    		obj.text=result[i].mkmc;
		    		obj.id="groupgnqx"+result[i].guid;
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
	//select改变选择option
	groupgnqx_change_mk:function(){
		var mkbhtem = $("#groupgnqx_mk_group option:selected").attr("id");
		var mkbh = mkbhtem.substring(9);
		currentMkid = mkbh;
		
		var issave = group_gnqx_validate();
    	if(issave == 0){//执行切换
    		beforeMkid = currentMkid;
    	}
	},
	//保存
	groupgnqx_save:function(mark){//mark为标识：当mark为1时，为点击保存按钮操作；当mark为0时，为切换被动保存
		var guidTem = null;//模块编号
		var zbhTem = null;//用户组编号
		if(mark==0){
			zbhTem = beforeGroupbh;
			guidTem = beforeMkid;		
		}else{
			zbhTem =  currentGroupbh;
			guidTem = currentMkid;
		}
	var finalResult = group_gnqx_getCheck();//得到勾选值
	 //初始勾选值和保存时勾选值作比较，执行删除+保存操作；
	 	 $.ajax({
       		     async : false,
      	         cache : false,
       	         type: "post",
       		     traditional: true,
       		     data:{   
       		    	 f_zbh:zbhTem,
       		    	 mkguid:guidTem,
       	       	  	 f_gnzds:finalResult,
       	       	  	 init_gnzds:wholeCheckedGnbh
       	         },
       		     url: _ctx + "/view/usergroupgnqx/usergroup_gnqx_add",
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
		 	 beforeGroupbh = currentGroupbh;
			 beforeMkid = currentMkid;
			 startShowGroupGnqx();
			 wholeCheckedGnbh = finalResult;
		},
	  
	
	pageInit : function(){          
		funauthoritymanage_usergroupgnqx.groupgnqx_loadmkmclist();//默认加载模块列表
		funauthoritymanage_usergroupgnqx.groupgnqx_loadGrouplist();//默认加载用户组列表
	}
	}
 })(jQuery, window, document);	
 funauthoritymanage_usergroupgnqx.pageInit();
 </script>