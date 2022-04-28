<!-----内容区域---->

<div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
           <a id="addhouseholdConf" href="javascript:void(-1);" onclick="basedatamanage_efficiencyanalysis_householdConf.show_addhousehold();" class="btn btn-primary" >
                	<i class="fa fa-plus"></i>增加 
            </a>
            <a id="importhousehold" class="btn btn-primary" data-toggle="modal"  href="#modal-form-importhousehold" >
                	<i class="fa fa-mail-reply"></i>导入 
            </a>
            <a id="exporthousehold" class="btn btn-primary" data-toggle="modal"  href="#modal-form-exporthousehold">
                	<i class="fa fa-mail-forward"></i>导出 
            </a>
            
    	</div>
        <div class="zc_search find">
        	<div class="zc_search_form">
            <input id="householdInfo" name="householdInfo" value="" placeholder="分户名称、编号"> 
				<button id="queryHousehold" onclick="basedatamanage_efficiencyanalysis_householdConf.searchhousehold()"></button>
            </div>
       	</div>
       	<!-- 生成园区下拉框  -->
       	<div style = "display:inline-block ;margin-left: -584px;">
 				<select id='btn_yqhouse' name='btn_yqhouse' required class="form-control selectpicker" style="height:34px;width:100%; border: 1px solid #bbb;
						border-radius: 6px;background-color:rgb(216, 239, 255);color:#000000;"
				onchange = "basedatamanage_efficiencyanalysis_householdConf.get_yqtreehouse_sub1()"  data-live-search="false">
				</select> 
		</div>
   </div>
 </div>

<div class="leftarea" id="lefthouseholdconf" style="margin-top:-9px;height:93%">
	<!-- <button id="btn_yqhouse" type="button" class="btn btn-primary" data-toggle="modal" data-target='#modal-yqtree'style="width:100%;height:5%">
		请先选择园区&nbsp; <i class="fa fa-angle-double-right"></i>
	</button>  -->
	<div id="tree_household"></div>
</div>

<div style=" float: right;height:93%;width:85%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%)">
		<div id="householdTable">	</div>
   </div>
</div>



<!---添加分项配置开始-----> 
<div class="modal fade" id="modal-form-addhouseholdConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加分户信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addhouseholdConfform" name="addhouseholdConfform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分户名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fFhmc" name="fFhmc" placeholder="请输入分户名称"  required class="form-control">
                        </div>
                    </div>      
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分户人数<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fPersonNums" name="fPersonNums" placeholder="请输入分户人数"  required class="form-control">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">分户面积<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fArea" name="fArea" placeholder="请输入分户面积"  required class="form-control">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属位置<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fLocation" name="fLocation" placeholder="请输入所属位置"  required class="form-control">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="fNybh_household" name="fNybh_household" required class="form-control">
                            
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">园区编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
							 <input type="text" id="fYqbh_household" name="fYqbh_household" value="" class="form-control" readonly="readonly">					
                        </div>
                    </div>                    
                                                     
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----编辑分户--->
<div class="modal fade" id="editHouseholdForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑分户信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_HouseholdForm" name="edit_HouseholdForm" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fFhbh">分户编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fFhbh" name="edit_fFhbh"  required class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fFhmc">分户名称<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fFhmc" name="edit_fFhmc"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fPersonNums">分户人数<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fPersonNums" name="edit_fPersonNums"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fArea">分户面积<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fArea" name="edit_fArea"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fLocation">所属位置<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fLocation" name="edit_fLocation"  required class="form-control">
					</div>
				</div>
                <div class="form-group">
                        <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fNybh_household" name="edit_fNybh_household" required class="form-control">
                            </select>
                        </div>
                </div>
 				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fYqbh_household">园区编号<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fYqbh_household" name="edit_fYqbh_household"  required class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4">
						<button class="btn btn-md btn-primary " type="submit">
						<strong>更新分项配置</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
				</form>
            </div>
        </div>
    </div>
</div>

<!----包含支路支路--->
<div class="modal fade" id="includeHouseholdBrc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:785px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">包含支路</h4>
            </div>
            <div class="modal-body" style="height:600px;">
            	<div style="float:left;width:57%"><button class="btn btn-md" style="cursor:default"><span>未选择</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>已选择</span></button></div>
<!--未选择table+搜索）- -->
            	<div class="notIncludeCss" >
            	
         <div style="padding:2px 2px 2px 10px;height:6%;">
		     <div style="float:left;">
		     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="notincludeBrcKeywords" name="notincludeBrcKeywords" value="" placeholder="支路编号、名称"> 
		     </div>
		     <div style="float:left;">
		     <span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBrc" onclick="basedatamanage_efficiencyanalysis_householdConf.searchNotIncludeBrc()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
		     </div>
		     
		   
       	</div>
            	  
       		
            	<div id="householdBrc_noInclude" style="margin-top:10px;overflow: auto;" >
            	</div>
            	</div>
<!-- 未选择用户结束- -->
            	
            	
<!--操作开始- -->
            	<div style="width:100px;height:400px;float:left">
            	<div id="BrcrightMove" style="margin-top:200px;margin-left:23px;"><button id="householdConf_right" type="button" onclick="basedatamanage_efficiencyanalysis_householdConf.BrcrightMove()" class="btn btn-primary">>></button></div>
            	<div id="BrcleftMove" style="margin-top:20px;margin-left:23px;"><button id="householdConf_left" type="button"  onclick="basedatamanage_efficiencyanalysis_householdConf.BrcleftMove()" class="btn btn-primary"><<</button></div>
            	</div>
<!--操作结束- -->
            	
            	
<!--包含用户开始- -->
            	<div class="includeCss">
            	
        <div style="position: relative;padding:2px 2px 2px 10px;height:6%;">
        <div style="float:left;">
		     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="includeBrcKeywords" name="includeBrcKeywords" value="" placeholder="分户名称、编号"> 
		     </div>
		     <div style="float:left;">
		     <span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryIncludeBrc" onclick="basedatamanage_efficiencyanalysis_householdConf.searchIncludeBrc()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
		     </div>
       
       	</div>
            	<div id="householdBrc_include" style="overflow: auto;margin-top:10px;" >
            	</div>
            	<div>
            	</div>
            	</div>
<!--包含用户结束- -->
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
            </div>
        </div>
    </div>
</div>

<!-- 组织机构树 -->
<div class="modal fade" id="modal-yqtree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:380px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">关联园区</h4>
            </div>
            <div class="modal-body" style="height:600px;overflow-y: auto;">
					<div id="sub_tree_yq"></div>
			</div>
                <div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
        		</div>
            
        </div>
    </div>
</div>

<style>
.includeCss{
	float: left;
    width: 310px;
    height: 480px;
    border: 1px solid rgba(121, 194, 218, 0.44);
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    margin: 5px 0;
    padding: 10px 0 10px 0;
    background-color: #0c2939;
}
.notIncludeCss{
		float: left;
    width: 310px;
    height: 480px;
    border: 1px solid rgba(121, 194, 218, 0.44);
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    margin: 5px 0;
    padding: 10px 0 10px 0;
    background-color: #0c2939;
}
</style>



<script type="text/javascript">
;
var basedatamanage_efficiencyanalysis_householdConf = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _householdFhbh = "00";//分户配置编码
	var _householdJs = "0";//用户组对应的级数
	var _fhbh = "00";
	var _yqbh = "";//组织机构编号
	var _yqlog = "0";//判断组织机构下是否有分项配置
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	var Selected_tree = null;//组织机构树被选中的节点
	var Selected_sub = null;//分项树被选中的节点
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var householdid = cell.getRow().getData().fFhbh;
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm include' data-id=" + householdid + " data-toggle='modal' data-target='#includeHouseholdBrc'><i class='fa fa-user'></i>  包含支路</button>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ householdid + " data-toggle='modal' data-target='#editHouseholdForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + householdid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};


	//创建并设置table属性
	$("#householdTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",align:"center"}, //frozen column
		{title:"分户编号", field:"fFhbh", width:85,sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"分户名称", field:"fFhmc",width:100,sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first 
		{title:"人数", field:"fPersonNums",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"面积", field:"fArea",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"所属位置", field:"fLocation",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"能源编号", field:"fNybh",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"所属园区", field:"fYqbh",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width : 250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
    	rowClick:function(e, row){

        	_curRow = row;
        	var id = _curRow.getData().fFhbh;
        	var choiseNode = $('#tree_household').treeview('findNodes', [ _curRow.getData().fFhbh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_household').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_household').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }        	
    	},
	});
	
	//填充数据---按照组织机构=》分项=》列表顺序加载，所以不直接填充数据
	//$("#householdTable").tabulator("setData", _ctx+'/view/basedatamanage/getHouseholdList');


	
	$(window).resize(function(){
		$("#householdTable").tabulator("redraw");
	});

	//加载分户 树
	function gettree_household(_yqbh) {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/basedatamanage/efficiencyanalysis/loadAllTree",
	        data:({     
			      fYqbh:_yqbh
			}),
	        dataType: "json",
	        success: function (result) {

	        	if(result.list == undefined){
	        		$('#tree_household').treeview('remove');
	        		$("#householdTable").tabulator("setData", []);
	        		_householdJs = "1";
	        		_yqlog = "0";
	        	}else{
               	_yqlog = "1";
	            $('#tree_household').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) {
	               $('#tree_household').treeview('clearSearch');//清除搜索选中高亮
	               		_householdFhbh = nodeData.id;
	               		_householdJs = nodeData.level;
	               		gethousehold_chlildtree(_householdFhbh,_householdJs);
	               		Selected_sub = $('#tree_household').treeview('getSelected');
	                }
	            });
		           //初始加载根节点
// 	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
// 	            	if(result.list.length >0){//如果包含判断是否长度大于0
// 			            var firstNode = $("#tree_household").treeview('findNodes',[result.list[0].id,'id']);
// 			        	$("#tree_household").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
// 	            	}
// 	            }
		            if(Selected_sub == null){
		            	_householdFhbh = result.list[0].id;
		            	_householdJs   = result.list[0].level;
		            	var firstNode = $("#tree_household").treeview('findNodes',[result.list[0].id,'id']);
			        	$("#tree_household").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
 		       			//获取子节点
	               		gethousehold_chlildtree(_householdFhbh,_householdJs);
 		            }
	        	}
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	function gethousehold_chlildtree(_householdFhbh,_householdJs){
        $.ajax({
    	    url: _ctx + "/view/basedatamanage/efficiencyanalysis/household_chlildtreegrid",
    	    type: "post",
    	    data: {
    	    	fFhbh:_householdFhbh,
    	    	f_js :_householdJs
    	    },
			success: function(nodeData2) {

			if(nodeData2.hasOwnProperty('list')==false){
	            $("#householdTable").tabulator("setData", []);
			}else{
	            $("#householdTable").tabulator("setData", nodeData2.list);
			}
            },
            error: function(nodeData2) {
          	    swal( nodeData2.msg,"", "error");
            }
	   });
		
	}

	//触发搜索的回车时间
	$("#householdInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				basedatamanage_efficiencyanalysis_householdConf.searchhousehold();//触发该事件
			} 
		 })
	});




	
	//select标签查询为在查询中刷新并显示下拉，特注释掉此方法，并将其分别放置于验证模态框出现前加载编辑中和添加中
	/* function fNybhhousehold(keywords){
		$.ajax({
        	type: "POST", 
        	url: _ctx + '/view/common/selectfNybhList', 
        	data:"",
        success: function(data){
		 	var ops="<option value=''>请选择能源编号</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.fNybh+'">';
			 	ops+=obj.fNybh+'('+obj.fNymc+')';
			 	ops+='</option>';
		 	}
		 	if(keywords == 'add'){
		 		$("#fNybh_household").append(ops);
		 	}else{
		 		$("#edit_fNybh_household").append(ops);	
		 	}
        }, 
        error:function(msg){
        	alert( "select列表查询失败!" );  
        } 
    });
 	} */

	//添加用户组表单验证
    var groupValidator = $("#addhouseholdConfform").validate({
	     rules: {
	    	 fFhmc: {
	             required: true,
	             rangelength: [1, 40]
	         }
	     },
	     messages: {
	         
	    	 fFhmc: {
	             required: "请填写分项名称",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
	     submitHandler: function (nodeData) {
	         addformhousehold(nodeData);
	     }
 	});
	
 	//新增保存
	function addformhousehold(nodeData) {

 		if(_yqlog == 0){
 			_householdFhbh = "";
 		}
	     $.ajax({
	       url: _ctx + "/view/basedatamanage/efficiencyanalysis/add_household",
	       type: "post",
	       data:({     
	    	   fFhmc:$("#fFhmc").val(),
	    	   fNybh:$("#fNybh_household").val(),
	    	   fYqbh:$("#fYqbh_household").val(),
	    	   fPersonNums:$("#fPersonNums").val(),
	    	   fArea:$("#fArea").val(),
	    	   fLocation:$("#fLocation").val(),
			   fPfhbh:_householdFhbh,
			   fJs:_householdJs
	 		}),
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
		            $('#modal-form-addhouseholdConf').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#householdTable').tabulator("addRow", data.list);
		            //在树上添加		            
		            var pNode = $("#tree_household").treeview("getSelected");
		            if (pNode.length == 0 && pNode != '') {
		            	$("#tree_household").treeview("addNode", [{ id:data.list[0].fFhbh, text:data.list[0].fFhmc, pid:pNode[0].id, js:data.list[0].fJs}, pNode]);  
		            }else{
	 		            gettree_household(data.list[0].fYqbh);
		            }
	          
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 


	//居中显示（添加）
	$('#modal-form-addhouseholdConf').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-addhouseholdConf .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
        'margin-top': modalHeight  
    }); 
	})
	
	//居中显示（编辑）
 	$('#editHouseholdForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editHouseholdForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})

	//居中显示（包含用户）
 	$('#includeHouseholdBrc').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#includeHouseholdBrc .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//居中显示（组织机构）
 	$('#modal-yqtree').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-yqtree .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})

	//关闭模态框清空表单值
    $("#modal-form-addhouseholdConf").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#fNybh_household").empty();
        groupValidator.resetForm();  
    });
	
    //删除数据
    $(document).on('click','#householdTable button.delete', function () {
        var id=$(this).data("id");
        var dNode = $('#tree_household').treeview('findNodes', [ id, 'id']);
	    if(dNode.length>1){
	    	
		    for (var i = 0; i < dNode.length; i++) {
				if(dNode[i].id == id){
					var booljuge = dNode[i].hasOwnProperty('nodes');
					if(booljuge == true){
						swal({ 
				            title: "请您先删除用户组下的子组！",
				            text: "经检测，您要删除的用户组包含子组!",
				            type: "warning",
				            showCancelButton: false,
				            confirmButtonColor: "#1ab394",
				            confirmButtonText: "关闭",
				            closeOnConfirm: false
				        });
					}else{
						deleteGroup(id,dNode[i]);//删除
					}
				}
			}
		    }else{
		    	deleteGroup(id,dNode[0]);
	        }
	    });
    

    function deleteGroup(id,node){
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
		          url: _ctx + "/view/basedatamanage/efficiencyanalysis/del_household",
		          type: "post",
		          data:{"fFhbh":id
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
	              		//在表格中删除该条数据
	              		$("#householdTable").tabulator("deleteRow", _curRow);
			            //在树上删除该条数据
			            //var dNode = $('#tree_household').treeview('findNodes', [ id, 'nodeTreeId']);
			            
			            var getGroupTable = $("#householdTable").tabulator("getData");
	              		$("#householdTable").tabulator("setData", getGroupTable);
			             $("#tree_household").treeview("removeNode", node, { silent: true } );    
		            } else{
		                swal(data.msg,"", "error");
		            }
		          },
		          error: function(data) {
		          	 swal( data.msg,"", "error");
		          }
		        });
            },100
        )
    });
    }
 	//表单验证
	$("#edit_HouseholdForm").validate({
  	 submitHandler: function(form) {
    	 editHouseholdForm(form);
  	 }
 	});

 	//编辑支路
	function editHouseholdForm(form) {

   		$.ajax({
     		url: _ctx + "/view/basedatamanage/efficiencyanalysis/edit_household",
     		type: "post",
     		data:({
     			fFhbh: $("#edit_fFhbh").val(),
     			fFhmc: $("#edit_fFhmc").val(),
     			fPersonNums: $("#edit_fPersonNums").val(),
     			fArea: $("#edit_fArea").val(),
     			fLocation: $("#edit_fLocation").val(),
     			fNybh: $("#edit_fNybh_household").val(),
     		}),
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
	         		$('#householdTable').tabulator("updateRow",_curRow, 
	         				{fFhmc:data.list[0].fFhmc,
	         				 fPersonNums:data.list[0].fPersonNums,
	         				 fArea:data.list[0].fArea,
	         				 fLocation:data.list[0].fLocation,
	         				 fNybh:data.list[0].fNybh,
	         				 fChdate:data.list[0].fChdate});
	         		//在树上修改   
				var parentNode = $('#tree_household').treeview('findNodes', [ _curRow.getData().fFhbh, 'id'])
				var newNode = {text: _curRow.getData().fFhmc};
					$('#tree_household').treeview('updateNode', [ parentNode, newNode]);
             	setTimeout(function(){
	              	$('#editHouseholdForm').modal('hide');//关闭编辑窗口
             	},1000)
         		} else{
             		swal("修改失败!", data.msg, "error");
         		}
    		},
    		error: function(data) {
         	 	swal("修改失败!", data.msg, "error");
    		}
   		});
	}
 
 	//验证在模态框出现前加载编辑
 	$("#editHouseholdForm").on('show.bs.modal', function(event) {
        $("#edit_fNybh_household").empty();
 		//fNybhhousehold('edit');
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取用户组编号
   		$.ajax({
        	type: "POST", 
        	url: _ctx + '/view/common/selectfNybhList', 
        	data:"",
        success: function(data){
		 	var ops="<option value=''>请选择能源编号</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.fNybh+'">';
			 	ops+=obj.fNybh+'('+obj.fNymc+')';
			 	ops+='</option>';
		 	}
		 	$("#edit_fNybh_household").append(ops);	
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/efficiencyanalysis/queryhousehold",
	       type: "post", 
	       data:{     
	 			"fFhbh":id
	 		},
	       success: function(result) {
	         $("#edit_fFhbh").val(result.list[0].fFhbh);
	         $("#edit_fFhmc").val(result.list[0].fFhmc);
	         $("#edit_fPersonNums").val(result.list[0].fPersonNums);
	         $("#edit_fArea").val(result.list[0].fArea);
	         $("#edit_fLocation").val(result.list[0].fLocation);
	         $("#edit_fYqbh_household").val(result.list[0].fYqbh);
	         $("#edit_fNybh_household").val(result.list[0].fNybh);
	       }
		});
	},
   		error : function(msg) {
			alert("select列表查询失败!");
   		}
		});
	});

 	//验证在模态框出现前加载编辑(组织机构树)
 	$("#modal-yqtree").on('show.bs.modal', function() {
 		basedatamanage_efficiencyanalysis_householdConf.get_yqtree_sub();
 	});
	//验证码在模态框出现前加载包含用户(可拖动)
	$("#includeHouseholdBrc").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	  	loadNoIncludeBrc(id);
		loadIncludeBrc(id);
		_fhbh = id;
	});
	
	//分项添加支路
 	$(document).on('click','#householdBrc_noInclude button.insertBranch', function () {
     var f_yhbh = $(this).data("id");
   	 $("#householdConf_left").attr('disabled',false);
	 $("#householdConf_right").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/basedatamanage/efficiencyanalysis/beshousehold_instBranch",
			          type: "post",
			          data:({     
			        			fFhbh:_fhbh,
			        			fZlbh:f_yhbh
			    	  }),
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在未选择表格中删除该条数据
		              		$("#householdBrc_noInclude").tabulator("deleteRow", _notincludecurRow);
				            //在已选择表格中添加该条数据
				             $('#householdBrc_include').tabulator("addRow", { fZlbh:data.data.fZlbh, fZlmc:data.data.fZlmc});
				           		//未包含用户表格：
					            var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
					             $("#householdBrc_noInclude").tabulator("setData", noinclude_group_data);
					            //已包含用户表格：
					            var include_group_data = $("#householdBrc_include").tabulator("getData");
					             $("#householdBrc_include").tabulator("setData", include_group_data);
					             if(noinclude_group_data.length == 0){					            	 
					            	 $("#householdConf_right").attr('disabled',true);
					            	 $("#householdConf_left").attr('disabled',false);
					             }
			            } else{
			                swal(data.msg,"", "error");
			            }
			          },
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
	});

	//移除分项中的支路
 	$(document).on('click','#householdBrc_include button.deleteBranch', function () {
     			var f_yhbh = $(this).data("id");
           	 $("#householdConf_left").attr('disabled',false);
        	 $("#householdConf_right").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/basedatamanage/efficiencyanalysis/beshousehold_delBranch",
			          type: "post",
			          data:({     
			        	  fZlbh:f_yhbh
			    	  }),
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在已选择表格中删除该条数据
		              		$("#householdBrc_include").tabulator("deleteRow", _includecurRow);
				            //在未选择表格中添加该条数据
				            $('#householdBrc_noInclude').tabulator("addRow", { fZlbh:data.data.fZlbh, fZlmc:data.data.fZlmc});
				            
				            //未包含用户表格：
				            var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
				             $("#householdBrc_noInclude").tabulator("setData", noinclude_group_data);
				            //已包含用户表格：
				            var include_group_data = $("#householdBrc_include").tabulator("getData");
				             $("#householdBrc_include").tabulator("setData", include_group_data);
				             if(include_group_data.length == 0){					            	 
				            	 $("#householdConf_left").attr('disabled',true);
				            	 $("#householdConf_right").attr('disabled',false);
				             }
			            } else{
			                swal(data.msg,"", "error");
			            }
			          },
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
	});

	//设置“未选择”格式
	var optIconNoIncludeFunc = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().fZlbh;
		return "<button class='btn btn-white btn-sm insertBranch' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
	};
	//设置“已选择”格式
	var optIconInclude = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().fZlbh;
		return "<button class='btn btn-white btn-sm deleteBranch' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
	};

	//创建并设置“未选择”table属性
	$("#householdBrc_noInclude").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
		{title:"支路编号", field:"fZlbh", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"支路名称", field:"fZlmc",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconNoIncludeFunc,headerSort:false},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});
	
	//创建并设置“已选择”table属性
	$("#householdBrc_include").tabulator({
		height:"93.3%",
		layout:"fitColumns",
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		//movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
		{title:"支路编号", field:"fZlbh", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"支路名称", field:"fZlmc",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconInclude,headerSort:false},
		],
		rowClick:function(e, in_row){
        	_includecurRow = in_row;
    	},
	});
	
	//加载未选择用户表
	function loadNoIncludeBrc(id,keywords){	

    $.ajax({
        url: _ctx + "/view/basedatamanage/efficiencyanalysis/loadNoBrc",
        type: "post",
        data:({
        	fFhbh: id,
        	keywords: keywords
        }),
        success: function(data) {

        	//填充“未选择”数据
			if(data.hasOwnProperty('list')==false){
	            $("#householdBrc_noInclude").tabulator("setData", []);
			}else{
	            $("#householdBrc_noInclude").tabulator("setData", data.list);
			}
	        var noinclude_group_data = $("#householdBrc_noInclude").tabulator("getData");
	        if(noinclude_group_data.length == 0){					            	 
	       	 $("#householdConf_right").attr('disabled',true);
	       	 $("#householdConf_left").attr('disabled',false);
	        }else{
		       	 $("#householdConf_right").attr('disabled',false);
	        }
       },
       error: function(data) {
            	 swal("修改失败!", data.msg, "error");
       }
      });
	}
	
	//加载已选择用户表
	function loadIncludeBrc(id,keywords){
    $.ajax({
        url: _ctx + "/view/basedatamanage/efficiencyanalysis/loadInBrc",
        type: "post",
        data:({
        	fFhbh: id,
        	keywords: keywords
        }),
        success: function(data) {
        	//填充“已选择”数据
			if(data.hasOwnProperty('list')==false){
	            $("#householdBrc_include").tabulator("setData", []);
			}else{
	            $("#householdBrc_include").tabulator("setData", data.list);
			}

	        var include_group_data = $("#householdBrc_include").tabulator("getData");
	        if(include_group_data.length == 0){					            	 
	       	 $("#householdConf_left").attr('disabled',true);
	       	 $("#householdConf_right").attr('disabled',false); 
	        }else{
		       	 $("#householdConf_left").attr('disabled',false);	
	        }
       },
       error: function(data) {
            	 swal("修改失败!", data.msg, "error");
       }
      });
	}
	
	return {
		
		//生成园区树
		  get_yqtree_sub : function (){

		 		$.ajax({
					type : "get",
					url : _ctx + '/view/basedatamanage/efficiencyanalysis/park_tree',
					success : function(data) {

						//var ops = "<option value=''>请选择园区名称</option>";
						var ops = "";
						for (var i = 0; i < data.list.length; i++) {
							var obj = data.list[i];
							ops += '<option value="'+obj.f_yqbh+'">';
							ops += obj.f_yqmc;
							ops += '</option>';
						}
						if(Selected_tree == null){
	 		            	_yqbh = data.list[0].f_yqbh;
	 		       			//传入能源树联动
	 		       			//get_altertree_esub(_yqbh);
	 		       			gettree_household(_yqbh);
	 		            }
						$("#btn_yqhouse").append(ops);
					},
					error : function(msg) {
						alert("select列表查询失败!");
					}
					

				}); 
		 		
		 	},
		 	
		 	get_yqtreehouse_sub1 : function (){
				var f_yqbh = $("#btn_yqhouse").val();
				    //传入能源树联动
		       			//get_altertree_esub(f_yqbh);
		       			gettree_household(f_yqbh);

 	          }, 
		//生成园区树
	 	/* get_yqtree_sub : function (){

			    $.ajax({
	 		        type: "post",
	 		        url: _ctx + "/view/basedatamanage/efficiencyanalysis/park_tree",
	 		        dataType: "json",
	 		        success: function (result) {
	 		            $('#sub_tree_yq').treeview({
	 		                data: result.list,         // 数据源
	 		                highlightSelected: true,    //是否高亮选中
	 		                levels : 4,
	 		                enableLinks : true,//必须在节点属性给出href属性
	 		                color: "#4a4747",
	 		               onNodeSelected: function (event, nodeData) {
	 		               $('#sub_tree_yq').treeview('clearSearch');//清除搜索选中高亮
	 		               		_yqbh = nodeData.id;
	 		               		_zzjgJs = nodeData.js;
	 		               //在组织机构按钮 显示 已选中的组织机构	
	 		               $("#btn_yqhouse").text(nodeData.id+'--'+nodeData.text);
	 		               gettree_household(_yqbh);
	 		               Selected_tree= $('#sub_tree_yq').treeview('getSelected');
	 		                } 		            
	 		            });
	 		            //如果被选中的节点为null，则默认选择第一个根节节点
	 		            if(Selected_tree == null){
	 		            	_yqbh = result.list[0].id;
	  		               $("#btn_yqhouse").text(_yqbh+'--'+result.list[0].text);
	 		       			//生成分项树
	 		       			gettree_household(_yqbh);
	 		            }
	 		        },
	 		        error: function (nodeData) {
	 		            swal( nodeData.msg,"", "error");
	 		        }
	 		    });  		 		
	 	}, */
		//搜索
		searchhousehold : function (){
			var householdInfo = $("#householdInfo").val();
	        $.ajax({
		    url: _ctx+'/view/basedatamanage/efficiencyanalysis/getHouseholdList',
		    type: "post",
		    data: {
		    	fFhmc:householdInfo,
		    },
	        beforeSend: function () {
	        	showLoad();	             
	            },
			success: function(data) {
				//$("#householdTable").tabulator("setData",data.list);
				if(data.hasOwnProperty('list')==false){
		            $("#householdTable").tabulator("setData", []);
				}else{
		            $("#householdTable").tabulator("setData", data.list);
				}

	        },
	        complete: function () {
	        	hiddenLoad();
	        },
	        error: function(data) {
	      	    swal( data.msg,"", "error");
	        }
		   });
		},
		
		//搜索已包含用户
		searchIncludeBrc : function (){
			var includeBrcKeywords = $("#includeBrcKeywords").val();
			loadIncludeBrc(_fhbh,includeBrcKeywords);
			//$("#householdBrc_include").tabulator("setData", _ctx+'/view/basedatamanage/loadGroupRlglUser?fFhbh='+_fhbh+'&keywords='+includeBrcKeywords);
		},
		
		//搜索未包含用户
		searchNotIncludeBrc : function (){
			var notincludeBrcKeywords = $("#notincludeBrcKeywords").val();
			loadNoIncludeBrc(_fhbh,notincludeBrcKeywords);
			//$("#householdBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeBrc?fFhbh='+_fhbh+'&keywords='+notincludeBrcKeywords);
		},
		
		//全部右移
		BrcrightMove : function (){
			var householdConf_tem = $("#householdBrc_noInclude").tabulator("getData");
			if(householdConf_tem.length < 1){
				$("#householdConf_right").attr('disabled',true);
			}else{
			$.ajax({
				url: _ctx + "/view/basedatamanage/efficiencyanalysis/beshousehold_rightmoveAll",
				type: "post",
				data:({     
				      fFhbh:_fhbh
				}),
			    success: function(data) {

			    		if (data.status == '1') {
			    			loadIncludeBrc(_fhbh);
							$("#householdBrc_noInclude").tabulator("setData",[]);
							$("#householdConf_right").attr('disabled',true);
							$("#householdConf_left").attr('disabled',false);
			    		} else{
			                swal(data.msg,"", "error");
			            }
			    		},
				error: function(data) {
					        swal( data.msg,"", "error");
					    }
				        });	
			}
		},
		//全部左移
		BrcleftMove : function (){
			var householdConf_tem = $("#householdBrc_include").tabulator("getData");
			if(householdConf_tem.length < 1){
				$("#householdConf_left").attr('disabled',true);
			}else{
			$.ajax({
				url: _ctx + "/view/basedatamanage/efficiencyanalysis/beshousehold_leftmoveAll",
				type: "post",
				data:({     
				      fFhbh:_fhbh
				}),
			    success: function(data) {
			            if (data.status == '1') {
			              	//swal(data.msg, "", "success");
			              	$("#householdBrc_include").tabulator("setData", []);
							//$("#householdBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeBrc?fFhbh='+_fhbh);
			              	loadNoIncludeBrc(_fhbh);
							$("#householdConf_right").attr('disabled',false);
							$("#householdConf_left").attr('disabled',true);
				            } else{
				                swal(data.msg,"", "error");
				            }
				          },
				          error: function(data) {
				          	 swal( data.msg,"", "error");
				          }
				        });
			}
		},
		
		//验证增加模态框是否弹出
		show_addhousehold : function () {

			if(_yqbh == ''){
				swal({ 
	        		title: "请选择园区",
	        		text: "经检测，您要未选择所属园区!",
	        		type: "warning",
	        		showCancelButton: false,
	        		confirmButtonColor: "#1ab394",
	        		confirmButtonText: "关闭",
	        		closeOnConfirm: false
	    		});
			}else{
				var Selectednode = $('#tree_household').treeview('getSelected');
				if(_yqlog == 1 && Selectednode.length == 0){
					swal({ 
	            		title: "请选择节点",
	            		text: "经检测，您要未选择分项树节点!",
	            		type: "warning",
	            		showCancelButton: false,
	            		confirmButtonColor: "#1ab394",
	            		confirmButtonText: "关闭",
	            		closeOnConfirm: false
	        		});
				}else{
					$('#modal-form-addhouseholdConf').modal('show');
					$.ajax({
			        	type: "POST", 
			        	url: _ctx + '/view/common/selectfNybhList', 
			        	data:"",
			        success: function(data){
					 	var ops="<option value=''>请选择能源编号</option>";
					 	for(var i=0;i<data.list.length;i++){
						 	var obj=data.list[i];
						 	ops+='<option value="'+obj.fNybh+'">';
						 	ops+=obj.fNybh+'('+obj.fNymc+')';
						 	ops+='</option>';
					 	}
					 	$("#fNybh_household").append(ops);
			        },
					 	error : function(msg) {
							alert("select列表查询失败!");
						}
					});
					$("#fYqbh_household").val(_yqbh);
				}
			}
		},
					/* fNybhhousehold('add');
					$("#fYqbh_household").val(_yqbh);
				}
			}
	   }, */

	pageInit : function(){
		basedatamanage_efficiencyanalysis_householdConf.get_yqtree_sub();
		
	}
}

})(jQuery, window, document);

basedatamanage_efficiencyanalysis_householdConf.pageInit();
</script>