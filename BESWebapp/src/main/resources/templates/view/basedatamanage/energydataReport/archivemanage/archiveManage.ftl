<#--
	历史数据管理
-->
<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;历史数据记录列表>>>
		</span>
    <!-- 增加按钮 -->
    <a id="addArchiveManage" data-toggle="modal" href="#modal-form-addArchiveManage" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true">补录</i>
    </a>

    <!-- 搜索框 -->
    <div class="zc_search find">
        <input type="text" class="find-style" id="searchArchiveManageInput" name="searchArchiveManageInput" placeholder="文件名称">
        <button id="searchArchiveManageButton"><i class="fa fa-search" aria-hidden="true"></i></button>
    </div>
</div>

<!---添加开始----->
<div class="modal fade" id="modal-form-addArchiveManage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;数据补录</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addArchiveManageForm" name="addArchiveManageForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input id="addArchiveManageStartTime"
                                   name="addArchiveManageStartTime"
                                   placeholder="请选择开始时间"
                                   required
                                   class="form-control"
                                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH', firstDayOfWeek:1,readOnly:true, maxDate: '#F{$dp.$D(\'addArchiveManageEndTime\')||\'%y-%M\'}'})"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input id="addArchiveManageEndTime"
                                   name="addArchiveManageEndTime"
                                   placeholder="请选择结束时间"
                                   required
                                   class="form-control"
                                   onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH', firstDayOfWeek:1,readOnly:true, minDate:'#F{$dp.$D(\'addArchiveManageStartTime\')}', maxDate:'%y-%M' })" />
                        </div>
                    </div>

                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 添加结束 -->

<!---分页列表----->
<div class="ibox" id="archiveManage_ibox" style="height:92%">
    <#include "/view/basedatamanage/energydataReport/archivemanage/archiveManage_page.ftl"/>
</div>


<script type="text/javascript">
    ;
    var archiveManage = (function ($, window, document, undefined) {
        var _ctx = '${ctx}';

        $(function () {
            // 获取历史数据加载到页面表格
            getArchiveManageListPage((data) => {
                $('#archiveManage_ibox').html(data);
            });
        });


        //下载数据点击事件
        $(document).on('click', '#archiveManageTable button.download', function (data) {

            var id = $(this).data('id');

            download(id);

            setTimeout(() => {
                $('#archiveManageTable').tabulator(
                    "updateRow", getArchiveManageListPage((data) => {
                        $('#archiveManage_ibox').html(data);
                    }, null, archiveManage_page.pageNum));
            }, 200)


        });

        $('#searchArchiveManageButton').click(() => {

            var value = $('#searchArchiveManageInput').val();

            // 获取历史数据加载到页面表格
            getArchiveManageListPage((data) => {
                $('#archiveManage_ibox').html(data);
            }, value);

        });

        //关闭模态框清空表单值
        $("#modal-form-addArchiveManage").on('hidden.bs.modal',function(event) {
            $(this).find("input").val("");
            addValidator.resetForm();
        });

        //添加
        var addValidator = $("#addArchiveManageForm").validate({
            rules : {
                addArchiveManageStartTime : {
                    required : true,
                },

                addArchiveManageEndTime : {
                    required : true,
                }
            },
            messages : {
                addArchiveManageStartTime : {
                    required : "请选择开始时间"
                },
                addArchiveManageEndTime : {
                    required : "请选择结束时间"
                },
            },
            submitHandler : function(form) {
                addArchiveManage(form);
            }
        });


        //新增保存
        function addArchiveManage(form) {

            $.ajax({
                url: _ctx + '/view/energydataReport/archivemanage/createDataPackage',
                type: "post",
                data : {
                    startTime : $("#addArchiveManageStartTime").val(),
                    endTime : $("#addArchiveManageEndTime").val()
                },
                success: function(data) {


                    if (data.status === '1') {
                        $('#modal-form-addArchiveManage').modal('hide');//关闭编辑窗口

                        setTimeout(() => {
                            swal({
                                title: "",
                                text: "",
                                type: "success",
                                showCloseButton:false,
                                allowOutsideClick:false,
                                showConfirmButton: false,
                                timer:1000
                            });
                        }, 1000)

                        //更新列表
                        $('#archiveManageTable').tabulator(
                            "updateRow", getArchiveManageListPage((data) => {
                                $('#archiveManage_ibox').html(data);
                            }));
                    } else{
                        setTimeout(() => {
                            swal({
                                title: data.msg,
                                text: "",
                                type: "warning",
                                showCloseButton:false,
                                allowOutsideClick:false,
                                showConfirmButton: false,
                                timer:1000
                            });
                        }, 1000)
                    }
                },
                error: function(data) {
                    swal( data.msg,"", "error");
                }
            });
        }

        // 获取历史数据
        function getArchiveManageListPage(callback, keywords, pageNum) {

            if (typeof callback !== 'function') {
                return;
            }

            $.ajax({
                url: _ctx + '/view/energydataReport/archivemanage/getArchiveManageListPage',
                type: "post",
                data: ({
                    "keywords": keywords,
                    "pageNum": pageNum
                }),

                success: function (data) {
                    callback(data);
                },

                error: function (e) {
                    console.error(e)
                }
            });
        }

        // 下载历史数据包
        function download(id) {

            var url = _ctx + '/dataCenter/download';

            var obj = {};//声明内部对象
            obj.url = url;
            obj._ifbody = null;
            obj._iframe = document.createElement("iframe");
            obj._form = document.createElement("form");//form表单元素
            obj._body = document.createElement("div");//form表单容器不显示
            obj._input = document.createElement("input");//元素
            obj._init = function () {//初始类函数
                obj._body.style.display = "none";
                obj._body.appendChild(obj._iframe);
                document.body.appendChild(obj._body);
                obj._form.action = [obj.url].join("");
                obj._form.method = "get";
                obj._input.type = "hidden";
                obj._input.name = "id";
                obj._form.appendChild(obj._input);
                obj._input.value = id;
                if (obj._iframe.contentDocument)
                    obj._ifbody = obj._iframe.contentDocument.body;
                else
                    obj._ifbody = obj._iframe.contentWindow.document.body;
                obj._ifbody.appendChild(obj._form);
                obj._form.submit()
            };
            obj._init();

        }


        return {}
    })(jQuery, window, document);

</script>