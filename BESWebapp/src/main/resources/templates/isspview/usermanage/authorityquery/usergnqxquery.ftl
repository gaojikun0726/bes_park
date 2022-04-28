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
		#query_esusergnzd_table>thead>tr>th{
			text-align: left !important;
		}
		.bootstrap-table{
			height: 100%;
    		overflow-y: auto;
		}
</style>
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
			  <input type="text" class="find-style"  id="query_search_gnqx_user_Keywords" name="query_search_gnqx_user_Keywords" placeholder="用户编号、名称">
			  <button  onclick="authorityquery_usergnqxquery.search_usergnqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
		<div id="query_esuser_gnqx_showtable" ></div>
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
			<div id="query_esusergnzd_table"style="width:100%;height:92% ! important;;overflow: auto;"></div>
   		</div>
	</div>
</div>


 <script type="text/javascript">
 ;
 var authorityquery_usergnqxquery = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var querycurrentYhbh = null;//当前选择用户编号 
	var datal;//定义全局变量接收后台返回的功能权限值
	
	//创建并设置table属性-用户列表
	$("#query_esuser_gnqx_showtable").tabulator({
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
        	_curRow = row;
        	querycurrentYhbh = _curRow.getData().f_yhbh;//获取当前选中用户编号
        	$("#query_esusergnzd_table").bootstrapTable('destroy');
        	//选择用户名称填充功能字典    
        	loadGnzdData();
        	loadGnzd_tabulator();
        	
		}	
	});
	
	$(function() {// 初始化内容
		$.ajax({
			type:"get",
			url:_ctx+'/view/usergnqxsh/user_list',
			dataType:"json",
			async: false,
			success:function(result){
				if(result.hasOwnProperty("list")){
					if(result.list.length>0){
						querycurrentYhbh = result.list[0].f_yhbh;
						loadGnzdData();
						$("#query_esuser_gnqx_showtable").tabulator("setData",result.list);
						var firstNode = $("#query_esuser_gnqx_showtable").tabulator("getRows");
						$("#query_esuser_gnqx_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});	
		
		
    }); 
	
	
		function loadGnzd_tabulator(){
			$('#query_esusergnzd_table').bootstrapTable({
				//url:_ctx+'/view/usergnqxquery/queryGnzdData?f_yhbh='+querycurrentYhbh,
				//method: 'post',
				data:datal,
				sidePagination: '',
				pagination: false,
		        treeView: true,
		        treeId: "f_gnbh",//节点id 
		        treeField: "f_gnmc",//节点名称 
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
	
	//回车触发搜索用户
	$("#query_search_gnqx_user_Keywords").focus(function(){
		  $(this).keydown(function (e){
		  if(e.which == "13"){
			  authorityquery_usergnqxquery.search_usergnqx();//触发该事件
		    } 
	  })
	});
	
	function loadGnzdData(){
		$.ajax({
			url : _ctx + "/view/usergnqxquery/queryGnzdData",
			type : "post",
			async: false,
			data : {
				f_yhbh : querycurrentYhbh,
				},
			beforeSend: function () {
				showLoad();
			},
			success : function(result) {
				
				if(result.hasOwnProperty("list")){
		           datal = result.list
				}
				loadGnzd_tabulator();
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(data) {
				swal(data.msg, "", "error");
			},
		});
		
	}
	
	
	
	return{
		//搜索用户
		search_usergnqx:function(){
			var user_keywords = $("#query_search_gnqx_user_Keywords").val();
			$("#query_esuser_gnqx_showtable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+user_keywords);
		},
	}
		
 })(jQuery, window, document);	
 </script>