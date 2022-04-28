<div style="height:95%">
  <div id="deviceFunctionPointTable"></div>
</div>

<!---分页表单----->
<div style="height:30px">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceFunctionPoint/queryPage"
        id="deviceFunctionPointForm">
    <!-- 查询条件，用隐藏表单域 -->
    <input type="hidden" value="${keywords! }" name="keywords"/>
    <input type="hidden" value="${deviceId! }" name="deviceId"/>
    <input type="hidden" value="${deviceFunctionId! }" name="deviceFunctionId"/>
    <input type="hidden" id="deviceFunctionPointPageNum" value="${pageNum! }"  name="pageNum" />
      <#assign formId = "deviceFunctionPointForm"><!-- formId: 分页控件表单ID -->
      <#assign showPageId = "deviceFunctionPointPageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
      <#include "/common/page.ftl"/><!-- 分页控键 -->
  </form>
</div>

<script type="text/javascript">
  var deviceFunctionPointPage = (function ($, window, document, undefined)
  {

    var dataEncodeTypeObject = [];

    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceFunctionPointModalEdit'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
    };
    var pageNum = $("#deviceFunctionPointPageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceFunctionPointTable").tabulator({
      height: "100%",
      layout: "fitColumns",//fitColumns  fitDataFill
      columnVertAlign: "bottom", //align header contents to bottom of cell
      tooltips: true,
      movableColumns: true,
      columns: [
        {
          title: "序号",
          field: "f_id",
          width: 50,
          formatter: "rownum",
          frozen: false,
          align: "center",
          sorter: "number",
          headerSort: false
        },
        {title: "名称", field: "name", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "所属设备", field: "deviceName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "设备功能", field: "deviceFunctionName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "点位值", field: "value", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "系统名称", field: "sysName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "顺序", field: "order", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "是否为复位键", field: "isResetKey", sorter: "string", editor: false, align: "center", headerSort: false,formatter:function(cell, formatterParams){
            var type = cell.getRow().getData().isResetKey;
            if (type == "0") {
              return "否"
            } else if (type == "1") {
              return "是"
            }

          }},
        {title: "备注", field: "comments", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "创建日期", field: "createTime", sorter: "date", editor: false, align: "center", headerSort: false},
        {title: "修改日期", field: "updateTime", sorter: "date", editor: false, align: "center", headerSort: false},
        {
          title: "操作",
          field: "opt",
          width: 200,
          tooltip: false,
          tooltipsHeader: false,
          align: "center",
          formatter: optIconFunction,
          headerSort: false
        },
      ],
      rowClick: function (e, row)
      {
        $("#deviceFunctionPointTable").tabulator("selectRow", 1);
        _curRow = row;
      },
    });

    $("#deviceFunctionPointTable").tabulator("setData", ${pageList});

  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>