

<style>
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        border: 1px #FFB951 solid;
        opacity: 0.8;
    }
    #tree_device_socket_modal {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;插座配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="socketAdd" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

    <!-- 导入按钮 -->
    <a id="importbesrate"  onclick="socket.impReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
        <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
    </a>

</div>
<!---分页列表----->
<div class="ibox" id="socket_page" style="height:90%">
</div>
<#-- 添加模态框 -->
<div class="modal fade" id="socket_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加插座配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="socket_add_form" name="socket_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="socketName_add"
                                       name="socketName_add"
                                       class="form-control"
                                       placeholder="请输入插座名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座地址
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="socketAddress_add"
                                       name="socketAddress_add"
                                       class="form-control"
                                       placeholder="请输入插座地址"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座开关
                            </label>
                            <div class="col-sm-8">
                                <input id="socketSwitch_add_hidden"
                                       name="socketSwitch_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="socketSwitch_add"
                                       name="socketSwitch_add"
                                       class="form-control"
                                       placeholder="请选择插座开关点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">能耗值
                            </label>
                            <div class="col-sm-8">
                                <input id="energyConsumptionValue_add_hidden"
                                       name="energyConsumptionValue_add_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="energyConsumptionValue_add"
                                       name="energyConsumptionValue_add"
                                       class="form-control"
                                       placeholder="请选择能耗值点位"
                                >
                            </div>
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
        </div><!-- end modal-content -->
    </div>
</div>

<#-- 编辑模态框 -->
<div class="modal fade" id="socket_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑插座配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="socket_edit_form" name="socket_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_socket_edit" name="id_socket_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="socket_name_edit"
                                       name="socket_name_edit"
                                       class="form-control"
                                       placeholder="请输入插座名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座地址
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="socketAddress_edit"
                                       name="socketAddress_edit"
                                       class="form-control"
                                       placeholder="请输入插座地址"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">插座开关
                            </label>
                            <div class="col-sm-8">
                                <input id="socketSwitch_edit_hidden"
                                       name="socketSwitch_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="socketSwitch_edit"
                                       name="socketSwitch_edit"
                                       class="form-control"
                                       placeholder="请选择插座开关点位"
                                >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">能耗值
                            </label>
                            <div class="col-sm-8">
                                <input id="energyConsumptionValue_edit_hidden"
                                       name="energyConsumptionValue_edit_hidden"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="energyConsumptionValue_edit"
                                       name="energyConsumptionValue_edit"
                                       class="form-control"
                                       placeholder="请选择能耗值点位"
                                >
                            </div>
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
        </div><!-- end modal-content -->
    </div>
</div>

<#-- 设备类型模态框 -->
<#-- wanghongjie  修改所有的id名称,加上电表的标识 -->
<div class="modal fade" id="tree_device_socket_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 600px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_socket_config_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_socket_config_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_socket_config" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

<!-- 上传模态框 -->
<div class="modal fade import-Model" id="socket_import_model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin-top: 8%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="importSocketModalLabel">导入报表</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger" style="font-size:13px;" role="alert">
                            <strong>注 意：</strong><br>
                            &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">模板下载</span>
                            <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp" onclick="socket.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import_form_socket_btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="socketImpExcel" >
                            <input id="socketInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="socket.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var socket = (function($, window, document) {

        var ctx = '${ctx}';
        var tree;// 树对象
        var modalAddActive = false; // 保存添加模态框是否是显示状态 true 显示 | false 隐藏
        var modalEditActive = false; // 保存编辑模态框是否是显示状态 true 显示 | false 隐藏
        var inputObj; // 模态框内输入框保存对象


        $(function () {

            initTree(); // 初始化树

            // 获取设备类型数据并把数据加载到树中
            getAustereDevicesTree(function (data) {

                var status = data.status;

                if(status !== '1') {
                    return;
                }

                var treeList = data.list || [];

                tree.loadData(treeList);
            });

            getPagingPage({}, function (data) {
                showPagingPage('socket_page', data);

            });

        });
        // 获取分页页面
        function getPagingPage(param, callback){

            if(typeof callback !== 'function'){
                return;
            }

            param = param || {};

            $.ajax({
                url     : _ctx + '/view/app/socket/getPaging',
                type    : "post",
                data    : param,
                success : function(result) {
                    callback(result);
                },

                error : function(result) {
                    console.log(result)
                }
            });

        }

        // 显示分页
        function showPagingPage(pageId, page){
            if(!page || !pageId){
                return;
            }

            $('#' + pageId).html(page);
        }
        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_socket_config',
                idKey    : 'f_sys_name',
                pIdKey   : 'f_psys_name',
                name     : 'f_nick_name',
                setting: {
                    view: {
                        showIcon: false,
                        fontCss: function ( treeId, treeNode ) {
                            return ( treeNode.highsocket )
                                    ? {color:'#A60000', 'font-weight':'bold'}
                                    : {color:'#D1E3F9', 'font-weight':'normal'};
                        }
                    }
                },
                callback: function (node) {
                    if ((modalAddActive || modalEditActive) && inputObj) {
                        inputObj.value = node.f_nick_name;
                        $(inputObj).prev().val(node.f_sys_name);
                    }
                }
            });
        }

        // 获取设备树信息
        function getAustereDevicesTree(callback){

            if(typeof callback !== 'function'){
                return;
            }

            $.ajax({
                type    : "post",
                url     : ctx + "/view/basedatamanage/eqmanage/austere_devices_tree",
                dataType: "json",
                success: function (result) {
                    callback(result);
                }
            });
        }

        // 查询设备信息
        function queryAmmeterConfig(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/socket/queryOne",
                dataType: "json",
                data    :  obj,
                success: function (result) {

                    callback(result);

                },
                error: function (result) {

                    console.warn(result)
                }
            });


        }


        //触发搜索的回车事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_socket_config_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_socket_config_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_socket_config_button').click(function () {

            tree.search('tree_device_type_search_socket_config_input', 'f_nick_name');
        });

        // 显示添加模态框
        $('#socketAdd').click(function () {
            $('#socket_modal_add').modal('show');
            modalAddActive = true;

        });

        // 显示编辑模态框事件
        $('#socket_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
        });

        // 添加输入框得到焦点时显示设备类型模态框
        // $(
        //         '#socketSwitch, ' +
        //         '#ammeter_instant_energy_edit, ' +
        //         '#ammeter_total_energy_edit '
        // )
        $("#socketSwitch_add,#socketSwitch_edit,#energyConsumptionValue_edit,#energyConsumptionValue_add")
                .focus(function (obj) {

            inputObj = obj.target;
            $('#tree_device_socket_modal').modal('show');
        });


        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#socket_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_socket_modal').modal('hide');
            $("#id").val('');
            $('#socketName_add').val('');
            $('#socketAddress_add').val('');
            $('#socketSwitch_add').val('');
            $('#energyConsumptionValue_add').val('');
            $('#energyConsumptionValue_add_hidden').val('');

            $('#socketSwitch_add_hidden').val('');
            $("#tree_device_type_search_socket_config_input").val('');

            // 重置添加验证表单
            addValidate.resetForm();
        });

        // 关闭编辑模态框时清空输入框
        $("#socket_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_socket_modal').modal('hide');

            $('#id_socket_edit')                   .val('');
            $('#socket_name_edit')                 .val('');
            $('#socketAddress_edit')              .val('');
            $('#socketSwitch_edit')       .val('');

            $('#socketSwitch_edit_hidden')       .val('');
            $('#energyConsumptionValue_edit_hidden')       .val('');
            $('#energyConsumptionValue_edit')       .val('');

            $("#tree_device_type_search_socket_config_input").val('');
            // 重置表单
            editValidate.resetForm();

        });

        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_socket_modal').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_socket_config_input').val('');
        });
        // 添加输入框验证
        var addValidate = $("#socket_add_form").validate({
            rules: {
                socketName_add:{
                    required: true,
                    maxlength: 50

                },
                socketAddress_add:{
                    required: true,
                    maxlength: 50

                }
            },
            messages: {
                socketName_add:{
                    required: '请输入插座名称'
                },
                socketAddress_add:{
                    required: '请输入插座地址'
                }
            },

            submitHandler: function(formData) {
                addSubmit(formData);
            }

        });



        // 编辑输入框验证
        var editValidate = $("#socket_edit_form").validate({
            rules: {
                socket_name_edit:{
                    required: true,
                    maxlength: 50

                },
                socketAddress_edit:{
                    required: true,
                    maxlength: 50

                }
            },
            messages: {
                socket_name_edit:{
                    required: '请输入插座名称'
                },
                socketAddress_edit:{
                    required: '请输入插座地址'
                }
            },
            submitHandler: function(formData) {
                editSubmit(formData);
            }

        });

        // 提交表单信息
        function addSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
             formData = form2js(formData ,null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/socket/insert",
                dataType: "json",
                data: {
                    id:formData.id,
                    name                 : formData.socketName_add,
                    socketSwitchDisplay          : formData.socketSwitch_add,
                    socketSwitch         : formData.socketSwitch_add_hidden,
                    energyConsumptionValueDisplay          : formData.energyConsumptionValue_add,
                    energyConsumptionValue          : formData.energyConsumptionValue_add_hidden,
                    socketAddress        : formData.socketAddress_add
                },
                success: function (result) {
                    if(result){
                        getPagingPage({}, function (data) {
                            showPagingPage('socket_page', data);

                        });

                        $('#socket_modal_add').modal('hide');

                        swal({
                            title             : "添加成功",// 展示的标题
                            text              : "", // 内容
                            type              : "success",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    } else {
                        swal({
                            title             : "添加失败",// 展示的标题
                            text              : "", // 内容
                            type              : "warning",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {
                    swal({
                        title             : "添加失败",// 展示的标题
                        text              : "", // 内容
                        type              : "warning",
                        showCloseButton   : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer             : 1000
                    });
                }
            });

        }

        // 更新表单信息
        function editSubmit(formData){
            if(!formData){
                return;
            }

            //获取表单数据
             formData = form2js( formData, null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/socket/update",
                dataType: "json",
                data: {
                    id: formData.id_socket_edit,
                    name: formData.socket_name_edit,
                    socketAddress : formData.socketAddress_edit,
                    socketSwitchDisplay : formData.socketSwitch_edit,
                    socketSwitch : formData.socketSwitch_edit_hidden,
                    energyConsumptionValueDisplay : formData.energyConsumptionValue_edit,
                    energyConsumptionValue : formData.energyConsumptionValue_edit_hidden
                },
                success: function (result) {
                    if(result){
                        $('#socket_modal_edit').modal('hide');

                        swal({
                            title             : "编辑成功",// 展示的标题
                            text              : "", // 内容
                            type              : "success",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });

                        getPagingPage({}, function (data) {
                            showPagingPage('socket_page', data);
                        });
                    } else {
                        swal({
                            title             : "编辑失败",// 展示的标题
                            text              : "", // 内容
                            type              : "warning",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    swal({
                        title             : "编辑失败",// 展示的标题
                        text              : "", // 内容
                        type              : "warning",
                        showCloseButton   : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer             : 1000
                    });
                }
            });

        }

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#socketConfigTable button.edit', function () {

            var id = $(this).data('id');

            queryAmmeterConfig({id}, function (obj) {
                var data = obj;
                if(!data){
                    return;
                }

                $('#id_socket_edit').val(data.id || '');
                $('#socket_name_edit').val(data.name || '');
                $('#socketAddress_edit').val(data.socketAddress || '');
                $('#socketSwitch_edit').val(data.socketSwitchDisplay || '');
                $('#socketSwitch_edit_hidden').val(data.socketSwitch || '');
                $('#energyConsumptionValue_edit').val(data.energyConsumptionValueDisplay || '');
                $('#energyConsumptionValue_edit_hidden').val(data.energyConsumptionValue || '');
            });
        });
        /**
         * 删除点击事件
         *
         */
        $(document).on('click', '#socketConfigTable button.delete', function () {

            var id = $(this).data('id');

            swal(
                    {
                        title : "您确定要删除吗?",
                        text : "信息删除后将不可恢复!",
                        type : "warning",
                        showCancelButton : true,
                        confirmButtonColor : "#1ab394",
                        confirmButtonText : "确定",
                        closeOnConfirm : false
                    },function () {
                        deleteAmmeterConfig(id);
                    }
            );

        });


        function deleteAmmeterConfig(id) {

            if (!id) {
                swal("删除失败",null,"error");
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/app/socket/delete",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {
                    if(result){
                        swal({
                            title : "删除成功",// 展示的标题
                            text : "",// 内容
                            type: "success",
                            showCloseButton : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer: 1000
                        });
                        getPagingPage({}, function (data) {
                            showPagingPage('socket_page', data);
                        });
                    }else {

                        swal({
                            title             : "删除失败",// 展示的标题
                            text              : "", // 内容
                            type              : "warning",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {
                    swal({
                        title             : "删除失败",// 展示的标题
                        text              : "", // 内容
                        type              : "warning",
                        showCloseButton   : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer             : 1000
                    });
                }
            });

        }

        //fileinput 上传插件初始化
        function initFileinput(){
            $("#socketInputFile").fileinput({
                language: 'zh', //设置语言
                uploadUrl: '${ctx}/view/app/socket/besSocketFileUpload',//上传请求路径
                allowedFileExtensions : ['xls', 'xlsx'],//接收的文件后缀,
                showUpload: true, //是否显示上传按钮
                showCaption: true,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                maxFileSize:3072,//最大单个文件上传大小
                maxFileCount:1,//最大上传个数
                showUpload:false,//是否显示上传按钮
                showBrowse:true,//是否显示浏览按钮
                showPreview:false,//是否显示预览区域
                autoReplace:true,//是否自动替换
                showRemove:true,//是否显示移除按钮
                uploadExtraData:function(id,index){
                    return {"fold":"socketImportExcel"};
                },//区分不同的模块：fold：文件夹
                //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
                deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
            }).on("fileuploaded",function(event, data, previewId, index){
                getPagingPage({}, function (data) {
                    showPagingPage('socket_page', data);
                });
                clearForm();//清空form表单
                var res = data.response;
                if(res.status== '1')
                {
                    swal({title: res.msg,type: "success",showCloseButton: true});
                }
                else
                {
                    swal({title: res.msg,type: "error",showCloseButton: true});
                    if(res.hasOwnProperty("list"))
                    {
                        var errorList = JSON.stringify(res.list);
                        var data = {
                            errorString : errorList,
                        };
                        var _url = "${ctx}/view/basedatamanage/enegrycollectionmanage/exportErrorExcel";
                        var exportName = "插座配置导入错误报告";
                        doExp(_url,exportName,"${ctx}",data);//导出excel并下载

                    }

                }

            }).on("filebatchuploadsuccess",function(){
                clearForm();//清空form表单
            });
        }


        //清空上传文件表单form 关闭模态框 并提示
        function clearForm(){
            $("#socketInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
            $("#socket_import_model").modal("hide");//关闭模态框
        }

        return {
            impReport :function(){
                //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
                $("#socket_import_model").modal("show");
                initFileinput();//初始化fileinput
            },
            //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
            btn_exp : function(){
                var fname = "插座配置模板.xls";
                var path = "file\\expExcel\\插座配置模板.xls";
                //FileDownload("${ctx}/file/newFileDownload",fname,path);
                FileDownload(_ctx + filePath.loadPath,fname,path);
            },
            //导入数据按钮
            btn_import:function(){
                var filepath = $("#socketInputFile").val();
                if(filepath != ""){
                    $("#socketInputFile").fileinput("upload");//上传方法
                }else{
                    swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
                }

            },
        }


    })(jQuery, window, document);


</script>
