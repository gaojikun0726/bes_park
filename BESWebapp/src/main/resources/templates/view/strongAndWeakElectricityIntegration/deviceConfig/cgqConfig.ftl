<#--
 wzx

 传感器配置页面
-->

<style>
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        /*border: 1px #FFB951 solid;*/
        opacity: 0.8;
    }
    #tree_device_type_modal_cgqconfig {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;传感器配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="cgq_config_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

</div>

<!---分页列表----->
<div class="ibox" id="cgq_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="cgq_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加传感器配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cgq_add_form" name="cgq_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_name_add"
                                       name="cgq_name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">传感器类型
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_type_add"
                                       name="cgq_type_add"
                                       class="form-control"
                                       placeholder="请输入传感器类型"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前值点位信息
                            </label>
                            <div class="col-sm-8">
                                <input id="cgq_dqz_hidden_add"
                                       name="cgq_dqz_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_dqz_add"
                                       name="cgq_dqz_add"
                                       class="form-control"
                                       placeholder="请选择当前值点位信息"
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
<div class="modal fade" id="cgq_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑传感器配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cgq_edit_form" name="cgq_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_cgq_edit" name="id_cgq_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_name_edit"
                                       name="cgq_name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">传感器类型
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_type_edit"
                                       name="cgq_type_edit"
                                       class="form-control"
                                       placeholder="请输入传感器类型"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前值点位信息
                            </label>
                            <div class="col-sm-8">
                                <input id="cgq_dqz_hidden_edit"
                                       name="cgq_dqz_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cgq_dqz_edit"
                                       name="cgq_dqz_edit"
                                       class="form-control"
                                       placeholder="请选择当前值点位信息"
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
<#-- wanghongjie  修改所有的id名称,加上传感器的标识 -->
<div class="modal fade" id="tree_device_type_modal_cgqconfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 600px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_cgq_config_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_cgq_config_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_cgq_config_type" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

<script type="text/javascript">

    var ammeterStrongWeakElectrical = (function($, window, document) {

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
                showPagingPage('cgq_page', data);

            });

        });

        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_cgq_config_type',
                idKey    : 'f_sys_name',
                pIdKey   : 'f_psys_name',
                name     : 'f_nick_name',
                setting: {
                    view: {
                        showIcon: false,
                        fontCss: function ( treeId, treeNode ) {
                            return ( treeNode.highlight )
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
        function queryCgqConfig(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/query",
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
        $('#tree_device_type_search_cgq_config_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_cgq_config_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_cgq_config_button').click(function () {

            tree.search('tree_device_type_search_cgq_config_input', 'f_nick_name');
        });

        // 显示添加模态框
        $('#cgq_config_add').click(function () {
            $('#cgq_modal_add').modal('show');
            modalAddActive = true;

        });

        // 显示编辑模态框事件
        $('#cgq_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
        });

        // 添加输入框得到焦点时显示设备类型模态框
        $(
            '#cgq_dqz_add, ' +
            '#cgq_dqz_edit '
        ).focus(function (obj) {
            inputObj = obj.target;
            $('#tree_device_type_modal_cgqconfig').modal('show');
        });


        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#cgq_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_type_modal_cgqconfig').modal('hide');

            $('#cgq_name_add').val('');
            $('#cgq_type_add').val('');
            $('#cgq_dqz_add').val('');


            $('#cgq_dqz_hidden_add').val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 关闭编辑模态框时清空输入框
        $("#cgq_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_type_modal_cgqconfig').modal('hide');

            $('#id_cgq_edit').val('');
            $('#cgq_name_edit').val('');
            $('#cgq_type_edit').val('');
            $('#cgq_dqz_edit').val('');


            $('#cgq_dqz_hidden_edit').val('');

            // 重置表单
            editValidate.resetForm()

        });

        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_type_modal_cgqconfig').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_cgq_config_input').val('');
        });


        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#cgqConfigTable button.edit', function () {

            var id = $(this).data('id');

            queryCgqConfig({id}, function (obj) {

                var data = obj && obj.data && obj.data[0];

                if(!data){
                    return;
                }

                $('#id_cgq_edit')                   .val(data.id                   || '');
                $('#cgq_name_edit')                 .val(data.name                 || '');
                $('#cgq_type_edit')              .val(data.cgqType          || '');
                $('#cgq_dqz_edit')       .val(data.cgqDqzDisplay || '');
                $('#cgq_dqz_hidden_edit').val(data.cgqDqz       || '');


            });
        });

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#cgqConfigTable button.delete', function () {

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
                    deleteCgqConfig(id);
                }
            );

        });



        // 添加输入框验证
            var addValidate = $("#cgq_add_form").validate({
            rules: {
                cgq_name_add:{
                    required: true,
                    maxlength: 50

                },
                cgq_type_add:{
                    required: true,
                    maxlength: 50

                },
                 cgq_dqz_add:{
                     required: true,
                     maxlength: 50

                 }

            },
            messages: {
                cgq_name_add:{
                    required: '请输入名称'
                },
                cgq_type_add:{
                    required: '请输入传感器类型'
                }
            },

            submitHandler: function(formData) {
                addSubmit(formData);
            }

        });



        // 编辑输入框验证
        var editValidate = $("#cgq_edit_form").validate({
            rules: {
                 cgq_name_edit:{
                     required: true,
                     maxlength: 50

                 },
                 cgq_type_edit:{
                     required: true,
                     maxlength: 50

                 },
                 cgq_dqz_edit:{
                     required: true,
                     maxlength: 50

                 }

            },
            messages: {
                cgq_name_edit:{
                    required: '请输入名称'
                },
                cgq_type_edit:{
                    required: '请输入传感器类型'
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
            var formData = form2js(formData ,null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/create",
                dataType: "json",
                data: {
                    name          : formData.cgq_name_add,
                    cgqType       : formData.cgq_type_add,
                    cgqDqz        : formData.cgq_dqz_hidden_add,
                    cgqDqzDisplay : formData.cgq_dqz_add
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('cgq_page', data);

                        });

                        $('#cgq_modal_add').modal('hide');

                        swal({
                            title             : "添加成功",// 展示的标题
                            text              : "", // 内容
                            type              : "success",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });


                    }else {
                        swal({
                            title             : "添加失败",// 展示的标题
                            text              : result.msg, // 内容
                            type              : "warning",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        // 更新表单信息
        function editSubmit(formData){

            if(!formData){
                return;
            }

            //获取表单数据
            var formData = form2js( formData, null, null, null, null, true);

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/update",
                dataType: "json",
                data: {
                    id                   : formData.id_cgq_edit,
                    name                 : formData.cgq_name_edit,
                    cgqType          : formData.cgq_type_edit,
                    cgqDqz        : formData.cgq_dqz_hidden_edit,
                    cgqDqzDisplay : formData.cgq_dqz_edit
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        $('#cgq_modal_edit').modal('hide');

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
                            showPagingPage('cgq_page', data);
                        });

                    }else {
                        swal({
                            title             : "编辑失败",// 展示的标题
                            text              : result.msg, // 内容
                            type              : "warning",
                            showCloseButton   : false, // 展示关闭按钮
                            allowOutsideClick : false,
                            showConfirmButton : false,
                            timer             : 1000
                        });
                    }

                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

        function deleteCgqConfig(id) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/delete",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('cgq_page', data);
                        });

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

                },
                error: function (result) {
                    swal("删除失败!",null,"error");
                    console.warn(result)
                }
            });

        }

        // 获取分页页面
        function getPagingPage(param, callback){

            if(typeof callback !== 'function'){
                return;
            }

            param = param || {};

            $.ajax({
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/getPaging',
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

    })(jQuery, window, document);
</script>
