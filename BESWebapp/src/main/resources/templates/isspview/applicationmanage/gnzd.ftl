
<!-- ----------------------------------------------------------- -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择模块>>>
		</span>
	</div>
	<div class="information-model">
		<div style=" display:inline-block ;margin-left: 5px;">
		    	 模块:
	     		<input type="hidden" id="gnzd_module_list" class="form-control">
	            <select id="gnzd_module_group" class="selector" style="width: 19vh;height: 2.9vh;" onchange="applicationmanage_gnzd.change_modules()">
	            </select>
	     </div>
	</div>
	<div id="tree_gnzd_sys" class="Information_area"style="height: 92%;">
	</div>
</div>

<div class="information_right">
   <div class="information_size">
   		<div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;菜单定义>>>
			</span>
			<!-- 增加按钮 -->
			<a id="gnzd_add_id" href="javascript:void(-1);" onclick="applicationmanage_gnzd.gnzd_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
			</a>
		     <div style=" display:none ;margin-left: 30px; ">
				系统:
		     	<input type="hidden" id="gnzd_xt_list"  class="form-control">
		           <select  id="gnzd_xt_group" class="selector" style="width: 19vh;height: 2.9vh;background-color:rgb(216, 239, 255);color:#000000;" onchange="applicationmanage_gnzd.change_xt()" disabled="disabled">
		           </select>
		     </div>
     	</div>
		<div id="gnzd_sysTable"></div>
   </div>
</div>

<!---添加信息开始-----> 
<div class="modal fade" id="modal-addgnzd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加功能菜单</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addGnzd_form" name="add" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">功能名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="gnmc" name="gnmc" placeholder="请输入功能名称,3-6位中文字符" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单样式<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                        	<input type="text"  id="cssClass" name="cssClass" placeholder="请输入菜单样式"  class="form-control menuManageFont" 
                        	autocomplete="off" onclick="applicationmanage_gnzd.getFotntsAddOrEdit()">
                        </div>  
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">URL<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="url" name="url"  placeholder="请输入URL" class="form-control">
                        </div>
                    </div> 
                     <div class="form-group" id="add_pgnbh_div">
                        <label class="col-sm-3 control-label">父功能编号</label>
                        <div class="col-sm-8">
                        	<input type="text" id="pgnbh" name="pgnbh" readonly="readonly"  class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">区域全屏</label>
                        <div class="col-sm-8">
					     <select id="add_gnzd_fullScreenType" class="selector" style="width: 22.9em;height: 2.5em;margin-left: -6px;" onchange="">
            			 <option value="0">带导航区域全屏</option>
            			 <option value="1">内容区域全屏</option>
            			 <option value="2">只显示内容区域 </option>
            			 </select>
                        </div>
                    </div>
                    <div class="form-group" id="qljtiot_gnzd_hide">
                        <label class="col-sm-3 control-label">状态</label>
                        <div class="col-sm-8" style="margin-top: 5px;">
						    <input type="radio" name="add_gnzd_hide" value="0"  id="not_hide" checked="checked">
						    <label class="radio-label" for="not_hide" style="width:2vw;"> 启用 </label>
	                        <input type="radio" name="add_gnzd_hide" value="1" id="yes_hide"  style="margin-left:4vw;">
						    <label class="radio-label" for="yes_hide"> 停用 </label>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label class="col-sm-3 control-label">DIV/newpage</label>
                        <div class="col-sm-8" style="margin-top: 5px;">
	                        <input type="radio" name="add_gnzd_openType" value="0" id="yes_div" checked="checked">
						    <label class="radio-label" for="yes_div" style="width:2vw;"> DIV </label>
						    <input type="radio" name="add_gnzd_openType" value="1"  id="yes_newpage" style="margin-left:4vw;">
						    <label class="radio-label" for="yes_newpage"> newpage </label>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">tab页是否可关闭</label>
                         <div class="col-sm-8" style="margin-top: 5px;">
						    <input type="radio" name="add_gnzd_tab" value="0"  id="not_tab" checked="checked" >
						    <label class="radio-label" for="not_tab" style="width:2vw;"> 否 </label>
	                        <input type="radio" name="add_gnzd_tab" value="1" id="yes_tab"style="margin-left:4vw;">
						    <label class="radio-label" for="yes_tab"> 是 </label>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">参数列</label>
                        <div class="col-sm-8">
                        	<input type="text" id="params" name="params" placeholder="请输入参数列" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">菜单排序</label>
                        <div class="col-sm-8">
                        	<input type="number" id="sort" name="sort" placeholder="请输入排序号" class="form-control">
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----编辑菜单功能--->
<div class="modal fade" id="editGnzdFormTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑菜单功能信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editGnzdForm" name="editGnzdForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label">功能名称</label>
        <div class="col-sm-8">
            <input type="hidden" id="edit_f_gnbh" readonly="readonly" name="edit_f_gnbh"   required class="form-control">
        	<input type="text" id="edit_f_gnmc" name="edit_f_gnmc"  class="form-control">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">菜单样式图标名</label>
        <div class="col-sm-8">
        	<input type="text" id="edit_f_cssClass" name="edit_f_cssClass"  class="form-control menuManageFont" 
        	autocomplete="off" onclick="applicationmanage_gnzd.getFotntsAddOrEdit()">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">URL</label>
        <div class="col-sm-8">
        	<input type="text" id="edit_f_url"  name="edit_f_url"  class="form-control">
        </div>
    </div>
    <div class="form-group">
                        <label class="col-sm-3 control-label">区域全屏</label>
                        <div class="col-sm-8" >
					     <select id="edit_fullScreenType" class="selector" style="width: 22.9em;height: 2.5em;margin-left: 0px;" onchange="">
            			 <option  value="0">带导航区域全屏</option>
            			 <option  value="1">内容区域全屏</option>
            			 <option  value="2">只显示内容区域 </option>
            			 </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态</label>
                        <div class="col-sm-8" style="margin-top: 5px;">
						    <input type="radio" name="edit_hide" value="0"  id="edit_not_hide"  checked="checked">
						    <label class="radio-label" for="edit_not_hide" style="width:2vw;"> 启用 </label>
	                        <input type="radio" name="edit_hide" value="1" id="edit_yes_hide" style="margin-left:4vw;">
						    <label class="radio-label" for="edit_yes_hide"> 停用 </label>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label class="col-sm-3 control-label">DIV/newpage</label>
                        <div class="col-sm-8" style="margin-top: 5px;">
	                        <input type="radio" name="edit_openType" value="0" id="edit_yes_div" checked="checked">
						    <label class="radio-label" for="edit_yes_div" style="width:2vw;"> DIV </label>
						    <input type="radio" name="edit_openType" value="1"  id="edit_yes_newpage" style="margin-left:4vw;">
						    <label class="radio-label" for="edit_yes_newpage"> newpage </label>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">tab页是否可关闭</label>
                         <div class="col-sm-8" style="margin-top: 5px;">
						    <input type="radio" name="edit_tab" value="0"  id="edit_not_tab"  checked="checked">
						    <label class="radio-label" for="edit_not_tab" style="width:2vw;"> 否 </label>
	                        <input type="radio" name="edit_tab" value="1" id="edit_yes_tab" style="margin-left:4vw;">
						    <label class="radio-label" for="edit_yes_tab"> 是 </label>
                        </div>
                    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">菜单参数列</label>
        <div class="col-sm-8">
        	<input type="text" id="edit_f_params"  name="edit_f_params"  class="form-control">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">菜单排序</label>
        <div class="col-sm-8">
        	<input type="number" id="edit_f_sort"  name="edit_f_sort"  class="form-control">
        </div>
    </div>
    
    <div class="form-group m-t-sm" >
        <div class="col-sm-6 col-sm-push-3 display_flex">
            <button class="btn btn-md btn-primary" type="button" onclick="applicationmanage_gnzd.gnzd_editGnzdForm()"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
        </div>
    </div>
            	</form>
            </div>
        </div>
    </div>
</div>
<!-- Start add by yangjx at 20191022 for 图标弹出框 -->
<div id="menuFontsDIV" style="display: none">
    <div id="menuFonts"></div>
</div>
<!-- End add by yangjx at 20191022 -->

<!---添加组织机构信息结束----->
 <script type="text/javascript">
 ;
 var applicationmanage_gnzd = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var gnzd_mkbh = null;//模块编号
	var gnzd_js = null;//级数
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var gnzd_gnbh = cell.getRow().getData().gnbh;
 		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ gnzd_gnbh + " data-toggle='modal' data-target='#editGnzdFormTable'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + gnzd_gnbh + "><i class='fa fa-trash'></i>  删除</button></div>"
	};
	/* $(function(){  
		loadxtmclist();//加载系统下拉列表 
	});  */
	
		//加载模块下拉列表
		function loadmodulemclist(id){
			
			$("#gnzd_module_group").empty();//清空select
			$("#gnzd_sysTable").tabulator("clearData");//清空表格
			$("#tree_gnzd_sys").empty();//清空树
			
			$.ajax({
		 	    url: _ctx + "/view/systemcenter/module_list",
		 	    type: "post",
		        dataType: "json",
		        data:({
		        	xtbh:id
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	if(result.length>0){
			    	gnzd_mkbh = result[0].guid;//初始化模块编号为第一位
		    		load_gnzd_tree(result[0].guid);//功能编号树-默认加载第一个模块编号
		    		
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("gnzd_module_group");  
			    		var obj=document.createElement("option");
			    		
			    		obj.value=result[i].mkmc;
			    		obj.text=result[i].mkmc;
			    		obj.id=result[i].guid;
			    		x.appendChild(obj);	
			    		if($("#gnzd_module_list").val()==result[i].mkmc){//设置当前选中的功能模块名称
			    			obj.selected = true; 
			    		}else{
			    			$("#gnzd_module_list").attr('');
			    		}
			    	}
			    }
		       },
		       complete: function () {
					hiddenLoad();
				},
		        error: function(result) {
		          	  alert("获取设备型号失败！");
		        }
			   });
		}
		//加载模块下所有功能编号
		function loadTabulatorByGuid(guid){
			//;
			$.ajax({
		        type: "post",
		        url: _ctx + "/view/systemcenter/gnzd_list",
		        dataType: "json",
		        data:({
		        	guid:guid
		        }),
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
					$("#gnzd_sysTable").tabulator("setData", result);
		      },
		      complete: function () {
					hiddenLoad();
				},
			error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
		}
	//创建并设置table属性
	$("#gnzd_sysTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		// selectable:1,
		movableColumns:false,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",headerSort:false}, //frozen column
		//{title:"功能编号", field:"gnbh", width:80,sorter:"string",align:"center",editor:false,headerSort:false}, //never hide this column
		{title:"功能名称", field:"gnmc",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
		//{title:"模块编号", field:"mkbh",width:80,sorter:"string",align:"center",editor:false,headerSort:false}, //hide this column first 
		{title:"图标名", field:"cssClass",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
		{title:"URL", field:"url",sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"状态", field:"hide",sorter:"string",align:"center",editor:false,headerSort:false, formatter:function(cell, formatterParams){
			if(cell.getValue() == "停用" || cell.getValue() == "1"){
				return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			}else{
				return cell.getValue();
			}
		}},
		//{title:"级数", field:"js",width:50,sorter:"string",align:"center",editor:false,headerSort:false},
		//{title:"明细", field:"mx",width:50,sorter:"string",align:"center",editor:false,headerSort:false},
		//{title:"父编号", field:"pgnbh",width:80,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"区域全屏", field:"fullScreenType",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"DIV/newpage", field:"openType",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"tab页是否可关闭", field:"tabcloseable",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"参数列", field:"params",sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"排序", field:"sort",sorter:"string",align:"center",editor:false,headerSort:false},
		//{title:"创建时间", field:"crdate",width:120,sorter:"date",align:"center",editable:false,headerSort:false},
		//{title:"修改时间", field:"chdate",width:120,sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:150,tooltip:false,tooltipsHeader:false,align:"left",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().gnbh;
        	var choiseNode = $('#tree_gnzd_sys').treeview('findNodes', [ id, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_gnzd_sys').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_gnzd_sys').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }
    	},
	});
	
	//加载功能字典树
	function load_gnzd_tree(guid){
		$.ajax({
	        type: "post",
	        url: _ctx + "/view/systemcenter/gnzd_tree",
	        dataType: "json",
	        data:({
	        	guid:guid
	        }),
	        beforeSend: function () { 
 				showLoad();	             
 			},
	        success: function (result) {
		        if(result.hasOwnProperty("list")==false){
					result.list =[];	
				}else{
					var id = result.list[0].id.substring(4);
		        	$("#gnzd_sysTable").tabulator("setData", _ctx+"/view/systemcenter/gnzd_treegrid?gnbh="+id+"&mark=root");
				}
	        	$('#tree_gnzd_sys').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	               onNodeSelected: function (event, nodeData) {
	            	   var  data;
	            	   //判断节点父id是否为根节点
	            	   if(nodeData.pid == "gnzdroot"){
	            		 	data={
	            			   gnbh:nodeData.id.substring(4),
	            			   mark:"root"
	            		   };
	            	   }else{
	            		    data={
	            			   gnbh:nodeData.id.substring(4),
	            			   mark:"normal"
	            		   };
	            	   }
	               $('#tree_gnzd_sys').treeview('clearSearch');//清除搜索选中高亮
	                    $.ajax({
	                	    url: _ctx + "/view/systemcenter/gnzd_treegrid",
	                	    contentType: "application/json; charset=utf-8",
	                	    type: "get",
	                	    data: data,
	                	    beforeSend: function () { 
	        	 				showLoad();	             
	        	 			},
							success: function(result) {
					            if(result.list!= undefined){
						            $("#gnzd_sysTable").tabulator("setData", result.list);
									}else{
						            $("#gnzd_sysTable").tabulator("setData", []);
									}
				            },
				            complete: function () {
								hiddenLoad();
							},
				            error: function(result) {
				          	    swal( result.msg,"", "error");
				            }
                	   });
	                }
	            });
	        	$("#tree_gnzd_sys").treeview('collapseAll');
	             var firstNode = $("#tree_gnzd_sys").treeview('findNodes',[result.list[0].id,'id']);
	            $("#tree_gnzd_sys").treeview('expandNode',firstNode);
	        	$("#tree_gnzd_sys").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
	        },
	        complete: function () {
				hiddenLoad();
			},
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	
	//触发搜索的回车时间
	$("#euserkeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
					  search_Gnzd();//触发该事件
					    } 
					  })
					});

	//搜索
	function search_Gnzd(){
		var euserkeywords = $("#euserkeywords").val();
		$("#gnzd_sysTable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+euserkeywords);
	}



	
	//添加组织机构表单验证
    var euserValidator = $("#addGnzd_form").validate({
    	rules: {
            gnmc: {
                required: true,
                rangelength: [1, 20],
            },
            sort: {
                required: true,
                rangelength: [1, 10],
            }
        },
        messages: {
        	gnmc: {
              required: "请输入菜单功能名称",
                   rangelength: jQuery.validator.format("菜单功能名称应为1-20位的英文字母、数字字符"),
                   remote: jQuery.validator.format("{0} is already in use")
           },
           sort: {
              required: "请输入菜单排序",
                   rangelength: jQuery.validator.format("菜单排序应为1-10位数字字符"),
                   remote: jQuery.validator.format("{0} is already in use")
           }
       },
	     submitHandler: function (form) {
	         addform_Gnzd(form);
	     }
 	});
	//重置单选框和下拉框
    function resetRadio(){
  	  $('input:radio[name=gnzd_hide]')[0].checked = true;
  	  $('input:radio[name=gnzd_tab]')[0].checked = true;
  	  $('input:radio[name=gnzd_openType]')[0].checked = true;
  	$("#gnzd_fullScreenType option:first").prop("selected", 'selected');
    }
    //新增保存
	function addform_Gnzd(form) {
		var fullScreenType = $("#add_gnzd_fullScreenType option:selected").val();//0:带导航区域全屏;1:内容区域全屏（包括Tab导航，且不含左侧菜单）；2：只显示内容区域
	/* 	var hide = $("input[name='add_gnzd_hide']:checked").val();//是否隐藏
		var tabclose = $("input[name='add_gnzd_tab']:checked").val();//tab页是否可关闭
		var openType = $("input[name='add_gnzd_openType']:checked").val();//0:DIV;1:newpage', */
		var hide = $("input[name='add_gnzd_hide']:checked")[0].id=="yes_hide"?"1":"0";
		var tabclose = $("input[name='add_gnzd_tab']:checked")[0].id=="yes_tab"?"1":"0";
		var openType = $("input[name='add_gnzd_openType']:checked")[0].id=="yes_newpage"?"1":"0";
	       $.ajax({
		      url: _ctx + "/view/systemcenter/add_gnzd",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({ 
		    	  gnmc:$("#gnmc").val(),
		    	  mkbh:gnzd_mkbh,
		    	  js : gnzd_js,
		    	  mx : 0,
		    	  url:$("#url").val(),
		    	  cssClass:$("#cssClass").val(),
		    	  hide:hide,
		    	  pgnbh:$("#pgnbh").val(),
		    	  fullScreenType:fullScreenType,
		    	  openType:openType,
		    	  tabcloseable:tabclose,
		    	  params:$("#params").val(),
		    	  sort:$("#sort").val()
		    	  
		      }),
		      beforeSend: function () { 
	 				showLoad();	             
	 			},
			 success: function(data) {
	         if (data.status == '1') {
	            $('#modal-addgnzd').modal('hide');//关闭编辑窗口
	            swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
		            var pNode = $("#tree_gnzd_sys").treeview("getSelected");
		            $("#tree_gnzd_sys").treeview("addNode", [{ id:data.data.gnbh, text:data.data.gnmc, pid:pNode[0].id, js:data.data.js}, pNode]);    
		            if(data.data.hasOwnProperty("pgnbh")==true){
		        	//在树上添加
			            //在表格中添加数据
			           // $('#epostTable').tabulator("addRow", { f_guid:data.f_guid,f_gwbh:data.f_gwbh, f_gwmc:data.f_gwmc,f_zzjgmc:data.f_zzjgmc,f_zzjgbh:data.f_zzjgbh, f_remark:data.f_remark,f_crdate:data.f_crdate, f_chdate:data.f_chdate});
			            $('#gnzd_sysTable').tabulator("addRow", {
			            	gnbh:data.data.gnbh,
			            	gnmc:data.data.gnmc, 
			            	mkbh:data.data.mkbh,
			            	cssClass:data.data.cssClass,
			            	url:data.data.url, 
			            	js:data.data.js,
			            	mx:data.data.mx,
			            	pgnbh:data.data.pgnbh,
			            	fullScreenType:data.data.fullScreenType,
			            	hide:data.data.hide,
			            	openType:data.data.openType,
			            	tabcloseable:data.data.tabcloseable,
			            	params:data.data.params,
			            	sort:data.data.sort,
			            	f_crdate:data.data.f_crdate, 
			            	f_chdate:data.data.f_chdate
			            	});
		            }else{
			          //	$("#tree_gnzd_sys").treeview("addNode", [{ id:data.data.gnbh, text:data.data.gnmc, js:data.data.js}, pNode]);
			          	$('#gnzd_sysTable').tabulator("addRow", {
			            	gnbh:data.data.gnbh,
			            	gnmc:data.data.gnmc, 
			            	mkbh:data.data.mkbh,
			            	cssClass:data.data.cssClass,
			            	url:data.data.url, 
			            	js:data.data.js,
			            	hide:data.data.hide,
			            	mx:data.data.mx,
			            	pgnbh:data.data.pgnbh,
			            	fullScreenType:data.data.fullScreenType,
			            	openType:data.data.openType,
			            	tabcloseable:data.data.tabcloseable,
			            	params:data.data.params,
			            	sort:data.data.sort,
			            	f_crdate:data.data.f_crdate, 
			            	f_chdate:data.data.f_chdate
			            	});
			         }
		           // resetRadio();//重置
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
				hiddenLoad();
			},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       	//resetRadio();//重置
	       }
	     });
	     //return false;
 	}
 	//居中显示（添加）
 	$('#modal-addgnzd').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-addgnzd .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editGnzdFormTable').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editGnzdFormTable .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-addgnzd").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidator.resetForm();  
    });
    
    //删除数据
    $(document).on('click','#gnzd_sysTable button.delete', function () {

        var id=$(this).data("id");
        var dNode = $('#tree_gnzd_sys').treeview('findNodes', [ id, 'id']);
	    	for (var i = 0; i < dNode.length; i++) {
				if(dNode[i].id == id){
					var booljuge = dNode[i].hasOwnProperty('nodes');
					if(booljuge == true){
						if(dNode[i].nodes.length>0){
							swal({ 
					            title: "请您先删除子功能菜单！",
					            text: "经检测，您要删除的功能菜单包含子功能菜单!",
					            type: "warning",
					            showCancelButton: false,
					            confirmButtonColor: "#1ab394",
					            confirmButtonText: "关闭",
					            closeOnConfirm: false
					        });
						}else{
							deleteGnzd(id,dNode[i]);//删除
						}
					}else{
						deleteGnzd(id,dNode[i]);//删除
					}
				}
			}
    });
    
    function deleteGnzd(id,node){
    	swal({ 
            title: "您确定要删除吗?",
            text: "信息删除后将不可恢复!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#1ab394",
            confirmButtonText: "确定",
            closeOnConfirm: false
        }, function () {
        	setTimeout(function(){
            	$.ajax({
		          url: _ctx + "/view/systemcenter/del",
		          contentType: "application/json; charset=utf-8",
		          type: "post",
		          data:JSON.stringify({     
		        			gnbh:id.substring(4),
		        			mkbh:gnzd_mkbh
		    	  }),
		    	  beforeSend: function () { 
		 				showLoad();	             
		 			},
	          	  success: function(data) {
	              	if (data.status == '1') {
	              		swal({
	    		        	title : data.msg,// 展示的标题
	    		   			text : "",// 内容
	    		   			type: "success",
	    		   			showCloseButton : false, // 展示关闭按钮
	    		   			allowOutsideClick : false,
	    		   			showConfirmButton : false,
	    		   			timer: 1000
	    		   		});
	              		$("#tree_gnzd_sys").treeview("removeNode", node, { silent: true } );   
	              		//在表格中删除该条数据
	              		$("#gnzd_sysTable").tabulator("deleteRow", _curRow);
	              		var getuserTable = $("#gnzd_sysTable").tabulator("getData");
	              		$("#gnzd_sysTable").tabulator("setData", getuserTable);
		            } else{
		                swal(data.msg,"", "error");
		            }
		          },
		          complete: function () {
						hiddenLoad();
					},
		          error: function(data) {
		          	 swal( data.msg,"", "error");
		          }
		        });
            },100
        )
    });
   }
    
  
  //验证在模态框出现前加载
  $("#editGnzdFormTable").on('show.bs.modal', function(event) {
	   
	  var id = _curRow.getData().gnbh;
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/systemcenter/edit_gnzd_obj",
	       type: "get",
	       contentType: "application/json; charset=utf-8",
	       data:{     
	 			gnbh:id
	 		},
	 		beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	    	   result = result.list;
	    	   
	         $("#edit_f_gnbh").val(result[0].gnbh);
	         $("#edit_f_gnmc").val(result[0].gnmc);
	         $("#edit_f_url").val(result[0].url);
	         $("#edit_f_cssClass").val(result[0].cssClass);
	        // $("#edit_f_mkbh").val(result[0].mkbh);
	         $(":radio[name='edit_hide'][value='" + result[0].hide + "']").prop("checked", "checked");
	         $(":radio[name='edit_openType'][value='" + result[0].openType + "']").prop("checked", "checked");
	         $(":radio[name='edit_tab'][value='" + result[0].tabcloseable + "']").prop("checked", "checked");
	         $("#edit_fullScreenType").find("option[value = '"+result[0].fullScreenType+"']").attr("selected","selected");
	         //$("#edit_f_pgnbh").val(result[0].pgnbh);
	         $("#edit_f_params").val(result[0].params);
	         $("#edit_f_sort").val(result[0].sort);
	         },
	         complete: function () {
					hiddenLoad();
			},
    		});  
  });
  
  /***************图标start add by yangjx at 20191022 for 图形显示 ***************/
  /* 图标弹出层 */
  function fontsView(data){
      indexFont=layer.open({
          type: 1,
          area: ['52.5vw', '68vh'],
          title: '选择图标',
          content : $('#menuFontsDIV')
      });
      getFonts();
  }
  //获取图标样式
  function getFonts(){
      $.ajax({
          type: "post",
          url: _ctx+'/view/esHomeModule/icon/getFonts',
          dataType: "json",
          success: function (result) {
              if(result.status == '1'){
                  var div=document.getElementById("menuFonts");
                  var fontsList=result.list;
                  for(var index in fontsList){
                      var fontName=fontsList[index];
                      var d=document.createElement("div");
                      d.setAttribute("style","margin: 10px;float: left;");
                      var a=document.createElement("a");
                      a.setAttribute("href","javascript:void(0)");
                      a.setAttribute("onclick","applicationmanage_gnzd.addFontValue('"+fontName+"')");
                      a.setAttribute("style","color: #80e6ff;font-size:1vw");
                      var i=document.createElement("i");
                      i.setAttribute("class","fa "+fontName);
                      d.appendChild(a);
                      a.appendChild(i);
                      div.appendChild(d);
                  }
              }else{
                  layer.msg("获取图标失败！");
              }
          },
          error: function (result) {
              layer.msg("获取图标失败！");
          }
      });
  }
  /*图标的关闭按钮  */
  function closeFontsLayer(){
      layer.close(indexFont);
      indexFont = 0;
  }
  /***************图标end***************/
  return {
		//select改变选择option
		change_xt: function(){
			var xtbh = $("#gnzd_xt_group option:selected").attr("id");
			loadmodulemclist(xtbh);
		},
		//加载系统下拉列表 
		loadxtmclist: function(){
			$.ajax({
		 	    url: _ctx + "/view/systemcenter/loadCurrentXt",
		 	    type: "post",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(result) {
			    	result = result.list;
			    	for(var i = 0;i < result.length; i++) {
			    		var x=document.getElementById("gnzd_xt_group");  
			    		var obj=document.createElement("option");
			    		obj.value=result[i].f_xtmc;
			    		obj.text=result[i].f_xtmc;
			    		obj.id=result[i].f_xtbh;
			    		x.appendChild(obj);	
			    		if($("#gnzd_xt_list").val()==result[i].f_xtmc){//设置当前选中的系统名称
			    			obj.selected = true; 
			    		}else{
			    			$("#gnzd_xt_list").attr('');
			    		}
			    	}
		    		loadmodulemclist(result[0].f_xtbh);//默认加载第一个系统对应下模块
	        },
		        complete: function () {
					hiddenLoad();
				},
		        error: function(result) {
		          	  alert("获取设备型号失败！");
		        }
		   });
		},
		//select改变选择option
		 change_modules:function(){
			var guid = $("#gnzd_module_group option:selected").attr("id");
			gnzd_mkbh = guid ;
			load_gnzd_tree(guid);//加载功能字典树
		},
		//验证增加模态框是否弹出
		  gnzd_show_addmodal:function() {
			  //
			if(gnzd_mkbh != null){
					var node = $('#tree_gnzd_sys').treeview('getSelected');
					if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
						swal({ 
				            title: "请选择节点",
				            text: "经检测，您要未选择菜单功能树节点!",
				            type: "warning",
				            showCancelButton: false,
				            confirmButtonColor: "#1ab394",
				            confirmButtonText: "关闭",
				            closeOnConfirm: false
				        });
					}else{
						$('#modal-addgnzd').modal('show'); 
						$("#add_pgnbh_div").hide();
						$("#pgnbh").val(node[0].pid == "gnzdroot"?"":node[0].id.substring(4));
						gnzd_js = node[0].level-1;
					}
			}else{
				swal({ 
		            title: "请添加该系统下菜单模块",
		            text: "经检测，系统下未添加菜单模块!",
		            type: "warning",
		            showCancelButton: false,
		            confirmButtonColor: "#1ab394",
		            confirmButtonText: "关闭",
		            closeOnConfirm: false
		        });
			}
	    },
	    //编辑
	     gnzd_editGnzdForm:function() {
		  	var hide = $("input[name='edit_hide']:checked").val();//是否隐藏
			var openType = $("input[name='edit_openType']:checked").val();//下拉显示val
			var tabclose = $("input[name='edit_tab']:checked").val();//tab页是否可关闭
			var fullScreenType = $("#edit_fullScreenType option:selected").val();//0:带导航区域全屏;1:内容区域全屏（包括Tab导航，且不含左侧菜单）；2：只显示内容区域
	    $.ajax({
	      url: _ctx + "/view/systemcenter/edit",
	      type: "post",
	      data:({ 
	      		gnbh: $("#edit_f_gnbh").val(),
		        gnmc: $("#edit_f_gnmc").val(),
		        url: $("#edit_f_url").val(),
		        cssClass: $("#edit_f_cssClass").val(),
		        hide: hide,
		        fullScreenType: fullScreenType,
		        openType: openType,
		        tabcloseable: tabclose,
		        params: $("#edit_f_params").val(),
		        sort: $("#edit_f_sort").val()
	      }),
	      beforeSend: function () { 
				showLoad();	             
			},
	      success: function(data) {
				if (data.status == '1') {
	             $('#editGnzdFormTable').modal('hide');//关闭编辑窗口
	              swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
	              if(data.data.hasOwnProperty("url") ==false){
	            	  data.data.url = '';  
	              }
		         $('#gnzd_sysTable').tabulator("updateRow",_curRow, {
		        	 gnbh:data.data.gnbh,
		        	 gnmc:data.data.gnmc, 
		        	 hide:data.data.hide, 
		        	 mkbh:data.data.mkbh, 
		        	 cssClass:data.data.cssClass, 
		        	 openType:data.data.openType, 
		        	 params:data.data.params, 
		        	 pgnbh:data.data.pgnbh, 
		        	 tabcloseable:data.data.tabcloseable, 
		        	 fullScreenType:data.data.fullScreenType, 
		        	 url:data.data.url, 
		        	 sort:data.data.sort, 
		        	 f_chdate:data.chdate
		        	 });
		       		//在树上修改   
				   var parentNode = $('#tree_gnzd_sys').treeview('findNodes', [_curRow.getData().cid, 'id'])
				   if(parentNode.length>1){
		    	    	for (var i = 0; i < parentNode.length; i++) {
		    				if(parentNode[i].id == _curRow.getData().cid){
		    					parentNode = parentNode[i];
		    				}
		    			}
		    	    }else{
		    	    	parentNode = parentNode[0];
		    	    }
	            	var newNode={
						text: data.data.gnmc
					};
					$('#tree_gnzd_sys').treeview('updateNode', [parentNode, newNode]);
	            } else{
	              swal("修改失败!", data.msg, "error");
	            }
	          },
	          complete: function () {
					hiddenLoad();
				},
	          error: function(data) {
	          	 swal("修改失败!", data.msg, "error");
	          }
	    });
	  },
	  thysClass : function(){
		  $(".tabulator-tableHolder").addClass("tabulator").removeClass("tabulator-tableHolder");
// 		  $(".tabulator").removeClass("tabulator-tableHolder");
	  },
		pageInit : function(){
			applicationmanage_gnzd.loadxtmclist();
			applicationmanage_gnzd.thysClass();
			//$("#qljtiot_gnzd_hide").hide();
		}
	   //Start add by yangjx at 20191022 for 菜单图标设置显示图形 and 刷新 
		,
	    getFotntsAddOrEdit:function(){
	          fontsView();
	    },
	    addFontValue:function (fontName) {
	          $(".menuManageFont").val(fontName);
	          closeFontsLayer();
	    }
		//End add by yangjx at 20191022 for 菜单图标设置显示图形 and 刷新
	  
	}
 })(jQuery, window, document);
 applicationmanage_gnzd.pageInit();
 </script>