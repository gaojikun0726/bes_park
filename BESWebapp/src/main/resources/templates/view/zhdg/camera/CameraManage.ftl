<#--
  描述:摄像头管理
-->
<div class="information_size">
    <div class="information-model">
        <span class="Subtitle">
            <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;摄像头列表>>>
        </span>
        <#-- 增加按钮 -->
        <a id="Camera_add" data-toggle="modal"  href="#CameraformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加</a>
        <#-- 搜索框 -->
        <div class="zc_search find">
             <input type="text" class="find-style"  id="Camera_search" placeholder="名称/IP">
             <button id="queryCameraBtn"  onclick="zhdg_Camera_CameraManage.pageInit();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="camera_ibox" style="height:91%"/>
</div>

<!---添加、编辑模态框----->
<div class="modal fade" id="CameraformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="CameraModelIcon">&nbsp; 添加摄像机</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="CameraModuleForm" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">编号<span class="text-danger">*</span></label>
                    <div class="col-sm-8">
                        <input type="text" id="cameraNum" name="cameraNum" placeholder="编号"   class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">名称</label>
                    <div class="col-sm-8">
                        <input type="text" id="cameraName" name="cameraName" placeholder="名称"   class="form-control">
                    </div>
                </div>
				<div class="form-group">
					<label class="col-sm-3 control-label">登录用户<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="cameraUser" name="cameraUser" placeholder="登录用户" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">密码<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="cameraPassword" name="cameraPassword" placeholder="密码" required  class="form-control">
					</div>
				</div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">IP地址<span class="text-danger">*</span></label>
                    <div class="col-sm-8">
                        <input type="text" id="cameraIp" name="cameraIp" placeholder="IP地址" required  class="form-control">
                    </div>
                </div>
                <div class="form-group m-t-sm" >
                    <div class="col-sm-6 col-sm-push-3 display_flex">
                        <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                        <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#--预览窗口-->
<div  class="modal fade" id="preViewModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 60vw;height: 60vh">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="preViewModelIcon">&nbsp; 视频预览</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="preViewModuleForm" class="form-horizontal">
                    <video  id="videoElement" muted autoplay controls></video>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
;var zhdg_Camera_CameraManage = (function($, window, document, undefined){
var _ctx = "${ctx}";
var player=null;
    //查询列表
    function selectCameraList(){
        $.issp_ajax({
            url : _ctx + "/zhdg/cameraManage/selectList",
            type : "post",
            data:({keywords: $("#Camera_search").val(),pageNum:$("#Camera_pageNum").val()}),
            success : function(data) {
                $("#camera_ibox").html(data);
            },
            error : function(data) {

            }
        });
    }
    /****************************添加start*/
    // 打开模态框-回显
    $('#CameraformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#CameraformModel .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
        //模态拖动
        $(this).draggable({
            handle: ".modal-header"     	// 只能点击头部拖动
        });
        $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
        var cameraId = $(event.relatedTarget).data("id");
        if(cameraId){
            //编辑回显
            reShow();
        }
    });
    //关闭模态框清空表单值
    $("#CameraformModel").on('hidden.bs.modal', function(event) {
        document.getElementById("CameraModuleForm").reset();
        addFormValidator.resetForm();
    });
    //追加设备类型信息表单验证
    var addFormValidator = $("#CameraModuleForm").validate({
        rules: {
            cameraNum:{
                required:true
            },
            cameraUser:{
                required: true,
            },
            cameraPassword:{
                required: true,
            },
            cameraIp:{
                required:true
            }
        },
        messages: {
            cameraNum:{
                required: "请输入编号",
            },
            cameraUser:{
                required: "请输入登录用户",
            },
            cameraPassword:{
                required: "请输入密码"
            },
            cameraIp:{
                required:"请输入IP"
            }
        },
        submitHandler: function (form) {
            var cameraId = $(event.relatedTarget).data("id");
            if(cameraId){
                //编辑提交
                editCameraInfo();
            }else {
                //添加提交
                addCameraInfo();
            }
        }
    });
    //提交添加表单
    function addCameraInfo(){
        $.ajax({
            url: _ctx + '/zhdg/cameraManage/add',
            dataType: 'json',
            type: 'post',
            data: {
                cameraName: $('#cameraName').val(),
                cameraNum: $('#cameraNum').val(),
                cameraUser: $("#cameraUser").val(),
                cameraPassword: $('#cameraPassword').val(),
                cameraIp: $('#cameraIp').val(),
            },
            success: function (result) {
                if (result.status == 1) {
                    swal({
                        title : result.msg,// 展示的标题
                        text : "", // 内容
                        type : "success",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    $("#CameraformModel").modal('hide');
                    selectCameraList();
                }
                else {
                    swal(result.msg, "", "error");
                }
            },
            error: function (result) {
                swal(result.msg, "", "error");
            }
        })
    }
    /****************************添加end*/
    //信息回显
    function reShow(){
        var rowData=zhdg_Camera_CameraManage_Page.get_curRow();
        var data = rowData.row.data;
        $('#cameraName').val(data.cameraName),
        $('#cameraNum').val(data.cameraNum),
        $("#cameraUser").val(data.cameraUser),
        $('#cameraPassword').val(data.cameraPassword),
        $('#cameraIp').val(data.cameraIp)
    }
    //提交编辑表单
    function editCameraInfo(){
        $.ajax({
            url: _ctx + '/zhdg/cameraManage/update',
            dataType: 'json',
            type: 'post',
            data: {
                cameraName: $('#cameraName').val(),
                cameraNum: $('#cameraNum').val(),
                cameraUser: $("#cameraUser").val(),
                cameraPassword: $('#cameraPassword').val(),
                cameraIp: $('#cameraIp').val(),
            },
            success: function (result) {
                if (result.status == 1) {
                    swal({
                        title : result.msg,// 展示的标题
                        text : "", // 内容
                        type : "success",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    $("#CameraformModel").modal('hide');
                    selectCameraList();
                }
                else {
                    swal(result.msg, "", "error");
                }
            },
            error: function (result) {
                swal(result.msg, "", "error");
            }
        })
    }
    //删除事件
    $(document).on("click", "#Camera_iboxTable button.delete", function() {
        var id = $(this).data("id");
        swal({
            title : "您确定要删除吗?",
            text : "信息删除后将不可恢复!",
            type : "warning",
            showCancelButton : true,
            confirmButtonColor : "#1ab394",
            confirmButtonText : "确定",
            closeOnConfirm : false
        }, function() {
            deleteCamera(id);
        });
    });
    //删除
    function deleteCamera(id)
    {
        $.ajax({
            url : _ctx + "/zhdg/cameraManage/delete",
            type : "post",
            data : {
                id : id,
            },
            success : function(result) {
                if (result.status == '1') {
                    swal({
                        title : result.msg,// 展示的标题
                        text : "", // 内容
                        type : "success",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                } else {
                    swal(result.msg, "", "error");
                }
                //重新检索并加载数据
                selectCameraList();
            },
            error : function(result) {
                swal(result.msg, "", "error");
            }
        });
    }
    //视频预览
    $('#preViewModel').on('show.bs.modal', function (event) {
        $(this).css('display', 'block');
        var modalHeight = $(window).height() / 2 - $('#preViewModel .modal-content').height() / 2;
        var modalWidth = $(window).width() / 2 - $('#preViewModel .modal-content').width()/2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight,
            'margin-left': modalWidth
        });
        //模态拖动
        $(this).draggable({
            handle: ".modal-header"     	// 只能点击头部拖动
        });
        $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
        var cameraId = $(event.relatedTarget).data("id");
        if(cameraId){
            //视频预览
            preView();
        }
    });
    //拉取流地址
    function preView() {
        var rowData=zhdg_Camera_CameraManage_Page.get_curRow();
        var data=rowData.row.data;
        $.ajax({
            url: _ctx + '/zhdg/cameraManage/preView',
            dataType: 'json',
            type: 'post',
            data: {
                cameraName: data.cameraName,
                cameraNum: data.cameraNum,
                cameraUser: data.cameraUser,
                cameraPassword: data.cameraPassword,
                cameraIp: data.cameraIp,
            },
            success: function (result) {
                if (result.status == 1) {
                    playVideo(result.data);
                }
                else {
                    swal(result.msg, "", "error");
                }
            },
            error: function (result) {
                swal(result.msg, "", "error");
            }
        })
    }
    //播放视频流
    function playVideo (url) {
        if (flvjs.isSupported()) {
            let video = document.getElementById('videoElement');
            if (video) {
                //创建播放器实例
                player = flvjs.createPlayer({
                    type: 'flv',
                    isLive: true,
                    url: url,
                    enableStashBuffer: false
                });
                player.attachMediaElement(video);
                try {
                    player.load();
                    //player.play();
                } catch (error) {
                    console.log(error)
                }
            }
        }
    }
    //关闭模态框销毁播放器实例
    $("#preViewModel").on('hidden.bs.modal', function(event) {
        destroy ();
    });
    function destroy () {
        if (player) {
            player.unload();
            player.destroy();
            player = null
        }
    }
    return {
        pageInit : function(){
            //查询摄像头列表
            selectCameraList();
        }
	 }
 })(jQuery, window, document);
zhdg_Camera_CameraManage.pageInit();
 </script>
