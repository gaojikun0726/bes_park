<#--
  描述:报警历史记录表
  作者:zs
  时间:2020-09-14 11:54:54
-->
<div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;报警历史记录表列表>>>
    </span>
    <#-- 清空历史记录 -->
    <a class="btn btn-primary toLeft" onclick="WarnInfo_listModule.CleanRecord()">
        <i class="fa fa-trash-o" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;清空
    </a>
    <#-- 搜索框 -->
    <#--<div class="zc_search find" style="position: absolute;right: 15%;width: 7.2vw;">-->
        <#--<select id="WarnInfo_warnType" name="WarnInfo_warnType" style="background-color: #09294dfc;border: 1px solid #001B3A;"></select>-->
    <#--</div>-->
    <div class="zc_search find">
        <input type="text" style="border-color: rgb(117, 191, 216);color: rgb(117, 191, 216);" class="find-style input-datecheck" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" name="WarnInfo_beginTime"  id="WarnInfo_beginTime" placeholder="开始日期">
        <input type="text" style="border-color: rgb(117, 191, 216);color: rgb(117, 191, 216);" class="find-style input-datecheck" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" name="WarnInfo_endTime" id="WarnInfo_endTime" placeholder="结束日期">
        <button onClick="WarnInfo_listModule.Search();"><i class="fa fa-search" aria-hidden="true"></i>
        </button>
    </div>
</div>
<div class="ibox" id="WarnInfo_ibox" style="height:91%"></div>

<!---添加||修改 模态框----->
<div class="modal fade" id="WarnInfoformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="WarnInfoformModelIcon">&nbsp; 添加报警历史记录表</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="WarnInfoModuleForm" class="form-horizontal">
                    <#--添加信息项-->
                    <#--新增修改标识符-->
                    <input type="hidden" name="operateType" id="WarnInfoOperateType">
                    <input type="hidden" name="id" id="WarnInfoid">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">点位id--关联seb_point-f_code</label>
                        <div class="col-sm-8">
                            <input type="text" id="WarnInfoCoverId" name="pointid"
                                   placeholder="点位id--关联seb_point-f_code" required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警id</label>
                        <div class="col-sm-8">
                            <input type="text" id="WarnInfowarnid" name="warnid" placeholder="报警id" required
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警类型</label>
                        <div class="col-sm-8">
                            <input type="text" id="WarnInfowarntype" name="warntype" placeholder="报警类型" required
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报警说明</label>
                        <div class="col-sm-8">
                            <input type="text" id="WarnInfowarnmsg" name="warnmsg" placeholder="报警说明" required
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-8">
                            <input type="text" id="WarnInfocreattime" name="creattime" placeholder="创建时间" required
                                   class="form-control">
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

<script type="text/javascript">
    var WarnInfo_listModule = (function ($, window, document, undefined) {
        var _ctx = "${ctx}";

        // 打开模态框-回显
        $('#WarnInfoformModel').on('show.bs.modal', function (event) {
            // 居中显示
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#WarnInfoformModel .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });

            // 标识符判断添加
            var id = $(event.relatedTarget).data("id");
            if (id == "undefined" || id == null || id == "") {
                $("#WarnInfoOperateType").val("C")
                $("#WarnInfoformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加报警历史记录表");
            } else {
                $("#WarnInfoOperateType").val("U")
                // 模态框标题修改
                $("#WarnInfoformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改报警历史记录表");
                // U修改添加回显
                editShow(id);
            }
        });

        // 编辑回显
        function editShow(id) {
            $.issp_ajax({
                url: _ctx + "/zhjg/warnrecord/getSearchById",
                type: "post",
                data: {"id": id},
                success: function (data) {
                    var obj = eval("(" + data.data + ")");
                    $("#WarnInfoid").val(obj.id);
                    $("#WarnInfoCoverId").val(obj.pointid);
                    $("#WarnInfowarnid").val(obj.warnid);
                    $("#WarnInfowarntype").val(obj.warntype);
                    $("#WarnInfowarnmsg").val(obj.warnmsg);
                    $("#WarnInfocreattime").val(obj.creattime);
                }
            });
        }

        //关闭模态框清空表单值
        $("#WarnInfoformModel").on('hidden.bs.modal', function (event) {
            $(this).find("input").val("");
            WarnRecordValidators.resetForm();
        });

        //表单验证
        var WarnRecordValidators = $("#WarnInfoModuleForm").validate({
            rules: {
                pointid: {
                    required: true
                },
                warnid: {
                    required: true
                },
                warntype: {
                    required: true
                },
                warnmsg: {
                    required: true
                },
                creattime: {
                    required: true
                }
            },
            messages: {
                pointid: {
                    required: '点位id--关联seb_point-f_code不能为空'
                },
                warnid: {
                    required: '报警id不能为空'
                },
                warntype: {
                    required: '报警类型不能为空'
                },
                warnmsg: {
                    required: '报警说明不能为空'
                },
                creattime: {
                    required: '创建时间不能为空'
                }
            },
            submitHandler: function (form) {
                WarnRecordModulePut(form);
            }
        });

        // form提交
        function WarnRecordModulePut(form) {
            var path = $("#WarnInfoOperateType").val() == "C" ? "add" : "update";
            $.issp_ajax({
                url: _ctx + "/zhjg/warnrecord/" + path,
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
        $(document).on('click', '#WarnInfo_iboxTable button.delete', function () {
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
                url: _ctx + "/zhjg/warnrecord/delete",
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
            $("#WarnInfoformModel").modal("hide");// 关闭模态框
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
                WarnInfo_listModule.Search()
            } else {
                swal(msg, "error");
            }
        }

        // 数据查询
        function DataSearch() {
            var date1 = new Date(($("#WarnInfo_beginTime").val()).replace(/-/g,"/"));
            var date2 = new Date(($("#WarnInfo_endTime").val()).replace(/-/g,"/"));
            if(date2.getTime()<date1.getTime()){
                swal( "开始时间不能大于结束时间！","", "warning");
                $("#WarnInfo_endTime").val("");
                return false;
            }
            $.issp_ajax({
                url: _ctx + "/zhjg/warn/getSearch",
                type: "post",
                data: ({
                    warnid: $("#WarnInfo_warnType").val(),
                    beginTime: $("#WarnInfo_beginTime").val(),
                    endTime: $("#WarnInfo_endTime").val()
                }),
                success: function (data) {
                    $("#WarnInfo_ibox").html(data);
                }
            });
        }

        /** 历史记录清空 */
        function WarnRecordClean() {
            swal({
                title : "您确定要清空报警历史记录吗?",
                text : "清空后将不可恢复!",
                type : "warning",
                showCancelButton : true,
                confirmButtonColor : "#1ab394",
                confirmButtonText : "确定",
                closeOnConfirm : false
            }, function() {
                $.issp_ajax({
                    url: _ctx + "/zhjg/warn/clean",
                    type: "post",
                    data: {},
                    success: function (data) {
                        resSwal(data.status, data.msg);
                    },
                    error: function (data) {
                        resSwal(data.status, data.msg);
                    }
                });
            });
        }

        // /** 报警类型select查询 */
        // function warnTypeSearch () {
        //     $.issp_ajax({
        //         url: _ctx + "/zhjg/warntype/getAllWarnType",
        //         type: "post",
        //         data: ({
        //         }),
        //         success: function (data) {
        //             if(data.status == '1'){
        //                 $("#WarnInfo_warnType").empty().append("<option value=''>---请选择报警类型---</option>");
        //                 data.list.forEach(function (i) {
        //                     $("#WarnInfo_warnType").append("<option value='"+i.id+"'>"+i.warntype+"</option>");
        //                 })
        //                 $("#WarnInfo_warnType").append("<option value='0'>离线</option>");
        //             }
        //         }
        //     });
        // }

        return {
            Search: function () {
                // warnTypeSearch();
                DataSearch();
            },
            CleanRecord: function(){
                  WarnRecordClean();
            },

            pageInit: function () {
                WarnInfo_listModule.Search();
            }
        }
    })(jQuery, window, document);
    WarnInfo_listModule.pageInit();
</script>