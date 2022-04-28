<#--
 设备类型
xiepufeng
-->


<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备类型列表>>>
		</span>
    <!-- 增加按钮 -->
    <a id="addDeviceType" data-toggle="modal" href="#deviceTypeModalAdd" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
    </a>

    <!-- 搜索框 -->
    <div class="zc_search find">
        <input type="text" class="find-style" id="deviceTypeSearchKeywords" name="deviceTypeSearchKeywords"
               placeholder="设备类型名称">
        <button id="queryDeviceType" onclick="deviceType.search()">
            <i class="fa fa-search" aria-hidden="true"></i>
        </button>
    </div>
</div>


<!---分页列表----->
<div class="ibox" id="deviceTypePageContainer" style="height:92%"></div>

<!---添加设备类型开始----->
<div class="modal fade" id="deviceTypeModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;编辑设备类型</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="deviceTypeFormAdd" name="deviceTypeFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="deviceTypeNameAdd" name="deviceTypeNameAdd" placeholder="请输入设备类型名称"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型代码</label>
                        <div class="col-sm-8">
                            <input type="text" id="deviceTypeCodeAdd" name="deviceTypeCodeAdd" placeholder="请输入设备类型代码"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="deviceTypeCommentsAdd" name="deviceTypeCommentsAdd"
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
<!-- 添加设备类型结束 -->

<!----编辑设备类型--->
<div class="modal fade" id="deviceTypeModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑通信协议信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="deviceTypeFormEdit" name="deviceTypeFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="hidden" id="deviceTypeIdEdit" name="deviceTypeIdEdit"/>
                            <input type="text" id="deviceTypeNameEdit" name="deviceTypeNameEdit" placeholder="请输入设备类型名称"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型代码</label>
                        <div class="col-sm-8">
                            <input type="text" id="deviceTypeCodeEdit" name="deviceTypeCodeEdit" placeholder="请输入设备类型代码"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="deviceTypeCommentsEdit" name="deviceTypeCommentsEdit"
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
<!----编辑设备类型结束--->

<script type="text/javascript">
    ;
    var deviceType = (function ($, window, document, undefined)
    {
        var _ctx = '${ctx}';


        refreshTable();

        function refreshTable(param)
        {
            getPagingPage(param, function (page)
            {
                showPagingPage('deviceTypePageContainer', page);
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
                url: _ctx + '/view/sysmanage/interfaceconfig/deviceType/queryPage',
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
        $(document).on('click', '#deviceTypeTable button.edit', function ()
        {

            var id = $(this).data('id');

            query({id}, function (obj)
            {

                var data = obj && obj.data && obj.data[0];

                if (!data)
                {
                    return;
                }

                $('#deviceTypeIdEdit').val(data.id || '');
                $('#deviceTypeNameEdit').val(data.name || '');
                $('#deviceTypeCodeEdit').val(data.code || '');
                $('#deviceTypeCommentsEdit').val(data.comments || '');
            });
        });

        /**
         * 删除点击事件
         *
         */
        $(document).on('click', '#deviceTypeTable button.delete', function ()
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

                                refreshTable();

                                if (typeof deviceConfiguration != 'undefined') {
                                    deviceConfiguration.pageInit();
                                }
                                if (typeof deviceFunction != 'undefined') {
                                    deviceFunction.pageInit();
                                }

                                swal({
                                    title : "删除成功",// 展示的标题
                                    text : "",// 内容
                                    type: "success",
                                    showCloseButton : false, // 展示关闭按钮
                                    allowOutsideClick : false,
                                    showConfirmButton : false,
                                    timer: 1000
                                });

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
        var addValidate = $('#deviceTypeFormAdd').validate({
            rules: {
                deviceTypeNameAdd: {
                    required: true,
                    maxlength: 50

                },
                deviceTypeCodeAdd: {
                    required: true,
                    maxlength: 50

                },
                deviceTypeCommentsAdd: {
                    maxlength: 50

                }
            },
            messages: {
                deviceTypeNameAdd: {
                    required: '请输入设备类型名称'
                },
                deviceTypeCodeAdd: {
                    required: '请输入设备类型代码'
                }
            },
            submitHandler: function (formData)
            {
                addSubmit(formData);
            }

        });

        // 修改设备类型输入框验证
        var editValidate = $('#deviceTypeFormEdit').validate({
            rules: {
                deviceTypeNameEdit: {
                    required: true,
                    maxlength: 50

                },
                deviceTypeCodeEdit: {
                    required: true,
                    maxlength: 50

                },
                deviceTypeCommentsEdit: {
                    maxlength: 50

                }
            },
            messages: {
                deviceTypeNameEdit: {
                    required: '请输入设备类型名称'
                },
                deviceTypeCodeEdit: {
                    required: '请输入设备类型代码'

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
                url: _ctx + '/view/sysmanage/interfaceconfig/deviceType/create',
                dataType: 'json',
                data: {
                    name: formData.deviceTypeNameAdd,
                    code: formData.deviceTypeCodeAdd,
                    comments: formData.deviceTypeCommentsAdd

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#deviceTypeModalAdd').modal('hide');

                        refreshTable();

                        if (typeof deviceConfiguration != 'undefined') {
                            deviceConfiguration.pageInit();
                        }
                        if (typeof deviceFunction != 'undefined') {
                            deviceFunction.pageInit();
                        }

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
                url: _ctx + '/view/sysmanage/interfaceconfig/deviceType/update',
                dataType: 'json',
                data: {
                    id: formData.deviceTypeIdEdit,
                    name: formData.deviceTypeNameEdit,
                    code: formData.deviceTypeCodeEdit,
                    comments: formData.deviceTypeCommentsEdit

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#deviceTypeModalEdit').modal('hide');

                        refreshTable();

                        if (typeof deviceConfiguration != 'undefined') {
                            deviceConfiguration.pageInit();
                        }
                        if (typeof deviceFunction != 'undefined') {
                            deviceFunction.pageInit();
                        }

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
                url: _ctx + "/view/sysmanage/interfaceconfig/deviceType/query",
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
                url     : _ctx + "/view/sysmanage/interfaceconfig/deviceType/delete",
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
        $('#deviceTypeModalAdd').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#deviceTypeNameAdd').val('');
            $('#deviceTypeCodeAdd').val('');
            $('#deviceTypeCommentsAdd').val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 编辑设备类型模态框关闭时处理事件 1、清空表单；2、重置验证表单
        $('#deviceTypeModalEdit').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#deviceTypeIdEdit').val('');
            $('#deviceTypeCodeEdit').val('');
            $('#deviceTypeNameEdit').val('');
            $('#deviceTypeCommentsEdit').val('');

            // 重置添加验证表单
            editValidate.resetForm()
        });


        // 根据条件搜索
        function search()
        {
            var keywords = $('#deviceTypeSearchKeywords').val();

            refreshTable({keywords});
        }




        return {
            search
        }

    })(jQuery, window, document);
</script>