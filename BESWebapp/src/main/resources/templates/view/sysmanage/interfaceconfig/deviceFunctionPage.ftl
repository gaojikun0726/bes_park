<div style="height:90%">
  <div id="deviceFunctionTable"></div>
</div>

<!---分页表单----->
<div style="height:30px">
  <form action="${ctx}/view/sysmanage/interfaceconfig/deviceFunction/queryPage"
        id="deviceFunctionForm">
    <!-- 查询条件，用隐藏表单域 -->
    <input type="hidden" value="${keywords! }" name="keywords"/>
    <input type="hidden" value="${deviceTypeId! }" name="deviceTypeId"/>
    <input type="hidden" id="deviceFunctionPageNum" value="${pageNum! }"  name="pageNum" />
      <#assign formId = "deviceFunctionForm"><!-- formId: 分页控件表单ID -->
      <#assign showPageId = "deviceFunctionPageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
      <#include "/common/page.ftl"/><!-- 分页控键 -->
  </form>
</div>

<script type="text/javascript">
  var deviceFunctionPage = (function ($, window, document, undefined)
  {

    var _curRow = null;
    var dataEncodeTypeObject = [];

    var optIconFunction = function (cell, formatterParams)
    { //plain text value
      var id = cell.getRow().getData().id;
      return "<div class='btn-group '>"
          + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceFunctionModalEdit'><i class='fa fa-pencil' ></i> 编辑</button>"
          + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button>"
          + "<button class='btn btn-white btn-sm add' data-id=" + id + " data-toggle='modal' data-target='#deviceFunctionValueModalAdd'><i class='fa fa-pencil' ></i> 添加值</button></div>"
    };
    var pageNum = $("#deviceFunctionPageNum").val();//新增或修改后(其他页面获取值)
    //创建并设置table属性
    $("#deviceFunctionTable").tabulator({
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
        // {title: "设备类型", field: "deviceType", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "设备功能代码", field: "code", sorter: "string", editor: false, align: "center", headerSort: false},
        // {title: "点位类型", field: "type", sorter: "string", editor: false, align: "center", headerSort: false,formatter:function(cell, formatterParams){
        //     var type = cell.getRow().getData().type;
        //     if (type == "1") {
        //       return "AO"
        //     } else if (type == "2") {
        //       return "AI"
        //     }else if (type == "3") {
        //       return "DO"
        //     }else if (type == "4") {
        //       return "DI"
        //     }
        //
        //   }},
        {title: "单位", field: "unit", sorter: "string", editor: false, align: "center", headerSort: false},
        {title: "备注", field: "comments", sorter: "string", editor: false, align: "center", headerSort: false},
        // {title: "创建日期", field: "createTime", sorter: "date", editor: false, align: "center", headerSort: false},
        // {title: "修改日期", field: "updateTime", sorter: "date", editor: false, align: "center", headerSort: false},
        {
          title: "操作",
          field: "opt",
          width: 260,
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
        deviceFunction.getPagingPage_value(_curRow.getData().id);
        // $("#deviceFunctionTable").tabulator("selectRow", 1);
        // _curRow = row;
      },
    });

    $("#deviceFunctionTable").tabulator("setData", ${pageList});
//默认加载第一个节点
    var firstNode = $("#deviceFunctionTable").tabulator("getRows");
    if(firstNode.length == 0) {
      $("#deviceFunctionTable").tabulator("selectRow",[]);
      //加载当前
      deviceFunction.getPagingPage_value("空");
    }else {
      $("#deviceFunctionTable").tabulator("selectRow",firstNode[0]);
      //加载当前
      deviceFunction.getPagingPage_value(firstNode[0].getData().id);
    }


  return{
    getPageNum : function() {
      return pageNum;
    },
  }
  })(jQuery, window, document);
</script>