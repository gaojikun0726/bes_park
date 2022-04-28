<#--
描述： 分户用能趋势统计分析(按单位)
作者： liuzhen
-->
<style>


  /*.qstjfhFixHead {*/
    /*background-color: #042D4B !important;*/
    /*border: 2px solid #c1c1c1 !important;*/
  /*}*/

  /*.qstjfhFix {*/
    /*!*border: 2px solid #366C90 !important;*!*/
  /*}*/

  thead th {
    text-align: center;
  }

  tbody td {
    text-align: center;
    vertical-align: middle;
  }

  .search_qstjfh_div_style_dw {
    display: flex;
    align-items: center;
    margin-right: 3vh;
    float: right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 7px 0px 1px 0px;
  }

  .search_qstjfx_style_dw {
    width: 100%;
    position: relative;
  }

</style>
<!-- tabs样式 -->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<link href="${ctx}/static/css/bootstrap-table-fixed-columns.css" rel="stylesheet">

<ul id="qstjfh_tab_dw" class="nav tabs"></ul>
<!-- 分项树模块start -->
<div id="qstjfh_div_dw" class="leftarea information_left">
  <div id="qstjfh_con_dw" class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
                                    aria-hidden="true"></i>&nbsp;筛选条件>>>
		</span>
  </div>
  <div id="search_div_dw" class="search_qstjfx_style_dw">

    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">日期范围:</span>
      <div id='qstjfh_group_dw'></div>
    </div>

    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">起始时间:</span>
      <input id="qstjfh_start_time_dw" class="input-datecheck" class="input-datecheck"
             onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"/>
    </div>


    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">终止时间:</span>
      <input id="qstjfh_end_time_dw" class="input-datecheck"
             onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"/>
    </div>
    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">图标类型:</span>
      <div id="qstjfh_table_group_dw"></div>
    </div>
    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">时间颗粒度:</span>
      <div id="qstjfh_time_group_dw"></div>
    </div>
    <div class="search_qstjfh_div_style_dw">
      <span class="zl_sxtj_span">单位换算:</span>
      <div id="qstjfh_dw_group_dw"></div>
    </div>
    <div class="search_qstjfh_div_style_dw"><span class="zl_sxtj_span">请选择分户>>> </span>
      <input type="text" style="visibility: hidden;" class="input-datecheck">
      <label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
        <input id="qstjfhCascade_dw" type="checkbox" checked="checked" value="1">是否级联
      </label>
    </div>
  </div>

  <div id="tree_qstjfh_dw"
       style="overflow-y: auto;overflow-x: auto;width: 100%;border-top:1px solid #007ABA;"></div>
  <div id="qstjfh_bottom_dw"
       style="height:5%!important;position: absolute;width:100%;bottom: 0;">
    <div style="float: right;padding-top: 0.6vh;padding-right:2vh;">
      <button type="button" style="width:5vw" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh_dw.getTableData()">
        <i class="fa fa-spinner"></i>&nbsp;加载数据
      </button>
      <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh_dw.exp()">
        <i class="fa fa-download"></i>&nbsp;报表生成
      </button>
      <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
              onclick="dataannlysis_fh_qstjfh_dw.reset()">
        <i class="fa fa-refresh"></i>&nbsp;重置
      </button>
    </div>
  </div>
</div>
<!-- 分项树模块end -->

<!-- 右侧内容模块start -->
<div class="information_right">
  <div class="information_size" style="height:50%">
    <!-- echarts图表 -->
    <div class="information-model">
			<span class="tree_Subtitle">统计分析>>>
			</span>
    </div>
    <div id="qstjfh_chart_dw" style="width: 95%; height: 90%;"></div>
  </div>

  <div class="information_size" style="height:50%">
    <div class="information-model" style="height:3.3vh">
			<span class="tree_Subtitle">数据展示>>>
			</span>
        <#--打印按钮-->
      <a href="javascript:void(-1);" onclick="dataannlysis_fh_qstjfh_dw.print()" class="btn btn-primary toLeft">
        <i class="fa fa-print" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
      </a>
    </div>

      <#--<div id="qstjfh_contain" class="Information_area table-responsive" >-->
    <table id="qstjfhTable_dw" class="table table-bordered" style="white-space: nowrap;  overflow-x: scroll;">
      <thead>
      <tr id="qstjfhday_dw" class="header_color">
      </tr>
      </thead>
      <tbody id='qstjfh_body_dw'>
      <tr>
      </tr>
      </tbody>
    </table>

  </div>
</div>
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
  ;
  var dataannlysis_fh_qstjfh_dw = (function ($, window, document, undefined) {
    var _fnybh = '';//能源类型
    var _ctx = '${ctx}';
    var qstjfh_time_group = "0";//时间类型
    var qstjfh_dw_group = "0";//单位换算类型
    var tableType = "";//图标类型
    var cascadeType = true;//是否级联
    var isFistSelect = true;
    var checkedGnbh = [];//单选框选中的节点
    var householdConfAndEnergyTypeData = {};

    var checkNodes = [];//选中的节点数组
    var deWhetherToManClick = true;//判断是否手动点击树节点
    $(function () {

      var qstjfh_group_id = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
      var qstjfh_group_val = ['本日', '本周', '本月', '本季', '本年', '上天', '上周', '上月', '上季', '上年', '自定义'];
      qstjfh_group_select('#qstjfh_group_dw', qstjfh_group_id, qstjfh_group_val);

      var qstjfh_dw_group_id = ['0', '1', '2', '3', '4', '5', '6', '7'];
      var qstjfh_dw_group_val = ['总能耗(Kwh)', '二氧环碳(ppm)', '总金额(元)', '耗煤量(吨)', '人均能耗(Kwh)', '人均金额(元)', '单位面积能耗(Kwh)', '单位面积金额(元)'];
      qstjfh_dw_group_select('#qstjfh_dw_group_dw', qstjfh_dw_group_id, qstjfh_dw_group_val);

      var qstjfh_time_group_id = ['0', '1', '2', '3'];
      var qstjfh_time_group_val = ['时', '日', '月', '年'];
      qstjfh_time_group_select('#qstjfh_time_group_dw', qstjfh_time_group_id, qstjfh_time_group_val);

      //图标类型传递数组
      var tb_idArray = ['line', 'bar'];
      var tb_valArray = ['曲线图', '柱状图'];
      tblx_select(tb_idArray, tb_valArray);
      //获取当前时间
      var date = getCurrentDate();
      //起始时间和默认时间
      $('#qstjfh_start_time_dw').val(getFormatDate(date));
      $('#qstjfh_end_time_dw').val(getFormatDate(date));
      $('#qstjfh_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfh_end_time_dw').attr('disabled', 'disabled');

      $('#qstjfh_sametime_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfh_sametime_end_time_dw').attr('disabled', 'disabled');
      getTabs();
      // qstjfh_tree(cascadeType, isFistSelect, checkedGnbh);
      getHeight();

      getHouseholdConfAndEnergyTypeData();
    })

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

    //Echars图表展示 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('qstjfh_chart_dw'));

    $(window).resize(function () {
      myChart.resize();
      setTimeout(function () {
        getHeight()
      }, 1);
// 		getHeight();
    });

    function getHeight() {
      //获取左侧高度
      var allheight = $("#qstjfh_div_dw").height();//总高度
      var tbhbfx_con = $("#qstjfh_con_dw").height();
      var num = $(".search_qstjfh_div_style_dw").length;
      var bottom = $("#qstjfh_bottom_dw").height();
      var tj = $(".search_qstjfh_div_style_dw").outerHeight() - 1.2;
      var s = allheight - (num * tj) - bottom - tbhbfx_con;
      $("#tree_qstjfh_dw").height(s);
    }

    //图标初始化selected
    function tblx_select(tb_idArray, tb_valArray) {
      $("#qstjfh_table_group_dw").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: tb_idArray,//id
        valArray: tb_valArray,//txt
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: tbChange,  //自定义事件
        liveSearch: false
      });
    }

    //图表改变事件
    function tbChange(sp) {
      tableType = sp.id;//每次改变赋值给tableType
    }

    //实例化日期范围selected
    function qstjfh_group_select(id, idArray, valArray) {
      //选择电表下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0px',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: timeChange,
        liveSearch: false
      });
    }

    //是否级联 点击事件
    $("#qstjfhCascade_dw").click(function () {
      if ($('#qstjfhCascade_dw').is(':checked')) {
        cascadeType = true;
        isFistSelect = false;
        deWhetherToManClick = true;
        //处理点击是否级联复选框时，先获取所有被选的复选框
        checkedGnbh = [];
        checkedGnbh = $('#tree_qstjfh_dw').treeview('getChecked');
        // qstjfh_tree(cascadeType, isFistSelect, checkedGnbh);
      } else {
        cascadeType = false;
        isFistSelect = false;
        //处理点击是否级联复选框时，先获取所有被选的复选框
        checkedGnbh = [];
        deWhetherToManClick = false;
        checkedGnbh = $('#tree_qstjfh_dw').treeview('getChecked');
        // qstjfh_tree(cascadeType, isFistSelect, checkedGnbh);
      }
    });


    //实例化单位换算selected
    function qstjfh_dw_group_select(id, idArray, valArray) {
      //选择电表下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0px',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: dwChange,
        liveSearch: false
      });
    }

    //实例化时间颗粒度selected
    function qstjfh_time_group_select(id, idArray, valArray) {
      //选择电表下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0px',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: timeUnitChange,
        liveSearch: false
      });
    }

    //时间单位改变方法
    function timeUnitChange(sp) {
      qstjfh_time_group = sp.id;
    }

    //单位换算改变方法
    function dwChange(sp) {
      qstjfh_dw_group = sp.id;
    }

    //实现日期范围和时间颗粒相关联
    function timeChange(sp) {
      $("#qstjfh_start_time_dw,#qstjfh_end_time_dw").removeAttr("cursor").css("cursor", "not-allowed");
      var range = sp.id;
      $('#qstjfh_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfh_end_time_dw').attr('disabled', 'disabled');
      switch (range) {
        case '0':
          var today = getCurrentDate();
          $('#qstjfh_start_time_dw').val(getFormatDate(today));
          $('#qstjfh_end_time_dw').val(getFormatDate(today));
          break;
        case '1':
          var thisWeek = getCurrentWeek();
          $('#qstjfh_start_time_dw').val(getFormatDate(thisWeek[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(thisWeek[1]));
          break;

        case '2':
          var thismouth = getCurrentMonth();
          $('#qstjfh_start_time_dw').val(getFormatDate(thismouth[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(thismouth[1]));
          break;

        case '3':
          var thisSeason = getCurrentSeason();
          $('#qstjfh_start_time_dw').val(getFormatDate(thisSeason[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(thisSeason[1]));
          break;

        case '4':
          var thisYear = getCurrentYear();
          $('#qstjfh_start_time_dw').val(getFormatDate(thisYear[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(thisYear[1]));
          break;

        case '5':
          var lastDay = getPreviousDate();
          $('#qstjfh_start_time_dw').val(getFormatDate(lastDay));
          $('#qstjfh_end_time_dw').val(getFormatDate(lastDay));
          break;

        case '6':
          var lastWeek = getPreviousWeek();
          $('#qstjfh_start_time_dw').val(getFormatDate(lastWeek[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(lastWeek[1]));
          break;

        case '7':
          var lastMouth = getPreviousMonth();
          $('#qstjfh_start_time_dw').val(getFormatDate(lastMouth[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(lastMouth[1]));
          break;

        case '8':
          var lastSeason = getPreviousSeason();
          $('#qstjfh_start_time_dw').val(getFormatDate(lastSeason[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(lastSeason[1]));
          break;

        case '9':
          var lastYear = getPreviousYear();
          $('#qstjfh_start_time_dw').val(getFormatDate(lastYear[0]));
          $('#qstjfh_end_time_dw').val(getFormatDate(lastYear[1]));
          break;
        case '10':
          $("#qstjfh_start_time_dw,#qstjfh_end_time_dw").css("cursor", "default");
          $('#qstjfh_start_time_dw').attr('disabled', false);
          $('#qstjfh_end_time_dw').attr('disabled', false);
          break;
      }
    }

    //获取tab页
    function getTabs() {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/getenrrgylist",
        dataType: "json",
        async: false,
        data: ({
          f_yqbh: '0000'
        }),
        success: function (result) {
          if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
            if (result.list.length > 0) {//如果包含判断是否长度大于0
              _fnybh = result.list[0].fnybh;//默认能源类型
              for (var i = 0; i < result.list.length; i++) {
                var treeNode = result.list[i];
                if (i == 0) {
                  $("#qstjfh_tab_dw").append("<li class='active nocancel'><a  data-toggle='tab' href='#'  data='" + treeNode.fnybh + "'>" + treeNode.fnymc + "</a></li>");
                } else {
                  $("#qstjfh_tab_dw").append("<li ><a  data-toggle='tab' href='#'   data='" + treeNode.fnybh + "'>" + treeNode.fnymc + "</a></li>");
                }

              }
            }
            qstjfh_tree(true, false, checkedGnbh);
            //点击tab,支路树变化
            $("#qstjfh_tab_dw a").click(function () {
              $(this.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
              _fnybh = $(this).attr("data");
              var dom = document.getElementById("qstjfh_chart_dw");
              var myChart = echarts.init(dom, "light");
              myChart.clear();
              $('#qstjfhday_dw').html("");
              $('#qstjfh_body_dw').html("");
              $('#qstjfhTable_dw').fixedThead('destroy');//销毁

              $('#tree_qstjfh_dw').treeview('uncheckAll', {silent: true});
              checkedGnbh = [];
              checkedGnbh = $('#tree_qstjfh_dw').treeview('getChecked');
              qstjfh_tree(true, false, checkedGnbh);
            });
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }


    document.onreadystatechange = function () {
      if (document.readyState == "complete") {
        document.getElementById('divprogressbar_dw').style.display = 'none';
      }
    }

    function getTableData() {
      $('#qstjfhTable_dw').fixedThead('destroy');//销毁

      $('#qstjfhday_dw').html("");
      $('#qstjfh_body_dw').html("");
      var dom = document.getElementById("qstjfh_chart_dw");
      var myChart = echarts.init(dom, "light");
      myChart.clear();

      var qstjfh_start_time = $('#qstjfh_start_time_dw').val();//开始时间
      var qstjfh_end_time = $('#qstjfh_end_time_dw').val();//结束时间

      //获取选中的树节点
      var checkedGnbh = $('#tree_qstjfh_dw').treeview('getChecked');
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

      //判断左边树的复选框
      var nodetreeIds = [];
      var nodeNames = [];
      for (var i = 0; i < checkedGnbh.length; i++) {
        var nodetreeId = checkedGnbh[i].nodeTreeId;
        nodetreeIds.push(nodetreeId);

      }
      if (nodetreeIds.length == 0) {
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
          "fType": qstjfh_time_group,
          "fCjsj_start": qstjfh_start_time,
          "fCjsj_end": qstjfh_end_time,
          "fFhbhs": nodetreeIds
        },
        beforeSend: function () {
          showLoad();
        },

        success: function (result) {
          if (result.hasOwnProperty("map") &&
              result.map.time.length > 0) {
            $('#qstjfhTable_dw').fixedThead('destroy');//销毁
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
              if (qstjfh_time_group == '0') {
                x_unit = "时";
                qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                    time[i].fCjsj.substring(5, 13) + "</th>";
                chartXdata.push(time[i].fCjsj.substring(5, 13).split(" ")[1]);
              }

              if (qstjfh_time_group == '1') {
                x_unit = "天";
                qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                    time[i].fCjsj.substring(0, 10) + "</th>";
                chartXdata.push(time[i].fCjsj.substring(0, 10).split("-")[2]);
              }

              if (qstjfh_time_group == '2') {
                x_unit = "月";
                qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                    time[i].fCjsj.substring(0, 7) + "</th>";
                chartXdata.push(time[i].fCjsj.substring(0, 7).split("-")[1]);
              }

              if (qstjfh_time_group == '3') {
                x_unit = "年";
                qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;'>" +
                    time[i].fCjsj.substring(0, 4) + "</th>";
                chartXdata.push(time[i].fCjsj.substring(0, 4));
              }

              allTime.push(time[i].fCjsj);
            }
            $('#qstjfhday_dw').html(qstjfhdaydata);

            var tdData = "";
            var seriesData = [];
            for (var i = 0; i < nodetreeIds.length; i++) {
              if (result.map[nodetreeIds[i]] != null && result.map[nodetreeIds[i]].length != 0) {
                tdData += "<tr ><td  class='qstjfhFixHead' style='white-space: nowrap;'>" + result.map[nodetreeIds[i]][0].fFhmc + "</td>"
                var tdList = result.map[nodetreeIds[i]];//分户数据集合
                for (var j = 0; j < allTime.length; j++) {
                  var flag = '0';
                  var data = '';

                  for (var c = 0; c < tdList.length; c++) {
                    seriesData = [];
                    //不同单位获取不同类型数据
                    /*if(tdList[c].fCjsj == allTime[j] )
                    {
                        flag ='1';
                        if(qstjfh_dw_group =='0')
                        {
                            data =tdList[c].fData || '0.0';
                            y_unit="Kwh";
                        }
                        if(qstjfh_dw_group =='1')
                        {
                            data =tdList[c].fCo2 || '0.0';
                            y_unit="ppm";
                        }
                        if(qstjfh_dw_group =='2')
                        {
                            data =tdList[c].fAllMoney || '0.0';
                            y_unit="元";
                        }
                        if(qstjfh_dw_group =='3')
                        {
                            data =tdList[c].fCoalAmount || '0.0';
                            y_unit="吨";
                        }
                        if(qstjfh_dw_group =='4')
                        {
                            data =tdList[c].fPermanData || '0.0';
                            y_unit="Kwh";
                        }
                        if(qstjfh_dw_group =='5')
                        {
                            y_unit="元";
                            data =tdList[c].fPermanMoney || '0.0';
                        }
                        if(qstjfh_dw_group =='6')
                        {
                            y_unit="Kwh";
                            data =tdList[c].fUnitareaData || '0.0';
                        }
                        if(qstjfh_dw_group =='7')
                        {
                            y_unit="元";
                            data =tdList[c].fUnitareaMoney || '0.0';
                        }

                        break;
                    }*/

                    if (tdList[c].fCjsj == allTime[j]) {
                      flag = '1';
                      var fData = tdList[c].fData;
                      if (qstjfh_dw_group == '0') {
                        data = fData;
                        y_unit = "Kwh";
                      }
                      if (qstjfh_dw_group == '1') {
                        var f_co2 = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_CO2;
                        data = fData * f_co2;
                        y_unit = "ppm";
                      }
                      if (qstjfh_dw_group == '2') {
                        var f_price = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PRICE;
                        data = fData * f_price;
                        y_unit = "元";
                      }
                      if (qstjfh_dw_group == '3') {
                        var f_coal_amount = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_COAL_AMOUNT;
                        data = fData * f_coal_amount;
                        y_unit = "吨";
                      }
                      if (qstjfh_dw_group == '4') {
                        var f_person_nums = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PERSON_NUMS;
                        data = fData / f_person_nums;
                        y_unit = "Kwh";
                      }
                      if (qstjfh_dw_group == '5') {
                        var f_price = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PRICE;
                        var f_person_nums = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_PERSON_NUMS;
                        y_unit = "元";
                        data = fData * f_price / f_person_nums;
                      }
                      if (qstjfh_dw_group == '6') {
                        var f_area = householdConfAndEnergyTypeData[tdList[c].fFhbh].F_AREA;
                        data = fData / f_area;
                        y_unit = "Kwh";
                      }
                      if (qstjfh_dw_group == '7') {
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
                  seriesMap['name'] = result.map[nodetreeIds[i]][0].fFhmc;
                  seriesMap['type'] = tableType;
                  seriesMap['smooth'] = 'true';
                  seriesMap['data'] = seriesData;
                  seriesMap['symbol'] = "roundRect";
                  seriesMap['symbolClip'] = true;
                  series.push(seriesMap);

                }

                tdData += "</tr>"
              }

            }
            $('#qstjfh_body_dw').html(tdData);
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
            var mytable = document.getElementById("qstjfhTable_dw");
            for (var i = 1, rows = mytable.rows.length; i < rows; i++) {
              for (var j = 0, cells = mytable.rows[i].cells.length; j < cells; j++) {
                cols.push(mytable.rows[i].cells[1].innerHTML);
                break;
              }
            }

            for (var i = 0; i < cols.length; i++) {
              echartData[i].data[0] = cols[i];
            }
            //echarts图按分户为单位显示，多个数据相加
            var data = [];
            for (var ei = 0; ei < echartData.length; ei++) {
              var sumData = Math.round(sum(echartData[ei].data) * 100) / 100;
              data.push(sumData);
            }
            echartData = [];
            var echartsMap = {};
            echartsMap["name"] = "数据";
            echartsMap["type"] = tableType;
            echartsMap["data"] = data;
            echartsMap["symbol"] = "roundRect";
            echartsMap["symbolClip"] = true;

            var legend = ["数据"];

            echartData.push(echartsMap);
            chartShow(nodeNames, legend, echartData, x_unit, y_unit);
            $('#qstjfhTable_dw').fixedThead({side: '85%', body: '85%', thead: '93%', row: 1, col: 1});//固定表头和第一列

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
    }

    //时间js----判断时间条件
    function timeFormat() {
      var startTime = $('#qstjfh_start_time_dw').val();
      var endTime = $('#qstjfh_end_time_dw').val();
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

      var dom = document.getElementById("qstjfh_chart_dw");
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
            name: "分户",
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

    //生成分户名称树
    function qstjfh_tree(cascadeType, isFistSelect, checkedGnbh) {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
        dataType: "json",
        async: false,
        data: ({
          fNybh: _fnybh,
          fYqbh: ""
        }),
        success: function (result) {

          //初始加载根节点
          if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
            if (result.list.length > 0) {//如果包含判断是否长度大于0
              $('#tree_qstjfh_dw').treeview({
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
              /*//第一次进入页面默认勾选
              if (isFistSelect == true) {
                var firstNode = $("#tree_qstjfh_dw").treeview('findNodes', [result.list[0].id, 'id']);
                var node = $("#tree_qstjfh_dw").treeview('findNodes', [firstNode[0].id, 'id']);
                $('#tree_qstjfh_dw').treeview('checkNode', [node, {silent: false}]);
                for (var i = 2; i < node.length; i++) {
                  var uncheckNode = $("#tree_qstjfh_dw").treeview('findNodes', [node[i].id, 'id']);
                  $('#tree_qstjfh_dw').treeview('uncheckNode', [uncheckNode, {silent: false}]);
                }
              }

              //点击不级联时，父级取消勾选
              if (cascadeType == false) {
                var chechedNodes = [];
                for (var i = 0; i < checkedGnbh.length; i++) {
                  if (checkedGnbh[i].level != 1) {
                    var cNode = $('#tree_qstjfh_dw').treeview('findNodes', [checkedGnbh[i].id, 'id']);
                    var parentNode = $('#tree_qstjfh_dw').treeview('findNodes', [checkedGnbh[i].pid, 'id']);
                    chechedNodes.push(cNode);
                    $("#tree_qstjfh_dw").treeview('uncheckNode', [parentNode, {silent: true}]);
                  }
                }
                for (var i = 0; i < chechedNodes.length; i++) {
                  $("#tree_qstjfh_dw").treeview('checkNode', [chechedNodes[i], {silent: true}]);
                }


              }

              //点击级联时，父级恢复勾选
              if (cascadeType == true && isFistSelect == false) {
                for (var i = 0; i < checkedGnbh.length; i++) {
                  if (checkedGnbh[i].level != 1) {
                    $("#tree_qstjfh_dw").treeview('checkNode', [checkedGnbh[i], {silent: true}]);
                    var parentNode = $('#tree_qstjfh_dw').treeview('findNodes', [checkedGnbh[i].pid, 'id']);
                    $("#tree_qstjfh_dw").treeview('checkNode', [parentNode, {silent: true}]);
                  }
                }
                var nodes = [];
                //去除联动勾选的数据
                $('#tree_qstjfh_dw').treeview('checkAll', {silent: true});
                var cancleChecks = $('#tree_qstjfh_dw').treeview('getChecked');

                for (var i = 0; i < cancleChecks.length; i++) {
                  var obj = cancleChecks[i];
                  var oid = obj.id;
                  var isExist = false;
                  for (var j = 0; j < checkedGnbh.length; j++) {
                    var aj = checkedGnbh[j];
                    var nid = aj.id;
                    if (nid == oid || obj.level == 1) {
                      isExist = true;
                      break;
                    }
                  }
                  if (!isExist) {
                    nodes.push(obj);
                  }
                }

                for (var i = 0; i < nodes.length; i++) {
                  $("#tree_qstjfh_dw").treeview('uncheckNode', [nodes[i], {silent: true}]);
                }

              }*/

            }

          } else {
            swal({
              title : '当前能源下暂无支路配置!',// 展示的标题
              text : "",// 内容
              type : "warning",
              showCloseButton : false, // 展示关闭按钮
              allowOutsideClick : false,
              showConfirmButton : false,
              timer : 1000
            });
            $('#tree_qstjfh_dw').treeview({
              data: "[]",         // 数据源
            });
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
      let allNodes = $("#tree_qstjfh_dw").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_qstjfh_dw").treeview('getChecked');
        //获取父节点
        let fatherNodeId = nodeData.pid;
        allNodes.forEach((node, i) => {
          if (node.nodeTreeId == fatherNodeId) {
            fatherNodeId = node;
            fatherNodes(fatherNodeId, allNodes);
          }
        })

        //获取子节点(如果有的话)
        if (Array.isArray(nodeData.nodes)) {
          childNodes = nodeData.nodes;
          childNodesFunction(childNodes, allNodes);
        }

        checkNodes.forEach((node, i) => {
          $('#tree_qstjfh_dw').treeview('checkNode', [node, {silent: false}]);
        })
      }
      deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
    }

    //获取父节点集合，将父节点集合放到选中的节点集合里面
    function fatherNodes(fatherNodeId, allNodes) {
      checkNodes.push(fatherNodeId);
      let result = [];
      let obj = {};
      for (var i = 0; i < checkNodes.length; i++) {
        if (!obj[checkNodes[i].nodeTreeId]) {
          result.push(checkNodes[i]);
          obj[checkNodes[i].nodeTreeId] = true;
        }
      }
      checkNodes = result;
      //判断父节点是否含有父节点
      if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
        allNodes.forEach((node, i) => {
          if (node.nodeTreeId == fatherNodeId.pid) {
            fatherNodeId = node;
            fatherNodes(fatherNodeId, allNodes);
          }
        })
      }
    }

    //获取子节点集合，将子节点集合放到选中的节点集合里面
    function childNodesFunction(childNodes, allNodes) {
      //遍历子节点集合,判断子节点中是否含有子节点
      childNodes.forEach((childNode, i) => {
        checkNodes.push(childNode);
        if (Array.isArray(childNode.nodes)) {
          childNodesFunction(childNode.nodes, allNodes);
        }
      })
      let result = [];
      let obj = {};
      for (var i = 0; i < checkNodes.length; i++) {
        if (!obj[checkNodes[i].nodeTreeId]) {
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
      let allNodes = $("#tree_qstjfh_dw").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_qstjfh_dw").treeview('getChecked');
        //获取父节点
        let fatherNodeId = nodeData.pid;
        allNodes.forEach((node, i) => {
          if (node.nodeTreeId == fatherNodeId) {
            fatherNodeId = node;
            unfatherNodes(fatherNodeId, allNodes);
          }
        })

        //获取子节点(如果有的话)
        if (Array.isArray(nodeData.nodes)) {
          childNodes = nodeData.nodes;
          unchildNodesFunction(childNodes, allNodes);
        }

        checkNodes.forEach((node, i) => {
          $('#tree_qstjfh_dw').treeview('checkNode', [node, {silent: false}]);
        })
      }
      deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
    }

    //获取父节点集合
    function unfatherNodes(fatherNodeId, allNodes) {
      checkNodes.splice(fatherNodeId);
      $('#tree_qstjfh_dw').treeview('uncheckNode', [fatherNodeId, {silent: true}]);
      //判断父节点是否含有父节点
      if (typeof fatherNodeId.pid != 'undefined') {//有的话,递归
        allNodes.forEach((node, i) => {
          if (node.nodeTreeId == fatherNodeId.pid) {
            fatherNodeId = node;
            unfatherNodes(fatherNodeId, allNodes);
          }
        })
      }
    }

    //获取子节点集合
    function unchildNodesFunction(childNodes, allNodes) {
      //遍历子节点集合,判断子节点中是否含有子节点
      childNodes.forEach((childNode, i) => {
        checkNodes.splice(childNode);
        $('#tree_qstjfh_dw').treeview('uncheckNode', [childNode, {silent: true}]);
        // checkNodes.push(childNode);
        if (Array.isArray(childNode.nodes)) {
          unchildNodesFunction(childNode.nodes, allNodes);
        }
      })
    }


    /**
     * 数组内容相加
     * @param arr
     * @returns {number}
     */
    function sum(arr) {
      var s = 0;
      for (var i = arr.length - 1; i >= 0; i--) {
        s += parseFloat(arr[i]);
      }
      return s;
    }

    return {
      //获取表格数据
      getTableData,
      // 报表生成
      exp: function () {
        // excel的列头
        var alias = new Array();
        // 数据List中的Map的key值.
        var names = new Array();
        // 数据存取list
        var ALLlist = new Array();
        // jquery获取Excel表头数据
        let thead = $("#clone_qstjfhTable_dw_corner>thead>tr").eq(0).find("th");
        $(thead).each(function (i) {
          alias.push($(this).html());
          names.push("a" + i);
        });
        let tbodyTr = $("#clone_qstjfhTable_dw_corner>tbody>tr");
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
        var exportName = "分户用能趋势统计分析(分户)";
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
      //清空以及重置
      reset: function () {
        var qstjfh_group_id = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
        var qstjfh_group_val = ['本天', '本周', '本月', '本季', '本年', '上天', '上周', '上月', '上季', '上年', '自定义'];
        $("#qstjfh_group_dw").ISSPSpinnerBox({
          width: '9vw',//下拉列表宽度
          height: '2.9vh',//下拉列表高度
          margLeft: '0px',//margin-left属性
          isHasData: true,
          selId: '10',
          idArray: qstjfh_group_id,//id
          valArray: qstjfh_group_val,//txt
          isNoSelectedText: false, //是否设置未选中提示文本
          callBack: timeChange,
        });
        $('#qstjfh_start_time_dw').attr('disabled', false);
        $('#qstjfh_end_time_dw').attr('disabled', false);
        $("#qstjfh_start_time_dw,#qstjfh_end_time_dw").css("cursor", "default");
        $('#qstjfh_start_time_dw').val("");
        $('#qstjfh_end_time_dw').val("");
        var dom = document.getElementById("qstjfh_chart_dw");
        var myChart = echarts.init(dom, "light");
        myChart.clear();
        $('#qstjfhTable_dw').fixedThead('destroy');//销毁
        $('#qstjfhday_dw').html("");
        $('#qstjfh_body_dw').html("");
        $('#tree_qstjfh_dw').treeview('uncheckAll', {silent: true});

      },
      print: function () {
        $("#clone_qstjfhTable_dw_corner").printThis({});
      }

    }

  })(jQuery, window, document);

</script>