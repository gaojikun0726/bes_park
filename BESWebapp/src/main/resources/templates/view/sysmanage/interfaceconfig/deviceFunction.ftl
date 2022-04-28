<#--
 设备功能
王红杰
-->
<style>
  .information_left_deviceFunction{
    /* margin-top: -10px; */
    height: 95% !important;
    border-bottom: solid 1px #366c90;
    border-top: solid 1px #366c90;
    border-right: solid 1px #366c90;
  }
  .leftarea_deviceFunction{
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
  .information_right_deviceFunction{
    float: right;
    height: 95%;
    width: 50%;
    position: relative;
    /* margin-top: -10px; */
    border: solid 0.5px #366c90;
  }
</style>

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i> 设备功能列表>>>
		</span>
  <!-- 增加按钮 -->
  <a id="adddeviceFunction" data-toggle="modal" href="#deviceFunctionModalAdd" class="btn btn-primary toLeft">
    <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
  </a>

  <div  style = "display:inline-block ;margin-left: 15px;">设备类型 :</div>
  <div id='deviceFunction_sblx' style = "display:inline-block ;margin-left: 15px;"></div>

  <!-- 搜索框 -->
  <div class="zc_search find">
    <input type="text" class="find-style" id="deviceFunctionKeywords" name="deviceFunctionKeywords"
           placeholder="设备功能名称">
    <button id="querydeviceFunction" onclick="deviceFunction.search()">
      <i class="fa fa-search" aria-hidden="true"></i>
    </button>
  </div>
</div>


<!---分页列表----->
<div class="ibox leftarea_deviceFunction information_left_deviceFunction" id="deviceFunctionPageContainer"></div>
<div class="ibox information_right_deviceFunction" style="clear: initial" id="deviceFunctionValuePageContainer" ></div>

<!---添加设备功能开始----->
<div class="modal fade" id="deviceFunctionModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp;添加设备功能</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionFormAdd" name="deviceFunctionFormEdit" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">设备功能名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionNameAdd" name="deviceFunctionNameAdd" placeholder="请输入设备功能名称"
                     class="form-control">
            </div>
          </div>
            <#--<div class="form-group">
              <label class="col-sm-3 control-label">设备功能类型<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                <select id="deviceFunctionDeviceTypeAdd" name="deviceFunctionDeviceTypeAdd" required class="form-control">

                </select>
              </div>
            </div>-->
          <div class="form-group">
            <label class="col-sm-3 control-label">设备功能代码<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionCodeAdd" name="deviceFunctionCodeAdd" placeholder="请输入设备功能代码"
                     class="form-control">
            </div>
          </div>
            <#-- <div class="form-group">
               <label class="col-sm-3 control-label">设备功能类型<span class="text-danger">*</span></label>
               <div class="col-sm-8">
                &lt;#&ndash; <input type="text" id="deviceFunctionTypeAdd" name="deviceFunctionTypeAdd" placeholder="设备功能类型"
                        class="form-control">&ndash;&gt;
                 <select id="deviceFunctionTypeAdd" name="deviceFunctionTypeAdd" required class="form-control">
                   <option value="1">AO</option>
                   <option value="2">AI</option>
                   <option value="3">DO</option>
                   <option value="4">DI</option>
                 </select>

               </div>
             </div>-->
            <div class="form-group">
              <label class="col-sm-3 control-label">单位</label>
              <div class="col-sm-8">
                <input type="text" id="deviceFunctionUnitAdd" name="deviceFunctionUnitAdd" placeholder="请输入单位"
                       class="form-control">
              </div>
            </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionCommentsAdd" name="deviceFunctionCommentsAdd"
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
<!-- 添加设备功能结束 -->

<!---添加设备功能值开始----->
<div class="modal fade" id="deviceFunctionValueModalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp;添加设备功能值</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionValueFormAdd" name="deviceFunctionValueFormAdd" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionValueNameAdd" name="deviceFunctionValueNameAdd" placeholder="请输入名称"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">功能值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionValueValueAdd" name="deviceFunctionValueValueAdd" placeholder="请输入功能值"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionValueCommentsAdd" name="deviceFunctionValueCommentsAdd"
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
<!-- 添加设备功能值结束 -->

<!----编辑设备功能--->
<div class="modal fade" id="deviceFunctionModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;编辑设备功能</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionFormEdit" name="deviceFunctionFormEdit" class="form-horizontal">

          <div class="form-group">
            <label class="col-sm-3 control-label">设备功能名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <div type="hidden" id="deviceFunctionIdEdit" name="deviceFunctionIdEdit"></div>
              <input type="text" id="deviceFunctionNameEdit" name="deviceFunctionNameEdit" placeholder="请输入设备功能名称"
                     class="form-control">
            </div>
          </div>
         <#-- <div class="form-group">
            <label class="col-sm-3 control-label">设备功能类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="deviceFunctionDeviceTypeEdit" name="deviceFunctionDeviceTypeEdit"  class="form-control">

              </select>
            </div>
          </div>-->
          <div class="form-group">
            <label class="col-sm-3 control-label">设备功能代码<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionCodeEdit" name="deviceFunctionCodeEdit" placeholder="请输入设备功能代码"
                     class="form-control">
            </div>
          </div>
         <#-- <div class="form-group">
            <label class="col-sm-3 control-label">设备功能类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
                &lt;#&ndash; <input type="text" id="deviceFunctionTypeEdit" name="deviceFunctionTypeEdit" placeholder="设备功能类型"
                        class="form-control">&ndash;&gt;
              <select id="deviceFunctionTypeEdit" name="deviceFunctionTypeEdit"  class="form-control">
                <option value="1">AO</option>
                <option value="2">AI</option>
                <option value="3">DO</option>
                <option value="4">DI</option>
              </select>

            </div>
          </div>-->
          <div class="form-group">
            <label class="col-sm-3 control-label">单位</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionUnitEdit" name="deviceFunctionUnitEdit" placeholder="请输入单位"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionCommentsEdit" name="deviceFunctionCommentsEdit"
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
<!----编辑设备功能结束--->

<!----编辑设备功能值--->
<div class="modal fade" id="deviceFunctionValueModalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;编辑设备功能值</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="deviceFunctionValueFormEdit" name="deviceFunctionValueFormEdit" class="form-horizontal">

          <div class="form-group">
            <label class="col-sm-3 control-label">名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <div type="hidden" id="deviceFunctionValueIdEdit" name="deviceFunctionValueIdEdit"></div>
              <input type="text" id="deviceFunctionValueNameEdit" name="deviceFunctionValueNameEdit" placeholder="请输入名称"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionValueValueEdit" name="deviceFunctionValueValueEdit" placeholder="请输入值"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-8">
              <input type="text" id="deviceFunctionValueCommentsEdit" name="deviceFunctionValueCommentsEdit"
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
<!----编辑设备功能值结束--->

<script type="text/javascript">
  ;
  var deviceFunction = (function ($, window, document, undefined)
  {
    var _ctx = '${ctx}';

    var deviceType_id=[];//设备类型下拉表id
    var deviceType_val=[];//设备类型下拉表值
    var deviceTypeId;
    var deviceFunctionId;//功能id


    // refreshTable();

    function refreshTable(param)
    {

      let pageNum = null;
      if (typeof deviceFunctionPage != 'undefined') {
        pageNum = deviceFunctionPage.getPageNum();
      }

      if (typeof (param) == 'undefined') {

        getPagingPage({param,deviceTypeId,pageNum}, function (page)
        {
          showPagingPage('deviceFunctionPageContainer', page);
        });
      } else {
        if (param.id != null) {
          deviceTypeId = param.id
        }
        getPagingPage({param,deviceTypeId,pageNum}, function (page)
        {
          showPagingPage('deviceFunctionPageContainer', page);
        });
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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/queryPage',
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

    // 获取分页页面
    function getPagingPage_value(param, callback)
    {

      if (typeof callback !== 'function')
      {
        return;
      }

      param = param || {};

      $.ajax({
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/query_valuePage',
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
    $(document).on('click', '#deviceFunctionTable button.edit', function ()
    {

      var id = $(this).data('id');

      query({id}, function (obj)
      {
        var data = obj.data;

        if (!data)
        {
          return;
        }
        $('#deviceFunctionIdEdit').val(id);
        $('#deviceFunctionNameEdit').val(data.name || '');
        $('#deviceFunctionDeviceTypeEdit').val(data.deviceTypeId || '');
        $('#deviceFunctionCodeEdit').val(data.code || '');
        $('#deviceFunctionTypeEdit').val(data.type || '');
        $('#deviceFunctionUnitEdit').val(data.unit || '');
        $('#deviceFunctionCommentsEdit').val(data.comments || '');
      });
    });

    /**
     * 删除点击事件
     *
     */
    $(document).on('click', '#deviceFunctionTable button.delete', function ()
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

    // 添加设备功能输入框验证
    var addValidate = $('#deviceFunctionFormAdd').validate({
      rules: {
        deviceFunctionNameAdd: {
          required: true,
          maxlength: 50

        },
        deviceFunctionDeviceTypeAdd: {
          required: true,
          maxlength: 50
        },
        deviceFunctionCodeAdd: {
          required: true,
          maxlength: 50
        },
        deviceFunctionTypeAdd: {
          required: true,
          maxlength: 50
        },
        deviceFunctionCommentsAdd: {
          maxlength: 50

        }
      },
      messages: {
        deviceFunctionNameAdd: {
          required: '请输入设备名称'
        },
        deviceFunctionDeviceTypeAdd: {
          required: '请选择设备类型'
        },
        deviceFunctionCodeAdd: {
          required: '请输入设备代码'
        },
        deviceFunctionTypeAdd: {
          required: '请选择类型'
        },
      },
      submitHandler: function (formData)
      {
        addSubmit(formData);
      }

    });

    // 修改设备功能输入框验证
    var editValidate = $('#deviceFunctionFormEdit').validate({
      rules: {

        deviceFunctionNameEdit: {
          required: true,
          maxlength: 50

        },
        deviceFunctionDeviceTypeEdit : {
          required: true,
          maxlength: 50
        },
        deviceFunctionCodeEdit: {
          required: true,
          maxlength: 50
        },
        deviceFunctionTypeEdit: {
          required: true,
          maxlength: 50
        },
        deviceFunctionCommentsEdit: {
          maxlength: 50

        }
      },
      messages: {
        deviceFunctionNameEdit: {
          required: '请输入功能名称'
        },
        deviceFunctionDeviceTypeEdit: {
          required: '请选择功能类型'
        },
        deviceFunctionCodeEdit: {
          required: '请输入功能代码'
        },
        deviceFunctionTypeEdit: {
          required: '请选择类型'
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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/create',
        dataType: 'json',
        data: {
          name: formData.deviceFunctionNameAdd,
          // deviceTypeId : formData.deviceFunctionDeviceTypeAdd,
          deviceTypeId : deviceTypeId,
          code : formData.deviceFunctionCodeAdd,
          type : formData.deviceFunctionTypeAdd,
          unit : formData.deviceFunctionUnitAdd,
          comments: formData.deviceFunctionCommentsAdd

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionModalAdd').modal('hide');

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
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/update',
        dataType: 'json',
        data: {
          id : $('#deviceFunctionIdEdit').val(),
          name: formData.deviceFunctionNameEdit,
          // deviceTypeId : formData.deviceFunctionDeviceTypeEdit,
          deviceTypeId : deviceTypeId,
          code : formData.deviceFunctionCodeEdit,
          type : formData.deviceFunctionTypeEdit,
          unit : formData.deviceFunctionUnitEdit,
          comments: formData.deviceFunctionCommentsEdit

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionModalEdit').modal('hide');

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
        url: _ctx + "/view/sysmanage/interfaceconfig/deviceFunction/query",
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
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceFunction/delete",
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

    // 添加设备功能模态框关闭时处理事件 1、清空表单；2、重置验证表单
    $('#deviceFunctionModalAdd').on('hide.bs.modal', function ()
    {
      // 清空表单
      $('#deviceFunctionNameAdd').val('');
      $('#deviceFunctionDeviceTypeAdd').val('');
      $('#deviceFunctionCodeAdd').val('');
      $('#deviceFunctionTypeAdd').val('1');
      $('#deviceFunctionUnitAdd').val('');
      $('#deviceFunctionCommentsAdd').val('');

      // 重置添加验证表单
      addValidate.resetForm()
    });
    $('#deviceFunctionValueModalAdd').on('hide.bs.modal', function ()
    {
      // 清空表单
      $('#deviceFunctionValueNameAdd').val('');
      $('#deviceFunctionValueValueAdd').val('');
      $('#deviceFunctionCommentsAdd').val('');

      // 重置添加验证表单
      addValueValidate.resetForm()
    });

    // 编辑设备功能模态框关闭时处理事件 1、清空表单；2、重置验证表单
    $('#deviceFunctionModalEdit').on('hide.bs.modal', function ()
    {

      // 清空表单
      $('#deviceFunctionNameEdit').val('');
      $('#deviceFunctionDeviceTypeEdit').val('');
      $('#deviceFunctionCodeEdit').val('');
      $('#deviceFunctionTypeEdit').val('');
      $('#deviceFunctionUnitEdit').val('');
      $('#deviceFunctionCommentsEdit').val('');

      // 重置添加验证表单
      editValidate.resetForm()
    });

    $('#deviceFunctionValueModalEdit').on('hide.bs.modal', function ()
    {

      // 清空表单
      $('#deviceFunctionValueNameEdit').val('');
      $('#deviceFunctionValueValueEdit').val('');
      $('#deviceFunctionValueCommentsEdit').val('');

      // 重置添加验证表单
      editValueValidate.resetForm()
    });

// 添加设备功能输入框验证
    var addValueValidate = $('#deviceFunctionValueFormAdd').validate({
      rules: {
        deviceFunctionValueNameAdd: {
          required: true,
          maxlength: 50

        },
        deviceFunctionValueValueAdd: {
          number:true,
          required: true,
          maxlength: 50
        },
        deviceFunctionValueCommentsAdd: {
          maxlength: 50

        }
      },
      messages: {
        deviceFunctionValueNameAdd: {
          required: '请输入名称'
        },
        deviceFunctionValueValueAdd: {
          required: '请输入值'
        },
      },
      submitHandler: function (formData)
      {
        addValueSubmit(formData);
      }

    });

    // 修改设备功能输入框验证
    var editValueValidate = $('#deviceFunctionValueFormEdit').validate({
      rules: {
        deviceFunctionValueNameEdit: {
          required: true,
          maxlength: 50

        },
        deviceFunctionValueValueEdit: {
          number:true,
          required: true,
          maxlength: 50
        },
        deviceFunctionValueCommentsEdit: {
          maxlength: 50

        }
      },
      messages: {
        deviceFunctionValueNameEdit: {
          required: '请输入名称'
        },
        deviceFunctionValueValueEdit: {
          required: '请输入值'
        },
      },
      submitHandler: function (formData)
      {
        editValueSubmit(formData);
      }

    });

    // 提交表单信息
    function addValueSubmit(formData)
    {

      if (!formData)
      {
        return;
      }

      //获取表单数据
      var formData = form2js(formData, null, null, null, null, true);

      $.ajax({
        type: 'POST',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/create_value',
        dataType: 'json',
        data: {
          name: formData.deviceFunctionValueNameAdd,
          // deviceTypeId : formData.deviceFunctionDeviceTypeAdd,
          deviceFunctionId : deviceFunctionId,
          value : formData.deviceFunctionValueValueAdd,
          comments: formData.deviceFunctionValueCommentsAdd

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionValueModalAdd').modal('hide');

            deviceFunction.getPagingPage_value(deviceFunctionId);
            // refreshTable();

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
    function editValueSubmit(formData)
    {

      if (!formData)
      {
        return;
      }

      //获取表单数据
      var formData = form2js(formData, null, null, null, null, true);

      $.ajax({
        type: 'POST',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/update_value',
        dataType: 'json',
        data: {
          id : $('#deviceFunctionValueIdEdit').val(),
          name: formData.deviceFunctionValueNameEdit,
          value : formData.deviceFunctionValueValueEdit,
          comments: formData.deviceFunctionValueCommentsEdit

        },
        success: function (result)
        {

          var status = result && result.status;

          if (status === '1')
          {

            $('#deviceFunctionValueModalEdit').modal('hide');

            deviceFunction.getPagingPage_value(deviceFunctionId);

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

    /**
     * 编辑点击事件
     *
     */
    $(document).on('click', '#deviceFunctionValueTable button.edit', function ()
    {

      var id = $(this).data('id');
      queryValue({id}, function (obj)
      {
        if(obj.status == "0") {
          console.warn(obj.msg);
          return;
        }
        var data = obj.data;

        if (!data)
        {
          return;
        }
        $('#deviceFunctionValueIdEdit').val(id);
        $('#deviceFunctionValueNameEdit').val(data.name || '');
        $('#deviceFunctionValueValueEdit').val(data.value || '');
        $('#deviceFunctionValueCommentsEdit').val(data.comments || '');
      });
    });

    // 查询
    function queryValue(obj, callback)
    {
      if (typeof callback !== 'function')
      {
        return;
      }

      obj = obj || {};

      $.ajax({
        type: "post",
        url: _ctx + "/view/sysmanage/interfaceconfig/deviceFunction/query_value",
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

    /**
     * 删除点击事件
     *
     */
    $(document).on('click', '#deviceFunctionValueTable button.delete', function ()
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
            deletes_value(id, function (result)
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
                deviceFunction.getPagingPage_value(deviceFunctionId);

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

    // 删除
    function deletes(id, callback) {

      if (!id) {
        console.warn('无效参数！');
        return;
      }

      $.ajax({
        type    : "POST",
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceFunction/delete",
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

    // 删除
    function deletes_value(id, callback) {

      if (!id) {
        console.warn('无效参数！');
        return;
      }

      $.ajax({
        type    : "POST",
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceFunction/delete_value",
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

    // 根据条件搜索
    function search()
    {
      var keywords = $('#deviceFunctionKeywords').val();

      refreshTable(keywords);
    }

    function getdeviceFunctionDeviceType() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/getDeviceType',
        success: function(result){

          var ops="<option value=''>请选择设备类型</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.id+'">';
            ops+=obj.name;
            ops+='</option>';
          }
          $("#deviceFunctionDeviceTypeAdd").append(ops);
          $("#deviceFunctionDeviceTypeEdit").append(ops);
        }
      });
    }


    //设备类型下拉框
    function  get_deviceTypetree() {

      $.issp_ajax({
        type: "POST",
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceFunction/getDeviceType',
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
              deviceType_select('#deviceFunction_sblx',deviceType_id,deviceType_val);
            }
          }
          else
          {
            deviceType_id.push("");
            deviceType_val.push("");
            deviceType_select('#deviceFunction_sblx',deviceType_id,deviceType_val);

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
      getPagingPage_value(_deviceFunctionId) {
        deviceFunctionId = _deviceFunctionId;
        getPagingPage_value({deviceFunctionId}, function (page)
        {

          showPagingPage('deviceFunctionValuePageContainer', page);
        });

      },
      search,
      pageInit: function () {
        // getdeviceFunctionDeviceType();
        get_deviceTypetree();
      }
    }

  })(jQuery, window, document);
  deviceFunction.pageInit();
</script>