<!-----内容区域---->

<div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
           <a id="addEsXt" href="javascript:void(-1);" onclick="isspview_applicationmanage_esXtList.esxt_show_addmodal()" class="btn btn-primary" >
                	添加系统 <i class="fa fa-plus"></i>
            </a>
    	</div>
        <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="esxtkeywords" name="euserrolekeywords" value="" placeholder="系统编号、名称"> 
			<span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryEsxtBtn" onclick="isspview_applicationmanage_esXtList.search_esxt()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
       	</div>
   </div>
 </div>
 

<div  style="height:93%;width:100%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%)">
		<div id="esXtListTable"></div>
   </div>
</div>

<!---添加系统信息开始-----> 
<div class="modal fade" id="modal-form-addesxt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加系统信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addXt" name="addEsXt" class="form-horizontal">
                	<div class="form-group">
                        <label class="col-sm-3 control-label">系统编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_xtbh" name="f_xtbh" placeholder="请输入系统编号"  required class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">系统名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_xtmc" name="f_xtmc" placeholder="请输入系统名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="esxt_f_remark" name="f_remark" class="form-control">
                          </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----编辑--->
<div class="modal fade" id="editEsxtForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑系统信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_EsxtForm" name="edit_EsxtForm" class="form-horizontal">
            	<div class="form-group">
	<label class="col-sm-4 control-label" for="edit_f_xtbh">系统编号  <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="edit_f_xtbh" name="edit_f_xtbh"  required class="form-control" readonly="readonly">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="edit_f_xtmc">系统名称<span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="edit_f_xtmc" name="edit_f_xtmc"  required class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="edit_f_remark">备注 <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="edit_f_remark" name="edit_f_remark" required class="form-control">
	</div>
</div>
<div class="form-group m-t-sm">
	<div class="col-sm-6 col-sm-push-4">
		<button class="btn btn-md btn-primary " type="submit">
			<strong>更新</strong>
		</button>
		<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
	</div>
</div>
            	</form>
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
 	var isspview_applicationmanage_esXtList = (function($, window, document, undefined){
 		var _ctx='${ctx}';
 		var _curRow = null;//对应行
 		
 		//创建并设置table属性
			$("#esXtListTable").tabulator({
				height:"100%",
				layout:"fitColumns",//fitColumns  fitDataFill
				columnVertAlign:"bottom", //align header contents to bottom of cell
				tooltips:true,
				movableColumns:true,
				columns:[
				{title:"系统编号",field:"f_xtbh",align:"center",frozen:false,headerSort:false},
				{title:"系统名称", field:"f_xtmc", sorter:"string",align:"center",editor:false,headerSort:false},
				{title:"备注", field:"f_remark",sorter:"string",align:"center",editor:false,headerSort:false},
				{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
				{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
				{title:"操作", field:"opt",tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
				],
				rowClick:function(e, row){
		        	_curRow = row;
		    	},
			});
			
			//填充数据
			$("#esXtListTable").tabulator("setData", _ctx+'/view/systemcenter/loadAllEsXt');
			

			$(window).resize(function(){
				$("#esXtListTable").tabulator("redraw");
			});
			
			//居中显示（添加）
		 	$('#modal-form-addesxt').on('show.bs.modal', function () {
		 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
		        $(this).css('display', 'block');  
		        var modalHeight=$(window).height()/2 - $('#modal-form-addesxt .modal-dialog').height()/2;  
		        $(this).find('.modal-dialog').css({  
		            'margin-top': modalHeight  
		        }); 
			})
		 	//居中显示（编辑）
		 	$('#editEsxtForm').on('show.bs.modal', function () {  
		        $(this).css('display', 'block');  
		        var modalHeight=$(window).height()/2 - $('#editEsxtForm .modal-dialog').height()/2;  
		        $(this).find('.modal-dialog').css({  
		            'margin-top': modalHeight  
		        }); 
			})
			//关闭模态框清空表单值
		    $("#modal-form-addesxt").on('hidden.bs.modal', function (event) {
		        $(this).find("input").val("");
		        esXtValidator.resetForm();  
		    });
			
		 	//触发搜索的回车时间
	 		$("#esxtkeywords").focus(function(){
	 						  $(this).keydown(function (e){
	 						  if(e.which == "13"){
	 							 isspview_applicationmanage_esXtList.search_esxt();//触发该事件
	 						    } 
	 						  })
	 		});
		    //删除数据
		    $(document).on('click','#esXtListTable button.delete', function () {
		        var f_xtbh=_curRow.getData().f_xtbh;
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
					          url: _ctx + "/view/systemcenter/esxt_del",
					          contentType: "application/json; charset=utf-8",
					          type: "post",
					          data:JSON.stringify({     
					        			f_xtbh:f_xtbh
					    	  }),
				          	  success: function(data) {
				              	if (data.status == '1') {
				              		swal(data.msg, "", "success");
				              		$("#esXtListTable").tabulator("deleteRow", _curRow);    
				              		var getroleTable = $("#esXtListTable").tabulator("getData");
				              		$("#esXtListTable").tabulator("setData", getroleTable);
					            } else{
					                swal(data.msg,"", "error");
					            }
					          },
					          error: function(data) {
					          	 swal( data.msg,"", "error");
					          },
					          beforeSend : function(){
						    	   showLoad();
						       },
						       complete : function(){
						    	   hiddenLoad();
						       }
					        });
		                },100
		            )
		        });
		    });
		     
		 //验证在模态框出现前加载
		  $("#editEsxtForm").on('show.bs.modal', function(event) { 
		    var f_xtbh = _curRow.getData().f_xtbh;
		    $.ajax({
			       url: _ctx + "/view/systemcenter/esxt_loadEditObj",
			        type: "post",
		           contentType: "application/json; charset=utf-8",
			       data:JSON.stringify({     
			 			f_xtbh:f_xtbh
			 		}),
			       success: function(result) {
			         $("#edit_f_xtbh").val(result.data.f_xtbh);
			         $("#edit_f_xtmc").val(result.data.f_xtmc);
			         $("#edit_f_remark").val(result.data.f_remark);
			         }
		    });  
		  });      
		    //编辑角色
		   //表单验证
		  $("#edit_EsxtForm").validate({
		    submitHandler: function(form) {
		      editEsxtFormFunc(form);
		    }
		  });
 		
 		//添加组织机构表单验证
 	    var esXtValidator = $("#addXt").validate({
 		     rules: {
 	            f_xtmc: {
 	            	required: true,
 	                rangelength: [1, 20],
 	            },
 	            f_xtbh: {
 	             	required: true,
 	                rangelength: [1, 20],
 	            }
 	        },
 	        messages: {
 	            f_xtmc: {
 	               required: "请输入系统名称",
 	                    rangelength: jQuery.validator.format("应为3-20位的英文字母、数字字符"),
 	                    remote: jQuery.validator.format("{0} is already in use")
 	            },
 	            f_xtbh: {
 	               required: "请输入系统编号",
 	                    rangelength: jQuery.validator.format("应为3-20位的英文字母、数字字符"),
 	                    remote: jQuery.validator.format("{0} is already in use")
 	            }
 	        },
 		     submitHandler: function (form) {
 		         addform_EsXt(form);
 		     }
 	 	});
 		
 	   function addform_EsXt(form) {
	       $.ajax({
		      url: _ctx + "/view/systemcenter/esxt_add",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({
		      		f_xtbh:$("#f_xtbh").val(),   
		 			f_xtmc:$("#f_xtmc").val(),
					f_remark:$("#esxt_f_remark").val()
		 	}),
			success: function(data) {
	         if (data.status == '1') {
	           swal({
		        					title : data.msg,
		        					text  : "",
		        					type : "success",
		        					showCloseButton : false,
		        					allowOutsideClick : false,
		        					showConfirmButton : false,
		        					timer : 1000
		        				});
		            $('#modal-form-addesxt').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#esXtListTable').tabulator("addRow", {f_xtbh:data.data.f_xtbh,f_xtmc:data.data.f_xtmc, f_remark:data.data.f_remark,f_crdate:data.data.f_crdate, f_chdate:data.data.f_chdate});
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       },
	       beforeSend : function(){
	    	   showLoad();
	       },
	       complete : function(){
	    	   hiddenLoad();
	       }
	     });
	     return false;
 	}
 	   
 	  function editEsxtFormFunc(form) {
 		    $.ajax({
 		      url: _ctx + "/view/systemcenter/esxt_upd",
 		      type: "post",
 		      contentType: "application/json; charset=utf-8",
 		      data: JSON.stringify({
 		      		f_xtbh: $("#edit_f_xtbh").val(),
 			        f_xtmc: $("#edit_f_xtmc").val(),
 			        f_remark: $("#edit_f_remark").val()
 		      }),
 		      success: function(data) {
 					if (data.status == '1') {
 		              swal("修改成功!", data.msg, "success");
 		              //$("#esXtListTable").tabulator("setData", _ctx+'/view/systemcenter/loadAllEsXt');
 		              $("#esXtListTable").tabulator("updateRow",_curRow,{
 		              	f_xtbh: $("#edit_f_xtbh").val(),
 			        	f_xtmc: $("#edit_f_xtmc").val(),
 			        	f_remark: $("#edit_f_remark").val()
 		              });
 		              setTimeout(function(){
 			              $('#editEsxtForm').modal('hide');//关闭编辑窗口
 		              },1000)
 		            } else{
 		              swal("修改失败!", data.msg, "error");
 		            }
 		          },
 		          error: function(data) {
 		          	 swal("修改失败!", data.msg, "error");
 		          },
 		          beforeSend : function(){
 			    	   showLoad();
 			      },
 			      complete : function(){
 			    	  hiddenLoad();
 			      }
 		    });
 		  }
 		  
 	 	function optIconFunction(cell, formatterParams){ //plain text value
			var esxtBh = cell.getRow().getData().f_xtbh;
			return "<div class='btn-group '>"
			+"<button class='btn btn-white btn-sm edit' data-id="+ esxtBh + " data-toggle='modal' data-target='#editEsxtForm'><i class='fa fa-pencil' ></i> 编辑</button>"
			+"<button class='btn btn-white btn-sm delete' data-id=" + esxtBh + "><i class='fa fa-trash'></i>  删除</button></div>"
		}
 	 	
 		return {
 			esxt_show_addmodal : function () {
					$('#modal-form-addesxt').modal('show'); 
	    	},
	    	search_esxt : function (){
	 			var esxtkeywords = $("#esxtkeywords").val();
	 			$("#esXtListTable").tabulator("setData", _ctx+'/view/systemcenter/loadEsXtSearch?keywords='+esxtkeywords);
	 		}
 		}
 	})(jQuery, window, document);
 </script>