<#--
 功能点位
王红杰
-->
<style>
  #tree_device_type_modal_deviceFunctionPoint {
    position: absolute;
    left:71%;
    top: 11.5%;
  }
</style>

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i> 功能点位列表>>>
		</span>
  <!-- 增加按钮 -->
  <#--<a id="adddeviceFunctionPoint" data-toggle="modal" href="#deviceFunctionPointModalAdd" class="btn btn-primary toLeft">
    <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
  </a>-->
  <a href="javascript:void(-1);" id="adddeviceFunctionPoint" class="btn btn-primary toLeft">
    <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
  </a>

  <div  style = "display:inline-block ;margin-left: 15px;">所属设备 :</div>
  <div id='devicePoint_sblx' style = "display:inline-block ;margin-left: 15px;"></div>
  <div  style = "display:inline-block ;margin-left: 15px;">所属功能 :</div>
  <div id='deviceFunctionPoint_sblx' style = "display:inline-block ;margin-left: 15px;"></div>

  <!-- 搜索框 -->
  <div class="zc_search find">
    <input type="text" class="find-style" id="deviceFunctionPointKeywords" name="deviceFunctionPointKeywords"
           placeholder="名称">
    <button id="querydeviceFunctionPoint" onclick="deviceFunctionPoint.search()">
      <i class="fa fa-search" aria-hidden="true"></i>
    </button>
  </div>
</div>


<!---分页列表----->
<div class="ibox" id="deviceFunctionPointPageContainer" style="height:92%"></div>

<!---添加功能点位开始----->
<div class="modal fade" id="deviceFunctionPointModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" >
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 700px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp;添加功能点位</h4>
      </div>
      <div class="modal-body">
        <form  id="deviceFunctionPointFormAdd" name="deviceFunctionPointFormEdit" class="form-horizontal">
          <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">
            <div class="form-group">
              <label class="col-sm-3 control-label">系统名称<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                  <#--              <input type="text" id="deviceFunctionPointSysNameAdd" name="deviceFunctionPointSysNameAdd" &lt;#&ndash;placeholder="请输入值"&ndash;&gt;-->
                  <#--                     class="form-control">-->

                <input id="deviceFunctionPointSysNameAdd_hidden"
                       name="deviceFunctionPointSysNameAdd_hidden"
                       type="hidden"
                >
                <input type="text"
                       id="deviceFunctionPointSysNameAdd"
                       name="deviceFunctionPointSysNameAdd"
                       class="form-control"
                       placeholder="请配置系统名称"
                >
              </div>
            </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">功能点位名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionPointNameAdd" name="deviceFunctionPointNameAdd" placeholder="请输入功能点位名称"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">功能点位值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionPointValueAdd" name="deviceFunctionPointValueAdd" <#--placeholder="请输入值"-->
                     class="form-control">
            </div>
          </div>

            <div class="form-group">
              <label class="col-sm-3 control-label">顺序<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                <input type="text" id="deviceFunctionPointOrderAdd" name="deviceFunctionPointOrderAdd" <#--placeholder="请输入值"-->
                       class="form-control">
              </div>
            </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">是否复位键<span class="text-danger">*</span></label>
            <div class="col-sm-8">
                <#-- <input type="text" id="deviceFunctionPointTypeAdd" name="deviceFunctionPointTypeAdd" placeholder="设备功能类型"
                        class="form-control">-->
              <select id="deviceFunctionPointIsResetKeyAdd" name="deviceFunctionPointIsResetKeyAdd" required class="form-control">
                <option value="0">否</option>
                <option value="1">是</option>
              </select>

            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionPointCommentsAdd" name="deviceFunctionPointCommentsAdd"
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
<!-- 添加功能点位结束 -->

<!----编辑功能点位--->
<div class="modal fade" id="deviceFunctionPointModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" >
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 700px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;编辑功能点位</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionPointFormEdit" name="deviceFunctionPointFormEdit" class="form-horizontal">
          <div  style="height: 70%;overflow-x: hidden;overflow-y: auto;">
          <div class="form-group">
            <label class="col-sm-3 control-label">系统名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
                <#--              <input type="text" id="deviceFunctionPointSysNameEdit" name="deviceFunctionPointSysNameEdit" &lt;#&ndash;placeholder="请输入值"&ndash;&gt;-->
                <#--                     class="form-control">-->

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
            <label class="col-sm-3 control-label">功能点位值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionPointValueEdit" name="deviceFunctionPointValueEdit" <#--placeholder="请输入值"-->
                     class="form-control">
            </div>
          </div>

            <div class="form-group">
              <label class="col-sm-3 control-label">顺序<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                <input type="text" id="deviceFunctionPointOrderEdit" name="deviceFunctionPointOrderEdit"
                       class="form-control">
              </div>
            </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">是否复位键<span class="text-danger">*</span></label>
            <div class="col-sm-8">
                <#-- <input type="text" id="deviceFunctionPointTypeEdit" name="deviceFunctionPointTypeEdit" placeholder="设备功能类型"
                        class="form-control">-->
              <select id="deviceFunctionPointIsResetKeyEdit" name="deviceFunctionPointIsResetKeyEdit" required class="form-control">
                <option value="0">否</option>
                <option value="1">是</option>
              </select>

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
<div class="modal fade" id="tree_device_type_modal_deviceFunctionPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 350px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
      </div>
      <div class="modal-body" style="height: 450px">
        <div style="position: relative" class="zc_search find">
          <input type="text" class="find-style" id="tree_device_type_search_deviceFunctionPoint_input" name="eqTypeInfo" placeholder="设备类型">
          <button id="tree_device_type_search_deviceFunctionPoint_button"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>

          <#--设备类型树-->
        <div id="tree_device_type_deviceFunctionPoint" class="Information_area ztree"></div>

      </div>
    </div><!-- end modal-content -->
  </div>
</div> <!-- end addParkBasicInfo -->


<script type="text/javascript">
  ;
  var deviceFunctionPoint = (function ($, window, document, undefined)
  {
    var _ctx = '${ctx}';

    var device_id=[];//设备下拉表id
    var device_val=[];//设备下拉表值
    var deviceFunction_id=[];//设备下拉表id
    var deviceFunction_val=[];//设备下拉表值
    var deviceId;//设备id
    var deviceFunctionId;//设备功能id
    var deviceType;//设备所属的设备类型
    var deviceObj = {};//存入所有的设备信息
    var inputObj; // 模态框内输入框保存对象
    var tree;// 树对象
    var modalAddActive = false; // 保存添加模态框是否是显示状态 true 显示 | false 隐藏
    var modalEditActive = false; // 保存编辑模态框是否是显示状态 true 显示 | false 隐藏
    var add_or_edit = 1;
    var treeCallback = function () {}; // 点击树节点回调回调

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
        container: 'tree_device_type_deviceFunctionPoint',
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

            var  cid = node.cid;
            if (add_or_edit == 1){//添加的时候
              let add = isItRedundantAdd(cid);//这一步可以不使用,在多个点位的时候使用
              if (add == 0) {//添加的点位不重复
                inputObj.value = node.f_sys_name_old;
                // $(inputObj).prev().val(node.f_sys_name);
                $("#deviceFunctionPointNameAdd").val(node.f_nick_name);
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

            }else if (add_or_edit == 2){//修改的时候
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

    function isItRedundantAdd(data) {

      let SysName    = document.forms["deviceFunctionPointFormAdd"].elements["deviceFunctionPointSysNameAdd_hidden"].value;

      if (data == SysName) {
        return 1;
      } else {
        return 0;
      }
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
        '#deviceFunctionPointSysNameAdd, '    + '#deviceFunctionPointSysNameEdit'
    ).focus(function (obj) {

      inputObj = obj.target;

      var f_sys_name_old = $('#deviceFunctionPointSysNameEdit').val();

      // 回显选中的节点
      if (f_sys_name_old)
      {
        tree.searchPrecise(f_sys_name_old, 'f_sys_name_old');
      }
      $('#tree_device_type_modal_deviceFunctionPoint').modal('show');
    });


    // 显示添加模态框
    $('#adddeviceFunctionPoint').click(function () {
      add_or_edit = 1;
      $('#deviceFunctionPointModalAdd').modal('show');
      modalAddActive = true;
    });
    // 显示编辑模态框事件
    $('#deviceFunctionPointModalEdit').on('show.bs.modal', function () {
      add_or_edit = 2;
      modalEditActive = true;
    });

    //触发搜索的回车事件， 以系统别名搜索设备树展开并以红色字体标注
    $('#tree_device_type_search_deviceFunctionPoint_input').keydown(function (e){
      if(e.which !== 13){
        return;
      }

      tree.search('tree_device_type_search_deviceFunctionPoint_input', 'f_nick_name');

    });

    // 触发搜索点击事件， 以系统别名搜索设备树展开并以红色字体标注
    $('#tree_device_type_search_deviceFunctionPoint_button').click(function () {

      tree.search('tree_device_type_search_deviceFunctionPoint_input', 'f_nick_name');
    });

    // 当添加模态框关闭时同时关闭设备类型模态框, 清空表单数据
    $('#deviceFunctionPointModalAdd').on('hide.bs.modal', function () {
      modalAddActive = false;
      inputObj = null;
      $('#tree_device_type_modal_deviceFunctionPoint').modal('hide');
      $('#deviceFunctionPointNameAdd').val('');
      $('#deviceFunctionPointValueAdd').val('');
      $('#deviceFunctionPointSysNameAdd_hidden') .val('');
      $('#deviceFunctionPointSysNameAdd') .val('');
      $('#deviceFunctionPointIsResetKeyAdd').val('0');
      $('#deviceFunctionPointOrderAdd').val('');
      $('#deviceFunctionPointCommentsAdd').val('');
      // 重置添加验证表单
      addValidate.resetForm()
    });

    // 关闭编辑模态框时清空输入框
    $("#deviceFunctionPointModalEdit").on('hidden.bs.modal', function(event) {

      inputObj = null;
      modalEditActive = false;
      $('#tree_device_type_modal_deviceFunctionPoint').modal('hide');

      $('#deviceFunctionPointIdEdit').val('');
      $('#deviceFunctionPointValueEdit').val('');
      $('#deviceFunctionPointSysNameEdit_hidden') .val('');
      $('#deviceFunctionPointSysNameEdit') .val('');
      $('#deviceFunctionPointIsResetKeyEdit').val('');
      $('#deviceFunctionPointOrderEdit').val('');
      $('#deviceFunctionPointCommentsEdit').val('');

      // 重置表单
      editValidate.resetForm()

    });


    // 当设备树模态框关闭时折叠设备树、清空搜索框内容
    $('#tree_device_type_modal_deviceFunctionPoint').on('hide.bs.modal', function () {
      tree.tree.expandAll( false );
      $('#tree_device_type_search_deviceFunctionPoint_input').val('');
    })

    // 控制点位输入框得到焦点时显示 tree 模态框（主机）
    $('#deviceFunctionPointSysNameAdd').focus(function (obj) {

      // treeCallback = function(node)
      // {
      //   // 主机关联点处理
      //   hostJoinPointHandle(node, obj.target);
      //
      // };

      var f_sys_name_old = $('#deviceFunctionPointNameAdd').val();

      // 回显选中的节点
      if (f_sys_name_old)
      {
        tree.searchPrecise(f_sys_name_old, 'f_sys_name_old');
      }


      // $('#host_linkage_tree_modal').modal('show');
    });
    // refreshTable();

    function refreshTable(param)
    {

      if (typeof (param) == 'undefined') {

        getPagingPage({param,deviceId}, function (page)
        {
          showPagingPage('deviceFunctionPointPageContainer', page);
        });
      } else {
        if (param.id != null) {
          deviceId = param.id
        }
        getPagingPage({param,deviceId}, function (page)
        {
          showPagingPage('deviceFunctionPointPageContainer', page);
        });
      }

    }

    function refreshTableFunction(param)
    {

      if (typeof (param) == 'undefined') {

        getPagingPage({param,deviceId,deviceFunctionId}, function (page)
        {
          showPagingPage('deviceFunctionPointPageContainer', page);
        });
      } else {
        if (param.id != null) {
          deviceFunctionId = param.id
        }
        getPagingPage({param,deviceId,deviceFunctionId}, function (page)
        {
          showPagingPage('deviceFunctionPointPageContainer', page);
        });
      }

    }

    function refreshTableDevice(param)
    {

      if (typeof (param) == 'undefined') {
        if (typeof deviceFunctionId != 'undefined') {
         /* getPagingPage({param,deviceId,deviceFunctionId}, function (page)
          {
            showPagingPage('deviceFunctionPointPageContainer', page);
          });*/
        }

      } else {
        if (param.id != null) {
          deviceId = param.id
        }
        let deviceTypeId;

        for (let i = 0; i < deviceObj.list.length; i++) {
          if (deviceId == deviceObj.list[i].id) {
            deviceTypeId = deviceObj.list[i].deviceTypeId;
          }
        }
        get_deviceFunctiontree(deviceId,deviceTypeId);
        if (typeof deviceFunctionId != 'undefined') {
          /*getPagingPage({param,deviceId,deviceFunctionId}, function (page)
          {
            showPagingPage('deviceFunctionPointPageContainer', page);
          });*/
        }

      }

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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/queryPage',
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
    $(document).on('click', '#deviceFunctionPointTable button.edit', function ()
    {

      var id = $(this).data('id');

      query({id}, function (obj)
      {
        var data = obj.data;

        if (!data)
        {
          return;
        }
        $('#deviceFunctionPointIdEdit').val(id);
        $('#deviceFunctionPointNameEdit').val(data.name || '');
        $('#deviceFunctionPointValueEdit').val(data.value || '');
        $('#deviceFunctionPointSysNameEdit').val(data.sysName || '');
        $('#deviceFunctionPointOrderEdit').val(data.order.toString());
        $('#deviceFunctionPointIsResetKeyEdit').val(data.isResetKey.toString());
        $('#deviceFunctionPointCommentsEdit').val(data.comments || '');
      });
    });

    /**
     * 删除点击事件
     *
     */
    $(document).on('click', '#deviceFunctionPointTable button.delete', function ()
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

                refreshTableFunction();



              }else {

                swal("删除失败!",null,"error");
              }
            })
          }
      );

    });

    // 添加功能点位输入框验证
    var addValidate = $('#deviceFunctionPointFormAdd').validate({
      rules: {
        deviceFunctionPointNameAdd: {
          required: true,
          maxlength: 50

        },
        deviceFunctionPointValueAdd: {
          required: true,
          maxlength: 50
        },
        deviceFunctionPointSysNameAdd: {
          required: true,
          maxlength: 50
        },
        deviceFunctionPointOrderAdd: {
          required: true,
          maxlength: 50
        }

      },
      messages: {
        deviceFunctionPointNameAdd: {
          required: '请输入设备名称'
        },
        deviceFunctionPointValueAdd: {
          required: '请输入点位置'
        },
        deviceFunctionPointSysNameAdd: {
          required: '请输入系统名称'
        },
        deviceFunctionPointOrderAdd: {
          required: '请输入顺序'
        }
      },
      submitHandler: function (formData)
      {
        addSubmit(formData);
      }

    });

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

      //判断所属功能是否为空
      if (deviceFunctionId == "") {
        swal({
          title: '添加失败',// 展示的标题
          text: "请配置当前设备的设备类型所关联的功能", // 内容
          type: 'warning',
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 2000
        });
        return;
      }
      if(isNaN(formData.deviceFunctionPointOrderAdd)){
        swal({
          title: '添加失败',// 展示的标题
          text: "顺序请输入整数", // 内容
          type: 'warning',
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 2000
        });
        return;
      }

      $.ajax({
        type: 'POST',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/create',
        dataType: 'json',
        data: {
          name: formData.deviceFunctionPointNameAdd,
          // deviceTypeId : formData.deviceFunctionPointDeviceTypeAdd,
          deviceId : deviceId,
          deviceFunctionId : deviceFunctionId,
          value : formData.deviceFunctionPointValueAdd,
          sysName : formData.deviceFunctionPointSysNameAdd,
          order : formData.deviceFunctionPointOrderAdd,
          isResetKey : formData.deviceFunctionPointIsResetKeyAdd,
          comments: formData.deviceFunctionPointCommentsAdd

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionPointModalAdd').modal('hide');

            refreshTableFunction();

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
      if(isNaN(formData.deviceFunctionPointOrderEdit)){
        swal({
          title: '修改失败',// 展示的标题
          text: "顺序请输入整数", // 内容
          type: 'warning',
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 2000
        });
        return;
      }

      $.ajax({
        type: 'POST',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/update',
        dataType: 'json',
        data: {
          id : $('#deviceFunctionPointIdEdit').val(),
          name: formData.deviceFunctionPointNameEdit,
          deviceId : deviceId,
          deviceFunctionId : deviceFunctionId,
          sysName : formData.deviceFunctionPointSysNameEdit,
          order : formData.deviceFunctionPointOrderEdit,
          isResetKey : formData.deviceFunctionPointIsResetKeyEdit,
          value : formData.deviceFunctionPointValueEdit,
          comments: formData.deviceFunctionPointCommentsEdit

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionPointModalEdit').modal('hide');

            swal({
              title: '编辑成功',// 展示的标题
              text: '', // 内容
              type: 'success',
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            refreshTableFunction();
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
        url: _ctx + "/view/sysmanage/interfaceconfig/deviceFunctionPoint/query",
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
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceFunctionPoint/delete",
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

    /*// 添加功能点位模态框关闭时处理事件 1、清空表单；2、重置验证表单
    $('#deviceFunctionPointModalAdd').on('hide.bs.modal', function ()
    {


      // 清空表单
      $('#deviceFunctionPointNameAdd').val('');
      $('#deviceFunctionPointDeviceTypeAdd').val('');
      $('#deviceFunctionPointCodeAdd').val('');
      $('#deviceFunctionPointTypeAdd').val('1');
      $('#deviceFunctionPointValueAdd').val('');
      $('#deviceFunctionPointCommentsAdd').val('');

      // 重置添加验证表单
      addValidate.resetForm()
    });

    // 编辑功能点位模态框关闭时处理事件 1、清空表单；2、重置验证表单
    $('#deviceFunctionPointModalEdit').on('hide.bs.modal', function ()
    {

      // 清空表单
      $('#deviceFunctionPointNameEdit').val('');
      $('#deviceFunctionPointDeviceTypeEdit').val('');
      $('#deviceFunctionPointCodeEdit').val('');
      $('#deviceFunctionPointTypeEdit').val('');
      $('#deviceFunctionPointValueEdit').val('');
      $('#deviceFunctionPointCommentsEdit').val('');

      // 重置添加验证表单
      editValidate.resetForm()
    });*/


    // 根据条件搜索
    function search()
    {
      var keywords = $('#deviceFunctionPointKeywords').val();

      refreshTable(keywords);
    }

    function getdeviceFunctionPointDeviceType() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/getDevice',
        success: function(result){

          var ops="<option value=''>请选择设备类型</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.id+'">';
            ops+=obj.name;
            ops+='</option>';
          }
          $("#deviceFunctionPointDeviceTypeAdd").append(ops);
          $("#deviceFunctionPointDeviceTypeEdit").append(ops);
        }
      });
    }


    //设备下拉框
    function  get_devicetree() {

      $.issp_ajax({
        type: "POST",
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/getDevice',
        async:false,
        success: function (data) {

          if (data.hasOwnProperty("list"))
          {
            if(data.list.length > 0 )
            {
              //清空保存的值再赋值
              device_id.length = 0
              device_val.length = 0

              for (var i = 0; i < data.list.length; i++) {
                var obj = data.list[i];
                device_id.push(obj.id);
                device_val.push(obj.name);
              }
              deviceObj = data;//存入所有的设备信息
              let deviceTypeId = data.list[0].deviceTypeId;
              let deviceid = data.list[0].id;
              get_deviceFunctiontree(deviceid,deviceTypeId);
              device_select('#devicePoint_sblx',device_id,device_val);
            }
          }
          else
          {
            device_id.push("");
            device_val.push("");
            device_select('#devicePoint_sblx',device_id,device_val);

          }


        },
        error: function (data) {
          swal( data.msg,"", "error");
        }
      });
    }

    //设备selected
    function device_select(id,idArray,valArray){
      //选择设备下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width:'9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft:'0px',//margin-left属性
        isHasData:true,
        idArray:idArray,//id
        valArray:valArray,//txt
        isNoSelectedText:false, //是否设置未选中提示文本
        liveSearch:false,
        callBack: refreshTableDevice,
      });
    }

    //设备功能下拉框
    function  get_deviceFunctiontree(deviceid,deviceTypeId) {

      $.issp_ajax({
        type: "POST",
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunctionPoint/getDeviceFunction',
        async:false,
        data : {
          deviceTypeId : deviceTypeId
        },
        success: function (data) {

          if (data.hasOwnProperty("list"))
          {
            if(data.list.length > 0 )
            {
              //清空保存的值再赋值
              deviceFunction_id.length = 0;
              deviceFunction_val.length = 0;

              for (var i = 0; i < data.list.length; i++) {
                var obj = data.list[i];
                deviceFunction_id.push(obj.id);
                deviceFunction_val.push(obj.name);
              }


              // getDiv("deviceFunctionPoint_sblx");
              deviceId = deviceid
              // yqbh = data.list[0].f_yqbh;
              // basedatamanage_enegrycollectionmanage_collMethod.zzjg_tree(yqbh);
                deviceFunction_Function('#deviceFunctionPoint_sblx',deviceFunction_id,deviceFunction_val);
            }
          } else
          {
            deviceFunction_id.length = 0;
            deviceFunction_val.length = 0;
            deviceFunction_Function('#deviceFunctionPoint_sblx',deviceFunction_id,deviceFunction_val);

          }


        },
        error: function (data) {
          swal( data.msg,"", "error");
        }
      });
    }

    //设备功能elected
    function deviceFunction_Function(id,idArray,valArray){
      //选择设备下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width:'9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft:'0px',//margin-left属性
        isHasData:true,
        idArray:idArray,//id
        valArray:valArray,//txt
        isNoSelectedText:false, //是否设置未选中提示文本
        liveSearch:false,
        callBack: refreshTableFunction,
      });
    }

    // 根据条件搜索
    function search()
    {
      var keywords = $('#DeviceConfigurationKeywords').val();

      refreshTable(keywords);
    }

    return {
      // search,
      search : function() {
        var keywords = $('#deviceFunctionPointKeywords').val();

        refreshTableFunction(keywords);
      },
      pageInit: function () {
        // getdeviceFunctionPointDeviceType();
        get_devicetree();
        // get_deviceFunctiontree();

      }
    }

  })(jQuery, window, document);
  deviceFunctionPoint.pageInit();
</script>