<!-- 园区表
	创建人：sunhao
 -->
<!-----内容区域---->
<!-- 按钮 -->
<!-- <div class="ibox-content m-b-sm border-bottom" style="width: 100%; height: 7%">
	<div class="input-group" style="height: 30px; width: 100%;">
		<div style="width: 50%; float: left; position: relative; padding-right: 15px;">
			<a id="addBESPark" href="javascript:void(-1);" onclick="basedatamanage_energyinformation_Bespark.park_show_addmodal()" class="btn btn-primary" st> 增加 <i class="fa fa-plus"></i>
			</a> <a id="importEquipmentBrand" class="btn btn-primary" data-toggle="modal" href="#modal-form-adduser"> 导入 <i class="fa fa-mail-reply"></i>
			</a> <a id="exportEquipmentBrand" class="btn btn-primary" data-toggle="modal" href="#modal-form-adduser"> 导出 <i class="fa fa-mail-forward"></i>
			</a>
		</div>
		<div style="width: 25%; float: right; position: relative; padding-right: 0px; padding-left: 15px;">
			<input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="park_keywords" 
				name="park_keywords" value="" placeholder="园区编号、园区名称...">
			<span class="input-group-btn" style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryparkBtn">
					<i class="fa fa-search"></i> 搜索
				</button>
			</span>
		</div>
		<div class="zc_search find">
			<div class="zc_search_form">
			  <input type="text"  id="park_keywords" name="park_keywords" placeholder="园区编号、园区名称...">
			  <button id="queryparkBtn"></button>
			</div>
		</div>
	</div>
</div> -->
<!--   ------------------------------------------------------------------------------------   -->

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;园区信息列表>>>
		</span>
  <!-- 增加按钮 -->
  <a id="addBESPark" href="javascript:void(-1);" onclick="basedatamanage_energyinformation_Bespark.park_show_addmodal()"
     class="btn btn-primary toLeft">
    <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
  </a>
  <!-- 导入按钮 -->
    <#--没有导入对应的方法，隐藏按钮-->
    <#--<a id="expBESPark" href="javascript:void(-1);" onclick="basedatamanage_energyinformation_bespark_page.imp_excel(this)" class="btn btn-primary toLeft"> -->
    <#--<i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入-->
    <#--</a> -->
  <!-- 导出按钮 -->
  <a id="expBESPark" href="javascript:void(-1);" onclick="basedatamanage_energyinformation_bespark_page.exp_excel(this)"
     class="btn btn-primary toLeft">
    <i class="fa fa-download" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
  </a>
  <!-- 搜索框 -->
  <div class="zc_search find">
    <input type="text" class="find-style" id="park_keywords" name="park_keywords" placeholder="园区编号、园区名称...">
    <button id="queryparkBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
  </div>
</div>
<!---分页列表----->
<div class="ibox" id="park_besparkType_ibox" style="height:92%">
    <#include "/view/basedatamanage/energyinformation/bespark_page.ftl"/>
</div>


<!-- 按钮结束  添加模态框开始---------------------------->
<div class="modal fade" id="modal-form-addBESPark" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp; 添加园区信息</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="park_addBESpark" name="add" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="park_f_user_name" name="park_f_user_name" required class="form-control">

              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="park_f_zzjg" name="park_f_zzjg" required class="form-control">

              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">园区名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_yqmc" name="park_f_yqmc" placeholder="请输入园区名称" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">节点编码<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_node_code" name="park_f_node_code" placeholder="请输入节点编码"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">总面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_all_area" name="park_f_all_area" placeholder="请输入总面积" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">监测面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_monitor_area" name="park_f_monitor_area" placeholder="请输入监测面积"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">供暖面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_heat_area" name="park_f_heat_area" placeholder="请输入供暖面积"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">总人口<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_person_nums" name="park_f_person_nums" placeholder="请输入总人口"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">建筑时间<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_build_time" name="park_f_build_time"
                     onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"
                     placeholder="请输入建筑时间" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">设备运行时间<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_equipment_runtime" name="park_f_equipment_runtime"
                     onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"
                     placeholder="请输入设备运行时间" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_contact_name" name="park_f_contact_name" placeholder="请输入联系人"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人电话<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_contact_phone" name="park_f_contact_phone" placeholder="请输入联系人电话"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人邮箱<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_contact_email" name="park_f_contact_email" placeholder="请输入联系人邮箱"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">经度<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_longitude" name="park_f_longitude" placeholder="请输入位置坐标（经度）"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">纬度<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="park_f_latitude" name="park_f_latitude" placeholder="请输入位置坐标（纬度）"
                     class="form-control">
            </div>
          </div>
          <div class="form-group m-t-sm">
            <div class="col-sm-6 col-sm-push-3 display_flex">
              <button class="btn btn-md btn-primary" type="submit">
                <strong>确定</strong>
              </button>
              <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- ------------------------------添加结束-------------------------------- -->
<!----编辑用户--->
<div class="modal fade" id="editBESParkTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp; 编辑园区信息</h4>
      </div>
      <div class="modal-body">
        <form id="yqb_editBESParkTable" name="yqb_editBESParkTable" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_park_f_user_name" name="edit_park_f_user_name"  class="form-control">

              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_park_f_zzjg" name="edit_park_f_zzjg" required class="form-control">

              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">园区名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_yqmc" name="edit_park_f_yqmc" placeholder="请输入园区名称"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">节点名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_f_node_code" name="edit_f_node_code" placeholder="请输入节点名称"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">总面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_all_area" name="edit_park_f_all_area" placeholder="请输入总面积"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">监测面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_monitor_area" name="edit_park_f_monitor_area" placeholder="请输入监测面积"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">供暖面积<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_heat_area" name="edit_park_f_heat_area" placeholder="请输入供暖面积"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">总人口<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_person_nums" name="edit_park_f_person_nums" placeholder="请输入总人口"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">建筑时间<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_build_time" name="edit_park_f_build_time"
                     onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"
                     placeholder="请输入建筑时间" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">设备运行时间<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_equipment_runtime" name="edit_park_f_equipment_runtime"
                     onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"
                     placeholder="请输入设备运行时间" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_contact_name" name="edit_park_f_contact_name" placeholder="请输入联系人"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人电话<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_contact_phone" name="edit_park_f_contact_phone" placeholder="请输入联系人电话"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">联系人邮箱<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_contact_email" name="edit_park_f_contact_email" placeholder="请输入联系人邮箱"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">经度<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_longitude" name="edit_park_f_longitude" placeholder="请输入位置坐标（经度）"
                     class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">纬度<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_park_f_latitude" name="edit_park_f_latitude" placeholder="请输入位置坐标（纬度）"
                     class="form-control">
            </div>
          </div>
          <div class="form-group m-t-sm">
            <div class="col-sm-6 col-sm-push-3 display_flex">
              <button class="btn btn-md btn-primary" type="submit">
                <strong>确定</strong>
              </button>
              <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!---编辑信息结束----->
<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script> <!-- 时间选框用JS -->
<script type="text/javascript">
  var basedatamanage_energyinformation_Bespark = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var f_yqbh = "";

    $(window).resize(function () {
      $("#yqb_ParkTable").tabulator("redraw");
    });
    //触发搜索的回车时间
    $("#park_keywords").focus(function () {
      $(this).keydown(function (e) {
        if (e.which == "13") {
          basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark({
            "keywords": $("#park_keywords").val()
          });
        }
      })
    });
    $("#queryparkBtn").click(function () {
      basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark({
        "keywords": $("#park_keywords").val()
      });
    });
    //居中显示（添加）
    $('#modal-form-addBESPark').on('show.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-form-addBESPark .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    //关闭模态框清空表单值
    $("#modal-form-addBESPark").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      park_Validator.resetForm();
    });
    //居中显示（编辑）
    $('#editBESParkTable').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#editBESParkTable .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    //关闭编辑模态框清空表单值
    $("#editBESParkTable").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      park_edit_Validator.resetForm();
    });
    //验证在编辑模态框出现前加载
    $("#editBESParkTable").on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget);
      f_yqbh = button.data("id");
      $.ajax({
        url: _ctx + "/view/park/loadeditobj",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          f_yqbh: f_yqbh
        }),
        success: function (result) {
          $('#edit_park_f_user_name').val(result.f_user_name || '');
          $('#edit_park_f_zzjg').val(result.f_zzjg_id || '');
          $("#edit_park_f_yqmc").val(result.f_yqmc);
          $("#edit_f_node_code").val(result.f_node_code);
          $("#edit_park_f_all_area").val(result.f_all_area);
          $("#edit_park_f_monitor_area").val(result.f_monitor_area);
          $("#edit_park_f_heat_area").val(result.f_heat_area);
          $("#edit_park_f_person_nums").val(result.f_person_nums);
          $("#edit_park_f_build_time").val(result.f_build_time);
          $("#edit_park_f_equipment_runtime").val(result.f_equipment_runtime);
          $("#edit_park_f_contact_name").val(result.f_contact_name);
          $("#edit_park_f_contact_phone").val(result.f_contact_phone);
          $("#edit_park_f_contact_email").val(result.f_contact_email);
          $("#edit_park_f_longitude").val(result.f_longitude);
          $("#edit_park_f_latitude").val(result.f_latitude);
        }
      });
    });
    //编辑验证
    var park_edit_Validator = $("#yqb_editBESParkTable").validate({
      rules: {
        edit_park_f_user_name: {
          required: true,
          maxlength: 50
        },
        edit_park_f_zzjg: {
          required: true,
          maxlength: 50

        },
        edit_park_f_yqmc: {
          required: true,
          rangelength: [1, 20],
        },
        edit_f_node_code: {
          required: true,
          rangelength: [1, 20],
        },
        edit_park_f_all_area: {
          number: true,
          rangelength: [1, 20],
        },
        edit_park_f_monitor_area: {
          number: true,
          rangelength: [1, 20],
        },
        edit_park_f_heat_area: {
          number: true,
          rangelength: [1, 20],
        },
        edit_park_f_person_nums: {
          number: true,
          rangelength: [1, 20],
        },
        edit_park_f_build_time: {
          required: true,
        },
        edit_park_f_equipment_runtime: {
          required: true,
        },
        edit_park_f_contact_name: {
          rangelength: [1, 20],
        },
        edit_park_f_contact_phone: {
          rangelength: [1, 20],
        },
        edit_park_f_contact_email: {
          email: true
        },
        edit_park_f_longitude: {
          longitude: true,
        },
        edit_park_f_latitude: {
          latitude: true,
        }
      },
      messages: {
        edit_park_f_user_name: {
          required: '请选择用户'
        },
        edit_park_f_zzjg: {
          required: '请选择组织机构'
        },
        edit_park_f_yqmc: {
          required: "请输入园区名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_f_node_code: {
          required: "请输入节点名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_all_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_monitor_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_heat_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_person_nums: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_build_time: {
          required: "请输入建筑时间",
        },
        edit_park_f_equipment_runtime: {
          required: "请输入设备运行时间",
        },
        edit_park_f_contact_name: {
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_contact_phone: {
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        edit_park_f_contact_email: {
          email: "必须输入正确格式的电子邮件",
        },
      },
      submitHandler: function (form) {
        park_BesparkForm(form);
      }
    });

    //编辑
    function park_BesparkForm(form) {
      $.ajax({
        url: _ctx + "/view/park/updateBESPark",
        type: "post",
        data: ({
          f_user_name: $("#edit_park_f_user_name").val(),
          f_zzjg_id : $("#edit_park_f_zzjg").val(),
          f_yqbh: f_yqbh,
          f_yqmc: $("#edit_park_f_yqmc").val(),
          f_node_code: $("#edit_f_node_code").val(),
          f_all_area: $("#edit_park_f_all_area").val(),
          f_monitor_area: $("#edit_park_f_monitor_area").val(),
          f_heat_area: $("#edit_park_f_heat_area").val(),
          f_person_nums: $("#edit_park_f_person_nums").val(),
          f_build_time: $("#edit_park_f_build_time").val(),
          f_equipment_runtime: $("#edit_park_f_equipment_runtime").val(),
          f_contact_name: $("#edit_park_f_contact_name").val(),
          f_contact_phone: $("#edit_park_f_contact_phone").val(),
          f_contact_email: $("#edit_park_f_contact_email").val(),
          f_longitude: $("#edit_park_f_longitude").val(),
          f_latitude: $("#edit_park_f_latitude").val(),
        }),
        success: function (result) {
          //basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark();
          if (result.status == '1') {
            swal({
              title: result.msg,// 展示的标题
              text: "", // 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            $('#editBESParkTable').modal('hide');//关闭编辑窗口
            $('#yqb_ParkTable').tabulator("updateRow", _curRow, {
              f_yqmc: result.data.f_yqmc,
              f_node_code: result.data.f_node_code,
              f_all_area: result.data.f_all_area,
              f_monitor_area: result.data.f_monitor_area,
              f_heat_area: result.data.f_heat_area,
              f_person_nums: result.data.f_person_nums,
              f_build_time: result.data.f_build_time,
              f_equipment_runtime: result.data.f_equipment_runtime,
              f_contact_name: result.data.f_contact_name,
              f_contact_phone: result.data.f_contact_phone,
              f_contact_email: result.data.f_contact_email,
              f_chdate: result.data.f_chdate,
              f_longitude: result.data.f_longitude,
              f_latitude: result.data.f_latitude
            });
          } else {
            swal("修改失败!", result.msg, "error");
          }
        },
        error: function (result) {
          swal("修改失败!", result.msg, "error");
        }
      });
    }

    // 经度验证
    $.validator.addMethod('longitude', function (value, element) {
      var lonRE = /^(\-|\+)?(((\d|[1-9]\d|1[0-7]\d|0{1,3})\.\d{0,6})|(\d|[1-9]\d|1[0-7]\d|0{1,3})|180\.0{0,6}|180)$/;
      return this.optional(element) || (lonRE.test(value));
    }, '经度整数部分为0-180,小数部分为0到6位!');
    // 纬度验证
    $.validator.addMethod('latitude', function (value, element) {
      var latRE = /^(\-|\+)?([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
      return this.optional(element) || (latRE.test(value));
    }, '纬度整数部分为0-90,小数部分为0到6位!');
    //添加组织机构表单验证
    var park_Validator = $("#park_addBESpark").validate({
      rules: {
        park_f_user_name: {
          required: true,
          maxlength: 50
        },
        park_f_zzjg: {
          required: true,
          maxlength: 50

        },
        park_f_yqmc: {
          required: true,
          rangelength: [1, 20],
        },
        park_f_node_code: {
          required: true,
          rangelength: [1, 20],
        },
        park_f_all_area: {
          number: true,
          rangelength: [1, 20],
        },
        park_f_monitor_area: {
          number: true,
          rangelength: [1, 20],
        },
        park_f_heat_area: {
          number: true,
          rangelength: [1, 20],
        },
        park_f_person_nums: {
          number: true,
          rangelength: [1, 20],
        },
        park_f_build_time: {
          required: true,
        },
        park_f_equipment_runtime: {
          required: true,
        },
        park_f_contact_name: {
          rangelength: [1, 20],
        },
        park_f_contact_phone: {
          rangelength: [1, 20],
        },
        park_f_contact_email: {
          email: true
        },
        park_f_longitude: {
          longitude: true,
        },
        park_f_latitude: {
          latitude: true,
        }
      },
      messages: {
        park_f_user_name: {
          required: '请选择用户'
        },
        park_f_zzjg: {
          required: '请选择组织机构'
        },
        park_f_yqmc: {
          required: "请输入园区名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_node_code: {
          required: "请输入节点编码",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_all_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_monitor_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_heat_area: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_person_nums: {
          number: "请输入合法数字",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_build_time: {
          required: "请输入建筑时间",
        },
        park_f_equipment_runtime: {
          required: "请输入设备运行时间",
        },
        park_f_contact_name: {
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_contact_phone: {
          minlength: jQuery.validator.format("Enter at least {0} characters")
        },
        park_f_contact_email: {
          email: "必须输入正确格式的电子邮件",
        },
      },
      submitHandler: function (form) {
        basedatamanage_energyinformation_Bespark.addform_BESPark(form);
      }
    });
    //删除数据
    $(document).on('click', '#yqb_ParkTable button.delete', function () {
      var f_yqbh = $(this).data("id");
      swal({
        title: "您确定要删除吗?",
        text: "信息删除后将不可恢复!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#1ab394",
        confirmButtonText: "确定",
        closeOnConfirm: false
      }, function () {
        $.ajax({
          url: _ctx + "/view/park/deleteBESPark",
          contentType: "application/json; charset=utf-8",
          type: "post",
          data: JSON.stringify({
            f_yqbh: f_yqbh
          }),
          success: function (data) {
            basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark();
            if (data.status == '1') {
              swal({
                title: data.msg,// 展示的标题
                text: "", // 内容
                type: "success",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1000
              });
              //在表格中删除该条数据
              $("#yqb_ParkTable").tabulator("deleteRow", f_yqbh);
              var getBESParkTable = $("#yqb_ParkTable").tabulator("getData");
              $("#yqb_ParkTable").tabulator("setData", getBESParkTable);
            } else {
              swal(data.msg, "", "error");
            }
          },
          error: function (data) {
            swal(data.msg, "", "error");
          }
        });
      });
    });

    function getuserName() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: '${ctx}/view/basedatamanage/energyinformation/getuserNameList',
        success: function(result){

          var ops="<option value=''>请选择用户</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.f_yhbh+'">';
            ops+=obj.f_name;
            ops+='</option>';
          }
          $("#park_f_user_name").append(ops);
          $("#edit_park_f_user_name").append(ops);
        }
      });
    }

    function getZZJG() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: '${ctx}/view/basedatamanage/energyinformation/getZZJGList',
        success: function(result){

          var ops="<option value=''>请选择组织机构</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.F_ZZJGBH+'">';
            ops+=obj.F_ZZJGMC;
            ops+='</option>';
          }
          $("#park_f_zzjg").append(ops);
          $("#edit_park_f_zzjg").append(ops);
        }
      });
    }
    return {
      //验证增加模态框是否弹出
      park_show_addmodal: function () {
        $('#modal-form-addBESPark').modal('show');
      },
      //新增保存
      addform_BESPark: function (form) {
        var n = $("f_yqbh").val();
        $.ajax({
          url: _ctx + "/view/park/addBESPark",
          type: "post",
          contentType: "application/json; charset=utf-8",
          data: JSON.stringify({
            f_user_name : $("#park_f_user_name").val(),
            f_zzjg_id: $("#park_f_zzjg").val(),
            f_yqmc: $("#park_f_yqmc").val(),
            f_node_code: $("#park_f_node_code").val(),
            f_all_area: $("#park_f_all_area").val(),
            f_monitor_area: $("#park_f_monitor_area").val(),
            f_heat_area: $("#park_f_heat_area").val(),
            f_person_nums: $("#park_f_person_nums").val(),
            f_build_time: $("#park_f_build_time").val(),
            f_equipment_runtime: $("#park_f_equipment_runtime").val(),
            f_contact_name: $("#park_f_contact_name").val(),
            f_contact_phone: $("#park_f_contact_phone").val(),
            f_contact_email: $("#park_f_contact_email").val(),
            f_longitude: $("#park_f_longitude").val(),
            f_latitude: $("#park_f_latitude").val()
          }),
          success: function (result) {
            basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark();
            if (result.status == '1') {
              swal({
                title: result.msg,// 展示的标题
                text: "", // 内容
                type: "success",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1000
              });
              $('#modal-form-addBESPark').modal('hide');//关闭编辑窗口
              //在表格中添加数据
              $('#yqb_ParkTable').tabulator("addRow", {
                // userName : result.data.userName,
                // zzjgId: result.data.userName,
                f_yqbh: result.data.f_yqbh,
                f_yqmc: result.data.f_yqmc,
                f_all_area: result.data.f_all_area,
                f_monitor_area: result.data.f_monitor_area,
                f_heat_area: result.data.f_heat_area,
                f_person_nums: result.data.f_person_nums,
                f_build_time: result.data.f_build_time,
                f_equipment_runtime: result.data.f_equipment_runtime,
                f_contact_name: result.data.f_contact_name,
                f_contact_phone: result.data.f_contact_phone,
                f_contact_email: result.data.f_contact_email,
                f_crdate: result.data.f_crdate,
                f_chdate: result.data.f_chdate
              });
            } else {
              swal(result.msg, "", "error");
            }
          },
          complete: function () {
            hiddenLoad();
          },
          error: function (result) {

            swal(result.msg, "", "error");
          }
        });
        return false;
      },
      //分页查询
      yqb_reLoadBESPark: function (datas) {
        $.issp_ajax({
          url: _ctx + '/view/park/BESPark_page',
          type: "post",
          data: datas,
          success: function (data) {
            $('#park_besparkType_ibox').html(data);
          },
          error: function (XMLHttpRequest, textStatus, errorThrown) {
            toastr.error('', '查询失败');
          }
        });
      },
      Park_pageInit: function () {
        basedatamanage_energyinformation_Bespark.yqb_reLoadBESPark();
        getZZJG();//获取组织机构信息
        getuserName();//获取用户信息
      }
    }
  })(jQuery, window, document);
  //初始加载
  basedatamanage_energyinformation_Bespark.Park_pageInit();
</script>
