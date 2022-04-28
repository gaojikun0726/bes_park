<#--
  描述:设备维护
  作者:YangChao
  时间:2020-10-09 14:28:58
-->
<div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备维护列表>>>
    </span>
    <#-- 增加按钮 -->
    <a id="Coverpoint_add" data-toggle="modal"  href="#CoverpointformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>
    <#-- 搜索框 -->
    <div class="zc_search find">
         <input type="text" class="find-style"  id="Coverpoint_search" placeholder="编号、名称">
         <button id="queryCoverpointBtn" onClick="zhjg_Coverpoint_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
    </div>
</div>
<div class="ibox" id="Coverpoint_ibox" style="height:91%"/>

<!---添加||修改 模态框----->
<div class="modal fade" id="CoverpointformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="CoverpointformModelIcon">&nbsp; 添加设备维护</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="CoverpointModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="CoverpointOperateType">
                <input type="hidden" name="fGuid" id="CoverpointfGuid">
				<div class="form-group">
					<label class="col-sm-3 control-label">设备卡号</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfCode" name="fCode" placeholder="设备卡号" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">设备名称</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfPointName" name="fPointName" placeholder="点位名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">设备IP</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfPointIp" name="fPointIp" placeholder="终端IP" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">经度</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfLongitude" name="fLongitude" placeholder="经度" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">纬度</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfLatitude" name="fLatitude" placeholder="纬度" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">详细地址</label>
					<div class="col-sm-8">
						<input type="text" id="CoverpointfRemark" name="fRemark" placeholder="详细地址" required  class="form-control">
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
;var zhjg_Coverpoint_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";

    // 打开模态框-回显
    $('#CoverpointformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#CoverpointformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#CoverpointOperateType").val("C")
            $("#CoverpointformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加设备维护");
        }else{
            $("#CoverpointOperateType").val("U")
             // 模态框标题修改
            $("#CoverpointformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改设备维护");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhjg/coverpoint/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#CoverpointfGuid").val(obj.fGuid);
                $("#CoverpointfCode").val(obj.fCode);
                $("#CoverpointfPointName").val(obj.fPointName);
                $("#CoverpointfPointIp").val(obj.fPointIp);
                $("#CoverpointfLongitude").val(obj.fLongitude);
                $("#CoverpointfLatitude").val(obj.fLatitude);
                $("#CoverpointfRemark").val(obj.fRemark);
            }
        });
    }

    //关闭模态框清空表单值
    $("#CoverpointformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        CoverpointValidators.resetForm();
    });

    //表单验证
    var CoverpointValidators = $("#CoverpointModuleForm").validate({
        rules : {
            fCode:{
                required: true
            },
            fPointName:{
                required: true
            },
            fPointIp:{
                required: true,
                isValidIP:true
            },
            fLongitude:{
                required: true,
                isNormalLongtitude:true
            },
            fLatitude:{
                required: true,
                isNormalLatitude:true
            },
            fRegionId:{
                required: true
            },
            fRemark:{
                required: true
            }
        },
        messages : {
            fCode:{
                required: '唯一编码不能为空'
            },
            fPointName:{
                required: '点位名称不能为空'
            },
            fPointIp:{
                required: '终端IP不能为空'
            },
            fLongitude:{
                required: '经度不能为空'
            },
            fLatitude:{
                required: '纬度不能为空'
            },
            fRegionId:{
                required: '所属区域不能为空'
            },
            fRemark:{
                required: '详细地址不能为空'
            }
        },
        submitHandler: function(form) {
            CoverpointModulePut(form);
        }
    });
    // form提交
    function CoverpointModulePut(form){
	 	var path = $("#CoverpointOperateType").val()=="C"?"add":"update";
		$.issp_ajax({
			url : _ctx + "/zhjg/coverpoint/"+path,
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
    $(document).on('click','#Coverpoint_iboxTable button.delete',function() {
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
            url : _ctx + "/zhjg/coverpoint/delete",
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
        $("#CoverpointformModel").modal("hide");// 关闭模态框
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
            zhjg_Coverpoint_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhjg/coverpoint/getSearch",
			type : "post",
			data:({keywords: $("#Coverpoint_search").val()}),
			success : function(data) {
                $("#Coverpoint_ibox").html(data);
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
            zhjg_Coverpoint_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhjg_Coverpoint_listModuleClosure.pageInit();
 </script>
