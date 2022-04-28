<style>
  .btn-primary_sbdy {
    color: rgba(255, 255, 255, 0.77);
    background-color: rgb(42, 123, 193);
    border-color: #518f9a;
    /* border: 1px solid; */
    /* width: 3.5vw; */
    height: 3.5vh;
    display: flex;
    justify-content: center;
    align-items: center;
    line-height: 50%;
    margin-right: 5px;
  }
</style>
<div id="eqconfiguration_sbdy_div" class="information-model">
	<span class="Subtitle"> <i class="fa fa-th-list"
                             aria-hidden="true"></i>&nbsp;设备型号列表>>>
	</span>

  <!-- 导入按钮 -->
  <a id="importbesrate" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.impReport()" href="javascript:void(-1);"
     class="btn btn-primary toLeft">
    <i class="fa fa-download" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
  </a>

  <a id="importbesrate" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.impReportsbdy()"
     href="javascript:void(-1);" class="btn btn-primary_sbdy toLeft">
    <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加园区根节点
  </a>

  <!-- 导出按钮 -->
  <a id="importbesrate" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.exportReport()" href="javascript:void(-1);"
     class="btn btn-primary toLeft">
    <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
  </a>

  <!-- <a id="refreshLeftTree" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.refreshLeftTree()" href="javascript:void(-1);"
     class="btn btn-primary toLeft">
    <i class="fa fa-refresh" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>刷新
  </a> -->

  <!-- 	<a id="syncOPC" class="btn btn-primary toLeft" style="width: 7%;" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.syncOpc()">  -->
  <!-- 		<i class="fa fa-refresh"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;同步OPC数据 -->
  <!-- 	</a> -->
  <!-- 搜索框 -->
  <div class="zc_search find">
    <input type="text" class="find-style" id="park_keywords"
           name="park_keywords" placeholder="联网设备">
    <button id="querySbdyBtn" onclick="searchqydy()">
      <i class="fa fa-search" aria-hidden="true"></i>
    </button>
  </div>

  <div class="modal fade import-Model" id="sbdyType_import_model" tabindex="-1" role="dialog" data-backdrop="false"
       aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title" id="importAmmeterModalLabel">导入报表</h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-12">
              <div class="alert alert-danger" style="font-size:13px;" role="alert">
                <strong>注 意：</strong><br>
                &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon">模板下载</span>
                <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.btn_expBuildingControl()">下载楼控模板</a>
		                         </span>
                  <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.btn_expIllumination()">下载照明模板</a>
		                         </span>
                  <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.btn_expEnergy()">下载能耗模板</a>
		                         </span>
                </div>
              </div>
            </div>
          </div>

          <div class="row" id="import_form_ammeter_btn">
            <div class="col-md-12" style="padding-top: 2vh;">
              <form id="ammeterTypeImpExcel">
                <input id="sbdyTypeInputFile" type="file" name="file" class="file-loading">
              </form>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"
                  onclick="basedatamanage_eqmanage_eqconfiguration_sbdy.btn_import()">导入数据
          </button>
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="modal-form-addsbdy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
       data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
          <h4 class="modal-title addIcon">&nbsp;添加园区节点</h4>
        </div>
        <div class="modal-body">
          <form role="form" id="addCollMform" name="addCollMform" class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-3 control-label">园区别名<span class="text-danger">*</span></label>
              <div class="col-sm-8">
                <input type="text" id="f_nick_name_sbdy" name="f_nick_name_sbdy" placeholder="请输入园区别名" required
                       class="form-control">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label">园区描述<span class="text-danger"></span></label>
              <div class="col-sm-8">
                <input type="text" id="f_descrption" name="f_descrption" placeholder="园区描述" required
                       class="form-control">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">园区编号<span class="text-danger">&nbsp;</span></label>
              <div class="col-sm-8">
                <select id="f_yubh" name="f_yubh" required class="form-control">

                </select>
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

  <#--导出-->
  <div class="modal fade" style="width: 600px;margin-left: 20%" id="sbdyType_export_model" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" >
      <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
        <div class="modal-header bg-primary">
          <button aria-hidden="true" data-dismiss="modal" class="close" type="button" id="exportClearPoint">×</button>
          <h4 class="modal-title addIcon">选择导出点位</h4>
        </div>

        <div class="modal-body">

          <form role="form" id="formTableData" name="formTableData" class="form-horizontal">

            <div class="form-group" style="margin-top: 20px;">
              <label class="col-sm-2 control-label">点位<span class="text-danger"></span></label>
              <div class="col-sm-8">

                <textarea  class='form-control' id="f_newPointNameExport" name="f_newPointNameExport" placeholder="请选择点位" />

              </div>
            </div>

            <div class="form-group m-t-sm" style="margin-top: 50px;margin-left: 110px;">
              <div class="col-sm-6 col-sm-push-2 display_flex">
                <#--<button class="btn btn-md btn-warning" style="height: 31px;margin-right: 12px;" type="button" id="submitAddTableData_reduce">
                  <strong>回退</strong></button>-->
                <button class="btn btn-md btn-primary" type="button" id="submitAddTableData">
                  <strong>确定</strong></button>
                <button class="btn btn-white m-l-sm" type="button" id="exportCancelPoint" data-dismiss="modal">取消</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>


</div>
<!-- 左侧设备树start -->
<div id="leftarea"
     style="height: 92%; float: left; width: 40%; position: relative; box-sizing: border-box; overflow: auto;">
  <div id="tree_sbdy"></div>
</div>
<!-- 左侧设备树end -->
<link href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<#--点位树-->
<div class="modal fade" style="width:40%;margin-left:44.5%;margin-top: 4.4%" id="selectPointTreeExport" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog" style="margin-top:6px;margin-right: 40px">
    <div class="modal-content" style="width: 350px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">请选择点位</h4>
      </div>
      <div class="modal-body" style="height: 500px">
        <#--设备类型树-->
        <div id="selectPoinTree" class="Information_area ztree"></div>
      </div>
    </div>
  </div>
</div>

<!-- 右侧属性详情start -->
<div style="float: right; height: 93%; width: 60%; position: relative; padding: 10px 5px 0px 5px; margin-top: -10px;">
  <div style="height: calc(100%)">
    <div id="sbdyInfo" style="height: 99%;"></div>
  </div>
  <input type="hidden" id="sel_f_sys_name" value="">
  <!-- 定义一个隐藏选中节点类型 -->
  <input type="hidden" id="sel_f_type" value="">
  <!-- 定义一个隐藏选中节点类型 -->
  <input type="hidden" id="pre_f_allpath" value="">
  <!-- 添加节点时上次选中的全路径 -->
  <input type="hidden" id="pre_f_sys_name" value="">
  <!-- 添加节点时上次选中的系统名称 -->
  <input type="hidden" id="hidden_node_fun_max_count">
  <!-- 选中节点节点数量上限 -->
</div>

<!-- 右侧属性详情end -->
<script type="text/javascript">
  ;
  var basedatamanage_eqmanage_eqconfiguration_sbdy = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var _sys_name = "00";
    var _type = "0";
    var imgMap = new Map();//节点图标(key:节点类型（在线/离线）  value:图片url)
    var editUrlMap = new Map();//节点图标(key:节点类型（在线/离线）  value:图片url)
    var idMap = new Map();
    var treeSelNode;//声明一个全局选中设备树设备树选中节点
    var tmp_treeSelNode;//声明一个全局选中设备树设备树选中节点
    var addNodeTextVar = "请修改节点属性";//可变设备树节点名称
    var addNodeTextCon = addNodeTextVar;//新增设备树节点名称
    var isShow = false;//所选节点变更时控制是否加载左侧属性
    var isAdd = false;
    var addStaus = 2;//添加节点时，输入的系统名称是否重复[重复：2，否：1]
    var addNodeId = '';
    var clock = '';//定时任务返回id
    var nodeIndex = 0;//当前节点在父级节点下的索引
    var pid;//当前点击节点的父节点id
    var selectedNode;//当前点击节点的属性信息
    var nodeLevel;//当前选中节点在树上的级数
    var nodeType;//当前选中节点的类型
    var nodeAttribution = '';//节点所属系统
    var yqbh = "";//园区编号
    var tsnodeType = "";//写死编号跳转调试
    var f_sys_name = "";
    var childType = "";//用来限制智能照明模块ip路由器下只能新增一种类型(类型)  tjw
    var childName = "";//类型名称

    var pointName = ""; //存储当前的选择
    var pointNameAndNickName = "";//点位文本框中保存的点位集合,带上别名的
    var pointId = ""; //点位ID
    var pointType = ""; //点位类型
    var pointPsysName = ""; //点位父级名称
    var controlPointModel = false;//控制场景修改模式点位模态框是否展示

    Array.prototype.indexOf = function(val) {
      for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
      }
      return -1;
    };

    Array.prototype.remove = function(val) {
      var index = this.indexOf(val);
      if (index > -1) {
        this.splice(index, 1);
      }
    };

    /*    var ddcNodeExpandedMap = new Map(); // ddc 树展开状态 true 展开 false 折叠
        var runStateMap = new Map();*/

    //导出弹窗位置
    $("#sbdyType_export_model").on('show.bs.modal', function(event) {

      //垂直居中显示
      $(this).css('display', 'block');
      $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
      document.getElementById("f_newPointNameExport").style.height = 40 + "px";
      document.getElementById("f_newPointNameExport").style.width = 100 + "%";
    });
    //关闭模态框清空表单值
    $("#sbdyType_export_model").on('hidden.bs.modal',function(event) {
      $(this).find("input").val("");
      $(this).find("textarea").val("");
      controlPointModel = false;
      pointId = "";//点位id
      pointName = "";//点位名称
      pointType = ""; //点位类型
      pointPsysName = ""; //点位父级名称
      pointNameAndNickName = "";
    });
    //控制场景添加指令模态框
    $("#exportClearPoint,#exportCancelPoint").click(function(event){
      pointName = "";
      pointNameAndNickName = "";
      $('#f_newPointNameExport').val("");
      $('#selectPointTreeExport').modal('hide');
    })

    //点位树初次加载
    function equip_tree() {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/eqmanage/austere_devices_tree",
        dataType: "json",
        async: true,
        success: function (result) {
          var status = result && result.status;

          if (status !== '1') {
            return;
          }

          var treeList = result.list || [];

          var tree = new Tree({
            container: 'selectPoinTree',
            idKey: 'f_sys_name',
            pIdKey: 'f_psys_name',
            name: 'f_nick_name',
            setting: {
              view: {
                showIcon: false,
                fontCss: function (treeId, treeNode) {
                  return (treeNode.highlight)
                          ? {color: '#A60000', 'font-weight': 'bold'}
                          : {color: '#000000', 'font-weight': 'normal'};
                }
              },
            },
            callback: function (node) {

              $('#f_newPointNameExport').val('')

              //选择点位后赋值
              pointId = node.f_id;
              pointType = node.f_type;
              pointPsysName = node.f_psys_name;
              pointName = node.f_sys_name_old;

              //文本框展示
              pointNameAndNickName = node.f_sys_name_old + "(" + node.f_nick_name + ")";
              $('#f_newPointNameExport').val(pointNameAndNickName);
              /*autoTextarea(document.getElementById("f_newPointNameExport"));*/

            }
          })
          tree.loadData(treeList);
        }
      })
    }

    //点位树展示
    $('#f_newPointNameExport').focus(function () {
      if (document.querySelectorAll("#selectPoinTree li").length == 0) {
        swal({
          title: '正在初始化设备树，请稍后点击！',// 展示的标题
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 2000
        });
        $('#f_newPointNameExport').blur();
        return
      }
      $('#selectPointTreeExport').modal('show');
      document.getElementById("f_newPointNameExport").style.height = 40 + "px";
    });

    //导出
    $("#submitAddTableData").click(function () {

      var _url = "${ctx}/view/basesbdy/excel/exportPoint";
      var _data = {

        f_pointName: pointName, //点位名称
        f_pointId: pointId,  //点位id
        f_type: pointType, //点位类型
        f_pointPsysName: pointPsysName, //点位父级名称

      }
      doExp(_url,"设备配置表","${ctx}",_data);//导出excel并下载

    });

    function getTreeData(callback, pSysName) {

      if (typeof callback !== 'function') {
        return;
      }
      $.ajax({
        type: "post",
        // url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree",
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree_lazyLoad",
        dataType: "json",
        data: {
          pSysName: pSysName
        },
        success: function (result) {
          callback(result);
        }
      });
    };
    //根据文本框内容自适应高度
    function autoTextarea(elem, extra, maxHeight) {
      extra = extra || 0;
      var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
              isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
              addEvent = function (type, callback) {
                elem.addEventListener ?
                        elem.addEventListener(type, callback, false) :
                        elem.attachEvent('on' + type, callback);
              },
              getStyle = elem.currentStyle ? function (name) {
                var val = elem.currentStyle[name];
                if (name === 'height' && val.search(/px/i) !== 1) {
                  var rect = elem.getBoundingClientRect();
                  return rect.bottom - rect.top -
                          parseFloat(getStyle('paddingTop')) -
                          parseFloat(getStyle('paddingBottom')) + 'px';
                };
                return val;
              } : function (name) {
                return getComputedStyle(elem, null)[name];
              },
              minHeight = parseFloat(getStyle('height'));
      elem.style.resize = 'none';
      var change = function () {
        var scrollTop, height,
                padding = 0,
                style = elem.style;
        if (elem._length === elem.value.length) return;
        elem._length = elem.value.length;
        if (!isFirefox && !isOpera) {
          padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
        };
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
        elem.style.height = minHeight + 'px';
        if (elem.scrollHeight > minHeight) {
          if (maxHeight && elem.scrollHeight > maxHeight) {
            height = maxHeight;
            style.overflowY = 'auto';
          } else {
            height = elem.scrollHeight;
            style.overflowY = 'hidden';
          };
          style.height = height + extra + 'px';
          // scrollTop += parseInt(style.height) - elem.currHeight;
          // document.body.scrollTop = scrollTop;
          // document.documentElement.scrollTop = scrollTop;
          elem.currHeight = parseInt(style.height);
        };
      };
      addEvent('propertychange', change);
      addEvent('input', change);
      addEvent('focus', change);
      change();
    };

    //加载树
    $(function () {

      equip_tree();//点位树

      $.ajax({
        type: "post",
        // url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree",
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree_lazyLoad",
        dataType: "json",
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.status == '1') {

            var treeData = buildTree({
              data: result.list,
              parentId: 'pid',
              children: 'nodes'
            });

            $('#tree_sbdy').treeview({
              data: treeData, // 数据源
              highlightSelected: true, //是否高亮选中
              levels: 2,
              enableLinks: true,//必须在节点属性给出href属性
              wrapNodeText: true,
              showImage: true,
              color: "#4a4747",
              staticLoad: true,
              lazyLoad: function (callback, id) {
                getTreeData(function (result){
                  var list = result.list;
                  if (!list) return;
                  callback(result.list);
                  var sysNames = [];
                  for(var i = 0; i < list.length; i++) {
                    sysNames.push(list[i].id);
                  }
                  basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(sysNames.toString());
                }, id);
              },
              onNodeSelected: function (event, nodeData) { //节点选中事件
                treeSelNode = $("#tree_sbdy").treeview("getSelected");//每次选中后改变全局选中设备树选中节点
                checkAddNodeNotSave(event, nodeData);//检查是否含有切换节点时存在新增节点未保存
                var isCheckSave = checkInfoIsSave(event, nodeData);//判断是否需要提示保存已修改内容
                isShow = !(tmp_treeSelNode == treeSelNode) && tmp_treeSelNode[0].text == addNodeTextVar;
                if (typeof (isCheckSave) == "undefined" && !isShow) {//如果信息未修改，直接根据节点信息加载属性页面
                  childPageLoad(event, nodeData);
                  $("#sel_f_type").val(nodeData.nodeType);
                }
                nodeIndex = nodeData.index;//选中节点时，获取该节点的索引值
                pid = nodeData.pid;
                selectedNode = nodeData;//当前选中节点的属性
                nodeLevel = nodeData.level;//当前选中节点在树上的级数
                nodeType = nodeData.nodeType;//当前选中节点的类型
                nodeAttribution = nodeData.nodeAttribution;
              },
              // 一个节点被展开(事件)
              onNodeExpanded: function (event, nodeData) {

                var nodeType = nodeData.nodeType;

                if (nodeType === '24' || nodeType === '9') {

                  var nodes = nodeData.nodes;

                  for (var i = 0; i < nodes.length; i++) {
                    PubSub.subscribe(nodes[i].nodeTreeId, refreshTreeSingle);
                  }

                }

                /* if(nodeData.nodeType === '2'){

                     ddcNodeExpandedMap.put(nodeData.id, true);

                     if(runStateMap.get(nodeData.id) == false || runStateMap.get(nodeData.id) == null){
                         getDDRealTimeInfo(nodeData.id);
                     }
                 }*/
              },

              // 一个节点被折叠(事件)
              onNodeCollapsed: function (event, nodeData) {

                var nodeType = nodeData.nodeType;

                if (nodeType === '24' || nodeType === '9') {

                  var nodes = nodeData.nodes;

                  for (var i = 0; i < nodes.length; i++) {
                    PubSub.unsubscribe(nodes[i].nodeTreeId, refreshTreeSingle);
                  }

                }

                /*   if(nodeData.nodeType === '2'){
                       ddcNodeExpandedMap.put(nodeData.id, false);
                   }*/
              }
            });
            var reMap = result.map;//数据库中所有的id
            for (var key in reMap) {
              idMap.put(key, key);
            }
            ;
            var selNode = $('#tree_sbdy').treeview('findNodes', ['root', 'pid']);//初次加载页面时获取第一个节点
            tmp_treeSelNode = selNode;
            yqbh = selNode[0].id;
            $("#tree_sbdy").treeview("selectNode", selNode);//将第一个节点设置为选中状态
            treeSelNode = selNode;
            //刷新设备树状态
            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon();
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal({
            title: result.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
      $.ajax({//加载设备树节点类型定义信息
        type: "post",
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_noteTypeInfos",
        dataType: "json",
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          var dataList = result.list;
          for (var i = 0; i < dataList.length; i++) {
            editUrlMap.put(dataList[i].f_node_type,
                dataList[i].f_edit_url);
            imgMap.put("on_" + dataList[i].f_node_type, "url('"
                + dataList[i].f_online_img + "')");
            imgMap.put("off_" + dataList[i].f_node_type, "url('"
                + dataList[i].f_offline_img + "')");
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal({
            title: result.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    });
    
    function refreshLeftTree() {
      $.ajax({
        type: "post",
        // url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree",
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_tree_reload",
        dataType: "json",
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.status == '1') {

            var treeData = buildTree({
              data: result.list,
              parentId: 'pid',
              children: 'nodes'
            });

            $('#tree_sbdy').treeview({
              data: treeData, // 数据源
              highlightSelected: true, //是否高亮选中
              levels: 2,
              enableLinks: true,//必须在节点属性给出href属性
              wrapNodeText: true,
              showImage: true,
              color: "#4a4747",
              staticLoad: true,
              lazyLoad: function (callback, id) {
                getTreeData(function (result){
                  var list = result.list;
                  if (!list) return;
                  callback(result.list);
                  var sysNames = [];
                  for(var i = 0; i < list.length; i++) {
                    sysNames.push(list[i].id);
                  }
                  basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(sysNames.toString());
                }, id);
              },
              onNodeSelected: function (event, nodeData) { //节点选中事件
                treeSelNode = $("#tree_sbdy").treeview("getSelected");//每次选中后改变全局选中设备树选中节点
                checkAddNodeNotSave(event, nodeData);//检查是否含有切换节点时存在新增节点未保存
                var isCheckSave = checkInfoIsSave(event, nodeData);//判断是否需要提示保存已修改内容
                isShow = !(tmp_treeSelNode == treeSelNode) && tmp_treeSelNode[0].text == addNodeTextVar;
                if (typeof (isCheckSave) == "undefined" && !isShow) {//如果信息未修改，直接根据节点信息加载属性页面
                  childPageLoad(event, nodeData);
                  $("#sel_f_type").val(nodeData.nodeType);
                }
                nodeIndex = nodeData.index;//选中节点时，获取该节点的索引值
                pid = nodeData.pid;
                selectedNode = nodeData;//当前选中节点的属性
                nodeLevel = nodeData.level;//当前选中节点在树上的级数
                nodeType = nodeData.nodeType;//当前选中节点的类型
                nodeAttribution = nodeData.nodeAttribution;
              },
              // 一个节点被展开(事件)
              onNodeExpanded: function (event, nodeData) {

                var nodeType = nodeData.nodeType;

                if (nodeType === '24' || nodeType === '9') {

                  var nodes = nodeData.nodes;

                  for (var i = 0; i < nodes.length; i++) {
                    PubSub.subscribe(nodes[i].nodeTreeId, refreshTreeSingle);
                  }

                }

                /* if(nodeData.nodeType === '2'){

                     ddcNodeExpandedMap.put(nodeData.id, true);

                     if(runStateMap.get(nodeData.id) == false || runStateMap.get(nodeData.id) == null){
                         getDDRealTimeInfo(nodeData.id);
                     }
                 }*/
              },

              // 一个节点被折叠(事件)
              onNodeCollapsed: function (event, nodeData) {

                var nodeType = nodeData.nodeType;

                if (nodeType === '24' || nodeType === '9') {

                  var nodes = nodeData.nodes;

                  for (var i = 0; i < nodes.length; i++) {
                    PubSub.unsubscribe(nodes[i].nodeTreeId, refreshTreeSingle);
                  }

                }

                /*   if(nodeData.nodeType === '2'){
                       ddcNodeExpandedMap.put(nodeData.id, false);
                   }*/
              }
            });
            var reMap = result.map;//数据库中所有的id
            for (var key in reMap) {
              idMap.put(key, key);
            }
            ;
            var selNode = $('#tree_sbdy').treeview('findNodes', ['root', 'pid']);//初次加载页面时获取第一个节点
            tmp_treeSelNode = selNode;
            yqbh = selNode[0].id;
            $("#tree_sbdy").treeview("selectNode", selNode);//将第一个节点设置为选中状态
            treeSelNode = selNode;
            //刷新设备树状态
            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon();
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal({
            title: result.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
      $.ajax({//加载设备树节点类型定义信息
        type: "post",
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_noteTypeInfos",
        dataType: "json",
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          var dataList = result.list;
          for (var i = 0; i < dataList.length; i++) {
            editUrlMap.put(dataList[i].f_node_type,
                    dataList[i].f_edit_url);
            imgMap.put("on_" + dataList[i].f_node_type, "url('"
                    + dataList[i].f_online_img + "')");
            imgMap.put("off_" + dataList[i].f_node_type, "url('"
                    + dataList[i].f_offline_img + "')");
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal({
            title: result.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
      $('#refreshLeftTree').blur();
    }


    //判断是否存在信息变更未保存离开当前页面
    function checkInfoIsSave(event, nodeData) {//当属性信息发生变更未保存离开当前页面时，检测是否保存当前变更信息
      var saveBtn = document.getElementById("saveBtn");
      if (!(typeof (saveBtn) == "undefined" || saveBtn == null)
          && !($("#saveBtn").prop("disabled"))
          && $("#saveBtn").text() == "保存") {
        swal({
          title: "修改的信息是否保存？",
          text: "",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#1ab394",
          confirmButtonText: "保存",
          cancelButtonText: "取消",
          closeOnConfirm: true,
          closeOnCancel: true
        }, function (isConfirm) {
          if (isConfirm) {
            //当修改的点位节点未保存时,点击提示框的保存后,不刷新节点内容
            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged(false);
            childPageLoad(event, nodeData);
          } else {
            childPageLoad(event, nodeData);
          }

          basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
        });
        return 1;
      }
    }

    //根据所选节点，加载不同页面
    function childPageLoad(event, nodeData) { //nodeData：所选择信息
      basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
      $('#tree_sbdy').treeview('clearSearch');//清除搜索选中高亮
      f_sys_name = nodeData.id;//传值，根据【系统名称】加载当前节点属性信息
      $("#sel_f_sys_name").val(f_sys_name);//给当前页面设置一个选中的系统名称
      _type = nodeData.nodeType;//所选节点类型
      tsnodeType = nodeData.text;
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_noteInfoByNoteType",
        contentType: "application/json; charset=utf-8",
        type: "get",
        data: {
          f_type: nodeData.nodeType
        },
        beforeSend: function () {
          showLoad();
        },
        success: function (returnObject) {

          if (returnObject.data.hasOwnProperty('f_max_count')) {//设置该节点节点数量上限
            $('#hidden_node_fun_max_count').val(returnObject.data.f_max_count);
          } else {
            $('#hidden_node_fun_max_count').val("");
          }
          if (!returnObject.hasOwnProperty('data')) {
            $('#sbdyInfo').html("");
            return;
          }
          var funNames = "";
          var funNodeTypes = "";
          if (returnObject.hasOwnProperty('data')) {//Start tianjiangwei
            if (returnObject.data.hasOwnProperty('f_fun_nodetype')) {
              if (_type == '3') {
                if (nodeData.hasOwnProperty('nodes')) {
                  childType = nodeData.nodes[0].nodeType;
                  if (childType == "24") {
                    if (typeof (nodeData.nodes[1]) != "undefined") {
                      childType = nodeData.nodes[1].nodeType;
                      var jsonObjType = [childType, "", "32"];//"24" 虚点的点类型
                      funNodeTypes = jsonObjType;
                    } else {
                      funNodeTypes = returnObject.data.f_fun_nodetype.split(",");
                    }
                  } else if (childType == "9") {
                    var jsonObjType = [childType, "", "32"];//"24" 虚点的点类型
                    funNodeTypes = jsonObjType;
                  }

                } else {
                  funNodeTypes = returnObject.data.f_fun_nodetype.split(",");
                }
              } else {
                funNodeTypes = returnObject.data.f_fun_nodetype.split(",");
              }
            }
            if (returnObject.data.hasOwnProperty('f_fun_mcs')) {
              if (_type == '3') {
                if (nodeData.hasOwnProperty('nodes')) {
                  if (childType == "24") {
                    funNames = returnObject.data.f_fun_mcs.split(",");
                  } else {
                    searchChildName(childType)
                    var jsonObjName = [childName, "删除", "编程"];
                    funNames = jsonObjName;
                  }


                } else {
                  funNames = returnObject.data.f_fun_mcs.split(",");
                }
              } else {
                funNames = returnObject.data.f_fun_mcs.split(",");
              }
            }//end
          }
          addFucMenu(nodeData, funNames, funNodeTypes);//给选中节点添加功能添加菜单
          if (!(typeof (_type) == 'undefined')) {//当节点类型不为空时
            if (!returnObject.data.hasOwnProperty('f_edit_url') || f_sys_name == '') {//数据库中没有取到对应的路径
              $('#sbdyInfo').html("");//清空属性信息
              return;
            }
            var variableUrl = returnObject.data.f_edit_url.split("/")[5];
            //var variableUrl = returnObject.data.f_edit_url;
            //$("#sbdyInfo").load(_ctx + variableUrl,
            $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/" + variableUrl,
                {
                  "f_psys_name": pid,
                  "f_sys_name": f_sys_name,
                  "nodeTabName": returnObject.data.f_node_table,
                  "f_type": nodeData.nodeType
                });//跳转所选节点属性页面
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    }

    //根据子节点的类型查询对应的名称  tianjiangwei
    function searchChildName(type) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/noteNameByNoteType",
        type: "post",
        async: false,
        data: {
          f_type: type
        },
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          var result = result.data;
          childName = result[0].f_node_name;
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal(result.msg, "", "error");
        }
      });

    }

    //选中节点后为设备树节点添加菜单
    function addFucMenu(nodeData, funNames, funNodeTypes) {
      //'请修改节点属性'
      if (nodeData.text == addNodeTextVar) {
        return;
      }
      var oSpan = document.createElement('span');
      oSpan.setAttribute("id", "spanMenu");//
      for (var i = 0; i < funNames.length; i++) {
        if (funNames[i] == "" || funNames[i] == null) {
          continue;
        }
        var oBtn = document.createElement('button');
        oBtn.setAttribute("class", "btn sbtreeNodeBtn");
        oBtn.setAttribute("value", funNames[i]);
        if (!funNodeTypes[i] == "") {
          oBtn.setAttribute("nodeType", funNodeTypes[i]);
        }
        oBtn.setAttribute("onclick", "basedatamanage_eqmanage_eqconfiguration_sbdy.btnMenuClick(this)");
        oBtn.innerText = funNames[i];
        oSpan.append(oBtn);
      }
      if ($("#spanMenu").length > 0) {
        $("#spanMenu").remove();//新建一个菜单前将上次创建的菜单移除
      }
      $("#tree_sbdy #" + nodeData.id).append(oSpan);//根据所选节点添加按钮
    }

    function checkAddNodeNotSave(event, nodeData) {
      if (!(tmp_treeSelNode == treeSelNode)
          && tmp_treeSelNode[0].text == addNodeTextVar) {
        swal({
          title: "新增信息是否保存？",
          text: "",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#1ab394",
          confirmButtonText: "保存",
          cancelButtonText: "取消",
          closeOnConfirm: true,
          closeOnCancel: true
        }, function (isConfirm) {

          if (isConfirm) {
            tmp_treeSelNode = treeSelNode;
            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged();//将数据保存

            var remNode = $('#tree_sbdy').treeview('search', basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeName);//获取刚添加的节点

            if (remNode.length > 0) {
              $("#tree_sbdy").treeview("removeNode", remNode[0]);
              basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon();// 刷新节点状态
            }

            childPageLoad(event, nodeData);
            $("#sel_f_type").val(nodeData.nodeType);

            /* childPageLoad(event, nodeData);
             if(addStaus=="2"){//添加失败离开页面时
                 var remNode = $('#tree_sbdy').treeview('search', basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon());//获取刚添加的节点
                 if(remNode.length>0){
                     $("#tree_sbdy").treeview("removeNode",remNode[0]);
                 }
                 $('#sbdyInfo').html("");//属性区域清空
                 /!* setTimeout(function() {
                 swal("保存失败", "", "success"); },300); *!/
             }
             isShow = false;
             if (typeof (isCheckSave) == "undefined" && !isShow) {//如果信息未修改，直接根据节点信息加载属性页面
                 childPageLoad(event, nodeData);
                 $("#sel_f_type").val(nodeData.nodeType);
             }*/
          } else {
            var remNode = $('#tree_sbdy').treeview('search', [addNodeTextVar, {exactMatch: true}]);//
            if (remNode.length > 0) {
              $("#tree_sbdy").treeview("removeNode", remNode[0]);
              basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon();// 刷新节点状态
            }
            $('#sbdyInfo').html("");//清空属性信息
            tmp_treeSelNode = treeSelNode;
            /* setTimeout(function() {
                swal("保存失败", "", "success"); },300); */
            isShow = false;
            if (typeof (isCheckSave) == "undefined" && !isShow) {//如果信息未修改，直接根据节点信息加载属性页面
              childPageLoad(event, nodeData);
              $("#sel_f_type").val(nodeData.nodeType);
            }
          }
        });
      }
    }

    //删除所选节点相关数据
    function delByNode(nodeId, f_type) {
      $.issp_ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbtree_delBySelNode",
        contentType: "application/json; charset=utf-8",
        type: "post",
        data: JSON.stringify({
          f_sys_name: nodeId,
          f_type: f_type,
          f_psys_name: pid,//父系统名称
// 				f_guid : basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getNodefGuid(),//从属性页面获取id
          fNodeType: nodeType,//节点类型
          nodeLevel: nodeLevel,//选中节点在树上的级数(模块，楼控系统为6，照明系统为4)
          f_node_attribution: nodeAttribution //节点所属系统
        }),
        success: function (returnObject) {
          if (returnObject.status == '1') {
            //在树上删除该条数据
            var dNodes = $('#tree_sbdy').treeview('findNodes', [nodeId, 'id']);
            if (dNodes.length > 1) {
              for (var i = 0; i < dNodes.length; i++) {
                if (dNodes[i].id == nodeId) {
                  $('#tree_sbdy').treeview('removeNode', dNodes[i]);//移除节点
                }
              }
            } else {
              $("#tree_sbdy").treeview("removeNode", dNodes[0]);
            }
            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon();// 刷新节点状态
            $('#sbdyInfo').html("");//属性区域清空

            swal({
              title: returnObject.msg,// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
          } else {

            setTimeout(function () {
              swal({
                title: returnObject.msg,// 展示的标题
                text: "",// 内容
                type: "warning",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
            }, 1200)


          }
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    }

    //新增默认节点相关数据
    function addByNode(f_type) {
      var addNodeAttribution = "";
      if (f_type == "1") {//楼宇自控
        addNodeAttribution = "1";
      } else if (f_type == "21") {//照明
        addNodeAttribution = "2";
      } else if (f_type == "31") {//能耗
        addNodeAttribution = "3";
      }
      isAdd = true;
      //在树上添加一个默认子节点
      if (treeSelNode[0].hasOwnProperty('nodes')) {
        addNodeId = treeSelNode[0].nodes[0].id + "00";
      } else {
        addNodeId = treeSelNode[0].id + "00";
      }
      if ($('#tree_sbdy').treeview('search', [addNodeTextCon, {exactMatch: true}]).length == 0) {//如果上次添加节点未修改属性，不再重新添加子节点
        var imgUrl = imgMap.get("on_" + f_type);//根据节点类型取图片路径
        if (!(imgUrl == '') || !(imgUrl == null)) {
          imageUrl = imgUrl.substring(5, imgUrl.length - 2);
        }
        $("#tree_sbdy").treeview('expandNode',treeSelNode);// 添加节点前展开节点

        setTimeout(function () {
          $("#tree_sbdy").treeview("addNode", [{
            nodeTreeId: addNodeId,
            id: addNodeId,
            text: addNodeTextCon,
            nodeType: f_type,
            pid: treeSelNode[0].id,
            image: imageUrl,
            nodeAttribution: addNodeAttribution,
          }, treeSelNode]);
          // basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态

          if ($("#f_allpath").text() == null || $("#f_allpath").text() == '') {//设置切换前节点的全路径
            $("#pre_f_allpath").val($('#tree_sbdy').treeview('getSelected')[0].text);
          } else {
            $("#pre_f_allpath").val($("#f_allpath").text());
          }
          $("#pre_f_sys_name").val($('#tree_sbdy').treeview('getSelected')[0].id);//设置切换前节点的id
          var addedNode = $('#tree_sbdy').treeview('search', [addNodeTextCon, {exactMatch: true}]);//设置切换前的选中节点
          // $("#tree_sbdy").treeview('selectNode', addedNode); //将添加接节点设置为选中状态
          setTimeout(function () {
            $("#tree_sbdy").treeview('selectNode', addedNode); //将添加接节点设置为选中状态
            tmp_treeSelNode = treeSelNode;
            treeSelNode = '';
          }, 0);//移除子元素中菜单按钮
          setTimeout(function () {
            $("#spanMenu").remove();
          }, 100);//移除子元素中菜单按钮
          /*   tmp_treeSelNode = treeSelNode;
             treeSelNode = '';*/

        }, 500);



      }

    }

    function addDefaultNode(f_type, funName, attrMap) {
      var nodeMaxCount = $('#hidden_node_fun_max_count').val();//该节点数量上限
      if (nodeMaxCount != "") {
        if (treeSelNode[0].hasOwnProperty("nodes")) {
          if (treeSelNode[0].nodes.length >= nodeMaxCount) {
            swal({
              title: "超过子节点数量设置！",
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
            return;
          }
        }
      }
      let a;

      if ($('#tree_sbdy').treeview('findNodes', [treeSelNode[0].nodeTreeId, 'id'])[0].nodeType == "24") {//如果获取的第一个数组是虚点无属性页面的信息,则取第二个总线的数组
        a = $('#tree_sbdy').treeview('findNodes', [treeSelNode[0].nodeTreeId, 'id'])[1].nodes;
      } else {
        a = $('#tree_sbdy').treeview('findNodes', [treeSelNode[0].nodeTreeId, 'id'])[0].nodes;
      }

      let objName = ["PNP", "FLN1", "FLN2", "FLN3", "FLN4"];


      //根据父节点系统名称查询所属系统的标识
      if (typeof (treeSelNode[0].nodeAttribution) == 'undefined' || treeSelNode[0].nodeAttribution == null) {

        if (f_type == "23") {

          if (typeof a == 'undefined') {
            funName = objName[0];
          } else {
            for (let i = 0; i < a.length; i++) {
              objName.remove(a[i].text)
            }

            funName = objName[0];
          }

        }

      } else if (treeSelNode[0].nodeAttribution == "1") {

        if (typeof a != "undefined") {
          if (a[0].nodeType == "24") {//虚点
            if (f_type == "24") {
              swal({
                title: "虚点超过节点限制 ！",
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
              return;
            }
          } else if(a[0].nodeType == "8") {//总线
            if (f_type == "8") {
              swal({
                title: "总线超过节点限制 ！",
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
              return;
            }
          } else if(a[0].nodeType == "23") {//线路

            for (let i = 0; i < a.length; i++) {
              objName.remove(a[i].text)
            }

            funName = objName[0];
          }
        } else {
          if (f_type != "24" && f_type != "8") {
            funName = objName[0];
          }
        }

      } else if (treeSelNode[0].nodeAttribution == "2") {
        for (let a = 0; a < attrMap.length; a++) {
          if (attrMap[a].nodeType == "24") {
            swal({
              title: "虚点超过节点限制 ！",
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
            return;
          }
        }
      }


      if (funName == "新增虚点") {
        funName = "虚点";
      }

      if (funName == "新增总线") {
        funName = "总线";
      }

      var addDefaultNodeId = "";
      var ranNum = RandomNumBoth(100, 200);
      if (treeSelNode[0].hasOwnProperty("nodes")) {
        if (treeSelNode[0].nodes.length > 0) {
          var selChildNodes = treeSelNode[0].nodes;
          addDefaultNodeId = selChildNodes[selChildNodes.length - 1].id + ranNum;
        }
      } else {
        addDefaultNodeId = treeSelNode[0].id + ranNum;
      }
      if (!(idMap.get(addDefaultNodeId) == null || idMap.get(addDefaultNodeId) == "")) {
        ranNum = RandomNumBoth(900, 999);
        addDefaultNodeId = treeSelNode[0].id + ranNum;
      }
      var addDefaultNodePid = treeSelNode[0].id;
      var addDefaultNodeAllPath = "";
      if ($("#f_allpath").text() == null || $("#f_allpath").text() == '') {
        addDefaultNodeAllPath = $("#f_allpath").text() + ">" + funName;
      } else {
        addDefaultNodeAllPath = $("#f_allpath").text() + ">" + funName;
      }
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn", //sbtree_addDefaultNode
        contentType: "application/json; charset=utf-8",
        type: "post",
        data: JSON.stringify({
          tabName: "BES_SBPZ_STRUCT",
          f_sys_name: addDefaultNodeId,
          f_psys_name: addDefaultNodePid,
          f_nick_name: funName,
          f_type: f_type,
          f_allpath: addDefaultNodeAllPath
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (returnObject) {
          if (returnObject.status == '1') {
            var imgUrl = imgMap.get("on_" + f_type);//根据节点类型取图片路径
            if (!(imgUrl == '') || !(imgUrl == null)) {
              imageUrl = imgUrl.substring(5, imgUrl.length - 2);
            }
            $("#tree_sbdy").treeview("addNode", [{
              nodeTreeId: addDefaultNodeId,
              id: addDefaultNodeId,
              text: funName,
              nodeType: f_type,
              pid: addDefaultNodePid,
              image: imageUrl
            }, treeSelNode]);
            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
            idMap.put(addDefaultNodeId, addDefaultNodeId);
            //wanghongjie 下面这段代码可以将新增的节点默认选中,但是会导致别的楼宇自控模块或者照明模块的所包含的这个名称的模块模块展开,所以注释掉
            /*if ($("#f_allpath").text() == null || $("#f_allpath").text() == ''){//设置切换前节点的全路径
                $("#pre_f_allpath").val( $('#tree_sbdy').treeview('getSelected')[0].text);
            }else{
                $("#pre_f_allpath").val($("#f_allpath").text());
            }
            $("#pre_f_sys_name").val($('#tree_sbdy').treeview('getSelected')[0].id);//设置切换前节点的id
            var addedNode = $('#tree_sbdy').treeview('search', [ funName, { exactMatch : true } ]);//设置切换前的选中节点
            $("#tree_sbdy").treeview('selectNode', addedNode); //将添加接节点设置为选中状态
            tmp_treeSelNode = treeSelNode;
            treeSelNode = '';*/


            swal({
              title: returnObject.msg,// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
          } else {
            swal({
              title: returnObject.msg,// 展示的标题
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    };

    //wanghongjie start 20200620 批量新增线路的方法
    function addDefaultNodeByBus(f_type, defaultNode) {

      var nodeMaxCount = $('#hidden_node_fun_max_count').val();//该节点数量上限
      if (nodeMaxCount != "") {
        if (treeSelNode[0].hasOwnProperty("nodes")) {
          if (treeSelNode[0].nodes.length = 1) {
            swal({
              title: "超过子节点数量设置！",
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
            return;
          }
        }
      }

      var addDefaultNodeId = "";
      var ranNum = RandomNumBoth(100, 200);
      if (treeSelNode[0].hasOwnProperty("nodes")) {
        if (treeSelNode[0].nodes.length > 0) {
          var selChildNodes = treeSelNode[0].nodes;
          addDefaultNodeId = selChildNodes[selChildNodes.length - 1].id + ranNum;
        }
      } else {
        addDefaultNodeId = treeSelNode[0].id + ranNum;
      }
      if (!(idMap.get(addDefaultNodeId) == null || idMap.get(addDefaultNodeId) == "")) {
        ranNum = RandomNumBoth(900, 999);
        addDefaultNodeId = treeSelNode[0].id + ranNum;
      }
      var addDefaultNodePid = treeSelNode[0].id;
      var addDefaultNodeAllPath = "";
      if ($("#f_allpath").text() == null || $("#f_allpath").text() == '') {
        addDefaultNodeAllPath = $("#f_allpath").text() + ">" + defaultNode;
      } else {
        addDefaultNodeAllPath = $("#f_allpath").text() + ">" + defaultNode;
      }
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
        contentType: "application/json; charset=utf-8",
        type: "post",
        data: JSON.stringify({
          tabName: "BES_SBPZ_STRUCT",
          f_sys_name: addDefaultNodePid,
          f_type: f_type,//总线下默认添加五个节点的默认type   40
          f_allpath: addDefaultNodeAllPath
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (returnObject) {
          if (returnObject.status == '1') {

            for (var i = 0; i < returnObject.list.length; i++) {//循环在树上添加默认子节点


              var nuwmap = new Map();//(key:ID value:设备树节点类型)
              nuwmap = returnObject.list[i];
              var imgUrl = imgMap.get("on_" + nuwmap.F_TYPE);//根据节点类型取图片路径
              if (!(imgUrl == '') || !(imgUrl == null))
                imageUrl = imgUrl.substring(5, imgUrl.length - 2);
              $("#tree_sbdy").treeview("addNode", [
                {
                  nodeTreeId: nuwmap.F_SYS_NAME,
                  id: nuwmap.F_SYS_NAME,
                  text: nuwmap.F_NICK_NAME,
                  nodeType: nuwmap.F_TYPE,
                  pid: nuwmap.F_SYS_NAME,
                  image: imageUrl
                }, treeSelNode]);
              idMap.put(addDefaultNodeId, addDefaultNodeId);
              basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
            }

          } else {
            swal({
              title: returnObject.msg,// 展示的标题
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1500,
            });
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    };

    //逻辑点上调试按钮
    function dubugNode(f_type) {
      if (basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getNodefGuid() == "") {
        swal({
          title: "请先配置右侧属性",// 展示的标题
          text: "",// 内容
          type: "error",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000,
        });
        return;
      }
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_noteInfoByNoteType",
        contentType: "application/json; charset=utf-8",
        type: "get",
        data: {
          f_type: f_type,
        },
        beforeSend: function () {
          showLoad();
        },
        success: function (returnObject) {
          var funNames = "";
          var funNodeTypes = "";
          if (returnObject.hasOwnProperty('data')) {
            if (returnObject.data.hasOwnProperty('f_fun_mcs')) {
              funNames = returnObject.data.f_fun_mcs.split(",");
            }
            if (returnObject.data.hasOwnProperty('f_fun_nodetype')) {
              funNodeTypes = returnObject.data.f_fun_nodetype.split(",");
            }
          }
// 				addFucMenu(nodeData, funNames, funNodeTypes);//给选中节点添加功能添加菜单
          if (!(typeof (_type) == 'undefined')) {//当节点类型不为空时
            if (!returnObject.data.hasOwnProperty('f_edit_url') || f_sys_name == '') {//数据库中没有取到对应的路径
              $('#sbdyInfo').html("");//清空属性信息
              return;
            }
            var variableUrl = returnObject.data.f_edit_url.split("/")[5];
            var nodeIndex = basedatamanage_eqmanage_eqconfiguration_sbdy.getNodeIndex();
            var nodeLevle = basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel();
            if (nodeLevle == 7) {
              /* if(nodeIndex == 1&&tsnodeType == 'MS'){
                   variableUrl = "sbdy_debugAOMS";
               }else if(nodeIndex == 1&&tsnodeType != 'B2QT'&&tsnodeType !='JZQT2'&&tsnodeType !='JZQT3'&&tsnodeType !='FFG'&&tsnodeType != 'JZQT5'&&tsnodeType != 'JZQT6'&&tsnodeType != 'FJ2QT'&&tsnodeType != 'FJ3QT'){
                   variableUrl = "sbdy_debugDOMS";
               }else if(nodeIndex == 2&&tsnodeType != 'B2QT'&&tsnodeType !='JZQT2'&&tsnodeType !='JZQT3'&&tsnodeType !='FFG'&&tsnodeType !='JZQT5'&&tsnodeType !='JZQT6'&&tsnodeType != 'FJ2QT'&&tsnodeType != 'FJ3QT'){
                   variableUrl = "sbdy_debugDOFS";
               }else if(nodeIndex == 3&&tsnodeType == 'SD'){
                   variableUrl = "sbdy_debugAOSD";
               }*/
            }
            if (typeof (selectedNode.icon) == "undefined") {
              selectedNode.icon = selectedNode.id;
              $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/getdubugNodeInfo/" + variableUrl,
                  {
                    // "f_sys_name" : selectedNode.icon,
                    "f_sys_name": selectedNode.id,
                    "f_type": selectedNode.nodeType
                  }
              );//跳转所选节点属性页面
            } else {
              $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/getdubugNodeInfo/" + variableUrl,
                  {
                    //"f_sys_name" : selectedNode.icon,
                    "f_sys_name": selectedNode.id,
                    "f_type": selectedNode.nodeType
                  }
              );//跳转所选节点属性页面
            }

          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    };
    //定时刷新设备树图图标
    // clock = window.setInterval("basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon()",10000);

    //生成随机数
    function RandomNumBoth(Min, Max) {
      var Range = Max - Min;
      var Rand = Math.random();
      var num = Min + Math.round(Rand * Range); //四舍五入
      return num;
    }

    //鼠标移至设备树以外的区域时隐藏菜单
    $('#sbdyInfo').mouseenter(function () {
      if ($("#spanMenu").length > 0) $("#spanMenu").hide();
    });
    $('#tabContent').mouseenter(function () {
      if ($("#spanMenu").length > 0) $("#spanMenu").hide();
    });
    $('#leftMenu').mouseenter(function () {
      if ($("#spanMenu").length > 0) $("#spanMenu").hide();
    });
    $('#top').mouseenter(function () {
      if ($("#spanMenu").length > 0) $("#spanMenu").hide();
    });
    $('#leftarea').mouseenter(function () {
      if ($("#spanMenu").length > 0) $("#spanMenu").show();
    });

    //逻辑点的删除按钮
    function reSet() {
      var nodeDate = selectedNode;
      var f_guid = basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getNodefGuid();
      if (f_guid == null || f_guid == "") {
        swal({
          title: "不可删除",// 展示的标题
          text: "下位机未配置，无需删除",// 内容
          type: "error",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1500,
        });
        return;
      } else {
        swal({
          title: "您确定要一起删除吗?",
          text: "信息删除后将不可恢复!",
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#1ab394",
          confirmButtonText: "确定",
          closeOnConfirm: true
        }, function () {
          reSetdefinite();
        });
      }


    }


    function reSetdefinite() {
      var nodeDate = selectedNode;
      if (typeof (selectedNode.icon) == "undefined") {
        selectedNode.icon = selectedNode.id;
      } else {
        selectedNode.icon
      }
      var f_guid = basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getNodefGuid();
      var f_channel_index = basedatamanage_eqmanage_eqconfiguration_sbdy.getNodeIndex().toString();//通道索引
      $.ajax({
        // url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/reSetPoint",
        url: _ctx + "/view/basedatamanage/eqmanage/reSetPoint",
        type: "post",
        data: {
          f_sys_name : selectedNode.nodeTreeId,
          f_sys_name_old: selectedNode.icon,
          f_psys_name: pid,//父系统名称
          f_sbid: f_guid,
          nodeLevel: nodeLevel,//选中节点在树上的级数(楼控系统为7，照明系统为5)
          f_channel_index: f_channel_index,//通道索引
          // f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
        },
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.status == '0') {
            swal({
              title: result.msg,// 展示的标题
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000,
            });
          } else {
            swal({
              title: result.msg,// 展示的标题
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });

            var f_type = result.data && result.data.f_type;

            if ((nodeDate.nodeType == "12" || nodeDate.nodeType == "10") && f_type == "14") {
              var index = nodeDate.index;
              var PNodes = $('#tree_sbdy').treeview('getParents', nodeDate);
            /*  $("#tree_sbdy").treeview("removeNode", nodeDate);
              $("#tree_sbdy").treeview("addNode", [{
                nodeTreeId: nodeDate.nodeTreeId,
                id: nodeDate.id,
                text: f_nick_name,
                nodeType: "14",
                pid: nodeDate.pid,
                image: nodeDate.image,
                nodeAttribution: PNodes[0].nodeAttribution,
                state: {selected: true}
              }, PNodes, index]);*/

              $('#tree_sbdy').treeview('updateNode', [ nodeDate, {
                nodeTreeId: nodeDate.nodeTreeId,
                id: nodeDate.id,
                text: f_nick_name,
                nodeType: "14",
                pid: nodeDate.pid,
                // image: changeNode.image,
                image: "./static/images/sbtreeimage/needDesign.png",
                nodeAttribution: PNodes[0].nodeAttribution,
                state: {selected: true}
              } ]);
              nodeDate.$el.click();
            }

            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
          }
          childPageLoad(event, nodeDate);
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (result) {
          swal({
            title: result.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        },
      });
    }

    //逻辑UI,UX上新增AI，AO,DI
    function loadUiUx(f_type) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_noteInfoByNoteType",
        contentType: "application/json; charset=utf-8",
        type: "get",
        data: {
          f_type: f_type,
        },
        beforeSend: function () {
          showLoad();
        },
        success: function (returnObject) {
          var funNames = "";
          var funNodeTypes = "";
          if (returnObject.hasOwnProperty('data')) {
            if (returnObject.data.hasOwnProperty('f_fun_mcs')) {
              funNames = returnObject.data.f_fun_mcs.split(",");
            }
            if (returnObject.data.hasOwnProperty('f_fun_nodetype')) {
              funNodeTypes = returnObject.data.f_fun_nodetype.split(",");
            }
          }
          imgMap.put("on_" + returnObject.data.f_node_type, "url('"
              + returnObject.data.f_online_img + "')");
          imgMap.put("off_" + returnObject.data.f_node_type, "url('"
              + returnObject.data.f_offline_img + "')");
// 				addFucMenu(nodeData, funNames, funNodeTypes);//给选中节点添加功能添加菜单
          if (!(typeof (_type) == 'undefined')) {//当节点类型不为空时
            if (!returnObject.data.hasOwnProperty('f_edit_url') || f_sys_name == '') {//数据库中没有取到对应的路径
              $('#sbdyInfo').html("");//清空属性信息
              return;
            }
            var variableUrl = returnObject.data.f_edit_url.split("/")[5];

            $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/" + variableUrl,
                {
                  "f_sys_name": selectedNode.id,
                  "nodeTabName": returnObject.data.f_node_table,
                  "f_type": f_type
                });//跳转所选节点属性页面
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (returnObject) {
          swal({
            title: returnObject.msg,// 展示的标题
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    }

    /* // 获取 ddc 数据
     function getDDRealTimeInfo(ddcName) {

         if($('#tree_sbdy').length <= 0){
             return;
         }

         if(typeof ddcName !== 'string'){
             console.warn('非法参数！');
             return;
         }

         runStateMap.put(ddcName, true);

         $.ajax({//加载树节点信息
             type: "post",
             url: _ctx + "/view/basedatamanage/eqmanage/getDDRealTimeInfo",
             data: {
                 sysName: ddcName
             },
             success: function (result) {
                 var name = ddcName;
                 if(result.status === '1' && ddcNodeExpandedMap.get(name)){
                     refreshDDCTree(result.data);
                     getDDRealTimeInfo(name);
                 }else {
                     runStateMap.put(ddcName, false);
                 }
             }
         })

     }*/

    function refreshDDCTree(data) {

      if (!Array.isArray(data)) {
        return;
      }

      for (var i = 0; i < data.length; i++) {
        var item = data[i];

        if (!item) {
          continue;
        }

        var f_status = item.f_status;
        var f_type = item.f_type;
        var f_sys_name = item.f_sys_name || '';
        var f_nick_name = item.f_nick_name || '';
        var f_init_val = item.f_init_val || '';
        var unit = item.unit || '';

        //设置节点图标
        /*if (f_status == '0'){
            $("#" + f_sys_name).children("span[class='image node-image']")
                    .css("background-image", imgMap.get("off_" + f_type));
        }else if(f_status == '1'){
            $("#" + f_sys_name).children("span[class='image node-image']")
                    .css( "background-image", imgMap.get("on_" + f_type));
        }*/
        //更改逻辑点实时数据
        if (f_type == '13') {
          if (unit != null && unit != "" && unit != "null") {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + unit + " | " + f_nick_name)
                .css("margin-left", "2%");
          } else {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + " | " + f_nick_name)
                .css("margin-left", "2%");
          }
        } else if (f_type == '11') {
          if (unit != null && unit != "" && unit != "null") {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + unit + " | " + f_nick_name)
                .css("margin-left", "2%");
          } else {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + " | " + f_nick_name)
                .css("margin-left", "2%");
          }
        } else if (f_type == '10') {
          if (unit != null && unit != "" && unit != "null") {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + unit + " | " + f_nick_name)
                .css("margin-left", "2%");
          } else {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + " | " + f_nick_name)
                .css("margin-left", "2%");
          }
        } else if (f_type == '12') {
          if (unit != null && unit != "" && unit != "null") {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + unit + " | " + f_nick_name)
                .css("margin-left", "2%");
          } else {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + " | " + f_nick_name)
                .css("margin-left", "2%");
          }
        } else if (f_type == '16') { //虚点类型

          if (unit != null && unit != "" && unit != "null") {
            $("#" + f_sys_name).children("span[class='text']")
                .text(f_init_val + unit + " | " + f_nick_name)
                .css("margin-left", "2%");
          } else {
            $("#" + f_sys_name).children("span[class='text']")
                .text((f_init_val || '') + " | " + f_nick_name)
                .css("margin-left", "2%");
          }
        } else if (f_type == '14') { //UI类型
          $("#" + f_sys_name).children("span[class='text']")
              .text(f_nick_name)
              .css("margin-left", "2%");
        }

      }
    }

    function refreshTree(data) {
      if (!Array.isArray(data)) {
        return;
      }

      for (var i = 0; i < data.length; i++) {

        var item = data[i];

        if (!item) {
          return;
        }

        var name = item.name || '';
        var alias = item.alias || '';
        var value = item.value || '';
        var unit = item.unit || '';

        $('#' + name).children('span[class="text"]')
            .text(value + unit + ' | ' + alias)
            .css('margin-left', '2%');

      }
    }

    function refreshTreeSingle(data) {

      if (!data) {
        return;
      }

      var name = data.name || '';
      var alias = data.alias || '';
      var value = data.value || '';
      var unit = data.unit || '';

      $('#' + name).children('span[class="text"]')
              .text(value + unit + ' | ' + alias)
              .css('margin-left', '2%');
    }

    //fileinput 上传插件初始化
    function initFileinput() {
      $("#sbdyTypeInputFile").fileinput({
        language: 'zh', //设置语言
        uploadUrl: '${ctx}/view/basesbdy/excel/bessbdyTypeFileUpload',//上传请求路径
        allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀,
        showUpload: true, //是否显示上传按钮
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        maxFileSize: 3072,//最大单个文件上传大小
        maxFileCount: 1,//最大上传个数
        showUpload: false,//是否显示上传按钮
        showBrowse: true,//是否显示浏览按钮
        showPreview: false,//是否显示预览区域
        autoReplace: true,//是否自动替换
        showRemove: true,//是否显示移除按钮
        uploadExtraData: function (id, index) {
          return {"fold": "sbdyTypeImportExcel"};
        },//区分不同的模块：fold：文件夹
        //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
        deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
      }).on("fileuploaded", function (event, data, previewId, index) {
        // dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType();
        clearForm();//清空form表单
        var res = data.response;
        if (res.status == '1') {
          swal({title: res.msg, type: "success", showCloseButton: false});
          $('#sbdyInfo').html("");//清空属性信息
          $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/sbdy");
          //跳转所选节点属性页面
        } else {
          swal({title: res.msg, type: "error", showCloseButton: false});
          if (res.hasOwnProperty("list")) {
            var errorList = JSON.stringify(res.list);
            var data = {
              errorString: errorList,
            };
            var _url = "${ctx}/view/basedatamanage/enegrycollectionmanage/exportErrorExcel";
            var exportName = "电表类型定义导入错误报告";
            doExp(_url, exportName, "${ctx}", data);//导出excel并下载

          }

        }

      }).on("filebatchuploadsuccess", function () {
        clearForm();//清空form表单
      });
    }

    //清空上传文件表单form 关闭模态框 并提示
    function clearForm() {
      $("#sbdyTypeInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
      $("#sbdyType_import_model").modal("hide");//关闭模态框
    }

    //下拉框列表查询
    function fNybhCollMethod(keywords) {
      $.ajax({
        type: "POST",
        url: _ctx + "/view/basedatamanage/eqmanage/select_park",
        data: "",
        beforeSend: function () {
          showLoad();
        },
        success: function (data) {

          var ops = "<option value=''>请选择能源编号</option>";
          for (var i = 0; i < data.list.length; i++) {
            var obj = data.list[i];
            ops += '<option value="' + obj.f_yqbh + '">';
            ops += obj.f_yqbh + '(' + obj.f_yqmc + ')';
            ops += '</option>';
          }
          if (keywords == 'add') {
            $("#f_yubh").append(ops);
          }
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (msg) {
          alert("下拉框列表查询失败!");
        }
      });
    }

    //关闭模态框清空表单值
    $("#modal-form-addsbdy").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      $("#f_yubh").empty();
      groupValidator.resetForm();
    });

    //添加采集参数表单验证
    var groupValidator = $("#addCollMform").validate({
      submitHandler: function (nodeData) {
        addformCollM(nodeData);
      }
    });

    //新增保存
    function addformCollM(nodeData) {
      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/insert_sbdy_node_first",
        type: "post",
        data: ({
          f_yqbh: $("#f_yubh").val(),
          f_descrption: $("#f_descrption").val(),
          f_nick_name: $("#f_nick_name_sbdy").val()
        }),
        beforeSend: function () {
          showLoad();
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
              timer: 1000,
            });
            $('#sbdyInfo').html("");//清空属性信息
            $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/sbdy");
          } else {
            swal(data.msg, "", "error");
          }
          $('#modal-form-addsbdy').modal('hide');//关闭编辑窗口
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (data) {
          swal(data.msg, "", "error");
        }
      });
      return false;
    }


    //居中显示
    $('#sbdyType_import_model').on('show.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      $(this).css('display', 'block');
      var modalHeight = $(window).height() / 2 - $('#sbdyType_import_model .modal-dialog').height() / 2;
      $(this).find('.modal-dialog').css({
        'margin-top': modalHeight
      });
    })
    return {
      getImgMap: function () {
        return imgMap;
      },
      getEditUrlMap: function () {
        return editUrlMap;
      },
      getIdMap: function () {
        return idMap;
      },
      setIdMap: function (key, value) {
        idMap.put(key, value);
      },
      getTreeSelNode: function () {
        return treeSelNode;
      },
      getTmp_treeSelNode: function () {
        return tmp_treeSelNode;
      },
      getAddNodeTextVar: function () {
        return addNodeTextVar;
      },
      getAddNodeTextCon: function () {
        return addNodeTextCon;
      },
      getIsShow: function () {
        return isShow;
      },
      getIsAdd: function () {
        return isAdd;
      },
      setIsAdd: function (add) {
        isAdd = add;
      },
      getAddStaus: function () {
        return addStaus;
      },
      setAddStaus: function (aStaus) {
        addStaus = aStaus;
      },
      getAddNodeId: function () {
        return addNodeId;
      },
      //获取选中节点在树上的索引值
      getNodeIndex: function () {
        return nodeIndex;
      },
      //获取选中节点的父节点的id
      getNodepid: function () {
        return pid;
      },
      //获取选中节点在树上的属性信息
      getSelectedNode: function () {
        return selectedNode;
      },
      //获取选中节点在树上的级数
      getnodeLevel: function () {
        return nodeLevel;
      },
      //获取选中节点的节点类型
      getSelectedNodeType: function () {
        return nodeType;
      },
      //获取选中节点所属系统
      getnodeAttribution: function () {
        return nodeAttribution;
      },
      //获取园区编号
      getyqbh: function () {
        return yqbh;
      },
      //设备树上按钮点击事件
      btnMenuClick: function (object) {

        var nodeId = object.parentNode.parentNode.getAttribute("id");
        var funName = object.getAttribute("value");
        var f_type = object.getAttribute("nodeType");
        var url = editUrlMap.get(f_type);

        var attrMap = $('#tree_sbdy').treeview('findNodes', [nodeId, 'id'])[0].nodes;


        var defaultNode = "新增线路";//默认节点的新增线路
        if (f_type == "32") {//编程
          $("#sbdyInfo").load(_ctx + "/view/basedatamanage/eqmanage/sbdy_ddcInfoBc",
              {
                "f_sys_name": selectedNode.id,
                "f_id": selectedNode.rootId
              });//跳转所选节点属性页面
          return;
        }
        ;
        if (f_type == null || f_type == '') {//删除操作
          if (treeSelNode[0].hasOwnProperty('nodes')) {//判断该节点是否含有子节点
            swal({
              title: "该节点下含有子节点，您确定要一起删除吗?",
              text: "信息删除后将不可恢复!",
              type: "warning",
              showCancelButton: true,
              confirmButtonColor: "#1ab394",
              confirmButtonText: "确定",
              closeOnConfirm: true
            }, function () {
              delByNode(nodeId, f_type);//删除节点和子节点
            });
          } else {
            swal({
              title: "您确定要删除吗?",
              text: "信息删除后将不可恢复!",
              type: "warning",
              showCancelButton: true,
              confirmButtonColor: "#1ab394",
              confirmButtonText: "确定",
              closeOnConfirm: true
            }, function () {
              delByNode(nodeId, f_type);//删除该节点
            });
          }
          return;
        }
        ;
        if (f_type == "40") {//默认添加总线下面的节点
          addDefaultNodeByBus(f_type, defaultNode);
          return;
        }
        ;
        if ((f_type != null || f_type != '')
            && (url == null || typeof (url) == 'undefined'
                || url == '/besview/basedatamanage/eqmanage/eqconfiguration/sbdy_busInfo_cross'
                || url == '/besview/basedatamanage/eqmanage/eqconfiguration/sbdy_lineInfo_cross'
                || url == '/besview/basedatamanage/eqmanage/eqconfiguration/sbdy_vpointInfo_cross')) {//新增一个无属性默认节点
          addDefaultNode(f_type, funName, attrMap);
          return;
        }
        ;
        if (f_type.length != null && f_type.length == 6) {//逻辑点调试按钮
          dubugNode(f_type);
          return;
        }
        ;
        if (f_type == "30") {//逻辑点删除按钮

          reSet();//删除节点和子节点
          return;
        }
        ;


        if (f_type == "10" || f_type == "11" || f_type == "12") {//逻辑点UI,UX上新增AI，AO，DI按钮
          loadUiUx(f_type);
          return;
        }
        ;
        addByNode(f_type);//新增一个节点
      },
      //设置页面可以保存
      setWebCanClose: function () {
        sessionStorage.setItem("#" + isspGlobal.getNthTabs().getActiveId() + "-isspTabCloseable", "true");//当前页面默认可关闭
      },
      //设置未保存，询问是否保存(提示信息)
      webNotSaveWithMsg: function () {
        sessionStorage.setItem("#" + isspGlobal.getNthTabs().getActiveId() + "-isspTabCloseable", "false");//如 #204-isspTabCloseable
        sessionStorage.setItem("#" + isspGlobal.getNthTabs().getActiveId() + "-isspTabCloseableMsg", "【" + isspGlobal.getNthTabs().getActiveName()
            + "】页面未保存，确定关闭吗？");
      },
      //刷新节点状态
      refreshIcon: function (f_sys_name) {
        if (judgeActive("eqconfiguration_sbdy_div")) {//页面活动时
          $.ajax({//加载树节点信息
            type: "post",
            url: _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
            // data: {f_sys_name: "qa2, qa1"},
            data: {f_sys_name: f_sys_name},
            success: function (returnObject) {

              if (returnObject.hasOwnProperty('list')) {
                var updateInfo = returnObject.list;
                refreshDDCTree(updateInfo);
              }
            },
            error: function (returnObject) {
              swal({
                title: returnObject.msg,// 展示的标题
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
            }
          });
        }

      },
      syncOpc: function () {
        $.issp_ajax({
          type: "post",
          url: _ctx + "/view/basedatamanage/eqmanage/syncOpc",
          success: function (result) {
            if (result.status == "1") {
              swal({
                title: result.msg,// 展示的标题
                text: "",// 内容
                type: "success",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
            } else {
              swal({
                title: result.msg,// 展示的标题
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500,
              });
            }
          }
        });
      },

      // selectNodeAttribution : function(callback){
      //     var f_guid = basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getNodefGuid();
      //     $.ajax({
      //         url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/f_node_attributionByPonit",
      //         type : "post",
      //         data : {
      //             f_sbid : f_guid,
      //             f_sys_name : selectedNode.icon,
      //         },
      //         beforeSend: function () {
      //             showLoad();
      //         },
      //         success : function(result) {
      //             callback(result)
      //         },
      //         complete: function () {
      //             hiddenLoad();
      //         },
      //         error : function(result) {
      //             swal(result.msg, "", "error");
      //         }
      //     });
      // },
      addNodeTextCon,
      refreshTree,
      onlineStateHandle: function (data) {
        if (!Array.isArray(data)) {
          return;
        }

        for (var i = 0; i < data.length; i++) {

          var item = data[i];
          if (!item) {
            return;
          }
          //设置节点图标
          if (item.f_status == '0') {
            $("#" + item.f_sys_name).children("span[class='image node-image']")
                .css("background-image", imgMap.get("off_" + item.f_type));
          } else if (item.f_status == '1') {
            $("#" + item.f_sys_name).children("span[class='image node-image']")
                .css("background-image", imgMap.get("on_" + item.f_type));
          }
        }
      },
      /* ddcNodeExpandedMap: ddcNodeExpandedMap,
       getDDRealTimeInfo: getDDRealTimeInfo,
       runStateMap: runStateMap*/
      impReport: function () {
        //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
        $("#sbdyType_import_model").modal("show");
        initFileinput();//初始化fileinput
      },
      impReportsbdy: function () {
        //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
        $("#modal-form-addsbdy").modal("show");
        fNybhCollMethod('add');
      },
      exportReport: function () {
        //1.先弹出导出文件模态框--2.选择点位进行导出操作--前端工作完成
        $("#sbdyType_export_model").modal("show");
      },
      //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
      btn_expBuildingControl: function () {
        var fname = "楼宇模板.xls";
        var path = "file\\expExcel\\楼宇模板.xls";
        FileDownload(_ctx + filePath.loadPath, fname, path);
      },
      btn_expIllumination: function () {
        var fname = "照明模板.xls";
        var path = "file\\expExcel\\照明模板.xls";
        FileDownload(_ctx + filePath.loadPath, fname, path);
      },
      btn_expEnergy: function () {
        var fname = "能耗模板.xls";
        var path = "file\\expExcel\\能耗模板.xls";
        FileDownload(_ctx + filePath.loadPath, fname, path);
      },
      //导入数据按钮
      btn_import: function () {
        var filepath = $("#sbdyTypeInputFile").val();
        if (filepath != "") {
          $("#sbdyTypeInputFile").fileinput("upload");//上传方法
        } else {
          swal({title: "请上传数据文件!", type: "warning", showCloseButton: true});
        }
      },
      refreshTreeSingle,
      refreshLeftTree
    };

  })(jQuery, window, document);
</script>

<!-----内容结束----->

