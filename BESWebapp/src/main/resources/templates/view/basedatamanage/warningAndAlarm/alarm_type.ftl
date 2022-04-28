<#--
功能：警报类型名称展示
作者：liuzhen
时间：2018/11/27
-->
<!-- 信息表格模块 -->
<div class="information_size">
    <div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;报警类型列表>>>
    </span>
        <!-- 增加按钮 -->
		<a id="addAlarmTypeButton" data-toggle="modal"  href="#addAlarmTypeModal" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
		</a>
		<!-- 导入 -->
        <#--导入功能的下载模板和上传功能没有对应的方法，故将导入按钮注释掉-->
		<#--<a data-toggle="modal" href="#import-addAlarmType" class="btn btn-primary toLeft"> <i class="fa fa-upload"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导入</a>-->
		<!-- 导出 -->
        <#--导出功能没有对应的方法，故将导出按钮注释掉-->
		<#--<a class="btn btn-primary toLeft" onclick="basedatamanage_alarmAndWarning_alarmType.exportReport()"> <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导出</a>-->
		 
        <!-- 搜索框 -->
        <div class="zc_search find">
            <input  id="queryAlarmType" type="text" class="find-style" id="alarmTypeInfo" name="alarmTypeInfo" placeholder="报警类型名称">
            <button  onclick="basedatamanage_alarmAndWarning_alarmType.getAlarmTypeBySearch()"><i
                    class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div id="alarmTypeTable" class="Information_area"></div>
</div>
<!-- 信息表格模块end -->

<!----添加报警类型--->
<div class="modal fade" id="addAlarmTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 报警类型</h4>
            </div>
            <div class="modal-body">
                <form id="addTypeForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="alarmTypeName">报警类型名称： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="alarmTypeName"   required class="form-control">
                        </div>
                    </div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong>确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!----编辑报警类型--->
<div class="modal fade" id="updateAlarmTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 报警类型</h4>
            </div>
            <div class="modal-body">
                <form id="updateTypeForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="updateAlarmTypeName">报警类型名称： <span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="updateAlarmTypeName"   required class="form-control">
                        </div>
                    </div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong>确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 上传模态框 -->
<div class="modal fade" id="import-addAlarmType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 35%;margin: 0 auto;">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;导入</h4>
            </div>
            <div class="modal-body">
            	<div class="row">
		            <div class="col-md-12">
		                <div class="alert alert-danger" style="font-size:13px;" role="alert">
		                <strong>注 意：</strong><br>
		                &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
		                </div>
		            </div>
	            </div>
				<div class="row"> 
		             <div class="col-xs-12">               
		                 <div class="input-group">
		                    <span class="input-group-addon">模板下载</span>
		                     <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp" onclick="basedatamanage_alarmAndWarning_alarmType.btn_exp()">下载模板</a>
		                         </span>
		                     </div>
		                </div>
		             </div>                    
		        </div>				
				<div class="row" id="import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="reportImpExcel" >
                            <input id="exportInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="basedatamanage_alarmAndWarning_alarmType.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
            </div>            
        </div>
    </div>
</div>
<script type="text/javascript">
;
var basedatamanage_alarmAndWarning_alarmType = (function($, window, document, undefined) {
   var updateId="";//更新时用到的id
    //设置表格操作格式
    var optIconFunction = function(cell, formatterParams){ //plain text value
        var typeId = cell.getRow().getData().alarmNum;
        return "<div class='btn-group '>"
                +"<button  class='btn btn-white btn-sm edit' data-id='"+typeId+"' data-toggle='modal' data-target='#updateAlarmTypeModal'><i class='fa fa-pencil' ></i> 编辑</button>"
                +"<button  class='btn btn-white btn-sm delete' data-id='"+typeId+"' data-toggle='modal'  data-target=''><i class='fa fa-trash' ></i> 删除</button></div>"

    };
    //创建并设置table属性
    $("#alarmTypeTable").tabulator({
        height : "95%",
        layout : "fitColumns",
        columnVertAlign : "bottom",
        tooltips : true,
        movableColumns : false,
        //date:tableDate,
        columns : [ {title:"序号", field:"id",formatter:"rownum",sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
            {title:"报警名称", field:"alarmTypeName",sorter:"string",editor:false,align:"left",headerSort:false},
            {title:"创建时间", field:"fcrdate",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"修改时间", field:"fchdate",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"操作", tooltip:false,tooltipsHeader:false,width:200,align:"center",headerSort:false,formatter:optIconFunction}],
        rowClick : function(e, row) {
            _curRow = row;
        },
    });

    $(document).on('click','#alarmTypeTable button.delete', function () {
        var id = $(this).data("id");
        deleteAlertType(id);
    });

    $(document).on('click','#alarmTypeTable button.delete', function () {
        var id = $(this).data("id");
        deleteAlertType(id);
    });

    //居中显示（添加报警类型）
    $('#addAlarmTypeModal').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#addAlarmTypeModal .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    });

    //居中显示（添加报警类型）
    $('#updateAlarmTypeModal').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#updateAlarmTypeModal .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    });

    //添加报警类型表单验证
    $("#addTypeForm").validate({
        submitHandler: function(form) {
            addTypeForm(form);
        }
    });
    //添加报警类型表单验证
    $("#updateTypeForm").validate({
        submitHandler: function(form) {
            updateTypeForm(form);
        }
    });

    //更新报警类型信息
    function updateTypeForm(form) {
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/updateAlarmType",
            type: "post",
            data:{
                alarmNum:updateId,
                alarmTypeName:$("#updateAlarmTypeName").val()
            },
            success: function(data) {

                getAlarmType();
                $('#updateAlarmTypeModal').modal('hide');//关闭编辑窗口

            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    }



    //更新报警类型信息赋值
    $("#updateAlarmTypeModal").on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget);
         updateId = button.data("id");//获取用户组编号
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmType",
            type: "post",
            data:{
                alarmNum:updateId
            },
            success: function(result)
            {
                console.log(result);
                $("#updateAlarmTypeName").val(result.list[0].alarmTypeName);
            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    });
    //插入报警类型信息
    function addTypeForm(form) {
        $.issp_ajax({
            url:"${ctx}/view/basedatamanage/warningAndAlarm/insertAlarmType",
            type: "post",
            data:{
                alarmTypeName: $("#alarmTypeName").val(),
            },
            success: function(data) {

                getAlarmType();
                $('#addAlarmTypeModal').modal('hide');//关闭编辑窗口
                $("#alarmTypeName").val("");
            },
            error: function(data) {
                swal(data.msg, "", "error");
            }
        });
    }

    //删除报警类型信息
    function deleteAlertType(id){
        swal({
            title: "您确定要删除吗?",
            text: "信息删除后将不可恢复!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#1ab394",
            confirmButtonText: "确定",
            closeOnConfirm: true
        }, function () {
            setTimeout(function(){
                        $.issp_ajax({
                            url: "${ctx}/view/basedatamanage/warningAndAlarm/deleteAlarmType",
                            type: "post",
                            data:{
                                "alarmNum":id
                            },
                            success: function(data) {
                                getAlarmType();
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
    function getAlarmType()
    {
        //每次先将数据清空
        $("#alarmTypeTable").tabulator('setData',[]);
        $.issp_ajax({
            type: "post",
            url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmType",
            dataType: "json",
            traditional: true,
            success: function (result)
            {
                console.log(result);
                if(result.hasOwnProperty("list"))
                {
                    if(result.list.length>0)
                    {
                        $("#alarmTypeTable").tabulator('setData',result.list);
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
        getAlarmTypeBySearch:function()
        {
        //每次先将数据清空
            $("#alarmTypeTable").tabulator('setData',[]);
            $.issp_ajax({
                type: "post",
                url:"${ctx}/view/basedatamanage/warningAndAlarm/getAlarmTypeBySearch",
                dataType: "json",
                traditional: true,
                data:{
                    alarmTypeName: $("#queryAlarmType").val(),
                },
                success: function (result)
                {
                    if(result.hasOwnProperty("list"))
                    {
                        if(result.list.length>0)
                        {
                            $("#alarmTypeTable").tabulator('setData',result.list);
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
            getAlarmType();
        }

    }



})(jQuery, window, document);
basedatamanage_alarmAndWarning_alarmType.pageInit();

</script>