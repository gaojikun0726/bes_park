<#--
  描述:动态协议配置
  作者:YangChao
  时间:2020-09-03 09:40:12
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;动态协议配置列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Dynamicagree_add" data-toggle="modal"  href="#DynamicagreeformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>
        <#-- 搜索框 -->
        <div class="zc_search find">
             <input type="text" class="find-style"  id="Dynamicagree_search" placeholder="编号、名称">
             <button id="queryDynamicagreeBtn" onclick="zhdg_Dynamicagree_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="Dynamicagree_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="DynamicagreeformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="DynamicagreeformModelIcon">&nbsp; 添加动态协议配置</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="DynamicagreeModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="DynamicagreeOperateType">
                <input type="hidden" name="id" id="Dynamicagreeid">
                <#--<input type="hidden" id="Dynamicagreeagreeid" name="agreeid" placeholder="协议类型id" value="1">-->
<#--				<div class="form-group">-->
<#--					<label class="col-sm-3 control-label">协议类型id</label>-->
<#--					<div class="col-sm-8">-->
<#--						<input type="text" id="Dynamicagreeagreeid" name="agreeid" placeholder="协议类型id" required  class="form-control">-->
<#--					</div>-->
<#--				</div>-->
				<div class="form-group">
					<label class="col-sm-3 control-label">名称</label>
					<div class="col-sm-8">
						<input type="text" id="Dynamicagreename" name="name" placeholder="名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">对象名称</label>
					<div class="col-sm-8">
						<input type="text" id="Dynamicagreeoname" name="oname" placeholder="对象名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">协议起始位</label>
					<div class="col-sm-8">
						<input type="text" id="Dynamicagreestart" name="start" placeholder="协议起始位" required  class="form-control number">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">协议终止位</label>
					<div class="col-sm-8">
						<input type="text" id="Dynamicagreeend" name="end" placeholder="协议终止位" required  class="form-control number">
					</div>
				</div>
				<div class="form-group"><#--0-是/解析 1-否/不解析-->
                    <label class="col-sm-3 control-label">是否解析</label>
					<div class="col-sm-8">
                        <select id="Dynamicagreeanalysisposition" name="analysisposition" class="input-sm form-control inline">
                            <option value="0">是</option>
                            <option value="1" selected>否</option>
                        </select>
                    </div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">字节位位置</label>
					<div class="col-sm-8">
						<input type="text" id="Dynamicagreeposition" name="position" placeholder="(1-8)"  class="form-control  number">
					</div>
				</div>
<#--				<div class="form-group">-->
<#--					<label class="col-sm-3 control-label">报警类型id--关联seb_warnType</label>-->
<#--					<div class="col-sm-8">-->
<#--						<input type="text" id="Dynamicagreewarnid" name="warnid" placeholder="报警类型id--关联seb_warnType" required  class="form-control">-->
<#--					</div>-->
<#--				</div>-->
				<div class="form-group">
					<label class="col-sm-3 control-label">协议处理类型</label>
					<div class="col-sm-8">
<#--						<input type="text" id="Dynamicagreehandleid" name="handleid" placeholder="协议处理类型id---关联seb_agreeHandle" required  class="form-control">-->
                        <select id="Dynamicagreehandleid" name="handleid" class="input-sm form-control inline">
                        </select>
                    </div>
				</div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">指令码</label>
                    <div class="col-sm-8">
                        <select id="Dynamicagreeordercode" name="ordercode" class="input-sm form-control inline">
                            <option value="171" selected>数据包</option>
                            <option value="151">配置包</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">报警类型</label>
                    <div class="col-sm-8">
                        <select id="Dynamicagreewarnid" name="warnid" class="input-sm form-control inline">
                        </select>
                    </div>
                </div>
				<div class="form-group"><#--0.启用--1.停用-->
					<label class="col-sm-3 control-label">是否启用</label>
					<div class="col-sm-8">
<#--						<input type="text" id="Dynamicagreestate" name="state" placeholder="0.启用--1.停用" required  class="form-control">-->
                        <select id="Dynamicagreestate" name="state" class="input-sm form-control inline">
                            <option value="0" selected>启用</option>
                            <option value="1">停用</option>
                        </select>
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
;var zhdg_Dynamicagree_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";

    // 打开模态框-回显
    $('#DynamicagreeformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#DynamicagreeformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#DynamicagreeOperateType").val("C")
            $("#DynamicagreeformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加协议信息");
        }else{
            $("#DynamicagreeOperateType").val("U")
            $("#DynamicagreeformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改协议信息");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/dynamicagree/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#Dynamicagreeid").val(obj.id);
                $("#Dynamicagreeordercode").val(obj.ordercode);
                $("#Dynamicagreeagreeid").val(obj.agreeid);
                $("#Dynamicagreename").val(obj.name);
                $("#Dynamicagreeoname").val(obj.oname);
                $("#Dynamicagreestart").val(obj.start);
                $("#Dynamicagreeend").val(obj.end);
                $("#Dynamicagreeanalysisposition").val(obj.analysisposition);
                $("#Dynamicagreeposition").val(obj.position);
                $("#Dynamicagreewarnid").val(obj.warnid);
                $("#Dynamicagreehandleid").val(obj.handleid);
                $("#Dynamicagreestate").val(obj.state);
            }
        });
    }

    //关闭模态框清空表单值
    $("#DynamicagreeformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        DynamicagreeValidators.resetForm();
    });

    //表单验证
    var DynamicagreeValidators = $("#DynamicagreeModuleForm").validate({
        rules : {
            ordercode:{
                required: true
            },
            // agreeid:{
            //     required: true
            // },
            name:{
                required: true
            },
            oname:{
                required: true
            },
            start:{
                required: true
            },
            end:{
                required: true
            },
            analysisposition:{
                required: true
            },
            position:{
                required: false,
                digits:true,
                range:[1,8]
            },
            handleid:{
                required: true
            },
            state:{
                required: true
            }
        },
        messages : {
            ordercode:{
                required: '指令码不能为空'
            },
            // agreeid:{
            //     required: '协议类型id不能为空'
            // },
            name:{
                required: '名称不能为空'
            },
            oname:{
                required: '对象名称不能为空'
            },
            start:{
                required: '协议起始位不能为空'
            },
            end:{
                required: '协议终止位不能为空'
            },
            analysisposition:{
                required: '0-是/解析 1-否/不解析 不能为空'
            },
            position:{
                // required: '字节位位置不能为空',
                digits:'只能输入整数',
                range:$.validator.format("请输入一个介于 {0} 和 {1} 之间的值")
            },
            handleid:{
                required: '协议处理类型id---关联seb_agreeHandle不能为空'
            },
            state:{
                required: '0.启用--1.停用不能为空'
            }
        },
        submitHandler: function(form) {
            DynamicagreeModulePut(form);
        }
    });
    // form提交
    function DynamicagreeModulePut(form){
	 	var path = $("#DynamicagreeOperateType").val()=="C"?"add":"update";
		$.issp_ajax({
			url : _ctx + "/zhdg/dynamicagree/"+path,
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
    $(document).on('click','#Dynamicagree_iboxTable button.delete',function() {
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
            url : _ctx + "/zhdg/dynamicagree/delete",
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
        $("#DynamicagreeformModel").modal("hide");// 关闭模态框
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
            zhdg_Dynamicagree_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhdg/dynamicagree/getSearch",
			type : "post",
			data:({keywords: $("#Dynamicagree_search").val(),pageNum:$("#Dynamicagree_pageNum").val()}),
			success : function(data) {
                $("#Dynamicagree_ibox").html(data);
			},
			error : function(data) {

			}
        });
	}
	// 协议处理类型查询
    function handleSearch(){
        $.issp_ajax({
            url : _ctx + "/zhdg/dynamicagree/getHandleSearch",
            type : "post",
            data:({}),
            success : function(data) {
                var list = data.list;
                var opt="";
                list.forEach(function(e){
                    opt += "<option value='"+e.id+"'>"+e.name+"---"+e.remarks+"</option>";
                })
                $("#Dynamicagreehandleid").append(opt);
            }
        });
    }
    /** 报警类型select查询 */
    function warnTypeSearch () {
        $.issp_ajax({
            url: _ctx + "/zhdg/warntype/getAllWarnType",
            type: "post",
            data: ({
            }),
            success: function (data) {
                if(data.status == '1'){
                    $("#Dynamicagreewarnid").empty().append("<option value=''>---请选择报警类型---</option>");
                    data.list.forEach(function (i) {
                        $("#Dynamicagreewarnid").append("<option value='"+i.id+"'>"+i.warntype+"</option>");
                    })
                    $("#Dynamicagreewarnid").append("<option value='0'>离线</option>");
                }
            }
        });
    }


    return {
        Search:function() {
            warnTypeSearch();
            DataSearch();
            handleSearch();
        },

        pageInit : function(){
            zhdg_Dynamicagree_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_Dynamicagree_listModuleClosure.pageInit();
 </script>
