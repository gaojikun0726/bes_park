<!-----内容区域---->
 <!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择设备类型>>>
		</span>
	</div>
	<div id="tree_eqType" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备类型列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addeqType" href="javascript:void(-1);" onclick="eqmanage_equipmentMold.eqType_show_addmodal();" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="eqTypeInfo" name="eqTypeInfo" placeholder="设备名称、类型">
				  <button onclick="eqmanage_equipmentMold.eqType_searcheqType()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="eqTypeTable" class="Information_area"></div>
	</div>
</div>
 
 <!---添加设备类型开始-----> 
<div class="modal fade" id="modal-form-addeqType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加设备类型信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addeqTypeForm" name="addeqType" class="form-horizontal">
                	<div class="form-group">
                        <label class="col-sm-3 control-label">设备类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_type" name="f_type" placeholder="请输入设备类型"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_sbmc" name="f_sbmc" placeholder="请输入设备名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备基础信息表</label>
                        <div class="col-sm-8">
                            <input type="text" id="f_sbjcxxb" name="f_sbjcxxb" placeholder="请输入设备基础信息表名称"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备数据表</label>
                        <div class="col-sm-8">
                            <input type="text" id="f_sbsjb" name="f_sbsjb" placeholder="请输入设备数据表名称"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="f_remark" name="f_remark" class="form-control">
                          </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-8">
                        	<input type="hidden" id="f_ptype" name="f_ptype" readonly="readonly" placeholder="请输入组织机构名称" class="form-control">
                        	
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


<!----编辑设备类型信息--->			
<div class="modal fade" id="editEqTypeFormTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑设备类型信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editEqTypeForm" name="editEqTypeForm" class="form-horizontal">
     
				    <div class="form-group">
				        <label class="col-sm-3 control-label">设备类型</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_type" name="edit_f_type" readonly="readonly" class="form-control">
				        </div>
				    </div>
		    
				    <div class="form-group">
				        <label class="col-sm-3 control-label">设备名称</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_sbmc" name="edit_f_sbmc"  required class="form-control">
				        </div>
				    </div>
				    
				    <div class="form-group">
				        <label class="col-sm-3 control-label">父设备类型</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_ptype" readonly="readonly" name="edit_f_ptype" class="form-control">
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">设备基础信息表</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_sbjcxxb" name="edit_f_sbjcxxb" class="form-control">
				        </div>
				    </div>
				    <div class="form-group">
				        <label class="col-sm-3 control-label">设备数据表</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_sbsjb"  name="edit_f_sbsjb"  class="form-control">
				        </div>
				    </div>
				    
				    <div class="form-group">
				        <label class="col-sm-3 control-label">备注</label>
				        <div class="col-sm-8">
				        	<input type="text" id="edit_f_remark"  name="edit_f_remark"  class="form-control">
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
 
<script type="text/javascript">
;
 var eqmanage_equipmentMold = (function($, window, document, undefined) {
	 	var _ctx = '${ctx}';
	 	var _eqTypebh = '';//设备编号 
	 	var _eqTypemc = '';//设备编号 
		var _curRow = null;//对应行
		
		//设置格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var eqTypepid = cell.getRow().getData().f_type;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ eqTypepid + " data-toggle='modal' data-target='#editEqTypeFormTable'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + eqTypepid + "><i class='fa fa-trash'></i>  删除</button></div>"
		}; 
		
		//创建并设置table属性
		$("#eqTypeTable").tabulator({
			height:"100%",
			layout:"fitColumns",//fitColumns  fitDataFill
			columnVertAlign:"bottom", //align header contents to bottom of cell
			tooltips:true,
			//selectable:true,
			movableColumns:true,
			columns:[
			{title:"序号",field:"id",formatter:"rownum",frozen:false,align:"center",sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false}, //frozen column
			{title:"设备类型", field:"f_type",sorter:"string",editor:false,align:"right",headerSort:false}, //never hide this column
			{title:"设备名称", field:"f_sbmc",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
			{title:"父设备类型", field:"f_ptype",sorter:"string",align:"left",visible: false,editor:false,headerSort:false},
			{title:"设备基础信息表", field:"f_sbjcxxb",sorter:"string",align:"left",editable:false,headerSort:false},
			{title:"设备数据表", field:"f_sbsjb",sorter:"string",align:"left",editor:false,headerSort:false},
			{title:"备注", field:"f_remark",sorter:"string",align:"left",editor:false,headerSort:false},
			{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
			{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
			{title:"操作", field:"opt", width:150,tooltip:false,tooltipsHeader:false,align:"left",formatter:optIconFunction,headerSort:false},
			],
			rowClick:function(e, row){
	        	_curRow = row;
	        	var id = _curRow.getData().f_type;
	        	var choiseNode = $('#tree_eqType').treeview('findNodes', [ _curRow.getData().f_type, 'id']);
	        	if(choiseNode.length>1){
	    	    	for (var i = 0; i < choiseNode.length; i++) {
	    				if(choiseNode[i].id == id){
	    					$('#tree_eqType').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
	    				}
	    			}
	    	    }else{
	        	$('#tree_eqType').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
	    	    }
	        	
	        	
	    	},
		});
		
		$(window).resize(function(){
			$("#eqTypeTable").tabulator("redraw");
		});
		
		
		//加载树
		$(function () {
		    $.ajax({
		        type: "post",
		        url: _ctx + "/view/equipmentmold/eqType_tree",
		        dataType: "json",
		        beforeSend: function () { 
	 				showLoad();	             
	 			},
		        success: function (result) {
		        	/* if(result.list.length >0){
			        	$("#eqTypeTable").tabulator("setData",  _ctx+'/view/equipmentmold/eqType_chlildtreegrid?f_type='+result.list[0].id);
			        	} */
		            $('#tree_eqType').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		               onNodeSelected: function (event, nodeData) {
		               $('#tree_eqType').treeview('clearSearch');//清除搜索选中高亮
		               _eqTypebh = nodeData.id;
		                     $.ajax({
		                	    url: _ctx + "/view/equipmentmold/eqType_chlildtreegrid",
		                	    contentType: "application/json; charset=utf-8",
		                	    type: "get",
		                	    data: {
		                	    	f_type:nodeData.id
		                	    	
		                	    },
		                	    beforeSend: function () { 
		             				showLoad();	             
		             			},
								success: function(result) {
										if(result.hasOwnProperty("list")){
							            	$("#eqTypeTable").tabulator("setData", result.list);
										}else{
							            	$("#eqTypeTable").tabulator("setData", []);
										}
					            },
					            complete: function () {
									hiddenLoad();
								},
					            error: function(result) {
					          	    swal( "查询失败","", "error");
					            }
	                	   }); 
		                }
		            });
			        	
		            //初始加载根节点
		            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
		            	if(result.list.length >0){//如果包含判断是否长度大于0
				            var firstNode = $("#tree_eqType").treeview('findNodes',[result.list[0].id,'id']);
				        	$("#tree_eqType").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
		
		
		//触发搜索的回车时间
		$("#eqTypeInfo").focus(function(){
						  $(this).keydown(function (e){
						  if(e.which == "13"){
							  eqmanage_equipmentMold.eqType_searcheqType();//触发该事件
						    } 
						  })
		});
		
		//关闭模态框清空表单值
	    $("#modal-form-addeqType").on('hidden.bs.modal', function (event) {
	        $(this).find("input").val("");
	        eqTypeValidator.resetForm();  
	    });
		
		//添加设备类型表单验证
	    var eqTypeValidator = $("#addeqTypeForm").validate({
		     rules: {
		         f_type: {
		        	 required: true,
		             isEnglish1: true,
		         }
		     },
		     messages: {
		         
		    	 f_type: {
		    		 required: "请输入设备类型 ",
	                   rangelength: jQuery.validator.format("设备类型应为英文 、数字字符"),
	                   isEnglish1: "请输入英文 、数字字符或下划线 "
		         }
		     },
		      submitHandler: function (form) {
		         addformEqType(form);
		     } 
		     
	 	});
		
	    //新增保存
		function addformEqType(form) {
		     $.ajax({
		       url: _ctx + "/view/equipmentmold/add_eqType",
		       type: "post",
		 	   data: $(form).serialize(),
		 	   beforeSend: function () { 
	 		   		showLoad();	             
	 			},
		       success: function(result) {
		         if (result.status == '1') {
		           //swal( result.msg,"", "success");
		           //setTimeout(function(){
		        	   swal({
							title : result.msg,// 展示的标题
							text : "",// 内容
							type: "success",
							showCloseButton : false, // 展示关闭按钮
							allowOutsideClick : false,
							showConfirmButton : false,
							timer: 1000
						});
			            $('#modal-form-addeqType').modal('hide');//关闭编辑窗口
			            //在表格中添加数据
			            $('#eqTypeTable').tabulator("addRow", { f_type:result.data.f_type,f_sbmc:result.data.f_sbmc,f_ptype:result.data.f_ptype,f_sbjcxxb:result.data.f_sbjcxxb, f_sbsjb:result.data.f_sbsjb, f_remark:result.data.f_remark, f_crdate:result.data.f_crdate, f_chdate:result.data.f_chdate});
			            //在树上添加
			            var pNode = $("#tree_eqType").treeview("getSelected");
			            $("#tree_eqType").treeview("addNode", [{ id:result.data.f_type, text:result.data.f_sbmc, pid:pNode[0].id}, pNode]);  
		           //},1000)
		         } else{
		           swal( result.msg, "", "error");
		         }
		       },
		       complete: function () {
					hiddenLoad();
				},
		       error: function(result) {
		       	 swal( result.msg,"", "error");
		       }
		     });
		     return false;
	 	};
		
		//居中显示（添加）
	 	$('#modal-form-addeqType').on('show.bs.modal', function () {
	 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
	 	//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 		//*************************************
	        $(this).css('display', 'block');  
	        var modalHeight=$(window).height() / 2 - $('#modal-form-addeqType .modal-dialog').height() / 2;  
	        $(this).find('.modal-dialog').css({  
	            'margin-top': modalHeight  
	        }); 
		});
		
		//居中显示（编辑）
 	$('#editEqTypeFormTable').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editEqTypeFormTable .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });
 	});
        
    	//修改设备类型信息表单验证
    	var ediatbeseqTypeValidator = $("#editEqTypeForm").validate({
    		rules: {
		         edit_f_sbmc: {
		        	 required: true,
		             rangelength: [1, 40]
		         }
		     },
		     messages: {
		         
		    	 edit_f_sbmc: {
		    		 required: "请填写设备名称",
		             minlength: jQuery.validator.format("Enter at least {0} characters")
		         }
		     },
		     submitHandler: function (form) {
		    	 editeqTypeForm(form);
		     }
	});
    	
    	   //新增保存
 		function editeqTypeForm(form) {

 		    $.ajax({
 		 	      url: _ctx + "/view/equipmentmold/edit_eqType",
 		 	      type: "post",
 		 	      data: ({
 		 	    	f_type: $("#edit_f_type").val(),
 		 	    	f_sbmc: $("#edit_f_sbmc").val(),
 		 	    	f_ptype: $("#edit_f_ptype").val(),
 		 	    	f_sbsjb: $("#edit_f_sbsjb").val(),
 		 	    	f_sbjcxxb: $("#edit_f_sbjcxxb").val(),
 		 	    	f_remark: $("#edit_f_remark").val()
 		 		      
 		 	      }),
 		 	      beforeSend: function () { 
 			 		  	showLoad();	             
 			 		  },
 		 	      success: function(result) {
 		 	    	  if (result.status == '1') {
 		 	    	         swal({
 		 	   	        	title : result.msg,// 展示的标题
 		 	   	   			text : "",// 内容
 		 	   	   			type: "success",
 		 	   	   			showCloseButton : false, // 展示关闭按钮
 		 	   	   			allowOutsideClick : false,
 		 	   	   			showConfirmButton : false,
 		 	   	   			timer: 1000
 		 	   	   		});
 		 	   	   		swal("修改成功!", result.msg, "success");
 		 	    	      $('#eqTypeTable').tabulator("updateRow",_curRow, { f_type:result.data.f_type,f_sbmc:result.data.f_sbmc,f_ptype:result.data.f_ptype,f_sbjcxxb:result.data.f_sbjcxxb, f_sbsjb:result.data.f_sbsjb, f_remark:result.data.f_remark, f_crdate:result.data.f_crdate, f_chdate:result.data.f_chdate});
 		 	    	    	//在树上修改
 		 	    	    	
 		 	    	        var parentNode = $('#tree_eqType').treeview('findNodes', [ _curRow.getData().f_type, 'id'])
			            	var newNode={
										text: _curRow.getData().f_sbmc
								};
							$('#tree_eqType').treeview('updateNode', [ parentNode, newNode]);
								 $('#editEqTypeFormTable').modal('hide');//关闭编辑窗口
 		 		           
 		 	            } else{
 		 	              swal("修改失败!", result.data.msg, "error");
 		 	            }
 		 	          },
 		 	         complete: function () {
 							hiddenLoad();
 						},
 		 	          error: function(result) {
 		 	          	 swal("修改失败!", result.data.msg, "error");
 		 	          }
 		 	    });
 	    	
 	    	
 	 	};
    	
  	  //验证在模态框出现前加载
   	  $("#editEqTypeFormTable").on('show.bs.modal', function(event) {
   	    var id = _curRow.getData().f_type;
   	//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 	//*************************************
   	    $.ajax({
   		       url: _ctx + "/view/equipmentmold/loadediteqType",
   		        type: "post",
   	           contentType: "application/json; charset=utf-8",
   		       data:JSON.stringify({     
   		    	     f_type:id
   		 		}),
   		       success: function(result) {
   		         $("#edit_f_type").val(result.f_type);
   		         $("#edit_f_sbmc").val(result.f_sbmc);
   		         $("#edit_f_ptype").val(result.f_ptype);
   		         $("#edit_f_sbjcxxb").val(result.f_sbjcxxb);
   		         $("#edit_f_sbsjb").val(result.f_sbsjb);
   		         $("#edit_f_remark").val(result.f_remark);
   		        
   		         }
   	    }); 
   	   			
   	  });
  	  
      //删除数据
      $(document).on('click','#eqTypeTable button.delete', function () {

    	  var id=$(this).data("id").toString(); 
          //var id=$(this).data("id");
          
          var dNode = $('#tree_eqType').treeview('findNodes', [ id, 'id']);
  	    if(dNode.length>1){
  	    
  	    for (var i = 0; i < dNode.length; i++) {
  	    	
  	    	/* alert(typeof dNode[i].id)
  	    	alert(typeof id)
  	    	alert(dNode[i].id === id) */
  			if(dNode[i].id === id){
  				var booljuge = dNode[i].hasOwnProperty('nodes');
  				if(booljuge == true){
  					swal({ 
  			            title: "请您先删除设备类型下的子设备类型！",
  			            text: "经检测，您要删除的设备类型包含子设备类型!",
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
          	//setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/equipmentmold/eqType_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_type:id
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
		              		//swal(data.msg, "", "success");
		              		//在表格中删除该条数据

		              		$("#eqTypeTable").tabulator("deleteRow", _curRow);
		              		var getGroupTable = $("#eqTypeTable").tabulator("getData");
		              		$("#eqTypeTable").tabulator("setData", getGroupTable);
				            //在树上删除该条数据
				             $("#tree_eqType").treeview("removeNode", node, { silent: true } );    
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
              //},100
          //)
      }); 
      };
		
    	return{
			//搜索
			eqType_searcheqType:function(){
				var eqTypeInfo = $("#eqTypeInfo").val();
				$("#eqTypeTable").tabulator("setData", _ctx+'/view/equipmentmold/searcheqType?f_sbmc='+eqTypeInfo);
			},
			
			//验证增加模态框是否弹出
			 eqType_show_addmodal:function() {
		       var node = $('#tree_eqType').treeview('getSelected');
						if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
							swal({ 
					            title: "请选择节点",
					            text: "经检测，您要未选择设备类型节点!",
					            type: "warning",
					            showCancelButton: false,
					            confirmButtonColor: "#1ab394",
					            confirmButtonText: "关闭",
					            closeOnConfirm: false
			        		});
						}else{
							_eqTypebh = node[0].nodeTreeId;
							_eqTypemc = node[0].text;
							$("#f_ptype").val(_eqTypebh);
						$('#modal-form-addeqType').modal('show');
						}
		    },
		}
		
		
 	})(jQuery, window, document);
 	</script>