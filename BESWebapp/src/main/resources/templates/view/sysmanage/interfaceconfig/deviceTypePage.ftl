<div style="height:95%">
    <div id="deviceTypeTable"></div>
</div>

   <!---分页表单----->
   <div style="height:30px">
       <form action="${ctx}/view/sysmanage/interfaceconfig/deviceType/queryPage"
             id="deviceTypeForm">
           <!-- 查询条件，用隐藏表单域 -->
           <input type="hidden" value="${keywords! }" name="keywords"/>
				<#assign formId = "deviceTypeForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "deviceTypePageContainer"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
       </form>
   </div>
   
<script type="text/javascript">
    var deviceTypePage = (function ($, window, document, undefined)
    {

        var optIconFunction = function (cell, formatterParams)
        { //plain text value
            var id = cell.getRow().getData().id;
            return "<div class='btn-group '>"
                    + "<button class='btn btn-white btn-sm edit' data-id=" + id + " data-toggle='modal' data-target='#deviceTypeModalEdit'><i class='fa fa-pencil' ></i> 编辑</button>"
                    + "<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
        };

        //创建并设置table属性
        $("#deviceTypeTable").tabulator({
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
                {title: "设备类型名称", field: "name", sorter: "string", editor: false, align: "center", headerSort: false},
                {title: "设备类型代码", field: "code", sorter: "string", editor: false, align: "center", headerSort: false},
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
                $("#deviceTypeTable").tabulator("selectRow", 1);
                _curRow = row;
            },
        });

        $("#deviceTypeTable").tabulator("setData", ${pageList});


    })(jQuery, window, document);
</script>