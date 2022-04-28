<!-----岗位功能权限审核---->

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
			  <input type="text" class="find-style"  id="sh_search_postGnqx_Keywords" name="sh_search_postGnqx_Keywords" placeholder="岗位编号、名称">
			  <button  onclick="funauthoritymanage_userpostgnqxsh.search_post_postgnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="sh_espost_gnqx_showtable" >
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;请选择对应信息>>>
			</span>
			<a href="javascript:void(-1);" onclick="funauthoritymanage_userpostgnqxsh.postgnqxsh_save(1)" class="btn btn-primary toLeft" >
               	<i class="fa fa-floppy-o"></i>&nbsp;审核
        	</a>
   		</div>
   		<div class="information-model">
   			<div  style="float:left;margin-left: 1%;">   
           	 	<label style=" display:inline-block ;margin-left:0%;">模块</label>
		     	<div style=" display:inline-block ;margin-left: 8px; ">
		            <select id="sh_postgnqx_mk_group" class="selector" style="width: 21vh;height: 3vh;" onchange="funauthoritymanage_userpostgnqxsh.postgnqx_change_mk()">
		            </select>
	     		</div>
         	</div>  
   		</div>
		<div id="sh_espostgnzd_tree" style="width: 20%;height:96%;;overflow: auto;"></div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var funauthoritymanage_userpostgnqxsh = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	
	var rshCurrentPostbh = null;//当前选择岗位编号
	var rshCurrentMkid = null;//当前选择模块guid
	var rshBeforePostbh = null;//跳转未审核前岗位编号
	var rshBeforeMkid = null;//跳转未审核前模块guid 
	var rshWholeCheckedGnbh = [];//treeview勾选信息
	
	//展现岗位功能权限树
	function startShowPostGnqxSh(){
		var gwguid = rshCurrentPostbh;//岗位编号
		var guid = rshCurrentMkid;//模块编号
		if( gwguid != null  && guid != null){
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/userpostgnqxsh/gnzd_tree",
		        dataType: "json",
		        data:({
		        	f_gwguid:gwguid,
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		        	$('#sh_espostgnzd_tree').treeview({
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
		            rshWholeCheckedGnbh = gouxuan;//得到回显的勾选数据
		            for (var i = 0; i < gouxuan.length; i++) {
				         	var $checknode = $('#sh_espostgnzd_tree').treeview('findNodes', [gouxuan[i], 'nodeTreeId']); 
		                	$('#sh_espostgnzd_tree').treeview("checkNode", $checknode[0]);
		            	}
		            }else{
		            	rshWholeCheckedGnbh = [];//
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
		var checkedGnbh = $('#sh_espostgnzd_tree').treeview('getChecked');
		 var pidArray=[];
		var treeArray=[];
		for (var i = 0; i < checkedGnbh.length; i++) {
			var nodetreeId = checkedGnbh[i].nodeTreeId;
			var pid = checkedGnbh[i].pid;
			if(pid != "postgnqxsh_root"){
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

	//创建并设置table属性-用户组列表
	$("#sh_espost_gnqx_showtable").tabulator({
		height:"92%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
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
        	rshCurrentPostbh = _curRow.getData().f_guid;//获取当前选中的岗位编号 
        	
        	var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		rshBeforePostbh = rshCurrentPostbh;
        	}
        	
        	
    	},
	});
	//验证是否已审核功能权限
	 function gnqx_validate(){
		var ifsetvalue = null;
		if(rshCurrentPostbh!=null  &&  rshCurrentMkid!=null){
		var gnqx_change = gnqx_getCheck();//改变后的勾选值
		if(gnqx_change.length<1 && rshWholeCheckedGnbh.length<1){
			startShowPostGnqxSh();//执行切换
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
					startShowPostGnqxSh();
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
						  funauthoritymanage_userpostgnqxsh.postgnqxsh_save(0);
					  } else { 
						  rshWholeCheckedGnbh = [];//清空全局变量存放勾选信息
					   	  startShowPostGnqxSh();
					  }
	        });
			
			
	}
	//回车触发搜索角色
	$("#sh_search_postGnqx_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  funauthoritymanage_userpostgnqxsh.search_post_postgnqx();//触发该事件
		    } 
	  })
	});
	return{
		//select改变选择option
		postgnqx_change_mk:function(){
			var mkbhtem = $("#sh_postgnqx_mk_group option:selected").attr("id");
			var mkbh = mkbhtem.substring(8);
			rshCurrentMkid = mkbh;
			
			var issave = gnqx_validate();
        	if(issave == 0){//执行切换
        		rshBeforeMkid = rshCurrentMkid;
        	}
		},
		//搜索岗位 
		search_post_postgnqx:function(){
			var post_keywords = $("#sh_search_postGnqx_Keywords").val();
			$("#sh_espost_gnqx_showtable").tabulator("setData", _ctx+'/view/userpost/user_post_search?keywords='+post_keywords);
			rshCurrentPostbh = null;//恢复默认为空
			rshBeforePostbh = null;//恢复默认为空
		},
		//加载模块下拉列表 
		postgnqxsh_loadmkmclist:function(){
			$.ajax({
				url: _ctx + "/view/userrolegnqx/module_list",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	rshCurrentMkid = result[0].guid;
			    	rshBeforeMkid = result[0].guid;
			    	startShowPostGnqxSh();
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("sh_postgnqx_mk_group");
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
		
		
		
		//加载用户组列表 
		postgnqxsh_loadPostshlist:function(){
			$.ajax({
				type:"get",
				url:_ctx+'/view/userpostgnqx/userpost_list',
				dataType:"json",
				success:function(result){
					if(result.hasOwnProperty("list")){
						if(result.list.length>0){
							rshCurrentPostbh = result.list[0].f_guid;
							rshBeforePostbh = result.list[0].f_guid;
							startShowPostGnqxSh();
							$("#sh_espost_gnqx_showtable").tabulator("setData",result.list);
							var firstNode = $("#sh_espost_gnqx_showtable").tabulator("getRows");
							$("#sh_espost_gnqx_showtable").tabulator("selectRow",firstNode[0]);
						}
					}
				}
			});
		},
		
		
		//审核
		postgnqxsh_save:function(mark){//mark为标识：当mark为1时，为点击审核按钮操作；当mark为0时，为切换被动审核
			var postbhTem = null;//用户组编号
			var guidTem = null;//模块编号
			if(mark==0){
				postbhTem = rshBeforePostbh;
				guidTem = rshBeforeMkid;		
			}else{
				postbhTem = rshCurrentPostbh;
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
	        	        	 f_gwguid:postbhTem,
	        	        	 mkguid:guidTem,
	        	       	  	 f_gnzds:result
	        	         },
	        		     url: _ctx + "/view/userpostgnqxsh/post_gnqxsh_add",
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
	        		 rshBeforePostbh = rshCurrentPostbh;
	        		 rshBeforeMkid = rshCurrentMkid;
	        		 startShowPostGnqxSh();
		    		 rshWholeCheckedGnbh = gnqx_getCheck();//得到勾选值
		},
		pageInit : function(){
			funauthoritymanage_userpostgnqxsh.postgnqxsh_loadmkmclist();//默认加载模块列表
			funauthoritymanage_userpostgnqxsh.postgnqxsh_loadPostshlist(); //默认加载岗位列表
		}
	}
	
 })(jQuery, window, document);	
 funauthoritymanage_userpostgnqxsh.pageInit();
 </script>