<#--
描述： 分项用能趋势统计分析
作者： liuzhen
-->
<style>
   /*.qstjfxFixHead{*/
        /*background-color: #042D4B !important;*/
        /*border: 2px solid #366C90 !important;*/
        /*white-space: nowrap;*/
    /*}*/

    /*.qstjfxFix{*/
        /*border: 2px solid #366C90 !important;*/
		/*white-space: nowrap;*/
    /*}*/
  thead th {
    text-align: center;
  }

  tbody td {
    text-align: center;
    vertical-align: middle;
  }

  .search_qstjfx_style_dw {
    width: 100%;
    height: 30%;
    position: relative;
  }

  .search_qstjfx_div_style_dw {
    display: flex;
    align-items: center;
    margin-right: 3vh;
    float: right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 7px 0px 1px 0px;
  }
</style>
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<ul id="qstjfx_Tab_dw" class="nav tabs"></ul>
<!-- 分项树模块start -->
<div id="qstjfx_div_dw" class="leftarea information_left" >
<div id="qstjfx_con_dw" class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
                                    aria-hidden="true"></i>&nbsp;筛选条件>>>
		</span>
  </div>
  <div class="search_qstjfx_style_dw">

    <div class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">日期范围:</span>
      <div id='qstjfx_group_dw' style="display:inline-block"></div>
    </div>

    <div class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">起始时间:</span>
      <input id="qstjfx_start_time_dw" class="input-datecheck"
             onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"/>
    </div>


    <div class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">终止时间:</span>
      <input id="qstjfx_end_time_dw" class="input-datecheck"
             onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})"/>
    </div>
    <div class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">图标类型:</span>
      <div id="qstjfx_table_group_dw"></div>
    </div>
    <div class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">时间颗粒度:</span>
      <div id="qstjfx_time_group_dw" style="display:inline-block"></div>
    </div>
    <div style="display: none" class="search_qstjfx_div_style_dw">
      <span class="zl_sxtj_span">单位换算:</span>
      <div id="qstjfx_dw_group_dw" style="display:inline-block"></div>
    </div>

    <div class="search_qstjfx_div_style_dw"><span class="zl_sxtj_span">请选择分项>>> </span>
      <input type="text" style="visibility: hidden;" class="input-datecheck">
      <label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
        <input id="qstjfxCascade_dw" type="checkbox" checked="checked" value="1">是否级联
      </label>
    </div>
  </div>

	<div id="tree_qstjfx_dw"
         style="overflow-y: auto;overflow-x: auto;width: 100%;border-top:1px solid #007ABA;"></div>
    <div id="qstjfx_bottom_dw" style="height:5%!important;position: absolute;width:100%;bottom: 0;" >
        <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
            <button type="button" style="width:5vw" id="search_button_dw" class="btn btn-sm btn-primary no-margins toLeft" >
                <i class="fa fa-spinner"></i>&nbsp;加载数据
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_fx_qstjfx_dw.reset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </button>

    </div>
  </div>
</div>
<!-- 分项树模块end -->

<!-- 右侧内容模块start -->
<div class="information_right">
  <div  class="information_size" style="height:50%">
	        <!-- echarts图表 -->
	         <div class="information-model">
			<span class="tree_Subtitle">统计分析>>>
			</span>
    </div>
    <div id="qstjfx_chart_dw" style="width: 95%; height: 90%;"></div>
  </div>

  <div class="information_size" style="height:50%">
    <div class="information-model" style="height:3.3vh">
			<span class="tree_Subtitle">数据展示>>>
			</span>
    </div>
    <table id="qstjfxTable_dw" class="table table-bordered" class="table table-bordered"
           style="white-space: nowrap;  overflow-x: scroll;">
      <thead>
      <tr id="qstjfxday_dw" class="header_color">
      </tr>
      <tr id="qstjfxtime_dw" class="header_color">
      </tr>
      </thead>
      <tbody id='qstjfx_body_dw'>
      </tbody>
    </table>
  </div>
</div>
<!-- 右侧内容模块模块end -->

<script src="${ctx}/static/js/time_range.js"></script><!-- 时间范围工具 -->

<script type="text/javascript">
  ;
  var dataannlysis_fx_qstjfx_dw = (function ($, window, document, undefined) {

    var qbbjkTabledata = [];//表格数据
    var _ctx = '${ctx}';
    var _fnybh = '';//能源类型
    var subitemList = [];//分项用能数据
    var qstjfx_time_group = "0";//时间类型
    var qstjfx_dw_group = "";//单位换算类型
    var tableType = "";//图标类型
    var cascadeType = true;//是否级联
    var isFistSelect = true;
    var checkedGnbh = [];//单选框选中的节点

		var checkNodes = [];//选中的节点数组
		var deWhetherToManClick = true;//判断是否手动点击树节点

    $(function () {

      //点击查询
      $("#search_button_dw").click(function () {
        var dom = document.getElementById("qstjfx_chart_dw");
        var myChart = echarts.init(dom, "light");
        myChart.clear();
        $('#qstjfxTable_dw').fixedThead('destroy');//销毁
        //每次清数据重新获取数据
        $('#qstjfxday_dw').html("");
        $('#qstjfxtime_dw').html("");
        $('#qstjfx_body_dw').html("");
        //时
        if (qstjfx_time_group == '0') {
          getTableData();

        }
        //天
        if (qstjfx_time_group == '1') {
          getDayTableData();
        }
        //月
        if (qstjfx_time_group == '2') {
          getMonthTableData();
        }
        //年
        if (qstjfx_time_group == '3') {
          getYearTableData();
        }

      });
      var tb_idArray = ['line', 'bar'];
      var tb_valArray = ['曲线图', '柱状图'];
      tblx_select(tb_idArray, tb_valArray);

      var qstjfx_group_id = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
      var qstjfx_group_val = ['本日', '本周', '本月', '本季', '本年', '上天', '上周', '上月', '上季', '上年', '自定义'];
      qstjfx_group_select('#qstjfx_group_dw', qstjfx_group_id, qstjfx_group_val);

      var qstjfx_dw_group_id = ['0', '1', '2', '3', '4', '5', '6', '7'];
      var qstjfx_dw_group_val = ['总能耗(Kwh)', '二氧环碳(ppm)', '总金额(元)', '耗煤量(吨)', '人均能耗(Kwh)', '人均金额(元)', '单位面积能耗(Kwh)', '单位面积金额(元)'];
      qstjfx_dw_group_select('#qstjfx_dw_group_dw', qstjfx_dw_group_id, qstjfx_dw_group_val);

      var qstjfx_time_group_id = ['0', '1', '2', '3'];
      var qstjfx_time_group_val = ['时', '日', '月', '年'];
      qstjfx_time_group_select('#qstjfx_time_group_dw', qstjfx_time_group_id, qstjfx_time_group_val);


      //获取当前时间
      var date = getCurrentDate();
      //起始时间和默认时间
      $('#qstjfx_start_time_dw').val(getFormatDate(date));
      $('#qstjfx_end_time_dw').val(getFormatDate(date));
      $('#qstjfx_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfx_end_time_dw').attr('disabled', 'disabled');

      $('#qstjfx_sametime_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfx_sametime_end_time_dw').attr('disabled', 'disabled');
      getTabs();
      // qstjfx_tree(cascadeType, isFistSelect, checkedGnbh);
      // if (qstjfx_time_group == '0') {
      //   //getTableData();
      //   setTimeout(getTableData, '100');
      // }
      // //天
      // if (qstjfx_time_group == '1') {
      //   getDayTableData();
      // }
      // //月
      // if (qstjfx_time_group == '2') {
      //   getMonthTableData();
      // }
      // //年
      // if (qstjfx_time_group == '3') {
      //   getYearTableData();
      // }
      getHeight();
    })

    function getHeight() {
      //获取左侧高度
      var allheight = $("#qstjfx_div_dw").height();//总高度
      var tbhbfx_con = $("#qstjfx_con_dw").height();
      var num = $(".search_qstjfx_div_style_dw").length;
      var bottom = $("#qstjfx_bottom_dw").height();
      var tj = $(".search_qstjfx_div_style_dw").outerHeight() - 1.2;
      var s = allheight - (num * tj) - bottom - tbhbfx_con;
      $("#tree_qstjfx_dw").height(s);
    }

    //实例化日期范围selected
    function qstjfx_group_select(id, idArray, valArray) {
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


    //实例化单位换算selected
    function qstjfx_dw_group_select(id, idArray, valArray) {
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
    function qstjfx_time_group_select(id, idArray, valArray) {
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
      qstjfx_time_group = sp.id;
    }

    //单位换算改变方法
    function dwChange(sp) {
      qstjfx_dw_group = sp.id;
    }

    //图标初始化selected
    function tblx_select(tb_idArray, tb_valArray) {
      $("#qstjfx_table_group_dw").ISSPSpinnerBox({
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

    //是否级联 点击事件
    $("#qstjfxCascade_dw").click(function () {
      if ($('#qstjfxCascade_dw').is(':checked')) {
        cascadeType = true;
        isFistSelect = false;
				deWhetherToManClick = true;
        //处理点击是否级联复选框时，先获取所有被选的复选框
        checkedGnbh = [];
        checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');
        // qstjfx_tree(cascadeType, isFistSelect, checkedGnbh);
      } else {
        cascadeType = false;
        isFistSelect = false;
				deWhetherToManClick = false;
				//处理点击是否级联复选框时，先获取所有被选的复选框
        checkedGnbh = [];
        checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');
        // qstjfx_tree(cascadeType, isFistSelect, checkedGnbh);
      }
    });


//图表改变事件
    function tbChange(sp) {
      tableType = sp.id;//每次改变赋值给tableType
    }

    //实现日期范围和时间颗粒相关联
    function timeChange(sp) {
      $("#qstjfx_start_time_dw,#qstjfx_end_time_dw").removeAttr("cursor").css("cursor", "not-allowed");
      var range = sp.id;
      $('#qstjfx_start_time_dw').attr('disabled', 'disabled');
      $('#qstjfx_end_time_dw').attr('disabled', 'disabled');
      switch (range) {
        case '0':
          var today = getCurrentDate();
          $('#qstjfx_start_time_dw').val(getFormatDate(today));
          $('#qstjfx_end_time_dw').val(getFormatDate(today));
          break;
        case '1':
          var thisWeek = getCurrentWeek();
          $('#qstjfx_start_time_dw').val(getFormatDate(thisWeek[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(thisWeek[1]));
          break;

        case '2':
          var thismouth = getCurrentMonth();
          $('#qstjfx_start_time_dw').val(getFormatDate(thismouth[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(thismouth[1]));
          break;

        case '3':
          var thisSeason = getCurrentSeason();
          $('#qstjfx_start_time_dw').val(getFormatDate(thisSeason[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(thisSeason[1]));
          break;

        case '4':
          var thisYear = getCurrentYear();
          $('#qstjfx_start_time_dw').val(getFormatDate(thisYear[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(thisYear[1]));
          break;

        case '5':
          var lastDay = getPreviousDate();
          $('#qstjfx_start_time_dw').val(getFormatDate(lastDay));
          $('#qstjfx_end_time_dw').val(getFormatDate(lastDay));
          break;

        case '6':
          var lastWeek = getPreviousWeek();
          $('#qstjfx_start_time_dw').val(getFormatDate(lastWeek[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(lastWeek[1]));
          break;

        case '7':
          var lastMouth = getPreviousMonth();
          $('#qstjfx_start_time_dw').val(getFormatDate(lastMouth[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(lastMouth[1]));
          break;

        case '8':
          var lastSeason = getPreviousSeason();
          $('#qstjfx_start_time_dw').val(getFormatDate(lastSeason[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(lastSeason[1]));
          break;

        case '9':
          var lastYear = getPreviousYear();
          $('#qstjfx_start_time_dw').val(getFormatDate(lastYear[0]));
          $('#qstjfx_end_time_dw').val(getFormatDate(lastYear[1]));
          break;
        case '10':
          $("#qstjfx_start_time_dw,#qstjfx_end_time_dw").css("cursor", "default");
          $('#qstjfx_start_time_dw').attr('disabled', false);
          $('#qstjfx_end_time_dw').attr('disabled', false);
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
                  $("#qstjfx_Tab_dw").append("<li class='active siblings' ><a  data-toggle='tab' href='#'  data='" + treeNode.fnybh + "'>" + treeNode.fnymc + "</a></li>");
                } else {
                  $("#qstjfx_Tab_dw").append("<li ><a  data-toggle='tab' href='#'   data='" + treeNode.fnybh + "'>" + treeNode.fnymc + "</a></li>");
                }
              }
							qstjfx_tree(cascadeType, false, checkedGnbh);
            }
            //点击tab,支路树变化
            $("#qstjfx_Tab_dw a").click(function () {
              $(this.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
              _fnybh = $(this).attr("data");
              $('#tree_qstjfx_dw').treeview('uncheckAll', {silent: true});
              qstjfx_tree(cascadeType, false, checkedGnbh);
              var dom = document.getElementById("qstjfx_chart_dw");
              var myChart = echarts.init(dom, "light");
              myChart.clear();
              $('#qstjfxTable_dw').fixedThead('destroy');//销毁
              //每次清数据重新获取数据
              $('#qstjfxday_dw').html("");
              $('#qstjfxtime_dw').html("");
              $('#qstjfx_body_dw').html("");
            });
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    //时间为月时拼接表格数据
    function getMonthTableData() {
      var qstjfx_tr = "";//第一列名称内容
      var dayarray = [];//存取获取的天
      var yeararray = [];//存取获取的年
      var monthmap = {};//天和具体时间对应
      var montharray = [];//月份
      var timearry = [];//存取具体时间
      var fCjsjarry = [];//存取获得时间初始数据
      var zlname = [];//支路名称
      var nameMap = {};//名称与数据对应关系
      var dataarray = [];//数据
      var marray = [];//第几月
      var qstjfx_start_time = $('#qstjfx_start_time_dw').val();//开始时间
      var qstjfx_end_time = $('#qstjfx_end_time_dw').val();//结束时间
      var checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');

      var series = []; //echarts列表
      var seriesMap = {};//echarts数据map
      var seriesData = [];//echarts具体每个支路数据
      var chartXdata = [];//charts图表横坐标

      var y_unit = "";//echarts纵坐标单位

      //判断左边树的复选框
      var nodetreeIds = [];
      for (var i = 0; i < checkedGnbh.length; i++) {
        var nodetreeId = checkedGnbh[i].nodeTreeId;
        nodetreeIds.push(nodetreeId);

      }
      var x_unit = "月";//echarts横坐标单位

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

      //时间判断空和合法性处理
      if (!timeFormat()) {
        return;
      }

      $.ajax({
        type: "post",
        url: "${ctx}/view/dataAnalysis/getQstjSubitemData",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "fType": qstjfx_time_group,
          "fCjsj_start": qstjfx_start_time,
          "fCjsj_end": qstjfx_end_time,
          "fFxbhs": nodetreeIds
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            if (result.list.length > 0) {
              $('#qstjfxTable_dw').fixedThead('destroy');//销毁
              //获取的所有数据循环
              for (var i = 0; i < result.list.length; i++) {
                //去除相同天的日期，获取具体天的日期
                if (i == 0 || result.list[i].fCjsj.split(" ")[0] != result.list[i - 1].fCjsj.split(" ")[0]) {
                  dayarray.push(result.list[i].fCjsj.split(" ")[0]);
                }
                fCjsjarry.push(result.list[i].fCjsj);

                //获取所有支路名称
                zlname.push(result.list[i].fFxmc);

              }
              //获取所有的月份
              for (var i = 0; i < fCjsjarry.length; i++) {
                if (i == 0 || fCjsjarry[i].split(" ")[0].split("-")[0] + "-" + fCjsjarry[i].split(" ")[0].split("-")[1] != fCjsjarry[i - 1].split(" ")[0].split("-")[0] + "-" + fCjsjarry[i - 1].split(" ")[0].split("-")[1]) {
                  montharray.push(fCjsjarry[i].split(" ")[0].split("-")[0] + "-" + fCjsjarry[i].split(" ")[0].split("-")[1]);

                }
              }

              //获取所有的年份
              for (var i = 0; i < dayarray.length; i++) {
                if (i == 0 || dayarray[i].split("-")[0] != dayarray[i - 1].split("-")[0]) {
                  yeararray.push(dayarray[i].split("-")[0]);

                }
              }

              fCjsjarry = Array.from(new Set(fCjsjarry));

              var dataMap = {};

              var map = result.map;

              for (let key in map) {
                let dataList = map[key];

                if (!Array.isArray(dataList)) {
                  break;
                }

                dataMap[key] = {};

                dataList.forEach(data => {
                  dataMap[key][data.fCjsj] = data;
                })
              }

              //去重支路名称
              var temp = uniq(zlname);
              var data = new Array();//存取具体数据
              //拼接具体数据
              for (var j = 0, fl = nodetreeIds.length; j < fl; j++) {
                if (result.map[nodetreeIds[j]] != null && result.map[nodetreeIds[j]].length != 0) {
                  var rowData = new Array();
                  data.push(rowData);
                  rowData.push(result.map[nodetreeIds[j]][0].fFxmc);
                  for (var i = 0; i < fCjsjarry.length; i++) {
                    var datum = dataMap[nodetreeIds[j]][fCjsjarry[i]]
                    if (datum) {
                      if (qstjfx_dw_group == 0) {
                        y_unit = "Kwh";
                        rowData.push(datum.fData || 0);
                      }

                      if (qstjfx_dw_group == 1) {
                        y_unit = "ppm";
                        rowData.push(datum.fCo2 || '0.0');
                      }

                      if (qstjfx_dw_group == 2) {
                        y_unit = "元";
                        rowData.push(datum.fAllMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 3) {
                        y_unit = "吨";
                        rowData.push(datum.fCoalAmount || '0.0');
                      }

                      if (qstjfx_dw_group == 4) {
                        y_unit = "Kwh";
                        rowData.push(datum.fPercapitaEnergy || '0.0');
                      }

                      if (qstjfx_dw_group == 5) {
                        y_unit = "元";
                        rowData.push(datum.fPercapitaMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 6) {
                        y_unit = "Kwh";
                        rowData.push(datum.fUnitareaData || '0.0');
                      }
                      if (qstjfx_dw_group == 7) {
                        y_unit = "元";
                        rowData.push(datum.fUnitareaMoney || '0.0');
                      }
                    } else {
                      rowData.push("0.00");
                    }
                  }
                }
              }

              $('#qstjfx_body_dw').html(qstjfx_tr);

              //构造月和年map关系
              for (let i = 0; i < yeararray.length; i++) {
                marray = [];
                for (let j = 0; j < dayarray.length; j++) {
                  if (dayarray[j].split("-")[0] == yeararray[i]) {
                    marray.push(dayarray[j].split("-")[1]);
                  }
                }
                monthmap[yeararray[i]] = marray;

              }
              //拼接表头年
              var qstjfxdaydata = "<th  rowspan='2'  clsss='qstjfxFixHead'  style='width:20%;text-align:center;vertical-align:middle;'>分项用能名称</th>";
              for (var i = 0; i < yeararray.length; i++) {
                qstjfxdaydata += "<th  clsss='qstjfxFixHead' colspan='" + monthmap[yeararray[i]].length + "'>" +
                    yeararray[i] + "</th>";
              }
              $('#qstjfxday_dw').html(qstjfxdaydata);

              //拼接表头具体第几月
              var fCjsjarrydata = "";
              for (var i = 0; i < montharray.length; i++) {

                fCjsjarrydata += "<th  clsss='qstjfxFixHead'>" +
                    montharray[i].split("-")[1].replace(/\b(0+)/gi, "") + "月</th>";
                chartXdata.push(montharray[i].split("-")[1].replace(/\b(0+)/gi, ""));

              }
              $('#qstjfxtime_dw').html(fCjsjarrydata);


              var dataStr = "";
              for (var i = 0; i < data.length; i++) {
                dataStr += "<tr>";
                seriesData = [];
                var seriesMap = {};
                for (var j = 0; j < data[0].length; j++) {
                  dataStr += "<td  clsss='qstjfxFix'>" + data[i][j] + "</td>";
                  seriesMap['name'] = data[i][0];
                  if (j != 0) {

                    seriesData.push(data[i][j]);
                  }

                }
                dataStr += "</tr>";
                seriesMap['type'] = tableType;
                seriesMap['smooth'] = 'true';
                seriesMap['data'] = seriesData;
                seriesMap['symbol'] = "roundRect";
                seriesMap['symbolClip'] = true;
                series.push(seriesMap);
              }
              $("#qstjfx_body_dw").html(dataStr);
              setTimeout(fixRow, "100");
              //echarts图按分户为单位显示，多个数据相加
              var data = [];
              for (var ei = 0; ei < series.length; ei++) {
                var sumData = Math.round(sum(series[ei].data) * 100) / 100;
                data.push(sumData);
              }
              series = [];
              var echartsMap = {};
              echartsMap["name"] = "数据";
              echartsMap["type"] = tableType;
              echartsMap["data"] = data;
              echartsMap["symbol"] = "roundRect";
              echartsMap["symbolClip"] = true;

              var legend = ["数据"];

              series.push(echartsMap);
              chartShow(temp, legend, series, x_unit, y_unit);
              $('#qstjfxday_dw th').addClass('qstjfxFixHead');
              $('#qstjfxtime_dw th').addClass('qstjfxFixHead');
              $('td').addClass('qstjfxFix');
              $('table tr td:first-child').addClass('qstjfxFixHead');

            }
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

    //时间为年时拼接表格数据
    function getYearTableData() {
      var qstjfx_tr = "";//第一列名称内容
      var dayarray = [];//存取获取的天
      var yeararray = [];//存取获取的年
      var monthmap = {};//天和具体时间对应
      var timearry = [];//存取具体时间
      var fCjsjarry = [];//存取获得时间初始数据
      var zlname = [];//支路名称
      var nameMap = {};//名称与数据对应关系
      var dataarray = [];//数据
      var marray = [];//第几月
      var qstjfx_start_time = $('#qstjfx_start_time_dw').val();//开始时间
      var qstjfx_end_time = $('#qstjfx_end_time_dw').val();//结束时间
      var checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');

      var x_unit = "年";//echarts横坐标单位
      var y_unit = "";//echarts纵坐标单位
      var series = []; //echarts列表
      var seriesMap = {};//echarts数据map
      var seriesData = [];//echarts具体每个支路数据
      var chartXdata = [];//charts图表横坐标
      //判断左边树的复选框
      var nodetreeIds = [];
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
      //时间判断空和合法性处理
      if (!timeFormat()) {
        return;
      }

      $.ajax({
        type: "post",
        url: "${ctx}/view/dataAnalysis/getQstjSubitemData",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "fType": qstjfx_time_group,
          "fCjsj_start": qstjfx_start_time,
          "fCjsj_end": qstjfx_end_time,
          "fFxbhs": nodetreeIds
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            if (result.list.length > 0) {
              $('#qstjfxTable_dw').fixedThead('destroy');//销毁
              //获取的所有数据循环
              for (var i = 0; i < result.list.length; i++) {
                //去除相同天的日期，获取具体天的日期
                if (i == 0 || result.list[i].fCjsj.split(" ")[0] != result.list[i - 1].fCjsj.split(" ")[0]) {
                  dayarray.push(result.list[i].fCjsj.split(" ")[0]);
                }
                fCjsjarry.push(result.list[i].fCjsj);

                //获取所有支路名称
                zlname.push(result.list[i].fFxmc);

              }

              //获取所有的年份
              for (var i = 0; i < dayarray.length; i++) {
                if (i == 0 || dayarray[i].split("-")[0] != dayarray[i - 1].split("-")[0]) {
                  yeararray.push(dayarray[i].split("-")[0]);

                }
              }

              fCjsjarry = Array.from(new Set(fCjsjarry));

              var dataMap = {};

              var map = result.map;

              for (let key in map) {
                let dataList = map[key];

                if (!Array.isArray(dataList)) {
                  break;
                }

                dataMap[key] = {};

                dataList.forEach(data => {
                  dataMap[key][data.fCjsj] = data;
                })
              }

              var temp = uniq(zlname);
              var data = new Array();//存取具体数据
              //拼接具体数据
              for (var j = 0, fl = nodetreeIds.length; j < fl; j++) {
                if (result.map[nodetreeIds[j]] != null && result.map[nodetreeIds[j]].length != 0) {
                  var rowData = new Array();
                  data.push(rowData);
                  rowData.push(result.map[nodetreeIds[j]][0].fFxmc);
                  for (var i = 0; i < fCjsjarry.length; i++) {
                    var datum = dataMap[nodetreeIds[j]][fCjsjarry[i]];
                    if (datum) {
                      if (qstjfx_dw_group == 0) {
                        y_unit = "Kwh";
                        rowData.push(datum.fData || '0.0');
                      }

                      if (qstjfx_dw_group == 1) {
                        y_unit = "ppm";
                        rowData.push(datum.fCo2 || '0.0');
                      }

                      if (qstjfx_dw_group == 2) {
                        y_unit = "元";
                        rowData.push(datum.fAllMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 3) {
                        y_unit = "吨";
                        rowData.push(datum.fCoalAmount || '0.0');
                      }

                      if (qstjfx_dw_group == 4) {
                        y_unit = "Kwh";
                        rowData.push(datum.fPercapitaEnergy || '0.0');
                      }

                      if (qstjfx_dw_group == 5) {
                        y_unit = "元";
                        rowData.push(datum.fPercapitaMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 6) {
                        y_unit = "Kwh";
                        rowData.push(datum.fUnitareaData || '0.0');
                      }
                      if (qstjfx_dw_group == 7) {
                        y_unit = "元";
                        rowData.push(datum.fUnitareaMoney || '0.0');
                      }
                    } else {
                      rowData.push("0.00");
                    }
                  }
                }
              }
              $('#qstjfx_body_dw').html(qstjfx_tr);
              //拼接表头年
              var qstjfxdaydata = "<th  clsss='qstjfxFixHead' style='width:20%';'  >分项用能名称</th>";
              for (var i = 0; i < yeararray.length; i++) {
                qstjfxdaydata += "<th  clsss='qstjfxFixHead'>" +
                    yeararray[i] + "</th>";
                chartXdata.push(yeararray[i]);

              }
              $('#qstjfxday_dw').html(qstjfxdaydata);
              $('#qstjfxtime_dw').remove();

              var dataStr = "";
              for (var i = 0; i < data.length; i++) {
                dataStr += "<tr>";
                seriesData = [];
                var seriesMap = {};
                for (var j = 0; j < data[0].length; j++) {
                  dataStr += "<td  clsss='qstjfxFix'>" + data[i][j] + "</td>";
                  seriesMap['name'] = data[i][0];
                  if (j != 0) {

                    seriesData.push(data[i][j]);
                  }

                }
                dataStr += "</tr>";
                seriesMap['type'] = tableType;
                seriesMap['smooth'] = 'true';
                seriesMap['data'] = seriesData;
                seriesMap['symbol'] = "roundRect";
                seriesMap['symbolClip'] = true;
                series.push(seriesMap);

              }
              $("#qstjfx_body_dw").html(dataStr);
              //echarts图按分户为单位显示，多个数据相加
              var data = [];
              for (var ei = 0; ei < series.length; ei++) {
                var sumData = Math.round(sum(series[ei].data) * 100) / 100;
                data.push(sumData);
              }
              series = [];
              var echartsMap = {};
              echartsMap["name"] = "数据";
              echartsMap["type"] = tableType;
              echartsMap["data"] = data;
              echartsMap["symbol"] = "roundRect";
              echartsMap["symbolClip"] = true;

              var legend = ["数据"];

              series.push(echartsMap);
              chartShow(temp, legend, series, x_unit, y_unit);
              $("table thead").append("<tr id='qstjfxtime_dw'></tr>");
              $('#qstjfxTable_dw').fixedThead({side: '85%', body: '85%', thead: '93%', row: 1, col: 1});//固定表头和第一列
              $('#qstjfxday_dw th').addClass('qstjfxFixHead');
              $('td').addClass('qstjfxFix');
              $('table tr td:first-child').addClass('qstjfxFixHead');

            }
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
        },

      });
    }


    //时间为天时拼接表格数据
    function getDayTableData() {
      var qstjfx_tr = "";//第一列名称内容
      var dayarray = [];//存取获取的天
      var montharray = [];//存取获取的天
      var daymap = {};//天和具体时间对应
      var timearry = [];//存取具体时间
      var fCjsjarry = [];//存取获得时间初始数据
      var zlname = [];//支路名称
      var nameMap = {};//名称与数据对应关系
      var dataarray = [];//数据
      var darray = [];//第几天
      var qstjfx_start_time = $('#qstjfx_start_time_dw').val();//开始时间
      var qstjfx_end_time = $('#qstjfx_end_time_dw').val();//结束时间
      var checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');

      var x_unit = "天";//echarts横坐标单位
      var y_unit = "";//echarts纵坐标单位
      var series = []; //echarts列表
      var seriesMap = {};//echarts数据map
      var seriesData = [];//echarts具体每个支路数据
      var chartXdata = [];//charts图表横坐标

      //判断左边树的复选框
      var nodetreeIds = [];
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

      //时间判断空和合法性处理
      if (!timeFormat()) {
        return;
      }
      $.ajax({
        type: "post",
        url: "${ctx}/view/dataAnalysis/getQstjSubitemData",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "fType": qstjfx_time_group,
          "fCjsj_start": qstjfx_start_time,
          "fCjsj_end": qstjfx_end_time,
          "fFxbhs": nodetreeIds
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            if (result.list.length > 0) {
              $('#qstjfxTable_dw').fixedThead('destroy');//销毁
              //获取的所有数据循环
              for (var i = 0; i < result.list.length; i++) {
                //去除相同天的日期，获取具体天的日期
                if (i == 0 || result.list[i].fCjsj.split(" ")[0] != result.list[i - 1].fCjsj.split(" ")[0]) {
                  dayarray.push(result.list[i].fCjsj.split(" ")[0]);
                }
                fCjsjarry.push(result.list[i].fCjsj);

                //获取所有支路名称
                zlname.push(result.list[i].fFxmc);

              }

              //获取所有的月份
              for (var i = 0; i < dayarray.length; i++) {
                if (i == 0 || dayarray[i].split("-")[0] + "-" + dayarray[i].split("-")[1] != dayarray[i - 1].split("-")[0] + "-" + dayarray[i - 1].split("-")[1]) {
                  montharray.push(dayarray[i].split("-")[0] + "-" + dayarray[i].split("-")[1]);

                }
              }

              //去重支路名称
              var temp = uniq(zlname);

              fCjsjarry = Array.from(new Set(fCjsjarry));

              var dataMap = {};

              var map = result.map;

              for (let key in map) {
                let dataList = map[key];

                if (!Array.isArray(dataList)) {
                  break;
                }

                dataMap[key] = {};

                dataList.forEach(data => {
                  dataMap[key][data.fCjsj] = data;
                })
              }

              var data = new Array();//存取具体数据
              //拼接具体数据
              for (var j = 0, fl = nodetreeIds.length; j < fl; j++) {
                if (result.map[nodetreeIds[j]] != null && result.map[nodetreeIds[j]].length != 0) {
                  var rowData = new Array();
                  data.push(rowData);
                  rowData.push(result.map[nodetreeIds[j]][0].fFxmc);
                  for (var i = 0; i < fCjsjarry.length; i++) {
                    var datum = dataMap[nodetreeIds[j]][fCjsjarry[i]];
                    if (datum) {
                      if (qstjfx_dw_group == 0) {
                        y_unit = "Kwh";
                        rowData.push(datum.fData || '0.0');
                      }

                      if (qstjfx_dw_group == 1) {
                        y_unit = "ppm";
                        rowData.push(datum.fCo2 || '0.0');
                      }

                      if (qstjfx_dw_group == 2) {
                        y_unit = "元";
                        rowData.push(datum.fAllMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 3) {
                        y_unit = "吨";
                        rowData.push(datum.fCoalAmount || '0.0');
                      }

                      if (qstjfx_dw_group == 4) {
                        y_unit = "Kwh";
                        qstjfx_tr +=
                            "<td  clsss='qstjfxFix'>" + datum.fPercapitaEnergy || '0.0' + "</td>";
                        rowData.push(datum.fPercapitaEnergy || '0.0');
                      }

                      if (qstjfx_dw_group == 5) {
                        y_unit = "元";
                        rowData.push(datum.fPercapitaMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 6) {
                        y_unit = "Kwh";
                        rowData.push(datum.fUnitareaData || '0.0');
                      }
                      if (qstjfx_dw_group == 7) {
                        y_unit = "元";
                        rowData.push(datum.fUnitareaMoney || '0.0');
                      }
                    } else {
                      rowData.push("0.00");
                    }
                  }
                }
              }

              $('#qstjfx_body_dw').html(qstjfx_tr);

              //构造月和天map关系
              for (let i = 0; i < montharray.length; i++) {
                darray = [];
                for (let j = 0; j < dayarray.length; j++) {
                  if (dayarray[j].split("-")[0] + "-" + dayarray[j].split("-")[1] == montharray[i]) {
                    darray.push(dayarray[j].split("-")[2]);
                  }
                }

                daymap[montharray[i]] = darray;

              }
              //拼接表头月
              var qstjfxdaydata = "<th rowspan='2' style='width:20%;text-align:center;vertical-align:middle;'>分项用能名称</th>";
              for (var i = 0; i < montharray.length; i++) {
                qstjfxdaydata += "<th  clsss='qstjfxFix' colspan='" + daymap[montharray[i]].length + "'>" +
                    montharray[i] + "</th>";
              }
              $('#qstjfxday_dw').html(qstjfxdaydata);

              //拼接表头具体第几天
              var fCjsjarrydata = "";
              for (var i = 0; i < dayarray.length; i++) {

                fCjsjarrydata += "<th  clsss='qstjfxFixHead'>" +
                    dayarray[i].split("-")[2].replace(/\b(0+)/gi, "") + "天</th>";
                chartXdata.push(dayarray[i].split("-")[2].replace(/\b(0+)/gi, ""));
              }
              $('#qstjfxtime_dw').html(fCjsjarrydata);


              var dataStr = "";
              for (var i = 0, fl = data.length; i < fl; i++) {
                dataStr += "<tr>";
                seriesData = [];
                var seriesMap = {};
                for (var j = 0; j < data[0].length; j++) {
                  dataStr += "<td  clsss='qstjfxFix'>" + data[i][j] + "</td>";
                  seriesMap['name'] = data[i][0];
                  if (j != 0) {

                    seriesData.push(data[i][j]);
                  }

                }
                dataStr += "</tr>";
                seriesMap['type'] = tableType;
                seriesMap['smooth'] = 'true';
                seriesMap['data'] = seriesData;
                seriesMap['symbol'] = "roundRect";
                seriesMap['symbolClip'] = true;
                series.push(seriesMap);
              }
              $("#qstjfx_body_dw").html(dataStr);

              //echarts图按分户为单位显示，多个数据相加
              var data = [];
              for (var ei = 0; ei < series.length; ei++) {
                var sumData = Math.round(sum(series[ei].data) * 100) / 100;
                data.push(sumData);
              }
              series = [];
              var echartsMap = {};
              echartsMap["name"] = "数据";
              echartsMap["type"] = tableType;
              echartsMap["data"] = data;
              echartsMap["symbol"] = "roundRect";
              echartsMap["symbolClip"] = true;

              var legend = ["数据"];

              series.push(echartsMap);
              chartShow(temp, legend, series, x_unit, y_unit);
              setTimeout(fixRow, "100");
              $('#qstjfxday_dw th').addClass('qstjfxFixHead');
              $('#qstjfxtime_dw th').addClass('qstjfxFixHead');
              $('td').addClass('qstjfxFix');
              $('table tr td:first-child').addClass('qstjfxFixHead');
            }
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

    //时间为时获取表格数据
    function getTableData() {
      var qstjfx_tr = "";
      var dayarray = [];//存取获取的天
      var daymap = {};//天和具体时间对应
      var timearry = [];//存取具体时间
      var fCjsjarry = [];//存取获得时间初始数据
      var zlname = [];//支路名称
      var nameMap = {};//名称与数据对应关系
      var dataarray = [];//数据
      var qstjfx_start_time = $('#qstjfx_start_time_dw').val();//开始时间
      var qstjfx_end_time = $('#qstjfx_end_time_dw').val();//结束时间
      var checkedGnbh = $('#tree_qstjfx_dw').treeview('getChecked');
      var series = []; //echarts列表
      var seriesMap = {};//echarts数据map
      var seriesData = [];//echarts具体每个支路数据
      var chartXdata = [];//charts图表横坐标
      var y_unit = "";//echarts纵坐标单位

      var x_unit = "时";//echarts横坐标单位

      //判断左边树的复选框
      var nodetreeIds = [];
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
      //时间判断空和合法性处理
      if (!timeFormat()) {
        return;
      }

      $.ajax({
        type: "post",
        url: "${ctx}/view/dataAnalysis/getQstjSubitemData",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "fType": qstjfx_time_group,
          "fCjsj_start": qstjfx_start_time,
          "fCjsj_end": qstjfx_end_time,
          "fFxbhs": nodetreeIds
        }),
        beforeSend: function () {
          showLoad();
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            if (result.list.length > 0) {
              $('#qstjfxTable_dw').fixedThead('destroy');//销毁
              //获取的所有数据循环
              for (var i = 0, len = result.list.length; i < len; i++) {
                //去除相同天的日期，表头用
                if (i == 0 || result.list[i].fCjsj.split(" ")[0].trim() != result.list[i - 1].fCjsj.split(" ")[0].trim()) {
                  dayarray.push(result.list[i].fCjsj.split(" ")[0]);
                }

                if (i == 0 || result.list[i].fCjsj != result.list[i - 1].fCjsj) {
                  fCjsjarry.push(result.list[i].fCjsj);

                }

                //获取所有支路名称
                zlname.push(result.list[i].fFxmc);

              }

              fCjsjarry = Array.from(new Set(fCjsjarry));

              var dataMap = {};

              var map = result.map;

              for (let key in map) {
                let dataList = map[key];

                if (!Array.isArray(dataList)) {
                  break;
                }

                dataMap[key] = {};

                dataList.forEach(data => {
                  dataMap[key][data.fCjsj] = data;
                })
              }

              //去重支路名称
              var temp = uniq(zlname);
              var data = new Array();//存取具体数据
              //拼接具体数据
              for (var j = 0, fl = nodetreeIds.length; j < fl; j++) {
                if (result.map[nodetreeIds[j]] != null && result.map[nodetreeIds[j]].length != 0) {
                  var rowData = new Array();
                  data.push(rowData);
                  rowData.push(result.map[nodetreeIds[j]][0].fFxmc);
                  for (var i = 0; i < fCjsjarry.length; i++) {
                    var datum = dataMap[nodetreeIds[j]][fCjsjarry[i]];
                    if (datum) {
                      if (qstjfx_dw_group == 0) {
                        y_unit = "Kwh";
                        rowData.push(datum.fData || '0.0');
                      }

                      if (qstjfx_dw_group == 1) {
                        y_unit = "ppm";
                        rowData.push(datum.fCo2 || '0.0');
                      }

                      if (qstjfx_dw_group == 2) {
                        y_unit = "元";
                        rowData.push(datum.fAllMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 3) {
                        y_unit = "吨";
                        rowData.push(datum.fCoalAmount || '0.0');
                      }

                      if (qstjfx_dw_group == 4) {
                        y_unit = "Kwh";
                        qstjfx_tr +=
                            "<td  clsss='qstjfxFix'>" + datum.fPercapitaEnergy || '0.0' + "</td>";
                        rowData.push(datum.fPercapitaEnergy || '0.0');
                      }

                      if (qstjfx_dw_group == 5) {
                        y_unit = "元";
                        rowData.push(datum.fPercapitaMoney || '0.0');
                      }

                      if (qstjfx_dw_group == 6) {
                        y_unit = "Kwh";
                        rowData.push(datum.fUnitareaData || '0.0');
                      }
                      if (qstjfx_dw_group == 7) {
                        y_unit = "元";
                        rowData.push(datum.fUnitareaMoney || '0.0');
                      }
                    } else {
                      rowData.push("0.00");
                    }
                  }
                }
              }

              $('#qstjfx_body_dw').html(qstjfx_tr);

              //构造天和时间map关系
              for (var i = 0, len = dayarray.length; i < len; i++) {
                timearry = [];
                for (var j = 0, len1 = fCjsjarry.length; j < len1; j++) {
                  if (fCjsjarry[j].split(" ")[0] == dayarray[i]) {
                    timearry.push(fCjsjarry[j].split(" ")[1]);
                  }
                }
                daymap[dayarray[i]] = timearry;

              }

              //拼接表头天
              var qstjfxdaydata = "<th  clsss='qstjfxFixHead' rowspan='2' style='width:20%;text-align:center;vertical-align:middle;'>分项用能名称</th>";
              for (var i = 0, len = dayarray.length; i < len; i++) {
                qstjfxdaydata += "<th  clsss='qstjfxFixHead' colspan='" + daymap[dayarray[i]].length + "'>" +
                    dayarray[i] + "</th>";
              }
              $('#qstjfxday_dw').html(qstjfxdaydata);

              //拼接表头具体时间
              var fCjsjarrydata = "";
              for (var i = 0, len = fCjsjarry.length; i < len; i++) {
                if (fCjsjarry[i].split(" ")[1].split(":")[0] == '00') {
                  fCjsjarrydata += "<th clsss ='qstjfxFixHead' clsss='qstjfxFix'>0时</th>";
                  chartXdata.push("0");

                } else {
                  fCjsjarrydata += "<th  clsss='qstjfxFixHead'>" +
                      fCjsjarry[i].split(" ")[1].split(":")[0].replace(/\b(0+)/gi, "") + "时</th>";
                  chartXdata.push(fCjsjarry[i].split(" ")[1].split(":")[0].replace(/\b(0+)/gi, ""));
                }
              }
              $('#qstjfxtime_dw').html(fCjsjarrydata);
              //重新去重拼接数据内容
              /*var mytable = document.getElementById("qstjfxTable");
              var data = [];
              var rows = mytable.rows.length;
                for(var i=0; i<rows; i++){
                    var cells=mytable.rows[i].cells.length
                  for(var j=0; j<cells; j++){
                    if(!data[i]){
                      data[i] = new Array();
                    }
                    data[i][j] = mytable.rows[i].cells[j].innerHTML;
                 }
              }*/
              var dataStr = "";
              for (var i = 0, dataLength = data.length; i < dataLength; i++) {
                dataStr += "<tr>";
                seriesData = [];
                var seriesMap = {};
                for (var j = 0, len = data[0].length; j < len; j++) {

                  dataStr += "<td  clsss='qstjfxFix'>" + data[i][j] + "</td>";
                  seriesMap['name'] = data[i][0];
                  if (j != 0) {
                    seriesData.push(data[i][j]);
                  }

                }
                dataStr += "</tr>";
                seriesMap['type'] = tableType;
                seriesMap['smooth'] = 'true';
                seriesMap['data'] = seriesData;
                seriesMap['symbol'] = "roundRect";
                seriesMap['symbolClip'] = true;
                series.push(seriesMap);
              }
              $("#qstjfx_body_dw").html(dataStr);
              //echarts图按分户为单位显示，多个数据相加
              var data = [];
              for (var ei = 0; ei < series.length; ei++) {
                var sumData = Math.round(sum(series[ei].data) * 100) / 100;
                data.push(sumData);
              }
              series = [];
              var echartsMap = {};
              echartsMap["name"] = "数据";
              echartsMap["type"] = tableType;
              echartsMap["data"] = data;
              echartsMap["symbol"] = "roundRect";
              echartsMap["symbolClip"] = true;

              var legend = ["数据"];

              series.push(echartsMap);
              chartShow(temp, legend, series, x_unit, y_unit);
              setTimeout(fixRow, "100");
              $('#qstjfxday_dw th').addClass('qstjfxFixHead');
              $('#qstjfxtime_dw th').addClass('qstjfxFixHead');
              $('td').addClass('qstjfxFix');
              $('table tr td:first-child').addClass('qstjfxFixHead');

            }
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

    function fixRow() {
      $('#qstjfxTable_dw').fixedThead({side: '75%', body: '75%', thead: '93%', row: 2, col: 1});//固定表头和第
    }


    //数组去重方法
    function uniq(array) {
      var temp = []; //一个新的临时数组
      for (var i = 0, len = array.length; i < len; i++) {
        if (temp.indexOf(array[i]) == -1) {
          temp.push(array[i]);
        }
      }
      return temp;
    }


    //生成分项名称树
    function qstjfx_tree(cascadeType, isFistSelect, checkedGnbh) {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/subitem_treegrid",
        dataType: "json",
        async: true,
        data: ({
          fNybh: _fnybh
        }),
        success: function (result) {
          //初始加载根节点
          if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
            if (result.list.length > 0) {//如果包含判断是否长度大于0
              $('#tree_qstjfx_dw').treeview({
								data: result.list,         // 数据源
								highlightSelected: true,    //是否高亮选中
								levels: 2,
								enableLinks: true,//必须在节点属性给出href属性
								wrapNodeText: true,
								color: "#4a4747",
								showCheckbox: true,
								// hierarchicalCheck:cascadeType,//级联勾选
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

            /*  if (isFistSelect == true) {
                var firstNode = $("#tree_qstjfx_dw").treeview('findNodes', [result.list[0].id, 'id']);
                var node = $("#tree_qstjfx_dw").treeview('findNodes', [firstNode[0].id, 'id']);
                $('#tree_qstjfx_dw').treeview('checkNode', [node, {silent: false}]);
                for (var i = 3; i < node.length; i++) {
                  var uncheckNode = $("#tree_qstjfx_dw").treeview('findNodes', [node[i].id, 'id']);
                  $('#tree_qstjfx_dw').treeview('uncheckNode', [uncheckNode, {silent: false}]);
                }
              }

              //点击不级联时，父级取消勾选
              if (cascadeType == false) {
                var chechedNodes = [];
                for (var i = 0; i < checkedGnbh.length; i++) {
                  if (checkedGnbh[i].level != 1 && checkedGnbh[i].level != 2) {
                    var cNode = $('#tree_qstjfx_dw').treeview('findNodes', [checkedGnbh[i].id, 'id']);
                    var parentNode = $('#tree_qstjfx_dw').treeview('findNodes', [checkedGnbh[i].pid, 'id']);
                    chechedNodes.push(cNode);
                    $("#tree_qstjfx_dw").treeview('uncheckNode', [parentNode, {silent: true}]);

                  }
                }
                for (var i = 0; i < chechedNodes.length; i++) {
                  $("#tree_qstjfx_dw").treeview('checkNode', [chechedNodes[i], {silent: true}]);
                }

              }

              var parentNode = [];
              //点击级联时，父级恢复勾选
              if (cascadeType == true && isFistSelect == false) {
                for (var i = 0; i < checkedGnbh.length; i++) {
                  $("#tree_qstjfx_dw").treeview('checkNode', [checkedGnbh[i], {silent: true}]);
                  parentNode = $('#tree_qstjfx_dw').treeview('findNodes', [checkedGnbh[i].pid, 'id']);
                  var ppnode = $('#tree_qstjfx_dw').treeview('findNodes', [parentNode[0].pid, 'id']);
                  $("#tree_qstjfx_dw").treeview('checkNode', [parentNode, {silent: true}]);
                  $("#tree_qstjfx_dw").treeview('checkNode', [ppnode[0], {silent: true}]);
                }


                var nodes = [];
                //去除联动勾选的数据

                var cancleChecks = $('#tree_qstjfx_dw').treeview('getChecked');

                for (var i = 0; i < cancleChecks.length; i++) {
                  var obj = cancleChecks[i];
                  var oid = obj.id;
                  var isExist = false;
                  for (var j = 0; j < checkedGnbh.length; j++) {
                    var aj = checkedGnbh[j];
                    var nid = aj.id;
                    if (nid == oid || obj.level == 1 || obj.level == 2) {
                      isExist = true;
                      break;
                    }
                  }
                  if (!isExist) {
                    nodes.push(obj);
                  }
                }
                for (var i = 0; i < nodes.length; i++) {
                  $("#tree_qstjfx_dw").treeview('uncheckNode', [nodes[i], {silent: true}]);
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
            $('#tree_qstjfx_dw').treeview({
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
			let allNodes = $("#tree_qstjfx_dw").treeview('getNodes');

			if (cascadeType) {//级联
				//获取所有选中的节点
				checkNodes = $("#tree_qstjfx_dw").treeview('getChecked');
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
					$('#tree_qstjfx_dw').treeview('checkNode', [node, {silent: false}]);
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
			let allNodes = $("#tree_qstjfx_dw").treeview('getNodes');

			if (cascadeType) {//级联
				//获取所有选中的节点
				checkNodes = $("#tree_qstjfx_dw").treeview('getChecked');
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
					$('#tree_qstjfx_dw').treeview('checkNode', [node, {silent: false}]);
				})
			}
			deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
		}

		//获取父节点集合
		function unfatherNodes(fatherNodeId, allNodes) {
			checkNodes.splice(fatherNodeId);
			$('#tree_qstjfx_dw').treeview('uncheckNode', [fatherNodeId, {silent: true}]);
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
				$('#tree_qstjfx_dw').treeview('uncheckNode', [childNode, {silent: true}]);
				// checkNodes.push(childNode);
				if (Array.isArray(childNode.nodes)) {
					unchildNodesFunction(childNode.nodes, allNodes);
				}
			})
		}

    return {
      //清空以及重置
      reset: function () {
        var qstjfh_group_id = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10'];
        var qstjfh_group_val = ['本天', '本周', '本月', '本季', '本年', '上天', '上周', '上月', '上季', '上年', '自定义'];
        $("#qstjfx_group_dw").ISSPSpinnerBox({
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
        $('#qstjfx_start_time_dw').attr('disabled', false);
        $('#qstjfx_end_time_dw').attr('disabled', false);
        $("#qstjfx_start_time,#qstjfh_end_time_dw").css("cursor", "default");
        $('#qstjfx_start_time_dw').val("");
        $('#qstjfx_end_time_dw').val("");
        var dom = document.getElementById("qstjfx_chart_dw");
        var myChart = echarts.init(dom, "light");
        myChart.clear();
        $('#qstjfxTable_dw').fixedThead('destroy');//销毁
        $('#qstjfxday_dw').html("");
        $('#qstjfxtime_dw').html("");
        $('#qstjfx_body_dw').html("");
        $('#tree_qstjfx_dw').treeview('uncheckAll', {silent: true});

      }
    }


    //时间js----判断时间条件
    function timeFormat() {
      var startTime = $('#qstjfx_start_time_dw').val();
      var endTime = $('#qstjfx_end_time_dw').val();
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
      var dom = document.getElementById("qstjfx_chart_dw");
      var myChart11 = echarts.init(dom, "light");
      var option = {
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
        dataZoom: [{
          type: 'inside',
          disabled: false
        }],

        xAxis: [
          {
            name: x_unit,
            nameTextStyle: {
              color: '#8fe3f7'
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

        yAxis: [
          {
            name: '单位：' + y_unit,
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

  })(jQuery, window, document);


</script>