<div style="height:95%">
    <div id="archiveManageTable"></div>
</div>

   <!---分页表单----->
   <div style="height:30px">
       <form action="${ctx }/view/energydataReport/archivemanage/getArchiveManageListPage" id="archiveManagePageForm">
           <!-- 查询条件，用隐藏表单域 -->
           <input type="hidden" value="${keywords! }" name="keywords"/>
				<#assign formId = "archiveManagePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "archiveManage_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
       </form>
   </div>        
   
<script type="text/javascript">
    ;
    var archiveManage_page = (function ($, window, document, undefined)
    {
        var pageNum = '${page.pageNum}';
        var optIconFunction = function (cell, formatterParams)
        { //plain text value
            var id = cell.getRow().getData().id;
            return "<div class='btn-group '>"
                    + "<button class='btn btn-white btn-sm download' data-id=" + id + "><i class='fa fa-download'></i>  下载</button></div>"
        };

        //创建并设置table属性
        $("#archiveManageTable").tabulator({
            height: "100%",
            layout: "fitColumns",//fitColumns  fitDataFill
            columnVertAlign: "bottom", //align header contents to bottom of cell
            tooltips: false,
            //selectable:true,
            movableColumns: true,
            columns: [
                {
                    title: "序号",
                    field: "f_id",
                    align: "center",
                    width: 70,
                    formatter: "rownum",
                    frozen: false,
                    sorter: "number"
                },
                {
                    title: "文件名称",
                    field: "fileName",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "文件日期",
                    field: "fileName",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false,
                    formatter: function (data)
                    {
                        var subVal = data.cell.value.slice(-10);
                        var year = subVal.slice(0, 4);
                        var month = subVal.slice(4, 6);
                        var day = subVal.slice(6, 8);
                        var hour = subVal.slice(8, 10);
                        return year + '-' + month + '-' + day + ' ' + hour;
                    }
                },
                {
                    title: "下载次数",
                    field: "downloadCount",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false
                },
                {
                    title: "创建时间",
                    field: "createTime",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false,
                    formatter: function (data)
                    {
                        return utils.dateFormat(new Date(data.cell.value), 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {
                    title: "更新时间",
                    field: "updateTime",
                    sorter: "string",
                    editor: false,
                    align: "center",
                    headerSort: false,
                    formatter: function (data)
                    {
                        return utils.dateFormat(new Date(data.cell.value), 'yyyy-MM-dd hh:mm:ss');
                    }
                },
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
                $("#archiveManageTable").tabulator("selectRow", 1);
                _curRow = row;
            },
        });
        $("#archiveManageTable").tabulator("setData", '${pageList}');
        return {
            pageNum
        }
    })(jQuery, window, document);
</script>