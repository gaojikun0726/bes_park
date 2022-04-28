<#--
  描述:区域表
  作者:YangChao
  时间:2020-08-28 11:48:44
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;区域表列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Region_add" data-toggle="modal"  href="#RegionformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>
        <#-- 搜索框 -->
        <div class="zc_search find">
             <input type="text" class="find-style"  id="Region_search" placeholder="编号、名称">
             <button id="queryRegionBtn" onclick="zhdg_Region_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="Region_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="RegionformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="RegionformModelIcon">&nbsp; 添加区域</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="RegionModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="RegionOperateType">
                <input type="hidden" name="fId" id="RegionfId">
				<div class="form-group">
					<label class="col-sm-3 control-label">区域名称</label>
					<div class="col-sm-8">
						<input type="text" id="RegionfRegionName" name="fRegionName" placeholder="区域名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">备注</label>
					<div class="col-sm-8">
						<input type="text" id="RegionfRemark" name="fRemark" placeholder="备注" required  class="form-control">
					</div>
				</div>
<#--				<div class="form-group">-->
<#--					<label class="col-sm-3 control-label">创建时间</label>-->
<#--					<div class="col-sm-8">-->
<#--						<input type="text" id="RegionfCreateTime" name="fCreateTime" placeholder="创建时间" required  class="form-control">-->
<#--					</div>-->
<#--				</div>-->
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
;var zhdg_Region_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";

    // 打开模态框-回显
    $('#RegionformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#RegionformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#RegionOperateType").val("C")
            $("#RegionformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加区域");
        }else{
            $("#RegionOperateType").val("U")
            $("#RegionformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改区域");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/region/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#RegionfId").val(obj.fId);
                $("#RegionfRegionName").val(obj.fRegionName);
                $("#RegionfRemark").val(obj.fRemark);
                $("#RegionfCreateTime").val(obj.fCreateTime);
            }
        });
    }

    //关闭模态框清空表单值
    $("#RegionformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        RegionValidators.resetForm();
    });

    //表单验证
    var RegionValidators = $("#RegionModuleForm").validate({
        rules : {
            fRegionName:{
                required: true
            },
            fRemark:{
                required: true
            },
            fCreateTime:{
                required: true
            }
        },
        messages : {
            fRegionName:{
                required: '区域名称不能为空'
            },
            fRemark:{
                required: '备注不能为空'
            },
            fCreateTime:{
                required: '创建时间不能为空'
            }
        },
        submitHandler: function(form) {
            RegionModulePut(form);
        }
    });
    // form提交
    function RegionModulePut(form){
	 	var path = $("#RegionOperateType").val()=="C"?"add":"update";
		$.issp_ajax({
			url : _ctx + "/zhdg/region/"+path,
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
    $(document).on('click','#Region_iboxTable button.delete',function() {
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
            url : _ctx + "/zhdg/region/delete",
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
        $("#RegionformModel").modal("hide");// 关闭模态框
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
            zhdg_Region_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhdg/region/getSearch",
			type : "post",
			data:({keywords: $("#Region_search").val()}),
			success : function(data) {
                $("#Region_ibox").html(data);
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
            zhdg_Region_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_Region_listModuleClosure.pageInit();
 </script>
