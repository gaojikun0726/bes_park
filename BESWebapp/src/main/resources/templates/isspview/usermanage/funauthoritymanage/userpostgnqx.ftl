<!-----内容区域---->

<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 65%;float: left;position: relative;padding-right: 15px;" >

		<div class="zc_search find">
			<div class="search_custom"style=" width: 30%;margin-left: 4px;float: left;">
			  <input type="text"  id="search_postGnqx_Keywords" name="search_postGnqx_Keywords" placeholder="岗位编号、名称">
			  <button  onclick="funauthoritymanage_userpostgnqx.search_post_usergnqx()"></button>
			</div>
		</div>
       	 <div class="zc_search find">
			<div class="search_custom"style=" width: 30%;float: left;margin-left: 4%;">
			  <input type="text"  id="search_postgnqx_k_Keywords" name="search_postgnqx_k_Keywords" placeholder="功能权限名称">
			  <button onclick="funauthoritymanage_userpostgnqx.search_gnqx_postgnqx()"></button>
			</div>
		</div>
			系统选择
         <div id="userpostgnqx_xtid" style="float:left;width:30%;">   
           <label style=" display:inline-block ;">系统</label>
		     <div style=" display:inline-block ;margin-left: 30px; ">
            	<select id="postgnqx_xt_group" class="selector" style="width: 15.5em;height: 2em;" onchange="funauthoritymanage_userpostgnqx.postgnqx_change_xt()">
            	</select>
     		</div>
         </div>  
            
    	</div>
        <div style="width: 5%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
			<span class="input-group-btn"  style="width: 60px;">
				 <a id="add_post_gnqx" href="javascript:void(-1);" onclick="funauthoritymanage_userpostgnqx.postgnqx_save(1)" class="btn btn-primary" >
                	保存 <i class="fa fa-floppy-o"></i>
            	</a>
            </span>
       	</div>
   </div>
 </div>

岗位列表 
<div class="leftarea"  style="margin-top:-10px;height:93%;width:20%;margin-left: 1%;border-radius:25px;-moz-border-radius:25px;padding-left:0;overflow:auto">
		<div id="espost_gnqx_showtable"></div>
</div>
功能权限字典列表 
<div class="leftarea"  style="margin-top:-10px;height:93%;width:20%;margin-left: 1%;border-radius:25px;-moz-border-radius:25px;padding-left:0;overflow:auto">
		<div id="esPostGnqxDataTable"></div>
</div>
模块列表 
<div class="leftarea"  style="margin-top:-10px;height:93%;width:20%;margin-left: 1%;border-radius:25px;-moz-border-radius:25px;padding-left:0;overflow:auto">
		<div id="postGnzdModuleTable""></div>
</div>
模块对应菜单树 
<div style=" float: right;height:93%;width:35%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;overflow: auto;">
   <div style="height:calc(90%)">
		<div id="espostgnzd_tree"></div>
	</div>
</div> -->




<!--岗位列表  -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择岗位名称>>>
		</span>
	</div>
	<!-- 搜索框 -->
	<div class="information-model">
		<div class="zc_search find" style="">
			  <input type="text" class="find-style"  id="search_postGnqx_Keywords" name="search_postGnqx_Keywords" placeholder="岗位编号、名称">
			  <button  onclick="funauthoritymanage_userpostgnqx.search_post_usergnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="espost_gnqx_showtable" >
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>                                
			<a href="javascript:void(-1);" onclick="funauthoritymanage_userpostgnqx.postgnqx_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;保存 
        	</a>
   		</div>
   		<div class="information-model">
   			<div id="postgnqx_mkid" style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="postgnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_userpostgnqx.postgnqx_change_mk()">
		            </select>   
	     		</div>
         	</div>  
   		</div>
		<div id="espostgnzd_tree" style="width: 20%;height:96%;;overflow: auto;"></div>
	</div>
</div>



 <script type="text/javascript">
 ;
 var funauthoritymanage_userpostgnqx =(function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	
	var currentPostbh = null;//当前选择岗位编号
	var currentMkid = null;//当前选择模块guid
	var beforePostbh = null;//跳转未保存前岗位编号
	var beforeMkid = null;//跳转未保存前模块guid
	var wholeCheckedGnbh = [];//treeview勾选信息
	
	//展现岗位功能权限树
	function startShowPostGnqx(){

		var gwguid = currentPostbh;//岗位编号
		var guid = currentMkid;//模块编号
		if( gwguid != null && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/userpostgnqx/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_gwguid:gwguid,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		            $('#espostgnzd_tree').treeview({
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
		            	wholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#espostgnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#espostgnzd_tree').treeview("checkNode", $checknode[0]);
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
	function post_gnqx_getCheck(){
		var checkedGnbh = $('#espostgnzd_tree').treeview('getChecked');
		var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "postgnqx_root"){
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
	
	//创建并设置table属性-岗位列表
	$("#espost_gnqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:1,
		movableColumns:false,
		resizableColumns:false,
		columns:[
		{title:"岗位名称", field:"f_gwmc", sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
			row.select();
        	_curRow = row;
        	currentPostbh = _curRow.getData().f_guid;
        	var issave = post_gnqx_validate();
        	if(issave == 0){//执行切换
        		beforePostbh = currentPostbh;
        	}
    	},
	});
	


	//验证是否已保存功能权限
	function post_gnqx_validate(){
		var ifsetvalue = null;
		if(currentPostbh!=null  &&  currentMkid!=null){
		var post_gnqx_change = post_gnqx_getCheck();//改变后的勾选值
		if(post_gnqx_change.length<1 && wholeCheckedGnbh.length<1){
			startShowPostGnqx();//执行切换
			ifsetvalue=0;
		}
		else if(post_gnqx_change.length > 0  && wholeCheckedGnbh.length<1){
			post_sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0  &&  post_gnqx_change.length<1 ){
			post_sweetAlertfun();//确认保存
			ifsetvalue = 1;
		}
		else if(wholeCheckedGnbh.length>0 &&  post_gnqx_change.length>0){
			if(wholeCheckedGnbh.length != post_gnqx_change.length){
				post_sweetAlertfun();//确认保存
				ifsetvalue = 1;
			}else{
				
				var tem="";
				for (var i = 0; i < wholeCheckedGnbh.length; i++) {
					tem = tem + ","+wholeCheckedGnbh[i];
				}
				var sink = true;//标记是否相等
				for (var i = 0; i < post_gnqx_change.length; i++) {
					if(wholeCheckedGnbh.indexOf(post_gnqx_change[i]) < 0 ){//不包含
						sink=false;
						break;
					}
				}
				if(sink==true){
					startShowPostGnqx();
					ifsetvalue = 0;
				}else{
					post_sweetAlertfun();//确认保存
					ifsetvalue = 1;
				}
			}
		}
	}
		return ifsetvalue;
	}	
		
	
	function post_sweetAlertfun(){
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
					  funauthoritymanage_userpostgnqx.postgnqx_save(0);
				  } else { 
					  wholeCheckedGnbh = [];//清空全局变量存放勾选信息
				      startShowPostGnqx();
				  }
       });
			
			
	}
	//触发回车事件搜索岗位
	$("#search_postGnqx_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  funauthoritymanage_userpostgnqx.search_post_usergnqx();//触发该事件
					    } 
					  })
					});
	
	return{
		//加载岗位列表 
		postgnqx_loadPostlist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/userpostgnqx/userpost_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							currentPostbh = result.list[0].f_guid;
							beforePostbh = result.list[0].f_guid;
							startShowPostGnqx();
							$("#espost_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#espost_gnqx_showtable").tabulator("getRows");
							$("#espost_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		  
		//加载模块下拉列表 
		postgnqx_loadmkmclist:function(){
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
			    	startShowPostGnqx();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("postgnqx_mk_group");
			    		var obj=document.createElement("option");
			    		obj.value=result[i].mkmc;
			    		obj.text=result[i].mkmc;
			    		obj.id="postgnqx"+result[i].guid;
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
		postgnqx_change_mk:function(){
			var mkbhtem = $("#postgnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			currentMkid = mkbh;
			
			var issave = post_gnqx_validate();
	    	if(issave == 0){//执行切换
	    		beforeMkid = currentMkid;
	    	}
		},
	
		//搜索岗位
		search_post_usergnqx:function(){
			var post_keywords = $("#search_postGnqx_Keywords").val();
			$("#espost_gnqx_showtable").tabulator("setData", _ctx+'/view/userpost/user_post_search?keywords='+post_keywords);
			currentPostbh = null;//恢复默认为空
			beforePostbh = null;//恢复默认为空
		},
		//保存
		postgnqx_save:function(mark){
			var gwguidTem = null;//岗位编号
			var guidTem = null;//模块编号
			if(mark==0){
				gwguidTem = beforePostbh;
				guidTem = beforeMkid;		
			}else{
				gwguidTem = currentPostbh;
				guidTem = currentMkid;
			}	
		var finalResult = post_gnqx_getCheck();//得到勾选值
		//初始勾选值和保存时勾选值作比较，执行删除+保存操作；
	 	 $.ajax({
      		     async : false,
     	         cache : false,
      	         type: "post",
      		     traditional: true,
      		     data:{   
      		    	 f_gwguid:gwguidTem,
      		    	 mkguid:guidTem,
      	       	  	 f_gnzds:finalResult,
      	       	  	 init_gnzds:wholeCheckedGnbh
      	         },
      		     url: _ctx + "/view/userpostgnqx/userpost_gnqx_add",
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
		 	 beforePostbh = currentPostbh;
			 beforeMkid = currentMkid;
			 startShowPostGnqx();
			 wholeCheckedGnbh = finalResult;
		 
		 
		},
		pageInit : function(){
			funauthoritymanage_userpostgnqx.postgnqx_loadPostlist();//默认加载岗位列表
			funauthoritymanage_userpostgnqx.postgnqx_loadmkmclist();//默认加载模块列表
		}
	}
 })(jQuery, window, document);
 
funauthoritymanage_userpostgnqx.pageInit();
	
 </script>