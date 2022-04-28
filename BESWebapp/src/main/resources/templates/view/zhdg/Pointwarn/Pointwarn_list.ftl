<#--
  描述:点位报警维护
  作者:YangChao
  时间:2020-09-16 10:20:05
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;点位报警维护列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Pointwarn_add" data-toggle="modal" href="#PointwarnformModel" class="btn btn-primary toLeft"> <i
                    class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>
        <#-- 搜索框 -->
        <div id="point_warn_type" class="zc_search find" style="position: absolute;right: 15%;width: 7.2vw;">
            <select id="Pointwarn_warnType" name="Pointwarn_warnType" class="Pointwarn_warnType"></select>
        </div>
        <div class="zc_search find">
            <input type="text" class="find-style" id="Pointwarn_search" placeholder="点位id">
            <button id="queryPointwarnBtn" onClick="zhdg_Pointwarn_listModuleClosure.Search();"><i class="fa fa-search"
                                                                                                   aria-hidden="true"></i>
            </button>
        </div>
    </div>
    <div class="ibox" id="Pointwarn_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="PointwarnformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="PointwarnformModelIcon">&nbsp; 添加点位报警维护</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="PointwarnModuleForm" class="form-horizontal">
                    <#--添加信息项-->
                    <#--新增修改标识符-->
                    <input type="hidden" name="operateType" id="PointwarnOperateType">
                    <input type="hidden" name="id" id="Pointwarnid">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">点位id</label>
                        <div class="col-sm-8">
                            <input type="text" id="Pointwarnpointid" readonly  placeholder="点击弹窗选择设备" data-toggle='modal' data-target='#PointSelectformModel' name="pointid" placeholder="" required
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警类型</label>
                        <div class="col-sm-8">
                            <select id="Pointwarnwarnid" name="warnid" class="input-sm form-control inline"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警阈值类型</label>
                        <div class="col-sm-8">
                            <select id="Pointwarnvtype" name="vtype"
                                    onchange="zhdg_Pointwarn_listModuleClosure.vTypeChange()"
                                    class="input-sm form-control inline">
                                <option value="0">定值</option>
                                <option value="1">浮动</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">定值</label>
                        <div class="col-sm-8">
                            <input type="text" id="Pointwarnvtype0" name="vtype0" placeholder="0正常-1告警"
                                   class="form-control number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">浮动阈值下限</label>
                        <div class="col-sm-8">
                            <input type="text" id="Pointwarnvtype1Min" name="vtype1Min" disabled placeholder="小于下限值报警"
                                   class="form-control number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">浮动阈值上限</label>
                        <div class="col-sm-8">
                            <input type="text" id="Pointwarnvtype1Max" name="vtype1Max" disabled placeholder="大于上限值报警"
                                   class="form-control number">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check"
                                                                                            aria-hidden="true"></i>&nbsp;确定</strong>
                            </button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal"><i
                                        class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="PointSelectformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 70vw;">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title" id="PointSelectformModelIcon"><i class="fa fa-bars" aria-hidden="true"></i>&nbsp; 点位列表</h4>

            </div>
            <div class="modal-body" style="height: 60vh;padding: 20px 25px 30px 25px;">
                <div class="ibox" id="PointwarnList" style="height:91%"></div>
                <div style="position: absolute;right:0;right: 2vw;bottom: 6vh;">
                    <button class="btn btn-md btn-primary" type="button" onclick="zhdg_Pointwarn_listModuleClosure.PointSelect()">
                        <strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    ;var zhdg_Pointwarn_listModuleClosure = (function ($, window, document, undefined) {
        var _ctx = "${ctx}";

        // 打开点位模态框
        $('#PointSelectformModel').on('show.bs.modal', function (event) {
            // 居中显示
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#PointSelectformModel .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
            var id = $(event.relatedTarget).data("id");
            // 新增
            if (id == "undefined" || id == null || id == "") {
                $.issp_ajax({
                    url : _ctx + "/zhdg/pointwarn/point/getSearch",
                    type : "post",
                    data:({}),
                    success : function(data) {
                        $("#PointwarnList").html(data);
                    }
                });
            }
        });

        // 打开模态框-回显
        $('#PointwarnformModel').on('show.bs.modal', function (event) {
            // 居中显示
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#PointwarnformModel .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });

            // 标识符判断添加
            var id = $(event.relatedTarget).data("id");
            if (id == "undefined" || id == null || id == "") {
                $("#PointwarnOperateType").val("C")
                $("#PointwarnformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加点位报警维护");
                $("#Pointwarnpointid").prop("disabled",false);
            } else {
                $("#PointwarnOperateType").val("U")
                // 模态框标题修改
                $("#PointwarnformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改点位报警维护");
                $("#Pointwarnpointid").prop("disabled",true);
                // U修改添加回显
                editShow(id);
            }
        });

        // 编辑回显
        function editShow(id) {
            $.issp_ajax({
                url: _ctx + "/zhdg/pointwarn/getSearchById",
                type: "post",
                data: {"id": id},
                success: function (data) {
                    var obj = eval("(" + data.data + ")");
                    $("#Pointwarnvtype").val(obj.vtype);
                    zhdg_Pointwarn_listModuleClosure.vTypeChange();
                    $("#Pointwarnid").val(obj.id);
                    $("#Pointwarnpointid").val(obj.pointid);
                    $("#Pointwarnwarnid").val(obj.warnid);
                    $("#Pointwarnvtype").val(obj.vtype);
                    $("#Pointwarnvtype0").val(obj.vtype0);
                    $("#Pointwarnvtype1Min").val(obj.vtype1Min);
                    $("#Pointwarnvtype1Max").val(obj.vtype1Max);
                }
            });
        }

        //关闭模态框清空表单值
        $("#PointwarnformModel").on('hidden.bs.modal', function (event) {
            $(this).find("input").val("");
            PointwarnValidators.resetForm();
        });

        //表单验证
        var PointwarnValidators = $("#PointwarnModuleForm").validate({
            rules: {
                pointid: {
                    required: true
                },
                warnid: {
                    required: true
                },
                vtype: {
                    required: true
                },
                vtype0: {
                    required: false,
                    range: [0, 1]
                },
            },
            messages: {
                pointid: {
                    required: '点位id不能为空'
                },
                warnid: {
                    required: '报警类型不能为空'
                },
                vtype: {
                    required: '报警阈值类型不能为空'
                },
                vtype0: {
                    required: '定值不能为空'
                },
            },
            submitHandler: function (form) {
                PointwarnModulePut(form);
            }
        });

        // form提交
        function PointwarnModulePut(form) {
            var path = $("#PointwarnOperateType").val() == "C" ? "add" : "update";
            $.issp_ajax({
                url: _ctx + "/zhdg/pointwarn/" + path,
                type: "post",
                data: $(form).serialize(),
                success: function (data) {
                    resSwal(data.status, data.msg);
                },
                error: function (data) {
                    resSwal(data.status, data.msg);
                }
            });
        }

        //删除点击事件
        $(document).on('click', '#Pointwarn_iboxTable button.delete', function () {
            var id = $(this).data("id");
            swal({
                title: "您确定要删除吗?",
                text: "信息删除后将不可恢复!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "确定",
                closeOnConfirm: false
            }, function () {
                deleteSth(id)
            });
        });

        // 删除执行事件
        function deleteSth(id) {
            $.issp_ajax({
                url: _ctx + "/zhdg/pointwarn/delete",
                type: "post",
                data: {"id": id},
                success: function (data) {
                    resSwal(data.status, data.msg);
                },
                error: function (data) {
                    resSwal(data.status, data.msg);
                }
            });
        }

        // swal 返回集合
        function resSwal(status, msg) {
            $("#PointwarnformModel").modal("hide");// 关闭模态框
            if (status == '1') {
                swal({
                    title: msg,
                    text: "",
                    type: "success",
                    showCloseButton: false,
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                //重新加载列表及分页
                zhdg_Pointwarn_listModuleClosure.Search()
            } else {
                swal({
                    title: msg,
                    text: "",
                    type: "error",
                    showCloseButton: false,
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 2000
                });
            }
        }

        // 数据查询
        function DataSearch() {
            $.issp_ajax({
                url: _ctx + "/zhdg/pointwarn/getSearch",
                type: "post",
                data: ({
                    pointid: $("#Pointwarn_search").val(),
                    warnid: $("#Pointwarn_warnType").val()
                }),
                success: function (data) {
                    $("#Pointwarn_ibox").html(data);
                },
                error: function (data) {

                }
            });
        }

        /** 报警类型select查询 */
        function warnTypeSearch(id) {
            $.issp_ajax({
                url: _ctx + "/zhdg/warntype/getAllWarnType",
                type: "post",
                data: ({}),
                success: function (data) {
                    if (data.status == '1') {
                        $("#" + id).empty().append("<option value=''>---请选择报警类型---</option>");
                        data.list.forEach(function (i) {
                            $("#" + id).append("<option value='" + i.id + "'>" + i.warntype + "</option>");
                        })
                        if (id != "Pointwarnwarnid") {
                            $("#" + id).append("<option value='0'>离线</option>");
                        }
                    }
                }
            });
        }

        return {
            Search: function () {
                DataSearch();
            },
            vTypeChange: function () {
                $("#Pointwarnvtype0").val("");
                $("#Pointwarnvtype1Min").val("");
                $("#Pointwarnvtype1Max").val("");
                // 0定值 1浮动
                var vtype = $("#Pointwarnvtype").val();
                if(vtype==0){
                    $("#Pointwarnvtype0").prop("disabled",false);
                    $("#Pointwarnvtype1Min").prop("disabled",true);
                    $("#Pointwarnvtype1Max").prop("disabled",true);
                }else if(vtype==1){
                    $("#Pointwarnvtype0").prop("disabled",true);
                    $("#Pointwarnvtype1Min").prop("disabled",false);
                    $("#Pointwarnvtype1Max").prop("disabled",false);
                }else{
                    $("#Pointwarnvtype0").prop("disabled",true);
                    $("#Pointwarnvtype1Min").prop("disabled",true);
                    $("#Pointwarnvtype1Max").prop("disabled",true);
                }
            },
            PointSelect : function(){
                $("#PointSelectformModel").modal("hide");
                var fCodeList = zhdg_Pointwarnpage_listModuleClosure.getSelectedData();
                $("#Pointwarnpointid").val(fCodeList.join(","));
            },
            pageInit: function () {
                warnTypeSearch("Pointwarn_warnType");
                warnTypeSearch("Pointwarnwarnid");
                zhdg_Pointwarn_listModuleClosure.Search();
            }
        }
    })(jQuery, window, document);
    zhdg_Pointwarn_listModuleClosure.pageInit();
</script>
