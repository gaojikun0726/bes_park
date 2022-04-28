<#--
 机组配置
xiepufeng
-->


<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;机组列表>>>
		</span>
    <!-- 增加按钮 -->
    <a id="addMachineSet" data-toggle="modal" href="#machineSetModalAdd" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
    </a>

    <!-- 搜索框 -->
    <div class="zc_search find">
        <input type="text" class="find-style" id="machineSetSearchKeywords" name="machineSetSearchKeywords"
               placeholder="机组名称">
        <button id="queryMachineSet" onclick="machineSet.search()">
            <i class="fa fa-search" aria-hidden="true"></i>
        </button>
    </div>
</div>


<!---分页列表----->	        
<div class="ibox" id="machineSetPageContainer" style="height:92%"></div>

<!---添加机组开始----->
<div class="modal fade" id="machineSetModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;编辑机组</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="machineSetFormAdd" name="machineSetFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">机组名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="machineSetNameAdd" name="machineSetNameAdd" placeholder="请输入机组名称"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="machineSetCommentsAdd" name="machineSetCommentsAdd"
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
<!-- 添加机组结束 -->

<!----编辑机组--->
<div class="modal fade" id="machineSetModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑通信协议信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="machineSetFormEdit" name="machineSetFormEdit" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">机组名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="hidden" id="machineSetIdEdit" name="machineSetIdEdit"/>
                            <input type="text" id="machineSetNameEdit" name="machineSetNameEdit" placeholder="请输入机组名称"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" id="machineSetCommentsEdit" name="machineSetCommentsEdit"
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
<!----编辑机组结束--->

<script type="text/javascript">
    ;
    var machineSet = (function ($, window, document, undefined)
    {
        var _ctx = '${ctx}';


        refreshTable();

        function refreshTable(param)
        {
            getPagingPage(param, function (page)
            {
                showPagingPage('machineSetPageContainer', page);
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
                url: _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/queryPage',
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
        $(document).on('click', '#machineSetTable button.edit', function ()
        {

            var id = $(this).data('id');

            query({id}, function (obj)
            {

                var data = obj && obj.data && obj.data[0];

                if (!data)
                {
                    return;
                }

                $('#machineSetIdEdit').val(data.id || '');
                $('#machineSetNameEdit').val(data.name || '');
                $('#machineSetCommentsEdit').val(data.comments || '');
            });
        });

        /**
         * 删除点击事件
         *
         */
        $(document).on('click', '#machineSetTable button.delete', function ()
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

                            swal("删除失败!",null,"error");
                        }
                    })
                }
            );

        });

        // 添加主机输入框验证
        var addValidate = $('#machineSetFormAdd').validate({
            rules: {
                machineSetNameAdd: {
                    required: true,
                    maxlength: 50

                },
                machineSetCommentsAdd: {
                    maxlength: 50

                }
            },
            messages: {
                machineSetNameAdd: {
                    required: '请输入机组名称'
                }
            },
            submitHandler: function (formData)
            {
                addSubmit(formData);
            }

        });

        // 添加主机输入框验证
        var editValidate = $('#machineSetFormEdit').validate({
            rules: {
                machineSetNameEdit: {
                    required: true,
                    maxlength: 50

                },
                machineSetCommentsEdit: {
                    maxlength: 50

                }
            },
            messages: {
                machineSetNameEdit: {
                    required: '请输入机组名称'
                }
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
                url: _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/create',
                dataType: 'json',
                data: {
                    name: formData.machineSetNameAdd,
                    comments: formData.machineSetCommentsAdd

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#machineSetModalAdd').modal('hide');

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
                url: _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/update',
                dataType: 'json',
                data: {
                    id: formData.machineSetIdEdit,
                    name: formData.machineSetNameEdit,
                    comments: formData.machineSetCommentsEdit

                },
                success: function (result)
                {

                    var status = result && result.status;

                    if (status === '1')
                    {

                        $('#machineSetModalEdit').modal('hide');

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
                url: _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/query",
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
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/machineSet/delete",
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

        // 添加主机模态框关闭时处理事件 1、清空表单；2、重置验证表单
        $('#machineSetModalAdd').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#machineSetNameAdd').val('');
            $('#machineSetCommentsAdd').val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 编辑主机模态框关闭时处理事件 1、清空表单；2、重置验证表单
        $('#machineSetModalEdit').on('hide.bs.modal', function ()
        {

            // 清空表单
            $('#machineSetIdEdit').val('');
            $('#machineSetNameEdit').val('');
            $('#machineSetCommentsEdit').val('');

            // 重置添加验证表单
            editValidate.resetForm()
        });


        // 根据条件搜索
        function search()
        {
            var keywords = $('#machineSetSearchKeywords').val();

            refreshTable({keywords});
        }




        return {
            search
        }

    })(jQuery, window, document);
</script>