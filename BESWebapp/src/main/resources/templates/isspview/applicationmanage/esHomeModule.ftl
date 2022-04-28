<!-----内容区域---->
<!--
<div class="ibox-content m-b-sm border-bottom" style="width: 100%; height: 6.5%">
   <div class="input-group"  style="height: 100%; width: 100%;">
        <div class="divStyle_size" >
           <a id="addEsHomeModule" href="javascript:void(-1);" class="btn btn-primary" >
                	增加 <i class="fa fa-plus"></i>
            </a>
    	</div>
        <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="esHomeModulekeywords" name="esHomeModulekeywords" value="" placeholder="模块编号、名称">
			<span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryEsHomeModuleBtn">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
       	</div>
       	<div class="zc_search find">
			<div class="zc_search_form">
			  <input type="text"  id="esHomeModulekeywords" name="esHomeModulekeywords" placeholder="模块编号、名称">
			  <button id="queryEsHomeModuleBtn"></button>
			</div>
		</div>
   </div>
 </div> -->


<!-- <div  style="height:93%;width:100%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%)">
		<div id="esHomeModuleTable"></div>
   </div>
</div> -->
<!-- ----------------------------------------------------------------------- -->
<div class="information_size">
    <div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;模块定义列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addEsHomeModule" href="javascript:void(-1);"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
		</a>
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="esHomeModulekeywords" name="esHomeModulekeywords" placeholder="模块编号、名称">
				 <button id="queryEsHomeModuleBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
 <div class="ibox" id="esHomeModule_dataCenter" style="height:92%">
         	<#include "isspview/applicationmanage/esHomeModule_page.ftl"/>
    </div>
</div>


<!---添加模块信息开始----->
<div class="modal fade" id="modal-form-addesHomeModule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加模块信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addEsHomeModuleForm" name="addEsHomeModule" class="form-horizontal">
                	<div class="form-group">
                        <label class="col-sm-3 control-label">系统模块编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_mkbh" name="f_mkbh" placeholder="请输入系统模块编号"  required class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">系统模块名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_mkmc" name="f_mkmc" placeholder="请输入系统模块名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">系统图标<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_css_class" name="f_css_class" placeholder="请输入系统图标"  required class="form-control systemIcon"
                            onclick="isspview_applicationmanage_esHomeModule.getFotntsAddOrEdit()" autocomplete="off">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">排序<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_sort" name="f_sort" placeholder="请输入数字"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否显示菜单</label>
                        <div class="col-sm-8">
                        	<input type="radio" name="add_f_showleftmenus" value="1" id="f_showleftmenus_yes" checked="checked">
					    	<label class="radio-label" for="yes_hide"> 是 </label>
					    	<input type="radio" name="add_f_showleftmenus" value="0"  id="f_showleftmenus_no" style="margin-left:30px;">
					    	<label class="radio-label" for="not_hide"> 否 </label>
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

<!----编辑--->
<div class="modal fade" id="editEsHomeModuleForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑模块信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_EsHomeModuleForm" name="edit_EsHomeModuleForm" class="form-horizontal">
            		<div class="form-group">
                        <label class="col-sm-3 control-label">系统模块编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_f_mkbh" name="f_mkbh" readonly="readonly"  required class="form-control">
                            <input type="hidden" name="edit_guid" id="edit_guid" readonly="readonly"/>
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">系统模块名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_f_mkmc" name="edit_f_mkmc"   required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">系统图标<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_f_css_class" name="edit_f_css_class"   required class="form-control systemIcon"
                            onclick="isspview_applicationmanage_esHomeModule.getFotntsAddOrEdit()"  autocomplete="off">
                        </div>
                    </div>
                       <div class="form-group">
                        <label class="col-sm-3 control-label">排序<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_f_sort" name="edit_f_sort"   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否显示菜单</label>
                        <div class="col-sm-8">
                        	<input type="radio" name="f_showleftmenus" value="1" id="edit_f_showleftmenus_yes" checked="checked">
					    	<label class="radio-label" for="yes_hide"> 是 </label>
					    	<input type="radio" name="f_showleftmenus" value="0"  id="edit_f_showleftmenus_no" style="margin-left:30px;">
					    	<label class="radio-label" for="not_hide"> 否 </label>
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

<div id="fontsDIV" style="display: none">
	<div id="fonts"></div>
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
 var isspview_applicationmanage_esHomeModule = (function($, window, document, undefined){
	 var _ctx = "${ctx}";
		//居中显示（添加）
	 	$('#modal-form-addesHomeModule').on('show.bs.modal', function () {
	 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
	 		//模态框拖动********************
			$(this).draggable({
				handle:".modal-header"
			});
			$(this).css("overflow","hidden");
			//*************************************
	        $(this).css('display', 'block');
	        var modalHeight=$(window).height()/2 - $('#modal-form-addesHomeModule .modal-dialog').height()/2;
	        $(this).find('.modal-dialog').css({
	            'margin-top': modalHeight
	        });
		});

	 	//关闭模态框清空表单值
	    $("#modal-form-addesHomeModule").on('hidden.bs.modal', function (event) {
	        $(this).find("input").val("");
	        esHomeModuleValidator.resetForm();
	    });

	  //关闭模态框清空表单值
	    $("#editEsHomeModuleForm").on('hidden.bs.modal', function (event) {
	        $(this).find("input").val("");
	        esHomeModuleValidators.resetForm();
	    });

	 	//居中显示（编辑）
	 	$('#editEsHomeModuleForm').on('show.bs.modal', function () {
	        $(this).css('display', 'block');
	        var modalHeight=$(window).height()/2 - $('#editEsHomeModuleForm .modal-dialog').height()/2;
	        $(this).find('.modal-dialog').css({
	            'margin-top': modalHeight
	        });
		});

		 $("#addEsHomeModule").click(function(){
			 $('#modal-form-addesHomeModule').modal('show');
		 });

	 	$(window).resize(function() {
			$("#esHomeModuleTable").tabulator("redraw");
		});

	 	//点击按钮查询
		$("#queryEsHomeModuleBtn").click(function() {
			isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage({
				"keywords" : $("#esHomeModulekeywords").val(),
				bars:$("#esHomeModule_pageSize").val(),
			});
		});

	 	//搜索框回车查询
		$("#esHomeModulekeywords").focus(function(){
			  $(this).keydown(function (e){
			  	if(e.which == "13"){
			  		isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage({
						"keywords" : $("#esHomeModulekeywords").val(),
						bars:$("#esHomeModule_pageSize").val(),
					});//触发该事件
			    }
			  })
		});

		//删除
		$(document).on('click','#esHomeModuleTable button.delete',function() {
			 var guid=isspview_applicationmanage_esHomeModulePage.getCurRow().getData().guid;
				swal(
				{
				title : "您确定要删除吗?",
				text : "系统模块信息删除后将不可恢复!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#1ab394",
				confirmButtonText : "确定",
				closeOnConfirm : false
				},
			function() {
				setTimeout(function() {
					$.ajax({
						url : _ctx + "/view/esHomeModule/delete",
						type : "post",
						data : {"guid" : guid
						},
						success : function(data) {
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
								isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage();
							} else {
								swal("删除失败!",data.msg,"error");
							}
						},
						error : function(data) {
							swal("删除失败!",data.msg,"error");
						},
						beforeSend : function(){
					    	   showLoad();
					    },
					    complete : function(){
					    	   hiddenLoad();
					    }
					});
				}, 100)
			});
		});

		//编辑设备型号信息
		//表单验证
	 var esHomeModuleValidators =	$("#edit_EsHomeModuleForm").validate({
			rules : {
				edit_f_mkmc : {
					required : true,
					rangelength : [ 1, 20 ],
				},
				edit_f_css_class: {
					required: true,
					isNormal:true
				},
				edit_f_sort: {
                    required : true,
                    digits:true,
                    range: [ 1, 99 ]
				}
			},
			messages : {
				edit_f_mkmc : {
					required : "请输入模块名称",
					minlength : jQuery.validator.format("Enter at least {0} characters")
				},
				edit_f_css_class: {
					required: "请输入系统图标样式",
					isNormal:"请输入标准字符！"
				},
				edit_f_sort: {
                    required : "请输入排序数字",
                    digits:'只能输入整数',
                    minlength : jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值")
				}
			},
			submitHandler : function(form) {
				editEsHomeModule(form);
			}
		});

		//验证在模态框出现前加载
		  $("#editEsHomeModuleForm").on('show.bs.modal', function(event) {
		    var guid = isspview_applicationmanage_esHomeModulePage.getCurRow().getData().guid;
		  //模态框拖动********************
			$(this).draggable({
				handle:".modal-header"
			});
			$(this).css("overflow","hidden");
			//*************************************
		    $.ajax({
			       url: _ctx + "/view/esHomeModule/loadEditObj",
			       type: "post",
		           data : {"guid" : guid
					},
			       success: function(result) {
			    	   if (result.status == '1') {
			    		   var f_showleftmenus = result.data.f_showleftmenus;
			    		   $("#edit_guid").val(result.data.guid);
			    		   $("#edit_f_mkbh").val(result.data.f_mkbh);
					       $("#edit_f_mkmc").val(result.data.f_mkmc);
					       $("#edit_f_css_class").val(result.data.f_css_class);
					       $("#edit_f_sort").val(result.data.f_sort);
					       $(":radio[name='f_showleftmenus'][value='" + f_showleftmenus + "']").prop("checked", "checked");
						}
			       }
		    });
		  });

	 var esHomeModuleValidator = $("#addEsHomeModuleForm").validate({
			rules : {
				f_mkbh : {
					required : true,
					rangelength : [ 3, 20 ],
				},

				f_mkmc : {
					required : true,
					rangelength : [ 1, 20 ],
				},
				f_css_class: {
					required: true,
					isNormal:true
				},
				f_sort: {
					required : true,
                    digits:true,
                    range: [ 1, 99 ]
				}
			},
			messages : {
				f_mkbh : {
					required : "请输入模块编号",
					rangelength : jQuery.validator.format("应为3-20位的英文字母、数字字符"),
					remote : jQuery.validator.format("{0} is already in use")
				},
				f_mkmc : {
					required : "请输入模块名称",
					minlength : jQuery.validator.format("Enter at least {0} characters")
				},
				f_css_class: {
					required: "请输入系统图标样式",
					isNormal:"请输入标准字符！"
				},
				f_sort: {
					required : "请输入排序数字",
                    digits:'只能输入整数',
                    minlength : jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值")
				}
			},
			submitHandler : function(form) {
				addEsHomeModule(form);
			}
	});

	 function addEsHomeModule (form){
         var f_sort = $("#f_sort").val();
         var sortNum = parseInt(f_sort);
		 $.ajax({
		        url:  _ctx + "/view/esHomeModule/add",
		        type: "post",
		        contentType:'application/json;charset=UTF-8',
		        data:JSON.stringify({
		        	f_mkbh:$("#f_mkbh").val(),
		        	f_mkmc:$("#f_mkmc").val(),
		        	f_showleftmenus:$("input[name='add_f_showleftmenus']:checked").val(),
		        	f_css_class:$("#f_css_class").val(),
		        	f_sort:sortNum,
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
		        				$('#modal-form-addesHomeModule').modal('hide');//关闭新增窗口
		        				isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage();
		        			}else{
		        				swal("新增模块失败!", "","error");
		        			}
		        },
		        error: function(data) {
				    swal("新增模块失败!", "","error");
				},
				beforeSend : function(){
			    	   showLoad();
			    },
			    complete : function(){
			    	   hiddenLoad();
			    }
		 });
	 }

	 function editEsHomeModule(){
         var f_sort = $("#edit_f_sort").val();
         var sortNum = parseInt(f_sort);
		$.ajax({
			url : _ctx + "/view/esHomeModule/update",
			type : "post",
			contentType:'application/json;charset=UTF-8',
			data:JSON.stringify({
					guid:$("#edit_guid").val(),
		        	f_mkbh:$("#edit_f_mkbh").val(),
		        	f_mkmc:$("#edit_f_mkmc").val(),
		        	f_showleftmenus:$("input[name='f_showleftmenus']:checked").val(),
		        	f_css_class:$("#edit_f_css_class").val(),
		        	f_sort: sortNum,
		    }),
			success : function(data) {
				if (data.status == '1') {
					swal("修改成功!", "","success");
					swal({
    					title : data.msg,
    					text  : "",
    					type : "success",
    					showCloseButton : false,
    					allowOutsideClick : false,
    					showConfirmButton : false,
    					timer : 1000
    				});
					//esHomeModule.reLoadEsHomeModulePage();
					//  $("#esHomeModuleTable").tabulator("updateRow",isspview_applicationmanage_esHomeModulePage.getCurRow(),{
					// 	 f_mkbh: $("#edit_f_mkbh").val(),
					// 	 f_mkmc: $("#edit_f_mkmc").val(),
					// 	 f_showleftmenus: $("input[name='f_showleftmenus']:checked").val(),
					// 	 f_css_class:$("#edit_f_css_class").val(),
					// 	 f_sort: $("#edit_f_sort").val(),
	              	// });
					// setTimeout(function() {
						$('#editEsHomeModuleForm').modal('hide');//关闭编辑窗口
					// }, 1000)
                    isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage();
				} else {
					swal("修改失败!", data.msg, "error");
				}
			},
			error : function(data) {
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

	 /***************图标start at 20191022 ***************/
	    function fontsView(data){
	        indexFont=layer.open({
	            type: 1,
	            area: ['52.5vw', '68vh'],
	            title: '查看详情',
	            content : $('#fontsDIV')
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
	                    var div=document.getElementById("fonts");
	                    var fontsList=result.list;
	                    for(var index in fontsList){
	                        var fontName=fontsList[index];
	                        var d=document.createElement("div");
	                        d.setAttribute("style","margin-left:10px;margin-top:10px;margin-right:10px;margin-bottom: 10px;float: left;");
	                        var a=document.createElement("a");
	                        a.setAttribute("href","javascript:void(0)");
							a.setAttribute("onclick","isspview_applicationmanage_esHomeModule.addFontValue('"+fontName+"')");
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
		 reLoadEsHomeModulePage:function (datas){
			 $.ajax({
					url : _ctx + '/view/esHomeModule/showPage',
					type : "post",
					data : datas,
					 beforeSend: function () {
		 		        	showLoad();
		 		        },
					success : function(data) {
						$('#esHomeModule_dataCenter').html(data);
					},
					complete: function () {
	 	            	hiddenLoad();
	 	            },
					error : function(XMLHttpRequest,textStatus, errorThrown) {
						toastr.error('', '查询失败');
					}
			});
		 },
	 	 Park_pageInit : function(){
		 	isspview_applicationmanage_esHomeModule.reLoadEsHomeModulePage();
		 }
		 //Start add by yangjx at 20191022 for 系统图标显示形态
		 ,
		 getFotntsAddOrEdit:function(){
             fontsView();
         },
         addFontValue:function (fontName) {
			 $(".systemIcon").val(fontName);
             closeFontsLayer();
         }
         //End add by yangjx at 20191022
	 }
 })(jQuery, window, document);
 isspview_applicationmanage_esHomeModule.Park_pageInit();
 </script>
