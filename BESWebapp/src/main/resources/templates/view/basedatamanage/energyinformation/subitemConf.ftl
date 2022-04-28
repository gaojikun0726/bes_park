<!-----内容区域---->
<!-- 组织机构树模块 -->

<div class="leftarea information_left" id="leftsubitemconf">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择分项>>>
		</span>
  </div>

  <div id="tree_subitem" class="Information_area" style="height:calc(100% - 7vh)"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
  <div class="information_size">
    <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;能源分项数据配置列表>>>
			</span>
      <!-- 增加按钮 -->
      <a id="addsubitem" href="javascript:void(-1);"
         onclick="basedatamanage_energyinformation_subitemConf.show_addsubitem();" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
      </a>
      <!-- 生成园区下拉框  -->
      <div style="display:inline-block ;">
        <select id='btn_yqsubitem' required class="form-control selectpicker" style="height:2.8vh;width:100%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_energyinformation_subitemConf.getSubitemConfData()" data-live-search="false">
        </select>
      </div>
      <div style="display:inline-block ;margin-left: 15px;">
        <select id='sel_nysubitem' required class="form-control selectpicker" style="height:2.8vh;width:115%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_energyinformation_subitemConf.getSubitemConfData()" data-live-search="false">
        </select>
      </div>

      <div style="display:inline-block ;margin-left: 30px;">
        <select id='sel_buildingInformation' required class="form-control selectpicker" style="height:2.8vh;width:115%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_energyinformation_subitemConf.getSubitemConfData()" data-live-search="false">
        </select>
      </div>
      <a id="addsubitem" href="javascript:void(-1);"
         onclick="basedatamanage_energyinformation_subitemConf.addsubitem_OneClickAdd();" class="btn btn-primary toLeft" style="margin-left: 30px;">
        <i class="fa fa-undo" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>一键添加
      </a>
      <!-- 搜索框 -->
      <div class="zc_search find">
        <input type="text" class="find-style" id="subitemInfo" name="subitemInfo" placeholder="分项名称、编号">
        <button id="querysubitem" onclick="basedatamanage_energyinformation_subitemConf.searchsubitem()"><i
                  class="fa fa-search" aria-hidden="true"></i></button>
      </div>
    </div>
    <div id="subitemTable" class="Information_area"></div>
  </div>
</div>
<!-- 信息表格模块end -->

<!---添加分项配置开始----->
<div class="modal fade" id="modal-form-addsubitem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp; 添加分项信息</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="addsubitemform" name="addsubitemform" class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id="subitemconf_fUserName" name="subitemconf_fUserName" required class="form-control">

							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id="subitemconf_fZZJG" name="subitemconf_fZZJG" required class="form-control">

							</select>
						</div>
					</div>

          <div class="form-group">
            <label class="col-sm-3 control-label">分项编号<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fFxbh" name="fFxbh" placeholder="请输入分项编号" required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">分项名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fFxmc" name="fFxmc" placeholder="请输入分项名称" required class="form-control">
            </div>
          </div>
            <#--<div class="form-group">
                <label class="col-sm-3 control-label">园区编号<span class="text-danger">*</span></label>
                <div class="col-sm-8">
                    <select id="btn_nysubitem_add" name="btn_nysubitem_add" required class="form-control">
                    </select>

    </div>
            </div>-->

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

<!----编辑分项--->
<div class="modal fade" id="editSubitemForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp; 编辑分项信息</h4>
      </div>
      <div class="modal-body">
        <form id="edit_SubitemForm" name="edit_SubitemForm" class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id="edit_subitemconf_fUserName" name="edit_subitemconf_fUserName"  class="form-control">

							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id="edit_subitemconf_fZZJG" name="edit_subitemconf_fZZJG" required class="form-control">

							</select>
						</div>
					</div>

          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fFxbh">分项编号 <span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="hidden" id="edit_fGuid" name="edit_fGuid" required class="form-control">
              <input type="text" id="edit_fFxbh" name="edit_fFxbh" disabled required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fFxmc">分项名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fFxmc" name="edit_fFxmc" required class="form-control">
            </div>
          </div>

            <#--<div class="form-group">
                    <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                    <div class="col-sm-8">
                        <select id="edit_fNybh_subitem" name="edit_fNybh_subitem" required class="form-control">
                        </select>
                    </div>
            </div>-->
            <#--<div class="form-group">
             <label class="col-sm-3 control-label" for="edit_fZzjgbh_subitem">园区编号<span class="text-danger">*</span></label>
             <div class="col-sm-8">
                           <select id="edit_fYqbh_subitem" name="edit_fYqbh_subitem" required class="form-control">
                           </select>
             </div>
           </div>-->
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

<!----包含支路支路--->
<div class="modal fade" id="includeSubitemBrc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content" style="width:1185px;left: -288px">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title">包含支路</h4>
      </div>
      <div class="modal-body" style="height:600px;">
        <div style="float:left;width:54.5%">
          <button class="btn btn-md" style="cursor:default"><span>未选择</span></button>
        </div>
        <div style="float:left;width:40%">
          <button class="btn btn-md" style="cursor:default;float: left"><span>已选择</span></button>
          <label class="col-sm-2 control-label">是否级联:</label>
          <div class="col-sm-4">
            <input type="radio" name="f_jl_subitem" value="0" checked="checked"/>是
            <input type="radio" name="f_jl_subitem" value="1"/>否
          </div>
        </div>
        <!--未选择table+搜索）- -->
        <div class="notIncludeCss">

          <!-- <div style="padding:2px 2px 2px 10px;height:6%;">
          <div style="float:left;">
          <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="notincludeBrcKeywords" name="notincludeBrcKeywords" value="" placeholder="支路编号、名称">
          </div>
          <div style="float:left;">
          <span class="input-group-btn"  style="width: 60px;">
         <button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBrc" onclick="basedatamanage_energyinformation_subitemConf.searchNotIncludeBrc()">
                   <i class="fa fa-search"></i> 搜索
                 </button>
             </span>
          </div>
          </div> -->
          <!-- 搜索框 -->
          <div class="zc_search_special_subitem find">
            <input type="text" class="zc_search_special_specialyle" id="notsubitem_includeBrcKeywords"
                   name="notsubitem_includeBrcKeywords" placeholder="支路编号、名称">
            <button id="queryNotIncludeBrc"
                    onclick="basedatamanage_energyinformation_subitemConf.searchNotIncludeBrc()"><i class="fa fa-search"
                                                                                                    aria-hidden="true"></i>
            </button>
          </div>
          <div id="subitemBrc_noInclude" style="margin-top:10px;overflow: auto;">
          </div>
        </div>
        <!-- 未选择用户结束- -->


        <!--操作开始- -->
        <div style="width:100px;height:400px;float:left">
          <div id="BrcrightMove" style="margin-top:200px;margin-left:23px;">
            <button id="subitemConf_right" type="button"
                    onclick="basedatamanage_energyinformation_subitemConf.BrcrightMove()" class="btn btn-primary">>>
            </button>
          </div>
          <div id="BrcleftMove" style="margin-top:20px;margin-left:23px;">
            <button id="subitemConf_left" type="button"
                    onclick="basedatamanage_energyinformation_subitemConf.BrcleftMove()" class="btn btn-primary"><<
            </button>
          </div>
        </div>
        <!--操作结束- -->


        <!--包含用户开始- -->
        <div class="includeCss">

          <div style="position: relative;padding:2px 2px 2px 10px;height:100%;">
            <!-- <div style="float:left;">
             <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="includeBrcKeywords" name="includeBrcKeywords" value="" placeholder="支路编号、名称">
             </div>
             <div style="float:left;">
             <span class="input-group-btn"  style="width: 60px;">
            <button class="btn btn-primary btn-sm m-b-none" id="queryIncludeBrc" onclick="basedatamanage_energyinformation_subitemConf.searchIncludeBrc()">
                      <i class="fa fa-search"></i> 搜索
                    </button>
                </span>
             </div>
             </div> -->
            <!-- 搜索框 -->
            <div class="zc_search_special_subitem find">
              <input type="text" class="find-style" id="subitem_includeBrcKeywords" name="subitem_includeBrcKeywords"
                     placeholder="支路编号、名称">
              <button id="queryIncludeBrc" onclick="basedatamanage_energyinformation_subitemConf.searchIncludeBrc()"><i
                        class="fa fa-search" aria-hidden="true"></i></button>
            </div>
            <div id="subitemBrc_include" style="overflow: auto;margin-top:10px;">
            </div>
          </div>
          <!--包含用户结束- -->
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 组织机构树  update by liuzhen 20181120-->
    <#--<div class="modal fade" id="modal-zzjgtree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width:380px;">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                    <h4 class="modal-title">关联组织机构</h4>
                </div>
                <div class="modal-body" style="height:600px;overflow-y: auto;">
              <div id="sub_tree_zzjg"></div>
          </div>
                    <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>

            </div>
        </div>
    </div>-->
</div>
<style>
  .includeCss {
    float: left;
    width: 510px;
    height: 480px;
    border: 1px solid rgba(121, 194, 218, 0.44);
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    margin: 5px 0;
    padding: 10px 0 10px 0;
    background-color: #0c2939;
  }

  .notIncludeCss {
    float: left;
    width: 510px;
    height: 480px;
    border: 1px solid rgba(121, 194, 218, 0.44);
    -webkit-border-radius: 6px;
    -moz-border-radius: 6px;
    border-radius: 6px;
    margin: 5px 0;
    padding: 10px 0 10px 0;
    background-color: #0c2939;
  }

  .zc_search_special_subitem {
    margin-left: 2vw;
    width: 16.2vw;
    height: 2.9vh;
    border-radius: 4px;
    display: flex !important;
    justify-content: center;
    align-items: center;
	/*background: #001b3a;*/
    border:1px solid lightblue;
  }
</style>

<script type="text/javascript">
  ;
  var basedatamanage_energyinformation_subitemConf = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var _subitemFxbh = "00";//分项配置编码
    var _subitemJs = "0";//用户组对应的级数
    var _fxbh = "00";
    //var _zzjgbh = "";//组织机构编号
    var _zzjglog = "0";//判断组织机构下是否有分项配置
    var _curRow = null;//对应行
    var _includecurRow = null;//“已选择”table对应行
    var _notincludecurRow = null;//“未选择”table对应行
    var Selected_tree = null;//组织机构树被选中的节点
    var Selected_sub = null;//分项树被选中的节点


    $(function () {
      $("input[type=radio][name='f_jl_subitem']").change(function () {
        changef_jl_subitem(this.value);
      });
    });
    //设置格式
    var optIconFunction = function (cell, formatterParams) { //plain text value
      var subitemid = cell.getRow().getData().fGuid;
      var subitemFxbh = cell.getRow().getData().fFxbh;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm include' data-id=" + subitemFxbh + " data-toggle='modal' data-target='#includeSubitemBrc'><i class='fa fa-user'></i>  包含支路</button>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + subitemid + " data-toggle='modal' data-target='#editSubitemForm'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-fFxbh=" + subitemFxbh + " data-id=" + subitemid + "><i class='fa fa-trash'></i>  删除</button></div>"
    };


    //创建并设置table属性
    $("#subitemTable").tabulator({
      height: "100%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      //selectable:true,
      movableColumns: true,
      columns: [
        {title: "序号", field: "id", width: 80, formatter: "rownum", frozen: false, sorter: "string", align: "center"}, //frozen column
        {title: "分项编号", field: "fSubitemCode", width: 85, sorter: "string", editor: false, align: "center", headerSort: false}, //never hide this column
        {title: "分项名称",field: "fFxmc",width: 100,sorter: "string",editor: false,align: "center",headerSort: false}, //hide this column first
        {title: "能源编号",field: "fNybh",width: 100,sorter: "string",editor: false,align: "center", headerSort: false},
        {title: "创建时间", field: "fCrdate", sorter: "date", align: "center", editable: false, headerSort: false},
        {title: "修改时间", field: "fChdate", sorter: "date", align: "center", editor: false, headerSort: false},
        {title: "操作",field: "opt",width: 250,tooltip: false,tooltipsHeader: false,align: "center",formatter: optIconFunction,headerSort: false},
      ],
      rowClick: function (e, row) {
        _curRow = row;
        var id = _curRow.getData().fFxbh;
        var choiseNode = $('#tree_subitem').treeview('findNodes', [_curRow.getData().fFxbh, 'id']);
        if (choiseNode.length > 1) {
          for (var i = 0; i < choiseNode.length; i++) {
            if (choiseNode[i].id == id) {
              $('#tree_subitem').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
            }
          }
        } else {
          $('#tree_subitem').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
        }
      },
    });

    //填充数据---按照组织机构=》分项=》列表顺序加载，所以不直接填充数据
    //$("#subitemTable").tabulator("setData", _ctx+'/view/basedatamanage/getSubitemConfList');


    $(window).resize(function () {
      $("#subitemTable").tabulator("redraw");
    });

    //加载分项 树
    function gettree_subitem(fnybh, fyqbh,fBudingId) {
      $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/energyinformation/subitemConftree",
        data: ({
          fNybh: fnybh,
          fYqbh: fyqbh,
          fBudingId : fBudingId
        }),
        dataType: "json",
        success: function (result) {
          if (result.list == undefined) {
            Selected_sub = null;
            $('#tree_subitem').treeview('remove');
            $("#subitemTable").tabulator("setData", []);
            _subitemJs = "1";
            _zzjglog = "0";
          } else {
            _zzjglog = "1";
            $('#tree_subitem').treeview({
              data: result.list,         // 数据源
              highlightSelected: true,    //是否高亮选中
              levels: 4,
              enableLinks: true,//必须在节点属性给出href属性
              color: "#4a4747",
              onNodeSelected: function (event, nodeData) {
                $('#tree_subitem').treeview('clearSearch');//清除搜索选中高亮
                _subitemFxbh = nodeData.id;
                _subitemJs = nodeData.level;
                getSubitem_chlildtree(_subitemFxbh, _subitemJs);
                Selected_sub = $('#tree_subitem').treeview('getSelected');
              }
            });
            //初始加载根节点
// 	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
// 	            	if(result.list.length >0){//如果包含判断是否长度大于0
// 			            var firstNode = $("#tree_subitem").treeview('findNodes',[result.list[0].id,'id']);
// 			        	$("#tree_subitem").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
// 	            	}
// 	            }
// 		            if(Selected_sub == null){
            _subitemFxbh = result.list[0].id;
            _subitemJs = result.list[0].level;
            var firstNode = $("#tree_subitem").treeview('findNodes', [result.list[0].id, 'id']);
            $("#tree_subitem").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
            //获取子节点
            getSubitem_chlildtree(_subitemFxbh, _subitemJs);
            // }
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    function getSubitem_chlildtree(_subitemFxbh, _subitemJs) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/subitem_chlildtreegrid",
        type: "post",
        data: {
          fFxbh: _subitemFxbh,
          f_js: _subitemJs
        },
        success: function (nodeData2) {
          if (nodeData2.hasOwnProperty('list') == false) {
            $("#subitemTable").tabulator("setData", []);
          } else {
            $("#subitemTable").tabulator("setData", nodeData2.list);
          }
        },
        error: function (nodeData2) {
          swal(nodeData2.msg, "", "error");
        }
      });

    }

    //触发搜索的回车时间
    $("#subitemInfo").focus(function () {
      $(this).keydown(function (e) {
        if (e.which == "13") {
          basedatamanage_energyinformation_subitemConf.searchsubitem();//触发该事件
        }
      })
    });

    //select标签查询
    function fNybhsubitem(keywords) {
      $.ajax({
        type: "POST",
        url: _ctx + '/view/common/selectfNybhList',
        data: "",
        success: function (data) {
          var ops = "<option value=''>请选择能源编号</option>";
          for (var i = 0; i < data.list.length; i++) {
            var obj = data.list[i];
            ops += '<option value="' + obj.fNybh + '">';
            ops += obj.fNybh + '(' + obj.fNymc + ')';
            ops += '</option>';
          }
          if (keywords == 'add') {
            $("#fNybh_subitem").append(ops);
          } else {
            $("#edit_fNybh_subitem").append(ops);
          }
        },
        error: function (msg) {
          alert("select列表查询失败!");
        }
      });
    }

    //添加用户组表单验证
    var groupValidator = $("#addsubitemform").validate({
      rules: {
        fFxmc: {
          required: true,
          rangelength: [1, 40]
        }
      },
      messages: {

        fFxmc: {
          required: "请填写分项名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        }
      },
      submitHandler: function (nodeData) {
        addformsubitem(nodeData);
      }
    });

    //新增保存
    function addformsubitem(nodeData) {
      if (_zzjglog == 0) {
        _subitemFxbh = "";
      }
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/add_subitem",
        type: "post",
        data: ({
					fUserName: $("#subitemconf_fUserName").val(),
					fZzjgId: $("#subitemconf_fZZJG").val(),
          fSubitemCode: $("#fFxbh").val(),
          fFxmc: $("#fFxmc").val(),
          fNybh: $("#sel_nysubitem").val(),
          // fYqbh:$("#btn_nysubitem_add").val(),
          fYqbh: $("#btn_yqsubitem").val(),
          fBudingId: $("#sel_buildingInformation").val(),
          fPfxbh: _subitemFxbh,
          fJs: _subitemJs
        }),
        success: function (data) {
          if (data.status == '1') {
            swal({
              title: data.msg,// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            $('#modal-form-addsubitem').modal('hide');//关闭编辑窗口
            //在表格中添加数据
            $('#subitemTable').tabulator("addRow", data.list);
            //在树上添加

            var pNode = $("#tree_subitem").treeview("getSelected");
            if (pNode.length != 0 && pNode != '' && Selected_sub) {
              $("#tree_subitem").treeview("addNode", [{
                id: data.list[0].fFxbh,
                text: data.list[0].fFxmc,
                pid: pNode[0].id,
                js: data.list[0].fJs
              }, pNode]);
            } else {
              var nybh = $("#sel_nysubitem").val();
              var yqbh = $("#btn_yqsubitem").val();
              let buildingbh = $("#sel_buildingInformation").val();
              gettree_subitem(nybh, yqbh,buildingbh);
            }

          } else {
            swal(data.msg, "", "warning");
          }
        },
        error: function (data) {
          swal(data.msg, "", "error");
        }
      });
      return false;
    }


    //居中显示（添加）
    $('#modal-form-addsubitem').on('show.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-form-addsubitem .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })

    //居中显示（编辑）
    $('#editSubitemForm').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#editSubitemForm .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })

    //居中显示（包含用户）
    $('#includeSubitemBrc').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#includeSubitemBrc .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    //居中显示（组织机构）
    $('#modal-zzjgtree').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-zzjgtree .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })

    //关闭模态框清空表单值
    $("#modal-form-addsubitem").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
			$(this).find("select").val("");
      $("#fNybh_subitem").empty();
      groupValidator.resetForm();
    });

    $("#includeSubitemBrc").on('hidden.bs.modal', function (event) {
      $("#notsubitem_includeBrcKeywords").val('');
      $("#subitem_includeBrcKeywords").val('');
    })

    //删除数据
    $(document).on('click', '#subitemTable button.delete', function () {
      var id = $(this).data("id");
      var fFxbh = $(this).data("ffxbh");
      var dNode = $('#tree_subitem').treeview('findNodes', [fFxbh, 'id']);

      if (dNode.length >= 1) {

        for (var i = 0; i < dNode.length; i++) {
          if (dNode[i].id == fFxbh) {
            var booljuge = dNode[i].hasOwnProperty('nodes');
            if (booljuge == true) {
              swal({
                title: "请您先删除用户组下的子组！",
                text: "经检测，您要删除的用户组包含子组!",
                type: "warning",
                showCancelButton: false,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "关闭",
                closeOnConfirm: false
              });
            } else {
              deleteGroup(id, dNode[i]);//删除
            }
          }
        }
      } else {
        deleteGroup(id, dNode[0]);
      }
    });


    function deleteGroup(id, node) {
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
                url: _ctx + "/view/basedatamanage/energyinformation/del_subitem",
                type: "post",
                data: {
                  "fGuid": id
                },
                success: function (data) {
                  if (data.status == '1') {
                    swal({
                      title: data.msg,// 展示的标题
                      text: "",// 内容
                      type: "success",
                      showCloseButton: false, // 展示关闭按钮
                      allowOutsideClick: false,
                      showConfirmButton: false,
                      timer: 1000
                    });
                    //在表格中删除该条数据
                    $("#subitemTable").tabulator("deleteRow", _curRow);
                    //在树上删除该条数据
                    var nodeParents = $('#tree_subitem').treeview('getParents', node);

                    if (nodeParents.length > 0) {
                      $("#tree_subitem").treeview("selectNode", nodeParents);
                    } else {
                      Selected_sub = null;
                      _subitemFxbh = null;
                    }

                    var getGroupTable = $("#subitemTable").tabulator("getData");
                    $("#subitemTable").tabulator("setData", getGroupTable);
                    $("#tree_subitem").treeview("removeNode", node, {silent: true});
                  } else {
                    swal(data.msg, "", "error");
                  }
                },
                error: function (data) {
                  swal(data.msg, "", "error");
                }
              });
            }, 100
        )
      });
    }

    //表单验证
    $("#edit_SubitemForm").validate({
      submitHandler: function (form) {
        editSubitemForm(form);
      }
    });

    //编辑分项
    function editSubitemForm(form) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/edit_subitem",
        type: "post",
        data: ({
					fUserName: $("#edit_subitemconf_fUserName").val(),
					fZzjgId: $("#edit_subitemconf_fZZJG").val(),
          fGuid: $("#edit_fGuid").val(),
          // fFxbh: $("#edit_fFxbh").val(),
          fFxmc: $("#edit_fFxmc").val(),
          // fNybh: $("#edit_fNybh_subitem").val(),
          // fYqbh:$("#edit_fYqbh_subitem").val()
        }),
        success: function (data) {
          if (data.status == '1') {
            swal({
              title: data.msg,// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            $('#subitemTable').tabulator("updateRow", _curRow,
                {
                  fFxmc: data.list[0].fFxmc,
                  fNybh: data.list[0].fNybh,
                  fChdate: data.list[0].fChdate
                });
            //在树上修改
            var parentNode = $('#tree_subitem').treeview('findNodes', [_curRow.getData().fFxbh, 'id'])
            var newNode = {text: _curRow.getData().fFxmc};
            $('#tree_subitem').treeview('updateNode', [parentNode, newNode]);
            setTimeout(function () {
              $('#editSubitemForm').modal('hide');//关闭编辑窗口
            }, 1000)
          } else {
            swal("修改失败!", data.msg, "error");
          }
        },
        error: function (data) {
          swal("修改失败!", data.msg, "error");
        }
      });
    }

    //验证在模态框出现前加载编辑
    $("#editSubitemForm").on('show.bs.modal', function (event) {
      $("#edit_fNybh_subitem").empty();
      // fNybhsubitem('edit');
      var button = $(event.relatedTarget);
      var id = button.data("id");//获取用户组编号
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/querySubitem",
        type: "post",
        data: {
          "fGuid": id
        },
        success: function (result) {
					$("#edit_subitemconf_fUserName").val(result.list[0].fUserName);
					$("#edit_subitemconf_fZZJG").val(result.list[0].fZzjgId);
          // $("#edit_fNybh_subitem").val(result.list[0].fNybh);
          $("#edit_fGuid").val(result.list[0].fGuid);
          $("#edit_fFxbh").val(result.list[0].fSubitemCode);
          $("#edit_fFxmc").val(result.list[0].fFxmc);
          // $("#edit_fYqbh_sFubitem").val(result.list[0].fYqbh);
        }
      });
    });

    //验证在模态框出现前加载编辑(组织机构树)
    $("#modal-zzjgtree").on('show.bs.modal', function () {
      basedatamanage_energyinformation_subitemConf.get_zzjgtree_sub();
    });
    //验证码在模态框出现前加载包含用户(可拖动)
    $("#includeSubitemBrc").on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget);
      var id = button.data("id");			//获取用户组编号
      $(this).draggable({
        handle: ".modal-header"     	// 只能点击头部拖动
      });
      $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
      loadShowCascade(id);//显示级联
      loadNoIncludeBrc(id);
      loadIncludeBrc(id);
      _fxbh = id;
    });

    //显示是否级联
    function loadShowCascade(id) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadShowCascade_subitem",
        type: "post",
        data: ({
          fFxbh: id
        }),
        success: function (data) {
          if (data.status == "0") {
            return;
          } else {
            if (data.data == "0") {

              document.getElementsByName("f_jl_subitem")[0].checked = true;
            } else {
              document.getElementsByName("f_jl_subitem")[1].checked = true;
            }
          }
        }
      });
    }

    //分项添加支路
    $(document).on('click', '#subitemBrc_noInclude button.insertBranch', function () {
      var f_yhbh = $(this).data("id");
      var f_jl = $("input[name='f_jl_subitem']:checked").val();
      $("#subitemConf_left").attr('disabled', false);
      $("#subitemConf_right").attr('disabled', false);
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/subitemconf_insertBranch",
        type: "post",
        data: ({
          fFxbh: _fxbh,
          fZlbh: f_yhbh,
          fJl: f_jl
        }),
        success: function (data) {
          if (data.status == '1') {
            //swal(data.msg, "", "success");
            //在未选择表格中删除该条数据
            $("#subitemBrc_noInclude").tabulator("deleteRow", _notincludecurRow);
            //在已选择表格中添加该条数据
            $('#subitemBrc_include').tabulator("addRow", {fZlbh: data.data.fZlbh, fZlmc: data.data.fZlmc});
            //未包含用户表格：
            var noinclude_group_data = $("#subitemBrc_noInclude").tabulator("getData");
            // $("#subitemBrc_noInclude").tabulator("setData", noinclude_group_data);
            //已包含用户表格：
            var include_group_data = $("#subitemBrc_include").tabulator("getData");
            // $("#subitemBrc_include").tabulator("setData", include_group_data);
            if (noinclude_group_data.length == 0) {
              $("#subitemConf_right").attr('disabled', true);
              $("#subitemConf_left").attr('disabled', false);
            }
          } else {
            swal(data.msg, "", "error");
          }
        },
        error: function (data) {
          swal(data.msg, "", "error");
        }
      });
    });

    function get_altertree_esub(_yqbh) {
      $("#sel_nysubitem").empty();

      $.ajax({
        type: "post",
        url: _ctx + '/view/basedatamanage/energyinformation/getlist',
        data: {
          f_yqbh: _yqbh
        },
        success: function (data) {
          document.getElementById("sel_nysubitem").options.length = 0;
          // if (typeof ($("#sel_nysubitem").options) != 'undefined') {
          //   $("#sel_nysubitem").options.length = 0;
          // }
          //var ops = "<option value=''>请选择园区名称</option>";
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

            //获取建筑信息
            getBuilding(_Nybh, _yqbh);

          }
          $("#sel_nysubitem").append(ops);
        },
        error: function (msg) {
          alert("select列表查询失败!");
        }

      });
    }

    //移除分项中的支路
    $(document).on('click', '#subitemBrc_include button.deleteBranch', function () {
      var f_yhbh = $(this).data("id");
      var f_jl = $("input[name='f_jl_subitem']:checked").val();
      $("#subitemConf_left").attr('disabled', false);
      $("#subitemConf_right").attr('disabled', false);
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/subitemconf_deleteBranch",
        type: "post",
        data: ({
          fFxbh: _fxbh,
          fZlbh: f_yhbh,
          fJl: f_jl
        }),
        success: function (data) {
          if (data.status == '1') {
            //swal(data.msg, "", "success");
            //在已选择表格中删除该条数据
            $("#subitemBrc_include").tabulator("deleteRow", _includecurRow);
            //在未选择表格中添加该条数据
            $('#subitemBrc_noInclude').tabulator("addRow", {fZlbh: data.data.fZlbh, fZlmc: data.data.fZlmc});

            //未包含用户表格：
            var noinclude_group_data = $("#subitemBrc_noInclude").tabulator("getData");
            // $("#subitemBrc_noInclude").tabulator("setData", noinclude_group_data);
            //已包含用户表格：
            var include_group_data = $("#subitemBrc_include").tabulator("getData");
            // $("#subitemBrc_include").tabulator("setData", include_group_data);
            if (include_group_data.length == 0) {
              $("#subitemConf_left").attr('disabled', true);
              $("#subitemConf_right").attr('disabled', false);
            }
          } else {
            swal(data.msg, "", "error");
          }
        },
        error: function (data) {
          swal(data.msg, "", "error");
        }
      });
    });

    //设置“未选择”格式
    var optIconNoIncludeFunc = function (cell, formatterParams) {
      var idyhbh = cell.getRow().getData().fZlbh;
      return "<button class='btn btn-white btn-sm insertBranch' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
    };
    //设置“已选择”格式
    var optIconInclude = function (cell, formatterParams) {
      var idyhbh = cell.getRow().getData().fZlbh;
      return "<button class='btn btn-white btn-sm deleteBranch' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
    };

    //创建并设置“未选择”table属性
    $("#subitemBrc_noInclude").tabulator({
      height: "93.3%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      //selectable:true,
      //placeholder:"无数据，请添加用户",
      movableColumns: true,
      columns: [
        {
          title: "序号",
          width: 50,
          formatter: "rownum",
          frozen: false,
          sorter: "number",
          align: "center",
          headerSort: false
        },
        {
          title: "支路编号",
          field: "fZlbh",
          width: 135,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "支路名称",
          field: "fZlmc",
          width: 240,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "操作",
          field: "opt",
          width: 80,
          tooltip: false,
          tooltipsHeader: false,
          align: "center",
          formatter: optIconNoIncludeFunc,
          headerSort: false
        },
      ],
      rowClick: function (e, not_row) {
        _notincludecurRow = not_row;
      },
    });

    //创建并设置“已选择”table属性
    $("#subitemBrc_include").tabulator({
      height: "93.3%",
      layout: "fitColumns",
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      //selectable:true,
      //placeholder:"无数据，请添加用户",
      //movableColumns:true,
      columns: [
        {
          title: "序号",
          width: 50,
          formatter: "rownum",
          frozen: false,
          sorter: "number",
          align: "center",
          headerSort: false
        },
        {
          title: "支路编号",
          field: "fZlbh",
          width: 135,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "支路名称",
          field: "fZlmc",
          width: 230,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "操作",
          field: "opt",
          width: 80,
          tooltip: false,
          tooltipsHeader: false,
          align: "center",
          formatter: optIconInclude,
          headerSort: false
        },
      ],
      rowClick: function (e, in_row) {
        _includecurRow = in_row;
      },
    });

    //加载未选择用户表
    function loadNoIncludeBrc(id, keywords) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadNoIncludeBrc",
        type: "post",
        data: ({
          fFxbh: id,
          keywords: keywords
        }),
        success: function (data) {
          //填充“未选择”数据
          if (data.hasOwnProperty('list') == false) {
            $("#subitemBrc_noInclude").tabulator("setData", []);
          } else {
            $("#subitemBrc_noInclude").tabulator("setData", data.list);
          }
          var noinclude_group_data = $("#subitemBrc_noInclude").tabulator("getData");
          if (noinclude_group_data.length == 0) {
            $("#subitemConf_right").attr('disabled', true);
            $("#subitemConf_left").attr('disabled', false);
          } else {
            $("#subitemConf_right").attr('disabled', false);
          }
        },
        error: function (data) {
          swal("修改失败!", data.msg, "error");
        }
      });
    }

    //加载已选择用户表
    function loadIncludeBrc(id, keywords) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadIncludeBrc",
        type: "post",
        data: ({
          fFxbh: id,
          keywords: keywords
        }),
        success: function (data) {
          //填充“已选择”数据
          if (data.hasOwnProperty('list') == false) {
            $("#subitemBrc_include").tabulator("setData", []);
          } else {
            $("#subitemBrc_include").tabulator("setData", data.list);
          }

          var include_group_data = $("#subitemBrc_include").tabulator("getData");
          if (include_group_data.length == 0) {
            $("#subitemConf_left").attr('disabled', true);
            $("#subitemConf_right").attr('disabled', false);
          } else {
            $("#subitemConf_left").attr('disabled', false);
          }
        },
        error: function (data) {
          swal("修改失败!", data.msg, "error");
        }
      });
    }

    //修改是否级联
    function changef_jl_subitem(value) {
      $.ajax({
        url: _ctx + '/view/basedatamanage/energyinformation/changef_jl_subitem',
        type: "post",
        data: {
          fJl: value,
          fFxbh: _fxbh
        }
      });
    }

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
					$("#subitemconf_fUserName").append(ops);
					$("#edit_subitemconf_fUserName").append(ops);
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
					$("#subitemconf_fZZJG").append(ops);
					$("#edit_subitemconf_fZZJG").append(ops);
				}
			});
		}

		function getBuilding(_Nybh, _yqbh) {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: '${ctx}/view/basedatamanage/energyinformation/getBuildingList',
        success: function(result){

          if (result.list.length == 0) {
            swal({
              title: "请先添加建筑基本信息",// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            return;
          }
          document.getElementById("sel_buildingInformation").options.length = 0;
          // if (typeof ($("#sel_buildingInformation").options) != "undefined") {
          //   $("#sel_buildingInformation").options.length = 0;
          // }

          var ops= null;
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.f_buding_id+'">';
            ops+=obj.f_buding_name;
            ops+='</option>';
          }

          gettree_subitem(_Nybh, _yqbh,result.list[0].f_buding_id);
          $("#sel_buildingInformation").append(ops);
        }
      });
    }

    function  uuid() {
      var  s = [];
      var  hexDigits =  "0123456789abcdef" ;
      for  ( var  i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] =  "4" ;   // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);   // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] =  "-" ;

      var  uuid = s.join( "" );
      return  uuid;
    }
    return {
      /*
      //生成组织机构树
       get_zzjgtree_sub : function (){
            $.ajax({
                 type: "post",
                 url: _ctx + "/view/user/zzjg_tree",
                 dataType: "json",
                 success: function (result) {
                     $('#sub_tree_zzjg').treeview({
                         data: result.list,         // 数据源
                         highlightSelected: true,    //是否高亮选中
                         levels : 4,
                         enableLinks : true,//必须在节点属性给出href属性
                         color: "#4a4747",
                        onNodeSelected: function (event, nodeData) {
                        $('#sub_tree_zzjg').treeview('clearSearch');//清除搜索选中高亮
                            //_zzjgbh = nodeData.id;
                            //_zzjgJs = nodeData.js;
                        //在组织机构按钮 显示 已选中的组织机构
                        //$("#btn_zzjg").text(nodeData.id+'--'+nodeData.text);
                        gettree_subitem();
                        Selected_tree= $('#sub_tree_zzjg').treeview('getSelected');
                         }
                     });
                     //如果被选中的节点为null，则默认选择第一个根节节点
                     if(Selected_tree == null){
                         //update by liuzhen
                       //_zzjgbh = result.list[0].id;
                         //$("#btn_zzjg").text(_zzjgbh+'--'+result.list[0].text);
                      //生成分项树
                      gettree_subitem();
                     }
                 },
                 error: function (nodeData) {
                     swal( nodeData.msg,"", "error");
                 }
             });
       },*/
      //搜索
      searchsubitem: function () {
        var subitemInfo = $("#subitemInfo").val();
        $.ajax({
          url: _ctx + '/view/basedatamanage/energyinformation/getSubitemConfList',
          type: "post",
          data: {
            fFxmc: subitemInfo,
          },
          beforeSend: function () {
            showLoad();
          },
          success: function (data) {
            //$("#subitemTable").tabulator("setData",data.list);
            if (data.hasOwnProperty('list') == false) {
              $("#subitemTable").tabulator("setData", []);
            } else {
              $("#subitemTable").tabulator("setData", data.list);
            }

          },
          complete: function () {
            hiddenLoad();
          },
          error: function (data) {
            swal(data.msg, "", "error");
          }
        });
      },

      //搜索已包含用户
      searchIncludeBrc: function () {
        var includeBrcKeywords = $("#subitem_includeBrcKeywords").val();
        loadIncludeBrc(_fxbh, includeBrcKeywords);
        //$("#subitemBrc_include").tabulator("setData", _ctx+'/view/basedatamanage/loadGroupRlglUser?fFxbh='+_fxbh+'&keywords='+includeBrcKeywords);
      },

      //搜索未包含用户
      searchNotIncludeBrc: function () {
        var notincludeBrcKeywords = $("#notsubitem_includeBrcKeywords").val();
        loadNoIncludeBrc(_fxbh, notincludeBrcKeywords);
        //$("#subitemBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeBrc?fFxbh='+_fxbh+'&keywords='+notincludeBrcKeywords);
      },

      //全部右移
      BrcrightMove: function () {
        var subitemConf_tem = $("#subitemBrc_noInclude").tabulator("getData");
        var f_jl = $("input[name='f_jl_subitem']:checked").val();
        var notincludeBrcKeywords = $("#notsubitem_includeBrcKeywords").val();
        if (subitemConf_tem.length < 1) {
          $("#subitemConf_right").attr('disabled', true);
        } else {
          $.ajax({
            url: _ctx + "/view/basedatamanage/energyinformation/subitemconf_rightmoveAll",
            type: "post",
            data: ({
              fFxbh: _fxbh,
              fJl: f_jl,
              keywords: notincludeBrcKeywords
            }),
            success: function (data) {

              if (data.status == '1') {
                loadIncludeBrc(_fxbh);
                $("#subitemBrc_noInclude").tabulator("setData", []);
                $("#subitemConf_right").attr('disabled', true);
                $("#subitemConf_left").attr('disabled', false);
              } else {
                swal(data.msg, "", "error");
              }
            },
            error: function (data) {
              swal(data.msg, "", "error");
            }
          });
        }
      },
      //全部左移
      BrcleftMove: function () {
        var subitemConf_tem = $("#subitemBrc_include").tabulator("getData");
        var f_jl = $("input[name='f_jl_subitem']:checked").val();
        var includeBrcKeywords = $("#subitem_includeBrcKeywords").val();
        if (subitemConf_tem.length < 1) {
          $("#subitemConf_left").attr('disableincludeBrcKeywordsd', true);
        } else {
          $.ajax({
            url: _ctx + "/view/basedatamanage/energyinformation/subitemconf_leftmoveAll",
            type: "post",
            data: ({
              fFxbh: _fxbh,
              fJl: f_jl,
              keywords: includeBrcKeywords
            }),
            success: function (data) {
              if (data.status == '1') {
                //swal(data.msg, "", "success");
                $("#subitemBrc_include").tabulator("setData", []);
                //$("#subitemBrc_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeBrc?fFxbh='+_fxbh);
                loadNoIncludeBrc(_fxbh);
                $("#subitemConf_right").attr('disabled', false);
                $("#subitemConf_left").attr('disabled', true);
              } else {
                swal(data.msg, "", "error");
              }
            },
            error: function (data) {
              swal(data.msg, "", "error");
            }
          });
        }
      },

      //生成园区树
      get_yqtree_sub: function () {

        $.ajax({
          type: "get",
          url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
          success: function (data) {
            document.getElementById("btn_yqsubitem").options.length = 0;
            // if (typeof ($("#btn_yqsubitem").options) != "undefined") {
            //   $("#btn_yqsubitem").options.length = 0;
            // }
            // if (typeof ($("#btn_nysubitem_add").options) != "undefined") {
            //   $("#btn_nysubitem_add").options.length = 0;
            // }
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
              //gettree_household(_yqbh);
            }
            $("#btn_yqsubitem").append(ops);
            $("#btn_nysubitem_add").append(ops);
            // $("#edit_fYqbh_subitem").append(ops);

          },
          error: function (msg) {
            alert("select列表查询失败!");
          }


        });
      },
      //验证增加模态框是否弹出
      show_addsubitem: function () {
        /*var Selectednode = $('#tree_subitem').treeview('getSelected');
        if(_zzjglog == 1 && Selectednode.length == 0){
            swal({
                title: "请选择节点",
                text: "经检测，您要未选择分项树节点!",
                type: "warning",
                showCancelButton: false,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "关闭",
                closeOnConfirm: false
            });
        }else{
            $('#modal-form-addsubitem').modal('show');
            fNybhsubitem('add');
            //$("#fZzjgbh_subitem").val(_zzjgbh);
        }*/

        $('#modal-form-addsubitem').modal('show');

      },
      //一键添加
      addsubitem_OneClickAdd: function() {

        swal({
              title: "您确定要一键添加吗?",
              text: "原先节点将被清除!",
              type: "warning",
              showCancelButton: true,
              confirmButtonColor: "#1ab394",
              confirmButtonText: "确定",
              closeOnConfirm: false
            }, function () {
          let fNybh = $("#sel_nysubitem").val();
          let fYqbh = $("#btn_yqsubitem").val();
          let buildingbh = $("#sel_buildingInformation").val();
          let list = [];

          if (fNybh == "01000") {//电
            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"01000",fFxmc:"电",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_oneuuid = uuid();
            let secondLevelNode_one = {fFxbh:secondLevelNode_oneuuid,fSubitemCode:"01A00",fFxmc:"照明插座用电",fPfxbh:firstLevelNodeuuid,fJs:"2",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            let secondLevelNode_one_oneuuid = uuid();
            let secondLevelNode_one_one = {fFxbh:secondLevelNode_one_oneuuid,fSubitemCode:"01A10",fFxmc:"室内照明",fPfxbh:secondLevelNode_oneuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_one_twouuid = uuid();
            let secondLevelNode_one_two = {fFxbh:secondLevelNode_one_twouuid,fSubitemCode:"01A20",fFxmc:"走廊与应急照明",fPfxbh:secondLevelNode_oneuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_one_threeuuid = uuid();
            let secondLevelNode_one_three = {fFxbh:secondLevelNode_one_threeuuid,fSubitemCode:"01A30",fFxmc:"室外照明",fPfxbh:secondLevelNode_oneuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_one_fouruuid = uuid();
            let secondLevelNode_one_four = {fFxbh:secondLevelNode_one_fouruuid,fSubitemCode:"01A40",fFxmc:"办公设备插座",fPfxbh:secondLevelNode_oneuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};


            let secondLevelNode_twouuid = uuid();
            let secondLevelNode_two = {fFxbh:secondLevelNode_twouuid,fSubitemCode:"01B00",fFxmc:"空调用电",fPfxbh:firstLevelNodeuuid,fJs:"2",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            let secondLevelNode_two_oneuuid = uuid();
            let secondLevelNode_two_one = {fFxbh:secondLevelNode_two_oneuuid,fSubitemCode:"01B10",fFxmc:"冷热站",fPfxbh:secondLevelNode_twouuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            let secondLevelNode_two_one_oneuuid = uuid();
            let secondLevelNode_two_one_one = {fFxbh:secondLevelNode_two_one_oneuuid,fSubitemCode:"01B1A",fFxmc:"冷水机组",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_one_twouuid = uuid();
            let secondLevelNode_two_one_two = {fFxbh:secondLevelNode_two_one_twouuid,fSubitemCode:"01B1B",fFxmc:"冷却泵",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_one_threeuuid = uuid();
            let secondLevelNode_two_one_three = {fFxbh:secondLevelNode_two_one_threeuuid,fSubitemCode:"01B1C",fFxmc:"冷冻泵",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_one_fouruuid = uuid();
            let secondLevelNode_two_one_four = {fFxbh:secondLevelNode_two_one_fouruuid,fSubitemCode:"01B1D",fFxmc:"冷却塔风机",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_one_fiveuuid = uuid();
            let secondLevelNode_two_one_five = {fFxbh:secondLevelNode_two_one_fiveuuid,fSubitemCode:"01B1E",fFxmc:"采暖泵",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_one_sixuuid = uuid();
            let secondLevelNode_two_one_six = {fFxbh:secondLevelNode_two_one_sixuuid,fSubitemCode:"01B1F",fFxmc:"锅炉",fPfxbh:secondLevelNode_two_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_twouuid = uuid();
            let secondLevelNode_two_two = {fFxbh:secondLevelNode_two_twouuid,fSubitemCode:"01B20",fFxmc:"空调末端",fPfxbh:secondLevelNode_twouuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_two_threeuuid = uuid();
            let secondLevelNode_two_three = {fFxbh:secondLevelNode_two_threeuuid,fSubitemCode:"01B30",fFxmc:"分散空调",fPfxbh:secondLevelNode_twouuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};


            let secondLevelNode_threeuuid = uuid();
            let secondLevelNode_three = {fFxbh:secondLevelNode_threeuuid,fSubitemCode:"01C00",fFxmc:"动力用电",fPfxbh:firstLevelNodeuuid,fJs:"2",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_three_oneuuid = uuid();
            let secondLevelNode_three_one = {fFxbh:secondLevelNode_three_oneuuid,fSubitemCode:"01C10",fFxmc:"电梯",fPfxbh:secondLevelNode_threeuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_three_twouuid = uuid();
            let secondLevelNode_three_two = {fFxbh:secondLevelNode_three_twouuid,fSubitemCode:"01C20",fFxmc:"水泵",fPfxbh:secondLevelNode_threeuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_three_threeuuid = uuid();
            let secondLevelNode_three_three = {fFxbh:secondLevelNode_three_threeuuid,fSubitemCode:"01C30",fFxmc:"通风机",fPfxbh:secondLevelNode_threeuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_three_fouruuid = uuid();
            let secondLevelNode_three_four = {fFxbh:secondLevelNode_three_fouruuid,fSubitemCode:"01C40",fFxmc:"电开水器",fPfxbh:secondLevelNode_threeuuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};


            let secondLevelNode_fouruuid = uuid();
            let secondLevelNode_four = {fFxbh:secondLevelNode_fouruuid,fSubitemCode:"01D00",fFxmc:"特殊用电",fPfxbh:firstLevelNodeuuid,fJs:"2",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_four_oneuuid = uuid();
            let secondLevelNode_four_one  = {fFxbh:secondLevelNode_four_oneuuid,fSubitemCode:"01D10",fFxmc:"信息中心",fPfxbh:secondLevelNode_fouruuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_four_one_oneuuid = uuid();
            let secondLevelNode_four_one_one  = {fFxbh:secondLevelNode_four_one_oneuuid,fSubitemCode:"01D1A",fFxmc:"信息设备",fPfxbh:secondLevelNode_four_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_four_one_twouuid = uuid();
            let secondLevelNode_four_one_two  = {fFxbh:secondLevelNode_four_one_twouuid,fSubitemCode:"01D1B",fFxmc:"信息中心专用空调",fPfxbh:secondLevelNode_four_oneuuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            let secondLevelNode_four_twouuid = uuid();
            let secondLevelNode_four_two  = {fFxbh:secondLevelNode_four_twouuid,fSubitemCode:"01D20",fFxmc:"厨房设备",fPfxbh:secondLevelNode_fouruuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_four_two_oneuuid = uuid();
            let secondLevelNode_four_two_one  = {fFxbh:secondLevelNode_four_two_oneuuid,fSubitemCode:"01D2A",fFxmc:"厨房炊事设备",fPfxbh:secondLevelNode_four_twouuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            let secondLevelNode_four_two_twouuid = uuid();
            let secondLevelNode_four_two_two  = {fFxbh:secondLevelNode_four_two_twouuid,fSubitemCode:"01D2B",fFxmc:"厨房空调风机",fPfxbh:secondLevelNode_four_twouuid,fJs:"4",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            let secondLevelNode_four_threeuuid = uuid();
            let secondLevelNode_four_three = {fFxbh:secondLevelNode_four_threeuuid,fSubitemCode:"01D30",fFxmc:"特殊用途设备",fPfxbh:secondLevelNode_fouruuid,fJs:"3",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};

            list.push(firstLevelNode);
            list.push(secondLevelNode_one);
            list.push(secondLevelNode_one_one);
            list.push(secondLevelNode_one_two);
            list.push(secondLevelNode_one_three);
            list.push(secondLevelNode_one_four);
            list.push(secondLevelNode_two);
            list.push(secondLevelNode_two_one);
            list.push(secondLevelNode_two_one_one);
            list.push(secondLevelNode_two_one_two);
            list.push(secondLevelNode_two_one_three);
            list.push(secondLevelNode_two_one_four);
            list.push(secondLevelNode_two_one_five);
            list.push(secondLevelNode_two_one_six);
            list.push(secondLevelNode_two_two);
            list.push(secondLevelNode_two_three);
            list.push(secondLevelNode_three);
            list.push(secondLevelNode_three_one);
            list.push(secondLevelNode_three_two);
            list.push(secondLevelNode_three_three);
            list.push(secondLevelNode_three_four);
            list.push(secondLevelNode_four);
            list.push(secondLevelNode_four_one);
            list.push(secondLevelNode_four_one_one);
            list.push(secondLevelNode_four_one_two);
            list.push(secondLevelNode_four_two);
            list.push(secondLevelNode_four_two_one);
            list.push(secondLevelNode_four_two_two);
            list.push(secondLevelNode_four_three);

          } else if (fNybh == "02000") {//水

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"02000",fFxmc:"水",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);

          } else if (fNybh == "03A00") {//天然气

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"03A00",fFxmc:"天然气",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);
          } else if (fNybh == "03B00") {//焦炉燃气

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"03B00",fFxmc:"焦炉燃气",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);
          } else if (fNybh == "03C00") {//其他燃气

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"03C00",fFxmc:"其他燃气",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);
          } else if (fNybh == "04000") {//集中供热量

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"04000",fFxmc:"集中供热量",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);
          }  else if (fNybh == "05000") {//集中供冷量

            let firstLevelNodeuuid = uuid();
            let firstLevelNode = {fFxbh:firstLevelNodeuuid,fSubitemCode:"05000",fFxmc:"集中供冷量",fPfxbh:"",fJs:"1",fNybh:fNybh,fYqbh:fYqbh,fJl:"0",fBudingId:buildingbh};
            list.push(firstLevelNode);
          }

          list = JSON.stringify(list);

          $.ajax({
            url: _ctx + "/view/basedatamanage/energyinformation/addsubitem_OneClickAdd",
            data:{
              // persons:list,
              fNybh:fNybh,
              fYqbh:fYqbh,
              buildingbh:buildingbh
            },
            type: "post",
            success:function(data){
              if (data.status == '1') {

                swal({
                  title: data.msg,// 展示的标题
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  showConfirmButton: false,
                  timer: 1000
                });
                gettree_subitem(fNybh,fYqbh,buildingbh);
                // basedatamanage_energyinformation_subitemConf.get_yqtree_sub();

              } else {
                swal(data.msg, "", "warning");
              }
            }
          });

        })

      },
      getSubitemConfData: function () {
        let nybh = $("#sel_nysubitem").val();
        let yqbh = $("#btn_yqsubitem").val();
        let buildingbh = $("#sel_buildingInformation").val();
        gettree_subitem(nybh, yqbh,buildingbh);
      },
      pageInit: function () {
        basedatamanage_energyinformation_subitemConf.get_yqtree_sub();
				getuserName();//获取用户信息
				getZZJG();//获取组织机构信息

      }
    }

  })(jQuery, window, document);

  basedatamanage_energyinformation_subitemConf.pageInit();
</script>