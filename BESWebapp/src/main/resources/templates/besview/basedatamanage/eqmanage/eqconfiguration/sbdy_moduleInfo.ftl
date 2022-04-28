   <div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <form role="form" id="sbdy_Moduleform" name="sbdy_Moduleform" class="form-horizontal">
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">系统名称:</label>
                                <div class="col-sm-4">
									<input  type="hidden" id="f_guid" name="f_guid"  value="${returnObject.data.F_GUID}">
									<input  type="hidden" id="f_sbid" name="f_sbid"  value="${returnObject.data.F_SBID}">
									<input  type="hidden" id="f_struct_id" name="f_struct_id"  value="${returnObject.data.F_STRUCT_ID}">
									<#--Start  add by wanghongjie at 20200116 for 增加设备树的f_id,传到后台查询该条的详细信息-->
									<input  type="hidden" id="f_id" name="f_id"  value="${besSbPzStruct.f_id}">
									<input  type="hidden" id="f_sbid_module" name="f_sbid_module"  value="${besSbPzStruct.f_sbid}">
									<input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                                    <input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}" type="text" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">模块别名:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name" required maxlength="32" value="${returnObject.data.F_NICK_NAME}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>           
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">安装地址:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_azwz"  value="${returnObject.data.F_AZWZ}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">通信地址:</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_addr_selected"  value="${returnObject.data.F_ADDR}" class="form-control">
                                    <span>
                                    <span id="trunkCoupler_F_ADDR"></span>
                                    <span id="branchCoupler_F_ADDR"></span>
                                    <select id="f_addr_group" class="selector" style="width: 12.7em;height: 2em;border:1px solid green;"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                    </span>                              	
                                </div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth"> 
                         	<div class="has-success">
                                <label class="col-sm-2 control-label">模块描述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description"  value="${returnObject.data.F_DESCRIPTION}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">模块型号:</label>
                                <!-- 
                                <div class="col-sm-4">
                                    <input type="text" id="f_type"  value="${returnObject.data.F_TYPE}" class="form-control" onchange="saveBtnIsEffective()">
                                </div>    -->
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_type_selected"  value="${returnObject.data.F_TYPE}" class="form-control">
                                    <select id="f_type_group" class="selector" style="width: 15.5em;height: 2em;border:1px solid green;"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                </div>
                                
                            </div>
                         </div>
                          <div  class="vertical-timeline-block eqTreeAttrLineWidth">                          	
                            <div class="has-success">
                               <label class="col-sm-2 control-label">使能状态:</label>
                               	 <input type="hidden" id="hidden_radio_f_enabled"  value="${returnObject.data.F_ENABLED}">
						      	 <div class="col-sm-4">
						       		 <input type="radio" id="f_enabledy" name="f_enabled" value="1"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						    		 <input type="radio" id="f_enabledn" name="f_enabled" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
						    	</div>
                            </div>
                           </div>
							<div  class="vertical-timeline-block eqTreeAttrLineWidth">
								<div class="has-success">
									<label class="col-sm-2 control-label">状态</label>
									<div class="col-sm-4">
										<input type="checkbox" id="f_module_state" disabled name="f_module_state" value="${returnObject.data.F_MODULE_STATE}"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
										<a id="module_state"></a>
										<#-- <input type="checkbox" id="f_online_state" disabled name="f_online_state" value="${returnObject.data.F_ONLINE_STATE}"
										onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                                         <a id="ammeter_onlinestate"></a>-->
									</div>
								</div>
							</div>
                           </form>
                               <br/> <br/>
                                <div style="margin-left: 25vw;padding-right: 15px;">
                                   <span>
                                    <button id="synDataBtn" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synModule();">同步数据</button>
                                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#compareModule">数据对比</button>
                                    <button id="saveBtn" class="btn sbtreeNodeBtn" type="submit"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                                   </span>
                                </div>
                 </div>
                 <input type="hidden" id="sel_f_type"><!-- 定义一个隐藏节点类型 -->
               <input type="hidden" id="sel_img_url"><!-- 定义一个隐藏图片url -->
              <input type="hidden" id="ch_f_sys_name"/>
              <input type="hidden" id="ch_f_nick_name"/>
              <input type="hidden" id="ch_f_allpath"/>
              <input type="hidden" id="ch_f_type"/>
              <input type="hidden" id="ch_f_psys_name" value="${returnObject.data.F_PSYS_NAME}"/>
              <!-- 动态添加节点属性信息 -->
              <input type="hidden" id="moduleType"/>
              <input type="hidden" id="moduleAmount"/>
              <input type="hidden" id="nodeTypes"/>
              <input type="hidden" id="hidden_id" value="${returnObject.data.F_GUID}">
	          <input type="hidden" id="f_psys_name" value="${besSbPzStruct.f_psys_name}"/>
     </div>
<!----模块信息对比--->
<div class="modal fade" id="compareModule" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机模块信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机模块信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机模块信息</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:450px;">
				<form id="local_moduleInfo" name="local_moduleInfo" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_GUID" name="local_F_GUID" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">名称<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_SYS_NAME" name="local_F_SYS_NAME" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">别名<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_NICK_NAME" name="local_F_NICK_NAME" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">安装位置<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_AZWZ" name="local_F_AZWZ" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">型号<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_TYPE" name="local_F_TYPE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ENABLED" name="local_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_DESCRIPTION" name="local_F_DESCRIPTION" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">通信地址<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ADDR" name="local_F_ADDR" class="form-control" readonly="readonly">
					</div>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属总线<span class="text-comparecoll">:</span></label> -->
<!-- 					<div class="col-sm-8" style="width:50%"> -->
<!-- 						<input type="text" id="local_FlnID" name="local_FlnID" class="form-control" readonly="readonly"> -->
<!-- 					</div> -->
<!-- 				</div> -->
				</form>
            	</div>
            	<!----上位机信息结束--->
                   	
            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:400px;height:450px;margin:5px 0 0 36px;">
				<form id="under_DDCInfo" name="under_DDCInfo" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_GUID" name="under_F_GUID" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">名称<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_SYS_NAME" name="under_F_SYS_NAME" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">别名<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_NICK_NAME" name="under_F_NICK_NAME" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">安装位置<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_AZWZ" name="under_F_AZWZ" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">型号<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_TYPE" name="under_F_TYPE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ENABLED" name="under_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_DESCRIPTION" name="under_F_DESCRIPTION" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">通信地址<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ADDR" name="under_F_ADDR" class="form-control" readonly="readonly">
					</div>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属总线<span class="text-comparecoll">:</span></label> -->
<!-- 					<div class="col-sm-8" style="width:50%"> -->
<!-- 						<input type="text" id="under_FlnID" name="under_FlnID" class="form-control" readonly="readonly"> -->
<!-- 					</div> -->
<!-- 				</div> -->
				</form>
            	</div>
            	<!----下位机信息结束--->

            </div>
            <div class="modal-footer">
        		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
       		</div>
        </div>
    </div>
</div>
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {

	//使能状态单选按钮状态设置  tianjiangwei 改变初始使能状态
	<#if returnObject.data.F_ENABLED == '0'>//使能状态(不使用【0】 使用【1】)
	
	$("#f_enabledn").prop('checked', true);//radio默认选中
		// $('input:radio').eq(1).prop('checked', true);//根据索引值设置任意一个radio为选中值
	<#else>
		// $('input:radio').eq(0).attr('checked', true);
	$("#f_enabledy").prop('checked', true);//radio默认选中
	</#if>
	var _ctx = '${ctx}';
	$("#sbdy_Moduleform").validate({
	});
	var addChildNodeNameMap = new Map();//(key:ID value:模块点类型(新增节点名称))
	var epModuleTypeMap = new Map();//(key:ID value:设备树节点类型)
	$(function() {
		//start wanghongjie 刚进入页面判断当前节点的系统名称在表里有没有数据,有的话将f_sys_name输入框设置成不能输入
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/info_f_sys_name",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val(),
				tabName    : "bes_module",//表名
			},
			success : function (result) {
				if (result.status == "1"){
					$("#f_sys_name").attr("readonly", "readonly");//系统名称变为不可输入
					$('#f_type_group').attr("disabled", true);//模块型号变为不可选
				}else {
					return
				}
			}
		});
		//wanghongjie end
		setCheckbox();//设置状态

	});
	$.ajax({ //模块型号下拉列表获取模块类型信息
		url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getModuleTypeInfo",
		contentType : "application/json; charset=utf-8",
		type : "get",
		beforeSend : function() {
			showLoad();
		},
		success : function(result) {
			if (result.status == "1") {
				var eList = result.list;
				for (var i = 0; i < eList.length; i++) {
					var x = document.getElementById("f_type_group");
					var obj = document.createElement("option");
					obj.setAttribute("moduleAmount",eList[i].fPointAmount);
					obj.setAttribute("nodeTypes",eList[i].fPointTypeCl);
					obj.value = eList[i].fModuleId;
					obj.text = eList[i].fModuleType;
					x.appendChild(obj);
					if ($("#f_type_selected").val() == eList[i].fModuleId) {//设置当前选中的设备型号
						obj.selected = true;
					} else {
						$("#f_type_selected").attr('');
					}
				}
				//新增页面时默认选中第一个赋值
				var sel = $("#f_type_group option:checked").val();
				$("#moduleType").val(sel);
				$("#moduleAmount").val($("#f_type_group").children("option[value='" + sel + "']")[0].attributes[0].nodeValue);
				$("#nodeTypes").val($("#f_type_group").children("option[value='" + sel + "']")[0].attributes[1].nodeValue);
			}
		},
		complete : function() {
			hiddenLoad();
		},
		error : function(result) {
			alert("获取模块型号失败！");
		}
	});
	$.ajax({ //根据模块型号下拉列表添加子节点信息
		url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getModuleTypeAddChidNodeInfo",
		contentType : "application/json; charset=utf-8",
		type : "get",
		beforeSend : function() {
			showLoad();
		},
		success : function(result) {
			if (result.status == "1") {
				var epModuleTypeList = result.list;
				for (var i = 0; i < epModuleTypeList.length; i++) {
					epModuleTypeMap.put(epModuleTypeList[i].fModulepointId,epModuleTypeList[i].fEpTreenodeType);
				}
				var rAddMap = result.map;
				for ( var key in rAddMap) {
					addChildNodeNameMap.put(key, rAddMap[key]);
				};
			}
		},
		complete : function() {
			hiddenLoad();
		},
		error : function(result) {
			alert("获添加子节点相关信息失败！");
		}
	});
	//填充通信地址前两位
	$.ajax({ 
		url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getModuleF_ADDR",
		contentType : "application/json; charset=utf-8",
		type : "post",
		data : JSON.stringify({
			f_sys_name : $("#f_sys_name").val(),
			f_psys_name : basedatamanage_eqmanage_eqconfiguration_sbdy.getNodepid(),//父系统名称
			// f_psys_name : $("#f_psys_name").val(),//父系统名称
			nodeLevel : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数 
			f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
		}),
		beforeSend : function() {
			showLoad();
		},
		success : function(result) {
			if (result.status == "1" ) {
				if(result.data.trunkCoupler_F_ADDR != undefined){
				$("#trunkCoupler_F_ADDR").text(result.data.trunkCoupler_F_ADDR +".");
				$("#branchCoupler_F_ADDR").text(result.data.branchCoupler_F_ADDR +".");
				}
			}
		},
		complete : function() {
			hiddenLoad();
		},
		error : function(result) {
			alert("获添加子节点相关信息失败！");
		}
	});	
	//通信地址
	for (var i = 1; i < 256; i++) {
		var x = document.getElementById("f_addr_group");
		var obj = document.createElement("option");
		obj.value = i ;
		obj.text = i ;
		x.appendChild(obj);
		if ($("#f_addr_selected").val().indexOf(".") != -1) {
			var str = $("#f_addr_selected").val();
			if (str.substring(str.lastIndexOf(".")+1) == i ) {//设置当前选中的通信地址
				obj.selected = true;
			}
		}else {
			if ($("#f_addr_selected").val() == i ) {//设置当前选中的通信地址
				obj.selected = true;
			}
		}
	}
	
	//【模块型号】下拉列表绑定change事件，当下拉框内容发生变化时事件被启动
	$("#f_type_group").bind("change",function() {
		//获取被选中的option的值
		//var miaoshu = $(this).val();
		//获取型号下拉列表自定义属性的值
		$("#moduleType").val($(this).find("option:selected").attr("value"));
		$("#moduleAmount").val($(this).find("option:selected").attr("moduleamount"));
		$("#nodeTypes").val($(this).find("option:selected").attr("nodetypes"));
	});
	function converStr(str) {//给数字字符串添加逗号分隔符
		if (/\./.test(str)) {
			return str.replace(/\d(?=(\d{1})+\.)/g, "$&,").split("").reverse()
					.join("").replace(/\d(?=(\d{1})+\.)/g, "$&,").split("")
					.reverse().join("");
		} else {
			return str.replace(/\d(?=(\d{1})+$)/g, "$&,");
		}
	};

	//采集器信息对比模态框出现前加载(可拖动)
	$("#compareModule").on('show.bs.modal', function(event) {
		$(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#compareModule .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     ,	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    fill_localModule();

	});
	//关闭模态框清空表单值
	$("#compareModule").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
	});

	//状态设置
	function setCheckbox(){
		//同步状态

		if($("#f_module_state").val() == "1"){
			setSyncPageModule(true);
		}else{
			setSyncPageModule(false);
		}
		//在线状态
		/*if($("#f_online_state").val() == "1"){
            $("#f_online_state").prop("checked",true);
            $("#ammeter_onlinestate").text("在线");
            $("#ammeter_onlinestate").attr("style","color: #00ff2d");
        }else{
            $("#f_online_state").prop("checked",false);
            $("#ammeter_onlinestate").text("离线");
            $("#ammeter_onlinestate").attr("style","color: #ff0000");
        }*/
		//使能状态
		/*if($("#hidden_f_enabled").val() == "1"){
            $("#f_enabled_yes").prop("checked",true);
            $("#hidden_f_enabled").val("1");
        }else if ($("#hidden_f_enabled").val() == "0")
        {
            $("#f_enabled_no").prop("checked",true);
            $("#hidden_f_enabled").val("0");
        }*/
	};

	function setSyncPageModule(state)
	{

		if (typeof state !== 'boolean')
		{
			return;
		}

		if (state)
		{
			$("#f_module_state").prop("checked",true);
			$("#module_state").text("已同步");
			$("#module_state").attr("style","color: #00ff2d");
		} else
		{
			$("#f_module_state").prop("checked",false);
			$("#module_state").text("未同步");
			$("#module_state").attr("style","color: #ff0000");
		}
	}

	//填充上位机模块信息
	function fill_localModule(){
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/getf_module_type",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			type : "post",
			data : JSON.stringify({
				f_module_type 		: $("#moduleType").val(),//模块型号
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
					$("#local_F_GUID").val($("#f_struct_id").val());
					$("#local_F_SYS_NAME").val($("#f_sys_name").val());
					$("#local_F_NICK_NAME").val($("#f_nick_name").val());
					$("#local_F_AZWZ").val($("#f_azwz").val());

					$("#local_F_TYPE").val(result.data);

					($("#hidden_radio_f_enabled").val() == "1" ) ? $("#local_F_ENABLED").val("使能") : $("#local_F_ENABLED").val("不使能");
					$("#local_F_DESCRIPTION").val($("#f_description").val());
					if($("#f_addr_selected").val().indexOf(".") != -1) {

						var addr = $("#f_addr_selected").val().substring( $("#f_addr_selected").val().lastIndexOf(".") + 1 ) ;

						$("#local_F_ADDR").val(addr);
					} else  {
						$("#local_F_ADDR").val($("#f_addr_selected").val());
					}
					fill_underModule();
				}


			},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			}
		});


// 		$("#local_FlnID").val($("#f_description").val());
	}
	//填充下位机模块信息
	function fill_underModule() {
		$.ajax({
			url: _ctx + "/view/basedatamanage/eqmanage/getModuleParam",
			type: "post",
			data: {
				f_id 	: $("#f_id").val(),//设备树的id
				fSysName: $("#f_sys_name").val(),//系统名称
				f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
			},
			beforeSend: function () {
				showLoad();
			},
			success: function (result) {
				if (result.status !== '1') {
					swal({
						title: result.msg,// 展示的标题
						text: "",// 内容
						type: "warning",
						showCloseButton: false, // 展示关闭按钮
						allowOutsideClick: false,
						showConfirmButton: false,
						timer: 1000,
					});
				}
			},

			complete: function () {
				hiddenLoad();
			},
			error: function (result) {
				swal(result.msg, "", "error");
			}
		});
	}
	return {
		//获取id
		getNodefGuid : function() {
			return $("#hidden_id").val();
		},
		addDifferSet : function(){
			if (basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()) {
				$("#f_type_group").attr("disabled", false);//下拉列表设置为可选择状态
			} else {
				$("#f_type_group").attr("disabled", true);//下拉列表设置为不可选择状态
			}
		},
		//信息发生变更时
		infoChanged : function() {
			var f_sys_name   =	$("#f_sys_name").val();
			var f_nick_name  =	$("#f_nick_name").val();
			var f_guid       =	$("#f_guid").val();
			var f_id         =	$("#f_id").val();
			var f_enabled    =	$('input[name="f_enabled"]:checked').val();
			var f_azwz       =	$("#f_azwz").val();
			var f_addr_module=	$("#trunkCoupler_F_ADDR").text() + $("#branchCoupler_F_ADDR").text() + $("#f_addr_group option:checked").val();
			var f_addr		 =	$("#f_addr_group option:checked").val();
			var f_type       =	$("#f_type").val();
			var f_description=	$("#f_description").val();

			//判断系统名称字符串第一个字符是否为数字,是的话不能添加
			var  f_sys_nameSub = Number(f_sys_name.substring(0,1));
			if (!isNaN(f_sys_nameSub)){
				swal({
					title : '系统名称首字母不能为数字!',// 展示的标题
					text : "",// 内容
					type : "warning",
					showCloseButton : false, // 展示关闭按钮
					allowOutsideClick : false,
					showConfirmButton : false,
					timer : 1000
				});
				return;
			};

			//判断输入的系统名称是否含有中文
			if (/.*[\u4e00-\u9fa5]+.*$/.test(f_sys_name)){
				swal({
					title : '系统名称不能包含汉字!',// 展示的标题
					text : "",// 内容
					type : "warning",
					showCloseButton : false, // 展示关闭按钮
					allowOutsideClick : false,
					showConfirmButton : false,
					timer : 1000
				});
				return;
			};

			if (eval($("#saveBtn").attr("name"))) {

				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_moduleInfo_update",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						tabName 			: "BES_MODULE",//模块表
						f_sys_name 			: f_sys_name,//手动输入系统名称
						f_nick_name 		: f_nick_name,//模块别名
						f_guid 				: f_guid.replace(/,/g,""),//模块的id
						f_id 				: f_id,//设备树的关于这个模块的信息的id
						f_enabled 			: f_enabled,//使能状态
						f_azwz 				: f_azwz,//安装地址
						f_addr 				: f_addr,//通信地址
						f_addr_module		: f_addr_module,//通信地址(为了区分照明的通信地址)
						f_module_type 		: $("#moduleType").val(),//模块型号
						f_description 		: f_description,//模块描述
						f_sbid				: $("#f_sbid_module").val(),//模块的sbid
						f_psys_name 		: $("#f_psys_name").val(),//父系统名称
						f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
						f_module_state : "0"//初始修改默认未同步状态
					}),
					beforeSend : function() {
						showLoad();
					},
					success : function(result) {
						//Start  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
						if(result.status == '2'){
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						}else {
							basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
						}
						if (result.status === '0')
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "error",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1500,
							});
						}
						else if(result.status == '1'){
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1500
							});
						$("#saveBtn").attr("disabled", true); //【保存】设置为无效
						$("#f_sys_name").attr("readonly", "readonly");
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
						}
						//End  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
					},
					complete : function() {
						hiddenLoad();
					},
					error : function(result) {
						swal(result.msg, "", "error");
					},
				});
			} else { //新增节点时
				$("#ch_f_sys_name").val($("#f_sys_name").val());
				$("#ch_f_nick_name").val($("#f_nick_name").val());
				$("#ch_f_allpath").val($("#f_allpath").text());
				$("#ch_f_type").val($("#sel_f_type").val());
				$("#ch_f_psys_name").val($("#pre_f_sys_name").val());
				$.ajax({//新增属性节点时
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_moduleInfo_insert",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						attrTabName    		: "BES_MODULE",
						f_enabled 			: f_enabled,//使能状态
						f_azwz 				: f_azwz,//安装地址
						f_addr 				: f_addr,//通信地址
						f_addr_module		: f_addr_module,//通信地址(为了区分照明的通信地址)
						f_module_type 		: $("#moduleType").val(),//模块型号
						f_description 		: f_description,//模块描述
						tabName 			: "BES_SBPZ_STRUCT",//表名
						f_id 				: f_id,
						f_sys_name 			: f_sys_name,//系统名称
						f_nick_name			: f_nick_name,//别 名
						f_allpath 			: $("#f_allpath").text(),//全路径
						f_type 				: $("#sel_f_type").val(),//节点类型
						f_psys_name 		: $("#pre_f_sys_name").val(),//父系统名称
						other_node_types 	: $("#nodeTypes").val(),//添加其他节点的类型
						other_node_count 	: $("#moduleAmount").val(),//添加其他节点的个数
						nodeLevel 			: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数
						f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
						f_yqbh 				: basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh(),//所属园区
						f_module_state : "0"//初始添加默认未同步状态
					}),
					beforeSend : function() {
						showLoad();
					},
					success : function(returnObject) {
						if(returnObject.status == '2'){
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						}else {
							basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
						}
						if (returnObject.status == '1') {//保存成功时
							swal({
								title: returnObject.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1500
							});
							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), {exactMatch : true} ]);//
							var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							addeNodeId = $("#ch_f_sys_name").val();
							addedNodeText = $("#ch_f_nick_name").val();//更新新保存的节点
							$('#tree_sbdy').treeview('updateNode',[ changeNode, {
										nodeTreeId 		: 	addeNodeId,
										id 				: 	addeNodeId,
										text 			: 	addedNodeText,
										nodeType 		: 	changeNode[0].nodeType,
										pid 			: 	changeNode[0].pid,
										image 			: 	changeNode[0].image,
										nodeAttribution : 	PNodes[0].nodeAttribution,
										state 			:	{selected :false}
									} ]);
							basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							$("#tree_sbdy").treeview("selectNode", changeNode);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							if ($("#nodeTypes").val() 	 != null && $("#nodeTypes").val() != '') {
											var formatStr = converStr($("#nodeTypes").val());
										 var nodeTypeList = formatStr.split(",");
								for (var i = 0; i < nodeTypeList.length; i++) { //循环在树上添加默认子节点
									addChildNodeId = addeNodeId + "0" + i;
									var imgUrl = basedatamanage_eqmanage_eqconfiguration_sbdy.getImgMap().get("on_" + epModuleTypeMap.get(nodeTypeList[i]));//根据节点类型取图片路径
									if (!(imgUrl == '') || !(imgUrl == null))
										imageUrl = imgUrl.substring(5, imgUrl.length - 2);
									$("#tree_sbdy").treeview("addNode",[
													{
														nodeTreeId 	: addChildNodeId,
														id 			: addChildNodeId,
														text 		: addChildNodeNameMap.get(nodeTypeList[i]),
														nodeType	: epModuleTypeMap.get(nodeTypeList[i]),
														pid 		: addeNodeId,
														image 		: imageUrl
													}, changeNode]);

									basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addChildNodeId,addChildNodeId);

									PubSub.subscribe(addChildNodeId, basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTreeSingle);
								}

								basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							}
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
							$("#saveBtn").text("保存");
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.refreshSubmitFlg();
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
						} else {//保存失败时
							// basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeAddFalse(returnObject);
							if (typeof callback === "function")
							{
								callback(returnObject);
							}

							swal({
								title: returnObject.msg,// 展示的标题
								text: "",// 内容
								type: "warning",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1500
							});
							return;
						}

					},
					complete : function() {
						hiddenLoad();
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					}
				});
			}
		},
		//同步模块
		synModule : function(){
			var f_sys_name		=	$("#f_sys_name").val();
			var old_f_sys_name	=	$("#f_sys_name_old").val();
			var f_id			=	$("#f_id").val();

			if(!($("#saveBtn").prop("disabled"))){
				swal({
		        	title : "数据已修改，请先保存",// 展示的标题
		   			text : "",// 内容
		   			type: "warning",
		   			showCloseButton : true, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
			}else{
			$.ajax({
				// url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/synModule",
				url: _ctx + "/view/basedatamanage/eqmanage/synchronizeModule",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				type: "post",
				data: JSON.stringify({
					tabname			:	"bes_module",
					f_id 			: 	f_id,
					old_f_sys_name 	: 	old_f_sys_name,//DO系统名称,下发到下位机的名称
					f_psys_name 	: 	$("#f_psys_name").val(),//父系统名称
					f_sbid_module   : 	$("#f_sbid").val(),//设备id
					f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
					f_addr		 	:	$("#f_addr_group option:checked").val(),
					f_addr_module	:	$("#trunkCoupler_F_ADDR").text() + $("#branchCoupler_F_ADDR").text() + $("#f_addr_group option:checked").val(),
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
				},
			});
			}
		},

		handleLowerData: function (data)
		{
			if (null == data)
			{
				return;
			}

			var active = data.active;

			if (active === 1)
			{
				active = '使能';
			}else if (active === 0)
			{
				active = '不使能';
			}else {
				active = '';
			}

			$("#under_F_GUID").val(data.id);
			$("#under_F_SYS_NAME").val(data.name);
			$("#under_F_NICK_NAME").val(data.alias);
			$("#under_F_AZWZ").val(data.location);
			$("#under_F_TYPE").val(data.modelID);//型号
			$("#under_F_ENABLED").val(active);//使能
			$("#under_F_DESCRIPTION").val(data.description);
			$("#under_F_ADDR").val(data.slaveAddress);//通信地址
			//判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
			($("#under_F_GUID").val() != $("#local_F_GUID").val() ) ? $("#local_F_GUID").attr('style','color:red') : $("#local_F_GUID").removeAttr('style');
			($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val() ) ? $("#local_F_SYS_NAME").attr('style','color:red') : $("#local_F_SYS_NAME").removeAttr('style');
			($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val() ) ? $("#local_F_NICK_NAME").attr('style','color:red') : $("#local_F_NICK_NAME").removeAttr('style');
			($("#under_F_AZWZ").val() != $("#local_F_AZWZ").val() ) ? $("#local_F_AZWZ").attr('style','color:red') : $("#local_F_AZWZ").removeAttr('style');
			($("#under_F_SSQY").val() != $("#local_F_SSQY").val() ) ? $("#local_F_SSQY").attr('style','color:red') : $("#local_F_SSQY").removeAttr('style');
			($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val() ) ? $("#local_F_ENABLED").attr('style','color:red') : $("#local_F_ENABLED").removeAttr('style');
			($("#under_F_DESCRIPTION").val() != $("#local_F_DESCRIPTION").val() ) ? $("#local_F_DESCRIPTION").attr('style','color:red') : $("#local_F_DESCRIPTION").removeAttr('style');
			($("#under_F_ADDR").val() != $("#local_F_ADDR").val() ) ? $("#local_F_ADDR").attr('style','color:red') : $("#local_F_ADDR").removeAttr('style');
		},

		setSyncPageModule,
	}
})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->