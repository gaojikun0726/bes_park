<!-----内容区域---->

<div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
           <a id="addbranchalertConf" href="javascript:void(-1);" onclick="basedatamanage_efficiencyanalysis_branchalertConf.show_addbranchalert();" class="btn btn-primary" >
                	 <i class="fa fa-plus"></i>增加
            </a>
            <a id="importbranchalert" class="btn btn-primary" data-toggle="modal"  href="#modal-form-importbranchalert" >
                	<i class="fa fa-mail-reply"></i>导入 
            </a>
            <a id="exportbranchalert" class="btn btn-primary" data-toggle="modal"  href="#modal-form-exportbranchalert">
                	 <i class="fa fa-mail-forward"></i>导出
            </a>
    	</div>
        <div class="zc_search find">
        	<div class="zc_search_form">
            <input id="branchalertInfo" name="branchalertInfo" value="" placeholder="报警名称、编号"> 
				<button id="queryBranchAlert" onclick="basedatamanage_efficiencyanalysis_branchalertConf.searchbranchalert()"></button>
            </div>
       	</div>
       	<!-- 生成园区与能源联动下拉框  -->
       	<div class="has-success" style="margin-left: 50px;">
    <!--  <label style=" display:inline-block ;margin-left: 50px;">系统</label> -->
       	<div style = "display:inline-block ;margin-left: -584px;">
       	 <input type="hidden" id="gnzd_xt_list" class="form-control">
 		<select id='btn_yq' name='btn_yq' required class="form-control selectpicker" style="height:34px;width:100%; border: 1px solid #bbb;
						border-radius: 6px;background-color:rgb(216, 239, 255);color:#000000;"
				onchange = "basedatamanage_efficiencyanalysis_branchalertConf.get_altertree_sub1()"  data-live-search="false">
		</select> 
		</div>
		<!-- <label style=" display:inline-block ;margin-left: 50px;">模块</label> -->
		<div style = "display:inline-block ;margin-left: 15px;">
 		<select id='btn_ny' name='btn_ny' required class="form-control selectpicker" style="height:35px;width:115%; border: 1px solid #bbb;
		border-radius: 6px;background-color:rgb(216, 239, 255);color:#000000;" 
		onchange = "basedatamanage_efficiencyanalysis_branchalertConf.get_altertree_sub2()" data-live-search="false">
		</select> 
		</div>
		 </div>
</div>
   </div>
 </div>

<div class="leftarea" id="leftaltertreeconf" style="margin-top:-9px;height:93%">
	 <!-- <button id="btn_yq" type="button" class="btn btn-primary" data-toggle="modal" data-target='#modal-yqtree'style="width:100%;height:5%">
		请先选择园区&nbsp; <i class="fa fa-angle-double-right"></i>
	</button>  --> 
	<div id="tree_branchalert"></div>
</div>

<div style=" float: right;height:93%;width:85%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%)">
		<div id="branchalertTable">	</div>
   </div>
</div>



<!---添加支路报警信息开始-----> 
<div class="modal fade" id="modal-form-addbranchalertConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加支路报警信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addbranchalertConfform" name="addbranchalertConfform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fAlertname" name="fAlertname" placeholder="请输入分户名称"  required class="form-control">
                        </div>
                    </div>      
                    <div class="form-group">
                        <label class="col-sm-3 control-label">运算公式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fOperator" name="fOperator" placeholder="请输入分户人数"  required class="form-control">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">范围类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="fRangetype" name="fRangetype" class="form-control selectpicker" >
								　　　　<option value= "0">最高阈值</option>
								　　　　<option value= "1">最小阈值</option>
								　　　　<option value= "2">阈值范围</option>
									 <option value= "3">准确值</option>
								</select>
                            <!-- <input type="text" id="fRangetype" name="fRangetype" placeholder="请输入分户面积"  required class="form-control"> -->
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">不高于<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fNomore" name="fNomore" placeholder="请输入所属位置"  required class="form-control">
                        </div>
                    </div>  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">不低于<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                           <input type="text" id="fNoless" name="fNoless" value="" class="form-control" >	
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">等于<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
							 <input type="text" id="fEqual" name="fEqual" value="" class="form-control" >					
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使能<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        <select id="fActive" name="fActive" class="form-control selectpicker" >
								　　　　<option value= "0">使能</option>
								　　　　<option value= "1">否</option>
								</select>
							<!--  <input type="text" id="fActive" name="fActive" value="" class="form-control" >	 -->				
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

<!----编辑支路报警--->
<div class="modal fade" id="editBranchalertForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑支路报警信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_BranchAlertForm" name="edit_BranchAlertForm" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fAlertname">报警名称  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fAlertname" name="edit_fAlertname"  required class="form-control" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fOperator">运算公式<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fOperator" name="edit_fOperator"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fRangetype">范围类型<span class="text-danger">*</span></label>
					<div class="col-sm-8">
					<select id="edit_fRangetype" name="edit_fRangetype" class="form-control selectpicker" >
								　　　　<option value= "0">最高阈值</option>
								　　　　<option value= "1">最小阈值</option>
								　　　　<option value= "2">阈值范围</option>
									 <option value= "3">准确值</option>
					</select>
			<!-- <input type="text" id="edit_fRangetype" name="edit_fRangetype"  required class="form-control"> -->
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fNomore">不高于<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fNomore" name="edit_fNomore"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fNoless">不低于<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fNoless" name="edit_fNoless"  required class="form-control">
					</div>
				</div>
                <div class="form-group">
                        <label class="col-sm-3 control-label" for="edit_fEqual">等于<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <!-- <select id="edit_fEqual" name="edit_fEqual" required class="form-control">
                            </select> -->
                           <input type="text" id="edit_fEqual" name="edit_fEqual"  required class="form-control">
                            
                        </div>
                </div>
 				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fActive">使能<span class="text-danger">*</span></label>
					<div class="col-sm-8">
					<select id="edit_fActive" name="edit_fActive" class="form-control selectpicker" >
					　　　　	<option value= "0">使能</option>
					　　　　	<option value= "1">否</option>
					</select>
						<!-- <input type="text" id="edit_fActive" name="edit_fActive"  required class="form-control" > -->
					</div>
				</div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4">
						<button class="btn btn-md btn-primary " type="submit">
						<strong>更新支路报警信息</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
				</form>
            </div>
        </div>
    </div>
</div>
<!--  ;
--包含支路支路-
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
未选择table+搜索）-
            	<div class="notIncludeCss" >
            	
         <div style="padding:2px 2px 2px 10px;height:6%;">
		     <div style="float:left;">
		     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="notincludeBrcKeywords" name="notincludeBrcKeywords" value="" placeholder="支路编号、名称"> 
		     </div>
		     <div style="float:left;">
		     <span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBrc" onclick="basedatamanage_efficiencyanalysis_branchalertConf.searchNotIncludeBrc()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
		     </div>
		     
		   
       	</div>
            	  
       		
            	<div id="householdBrc_noInclude" style="margin-top:10px;overflow: auto;" >
            	</div>
            	</div>
未选择用户结束-
            	
            	
操作开始-
            	<div style="width:100px;height:400px;float:left">
            	<div id="BrcrightMove" style="margin-top:200px;margin-left:23px;"><button id="householdConf_right" type="button" onclick="basedatamanage_efficiencyanalysis_branchalertConf.BrcrightMove()" class="btn btn-primary">>></button></div>
            	<div id="BrcleftMove" style="margin-top:20px;margin-left:23px;"><button id="householdConf_left" type="button"  onclick="basedatamanage_efficiencyanalysis_branchalertConf.BrcleftMove()" class="btn btn-primary"><<</button></div>
            	</div>
操作结束-
            	
            	
包含用户开始-
            	<div class="includeCss">
            	
        <div style="position: relative;padding:2px 2px 2px 10px;height:6%;">
        <div style="float:left;">
		     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="includeBrcKeywords" name="includeBrcKeywords" value="" placeholder="分户名称、编号"> 
		     </div>
		     <div style="float:left;">
		     <span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryIncludeBrc" onclick="basedatamanage_efficiencyanalysis_branchalertConf.searchIncludeBrc()">
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
包含用户结束-
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
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
</style> -->



<script type="text/javascript">
;
var basedatamanage_efficiencyanalysis_branchalertConf = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _branchZlbh = "00";//分户配置编码
	var _branchJs = "0";//用户组对应的级数
	var _fhbh = "00";
	var _yqbh = "";//园区编号
	var _Nybh = "";//能源编号
	var _yqlog = "0";//判断组织机构下是否有分项配置
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	var Selected_tree = null;//组织机构树被选中的节点
	var Selected_sub = null;//分项树被选中的节点
 	var branchalertid = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		branchalertid = cell.getRow().getData().fId;
		return "<div class='btn-group '>"
				/* +"<button class='btn btn-white btn-sm include' data-id=" + branchalertid + " data-toggle='modal' data-target='#includeHouseholdBrc'><i class='fa fa-user'></i>  包含支路</button>" */
				+"<button class='btn btn-white btn-sm edit' data-id="+ branchalertid + " data-toggle='modal' data-target='#editBranchalertForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + branchalertid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};


	//创建并设置table属性
	$("#branchalertTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",align:"center"}, //frozen column
		{title:"报警名称", field:"fAlertname", width:85,sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"运算公式", field:"fOperator",width:100,sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first 
		{title:"范围类型", field:"fRangetype",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"不高于", field:"fNomore",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"不低于", field:"fNoless",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"等于", field:"fEqual",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"使能", field:"fActive",width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width : 250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
    	rowClick:function(e, row){

    		_curRow = row; 
        	$("#branchalertTable").tabulator("selectRow", 1);
    	},
	});
	
	//填充数据---按照组织机构=》分项=》列表顺序加载，所以不直接填充数据
	//$("#branchalertTable").tabulator("setData", _ctx+'/view/basedatamanage/getHouseholdList');

	
	$(window).resize(function(){
		$("#branchalertTable").tabulator("redraw");
	});

	//加载支路树
	function gettree_branchalert(_yqbh,_Nybh) {

	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/basedatamanage/efficiencyanalysis/branchalert_tree",
	        data:({     
			      fYqbh:_yqbh,
			      fNybh:_Nybh
			}),
	        dataType: "json",
	        success: function (result) {
	        	if(result.list == undefined){
	        		$('#tree_branchalert').treeview('remove');
	        		$("#branchalertTable").tabulator("setData", []);
	        		_branchJs = "1";
	        		_yqlog = "0";
	        	}else{
               	_yqlog = "1";
	            $('#tree_branchalert').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) {
	               $('#tree_branchalert').treeview('clearSearch');//清除搜索选中高亮
	               		_branchZlbh = nodeData.id;
	               		_branchJs = nodeData.level;
	               		getbranchalert_chlildtree(_branchZlbh,_branchJs);
	               		Selected_sub = $('#tree_branchalert').treeview('getSelected');
	                }
	            });
		           //初始加载根节点
// 	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
// 	            	if(result.list.length >0){//如果包含判断是否长度大于0
// 			            var firstNode = $("#tree_branchalert").treeview('findNodes',[result.list[0].id,'id']);
// 			        	$("#tree_branchalert").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
// 	            	}
// 	            }
		            if(Selected_sub == null){
		            	_branchZlbh = result.list[0].id;
		            	_branchJs   = result.list[0].level;
		            	var firstNode = $("#tree_branchalert").treeview('findNodes',[result.list[0].id,'id']);
			        	$("#tree_branchalert").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
 		       			//获取子节点
	               		getbranchalert_chlildtree(_branchZlbh,_branchJs);
 		            }
	        	}
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	} 
	function getbranchalert_chlildtree(_branchZlbh,_branchJs){
        $.ajax({
    	    url: _ctx + "/view/basedatamanage/efficiencyanalysis/branchalert_chlildtreegrid",
    	    type: "post",
    	    data: {
    	    	fZlbh:_branchZlbh,
    	    	f_js :_branchJs
    	    },
			success: function(nodeData2) {

			if(nodeData2.hasOwnProperty('list')==false){
	            $("#branchalertTable").tabulator("setData", []);
			}else{
	            $("#branchalertTable").tabulator("setData", nodeData2.list);
			}
            },
            error: function(nodeData2) {
          	    swal( nodeData2.msg,"", "error");
            }
	   });
		
	}

	//触发搜索的回车时间
	$("#branchalertInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				basedatamanage_efficiencyanalysis_branchalertConf.searchbranchalert();//触发该事件
			} 
		 })
	});

	//添加用户组表单验证
    var groupValidator = $("#addbranchalertConfform").validate({
	     rules: {
	    	 fAlertname: {
	             required: true,
	             rangelength: [1, 40]
	         }
	     },
	     messages: {
	    	 fAlertname: {
	             required: "请填写分项名称",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
	     submitHandler: function (nodeData) {
	         addformbranchalert(nodeData);
	     }
 	});
 	
 	//新增添加
	function addformbranchalert(nodeData) {
		if(_yqlog == 0){
 			_branchZlbh = "";
 		}
		$.ajax({
			url: _ctx + "/view/basedatamanage/efficiencyanalysis/insbranchalert",
			type : "post",
			contentType : "application/json; charset=utf-8",
			data: JSON.stringify({
				   //fZlbh:$("#fZlbh").val(),
		    	   fAlertname:$("#fAlertname").val(),
		    	   fNoless:$("#fNoless").val(),
		    	   fEqual:$("#fEqual").val(),
		    	   fOperator:$("#fOperator").val(),
		    	   fRangetype:$("#fRangetype").val(), 
		    	   fNomore:$("#fNomore").val(),
		    	   fActive:$("#fActive").val(),
		    	   fZlbh:_branchZlbh,
				   fJs:_branchJs 
		 		}),
			success : function(data) {

				if (data.status === '1') {
					swal({ 
				            title: data.msg,
				            text: "",
				            type: "success",
				            showCloseButton:false,
				            allowOutsideClick:false,
				            showConfirmButton: false,
				            timer:1000
				        });
					//setTimeout(function() {
						$('#modal-form-addbranchalertConf').modal(
								'hide');//关闭添加窗口
						//在表格中添加数据
						$('#branchalertTable').tabulator("addRow", {
							fId:data.data.fId,
							fAlertname : data.data.fAlertname,
							fNoless : data.data.fNoless,
							fEqual : data.data.fEqual,
							fRangetype : data.data.fRangetype,
							fOperator : data.data.fOperator,
							fNomore: data.data.fNomore,
							fActive: data.data. fActive,
							fCrdate : data.data.fCrdate,
							fChdate : data.data.fChdate
						});
				} else {
					swal(data.msg, "", "error");
				}
			},
			error : function(data) {
				swal(data.msg, "", "error");
			}
		});
		return false;
	}

	//居中显示（添加）
	$('#modal-form-addbranchalertConf').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-addbranchalertConf .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
        'margin-top': modalHeight  
    }); 
	})
	
	//居中显示（编辑）
 	$('#editBranchalertForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editBranchalertForm .modal-dialog').height() / 2;  
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
    $("#modal-form-addbranchalertConf").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#fNoless").empty();
        groupValidator.resetForm();  
    });
	
    
	     $(document).on('click','#branchalertTable button.delete',function() {

					//var id = $(this).data("branchalertid").toString();
					//var id= sysmanage_eqmanage_bessbtreenodetype_page.getCurRow().getData().fId
					var id=$(this).data("id");
					swal({
						title : "您确定要删除吗?",
						text : "信息删除后将不可恢复!",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#1ab394",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {
						$.ajax({
							url : _ctx
									+ "/view/basedatamanage/efficiencyanalysis/del_branchalert",
							type : "post",
							data : {
								"fId" : id
							},
							beforeSend : function() {
								showLoad();
							},
							success : function(data) {
								if (data.status === '1') {
									//swal(data.msg, "", "success");
									swal({ 
								            title: data.msg,
								            text: "",
								            type: "success",
								            showCloseButton:false,
								            allowOutsideClick:false,
								            showConfirmButton: false,
								            timer:1000
					        			});
									$("#branchalertTable").tabulator(
											"deleteRow",_curRow);
									var getGroupTable = $("#branchalertTable").tabulator("getData");
				              		$("#branchalertTable").tabulator("setData", getGroupTable);
								} else {
									swal(data.msg, "", "error");
								}
							},
							complete : function() {
								hiddenLoad();
							},
							error : function(data) {
								swal(data.msg, "", "error");
							}
						});
					});
				}); 

 	//表单验证
	$("#edit_BranchAlertForm").validate({
  	 submitHandler: function(form) {
    	 editBranchalertForm(form);
  	 }
 	});

 	
	function editBranchalertForm(form) {

		$.ajax({
			url : _ctx + "/view/basedatamanage/efficiencyanalysis/updbranchalert",
			type : "post",
			data:({
				fId:branchalertid,
				fAlertname: $("#edit_fAlertname").val(),
     			fOperator: $("#edit_fOperator").val(),
     			fRangetype: $("#edit_fRangetype").val(),
     			fNomore: $("#edit_fNomore").val(),
     			fNoless: $("#edit_fNoless").val(),
     			fEqual: $("#edit_fEqual").val(),
     			fActive: $("#edit_fActive").val(),
     		}),
			success : function(data) {

				if (data.status === '1') {

					swal({ 
			            title: data.msg,
			            text: "",
			            type: "success",
			            showCloseButton:false,
			            allowOutsideClick:false,
			            showConfirmButton: false,
			            timer:1000
			        });
					$('#branchalertTable').tabulator(
							"updateRow",_curRow, {
								fAlertname : data.data.fAlertname,
								fNoless : data.data.fNoless,
								fEqual : data.data.fEqual,
								fRangetype : data.data.fRangetype,
								fOperator : data.data.fOperator,
								fNomore: data.data.fNomore,
								fActive: data.data. fActive,
								fChdate : data.data.fChdate
							});
						$('#editBranchalertForm').modal('hide');//关闭编辑窗口
				} else {
					swal("", data.msg, "error");
				}
			},
			error : function(data) {
				swal("", data.msg, "error");
			}
		});
	}
	

	//验证在模态框出现前加载
	$("#editBranchalertForm").on('show.bs.modal', function(event) {

		var button = $(event.relatedTarget);
		var id = button.data("id").toString();
				
				$.ajax({
					url : _ctx + "/view/basedatamanage/efficiencyanalysis/selbranchalert",
					type : "post",
					data : {
						"fId" : id
					},
					success : function(result) {

						 $("#edit_fAlertname").val(result.data.fAlertname);
				         $("#edit_fActive").val(result.data.fActive);
				         $("#edit_fOperator").val(result.data.fOperator);
				         $("#edit_fRangetype").val(result.data.fRangetype);
				         $("#edit_fNomore").val(result.data.fNomore);
				         $("#edit_fEqual").val(result.data.fEqual);
				         $("#edit_fNoless").val(result.data.fNoless);
					}
				});
			
		});
 

 	//验证在模态框出现前加载编辑(组织机构树)
 	$("#modal-yqtree").on('show.bs.modal', function() {
 		basedatamanage_efficiencyanalysis_branchalertConf.get_altertree_sub();
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
	//关联能源下拉框
	  function get_altertree_esub(_yqbh){
		  $("#btn_ny").empty(); 

 	    $.ajax({
		type : "post",
		url : _ctx + '/view/basedatamanage/efficiencyanalysis/getgllist',
		data:{
			f_yqbh:_yqbh
		},
		success : function(data) {

			//var ops = "<option value=''>请选择园区名称</option>";
			var ops = "";
			for (var i = 0; i < data.list.length; i++) {
				var obj = data.list[i];
				ops += '<option value="'+obj.F_NYBH+'">';
				ops += obj.F_NYMC;
				ops += '</option>';
			}
			if(Selected_tree == null){
				_Nybh = data.list[0].F_NYBH;
	       			//生成分项树

	       			gettree_branchalert(_yqbh,_Nybh);
	            }
			$("#btn_ny").append(ops);
		},
		error : function(msg) {
			alert("select列表查询失败!");
		}

	});
 		};	
	return {
		//生成园区树
	  get_altertree_sub : function (){

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
 		       			get_altertree_esub(_yqbh);
 		       			//gettree_branchalert(_yqbh);
 		            }
					$("#btn_yq").append(ops);
				},
				error : function(msg) {
					alert("select列表查询失败!");
				}
				

			}); 
	 		
	 	},
		//生成园区树
	 	 get_altertree_sub1 : function (){
					var f_yqbh = $("#btn_yq").val();
					    //传入能源树联动
 		       			get_altertree_esub(f_yqbh);
 		       		    gettree_branchalert(_yqbh);
	 	}, 
	 	
		//生成能源树
	 	get_altertree_sub2 : function (){
					var fNybh = $("#btn_ny").val();
					_yqbh = $("#btn_yq").val();
 		       			//生成支路报警树
 		       			gettree_branchalert(_yqbh,fNybh);

	 	},
		//搜索
		searchbranchalert : function (){
			var branchalertInfo = $("#branchalertInfo").val();
	        $.ajax({
		    url: _ctx+'/view/basedatamanage/efficiencyanalysis/getAlertList',
		    type: "post",
		    data: {
		    	fAlertname:branchalertInfo,
		    },
	        beforeSend: function () {
	        	showLoad();	             
	            },
			success: function(data) {
				//$("#branchalertTable").tabulator("setData",data.list);
				if(data.hasOwnProperty('list')==false){
		            $("#branchalertTable").tabulator("setData", []);
				}else{
		            $("#branchalertTable").tabulator("setData", data.list);
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
		show_addbranchalert : function () {

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
				var Selectednode = $('#tree_branchalert').treeview('getSelected');
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
					$('#modal-form-addbranchalertConf').modal('show');
				}
			}
		},
				

	pageInit : function(){
		basedatamanage_efficiencyanalysis_branchalertConf.get_altertree_sub();
		
	}
}

})(jQuery, window, document);

basedatamanage_efficiencyanalysis_branchalertConf.pageInit();
</script>