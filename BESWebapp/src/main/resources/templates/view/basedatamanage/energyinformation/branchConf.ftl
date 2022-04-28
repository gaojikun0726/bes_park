<!-----内容区域---->


<style>
  .ztree li a.curSelectedNode {
    padding-top: 0px;
    background-color: rgba(31, 255, 8, 0);
    color: black;
    height: 16px;
    border: 1px #FFB951 solid;
    opacity: 0.8;
  }

  #tree_device_type_modal_curtainconfig {
    position: absolute;
    left: 71%;
    top: 11.5%;
  }
</style>
<!-- 组织机构树模块 -->
<div class="leftarea information_left" id="leftbranchconf">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择支路>>>
		</span>
  </div>

  <div id="tree_branch" class="Information_area" style="height:calc(100% - 7vh)"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
  <div class="information_size">
    <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;支路计量拓扑配置列表>>>
			</span>
      <!-- 增加按钮 -->
      <a id="addbranch" href="javascript:void(-1);"
         onclick="basedatamanage_efficiencyanalysis_branchConf.show_addbranch();" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
      </a>

      <#--批量整理支路树结构-->
      <a id="addbranch" href="javascript:void(-1);"
         onclick="basedatamanage_efficiencyanalysis_branchConf.tidyTree();" class="btn btn-primary toLeft">
        <i class="fa" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>整理树
      </a>

      <!-- 生成园区下拉框  -->
      <div style="display:inline-block ;">
        <select id='btn_yqbrach' name='btn_yqhouse' required class="form-control selectpicker" style="height:2.8vh;width:100%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_efficiencyanalysis_branchConf.getBrachConfData()" data-live-search="false">
        </select>
      </div>
      <div style="display:inline-block ;margin-left: 15px;">
        <select id='btn_nybrach' name='btn_nyhouse' required class="form-control selectpicker" style="height:2.8vh;width:115%;padding: 0px 12px; border: 1px solid #bbb;
					background-color:rgb(216, 239, 255);color:#000000;"
                onchange="basedatamanage_efficiencyanalysis_branchConf.getBrachConfData()" data-live-search="false">
        </select>
      </div>
      <!-- 搜索框 -->
      <div class="zc_search find">
        <input type="text" class="find-style" id="branchInfo" name="branchInfo" placeholder="支路名称、编号">
        <button id="querybranch" onclick="basedatamanage_efficiencyanalysis_branchConf.searchbranch()"><i
                  class="fa fa-search" aria-hidden="true"></i></button>
      </div>
    </div>
    <div id="branchTable" class="Information_area"></div>
  </div>
</div>
<!-- 信息表格模块end -->
<!---添加用户组信息开始----->
<div class="modal fade" id="modal-form-addbranch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">&nbsp;添加支路信息</h4>
      </div>
      <div class="modal-body">
        <form role="form" id="addbranchform" name="addbranchform" class="form-horizontal">

          <div class="form-group">
            <label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="fUserName" name="fUserName" required class="form-control">

              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="fZZJG" name="fZZJG" required class="form-control">

              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">支路名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="fZlmc" name="fZlmc" placeholder="请输入支路名称" required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">实际编号<span class="text-danger"> &nbsp;</span></label>
            <div class="col-sm-8">
              <input type="text" id="fRealBh" name="fRealBh" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">所属位置<span class="text-danger"> &nbsp;</span></label>
            <div class="col-sm-8">
              <input type="text" id="fLocation" name="fLocation" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">额定功率<span class="text-danger"> &nbsp;</span></label>
            <div class="col-sm-8">
              <input type="text" id="fRatedPower" name="fRatedPower" class="form-control">
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

<!----编辑支路--->
<div class="modal fade" id="editBranchForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp;编辑支路信息</h4>
      </div>
      <div class="modal-body">
        <form id="edit_BranchForm" name="edit_BranchForm" class="form-horizontal">

          <div class="form-group">
            <label class="col-sm-3 control-label">选择用户<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_fUserName" name="edit_fUserName"  class="form-control">

              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">选择组织机构<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <select id="edit_fZZJG" name="edit_fZZJG" required class="form-control">

              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_f_zbh">支路编号 <span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fZlbh" name="edit_fZlbh" required class="form-control" readonly="readonly">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_f_zmc">支路名称<span class="text-danger">*</span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fZlmc" name="edit_fZlmc" required class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_f_RealBh">实际编号<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fRealBh" name="edit_fRealBh" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_f_Location">所属位置<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fLocation" name="edit_fLocation" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label" for="edit_fRatedPower">额定功率<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="edit_fRatedPower" name="edit_fRatedPower" class="form-control">
            </div>
          </div>
            <#--<div class="form-group">
                            <label class="col-sm-3 control-label">设备<span class="text-danger">*</span></label>
                            <input type="hidden" id="edit_hidden_fEquipment">
                            <div id="edit_fEquipment" class="display_flex" style="margin-left: 28%;"></div>
                    </div>-->
            <#--<div class="form-group">
                            <label class="col-sm-3 control-label">设备集<span class="text-danger">*</span></label>
                            <input type="hidden" id="edit_hidden_fEquipment_Set">
                            <div id="edit_fEquipment_Set" class="display_flex" style="margin-left: 28%;"></div>
                    </div>-->
            <#-- <div class="form-group">
                 <label class="col-sm-3 control-label">园区编号<span class="text-danger">*</span></label>
                 <div class="col-sm-8">
                     <select id="yqbh_edit_branch" name="yqbh_edit_branch" required class="form-control">

                     </select>
                 </div>
             </div>-->
            <#-- <div class="form-group">
                     <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                     <div class="col-sm-8">
                         <select id="edit_fNybh_branch" name="edit_fNybh_branch" required class="form-control">
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

<!----包含支路电表--->
<div class="modal fade" id="includeBranchAmt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 90vw;left: -30vw;height: 80vh;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title">包含支路电表</h4>
      </div>
      <div class="modal-body" style="height:76vh;">
        <div style="float:left;width:54%">
          <button class="btn btn-md" style="cursor:default"><span>未选择</span></button>
        </div>
        <div style="float:left;width:46%">
          <button class="btn btn-md" style="cursor:default;float: left"><span>已选择</span></button>
          <label class="col-sm-2 control-label">是否级联:</label>
          <div class="col-sm-4">
            <input type="radio" name="f_jl_branch" value="0" checked="checked"/>是
            <input type="radio" name="f_jl_branch" value="1"/>否
          </div>
        </div>
        <!----未选择table+搜索）--->
        <div class="notIncludeCss" style="width: 46%;height: 93%;">

          <!-- 搜索框 -->
          <div class="zc_search_special_branch find" style="width: 36vw;">
            <input type="text" class="zc_search_special_specialyle" id="notincludeAmtKeywords"
                   name="notincludeAmtKeywords" placeholder="电表编号、名称">
            <button id="queryNotIncludeAmt"
                    onclick="basedatamanage_efficiencyanalysis_branchConf.searchNotIncludeAmt()"><i class="fa fa-search"
                                                                                                    aria-hidden="true"></i>
            </button>
          </div>
          <div id="branchAmt_noInclude" style="margin-top:10px;">
          </div>
        </div>
        <!----未选择用户结束--->


        <!----操作开始--->
        <div style="width:8%;height:400px;float:left">
          <div id="AmtrightMove" style="margin-top:200px;text-align: -webkit-center;">
            <button id="branchConf_right" type="button"
                    onclick="basedatamanage_efficiencyanalysis_branchConf.AmtrightMove()" class="btn btn-primary">>>
            </button>
          </div>
          <div id="AmtleftMove" style="margin-top:20px;text-align: -webkit-center;">
            <button id="branchConf_left" type="button"
                    onclick="basedatamanage_efficiencyanalysis_branchConf.AmtleftMove()" class="btn btn-primary"><<
            </button>
          </div>
        </div>
        <!----操作结束--->


        <!----包含用户开始--->
        <div class="includeCss" style="width: 46%;height: 93%;">

          <!-- 搜索框 -->
          <div class="zc_search_special_branch find" style="width: 36vw;">
            <input type="text" class="zc_search_special_specialyle" id="includeAmtKeywords" name="includeAmtKeywords"
                   placeholder="电表编号、名称">
            <button id="queryIncludeAmt" onclick="basedatamanage_efficiencyanalysis_branchConf.searchIncludeAmt()"><i
                      class="fa fa-search" aria-hidden="true"></i></button>
          </div>
          <div id="branchAmt_include" style="margin-top:10px;">
          </div>
          <div>
            <!----包含用户结束--->
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 组织机构树 -->
    <div class="modal fade" id="modal-branch-zzjgtree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog">
        <div class="modal-content" style="width:380px">
          <div class="modal-header bg-primary">
            <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
            <h4 class="modal-title">关联组织机构</h4>
          </div>
          <div class="modal-body" style="height:600px;overflow-y: auto;">
            <div id="tree_zzjg_branch"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
          </div>

        </div>
      </div>
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

      .zc_search_special_branch {
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
  </div>
</div>


<script type="text/javascript">
  ;
  var basedatamanage_efficiencyanalysis_branchConf = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var _branchZlbh = "00";//用户组编码
    var _branchJs = "0";//用户组对应的级数
    var _fzbh = "00";
    var _curRow = null;//对应行
    var _includecurRow = null;//“已选择”table对应行
    var _notincludecurRow = null;//“未选择”table对应行
    var _zzjgbhbran = "";//组织机构编号
    var _zzjglog = "0";//判断组织机构下是否有分项配置
    var Selected_tree_branch = null;//组织机构树被选中的节点
    var Selected_branch = null;//支路被选中的节点
    var EquipmentSet = "";//设备集id
    var Selected_tree = null;//组织机构树被选中的节点

    var AllAmmeter = null;//所有电表的数组
    var NoAllAmmeter = [];//未加载电表的数组
    var YesAllAmmeter = [];//已加载电表的数组

    var YesAllAmmeter1 = [];//已加载电表的数组

    $(function () {
      $("input[type=radio][name='f_jl_branch']").change(function () {
        changef_jl_branch(this.value);
      });
    });
    //设置格式
    var optIconFunction = function (cell, formatterParams) { //plain text value
      var branchid = cell.getRow().getData().fZlbh;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm include' data-id=" + branchid + " data-toggle='modal' data-target='#includeBranchAmt'><i class='fa fa-user'></i>  包含电表</button>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + branchid + " data-toggle='modal' data-target='#editBranchForm'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + branchid + "><i class='fa fa-trash'></i>  删除</button></div>"
    };

    //创建并设置table属性
    $("#branchTable").tabulator({
      height: "100%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      //selectable:true,
      movableColumns: true,
      columns: [
        {title: "序号", field: "id", width: 70, formatter: "rownum", frozen: false, sorter: "string"}, //frozen column
        {title: "支路编号", field: "fZlbh", width: 85, sorter: "string", editor: false, align: "center", headerSort: false}, //never hide this column
        {
          title: "支路名称",
          field: "fZlmc",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        }, //hide this column first
        {
          title: "实际编号",
          field: "fRealBh",
          width: 85,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "所属位置",
          field: "fLocation",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "额定功率",
          field: "fRatedPower",
          width: 80,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "设备集",
          field: "fEquipmentSet",
          width: 80,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "能源编号",
          field: "fNybh",
          width: 100,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {title: "创建时间", field: "fCrdate", sorter: "date", align: "center", editable: false, headerSort: false},
        {title: "修改时间", field: "fChdate", sorter: "date", align: "center", editor: false, headerSort: false},
        {
          title: "操作",
          field: "opt",
          width: 250,
          tooltip: false,
          tooltipsHeader: false,
          align: "center",
          formatter: optIconFunction,
          headerSort: false
        },
      ],
      rowClick: function (e, row) {
        _curRow = row;
        var id = _curRow.getData().fZlbh;
        var choiseNode = $('#tree_branch').treeview('findNodes', [_curRow.getData().fZlbh, 'nodeTreeId']);
        if (choiseNode.length > 1) {
          for (var i = 0; i < choiseNode.length; i++) {
            if (choiseNode[i].nodeTreeId == id) {
              $('#tree_branch').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
            }
          }
        } else {
          $('#tree_branch').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
        }
      },
    });

    //填充数据---按照组织机构=》支路=》列表顺序加载，所以不直接填充数据
    //$("#branchTable").tabulator("setData", _ctx+'/view/basedatamanage/getBranchConf_treegrid');
    $(window).resize(function () {
      $("#branchTable").tabulator("redraw");
    });


    //加载树
    function gettree_branch(yqbh, nybh) {
      $.ajax({
        type: "post",
        url: _ctx + "/view/basedatamanage/energyinformation/branchalert_tree",
        data: {
          "fYqbh": yqbh,
          "fNybh": nybh
        },
        dataType: "json",
        success: function (result) {
          if (result.list == undefined) {
            Selected_branch = null;
            $('#tree_branch').treeview('remove');
            $("#branchTable").tabulator("setData", []);
            _branchJs = "1";
            _zzjglog = "0";
          } else {
            _zzjglog = "1";
            $('#tree_branch').treeview({
              data: result.list,         // 数据源
              highlightSelected: true,    //是否高亮选中
              levels: 4,
              enableLinks: true,//必须在节点属性给出href属性
              color: "#4a4747",
              onNodeSelected: function (event, nodeData) {
                $('#tree_branch').treeview('clearSearch');//清除搜索选中高亮
                _branchZlbh = nodeData.id;
                _branchJs = nodeData.level;
                getbranch_chlildtree(_branchZlbh, _branchJs);
                Selected_branch = $('#tree_branch').treeview('getSelected');
              }
            });
            //初始加载根节点
// 		            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
// 		            	if(result.list.length >0){//如果包含判断是否长度大于0
// 				            var firstNode = $("#tree_branch").treeview('findNodes',[result.list[0].id,'id']);
// 				        	$("#tree_branch").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
// 		            	}
// 		            }
// 	            if(Selected_branch == null){
            _branchZlbh = result.list[0].id;
            _branchJs = result.list[0].level;
            var firstNode = $("#tree_branch").treeview('findNodes', [result.list[0].id, 'id']);
            $("#tree_branch").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
            //获取子节点
            getbranch_chlildtree(_branchZlbh, _branchJs);
            // }
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    function getbranch_chlildtree(_branchZlbh, _branchJs) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/branch_chlildtreegrid",
        contentType: "application/json; charset=utf-8",
        type: "get",
        data: {
          fZlbh: _branchZlbh,
          f_js: _branchJs
        },
        success: function (nodeData2) {
          if (nodeData2.hasOwnProperty('list') == false) {
            $("#branchTable").tabulator("setData", []);
          } else {
            $("#branchTable").tabulator("setData", nodeData2.list);
          }
        },
        error: function (nodeData2) {
          swal(nodeData2.msg, "", "error");
        }
      });
    }

    //触发搜索的回车时间
    $("#branchInfo").focus(function () {
      $(this).keydown(function (e) {
        if (e.which == "13") {
          basedatamanage_efficiencyanalysis_branchConf.searchbranch();//触发该事件
        }
      })
    });
    //select标签查询
    /*function fNybhbranch(keywords){
      $.ajax({
            type: "POST",
            url: _ctx + '/view/common/selectfNybhList',
            data:"",
          success: function(data){
         var ops="<option value=''>请选择能源编号</option>";
         for(var i=0;i<data.list.length;i++){
           var obj=data.list[i];
           ops+='<option value="'+obj.fNybh+'">';
           ops+=obj.fNybh+'('+obj.fNymc+')';
           ops+='</option>';
         }
         if(keywords == 'add'){
           $("#fNybh_branch").empty().append(ops);
         }else{
           $("#edit_fNybh_branch").empty().append(ops);
         }
          },
          error:function(msg){
            alert( "select列表查询失败!" );
          }
      });
     }*/

    //添加用户组表单验证
    var groupValidator = $("#addbranchform").validate({
      rules: {
        fZlmc: {
          required: true,
          rangelength: [1, 40]
        }
      },
      messages: {

        fZlmc: {
          required: "请填写支路名称",
          minlength: jQuery.validator.format("Enter at least {0} characters")
        }
      },
      submitHandler: function (nodeData) {
        addformbranch(nodeData);
      }
    });


    //新增保存
    function addformbranch(nodeData) {
      if (_zzjglog == 0) {
        _branchZlbh = "";
      }
      $.issp_ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/add_branch",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          fUserName: $("#fUserName").val(),
          fZzjgId: $("#fZZJG").val(),
          fZlmc: $("#fZlmc").val(),
          fRealBh: $("#fRealBh").val(),
          fLocation: $("#fLocation").val(),
          fRatedPower: $("#fRatedPower").val(),
          // fNybh:$("#fNybh_branch").val(),
          fNybh: $("#btn_nybrach").val(),
          // fYqbh:$("#fYqbh_branch").val(),
          fYqbh: $("#btn_yqbrach").val(),
          fPzlbh: _branchZlbh,
          fJs: _branchJs,
          fEquipmentSet: EquipmentSet,
          // fEquipment : $("#fEquipment").val()
        }),
        success: function (data) {

          if (data.status != "0") {
            $('#modal-form-addbranch').modal('hide');//关闭编辑窗口
            //在表格中添加数据
            $('#branchTable').tabulator("addRow", data.list);
          }
          //在树上添加
          var pNode = $("#tree_branch").treeview("getSelected");

          if (pNode.length != 0 && pNode != '' && Selected_branch) {
            $("#tree_branch").treeview("addNode", [{
              nodeTreeId: data.list[0].fZlbh,
              id: data.list[0].fZlbh,
              text: data.list[0].fZlmc,
              pid: pNode[0].nodeTreeId,
              js: data.list[0].fJs
            }, pNode]);
          } else {
            var fNybh = $("#btn_nybrach").val();
            // var fNybh=$("#fNybh_branch").val();
            var fYqbh = $("#btn_yqbrach").val();
            // var fYqbh=$("#fYqbh_branch").val();
            gettree_branch(fYqbh, fNybh);
          }

        },
        error: function (data) {
          swal(data.msg, "", "error");
        }
      });
      return false;
    }

    //居中显示（添加）
    $('#modal-form-addbranch').on('show.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-form-addbranch .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    //居中显示（编辑）
    $('#editBranchForm').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#editBranchForm .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    //居中显示（包含用户）
    $('#includeBranchAmt').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#includeBranchAmt .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })

    //居中显示（组织机构）
    $('#modal-branch-zzjgtree').on('show.bs.modal', function () {
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#modal-branch-zzjgtree .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })


    //关闭模态框清空表单值
    $("#modal-form-addbranch").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      $(this).find("select").val("");
      groupValidator.resetForm();
    });
    //关闭包含模态框清空表单值
    $("#includeBranchAmt").on('hidden.bs.modal', function (event) {
      $("#branchAmt_noInclude").tabulator("setData", []);
      $("#branchAmt_include").tabulator("setData", []);
      $("#includeAmtKeywords").val('');
      $("#notincludeAmtKeywords").val('');
      //最后清空NoAllAmmeter,YesAllAmmeter
      NoAllAmmeter.length = 0;
      YesAllAmmeter.length = 0;

      // $(this).find("input").val("");
    });

    //删除数据
    $(document).on('click', '#branchTable button.delete', function () {
      var id = $(this).data("id");
      var dNode = $('#tree_branch').treeview('findNodes', [id, 'nodeTreeId']);
      if (dNode.length > 1) {
        for (var i = 0; i < dNode.length; i++) {
          if (dNode[i].nodeTreeId == id) {
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
              $.issp_ajax({
                url: _ctx + "/view/basedatamanage/energyinformation/del_branch",
                type: "post",
                data: {
                  "fZlbh": id
                },
                success: function (data) {

                  if (data.status != '1') {
                    swal({
                      title: data.msg,// 展示的标题
                      text: "", // 内容
                      type: "warning",
                      showCloseButton: false, // 展示关闭按钮
                      allowOutsideClick: false,
                      showConfirmButton: false,
                      timer: 3000
                    });

                    return;
                  }
                  swal({
                    title: data.msg,// 展示的标题
                    text: "", // 内容
                    type: "success",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                  });

                  var nodeParents = $('#tree_branch').treeview('getParents', node);

                  if (nodeParents.length > 0) {
                    $("#tree_branch").treeview("selectNode", nodeParents);
                  } else {
                    Selected_branch = null;
                    _fzbh = null;
                  }

                  //在表格中删除该条数据
                  $("#branchTable").tabulator("deleteRow", _curRow);
                  //在树上删除该条数据
                  // var dNode = $('#tree_branch').treeview('findNodes', [ id, 'nodeTreeId']);
                  var getGroupTable = $("#branchTable").tabulator("getData");
                  $("#branchTable").tabulator("setData", getGroupTable);
                  $("#tree_branch").treeview("removeNode", node, {silent: true});
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
    $("#edit_BranchForm").validate({
      submitHandler: function (form) {
        editBranchForm(form);
      }
    });

    //编辑支路
    function editBranchForm(form) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/edit_branch",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          fUserName: $("#edit_fUserName").val(),
          fZzjgId: $("#edit_fZZJG").val(),
          fZlbh: $("#edit_fZlbh").val(),
          fZlmc: $("#edit_fZlmc").val(),
          fRealBh: $("#edit_fRealBh").val(),
          fLocation: $("#edit_fLocation").val(),
          fRatedPower: $("#edit_fRatedPower").val(),
          // fNybh: $("#edit_fNybh_branch").val(),
          // fYqbh: $("#yqbh_edit_branch").val(),
          fEquipmentSet: EquipmentSet,
          // fEquipment : $("#edit_fEquipment").val()
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
            $('#branchTable').tabulator("updateRow", _curRow,
                {
                  fZlmc: data.list[0].fZlmc,
                  fRealBh: data.list[0].fRealBh,
                  fLocation: data.list[0].fLocation,
                  fRatedPower: data.list[0].fRatedPower,
                  fNybh: data.list[0].fNybh,
                  fChdate: data.list[0].fChdate
                });
            //在树上修改
            var parentNode = $('#tree_branch').treeview('findNodes', [_curRow.getData().fZlbh, 'nodeTreeId'])
            var newNode = {text: _curRow.getData().fZlmc};
            $('#tree_branch').treeview('updateNode', [parentNode, newNode]);
            setTimeout(function () {
              $('#editBranchForm').modal('hide');//关闭编辑窗口
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

    //验证在模态框出现前加载编辑支路
    $("#editBranchForm").on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget);
      var id = button.data("id");//获取用户组编号
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/queryBranch",
        type: "post",
        data: {
          "fZlbh": id
        },
        success: function (result) {
          $("#edit_fUserName").val(result.list[0].fUserName);
          $("#edit_fZZJG").val(result.list[0].fZzjgId);
          $("#edit_fZlbh").val(result.list[0].fZlbh);
          $("#edit_fZlmc").val(result.list[0].fZlmc);
          $("#edit_fRealBh").val(result.list[0].fRealBh);
          $("#edit_fLocation").val(result.list[0].fLocation);
          $("#edit_fRatedPower").val(result.list[0].fRatedPower);
          // $("#edit_fNybh_branch").val(result.list[0].fNybh);
          $("#edit_fZzjgbh_branch").val(result.list[0].fZzjgbh);
          // $("#yqbh_edit_branch").val(result.list[0].fYqbh);
        }
      });
    });

    //验证在模态框出现前加载编辑(组织机构树)
    $("#modal-branch-zzjgtree").on('show.bs.modal', function () {
      basedatamanage_efficiencyanalysis_branchConf.get_zzjgtree_branch();
    });


    //验证码在模态框出现前加载包含用户(可拖动)
    $("#includeBranchAmt").on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget);
      var id = button.data("id");			//获取用户组编号
      $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
      $(this).draggable({
        handle: ".modal-header"     	// 只能点击头部拖动
      });
      loadShowCascade(id);//显示级联
      loadNoIncludeAmt(id);
      // loadIncludeAmt(id);
      _fzbh = id;

    });

    //显示是否级联
    function loadShowCascade(id) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadShowCascade_Branch",
        type: "post",
        data: ({
          fZlbh: id
        }),
        success: function (data) {
          if (data.status == "0") {
            return;
          } else {
            if (data.data == "0") {

              document.getElementsByName("f_jl_branch")[0].checked = true;
            } else {
              document.getElementsByName("f_jl_branch")[1].checked = true;
            }
          }
        }
      });
    }

    //支路添加电表
    $(document).on('click', '#branchAmt_noInclude button.insertAmmeter', function () {
      var f_yhbh = $(this).data("id");
      var f_jl = $("input[name='f_jl_branch']:checked").val();
      $("#branchConf_right").attr('disabled', false);
      $("#branchConf_left").attr('disabled', false);
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/branchconf_insertAmmeter",
        type: "post",
        data: ({
          fZlbh: _fzbh,
          fDbsysName: f_yhbh,
          fOperator: "0",
          fJl: f_jl
        }),
        success: function (data) {
          if (data.status == '1') {
            //swal(data.msg, "", "success");
            //在未选择表格中删除该条数据
            $("#branchAmt_noInclude").tabulator("deleteRow", _notincludecurRow);
            //在已选择表格中添加该条数据
            $('#branchAmt_include').tabulator("addRow", {fSysNameOld: data.data.fSysNameOld, fNickName: data.data.fNickName});
            //未包含电表表格：
            var noinclude_group_data = $("#branchAmt_noInclude").tabulator("getData");
            // $("#branchAmt_noInclude").tabulator("setData", noinclude_group_data);
            //已包含电表表格：
            var include_group_data = $("#branchAmt_include").tabulator("getData");
            // $("#branchAmt_include").tabulator("setData", include_group_data);
            if (noinclude_group_data.length == 0) {
              $("#branchConf_right").attr('disabled', true);
              $("#branchConf_left").attr('disabled', false);
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

    //移除支路中的电表
    $(document).on('click', '#branchAmt_include button.deleteAmmeter', function () {
      var f_yhbh = $(this).data("id");
      var f_jl = $("input[name='f_jl_branch']:checked").val();
      var f_operator = $("#sel" + f_yhbh + "").val();;
      $("#branchConf_right").attr('disabled', false);
      $("#branchConf_left").attr('disabled', false);
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/branchconf_deleteAmmeter",
        type: "post",
        data: ({
          fZlbh: _fzbh,
          fDbsysName: f_yhbh,
          fJl: f_jl,
          fOperator:f_operator
        }),
        success: function (data) {
          if (data.status == '1') {
            //swal(data.msg, "", "success");
            //在已选择表格中删除该条数据
            $("#branchAmt_include").tabulator("deleteRow", _includecurRow);
            //在未选择表格中添加该条数据
            $('#branchAmt_noInclude').tabulator("addRow", {
              fSysNameOld: data.data.fSysNameOld,
              fNickName: data.data.fNickName
            });

            //未包含用户表格：
            var noinclude_group_data = $("#branchAmt_noInclude").tabulator("getData");
            // $("#branchAmt_noInclude").tabulator("setData", noinclude_group_data);
            //已包含用户表格：
            var include_group_data = $("#branchAmt_include").tabulator("getData");
            // $("#branchAmt_include").tabulator("setData", include_group_data);

            if (include_group_data.length == 0) {
              $("#branchConf_right").attr('disabled', false);
              $("#branchConf_left").attr('disabled', true);
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
      var idyhbh = cell.getRow().getData().fSysNameOld;
      return "<button class='btn btn-white btn-sm insertAmmeter' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
    };
    //设置“已选择”格式
    var optIconInclude = function (cell, formatterParams) {
      var idyhbh = cell.getRow().getData().fSysNameOld;
      var fOperator = cell.getRow().getData().fOperator;
      return "<button class='btn btn-white btn-sm deleteAmmeter' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
    };

    var tabselect = function (cell, formatterParams) {
      var idyhbh = cell.getRow().getData().fOperator;
      var fSysName = cell.getRow().getData().fSysNameOld;
      var fZlbh = _fzbh;
      return "<select id='sel" + fSysName + "' class='selector' style='width: 5em; height: 2em;' onchange='basedatamanage_efficiencyanalysis_branchConf.update_inclu_fOperator()'> "
          + "<option value='0'>" + "加" + "</option>"
          + "<option value='1'>" + "减" + "</option>"
          + "</select>"
    };

    //创建并设置“未选择”table属性
    $("#branchAmt_noInclude").tabulator({
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
          width: 70,
          formatter: "rownum",
          frozen: false,
          sorter: "number",
          align: "center",
          headerSort: false
        },
        {
          title: "电表编号",
          field: "fSysNameOld",
          width: 290,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "电表名称",
          field: "fNickName",
          width: 290,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "操作",
          field: "opt",
          width: 92,
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
    $("#branchAmt_include").tabulator({
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
          width: 70,
          formatter: "rownum",
          frozen: false,
          sorter: "number",
          align: "center",
          headerSort: false
        },
        {
          title: "电表编号",
          field: "fSysNameOld",
          width: 250,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {
          title: "电表名称",
          field: "fNickName",
          width: 250,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        },
        {title: "运算符", field: "fOperator", width: 80, align: "center", formatter: tabselect, headerSort: false},
        {
          title: "操作",
          field: "opt",
          width: 92,
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

    function aaa(a,b) {

      if (a.fDbsysName == b.fSysName) {
        
        return a.fOperator
      }
    }

    //加载未包含电表
    function loadNoIncludeAmt(id, keywords) {

      //根据支路编号查询所有的电表
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadAmmeterByBranchId",
        type: "post",
        data: ({
          fZlbh: id
          // keywords: keywords
        }),
        success: function (data) {
          
          if (data.list == null) {
            NoAllAmmeter = [...AllAmmeter];
            YesAllAmmeter = [];
          } else {
            AllAmmeter.map((el,index) => {

              if (!data.list.find(item => item.fDbsysName == el.fSysName)) {
                NoAllAmmeter.push(AllAmmeter[index])
              } else {
                
                let bbb = data.list.find(function (item) {
                  if (item.fDbsysName == el.fSysName) {
                    return item.fOperator
                  }
                })
                AllAmmeter[index].fOperator = bbb.fOperator;
                YesAllAmmeter.push(AllAmmeter[index] )
              }
            })


          }

          //填充“未选择”数据
          $("#branchAmt_noInclude").tabulator("setData", NoAllAmmeter);

          let noinclude_branch_data = $("#branchAmt_noInclude").tabulator("getData");
          if (noinclude_branch_data.length == 0) {
            $("#branchConf_right").attr('disabled', true);
            $("#branchConf_left").attr('disabled', false);
          } else {
            $("#branchConf_right").attr('disabled', false);
          }
          
          //填充“已选择”数据
          $("#branchAmt_include").tabulator("setData", YesAllAmmeter);
          let include_branch_data = $("#branchAmt_include").tabulator("getData");
          if (include_branch_data.length == 0) {
            $("#branchConf_right").attr('disabled', false);
            $("#branchConf_left").attr('disabled', true);
          } else {
            $("#branchConf_left").attr('disabled', false);
            //设置已包含表格中 运算符列的值
            for (var i = 0; i < include_branch_data.length; i++) {
              var fSysName = include_branch_data[i].fSysNameOld;
              var fOperator = include_branch_data[i].fOperator;
              $("#sel" + fSysName + "").val(fOperator);

            }
          }
        },
        error: function (data) {
          swal("加载未包含失败!", data.msg, "error");
        }
      });
    }

    //加载已包含电表
    function loadIncludeAmt(id, keywords) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadIncludeAmt",
        type: "post",
        data: ({
          fZlbh: id,
          keywords: keywords
        }),
        success: function (data) {
          //填充“已选择”数据
          if (data.hasOwnProperty('list') == false) {
            $("#branchAmt_include").tabulator("setData", []);
          } else {
            $("#branchAmt_include").tabulator("setData", data.list);
          }
          var include_branch_data = $("#branchAmt_include").tabulator("getData");
          if (include_branch_data.length == 0) {
            $("#branchConf_right").attr('disabled', false);
            $("#branchConf_left").attr('disabled', true);
          } else {
            $("#branchConf_left").attr('disabled', false);
            //设置已包含表格中 运算符列的值
            for (var i = 0; i < include_branch_data.length; i++) {
              var fSysName = include_branch_data[i].fSysNameOld;
              var fOperator = include_branch_data[i].fOperator;
              $("#sel" + fSysName + "").val(fOperator);

            }
          }
        },
        error: function (data) {
          swal("加载已包含失败!", data.msg, "error");
        }
      });

    }

    //园区选择传入能源树联动
    function get_altertree_esub(_yqbh) {
      $("#btn_nysubitem").empty();

      $.ajax({
        type: "post",
        url: _ctx + '/view/basedatamanage/energyinformation/getlist',
        data: {
          f_yqbh: _yqbh
        },
        success: function (data) {

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

            $("#btn_yqbrach").val();
            gettree_branch(_yqbh, _Nybh);
          }
          $("#btn_nybrach").append(ops);
          // $("#fNybh_branch").append(ops);

        },
        error: function (msg) {
          alert("select列表查询失败!");
        }

      });
    }

    //修改是否级联
    function changef_jl_branch(data) {
      $.ajax({
        url: _ctx + '/view/basedatamanage/energyinformation/changef_jl_branch',
        type: "post",
        data: {
          fJl: data,
          fZlbh: _fzbh
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
          $("#fUserName").append(ops);
          $("#edit_fUserName").append(ops);
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
          $("#fZZJG").append(ops);
          $("#edit_fZZJG").append(ops);
        }
      });
    }

    function getAllAmmeter() {
      $.ajax({
        url: _ctx + "/view/basedatamanage/energyinformation/loadAllAmmeter",
        type: "post",
        success: function (data) {
          AllAmmeter = data.list;
        },
        error: function (data) {
          swal("加载未包含失败!", data.msg, "error");
        }
      });
    }

    /**
     * 使用spilt方法实现模糊查询
     * @param  {Array}  list     进行查询的数组
     * @param  {String} keyWord  查询的关键词
     * @return {Array}           查询的结果
     */
    function NofuzzyQuery(list, keyWord) {
      let arr = [];
      for (var i = 0; i < list.length; i++) {
        if (list[i].fSysName.split(keyWord).length > 1) {
          arr.push(list[i]);
        }
      }
      return arr;
    }
    function YesfuzzyQuery(list, keyWord) {
      let arr = [];
      for (var i = 0; i < list.length; i++) {
        if (list[i].fSysNameOld.split(keyWord).length > 1) {
          arr.push(list[i]);
        }
      }
      return arr;
    }

    return {
      get_zzjgtree_branch: function () {
        $.ajax({
          type: "post",
          url: _ctx + "/view/user/zzjg_tree",
          dataType: "json",
          success: function (result) {
            $('#tree_zzjg_branch').treeview({
              data: result.list,         // 数据源
              highlightSelected: true,    //是否高亮选中
              levels: 4,
              enableLinks: true,//必须在节点属性给出href属性
              wrapNodeText: true,
              color: "#4a4747",
              onNodeSelected: function (event, nodeData) {
                $('#tree_zzjg_branch').treeview('clearSearch');//清除搜索选中高亮
                _zzjgbhbran = nodeData.id;
                _zzjgJs = nodeData.js;
                //	$("#btn_zzjg_branch").text(nodeData.id+'--'+nodeData.text);
                gettree_branch(_zzjgbhbran);
                Selected_tree_branch = $('#sub_tree_zzjg').treeview('getSelected');
              }
            });
            //如果被选中的节点为null，则默认选择第一个根节节点
            if (Selected_tree_branch == null) {
              _zzjgbhbran = result.list[0].id;
              //$("#btn_zzjg_branch").text(_zzjgbhbran+'--'+result.list[0].text);
              //生成支路树
              gettree_branch(_zzjgbhbran);
            }
          },
          error: function (nodeData) {
            swal(nodeData.msg, "", "error");
          }
        });
      },
      //搜索
      searchbranch: function () {
        var branchInfo = $("#branchInfo").val();
        $("#branchTable").tabulator("setData", _ctx + '/view/basedatamanage/energyinformation/getBranchConf_treegrid?keywords=' + branchInfo);
      },

      //搜索已包含用户
      searchIncludeAmt: function () {
        let includeAmtKeywords = $("#includeAmtKeywords").val();

        let list = YesfuzzyQuery(YesAllAmmeter,includeAmtKeywords);
        $("#branchAmt_include").tabulator("setData", list);
        let include_branch_data = $("#branchAmt_include").tabulator("getData");
        if (include_branch_data.length == 0) {
          $("#branchConf_right").attr('disabled', false);
          $("#branchConf_left").attr('disabled', true);
        } else {
          $("#branchConf_left").attr('disabled', false);
          //设置已包含表格中 运算符列的值
          for (var i = 0; i < include_branch_data.length; i++) {
            var fSysName = include_branch_data[i].fSysNameOld;
            var fOperator = include_branch_data[i].fOperator;
            $("#sel" + fSysName + "").val(fOperator);

          }
        }
        // loadIncludeAmt(_fzbh, includeAmtKeywords);
        //$("#branchAmt_include").tabulator("setData", _ctx+'/view/basedatamanage/loadGroupRlglUser?fZlbh='+_fzbh+'&keywords='+includeAmtKeywords);
      },

      //搜索未包含用户
      searchNotIncludeAmt: function () {
        let notincludeAmtKeywords = $("#notincludeAmtKeywords").val();


        let list = NofuzzyQuery(NoAllAmmeter,notincludeAmtKeywords);
        //填充“未选择”数据
        $("#branchAmt_noInclude").tabulator("setData", []);
        $("#branchAmt_noInclude").tabulator("setData", list);

        let noinclude_branch_data = $("#branchAmt_noInclude").tabulator("getData");
        if (noinclude_branch_data.length == 0) {
          $("#branchConf_right").attr('disabled', true);
          $("#branchConf_left").attr('disabled', false);
        } else {
          $("#branchConf_right").attr('disabled', false);
        }
        // loadNoIncludeAmt(_fzbh, notincludeAmtKeywords);
        //$("#branchAmt_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeAmt?fZlbh='+_fzbh+'&keywords='+notincludeAmtKeywords);
      },
      //园区下拉列表
      get_yqtree_sub: function () {
        $.ajax({
          type: "get",
          url: _ctx + '/view/basedatamanage/energyinformation/park_tree',
          success: function (data) {
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
            $("#btn_yqbrach").append(ops);
            // $("#fYqbh_branch").append(ops);
            // $("#yqbh_edit_branch").append(ops);

          },
          error: function (msg) {
            alert("select列表查询失败!");
          }


        });
      },

      //全部右移
      AmtrightMove: function () {
        var branchConf_tem = $("#branchAmt_noInclude").tabulator("getData");
        var f_jl = $("input[name='f_jl_branch']:checked").val();
        if (branchConf_tem.length < 1) {
          $("#branchConf_right").attr('disabled', true);
        } else {
          var notincludeAmtKeywords = $("#notincludeAmtKeywords").val();
          $.ajax({
            url: _ctx + "/view/basedatamanage/energyinformation/branchconf_rightmoveAll",
            type: "post",
            data: ({
              fZlbh: _fzbh,
              keywords: notincludeAmtKeywords,
              fJl: f_jl
            }),
            success: function (data) {
              if (data.status == '1') {
                // loadIncludeAmt(_fzbh);
                YesAllAmmeter = [...AllAmmeter];

                $("#branchAmt_noInclude").tabulator("setData", []);
                $("#branchConf_right").attr('disabled', true);
                $("#branchConf_left").attr('disabled', false);
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
      AmtleftMove: function () {
        var branchConf_tem = $("#branchAmt_include").tabulator("getData");
        var f_jl = $("input[name='f_jl_branch']:checked").val();
        if (branchConf_tem.length < 1) {
          $("#branchConf_left").attr('disabled', true);
        } else {
          var includeAmtKeywords = $("#includeAmtKeywords").val();
          $.ajax({
            url: _ctx + "/view/basedatamanage/energyinformation/branchconf_leftmoveAll",
            type: "post",
            data: ({
              fZlbh: _fzbh,
              keywords: includeAmtKeywords,
              fJl: f_jl
            }),
            success: function (data) {
              if (data.status == '1') {
                //swal(data.msg, "", "success");
                $("#branchAmt_include").tabulator("setData", []);
                //$("#branchAmt_noInclude").tabulator("setData", _ctx+'/view/basedatamanage/loadNoIncludeAmt?fZlbh='+_fzbh);
                // loadNoIncludeAmt(_fzbh);
                NoAllAmmeter = [...AllAmmeter];
                $("#branchConf_right").attr('disabled', false);
                $("#branchConf_left").attr('disabled', true);
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

      //验证增加模态框是否弹出
      show_addbranch: function () {
        /* var node = $('#tree_branch').treeview('getSelected');
       if(_zzjglog == 1 && node.length == 0){
         swal({
                 title: "请选择节点",
                 text: "经检测，您要未选择支路配置节点!",
                 type: "warning",
                 showCancelButton: false,
                 confirmButtonColor: "#1ab394",
                 confirmButtonText: "关闭",
                 closeOnConfirm: false
             });
       }else{
         $('#modal-form-addbranch').modal('show');
         $("#fZzjgbh_branch").val(_zzjgbhbran);
       }*/
        $('#modal-form-addbranch').modal('show');
      },

      update_inclu_fOperator: function () {
        var fZlbh = _fzbh;
        var fSysName = _includecurRow.getData().fSysNameOld;
        var fOperator = $("#sel" + fSysName + "").val();
        $.ajax({
          url: _ctx + "/view/basedatamanage/energyinformation/update_inclu_fOperator",
          type: "post",
          data: ({
            fZlbh: fZlbh,
            fSysName: fSysName,
            fOperator: fOperator
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
            } else {
              swal(data.msg, "", "error");
            }
          },
          error: function (data) {
            swal(data.msg, "", "error");
          }
        });

      },
      getBrachConfData: function () {
        var nybh = $("#btn_nybrach").val();
        var yqbh = $("#btn_yqbrach").val();
        gettree_branch(yqbh, nybh);
      },
      pageInit: function () {
        basedatamanage_efficiencyanalysis_branchConf.get_yqtree_sub();
        getuserName();//获取用户信息
        getZZJG();//获取组织机构信息
        getAllAmmeter();//获取所有的电表
      },

      /*整理树结构*/
      tidyTree: function() {

        swal({
          title: "是否需要重新整理支路树？请不要轻易整理",
          text: "",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#1ab394",
          confirmButtonText: "保存",
          cancelButtonText: "取消",
          closeOnConfirm: true,
          closeOnCancel: true
        }, function () {
          $.ajax({
            type: "POST",
            dataType: 'json',
            async: true,
            url: _ctx + "/view/basedatamanage/energyinformation/tidyTree",
            success: function(result){
             /* if (result.list == undefined) {
                Selected_branch = null;
                $('#tree_branch').treeview('remove');
                $("#branchTable").tabulator("setData", []);
                _branchJs = "1";
                _zzjglog = "0";
              } else {
                _zzjglog = "1";
                $('#tree_branch').treeview({
                  data: result.list,         // 数据源
                  highlightSelected: true,    //是否高亮选中
                  levels: 4,
                  enableLinks: true,//必须在节点属性给出href属性
                  color: "#4a4747",
                  onNodeSelected: function (event, nodeData) {
                    $('#tree_branch').treeview('clearSearch');//清除搜索选中高亮
                    _branchZlbh = nodeData.id;
                    _branchJs = nodeData.level;
                    getbranch_chlildtree(_branchZlbh, _branchJs);
                    Selected_branch = $('#tree_branch').treeview('getSelected');
                  }
                });
                _branchZlbh = result.list[0].id;
                _branchJs = result.list[0].level;
                var firstNode = $("#tree_branch").treeview('findNodes', [result.list[0].id, 'id']);
                $("#tree_branch").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                //获取子节点
                getbranch_chlildtree(_branchZlbh, _branchJs);
              }*/
            }
          });
        })

      }
    }

  })(jQuery, window, document);

  basedatamanage_efficiencyanalysis_branchConf.pageInit();

</script>