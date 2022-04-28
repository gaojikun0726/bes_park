<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">
  .form-control {
    padding: 0px !important;
  }

  .zl_cscx input {
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

  .zl_sxtjq_cscx {
    width: 100%;
    height: 96%;
    position: relative;
  }

  .xzzlys {
    cursor: text;
    background-color: rgb(216, 239, 255);
    width: 7vw !important;
    height: 3vh;
    text-align: left;
    padding-left: 0.6vh;
  }
</style>
<ul id="cscx_tab" class="nav tabs tsys">
</ul>
<!-- 组织机构树模块 -->
<div class="leftarea information_left">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;加载数据条件>>>
		</span>
  </div>
  <div class="zl_sxtjq_cscx">
    <div class="sjnyjc_zlyn"><span class="zl_sxtj_span">选择时间： </span>
      <input id="zl_cscx_time" type="text" name="start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"
             class="input-datecheck">
    </div>
    <div id="zl_cscx_xzzl" class="sjnyjc_zlyn battery_style"><span class="zl_sxtj_span">选择支路：</span>
    </div>
    <div class="sjnyjc_zlyn"><span class="zl_sxtj_span">选择电表：</span>
      <div id="zl_cscx_xzdb"></div>
    </div>
    <div class="sjnyjc_zlyn" id="zl_cscx_dncs"><span class="zl_sxtj_span">请选择电能参数>>> </span>
      <input type="text" style="visibility: hidden;" class="input-datecheck">
    </div>
    <div id="tree_zl_cscx_dncs" class="Information_area"
         style="height:72%!important;border-top: 1px solid #00adffa6;"></div>
    <div style="height:5%;position: absolute;width:100%;bottom: 0;">
      <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_cscx.sub()">
          <i class="fa fa-spinner"></i>&nbsp;加载数据
        </button>
        <button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_cscx.exp()">
          <i class="fa fa-download"></i>&nbsp;报表生成
        </button>
        <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                onclick="view_dataAnalysis_eneryCollection_zl_cscx.reset()">
          <i class="fa fa-refresh"></i>&nbsp;重置
        </button>
      </div>
    </div>
  </div>
</div>
<div class="information_right">
  <div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
			</span>
      <#--打印按钮-->
    <a href="javascript:void(-1);" onclick="view_dataAnalysis_eneryCollection_zl_cscx.print()"
       class="btn btn-primary toLeft">
      <i class="fa fa-print" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
    </a>
  </div>
  <!--tbale -->
  <div id="zl_cscx_table" class="Information_area">
  </div>
  <script src="${ctx}/static/js/time_range.js"></script><!-- 时间范围工具 -->
  <script type="text/javascript">
    ;
    var view_dataAnalysis_eneryCollection_zl_cscx = (function ($, window, document, undefined) {
      var _ctx = '${ctx}';
      var _curRow = null;
      var fnybh = "";//fnybh 能耗类型
      var zlid = "";//支路模态框 支路id
      var zlmc = "";//支路模态框 支路名称
      var ammeterid = "";//电表id
			var exp_dncs;//电能参数(报表生成时,使用)
			var exp_dncsmc;//电能名称(报表生成时,使用)
			var exp_list;//电能参数值的集合(报表生成时,使用)
			var exp_columb;//电能参数集合(报表生成时,使用)

      $("#zl_cscx_xzzl").ISSPHelpComboBox({
        title: '请选择支路名称',
        inputWidth: '9vw',
        inputEditable: false,
        inputPromMsg: '请选择支路',
        getDataFun: ZlxxData,
        isMultistage: true,
        searchDisCxt: '请输入支路名称',
        callBacks: zlxxConfirm,  //自定义事件
      });

      function ZlxxData() {//点击选择支路
        $.ajax({
          type: "post",
          url: _ctx + "/view/dataAnalysis/branch_tree",
//             url:"${ctx}/view/basedatamanage/energyinformation/subitem_treegrid",
          data: {"fnybh": fnybh},
          dataType: "json",
          success: function (result) {
            if (result.hasOwnProperty("list")) {
              _zlNodes = $("#zl_cscx_xzzl").setComboBoxData({data: result.list,});
            } else {
              swal("当前能源下暂无支路配置", "", "warning");
            }
          },
          error: function (nodeData) {
            swal(nodeData.msg, "", "error");
          }
        });
      };

      function zlxxConfirm(node) {//双击或者确认后
        var saveMap = new Map();
        if (node != '') {
          var clickStatus = node.clickStatus;
          if (clickStatus == 'undefined' || clickStatus == '' || clickStatus == null) {//判断是否重置按钮
            return false;
          }
        }
        var nodeList = _zlNodes.data('treeview').getChildsArrayByNode(node[0]);
        nodeList.push(node[0].id);//存入当前节点
        var nodesStr = nodeList.join(",");//支路id
        Select_Xzdb(nodesStr);
      };

      //实例化selected
      function xzdb_select(idArray, valArray) {
        //选择电表下拉列表 空间选择
        $("#zl_cscx_xzdb").ISSPSpinnerBox({
          width: '9vw',//下拉列表宽度
          height: '2.9vh',//下拉列表高度
          margLeft: '0%',//margin-left属性
          isHasData: true,
          idArray: idArray,//id
          valArray: valArray,//txt
          liveSearch: false,//关闭搜索框
          isNoSelectedText: true, //是否设置未选中提示文本
          callBack: xzdb_change,  //自定义事件
        });
      }

      //选择电表change回调
      function xzdb_change(sp) {
        ammeterid = sp.id;//ammeterid
        var txt = sp.txt;
        $("#tree_zl_cscx_dncs").empty();
        if (ammeterid != '' && ammeterid != 'undefined' && ammeterid != null) {
          $.ajax({
            type: "post",
            url: _ctx + "/view/dataAnalysis/eneryCollection/zl_cscx_Select_dncs",
            beforeSend: function () {
              showLoad();
            },
            data: {
              "ammeterid": ammeterid,
            },
            success: function (result) {
              if (result.hasOwnProperty("list")) {
                var obj = "";//电能参数
                obj += "<div class='col-sm-12' style='padding-top: 5px;'>"
                var res = result.list;
                for (var i = 0; i < res.length; i++) {
                  var BS = res[i].BS;//单位
                  var ID = res[i].ID;//id
                  var NAME = res[i].NAME;//名称
                  obj += "<label style='cursor:pointer;display:flex;padding:3px;'>";
                  obj += "<input type='checkbox' name=" + NAME + " value=" + ID + ">";
                  obj += "" + NAME + "(" + BS + ")" + "";
                  obj += "</label>";
                }
                obj += "</div>";
                $("#tree_zl_cscx_dncs").append(obj);
              } else {
                swal("该电表下未查询到电能参数", "", "warning");
              }
            },
            complete: function () {
              hiddenLoad();
            },
            error: function (nodeData) {
              swal("电能参数查询失败", "", "error");
            }
          });
        }
      }

      //加载tab-list
      $(function () {
        tab_load();
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
                opt += "<li><a href='#home' value='" + obj.ID + "' data-toggle='tab' onclick='view_dataAnalysis_eneryCollection_zl_cscx.tabclick(this)'>" + obj.NAME + "</a></li>";
              }
              $("#cscx_tab").append(opt);
              $("#cscx_tab").find("li").eq(0).addClass("active nocancel");
              fnybh = $("#cscx_tab").find("li>a").eq(0).attr("value");
            }
          },
          complete: function () {
            hiddenLoad();
          },
          error: function (nodeData) {
            swal(nodeData.msg, "", "error");
          }
        });
      }

      //根据条件查询动态table拼装数据和动态echars拼装数据
      function pin_data(time, ammeterid, dncs, dncsmc) {
        $.ajax({
          type: "post",
          url: _ctx + "/view/dataAnalysis/eneryCollection/zl_cscx_dncs_tjfx",
          beforeSend: function () {
            showLoad();
          },
          data: {
            "time": time,
            "ammeterid": ammeterid,
            "dncs": dncs,
          },
          dataType: "json",
          success: function (result) {
            if (result.hasOwnProperty("list")) {
              dynamic_tabulator(result, dncs, dncsmc);//动态列展示
            } else {
              swal("没有符合条件的记录", "", "warning");
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

      //动态拼装tabulator
      function dynamic_tabulator(result, dncs, dncsmc) {
        var dncssz = dncs.split(',');
        var obj = result.list;
        var columb = [];
        columb.push({
          title: '时间',
          field: 'time',
          width: 200,
          sorter: 'string',
          editor: false,
          align: 'center',
          headerSort: false
        },);
        for (var i = 0; i < dncssz.length; i++) {
          var fieid = "a" + dncssz[i];
          columb.push({
            title: dncsmc[i],
            field: fieid,
            width: 100,
            sorter: 'string',
            editor: false,
            align: 'right',
            headerSort: false
          },);
        }
        //创建并设置table属性
        $("#zl_cscx_table").tabulator({
          height: "100%",
          layout: "fitColumns",//fitColumns  fitDataFill
          columnVertAlign: "bottom", //align header contents to bottom of cell
          tooltips: false,
          movableColumns: true,
          columns: columb,
        });
				exp_columb = columb;
				exp_list =  result.list;
        $("#zl_cscx_table").tabulator("setColumns", columb);//用新列定义数组覆盖现有列 */
        $("#zl_cscx_table").tabulator("setData", result.list);
      }

      //获取选择电表下拉框 根据支路id
      function Select_Xzdb(zlid) {
        $.ajax({
          type: "post",
          url: _ctx + "/view/dataAnalysis/eneryCollection/zl_cscx_Select",
          beforeSend: function () {
            showLoad();
          },
          data: {
            "zlid": zlid,
          },
          success: function (result) {
            if (result.hasOwnProperty("list")) {
              var idArray = new Array();//selected id
              var valArray = new Array();//selected txt
              var obj = "<option value='qxz'>--请选择--</option>";//选择电表
              var res = result.list;
              for (var i = 0; i < res.length; i++) {
                idArray.push(res[i].NAME);
                valArray.push(res[i].NICKNAME);
              }
              xzdb_select(idArray, valArray);
            } else {
              swal("该支路下未查询到电表", "", "warning");
            }
          },
          complete: function () {
            hiddenLoad();
          },
          error: function (nodeData) {
            swal("下拉列表查询失败", "", "error");
          }
        });
      }

      return {
        //清空条件
        reset: function () {
          view_dataAnalysis_eneryCollection_zl_cscx.pageInit();
          $("#zl_cscx_xzzl").clearComboxText("zl_cscx_xzzl");
        },
        //加载数据
        sub: function () {
          //获取条件值
          var time = $("#zl_cscx_time").val();//选择时间
          var dncsarr = [];//定义电能参数数组
          var dncsmc = [];//电能参数名称
          $("#tree_zl_cscx_dncs").find("input[type=checkbox]:checked").each(function (i) {//each循环所有:checkbox
            dncsarr.push($(this).val());
            dncsmc.push($(this).attr("name"));
          });
          var dncs = dncsarr.join(",");
          if (dncs == '' || dncs == 'undefined' || dncs == null) {
            swal("请选择电能参数！", "", "warning");
          } else {
						exp_dncs = dncs;
						exp_dncsmc = dncsmc;
            //1.根据条件拼装table 2.根据条件拼装echars
            pin_data(time, ammeterid, dncs, dncsmc);
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

					for (let i = 0; i < exp_columb.length; i++) {
						alias.push(exp_columb[i].title) ;
					}
					for (let j = 0; j < alias.length; j++) {
						names.push("a" + j);
					}
					for (let i = 0; i < exp_list.length; i++) {
						let map = {};
						for (let j = 0; j < exp_columb.length; j++) {
							map["a" + j] = exp_list[i][exp_columb[j].field];
						}
						ALLlist.push(map);
					}
					//导出--传表名和传list---jsonList
					var exportName = "支路参数查询";
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
          var nybh = object.getAttribute("value");//能源bh
          view_dataAnalysis_eneryCollection_zl_cscx.pageInit();
          $("#tree_zl_cscx_dncs").empty();
          $("#zl_cscx_xzzl").clearComboxText("zl_cscx_xzzl");
          fnybh = nybh;
        },
        pageInit: function () {
          $('#zl_cscx_time').val(getFormatDate(getCurrentDate()));
          xzdb_select(new Array(), new Array());//初始化selected
        },
        //打印按钮
        print: function () {
          $("#zl_cscx_table").printThis({});
        }
      }


    })(jQuery, window, document);
    view_dataAnalysis_eneryCollection_zl_cscx.pageInit();
  </script>