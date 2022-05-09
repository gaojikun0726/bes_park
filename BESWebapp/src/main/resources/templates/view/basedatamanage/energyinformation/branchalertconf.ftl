<!-----内容区域---->
<!-- 组织机构树模块 -->
<style>
  .ztree li a.curSelectedNode {
    padding-top: 0px;
    background-color: rgba(31, 255, 8, 0);
    color: black;
    height: 16px;
    /*border: 1px #FFB951 solid;*/
    opacity: 0.8;
  }

  #tree_device_type_modal_branchalertConf {
    position: absolute;
    left: 58%;
    top: 11.5%;
  }
</style>
<div class="leftarea information_left" id="leftaltertreeconf">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择支路>>>
		</span>
  </div>
  <div id="tree_branchalert" class="Information_area" style=""></div>
</div>

<div class="modal fade" style="width: 40% " id="tree_device_type_modal_branchalertConf" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog" style="margin-top: 8%;">
    <div class="modal-content" style="width: 350px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp; 请选择设备类型</h4>
      </div>
      <div class="modal-body" style="height: 450px">
        <div style="position: relative" class="zc_search find">
          <input type="text" class="find-style" id="tree_device_type_search_water_coolant_input" name="eqTypeInfo"
                 placeholder="设备类型">
          <button id="tree_device_type_search_water_coolant_button"><i class="fa fa-search" aria-hidden="true"></i>
          </button>
        </div>

          <#--设备类型树-->
        <div id="tree_device_type_water_coolant" class="Information_area ztree"></div>

      </div>
    </div><!-- end modal-content -->
  </div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
  <div class="information_size">
    <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;支路参数报警配置列表>>>
			</span>
      <!-- 增加按钮 -->
      <a id="addbranchalertConf" href="javascript:void(-1);"
         onclick="basedatamanage_energyinformation_branchalertConf.show_addbranchalert();"
         class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
      </a>
      <!-- 生成园区下拉框  -->
      <div style="display:inline-block ;">
        <input type="hidden" id="gnzd_xt_list" class="form-control">
        <select id='btn_yq' name='btn_yq' required class="form-control selectpicker" style="height:2.8vh;width:100%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_energyinformation_branchalertConf.get_altertree_sub1()"
                data-live-search="false">
        </select>
      </div>
      <!-- <label style=" display:inline-block ;margin-left: 50px;">模块</label> -->
      <div style="display:inline-block ;margin-left: 15px;">
        <select id='btn_ny' name='btn_ny' required class="form-control selectpicker" style="height:2.8vh;width:115%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_energyinformation_branchalertConf.get_altertree_sub2()"
                data-live-search="false">
        </select>
      </div>
      <!-- 搜索框 -->
      <div class="zc_search find">
        <input type="text" class="find-style" id="branchalertInfo" name="branchalertInfo" placeholder="报警名称">
        <button id="queryBranchAlert" onclick="basedatamanage_energyinformation_branchalertConf.searchbranchalert()"><i
                  class="fa fa-search" aria-hidden="true"></i></button>
      </div>
    </div>
    <div id="branchalertTable" class="Information_area"></div>
  </div>
</div>
<!-- 信息表格模块end -->


<!---添加支路报警信息开始----->
<div class="modal fade" id="modal-form-addbranchalertConf" style="width: 40%;margin-left: 20%" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">添加支路报警信息</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="addbranchalertConfform" name="addbranchalertConfform" class="form-horizontal">
          <div class="form-group">
            <label class="col-sm-3 control-label">报警名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fAlertname" name="fAlertname" placeholder="请输入报警名称" required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">运算公式<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fOperator" name="fOperator" placeholder="在输入的每个参数前请加入$符号,例如$1+$2" required
                     class="form-control">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">报警类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="f_alertType" name="f_alertType" class="form-control selectpicker">
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">输入参数
            </label>
            <div class="col-sm-8">
              <input id="f_parameter"
                     name="f_parameter"
                     type="hidden"
              >
              <input style="width: 348px"
                     type="text"
                     id="fparameter"
                     name="fparameter"
                     class="form-control"
                     placeholder="请选择输入参数"
              >
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">范围类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="fRangetype" name="fRangetype" class="form-control selectpicker">
                　　　　
                <option value="0">最高阈值</option>
                　　　　
                <option value="1">最小阈值</option>
                　　　　
                <option value="2">阈值范围</option>
                <option value="3">准确值</option>
              </select>
              <!-- <input type="text" id="fRangetype" name="fRangetype" placeholder="请输入分户面积"  required class="form-control"> -->
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">报警等级<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="fgrade" name="fgrade" class="form-control selectpicker">
                　　　　
                <option value="1">重大报警</option>
                　　　　
                <option value="2">较大报警</option>
                　　　　
                <option value="3">一般报警</option>
              </select>
              <!-- <input type="text" id="fRangetype" name="fRangetype" placeholder="请输入分户面积"  required class="form-control"> -->
            </div>
          </div>
          <div class="form-group" id="fNomoreKZ" hidden="true">
            <label class="col-sm-3 control-label">最大值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fNomore" name="fNomore" placeholder="请输入最大值" required class="form-control">
            </div>
          </div>
          <div class="form-group" id="fNolessKZ" hidden="true">
            <label class="col-sm-3 control-label">最小值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fNoless" name="fNoless" value="" placeholder="请输入最小值" class="form-control">
            </div>
          </div>
          <div class="form-group" id="fEqualKZ" hidden="true">
            <label class="col-sm-3 control-label">准确值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fEqual" name="fEqual" value="" placeholder="请输入准确值" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">使能<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="fActive" name="fActive" class="form-control selectpicker">
                　　　　
                <option value="0">使能</option>
                　　　　
                <option value="1">否</option>
              </select>
              <!--  <input type="text" id="fActive" name="fActive" value="" class="form-control" >	 -->
            </div>
          </div>

          <div class="form-group m-t-sm">
            <div class="col-sm-6 col-sm-push-4 display_flex">
              <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
              <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!----编辑支路报警--->
<div class="modal fade" id="editBranchalertForm" style="width: 40%;margin-left: 20%" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp; 编辑支路报警信息</h4>
      </div>
      <div class="modal-body">
        <form id="edit_BranchAlertForm" name="edit_BranchAlertForm" class="form-horizontal">

          <div class="form-group" hidden>
            <label class="col-sm-3 control-label" for="edit_fId">报警Id<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fId" name="edit_fId" required class="form-control">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fAlertname">报警名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fAlertname" name="edit_fAlertname" required class="form-control"
                     onchange="basedatamanage_energyinformation_branchalertConf.thisEditAlertName(this.edit_fAlertname)">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fOperator">运算公式<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fOperator" name="edit_fOperator" required class="form-control">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">报警类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_alertType" name="edit_alertType" class="form-control selectpicker">
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">输入参数
            </label>
            <div class="col-sm-8">
              <input id="f_parameter"
                     name="f_parameter"
                     type="hidden"
              >
              <input style="width: 348px"
                     type="text"
                     id="ex_fparameter"
                     name="ex_fparameter"
                     class="form-control"
                     placeholder="请选择输入参数"
              >
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fRangetype">范围类型<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_fRangetype" name="edit_fRangetype" class="form-control selectpicker">
                　　　　
                <option value="0">最高阈值</option>
                　　　　
                <option value="1">最小阈值</option>
                　　　　
                <option value="2">阈值范围</option>
                <option value="3">准确值</option>
              </select>
            </div>
          </div>

					<div class="form-group">
						<label class="col-sm-3 control-label">报警等级<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id="edit_fgrade" name="edit_fgrade" class="form-control selectpicker">
								　　　　
								<option value="1">重大报警</option>
								　　　　
								<option value="2">较大报警</option>
								　　　　
								<option value="3">一般报警</option>
							</select>
							<!-- <input type="text" id="fRangetype" name="fRangetype" placeholder="请输入分户面积"  required class="form-control"> -->
						</div>
					</div>


					<div class="form-group" id="edit_fNomoreKZ" hidden>
            <label class="col-sm-3 control-label" for="edit_fNomore">最大值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fNomore" name="edit_fNomore" required class="form-control">
            </div>
          </div>
          <div class="form-group" id="edit_fNolessKZ" hidden>
            <label class="col-sm-3 control-label" for="edit_fNoless">最小值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fNoless" name="edit_fNoless" required class="form-control">
            </div>
          </div>
          <div class="form-group" id="edit_fEqualKZ" hidden>
            <label class="col-sm-3 control-label" for="edit_fEqual">准确值<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <!-- <select id="edit_fEqual" name="edit_fEqual" required class="form-control">
              </select> -->
              <input type="text" id="edit_fEqual" name="edit_fEqual" required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fActive">使能<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_fActive" name="edit_fActive" class="form-control selectpicker">
                　　　　
                <option value="0">使能</option>
                　　　　
                <option value="1">否</option>
              </select>
              <!-- <input type="text" id="edit_fActive" name="edit_fActive"  required class="form-control" > -->
            </div>
          </div>
          <div class="form-group m-t-sm">
            <div class="col-sm-6 col-sm-push-4 display_flex">
              <button class="btn btn-md btn-primary " type="submit">
                <strong>确定</strong>
              </button>
              <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">
  ;
  var basedatamanage_energyinformation_branchalertConf = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var _branchZlbh = "00";//分户配置编码
    var _branchJs = "0";//用户组对应的级数
    var _fhbh = "00";
    var _yqbh = "";//园区编号
    var _Nybh = "";//能源编号
    var _yqlog = "0";//判断组织机构下是否有分项配置
    var _curRow = null;//对应行
    var Selected_tree = null;//组织机构树被选中的节点
    var Selected_sub = null;//分项树被选中的节点
    var branchalertid = null;
    var tree;
    var parentId = "";
    //设置格式
    var optIconFunction = function (cell, formatterParams) { //plain text value
      branchalertid = cell.getRow().getData().fId;
      return "<div class='btn-group '>"
          /* +"<button class='btn btn-white btn-sm include' data-id=" + branchalertid + " data-toggle='modal' data-target='#includeHouseholdBrc'><i class='fa fa-user'></i>  包含支路</button>" */
          + "<button class='btn btn-white btn-sm edit' data-id=" + branchalertid + " data-toggle='modal' data-target='#editBranchalertForm'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + branchalertid + "><i class='fa fa-trash'></i>删除</button></div>"
    };

    //创建并设置table属性
    $("#branchalertTable").tabulator({
      height: "100%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      //selectable:true,
      movableColumns: true,
      columns: [
        {title: "序号", field: "id", width: 70, formatter: "rownum", frozen: false, sorter: "string", align: "center"}, //frozen column
        {
          title: "报警名称",
          field: "fAlertname",
          width: 120,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        }, //never hide this column
        {
          title: "输入参数",
          field: "fparameter",
          width: 300,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "运算公式",
          field: "fOperator",
          width: 200,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        }, //hide this column first
        //{title:"范围类型", field:"fRangetype",width:60,sorter:"string",editor:false,align:"left",headerSort:false},
        {
          title: "报警类型",
          field: "fAlertTypeName",
          width: 140,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "报警等级",
          field: "fgrade",
          width: 80,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "最大值",
          field: "fNomore",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "最小值",
          field: "fNoless",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "准确值",
          field: "fEqual",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "使能",
          field: "fActive",
          width: 80,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },

        // {title:"创建时间", field:"fCrdate",width:120,sorter:"date",align:"center",editable:false,headerSort:false},
        // {title:"修改时间", field:"fChdate",width:120,sorter:"date",align:"center",editor:false,headerSort:false},
        {
          title: "操作",
          field: "opt",
          width: 150,
          tooltip: false,
          tooltipsHeader: false,
          align: "center",
          formatter: optIconFunction,
          headerSort: false
        },
      ],
      rowClick: function (e, row) {

        _curRow = row;
        $("#branchalertTable").tabulator("selectRow", 1);
      },
    });

    $(window).resize(function () {
      $("#branchalertTable").tabulator("redraw");
    });

    function getAlertType() {
      $("#f_alertType").empty();
      $("#edit_alertType").empty();
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/getAlertTypeInfo",
        dataType: "json",
        async: false,
        data: ({}),
        success: function (result) {
          var list = result.list;
          if (result.status == "1") {
            var content = "";
            for (var i = 0; i < list.length; i++) {
              var obj = list[i];
              var typeId = obj.ALARM_TYPE_NUM;
              var typeName = obj.ALARM_TYPE_NAME;

							if (typeId == 1) {
								continue;
							}

              var opt = "<option value=" + typeId + ">" + typeName + "</option>";
              content += opt;
            }
            $("#f_alertType").append(content);
            $("#edit_alertType").append(content);
          } else {
            swal({
              title: "查询报警类型失败!",
              text: "",// 内容
              type: "warning",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
          }
        },
        error: function () {
          swal({
            title: "查询报警类型失败!",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
        }
      })
    }

    //加载支路树
    function gettree_branchalert(_yqbh, _Nybh) {
      $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/energyinformation/branchalert_tree",
        data: ({
          fYqbh: _yqbh,
          fNybh: _Nybh
        }),
        dataType: "json",
        success: function (result) {
          if (result.list == undefined) {
            $('#tree_branchalert').treeview('remove');
            $("#branchalertTable").tabulator("setData", []);
            _branchJs = "1";
            _yqlog = "0";
          } else {
            _yqlog = "1";
            $('#tree_branchalert').treeview({
              data: result.list,         // 数据源
              highlightSelected: true,    //是否高亮选中
              levels: 4,
              enableLinks: true,//必须在节点属性给出href属性
              color: "#4a4747",
              onNodeSelected: function (event, nodeData) {
                $('#tree_branchalert').treeview('clearSearch');//清除搜索选中高亮
                _branchZlbh = nodeData.id;
                _branchJs = nodeData.level;
                getbranchalert_chlildtree(_branchZlbh, _branchJs);
                Selected_sub = $('#tree_branchalert').treeview('getSelected');
              }
            });

						var firstNode = $("#tree_branchalert").treeview('findNodes',[result.list[0].id,'id']);
						$("#tree_branchalert").treeview("selectNode", firstNode[0]);//将第1个节点的第一个子节点设置为选中状态

						if (Selected_sub == null) {
              // //获取子节点
              getbranchalert_chlildtree("", "");
            }
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    $(function () {
      initTree(); // 初始化树
      // getAlertType();
      $("#edit_fOperator").on(" input propertychange", function () {
        $("#ex_fparameter").val("");
        $("#fparameter").val("");
        arrName.length = 0;
      })
    });

    //控制报警范围类型的方法
    $("#fRangetype").on("change", function () {
      if ($(this).val() == 0) {
        $("#fNomoreKZ").show();
        $("#fNolessKZ").hide();
        $("#fEqualKZ").hide();

        $("#fNoless").val("");
        $("#fEqual").val("");
      } else if ($(this).val() == 1) {
        $("#fNomoreKZ").hide();
        $("#fNolessKZ").show();
        $("#fEqualKZ").hide();

        $("#fNomore").val("");
        $("#fEqual").val("");
      } else if ($(this).val() == 3) {
        $("#fNomoreKZ").hide();
        $("#fNolessKZ").hide();
        $("#fEqualKZ").show();

        $("#fNomore").val("");
        $("#fNoless").val("");
      } else if ($(this).val() == 2) {
        $("#fNomoreKZ").show();
        $("#fNolessKZ").show();
        $("#fEqualKZ").hide();

        $("#fEqual").val("");
      }
    })

    $("#edit_fRangetype").on("change", function () {
      if ($(this).val() == 0) {
        $("#edit_fNomoreKZ").show();

        $("#edit_fNolessKZ").hide();
        $("#edit_fEqualKZ").hide();

        $("#edit_fNoless").val("");
        $("#edit_fEqual").val("");
      } else if ($(this).val() == 1) {
        $("#edit_fNomoreKZ").hide();
        $("#edit_fNolessKZ").show();
        $("#edit_fEqualKZ").hide();

        $("#edit_fNomore").val("");
        $("#edit_fEqual").val("");
      } else if ($(this).val() == 3) {
        $("#edit_fNomoreKZ").hide();
        $("#edit_fNolessKZ").hide();
        $("#edit_fEqualKZ").show();

        $("#edit_fNomore").val("");
        $("#edit_fNoless").val("");
      } else if ($(this).val() == 2) {
        $("#edit_fNomoreKZ").show();
        $("#edit_fNolessKZ").show();
        $("#edit_fEqualKZ").hide();

        $("#edit_fEqual").val("");
      }
    })

    function gettree_ammeter(_branchZlbh, _branchJs) {
      $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/energyinformation/branchalert_ammeter",
        data: ({
          fYqbh: _branchZlbh,
          fNybh: _branchJs,
        }),
        dataType: "json",
        success: function (result) {
          if (tree.tree) {
            tree.tree.destroy();
          }
          initTree();

          tree.loadData(result);
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    function initTree() {
      tree = new Tree({
        container: 'tree_device_type_water_coolant',
        idKey: 'id',
        pIdKey: 'pId',
        name: 'name',
        setting: {
          view: {
            showIcon: false,
            fontCss: function (treeId, treeNode) {
              return (treeNode.highlight)
                  ? {color: '#A60000', 'font-weight': 'bold'}
                  : {color: '#D1E3F9', 'font-weight': 'normal'};
            }
          },
          callback: {
            onClick: reLoadOpenURL,//节点被点击时调用的函数
            beforeExpand: zTreeBeforeExpand,//用于捕获父节点展开之前的事件回调函数，并且根据返回值确定是否允许展开操作
            // onCheck: zTreeOnclick
          }
        }
      });
    }


    //点击ztree 复制输入框
    var num = 0; //可点击次数

    var now = false

    function zTreeBeforeExpand(treeId, treeNode) {
      tree = $.fn.zTree.getZTreeObj(treeId);
      if (treeNode.isParent) {
        /*如果是父节点*/
        if (treeNode.open) {
          /*父节点为展开状态，则折叠父节点*/
          tree.expandNode(treeNode, false, true, true, false);
        } else {
          /*父节点为折叠状态,点击展开后加载子节点 */
          parentId = treeNode.id;
          $.ajax({
            type: "post",
            url: _ctx + "/view/basedatamanage/energyinformation/branchalert_ammeter_rlgl",
            data: ({
              parentId: parentId
            }),
            dataType: "json",
            success: function (result) {
              tree.removeChildNodes(treeNode);
              tree.addNodes(treeNode, result)
              //点击节点存在子节点  不获取name  不存在获取name
              if (result.length) {
                now = true
              } else {
                now = false
              }
            },
            error: function (nodeData) {
              swal(nodeData.msg, "", "error");
            },
          });
        }
      }
    }

    var arrName = [] //存储当前 选择的集合
    var dncsId = [] //存储电能参数的id
    var dbid = []//存储电表id

    //运算公式校验
    $("#fOperator").bind('input propertychange', function () {
      $("#fparameter").val("")
      $("#ex_fparameter").val("")
      arrName.length = 0;
      dncsId.length = 0;
      dbid.length = 0;
    });

    function reLoadOpenURL(event, treeId, obj) {
      //存在子节点啦
      // if(xxx.value){ //存在运算公式  才能获取name
      // 	  存在两位数  arr.pish(name)  if  arr.length==2  arr.splice(0,1)  arr.push(name)
      var type = null;
      var type1 = $('#fOperator').val()
      var type2 = $('#edit_fOperator').val()//获取输入参数的值
      if (type1) {
        type = type1
      } else {
        type = type2
      }
      OperaterPanduan(type, event, treeId, obj)
    }

    function OperaterPanduan(type, event, treeId, obj) {
      if (obj.wldz == 1) {//证明是电表 塞ID

        if (obj.pId == null) {
          swal({
            title: "请选择电能参数",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
        }

      } else if (obj.wldz == 0) {//证明是AI点
        var nowType = 1  //默认两位
        if (type.indexOf("$") != -1) {
          for (var i = 0; i < type.length; i++) {
            var str1 = type.substring(i, i + 1);
            if (str1 == "$") {
              nowType++
            }
          }
        } else {
          swal({
            title: "请输入$符号",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
        }
        if (nowType - 1) {
          //当前存在俩
          if (arrName.length == nowType - 1) {
            arrName.splice(0, 1)//删除第一项
            dncsId.splice(0, 1)//删除第一项
            dbid.splice(0, 1)//删除第一项
            arrName.push(obj.name);
            dncsId.push(10000);
            dbid.push(obj.old);
            console.log(dncsId.id)
          } else {
            arrName.push(obj.name)

            dncsId.push(10000);

            dbid.push(obj.old);
          }
          // console.log(arrName.join(''))
          $('#fparameter').val(arrName.join(','))
          $('#ex_fparameter').val(arrName.join(','))
        }
      } else { //证明是其中的一块电表
        arrName.push(obj.name)
        dncsId.push(obj.id);
        dbid.push(obj.pId);
        $('#fparameter').val(arrName.join(','))
        $('#ex_fparameter').val(arrName.join(','))
      }
    }

    function zTreeOnclick(event, treeId, treeNode) {
      console.log(2111111, event, treeId, treeNode)
    }

    function getbranchalert_chlildtree(_branchZlbh, _branchJs) {
      var yqbh = $("#btn_yq").val();
      var nybh = $("#btn_ny").val();
      var searchInfo = $("#branchalertInfo").val();
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/branchalert_chlildtreegrid",
        type: "post",
        data: {
          fZlbh: _branchZlbh,
          f_js: _branchJs,
          yqbh: yqbh,
          nybh: nybh,
          searchInfo: searchInfo
        },
        success: function (nodeData2) {
          if (nodeData2.hasOwnProperty('list') == false) {
            $("#branchalertTable").tabulator("setData", []);
          } else {
            $("#branchalertTable").tabulator("setData", nodeData2.list);
          }
        },
        error: function (nodeData2) {
          swal(nodeData2.msg, "", "error");
        }
      });

    }

    $('#fparameter').focus(function (obj) {
      $('#tree_device_type_modal_branchalertConf').modal('show');
    });
    $('#ex_fparameter').focus(function (obj) {
      $('#tree_device_type_modal_branchalertConf').modal('show');
    });

    //触发搜索的回车时间
    $("#branchalertInfo").focus(function () {
      $(this).keydown(function (e) {
        if (e.which == "13") {
          basedatamanage_energyinformation_branchalertConf.searchbranchalert();//触发该事件
        }
      })
    });

    //添加用户组表单验证
    var groupValidator = $("#addbranchalertConfform").validate({
      rules: {
        fAlertname: {
          required: true,
          rangelength: [1, 40]
        }
      },
      messages: {
        fAlertname: {
          required: "请填写分项名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        }
      },
      submitHandler: function (nodeData) {
        // addDncsId();
        addformbranchalert(nodeData);
      }
    });

    //新增添加
    function addformbranchalert(nodeData, treeNode) {

      var f_parameter = $("#fparameter").val();

      var fOperator = $("#fOperator").val();

      var arr1 = f_parameter.split(",");
      var arr = fOperator.split("$");

      if (arr1.length != arr.length - 1) {
        swal({
          title: '运算公式参数与输入参数请保持一致！',
          text: "",
          type: "success",
          showCloseButton: false,
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
      } else {
        if (_yqlog == 0) {
          _branchZlbh = "";
        }
        $.ajax({
          url: _ctx + "/view/basedatamanage/energyinformation/insbranchalert",
          type: "post",
          contentType: "application/json; charset=utf-8",
          data: JSON.stringify({
            //fZlbh:$("#fZlbh").val(),
            fAlertname: $("#fAlertname").val(),
            fNoless: $("#fNoless").val(),
            fEqual: $("#fEqual").val(),
            pId: dbid,
            fsortno: dncsId,
            fgrade: $("#fgrade").val(),
            fparameter: $("#fparameter").val(),
            fOperator: $("#fOperator").val(),
            fRangetype: $("#fRangetype").val(),
            fNomore: $("#fNomore").val(),
            fActive: $("#fActive").val(),
            fZlbh: _branchZlbh,
            fJs: _branchJs,
            fAlertypeId: $("#f_alertType").val(),
          }),
          success: function (data) {
            if (data.status === '1') {
              swal({
                title: data.msg,
                text: "",
                type: "success",
                showCloseButton: false,
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1000
              });
              dncsId.length = 0;
              arrName.length = 0;
              dbid.length = 0;
              //setTimeout(function() {
              //关闭添加窗口
              $('#branchalertTable').tabulator("addRow", data.list);
              if (data.status == '1') {
                $('#modal-form-addbranchalertConf').modal('hide');
                getbranchalert_chlildtree(_branchZlbh, _branchJs);
              } else {
                swal(result.msg, "", "error");
              }
            } else {
              swal(data.msg, "", "error");
            }
          },
          error: function (data) {
            swal(data.msg, "", "error");
          }
        });
        return false;
      }

    }

    //居中显示（添加）
    $('#modal-form-addbranchalertConf').on('show.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-form-addbranchalertConf .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
      getAlertType();
    })

    //居中显示（编辑）
    $('#editBranchalertForm').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#editBranchalertForm .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
      getAlertType();
    })

    //居中显示（组织机构）
    $('#modal-yqtree').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-yqtree .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })

    //关闭模态框清空表单值
    $("#modal-form-addbranchalertConf").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      $("#fNoless").empty();
      groupValidator.resetForm();
      $('#tree_device_type_modal_branchalertConf').modal('hide');
      $("#edit_fNomoreKZ").show();
      $("#edit_fNolessKZ").hide();
      $("#edit_fEqualKZ").hide();
    });

    $("#editBranchalertForm").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      $("#fNoless").empty();
      groupValidator.resetForm();
      $('#tree_device_type_modal_branchalertConf').modal('hide');
    });


    $(document).on('click', '#branchalertTable button.delete', function () {
      var id = $(this).data("id");
      swal({
        title: "您确定要删除吗?",
        text: "信息删除后将不可恢复!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#1ab394",
        confirmButtonText: "确定",
        closeOnConfirm: false
      }, function () {
        setTimeout(function () {
              $.ajax({
                url: _ctx
                    + "/view/basedatamanage/energyinformation/del_branchalert",
                type: "post",
                data: {
                  "fId": id
                },
                beforeSend: function () {
                  showLoad();
                },
                success: function (data) {
                  if (data.status === '1') {
                    //swal(data.msg, "", "success");
                    swal({
                      title: data.msg,
                      text: "",
                      type: "success",
                      showCloseButton: false,
                      allowOutsideClick: false,
                      showConfirmButton: false,
                      timer: 1000
                    });
                    $("#branchalertTable").tabulator(
                        "deleteRow", _curRow);
                    var getGroupTable = $("#branchalertTable").tabulator("getData");
                    $("#branchalertTable").tabulator("setData", getGroupTable);
                  } else {
                    swal(data.msg, "", "error");
                  }
                },
                complete: function () {
                  hiddenLoad();
                },
                error: function (data) {
                  swal(data.msg, "", "error");
                }
              });
            }, 100
        )
      });
    });

    //表单验证
    $("#edit_BranchAlertForm").validate({
      submitHandler: function (form) {
        //getbranchalert_chlildtree(_branchZlbh,_branchJs);
        editBranchalertForm(form);
      }
    });


    function editBranchalertForm(form) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/updbranchalert",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          fId: $("#edit_fId").val(),
          fAlertname: $("#edit_fAlertname").val(),
          fOperator: $("#edit_fOperator").val(),
          fparameter: $("#ex_fparameter").val(),
          fRangetype: $("#edit_fRangetype").val(),
          fNomore: $("#edit_fNomore").val(),
          fNoless: $("#edit_fNoless").val(),
          fEqual: $("#edit_fEqual").val(),
          fActive: $("#edit_fActive").val(),
          pId: dbid,
          fsortno: dncsId,
          fAlertypeId: $("#edit_alertType").val(),
					fgrade: $("#edit_fgrade").val(),
        }),
        success: function (data) {
          if (data.status === '1') {
            swal({
              title: data.msg,
              text: "",
              type: "success",
              showCloseButton: false,
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            $('#editBranchalertForm').modal('hide');//关闭编辑窗口
            $('#tree_device_type_modal_branchalertConf').modal('hide');//关闭编辑窗口
            getbranchalert_chlildtree(_branchZlbh, _branchJs)
          } else {
            swal("", data.msg, "error");
          }
        },
        error: function (data) {
          swal("", data.msg, "error");
        }
      });
    }

    //单独验证
    function thisEditAlertName() {
      var name = document.getElementById("edit_fAlertname").value;
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/selectAlertNameRepeat",
        type: "post",
        data: {
          "alertName": name
        },
        success: function (result) {
          if (result.status === '0') {
            swal({
              title: result.msg,
              text: "",
              type: "success",
              showCloseButton: false,
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
          }
        },
        error: function () {

        }
      })
    }

    //验证在模态框出现前加载
    $("#editBranchalertForm").on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget);
      var id = button.data("id").toString();
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/selbranchalert",
        type: "post",
        data: {
          "fId": id
        },
        success: function (result) {
          dncsId.length = 0;
          arrName.length = 0;
          dbid.length = 0;
          var list = result.list;
          $("#edit_fId").val(result.data.fId);
          $("#edit_fNolessKZ").hide();
          $("#edit_fAlertname").val(result.data.fAlertname);
          $("#edit_fgrade").val(result.data.fgrade);
          $("#edit_fActive").val(result.data.fActive);
          $("#edit_fOperator").val(result.data.fOperator);
          $("#edit_fRangetype").val(result.data.fRangetype);
          $("#ex_fparameter").val(result.data.fparameter);
          $("#edit_fNomore").val(result.data.fNomore);
          $("#edit_fEqual").val(result.data.fEqual);
          $("#edit_fNoless").val(result.data.fNoless);
          $("#edit_alertType").val(result.data.fAlertypeId);
          yanzheng(result);
          if (list == undefined) {

          } else {
            for (var i = 0; i < list.length; i++) {
              dncsId.push(list[i].F_ELEDNBH);
              dbid.push(list[i].F_AMMSYS_NAME);
            }
          }
        }
      });

    });

    $("#modal-form-addbranchalertConf").on('show.bs.modal', function (event) {
      document.getElementById("fNomoreKZ").removeAttribute("hidden");
      document.getElementById("fNomoreKZ").removeAttribute("style");
      $("#fNolessKZ").hide();
      $("#fEqualKZ").hide();

    });

    function yanzheng(result) {
      //控制报警范围类型的方法
      if (result.data.fRangetype == 0) {
        document.getElementById("edit_fNomoreKZ").removeAttribute("hidden");
        document.getElementById("edit_fNomoreKZ").removeAttribute("style");
        $("#edit_fNolessKZ").hide();
        $("#edit_fEqualKZ").hide();
      } else if (result.data.fRangetype == 1) {
        document.getElementById("edit_fNolessKZ").removeAttribute("hidden");
        document.getElementById("edit_fNolessKZ").removeAttribute("style");
        $("#edit_fNomoreKZ").hide();
        $("#edit_fEqualKZ").hide();
      } else if (result.data.fRangetype == 3) {
        document.getElementById("edit_fEqualKZ").removeAttribute("hidden");
        document.getElementById("edit_fEqualKZ").removeAttribute("style");
        $("#edit_fNomoreKZ").hide();
        $("#edit_fNolessKZ").hide();
      } else if (result.data.fRangetype == 2) {
        document.getElementById("edit_fNomoreKZ").removeAttribute("hidden");
        document.getElementById("edit_fNomoreKZ").removeAttribute("style");
        document.getElementById("edit_fNolessKZ").removeAttribute("hidden");
        document.getElementById("edit_fNolessKZ").removeAttribute("style");
        $("#edit_fEqualKZ").hide();
      }
    }

    //验证在模态框出现前加载编辑(组织机构树)
    $("#modal-yqtree").on('show.bs.modal', function () {
      basedatamanage_energyinformation_branchalertConf.get_altertree_sub();
    });
    //验证在模态框加载前显示电表树
    $("#tree_device_type_modal_branchalertConf").on('show.bs.modal', function () {
      basedatamanage_energyinformation_branchalertConf.get_altertree_ammeter();
    });

    //关联能源下拉框
    function get_altertree_esub(_yqbh) {
      $("#btn_ny").empty();

      $.ajax({
        type: "post",
        url: _ctx + '/view/basedatamanage/energyinformation/getgllist',
        data: {
          f_yqbh: _yqbh
        },
        success: function (data) {

          var ops = "";
          for (var i = 0; i < data.list.length; i++) {
            var obj = data.list[i];
            ops += '<option value="' + obj.F_NYBH + '">';
            ops += obj.F_NYMC;
            ops += '</option>';
          }
          if (Selected_tree == null) {
            _Nybh = data.list[0].F_NYBH;
            //生成分项树
            gettree_branchalert(_yqbh, _Nybh);
          }
          $("#btn_ny").append(ops);
        },
        error: function (msg) {
          alert("select列表查询失败!");
        }

      });
    };
    return {
      //生成园区树
      get_altertree_sub: function () {

        $.ajax({
          type: "get",
          url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
          success: function (data) {

            //var ops = "<option value=''>请选择园区名称</option>";
            var ops = "";
            for (var i = 0; i < data.list.length; i++) {
              var obj = data.list[i];
              ops += '<option value="' + obj.f_yqbh + '">';
              ops += obj.f_yqmc;
              ops += '</option>';
            }
            if (Selected_tree == null) {
              _yqbh = data.list[0].f_yqbh;
              //传入能源树联动
              get_altertree_esub(_yqbh);
            }
            $("#btn_yq").append(ops);
          },
          error: function (msg) {
            alert("select列表查询失败!");
          }
        });
      },


      get_altertree_ammeter: function () {

        gettree_ammeter(_branchZlbh, _branchJs);
      },

      //生成园区树
      get_altertree_sub1: function () {
        var f_yqbh = $("#btn_yq").val();
        //传入能源树联动
        get_altertree_esub(f_yqbh);
        gettree_branchalert(_yqbh);
      },

      //生成能源树
      get_altertree_sub2: function () {
        var fNybh = $("#btn_ny").val();
        _yqbh = $("#btn_yq").val();
        //生成支路报警树
        gettree_branchalert(_yqbh, fNybh);

      },
      //搜索
      searchbranchalert: function () {
        getbranchalert_chlildtree();
      },

      thisEditAlertName: function () {
        return thisEditAlertName();
      },
      //验证增加模态框是否弹出
      show_addbranchalert: function () {

        if (_yqbh == '') {
          swal({
            title: "请选择园区",
            text: "经检测，您要未选择所属园区!",
            type: "warning",
            showCancelButton: false,
            confirmButtonColor: "#1ab394",
            confirmButtonText: "关闭",
            closeOnConfirm: false
          });
        } else {
          var Selectednode = $('#tree_branchalert').treeview('getSelected');
          if (_yqlog == 1 && Selectednode.length == 0) {
            swal({
              title: "请选择节点",
              text: "经检测，您要未选择分项树节点!",
              type: "warning",
              showCancelButton: false,
              confirmButtonColor: "#1ab394",
              confirmButtonText: "关闭",
              closeOnConfirm: false
            });
          } else {
            $('#modal-form-addbranchalertConf').modal('show');
          }
        }
      },


      pageInit: function () {
        basedatamanage_energyinformation_branchalertConf.get_altertree_sub();
      }
    }
  })(jQuery, window, document);

  basedatamanage_energyinformation_branchalertConf.pageInit();
</script>
