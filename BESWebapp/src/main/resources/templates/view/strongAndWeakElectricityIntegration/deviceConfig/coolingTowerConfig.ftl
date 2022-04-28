<#--
 xiepufeng

 冷却塔配置页面
-->

<style>
    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        border: 1px #FFB951 solid;
        opacity: 0.8;
    }

    #tree_device_type_modal_coolingtowerconfig {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;冷却塔配置列表>>>
			</span>

    <!-- 增加按钮 -->
    <a href="javascript:void(-1);" id="cooling_tower_config_add" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>

</div>

<!---分页列表----->
<div class="ibox" id="cooling_tower_config_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="cooling_tower_config_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加冷却塔配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cooling_tower_config_add_form" name="cooling_tower_config_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cooling_tower_name_add"
                                       name="cooling_tower_name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔工作模式
                            </label>
                            <div class="col-sm-8">
                                <input id="operating_mode_add"
                                       name="operating_mode_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operating_mode_display_add"
                                       name="operating_mode_display_add"
                                       class="form-control"
                                       placeholder="请配置风机启停"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="fault_state_add"
                                       name="fault_state_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fault_state_display_add"
                                       name="fault_state_display_add"
                                       class="form-control"
                                       placeholder="请配置故障状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔运行时间
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_hours_add"
                                       name="operation_hours_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_hours_display_add"
                                       name="operation_hours_display_add"
                                       class="form-control"
                                       placeholder="请配置运行时间"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_open_arrives_add"
                                       name="inlet_valve_open_arrives_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_open_arrives_display_add"
                                       name="inlet_valve_open_arrives_display_add"
                                       class="form-control"
                                       placeholder="请配置进水阀开到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_close_arrives_add"
                                       name="inlet_valve_close_arrives_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_close_arrives_display_add"
                                       name="inlet_valve_close_arrives_display_add"
                                       class="form-control"
                                       placeholder="请配置进水阀关到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_open_arrives_add"
                                       name="outlet_valve_open_arrives_add"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="outlet_valve_open_arrives_display_add"
                                        name="outlet_valve_open_arrives_display_add"
                                        class="form-control"
                                        placeholder="请配置出水阀开到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_close_arrives_add"
                                       name="outlet_valve_close_arrives_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="outlet_valve_close_arrives_display_add"
                                       name="outlet_valve_close_arrives_display_add"
                                       class="form-control"
                                       placeholder="请配置出水阀关到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀故障
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_fault_add"
                                       name="inlet_valve_fault_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_fault_display_add"
                                       name="inlet_valve_fault_display_add"
                                       class="form-control"
                                       placeholder="请配置进水阀故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀故障
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_fault_add"
                                       name="outlet_valve_fault_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="outlet_valve_fault_display_add"
                                       name="outlet_valve_fault_display_add"
                                       class="form-control"
                                       placeholder="请配置出水阀故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机故障
                            </label>
                            <div class="col-sm-8">
                                <input id="fan_fault_add"
                                       name="fan_fault_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fan_fault_display_add"
                                       name="fan_fault_display_add"
                                       class="form-control"
                                       placeholder="请配置风机故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="running_status_add"
                                       name="running_status_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="running_status_display_add"
                                       name="running_status_display_add"
                                       class="form-control"
                                       placeholder="请配置风机运行状态"
                                >
                            </div>
                        </div>
			<!--wanghongjie 电表的选择框- -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表编号
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <select id="Electric_meter_number_add_lqt" name="Electric_meter_number_add_lqt" class="form-control" style="width: 390px">
                                </select>
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
<div class="modal fade" id="cooling_tower_config_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑冷却塔配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cooling_tower_config_edit_form" name="cooling_tower_config_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_tower_edit" name="id_tower_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="cooling_tower_name_edit"
                                       name="cooling_tower_name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔工作模式
                            </label>
                            <div class="col-sm-8">
                                <input id="operating_mode_edit"
                                       name="operating_mode_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operating_mode_display_edit"
                                       name="operating_mode_display_edit"
                                       class="form-control"
                                       placeholder="请配置风机启停"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔故障状态
                            </label>
                            <div class="col-sm-8">
                                <input id="fault_state_edit"
                                       name="fault_state_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fault_state_display_edit"
                                       name="fault_state_display_edit"
                                       class="form-control"
                                       placeholder="请配置故障状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却塔运行时间
                            </label>
                            <div class="col-sm-8">
                                <input id="operation_hours_edit"
                                       name="operation_hours_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="operation_hours_display_edit"
                                       name="operation_hours_display_edit"
                                       class="form-control"
                                       placeholder="请配置运行时间"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_open_arrives_edit"
                                       name="inlet_valve_open_arrives_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_open_arrives_display_edit"
                                       name="inlet_valve_open_arrives_display_edit"
                                       class="form-control"
                                       placeholder="请配置进水阀开到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_close_arrives_edit"
                                       name="inlet_valve_close_arrives_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_close_arrives_display_edit"
                                       name="inlet_valve_close_arrives_display_edit"
                                       class="form-control"
                                       placeholder="请配置进水阀关到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀开到位
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_open_arrives_edit"
                                       name="outlet_valve_open_arrives_edit"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="outlet_valve_open_arrives_display_edit"
                                        name="outlet_valve_open_arrives_display_edit"
                                        class="form-control"
                                        placeholder="请配置出水阀开到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀关到位
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_close_arrives_edit"
                                       name="outlet_valve_close_arrives_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="outlet_valve_close_arrives_display_edit"
                                       name="outlet_valve_close_arrives_display_edit"
                                       class="form-control"
                                       placeholder="请配置出水阀关到位"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">进水阀故障
                            </label>
                            <div class="col-sm-8">
                                <input id="inlet_valve_fault_edit"
                                       name="inlet_valve_fault_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="inlet_valve_fault_display_edit"
                                       name="inlet_valve_fault_display_edit"
                                       class="form-control"
                                       placeholder="请配置进水阀故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">出水阀故障
                            </label>
                            <div class="col-sm-8">
                                <input id="outlet_valve_fault_edit"
                                       name="outlet_valve_fault_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="outlet_valve_fault_display_edit"
                                       name="outlet_valve_fault_display_edit"
                                       class="form-control"
                                       placeholder="请配置出水阀故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机故障
                            </label>
                            <div class="col-sm-8">
                                <input id="fan_fault_edit"
                                       name="fan_fault_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="fan_fault_display_edit"
                                       name="fan_fault_display_edit"
                                       class="form-control"
                                       placeholder="请配置风机故障"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">风机运行状态
                            </label>
                            <div class="col-sm-8">
                                <input id="running_status_edit"
                                       name="running_status_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="running_status_display_edit"
                                       name="running_status_display_edit"
                                       class="form-control"
                                       placeholder="请配置风机运行状态"
                                >
                            </div>
                        </div>
			<!--wanghongjie 电表的选择框-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表编号
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <select id="Electric_meter_number_edit_lqt" name="Electric_meter_number_edit_lqt" class="form-control" style="width: 390px">
                                </select>
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
<div class="modal fade" id="tree_device_type_modal_coolingtowerconfig" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 698px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

            <#--设备类型树-->
                <div id="tree_device_type" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

 <script type="text/javascript">

     var coolingTowerConfigStrongWeakElectrical = (function($, window, document) {

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
                 showPagingPage('cooling_tower_config_page', data);

             });

         });
	 <!--wanghongjie 进入页面的新增和修改的电表回显的下拉框 start-->
         $(function(){
             Electric_meter_number_add_lqt('add');
             Electric_meter_number_add_lqt('edit');
         });
         //select标签查询
         function Electric_meter_number_add_lqt(keywords){
             $.ajax({
                 type: "POST",
                 url: _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/electricMeterNnumber',
                 data:"",
                 success: function(data){
                     var ops="<option value=''>请选择电表编号</option>";
                     for(var i=0;i<data.list.length;i++){
                         var obj=data.list[i];
                         ops+='<option value="'+obj.id+'">';
                         ops+=obj.id+'('+obj.name+')';
                         ops+='</option>';
                     }
                     if(keywords == 'add'){
                         $("#Electric_meter_number_add_lqt").append(ops);
                     }else{
                         $("#Electric_meter_number_edit_lqt").append(ops);
                     }
                 },
                 error:function(msg){
                     alert( "select列表查询失败!" );
                 }
             });
         }
	<!--wanghongjie end -->
         // 初始化设备类型树
         function initTree() {
             tree = new Tree({
                 container: 'tree_device_type',
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
         function queryCoolingTowerConfig(obj, callback) {

             if(typeof callback !== 'function'){
                 return;
             }

             obj = obj || {};

             $.ajax({
                 type    : "POST",
                 url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/query",
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
         $('#tree_device_type_search_input').keydown(function (e){
             if(e.which !== 13){
                 return;
             }

             tree.search('tree_device_type_search_input', 'f_nick_name');

         });

         // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
         $('#tree_device_type_search_button').click(function () {

             tree.search('tree_device_type_search_input', 'f_nick_name');
         });

         // 显示添加模态框
         $('#cooling_tower_config_add').click(function () {
             $('#cooling_tower_config_modal_add').modal('show');
             modalAddActive = true;

         });

         // 显示编辑模态框事件
         $('#cooling_tower_config_modal_edit').on('show.bs.modal', function () {
             modalEditActive = true;
         });

         // 添加输入框得到焦点时显示设备类型模态框
         $(

                 '#operating_mode_display_add, ' +
                 '#fault_state_display_add, ' +
                 '#operation_hours_display_add, ' +
                 '#inlet_valve_open_arrives_display_add, ' +
                 '#inlet_valve_close_arrives_display_add, ' +
                 '#outlet_valve_open_arrives_display_add, ' +
                 '#outlet_valve_close_arrives_display_add, ' +
                 '#inlet_valve_fault_display_add, ' +
                 '#outlet_valve_fault_display_add, ' +
                 '#fan_fault_display_add, ' +
                 '#running_status_display_add, ' +
                 '#operating_mode_display_edit, ' +
                 '#fault_state_display_edit, ' +
                 '#operation_hours_display_edit, ' +
                 '#inlet_valve_open_arrives_display_edit, ' +
                 '#inlet_valve_close_arrives_display_edit, ' +
                 '#outlet_valve_open_arrives_display_edit, ' +
                 '#outlet_valve_close_arrives_display_edit, ' +
                 '#inlet_valve_fault_display_edit, ' +
                 '#outlet_valve_fault_display_edit, ' +
                 '#fan_fault_display_edit, ' +
                 '#running_status_display_edit'

         ).focus(function (obj) {
             inputObj = obj.target;
             $('#tree_device_type_modal_coolingtowerconfig').modal('show');
         });


         // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
         $('#cooling_tower_config_modal_add').on('hide.bs.modal', function () {
             modalAddActive = false;
             inputObj = null;
             $('#tree_device_type_modal_coolingtowerconfig').modal('hide');

             $('#cooling_tower_name_add')                .val('');
             $('#operating_mode_display_add')            .val('');
             $('#fault_state_display_add')               .val('');
             $('#operation_hours_display_add')           .val('');
             $('#inlet_valve_open_arrives_display_add')  .val('');
             $('#inlet_valve_close_arrives_display_add') .val('');
             $('#outlet_valve_open_arrives_display_add') .val('');
             $('#outlet_valve_close_arrives_display_add').val('');
             $('#inlet_valve_fault_display_add')         .val('');
             $('#outlet_valve_fault_display_add')        .val('');
             $('#fan_fault_display_add')                 .val('');
             $('#running_status_display_add')            .val('');
             $('#operating_mode_add')                    .val('');
             $('#fault_state_add')                       .val('');
             $('#operation_hours_add')                   .val('');
             $('#inlet_valve_open_arrives_add')          .val('');
             $('#inlet_valve_close_arrives_add')         .val('');
             $('#outlet_valve_open_arrives_add')         .val('');
             $('#outlet_valve_close_arrives_add')        .val('');
             $('#inlet_valve_fault_add')                 .val('');
             $('#outlet_valve_fault_add')                .val('');
             $('#fan_fault_add')                         .val('');
             $('#running_status_add')                    .val('');
             $('#Electric_meter_number_add_lqt')          .val('');


             // 重置添加验证表单
             addValidate.resetForm()
         });

         // 关闭编辑模态框时清空输入框
         $("#cooling_tower_config_modal_edit").on('hidden.bs.modal', function(event) {

             inputObj = null;
             modalEditActive = false;
             $('#tree_device_type_modal_coolingtowerconfig').modal('hide');

             $('#id_tower_edit')                          .val('');
             $('#cooling_tower_name_edit')                .val('');

             $('#operating_mode_display_edit')            .val('');
             $('#fault_state_display_edit')               .val('');
             $('#operation_hours_display_edit')           .val('');
             $('#inlet_valve_open_arrives_display_edit')  .val('');
             $('#inlet_valve_close_arrives_display_edit') .val('');
             $('#outlet_valve_open_arrives_display_edit') .val('');
             $('#outlet_valve_close_arrives_display_edit').val('');
             $('#inlet_valve_fault_display_edit')         .val('');
             $('#outlet_valve_fault_display_edit')        .val('');
             $('#fan_fault_display_edit')                 .val('');
             $('#running_status_display_edit')            .val('');
             $('#operating_mode_edit')                    .val('');
             $('#fault_state_edit')                       .val('');
             $('#operation_hours_edit')                   .val('');
             $('#inlet_valve_open_arrives_edit')          .val('');
             $('#inlet_valve_close_arrives_edit')         .val('');
             $('#outlet_valve_open_arrives_edit')         .val('');
             $('#outlet_valve_close_arrives_edit')        .val('');
             $('#inlet_valve_fault_edit')                 .val('');
             $('#outlet_valve_fault_edit')                .val('');
             $('#fan_fault_edit')                         .val('');
             $('#running_status_edit')                    .val('');
             $('#Electric_meter_number_edit_lqt')         .val('');

             // 重置表单
             editValidate.resetForm()

         });

         // 当设备树模态框关闭时折叠设备树、清空搜索框内容
         $('#tree_device_type_modal_coolingtowerconfig').on('hide.bs.modal', function () {
             tree.tree.expandAll( false );
             $('#tree_device_type_search_input').val('');
         });


         /**
          * 编辑点击事件
          *
          */
         $(document).on('click', '#coolingTowerConfigTable button.edit', function () {


             var id = $(this).data('id');

             queryCoolingTowerConfig({id}, function (obj) {

                 var data = obj && obj.data && obj.data[0];

                 if(!data){
                     return;
                 }

                 $('#id_tower_edit')                          .val(data.id                             || '');
                 $('#cooling_tower_name_edit')                .val(data.name                           || '');

                 $('#operating_mode_edit')                     .val(data.operatingMode                      || '');
                 $('#fault_state_edit')                        .val(data.faultState                 || '');
                 $('#operation_hours_edit')                    .val(data.operationHours                || '');

                 $('#inlet_valve_open_arrives_edit')          .val(data.inletValveOpenArrives          || '');
                 $('#inlet_valve_close_arrives_edit')         .val(data.inletValveCloseArrives         || '');
                 $('#outlet_valve_open_arrives_edit')         .val(data.outletValveOpenArrives         || '');
                 $('#outlet_valve_close_arrives_edit')        .val(data.outletValveCloseArrives        || '');
                 $('#inlet_valve_fault_edit')                 .val(data.inletValveFault                || '');
                 $('#outlet_valve_fault_edit')                .val(data.outletValveFault               || '');
                 $('#fan_fault_edit')                         .val(data.fanFault                       || '');
                 $('#running_status_edit')                    .val(data.runningStatus                  || '');

                 $('#operating_mode_display_edit')            .val(data.operatingModeDisplay          || '');
                 $('#fault_state_display_edit')               .val(data.faultStateDisplay               || '');
                 $('#operation_hours_display_edit')           .val(data.operationHoursDisplay         || '');

                 $('#inlet_valve_open_arrives_display_edit')  .val(data.inletValveOpenArrivesDisplay   || '');
                 $('#inlet_valve_close_arrives_display_edit') .val(data.inletValveCloseArrivesDisplay  || '');
                 $('#outlet_valve_open_arrives_display_edit') .val(data.outletValveOpenArrivesDisplay  || '');
                 $('#outlet_valve_close_arrives_display_edit').val(data.outletValveCloseArrivesDisplay || '');
                 $('#inlet_valve_fault_display_edit')         .val(data.inletValveFaultDisplay         || '');
                 $('#outlet_valve_fault_display_edit')        .val(data.outletValveFaultDisplay        || '');
                 $('#fan_fault_display_edit')                 .val(data.fanFaultDisplay                || '');
                 $('#running_status_display_edit')            .val(data.runningStatusDisplay           || '');
                 $('#Electric_meter_number_edit_lqt')         .val(data.electric_meter_number || '');



             });
         });

         /**
          * 编辑点击事件
          *
          */
         $(document).on('click', '#coolingTowerConfigTable button.delete', function () {

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
                         deleteCoolingTowerConfig(id);
                     }
             );

         });



         // 添加输入框验证
         var addValidate = $("#cooling_tower_config_add_form").validate({
             rules: {
                 cooling_tower_name_add:{
                     required: true,
                     maxlength: 50

                 },
                 Electric_meter_number_add_lqt:{
                     required: true

                 }

             },
             messages: {
                 cooling_tower_name_add:{
                     required: '请输入名称'
                 },
                 Electric_meter_number_add_lqt:{
                     required: "请选择电表编号"
                 }
             },
             submitHandler: function(formData) {
                 addSubmit(formData);
             }

         });

         // 编辑输入框验证
         var editValidate = $("#cooling_tower_config_edit_form").validate({
             rules: {
                 cooling_tower_name_edit:{
                     required: true,
                     maxlength: 50

                 },
                 Electric_meter_number_edit_lqt:{
                     required: true

                 }
             },
             messages: {
                 cooling_tower_name_edit:{
                     required: '请输入名称'
                 },
                 Electric_meter_number_edit_lqt:{
                     required: "请选择电表编号"

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
                 url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/create",
                 dataType: "json",
                 data: {
                     name                           : formData.cooling_tower_name_add,
                     operatingMode                  : formData.operating_mode_add,
                     faultState                     : formData.fault_state_add,
                     operationHours                 : formData.operation_hours_add,
                     inletValveOpenArrives          : formData.inlet_valve_open_arrives_add,
                     inletValveCloseArrives         : formData.inlet_valve_close_arrives_add,
                     outletValveOpenArrives         : formData.outlet_valve_open_arrives_add,
                     outletValveCloseArrives        : formData.outlet_valve_close_arrives_add,
                     inletValveFault                : formData.inlet_valve_fault_add,
                     outletValveFault               : formData.outlet_valve_fault_add,
                     fanFault                       : formData.fan_fault_add,
                     runningStatus                  : formData.running_status_add,
                     operatingModeDisplay           : formData.operating_mode_display_add,
                     faultStateDisplay              : formData.fault_state_display_add,
                     operationHoursDisplay          : formData.operation_hours_display_add,
                     inletValveOpenArrivesDisplay   : formData.inlet_valve_open_arrives_display_add,
                     inletValveCloseArrivesDisplay  : formData.inlet_valve_close_arrives_display_add,
                     outletValveOpenArrivesDisplay  : formData.outlet_valve_open_arrives_display_add,
                     outletValveCloseArrivesDisplay : formData.outlet_valve_close_arrives_display_add,
                     inletValveFaultDisplay         : formData.inlet_valve_fault_display_add,
                     outletValveFaultDisplay        : formData.outlet_valve_fault_display_add,
                     fanFaultDisplay                : formData.fan_fault_display_add,
                     runningStatusDisplay           : formData.running_status_display_add,
                     electric_meter_number          : formData.Electric_meter_number_add_lqt
                 },
                 success: function (result) {

                     var status = result && result.status;

                     if(status === "1"){

                         getPagingPage({}, function (data) {
                             showPagingPage('cooling_tower_config_page', data);

                         });

                         $('#cooling_tower_config_modal_add').modal('hide');

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
                 url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/update",
                 dataType: "json",
                 data: {
                     id                             : formData.id_tower_edit,
                     name                           : formData.cooling_tower_name_edit,
                     operatingMode                  : formData.operating_mode_edit,
                     faultState                     : formData.fault_state_edit,
                     operationHours                 : formData.operation_hours_edit,
                     inletValveOpenArrives          : formData.inlet_valve_open_arrives_edit,
                     inletValveCloseArrives         : formData.inlet_valve_close_arrives_edit,
                     outletValveOpenArrives         : formData.outlet_valve_open_arrives_edit,
                     outletValveCloseArrives        : formData.outlet_valve_close_arrives_edit,
                     inletValveFault                : formData.inlet_valve_fault_edit,
                     outletValveFault               : formData.outlet_valve_fault_edit,
                     fanFault                       : formData.fan_fault_edit,
                     runningStatus                  : formData.running_status_edit,
                     operatingModeDisplay           : formData.operating_mode_display_edit,
                     faultStateDisplay              : formData.fault_state_display_edit,
                     operationHoursDisplay          : formData.operation_hours_display_edit,
                     inletValveOpenArrivesDisplay   : formData.inlet_valve_open_arrives_display_edit,
                     inletValveCloseArrivesDisplay  : formData.inlet_valve_close_arrives_display_edit,
                     outletValveOpenArrivesDisplay  : formData.outlet_valve_open_arrives_display_edit,
                     outletValveCloseArrivesDisplay : formData.outlet_valve_close_arrives_display_edit,
                     inletValveFaultDisplay         : formData.inlet_valve_fault_display_edit,
                     outletValveFaultDisplay        : formData.outlet_valve_fault_display_edit,
                     fanFaultDisplay                : formData.fan_fault_display_edit,
                     runningStatusDisplay           : formData.running_status_display_edit,
                     electric_meter_number          : formData.Electric_meter_number_edit_lqt
                 },
                 success: function (result) {

                     var status = result && result.status;

                     if(status === "1"){

                         $('#cooling_tower_config_modal_edit').modal('hide');

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
                             showPagingPage('cooling_tower_config_page', data);
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

         function deleteCoolingTowerConfig(id) {

             if (!id) {
                 console.warn('无效参数！');
                 return;
             }

             $.ajax({
                 type    : "POST",
                 url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/delete",
                 dataType: "json",
                 data: {
                     id : id,

                 },
                 success: function (result) {

                     var status = result && result.status;

                     if(status === "1"){

                         getPagingPage({}, function (data) {
                             showPagingPage('cooling_tower_config_page', data);
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
                 url     : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/getPaging',
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