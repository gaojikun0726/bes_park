<#--
数据分析-同比环比分析
-->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">
  /*.qstjfhFixHead {
    background-color: #042D4B !important;
    border: 2px solid #366C90 !important;
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
  .zl_tbhbfx {
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

  /* tab */
  .form-control {
    padding: 0px !important;
    background-color: rgb(216, 239, 255);
  }

  .zl_tbhbfx input {
    border-radius: 4px;
  }

  .czright {
    margin-right: 0px !important;
  }

  .jzxs {
    text-align: center;
  }

  .czjz {
    vertical-align: middle !important;
    text-align: center;
    /*background-color: #0f627b42 !important;*/
  }

  .zl_sxtjdw {
    width: 100%;
    height: 96%;
    position: relative;
  }
</style>
<ul id="tbhbdw_tab" class="nav tabs tsys">
</ul>
<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;加载数据条件>>>
		</span>
  </div>
  <div class="zl_sxtjdw">
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">对比形式：</span>
      <div id="zl_tbhbfxdw_dbxs"></div>
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">日期范围：</span>
      <div id="zl_tbhbfxdw_rqfw"></div>
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">本期起始： </span>
      <input id="zl_tbhbfxdw_bq_start_time" disabled="disabled" type="text" name="bq_start"
             onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true,onpicked:view_dataAnalysis_zlyn_zl_tbhbfxdw.bqtime_change})"
             class="input-datecheck">
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">本期终止： </span>
      <input id="zl_tbhbfxdw_bq_end_time" disabled="disabled" type="text" name="bq_end"
             onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true,onpicked:view_dataAnalysis_zlyn_zl_tbhbfxdw.bqtime_change})"
             class="input-datecheck">
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">同期起始： </span>
      <input id="zl_tbhbfxdw_tq_start_time" disabled="disabled" type="text" name="tq_start"
             onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" class="input-datecheck">
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">同期终止： </span>
      <input id="zl_tbhbfxdw_tq_end_time" disabled="disabled" type="text" name="tq_end"
             onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" class="input-datecheck">
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">图表类型： </span>
      <div id="zl_tbhbfxdw_tblx"></div>
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">时间颗粒度：</span>
      <div id="zl_tbhbfxdw_sjkld"></div>
    </div>
    <div style="display: none" class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">单位换算：</span>
      <div id="zl_tbhbfxdw_dwhs"></div>
    </div>
    <div class="zl_tbhbfx zl_tbhbfx_jsgddw"><span class="zl_sxtjdw_span">请选择支路>>> </span>
      <input type="text" style="visibility: hidden;" class="input-datecheck">
      <label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
        <input id="zl_thbfxCascadedw" type="checkbox" checked value="1">是否级联
      </label>
    </div>

    <div id="tree_zl_tbhbfxdw"
         style="overflow-y: auto;overflow-x: auto;width: 100%;border-top: 1px solid #00adffa6;"></div>
    <div class="thb_bottontjdw"
         style="height:5%;position: absolute;width:100%;bottom: 0;">
      <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_zlyn_zl_tbhbfxdw.sub()">
          <i class="fa fa-spinner"></i>&nbsp;加载数据
        </button>
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_zlyn_zl_tbhbfxdw.exp()">
          <i class="fa fa-download"></i>&nbsp;报表生成
        </button>
        <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_zlyn_zl_tbhbfxdw.reset()">
          <i class="fa fa-refresh"></i>&nbsp;重置
        </button>
      </div>
    </div>
  </div>
</div>
 <!---右侧收费站区域---->
<div class="information_right"><!--Echars -->
	<div class="information_size"  style="height: 50%;border-bottom: solid 2px #366c90;">
	   <div class="information-model" style="border-bottom:0;">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
			</span>
  </div>
  <div id="zl_tbhbfxdw_echarsdw" class="Information_areaq"></div>
</div>
<!---table---->
<div class="information_size" style="height: 50%"><!-- 列表展示 -->
  <div class="information-model" style="height:3.3vh">
     		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据展示>>>
			</span>
      <#--打印按钮-->
    <a href="javascript:void(-1);" onclick="view_dataAnalysis_zlyn_zl_tbhbfxdw.print()" class="btn btn-primary toLeft">
      <i class="fa fa-print" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
    </a>
  </div>
  <#--<table class="table table-bordered tablestyle" id="zl_tbhbfxdw_table_fix"
         style="white-space: nowrap;  overflow-x: scroll;height:100%;">
  </table>-->
  <table id="zl_qstjfxTable_tolldw" class="table table-bordered" style="white-space: nowrap;  overflow-x: scroll;">
    <thead>
    <tr id="zl_qstjfxDay_tolldw" class="header_color">
    </tr>
    </thead>
    <tbody id='zl_qstjfx_body_tolldw'>
    <tr>
    </tr>
    </tbody>
  </table>
</div>
</div>
<script src="${ctx}/static/js/time_range.js"></script><!-- 时间范围工具 -->
<script type="text/javascript">
  ;
  var view_dataAnalysis_zlyn_zl_tbhbfxdw = (function ($, window, document, undefined) {
    'use strict'
    var _ctx = '${ctx}';
    var _curRow = null;
    var cascadeType = false;//是否级联
    var fnybh = "";//fnybh 能耗类型
    var dbxs = "";//对比形式
    var tblx = "";//图标类型
    var sjkld = "";//时间颗粒度
    var dwhs = "";//单位换算
    var dwhsmc = "";//单位换算名称
    var checkid = "";//默认获取第三级第一个id
    //对比形式数组
    var idArray = ['0', '1'];
    var valArray = ['同比', '环比'];
    //日期范围
    var rq_idArray = ['bt', 'bz', 'by', 'bj', 'bn', 'sr', 'sz', 'sy', 'sj', 'sn', 'zdy'];
    var rq_valArray = ['本天', '本周', '本月', '本季', '本年', '上日', '上周', '上月', '上季', '上年', '自定义'];
    //图标类型
    var tb_idArray = ['bar', 'line'];
    var tb_valArray = ['柱状图', '曲线图'];
    //时间颗粒度
    var sjkld_idArray = ['0', '1', '2', '3'];
    var sjkld_valArray = ['时', '日', '月', '年'];
    //单位换算
    var dwhs_idArray = ['f_data', 'f_all_money', 'f_coal_amount', 'f_co2', 'f_perman_data', 'f_perman_money', 'f_unitarea_data', 'f_unitarea_money'];
    var dwhs_valArray = ['总能耗(Kwh)', '总金额(元)', '耗能量(吨)', '二氧化碳(ppm)', '人均能耗(Kwh)', '人均金额(元)', '单位面积耗能(Kwh)', '单位面积金额(元)'];

    var checkNodes = [];//选中的节点数组
    var deWhetherToManClick = true;//判断是否手动点击树节点
    var startDateBetween = [];
    var endDateBetween = [];
    var rqfw = "";//日期范围


    //对比形式 下拉框
    function dbxs_select(idArray, valArray) {
      $("#zl_tbhbfxdw_dbxs").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: dbxsChange,  //自定义事件
      });
    }

    //对比形式change事件
    function dbxsChange(sp) {

      dbxs = sp.id;
      if (rqfw === "by") {//本月
        let time = getCurrentMonth();
        change_tqtime(time[0],time[1]);
      } else {
        let bqstart = $("#zl_tbhbfxdw_bq_start_time").val();
        let bqend = $("#zl_tbhbfxdw_bq_end_time").val();
        bqstart = parserDate(bqstart);
        bqend = parserDate(bqend);
        bqend = new Date(bqend.getTime()  + 12*60*59*1000);
        change_tqtime(bqstart, bqend);
      }


    }

    //日期范围选择事件
    function rqfw_select(rq_idArray, rq_valArray) {
      $("#zl_tbhbfxdw_rqfw").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: rq_idArray,//id
        valArray: rq_valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        selId: 'bt',//默认选中id
        callBack: rqChange,  //自定义事件
      });
    }


    function getDateBetween(start, end) {

      var result = [];
      //使用传入参数的时间
      var startTime = new Date(start);
      var endTime = new Date(end);
      while (endTime - startTime >= 0) {
        let year = startTime.getFullYear();
        let month = startTime.getMonth();
        month = month<9?'0'+(month+1):month+1;

        let day = startTime.getDate().toString().length == 1 ? "0" + startTime.getDate() : startTime.getDate();

        if (sjkld === '0') {//时

          let hour =startTime.getHours().toString().length == 1 ? "0" + startTime.getHours() : startTime.getHours();
          //加入数组
          result.push(year + "-" + month + "-" + day + " " + hour + ":00:00");

          //更新日期
          startTime.setHours(startTime.getHours() + 1);
        }
        if (sjkld === '1') {//天
          //加入数组
          result.push(year + "-" + month + "-" + day + " " + "00:00:00");
          //更新日期
          startTime.setDate(startTime.getDate() + 1);
        }
        if (sjkld === '2') {//月
                            //加入数组
          result.push(year + "-" + month + "-" + "01" + " " + "00:00:00");
          //更新日期
          startTime.setMonth(startTime.getMonth() + 1);
        }
        if (sjkld === '3') {//年
          result.push(year + "-" + "01" + "-" + "01" + " " + "00:00:00");
          //更新日期
          startTime.setFullYear(startTime.getFullYear() + 1);
          startTime.getMinutes()
        }

      }
      return result;
    }

    //图标类型selected
    function tblx_select(tb_idArray, tb_valArray) {
      $("#zl_tbhbfxdw_tblx").ISSPSpinnerBox({
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

    //日期范围change事件
    function rqChange(sp) {

      let oneday = 1000 * 60 * 60 * 24;

      let id = null;
      if (sp != null) {
        id = sp.id;
        rqfw = id;
      } else {
        id = rqfw;
      }


      $("#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time,#zl_tbhbfxdw_tq_start_time,#zl_tbhbfxdw_tq_end_time").removeAttr("disabled").attr("disabled", true);
      $("#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time,#zl_tbhbfxdw_tq_start_time,#zl_tbhbfxdw_tq_end_time").removeAttr("cursor").css("cursor", "not-allowed");
      //联动规则
      switch (id) {
          //0表示今天
        case "bt":

          //获取当天0点的时间戳
          // var start = new Date(new Date(new Date().toLocaleDateString()).getTime());
          //获取现在的时间
          let starttime = getCurrentDate();//当前日期
          let endtime = getCurrentDate();//当前日期

          $('#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time').val(getFormatDate(starttime));

          //加载同期的起始时间和终止时间
          // startDateBetween = getDateBetween(start,time);
          // startDateBetween = getDateBetween(starttime,endtime);
          change_tqtime(starttime, endtime);
          break;
          //1本周
        case "bz":


          var time = getCurrentWeek();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          let weakstart = time[0] - (time[0].getHours() * 60 * 60 * 1000 + time[0].getMinutes() * 60 * 1000 + time[0].getSeconds() * 1000);
          weakstart = new Date(weakstart);

          //加载同期的起始时间和终止时间
          // startDateBetween = getDateBetween(weakstart,time[1]);
          change_tqtime(weakstart, time[1]);
          break;

          //2本月
        case "by":
          var time = getCurrentMonth();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //3本季
        case "bj":
          var time = getCurrentSeason();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //4本年
        case "bn":
          var time = getCurrentYear();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //5表示昨天
        case "sr":


          // let todaystart = new Date(new Date(new Date().toLocaleDateString()).getTime());
          // let lastdaystart = new Date(todaystart - oneday);

          let todaystart =  getPreviousDate();
          let lastdaystart = getPreviousDate();
          var time = getPreviousDate();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(todaystart));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(lastdaystart));
          //加载同期的起始时间和终止时间
          // startDateBetween = getDateBetween(todaystart,lastdaystart);
          change_tqtime(todaystart, lastdaystart);
          break;
          //6上周
        case "sz":
          var time = getPreviousWeek();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));

          let lastweakstart = time[0] - (time[0].getHours() * 60 * 60 * 1000 + time[0].getMinutes() * 60 * 1000 + time[0].getSeconds() * 1000);
          lastweakstart = new Date(lastweakstart);
          let lastweakend = time[1] - (time[1].getHours() * 60 * 60 * 1000 + time[1].getMinutes() * 60 * 1000 + time[1].getSeconds() * 1000);
          lastweakend = new Date(lastweakend + 23 * 59 * 59 * 1000);
          //加载同期的起始时间和终止时间
          // startDateBetween = getDateBetween(lastweakstart,lastweakend);

          change_tqtime(lastweakstart, lastweakend);
          break;
          //7上月
        case "sy":
          var time = getPreviousMonth();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //8上季
        case "sj":
          var time = getPreviousSeason();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //9上年
        case "sn":
          var time = getPreviousYear();
          $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(time[0]));
          $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time[1]));
          change_tqtime(time[0], time[1]);
          break;
          //自定义
        case "zdy" :
          $("#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time").removeAttr("disabled");
          $("#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time").css("cursor", "default");
          break;
        default:
          break;
      }
    }

    //图表改变事件
    function tbChange(sp) {
      tblx = sp.id;//每次改变赋值给tblx
    }

    //时间颗粒度 selected
    function skkld_select(sjkld_idArray, sjkld_valArray) {
      $("#zl_tbhbfxdw_sjkld").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: sjkld_idArray,//id
        valArray: sjkld_valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        selId: '1',
        callBack: sjkldChange,  //自定义事件
      });
    }

    //时间颗粒度 change
    function sjkldChange(sp) {
      sjkld = sp.id;
    }

    //单位换算 selected
    function dwhs_select(dwhs_idArray, dwhs_valArray) {
      $("#zl_tbhbfxdw_dwhs").ISSPSpinnerBox({
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
    var myChart = echarts.init(document.getElementById('zl_tbhbfxdw_echarsdw'));

    $(window).resize(function () {
      myChart.resize();
      setTimeout(function () {
        getHeight()
      }, 1);
// 		getHeight();
    });

    function getHeight() {
      //获取左侧高度
      var allheight = $(".zl_sxtjdw").height();//总高度
      var botton = $(".thb_bottontjdw").height();//底部
      var num = $(".zl_tbhbfx_jsgddw").length;
      var tj = $(".zl_tbhbfx_jsgddw").outerHeight() - 1.3;
      var s = allheight - (num * tj) - botton;
      $("#tree_zl_tbhbfxdw").height(s);
    }

    //加载tab-list
    $(function () {
      tab_load();
    });

    //初始化时间
    function time_csh() {

      //填加默认时间  先判断对比形式
      // var time = getCurrentDate();//获取当前时间
      // var qntime = getPreviouDate();//去年当前时间
      if (dbxs == '0') {//同比

        //获取当天0点的时间戳
        let start = new Date(new Date(new Date().toLocaleDateString()).getTime());
        //获取现在的时间
        let time = getCurrentDate();//当前日期
        $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(start));
        $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(time));


        // var week = getCurrentWeek();
        // $('#zl_tbhbfxdw_bq_start_time').val(getFormatDate(week[0]));
        // $('#zl_tbhbfxdw_bq_end_time').val(getFormatDate(week[1]));
        change_tqtime(start, time);
        $("#zl_tbhbfxdw_bq_start_time,#zl_tbhbfxdw_bq_end_time").removeAttr("cursor").css("cursor", "not-allowed");
      }
    }

    //获取级联方法
    function Cascade() {
      var cascade = "";//定义级联数组
      $("#zl_thbfxCascadedw[type=checkbox]:checked").each(function (i) {//each循环所有:checkbox
        cascade = $(this).val();
      });
      if (cascade == '' || cascade == 'undefined' || cascade == null) {//没获取到 则为0  不级联
        cascadeType = false;
      } else {
        cascadeType = true;
      }
    }

    //动态拼装tab
    function tab_load() {
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
              opt += "<li><a href='#home' value='" + obj.ID + "' data-toggle='tab' onclick='view_dataAnalysis_zlyn_zl_tbhbfxdw.tabclick(this)'>" + obj.NAME + "</a></li>";
            }
            $("#tbhbdw_tab").append(opt);
            $("#tbhbdw_tab").find("li").eq(0).addClass("active nocancel");
            fnybh = $("#tbhbdw_tab").find("li>a").eq(0).attr("value");
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
        url: _ctx + "/view/dataAnalysis/branch_tree",
        data: {"fnybh": fnybh},
        dataType: "json",
        success: function (result) {
          if (result.hasOwnProperty("list")) {//返回tree成功
            $('#tree_zl_tbhbfxdw').treeview({
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
              }
            });
            // var firstNode = $("#tree_zl_tbhbfxdw").treeview('findNodes', [result.list[0].id, 'id']);//一级
            // var node = $("#tree_zl_tbhbfxdw").treeview('findNodes', [firstNode[0].id, 'id']);//二级
            // var three_node = $("#tree_zl_tbhbfxdw").treeview('findNodes', [node[1].id, 'id']);//三级
            // checkid = node[1].id;
            // first_check(checkid, refreshType, AllChecked);//默认勾选
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
            $('#tree_zl_tbhbfxdw').treeview('remove');//移除列表树容器。
            $('#tree_zl_tbhbfxdw').treeview('uncheckAll', {silent: true});//清空所有check
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
      let allNodes = $("#tree_zl_tbhbfxdw").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_zl_tbhbfxdw").treeview('getChecked');
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
          $('#tree_zl_tbhbfxdw').treeview('checkNode', [node, {silent: false}]);
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
      let allNodes = $("#tree_zl_tbhbfxdw").treeview('getNodes');

      if (cascadeType) {//级联
        //获取所有选中的节点
        checkNodes = $("#tree_zl_tbhbfxdw").treeview('getChecked');
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
          $('#tree_zl_tbhbfxdw').treeview('checkNode', [node, {silent: false}]);
        })
      }
      deWhetherToManClick = true;//判断是否手动点击树节点(点击的逻辑走完后,设置成true)
    }

    //获取父节点集合
    function unfatherNodes(fatherNodeId,allNodes) {
      checkNodes.splice(fatherNodeId);
      $('#tree_zl_tbhbfxdw').treeview('uncheckNode', [fatherNodeId, {silent: true}]);
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
        $('#tree_zl_tbhbfxdw').treeview('uncheckNode', [childNode, {silent: true}]);
        // checkNodes.push(childNode);
        if (Array.isArray(childNode.nodes)) {
          unchildNodesFunction(childNode.nodes,allNodes);
        }
      })
    }

    //是否级联 点击事件
    $("#zl_thbfxCascadedw").click(function () {
      //只有当点击是否级联的时候不刷新数据
      var refreshType = false;
      var AllChecked = $("#tree_zl_tbhbfxdw").treeview('getChecked');
      if ($('#zl_thbfxCascadedw').is(':checked')) {
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
      var startTime = $('#zl_tbhbfxdw_bq_start_time').val();
      var endTime = $('#zl_tbhbfxdw_bq_end_time').val();
      var tqstartTime = $('#zl_tbhbfxdw_tq_start_time').val();
      var tqendTime = $('#zl_tbhbfxdw_tq_end_time').val();
      if (startTime == '' || endTime == '' || tqstartTime == '' || tqendTime == '') {
        swal("请输入查询时间段", "", "warning");
        return false;
      }
      var date1 = new Date(startTime.replace(/-/g, "/"));
      var date2 = new Date(endTime.replace(/-/g, "/"));
      var date3 = new Date(tqstartTime.replace(/-/g, "/"));
      var date4 = new Date(tqendTime.replace(/-/g, "/"));
      if (date2.getTime() < date1.getTime()) {
        swal("本期开始时间不能大于结束时间！", "", "warning");
        return false;
      }
      if (date4.getTime() < date3.getTime()) {
        swal("同期开始时间不能大于结束时间！", "", "warning");
        return false;
      }
      var day = parseInt(((date2.getTime() - date1.getTime()) / 1000) / (24 * 60 * 60));
      return true;
    }

    //改变同期时间
    function change_tqtime(start, end) {

      //1. 同比状态
      if (dbxs == '0') {//同比
        if (start != null) {
          start.setFullYear(start.getFullYear() - 1);
        }
        if (end != null) {
          end.setFullYear(end.getFullYear() - 1);
        }
        // endDateBetween = getDateBetween(start,end);
        $("#zl_tbhbfxdw_tq_start_time").val(getFormatDate(start));
        $("#zl_tbhbfxdw_tq_end_time").val(getFormatDate(end));

        // console.log(endDateBetween);
        // console.log(startDateBetween);
      } else if (dbxs == '1') {//环比
        if (start != null && end != null) {

          if (start.getTime() == end.getTime()) {
            start.setTime(start.getTime() - 24 * 60 * 60 * 1000);
            end.setTime(start.getTime());
          } else {

            if (rqfw == "bj") {
              let lastSeason = getPreviousSeason();
              start = lastSeason[0];
              end = lastSeason[1];
            } else if (rqfw == "bn") {
              start.setFullYear(start.getFullYear() - 1);
              end.setFullYear(end.getFullYear() - 1);
            } else if (rqfw == "sy") {
              let lastTwoMonth = getPreviousTwoMonth();
              start = lastTwoMonth[0];
              end = lastTwoMonth[1];
            } else if (rqfw == "sj") {
              let lastTwoSeason = getPreviousTwoSeason();
              start = lastTwoSeason[0];
              end = lastTwoSeason[1];
            } else if (rqfw == "sn") {
              let lastTwoSeason = getTwoPreviousYear();
              start = lastTwoSeason[0];
              end = lastTwoSeason[1];
            }else if(rqfw === "bz" || rqfw === "sz"){
                //本周、上周
                end.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                start.setTime(start.getTime() - 7 * 24 * 60 * 60 * 1000);
            } else if(rqfw === "by"){
                //本月
              end.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                start.setMonth(start.getMonth() - 1);
                // var t = 2 * start.getTime() - end.getTime();
              // start.setTime(t);
            }else if(rqfw === "bt" || rqfw === "sr"){
                //本天、上日
                start.setDate(start.getDate() - 1);
                end.setDate(end.getDate() - 1);
            }

          }
          // endDateBetween = getDateBetween(start,end);
          $("#zl_tbhbfxdw_tq_start_time").val(getFormatDate(start));
          $("#zl_tbhbfxdw_tq_end_time").val(getFormatDate(end));
          // console.log(endDateBetween);
          // console.log(startDateBetween);
        }
      } else {//自定义
        var startCustom = $("#zl_tbhbfxdw_tq_start_time").val();
        if (start != null && end != null && startCustom != null) {
          var t = startCustom.getTime() + end.getTime() - start.getTime();
          $("#zl_tbhbfxdw_tq_end_time").val(t);
          $("#zl_tbhbfxdw_tq_end_time").val(getFormatDate(end));
        }
      }
    }

    //将字符串格式时间转换成date类型
    function parserDate(date) {
      var t = Date.parse(date);
      if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
      } else {
        return new Date();
      }
    }

    //加载数据之前,先处理数据
    function dataProcessing() {
      let bqStartTime = $("#zl_tbhbfxdw_bq_start_time").val();//本期起始时间
      var bqEndTime = $("#zl_tbhbfxdw_bq_end_time").val();//本期结束时间
      var tqStartTime = $("#zl_tbhbfxdw_tq_start_time").val();//同期起始时间
      var tqEndTime = $("#zl_tbhbfxdw_tq_end_time").val();//同期结束时间

      startDateBetween = getDateBetween(bqStartTime + " 00:00:00",bqEndTime + " 23:59:59");
      endDateBetween = getDateBetween(tqStartTime + " 00:00:00",tqEndTime + " 23:59:59");
      console.log(endDateBetween);
      console.log(startDateBetween);
    }

    //echarts数据
    function chartShow(xdata, legend, series, x_unit, y_unit) {
      var dom = document.getElementById("zl_tbhbfxdw_echarsdw");
      var myChart11 = echarts.init(dom, "light");
      var option = {
        color: ['#EE9201', '#29AAE3', '#B74AE5', '#0AAF9F', '#E89589', '#16A085', '#4A235A', '#C39BD3'],
        title: {
          text: '支路统计分析',
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
            name: "单位:" +  x_unit,
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

    return {
      //清空条件
      reset: function () {
        //清空table
        $("#zl_tbhbfxdw_table_fix").empty();
        $('#zl_tbhbfxdw_table_fix').fixedThead('destroy');//销毁
        myChart.clear();//清空echars
        $('#tree_zl_tbhbfxdw').treeview('uncheckAll', {silent: true});//清空所有check
        view_dataAnalysis_zlyn_zl_tbhbfxdw.pageInit();
      },

      //加载数据
      sub: function () {

        //加载数据之前,判断时间颗粒度和日期范围是否可以关联
        if (sjkld == "2") {//月
          if (rqfw == "bt" || rqfw == "bz") {
            swal({
              title : '时间颗粒度和日期范围请合理关联!',// 展示的标题
              text : "",// 内容
              type : "warning",
              showCloseButton : false, // 展示关闭按钮
              allowOutsideClick : false,
              showConfirmButton : false,
              timer : 1000
            });
            return;
          }
        } else if (sjkld == "3") {//年
          if (rqfw == "bt" || rqfw == "bz" || rqfw == "by" || rqfw == "bj") {
            swal({
              title : '时间颗粒度和日期范围请合理关联!',// 展示的标题
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

        //加载数据之前,先处理数据
        dataProcessing();
        // if (startDateBetween > endDateBetween) {
        //   let endDateBetweenLength = endDateBetween.length;
        //   let startDateBetweenLength = startDateBetween.length;
        //   let startDateBetweenLength - endDateBetweenLength
        //   endDateBetweenLength.push();
        // }

        $('#zl_qstjfxTable_tolldw').fixedThead('destroy');//销毁

        $('#zl_qstjfxDay_tolldw').html("");
        $('#zl_qstjfx_body_tolldw').html("");
        var dom = document.getElementById("zl_tbhbfxdw_echarsdw");
        var myChart = echarts.init(dom, "light");
        myChart.clear();

        var time_start = $("#zl_tbhbfxdw_bq_start_time").val();//本期起始时间
        var time_end = $("#zl_tbhbfxdw_bq_end_time").val();//本期结束时间
        var tqtime_start = $("#zl_tbhbfxdw_tq_start_time").val();//同期起始时间
        var tqtime_end = $("#zl_tbhbfxdw_tq_end_time").val();//同期结束时间
        //获取选中的树节点
        let checkedZlbh = $('#tree_zl_tbhbfxdw').treeview('getChecked');
        let checkedZlbhs = [];
        let checkedZlbhsName = [];
        checkedZlbh.forEach((node,i) => {
          checkedZlbhs.push(node.nodeTreeId);
          checkedZlbhsName.push(node.text);
        })

        var nodeNames = [];
        var series = []; //echarts列表
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
          url: _ctx + "/view/dataAnalysis/yoyAndMoMAnalysis",
          dataType: "json",
          async: true,
          traditional: true,
          data: {
            "fType": sjkld,
            "time_start": time_start + " 00:00:00",
            "time_end": time_end + " 23:59:59",
            "tqtime_start":tqtime_start + " 00:00:00",
            "tqtime_end": tqtime_end + " 23:59:59",
            "zlbhs": checkedZlbhs,
            "nhlx": fnybh
        // "fConfType" : "1"//收费站:1 服务区:2 隧道:3
      },
        beforeSend: function () {
          showLoad();
        },

        success: function (result) {

          var data = result && result.data;

          if (data["本期"].length <= 0 && data["同期"].length <= 0)
          {
            swal({
              title : '没有搜索到要查找的内容!',// 展示的标题
              text : "",// 内容
              type : "warning",
              showCloseButton : false, // 展示关闭按钮
              allowOutsideClick : false,
              showConfirmButton : false,
              timer : 1000
            });

            return;
          }
          var dataMap_bq = new Map();
          var dataMap_tq = new Map();

          var bqtimeSet = new Set();
          var tqtimeSet = new Set();

          //判断本期是否有数据
          if (data["本期"].length > 0) {
            data["本期"].forEach(bqData => {
              var fzlbh = bqData.fZlbh;
              var fCjsj = bqData.fCjsj;

              y_unit = bqData.fUnit;

              bqtimeSet.add(fCjsj);

              var itemMapbq = dataMap_bq.get(fzlbh);

              if (!itemMapbq)
              {
                itemMapbq = new Map();

                dataMap_bq.put(fzlbh, itemMapbq);

              }

              itemMapbq.put(fCjsj, bqData);
            })
          }

          //判断同期是否有数据
          if (data["同期"].length > 0) {
            data["同期"].forEach(tqData => {
              var fzlbh = tqData.fZlbh;
              var fCjsj = tqData.fCjsj;

              y_unit = tqData.fUnit;

              tqtimeSet.add(fCjsj);

              var itemMaptq = dataMap_tq.get(fzlbh);

              if (!itemMaptq)
              {
                itemMaptq = new Map();

                dataMap_tq.put(fzlbh, itemMaptq);

              }

              itemMaptq.put(fCjsj, tqData);
            })
          }

          let bqRemovalTime = [];
          let tqRemovalTime = [];
          for (let i = 0; i < startDateBetween.length; i++) {
            if (Array.from(bqtimeSet).indexOf(startDateBetween[i]) == -1) {
              bqRemovalTime.push(i)
            }
          }

          for (let j = 0; j < endDateBetween.length; j++) {
            if (Array.from(tqtimeSet).indexOf(endDateBetween[j]) == -1) {
              tqRemovalTime.push(j)
            }
          }

          let newArr = [];
          for (let i = 0; i < tqRemovalTime.length; i++) {
            for (let j = 0; j < bqRemovalTime.length; j++) {
              if(bqRemovalTime[j] === tqRemovalTime[i]){
                newArr.push(bqRemovalTime[j]);
              }
            }
          }

          //移除本期,同期都没有的时间
          newArr.forEach(len => {
            startDateBetween.splice(len);
            endDateBetween.splice(len);
          })


          console.log(endDateBetween);
          console.log(startDateBetween);

          var qstjfhdaydata = "<th class='czjz qstjfhFixHead' colspan='2' style='width:20%;white-space: nowrap;' >支路名称</th>";
          startDateBetween.forEach(time => {
            if (sjkld === '0') {//时
              time = time.substring(0, 13);
            } else if (sjkld === '1') {//日
              time = time.substring(0, 10);
            } else if (sjkld === '2') {//月
              time = time.substring(0, 7);
            } else if (sjkld === '3') {//年
              time = time.substring(0, 4);
            }
            qstjfhdaydata += "<th class='qstjfhFixHead' style='white-space: nowrap;' >" + time + "</th>"
          });

          $('#zl_qstjfxDay_tolldw').html(qstjfhdaydata);

          var tdData = "";

          var bqseriesData = [];
          var tqseriesData = [];

          checkedZlbhs.forEach((zlbh,i) => {



            tdData += "<tr ><th class='czjz qstjfhFixHead' rowspan='2'  style='width:20%;white-space: nowrap;'>" + checkedZlbhsName[i] + "</th>"
            tdData += "<th class='qstjfhFix' style='white-space: nowrap;text-align:center;'>本期</th>"

            let bqfDatas = 0.0;

            startDateBetween.forEach(fCjsj => {

              let bqfData = 0.0;

              if (dataMap_bq.get(zlbh) != null) {
                if (dataMap_bq.get(zlbh).get(fCjsj) != null) {

                  if (typeof (dataMap_bq.get(zlbh).get(fCjsj).fData) != "undefined") {
                    bqfData = dataMap_bq.get(zlbh).get(fCjsj).fData;
                  }
                }
              }

              tdData += "<th class='qstjfhFix' style='white-space: nowrap;text-align:center;'>" + bqfData.toFixed(2) + "</th>";

              bqfDatas += bqfData;
              /* echarts 图表*/

            });
            bqseriesData.push(bqfDatas.toFixed(2));

            tdData += "</tr>"
            tdData += "<tr>"
            tdData += "<th class='qstjfhFix' style='white-space: nowrap;text-align:center;'>同期</th>"

            let tqfDatas = 0.0;
            endDateBetween.forEach(fCjsj => {

              let tqfData = 0.0;

              if (dataMap_tq.get(zlbh) != null) {
                if (dataMap_tq.get(zlbh).get(fCjsj) != null) {

                  if (typeof (dataMap_tq.get(zlbh).get(fCjsj).fData) != "undefined") {
                    tqfData = dataMap_tq.get(zlbh).get(fCjsj).fData;
                  }
                }
              }

              tdData += "<th class='qstjfhFix' style='white-space: nowrap;text-align:center;'>" + tqfData.toFixed(2) + "</th>";

              tqfDatas += tqfData;
              /* echarts 图表*/

            });
            tqseriesData.push(tqfDatas.toFixed(2));
            tdData += "</tr>";



            nodeNames.push(checkedZlbhsName[i]);

          })

          let bqseriesMap = {};

          bqseriesMap['name'] = "本期数据";
          bqseriesMap['type'] = tblx;
          bqseriesMap['smooth'] = 'true';
          bqseriesMap['data'] = bqseriesData;
          bqseriesMap['symbol'] = "roundRect";
          bqseriesMap['symbolClip'] = true;
          series.push(bqseriesMap);

          let tqseriesMap = {};
          tqseriesMap['name'] = "同期数据";
          tqseriesMap['type'] = tblx;
          tqseriesMap['smooth'] = 'true';
          tqseriesMap['data'] = tqseriesData;
          tqseriesMap['symbol'] = "roundRect";
          tqseriesMap['symbolClip'] = true;
          series.push(tqseriesMap);

          $('#zl_qstjfx_body_tolldw').html(tdData);


          $('#zl_qstjfxTable_tolldw').fixedThead({side:'87.5%',body:'87.5%',thead:'93%',row:1,col:1});//固定表头和第一列



          chartShow(nodeNames, ["本期数据","同期数据"], series, "支路", y_unit);

        },
        complete: function () {
          hiddenLoad();
        }
      });
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
        let thead = $("#clone_zl_qstjfxTable_tolldw_head>thead>tr>th").eq(0).text();
        let thead2 = $("#clone_zl_qstjfxTable_tolldw_head>thead>tr").eq(0).find("th");
        alias.push(thead);
        names.push("a0");
        $(thead2).each(function (i) {

          if (i != 0) {
            if (($(this).html()).indexOf("div") == -1 && ($(this).html()) != "" ) {
              alias.push($(this).html());
              names.push("a" + (i));
            }
          }

          // alias.push($(this).html());
          // names.push("a" + (i + 1));
        });
        // 内容
        let tbodyTr = $("#clone_zl_qstjfxTable_tolldw_head>tbody>tr");
        $(tbodyTr).each(function (i) {
          let map = {};
          let tbodyTh = $(this).find("th");
          if (i % 2 != 0) {
            map['a0'] = $(tbodyTr).eq(i - 1).find("th").html();
          }
          $(tbodyTh).each(function (j) {
            if (i % 2 != 0) {
              let text = $(this).html();
              map['a' + (j + 1)] = text;
            } else {
              let text = $(this).html();
              map['a' + j] = text;
            }
          });
          ALLlist.push(map);
        });
        //导出--传表名和传list---jsonList
        var exportName = "同比环比分析";
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
      //tab点击事件
      tabclick: function (object) {
        $(object.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
        var val = object.getAttribute("value");
        fnybh = val;
        left_tree(val, true, []);
        //清空table
        $("#zl_tbhbfxdw_table_fix").empty();
        $('#zl_tbhbfxdw_table_fix').fixedThead('destroy');//销毁
        myChart.clear();//清空echars
        view_dataAnalysis_zlyn_zl_tbhbfxdw.pageInit();
      },
      //自定义本期时间改变事件
      bqtime_change: function () {
        var bqstart = $("#zl_tbhbfxdw_bq_start_time").val();
        var bqend = $("#zl_tbhbfxdw_bq_end_time").val();
        bqstart = parserDate(bqstart);
        bqend = parserDate(bqend);
        if (dbxs == '0') {//同比
          bqstart.setFullYear(bqstart.getFullYear() - 1);
          bqend.setFullYear(bqend.getFullYear() - 1);
        } else if (dbxs == '1') {//环比

          if (bqstart != null && bqend != null) {
            var t = 2 * bqstart.getTime() - bqend.getTime() - 24 * 60 * 60 * 1000;
            bqend.setTime(bqstart.getTime() - 24 * 60 * 60 * 1000);
            bqstart.setTime(t);
          }
          // bqstart.setFullYear(bqstart.getFullYear() - 1);
          // bqend.setFullYear(bqend.getFullYear() - 1);
        }
        $("#zl_tbhbfxdw_tq_start_time").val(getFormatDate(bqstart));
        $("#zl_tbhbfxdw_tq_end_time").val(getFormatDate(bqend));
      },
      pageInit: function () {
        //初始化条件
        skkld_select(sjkld_idArray, sjkld_valArray)
        dbxs_select(idArray, valArray);
        rqfw_select(rq_idArray, rq_valArray);
        tblx_select(tb_idArray, tb_valArray);

        dwhs_select(dwhs_idArray, dwhs_valArray);
        time_csh();//初始化时间
        getHeight();//重新计算高度
      },
      //打印按钮
      print: function () {
        $("#clone_zl_qstjfxTable_tolldw_head").printThis({});
      }
    }

  })(jQuery, window, document);
  view_dataAnalysis_zlyn_zl_tbhbfxdw.pageInit();
</script>



