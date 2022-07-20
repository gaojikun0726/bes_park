<div style="height:90%">
  <div id="deviceExceptionLogTable"></div>
</div>

<!---分页表单----->
<div style="height:9%">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceExceptionLog/queryPage"
        id="deviceExceptionLogForm">
    <!-- 查询条件，用隐藏表单域 -->
    <input type="hidden" value="${keywords! }" name="keywords"/>
    <input type="hidden" value="${deviceTypeId! }" name="deviceTypeId"/>
    <input type="hidden" id="deviceExceptionLogPageNum" value="${pageNum! }"  name="deviceExceptionLogPageNum" />
      <#assign formId = "deviceExceptionLogForm"><!-- formId: 分页控件表单ID -->
      <#assign showPageId = "deviceExceptionLogPageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
      <#include "/common/page.ftl"/><!-- 分页控键 -->
  </form>
</div>

<script type="text/javascript">
  var deviceExceptionLogPage = (function ($, window, document, undefined)
  {
    var _curRow = null;
    var dataEncodeTypeObject = [];

    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
    };
    var pageNum = $("#deviceExceptionLogPageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceExceptionLogTable").tabulator({
      height: "100%",
      layout:"fitColumns",
      columnVertAlign:"bottom",
      tooltips:true,
      // selectable:1,
      selectablePersistence:false,
      resizableColumns:false,
      movableColumns:false,
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
        {title: "设备名称", field: "deviceName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "设备类型", field: "deviceTypeName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "功能", field: "deviceFunctionTypeName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "区域", field: "positionName", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "异常信息", field: "exceptionLog", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "异常时间", field: "exceptionTime", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "创建日期", field: "createTime", sorter: "date", editor: false, align: "center", headerSort: false},
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
    });

    $("#deviceExceptionLogTable").tabulator("setData", ${pageList});


  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>