<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">

  /* tab */
  .form-control {
    padding: 0px !important;
  }

  .zl_fsdtj input {
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
  }

  .tabulator {
    height: 96% !important;
  }

  .zl_sxtjq_fsdtj {
    width: 100%;
    height: 96%;
    position: relative;
  }
</style>
<ul id="fsdtj_tab" class="nav tabs tsys">
</ul>
<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;加载数据条件>>>
		</span>
  </div>
  <div class="zl_sxtjq_fsdtj">
    <div class="sjnyjc_zlyn zl_fsdtj_jsgd">
      <span class="zl_sxtj_span">日期范围：</span>
      <div id="zl_fsdtj_rqfw"></div>
    </div>
    <div class="sjnyjc_zlyn zl_fsdtj_jsgd">
      <span class="zl_sxtj_span">时间起始： </span> <input id="zl_fsdtj_start_time" disabled="disabled" type="text"
                                                      name="start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"
                                                      class="input-datecheck">
    </div>
    <div class="sjnyjc_zlyn zl_fsdtj_jsgd">
      <span class="zl_sxtj_span">时间终止： </span> <input id="zl_fsdtj_end_time" disabled="disabled" type="text" name="end"
                                                      onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"
                                                      class="input-datecheck">
    </div>
    <div class="sjnyjc_zlyn zl_fsdtj_jsgd">
      <span class="zl_sxtj_span">请选择支路>>> </span> <input type="text" style="visibility: hidden;"
                                                         class="input-datecheck">
    </div>

    <div id="tree_zl_fsdtj"
         style="height:72%!important;border-top: 1px solid #00adffa6;overflow-y: auto;overflow-x: auto;width: 100%;"></div>
    <div class="zl_fsdtjbotton"
         style="height:5%;position: absolute;width:100%;bottom: 0;">
      <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_fsdtj.sub()">
          <i class="fa fa-spinner"></i>&nbsp;加载数据
        </button>
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_fsdtj.exp()">
          <i class="fa fa-filter"></i>&nbsp;报表生成
        </button>
        <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_fsdtj.reset()">
          <i class="fa fa-refresh"></i>&nbsp;重置
        </button>
      </div>
    </div>
  </div>
</div>
<div class="information_right">
	<div class="information-model">
		<span class="Subtitle"> <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
		</span>
      <#--打印按钮-->
    <a href="javascript:void(-1);" onclick="view_dataAnalysis_eneryCollection_zl_fsdtj.print()"
       class="btn btn-primary toLeft">
      <i class="fa fa-print" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
    </a>
  </div>
  <!--tbale -->
  <div id="zl_fsdtj_table" class="Information_area"></div>
</div>
<script src="${ctx}/static/js/time_range.js"></script>
<!-- 时间范围工具 -->
<script type="text/javascript">
  ;
  var view_dataAnalysis_eneryCollection_zl_fsdtj = (function ($, window,
                                                              document, undefined) {
    var _ctx = '${ctx}';
    var _curRow = null;
    var fnybh = "";//fnybh 能耗类型
    var checkid = "";//默认获取第三级第一个id
    var idArray = ['bt', 'bz', 'by', 'bj', 'bn', 'sr', 'sz', 'sy', 'sj',
      'sn', 'zdy'];
    var valArray = ['本天', '本周', '本月', '本季', '本年', '上日', '上周', '上月', '上季',
      '上年', '自定义'];

    var energyType = "";//能源类型编号

    var expData = null;

    //select 下拉框
    function rqfw_select(idArray, valArray) {
      $("#zl_fsdtj_rqfw").ISSPSpinnerBox({
        width: '9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft: '0%',//margin-left属性
        isHasData: true,
        idArray: idArray,//id
        valArray: valArray,//txt
        liveSearch: false,//关闭搜索框
        isNoSelectedText: false, //是否设置未选中提示文本
        callBack: timeChange, //自定义事件
      });
    }

    //时间改变事件
    function timeChange(sp) {
      var id = sp.id;//时间id
      $("#zl_fsdtj_start_time,#zl_fsdtj_end_time").removeAttr("disabled")
          .attr("disabled", true);
      $("#zl_fsdtj_start_time,#zl_fsdtj_end_time").removeAttr("cursor")
          .css("cursor", "not-allowed");
      //联动规则
      switch (id) {
          //0表示今天
        case "bt":
          var time = getCurrentDate();
          $('#zl_fsdtj_start_time').val(getFormatDate(time));
          $('#zl_fsdtj_end_time').val(getFormatDate(time));
          break;
          //1本周
        case "bz":
          var time = getCurrentWeek();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //2本月
        case "by":
          var time = getCurrentMonth();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //3本季
        case "bj":
          var time = getCurrentSeason();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //4本年
        case "bn":
          var time = getCurrentYear();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //5表示昨天
        case "sr":
          var time = getPreviousDate();
          $('#zl_fsdtj_start_time').val(getFormatDate(time));
          $('#zl_fsdtj_end_time').val(getFormatDate(time));
          break;
          //6上周
        case "sz":
          var time = getPreviousWeek();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //7上月
        case "sy":
          var time = getPreviousMonth();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //8上季
        case "sj":
          var time = getPreviousSeason();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //9上年
        case "sn":
          var time = getPreviousYear();
          $('#zl_fsdtj_start_time').val(getFormatDate(time[0]));
          $('#zl_fsdtj_end_time').val(getFormatDate(time[1]));
          break;
          //自定义
        case "zdy":
          $("#zl_fsdtj_start_time,#zl_fsdtj_end_time").removeAttr(
              "disabled");
          $("#zl_fsdtj_start_time,#zl_fsdtj_end_time").css("cursor",
              "default");
          break;
        default:
          break;
      }
    }

    //创建并设置table属性
    $("#zl_fsdtj_table").tabulator({
      height: "100%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: false,
      movableColumns: true,
      columns: [{
        title: "节点名称",
        field: "ZLMC",
        sorter: "string",
        width: 150,
        editor: false,
        align: "center",
        headerSort: false
      }, //never hide this column
        {
          title: "当前节点能耗",
          field: "DQJDNH",
          sorter: "string",
          width: 150,
          editor: false,
          align: "center",
          headerSort: false
        }, //hide this column first
        {
          title: "下级节点能耗合计",
          field: "XJJDNHHJ",
          width: 200,
          sorter: "string",
          editor: false,
          align: "center",
          headerSort: false
        }, {
          title: "当前节点和下级节点合计差值",
          field: "DQJDHXJJDHJZ",
          width: 250,
          sorter: "string",
          align: "center",
          editable: false,
          headerSort: false
        }, {
          title: "相差百分比(%)",
          field: "CCBFB",
          sorter: "string",
          width: 150,
          align: "center",
          editable: false,
          headerSort: false
        }, {
          title: "金额(元)",
          field: "JE",
          sorter: "string",
          width: 150,
          align: "center",
          editable: false,
          headerSort: false
        }, {
          title: "能耗量(吨)",
          field: "MHL",
          sorter: "string",
          width: 150,
          align: "center",
          editable: false,
          headerSort: false
        }, {
          title: "二氧化碳(ppm)",
          field: "CO",
          sorter: "string",
          width: 150,
          align: "center",
          editable: false,
          headerSort: false
        }],
    });

    $(window).resize(function () {
      $("#zl_fsdtj_table").tabulator("redraw");
      setTimeout(function () {
        getHeight()
      }, 1);
// 			getHeight();
    });

    function getHeight() {
      //获取左侧高度
      var allheight = $(".zl_sxtjq_fsdtj").height();//总高度
      var botton = $(".zl_fsdtjbotton").height();//底部
      var num = $(".zl_fsdtj_jsgd").length;
      var tj = $(".zl_fsdtj_jsgd").outerHeight() - 1.1;
      var s = allheight - (num * tj) - botton;
      $("#tree_zl_fsdtj").height(s);
    }

    //加载tab-list
    $(function () {
      tab_load();
      //填加默认时间
      var time = getCurrentDate();//获取当前时间
      $('#zl_fsdtj_start_time').val(getFormatDate(time));
      $('#zl_fsdtj_end_time').val(getFormatDate(time));
      $("#zl_fsdtj_start_time,#zl_fsdtj_end_time").removeAttr("cursor")
          .css("cursor", "not-allowed");
    });

    //动态拼装tab
    function tab_load() {
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
              opt += "<li><a href='#home' value='"
                  + obj.ID
                  + "' data-toggle='tab' onclick='view_dataAnalysis_eneryCollection_zl_fsdtj.tabclick(this)'>"
                  + obj.NAME + "</a></li>";
            }
            $("#fsdtj_tab").append(opt);
            $("#fsdtj_tab").find("li").eq(0).addClass("active nocancel");
            fnybh = $("#fsdtj_tab").find("li>a").eq(0).attr("value");
            energyType = fnybh;
          }
          left_tree(fnybh);
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
    function left_tree(fnybh) {
      $.ajax({
        type: "post",
        url: _ctx + "/view/dataAnalysis/branch_tree",
        data: {
          "fnybh": fnybh
        },
        dataType: "json",
        success: function (result) {
          if (result.hasOwnProperty("list")) {//返回tree成功
            $('#tree_zl_fsdtj').treeview({
              data: result.list, // 数据源
              highlightSelected: true, //是否高亮选中
              levels: 4,
              enableLinks: true,//必须在节点属性给出href属性
              wrapNodeText: true,
              color: "#4a4747",
              showCheckbox: true,
              hierarchicalCheck: true,//级联勾选
              propagateCheckEvent: true,
              onNodeChecked: function (event, nodeData) {//选中方法
              },
              onNodeUnchecked: function (event, nodeData) {//取消方法
              }
            });
            var firstNode = $("#tree_zl_fsdtj").treeview('findNodes', [result.list[0].id, 'id']);//一级
            var node = $("#tree_zl_fsdtj").treeview('findNodes', [firstNode[1].id, 'id']);//二级
            var three_node = $("#tree_zl_fsdtj").treeview('findNodes', [node[1].id, 'id']);//三级
            checkid = node[1].id;
            first_check(checkid);//默认勾选
          } else {//树查询失败
            swal("当前能源下暂无支路配置", "", "warning");
            $('#tree_zl_fsdtj').treeview('remove');//移除列表树容器。
            $('#tree_zl_fsdtj').treeview('uncheckAll', {
              silent: true
            });//清空所有check
          }
        },
        error: function (nodeData) {
          swal(nodeData.msg, "", "error");
        }
      });
    }

    //默认勾选
    function first_check(checkid) {
      var li = $("#tree_zl_fsdtj").find("li");
      for (var i = 0; i < li.length; i++) {
        var id = $("#tree_zl_fsdtj").find("li").eq(i).attr("id");
        if (id == checkid) {
          $("#tree_zl_fsdtj").find("li").eq(i).find(".check-icon").click();
        }
      }
      //触发加载数据事件
      view_dataAnalysis_eneryCollection_zl_fsdtj.sub();
    }

    //时间js----判断时间条件
    function timeFormat() {
      var startTime = $('#zl_fsdtj_start_time').val();
      var endTime = $('#zl_fsdtj_end_time').val();
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

    //根据条件查询动态table拼装数据和动态echars拼装数据
    function pin_data(time_start, time_end, zlid, unit) {
      $
          .ajax({
            type: "post",
            url: _ctx
                + "/view/dataAnalysis/eneryCollection/zl_fsdtj_jcsj",
            beforeSend: function () {
              showLoad();
            },
            data: {
              "time_start": time_start,
              "time_end": time_end,
              "zlid": zlid,
            },
            dataType: "json",
            success: function (result) {
              if (result.hasOwnProperty("list")) {
                let list = result.list;
                for (let a = 0; a < list.length; a++) {

                  if (list[a].DQJDNH != null && list[a].DQJDNH != "-") {
                    list[a].DQJDNH = list[a].DQJDNH.toFixed(2) + unit;
                  } else {
                    list[a].DQJDNH = '-';
                  }
                  if (list[a].XJJDNHHJ != null && list[a].XJJDNHHJ != "-") {
                    list[a].XJJDNHHJ = list[a].XJJDNHHJ.toFixed(2) + unit;
                  } else {
                    list[a].XJJDNHHJ = '-';
                  }
                  if (list[a].JE != null && list[a].JE != "-") {
                    list[a].JE = list[a].JE;
                  } else {
                    list[a].JE = '-';
                  }
                  if (list[a].MHL != null && list[a].MHL != "-") {
                    list[a].MHL = list[a].MHL;
                  } else {
                    list[a].MHL = '-';
                  }
                  if (list[a].CO != null && list[a].CO != "-") {
                    list[a].CO = list[a].CO;
                  } else {
                    list[a].CO = '-';
                  }

                }
                expData = list;
                $("#zl_fsdtj_table").tabulator("setData", list);
              } else {
                swal("未查询到数据", "", "error");
                $("#zl_fsdtj_table").tabulator("setData", []);
              }
            },
            complete: function () {
              hiddenLoad();
            },
            error: function (nodeData) {
              swal("图表初始化失败", "", "error");
            }
          });
    }

    function getRealUnit(callback) {
      if (typeof callback !== "function") {
        return;
      }
      $.ajax({
        type: "POST",
        url: _ctx + "/view/dataAnalysis/eneryCollection/getUnitByEnergyType",
        dataType: "json",
        data: {
          energyType: energyType
        },
        success: function (result) {

          if (result.status == "0") {
            swal(result.msg, "", "warning");
          } else if (result.status == "1") {
            callback(result.data);
          }
        },
        error: function (result) {

          console.warn(result)
        }
      });
    }

    return {
      //清空条件
      reset: function () {
// 				var nhlx = $("#fsdtj_tab>.active>a").attr("value");//能耗类型
// 				left_tree(nhlx);
        view_dataAnalysis_eneryCollection_zl_fsdtj.pageInit();
        $('#tree_zl_zlynjc').treeview('uncheckAll', {silent: true});//清空所有check
      },
      //加载数据
      sub: function () {
        var checkid = $("#tree_zl_fsdtj").treeview('getChecked');
        var arr = [];
        var three_arr = [];//三级 支路id数组
        var two_arr = [];//二级
        for (var i = 0; i < checkid.length; i++) {
          var nodetreeId = checkid[i].nodeTreeId;
          var level = checkid[i].nodeStatus;//级数
          arr.push(nodetreeId);
          if (level == '2') {
            two_arr.push(nodetreeId);
          } else {
            three_arr.push(nodetreeId);
          }
        }
        if (arr == '' || arr == 'undefined' || arr == null) {
          swal("当前未选择支路", "", "warning");
        } else {
          var zlid = arr.join(",");//将数组转换成字符串，逗号隔开
          var two_zlids = two_arr.join(",");
          var three_zlids = three_arr.join(",");
          var flag = timeFormat();//时间验证
          if (flag) {//验证通过
            //获取条件值
            var time_start = $("#zl_fsdtj_start_time").val();//起始时间
            var time_end = $("#zl_fsdtj_end_time").val();//结束时间


            getRealUnit(function (unit) {
              //1.根据条件拼装table 2.根据条件拼装echars
              pin_data(time_start, time_end, zlid, unit);
            });
          }
        }
      },

      // 报表生成
      exp: function () {
        // excel的列头
        var alias = new Array();
        // 数据List中的Map的key值.
        var names = new Array();
        // 数据存取list
        var ALLlist = new Array();

        if (expData == null) {
          return;
        }

        alias = ["节点名称","当前节点能耗","下级节点能耗合计","当前节点和下级节点合计差值","相差百分比(%)","金额(元)","能耗量(吨)","二氧化碳(ppm)"];
        for (let j = 0; j < alias.length; j++) {
          names.push("a" + j);
        }
        for (let i = 0; i < expData.length; i++) {
          let map = {};
          map["a" + 0] = expData[i].ZLMC;
          map["a" + 1] = expData[i].DQJDNH;
          map["a" + 2] = expData[i].XJJDNHHJ;
          map["a" + 3] = expData[i].DQJDHXJJDHJZ;
          map["a" + 4] = expData[i].CCBFB;
          map["a" + 5] = expData[i].JE;
          map["a" + 6] = expData[i].MHL;
          map["a" + 7] = expData[i].CO;
          ALLlist.push(map);
        }
        //导出--传表名和传list---jsonList
        var exportName = "支路用能分时段统计";
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
        $(object.parentNode).addClass("nocancel").siblings("li")
            .removeClass("nocancel");
        var val = object.getAttribute("value");
        energyType = val;
        left_tree(val);
        view_dataAnalysis_eneryCollection_zl_fsdtj.pageInit();
      },
      pageInit: function () {
        rqfw_select(idArray, valArray);
        $("#zl_fsdtj_table").tabulator("setData", []);
// 				getHeight();
        setTimeout(function () {
          getHeight()
        }, 1);
      },
      //打印按钮
      print: function () {
        $("#zl_fsdtj_table").printThis({});
      }
    }

  })(jQuery, window, document);
  view_dataAnalysis_eneryCollection_zl_fsdtj.pageInit();
</script>

