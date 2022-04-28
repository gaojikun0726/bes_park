<#--
 设备配置
王红杰
-->
<style>
  .information_left_deviceConfiguration{
    /* margin-top: -10px; */
    height: 95% !important;
    border-bottom: solid 1px #366c90;
    border-top: solid 1px #366c90;
    border-right: solid 1px #366c90;
  }
  .leftarea_deviceConfiguration{
    float: left;
    width: 50%;
    /* margin-left: -10px; */
    position: relative;
    box-sizing: border-box;
    height: auto;
    border-right: solid 1px rgb(54, 108, 144);
    /* padding: 0px 0px 0px 10px; */
    /* background-color: #ffffff; */
  }
  .information_right_deviceConfiguration{
    float: right;
    height: 95%;
    width: 50%;
    position: relative;
    /* margin-top: -10px; */
    border: solid 0.5px #366c90;
  }
  #tree_device_type_modal_deviceConfiguration_Function_Point {
    position: absolute;
    left:71%;
    top: 11.5%;
  }
</style>

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i> 设备配置列表>>>
		</span>
  <!-- 增加按钮 -->
  <a id="adddeviceConfiguration" data-toggle="modal" href="#deviceConfigurationModalAdd" class="btn btn-primary toLeft">
    <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
  </a>

  <div  style = "display:inline-block ;margin-left: 15px;">设备类型 :</div>
  <div id='deviceConfiguration_sblx' style = "display:inline-block ;margin-left: 15px;"></div>

  <!-- 搜索框 -->
  <div class="zc_search find">
    <input type="text" class="find-style" id="DeviceConfigurationKeywords" name="DeviceConfigurationKeywords"
           placeholder="设备名称">
    <button id="querydeviceConfiguration" onclick="deviceConfiguration.search()">
      <i class="fa fa-search" aria-hidden="true"></i>
    </button>
  </div>
</div>


<!---分页列表----->
<div class="ibox leftarea_deviceConfiguration information_left_deviceConfiguration" id="deviceConfigurationPageContainer"   ></div>
<div class="ibox information_right_deviceConfiguration" style="clear: initial" id="deviceConfiguration_FunctionPageContainer" ></div>

<!---添加设备类型开始----->
<div class="modal fade" id="deviceConfigurationModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp;添加设备配置</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceConfigurationFormAdd" name="deviceConfigurationFormEdit" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationNameAdd" name="deviceConfigurationNameAdd" placeholder="请输入设备名称"
                     class="form-control">
            </div>
          </div>
         <#-- <div class="form-group">
            <label class="col-sm-3 control-label">设备类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="deviceConfigurationDeviceTypeAdd" name="deviceConfigurationDeviceTypeAdd" required class="form-control">

              </select>
            </div>
          </div>-->
          <div class="form-group">
            <label class="col-sm-3 control-label">设备代码<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationCodeAdd" name="deviceConfigurationCodeAdd" placeholder="请输入设备代码"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">位置<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationSiteAdd" name="deviceConfigurationSiteAdd" placeholder="请输入位置"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationCommentsAdd" name="deviceConfigurationCommentsAdd"
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
<div class="modal fade" id="deviceConfigurationModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;编辑设备配置</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceConfigurationFormEdit" name="deviceConfigurationFormEdit" class="form-horizontal">

          <div class="form-group">
            <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <div type="hidden" id="deviceConfigurationIdEdit" name="deviceConfigurationIdEdit"></div>
              <input type="text" id="deviceConfigurationNameEdit" name="deviceConfigurationNameEdit" placeholder="请输入设备名称"
                     class="form-control">
            </div>
          </div>
          <#--<div class="form-group">
            <label class="col-sm-3 control-label">设备类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="deviceConfigurationDeviceTypeEdit" name="deviceConfigurationDeviceTypeEdit"  class="form-control">

              </select>
            </div>
          </div>-->
          <div class="form-group">
            <label class="col-sm-3 control-label">设备代码<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationCodeEdit" name="deviceConfigurationCodeEdit" placeholder="请输入设备代码"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">位置<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationSiteEdit" name="deviceConfigurationSiteEdit" placeholder="请输入位置"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceConfigurationCommentsEdit" name="deviceConfigurationCommentsEdit"
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

<!----编辑功能点位--->
<div class="modal fade" id="deviceConfiguration_Function_AssociationPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" >
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 700px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;配置功能点位</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionPointFormEdit" name="deviceFunctionPointFormEdit" class="form-horizontal">
          <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">
            <div class="form-group">
              <label class="col-sm-3 control-label">系统名称<span class="text-danger">*</span></label>
              <div class="col-sm-8">

                <input id="deviceFunctionPointSysNameEdit_hidden"
                       name="deviceFunctionPointSysNameEdit_hidden"
                       type="hidden"
                >
                <input type="text"
                       id="deviceFunctionPointSysNameEdit"
                       name="deviceFunctionPointSysNameEdit"
                       class="form-control"
                       placeholder="请配置系统名称"
                >
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">功能点位名称<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                <div type="hidden" id="deviceFunctionPointIdEdit" name="deviceFunctionPointIdEdit"></div>
                <input type="text" id="deviceFunctionPointNameEdit" name="deviceFunctionPointNameEdit" placeholder="请输入功能点位名称"
                       class="form-control">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label">备注</label>
              <div class="col-sm-8">
                <input type="text" id="deviceFunctionPointCommentsEdit" name="deviceFunctionPointCommentsEdit"
                       class="form-control">
              </div>
            </div>
            <div class="form-group m-t-sm">
              <div class="col-sm-6 col-sm-push-3 display_flex">
                <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
              </div>
            </div>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
<!----编辑功能点位结束--->

<#-- 设备类型模态框 -->
<div class="modal fade" id="tree_device_type_modal_deviceConfiguration_Function_Point" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 350px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
      </div>
      <div class="modal-body" style="height: 450px">
        <div style="position: relative" class="zc_search find">
          <input type="text" class="find-style" id="tree_device_type_modal_deviceConfiguration_Function_Point_input" name="eqTypeInfo" placeholder="设备类型">
          <button id="tree_device_type_modal_deviceConfiguration_Function_Point_button"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>

          <#--设备类型树-->
        <div id="tree_device_type_deviceConfiguration_Function_Point" class="Information_area ztree"></div>

      </div>
    </div><!-- end modal-content -->
  </div>
</div> <!-- end addParkBasicInfo -->


<script type="text/javascript">
  ;
  var deviceConfiguration = (function ($, window, document, undefined)
  {
    var _ctx = '${ctx}';

    var deviceType_id=[];//设备类型下拉表id
    var deviceType_val=[];//设备类型下拉表值
    var deviceTypeId;

    var deviceId;//设备id
    var deviceFunctionId;//设备功能id
    var inputObj; // 模态框内输入框保存对象
    var tree;// 树对象
    var modalAddActive = false; // 保存添加模态框是否是显示状态 true 显示 | false 隐藏
    var modalEditActive = false; // 保存编辑模态框是否是显示状态 true 显示 | false 隐藏

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
    });

    // 初始化设备类型树
    function initTree() {
      tree = new Tree({
        container: 'tree_device_type_deviceConfiguration_Function_Point',
        idKey: 'f_sys_name',
        pIdKey: 'f_psys_name',
        name: 'f_nick_name',
        setting: {
          view: {
            showIcon: false,
            fontCss: function ( treeId, treeNode ) {
              return ( treeNode.highlight )
                  ? {color:'#0bfffc', 'font-weight':'bold'}
                  : {color:'#D1E3F9', 'font-weight':'normal'};
            }
          }
        },
        callback: function (node) {

          if ((modalEditActive) && inputObj) {
            
            if (node.f_type == "10" || node.f_type == "11" || node.f_type == "12" || node.f_type == "13" || node.f_type == "16") {
              let  cid = node.cid;
              let edit = isItRedundantEdit(cid);
              if (edit == 0) {//添加的点位不重复
                inputObj.value = node.f_sys_name_old;
                // $(inputObj).prev().val(node.f_sys_name);
                $("#deviceFunctionPointNameEdit").val(node.f_nick_name);
              }else {//添加的点位重复
                swal({
                  title : '此点位已存在!',// 展示的标题
                  text : "",// 内容
                  type : "warning",
                  showCloseButton : false, // 展示关闭按钮
                  allowOutsideClick : false,
                  showConfirmButton : false,
                  timer : 1000
                });
                return;
              }
            } else {
              swal({
                title : '请选择正确的点位!',// 展示的标题
                text : "",// 内容
                type : "warning",
                showCloseButton : false, // 展示关闭按钮
                allowOutsideClick : false,
                showConfirmButton : false,
                timer : 1000
              });
              return;
            }

          }
        }
      });
    }

    function getAustereDevicesTree(callback){

      if(typeof callback !== 'function'){
        return;
      }

      $.ajax({
        type    : "post",
        url     : _ctx + "/view/basedatamanage/eqmanage/austere_devices_tree",
        dataType: "json",
        success: function (result) {
          callback(result);
        }
      });
    }

    function isItRedundantEdit(data) {
      let SysName    = document.forms["deviceFunctionPointFormEdit"].elements["deviceFunctionPointSysNameEdit_hidden"].value;

      if (data == SysName) {
        return 1;
      } else {
        return 0;
      }
    }
    // 添加输入框得到焦点时显示设备类型模态框
    $(
        '#deviceFunctionPointSysNameEdit'
    ).focus(function (obj) {

      inputObj = obj.target;

      var f_sys_name_old = $('#deviceFunctionPointSysNameEdit').val();

      // 回显选中的节点
      if (f_sys_name_old)
      {
        tree.searchPrecise(f_sys_name_old, 'f_sys_name_old');
      }
      $('#tree_device_type_modal_deviceConfiguration_Function_Point').modal('show');
    });

    // 显示编辑模态框事件
    $('#deviceConfiguration_Function_AssociationPoint').on('show.bs.modal', function () {
      modalEditActive = true;
    });

    // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
    $('#tree_device_type_modal_deviceConfiguration_Function_Point_button').click(function () {

      tree.search('tree_device_type_modal_deviceConfiguration_Function_Point_input', 'f_nick_name');
    });

    // 关闭编辑模态框时清空输入框
    $("#deviceConfiguration_Function_AssociationPoint").on('hidden.bs.modal', function(event) {

      inputObj = null;
      modalEditActive = false;
      $('#tree_device_type_modal_deviceConfiguration_Function_Point').modal('hide');

      $('#deviceFunctionPointIdEdit').val('');
      $('#deviceFunctionPointSysNameEdit_hidden') .val('');
      $('#deviceFunctionPointSysNameEdit') .val('');
      $('#deviceFunctionPointCommentsEdit').val('');
      $('#deviceFunctionPointNameEdit').val('');


      // 重置表单
      editValidate.resetForm()

    });

    // 当设备树模态框关闭时折叠设备树、清空搜索框内容
    $('#tree_device_type_modal_deviceConfiguration_Function_Point').on('hide.bs.modal', function () {
      tree.tree.expandAll( false );
      $('#tree_device_type_modal_deviceConfiguration_Function_Point_input').val('');
    })

    /**
     * 关联点位点击事件
     *
     */
    $(document).on('click', '#deviceConfiguration_FunctionTable button.edit', function ()
    {
      deviceFunctionId = $(this).data('id');

      queryPoint({deviceFunctionId}, function (obj)
      {

        var data = obj.data;

        if (!data)
        {
          return;
        }
        $('#deviceFunctionPointIdEdit').val(data.id);
        $('#deviceFunctionPointNameEdit').val(data.name || '');
        $('#deviceFunctionPointSysNameEdit').val(data.sysName || '');
        $('#deviceFunctionPointCommentsEdit').val(data.comments || '');
      });
    });

    function queryPoint(deviceFunctionId,callback) {
      if (typeof callback !== 'function')
      {
        return;
      }

      // deviceFunctionId = deviceFunctionId || {};

      $.ajax({
        type: "GET",
        url: _ctx + "/view/sysmanage/interfaceconfig/deviceConfiguration/queryPoint",
        dataType: "json",
        data: {
          deviceFunctionId:deviceFunctionId.deviceFunctionId,
          deviceId:deviceId
          },
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

    // 修改功能点位输入框验证
    var editValidate = $('#deviceFunctionPointFormEdit').validate({
      rules: {

        deviceFunctionPointNameEdit: {
          required: true,
          maxlength: 50

        },
        deviceFunctionPointValueEdit : {
          required: true,
          maxlength: 50
        },
        deviceFunctionPointSysNameEdit: {
          required: true,
          maxlength: 50
        },
        deviceFunctionPointOrderEdit: {
          required: true,
          maxlength: 50
        },
        deviceFunctionPointCommentsEdit: {
          maxlength: 50

        }
      },
      messages: {
        deviceFunctionPointNameEdit: {
          required: '请输入功能名称'
        },
        deviceFunctionPointValueEdit: {
          required: '请选择点位值'
        },
        deviceFunctionPointSysNameEdit: {
          required: '请输入系统名称'
        },
        deviceFunctionPointOrderEdit: {
          required: '请输入顺序'
        },

      },
      submitHandler: function (formData)
      {
        editPointSubmit(formData);
      }

    });

    // 提交表单信息
    function editPointSubmit(formData)
    {

      if (!formData)
      {
        return;
      }

      //获取表单数据
      var formData = form2js(formData, null, null, null, null, true);

      $.ajax({
        type: 'POST',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/add_update',
        dataType: 'json',
        data: {
          id : $('#deviceFunctionPointIdEdit').val(),
          name: formData.deviceFunctionPointNameEdit,
          deviceId : deviceId,
          deviceFunctionId : deviceFunctionId,
          sysName : formData.deviceFunctionPointSysNameEdit,
          comments: formData.deviceFunctionPointCommentsEdit

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceConfiguration_Function_AssociationPoint').modal('hide');

            swal({
              title: result.msg,// 展示的标题
              text: '', // 内容
              type: 'success',
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            deviceConfiguration.getPagingPage_function(deviceId);
          } else
          {
            swal({
              title: result.msg,// 展示的标题
              text: '', // 内容
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

    function refreshTable(param)
    {
      if (typeof (param) != 'undefined') {
        if (param.id != null) {
          deviceTypeId = param.id
        }
      }
      let pageNum = null;
      if (typeof deviceConfigurationPage != 'undefined') {
        pageNum = deviceConfigurationPage.getPageNum();
      }

      getPagingPage({param,deviceTypeId,pageNum}, function (page)
      {
        showPagingPage('deviceConfigurationPageContainer', page);
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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/queryPage',
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
    function getPagingPage_function(param, callback)
    {

      if (typeof callback !== 'function')
      {
        return;
      }

      param = param || {};

      $.ajax({
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/queryPage_function',
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
    $(document).on('click', '#deviceConfigurationTable button.edit', function ()
    {

      var id = $(this).data('id');

      query({id}, function (obj)
      {
        var data = obj.data;

        if (!data)
        {
          return;
        }
        $('#deviceConfigurationIdEdit').val(id);
        $('#deviceConfigurationNameEdit').val(data.name || '');
        $('#deviceConfigurationDeviceTypeEdit').val(data.deviceTypeId || '');
        $('#deviceConfigurationCodeEdit').val(data.code || '');
        $('#deviceConfigurationSiteEdit').val(data.site || '');
        $('#deviceConfigurationCommentsEdit').val(data.comments || '');
      });
    });

    /**
     * 删除点击事件
     *
     */
    $(document).on('click', '#deviceConfigurationTable button.delete', function ()
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

                if (typeof deviceFunctionPoint != 'undefined') {
                  deviceFunctionPoint.pageInit();
                }

              }else {

                swal({
                  title : "删除失败",// 展示的标题
                  text : result.msg,// 内容
                  type: "error",
                  showCloseButton : false, // 展示关闭按钮
                  allowOutsideClick : false,
                  showConfirmButton : false,
                  timer: 1000
                });
              }
            })
          }
      );

    });

    /**
     * 删除点位的点击事件
     *
     */
    $(document).on('click', '#deviceConfiguration_FunctionTable button.delete', function ()
    {
      var deviceFunctionPointID = $(this).data('id');
      swal
      (
          {
            title: '您确定要删除当前配置的功能点位吗?',
            text: '信息删除后将不可恢复!',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#1ab394',
            confirmButtonText: '确定',
            closeOnConfirm: false
          }, function ()
          {
            deletePoint(deviceFunctionPointID, function (result)
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
                  text : result.msg,// 内容
                  type: "error",
                  showCloseButton : false, // 展示关闭按钮
                  allowOutsideClick : false,
                  showConfirmButton : false,
                  timer: 1000
                });
              }
            })
          }
      );

    });

    // 添加设备配置输入框验证
    var addValidate = $('#deviceConfigurationFormAdd').validate({
      rules: {
        deviceConfigurationNameAdd: {
          required: true,
          maxlength: 50

        },
        deviceConfigurationDeviceTypeAdd: {
          required: true,
          maxlength: 50
        },
        deviceConfigurationCodeAdd: {
          required: true,
          maxlength: 50
        },
        deviceConfigurationSiteAdd: {
          required: true,
          maxlength: 50
        },
        deviceConfigurationCommentsAdd: {
          maxlength: 50

        }
      },
      messages: {
        deviceConfigurationNameAdd: {
          required: '请输入设备名称'
        },
        deviceConfigurationDeviceTypeAdd: {
          required: '请选择设备类型'
        },
        deviceConfigurationCodeAdd: {
          required: '请输入设备代码'
        },
        deviceConfigurationSiteAdd: {
          required: '请输入位置'
        },
      },
      submitHandler: function (formData)
      {
        addSubmit(formData);
      }

    });

    // 修改设备配置输入框验证
    var editValidate = $('#deviceConfigurationFormEdit').validate({
      rules: {

        deviceConfigurationNameEdit: {
          required: true,
          maxlength: 50

        },
        deviceConfigurationDeviceTypeEdit : {
          required: true,
          maxlength: 50
        },
        deviceConfigurationCodeEdit: {
          required: true,
          maxlength: 50
        },
        deviceConfigurationSiteEdit: {
          required: true,
          maxlength: 50
        },
        deviceConfigurationCommentsEdit: {
          maxlength: 50

        }
      },
      messages: {
        deviceConfigurationNameEdit: {
          required: '请输入设备名称'
        },
        deviceConfigurationDeviceTypeEdit: {
          required: '请选择设备类型'
        },
        deviceConfigurationCodeEdit: {
          required: '请输入设备代码'
        },
        deviceConfigurationNameEdit: {
          required: '请输入位置'
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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/create',
        dataType: 'json',
        data: {
          name: formData.deviceConfigurationNameAdd,
          // deviceTypeId : formData.deviceConfigurationDeviceTypeAdd,
          deviceTypeId : deviceTypeId,
          code : formData.deviceConfigurationCodeAdd,
          site : formData.deviceConfigurationSiteAdd,
          comments: formData.deviceConfigurationCommentsAdd

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceConfigurationModalAdd').modal('hide');

            refreshTable();

            if (typeof deviceFunctionPoint != 'undefined') {
              deviceFunctionPoint.pageInit();
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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/update',
        dataType: 'json',
        data: {
          id : $('#deviceConfigurationIdEdit').val(),
          name: formData.deviceConfigurationNameEdit,
          // deviceTypeId : formData.deviceConfigurationDeviceTypeEdit,
          deviceTypeId : deviceTypeId,
          code : formData.deviceConfigurationCodeEdit,
          site : formData.deviceConfigurationSiteEdit,
          comments: formData.deviceConfigurationCommentsEdit

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceConfigurationModalEdit').modal('hide');

            refreshTable();

            if (typeof deviceFunctionPoint != 'undefined') {
              deviceFunctionPoint.pageInit();
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
        url: _ctx + "/view/sysmanage/interfaceconfig/deviceConfiguration/query",
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
        type    : "POST",
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceConfiguration/delete",
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

    // 删除配置的功能点位
    function deletePoint(deviceFunctionPointID, callback) {

      if (!deviceFunctionPointID) {
        console.warn('无效参数！');
        return;
      }

      $.ajax({
        type    : "POST",
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceConfiguration/deletePoint",
        dataType: "json",
        data: {
          deviceFunctionPointID : deviceFunctionPointID,

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
    $('#deviceConfigurationModalAdd').on('hide.bs.modal', function ()
    {


      // 清空表单
      $('#deviceConfigurationNameAdd').val('');
      // $('#deviceConfigurationDeviceTypeAdd').val('');
      $('#deviceConfigurationCodeAdd').val('');
      $('#deviceConfigurationSiteAdd').val('');
      $('#deviceConfigurationCommentsAdd').val('');

      // 重置添加验证表单
      addValidate.resetForm()
    });

    // 编辑主机模态框关闭时处理事件 1、清空表单；2、重置验证表单
    $('#deviceConfigurationModalEdit').on('hide.bs.modal', function ()
    {

      // 清空表单
      $('#deviceConfigurationNameEdit').val('');
      // $('#deviceConfigurationDeviceTypeEdit').val('');
      $('#deviceConfigurationCodeEdit').val('');
      $('#deviceConfigurationSiteEdit').val('');
      $('#deviceConfigurationCommentsEdit').val('');

      // 重置添加验证表单
      editValidate.resetForm()
    });


    // 根据条件搜索
    function search()
    {
      var keywords = $('#DeviceConfigurationKeywords').val();

      refreshTable(keywords);
    }

    function getDeviceConfigurationDeviceType() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/getDeviceType',
        success: function(result){

          var ops="<option value=''>请选择设备类型</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.id+'">';
            ops+=obj.name;
            ops+='</option>';
          }
          $("#deviceConfigurationDeviceTypeAdd").append(ops);
          $("#deviceConfigurationDeviceTypeEdit").append(ops);
        }
      });
    }


    //设备类型下拉框
    function  get_deviceTypetree() {

      $.issp_ajax({
        type: "POST",
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/getDeviceType',
        async:false,
        success: function (data) {

          if (data.hasOwnProperty("list"))
          {
            if(data.list.length > 0 )
            {
              //清空保存的值再赋值
              deviceType_id.length = 0
              deviceType_val.length = 0

              for (var i = 0; i < data.list.length; i++) {
                var obj = data.list[i];
                deviceType_id.push(obj.id);
                deviceType_val.push(obj.name);
              }

              // yqbh = data.list[0].f_yqbh;
              // basedatamanage_enegrycollectionmanage_collMethod.zzjg_tree(yqbh);
              deviceType_select('#deviceConfiguration_sblx',deviceType_id,deviceType_val);
            }
          }
          else
          {
            deviceType_id.push("");
            deviceType_val.push("");
            deviceType_select('#deviceConfiguration_sblx',deviceType_id,deviceType_val);

          }


        },
        error: function (data) {
          swal( data.msg,"", "error");
        }
      });
    }

    //所属园区selected
    function deviceType_select(id,idArray,valArray){
      //选择设备类型下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width:'9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft:'0px',//margin-left属性
        isHasData:true,
        idArray:idArray,//id
        valArray:valArray,//txt
        isNoSelectedText:false, //是否设置未选中提示文本
        liveSearch:false,
        callBack: refreshTable,
      });
    }




    return {
      getPagingPage_function(_deviceId) {
        deviceId = _deviceId;
        getPagingPage_function({deviceId,deviceTypeId}, function (page)
        {

          showPagingPage('deviceConfiguration_FunctionPageContainer', page);
        });

      },
      search,
      pageInit: function () {
        // getDeviceConfigurationDeviceType();
        get_deviceTypetree();
      }
    }

  })(jQuery, window, document);
  deviceConfiguration.pageInit();
</script>