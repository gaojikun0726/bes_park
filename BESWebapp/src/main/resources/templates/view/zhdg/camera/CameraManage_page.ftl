<#--
  描述:摄像头管理
-->
<div style="height:95%">
    <div id="Camera_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/cameraManage/selectList" id="CameraPageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords! }" name="keywords" />
        <input type="hidden" value="${pageNum! }" id="Camera_pageNum" name="Camera_pageNum" />
        <#assign formId = "CameraPageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Camera_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhdg_Camera_CameraManage_Page = (function($, window, document, undefined){
    //创建并设置table属性
    $("#Camera_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"编号",field:"cameraNum",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"名称",field:"cameraName",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"登录用户",field:"cameraUser",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"密码",field:"cameraPassword",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"IP地址",field:"cameraIp",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"操作", field:"opt",width:250,align:"left",tooltip:false,tooltipsHeader:false,
            formatter:function(cell, formatterParams){
                var guid = cell.getRow().getData().id;
                    return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#preViewModel'><i class='fa fa-pencil' ></i> 预览</button>"
                            +"<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#CameraformModel'><i class='fa fa-pencil' ></i> 编辑</button>"
                            +"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
                },headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Camera_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>