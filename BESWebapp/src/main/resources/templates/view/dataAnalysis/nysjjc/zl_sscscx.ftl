<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">
  .zl_zlynjc {
    display: flex;
    align-items: center;
    margin-left: 13px;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .treeview span.icon {
    width: 5px !important;
    margin-right: 10px !important;
  }

  .form-control {
    padding: 0px !important;
  }

  .btn-primary {
    margin-right: 5px !important;
  }

  .jzxs {
    text-align: center;
  }

  .czjz {
    vertical-align: middle !important;
    text-align: center;
  }

  /* tab */
</style>
<ul id="sscscx_tab" class="nav tabs tsys">
</ul>
<!-- 组织机构树模块 -->
<div class="leftarea information_left" style="width:18.4%!important;height:96% !important;">
  <div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择支路>>>
		</span>
  </div>
  <div id="tree_zl_sscscx" class="Information_area"></div>
</div>
<div class="information_right" style="width:81.5%!important;height:96% !important;">
  <div class="information-model">
		<span class="Subtitle"> <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
		</span>
      <#--打印按钮-->
    <a href="javascript:void(-1);" onclick="view_dataAnalysis_eneryCollection_zl_sscscx.print()"
       class="btn btn-primary toLeft">
      <i class="fa fa-print" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
    </a>
		<button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft"
						onclick="view_dataAnalysis_eneryCollection_zl_sscscx.exp()">
			<i class="fa fa-download"></i>&nbsp;报表生成
		</button>
  </div>
  <div id="zl_sscscx_table" class="Information_area"></div>
</div>
  <script type="text/javascript">
    ;
    var view_dataAnalysis_eneryCollection_zl_sscscx = (function ($, window, document, undefined) {
      var _ctx = '${ctx}';
      var _curRow = null;
      var fnybh = "";//fnybh 能耗类型
      var checkid = "";//默认获取第三级第一个id
			var exp_list;//电能参数值的集合(报表生成时,使用)
			var exp_columb;//电能参数集合(报表生成时,使用)

      $(window).resize(function () {
        $("#zl_sscscx_table").tabulator("redraw");
      });

      //加载tab-list
      $(function () {
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
                opt += "<li><a href='#home' value='" + obj.ID + "' data-toggle='tab' onclick='view_dataAnalysis_eneryCollection_zl_sscscx.tabclick(this)'>" + obj.NAME + "</a></li>";
              }
              $("#sscscx_tab").append(opt);
              $("#sscscx_tab").find("li").eq(0).addClass("active nocancel");
              fnybh = $("#sscscx_tab").find("li>a").eq(0).attr("value");
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
      });

      //加载左侧树
      function left_tree(fnybh) {
        $.ajax({
          type: "post",
          url: _ctx + "/view/dataAnalysis/branch_tree",
          data: {"fnybh": fnybh},
          dataType: "json",
          success: function (result) {
            if (result.hasOwnProperty("list")) {//返回tree成功
              $('#tree_zl_sscscx').treeview({
                data: result.list,         // 数据源
                highlightSelected: true,    //是否高亮选中
                levels: 4,
                enableLinks: true,//必须在节点属性给出href属性
                wrapNodeText: true,
                color: "#4a4747",
                showCheckbox: false,
                hierarchicalCheck: true,//级联勾选
                propagateCheckEvent: true,
                onNodeSelected(event, nodeData) {
                  var level = nodeData.nodeStatus;
                  var zlid = nodeData.id;
                  Refresh(zlid, level, fnybh);//刷新table数据
                },
                onNodeChecked: function (event, nodeData) {//选中方法
                },
                onNodeUnchecked: function (event, nodeData) {//取消方法
                }
              });
              var firstNode = $("#tree_zl_sscscx").treeview('findNodes', [result.list[0].id, 'id']);//一级
              var node = $("#tree_zl_sscscx").treeview('findNodes', [firstNode[1].id, 'id']);//二级
              var three_node = $("#tree_zl_sscscx").treeview('findNodes', [node[1].id, 'id']);//三级
            } else {//树查询失败
              swal("当前能源下暂无支路配置", "", "warning");
              $('#tree_zl_sscscx').treeview('remove');//移除列表树容器。
              $('#tree_zl_sscscx').treeview('uncheckAll', {silent: true});//清空所有check
            }
          },
          error: function (nodeData) {
            swal(nodeData.msg, "", "error");
          }
        });
      }

      //刷新table数据 level 几级节点
      function Refresh(zlid, level, fnybh) {
        $.ajax({
          type: "post",
          url: _ctx + "/view/dataAnalysis/eneryCollection/zl_sscscx_sssj",
          data: {
            "zlid": zlid,
            "level": level,
            "fnybh": fnybh,
          },
          dataType: "json",
          beforeSend: function () {
            showLoad();
          },
          success: function (result) {
            if (result.hasOwnProperty("list")) {
              tabulator(result.list, result.data);
            } else {
              swal("该能源下暂未配置电表参数", "", "warning");
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

      //加载tabulator
      function tabulator(list, data) {
        //创建数组
        var columb = [];
        columb.push({
          title: '电表名称',
          field: 'a00',
          width: 170,
          sorter: 'string',
          editor: false,
          align: 'center',
          headerSort: false
        });
        for (var i = 0; i < list.length; i++) {
          var fieid = "a" + list[i].DNBH;//电能编号作为识别字段名称
          var title = list[i].NAME + "(" + list[i].UNIT + ")"
          columb.push({
            title: title,
            field: fieid,
            width: 200,
            sorter: 'string',
            editor: false,
            align: 'center',
            headerSort: false
          });
        }
        //创建并设置table属性
        $("#zl_sscscx_table").tabulator({
          height: "100%",
          layout: "fitColumns",//fitColumns  fitDataFill
          columnVertAlign: "bottom", //align header contents to bottom of cell
          tooltips: false,
          movableColumns: true,
          columns: columb,
        });
				exp_columb = columb;
				exp_list =  data;
        $("#zl_sscscx_table").tabulator("setData", data);
      }

      //定时任务 实时刷新数据
// 	setInterval(function (){
// 		var selNode = $("#tree_zl_sscscx").treeview('getSelected');
// 		var zlid = selNode.id;
// 		var level = selNode.id;
// 		Refresh(zlid,level);
// 	},10000);
      return {
        //tab点击事件
        tabclick: function (object) {
          $(object.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
          var val = object.getAttribute("value");
          left_tree(val);
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
          var exportName = "实时参数查询";
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
        pageInit: function () {

        },
        //打印按钮
        print: function () {
          $("#zl_sscscx_table").printThis({});
        }
      }
    })(jQuery, window, document);

    view_dataAnalysis_eneryCollection_zl_sscscx.pageInit();
  </script>

