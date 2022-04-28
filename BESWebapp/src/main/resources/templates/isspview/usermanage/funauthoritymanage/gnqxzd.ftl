<!-----内容区域---->

<div class="ibox-content m-b-sm border-bottom" style="width: 100%; height: 6.5%"> 
   <div class="input-group"  style="height: 100%; width: 100%;">
        <div class="divStyle_size" >
           <a   href="javascript:void(-1);" onclick="funauthoritymanage_gnqxzd.show_addgnqxzd()" class="btn btn-primary" >
                	增加 <i class="fa fa-plus"></i>
            </a>
    	</div>
        <!-- <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="gnqx_keywords" name="gnqx_keywords" value="" placeholder="功能权限编号、名称"> 
			<span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryBmzdBtn" onclick="funauthoritymanage_gnqxzd.search_gnqx()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
       	</div> -->
       	<div class="zc_search find">
			<div class="zc_search_form">
			  <input type="text"  id="gnqx_keywords" name="gnqx_keywords" placeholder="功能权限编号、名称">
			  <button onclick="funauthoritymanage_gnqxzd.search_gnqx()"></button>
			</div>
		</div>
   </div>
 </div>


<div class="leftarea"  style="display: none;margin-top:-10px;height:93%;width:12%;padding-left:0;overflow:auto">
	<div class="container-fluid" style="padding:0;">
	<div class="row-fluid" >
		<div class="span12">
			<ul class="nav nav-list" id="gnqx_ulBox">
			</ul>
		</div>
	</div>
</div>
</div>

<div style=" float: right;height:93%;width:100%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%);overflow:auto;">
		<div id="egnqxTable">	</div>
   </div>
</div>







<!---添加功能权限信息开始-----> 
<div class="modal fade" id="modal-form-addgnqx" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加功能权限信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addEsGnqx" name="addEsGnqx" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权限名称<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_qxmc" name="addgnqx_f_qxmc" placeholder="请输入权限名称"  required class="form-control">
                        </div>  
                   		 <label class="col-sm-2 control-label">系统名称<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="hidden" id="addgnqx_f_xtbh" name="addgnqx_f_xtbh" placeholder=""  required class="form-control">
                            <input type="text" id="addgnqx_f_xtmc" name="addgnqx_f_xtmc"  required class="form-control" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">数据表名<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_tabn" name="addgnqx_f_tabn" placeholder="请输入数据表名"   class="form-control">
                        </div>
                         <label class="col-sm-2 control-label">编号字段<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_bhzd" name="addgnqx_f_bhzd" placeholder="请输入编号字段"   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称字段</label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_mczd" name="addgnqx_f_mczd" placeholder="请输入名称字段"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">是否使用<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                       <input type="radio" name="add_gnqx_usemark" value="1" id="add_yes_gnqx" checked="checked">
					    <label class="radio-label" for="add_yes_gnqx"> 是 </label>
					    <input type="radio" name="add_gnqx_usemark" value="0"  id="add_not_gnqx" style="margin-left:30px;">
					    <label class="radio-label" for="add_not_gnqx"> 否 </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户权限表</label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_user_qxb" name="addgnqx_f_user_qxb" placeholder="请输入条件"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">用户组权限表</label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_yhz_qxb" name="addgnqx_f_yhz_qxb" placeholder="请输入条件"   class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">角色权限表</label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_role_qxb" name="addgnqx_f_role_qxb" placeholder="请输入条件"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">岗位权限表</label>
                        <div class="col-sm-4">
                            <input type="text" id="addgnqx_f_gw_qxb" name="addgnqx_f_gw_qxb" placeholder="请输入条件"   class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-5 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----编辑role--->
<div class="modal fade" id="editGnqxForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑功能权限信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_GnqxForm" name="edit_GnqxForm" class="form-horizontal">
<div class="form-group">
	<label class="col-sm-2 control-label" for="editgnqx_f_qxmc">权限名称  <span class="text-danger">*</span></label>
	<div class="col-sm-4">
		<input type="hidden" id="editgnqx_f_qxbh" name="editgnqx_f_qxbh"  required class="form-control" readonly="readonly">
		<input type="text" id="editgnqx_f_qxmc" name="editgnqx_f_qxmc"  required class="form-control" >
	</div>
	<label class="col-sm-2 control-label" for="editgnqx_f_tabn">数据表名<span class="text-danger">*</span></label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_tabn" name="editgnqx_f_tabn"   class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label" for="editgnqx_f_bhzd">编码字段<span class="text-danger">*</span></label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_bhzd" name="editgnqx_f_bhzd"   class="form-control">
	</div>
	<label class="col-sm-2 control-label" for="editgnqx_f_mczd">名称字段</label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_mczd" name="editgnqx_f_mczd"   class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label" for="editgnqx_f_user_qxb">用户权限表</label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_user_qxb" name="editgnqx_f_user_qxb"   class="form-control">
	</div>
	<label class="col-sm-2 control-label" for="editgnqx_f_yhz_qxb">用户组权限表</label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_yhz_qxb" name="editgnqx_f_yhz_qxb"   class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label" for="editgnqx_f_role_qxb">角色权限表</label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_role_qxb" name="editgnqx_f_role_qxb"   class="form-control">
	</div>
	<label class="col-sm-2 control-label" for="editgnqx_f_gw_qxb">岗位权限表</label>
	<div class="col-sm-4">
		<input type="text" id="editgnqx_f_gw_qxb" name="editgnqx_f_gw_qxb"   class="form-control">
	</div>
</div>
<div class="form-group">
    <label class="col-sm-2 control-label">是否使用<span class="text-danger">*</span></label>
    <div class="col-sm-4">
         <input type="radio" class="editgnqx_radio" name="editgnqx_usemark" value="1" id="editgnqx_yes_target" checked="checked">
		 <label class="radio-label" for="editgnqx_yes_target"> 是 </label>
		 <input type="radio" class="editgnqx_radio" name="editgnqx_usemark" value="0"  id="editgnqx_not_target" style="margin-left:30px;">
		 <label class="radio-label" for="editgnqx_not_target"> 否 </label>
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
<style>
</style>

 <script type="text/javascript">
 ;
 var funauthoritymanage_gnqxzd = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var _froleguid = "00";
	var gnqx_listatus = new Array();   //定义数组， gnqx_listatus[0]系统编码；gnqx_listatus[1]系统名称
	gnqx_listatus[0] = null;    
	gnqx_listatus[1] = null; 
	
	/* $(function(){  
		gnqx_loadMenu();//加载系统列表菜单
	}); */
	//设置格式
	var optIconFunction = function(cell, formatterParams){
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-group '>"
		+"<button class='btn btn-white btn-sm edit' data-id="+ fqxbh + " data-toggle='modal' data-target='#editGnqxForm'><i class='fa fa-pencil' ></i> 编辑</button>"
		+"<button class='btn btn-white btn-sm delete' data-id=" + fqxbh + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	
	//创建并设置table属性
	$("#egnqxTable").tabulator({
		height:"100%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		//selectable:true,
		movableColumns:false,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,headerSort:false,align:"center",tooltip:false,tooltipsHeader:false},
		//{title:"权限编号", field:"f_qxbh",width:80, sorter:"string",editor:false,headerSort:false},
		{title:"权限名称", field:"f_qxmc",sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"权限标志", field:"f_qxbz",width:120,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"权限说明", field:"f_qxsm",width:120,sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"数据表名", field:"f_tabn",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"编码字段", field:"f_bhzd",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"名称字段", field:"f_mczd",sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"编码结构", field:"f_bmjg",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"级数字段", field:"f_jszd",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"明细字段", field:"f_mxzd",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"条件", field:"f_wher",width:120,sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"是否使用", field:"f_sfsy",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"用户权限表", field:"f_user_qxb",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"用户组权限表", field:"f_yhz_qxb",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"角色权限表", field:"f_role_qxb",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"岗位权限表", field:"f_gw_qxb",sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		//{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:200, tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	//填充数据
	//$("#egnqxTable").tabulator("setData", _ctx+'/view/gnqx/loadAllGnqx');
	

	$(window).resize(function(){
		$("#egnqxTable").tabulator("redraw");
	});
	//创建并设置table属性
	$("#xtlistTable").tabulator({
		height:"50%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,headerSort:false},
		{title:"系统编号", field:"f_xtbh",width:85, sorter:"string",editor:false,headerSort:false},
		{title:"系统名称", field:"f_xtmc",width:85,sorter:"string",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	//填充数据
	$("#xtlistTable").tabulator("setData", _ctx+'/view/gnqx/loadAllXt');

	$(window).resize(function(){
		$("#xtlistTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间
	$("#gnqx_keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
					  search_gnqx();//触发该事件
					    } 
					  })
					});

	//添加组织机构表单验证
    var gnqxValidator = $("#addEsGnqx").validate({
	     rules: {
            f_qxmc: {
                required: true,
                rangelength: [1, 20],
            }
        },
        messages: {
        	f_qxmc: {
               required: "请输功能权限名称",
                    rangelength: jQuery.validator.format("应为3-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            }
        },
	     submitHandler: function (form) {
	         addform_EsGnqx(form);
	         
	     }
 	});
	
    //新增保存
	function addform_EsGnqx(form) {
		var f_sfsy = $("input[name='add_gnqx_usemark']:checked")[0].id=="add_yes_gnqx"?"1":"0";
	       $.ajax({
		      url: _ctx + "/view/gnqx/data_gnqx_add",
		      contentType: "application/json; charset=utf-8",
	          type: "post",
	          data:JSON.stringify({
		      		f_qxmc: $("#addgnqx_f_qxmc").val(),
		      		f_xtbh: $("#addgnqx_f_xtbh").val(),
		      		f_tabn: $("#addgnqx_f_tabn").val(),
		      		f_bhzd: $("#addgnqx_f_bhzd").val(),
		      		f_mczd: $("#addgnqx_f_mczd").val(),
		      		f_user_qxb: $("#addgnqx_f_user_qxb").val(),
		      		f_yhz_qxb: $("#addgnqx_f_yhz_qxb").val(),
		      		f_role_qxb: $("#addgnqx_f_role_qxb").val(),
		      		f_gw_qxb: $("#addgnqx_f_gw_qxb").val(),
		      		f_sfsy: f_sfsy
		      }),
		      beforeSend: function () { 
	 				showLoad();	             
	 			},
		success: function(data) {
	         if (data.status == '1') {
             $('#modal-form-addgnqx').modal('hide');//关闭编辑窗口
	           swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
		            //在表格中添加数据
		            $('#egnqxTable').tabulator("addRow", {
		            	f_qxbh:data.data.f_qxbh,
		            	f_qxmc:data.data.f_qxmc,
		            	f_tabn:data.data.f_tabn,
		            	f_bhzd:data.data.f_bhzd,
		            	f_mczd:data.data.f_mczd,
		            	f_sfsy:data.data.f_sfsy,
		            	f_user_qxb:data.data.f_user_qxb,
		            	f_yhz_qxb:data.data.f_yhz_qxb,
		            	f_role_qxb:data.data.f_role_qxb,
		            	f_gw_qxb:data.data.f_gw_qxb,
		            	f_crdate:data.data.f_crdate,
		            	f_chdate:data.data.f_chdate
		            	});
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
 	$('#modal-form-addgnqx').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 		//*************************************
        $(this).css('display', 'block');  
        var  modalHeight=$(window).height() / 2 - $('#modal-form-addgnqx .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editGnqxForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editGnqxForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-form-addgnqx").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        gnqxValidator.resetForm();  
    });
    //删除数据
    $(document).on('click','#egnqxTable button.delete', function () {
        var id=$(this).data("id");
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
			          url: _ctx + "/view/gnqx/data_gnqx_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_qxbh:id
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
		              		$("#egnqxTable").tabulator("deleteRow", _curRow); 
		              		var getTable = $("#egnqxTable").tabulator("getData");
		              		$("#egnqxTable").tabulator("setData", getTable);
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
    });
     
       
    //编辑功能权限
   //表单验证
  $("#edit_GnqxForm").validate({
    submitHandler: function(form) {
      editGnqxFormFunc(form);
    }
  });

  function editGnqxFormFunc(form) {
	  var f_sfsy = $("input[name='editgnqx_usemark']:checked").val();
    $.ajax({
      url: _ctx + "/view/gnqx/gnqx_edit",
      type: "post",
      data: ({
	        f_qxbh: $("#editgnqx_f_qxbh").val(),
   			f_qxmc: $("#editgnqx_f_qxmc").val(),
			f_tabn:	$("#editgnqx_f_tabn").val(),
			f_bhzd:	$("#editgnqx_f_bhzd").val(),
			f_mczd:	$("#editgnqx_f_mczd").val(),
			f_user_qxb:$("#editgnqx_f_user_qxb").val(),
			f_yhz_qxb:$("#editgnqx_f_yhz_qxb").val(),
			f_role_qxb:$("#editgnqx_f_role_qxb").val(),
			f_gw_qxb:$("#editgnqx_f_gw_qxb").val(),
			f_sfsy:	f_sfsy
      }),
      beforeSend: function () { 
			showLoad();	             
		},
      success: function(data) {
			if (data.status == '1') {
             $('#editGnqxForm').modal('hide');//关闭编辑窗口
              swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
	         $('#egnqxTable').tabulator("updateRow",_curRow, {
	        	 f_qxbh:data.data.f_qxbh, 
	        	 f_qxmc:data.data.f_qxmc,
	        	 f_tabn:data.data.f_tabn,
	        	 f_bhzd:data.data.f_bhzd,
	        	 f_mczd:data.data.f_mczd,
	        	 f_user_qxb:data.data.f_user_qxb,
	        	 f_yhz_qxb:data.data.f_yhz_qxb,
	        	 f_role_qxb:data.data.f_role_qxb,
	        	 f_gw_qxb:data.data.f_gw_qxb,
	        	 f_sfsy:data.data.f_sfsy=='1'?'是':'否',
	        	 f_chdate:data.data.f_chdate});
            }else{
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
  
  //验证在模态框出现前加载
  $("#editGnqxForm").on('show.bs.modal', function(event) {
    var id = _curRow.getData().f_qxbh;
  	//模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/gnqx/loadeditobj",
	        type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({     
	 			f_qxbh:id
	 		}),
	 		beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	    	 $(":radio[name='editgnqx_usemark'][value='" + result.f_sfsy + "']").prop("checked", "checked");
	         $("#editgnqx_f_qxbh").val(result.f_qxbh);
	         $("#editgnqx_f_qxmc").val(result.f_qxmc);
	         //$("#editgnqx_f_xtbh").val(result.f_xtbh);
	         $("#editgnqx_f_qxbz").val(result.f_qxbz);
	         $("#editgnqx_f_qxsm").val(result.f_qxsm);
	         $("#editgnqx_f_tabn").val(result.f_tabn);
	         $("#editgnqx_f_bhzd").val(result.f_bhzd);
	         $("#editgnqx_f_mczd").val(result.f_mczd);
	         $("#editgnqx_f_bmjg").val(result.f_bmjg);
	         $("#editgnqx_f_jszd").val(result.f_jszd);
	         $("#editgnqx_f_mxzd").val(result.f_mxzd);
	         $("#editgnqx_f_wher").val(result.f_wher);
	         $("#editgnqx_f_user_qxb").val(result.f_user_qxb);
	         $("#editgnqx_f_yhz_qxb").val(result.f_yhz_qxb);
	         $("#editgnqx_f_role_qxb").val(result.f_role_qxb);
	         $("#editgnqx_f_gw_qxb").val(result.f_gw_qxb);
	         //$("#editgnqx_f_sfsy").val(result.f_sfsy);
	         },
	         complete: function () {
					hiddenLoad();
				},
    });  
  });
  //加载子系统列表
  function gnqx_loadMenu(){
	  $.ajax({
	       url: _ctx + "/view/gnqx/loadAllXt",
	       type: "post",
	       success: function(result) {
	    	result = result.list;
	    	 for (var i = 0; i < result.length; i++) {
	    	 $("#gnqx_ulBox").append("<li id='gnqx"+result[i].f_xtbh+"'><a href='javascript:;'onclick=\"funauthoritymanage_gnqxzd.selectXtGnqx('"+result[i].f_xtbh+" ','"+result[i].f_xtmc+" ')\">"+result[i].f_xtmc+"</a></li>");
			}
	    	$("#egnqxTable").tabulator("setData", _ctx+'/view/gnqx/data_gnqxByXtbh?f_xtbh='+result[0].f_xtbh);//表格默认填充第一条数据
			$("#gnqx"+result[0].f_xtbh).addClass('qxselected');// 添加当前元素的样式
			gnqx_listatus[0]= result[0].f_xtbh;
		  	gnqx_listatus[1]= result[0].f_xtmc;	
	   }
   }); 
  }
 
  return{
	 //根据系统编码查询功能权限
	 selectXtGnqx:function(xtbh,xtmc){
		 $("#egnqxTable").tabulator("clearData");//清空
		  $("#gnqx"+xtbh).siblings('li').removeClass('qxselected');// 删除其他兄弟元素的样式
		  $("#gnqx"+xtbh).addClass('qxselected');// 添加当前元素的样式
		  		gnqx_listatus[0]= xtbh;
		  		gnqx_listatus[1]= xtmc;
				$("#egnqxTable").tabulator("setData", _ctx+'/view/gnqx/data_gnqxByXtbh?f_xtbh='+xtbh);
	  },
	//搜索
	search_gnqx:function(){
			var gnqx_keywords = $("#gnqx_keywords").val();
			$("#egnqxTable").tabulator("setData", _ctx+'/view/gnqx/data_gnqx_search?keywords='+gnqx_keywords+'&f_xtbh='+gnqx_listatus[0]);
		},
	//验证增加模态框是否弹出
	show_addgnqxzd:function () {
				if(gnqx_listatus[0] !=null){
					$('#modal-form-addgnqx').modal('show'); 
					$("#addgnqx_f_xtbh").val(gnqx_listatus[0]);
					$("#addgnqx_f_xtmc").val(gnqx_listatus[1]);
			}else{
				swal({ 
		            title: "请选择子系统",
		            text: "经检测，您要未选择子系统!",
		            type: "warning",
		            showCancelButton: false,
		            confirmButtonColor: "#1ab394",
		            confirmButtonText: "关闭",
		            closeOnConfirm: false
		        });
			}
	    },
		pageInit : function(){
			gnqx_loadMenu();//加载系统列表菜单
		}
  }
 })(jQuery, window, document);
 funauthoritymanage_gnqxzd.pageInit();
 </script>