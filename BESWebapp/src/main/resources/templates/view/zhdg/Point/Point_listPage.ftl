<#--
  描述:设备维护
  作者:YangChao
  时间:2020-08-27 18:36:45
-->
<div style="height:95%">
    <div id="Point_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/point/getSearch" id="PointModulePageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <#assign formId = "PointModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Point_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhdg_Point_listModuleClosurePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#Point_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"唯一编码",field:"fCode",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"设备名称",field:"fPointName",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"显示屏IP",field:"fPointIp",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"显示屏编码",field:"screenCode",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"PDU IP",field:"pduIp",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"音柱IP",field:"soundIp",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"动环主板IP",field:"mainboardIp",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"摄像头IP",field:"cameraIp",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"一键报警IP",field:"alarmIp",sorter:"string",editor:false,align:"center",headerSort:false},
	            // {title:"经度",field:"fLongitude",sorter:"string",editor:false,align:"center",headerSort:false},
	            // {title:"纬度",field:"fLatitude",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"所属区域",field:"fRegionName",sorter:"string",editor:false,align:"center",headerSort:false},
	            // {title:"详细地址",field:"fRemark",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"气象状态",field:"fStatus",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function(cell, formatterParams){
	                var status = cell.getRow().getData().fStatus;
	                if(status=='0'){
	                    return "<span style='color: #00FF00'>在线</span>";
                    }else if(status == '1'){
                        return "<span style='color: #ff0000'>告警</span>";
                    }else{
                        return "<span style='color: #C0C0C0'>离线</span>";
                    }
                }},
            {title:"操作", field:"opt",width:300,align:"left",tooltip:false,tooltipsHeader:false,
            formatter:function(cell, formatterParams){
                var guid = cell.getRow().getData().fGuid;
                var fcode = cell.getRow().getData().fCode;
                return "<div class='btn-group '>" +
                    "<button class='btn btn-white btn-sm xq' data-id="+ fcode + " data-toggle='modal' data-target='#realPointformXqModel'><i class='fa fa-info-circle'></i>  详情</button>" +
                    "<button class='btn btn-white btn-sm edit' data-id="+ fcode + " data-toggle='modal' data-target='#realPointformSzModel'><i class='fa fa-cog' ></i> 设置</button></div>" +
                    "<button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#PointformModel'><i class='fa fa-pencil' ></i> 编辑</button>"+
                    "<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button>" +
                    "</div>"
            },headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Point_iboxTable").tabulator("setData",'${pageList}');

    //每隔5秒执行一次，加载气象状态数据
    setInterval(getStatusData,5000);

    //获取气象状态数据
    function getStatusData(){

        $.ajax({
            url: _ctx + '/zhdg/point/getWeatherStatusData',
            type: "post",
            success: function (result) {

                if(result && result.map){
                    var ipMap = result.map;
                    var tableData = $("#Point_iboxTable").tabulator("getData");
                    for(var i = 0; i < tableData.length; i++){
                        var ip = tableData[i].mainboardIp;
                        if(ipMap.hasOwnProperty(ip)){
                            tableData[i].fStatus = ipMap[ip];
                            // console.log("状态数据：ip="+ip+",status="+ipMap[ip]);
                        }
                    }
                    // console.log("**********************");
                    $("#Point_iboxTable").tabulator("setData",tableData);
                }
            }, error: function () {
            }
        });
    }
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>
