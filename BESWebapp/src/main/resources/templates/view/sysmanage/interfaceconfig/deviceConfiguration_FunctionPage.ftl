<div style="height:100%">
  <div id="deviceConfiguration_FunctionTable"></div>
</div>

<!---分页表单----->
<#--<div style="height:9%">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceConfiguration/queryPage_function"
        id="deviceConfiguration_FunctionForm">
    <!-- 查询条件，用隐藏表单域 &ndash;&gt;
&lt;#&ndash;    <input type="hidden" value="${keywords! }" name="keywords"/>&ndash;&gt;
    <input type="hidden" id="deviceConfiguration_FunctionPageNum" value="${pageNum! }"  name="pageNum" />
      <#assign formId = "deviceConfiguration_FunctionForm"><!-- formId: 分页控件表单ID &ndash;&gt;
      <#assign showPageId = "deviceConfiguration_FunctionPageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 &ndash;&gt;
      <#include "/common/page.ftl"/><!-- 分页控键 &ndash;&gt;
  </form>
</div>-->

<script type="text/javascript">
  var deviceConfiguration_FunctionPage = (function ($, window, document, undefined)
  {
    var _curRow = null;
    var dataEncodeTypeObject = [];


    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      var deviceFunctionPointID = cell.getRow().getData().deviceFunctionPointID;

      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceConfiguration_Function_AssociationPoint'><i class='fa fa-pencil' ></i>关联点位</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + deviceFunctionPointID + "><i class='fa fa-trash'></i>  删除</button>"
          + "</div>"
    };
    var pageNum = $("#deviceConfiguration_FunctionPageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceConfiguration_FunctionTable").tabulator({
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
        {title: "设备功能名称", field: "name", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "功能代码", field: "code", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "单位", field: "unit", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "功能点位名称", field: "functionPointName", sorter: "string", editor: false, align: "center", headerSort: false},
        // {title: "点位值", field: "value", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "系统名称", field: "sysName", sorter: "string", editor: false, align: "center", headerSort: false},
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
        // $("#deviceConfiguration_FunctionTable").tabulator("selectRow", 1);
        _curRow = row;
      },
    });

    $("#deviceConfiguration_FunctionTable").tabulator("setData", ${pageList});
    //默认加载第一个节点
    // var firstNode = $("#deviceConfiguration_FunctionTable").tabulator("getRows");
    // $("#deviceConfiguration_FunctionTable").tabulator("selectRow",firstNode[0]);

  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>