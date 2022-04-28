<#--
  描述:协议处理类型维护
  作者:YangChao
  时间:2020-08-31 17:41:28
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;协议处理类型维护列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Agreehandle_add" data-toggle="modal"  href="#AgreehandleformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>
        <#-- 搜索框 -->
        <div class="zc_search find">
             <input type="text" class="find-style"  id="Agreehandle_search" placeholder="编号、名称">
             <button id="queryAgreehandleBtn"  onclick="zhdg_Agreehandle_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="Agreehandle_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="AgreehandleformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="AgreehandleformModelIcon">&nbsp; 添加协议处理类型维护</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="AgreehandleModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="AgreehandleOperateType">
                <input type="hidden" name="id" id="Agreehandleid">
				<div class="form-group">
					<label class="col-sm-3 control-label">处理类型名称</label>
					<div class="col-sm-8">
						<input type="text" id="Agreehandlename" name="name" placeholder="处理类型名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">处理类型说明</label>
					<div class="col-sm-8">
						<input type="text" id="Agreehandleremarks" name="remarks" placeholder="处理类型说明" required  class="form-control">
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
;var zhdg_Agreehandle_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";

    // 打开模态框-回显
    $('#AgreehandleformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#AgreehandleformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#AgreehandleOperateType").val("C")
            $("#AgreehandleformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加协议操作类型");
        }else{
            $("#AgreehandleOperateType").val("U")
            $("#AgreehandleformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改协议操作类型");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/agreehandle/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#Agreehandleid").val(obj.id);
                $("#Agreehandlename").val(obj.name);
                $("#Agreehandleremarks").val(obj.remarks);
            }
        });
    }

    //关闭模态框清空表单值
    $("#AgreehandleformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        AgreehandleValidators.resetForm();
    });

    //表单验证
    var AgreehandleValidators = $("#AgreehandleModuleForm").validate({
        rules : {
            name:{
                required: true
            },
            remarks:{
                required: true
            }
        },
        messages : {
            name:{
                required: '处理类型名称不能为空'
            },
            remarks:{
                required: '处理类型说明不能为空'
            }
        },
        submitHandler: function(form) {
            AgreehandleModulePut(form);
        }
    });
    // form提交
    function AgreehandleModulePut(form){
	 	var path = $("#AgreehandleOperateType").val()=="C"?"add":"update";
		$.issp_ajax({
			url : _ctx + "/zhdg/agreehandle/"+path,
			type : "post",
			data:$(form).serialize(),
			success : function(data) {
                resSwal(data.status,data.msg);
			},
			error : function(data) {
                resSwal(data.status,data.msg);
			}
        });
	}

    //删除点击事件
    $(document).on('click','#Agreehandle_iboxTable button.delete',function() {
        var id = $(this).data("id");
        swal({
            title : "您确定要删除吗?",
            text : "信息删除后将不可恢复!",
            type : "warning",
            showCancelButton : true,
            confirmButtonColor : "#1ab394",
            confirmButtonText : "确定",
            closeOnConfirm : false
        },function(){deleteSth(id)});
    });

    // 删除执行事件
    function deleteSth(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/agreehandle/delete",
            type : "post",
            data : {"id" : id},
            success : function(data) {
                resSwal(data.status,data.msg);
            },
            error : function(data) {
                resSwal(data.status,data.msg);
            }
        });
    }

    // swal 返回集合
    function resSwal(status,msg){
        $("#AgreehandleformModel").modal("hide");// 关闭模态框
        if (status == '1') {
            swal( {
                title: msg,
                text: "",
                type: "success",
                showCloseButton:false,
                allowOutsideClick:false,
                showConfirmButton: false,
                timer:1000
            });
            //重新加载列表及分页
            zhdg_Agreehandle_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhdg/agreehandle/getSearch",
			type : "post",
			data:({keywords: $("#Agreehandle_search").val()}),
			success : function(data) {
                $("#Agreehandle_ibox").html(data);
			},
			error : function(data) {

			}
        });
	}

    return {
        Search:function() {
            DataSearch();
        },

        pageInit : function(){
            zhdg_Agreehandle_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_Agreehandle_listModuleClosure.pageInit();
 </script>
