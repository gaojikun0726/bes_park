<#--
功能：警报等级名称展示
作者：liuzhen
时间：2018/11/27
-->
<!-- 信息表格模块 -->
<div class="information_size">
    <div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;报警等级列表>>>
    </span>
        <!-- 增加按钮 -->
        <button id="addAlarmLevelButton" class='btn btn-primary toLeft'  data-id='' data-toggle='modal' data-target='#addAlarmLevelModal'><i class='fa fa-plus' ></i> 增加</button>

        <!-- 搜索框 -->
        <div class="zc_search find">
            <input  id="queryAlarmLevel" Level="text" class="find-style" id="alarmLevelInfo" name="alarmLevelInfo" placeholder="报警等级名称">
            <button  onclick="basedatamanage_alarmAndWarning_alarmLevel.getAlarmLevelBySearch()"><i
                    class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div id="alarmLevelTable" class="Information_area"></div>
</div>
<!-- 信息表格模块end -->

<!----添加报警等级--->
<div class="modal fade" id="addAlarmLevelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" Level="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 报警等级</h4>
            </div>
            <div class="modal-body">
                <form id="addLevelForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="alarmLevelName">报警等级名称： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input Level="text" id="alarmLevelName"   required class="form-control">
                        </div>
                    </div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " Level="submit">
                                <strong>确定</strong>
                            </button>
                            <button Level="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!----添加报警等级--->
<div class="modal fade" id="updateAlarmLevelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" Level="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 报警等级</h4>
            </div>
            <div class="modal-body">
                <form id="updateLevelForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="updateAlarmLevelName">报警等级名称： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input Level="text" id="updateAlarmLevelName"   required class="form-control">
                        </div>
                    </div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " Level="submit">
                                <strong>确定</strong>
                            </button>
                            <button Level="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script Level="text/javascript">
;
var basedatamanage_alarmAndWarning_alarmLevel = (function($, window, document, undefined) {
   var updateId="";//更新时用到的id
    //设置表格操作格式
    var optIconFunction = function(cell, formatterParams){ //plain text value
        var LevelId = cell.getRow().getData().alarmLevelNum;
        return "<div class='btn-group '>"
                +"<button  class='btn btn-white btn-sm edit' data-id='"+LevelId+"' data-toggle='modal' data-target='#updateAlarmLevelModal'><i class='fa fa-pencil' ></i> 编辑</button>"
                +"<button  class='btn btn-white btn-sm delete' data-id='"+LevelId+"' data-toggle='modal'  data-target=''><i class='fa fa-trash' ></i> 删除</button></div>"

    };
    //创建并设置table属性
    $("#alarmLevelTable").tabulator({
        height : "95%",
        layout : "fitColumns",
        columnVertAlign : "bottom",
        tooltips : true,
        movableColumns : false,
        //date:tableDate,
        columns : [ {title:"序号", field:"id",sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
            {title:"报警等级名称", field:"alarmLevelName",sorter:"string",editor:false,align:"left",headerSort:false},
            {title:"创建时间", field:"fcrdate",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"修改时间", field:"fchdate",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"操作", tooltip:false,tooltipsHeader:false,width:200,align:"center",headerSort:false,formatter:optIconFunction}],
        rowClick : function(e, row) {
            _curRow = row;
        },
    });

    $(document).on('click','#alarmLevelTable button.delete', function () {
        var id = $(this).data("id");
        deleteAlertLevel(id);
    });

    $(document).on('click','#alarmLevelTable button.delete', function () {
        var id = $(this).data("id");
        deleteAlertLevel(id);
    });

    //居中显示（添加报警等级）
    $('#addAlarmLevelModal').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#addAlarmLevelModal .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    });

    //居中显示（添加报警等级）
    $('#updateAlarmLevelModal').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#updateAlarmLevelModal .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    });

    //添加报警等级表单验证
    $("#addLevelForm").validate({
        submitHandler: function(form) {
            addLevelForm(form);
        }
    });
    //添加报警等级表单验证
    $("#updateLevelForm").validate({
        submitHandler: function(form) {
            updateLevelForm(form);
        }
    });

    //更新报警等级信息
    function updateLevelForm(form) {
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/updateAlarmLevel",
            type: "post",
            data:{
                alarmLevelNum:updateId,
                alarmLevelName:$("#updateAlarmLevelName").val()
            },
            success: function(data) {

                getAlarmLevel();
                $('#updateAlarmLevelModal').modal('hide');//关闭编辑窗口

            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    }



    //更新报警等级信息赋值
    $("#updateAlarmLevelModal").on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget);
         updateId = button.data("id");//获取用户组编号
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmLevel",
            type: "post",
            data:{
                alarmLevelNum:updateId
            },
            success: function(result)
            {
                console.log(result);
                $("#updateAlarmLevelName").val(result.list[0].alarmLevelName);
            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    });
    //插入报警等级信息
    function addLevelForm(form) {
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/insertAlarmLevel",
            type:"post",
            data:{
                alarmLevelName: $("#alarmLevelName").val(),
            },
            success: function(data) {

                getAlarmLevel();
                $('#addAlarmLevelModal').modal('hide');//关闭编辑窗口
                $("#alarmLevelName").val("");
            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    }

    //删除报警等级信息
    function deleteAlertLevel(id){
        swal({
            title: "您确定要删除吗?",
            text: "信息删除后将不可恢复!",
            Level: "warning",
            showCancelButton: true,
            confirmButtonColor: "#1ab394",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
            setTimeout(function(){
                        $.issp_ajax({
                            url: "${ctx}/view/basedatamanage/warningAndAlarm/deleteAlarmLevel",
                            type: "post",
                            data:{
                                "alarmLevelNum":id
                            },
                            success: function(data) {
                                getAlarmLevel();
                            },
                            error: function(data) {
                                swal( data.msg,"", "error");
                            }
                        });
                    },100
            )
        });
    }

    //获取能源计划数据
    function getAlarmLevel()
    {
        //每次先将数据清空
        $("#alarmLevelTable").tabulator('setData',[]);
        $.issp_ajax({
            type: "post",
            url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmLevel",
            dataLevel: "json",
            traditional: true,
            success: function (result)
            {
                console.log(result);
                if(result.hasOwnProperty("list"))
                {
                    if(result.list.length>0)
                    {
                        $("#alarmLevelTable").tabulator('setData',result.list);
                    }
                }
            },
            error: function(result) {
                swal( result.msg,"", "error");
            }
        });
    }

    return{

        //根据搜索内容获取能源计划数据
        getAlarmLevelBySearch:function()
        {
        //每次先将数据清空
            $("#alarmLevelTable").tabulator('setData',[]);
            $.issp_ajax({
                type: "post",
                url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmLevelBySearch",
                dataLevel: "json",
                traditional: true,
                data:{
                    alarmLevelName: $("#queryAlarmLevel").val(),
                },
                success: function (result)
                {
                    if(result.hasOwnProperty("list"))
                    {
                        if(result.list.length>0)
                        {
                            $("#alarmLevelTable").tabulator('setData',result.list);
                        }
                    }
                },
                error: function(result) {
                    swal( result.msg,"", "error");
                }
            });
        },
        pageInit: function()
        {
            getAlarmLevel();
        }

    }

})(jQuery, window, document);
basedatamanage_alarmAndWarning_alarmLevel.pageInit();

</script>