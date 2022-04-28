<#--
  描述:设备维护
  作者:YangChao
  时间:2020-08-27 18:36:45
-->
<#--<style type="text/css">-->
    <#--.weather_unit{-->
        <#--display: inline-block;-->
    <#--}-->
<#--</style>-->

<div class="information_size">
    <div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备维护列表>>>
    </span>
    <#-- 增加按钮 -->
    <a id="Point_add" data-toggle="modal"  href="#PointformModel"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
    </a>
    <#-- 搜索框 -->
        <div class="zc_search find">
            <input type="text" class="find-style"  id="Point_search" placeholder="编号、名称">
            <button id="queryPointBtn" onclick="zhdg_Point_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
    <div class="ibox" id="Point_ibox" style="height:91%"/>
</div>

<!---添加||修改 模态框----->
<div class="modal fade" id="PointformModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon" id="PointformModelIcon">&nbsp; 添加设备</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="PointModuleForm" class="form-horizontal">
				<#--添加信息项-->
                <#--新增修改标识符-->
                <input type="hidden" name="operateType" id="PointOperateType">
                <input type="hidden" name="fGuid" id="PointfGuid">
				<div class="form-group">
					<label class="col-sm-3 control-label">唯一编码</label>
					<div class="col-sm-8">
						<input type="text" id="PointfCode" name="fCode" placeholder="唯一编码" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">设备名称</label>
					<div class="col-sm-8">
						<input type="text" id="PointfPointName" name="fPointName" placeholder="设备名称" required  class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">显示屏IP</label>
					<div class="col-sm-8">
						<input type="text" id="PointfPointIp" name="fPointIp" placeholder="显示屏IP" required  class="form-control">
					</div>
				</div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">显示屏编码</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="screenCode" name="screenCode" placeholder="大屏编码" required  class="form-control">-->
                                <select id="screenCode" name="screenCode" class="input-sm form-control inline"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">PDU IP</label>
                        <div class="col-sm-8">
                            <input type="text" id="pduIp" name="pduIp" placeholder="PDU IP" required  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">音柱IP</label>
                        <div class="col-sm-8">
                            <input type="text" id="soundIp" name="soundIp" placeholder="音柱IP" required  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">动环主板IP</label>
                        <div class="col-sm-8">
                            <input type="text" id="mainboardIp" name="mainboardIp" placeholder="动环主板IP" required  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">摄像头IP</label>
                        <div class="col-sm-8">
                            <input type="text" id="cameraIp" name="cameraIp" placeholder="摄像头IP" required  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">一键报警IP</label>
                        <div class="col-sm-8">
                            <input type="text" id="alarmIp" name="alarmIp" placeholder="一键报警IP" required  class="form-control">
                        </div>
                    </div>
				<#--<div class="form-group">-->
					<#--<label class="col-sm-3 control-label">经度</label>-->
					<#--<div class="col-sm-8">-->
						<#--<input type="text" id="PointfLongitude" name="fLongitude" placeholder="经度" required  class="form-control">-->
					<#--</div>-->
				<#--</div>-->
				<#--<div class="form-group">-->
					<#--<label class="col-sm-3 control-label">纬度</label>-->
					<#--<div class="col-sm-8">-->
						<#--<input type="text" id="PointfLatitude" name="fLatitude" placeholder="纬度" required  class="form-control">-->
					<#--</div>-->
				<#--</div>-->
				<div class="form-group">
					<label class="col-sm-3 control-label">所属区域</label>
					<div class="col-sm-8">
                        <select id="PointfRegionId" name="fRegionId" class="input-sm form-control inline"></select>
                    </div>
				</div>
				<#--<div class="form-group">-->
					<#--<label class="col-sm-3 control-label">详细地址</label>-->
					<#--<div class="col-sm-8">-->
						<#--<input type="text" id="PointfRemark" name="fRemark" placeholder="详细地址" required  class="form-control">-->
					<#--</div>-->
				<#--</div>-->
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

<!---详情 模态框----->
<div class="modal fade" id="realPointformXqModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" keyboard="true">
    <div class="modal-dialog" style="height: 62vh;">
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class='fa fa-info-circle'></i>&nbsp; 气象数据</h4>
            </div>
            <div class="modal-body">
                <#--&lt;#&ndash;璐总提议&ndash;&gt;-->
                <#--<ol class="breadcrumb" style="background-color: inherit;">-->
                    <#--<li class="active"><a href="javascript:void(0);" idnm="realPointXqJbxx">气象数据</a></li>-->
                <#--</ol>-->
                <div>
                    <ul id="realPointXqJbxx" style="display: block;height: 47vh;border: 1px solid #366c90;margin-top: 2vh;padding: 1vh;border-radius: 7px;" >
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">气象编码：</div>
                            <div style="display: inline-block;" id="weather_code"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">风向：</div>
                            <div style="display: inline-block;" id="realPointXq_windDirection"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">风速：</div>
                            <div style="display: inline-block;" id="realPointXq_windSpeed"></div>
                            <#--<div class="weather_unit">m/s</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">室外温度：</div>
                            <div style="display: inline-block;" id="realPointXq_outdoorTemperature"></div>
                            <#--<div class="weather_unit">℃</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">室外湿度：</div>
                            <div style="display: inline-block;" id="realPointXq_outdoorHumidity"></div>
                            <#--<div class="weather_unit">%</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">气压：</div>
                            <div style="display: inline-block;" id="realPointXq_airOressure"></div>
                            <#--<div class="weather_unit">hPa</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">雨量（10分钟雨量）：</div>
                            <div style="display: inline-block;" id="realPointXq_rainfall"></div>
                            <#--<div class="weather_unit">mm</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">PM2.5：</div>
                            <div style="display: inline-block;" id="realPointXq_pm25"></div>
                            <#--<div class="weather_unit">μg/m³</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">PM10：</div>
                            <div style="display: inline-block;" id="realPointXq_pm10"></div>
                            <#--<div class="weather_unit">μg/m³</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">噪声：</div>
                            <div style="display: inline-block;" id="realPointXq_noise"></div>
                            <#--<div class="weather_unit">dB</div>-->
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">环境光照：</div>
                            <div style="display: inline-block;" id="realPointXq_illumination"></div>
                            <#--<div class="weather_unit">Klux</div>-->
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!---设置 模态框----->
<div class="modal fade" id="realPointformSzModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="height: 60vh;">
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class='fa fa-cog' ></i>&nbsp; 设备控制</h4>
            </div>
            <div class="modal-body">
                <#--设备编码id-->
                <input type="hidden" id="realPoint_fCode">
                <div>
                    <ul id="realPointSzJbxx" style="display: block;height: 46vh;border: 1px solid #366c90;margin-top: 2vh;padding: 1vh;border-radius: 7px;">
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">灯1亮度控制：<span id="realPointSzJbxx_luminaireBrightness"></span></div>
                                <input type="text" id="realPointSzJbxx_luminaireBrightness_controller" style="width: 90px;margin-left: 16px;border: 1px solid #c7c7c7;" placeholder="0~100">
                                <button style="display: inline-block;"  type="button" class="btn btn-primary btn-xs" onclick="zhdg_Point_listModuleClosure.sendClick(this,'kzxf','75','','')">下发</button>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">显示屏开关控制：<span id="realPointSzJbxx_relayControl"></span></div>
                                <button style="display: inline-block;"  type="button" class="btn btn-primary btn-xs" onclick="zhdg_Point_listModuleClosure.sendClick(this,'xsp','73','0','1')">开机</button>
                                <button style="display: inline-block;"  type="button" class="btn btn-primary btn-xs" onclick="zhdg_Point_listModuleClosure.sendClick(this,'xsp','73','0','0')">关机</button>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

 <script type="text/javascript">
;var zhdg_Point_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";
var timer = "";

    // 打开模态框-回显
    $('#PointformModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#PointformModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});

        // 标识符判断添加
        var id = $(event.relatedTarget).data("id");
        if(id=="undefined"||id==null||id==""){
            $("#PointOperateType").val("C")
            $("#PointformModelIcon").removeClass("editIcon").addClass("addIcon").html("&nbsp; 添加设备信息");
        }else{
            $("#PointOperateType").val("U")
            $("#PointformModelIcon").removeClass("addIcon").addClass("editIcon").html("&nbsp; 修改设备信息");
            // U修改添加回显
            editShow(id);
        }
    });
    // 编辑回显
    function editShow(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/point/getSearchById",
            type : "post",
            data:{"id" : id},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#PointfGuid").val(obj.fGuid);
                $("#PointfCode").val(obj.fCode);
                $("#PointfPointName").val(obj.fPointName);
                $("#PointfPointIp").val(obj.fPointIp);
                $("#screenCode").val(obj.screenCode);
                $("#pduIp").val(obj.pduIp);
                $("#soundIp").val(obj.soundIp);
                $("#mainboardIp").val(obj.mainboardIp);
                $("#cameraIp").val(obj.cameraIp);
                $("#alarmIp").val(obj.alarmIp);
                $("#PointfLongitude").val(obj.fLongitude);
                $("#PointfLatitude").val(obj.fLatitude);
                $("#PointfRegionId").val(obj.fRegionId);
                $("#PointfRemark").val(obj.fRemark);
                $("#PointfIsUse").val(obj.fIsUse);
                $("#PointfStatus").val(obj.fStatus);
                $("#PointfCrdate").val(obj.fCrdate);
                $("#PointfChdate").val(obj.fChdate);
            }
        });
    }

    //关闭模态框清空表单值
    $("#PointformModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        PointValidators.resetForm();
    });

    //表单验证
    var PointValidators = $("#PointModuleForm").validate({
        rules : {
            fCode:{
                required: true
            },
            fPointName:{
                required: true
            },
            fPointIp:{
                required: true,
                isValidIP:true
            },
            screenCode:{
                required: true
            },
            pduIp:{
                required: true,
                isValidIP:true
            },
            soundIp:{
                required: true,
                isValidIP:true
            },
            mainboardIp:{
                required: true,
                isValidIP:true
            },
            cameraIp:{
                required: true,
                isValidIP:true
            },
            alarmIp:{
                required: true,
                isValidIP:true
            },
            // fLongitude:{
            //     required: true,
            //     isNormalLongtitude:true
            // },
            // fLatitude:{
            //     required: true,
            //     isNormalLatitude:true
            // },
            fRegionId:{
                required: true
            },
            // fRemark:{
            //     required: true
            // },
            fIsUse:{
                required: true
            },
            fStatus:{
                required: true
            },
            fCrdate:{
                required: true
            },
            fChdate:{
                required: true
            }
        },
        messages : {
            fCode:{
                required: '唯一编码--12位-由硬件提供不能为空'
            },
            fPointName:{
                required: '设备名称不能为空'
            },
            fPointIp:{
                required: '显示屏IP不能为空'
            },
            screenCode:{
                required: '显示屏编码不能为空'
            },
            pduIp:{
                required: 'PDU IP不能为空'
            },
            soundIp:{
                required: "音柱IP不能为空"
            },
            mainboardIp:{
                required: "动环主板IP不能为空"
            },
            cameraIp:{
                required: "摄像头IP不能为空"
            },
            alarmIp:{
                required: "一键报警IP不能为空"
            },
            // fLongitude:{
            //     required: '经度不能为空'
            // },
            // fLatitude:{
            //     required: '纬度不能为空'
            // },
            fRegionId:{
                required: '所属区域不能为空'
            },
            // fRemark:{
            //     required: '详细地址不能为空'
            // },
            fIsUse:{
                required: '是否启用1是0否不能为空'
            },
            fStatus:{
                required: '0正常、1警告、2掉线不能为空'
            },
            fCrdate:{
                required: '添加时间不能为空'
            },
            fChdate:{
                required: '修改时间不能为空'
            }
        },
        submitHandler: function(form) {
            PointModulePut(form);
        }
    });
    // form提交
    function PointModulePut(form){
	 	var path = $("#PointOperateType").val()=="C"?"add":"update";
	 	//校验各IP字段不可相同
        var pointIp = $("#PointfPointIp").val();
        var pduIp = $("#pduIp").val();
        var soundIp = $("#soundIp").val();
        var mainboardIp = $("#mainboardIp").val();
        var cameraIp = $("#cameraIp").val();
        var alarmIp = $("#alarmIp").val();

        if(pointIp === pduIp || pointIp === soundIp || pointIp === mainboardIp || pointIp === cameraIp || pointIp === alarmIp
        || pduIp === soundIp || pduIp === mainboardIp || pduIp === cameraIp || pduIp === alarmIp
        || soundIp === mainboardIp || soundIp === cameraIp || soundIp === alarmIp
        || mainboardIp === cameraIp || mainboardIp === alarmIp
        || cameraIp === alarmIp){
            swal( {
                title: "IP地址不可相同",
                text: "",
                type: "warning",
                showCloseButton:false,
                allowOutsideClick:false,
                showConfirmButton: false,
                timer:1000
            });
            return;
        }

		$.issp_ajax({
			url : _ctx + "/zhdg/point/"+path,
			type : "post",
			data:$(form).serialize(),
			success : function(data) {
			    if(data.status === "1"){
                    resSwal(data.status,data.msg);
                }
                //data.status === "0"时，封装的ajax会弹出提示
                // else{
                //     swal( {
                //         title: data.msg,
                //         text: "",
                //         type: "warning",
                //         showCloseButton:false,
                //         allowOutsideClick:false,
                //         showConfirmButton: false,
                //         timer:1000
                //     });
                // }

			},
			error : function(data) {
                resSwal(data.status,data.msg);
			}
        });
	}

    //删除点击事件
    $(document).on('click','#Point_iboxTable button.delete',function() {
        var id = $(this).data("id");
        swal({
            title : "您确定要删除吗?",
            text : "信息删除后将不可恢复!",
            type : "warning",
            showCancelButton : true,
            confirmButtonColor : "#1ab394",
            confirmButtonText : "确定",
            closeOnConfirm : false
        },function(){deleteSth(id)});
    });

    // 删除执行事件
    function deleteSth(id){
        $.issp_ajax({
            url : _ctx + "/zhdg/point/delete",
            type : "post",
            data : {"id" : id},
            success : function(data) {
                resSwal(data.status,data.msg);
            },
            error : function(data) {
                resSwal(data.status,data.msg);
            }
        });
    }


    // 打开详情模态框-回显数据
    $('#realPointformXqModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#realPointformXqModel .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
        var fCode = $(event.relatedTarget).data("id");
        detailShow(fCode);

        //定时器，每隔5秒执行一次加载数据
        timer = setInterval(function(){
            detailShow(fCode);
        },5000);
    });

    /** 详情回显*/
    function detailShow(fCode){
        /**设备在线实时数据*/
        $.issp_ajax({
            url : _ctx + "/zhdg/point/getPointRealData",
            type : "post",
            data:{"deviceId" : fCode},
            success : function(data) {
                $("#weather_code").text(fCode);

                if(data && data.map){
                    var map = data.map;
                    //风向解析【暂定】：共16份，东北、东南、西南、西北,各3份，东南西北风各一份，22.5°为一份
                    //比如东北风的3份，实际包括北东北、东北、东东北3个风向
                    var direction = Number(map.windDirection);
                    var windText = "";
                    if(direction > 11.25 && direction <= 78.75){
                        windText = "东北风";
                    } else if(direction > 78.75){
                        windText = "东风";
                    } else if(direction > 101.25 && direction <= 168.75){
                        windText = "东南风";
                    }else if(direction > 168.75){
                        windText = "南风";
                    }else if(direction > 191.25 && direction <= 258.75){
                        windText = "西南风";
                    }else if(direction > 258.75){
                        windText = "西风";
                    }else if(direction > 281.25 && direction <= 348.75){
                        windText = "西北风";
                    }else{
                        windText = "北风";
                    }
                    windText = windText + "（" + direction + "°）";
                    $("#realPointXq_windDirection").text(windText);
                    $("#realPointXq_windSpeed").text(map.windSpeed+"m/s");
                    $("#realPointXq_outdoorTemperature").text((map.outdoorTemperature*1).toFixed(2) +"℃");
                    $("#realPointXq_outdoorHumidity").text(map.outdoorHumidity +"%");
                    $("#realPointXq_airOressure").text(map.airOressure+"hPa");
                    $("#realPointXq_rainfall").text(map.rainfall+"mm");
                    $("#realPointXq_pm25").text(map.pm25+"ppm");
                    //μg/m³
                    $("#realPointXq_pm10").text(map.pm10+"ppm");
                    $("#realPointXq_noise").text(map.noise+"dB");
                    $("#realPointXq_illumination").text(map.illumination+"Klux");
                }else{
                    //没有数据
                    //清空详情弹窗数据
                    clearRealDataModel();
                }
            }
        });
    }

    // 关闭详情模态框
    $("#realPointformXqModel").on('hidden.bs.modal', function (event) {
        $("#weather_code").text("");

        //清空详情弹窗数据
        clearRealDataModel();

        //关闭详情弹窗，清除定时器
        clearInterval(timer);
    });

    //清空详情弹窗数据
    function clearRealDataModel(){
        $("#realPointXq_windDirection").text("");
        $("#realPointXq_windSpeed").text("");
        $("#realPointXq_outdoorTemperature").text("");
        $("#realPointXq_outdoorHumidity").text("");
        $("#realPointXq_airOressure").text("");
        $("#realPointXq_rainfall").text("");
        $("#realPointXq_pm25").text("");
        $("#realPointXq_pm10").text("");
        $("#realPointXq_noise").text("");
        $("#realPointXq_illumination").text("");
    }

    // 打开设置模态框
    $('#realPointformSzModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#realPointformSzModel .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
        var fCode = $(event.relatedTarget).data("id");
        /** 将设备编码保存到dom中 */
        $("#realPoint_fCode").val(fCode);
        /** 将控制按钮得状态值回显 */
        SzModelSx();
    });

    // 控制刷新
    function SzModelSx(){
        /**设备在线实时数据*/
        $.issp_ajax({
            url : _ctx + "/zhdg/point/getPointRealData",
            type : "post",
            data:{"deviceId" :  $("#realPoint_fCode").val()},
            success : function(data) {
                var map = data.map;
                $("#realPointSzJbxx_luminaireBrightness").text(map.luminaireBrightness);// 灯具1亮度
                if(map.relayControl1 == '0'){
                    $("#realPointSzJbxx_relayControl").text("关机");// 继电器状态
                }else{
                    $("#realPointSzJbxx_relayControl").text("开机");// 继电器状态
                }

            }
        });
    }

    //关闭模态框清空表单值
    $("#realPointformSzModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
    });

    // swal 返回集合
    function resSwal(status,msg){
        $("#PointformModel").modal("hide");// 关闭模态框
        if (status == '1') {
            swal( {
                title: msg,
                text: "",
                type: "success",
                showCloseButton:false,
                allowOutsideClick:false,
                showConfirmButton: false,
                timer:1000
            });
            //重新加载列表及分页
            zhdg_Point_listModuleClosure.Search()
        } else {
            swal(msg,"","error");
        }
    }

	// 数据查询
	function DataSearch(){
        $.issp_ajax({
			url : _ctx + "/zhdg/point/getSearch",
			type : "post",
			data:({keywords: $("#Point_search").val()}),
			success : function(data) {
                $("#Point_ibox").html(data);
			},
			error : function(data) {

			}
        });
	}
	// 下拉列表查询
    function RegionSearch() {
        $.issp_ajax({
            url : _ctx + "/zhdg/point/getRegionSearch",
            type : "post",
            data:({}),
            success : function(data) {
                var list = data.list;
                var opt="";
                list.forEach(function(e){
                    opt += "<option value='"+e.fid+"'>"+e.fregionName+"</option>";
                })
                $("#PointfRegionId").empty().append(opt);
            }
        });
    }

    /**
     * 查询灯杆的设备序列号
     **/
    function LightPoleSerialSearch(){
        $.issp_ajax({
            url : _ctx + "/assetInfo/getLightPoleSerialList",
            type : "post",
            success : function(data) {
                var list = data;
                var opt="";
                list.forEach(function(e){
                    opt += "<option value='"+e+"'>"+e+"</option>";
                });
                $("#screenCode").empty().append(opt);
            }
        });
    }

    return {
        sendClick:function(e, type, orderCode, digits, datas) {
            var fCode = $("#realPoint_fCode").val();
            if(type == 'xsp'){
            }else{
                datas = $("#realPointSzJbxx_luminaireBrightness_controller").val();
            }
            /** 下发指令 */
            $.issp_ajax({
                url : _ctx + "/zhdg/point/sendMsg",
                type : "post",
                data:{
                    "DeviceId" : fCode,
                    "datas" : datas,
                    "orderCode" : orderCode,
                    "digits" : digits
                },
                success : function(data) {
                    swal( {
                        title: data.msg,
                        text: "",
                        type: "success",
                        showCloseButton:false,
                        allowOutsideClick:false,
                        showConfirmButton: false,
                        timer:1000
                    });
                    // 等1.5秒刷新
                    setTimeout(function () {
                        SzModelSx();
                    },2000)
                },
                error: function(data){
                    swal( {
                        title: data.msg,
                        text: "",
                        type: "warning",
                        showCloseButton:false,
                        allowOutsideClick:false,
                        showConfirmButton: false,
                        timer:1000
                    });
                }
            });
        },
        Search:function() {
            DataSearch();
            RegionSearch();
            LightPoleSerialSearch();
        },

        pageInit : function(){
            zhdg_Point_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_Point_listModuleClosure.pageInit();
 </script>
<style type="text/css">
    #Point_iboxTable .btn-group-sm>.btn, .btn-sm{
        padding: 2px 10px;
    }
</style>
