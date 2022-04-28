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
		#query_esuserpostgnzd_table>thead>tr>th{
			 text-align: left !important;
			}
		.bootstrap-table{
			 height: 100%;
	    	 overflow-y: auto;
			}
</style>
<!-----用户功能权限审核---->
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
			  <input type="text" class="find-style"  id="query_postGnqx_Keywords" name="query_postGnqx_Keywords" placeholder="岗位编号、名称">
			  <button  onclick="authorityquery_user_postgnqxquery.query_post_postgnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="esquerypost_gnqx_showtable" >
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
   		<div style="width:50%; overflow-y:auto;height: 96%;">
			<div id="query_esuserpostgnzd_table"style="width:100%;height:92% ! important;;overflow: auto;"></div>
	    </div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var authorityquery_user_postgnqxquery = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var currentPostbh = null;//当前选择岗位编号
	var data;//定义全局变量接收后台返回的功能权限值
	
	//创建并设置table属性-用户列表
	$("#esquerypost_gnqx_showtable").tabulator({
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
			{title:"岗位名称", field:"f_gwmc", sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
        	_curRow = row;  
        	currentPostbh = _curRow.getData().f_guid;//获取当前选中用户编号
        	$("#query_esuserpostgnzd_table").bootstrapTable('destroy');
        	//选择角色名称填充功能列表数据   
        	loadPostGnzdData();
        	loadPostGnzd_tabulator();
		}	
	});
	
	//创建并设置“获取电能参数数据”table属性
	function loadPostGnzd_tabulator(){
		$("#query_esuserpostgnzd_table").bootstrapTable({
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



	$(function() {// 初始化内容
		$.ajax({
			type:"get",
			url:_ctx+'/view/userpostgnqx/userpost_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						currentPostbh = result.list[0].f_guid;
						loadPostGnzdData();
						$("#esquerypost_gnqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#esquerypost_gnqx_showtable").tabulator("getRows");
						$("#esquerypost_gnqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		}); 
	
     }); 
	function loadPostGnzdData(){
		$.ajax({
			url : _ctx + "/view/userpostgnqxquery/queryPostGnzdData",
			type : "post",
			async:false,
			data : {
				f_gwguid : currentPostbh,
				},
			beforeSend: function () {
				showLoad();
			},
			success : function(result) {
				if(result.hasOwnProperty("list")){
			           data = result.list
				}
				loadPostGnzd_tabulator();
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
	$("#query_postGnqx_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  authorityquery_user_postgnqxquery.query_post_postgnqx();//触发该事件
		    } 
	  })
	});
	

	return{
		//搜索角色
		query_post_postgnqx:function(){
			var post_keywords = $("#query_postGnqx_Keywords").val();
			$("#esquerypost_gnqx_showtable").tabulator("setData", _ctx+'/view/userpost/user_post_search?keywords='+post_keywords);
		},
      
	}
		
	
	
 })(jQuery, window, document);	
 </script>