<#--
 xiepufeng

 冷热机组配置页面
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
    .form-control_device_config{display:block;width:94%;height:34px;padding:6px 12px;font-size:14px;line-height:1.42857143;color:#555;background-color:#fff;background-image:none;border:1px solid #ccc;border-radius:4px;-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075);box-shadow:inset 0 1px 1px rgba(0,0,0,.075);-webkit-transition:border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;-o-transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s;transition:border-color ease-in-out .15s,box-shadow ease-in-out .15s}

    #tree_device_type_modal_coolingheatingunit {
        position: absolute;
        left:71%;
        top: 11.5%;
    }
</style>

<!-- 信息表格模块 -->
<div class="information-model">

			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;冷热机组配置列表>>>
			</span>

        <!-- 增加按钮 -->
        <a href="javascript:void(-1);" id="cooling_heating_add" class="btn btn-primary toLeft">
            <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
        </a>

        <!-- 搜索框 -->
    <#--  <div class="zc_search find">
          <input type="text" class="find-style"  id="device_search_box" name="device_search_box" placeholder="设备编号">
          <button id="device_search"><i class="fa fa-search" aria-hidden="true"></i></button>
      </div>-->

    </div>

<!---分页列表----->
<div class="ibox" id="cooling_heating_unit_page" style="height:90%">
</div>

<#-- 添加模态框 -->
<div class="modal fade" id="cooling_heating_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 增加冷热机组配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cooling_heating_add_form" name="cooling_heating_add_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="name_add"
                                       name="name_add"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="current_state_hidden_add"
                                       name="current_state_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="current_state_add"
                                       name="current_state_add"
                                       class="form-control"
                                       placeholder="请选择当前状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷冻水供水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="freeze_supply_water_temperature_hidden_add"
                                       name="freeze_supply_water_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="freeze_supply_water_temperature_add"
                                       name="freeze_supply_water_temperature_add"
                                       class="form-control"
                                       placeholder="请选择冷冻水供水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷冻水回水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="freeze_return_water_temperature_hidden_add"
                                       name="freeze_return_water_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="freeze_return_water_temperature_add"
                                       name="freeze_return_water_temperature_add"
                                       class="form-control"
                                       placeholder="请选择冷冻水回水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却水供水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cooling_supply_water_temperature_hidden_add"
                                       name="cooling_supply_water_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cooling_supply_water_temperature_add"
                                       name="cooling_supply_water_temperature_add"
                                       class="form-control"
                                       placeholder="请选择冷却水供水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却水回水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cooling_return_water_temperature_hidden_add"
                                       name="cooling_return_water_temperature_hidden_add"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="cooling_return_water_temperature_add"
                                        name="cooling_return_water_temperature_add"
                                        class="form-control"
                                        placeholder="请选择冷却水回水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">总故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="current_flow_hidden_add"
                                       name="current_flow_hidden_add"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="current_flow_add"
                                       name="current_flow_add"
                                       class="form-control"
                                       placeholder="请选择当前流量"
                                >
                            </div>
                        </div>
			<!-- wanghongjie 电表的选择框 -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <select id="lrjz_ammeter_add" name="lrjz_ammeter_add" class="form-control" style="width: 390px">
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
<div class="modal fade" id="cooling_heating_modal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 700px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 编辑冷热机组配置信息</h4>
            </div>
            <div class="modal-body">
                <form id="cooling_heating_edit_form" name="cooling_heating_edit_form" class="form-horizontal">
                    <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">

                        <input type="hidden" id="id_heating_edit" name="id_heating_edit"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input style="width: 390px"
                                       type="text"
                                       id="name_edit"
                                       name="name_edit"
                                       class="form-control"
                                       placeholder="请输入名称"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">当前状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="current_state_hidden_edit"
                                       name="current_state_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="current_state_edit"
                                       name="current_state_edit"
                                       class="form-control"
                                       placeholder="请选择当前状态"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷冻水供水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="freeze_supply_water_temperature_hidden_edit"
                                       name="freeze_supply_water_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="freeze_supply_water_temperature_edit"
                                       name="freeze_supply_water_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择冷冻水供水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷冻水回水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="freeze_return_water_temperature_hidden_edit"
                                       name="freeze_return_water_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="freeze_return_water_temperature_edit"
                                       name="freeze_return_water_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择冷冻水回水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却水供水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cooling_supply_water_temperature_hidden_edit"
                                       name="cooling_supply_water_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="cooling_supply_water_temperature_edit"
                                       name="cooling_supply_water_temperature_edit"
                                       class="form-control"
                                       placeholder="请选择冷却水供水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">冷却水回水温度
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="cooling_return_water_temperature_hidden_edit"
                                       name="cooling_return_water_temperature_hidden_edit"
                                       type="hidden"
                                >
                                <input
                                        style="width: 390px"
                                        type="text"
                                        id="cooling_return_water_temperature_edit"
                                        name="cooling_return_water_temperature_edit"
                                        class="form-control"
                                        placeholder="请选择冷却水回水温度"
                                >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">总故障状态
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <input id="current_flow_hidden_edit"
                                       name="current_flow_hidden_edit"
                                       type="hidden"
                                >
                                <input style="width: 390px"
                                       type="text"
                                       id="current_flow_edit"
                                       name="current_flow_edit"
                                       class="form-control"
                                       placeholder="请选择当前流量"
                                >
                            </div>
                        </div>
			<!-- wanghongjie 电表的选择框 -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电表
                                <span style="color: red;">*</span>
                            </label>
                            <div class="col-sm-8">
                                <select id="lrjz_ammeter_edit" name="lrjz_ammeter_edit" class="form-control" style="width: 390px">
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
<div class="modal fade" id="tree_device_type_modal_coolingheatingunit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top: 8%;">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
            </div>
            <div class="modal-body" style="height: 450px">
                <div style="position: relative" class="zc_search find">
                    <input type="text" class="find-style" id="tree_device_type_search_heatingunit_input" name="eqTypeInfo" placeholder="设备类型">
                    <button id="tree_device_type_search_heatingunit_button"><i class="fa fa-search" aria-hidden="true"></i></button>
                </div>

                <#--设备类型树-->
                <div id="tree_device_heatingunit_type" class="Information_area ztree"></div>

            </div>
        </div><!-- end modal-content -->
    </div>
</div> <!-- end addParkBasicInfo -->

 <script type="text/javascript">

 var coolingHeatingUnit = (function($, window, document) {

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
             showPagingPage('cooling_heating_unit_page', data);

         });

     });
	<!--wanghongjie 进入页面新增和回显的电表编号的下拉框  start -->
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
                     $("#lrjz_ammeter_add").append(ops);
                 }else{
                     $("#lrjz_ammeter_edit").append(ops);
                 }
             },
             error:function(msg){
                 alert( "select列表查询失败!" );
             }
         });
     }
<!-- end -->
	 // 初始化设备类型树
	 function initTree() {
         tree = new Tree({
             container: 'tree_device_heatingunit_type',
             idKey: 'f_sys_name',
             pIdKey: 'f_psys_name',
			 name: 'f_nick_name',
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
	 
     // 获取冷热机组配置信息
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

     // 查询设备信息 回显
     function queryCoolingHeatingUnit(obj, callback) {

         if(typeof callback !== 'function'){
             return;
         }

         obj = obj || {};

         $.ajax({
             type    : "POST",
             url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/query",
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
     $('#tree_device_type_search_heatingunit_input').keydown(function (e){
         if(e.which !== 13){
             return;
         }

         tree.search('tree_device_type_search_heatingunit_input', 'f_nick_name');

     });

	 // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
     $('#tree_device_type_search_heatingunit_button').click(function () {

         tree.search('tree_device_type_search_heatingunit_input', 'f_nick_name');
     });

     // 显示添加模态框
     $('#cooling_heating_add').click(function () {
         $('#cooling_heating_modal_add').modal('show');
         modalAddActive = true;

     });

     // 显示编辑模态框事件
     $('#cooling_heating_modal_edit').on('show.bs.modal', function () {
         modalEditActive = true;
     });

	 // 添加输入框得到焦点时显示设备类型模态框
     $(
         '#current_state_add, ' +
         '#freeze_supply_water_temperature_add, ' +
         '#freeze_return_water_temperature_add, ' +
         '#cooling_supply_water_temperature_add, ' +
         '#cooling_return_water_temperature_add, ' +
         '#current_flow_add, ' +
         '#current_state_edit, ' +
         '#freeze_supply_water_temperature_edit, ' +
         '#freeze_return_water_temperature_edit, ' +
         '#cooling_supply_water_temperature_edit, ' +
         '#cooling_return_water_temperature_edit, ' +
         '#current_flow_edit'
     ).focus(function (obj) {
         inputObj = obj.target;
         $('#tree_device_type_modal_coolingheatingunit').modal('show');
     });


     // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
     $('#cooling_heating_modal_add').on('hide.bs.modal', function () {
         modalAddActive = false;
         inputObj = null;
         $('#tree_device_type_modal_coolingheatingunit').modal('hide');
         $('#name_add')                            .val('');
         $('#current_state_add')                   .val('');
         $('#freeze_supply_water_temperature_add') .val('');
         $('#freeze_return_water_temperature_add') .val('');
         $('#cooling_supply_water_temperature_add').val('');
         $('#cooling_return_water_temperature_add').val('');
         $('#current_flow_add')                    .val('');


         $('#current_state_hidden_add')                   .val('');
         $('#freeze_supply_water_temperature_hidden_add') .val('');
         $('#freeze_return_water_temperature_hidden_add') .val('');
         $('#cooling_supply_water_temperature_hidden_add').val('');
         $('#cooling_return_water_temperature_hidden_add').val('');
         $('#current_flow_hidden_add')                    .val('');
         $('#lrjz_ammeter_add')                    .val('');

         // 重置添加验证表单
         addValidate.resetForm()
     });

     // 关闭编辑模态框时清空输入框
     $("#cooling_heating_modal_edit").on('hidden.bs.modal', function(event) {

         inputObj = null;
         modalEditActive = false;
         $('#tree_device_type_modal_coolingheatingunit').modal('hide');

         $('#id_heating_edit')                                     .val('');
         $('#name_edit')                                   .val('');
         $('#current_state_hidden_edit')                   .val('');
         $('#freeze_supply_water_temperature_hidden_edit') .val('');
         $('#freeze_return_water_temperature_hidden_edit') .val('');
         $('#cooling_supply_water_temperature_hidden_edit').val('');
         $('#cooling_return_water_temperature_hidden_edit').val('');
         $('#current_flow_hidden_edit')                    .val('');
         $('#current_state_edit')                          .val('');
         $('#freeze_supply_water_temperature_edit')        .val('');
         $('#freeze_return_water_temperature_edit')        .val('');
         $('#cooling_supply_water_temperature_edit')       .val('');
         $('#cooling_return_water_temperature_edit')       .val('');
         $('#current_flow_edit')                           .val('');
         $('#lrjz_ammeter_edit')                           .val('');

         // 重置表单
         editValidate.resetForm()

     });

     // 当设备树模态框关闭时折叠设备树、清空搜索框内容
     $('#tree_device_type_modal_coolingheatingunit').on('hide.bs.modal', function () {
         tree.tree.expandAll( false );
         $('#tree_device_type_search_heatingunit_input').val('');
     })


     /**
      * 编辑点击事件
      *
      */
     $(document).on('click', '#coolingHeatingUnitTable button.edit', function () {

         var id = $(this).data('id');

         queryCoolingHeatingUnit({id}, function (obj) {

             var data = obj && obj.data && obj.data[0];

             if(!data){
                 return;
             }

             $('#id_heating_edit')                                     .val(data.id                                 || '');
             $('#name_edit')                                   .val(data.name                               || '');
             $('#current_state_hidden_edit')                   .val(data.currentState                       || '');
             $('#freeze_supply_water_temperature_hidden_edit') .val(data.freezeSupplyWaterTemperature       || '');
             $('#freeze_return_water_temperature_hidden_edit') .val(data.freezeReturnWaterTemperature       || '');
             $('#cooling_supply_water_temperature_hidden_edit').val(data.coolingSupplyWaterTemperature      || '');
             $('#cooling_return_water_temperature_hidden_edit').val(data.coolingReturnWaterTemperature      || '');
             $('#current_flow_hidden_edit')                    .val(data.currentFlow                        || '');
             $('#current_state_edit')                          .val(data.currentStateAlias                  || '');
             $('#freeze_supply_water_temperature_edit')        .val(data.freezeSupplyWaterTemperatureAlias  || '');
             $('#freeze_return_water_temperature_edit')        .val(data.freezeReturnWaterTemperatureAlias  || '');
             $('#cooling_supply_water_temperature_edit')       .val(data.coolingSupplyWaterTemperatureAlias || '');
             $('#cooling_return_water_temperature_edit')       .val(data.coolingReturnWaterTemperatureAlias || '');
             $('#current_flow_edit')                           .val(data.currentFlowAlias                   || '');
             $('#lrjz_ammeter_edit')                           .val(data.electric_meter_number                   || '');


         });
     });

     /**
      * 删除的点击事件
      *
      */
     $(document).on('click', '#coolingHeatingUnitTable button.delete', function () {

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
                     deleteCoolingHeatingUnit(id);
                 }
         );

     });



     // 添加输入框验证
     var addValidate = $("#cooling_heating_add_form").validate({
         rules: {
             name_add:{
                 required: true,
                 maxlength: 50

             },
             current_state_add:{
                 required: true,
                 maxlength: 50

             },
             freeze_supply_water_temperature_add:{
                 required: true,
                 maxlength: 50
             },
             freeze_return_water_temperature_add:{
                 required: true,
                 maxlength: 50
             },
             cooling_supply_water_temperature_add:{
                 required: true,
                 maxlength: 50
             },
             cooling_return_water_temperature_add:{
                 required: true,
                 maxlength: 50
             },
             current_flow_add:{
                 required: true,
                 maxlength: 50
             },
             lrjz_ammeter_add:{
                 required: true
             }

         },
         messages: {
             name_add:{
                 required: '请输入名称'
             },

             current_state_add:{
                 required: "请选择当前状态"
             },

             freeze_supply_water_temperature_add:{
                 required: "请选择冷冻水供水温度"
             },

             freeze_return_water_temperature_add:{
                 required: "请选择冷冻水回水温度"
             },
             cooling_supply_water_temperature_add:{
                 required: "请选择冷却水供水温度"
             },
             cooling_return_water_temperature_add:{
                 required: "请选择冷却水回水温度"
             },
             current_flow_add:{
                 required: "请选择当前流量"
             },
             lrjz_ammeter_add:{
                 required: "请选择电表"
             }
         },
         submitHandler: function(formData) {
             addSubmit(formData);
         }

     });

     // 编辑输入框验证
     var editValidate = $("#cooling_heating_edit_form").validate({
         rules: {
             name_edit:{
                 required: true,
                 maxlength: 50

             },
             current_state_edit:{
                 required: true,
                 maxlength: 50

             },
             freeze_supply_water_temperature_edit:{
                 required: true,
                 maxlength: 50
             },
             freeze_return_water_temperature_edit:{
                 required: true,
                 maxlength: 50
             },
             cooling_supply_water_temperature_edit:{
                 required: true,
                 maxlength: 50
             },
             cooling_return_water_temperature_edit:{
                 required: true,
                 maxlength: 50
             },
             current_flow_edit:{
                 required: true,
                 maxlength: 50
             },
             lrjz_ammeter_edit:{
                 required: true
             }

         },
         messages: {
             name_edit:{
                 required: '请输入名称'
             },

             current_state_edit:{
                 required: "请选择当前状态"
             },

             freeze_supply_water_temperature_edit:{
                 required: "请选择冷冻水供水温度"
             },

             freeze_return_water_temperature_edit:{
                 required: "请选择冷冻水回水温度"
             },
             cooling_supply_water_temperature_edit:{
                 required: "请选择冷却水供水温度"
             },
             cooling_return_water_temperature_edit:{
                 required: "请选择冷却水回水温度"
             },
             current_flow_edit:{
                 required: "请选择当前流量"
             },
             lrjz_ammeter_edit:{
                 required: "请选择电表"
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
             url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/create",
             dataType: "json",
             data: {
                 name                              : formData.name_add,
                 currentState                      : formData.current_state_hidden_add,
                 freezeSupplyWaterTemperature      : formData.freeze_supply_water_temperature_hidden_add,
                 freezeReturnWaterTemperature      : formData.freeze_return_water_temperature_hidden_add,
                 coolingSupplyWaterTemperature     : formData.cooling_supply_water_temperature_hidden_add,
                 coolingReturnWaterTemperature     : formData.cooling_return_water_temperature_hidden_add,
                 currentFlow                       : formData.current_flow_hidden_add,
                 currentStateAlias                 : formData.current_state_add,
                 freezeSupplyWaterTemperatureAlias : formData.freeze_supply_water_temperature_add,
                 freezeReturnWaterTemperatureAlias : formData.freeze_return_water_temperature_add,
                 coolingSupplyWaterTemperatureAlias: formData.cooling_supply_water_temperature_add,
                 coolingReturnWaterTemperatureAlias: formData.cooling_return_water_temperature_add,
                 currentFlowAlias                  : formData.current_flow_add,
                 electric_meter_number             : formData.lrjz_ammeter_add,
             },
             success: function (result) {

                 var status = result && result.status;

                 if(status === "1"){

                     getPagingPage({}, function (data) {
                         showPagingPage('cooling_heating_unit_page', data);

                     });

                     $('#cooling_heating_modal_add').modal('hide');

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
             url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/update",
             dataType: "json",
             data: {
                 id                                : formData.id_heating_edit,
                 name                              : formData.name_edit,
                 currentState                      : formData.current_state_hidden_edit,
                 freezeSupplyWaterTemperature      : formData.freeze_supply_water_temperature_hidden_edit,
                 freezeReturnWaterTemperature      : formData.freeze_return_water_temperature_hidden_edit,
                 coolingSupplyWaterTemperature     : formData.cooling_supply_water_temperature_hidden_edit,
                 coolingReturnWaterTemperature     : formData.cooling_return_water_temperature_hidden_edit,
                 currentFlow                       : formData.current_flow_hidden_edit,
                 currentStateAlias                 : formData.current_state_edit,
                 freezeSupplyWaterTemperatureAlias : formData.freeze_supply_water_temperature_edit,
                 freezeReturnWaterTemperatureAlias : formData.freeze_return_water_temperature_edit,
                 coolingSupplyWaterTemperatureAlias: formData.cooling_supply_water_temperature_edit,
                 coolingReturnWaterTemperatureAlias: formData.cooling_return_water_temperature_edit,
                 currentFlowAlias                  : formData.current_flow_edit,
                 electric_meter_number             : formData.lrjz_ammeter_edit,
             },
             success: function (result) {

                 var status = result && result.status;

                 if(status === "1"){

                     $('#cooling_heating_modal_edit').modal('hide');

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
                         showPagingPage('cooling_heating_unit_page', data);
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
     //删除表单信息
     function deleteCoolingHeatingUnit(id) {

         if (!id) {
             console.warn('无效参数！');
             return;
         }

         $.ajax({
             type    : "POST",
             url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/delete",
             dataType: "json",
             data: {
                 id : id,

             },
             success: function (result) {

                 var status = result && result.status;

                 if(status === "1"){

                     getPagingPage({}, function (data) {
                         showPagingPage('cooling_heating_unit_page', data);
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
             url     : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/getPaging',
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