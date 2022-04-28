<div style="height:90%">
  <div id="deviceConfigurationTable"></div>
</div>

<!---分页表单----->
<div style="height:9%">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceConfiguration/queryPage"
        id="deviceConfigurationForm">
    <!-- 查询条件，用隐藏表单域 -->
    <input type="hidden" value="${keywords! }" name="keywords"/>
    <input type="hidden" value="${deviceTypeId! }" name="deviceTypeId"/>
    <input type="hidden" id="deviceConfigurationPageNum" value="${pageNum! }"  name="deviceConfigurationPageNum" />
      <#assign formId = "deviceConfigurationForm"><!-- formId: 分页控件表单ID -->
      <#assign showPageId = "deviceConfigurationPageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
      <#include "/common/page.ftl"/><!-- 分页控键 -->
  </form>
</div>

<script type="text/javascript">
  var deviceConfigurationPage = (function ($, window, document, undefined)
  {
    var _curRow = null;
    var dataEncodeTypeObject = [];

    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceConfigurationModalEdit'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
    };
    var pageNum = $("#deviceConfigurationPageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceConfigurationTable").tabulator({
      height: "100%",
      layout:"fitColumns",
      columnVertAlign:"bottom",
      tooltips:true,
      resizableColumns:false,
      selectable:1,
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
        {title: "设备名称", field: "name", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "设备类型", field: "deviceType", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "设备代码", field: "code", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "位置", field: "site", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "备注", field: "comments", sorter: "string", editor: false, align: "center", headerSort: false},
        // {title: "创建日期", field: "createTime", sorter: "date", editor: false, align: "center", headerSort: false},
        // {title: "修改日期", field: "updateTime", sorter: "date", editor: false, align: "center", headerSort: false},
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
        row.select();
        // $("#deviceConfigurationTable").tabulator("selectRow", 1);
        _curRow = row;
        deviceConfiguration.getPagingPage_function(_curRow.getData().id);
      },
    });

    $("#deviceConfigurationTable").tabulator("setData", ${pageList});
    //默认加载第一个节点
    var firstNode = $("#deviceConfigurationTable").tabulator("getRows");
    if(firstNode.length == 0) {
      $("#deviceConfigurationTable").tabulator("selectRow",[]);
      //加载当前
      deviceConfiguration.getPagingPage_function("空");
    }else {
      $("#deviceConfigurationTable").tabulator("selectRow",firstNode[0]);
      //加载当前
      deviceConfiguration.getPagingPage_function(firstNode[0].getData().id);
    }

  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>