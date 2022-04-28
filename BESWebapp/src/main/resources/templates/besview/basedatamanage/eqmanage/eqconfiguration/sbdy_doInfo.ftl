   <div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <form role="form" id="sbdy_DOform" name="sbdy_DOform" class="form-horizontal">
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">DO名称:</label>
                                <div class="col-sm-4">
									<input id="f_guid" value="${returnObject.data.F_GUID}" TYPE="hidden">
									<#--Start  add by wanghongjie at 20200116 for 增加设备树的f_id,传到后台查询该条的详细信息-->
									<input id="f_id" value="${besSbPzStruct.f_id}" TYPE="hidden">
									<input id="f_guid1" value="${besSbPzStruct.f_guid}" TYPE="hidden">
									<input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                                    <input id="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}" onkeyup="this.value=this.value.replace(/[^\w_]/g,'');" type="text" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
								</div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">DO别名:</label>
                                <div class="col-sm-4">
                                    <input id="f_nick_name" name="f_nick_name" required maxlength="32" value="${returnObject.data.F_NICK_NAME}" type="text" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                               <label class="col-sm-2 control-label">使能状态:</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_enabled" value="1" checked="checked"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						        	 <input type="radio" name="f_enabled" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
						    	</div>
                            </div>
                            <div class="has-success">
                               <label class="col-sm-2 control-label">是否反向:</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_reversed" checked="checked" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>正向
						        	 <input type="radio" name="f_reversed" value="1"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>反向
						    	</div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                                <label class="col-sm-2 control-label">初始值:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_init_val" digits=true required maxlength="9" value="${returnObject.data.F_INIT_VAL}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div> 
                            <div class="has-success">
                                <label class="col-sm-2 control-label">DO类型:</label>
                                <div class="col-sm-4">
                                	<input  type="hidden" id="f_node_type" name="f_sbid"  value="${returnObject.data.F_NODE_TYPE}">
                                    <input type="text" id="f_node_type_name"  value="${returnObject.data.F_NODE_TYPE_NAME}" class="form-control" readonly="readonly"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth"> 
                         	  <div class="has-success">
                                <label class="col-sm-2 control-label">DO描述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description" required value="${returnObject.data.F_DESCRIPTION}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                               </div>
                              <div class="has-success">
                               <label class="col-sm-2 control-label">工作模式:</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_work_mode" checked="checked" value="1"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>手动
						        	 <input type="radio" name="f_work_mode" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>自动
						    	</div>
                               </div>
                         </div>
                          <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                               <label class="col-sm-2 control-label">报警使能:</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_alarm_enable" checked="checked" value="1"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						        	 <input type="radio" name="f_alarm_enable" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
						    	</div>
                             </div>
                             <div class="has-success">
                               <label class="col-sm-2 control-label">报警类型:</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_alarm_type" checked="checked" value="0"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective(),
													basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective()"/>不报警
						        	 <input type="radio" name="f_alarm_type" value="1"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective(),
													basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective()"/>标准报警
						        	 <input type="radio" name="f_alarm_type" value="2"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective(),
													basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective()"/>增强报警
						    	</div>
                             </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                                <label class="col-sm-2 control-label">闭合状态:</label>
                                <div class="col-sm-4" id="BoxGroup_do">
																		<input type="hidden" id="h_f_close_state" value="${returnObject.data.F_CLOSE_STATE}"/>
																	 	<input type="checkbox" name="f_close_state" value="0"
																		onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>闭合
																		<input type="checkbox" name="f_close_state" value="1"
																		onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>断开
<#--																		<input type="checkbox" name="f_close_state" value="2"-->
<#--																	 	onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>无-->
                                </div>
                            </div>  
                              <div class="has-success">
                               <label class="col-sm-2 control-label">优先级:</label>
						      	 <div class="col-sm-4">
						      	 	 <input type="hidden" id="h_f_alarm_priority" value="${returnObject.data.F_ALARM_PRIORITY}"/>
						       		 <input type="radio" name="f_alarm_priority" checked="checked" value="0" disabled="disabled"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>一般
						        	 <input type="radio" name="f_alarm_priority" value="1" disabled="disabled"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>较大
						        	 <input type="radio" name="f_alarm_priority" value="2" disabled="disabled"
											onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>重大
						    	</div>
                             </div>
                         </div>
						<div  class="vertical-timeline-block eqTreeAttrLineWidth">
							<div class="has-success">
								<label class="col-sm-2 control-label">故障状态:</label>
								<div class="col-sm-4">
									<input type="hidden" id="h_f_fault_state" value="${returnObject.data.F_FAULT_STATE}"/>
									<input type="radio" name="f_fault_state" value="1" disabled="disabled"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
									<input type="radio" name="f_fault_state" checked="checked" value="0" disabled="disabled"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否

								</div>
							</div>
						</div>

							<div  class="vertical-timeline-block eqTreeAttrLineWidth">
								<div class="has-success">
									<label class="col-sm-2 control-label">状态</label>
									<div class="col-sm-4">
										<input type="checkbox" id="f_point_state" disabled name="f_point_state" value="${returnObject.data.F_DO_STATE}"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
										<a id="point_state"></a>
										<#-- <input type="checkbox" id="f_online_state" disabled name="f_online_state" value="${returnObject.data.F_ONLINE_STATE}"
										onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                                         <a id="ammeter_onlinestate"></a>-->
									</div>
								</div>
							</div>

                         </form>
                               <br/> <br/>
                                <div style="margin-left: 233px;padding-right: 15px;">
                                   <span>
                                	<button id="synDataBtn" class="btn sbtreeNodeBtn"
																					onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synPoint();">同步数据</button>
                                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#compareDO">数据对比</button>
<!--                                     <button id="reStartBtn" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.reSet()">重置</button> -->
                                    <button id="saveBtn" class="btn sbtreeNodeBtn" type="submit"
																						onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                               		</span>
                                </div>
                       <input type="hidden" id="hidden_edit_status"  value="${returnObject.status}" class="form-control">
                       <input type="hidden" id="hidden_edit_node_type"  value="${returnObject.data.f_type}">
                       <input type="hidden" id="hidden_edit_node_type_name"  value="${returnObject.data.f_type_name}">
                       <input type="hidden" id="hidden_edit_sys_name_tree"  value="${returnObject.data.f_sys_name_old}">
                       <input type="hidden" id="hidden_edit_sys_name"  value="${returnObject.data.F_SYS_NAME_OLD}">
                       <input type="hidden" id="hidden_edit_nick_name"  value="${returnObject.data.f_nick_name}">
                       <input type="hidden" id="hidden_edit_allpath"  value="${returnObject.data.f_allpath}">
                       <input type="hidden" id="hidden_id" value="${returnObject.data.F_GUID}">
                       <input type="hidden" id="hidden_f_sbid" value="${besSbPzStruct.f_sbid}">
                 </div>
     </div>
<!----DO逻辑点信息对比--->
<div class="modal fade" id="compareDO" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机DO点信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机DO点信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机DO点信息</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:450px;overflow:auto">
				<form id="local_AIInfo" name="local_AIInfo" class="form-horizontal">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_DESCRIPTION" name="local_F_DESCRIPTION" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ENABLED" name="local_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">点类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_PointType" name="local_F_PointType" class="form-control" readonly="readonly">
					</div>
				</div>		
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所在模块<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ModuleID" name="local_F_ModuleID" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所在通道<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ChannelIndex" name="local_F_ChannelIndex" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">工作模式<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_WorkMode" name="local_F_WorkMode" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">极性<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_Polarity" name="local_F_Polarity" class="form-control" readonly="readonly">
					</div>
				</div>		
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">初始值<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_InitValue" name="local_F_InitValue" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">是否启用<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_AlarmActive" name="local_F_AlarmActive" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_AlarmType" name="local_F_AlarmType" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">优先级<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_AlarmPriority" name="local_F_AlarmPriority" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警触发<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_AlarmTrigger" name="local_F_AlarmTrigger" class="form-control" readonly="readonly">
					</div>
				</div>
				</form>
            	</div>
            	<!----上位机信息结束--->
                   	
            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:400px;height:450px;margin:5px 0 0 36px; overflow:auto">
				<form id="under_AIInfo" name="under_AIInfo" class="form-horizontal">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_DESCRIPTION" name="under_F_DESCRIPTION" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ENABLED" name="under_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">点类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_PointType" name="under_F_PointType" class="form-control" readonly="readonly">
					</div>
				</div>		
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所在模块<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ModuleID" name="under_F_ModuleID" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所在通道<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ChannelIndex" name="under_F_ChannelIndex" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">工作模式<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_WorkMode" name="under_F_WorkMode" class="form-control" readonly="readonly">
					</div>
				</div>	
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">极性<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_Polarity" name="under_F_Polarity" class="form-control" readonly="readonly">
					</div>
				</div>		
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">初始值<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_InitValue" name="under_F_InitValue" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">是否启用<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_AlarmActive" name="under_F_AlarmActive" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_AlarmType" name="under_F_AlarmType" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">优先级<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_AlarmPriority" name="under_F_AlarmPriority" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警触发<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_AlarmTrigger" name="under_F_AlarmTrigger" class="form-control" readonly="readonly">
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

<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
<script type="text/javascript">
;
	var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
		//使能状态单选按钮状态设置
		<#if returnObject.data.F_ENABLED == '0'>//使能状态(不使用【0】 使用【1】)
			$("input[name='f_enabled']:eq(1)").attr("checked", 'checked');
		<#else>
			$("input[name='f_enabled']:eq(0)").attr("checked", 'checked');
		</#if>
		//是否反向单选按钮状态设置
		<#if returnObject.data.F_REVERSED == '1'>//是否反向(正向【0】 反向【1】)
			$("input[name='f_reversed']:eq(1)").attr("checked", 'checked');
		<#else>
			$("input[name='f_reversed']:eq(0)").attr("checked", 'checked');
		</#if>
		//工作模式单选按钮状态设置
		<#if returnObject.data.F_WORK_MODE == '0'>//(自动【0】 手动【1】)
			$("input[name='f_work_mode']:eq(1)").attr("checked", 'checked');
		<#else>
			$("input[name='f_work_mode']:eq(0)").attr("checked", 'checked');
		</#if>
		//报警使能单选按钮状态设置
		<#if returnObject.data.F_ALARM_ENABLE == '1'>//(不使能【0】 使能【1】)
			$("input[name='f_alarm_enable']:eq(0)").attr("checked", 'checked');
		<#else>
			$("input[name='f_alarm_enable']:eq(1)").attr("checked", 'checked');
		</#if>
		//报警类型单选按钮状态设置
		<#if returnObject.data.F_ALARM_TYPE == '0'>//(不报警【0】标准报警【1】增强报警【2】)
			$("input[name='f_alarm_type']:eq(0)").attr("checked", 'checked');
		<#else>
			<#if returnObject.data.F_ALARM_TYPE == '1'>
				$("input[name='f_alarm_type']:eq(1)").attr("checked", 'checked');
			<#else>
				<#if returnObject.data.F_ALARM_TYPE == '2'>
					$("input[name='f_alarm_type']:eq(2)").attr("checked", 'checked');
				<#else>
					$("input[name='f_alarm_type']:eq(0)").attr("checked", 'checked');
				</#if>
			</#if>
		</#if>

		<#if returnObject.data.F_WORK_MODE == ''>
			<#if besSbPzStruct.f_node_attribution == "2" >//楼控默认手动,照明默认自动
			$("input[name='f_work_mode']:eq(1)").attr("checked", 'checked');
			<#else>
			$("input[name='f_work_mode']:eq(0)").attr("checked", 'checked');
			</#if>
		</#if>
		var _ctx = '${ctx}';
		//校验
		$("#sbdy_DOform").validate({
		});
		var isEditDefault = $("#hidden_edit_status").val();

		var fId = $("#hidden_id").val();
		if(isEditDefault =="0"){//添加节点后默认修改属性
			if (!$("#hidden_edit_allpath").val() == "") {//设置全路径
				var allPath = $("#hidden_edit_allpath").val().split(">");
				var s = "";
				for (var i = 0; i < allPath.length - 1; i++) {
					s = s + ">" + allPath[i];
				}
				$("#f_allpath").val(s.substring(1, s.length));
				$("#pre_f_allpath").val(s.substring(1, s.length));
			}
			//wanghongjie 系统名称可输入
			//$('input[name=f_sys_name]').removeAttr("readonly");//系统名称
			$("#f_node_type").val($("#hidden_edit_node_type").val());//设置点类型
			$("#f_node_type_name").val($("#hidden_edit_node_type_name").val());//设置点类型名称
		}
	
	$(function() {
		//判断系统名称
		if($("#hidden_edit_sys_name_tree").val() != ""){
			$("#f_sys_name").val($("#hidden_edit_sys_name_tree").val());
		}else{
			$("#f_sys_name").val($("#hidden_edit_sys_name").val());
		}

		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective();//加载完页面后根据报警类型判断其他是否效

		$('#BoxGroup_do').find('input[type=checkbox]').bind('click', function () {
			$(this).attr("checked", true);

			$('#BoxGroup_do').find('input[type=checkbox]').not(this).attr("checked", false);
		});
		//start wanghongjie 刚进入页面判断当前节点的系统名称在DO表里有没有数据,有的话将f_sys_name输入框设置成不能输入
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/info_f_sys_name",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val(),
				tabName    : "bes_digit_ouput",//表名
			},
			success : function (result) {
					if (result.status == "1"){
						$("#f_sys_name").attr("readonly", "readonly");//系统名称
					}else {
						return
					}
			}
		});
		//wanghongjie end
		setCheckbox();//设置状态
	});

	//逻辑点信息对比模态框出现前加载(可拖动)
	$("#compareDO").on('show.bs.modal', function(event) {
		$(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#compareDO .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    fill_localDO();

	});
	//关闭模态框清空表单值
	$("#compareDO").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
	});

		//状态设置
		function setCheckbox(){
			//同步状态

			if($("#f_point_state").val() == "1"){
				setSyncPage(true);
			}else{
				setSyncPage(false);
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

		function setSyncPage(state)
		{

			if (typeof state !== 'boolean')
			{
				return;
			}

			if (state)
			{
				$("#f_point_state").prop("checked",true);
				$("#point_state").text("已同步");
				$("#point_state").attr("style","color: #00ff2d");
			} else
			{
				$("#f_point_state").prop("checked",false);
				$("#point_state").text("未同步");
				$("#point_state").attr("style","color: #ff0000");
			}
		}

	//填充上位机逻辑点信息
	function fill_localDO(){
		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.DOf_node_attribution(function (data) {
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/getPointInfoFillLocal",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			type : "post",
			data : JSON.stringify({
				tabName				: "BES_DIGIT_OUPUT",
				f_sys_name  		: $("#f_sys_name").val(),
				f_id 				: $("#f_id").val(),//设备树的id
				f_node_type 		: $("#f_node_type").val(),//DO点  类型
				f_psys_name 		: basedatamanage_eqmanage_eqconfiguration_sbdy.getNodepid(),//父系统名称
				nodeLevel 			: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数
				f_channel_index 	: basedatamanage_eqmanage_eqconfiguration_sbdy.getNodeIndex().toString(),//通道索引
				f_node_attribution 	: data.data.F_NODE_ATTRIBUTION//所属系统
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

					$("#local_F_GUID").val(result.data.id);
					$("#local_F_SYS_NAME").val(result.data.name);
					$("#local_F_NICK_NAME").val(result.data.alias);
					$("#local_F_DESCRIPTION").val(result.data.description);
					$("#local_F_ENABLED").val(result.data.active);
					$("#local_F_PointType").val(result.data.pointType);
					$("#local_F_ModuleID").val(result.data.moduleID);
					$("#local_F_ChannelIndex").val(result.data.channelIndex);
					$("#local_F_WorkMode").val(result.data.workMode);
					$("#local_F_Polarity").val(result.data.polarity);
					$("#local_F_InitValue").val(result.data.initValue);
					$("#local_F_AlarmActive").val(result.data.alarmActive);
					$("#local_F_AlarmType").val(result.data.alarmType);
					$("#local_F_AlarmPriority").val(result.data.alarmPriority);
					$("#local_F_AlarmTrigger").val(result.data.alarmTrigger);
				}

				fill_underDO();
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			}
		});
		})
	}
	//填充下位机逻辑点信息

		function fill_underDO() {
			basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.DOf_node_attribution(function (data) {
				$.ajax({
					url: _ctx + "/view/basedatamanage/eqmanage/getPointParam",
					type: "post",
					data: {
						tabName 			: "BES_DIGIT_OUPUT",
						f_id 				: $("#f_id").val(),//设备树的id
						fSysName			: $("#f_sys_name").val(),//系统名称
						f_node_attribution 	: data.data.F_NODE_ATTRIBUTION,//所属系统
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
			})
		}
		/* Start add by yangjx at 20191016 for 当数据未点击同步时，可更新DO名称 */
		var beginDoName;
		$(function(){

			beginDoName = $("#f_sys_name").val();//获取进入页面后自动刷新出来的DO名称。以便于同步前更改此名称
			/*进入页面后，根据DO的系统名称查询数据库，若bes_digit_ouput表中无此条对应名字的数据，代表未点击页面同步数据按钮，系统
			名称可更改。若DO表中有此系统名称对应的数据，代表进行了数据同步，则将系统名称设置为只读属性，不可更改*/
			/*$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val(),//DO系统名称
					tableName : "BES_DIGIT_OUPUT",//DO表名/view/basedatamanage/eqmanage
				},
				success : function(result) {
					/!* 如果返回数据，则表明已经同步，将系统名称设置为只读属性 *!/
					if(result>0){
						$("#f_sys_name").attr("readonly", true); //将系统名称设置为只读属性，不可更改
					}
				},
				error : function(result) {
					swal(result.msg, "", "error");
				},
			});*/
		});
	return {
		//获取id
		getNodefGuid : function() {
			return fId;
		},
		DOf_node_attribution:function (callback){
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/f_node_attribution",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val()
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
		},
		//信息发生变更时
		infoChanged : function(boolean) {
			//Start  add by wanghongjie at 20200115 for 统一获取模态框的值
			var f_sys_name		=	$("#f_sys_name").val();
			var f_nick_name		=	$("#f_nick_name").val();
			var f_enabled		=	$('input[name="f_enabled"]:checked').val();
			var f_reversed		=	$('input[name="f_reversed"]:checked').val();
			var f_init_val		=	$("#f_init_val").val();
			if($("#f_init_val").val().length<1){
				basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputInitValNotEffective();
				return;
			}
			var f_description	=	$("#f_description").val();
			var f_work_mode		=	$('input[name="f_work_mode"]:checked').val();
			var f_alarm_enable	=	$('input[name="f_alarm_enable"]:checked').val();
			var f_alarm_type	=	$('input[name="f_alarm_type"]:checked').val();
			var old_f_sys_name	=	$("#f_sys_name_old").val();
			var f_allpath		=	$("#f_allpath").text();
			var f_id			=	$("#f_id").val();
			var attrTabName 	=	"BES_DIGIT_OUPUT";
			var tabName 		=	"BES_SBPZ_STRUCT";
			var f_sbid			=	$("#hidden_f_sbid").val();//设备树id
			var f_close_state	=	$('input[name="f_close_state"]:checked').val();//闭合状态
			if (typeof f_close_state == 'undefined') {//如果闭合状态复选框没有选择,则闭合状态默认为2
				f_close_state = "2";
			}
			var f_fault_state	=	$('input[name="f_fault_state"]:checked').val();//故障状态
			var f_alarm_priority=	$('input[name="f_alarm_priority"]:checked').val();//报警优先级
			var f_node_type		=	$("#f_node_type").val();//DO点  类型

			var nodeLevel 		=	basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel();//选中节点在树上的级数
			var f_node_attribution=	basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution();//所属系统
			var f_yqbh			= 	basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh();//所属园区
			var f_channel_index = 	basedatamanage_eqmanage_eqconfiguration_sbdy.getNodeIndex().toString();//通道索引

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

			if(f_sys_name == "" || f_sys_name ==null){//若取得的数据为空。报错提示
				swal({
					title : '名称不能为空!',// 展示的标题
					text : "",// 内容
					type : "warning",
					showCloseButton : false, // 展示关闭按钮
					allowOutsideClick : false,
					showConfirmButton : false,
					timer : 1000
				});
				return;
			};
			var f_init_val_value=document.getElementById("f_init_val");
			var reg = new RegExp("^[0-9]*$");
			if(!reg.test(f_init_val_value.value)){
				swal({
					title : '请输入初始值为整数!',// 展示的标题
					text : "",// 内容
					type : "warning",
					showCloseButton : false, // 展示关闭按钮
					allowOutsideClick : false,
					showConfirmButton : false,
					timer : 1000
				});
				return;
			}
			if(isEditDefault =="0"){//新增

				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_editDefaultNodeInfo",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_DOInfo_Insert",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						attrTabName 			:	attrTabName,//DO表名
						tabName					: 	tabName,//表名
						f_sbid 					:	f_sbid,//设备树id
						f_enabled 				: 	f_enabled,//使能状态
						f_sys_name				: 	old_f_sys_name,//新增节点的时候,默认的系统名称
						old_f_sys_name			:	f_sys_name,//修改的系统名称
						f_nick_name				: 	f_nick_name,//别 名
						f_description			: 	f_description,//描 述
						f_work_mode 			: 	f_work_mode,//工作模式
						f_reversed 				: 	f_reversed,//是否反向
						f_init_val 				: 	f_init_val,//初始值
						f_alarm_enable 			: 	f_alarm_enable,//报警使能
						f_alarm_type 			: 	f_alarm_type,//报警类型
						f_close_state 			: 	f_close_state,//闭合状态
						f_fault_state 			: 	f_fault_state,//故障状态
						f_alarm_priority 		: 	f_alarm_priority,//报警优先级
						f_node_type 			: 	f_node_type,//DO点  类型
						f_id 					: 	f_id,//设备树的id
						f_allpath				: 	f_allpath,//全路径
						nodeLevel 				: 	nodeLevel,//选中节点在树上的级数
						f_node_attribution 		: 	f_node_attribution,//所属系统
						f_yqbh 					: 	f_yqbh,//所属园区
						f_channel_index			: 	f_channel_index,//通道索引
						f_do_state					: "0"//初始添加默认未同步
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(result) {
						if (result.status=="0"){
							swal({
								title : result.msg,// 展示的标题
								text : "",// 内容
								type: "warning",
								showCloseButton : false, // 展示关闭按钮
								allowOutsideClick : false,
								showConfirmButton : false,
								timer: 1000,
							});
						}
						else if(result.status=="1"){
							swal({
					        	title : result.msg,// 展示的标题
					   			text : "",// 内容
					   			type: "success",
					   			showCloseButton : false, // 展示关闭按钮
					   			allowOutsideClick : false,
					   			showConfirmButton : false,
					   			timer: 1000,
					   		});
 							//var changeNode = $('#tree_sbdy').treeview('search',[$("#hidden_edit_nick_name").val(),{exactMatch: true }]);//刚添加的节点
							var changeNode = ('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNode(), {exactMatch : true} ]);//
							//var changeNode = basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNode();
							addeNodeId = f_sys_name;
							addedNodeText = f_nick_name;
// 							$('#tree_sbdy').treeview('updateNode',[changeNode,{ nodeTreeId:addeNodeId,id:addeNodeId, text:addedNodeText,nodeType:changeNode[0].nodeType, pid:changeNode[0].pid,image:changeNode[0].image}]);
							//var changeNode = basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNode();
							//var index = changeNode.index;
							var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							// $("#tree_sbdy").treeview("removeNode",changeNode);

							if (typeof boolean == "undefined"){
								$("#tree_sbdy").treeview("updateNodeOld", [changeNode[0], {
									nodeTreeId 		: 	changeNode[0].nodeTreeId,
									id			 	: 	changeNode[0].id,
									text 			: 	f_nick_name,
									nodeType 		: 	f_node_type,
									pid 			: 	changeNode[0].pid,
									image 			: 	changeNode[0].image,
									nodeAttribution : 	PNodes[0].nodeAttribution,
									state 			:	{selected :true}
								} ]);
							}


							basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态

							$("#tree_sbdy").treeview("selectNode", changeNode[0]);
							basedatamanage_eqmanage_eqconfiguration_sbdy.getIdMap().remove($("#hidden_edit_sys_name").val());
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId,addeNodeId);
							$("#saveBtn").attr("disabled", true); //【保存】设置为无效
							basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
							$("#f_sys_name").attr("readonly", "readonly");//系统名称
							isEditDefault ="1";
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);

						}else if (result.status=="4") {
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						} else {
							return;
						}
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.refreshSubmitFlg();
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
					},
					complete: function () {
						hiddenLoad();
					},
					error : function(result) {
						swal(result.msg, "", "error");
					}
				});
			}else {

			$.ajax({
				// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
				url : _ctx + "/view/basedatamanage/eqmanage/sbdy_DOInfo_Update",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				type : "post",
				data : JSON.stringify({
					f_id 				: 	f_id,
					f_sbid 				:	f_sbid,//设备树id
					old_f_sys_name 		: 	old_f_sys_name,//DO系统名称,下发到下位机的名称
					f_sys_name 			: 	f_sys_name,//DO系统名称,组成树的名称
					f_nick_name 		: 	f_nick_name,//DO 别 名
					f_enabled 			: 	f_enabled,//使能状态
					f_reversed		 	: 	f_reversed,//是否反向
					f_init_val 			: 	f_init_val,//初始值
					f_node_type 		: 	f_node_type,//DO点  类型
					f_description 		: 	f_description,//DO 描 述
					f_work_mode 		: 	f_work_mode,//工作模式
					f_alarm_enable 		: 	f_alarm_enable,//报警使能
					f_alarm_type 		: 	f_alarm_type,//报警类型
					f_close_state 		: 	f_close_state,//闭合状态
					f_fault_state 		: 	f_fault_state,//故障状态
					f_alarm_priority 	: 	f_alarm_priority,//报警优先级
					f_node_attribution 	: 	f_node_attribution,//所属系统
					f_channel_index		: f_channel_index,//通道索引
					f_do_state					: "0"//初始修改默认未同步
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
							type: "warning",
							showCloseButton: false, // 展示关闭按钮
							allowOutsideClick: false,
							showConfirmButton: false,
							timer: 1500,
						});
					}
					//Start  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
					else if(result.status == '2'){
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
						return;
					}else {
						basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
					}
					if(result.status == '1') {
						swal({
							title: result.msg,// 展示的标题
							text: "",// 内容
							type: "success",
							showCloseButton: false, // 展示关闭按钮
							allowOutsideClick: false,
							showConfirmButton: false,
							timer: 1500,
						});
						basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
					$("#saveBtn").attr("disabled", true); //【保存】设置为无效
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
					}
					//End  add by wanghongjie at 20200115 for 增加修改系统名称时做校验
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
		//同步逻辑点DO
		synPoint : function(){

			var f_sys_name		=	$("#f_sys_name").val();
			var old_f_sys_name	=	$("#f_sys_name_old").val();
			var f_id			=	$("#f_id").val();

				if (!($("#saveBtn").prop("disabled"))) {
					swal({
						title: "数据已修改，请先保存",// 展示的标题
						text: "",// 内容
						type: "warning",
						showCloseButton: true, // 展示关闭按钮
						allowOutsideClick: false,
						showConfirmButton: false,
						timer: 1000
					});
				} else {
					$.ajax({
						// url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/synPoint",
						url: _ctx + "/view/basedatamanage/eqmanage/synchronizePoint",
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						type: "post",
						data: JSON.stringify({
							tabname			:	"bes_digit_ouput",
							f_id 			: 	f_id,
							f_sbid 			:	$("#hidden_f_sbid").val(),//设备树id
							old_f_sys_name 	: 	old_f_sys_name,//DO系统名称,下发到下位机的名称
							f_node_type 	: 	$("#f_node_type").val(),//DO点  类型
						}),
						beforeSend: function () {
							showLoad();
						},
						success: function (result) {
							if (result.status == '0') {
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "error",
									showCloseButton: false, // 展示关闭按钮
									allowOutsideClick: false,
									showConfirmButton: false,
									timer: 1000,
								});
							} else {
								swal({
									title: result.msg,// 展示的标题
									text: "",// 内容
									type: "success",
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
						error: function (result) {
							swal(result.msg, "", "error");
						},
					});
				}
		},

		setSyncPage,

		handleLowerData: function (data)
		{
			var moduleName = "";
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/selectModuleByModuleID",
				type : "post",
				data : {
					moduleID : data.moduleID
				},
				success : function(result) {
					moduleName = result.data;
					$("#under_F_ModuleID").val(moduleName);//所属模块
					($("#under_F_ModuleID").val() != $("#local_F_ModuleID").val()) ? $("#local_F_ModuleID").attr('style', 'color:red') : $("#local_F_ModuleID").removeAttr('style');

				},
			});


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

			var pointType = data.pointType;
			if (pointType === 0){
				pointType = "AI";
			}else if (pointType === 1){
				pointType = "AO";
			}else if (pointType === 2){
				pointType = "DI";
			}else if (pointType === 3){
				pointType = "DO";
			}else {
				pointType = '';
			}

			var workMode = data.workMode;
			if (workMode === 0){
				workMode = "自动";
			}else if (workMode === 1){
				workMode = "手动";
			}else {
				workMode = '';
			}

			var polarity = data.polarity;
			if (polarity === 0){
				polarity = "正向";
			}else if (polarity === 1){
				polarity = "反向";
			}else {
				polarity = '';

			}

			var alarmActive = data.alarmActive;
			if (alarmActive === 0){
				alarmActive = "禁用";
		    }else if (alarmActive === 1){
				alarmActive = "启用";
			}else {
				alarmActive = "";
			}

			var alarmType = data.alarmType;
			if (alarmType === 0){
				alarmType = "不报警";
			}else if (alarmType === 1){
				alarmType = "标准报警";
			}else if (alarmType === 2){
				alarmType = "增强报警";
			}else {
				alarmType = "";
			}

			var alarmPriority = data.alarmPriority;
			if (alarmPriority === 0){
				alarmPriority = "一般";
			}else if (alarmPriority === 1){
				alarmPriority = "较大";
			}else if (alarmPriority === 2){
				alarmPriority = "重大";
			}else {
				alarmPriority = "";
			}

			var alarmTrigger = data.alarmTrigger;
			if (alarmTrigger === 0){
				alarmTrigger = "闭合";
			}else if (alarmTrigger === 1){
				alarmTrigger = "断开";
			}else {
				alarmTrigger = "";
			}

			$("#under_F_GUID").val(data.id);
			$("#under_F_SYS_NAME").val(data.name);
			$("#under_F_NICK_NAME").val(data.alias);
			$("#under_F_DESCRIPTION").val(data.description);
			$("#under_F_ENABLED").val(active);//使能
			$("#under_F_PointType").val(pointType);
			$("#under_F_ChannelIndex").val(data.channelIndex);
			$("#under_F_WorkMode").val(workMode);
			$("#under_F_Polarity").val(polarity);
			$("#under_F_InitValue").val(data.initValue);
			$("#under_F_AlarmActive").val(alarmActive);//报警是否启用
			$("#under_F_AlarmType").val(alarmType);//报警类型
			$("#under_F_AlarmPriority").val(alarmPriority);
			$("#under_F_AlarmTrigger").val(alarmTrigger);


			//判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
			($("#under_F_GUID").val() != $("#local_F_GUID").val() ) ? $("#local_F_GUID").attr('style','color:red') : $("#local_F_GUID").removeAttr('style');
			($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val() ) ? $("#local_F_SYS_NAME").attr('style','color:red') : $("#local_F_SYS_NAME").removeAttr('style');
			($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val() ) ? $("#local_F_NICK_NAME").attr('style','color:red') : $("#local_F_NICK_NAME").removeAttr('style');
			($("#under_F_DESCRIPTION").val() != $("#local_F_DESCRIPTION").val() ) ? $("#local_F_DESCRIPTION").attr('style','color:red') : $("#local_F_DESCRIPTION").removeAttr('style');
			($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val() ) ? $("#local_F_ENABLED").attr('style','color:red') : $("#local_F_ENABLED").removeAttr('style');
			($("#under_F_PointType").val() != $("#local_F_PointType").val() ) ? $("#local_F_PointType").attr('style','color:red') : $("#local_F_PointType").removeAttr('style');

			($("#under_F_ChannelIndex").val() != $("#local_F_ChannelIndex").val() ) ? $("#local_F_ChannelIndex").attr('style','color:red') : $("#local_F_ChannelIndex").removeAttr('style');
			($("#under_F_WorkMode").val() != $("#local_F_WorkMode").val() ) ? $("#local_F_WorkMode").attr('style','color:red') : $("#local_F_WorkMode").removeAttr('style');
			($("#under_F_Polarity").val() != $("#local_F_Polarity").val() ) ? $("#local_F_Polarity").attr('style','color:red') : $("#local_F_Polarity").removeAttr('style');
			($("#under_F_InitValue").val() != $("#local_F_InitValue").val() ) ? $("#local_F_InitValue").attr('style','color:red') : $("#local_F_InitValue").removeAttr('style');
			($("#under_F_AlarmActive").val() != $("#local_F_AlarmActive").val() ) ? $("#local_F_AlarmActive").attr('style','color:red') : $("#local_F_AlarmActive").removeAttr('style');
			($("#under_F_AlarmType").val() != $("#local_F_AlarmType").val() ) ? $("#local_F_AlarmType").attr('style','color:red') : $("#local_F_AlarmType").removeAttr('style');
			($("#under_F_AlarmPriority").val() != $("#local_F_AlarmPriority").val() ) ? $("#local_F_AlarmPriority").attr('style','color:red') : $("#local_F_AlarmPriority").removeAttr('style');
			($("#under_F_AlarmTrigger").val() != $("#local_F_AlarmTrigger").val() ) ? $("#local_F_AlarmTrigger").attr('style','color:red') : $("#local_F_AlarmTrigger").removeAttr('style');

		},
	};
	
})(jQuery, window, document);
</script>