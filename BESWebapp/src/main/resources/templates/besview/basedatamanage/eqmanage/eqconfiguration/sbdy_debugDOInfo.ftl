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
					<input type="hidden" id="hidden_f_struct_id" value="${returnObject.data.F_STRUCT_ID}">
					<input type="hidden" id="hidden_f_guid" value="${returnObject.data.F_GUID}"> 
					<input type="hidden" id="hidden_f_sbid" value="${returnObject.data.F_SBID}">
					<input type="hidden" id="f_nick_name"  value="${returnObject.data.F_NICK_NAME}" >
					<input type="hidden" id="f_sys_name_hidden"  value="${returnObject.data.F_SYS_NAME}" >
					<input type="hidden" id="hidden_radio_f_enabled"  value="${returnObject.data.F_WORK_MODE}" >
					<input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}" type="text"
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
						<option value="255">开机</option>
						<option value="0">关机</option>
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
					onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.debugDOInfo();">执行</button>
				<button id="dataContrast" class="btn sbtreeNodeBtn"
						 onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.loadParentPage();">返回</button>
				<!-- Start add by yangjx at 20191025 for 设置配置不同参数，显示不同文字-->
				<button id="debugDOInfoEquipmentConfigSetting" class="btn sbtreeNodeBtn"
						onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setEquipmentConfig()">
					设置配置
				</button>
			</span>
		</div>
		<!-- TEST ADD EDIT BEGIN-->
		<div id="sbdy_debugDOinfoConfigSettingTargetMom" style="display:none;">
			<div id="0" class="form-group remainDiv">
				<div class="col-sm-10 remainDiv">
					<input type="text" value="提示" readonly class="form-control valid" style="text-align:center; width:200px; float:left;">
					<input type="text" value="数值" readonly class="form-control valid" style="text-align:center; width:200px; float:left;margin-left:10px;">
					<i id="add" class="fa fa-plus btn btn-default" style="float:left; margin-left:10px;margin-top:5px;"></i>
					<i id="pop" class="fa fa-minus btn btn-default" style="margin-left:10px;margin-top:5px;"></i>
				</div>
			</div>
			<div class="remainDiv">
				<input id="equipmentConfigSubmit" type="button" value="确定" class="btn btn-default" style="margin-top:5px;"
					   onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.equipmentConfigSubmit();"/>
				<input type="button" value="取消" class="btn btn-default" style="margin-top:5px;margin-right:5px;"
					   onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.closeBoxLayer();"/>
			</div>
		</div>
		<script type="text/javascript">
			var num =0;
			var data1 = {};
			$("i[id='add']").click(add);
			$("i[id='pop']").click(pop);
			var momEle = document.getElementById("sbdy_debugDOinfoConfigSettingTargetMom");
			function add() {
				num++;
				console.log(num);
				var divEle = document.createElement("div");
				divEle.setAttribute("class", "form-group");
				divEle.setAttribute("id", num);
				var inner = `<div class="col-sm-10" style="margin-top:10px;"><input type="text" value="" id=`+"key"+num +` class="form-control" style="text-align:center;width:200px; float:left;">
							  <input type="number" value="" id=`+"val"+num +` class="form-control" style="text-align:center;width:200px;margin-left:210px;"</div>`;
				divEle.innerHTML = inner;
				momEle.appendChild(divEle);
			}
			function pop() {
				if (num != 0) {
					var current_dom = document.getElementById(num);//获取最下面的一行数据
					momEle.removeChild(current_dom);//将最下面的一行数据删除
					num--
				} else {  //若仅有一行，还要继续点击“-”号进行删除操作时，报错提示
					layer.alert("已无数据删除");
				}
			}
		</script>
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
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
	var layer;
	var indexFont;
	var _ctx = '${ctx}';

	var variableUrl = "";//取消跳转url tianjiangwei
	var nodeTabName = ""//节点table
	/* Start add by yangjx at 20191026 for 进入页面后，查询节点配置设置表。动态更新下拉框选项 */
	$(function(){//进入页面后，查询节点
		checkAndUpdateOption();
	})
	function checkAndUpdateOption(){

		$.ajax({
			url : _ctx +"/view/basedatamanage/eqmanage/selectNodesConfigSetting",
			type : "post",
			data : {
				f_sys_name :$("#f_sys_name_hidden").val()
			},
			success : function(result){

				var resultList = result.list;
				if (typeof resultList != 'undefined') {
					var dataNum = resultList.length;
					if(dataNum>0){//如果对do节点进行了设置配置，进行处理
						$("#f_init_val").empty();//首先清空下拉列表中页面默认的选项
						for(var i=0;i<dataNum;i++){//将下拉框设置为配置的数据
							var selectSettingValueLeaderConfig = document.getElementById("f_init_val");
							var configSetting=document.createElement("option");
							var text = result.list[i].F_DESC;
							configSetting.text = result.list[i].F_DESC;
							configSetting.value=result.list[i].F_VALUE;
							selectSettingValueLeaderConfig.appendChild(configSetting);
						}
					}
				}

			},
			error : function(result){
				swal(result.msg, "", "error");
			}
		});
	}
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
	layui.use(['layer'],function(){
		layer=layui.layer;
	});

	/* Start add for 设备配置弹出框 at 20191024*/
	function equipmentConfigSet(){
		indexFont=layer.open({
			type: 1,
			area: ['46vw', '68vh'],
			title: '设置配置',
			content : $('#sbdy_debugDOinfoConfigSettingTargetMom'),
			success:function(){
                var $mom = $("#sbdy_debugDOinfoConfigSettingTargetMom");
                $mom.find("div:not('.remainDiv')").remove();
			    queryConfigData();
			}
		});
	}
	/**
	 * 查询配置数据用于回显
	 * **/
	function queryConfigData(){
        num = 0;
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/selectNodesConfigSetting",
            type : "post",
            data : {
                f_sys_name : $("#f_sys_name_hidden").val(),//DO节点系统名称
            },
            success : function(result) {
				if(result && result.list){
				    var list = result.list;
                    var momEle = document.getElementById("sbdy_debugDOinfoConfigSettingTargetMom");
				    for(var i = 0; i < list.length; i++){
				        num++;
                        var divEle = document.createElement("div");
                        divEle.setAttribute("class", "form-group");
                        divEle.setAttribute("id", num);
                        var inner = '<div class="col-sm-10" style="margin-top:10px;">'+
							'<input type="text" value="'+list[i].F_DESC+'" id="key'+num +'" class="form-control" style="text-align:center;width:200px; float:left;" >'+
								'<input type="number" value="'+list[i].F_VALUE+'" id='+"val"+num + ' class="form-control" style="text-align:center;width:200px;margin-left:210px;"</div>';
                        divEle.innerHTML = inner;
                        momEle.appendChild(divEle);
					}

				}
            },
            error : function(result) {
                swal(result.msg, "", "error");
            },
        });
	}

	/* End add for 设备配置弹出框 at 20191024*/

	/*弹出框的关闭按钮  */
	function closeBoxLayer(){
		layer.close(indexFont);
		indexFont = 0;
	}

	return {
		closeBoxLayer: function(){
			closeBoxLayer();
		},
		equipmentConfigSubmit: function(){//当配置好提交时，首先校验数据。至少要有一条。完成验证后，将页面"设置"的下拉框更新为配置数据
			//Step1.取得数据，验证数据后，存入map；
			var map = {};//页面所用 // eg：key1:开机,val1:255,key2:关机,val2:0
			var configMap = {};//传到前台，eg： 开机:255,关机=0；
            for(var i=1;i<=num;i++){//获取每一行提示和数值的值，传入map
                var keyValue = $("#key"+i).val();
                var valValue = $("#val"+i).val();
                if(keyValue==""||keyValue==null||valValue==""||valValue==null){//若取得的数据为空。报错提示
                    layer.alert("数据不能为空值");
                    return;
                }
                map["key"+i]= keyValue;
                map["val"+i]= valValue;
                configMap[keyValue]=valValue;//传至后台的map的赋值
            }
			//Step2. 根据数据，将设置下拉框动态更新设置
			$("#f_init_val").empty();//首先清空下拉列表中的选项
			var mapLength = (Object.keys(map).length)/2; //取得map的长度的一半(reason:key和val 都占一份)
			for(var j=1;j<=mapLength;j++){
				var selectSettingValueLeader = document.getElementById("f_init_val");
				console.log(selectSettingValueLeader);
				var configSettingObj=document.createElement("option");
				var keyJ = map["key"+j];
				console.log(keyJ);
				configSettingObj.text = keyJ;
				var valJ = map["val"+j];
				console.log(valJ);
				configSettingObj.value=valJ;
				selectSettingValueLeader.appendChild(configSettingObj);
				console.log("ok");
			}
			//Step3. TODO 将数据插入节点配置设置表 bes_node_config_setting   step3 暂时注释掉
			//先查询bes_node_config_setting表中有无此系统名称对应的数据，若无，则插入，若有，则更改
			//a.查询表中有无此系统对应的数据
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name_hidden").val(),//DO节点系统名称
					tableName : "BES_NODE_CONFIG_SETTING",//DO表名
				},
				success : function(result) {

					basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.fill_localDO11(function (data) {
					if(result>0){//如果返回数据，表名表中已有此条系统名称相关的数据，则将节点配置设置表的数据更新(删除后插入)；
						$.ajax({
							url : _ctx+"/view/basedatamanage/eqmanage/updateNodesConfigSetting",
							type : "post",
							contentType : "application/json; charset=utf-8",
							dataType : "json",
							data : JSON.stringify({
								/*f_sys_name : $("#f_sys_name").val(),*/
								f_sys_name : data.data.F_SYS_NAME,
								//map : configMap,
								map : map,
								config_num : num
							}),
							/* data : {
								f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
								map :map//map传值(提示名称，提示对应值)
							}, */
							success : function (result){
                                if(result>0){
                                    layer.msg("设置成功，共设置:"+result+"条数据");
                                }else if (result == -1)
                                {
                                    $('#f_init_val').html('<option value="255">开机</option>'+
                                            '<option value="0">关机</option>');
                                    layer.msg("删除成功");
                                }else
                                {
                                    layer.alert("设置失败");
                                }

							},
							error : function (result){
								layer.alert("设置失败");
							}
						});
					}else if(result==0){//若先前没有配置数据，则直接添加至节点配置设置表

							$.ajax({
								url: _ctx + "/view/basedatamanage/eqmanage/insertNodesConfigSetting",
								type: "post",
								contentType: "application/json; charset=utf-8",
								dataType: "json",
								data: JSON.stringify({
									/*f_sys_name: $("#f_sys_name").val(),*/
									f_sys_name : data.data.F_SYS_NAME,
									map: map,
									config_num: num
								}),
								success: function (result) {
									if (result > 0) {
										layer.alert("设置成功，共设置:" + result + "条数据");
									} else {
										layer.alert("设置失败");
									}
								},
								error: function (result) {
									layer.alert("设置失败");
								}
							});

					}
					})
				},
				error : function(result) {
					swal(result.msg, "", "error");
				},
			});
			//Step4. 点击确定验证成功后，自动关闭弹出框
			closeBoxLayer();
		},
		setEquipmentConfig:function(){
			equipmentConfigSet();
		},
		fill_localDO11:function(callback){

			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfo1",
				type : "post",
				data : {
					f_nick_name : $("#f_nick_name").val(),
					f_guid:$("#hidden_f_guid").val(),
					f_node_type: $("#f_node_type").val(),
					//f_id:$("#f_id").val(),
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					callback(result)
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
			/*var aa=result_data.data.F_SYS_NAME;
			return aa;*/

		},
		debugDOInfo : function(){
				$.ajax({
					url : _ctx + "/api/debugPointInfo",
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_put_up_point",
					type : "post",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify({
						f_sys_name 		: $("#f_sys_name").val(),
						f_work_mode 	: $('input[name="f_work_mode"]:checked').val(),
						f_init_val 		: $("#f_init_val").val(),
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(result) {
						if(result.status == '0'){
							swal({
								title : result.msg,// 展示的标题
								text : "",// 内容
								type: "warning",
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
		},

		//取消功能  tianjiangwei
		loadParentPage : function (){
			var nodeType = $("#hidden_f_node_type").val();
			var sysName = $("#f_sys_name").val();
			basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.searchNodeTable(nodeType);//查询跳转所需信息

			$('#sbdyInfo').html("");//清空属性信息
			$("#sbdyInfo").load(_ctx +"/view/basedatamanage/eqmanage/" + variableUrl,
					{
						"f_sys_name" : sysName,
						"nodeTabName" : nodeTabName,
						"f_type":nodeType
					});//跳转所选节点属性页面
		},

		//根据子节点的类型查询对应的名称
		searchNodeTable:function (type){
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/noteNameByNoteType",
				type : "post",
				async:false,
				data : {
					f_type : type
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					
					// variableUrl = result.data[5].f_edit_url.split("/")[5];
					// nodeTabName = result.data[5].f_node_table;
					variableUrl = "sbdy_doInfo";
					nodeTabName = "BES_DIGIT_OUPUT";
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});

		},
	}

})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script>
<!-- 引入共通Js -->