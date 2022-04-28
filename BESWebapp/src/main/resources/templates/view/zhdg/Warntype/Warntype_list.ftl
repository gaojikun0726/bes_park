<#--
  描述:报警类型维护表
  作者:YangChao
  时间:2020-09-14 11:20:24
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;报警类型维护表列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Warntype_add" data-toggle="modal"  href="#WarntypeformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>
        <#-- 搜索框 -->
        <div class="zc_search find">
             <input type="text" class="find-style"  id="Warntype_search" placeholder="编号、名称">
             <button id="queryWarntypeBtn" onClick="zhdg_Warntype_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="Warntype_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="WarntypeformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="WarntypeformModelIcon">&nbsp; 添加报警类型维护表</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="WarntypeModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="WarntypeOperateType">
                <input type="hidden" name="id" id="Warntypeid">
				<div class="form-group">
					<label class="col-sm-3 control-label">报警类型</label>
					<div class="col-sm-8">
						<input type="text" id="Warntypewarntype" name="warntype" placeholder="报警类型" required  class="form-control">
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
;var zhdg_Warntype_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";

    // 打开模态框-回显
    $('#WarntypeformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#WarntypeformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#WarntypeOperateType").val("C")
            $("#WarntypeformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加报警类型");
        }else{
            $("#WarntypeOperateType").val("U")
             // 模态框标题修改
            $("#WarntypeformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改报警类型");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/warntype/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#Warntypeid").val(obj.id);
                $("#Warntypewarntype").val(obj.warntype);
            }
        });
    }

    //关闭模态框清空表单值
    $("#WarntypeformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        WarntypeValidators.resetForm();
    });

    //表单验证
    var WarntypeValidators = $("#WarntypeModuleForm").validate({
        rules : {
            warntype:{
                required: true
            }
        },
        messages : {
            warntype:{
                required: '报警类型不能为空'
            }
        },
        submitHandler: function(form) {
            WarntypeModulePut(form);
        }
    });
    // form提交
    function WarntypeModulePut(form){
	 	var path = $("#WarntypeOperateType").val()=="C"?"add":"update";
		$.issp_ajax({
			url : _ctx + "/zhdg/warntype/"+path,
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
    $(document).on('click','#Warntype_iboxTable button.delete',function() {
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
            url : _ctx + "/zhdg/warntype/delete",
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
        $("#WarntypeformModel").modal("hide");// 关闭模态框
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
            zhdg_Warntype_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhdg/warntype/getSearch",
			type : "post",
			data:({keywords: $("#Warntype_search").val()}),
			success : function(data) {
                $("#Warntype_ibox").html(data);
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
            zhdg_Warntype_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_Warntype_listModuleClosure.pageInit();
 </script>
