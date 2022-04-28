<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择能源类型>>>
		</span>
	</div>
	<div id="tree_zzjg_CollM" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;采集方案定义列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addCollMethod" href="javascript:void(-1);" onclick="basedatamanage_enegrycollectionmanage_collMethod.show_addCollMethod();" class="btn btn-primary toLeft"> 
			<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
			</a>

            <div id='collMethod_yqmc' style = "display:inline-block ;margin-left: 15px;"></div>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="CollMethodInfo" name="CollMethodInfo" placeholder="采集方案名称、编号">
				  <button id="queryCollMethod" onclick="basedatamanage_enegrycollectionmanage_collMethod.getCollMethodList()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="CollMethodTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加采集方案开始-----> 
<div class="modal fade" id="modal-form-addCollM" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加采集方案</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addCollMform" name="addCollMform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">采集方案名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fCjfamc" name="fCjfamc" placeholder="请输入采集方案名称"  required class="form-control">
                        </div>
                    </div>      

					<#--<div class="form-group">
						<label class="col-sm-3 control-label">园区名称<span class="text-danger">&nbsp;</span></label>
						<div class="col-sm-8">
							<select id="fyqbh_collM" name="fNybh_CollM" required class="form-control">

							</select>
						</div>
					</div>-->
            <div class="form-group">
                        <label class="col-sm-3 control-label">能源类型<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <select id="fNybh_CollM" name="fNybh_CollM" required class="form-control">
                            
                            </select>
                        </div>
                    </div> 
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!----编辑采集方案--->
<div class="modal fade" id="editCollMForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑采集方案</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_CollMForm" name="edit_CollMForm" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fCjfabh">采集方案编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fCjfabh" name="edit_fCjfabh"  required class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fCjfamc">采集方案名称<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fCjfamc" name="edit_fCjfamc"  required class="form-control">
					</div>
				</div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">园区名称<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fyqbh_collM" disabled="disabled" name="edit_fyqbh_collM" required class="form-control">

                            </select>
                        </div>
                    </div>

				
                <div class="form-group">
                        <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fNybh_CollM"  name="edit_fNybh_CollM" required class="form-control">
                            </select>
                        </div>
                </div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4 display_flex">
						<button class="btn btn-md btn-primary " type="submit">
						<strong>确定</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
				</form>
            </div>
        </div>
    </div>
</div>


<!----包含能耗参数--->
<div class="modal fade" id="includeElectricP" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1080px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">包含能耗参数</h4>
            </div>
            <div class="modal-body" style="height:600px;">
            	<div style="float:left;width:50%"><button class="btn btn-md" style="cursor:default"><span>未选择</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>已选择</span></button></div>
<!--未选择table+搜索）- -->
            	<div class="notIncludeCss" >            	
         			<!-- <div style="padding:2px 2px 2px 10px;height:6%;">
		     		<div style="float:left;">
		     			<input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="notincludeEPKeywords" name="notincludeEPKeywords" value="" placeholder="能耗编号、名称"> 
		     		</div>
		     		<div style="float:left;">
		     			<span class="input-group-btn"  style="width: 60px;">
							<button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeEP" onclick="basedatamanage_enegrycollectionmanage_collMethod.searchNotIncludeEP()">
                				<i class="fa fa-search"></i> 搜索
                			</button>
            			</span>
		     		</div>	   
       				</div>  -->
       				<!-- 搜索框 -->
					<div class="zc_search_special find">
						  <input type="text" classzc_search_special_specialyle"  id="notincludeEPKeywords" name="notincludeEPKeywords" placeholder="能耗编号、名称">
						  <button id="queryNotIncludeEP"onclick="basedatamanage_enegrycollectionmanage_collMethod.searchNotIncludeEP()"><i class="fa fa-search" aria-hidden="true"></i></button>
					</div>     		
            		<div id="collMethod_noInclude" style="margin-top:10px;overflow: auto;" >
            		</div>
            	</div>
<!-- 未选择用户结束- -->
            	
            	
<!--操作开始- -->
            	<div style="width:100px;height:400px;float:left">
            		<div id="EPrightMove" style="margin-top:200px;margin-left:23px;"><button id="ElectricP_right" type="button" onclick="basedatamanage_enegrycollectionmanage_collMethod.EPrightMove()" class="btn btn-primary">>></button></div>
            		<div id="EPleftMove" style="margin-top:20px;margin-left:23px;"><button id="ElectricP_left" type="button"  onclick="basedatamanage_enegrycollectionmanage_collMethod.EPleftMove()" class="btn btn-primary"><<</button></div>
            	</div>
<!--操作结束- -->
            	
            	
<!--包含用户开始- -->
            	<div class="includeCss">            	
        			<!-- <div style="position: relative;padding:2px 2px 2px 10px;height:6%;">
        			<div style="float:left;">
		     			<input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="includeEPKeywords" name="includeEPKeywords" value="" placeholder="支路编号、名称"> 
		     		</div>
		     		<div style="float:left;">
		     			<span class="input-group-btn"  style="width: 60px;">
							<button class="btn btn-primary btn-sm m-b-none" id="queryIncludeEP" onclick="basedatamanage_enegrycollectionmanage_collMethod.searchIncludeEP()">
                				<i class="fa fa-search"></i> 搜索
                			</button>
            			</span>
		     		</div>       
       				</div> -->
       				<!-- 搜索框 -->
					<div class="zc_search_special find">
						  <input type="text" classzc_search_special_specialyle"  id="includeEPKeywords" name="includeEPKeywords" placeholder="支路编号、名称">
						  <button id="queryIncludeEP"onclick="basedatamanage_enegrycollectionmanage_collMethod.searchIncludeEP()"><i class="fa fa-search" aria-hidden="true"></i></button>
					</div>
            		<div id="collMethod_include" style="overflow: auto;margin-top:10px;" >
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
<style>
.includeCss{
	float: left;
    width: 490px;
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
    width: 410px;
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

<script>
;
var basedatamanage_enegrycollectionmanage_collMethod = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var _ugroupJs = "0";//用户组对应的级数
	var _fCjfabh = "00";
	var _zzjgbh = "";//组织机构编号
	var _zzjgJs = "0";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
    var park_id=[];//园区下拉表id
    var park_val=[];//园区下拉表值
    var yqbh="";//园区编号

    //所属园区selected
    function park_group_select(id,idArray,valArray){
        //选择电表下拉列表 空间选择
        $(id).ISSPSpinnerBox({
            width:'9vw',//下拉列表宽度
            height: '2.9vh',//下拉列表高度
            margLeft:'0px',//margin-left属性
            isHasData:true,
            idArray:idArray,//id
            valArray:valArray,//txt
            isNoSelectedText:false, //是否设置未选中提示文本
            liveSearch:false,
            callBack: parkChange,
        });
    }


    //新增时园区改变事件
    function parkChange(sp){
        yqbh=sp.id;//每次改变赋值给tableType
        basedatamanage_enegrycollectionmanage_collMethod.zzjg_tree(yqbh);
    }


    //园区下拉列表
    function  get_yqtree_sub() {

        $.issp_ajax({
            type: "get",
            url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
            async:false,
            success: function (data) {
                if (data.hasOwnProperty("list"))
                {
                    if(data.list.length > 0 )
                    {
                        for (var i = 0; i < data.list.length; i++) {
                            var obj = data.list[i];
                            park_id.push(obj.f_yqbh);
                            park_val.push(obj.f_yqmc);
                        }

                        yqbh = data.list[0].f_yqbh;
                        basedatamanage_enegrycollectionmanage_collMethod.zzjg_tree(yqbh);
                        park_group_select('#collMethod_yqmc',park_id,park_val);
                    }
                }
                else
                {
                    park_id.push("");
                    park_val.push("");
                    park_group_select('#collMethod_yqmc',park_id,park_val);

                }


            },
            error: function (data) {
                swal( data.msg,"", "error");
            }
        });
    }

	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var collMethod_id = cell.getRow().getData().fCjfabh;
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm include' data-id=" + collMethod_id + " data-toggle='modal' data-target='#includeElectricP'><i class='fa fa-user'></i>包含采集参数</button>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ collMethod_id + " data-toggle='modal' data-target='#editCollMForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + collMethod_id + "><i class='fa fa-trash'></i>  删除</button></div>"	};

	//创建并设置table属性
	$("#CollMethodTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",headerSort:false}, //frozen column
		{title:"编号", field:"fCjfabh", width:150,sorter:"string",align:"center",editor:false,headerSort:false}, //never hide this column
		{title:"名称", field:"fCjfamc",width:150,sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
		{title:"园区编号", field:"fZzjgbh", width:120,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"能源类型", field:"fNybh", width:120,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().fZzjgbh;
        	var choiseNode = $('#tree_zzjg_CollM').treeview('findNodes', [ _curRow.getData().fZzjgbh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_zzjg_CollM').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_zzjg_CollM').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    } 
    	},
	});

	//触发搜索的回车时间
	$("#CollMethodInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				basedatamanage_enegrycollectionmanage_collMethod.getCollMethodList();//触发该事件
			} 
		 })
	});
    //下拉框列表查询
    function fyqbhCollMethod(){
        $.ajax({
            type: "POST",
            url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
            data:"",
            beforeSend: function () {
                showLoad();
            },
            success: function(data){
                var ops="<option value=''>请选择园区编号</option>";
                for(var i=0;i<data.list.length;i++){
                    var obj=data.list[i];
                    ops+='<option value="'+obj.id+'">';
                    ops+=obj.id+'('+obj.text+')';
                    ops+='</option>';
                }
				$("#fyqbh_collM").append(ops);
                $("#edit_fyqbh_collM").append(ops);


            },
            complete: function () {
                hiddenLoad();
            },
            error:function(msg){
                swal( data.msg,"", "error");
            }
        });
    }




	//下拉框列表查询
	function fNybhCollMethod(keywords){
		$.ajax({
        	type: "POST", 
        	url: _ctx + '/view/common/selectfNybhList', 
        	data:"",
        	beforeSend: function () { 
        		showLoad();	             
        	},
        success: function(data){
		 	var ops="<option value=''>请选择能源编号</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.fNybh+'">';
			 	ops+=obj.fNybh+'('+obj.fNymc+')';
			 	ops+='</option>';
		 	}
		 	if(keywords == 'add'){
		 		$("#fNybh_CollM").append(ops);
		 	}else{
		 		$("#edit_fNybh_CollM").append(ops);	
		 	}
        }, 
        complete: function () {
        	hiddenLoad();
        },
        error:function(msg){
        	alert( "下拉框列表查询失败!" );  
        } 
    });
 	}

	
	//添加采集参数表单验证
    var groupValidator = $("#addCollMform").validate({
	     rules: {
	    	 fCjfamc: {
	             required: true,
	             rangelength: [1, 40]
	         }
	     },
	     messages: {
	         
	    	 fCjfamc: {
	             required: "请填写采集方案名称",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
	     submitHandler: function (nodeData) {
	    	 addformCollM(nodeData);
	     }
 	});
	
 	//新增保存
	function addformCollM(nodeData) {
	     $.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/add_CollM",
	       type: "post",
	       data:({     
	    	   fCjfamc:$("#fCjfamc").val(),
	    	   fZzjgbh:yqbh,
	    	   // fZzjgbh:$("#fyqbh_collM").val(),
	    	   fNybh:$("#fNybh_CollM").val()
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
		            $('#modal-form-addCollM').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#CollMethodTable').tabulator("addRow", data.data);
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
	    		hiddenLoad();
	    	},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	
	//居中显示（添加）
	$('#modal-form-addCollM').on('show.bs.modal', function () {
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-addCollM .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
        'margin-top': modalHeight  
    }); 
	})

	//居中显示（编辑）
 	$('#editCollMForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editCollMForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})

	//居中显示（包含用户）
 	$('#includeElectricP').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#includeElectricP .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-form-addCollM").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#fNybh_CollM").empty();
        groupValidator.resetForm();  
    });

	//删除数据
	$(document).on('click','#CollMethodTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "采集方案删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			// row.className="animated bounceOut";
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/del_CollM",
				type : "post",
				data : {"fCjfabh" : id
				},
				beforeSend: function () { 
		 			showLoad();	             
		 		},
				success : function(data) {
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
              		$("#CollMethodTable").tabulator("deleteRow", _curRow);

				} else {
					swal("删除失败!",data.msg,"error");
				}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(data) {
					swal("删除失败!",data.msg,"error");
				}
			});
			}, 100)
		});
	});
	
 	//表单验证
	$("#edit_CollMForm").validate({
  	 submitHandler: function(form) {
  		editCollMForm(form);
  	 }
 	});

 	//编辑支路
	function editCollMForm(form) {
   		$.ajax({
     		url: _ctx + "/view/basedatamanage/enegrycollectionmanage/edit_CollM",
     		type: "post",
     		data:({
     			fCjfabh:$("#edit_fCjfabh").val(),
     			fCjfamc: $("#edit_fCjfamc").val(),
     			fNybh: $("#edit_fNybh_CollM").val(),
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
	         		$('#CollMethodTable').tabulator("updateRow",_curRow, 
	         				{fCjfamc:data.data.fCjfamc,
	         				 fNybh:data.data.fNybh,
	         				 fChdate:data.data.fChdate});
	              	$('#editCollMForm').modal('hide');//关闭编辑窗口
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
	}
 
 	//验证在模态框出现前加载编辑
 	$("#editCollMForm").on('show.bs.modal', function(event) {
        $("#edit_fNybh_CollM").empty();
        fNybhCollMethod('edit');
        setTimeout(function(){
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取用户组编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/queryCollM",
	       type: "post", 
	       data:{     
	 			"fCjfabh":id
	 		},
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(result) {
	         $("#edit_fCjfabh").val(result.data.fCjfabh);
	         $("#edit_fCjfamc").val(result.data.fCjfamc);
	         $("#edit_fyqbh_collM").val(result.data.fZzjgbh);
	         $("#edit_fNybh_CollM").val(result.data.fNybh);
	         },
	 		complete: function () {
        		hiddenLoad();
        	},
          error: function(data) {
          	 swal( data.msg,"", "error");
          }
   		});  
 		},100)
 	});

 	
	//验证码在模态框出现前加载包含用户(可拖动)
	$("#includeElectricP").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	  	loadNoIncludeEP(id);
	  	loadIncludeEP(id);
		_fCjfabh = id;
	});

	//采集方案中添加能耗参数
 	$(document).on('click','#collMethod_noInclude button.insertElectricP', function () {
     	var fDnbh = $(this).data("id");
   	 	$("#ElectricP_left").attr('disabled',false);
	 	$("#ElectricP_right").attr('disabled',false);
	       $.ajax({
			   url: _ctx + "/view/basedatamanage/enegrycollectionmanage/CollMethod_insertElectricP",
			   type: "post",
			   data:({     
			       fCjfabh:_fCjfabh,
			       fNhbh:fDnbh,
			       fStatisticalParam : "0"
			    }),
			    beforeSend: function () { 
			    	showLoad();	             
			    },
		       success: function(data) {
		          if (data.status == '1') {
		             //swal(data.msg, "", "success");
		             //在未选择表格中删除该条数据
		             $("#collMethod_noInclude").tabulator("deleteRow", _notincludecurRow);
				     //在已选择表格中添加该条数据
				     loadIncludeEP(_fCjfabh);
				     //$('#collMethod_include').tabulator("addRow", {fDnbh:data.data.fDnbh, fDnmc:data.data.fDnmc, fNybh:data.data.fNybh});
				     //未包含用户表格：
					 var noinclude_collM_data = $("#collMethod_noInclude").tabulator("getData");
					 $("#collMethod_noInclude").tabulator("setData", noinclude_collM_data);
					 //已包含用户表格：
					 var include_collM_data = $("#collMethod_include").tabulator("getData");
					 $("#collMethod_include").tabulator("setData", include_collM_data);
					 	if(noinclude_collM_data.length == 0){					            	 
					    	$("#ElectricP_right").attr('disabled',true);
					    	$("#ElectricP_left").attr('disabled',false);
					  	}
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
	});
	//采集方案中移除能耗参数
 	$(document).on('click','#collMethod_include button.deleteElectricP', function () {
     			var fDnbh = $(this).data("id");
     			swal({
   					title : "您确定要移除吗?",
   					text : "数据分析将采集不到数据！",
   					type : "warning",
   					showCancelButton : true,
   					confirmButtonColor : "#1ab394",
   					confirmButtonText : "确定",
   					closeOnConfirm : true
     				},
     				function() {
					 $("#ElectricP_left").attr('disabled',false);
		        	 $("#ElectricP_right").attr('disabled',false);
		            	$.ajax({
				          url: _ctx + "/view/basedatamanage/enegrycollectionmanage/CollMethod_deleteElectricP",
				          type: "post",
				          data:({     
				        	  fNhbh:fDnbh,
                              fCjfabh:_fCjfabh
				    	  }),
				    	  beforeSend: function () { 
					        	showLoad();	             
					        },
			          	  success: function(data) {
			              	if (data.status == '1') {
			              		//swal(data.msg, "", "success");
			              		//在已选择表格中删除该条数据
			              		//$("#collMethod_include").tabulator("deleteRow", _includecurRow);
			              		loadIncludeEP(_fCjfabh);
					            //在未选择表格中添加该条数据
					            $('#collMethod_noInclude').tabulator("addRow", {fDnbh:data.data.fDnbh, fDnmc:data.data.fDnmc, fNybh:data.data.fNybh});
					            
					            //未包含用户表格：
					            var noinclude_collM_data = $("#collMethod_noInclude").tabulator("getData");
					             $("#collMethod_noInclude").tabulator("setData", noinclude_collM_data);
					            //已包含用户表格：
					            var include_collM_data = $("#collMethod_include").tabulator("getData");
					             $("#collMethod_include").tabulator("setData", include_collM_data);
					             if(include_collM_data.length == 0){					            	 
					            	 $("#ElectricP_left").attr('disabled',true);
					            	 $("#ElectricP_right").attr('disabled',false);
					             }
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
     				});
           	
	});
	
	//设置“未选择”格式
	var optIconNoIncludeFunc = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().fDnbh;
		return "<button class='btn btn-white btn-sm insertElectricP' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
	};
	//设置“已选择”格式
	var optIconInclude = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().fDnbh;
		return "<button class='btn btn-white btn-sm deleteElectricP' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
	};
	var tabselect = function(cell,formatterParams){
		var fStatisticalParam = cell.getRow().getData().fStatisticalParam;
		var fDnbh = cell.getRow().getData().fDnbh;
		if(fStatisticalParam == "1"){
			return "<select id='sel"+fDnbh+"' class='selector' disabled='disabled' style='width: 5em; height: 2em;' onchange='basedatamanage_enegrycollectionmanage_collMethod.update_inclu_fStatisticalParam()'> "
			+"<option value='1'>"+"是"+"</option>"
			+"<option value='0'>"+"否"+"</option>"
			+"</select>"
		}else{
			return "<select id='sel"+fDnbh+"' class='selector' style='width: 5em; height: 2em;' onchange='basedatamanage_enegrycollectionmanage_collMethod.update_inclu_fStatisticalParam()'> "
			+"<option value='1'>"+"是"+"</option>"
			+"<option value='0'>"+"否"+"</option>"
			+"</select>"
		}
					
	};

	// 变比选择下拉框
	var tabSelectRate = function(cell,formatterParams){
		var fDnbh = cell.getRow().getData().fDnbh;
        return "<select id='selRate"+fDnbh+"' class='selector' style='width: 5em; height: 2em;' onchange='basedatamanage_enegrycollectionmanage_collMethod.update_inclu_fIsRate()'> "
                +"<option value='1'>"+"是"+"</option>"
                +"<option value='0'>"+"否"+"</option>"
                +"</select>"

	};
	//创建并设置“未选择”table属性
	$("#collMethod_noInclude").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
// 		{title:"能耗编号", field:"fDnbh", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"参数名称", field:"fDnmc", width:120,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"能源类型", field:"fNybh",width:120,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:100,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconNoIncludeFunc,headerSort:false},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});
	
	//创建并设置“已选择”table属性
	$("#collMethod_include").tabulator({
		height:"93.3%",
		layout:"fitColumns",
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		//movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
		{title:"参数名称", field:"fDnmc", width:90,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"能源类型", field:"fNybh",width:90,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"统计参数", field:"fStatisticalParam",width:80,align:"center",formatter:tabselect,headerSort:false},
		{title:"变比", field:"fStatisticalParam",width:80,align:"center",formatter:tabSelectRate,headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconInclude,headerSort:false},
		],
		rowClick:function(e, in_row){
        	_includecurRow = in_row;
    	},
	});

	//加载未选择用户表
	function loadNoIncludeEP(id,keywords){	
    $.ajax({ 
        url: _ctx + "/view/basedatamanage/enegrycollectionmanage/loadNoIncludeCollM",
        type: "post",
        data:({
        	fCjfabh: id,
        	keywords: keywords
        }),
        beforeSend: function () { 
        	showLoad();	             
        },
        success: function(data) {
        	//填充“未选择”数据
			if(data.hasOwnProperty('list')==false){
	            $("#collMethod_noInclude").tabulator("setData", []);
			}else{
	            $("#collMethod_noInclude").tabulator("setData", data.list);
			}
	        var noinclude_collM_data = $("#collMethod_noInclude").tabulator("getData");
	        if(noinclude_collM_data.length == 0){					            	 
	       	 $("#ElectricP_right").attr('disabled',true);
	       	 $("#ElectricP_left").attr('disabled',false);
	        }else{
		       	 $("#ElectricP_right").attr('disabled',false);
	        }
       },
       complete: function () {
    		hiddenLoad();
    	},
       error: function(data) {
            	 swal("修改失败!", data.msg, "error");
       }
      });
	}

	//加载已选择用户列表
	function loadIncludeEP(id,keywords){
	    $.ajax({
	        url: _ctx + "/view/basedatamanage/enegrycollectionmanage/loadIncludeCollM",
	        type: "post",
	        data:({
	        	fCjfabh: id,
	        	keywords: keywords
	        }),
	        beforeSend: function () { 
	        	showLoad();	             
	        },
	        success: function(data) {
	        	//填充“已选择”数据
				if(data.hasOwnProperty('list')==false){
		            $("#collMethod_include").tabulator("setData", []);
				}else{
		            $("#collMethod_include").tabulator("setData", data.list);
				}
		        var include_collM_data = $("#collMethod_include").tabulator("getData");
		        if(include_collM_data.length == 0){					            	 
		       	 $("#ElectricP_left").attr('disabled',true);
		       	 $("#ElectricP_right").attr('disabled',false); 
		        }else{
			       	 $("#ElectricP_left").attr('disabled',false);
			       	 //设置已包含表格中 运算符列的值
						for (var i = 0; i < include_collM_data.length; i++) {

							var fNhbh = include_collM_data[i].fDnbh;
							var fStatisticalParam = include_collM_data[i].fStatisticalParam;
							var fIsRate = include_collM_data[i].fIsRate;
							$("#sel"+fNhbh+"").val(fStatisticalParam);
							$("#selRate"+fNhbh+"").val(fIsRate);

						}
		        }
	       },
	       complete: function () {
	    		hiddenLoad();
	    	},
	       error: function(data) {
	            	 swal("修改失败!", data.msg, "error");
	       }
	      });
		}
	return {
	//搜索
	getCollMethodList : function (){
		var CollMethodInfo = $("#CollMethodInfo").val();
        $.ajax({
	    url: _ctx+'/view/basedatamanage/enegrycollectionmanage/getCollMethodList',
	    type: "post",
	    data: {
	    	keywords:CollMethodInfo,
	    },
	    beforeSend: function () { 
	    	showLoad();	             
	    },
		success: function(data) {
			//$("#CollMethodTable").tabulator("setData",data.list);
			if(data.hasOwnProperty('list')==false){
	            $("#CollMethodTable").tabulator("setData", []);
			}else{
	            $("#CollMethodTable").tabulator("setData", data.list);
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
	
	//加载树
	zzjg_tree : function(yqbh) {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/basedatamanage/enegrycollectionmanage/getParkEnergyTree",
	        dataType: "json",
            data : {fyqbh : yqbh},
	        beforeSend: function () { 
	        	showLoad();	             
	        },
	        success: function (result) {

	            //初始加载根节点
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_zzjg_CollM').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	               onNodeSelected: function (event, nodeData) {
	               $('#tree_zzjg_CollM').treeview('clearSearch');//清除搜索选中高亮
                       var fyqbh ="";
                       var fnybh ="";
					   if(nodeData.pid!=null&&nodeData.pid!="")
					   {
                            fyqbh =nodeData.pid.split("_")[1];
                            fnybh =nodeData.id;
					   }
					   else
					   {
                           fyqbh =nodeData.id.split("_")[1];
					   }


	                    $.ajax({
	                	    url: _ctx + "/view/basedatamanage/enegrycollectionmanage/getCMbyZzjgbh",
	                	    type: "post",
	                	    data: {
								fyqbh:fyqbh,
								fnybh:fnybh
	                	    },
	                	    beforeSend: function () { 
	                	    	showLoad();	             
	                	    },
							success: function(nodeData2) {
							if(nodeData2.hasOwnProperty('list')==false){
					            $("#CollMethodTable").tabulator("setData", []);
							}else{
					            $("#CollMethodTable").tabulator("setData", nodeData2.list);
							}
				            },
				            complete: function () {
				            	hiddenLoad();
				            },
				            error: function(nodeData2) {
				          	    swal( nodeData2.msg,"", "error");
				            }
                	   });
	                }
	            });
                        var firstNode = $("#tree_zzjg_CollM").treeview('findNodes',[result.list[0].id,'id']);
                        $("#tree_zzjg_CollM").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
	
	//搜索已包含用户
	searchIncludeEP : function (){
		var includeEPKeywords = $("#includeEPKeywords").val();
		loadIncludeEP(_fCjfabh,includeEPKeywords);
		//$("#subitemBrc_include").tabulator("setData", _ctx+'/view/dataCenter/loadGroupRlglUser?fFxbh='+_fzbh+'&keywords='+includeBrcKeywords);
	},
	
	//搜索未包含用户
	searchNotIncludeEP : function (){
		var notincludeEPKeywords = $("#notincludeEPKeywords").val();
		loadNoIncludeEP(_fCjfabh,notincludeEPKeywords);
		//$("#collMethod_noInclude").tabulator("setData", _ctx+'/view/dataCenter/loadNoIncludeBrc?fFxbh='+_fzbh+'&keywords='+notincludeBrcKeywords);
	},
	
	//全部右移
	EPrightMove : function (){
		var collMethod_tem = $("#collMethod_noInclude").tabulator("getData");
		if(collMethod_tem.length < 1){
			$("#ElectricP_right").attr('disabled',true);
		}else{
		$.ajax({
			url: _ctx + "/view/basedatamanage/enegrycollectionmanage/CollMethod_rightmoveAll",
			type: "post",
			data:({     
				fCjfabh:_fCjfabh
			}),
			beforeSend: function () { 
				showLoad();	             
			},
		    success: function(data) {
		    		if (data.status == '1') {
		    			loadIncludeEP(_fCjfabh);
						$("#collMethod_noInclude").tabulator("setData",[]);
						$("#ElectricP_right").attr('disabled',true);
						$("#ElectricP_left").attr('disabled',false);
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
		}
	},
	
	//全部左移  EpleftMove
	EPleftMove : function (){
		var collMethod_tem = $("#collMethod_include").tabulator("getData");
		swal({
			title : "您确定要移除吗?",
			text : "数据分析将采集不到数据！",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : true
			},
			function() {
				if(collMethod_tem.length < 1){
					$("#ElectricP_left").attr('disabled',true);
				}else{
				$.ajax({
					url: _ctx + "/view/basedatamanage/enegrycollectionmanage/CollMethod_leftmoveAll",
					type: "post",
					data:({     
						fCjfabh:_fCjfabh
					}),
					beforeSend: function () { 
						showLoad();	             
					},
				    success: function(data) {
				            if (data.status == '1') {
				              	//swal(data.msg, "", "success");
				              	$("#collMethod_include").tabulator("setData", []);
								//$("#subitemBrc_noInclude").tabulator("setData", _ctx+'/view/dataCenter/loadNoIncludeBrc?fFxbh='+_fzbh);
				              	loadNoIncludeEP(_fCjfabh);
								$("#ElectricP_right").attr('disabled',false);
								$("#ElectricP_left").attr('disabled',true);
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
				}
			});
	
	},

	//验证增加模态框是否弹出
	show_addCollMethod : function () {
      var node = $('#tree_zzjg_CollM').treeview('getSelected');
				if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
					swal({ 
	            		title: "请选择节点",
	            		text: "经检测，您未选择组织机构!",
	            		type: "warning",
	            		showCancelButton: false,
	            		confirmButtonColor: "#1ab394",
	            		confirmButtonText: "关闭",
	            		closeOnConfirm: false
	        		});
				}else{
				$('#modal-form-addCollM').modal('show'); 
				$("#fZzjgbh_CollM").val(_zzjgbh);
				fNybhCollMethod('add');
				}
	},
	  update_inclu_fStatisticalParam : function (){
			var fDnbh = _includecurRow.getData().fDnbh;
			var fStatisticalParam = $("#sel"+fDnbh+"").val();
			$.ajax({
				url: _ctx + "/view/basedatamanage/enegrycollectionmanage/update_inclu_fStatisticalParam",
				type: "post",
				data:({     
					fNhbh : fDnbh,
					fCjfabh : _fCjfabh,
				    fStatisticalParam : fStatisticalParam
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
			  				loadIncludeEP(_fCjfabh);
				            } else{
				                swal(data.msg,"", "error");
				            }
				          },
				          error: function(data) {
				          	 swal( data.msg,"", "error");
				          }
				        });
			
		},
        update_inclu_fIsRate : function (){
            var fDnbh = _includecurRow.getData().fDnbh;
            var fIsRate = $("#selRate"+fDnbh+"").val();
            $.ajax({
                url: _ctx + "/view/basedatamanage/enegrycollectionmanage/update_inclu_fIsRate",
                type: "post",
                data:({
                    fNhbh : fDnbh,
                    fCjfabh : _fCjfabh,
                    fIsRate : fIsRate
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
                        loadIncludeEP(_fCjfabh);
                    } else{
                        swal(data.msg,"", "error");
                    }
                },
                error: function(data) {
                    swal( data.msg,"", "error");
                }
            });

        },
	pageInit : function(){
		//basedatamanage_enegrycollectionmanage_collMethod.getCollMethodList();
        fyqbhCollMethod();
        get_yqtree_sub();
	}
	}
})(jQuery, window, document);

basedatamanage_enegrycollectionmanage_collMethod.pageInit();
</script>