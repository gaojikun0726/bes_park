<#--
 xiepufeng

 电表配置页面
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
    #tree_device_type_modal_ammeterconfig {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;电表配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="ammeter_config_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

</div>

<!---分页列表----->
<div class="ibox" id="ammeter_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="ammeter_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加电表配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="ammeter_add_form" name="ammeter_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_name_add"
                                       name="ammeter_name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属机柜
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_cabinet_add"
                                       name="ammeter_cabinet_add"
                                       class="form-control"
                                       placeholder="请输入机柜名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前瞬时能耗
                            </label>
                            <div class="col-sm-8">
                                <input id="ammeter_instant_energy_hidden_add"
                                       name="ammeter_instant_energy_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_instant_energy_add"
                                       name="ammeter_instant_energy_add"
                                       class="form-control"
                                       placeholder="请选择当前瞬时能耗"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">累计能耗
                            </label>
                            <div class="col-sm-8">
                                <input id="ammeter_total_energy_hidden_add"
                                       name="ammeter_total_energy_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_total_energy_add"
                                       name="ammeter_total_energy_add"
                                       class="form-control"
                                       placeholder="请选择累计能耗"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="a_phase_voltage_hidden_add"
                                       name="a_phase_voltage_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="a_phase_voltage_add"
                                       name="a_phase_voltage_add"
                                       class="form-control"
                                       placeholder="请选择A相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="b_phase_voltage_hidden_add"
                                       name="b_phase_voltage_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="b_phase_voltage_add"
                                       name="b_phase_voltage_add"
                                       class="form-control"
                                       placeholder="请选择B相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="c_phase_voltage_hidden_add"
                                       name="c_phase_voltage_hidden_add"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="c_phase_voltage_add"
                                        name="c_phase_voltage_add"
                                        class="form-control"
                                        placeholder="请选择C相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="a_phase_current_hidden_add"
                                       name="a_phase_current_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="a_phase_current_add"
                                       name="a_phase_current_add"
                                       class="form-control"
                                       placeholder="请选择A相电流"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="b_phase_current_hidden_add"
                                       name="b_phase_current_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="b_phase_current_add"
                                       name="b_phase_current_add"
                                       class="form-control"
                                       placeholder="请选择B相电流"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="c_phase_current_hidden_add"
                                       name="c_phase_current_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="c_phase_current_add"
                                       name="c_phase_current_add"
                                       class="form-control"
                                       placeholder="请选择C相电流"
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
<div class="modal fade" id="ammeter_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑电表配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="ammeter_edit_form" name="ammeter_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_ammeter_edit" name="id_ammeter_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_name_edit"
                                       name="ammeter_name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属机柜
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_cabinet_edit"
                                       name="ammeter_cabinet_edit"
                                       class="form-control"
                                       placeholder="请输入机柜名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前瞬时能耗
                            </label>
                            <div class="col-sm-8">
                                <input id="ammeter_instant_energy_hidden_edit"
                                       name="ammeter_instant_energy_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_instant_energy_edit"
                                       name="ammeter_instant_energy_edit"
                                       class="form-control"
                                       placeholder="请选择当前瞬时能耗"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">累计能耗
                            </label>
                            <div class="col-sm-8">
                                <input id="ammeter_total_energy_hidden_edit"
                                       name="ammeter_total_energy_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="ammeter_total_energy_edit"
                                       name="ammeter_total_energy_edit"
                                       class="form-control"
                                       placeholder="请选择累计能耗"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="a_phase_voltage_hidden_edit"
                                       name="a_phase_voltage_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="a_phase_voltage_edit"
                                       name="a_phase_voltage_edit"
                                       class="form-control"
                                       placeholder="请选择A相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="b_phase_voltage_hidden_edit"
                                       name="b_phase_voltage_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="b_phase_voltage_edit"
                                       name="b_phase_voltage_edit"
                                       class="form-control"
                                       placeholder="请选择B相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电压
                            </label>
                            <div class="col-sm-8">
                                <input id="c_phase_voltage_hidden_edit"
                                       name="c_phase_voltage_hidden_edit"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="c_phase_voltage_edit"
                                        name="c_phase_voltage_edit"
                                        class="form-control"
                                        placeholder="请选择C相电压"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">A相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="a_phase_current_hidden_edit"
                                       name="a_phase_current_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="a_phase_current_edit"
                                       name="a_phase_current_edit"
                                       class="form-control"
                                       placeholder="请选择A相电流"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">B相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="b_phase_current_hidden_edit"
                                       name="b_phase_current_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="b_phase_current_edit"
                                       name="b_phase_current_edit"
                                       class="form-control"
                                       placeholder="请选择B相电流"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">C相电流
                            </label>
                            <div class="col-sm-8">
                                <input id="c_phase_current_hidden_edit"
                                       name="c_phase_current_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="c_phase_current_edit"
                                       name="c_phase_current_edit"
                                       class="form-control"
                                       placeholder="请选择C相电流"
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
<div class="modal fade" id="tree_device_type_modal_ammeterconfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 600px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_ammeter_config_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_ammeter_config_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_ammeter_config_type" class="Information_area ztree"></div>

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
        /*wanghongjie 增加或修改的时候验证点选的点位是否重复,如果重复则新增或修改失败*/
        jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo1", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo2", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo3", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo4", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo5", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));
        jQuery.validator.addMethod("notEqualTo6", function(value, element, param) {
            return value != $(param).val();
        }, $.validator.format("点位输入不能相同!"));

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
                showPagingPage('ammeter_page', data);

            });

        });

        // 初始化设备类型树
        function initTree() {
            tree = new Tree({
                container: 'tree_device_ammeter_config_type',
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
        function queryAmmeterConfig(obj, callback) {

            if(typeof callback !== 'function'){
                return;
            }

            obj = obj || {};

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/query",
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
        $('#tree_device_type_search_ammeter_config_input').keydown(function (e){
            if(e.which !== 13){
                return;
            }

            tree.search('tree_device_type_search_ammeter_config_input', 'f_nick_name');

        });

        // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
        $('#tree_device_type_search_ammeter_config_button').click(function () {

            tree.search('tree_device_type_search_ammeter_config_input', 'f_nick_name');
        });

        // 显示添加模态框
        $('#ammeter_config_add').click(function () {
            $('#ammeter_modal_add').modal('show');
            modalAddActive = true;

        });

        // 显示编辑模态框事件
        $('#ammeter_modal_edit').on('show.bs.modal', function () {
            modalEditActive = true;
        });

        // 添加输入框得到焦点时显示设备类型模态框
        $(
            '#ammeter_instant_energy_add, ' +
            '#ammeter_total_energy_add, ' +
            '#a_phase_voltage_add, ' +
            '#b_phase_voltage_add, ' +
            '#c_phase_voltage_add, ' +
            '#a_phase_current_add, ' +
            '#b_phase_current_add, ' +
            '#c_phase_current_add, ' +
            '#ammeter_instant_energy_edit, ' +
            '#ammeter_total_energy_edit, ' +
            '#a_phase_voltage_edit, ' +
            '#b_phase_voltage_edit, ' +
            '#c_phase_voltage_edit, ' +
            '#a_phase_current_edit, ' +
            '#b_phase_current_edit, ' +
            '#c_phase_current_edit'
        ).focus(function (obj) {
            inputObj = obj.target;
            $('#tree_device_type_modal_ammeterconfig').modal('show');
        });


        // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
        $('#ammeter_modal_add').on('hide.bs.modal', function () {
            modalAddActive = false;
            inputObj = null;
            $('#tree_device_type_modal_ammeterconfig').modal('hide');

            $('#ammeter_name_add')                 .val('');
            $('#ammeter_cabinet_add')              .val('');
            $('#ammeter_instant_energy_add')       .val('');
            $('#ammeter_total_energy_add')         .val('');
            $('#a_phase_voltage_add')              .val('');
            $('#b_phase_voltage_add')              .val('');
            $('#c_phase_voltage_add')              .val('');
            $('#a_phase_current_add')              .val('');
            $('#b_phase_current_add')              .val('');
            $('#c_phase_current_add')              .val('');


            $('#ammeter_instant_energy_hidden_add').val('');
            $('#ammeter_total_energy_hidden_add')  .val('');
            $('#a_phase_voltage_hidden_add')       .val('');
            $('#b_phase_voltage_hidden_add')       .val('');
            $('#c_phase_voltage_hidden_add')       .val('');
            $('#a_phase_current_hidden_add')       .val('');
            $('#b_phase_current_hidden_add')       .val('');
            $('#c_phase_current_hidden_add')       .val('');

            // 重置添加验证表单
            addValidate.resetForm()
        });

        // 关闭编辑模态框时清空输入框
        $("#ammeter_modal_edit").on('hidden.bs.modal', function(event) {

            inputObj = null;
            modalEditActive = false;
            $('#tree_device_type_modal_ammeterconfig').modal('hide');

            $('#id_ammeter_edit')                   .val('');
            $('#ammeter_name_edit')                 .val('');
            $('#ammeter_cabinet_edit')              .val('');
            $('#ammeter_instant_energy_edit')       .val('');
            $('#ammeter_total_energy_edit')         .val('');
            $('#a_phase_voltage_edit')              .val('');
            $('#b_phase_voltage_edit')              .val('');
            $('#c_phase_voltage_edit')              .val('');
            $('#a_phase_current_edit')              .val('');
            $('#b_phase_current_edit')              .val('');
            $('#c_phase_current_edit')              .val('');


            $('#ammeter_instant_energy_hidden_edit').val('');
            $('#ammeter_total_energy_hidden_edit')  .val('');
            $('#a_phase_voltage_hidden_edit')       .val('');
            $('#b_phase_voltage_hidden_edit')       .val('');
            $('#c_phase_voltage_hidden_edit')       .val('');
            $('#a_phase_current_hidden_edit')       .val('');
            $('#b_phase_current_hidden_edit')       .val('');
            $('#c_phase_current_hidden_edit')       .val('');

            // 重置表单
            editValidate.resetForm()

        });

        // 当设备树模态框关闭时折叠设备树、清空搜索框内容
        $('#tree_device_type_modal_ammeterconfig').on('hide.bs.modal', function () {
            tree.tree.expandAll( false );
            $('#tree_device_type_search_ammeter_config_input').val('');
        });


        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#ammeterConfigTable button.edit', function () {

            var id = $(this).data('id');

            queryAmmeterConfig({id}, function (obj) {

                var data = obj && obj.data && obj.data[0];

                if(!data){
                    return;
                }

                $('#id_ammeter_edit')                   .val(data.id                   || '');
                $('#ammeter_name_edit')                 .val(data.name                 || '');
                $('#ammeter_cabinet_edit')              .val(data.cabinetName          || '');
                $('#ammeter_instant_energy_edit')       .val(data.instantEnergyDisplay || '');
                $('#ammeter_total_energy_edit')         .val(data.totalEnergyDisplay   || '');
                $('#a_phase_voltage_edit')              .val(data.aPhaseVoltageDisplay || '');
                $('#b_phase_voltage_edit')              .val(data.bPhaseVoltageDisplay || '');
                $('#c_phase_voltage_edit')              .val(data.cPhaseVoltageDisplay || '');
                $('#a_phase_current_edit')              .val(data.aPhaseCurrentDisplay || '');
                $('#b_phase_current_edit')              .val(data.bPhaseCurrentDisplay || '');
                $('#c_phase_current_edit')              .val(data.cPhaseCurrentDisplay || '');
                $('#ammeter_instant_energy_hidden_edit').val(data.instantEnergy        || '');
                $('#ammeter_total_energy_hidden_edit')  .val(data.totalEnergy          || '');
                $('#a_phase_voltage_hidden_edit')       .val(data.aPhaseVoltage        || '');
                $('#b_phase_voltage_hidden_edit')       .val(data.bPhaseVoltage        || '');
                $('#c_phase_voltage_hidden_edit')       .val(data.cPhaseVoltage        || '');
                $('#a_phase_current_hidden_edit')       .val(data.aPhaseCurrent        || '');
                $('#b_phase_current_hidden_edit')       .val(data.bPhaseCurrent        || '');
                $('#c_phase_current_hidden_edit')       .val(data.cPhaseCurrent        || '');


            });
        });

        /**
         * 编辑点击事件
         *
         */
        $(document).on('click', '#ammeterConfigTable button.delete', function () {

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



        // 添加输入框验证
            var addValidate = $("#ammeter_add_form").validate({
            rules: {
                ammeter_name_add:{
                    required: true,
                    maxlength: 50

                },
                ammeter_cabinet_add:{
                    required: true,
                    maxlength: 50

                },
                 ammeter_instant_energy_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50

                 },
                 ammeter_total_energy_add:{
                     notEqualTo : "#ammeter_instant_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 a_phase_voltage_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#ammeter_instant_energy_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 b_phase_voltage_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#ammeter_instant_energy_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 c_phase_voltage_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#ammeter_instant_energy_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 a_phase_current_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#ammeter_instant_energy_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 b_phase_current_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#ammeter_instant_energy_hidden_add",
                     notEqualTo6 : "#c_phase_current_hidden_add",
                     maxlength: 50
                 },
                 c_phase_current_add:{
                     notEqualTo : "#ammeter_total_energy_hidden_add",notEqualTo1 : "#a_phase_voltage_hidden_add",notEqualTo2 : "#b_phase_voltage_hidden_add",
                     notEqualTo3 : "#c_phase_voltage_hidden_add",notEqualTo4 : "#a_phase_current_hidden_add",notEqualTo5 : "#b_phase_current_hidden_add",
                     notEqualTo6 : "#ammeter_instant_energy_hidden_add",
                     maxlength: 50
                 }

            },
            messages: {
                ammeter_name_add:{
                    required: '请输入名称'
                },
                ammeter_cabinet_add:{
                    required: '请输入机柜名称'
                }
            },

            submitHandler: function(formData) {
                addSubmit(formData);
            }

        });



        // 编辑输入框验证
        var editValidate = $("#ammeter_edit_form").validate({
            rules: {
                 ammeter_name_edit:{
                     required: true,
                     maxlength: 50

                 },
                 ammeter_cabinet_edit:{
                     required: true,
                     maxlength: 50

                 },
                 ammeter_instant_energy_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50

                 },
                 ammeter_total_energy_edit:{
                     notEqualTo : "#ammeter_instant_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 a_phase_voltage_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#ammeter_instant_energy_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 b_phase_voltage_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#ammeter_instant_energy_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 c_phase_voltage_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#ammeter_instant_energy_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 a_phase_current_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#ammeter_instant_energy_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 b_phase_current_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#ammeter_instant_energy_hidden_edit",
                     notEqualTo6 : "#c_phase_current_hidden_edit",
                     maxlength: 50
                 },
                 c_phase_current_edit:{
                     notEqualTo : "#ammeter_total_energy_hidden_edit",notEqualTo1 : "#a_phase_voltage_hidden_edit",notEqualTo2 : "#b_phase_voltage_hidden_edit",
                     notEqualTo3 : "#c_phase_voltage_hidden_edit",notEqualTo4 : "#a_phase_current_hidden_edit",notEqualTo5 : "#b_phase_current_hidden_edit",
                     notEqualTo6 : "#ammeter_instant_energy_hidden_edit",
                     maxlength: 50
                 }
            },
            messages: {
                ammeter_name_edit:{
                    required: '请输入名称'
                },
                ammeter_cabinet_edit:{
                    required: '请输入机柜名称'
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
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/create",
                dataType: "json",
                data: {
                    name                 : formData.ammeter_name_add,
                    cabinetName          : formData.ammeter_cabinet_add,
                    instantEnergy        : formData.ammeter_instant_energy_hidden_add,
                    totalEnergy          : formData.ammeter_total_energy_hidden_add,
                    aPhaseVoltage        : formData.a_phase_voltage_hidden_add,
                    bPhaseVoltage        : formData.b_phase_voltage_hidden_add,
                    cPhaseVoltage        : formData.c_phase_voltage_hidden_add,
                    aPhaseCurrent        : formData.a_phase_current_hidden_add,
                    bPhaseCurrent        : formData.b_phase_current_hidden_add,
                    cPhaseCurrent        : formData.c_phase_current_hidden_add,
                    instantEnergyDisplay : formData.ammeter_instant_energy_add,
                    totalEnergyDisplay   : formData.ammeter_total_energy_add,
                    aPhaseVoltageDisplay : formData.a_phase_voltage_add,
                    bPhaseVoltageDisplay : formData.b_phase_voltage_add,
                    cPhaseVoltageDisplay : formData.c_phase_voltage_add,
                    aPhaseCurrentDisplay : formData.a_phase_current_add,
                    bPhaseCurrentDisplay : formData.b_phase_current_add,
                    cPhaseCurrentDisplay : formData.c_phase_current_add
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('ammeter_page', data);

                        });

                        $('#ammeter_modal_add').modal('hide');

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
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/update",
                dataType: "json",
                data: {
                    id                   : formData.id_ammeter_edit,
                    name                 : formData.ammeter_name_edit,
                    cabinetName          : formData.ammeter_cabinet_edit,
                    instantEnergy        : formData.ammeter_instant_energy_hidden_edit,
                    totalEnergy          : formData.ammeter_total_energy_hidden_edit,
                    aPhaseVoltage        : formData.a_phase_voltage_hidden_edit,
                    bPhaseVoltage        : formData.b_phase_voltage_hidden_edit,
                    cPhaseVoltage        : formData.c_phase_voltage_hidden_edit,
                    aPhaseCurrent        : formData.a_phase_current_hidden_edit,
                    bPhaseCurrent        : formData.b_phase_current_hidden_edit,
                    cPhaseCurrent        : formData.c_phase_current_hidden_edit,
                    instantEnergyDisplay : formData.ammeter_instant_energy_edit,
                    totalEnergyDisplay   : formData.ammeter_total_energy_edit,
                    aPhaseVoltageDisplay : formData.a_phase_voltage_edit,
                    bPhaseVoltageDisplay : formData.b_phase_voltage_edit,
                    cPhaseVoltageDisplay : formData.c_phase_voltage_edit,
                    aPhaseCurrentDisplay : formData.a_phase_current_edit,
                    bPhaseCurrentDisplay : formData.b_phase_current_edit,
                    cPhaseCurrentDisplay : formData.c_phase_current_edit
                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        $('#ammeter_modal_edit').modal('hide');

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
                            showPagingPage('ammeter_page', data);
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

        function deleteAmmeterConfig(id) {

            if (!id) {
                console.warn('无效参数！');
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/delete",
                dataType: "json",
                data: {
                    id : id,

                },
                success: function (result) {

                    var status = result && result.status;

                    if(status === "1"){

                        getPagingPage({}, function (data) {
                            showPagingPage('ammeter_page', data);
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
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/getPaging',
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