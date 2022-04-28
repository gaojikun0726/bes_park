<div class="attrInfo">
	<div class="frist_attr">
		<div class="has-success">
			<label class="col-sm-2 control-label" style="font-size: 15px;">当前位置</label>
			<label style="font-size: 15px;" id="f_allpath">调试</label>
		</div>
		<form role="form" id="sbdy_debugDOform" name="sbdy_debugDOform" class="form-horizontal">
		<div class="vertical-timeline-block eqTreeAttrLineWidth">
			<div class="has-success">
				<label class="col-sm-2 control-label">系统名称</label>
				<div class="col-sm-4">
					<input type="hidden" id="hidden_f_guid" value="${returnObject.data.F_GUID}"> 
					<input type="hidden" id="hidden_f_sbid" value="${returnObject.data.F_SBID}">
					<input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME}" type="text"
						class="form-control" readonly="readonly"
						onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
				</div>
			</div>

		</div>
		<div class="vertical-timeline-block eqTreeAttrLineWidth">
			<div class="has-success">
				<label class="col-sm-2 control-label">设置</label>
				<div class="col-sm-4">
					<input type="hidden" id="f_accuracy_selected"  value="${returnObject.data.F_INIT_VAL}" class="form-control">
					<select id="f_init_val" class="selector" style="width: 13.1em;height: 2em;border:1px solid green;" 
						onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
						<option value="255">制冷</option>
						<option value="0">制热</option>
					</select>
				</div>
			</div>
		</div>
		<div class="vertical-timeline-block eqTreeAttrLineWidth">
			<div class="has-success">
				<label class="col-sm-2 control-label">工作模式</label> <input
					type="hidden" id="hidden_radio_f_enabled"
					value="${returnObject.data.F_WORK_MODE}">
				<div class="col-sm-4">
					<input type="radio" name="f_work_mode" value="0"
						onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()" />自动
					<input type="radio" name="f_work_mode" value="1"
						onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()" />手动
				</div>
			</div>
		</div>
		</form>
		<br /> <br />
		<div style="margin-left: 233px; padding-right: 15px;">
			<span>
				<button id="synDataBtn" class="btn sbtreeNodeBtn"
					onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfoAo.debugDOInfo();">执行</button>
				<button id="dataContrast" class="btn sbtreeNodeBtn"
					data-toggle="modal" data-target="#compareModule">取消</button>
			</span>
		</div>
	</div>
	<input type="hidden" id="sel_f_type">
	<!-- 定义一个隐藏节点类型 -->
	<input type="hidden" id="sel_img_url">
	<!-- 定义一个隐藏图片url -->
	<input type="hidden" id="ch_f_sys_name" /> <input type="hidden"
		id="ch_f_nick_name" /> <input type="hidden" id="ch_f_allpath" /> <input
		type="hidden" id="ch_f_type" /> <input type="hidden"
		id="ch_f_psys_name" />
	<!-- 动态添加节点属性信息 -->
	<input type="hidden" id="moduleType" /> <input type="hidden"
		id="moduleAmount" /> <input type="hidden" id="nodeTypes" />
</div>
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfoAo = (function($,window, document, undefined) {
	var _ctx = '${ctx}';
	$("#sbdy_debugDOform").validate({
	});
	//工作模式单选按钮状态设置
	<#if returnObject.data.F_WORK_MODE == "1">//工作模式(自动【0】 手动【1】)
		$("input[name='f_work_mode']:eq(1)").attr("checked", 'checked');
	<#else>
		$("input[name='f_work_mode']:eq(0)").attr("checked", 'checked');
	</#if>
	//开关状态设置
	$("#f_init_val").val($("#f_accuracy_selected").val());

	return {
		debugDOInfo : function(){
			$.ajax({
				url : _ctx + "/api/debugPointInfo",
				type : "post",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				data : JSON.stringify({
					f_sys_name : $("#f_sys_name").val(),
					f_guid : $("#hidden_f_guid").val(),
					f_id : $("#hidden_f_sbid").val(),
					f_work_mode : $('input[name="f_work_mode"]:checked').val(),
					f_init_val : $("#f_init_val").val(),
					tabname : "bes_sbpz_struct",
					nodeLevel : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数
					f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
				}),
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					if(result.status == '0'){
						swal({
				        	title : result.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "error",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000,
				   		});
					}else{
					swal({
			        	title : result.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		}); 
					}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
		}
	}

})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script>
<!-- 引入共通Js -->