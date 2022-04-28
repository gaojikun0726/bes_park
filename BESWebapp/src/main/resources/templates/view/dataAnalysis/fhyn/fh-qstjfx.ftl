<#--
数据分析-用能统计分析
-->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">

  /*.qstjfhFixHead {
    !*background-color: #042D4B !important;*!
    border: 2px solid #c1c1c1 !important;
  }

  .qstjfhFix {
    border: 2px solid #366C90 !important;
  }*/

  thead th {
    text-align: center;
  }

  tbody td {
    text-align: center;
    vertical-align: middle;
  }


  .zl_qstjfx {
    display: flex;
    align-items: center;
    margin-right: 3vh;
    float: right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 7px 0px 1px 0px;
  }

  .treeview span.icon {
    width: 5px !important;
    margin-right: 10px !important;
  }


  .zl_qstjfx input {
    border-radius: 4px;
  }

  .fh_qstjfx {
    width: 100%;
    height: 96%;
    position: relative;
  }

  .height_ds {
    liear: both;
  }
</style>
<!-- 组织机构树模块 -->
<ul id="qstjfh_tab" class="nav tabs tsys">
</ul>
<div class="leftarea information_left">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;加载数据条件>>>
		</span>
  </div>
  <div class="fh_qstjfx">
    <div class="height_ds">
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">日期范围：</span>
        <div id="qstjfh_group">
        </div>
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">时间起始： </span>
        <input id="qstjfh_start_time" disabled="disabled" type="text" name="start"
               onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" class="input-datecheck">
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">时间终止： </span>
        <input id="qstjfh_end_time" disabled="disabled" type="text" name="end"
               onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" class="input-datecheck">
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">图表类型： </span>
        <div id="qstjfh_table_group">
        </div>
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">时间颗粒度：</span>
        <div id="qstjfh_time_group"></div>
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">单位换算：</span>
        <div id="qstjfh_dw_group"></div>
      </div>
      <div class="zl_qstjfx fh_qstjfx_jsgd"><span class="zl_sxtj_span">请选择分户>>> </span>
        <input type="text" style="visibility: hidden;" class="input-datecheck">
        <label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
          <input id="qstjfhCascade" type="checkbox" checked value="1">是否级联
        </label>
      </div>
    </div>
    <div id="tree_qstjfh" style="overflow-y: auto;overflow-x: auto;width: 100%;"></div>
  </div>
  <div class="botton_fhqstjfx"
       style="height:5%;position: absolute;width:100%;bottom: 0;">
    <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
      <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh.sub()">
        <i class="fa fa-spinner"></i>&nbsp;加载数据
      </button>
      <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh.exp()">
        <i class="fa fa-download"></i>&nbsp;报表生成
      </button>
      <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh.reset()">
        <i class="fa fa-refresh"></i>&nbsp;重置
      </button>
    </div>
  </div>
</div>
<!---echars---->
<div class="information_right"><!--Echars -->
  <div class="information_size" style="height: 50%;border-bottom: solid 2px #366c90;">
    <div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
			</span>
    </div>
    <div id="qstjfh_chart" class="Information_areaq"></div>
  </div>
  <!---table---->
  <div class="information_size" style="height: 50%"><!-- 列表展示 -->
    <div class="information-model" style="height:3.3vh">
     		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据展示>>>
			</span>
        <#--打印按钮-->
      <a href="javascript:void(-1);" onclick="dataannlysis_fh_qstjfh.print()"
         class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
      </a>
    </div>
      <#--    <table class="table table-bordered tablestyle" id="zl_qstjfxdw_table_fix"-->
      <#--           style="white-space: nowrap;  overflow-x: scroll;">-->
      <#--    </table>-->

    <table id="qstjfhTable" class="table table-bordered" style="white-space: nowrap;  overflow-x: scroll;">
      <thead>
      <tr id="qstjfhday" class="header_color">
      </tr>
      </thead>
      <tbody id='qstjfh_body'>
      <tr>
      </tr>
      </tbody>
    </table>
  </div>
</div>
<script src="${ctx}/static/js/time_range.js"></script><!-- 时间范围工具 -->
<script type="text/javascript">
  ;
  var dataannlysis_fh_qstjfh = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var _curRow = null;
    var cascadeType = false;//是否级联
    var fnybh = "";//fnybh 能耗类型
    var tblx = "";//图标类型
    var sjkld = "";//时间颗粒度
    var dwhs = "";//单位换算
    var dwhsmc = "";//单位换算名称
    var checkid = "";//默认获取第三级第一个id
    //时间 select 传递数组
    var idArray = ['bt', 'bz', 'by', 'bj', 'bn', 'sr', 'sz', 'sy', 'sj', 'sn', 'zdy'];
    var valArray = ['本天', '本周', '本月', '本季', '本年', '上日', '上周', '上月', '上季', '上年', '自定义'];
    //图标类型传递数组
    var tb_idArray = ['line', 'bar'];
    var tb_valArray = ['曲线图', '柱状图'];
    //时间颗粒度
    var sjkld_idArray = ['0', '1', '2', '3'];
    var sjkld_valArray = ['时', '日', '月', '年'];
    //单位换算
    var dwhs_idArray = ['0', '1', '2', '3', '4', '5', '6', '7'];
    var dwhs_valArray = ['总能耗(Kwh)', '二氧环碳(ppm)', '总金额(元)', '耗煤量(吨)', '人均能耗(Kwh)', '人均金额(元)', '单位面积能耗(Kwh)', '单位面积金额(元)'];


    var householdConfAndEnergyTypeData = {};
    var checkNodes = [];//选中的节点数组
    var deWhetherToManClick = true;//判断是否手动点击树节点
    //select 日期范围下拉框
    function rqfw_select(idArray, valArray) {
      $("#qstjfh_group").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: timeChange,  //自定义事件
      });
    }

    //日期范围修改事件
    function timeChange(sp) {
      var id = sp.id;//
      $("#qstjfh_start_time,#qstjfh_end_time").removeAttr("disabled").attr("disabled", true);
      $("#qstjfh_start_time,#qstjfh_end_time").removeAttr("cursor").css("cursor", "not-allowed");
      //联动规则
      switch (id) {
          //0表示今天
        case "bt":
          var time = getCurrentDate();
          $('#qstjfh_start_time').val(getFormatDate(time));
          $('#qstjfh_end_time').val(getFormatDate(time));
          break;
          //1本周
        case "bz":
          var time = getCurrentWeek();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //2本月
        case "by":
          var time = getCurrentMonth();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //3本季
        case "bj":
          var time = getCurrentSeason();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //4本年
        case "bn":
          var time = getCurrentYear();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //5表示昨天
        case "sr":
          var time = getPreviousDate();
          $('#qstjfh_start_time').val(getFormatDate(time));
          $('#qstjfh_end_time').val(getFormatDate(time));
          break;
          //6上周
        case "sz":
          var time = getPreviousWeek();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //7上月
        case "sy":
          var time = getPreviousMonth();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //8上季
        case "sj":
          var time = getPreviousSeason();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //9上年
        case "sn":
          var time = getPreviousYear();
          $('#qstjfh_start_time').val(getFormatDate(time[0]));
          $('#qstjfh_end_time').val(getFormatDate(time[1]));
          break;
          //自定义
        case "zdy" :
          $("#qstjfh_start_time,#qstjfh_end_time").removeAttr("disabled");
          $("#qstjfh_start_time,#qstjfh_end_time").css("cursor", "default");
          break;
        default:
          break;
      }
    }

    //图标初始化selected
    function tblx_select(tb_idArray, tb_valArray) {
      $("#qstjfh_table_group").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: tb_idArray,//id
        valArray: tb_valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: tbChange,  //自定义事件
      });
    }

    //图表改变事件
    function tbChange(sp) {
      tblx = sp.id;//每次改变赋值给tblx
    }

    //时间颗粒度 selected
    function sjkld_select(sjkld_idArray, sjkld_valArray) {
      $("#qstjfh_time_group").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: sjkld_idArray,//id
        valArray: sjkld_valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: sjkldChange,  //自定义事件
      });
    }

    //时间颗粒度 change
    function sjkldChange(sp) {
      sjkld = sp.id;
    }

    //单位换算 selected
    function dwhs_select(dwhs_idArray, dwhs_valArray) {
      $("#qstjfh_dw_group").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: dwhs_idArray,//id
        valArray: dwhs_valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: dwhsChange,  //自定义事件
      });
    }

    //时间颗粒度 change
    function dwhsChange(sp) {
      dwhs = sp.id;
      dwhsmc = sp.txt;
    }

    //Echars图表展示 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstjfh_chart'));

    $(window).resize(function () {
      myChart.resize();
      setTimeout(function () {
        getHeight()
      }, 1);
// 		getHeight();
    });

    //自动获取高度并赋予
    function getHeight() {
      //获取左侧高度
      var allheight = $(".fh_qstjfx").height();//总高度
      var botton = $(".botton_fhqstjfx").height();//底部
      var num = $(".fh_qstjfx_jsgd").length;//
      var tj = $(".fh_qstjfx_jsgd").outerHeight() - 1;
      var s = allheight - (num * tj) - botton;
      $("#tree_qstjfh").height(s);
    }

    //动态拼装tab
    $(function () {
      tab_load();
    });

    //获取级联方法
    function Cascade() {
      var cascade = "";//定义级联数组
      $("#qstjfhCascade[type=checkbox]:checked").each(function (i) {//each循环所有:checkbox
        cascade = $(this).val();
      });
      if (cascade == '' || cascade == 'undefined' || cascade == null) {//没获取到 则为0  不级联
        cascadeType = false;
      } else {
        cascadeType = true;
      }
    }

    //初始化时间
    function refreshTime() {
      var time = getCurrentDate();//获取当前时间
      $('#qstjfh_start_time').val(getFormatDate(time));
      $('#qstjfh_end_time').val(getFormatDate(time));
      $("#qstjfh_start_time,#qstjfh_end_time").removeAttr("cursor").css("cursor", "not-allowed");
    }

    //tab方法
    function tab_load() {
      refreshTime();
      Cascade();//获取级联方法
      $.ajax({
        type: "post",
        url: _ctx + "/view/dataAnalysis/zl_tablist",
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            var opt = "";
            for (var i = 0; i < result.list.length; i++) {
              var obj = result.list[i];
              opt += "<li><a href='#home' value='" + obj.ID + "' data-toggle='tab' onclick='dataannlysis_fh_qstjfh.tabclick(this)'>" + obj.NAME + "</a></li>";
            }
            $("#qstjfh_tab").append(opt);
            $("#qstjfh_tab").find("li").eq(0).addClass("active nocancel");
            fnybh = $("#qstjfh_tab").find("li>a").eq(0).attr("value");
          }
          left_tree(fnybh, true, []);
        },
        complete: function () {
          hiddenLoad();
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    //加载左侧树
    function left_tree(fnybh, refreshType, AllChecked) {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
        data: {"fNybh": fnybh},
        dataType: "json",
        success: function (result) {
          if (result.hasOwnProperty("list")) {//返回tree成功
            $('#tree_qstjfh').treeview({
              data: result.list,         // 数据源
              highlightSelected: true,    //是否高亮选中
              levels: 1,
              enableLinks: true,//必须在节点属性给出href属性
              wrapNodeText: true,
              color: "#4a4747",
              showCheckbox: true,
              // hierarchicalCheck: cascadeType,//级联勾选
              propagateCheckEvent: true,
              onNodeChecked: function (event, nodeData) {//选中方法
                //级联的情况下,选中时默认将子节点和父节点选中（级联的情况下,不级联的时候默认选中选择的节点）
                if (deWhetherToManClick) {
                  deWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                  nodeChecked(nodeData);
                }
              },
              onNodeUnchecked: function (event, nodeData) {//取消方法
                if (deWhetherToManClick) {
                  deWhetherToManClick = false;//判断是否手动点击树节点(点击过后首先设置为false)
                  unnodeChecked(nodeData);
                }
              },
            });

            // //点击是否级联是,默认加载当前选中的节点
            // if (!deWhetherToManClick) {
            //   AllChecked.forEach((chickNode,i) => {
            //     $('#tree_qstjfh').treeview('checkNode', [chickNode, {silent: true}]);
            //   })
            //   deWhetherToManClick = true
            // }
            // var firstNode = $("#tree_qstjfh").treeview('findNodes', [result.list[0].id, 'id']);//一级
            // var node = $("#tree_qstjfh").treeview('findNodes', [firstNode[0].id, 'id']);//二级
            // checkid = node[1].id;

            //默认加载第一个节点下第一个子节点
            // traverseFirstNode(node);

            // first_check(checkid, refreshType, AllChecked,nodes);//默认勾选
          } else {//树查询失败
            swal({
              title : '当前能源下暂无支路配置!',// 展示的标题
              text : "",// 内容
              type : "warning",
              showCloseButton : false, // 展示关闭按钮
              allowOutsideClick : false,
              showConfirmButton : false,
              timer : 1000
            });
            $('#tree_qstjfh').treeview('remove');//移除列表树容器。
            $('#tree_qstjfh').treeview('uncheckAll', {silent: true});//清空所有check
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    //选中方法
    function nodeChecked(nodeData) {
      let childNodes = [];//选中节点的子节点集合
      checkNodes.length = 0;//清空选中的节点的数组

      //获取设备树所有节点
      let allNodes = $("#tree_qstjfh").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_qstjfh").treeview('getChecked');
        //获取父节点
        let fatherNodeId = nodeData.pid;
        allNodes.forEach((node,i) => {
          if (node.nodeTreeId == fatherNodeId) {
            fatherNodeId = node;
            fatherNodes(fatherNodeId,allNodes);
          }
        })

        //获取子节点(如果有的话)
        if (Array.isArray(nodeData.nodes)) {
          childNodes = nodeData.nodes;
          childNodesFunction(childNodes,allNodes);
        }

        checkNodes.forEach((node,i) =>{
          $('#tree_qstjfh').treeview('checkNode', [node, {silent: false}]);
        })
      }
      deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
    }

    //获取父节点集合，将父节点集合放到选中的节点集合里面
    function fatherNodes(fatherNodeId,allNodes) {
      checkNodes.push(fatherNodeId);
      let result = [];
      let obj = {};
      for(var i =0; i<checkNodes.length; i++){
        if(!obj[checkNodes[i].nodeTreeId]){
          result.push(checkNodes[i]);
          obj[checkNodes[i].nodeTreeId] = true;
        }
      }
      checkNodes = result;
      //判断父节点是否含有父节点
      if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
        allNodes.forEach((node,i) => {
          if (node.nodeTreeId == fatherNodeId.pid) {
            fatherNodeId = node;
            fatherNodes(fatherNodeId,allNodes);
          }
        })
      }
    }

    //获取子节点集合，将子节点集合放到选中的节点集合里面
    function childNodesFunction(childNodes,allNodes) {
      //遍历子节点集合,判断子节点中是否含有子节点
      childNodes.forEach((childNode,i) => {
        checkNodes.push(childNode);
        if (Array.isArray(childNode.nodes)) {
          childNodesFunction(childNode.nodes,allNodes);
        }
      })
      let result = [];
      let obj = {};
      for(var i =0; i<checkNodes.length; i++){
        if(!obj[checkNodes[i].nodeTreeId]){
          result.push(checkNodes[i]);
          obj[checkNodes[i].nodeTreeId] = true;
        }
      }
      checkNodes = result;
    }

    //取消方法
    function unnodeChecked(nodeData) {

      let childNodes = [];//选中节点的子节点集合
      checkNodes.length = 0;//清空选中的节点的数组

      //获取设备树所有节点
      let allNodes = $("#tree_qstjfh").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_qstjfh").treeview('getChecked');
        //获取父节点
        let fatherNodeId = nodeData.pid;
        allNodes.forEach((node,i) => {
          if (node.nodeTreeId == fatherNodeId) {
            fatherNodeId = node;
            unfatherNodes(fatherNodeId,allNodes);
          }
        })

        //获取子节点(如果有的话)
        if (Array.isArray(nodeData.nodes)) {
          childNodes = nodeData.nodes;
          unchildNodesFunction(childNodes,allNodes);
        }

        checkNodes.forEach((node,i) =>{
          $('#tree_qstjfh').treeview('checkNode', [node, {silent: false}]);
        })
      }
      deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
    }

    //获取父节点集合
    function unfatherNodes(fatherNodeId,allNodes) {
      checkNodes.splice(fatherNodeId);
      $('#tree_qstjfh').treeview('uncheckNode', [fatherNodeId, {silent: true}]);
      //判断父节点是否含有父节点
      if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
        allNodes.forEach((node,i) => {
          if (node.nodeTreeId == fatherNodeId.pid) {
            fatherNodeId = node;
            unfatherNodes(fatherNodeId,allNodes);
          }
        })
      }
    }

    //获取子节点集合
    function unchildNodesFunction(childNodes,allNodes) {
      //遍历子节点集合,判断子节点中是否含有子节点
      childNodes.forEach((childNode,i) => {
        checkNodes.splice(childNode);
        $('#tree_qstjfh').treeview('uncheckNode', [childNode, {silent: true}]);
        // checkNodes.push(childNode);
        if (Array.isArray(childNode.nodes)) {
          unchildNodesFunction(childNode.nodes,allNodes);
        }
      })
    }

    //是否级联 点击事件
    $("#qstjfhCascade").click(function () {
      //只有当点击是否级联的时候不刷新数据
      // deWhetherToManClick = !deWhetherToManClick;
      let refreshType = false;
      let AllChecked = $("#tree_qstjfh").treeview('getChecked');
      if ($('#qstjfhCascade').is(':checked')) {
        cascadeType = true;
        deWhetherToManClick = true;
        // left_tree(fnybh, refreshType, AllChecked);
      } else {
        cascadeType = false;
        deWhetherToManClick = false;
        // left_tree(fnybh, refreshType, AllChecked);
      }
    });

    //时间js----判断时间条件
    function timeFormat() {
      var startTime = $('#qstjfh_start_time').val();
      var endTime = $('#qstjfh_end_time').val();
      if (startTime == '' || endTime == '') {
        swal("请输入查询时间段", "", "warning");
        return false;
      }
      var date1 = new Date(startTime.replace(/-/g, "/"));
      var date2 = new Date(endTime.replace(/-/g, "/"));
      if (date2.getTime() < date1.getTime()) {
        swal("开始时间不能大于结束时间！", "", "warning");
        return false;
      }
      return true;
    }

    //echarts数据
    function chartShow(xdata, legend, series, x_unit, y_unit) {
      var dom = document.getElementById("qstjfh_chart");
      var myChart11 = echarts.init(dom, "light");
      var option = {
        color: ['#EE9201', '#29AAE3', '#B74AE5', '#0AAF9F', '#E89589', '#16A085', '#4A235A', '#C39BD3'],
        title: {
          text: '逐时统计分析',
          left: 'left',
          top: "2%",
          textStyle: {
            color: '#8fe3f7',
            fontSize: '14'
          },
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },

        legend: {
          type: 'scroll',
          left: "10%",
          top: "2%",
          right: "3%",
          type: 'scroll',
          data: legend,
          pageIconColor: "rgb(42, 123, 193)",
          pageFormatter: '',//隐藏翻页的数字
          pageButtonItemGap: -6,//翻页按钮的两个之间的间距
          textStyle: {
            color: '#8fe3f7'
          }
        },
        grid: {
          left: '9%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            name: x_unit,
            nameTextStyle: {
              color: '#8fe3f7',
            },
            type: 'category',
            data: xdata,
            axisLine: {
              lineStyle: {
                color: '#8fe3f7'
              }
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: '#8fe3f7'
              }
            }
          }
        ],
        dataZoom: [{
          type: 'inside',
          disabled: false
        }],
        yAxis: [
          {
            name: '单位:' + y_unit,
            nameTextStyle: {
              color: '#8fe3f7'
            },
            type: 'value',
            axisLine: {
              lineStyle: {
                color: '#8fe3f7'
              }
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(40, 76, 117, 0.2)',
              }
            },

            axisLabel: {
              show: true,
              textStyle: {
                color: '#8fe3f7'
              }
            }
          }
        ],
        series: series
      };

      if (option && typeof option === "object") {
        myChart11.setOption(option, true);
        myChart11.resize();
      }
      $(window).resize(function () {
        myChart11.resize();
        setTimeout(function () {
          getHeight()
        }, 1);
      });
    }

    // 获取分户配置信息和能源类型信息
    function getHouseholdConfAndEnergyTypeData() {

      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/getHomePageTreeData",
        dataType: "json",
        success: function (result) {

          var status = result && result.status;

          if (status !== '1') {
            return;
          }

          var data = result.data;

          if (!Array.isArray(data)) {
            return;
          }

          for (var i = 0; i < data.length; i++) {
            householdConfAndEnergyTypeData[data[i].F_FHBH] = data[i];
          }

          // getTableData();
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }
    return {
      //清空条件
      reset: function () {
        //清空table
        $("#qstjfhTable").empty();
        $('#qstjfhTable').fixedThead('destroy');//销毁
        myChart.clear();//清空echars
        $('#tree_qstjfh').treeview('uncheckAll', {silent: true});//清空所有check
        dataannlysis_fh_qstjfh.pageInit();
      },
      // 报表生成
      exp: function () {
        // excel的列头
        var alias = new Array();
        // 数据List中的Map的key值.
        var names = new Array();
        // 数据存取list
        var ALLlist = new Array();
        // jquery获取Excel表头数据
        let thead = $("#clone_qstjfhTable_head>thead>tr>th").eq(0).text();
        alias.push(thead);
        names.push("a0");
        // let thead1 = $("#clone_qstjfhTable_head>thead>tr>th").eq(1).text();
        let thead2 = $("#clone_qstjfhTable_head>thead>tr").eq(0).find("th");
        $(thead2).each(function (i) {
          if (i != 0) {
            if (($(this).html()).indexOf("div") == -1 && ($(this).html()) != "" ) {
              alias.push($(this).html());
              names.push("a" + (i));
            }
          }
          // alias.push(thead1 + " " + $(this).html());
          // names.push("a" + (i + 1));
        });
        let tbodyTr = $("#clone_qstjfhTable_head>tbody>tr");
        $(tbodyTr).each(function (i) {
          let map = {};
          let tbodyTh = $(this).find("td");
          $(tbodyTh).each(function (j) {
            let text = $(this).html();
            map['a' + j] = text;
          });
          ALLlist.push(map);
        });
        //导出--传表名和传list---jsonList
        var exportName = "用能统计分析";
        //数据json内容
        var data = {
          alias: JSON.stringify(alias),
          names: JSON.stringify(names),
          jsonList: JSON.stringify(ALLlist),
        };
        //统一导出excel接口
        var _url = "${ctx}/view/dataAnalysis/eneryCollection/expExcel";
        doExp(_url, exportName, "${ctx}", data);//导出excel并下载
      },


      //加载数据
      sub: function () {

        $('#qstjfhTable').fixedThead('destroy');//销毁

        $('#qstjfhday').html("");
        $('#qstjfh_body').html("");
        var dom = document.getElementById("qstjfh_chart");
        var myChart = echarts.init(dom, "light");
        myChart.clear();

        var qstjfh_start_time = $('#qstjfh_start_time').val();//开始时间
        var qstjfh_end_time = $('#qstjfh_end_time').val();//结束时间
        //获取选中的树节点
        let checkedZlbh = $('#tree_qstjfh').treeview('getChecked');
        let checkedZlbhs = [];
        let checkedZlbhsName = [];
        checkedZlbh.forEach((node,i) => {
          checkedZlbhs.push(node.nodeTreeId);
          checkedZlbhsName.push(node.text);
        })

        var nodeNames = [];
        var allTime = [];//存取所有时间
        var series = []; //echarts列表
        var seriesMap = {};//echarts数据map
        var seriesData = [];//echarts具体每个支路数据
        var chartXdata = [];//charts图表横坐标

        var x_unit = "";//echarts横坐标单位
        var y_unit = "";//echarts纵坐标单位
        //判断空和合法性处理
        if (!timeFormat()) {
          return;
        }
        if (checkedZlbhs.length == 0) {
          swal({
            title : '当前未选择支路!',// 展示的标题
            text : "",// 内容
            type : "warning",
            showCloseButton : false, // 展示关闭按钮
            allowOutsideClick : false,
            showConfirmButton : false,
            timer : 1000
          });
          return;
        }

        $.ajax({
          type: "post",
          url: "${ctx}/view/dataAnalysises/getHouseHoldData",
          dataType: "json",
          async: true,
          traditional: true,
          data: {
            "fType": sjkld,
            "fCjsj_start": qstjfh_start_time + " 00:00:00",
            "fCjsj_end": qstjfh_end_time+ " 23:59:59",
            "fFhbhs": checkedZlbhs
            // "fConfType" : "1"//收费站:1 服务区:2 隧道:3
          },
          beforeSend: function () {
            showLoad();
          },

          success: function (result) {

            if (result.hasOwnProperty("map") &&
                result.map.time.length > 0) {
              $('#qstjfhTable').fixedThead('destroy');//销毁
              //获取所有分项名称，echarts使用
              var fxNames = result.map['houseName'];
              for (var i = 0; i < fxNames.length; i++) {
                nodeNames.push(fxNames[i].fFhmc);

              }
              //拼接表头时间
              var time = result.map['time'];
              var qstjfhdaydata = "<th class='qstjfhFixHead' style='width:20%;white-space: nowrap;' >分户用能名称</th>";
              for (var i = 0; i < time.length; i++) {
                //不同时间类型时天月年显示不一样
                if (sjkld == '0') {
                  x_unit = "时";
                  qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                      time[i].fCjsj.substring(5, 13) + "</th>";
                  chartXdata.push(time[i].fCjsj.substring(5, 13).split(" ")[1]);
                }

                if (sjkld == '1') {
                  x_unit = "天";
                  qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                      time[i].fCjsj.substring(0, 10) + "</th>";
                  chartXdata.push(time[i].fCjsj.substring(0, 10).split("-")[2]);
                }

                if (sjkld == '2') {
                  x_unit = "月";
                  qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                      time[i].fCjsj.substring(0, 7) + "</th>";
                  chartXdata.push(time[i].fCjsj.substring(0, 7).split("-")[1]);
                }

                if (sjkld == '3') {
                  x_unit = "年";
                  qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                      time[i].fCjsj.substring(0, 4) + "</th>";
                  chartXdata.push(time[i].fCjsj.substring(0, 4));
                }

                allTime.push(time[i].fCjsj);
              }
              $('#qstjfhday').html(qstjfhdaydata);

              var tdData = "";
              var seriesData = [];
              for (var i = 0; i < checkedZlbhs.length; i++) {
                if (result.map[checkedZlbhs[i]] != null && result.map[checkedZlbhs[i]].length != 0) {
                  tdData += "<tr ><td  class='qstjfhFixHead' style='white-space: nowrap;'>" + result.map[checkedZlbhs[i]][0].fFhmc + "</td>"
                  var tdList = result.map[checkedZlbhs[i]];//分户数据集合
                  for (var j = 0; j < allTime.length; j++) {
                    var flag = '0';
                    var data = '';

                    for (var c = 0; c < tdList.length; c++) {
                      seriesData = [];
                      if (tdList[c].fCjsj == allTime[j]) {
                        flag = '1';
                        var fData = tdList[c].fData;
                        if (dwhs == '0') {
                          data = fData;
                          y_unit = "Kwh";
                        }
                        if (dwhs == '1') {
                          var f_co2 = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_CO2;
                          data = fData * f_co2;
                          y_unit = "ppm";
                        }
                        if (dwhs == '2') {
                          var f_price = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PRICE;
                          data = fData * f_price;
                          y_unit = "元";
                        }
                        if (dwhs == '3') {
                          var f_coal_amount = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_COAL_AMOUNT;
                          data = fData * f_coal_amount;
                          y_unit = "吨";
                        }
                        if (dwhs == '4') {
                          var f_person_nums = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PERSON_NUMS;
                          data = fData / f_person_nums;
                          y_unit = "Kwh";
                        }
                        if (dwhs == '5') {
                          var f_price = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PRICE;
                          var f_person_nums = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PERSON_NUMS;
                          y_unit = "元";
                          data = fData * f_price / f_person_nums;
                        }
                        if (dwhs == '6') {
                          var f_area = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_AREA;
                          data = fData / f_area;
                          y_unit = "Kwh";
                        }
                        if (dwhs == '7') {
                          var f_area = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_AREA;
                          var f_price = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PRICE;
                          data = fData * f_price / f_area;
                          y_unit = "元";
                        }

                        break;
                      }

                    }

                    data = Number(data || '0').toFixed(2);

                    if (flag == '1') {
                      tdData += "<td class='qstjfhFix' style='white-space: nowrap;'>" + data + "</td>";
                      seriesData.push(data);
                    } else {
                      tdData += "<td class='qstjfhFix' style='white-space: nowrap;'>0.00</td>";
                      seriesData.push("0.00");
                    }

                    var seriesMap = {};
                    seriesMap['name'] = result.map[checkedZlbhs[i]][0].fFhmc;
                    seriesMap['type'] = tblx;
                    seriesMap['smooth'] = 'true';
                    seriesMap['data'] = seriesData;
                    seriesMap['symbol'] = "roundRect";
                    seriesMap['symbolClip'] = true;
                    series.push(seriesMap);

                  }

                  tdData += "</tr>"
                }

              }
              $('#qstjfh_body').html(tdData);
              //数据去重
              var echartData = [];
              var obj = {};
              for (var i = 0; i < series.length; i++) {
                if (!obj[series[i].name]) {
                  echartData.push(series[i]);
                  obj[series[i].name] = true;
                }
              }

              //合并data数据，echarts使用
              for (let i = 0; i < echartData.length; i++) {
                echartData[i].data = [];
                for (let j = 0; j < series.length; j++) {
                  if (echartData[i].name == series[j].name) {
                    var data = series[j].data[0];
                    echartData[i].data.push(data);
                  }
                }
              }

              var cols = [];
              var mytable = document.getElementById("qstjfhTable");
              for (var i = 1, rows = mytable.rows.length; i < rows; i++) {
                for (var j = 0, cells = mytable.rows[i].cells.length; j < cells; j++) {
                  cols.push(mytable.rows[i].cells[1].innerHTML);
                  break;
                }
              }

              for (var i = 0; i < cols.length; i++) {
                echartData[i].data[0] = cols[i];
              }
              chartShow(chartXdata, nodeNames, echartData, x_unit, y_unit);
              $('#qstjfhTable').fixedThead({side: '85%', body: '85%', thead: '93%', row: 1, col: 1});//固定表头和第一列

            } else {
              swal({
                title : '没有搜索到要查找的内容!',// 展示的标题
                text : "",// 内容
                type : "warning",
                showCloseButton : false, // 展示关闭按钮
                allowOutsideClick : false,
                showConfirmButton : false,
                timer : 1000
              });
            }

          },
          complete: function () {
            hiddenLoad();

          }
        });
      },
      //tab点击事件
      tabclick: function (object) {
        $(object.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
        var val = object.getAttribute("value");
        fnybh = val;//赋值给能源编号
        left_tree(val, true, []);
        //清空table
        $("#qstjfhTable").empty();
        $('#qstjfhTable').fixedThead('destroy');//销毁
        myChart.clear();
        dataannlysis_fh_qstjfh.pageInit();
      },
      pageInit: function () {
        rqfw_select(idArray, valArray);
        tblx_select(tb_idArray, tb_valArray);
        sjkld_select(sjkld_idArray, sjkld_valArray);
        dwhs_select(dwhs_idArray, dwhs_valArray);
        getHeight();
        getHouseholdConfAndEnergyTypeData();
      },
      //打印按钮
      print: function () {
        $("#clone_qstjfhTable_corner").printThis({});
      },
    }
  })(jQuery, window, document);
  dataannlysis_fh_qstjfh.pageInit();
</script>
