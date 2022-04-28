<style>
		.glyphicon-ok:before {
			  color:green;
			}
		.glyphicon-remove:before {
			  color:red;
			}
		tbody td {
			  text-align: left !important;
			  width:10%;
			}
		#query_esusergroupgnzd_table>thead>tr>th{
			 text-align: left !important;
			}
		.bootstrap-table{
			 height: 100%;
	    	 overflow-y: auto;
			}
</style> 
<!-----用户组功能权限审核---->
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
			  <input type="text" class="find-style"  id="query_groupGnqx_Keywords" name="query_groupGnqx_Keywords" placeholder="用户组编号、名称">
			  <button  onclick="authorityquery_user_groupgnqxquery.query_group_groupgnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div> -->
	<div id="esquerygroup_gnqx_showtable" class="Information_area">
	</div>
</div>
<!--模块对应菜单树  -->
<div class="information_right">
   
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;功能权限信息>>>
			</span>
   		</div>
   		<div style="width:50%; overflow-y:auto;height: 100%;">
			<div id="query_esusergroupgnzd_table"style="width:100%;height:92% ! important;;overflow: auto;"></div>
	    </div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var authorityquery_user_groupgnqxquery = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var currentGroupbh = null;//当前选择角色编号
	var data;//定义全局变量接收后台返回的功能权限值
	
	//创建并设置table属性-用户列表
/* 	$("#esquerygroup_gnqx_showtable").tabulator({
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
			{title:"用户组名称", field:"f_zmc",sorter:"string",align:"center",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	currentGroupbh = _curRow.getData().f_zbh;//获取当前选中用户组编号
        	$("#query_esusergroupgnzd_table").bootstrapTable('destroy');
        	//选择角色名称填充功能列表数据   
        	loadGroupGnzdData();
        	loadGroupGnzd_tabulator();
		}	
	}); */
	
	//创建并设置“获取电能参数数据”table属性
	function loadGroupGnzd_tabulator(){
		$("#query_esusergroupgnzd_table").bootstrapTable({
			data:data,
			sidePagination: '',
			pagination: false,
	        treeView: true,
	        treeId: "f_gnbh",
	        treeField: "f_gnmc",
	        parentId: "f_parentid", //父节点字段ID
			columns:[
				{title:"菜单名称", field:"f_gnmc", sorter:"string",editor:false,align:"center",headerSort:false},
				{title:"授权", field:"opt",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
					formatter :function(value, row, index) {
						var f_sq = '';//是否授权
						f_sq = row.f_sq;
						if (f_sq == '1') {
							return "<span class='glyphicon glyphicon-ok'></span>" 
						}else if (f_sq == '0'){
							return "<span class='glyphicon glyphicon-remove'></span>"
						}
					}
				},
				{title:"审核", field:"opt1",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
					formatter :function(value, row, index) {
						var f_sh = '';//是否审核
						f_sh = row.f_sh;
						if (f_sh == '1') {
							return "<span class='glyphicon glyphicon-ok'></span>" 
						}else if (f_sh == '0'){
							return "<span class='glyphicon glyphicon-remove'></span>"
						}
					}
				},
				
				{title:"是否审核", field:"f_sh", sorter:"string",editor:false,visible : false,align:"center",headerSort:false},
				{title:"是否授权", field:"f_sq", sorter:"string",editor:false,visible : false,align:"center",headerSort:false}, 
			
				
			],
		
	    	
		});
	}


	//加载树
	$(function () {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/usergroup/ugroup_tree",
	        dataType: "json",
	        beforeSend: function () { 
 				showLoad();	             
 			},
	        success: function (result) {
        	 //初始加载根节点
            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#esquerygroup_gnqx_showtable').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
 	               onNodeSelected: function (event, nodeData) {
 	            	  $('#esquerygroup_gnqx_showtable').treeview('clearSearch');//清除搜索选中高亮
 	            	  /* var _ugroupZbh = nodeData.id;
	               		var _ugroupJs = nodeData.level; */
	               		//console.log(nodeData);
	               		currentGroupbh = nodeData.id;
	               		$("#query_esusergroupgnzd_table").bootstrapTable('destroy');
	                	//选择角色名称填充功能列表数据   
	                	loadGroupGnzdData();
	                	loadGroupGnzd_tabulator();
	              
	                } 
	            });
	             currentGroupbh = result.list[0].id;
	            $("#query_esusergroupgnzd_table").bootstrapTable('destroy');
	            loadGroupGnzdData();
	            loadGroupGnzd_tabulator(); 
	        	$("#esquerygroup_gnqx_showtable").treeview('collapseAll');
	            var firstNode = $("#esquerygroup_gnqx_showtable").treeview('findNodes',[result.list[0].id,'id']);
	            $("#esquerygroup_gnqx_showtable").treeview('expandNode',firstNode);
	        	$("#esquerygroup_gnqx_showtable").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
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
	});
/* 	$(function() {// 初始化内容，加载用户组列表 
		$.ajax({
			type:"get", 
			url:_ctx+'/view/usergroupgnqx/usergroup_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						currentGroupbh = result.list[0].f_zbh;
						loadGroupGnzdData();
						$("#esquerygroup_gnqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esquerygroup_gnqx_showtable").tabulator("getRows");
						$("#esquerygroup_gnqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
     });  */
	function loadGroupGnzdData(){
    	 $.ajax({
			url : _ctx + "/view/usergroupgnqxquery/queryGroupGnzdData",
			type : "post",
			async:false,
			data : {
				f_zbh : currentGroupbh,
				},
			beforeSend: function () {
				showLoad();
			},
			success : function(result) {
				if(result.hasOwnProperty("list")){
			           data = result.list
				}
				loadGroupGnzd_tabulator();
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(data) {
				swal(data.msg, "", "error");
			},
		});
	}
	
	//回车触发搜索角色
	$("#query_groupGnqx_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  authorityquery_user_groupgnqxquery.query_group_groupgnqx();//触发该事件
		    } 
	  })
	});
	

	return{
		//搜索角色
		query_group_groupgnqx:function(){
			var group_keywords = $("#query_groupGnqx_Keywords").val();
			$("#esquerygroup_gnqx_showtable").tabulator("setData", _ctx+'/view/usergroup/user_group_search?keywords='+group_keywords);
		},
      
	}
		
	
	
 })(jQuery, window, document);	
 </script>