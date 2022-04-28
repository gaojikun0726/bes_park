<div style="height:90%">
  <div id="deviceFunctionValueTable"></div>
</div>

<!---分页表单----->
<#--<div style="height:30px">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceFunction/queryValuePage"
        id="deviceFunctionValueForm">
    <!-- 查询条件，用隐藏表单域 &ndash;&gt;
    <input type="hidden" value="${keywords! }" name="keywords"/>
    <input type="hidden" id="deviceFunctionValuePageNum" value="${pageNum! }"  name="pageNum" />
      <#assign formId = "deviceFunctionValueForm"><!-- formId: 分页控件表单ID &ndash;&gt;
      <#assign showPageId = "deviceFunctionValuePageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 &ndash;&gt;
      <#include "/common/page.ftl"/><!-- 分页控键 &ndash;&gt;
  </form>
</div>-->

<script type="text/javascript">
  var deviceFunctionValuePage = (function ($, window, document, undefined)
  {
    var _curRow = null;
    var dataEncodeTypeObject = [];

    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceFunctionValueModalEdit'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
    };
    var pageNum = $("#deviceFunctionValuePageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceFunctionValueTable").tabulator({
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
        {title: "名称", field: "name", sorter: "string", editor: false, align: "center", headerSort: false},
        // {title: "设备类型", field: "deviceType", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "功能值", field: "value", sorter: "string", editor: false, align: "center", headerSort: false},
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
        _curRow = row;
      },
    });

    $("#deviceFunctionValueTable").tabulator("setData", ${pageList});

  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>