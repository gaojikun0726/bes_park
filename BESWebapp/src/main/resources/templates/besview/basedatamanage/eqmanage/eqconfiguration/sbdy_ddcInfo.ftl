<style>
	#ddcbcInfo-import-form-control-btn .form-control{
		height: 28px!important;
		line-height: 1.8;
	}
	#ddcbcInfo-import-form-control-btn .btn-primary{
		width:6.5vw!important;
		height: 3.85vh!important;
	}

</style>
   <div class="attrInfo" id="attrInfo" >
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <form role="form" id="sbdy_DDCform" name="sbdy_DDCform" class="form-horizontal">
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">名  称:</label>
                                <div class="col-sm-4">
                                    <input id="f_sys_name" name="f_sys_name"  value="${returnObject.data.F_SYS_NAME}" type="text" class="form-control" readonly="readonly"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                	<input  type="hidden" id="f_guid" name="f_guid"  value="${returnObject.data.F_GUID}">
                                	<input  type="hidden" id="f_sbid" name="f_sbid"  value="${returnObject.data.F_SBID}">
									<input  type="hidden" id="f_id" name="f_id"  value="${besSbPzStruct.f_id}">
									<input  type="hidden" id="f_type" name="f_type"  value="${besSbPzStruct.f_type}">
									<input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">别  名:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name" required maxlength="32" value="${returnObject.data.F_NICK_NAME}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
							<div class="has-success">
								<label class="col-sm-2 control-label">IP地址:</label>
								<div class="col-sm-4">
									<input type="hidden" id="hidden_f_ip_addr" value="${returnObject.data.F_IP_ADDR}">
									<input type="text" name="ip_b_1" id="ip_b_1" class="ip-input"/>.
									<input type="text" name="ip_b_2" id="ip_b_2" class="ip-input"/>.
									<input type="text" name="ip_b_3" id="ip_b_3" class="ip-input"/>.
									<input type="text" name="ip_b_4" id="ip_b_4" class="ip-input"/>
								</div>
								<img id="ip_warn_hint_img" data-value="${returnObject.data.F_CHANNEL_ID}"
									 title="ip地址已更新请及时重启，原ip地址：${returnObject.data.F_CHANNEL_ID}" style="height: 20px; display: none" src="images/warn-circle.png">
							</div>
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

						<div id="network_config_slave" style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth">
							<div class="has-success">
								<label class="col-sm-2 control-label">默认网关:</label>
								<div class="col-sm-4">
									<input type="hidden" id="hidden_f_gateway" value="${returnObject.data.F_GATEWAY}">
									<input type="text" name="gateway_1" id="gateway_1" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="gateway_2" id="gateway_2" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="gateway_3" id="gateway_3" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="gateway_4" id="gateway_4" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
								</div>
							</div>

							<div class="has-success">
								<label class="col-sm-2 control-label">子网掩码:</label>
								<div class="col-sm-4">
									<input type="hidden" id="hidden_f_mask" value="${returnObject.data.F_MASK}">
									<input type="text" name="mask_1" id="mask_1" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="mask_2" id="mask_2" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="mask_3" id="mask_3" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
									<input type="text" name="mask_4" id="mask_4" class="ip-input"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
								</div>
							</div>

						</div>
                          <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                          	<div class="has-success">
                                <label class="col-sm-2 control-label">描  述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description" maxlength="32" value="${returnObject.data.F_DESCRIPTION}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">安装位置:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_azwz" maxlength="32"  value="${returnObject.data.F_AZWZ}"  class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                           </div>
                           <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                           	<div class="has-success">
                                <label class="col-sm-2 control-label">归属区域:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_ssqy"  value="${returnObject.data.F_SSQY}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">控制器时间:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_ddc_time" readonly value="" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                           </div>

							<div id="network_config_master" style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth">
								<div class="has-success">
									<label class="col-sm-2 control-label">主机IP:</label>
									<div class="col-sm-4">
										<input type="hidden" id="hidden_f_ip_master" value="${returnObject.data.F_IP_MASTER}">
										<input type="text" name="ip_master_1" id="ip_master_1" class="ip-input"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
										<input type="text" name="ip_master_2" id="ip_master_2" class="ip-input"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
										<input type="text" name="ip_master_3" id="ip_master_3" class="ip-input"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
										<input type="text" name="ip_master_4" id="ip_master_4" class="ip-input"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
									</div>
								</div>

								<div class="has-success">
									<label class="col-sm-2 control-label">主机端口:</label>
									<div class="col-sm-4">
										<input type="text" id="f_port_master" value="${returnObject.data.F_PORT_MASTER}"
											   class="form-control"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
									</div>
								</div>

							</div>

                            <div  class="vertical-timeline-block eqTreeAttrLineWidth">
								<div class="has-success">
								   <label class="col-sm-2 control-label">状  态:</label>
									 <div class="col-sm-4">
										 <input type="checkbox" id="f_ddc_state" disabled name="f_ddc_state" value="${returnObject.data.F_DDC_STATE}"
												onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
										 <a id="ddc_state"></a>
										 <input type="checkbox" id="f_ddc_onlinestate" disabled name="f_ddc_onlinestate" value="${returnObject.data.F_ONLINE_STATE}"
												onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
										 <a id="ddc_onlinestate"></a>
									</div>
								</div>

								<div class="has-success">
									<label class="col-sm-2 control-label" title="能耗保存周期">保存周期:</label>
									<div class="col-sm-4">
										<input type="hidden" id="f_ddc_save_period"
											   value="${returnObject.data.F_SAVE_PERIOD}" class="form-control">
										<select id="f_ddc_save_period_select" class="selector"
												style="width: 13em;height: 2em;border:1px solid green;"
												onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
									</div>
								</div>
                           </div>
                           </form>
                                  <div style="margin-left: 17px;margin-top: 5em; padding-right: 10px;;">
                                   <span>
									<#--wanghongjie 添加ip跳转 start 2020/05/09-->
									<button id="tiaozhuan" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.jumpIpPageDdc(6);">跳转</button>
                                    <#--wanghongjie 添加ip跳转 end-->
									<button id="setDateBtn" class="btn sbtreeNodeBtn"
											<#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operDDCController(2);">设置时间</button>-->
									   		onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setTimeDDC();">设置时间</button>
                                    <button id="getDateBtn" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getTimeDDC();">获取时间</button>
                                    <button id="reSetBtn" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.restartDDC()">重启</button>
                                    <button id="reStartBtn" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.resetDDC()">重置</button>
                                    <#--<button id="remoteUpMoreBtn" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synDDC('1')">批量同步数据</button>-->
								    <button id="remoteUpBtn" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synDDC()">同步数据</button>
                                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#compareDDC">数据对比</button>
                                   <button id="synDataBtn" class="btn sbtreeNodeBtn"
										   onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operDDCController()">远程升级</button>
									<#--wanghongjie ddc远程升级 start 2020/05/09-->
									<button id="ddcRemoteUpgrades" class="btn sbtreeNodeBtn"
											onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.ddc_impReport()">下发程序</button>
									<#--wanghongjie ddc远程升级 end-->
									<#--<input type="hidden" id="pollStatusInput" name="pollStatusInput" value="${returnObject.data.F_POLL_STATUS}"/>
									<select id="pollStatusBtn" class="btn" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operPollStatus()">
										<option value="1">轮询开</option>
										<option value="0">轮询关</option>
									</select>-->
                                    <button id="saveBtn" style="margin-left: 10px;" class="btn sbtreeNodeBtn" type="submit"
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
              <input type="hidden" id="ch_f_psys_name"/>

              <input type="hidden" id="tree_f_sblxbh"/><!-- 设备类型编号 -->
              <input type="hidden" id="tree_f_type"/><!-- 型号 -->

     </div>
<!----DDC信息对比--->
<div class="modal fade" id="compareDDC" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机IP路由器信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机IP路由器信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机IP路由器信息</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:450px;">
				<form id="local_DDCInfo" name="local_DDCInfo" class="form-horizontal">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属区域<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_SSQY" name="local_F_SSQY" class="form-control" readonly="readonly">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属区域<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_SSQY" name="under_F_SSQY" class="form-control" readonly="readonly">
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
   <!--wanghongjie ddc上传模态框 -->
   <div class="modal fade import-Model" id="ddc_import_Model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
	   <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
		   <div class="modal-content">
			   <div class="modal-header">
				   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				   <h4 class="modal-title" id="ImportmyModalLabel">导入文件</h4>
			   </div>
			   <div class="modal-body">
				   <div class="row" id="ddcbcInfo-import-form-control-btn">
					   <div class="col-md-12" style="padding-top: 2vh;">
						   <form id="ddcInfo_RemoteUpgrades">
							   <input id="ddcInfo_RemoteUpgradesFile" type="file" name="file"  class="file-loading">
							   <textarea  class='form-control' name="ddcbcInfoText" placeholder="请输入文本" style='display: none;'></textarea>
							   <input type="text" id="f_ip" name="f_ip" value="${returnObject.data.F_CHANNEL_ID}" TYPE="hidden" style="display: none;" >
						   </form>
					   </div>
				   </div>

			   </div>
			   <div class="modal-footer">
				   <button type="button" class="btn btn-default" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.ddcInfoUpload('#ddcInfo_RemoteUpgrades')">下发程序</button>
				   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			   </div>
		   </div>
	   </div>
   </div>
<#include '/besview/basedatamanage/eqmanage/eqconfiguration/sbdy_setInfo.ftl'>  <!--引入属性设置页面-->
<script src="${ctx}/static/js/issp/issp-helpcombobox.js"></script>
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
	//使能状态单选按钮状态设置

 	<#if returnObject.data.F_ENABLED == '0'>//使能状态(不使用【0】 使用【1】)
 		$('input:radio').eq(1).prop('checked', true);//根据索引值设置任意一个radio为选中值
 	<#else>
 		$('input:radio').eq(0).attr('checked', true);
 	</#if>

	<#if returnObject.data.F_SYS_NAME_OLD != ''>
	$('#network_config_master').show();
	$('#network_config_slave').show();
	</#if>

	<#if returnObject.data.F_CHANNEL_ID != returnObject.data.F_IP_ADDR>
	$('#ip_warn_hint_img').show();
	<#else>
	$('#ip_warn_hint_img').hide();
	</#if>

	var _ctx = '${ctx}';
	var sblx = "";
	var sblxbh = "";
	var allpath = "";
	$("#sbdy_DDCform").validate({
	});


	// dom加载完毕 赋值
	$(function(){
		var f_port = "${returnObject.data.F_PORT}";
		allpath = "${besSbPzStruct.f_allpath}";
		// 判断
		if(f_port !=''){
			$("#f_port").val(f_port);
		}else{
			$("#f_port").val('1204');
		}

		// 填充周期下拉框
		setSavePeriodSelectHtml();

		setSavePeriodSelect();

	});

	// 填充周期下拉框
	function setSavePeriodSelectHtml() {

		var html = [];

		for (var i = 1; i <= 36; i++) {
			html.push('<option value="' + i + '">' + i * 5 + '分钟' + '</option>')
		}

		$('#f_ddc_save_period_select').html(html.join(''));
	}

	// 赋值周期下拉框
	function setSavePeriodSelect() {

		var savePeriod = $('#f_ddc_save_period').val();
		if (savePeriod) {
			$('#f_ddc_save_period_select').val(savePeriod);
		}
	}


	//同步状态
	function setSyncPageByDDC(state)
	{

		if (typeof state !== 'boolean')
		{
			return;
		}

		if (state)
		{
			$("#f_ddc_state").prop("checked",true);
			$("#ddc_state").text("已同步");
			$("#ddc_state").attr("style","color: #00ff2d");
		} else
		{
			$("#f_ddc_state").prop("checked",false);
			$("#ddc_state").text("未同步");
			$("#ddc_state").attr("style","color: #ff0000");
		}
	}
	//状态设置
	function setCheckbox(){
		//同步状态
		if($("#f_ddc_state").val() == "1"){
			setSyncPageByDDC(true);
		}else{
			setSyncPageByDDC(false);
		}
		//在线状态
		if($("#f_ddc_onlinestate").val() == "1"){
			$("#f_ddc_onlinestate").prop("checked",true);
			$("#ddc_onlinestate").text("在线");
			$("#ddc_onlinestate").attr("style","color: #00ff2d");
		}else{
			$("#f_ddc_onlinestate").prop("checked",false);
			$("#ddc_onlinestate").text("离线");
			$("#ddc_onlinestate").attr("style","color: #ff0000");
		}
		//使能状态
		if($("#hidden_radio_f_enabled").val() == "0"){
			$("#f_enabledn").prop("checked",true);
			$("#hidden_radio_f_enabled").val("0");
		}else{
			$("#f_enabledy").prop("checked",true);
			$("#hidden_radio_f_enabled").val("1");

		}
	}

	/*function setPollStatus(){
		if($('#pollStatusInput').val() == '1'){
			$('#pollStatusBtn').val(1);
		}else{
			$('#pollStatusBtn').val(0);
		}
	}*/

	//填充上位机DDC信息
	function fill_localDDC(){
		$("#local_F_GUID").val($("#f_sbid").val());
		$("#local_F_SYS_NAME").val($("#f_sys_name").val());
		$("#local_F_NICK_NAME").val($("#f_nick_name").val());
		$("#local_F_AZWZ").val($("#f_azwz").val());
		$("#local_F_SSQY").val($("#f_ssqy").val());
		($("#hidden_radio_f_enabled").val() == "1" ) ? $("#local_F_ENABLED").val("使能") : $("#local_F_ENABLED").val("不使能");
		$("#local_F_DESCRIPTION").val($("#f_description").val());
	}
	//填充下位机DDC信息
	function fill_underDDC(){
		$.ajax({
			// url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/compareDDC",
			url : _ctx + "/view/basedatamanage/eqmanage/getDDCInfoParam",
			type : "post",
			data : {
				fSysName : $("#f_sys_name").val(),
				f_type	 : $("#f_type").val(),//节点类型
			},
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
 	//居中显示（采集器信息对比）
 	$('#compareDDC').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#compareDDC .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//采集器信息对比模态框出现前加载(可拖动)
	$("#compareDDC").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"   ,  	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    fill_localDDC();
	    fill_underDDC();
	});
	//关闭模态框清空表单值
	$("#compareDDC").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
	});
	$(function() {
		var hidIp = $("#hidden_f_ip_addr").val();
		if(hidIp!= null && hidIp!=""){//初始化ip地址
			var ips = hidIp.split(".");
			$("#ip_b_1").val(ips[0]);
			$("#ip_b_2").val(ips[1]);
			$("#ip_b_3").val(ips[2]);
			$("#ip_b_4").val(ips[3]);
		}

		var gatewayIp   = $("#hidden_f_gateway").val();
		if(gatewayIp!= null && gatewayIp!=""){//初始化ip地址
			var ip = gatewayIp.split(".");
			$("#gateway_1").val(ip[0]);
			$("#gateway_2").val(ip[1]);
			$("#gateway_3").val(ip[2]);
			$("#gateway_4").val(ip[3]);
		}


		var mask = $("#hidden_f_mask").val();
		if(mask!= null && mask!=""){//初始化ip地址
			var masks = mask.split(".");
			$("#mask_1").val(masks[0]);
			$("#mask_2").val(masks[1]);
			$("#mask_3").val(masks[2]);
			$("#mask_4").val(masks[3]);
		}


		var ipmaster = $("#hidden_f_ip_master").val();
		if(ipmaster!= null && ipmaster!=""){//初始化ip地址
			var ipmasters = ipmaster.split(".");
			$("#ip_master_1").val(ipmasters[0]);
			$("#ip_master_2").val(ipmasters[1]);
			$("#ip_master_3").val(ipmasters[2]);
			$("#ip_master_4").val(ipmasters[3]);
		}
		setCheckbox();//设置状态
	});

	//fileinput 上传插件初始化
	function initFileinput(){
		$("#ddcInfo_RemoteUpgradesFile").fileinput({
			language: 'zh', //设置语言
			allowedFileExtensions : ['BIN'],//接收的文件后缀,
			showUpload: true, //是否显示上传按钮
			showCaption: true,//是否显示标题
			browseClass: "btn btn-primary", //按钮样式
			maxFileSize:3072,//最大单个文件上传大小
			maxFileCount:1,//最大上传个数
			showUpload:false,//是否显示上传按钮
			showBrowse:true,//是否显示浏览按钮
			showPreview:false,//是否显示预览区域
			autoReplace:true,//是否自动替换
			showRemove:true,//是否显示移除按钮
			uploadExtraData:function(id,index){
				var importExcel = $("#ddcInfo_RemoteUpgradesFile").val();
				return {"fold":importExcel};
			},//区分不同的模块：fold：文件夹
			//uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
		}).on("fileuploaded",function(){
			clearForm();//清空form表单
		}).on("filebatchuploadsuccess",function(){
			clearForm();//清空form表单
		});
	}
	return {
		//ddc的ip跳转网页
		jumpIpPageDdc:function(index){//跳转到当前ip的网页,将获取时间的方法放进来,如果当前ip可以和下位机连接,就可以跳转,否则弹出提示框,不可跳转
			let ip = $("#ip_b_1").val()+"."+ $("#ip_b_2").val()+"."+$("#ip_b_3").val()+"."+ $("#ip_b_4").val();//网络地址
			window.open("http://"+ip);

	},
		//wanghongjie 能耗远程升级
		ddcInfoUpload:function (formId)
		{
			var form =new FormData($(formId)[0] );     //通过id获取表单的数据
			if($("#ddcInfo_RemoteUpgradesFile").val() == "")
			{
				swal( "请选择文件","", "warning");
				return;
			}

			$.ajax({
				type:"POST",//请求的类型
				url:"${ctx}/view/basedatamanage/eqmanage/collectorUpload",//请求的路径
				data: form, //请求的参数
				//async: false,//异步请求
				//cache: false,//false的话会在url后面加一个时间缀，让它跑到服务器获取结果。cache只有GET方式的时候有效
				contentType: false,
				processData: false,
				beforeSend: function () {
					showLoad();
				},
				success: function (data) { //成功返回触发的方法
					if(data.data.data == true){//如果后台返回下发程序成功,为true
						swal( data.data.msg,"", "success");
					}else if (data.data.data == false){//如果后台返回下发程序失败,为false
						swal( data.data.msg,"", "error");
					}
					$("#ddc_import_Model").modal("hide");

				},
				complete: function () {
					hiddenLoad();
				},
				//请求失败触发的方法
				error:function(){
					console.log("ajax请求失败");
				}
			});
		},
		ddc_impReport :function(){
			//1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成
			$("#ddc_import_Model").modal("show");
		},
		//获取id
		getNodefGuid : function() {
			return $("#hidden_id").val();
		},
		addDifferSet : function(){
			if(basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){
				basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
			}
		},
		//信息发生变更时
		infoChanged : function() {
			if(!basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.ipIsIllegal()){//验证IP地址
				return;
			}
			var f_sys_name 		= 	$("#f_sys_name").val();//DDC名称
			var f_id 			= 	$("#f_id").val();
			var f_enabled 		= 	$('input[name="f_enabled"]:checked').val();//使能状态
			var f_nick_name 	= 	$("#f_nick_name").val();//DDC别名
			var f_ip_addr 		=	$("#ip_b_1").val()+"."+ $("#ip_b_2").val()+"."+$("#ip_b_3").val()+"."+ $("#ip_b_4").val();//网络地址
			var f_port 			=	$("#f_port").val();//端口
			var f_guid 			=	$("#f_guid").val();//表的id
			var f_ssqy 			=	$("#f_ssqy").val();//归属区域
			var f_description 	=	$("#f_description").val();//DDC描述
			var f_azwz 			=	$("#f_azwz").val();//安装位置
			var f_gateway 		=	$("#gateway_1").val() + "." + $("#gateway_2").val() + "." + $("#gateway_3").val() + "." + $("#gateway_4").val();//默认网关
			var f_mask 			=	$("#mask_1").val() + "." + $("#mask_2").val() + "." + $("#mask_3").val() + "." + $("#mask_4").val();//子网掩码
			var f_ip_master 	=	$("#ip_master_1").val() + "." + $("#ip_master_2").val() + "." + $("#ip_master_3").val() + "." + $("#ip_master_4").val();//主机ip
			var f_allpath 		=	$("#f_allpath").text();//全路径
			var f_type			=	$("#sel_f_type").val();//节点类型
			var f_psys_name		=	$("#pre_f_sys_name").val();
			var f_type_add		=	$("#f_type").val();
			var f_save_period	=	$("#f_ddc_save_period_select").val();

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

				var portMaster = $("#f_port_master").val();

				if (isNaN(portMaster))
				{
					swal({
						title: "主机端口必须是数字",// 展示的标题
						text: "",// 内容
						type: "warning",
						showCloseButton: false, // 展示关闭按钮
						allowOutsideClick: false,
						showConfirmButton: false,
						timer: 1000
					});
					return;
				}

				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_ddcInfo_Update",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						tabName 		: "BES_DDC",		//DDC表名
						f_sys_name 		: f_sys_name,		//DDC名称
						f_id 			: f_id,
						f_enabled 		: f_enabled,		//使能状态
						attr_f_type 	: sblx,				//型号
						attr_f_sblxbh 	: sblxbh,			//设备类型编号
						f_nick_name 	: f_nick_name,		//DDC别名
						f_ip_addr 		: f_ip_addr,		//网络地址
						f_port 			: f_port,			//端口
						f_guid 			: f_guid,			//表的id
						f_ssqy 			: f_ssqy,			//归属区域
						f_description 	: f_description,	//DDC描述
						f_azwz 			: f_azwz, 			//安装位置
						f_allpath 		: allpath,			//全路径
						f_gateway		: f_gateway,		//默认网关
						f_mask			: f_mask,			//子网掩码
						f_ip_master		: f_ip_master,		//主机ip
						f_port_master	: portMaster, 		//主机端口
						f_type 			: f_type_add,	    //节点类型
						f_ddc_state     : "0",              //初始修改默认未同步
						f_save_period   : f_save_period     //保存周期
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(result) {
						if (result.status === '0')
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "error",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
						}else if (result.status === '1'){

							$('#hidden_f_ip_addr').val(f_ip_addr);

							if (f_ip_addr !== $('#ip_warn_hint_img').attr('data-value'))
							{
								$('#ip_warn_hint_img').show();
							}else
							{
								$('#ip_warn_hint_img').hide();
							}

							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000
							});
							$("#saveBtn").attr("disabled", true); //【保存】设置为无效

							$("#sbdyInfo").load(_ctx +"/view/basedatamanage/eqmanage/sbdy_ddcInfo",
									{
										"f_sys_name" : f_sys_name,
										"nodeTabName" : "BES_DDC",
										"f_type":'2'
									});//跳转所选节点属性页面
							basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
						}
						/*IP验证是否重复*/
						else if (result.status=="3"){////wanghongjie 验证ip是否重复,如果重复,则提交不了
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffectiveByIP();
							return;
						}
					},
					complete: function () {
						hiddenLoad();
					},
					error : function(result) {
						swal(result.msg, "", "error");
					}
				});
			} else {
				$("#ch_f_sys_name").val($("#f_sys_name").val());
				$("#ch_f_nick_name").val($("#f_nick_name").val());
				$("#ch_f_allpath").val($("#f_allpath").text());
				$("#ch_f_type").val($("#sel_f_type").val());
				$("#ch_f_psys_name").val($("#pre_f_sys_name").val());
				$.ajax({//新增属性节点时  sbdy_Info_addByTreeBtn
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_ddcInfo_Insert",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						attrTabName 		: "BES_DDC",
						f_sys_name 			: f_sys_name,		//DDC名称
						f_enabled 			: f_enabled,		//使能状态
						f_id 				: f_id,
						attr_f_type 		: sblx,				//型号
						attr_f_sblxbh 		: sblxbh,			//设备类型编号
						f_nick_name 		: f_nick_name,		//DDC别名
						f_ip_addr 			: f_ip_addr,		//网络地址
						f_port 				: f_port,			//端口
						f_ssqy 				: f_ssqy,			//归属区域
						f_description 		: f_description,	//DDC描述
						f_azwz 				: f_azwz,			//安装位置
						tabName 			: "BES_SBPZ_STRUCT",//表名
						f_allpath 			: f_allpath,		//全路径
						f_type 				: f_type,			//节点类型
						f_psys_name 		: f_psys_name, 		//父系统名称
						f_gateway			: f_gateway,		//默认网关
						f_mask				: f_mask,			//子网掩码
						f_ip_master			: f_ip_master,		//主机ip
						f_port_master		: portMaster, 		//主机端口
						f_save_period       : f_save_period,    //保存周期
						f_yqbh 				: basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh(),//所属园区
						f_node_attribution  : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
						f_ddc_state : "0"//初始添加默认未同步
						/*f_gateWay : $("#hidden_f_gateway").val(), //默认网关
						f_mask: $("#hidden_f_mask").val(),//子网掩码
						f_serverIP:$("#hidden_f_ip_master").val(),//主机ip
						f_serverPort: $("#f_port_master").val(),//主机端口*/
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(returnObject) {
						if(returnObject.status == '2'){
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						}else if(returnObject.status == '3'){/*wanghongjie IP验证是否重复*/
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffectiveByIP();
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
								timer: 1000
							});
							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), { exactMatch : true } ]);//获取刚修改属性的节点
							addeNodeId = $("#ch_f_sys_name").val();
							addedNodeText = $("#ch_f_nick_name").val();
							var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							//更新新保存的节点
							$('#tree_sbdy').treeview('updateNode', [ changeNode, {
								nodeTreeId : addeNodeId,
								id : addeNodeId,
								text : addedNodeText,
								nodeType : changeNode[0].nodeType,
								pid : changeNode[0].pid,
								image : changeNode[0].image,
								nodeAttribution : PNodes[0].nodeAttribution,
								state :{selected :false}
							} ]);
							basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							// if (f_type == "2"){//新增DDC的时候,默认新增下面的总线和虚点,新增照明的时候,不加
								for (var i = 0; i < returnObject.list.length; i++) {//循环在树上添加默认子节点
									var nuwmap = new Map();//(key:ID value:设备树节点类型)
									nuwmap = returnObject.list[i];
									addChildNodeId = addeNodeId + "0" + i;
									var imgUrl = basedatamanage_eqmanage_eqconfiguration_sbdy.getImgMap().get("on_" + nuwmap.F_TYPE);//根据节点类型取图片路径
									if (!(imgUrl == '') || !(imgUrl == null))
										imageUrl = imgUrl.substring(5, imgUrl.length - 2);
									if (nuwmap.F_PSYS_NAME == changeNode[0].nodeTreeId){
										$("#tree_sbdy").treeview("addNode",[
											{
												nodeTreeId 	: nuwmap.F_SYS_NAME,
												id 			: nuwmap.F_SYS_NAME,
												text 		: nuwmap.F_NICK_NAME,
												nodeType	: nuwmap.F_TYPE,
												pid 		: nuwmap.F_SYS_NAME,
												image 		: imageUrl
											}, changeNode]);
									}
									basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
									basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addChildNodeId,addChildNodeId);
								}
							// }


							$("#tree_sbdy").treeview("selectNode", changeNode);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							$("#f_sys_name").attr("readonly", "readonly");
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
							// yemianzha();
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
							$("#saveBtn").text("保存");

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
                                timer: 1000
                            });
                            return;
						}

					},
					complete: function () {
						hiddenLoad();
						/*yemianzha();*/
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					}
				});
			}
		},
		refreshSubmitFlg : function() {//更新按钮状态
			var submitFlg = true;
			if ($("#saveBtn").text() == "新增") {
				$("#setDateBtn").attr("disabled", true);
				$("#getDateBtn").attr("disabled", true);
				$("#reSetBtn").attr("disabled", true);
				$("#reStartBtn").attr("disabled", true);
				$("#remoteUpBtn").attr("disabled", true);
                $("#remoteUpMoreBtn").attr("disabled", true);
                $("#dataContrast").attr("disabled", true);
				$("#synDataBtn").attr("disabled", true);
				submitFlg = false;
			} else {
				$("#setDateBtn").attr("disabled", false);
				$("#getDateBtn").attr("disabled", false);
				$("#reSetBtn").attr("disabled", false);
				$("#reStartBtn").attr("disabled", false);
				$("#remoteUpBtn").attr("disabled", false);
                $("#remoteUpMoreBtn").attr("disabled", false);
                $("#dataContrast").attr("disabled", false);
				$("#synDataBtn").attr("disabled", false);
				submitFlg = true;
			}
			$("#saveBtn").attr("name", submitFlg);
		},
		//DDC远程操作，设置时间2/重启3/远程升级30
		operDDCController : function(){

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
				swal({
					title : "您确定要远程升级吗?",
					text : "设备将断开连接!",
					type : "warning",
					showCancelButton : true,
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function ()
				{
					$.ajax({
						url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/remoteUpgradeDdc",
						type : "post",
						data : {
							fSysName : $("#f_sys_name").val(),
						},
						beforeSend: function () {
							showLoad();
						},
						success : function(result) {
							if (result.status === '1')
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "success",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000,
								});
							} else
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "error",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
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
				});
			}



		},
		// 重置DDC控制器器
		resetDDC: function()
		{

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
				swal({
					title : "您确定要重置吗?",
					text : "重置后设备将恢复出厂设置，并重启!",
					type : "warning",
					showCancelButton : true,
					confirmButtonText : "确定",
					closeOnConfirm : false
				},function ()
				{
					$.ajax({
						url: _ctx + "/view/basedatamanage/eqmanage/resetDDC",
						type: "post",
						data: {
							fSysName: $("#f_sys_name").val(),
							f_type	: $("#f_type").val(),//节点类型
						},
						beforeSend: function ()
						{
							showLoad();
						},
						success: function (result)
						{
							if (result.status === '1')
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "success",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000,
								});
							} else
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "error",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000
								});
							}
						},
						complete: function ()
						{
							hiddenLoad();
						},
						error: function (result)
						{
							swal(result.msg, "", "error");
						}
					});
				})
			}
		},

		// 重启DDC控制器
		restartDDC: function()
		{

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
				swal({
					title : "您确定要重启吗?",
					text : "设备将重新启动!",
					type : "warning",
					showCancelButton : true,
					confirmButtonText : "确定",
					closeOnConfirm : false
				}, function ()
				{
					$.ajax({
						url: _ctx + "/view/basedatamanage/eqmanage/restartDDC",
						type: "post",
						data: {
							fSysName: $("#f_sys_name").val(),
							f_type	: $("#f_type").val(),//节点类型
						},
						beforeSend: function ()
						{
							showLoad();
						},
						success: function (result)
						{
							if (result.status === '1')
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "success",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000,
								});
							} else
							{
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "error",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000
								});
							}
						},
						complete: function ()
						{
							hiddenLoad();
						},
						error: function (result)
						{
							swal(result.msg, "", "error");
						}
					});
				});
			}
		},

		//DDC，获取时间
		getTimeDDC: function()
		{

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
					url: _ctx + "/view/basedatamanage/eqmanage/getTimeDDC",
					type: "post",
					data: {
						fSysName: $("#f_sys_name").val(),
						f_type	: $("#f_type").val(),//节点类型

					},
					beforeSend: function ()
					{
						showLoad();
					},
					success: function (result)
					{
						if (result.status === '1')
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
						} else
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "error",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000
							});
						}
					},
					complete: function ()
					{
						hiddenLoad();
					},
					error: function (result)
					{
						swal(result.msg, "", "error");
					}
				});
			}
		},
		//同步DDC
			synDDC : function(){
			var f_sys_name		=	$("#f_sys_name").val();
			var old_f_sys_name	=	$("#f_sys_name_old").val();
			var f_id			=	$("#f_id").val();
			var f_type 			= 	$("#f_type").val();//节点类型

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
					url: _ctx + "/view/basedatamanage/eqmanage/synchronizeDDC",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type: "post",
					data: JSON.stringify({
						tabname			:	"bes_ddc",
						f_id 			: 	f_id,
						old_f_sys_name 	: 	old_f_sys_name,//DO系统名称,下发到下位机的名称
						f_sbid_module   : 	$("#f_sbid").val(),//设备id
						f_type			:   f_type,//节点类型
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
		},


		// 设置时间
		setTimeDDC: function()
		{

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
					url: _ctx + "/view/basedatamanage/eqmanage/setTimeDDC",
					type: "post",
					data: {
						fSysName	: $("#f_sys_name").val(),
						f_type		: $("#f_type").val()//节点类型
					},
					beforeSend: function ()
					{
						showLoad();
					},
					success: function (result)
					{
						if (result.status === '1')
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
						} else
						{
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "warning",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000
							});
						}
					},
					complete: function ()
					{
						hiddenLoad();
					},
					error: function (result)
					{
						swal(result.msg, "", "error");
					}
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
			$("#under_F_SSQY").val(data.zone);//所属区域
			$("#under_F_ENABLED").val(active);//使能
			$("#under_F_DESCRIPTION").val(data.description);
			//判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
			($("#under_F_GUID").val() != $("#local_F_GUID").val() ) ? $("#local_F_GUID").attr('style','color:red') : $("#local_F_GUID").removeAttr('style');
			($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val() ) ? $("#local_F_SYS_NAME").attr('style','color:red') : $("#local_F_SYS_NAME").removeAttr('style');
			($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val() ) ? $("#local_F_NICK_NAME").attr('style','color:red') : $("#local_F_NICK_NAME").removeAttr('style');
			($("#under_F_AZWZ").val() != $("#local_F_AZWZ").val() ) ? $("#local_F_AZWZ").attr('style','color:red') : $("#local_F_AZWZ").removeAttr('style');
			($("#under_F_SSQY").val() != $("#local_F_SSQY").val() ) ? $("#local_F_SSQY").attr('style','color:red') : $("#local_F_SSQY").removeAttr('style');
			($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val() ) ? $("#local_F_ENABLED").attr('style','color:red') : $("#local_F_ENABLED").removeAttr('style');
			($("#under_F_DESCRIPTION").val() != $("#local_F_DESCRIPTION").val() ) ? $("#local_F_DESCRIPTION").attr('style','color:red') : $("#local_F_DESCRIPTION").removeAttr('style');

		},

		showDDCTime: function (data)
		{
			if (!data)
			{
				return;
			}

			var year = data.year;
			var month = data.month;
			var day = data.day;
			var hour = data.hour + '';
			var minute = data.minute + '';
			var second = data.second + '';

			if (hour.length === 1)
			{
				hour = '0' + hour;
			}

			if (minute.length === 1)
			{
				minute = '0' + minute;
			}

			if (second.length === 1)
			{
				second = '0' + second;
			}

			var time = '20' + year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;

			$('#f_ddc_time').val(time);
		},

		setSyncPageByDDC,

		pageInit:function () {
			initFileinput();
		},
        // 实时更新在线状态
        onlineStateHandle: function (ip, state)
        {
            if (ip !== $('#ip_warn_hint_img')[0].dataset.value)
            {
                return;
            }

            if (state === 0)
            {
                $("#f_ddc_onlinestate").val("0");
                $("#f_ddc_onlinestate").prop("checked", false);
                $("#ddc_onlinestate").text("离线");
                $("#ddc_onlinestate").attr("style", "color: #ff0000");

            }else if (state === 1)
            {
                $("#f_ddc_onlinestate").val("1");
                $("#f_ddc_onlinestate").prop("checked",true);
                $("#ddc_onlinestate").text("在线");
                $("#ddc_onlinestate").attr("style","color: #00ff2d");
            }

        }
	}
})(jQuery, window, document);
basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.pageInit();
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
