<#--
 消息监听接口
xiepufeng
-->


<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;消息监听列表>>>
		</span>
    <!-- 增加按钮 -->
    <a id="addmessageMonitoring" data-toggle="modal" href="#messageMonitoringModalAdd" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
    </a>

    <!-- 搜索框 -->
   <#-- <div class="zc_search find">
        <input type="text" class="find-style" id="messageMonitoringSearchKeywords" name="messageMonitoringSearchKeywords"
               placeholder="设备类型名称">
        <button id="querymessageMonitoring" onclick="messageMonitoring.search()">
            <i class="fa fa-search" aria-hidden="true"></i>
        </button>
    </div>-->
</div>


<!---分页列表----->
<div class="ibox" id="messageMonitoringPageContainer" style="height:92%"></div>

<!---添加设备类型开始----->
<div class="modal fade" id="messageMonitoringModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加消息监听</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="messageMonitoringFormAdd" name="messageMonitoringFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">url<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="messageMonitoringHttpCallbackAdd" name="messageMonitoringHttpCallbackAdd" placeholder="请输入url回调地址链接"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">事件类型</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="messageMonitoringCodeAdd" name="messageMonitoringCodeAdd" placeholder="请输入设备类型代码"
                                   class="form-control">-->
                            <select id="messageMonitoringEventTypeAdd" name="messageMonitoringEventTypeAdd" required class="form-control">
                                <option value="1">控制状态反馈</option>
                                <option value="2">实时状态反馈</option>
                                <option value="3">控制器状态反馈</option>
                                <option value="4">电表实时数据</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="messageMonitoringCommentsAdd" name="messageMonitoringCommentsAdd"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 添加消息监听结束 -->

<!----编辑消息监听--->
<div class="modal fade" id="messageMonitoringModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑消息监听</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="messageMonitoringFormEdit" name="messageMonitoringFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">url<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input id="messageMonitoringIdEdit" name="messageMonitoringIdEdit" type="hidden"/>
                            <input type="text" id="messageMonitoringHttpCallbackEdit" name="messageMonitoringHttpCallbackEdit" placeholder="请输入url回调地址链接"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">事件类型</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="messageMonitoringCodeAdd" name="messageMonitoringCodeAdd" placeholder="请输入设备类型代码"
                                   class="form-control">-->
                            <select id="messageMonitoringEventTypeEdit" name="messageMonitoringEventTypeEdit" required class="form-control">
                                <option value="1">控制状态反馈</option>
                                <option value="2">实时状态反馈</option>
                                <option value="3">控制器状态反馈</option>
                                <option value="4">电表实时数据</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="messageMonitoringCommentsEdit" name="messageMonitoringCommentsEdit"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!----编辑消息监听结束--->

<script type="text/javascript">
    ;
    var messageMonitoring = (function ($, window, document, undefined)
    {
        var _ctx = '${ctx}';


        refreshTable();

        function refreshTable(param)
        {
            getPagingPage(param, function (page)
            {
                showPagingPage('messageMonitoringPageContainer', page);
            });
        }

        // 获取分页页面
        function getPagingPage(param, callback)
        {

            if (typeof callback !== 'function')
            {
                return;
            }

            param = param || {};

            $.ajax({
                url: _ctx + '/view/sysmanage/interfaceconfig/messageMonitoring/queryPage',
                type: "post",
                data: param,
                success: function (result)
                {
                    callback(result);
                },

                error: function (result)
                {
                    console.log(result)
                }
            });

        }

        // 显示分页
        function showPagingPage(pageId, page)
        {
            if (!page || !pageId)
            {
                return;
            }

            $('#' + pageId).html(page);
        }

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#messageMonitoringTable button.edit', function ()
        {

            var id = $(this).data('id');

            query({id}, function (obj)
            {

                var data = obj && obj.data;

                if (!data)
                {
                    return;
                }

                $('#messageMonitoringIdEdit').val(data.id || '');
                $('#messageMonitoringHttpCallbackEdit').val(data.httpCallback || '');
                $('#messageMonitoringEventTypeEdit').val(data.eventType || '');
                $('#messageMonitoringCommentsEdit').val(data.comments || '');
            });
        });

        /**
         * 删除点击事件
         *
         */
        $(document).on('click', '#messageMonitoringTable button.delete', function ()
        {
            var id = $(this).data('id');

            swal
            (
                    {
                        title: '您确定要删除吗?',
                        text: '信息删除后将不可恢复!',
                        type: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#1ab394',
                        confirmButtonText: '确定',
                        closeOnConfirm: false
                    }, function ()
                    {
                        deletes(id, function (result)
                        {
                            var status = result && result.status;

                            if(status === "1"){
                                swal({
                                    title : "删除成功",// 展示的标题
                                    text : "",// 内容
                                    type: "success",
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });

                                refreshTable();



                            }else {

                                swal({
                                    title : "删除失败",// 展示的标题
                                    text: result.msg, // 内容
                                    type: 'warning',
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 2000
                                });
                            }
                        })
                    }
            );

        });

        // 添加设备类型输入框验证
        var addValidate = $('#messageMonitoringFormAdd').validate({
            rules: {
                messageMonitoringHttpCallbackAdd: {
                    required: true,
                    maxlength: 100

                },
                messageMonitoringEventTypeAdd: {
                    required: true,
                    maxlength: 100

                },
                messageMonitoringCommentsAdd: {
                    maxlength: 100

                }
            },
            messages: {
                messageMonitoringHttpCallbackAdd: {
                    required: '请输入url回调地址链接'
                },
                messageMonitoringEventTypeAdd: {
                    required: '请选择事件类型'
                }
            },
            submitHandler: function (formData)
            {
                addSubmit(formData);
            }

        });

        // 修改设备类型输入框验证
        var editValidate = $('#messageMonitoringFormEdit').validate({
            rules: {
                messageMonitoringHttpCallbackEdit: {
                    required: true,
                    maxlength: 100

                },
                messageMonitoringEventTypeEdit: {
                    required: true,
                    maxlength: 100

                },
                messageMonitoringCommentsEdit: {
                    maxlength: 100

                }
            },
            messages: {
                messageMonitoringHttpCallbackEdit: {
                    required: '请输入url回调地址链接'
                },
                messageMonitoringEventTypeEdit: {
                    required: '请选择事件类型'

                },
            },
            submitHandler: function (formData)
            {
                editSubmit(formData);
            }

        });

        // 提交表单信息
        function addSubmit(formData)
        {

            if (!formData)
            {
                return;
            }

            //获取表单数据
            var formData = form2js(formData, null, null, null, null, true);

            $.ajax({
                type: 'POST',
                url: _ctx + '/view/sysmanage/interfaceconfig/messageMonitoring/create',
                dataType: 'json',
                data: {
                    httpCallback: formData.messageMonitoringHttpCallbackAdd,
                    eventType: formData.messageMonitoringEventTypeAdd,
                    comments: formData.messageMonitoringCommentsAdd

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#messageMonitoringModalAdd').modal('hide');

                        refreshTable();

                        swal({
                            title: '添加成功',// 展示的标题
                            text: '', // 内容
                            type: 'success',
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });


                    } else
                    {
                        swal({
                            title: '添加失败',// 展示的标题
                            text: result.msg, // 内容
                            type: 'warning',
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }

                },
                error: function (result)
                {

                    console.warn(result)
                }
            });

        }

        // 提交表单信息
        function editSubmit(formData)
        {

            if (!formData)
            {
                return;
            }

            //获取表单数据
            var formData = form2js(formData, null, null, null, null, true);

            $.ajax({
                type: 'POST',
                url: _ctx + '/view/sysmanage/interfaceconfig/messageMonitoring/update',
                dataType: 'json',
                data: {
                    id: formData.messageMonitoringIdEdit,
                    httpCallback: formData.messageMonitoringHttpCallbackEdit,
                    eventType: formData.messageMonitoringEventTypeEdit,
                    comments: formData.messageMonitoringCommentsEdit

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#messageMonitoringModalEdit').modal('hide');

                        refreshTable();

                        swal({
                            title: '编辑成功',// 展示的标题
                            text: '', // 内容
                            type: 'success',
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });


                    } else
                    {
                        swal({
                            title: '编辑失败',// 展示的标题
                            text: result.msg, // 内容
                            type: 'warning',
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }

                },
                error: function (result)
                {

                    console.warn(result)
                }
            });

        }

        // 查询
        function query(obj, callback)
        {
            if (typeof callback !== 'function')
            {
                return;
            }

            obj = obj || {};

            $.ajax({
                type: "GET",
                url: _ctx + "/view/sysmanage/interfaceconfig/messageMonitoring/query",
                dataType: "json",
                data: obj,
                success: function (result)
                {

                    callback(result);

                },
                error: function (result)
                {

                    console.warn(result)
                }
            });

        }

        // 删除
        function deletes(id, callback) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "GET",
                url     : _ctx + "/view/sysmanage/interfaceconfig/messageMonitoring/delete",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {

                    callback(result);

                },
                error: function (result) {
                    swal("删除失败!",null,"error");
                    console.warn(result)
                }
            });

        }

        // 添加设备类型模态框关闭时处理事件 1、清空表单；2、重置验证表单
        $('#messageMonitoringModalAdd').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#messageMonitoringHttpCallbackAdd').val('');
            $('#messageMonitoringEventTypeAdd').val('1');
            $('#messageMonitoringCommentsAdd').val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 编辑设备类型模态框关闭时处理事件 1、清空表单；2、重置验证表单
        $('#messageMonitoringModalEdit').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#messageMonitoringIdEdit').val('');
            $('#messageMonitoringHttpCallbackEdit').val('');
            $('#messageMonitoringEventTypeEdit').val('');
            $('#messageMonitoringCommentsEdit').val('');

            // 重置添加验证表单
            editValidate.resetForm()
        });


        // 根据条件搜索
        function search()
        {
            var keywords = $('#messageMonitoringSearchKeywords').val();

            refreshTable({keywords});
        }




        return {
            // search
        }

    })(jQuery, window, document);
</script>