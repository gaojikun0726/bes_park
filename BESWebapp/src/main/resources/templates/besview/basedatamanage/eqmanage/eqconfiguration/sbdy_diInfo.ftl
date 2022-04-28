   <div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath" >${besSbPzStruct.f_allpath}</label>
                        </div>
                        <form role="form" id="sbdy_DIform" name="sbdy_DIform" class="form-horizontal">
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">DI 名 称</label>
                                <div class="col-sm-4">
							<input id="f_guid" value="${returnObject.data.F_GUID}" TYPE="hidden">
							<#--Start  add by wanghongjie at 20200116 for 增加设备树的f_id,传到后台查询该条的详细信息-->
							<input  type="hidden" id="f_id" name="f_id"  value="${besSbPzStruct.f_id}">
							<input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
							<input id="f_sys_name1" value="${besSbPzStruct.f_sys_name}" TYPE="hidden">
                                    <input id="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}" type="text" onkeyup="this.value=this.value.replace(/[^\w_]/g,'');" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">DI 别 名</label>
                                <div class="col-sm-4">
                                    <input id="f_nick_name" name="f_nick_name"required maxlength="32" value="${returnObject.data.F_NICK_NAME}" type="text" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                               <label class="col-sm-2 control-label">使能状态</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_enabled" value="1" checked="checked"
															onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						    		 <input type="radio" name="f_enabled" value="0"
														onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
						    	</div>
                            </div>
                            <div class="has-success">
                               <label class="col-sm-2 control-label">是否反向</label>
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
                               <label class="col-sm-2 control-label">是否有源</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_sourced"  value="1"
															onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>有源
						        	 <input type="radio" name="f_sourced" checked="checked" value="0"
															onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>无源
						    	</div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">DI点类型</label>
                                <div class="col-sm-4">
                                	<input  type="hidden" id="f_node_type" name="f_sbid"  value="${returnObject.data.F_NODE_TYPE}">
                                    <input type="text" id="f_node_type_name"  value="${returnObject.data.F_NODE_TYPE_NAME}" class="form-control" readonly="readonly"
																					 onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth"> 
                         	  <div class="has-success">
                                <label class="col-sm-2 control-label">DI 描述</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description"required value="${returnObject.data.F_DESCRIPTION}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                               </div>
                              <div class="has-success">
                               <label class="col-sm-2 control-label">工作模式</label>
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
                               <label class="col-sm-2 control-label">报警 使能</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_alarm_enable" checked="checked" value="1"
															onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						        	 <input type="radio" name="f_alarm_enable" value="0"
															onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
						    	</div>
                             </div>
                             <div class="has-success">
                               <label class="col-sm-2 control-label">报警 类型</label>
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
                                <label class="col-sm-2 control-label">闭合状态</label>
                                <div class="col-sm-4" id="BoxGroup_di">
                                	 <input type="hidden" id="h_f_close_state" value="${returnObject.data.F_CLOSE_STATE}"/>
                                     <input type="checkbox" name="f_close_state" value="0" disabled="disabled"
																			onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>闭合
																		 <input type="checkbox" name="f_close_state" value="1" disabled="disabled"
																			onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>断开
                                </div>
                            </div>  
                              <div class="has-success">
                               <label class="col-sm-2 control-label">报警优先级</label>
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
						<div class="vertical-timeline-block eqTreeAttrLineWidth">
							<div class="has-success">
								<label class="col-sm-2 control-label">状态</label>
								<div class="col-sm-4">
									<input type="checkbox" id="f_point_state" disabled name="f_point_state" value="${returnObject.data.F_DI_STATE}"
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
												onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.pointValueConfig()">点值配置</button>
                                	<button id="synDataBtn" class="btn sbtreeNodeBtn"
																			onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synPoint();">同步数据</button>
                                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#compareDI">数据对比</button>
<!--                                     <button id="reStartBtn" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.reSet()">重置</button> -->
                                    <button id="saveBtn" class="btn sbtreeNodeBtn" type="submit"
																			onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                               		</span>
                                </div>
                       <input type="hidden" id="sel_f_type"><!-- 定义一个隐藏节点类型 -->
                       <input type="hidden" id="sel_img_url"><!-- 定义一个隐藏图片url -->
                       <input type="hidden" id="ch_f_sys_name"/>
                       <input type="hidden" id="ch_f_nick_name"/>
                       <input type="hidden" id="ch_f_allpath"/>
                       <input type="hidden" id="ch_f_type"/>
                       <input type="hidden" id="ch_f_psys_name"/>
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
<!----DI逻辑点信息对比--->
<div class="modal fade" id="compareDI" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机DI点信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机DI点信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机DI点信息</span></button></div>
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警是否启用<span class="text-comparecoll">:</span></label>
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警优先级<span class="text-comparecoll">:</span></label>
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
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">有源无源<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ActivePassive" name="local_F_ActivePassive" class="form-control" readonly="readonly">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警是否启用<span class="text-comparecoll">:</span></label>
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">报警优先级<span class="text-comparecoll">:</span></label>
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
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">有源无源<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ActivePassive" name="under_F_ActivePassive" class="form-control" readonly="readonly">
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

   <#--点值配置模态框-->
<div id="sbdy_pointValueDIinfoConfigSettingTargetMom" style="display:none;">
    <div id="0" class="form-group remainDiv">
        <div class="col-sm-10 remainDiv">
            <input type="text" value="提示" readonly class="form-control valid" style="text-align:center; width:200px; float:left;">
            <input type="text" value="数值" readonly class="form-control valid" style="text-align:center; width:200px; float:left;margin-left:10px;">
            <i id="addDI" class="fa fa-plus btn btn-default" style="float:left; margin-left:10px;margin-top:5px;"></i>
            <i id="popDI" class="fa fa-minus btn btn-default" style="margin-left:10px;margin-top:5px;"></i>
        </div>
    </div>
    <div class="remainDiv">
        <input id="pointValueConfigSubmitDI" type="button" value="确定" class="btn btn-default" style="margin-top:5px;"
               onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.pointValueConfigSubmitDI();"/>
        <input type="button" value="取消" class="btn btn-default" style="margin-top:5px;margin-right:5px;"
               onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.closeBoxLayer();"/>
    </div>
</div>

<script type="text/javascript">
   var num =0;
   var data1 = {};
   $("i[id='addDI']").click(addDI);
   $("i[id='popDI']").click(popDI);
   var momEle = document.getElementById("sbdy_pointValueDIinfoConfigSettingTargetMom");
   function addDI() {
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
   function popDI() {
	   if (num != 0) {
		   var current_dom = document.getElementById(num);//获取最下面的一行数据
		   momEle.removeChild(current_dom);//将最下面的一行数据删除
		   num--
	   } else {  //若仅有一行，还要继续点击“-”号进行删除操作时，报错提示
		   layer.alert("已无数据删除");
	   }
   }
</script>

<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {

    var layer;
    var indexFont;

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
	//是否有源单选按钮状态设置
	<#if returnObject.data.F_SOURCED == '1'>//是否有源(有源【1】无源【0】)
		$("input[name='f_sourced']:eq(0)").attr("checked", 'checked');
	<#else>
		$("input[name='f_sourced']:eq(1)").attr("checked", 'checked');
	</#if>
	//工作模式单选按钮状态设置
	<#if returnObject.data.F_WORK_MODE != ''>
		<#if returnObject.data.F_WORK_MODE == '1'>//(自动【0】 手动【1】)
			$("input[name='f_work_mode']:eq(0)").attr("checked", 'checked');
		<#else>
			$("input[name='f_work_mode']:eq(1)").attr("checked", 'checked');
		</#if>
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
	//报警优先级

	<#--<#if returnObject.data.F_ALARM_PRIORITY == '0'>
	$("input[name='f_alarm_priority']:eq(0)").attr("checked", 'checked');
	<#else>
	<#if returnObject.data.F_ALARM_PRIORITY == '1'>
	$("input[name='f_alarm_priority']:eq(1)").attr("checked", 'checked');
		<#else>
		$("input[name='f_alarm_priority']:eq(2)").attr("checked", 'checked');
		</#if>
	</#if>-->
	var _ctx = '${ctx}';
	$("#sbdy_DIform").validate({
	});
	var isEditDefault = $("#hidden_edit_status").val();
	if(isEditDefault =="0" && !basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){//添加节点后默认修改属性
		if (!$("#hidden_edit_allpath").val() == "") {//设置全路径
			var allPath = $("#hidden_edit_allpath").val().split(">");
			var s = "";
			for (var i = 0; i < allPath.length - 1; i++) {
				s = s + ">" + allPath[i];
			}
			$("#f_allpath").val(s.substring(1, s.length));
			$("#pre_f_allpath").val(s.substring(1, s.length));
		}
		//$('input[name=f_sys_name]').removeAttr("readonly");//系统名称
		$("#f_node_type").val($("#hidden_edit_node_type").val());//设置点类型
		$("#f_node_type_name").val($("#hidden_edit_node_type_name").val());//设置点类型名称
	}

    layui.use(['layer'],function(){
        layer=layui.layer;
    });

    /*弹出框的关闭按钮  */
    function closeBoxLayer(){
        layer.close(indexFont);
        indexFont = 0;
    }


    $(function() {
		//判断系统名称
		if($("#hidden_edit_sys_name_tree").val() != ""){
			$("#f_sys_name").val($("#hidden_edit_sys_name_tree").val());
		}else{
			$("#f_sys_name").val($("#hidden_edit_sys_name").val());
		}
		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective();//加载完页面后根据报警类型判断其他是否效

			$("#BoxGroup_di").find('input[type=checkbox]').bind('click',function () {
			$(this).attr("checked",true);
			$("#BoxGroup_di").find('input[type=checkbox]').not(this).attr("checked",false);
			});
		//start wanghongjie 刚进入页面判断当前节点的系统名称在DI表里有没有数据,有的话将f_sys_name输入框设置成不能输入
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/info_f_sys_name",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val(),
				tabName    : "bes_digit_input",//表名
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
	$("#compareDI").on('show.bs.modal', function(event) {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#compareDI .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    fill_localDI();
	    // fill_underDI();
	});
	//关闭模态框清空表单值
	$("#compareDI").on('hidden.bs.modal',function(event) {
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
	function fill_localDI(){
		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.DIf_node_attribution(function (data) {


		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/getPointInfoFillLocal",
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			type : "post",
			data : JSON.stringify({
				tabName				: "BES_DIGIT_INPUT",
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
					$("#local_F_ActivePassive").val(result.data.activePassive);
				}

				fill_underDI();
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
	function fill_underDI() {
		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.DIf_node_attribution(function (data) {
			$.ajax({
				url: _ctx + "/view/basedatamanage/eqmanage/getPointParam",
				type: "post",
				data: {
					tabName 			: "BES_DIGIT_INPUT",
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

    function pointValueConfigSetDI()
    {


        var f_guid = $("#f_guid").val();

        if (!f_guid)
        {

            swal({
                title : "请配置点位信息",// 展示的标题
                text : "",// 内容
                type: "error",
                showCloseButton : true, // 展示关闭按钮
                allowOutsideClick : false,
                showConfirmButton : false,
                timer: 1000,
            });
            return;
        }

        indexFont = layer.open({
            type: 1,
            area: ['46vw', '68vh'],
            title: '点值配置',
            content : $('#sbdy_pointValueDIinfoConfigSettingTargetMom'),
            success:function(){
                var $mom = $("#sbdy_pointValueDIinfoConfigSettingTargetMom");
                //在div中,除了 ".remainDiv" 之外的所有元素都移除
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
                f_sys_name : $("#f_sys_name1").val(),//DO节点系统名称
            },
            success : function(result) {
                if(result && result.list){
                    var list = result.list;
                    var momEle = document.getElementById("sbdy_pointValueDIinfoConfigSettingTargetMom");
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

	return {
		//获取id
		getNodefGuid: function () {
			return $("#hidden_id").val();
		},
		addDifferSet: function () {
			if (basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()) {
				$("#f_node_type").val($("#sel_f_type").val());//设置点类型
			}
		},

		DIf_node_attribution: function (callback) {
			$.ajax({
				url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/f_node_attribution",
				type: "post",
				data: {
					f_sys_name: $("#f_sys_name").val()
				},
				beforeSend: function () {
					showLoad();
				},
				success: function (result) {
					callback(result)
				},
				complete: function () {
					hiddenLoad();
				},
				error: function (result) {
					swal(result.msg, "", "error");
				}
			});
		},
		//信息发生变更时
		infoChanged: function (boolean) {
			//Start  add by wanghongjie at 20200115 for 统一获取模态框的值
			var f_sys_name 		= 	$("#f_sys_name_old").val();
			var old_f_sys_name 	= 	$("#f_sys_name").val();
			var f_nick_name 	= 	$("#f_nick_name").val();
			var f_enabled 		= 	$('input[name="f_enabled"]:checked').val();
			var f_reversed 		= 	$('input[name="f_reversed"]:checked').val();
			var f_sourced 		= 	$('input[name="f_sourced"]:checked').val();
			var f_description 	= 	$("#f_description").val();
			var f_work_mode 	= 	$('input[name="f_work_mode"]:checked').val();
			var f_alarm_enable 	= 	$('input[name="f_alarm_enable"]:checked').val();
			var f_alarm_type 	= 	$('input[name="f_alarm_type"]:checked').val();
			var f_allpath 		= 	$("#f_allpath").text();
			var f_id 			= 	$("#f_id").val();
			var f_sbid 			=	$("#hidden_f_sbid").val();//设备id
			var f_node_type		=	$("#f_node_type").val();//DI点  类型
			var f_close_state	=	$('input[name="f_close_state"]:checked').val();//闭合状态
			if (typeof f_close_state == 'undefined') {//如果闭合状态复选框没有选择,则闭合状态默认为2
				f_close_state = "2";
			}
			var f_fault_state	=	$('input[name="f_fault_state"]:checked').val();//故障状态
			var f_alarm_priority=	$('input[name="f_alarm_priority"]:checked').val();//报警优先级
			var nodeLevel		=	basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel();//选中节点在树上的级数
			var f_node_attribution=	basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution();//所属系统
			var f_yqbh			=	basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh();//所属园区
			var type 			= 	$("#f_node_type").val();
			var f_init_val		=	"0";
			var f_channel_index = 	basedatamanage_eqmanage_eqconfiguration_sbdy.getNodeIndex().toString();//通道索引
			//var rel = /^[0-9a-zA-Z]+$/;

			//判断系统名称字符串第一个字符是否为数字,是的话不能添加
			var  f_sys_nameSub = Number(old_f_sys_name.substring(0,1));
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
			if (/.*[\u4e00-\u9fa5]+.*$/.test(old_f_sys_name)){
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

			if (old_f_sys_name == "" || old_f_sys_name == null) {//若取得的数据为空。报错提示
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
			}
			;
			if (isEditDefault == "0") { //先增加默认节点，再修改属性的方式添加逻辑
				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_editDefaultNodeInfo",
					url: _ctx + "/view/basedatamanage/eqmanage/sbdy_DIInfo_Insert",
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					type: "post",
					data: JSON.stringify({
						f_sbid				: f_sbid,//设备id
						f_sys_name			: f_sys_name,//DI系统名称
						f_nick_name			: f_nick_name,//DI 别 名
						f_enabled			: f_enabled,//使能状态
						f_reversed			: f_reversed,//是否反向
						f_sourced			: f_sourced,//是否有源
						f_node_type			: f_node_type,//DI点  类型
						f_description		: f_description,//DI 描 述
						f_work_mode			: f_work_mode,//工作模式
						f_alarm_enable		: f_alarm_enable,//报警使能
						f_alarm_type		: f_alarm_type,//报警类型
						f_close_state		: f_close_state,//闭合状态
						f_fault_state		: f_fault_state,//故障状态
						f_alarm_priority	: f_alarm_priority,//报警优先级
						f_id				: f_id,
						old_f_sys_name		: old_f_sys_name,//新增节点的时候,默认的系统名称
						f_allpath			: f_allpath,//全路径
						nodeLevel			: nodeLevel,//选中节点在树上的级数
						f_node_attribution	: f_node_attribution,//所属系统
						f_yqbh				: f_yqbh,//所属园区
						f_init_val			: f_init_val,//新增的时候,默认值为0
						f_channel_index		: f_channel_index,//通道索引
						f_di_state					: "0"//初始添加默认未同步
					}),
					beforeSend: function () {
						showLoad();
					},
					success: function (result) {

						if (result.status == "0") {
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "warning",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
						} else if (result.status == "1") {
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
							addeNodeId =	old_f_sys_name;
							addedNodeText = f_nick_name;
							var changeNode = basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNode();
							var index = changeNode.index;
							var PNodes = $('#tree_sbdy').treeview('getParents', changeNode);
							// $("#tree_sbdy").treeview("removeNode", changeNode);

							if (typeof boolean == "undefined") {
								/*$("#tree_sbdy").treeview("updateNode", [{
									nodeTreeId: changeNode.nodeTreeId,
									id: changeNode.id,
									text: f_nick_name,
									nodeType: type,
									pid: changeNode.pid,
									image: changeNode.image,
									nodeAttribution: PNodes[0].nodeAttribution,
									state: {selected: true}
								}, PNodes, index]);*/

								$('#tree_sbdy').treeview('updateNode', [ changeNode, {
									nodeTreeId: changeNode.nodeTreeId,
									id: changeNode.id,
									text: f_nick_name,
									nodeType: type,
									pid: changeNode.pid,
									// image: changeNode.image,
									image: "./static/images/sbtreeimage/DI.png",
									nodeAttribution: PNodes[0].nodeAttribution,
									state: {selected: true}
								} ]);
								changeNode.$el.click();

							}
							basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							basedatamanage_eqmanage_eqconfiguration_sbdy.getIdMap().remove($("#hidden_edit_sys_name").val());
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【保存】设置为无效
							basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
							$("#f_sys_name").attr("readonly", "readonly");//系统名称
							isEditDefault = "1";

                            $("#sbdyInfo").load(_ctx +"/view/basedatamanage/eqmanage/sbdy_diInfo",
                                    {
                                        "f_sys_name" : f_sys_name,
                                        "nodeTabName" : 'BES_DIGIT_INPUT',
                                        "f_type":'12'
                                    });//跳转所选节点属性页面
						}
					},
					complete: function () {
						hiddenLoad();
					},
					error: function (result) {
						swal(result.msg, "", "error");
					}
				});
				return;
			} else {//修改节点属性的逻辑
				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
					url: _ctx + "/view/basedatamanage/eqmanage/sbdy_DIInfo_Update",
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					type: "post",
					data: JSON.stringify({
						f_id				: 	f_id,
						f_sbid				: 	f_sbid,//设备id
						f_sys_name			: 	f_sys_name,//DI系统名称
						old_f_sys_name		: 	old_f_sys_name,//DI系统名称
						f_nick_name			: 	f_nick_name,//DI 别 名
						f_enabled			: 	f_enabled,//使能状态
						f_reversed			: 	f_reversed,//是否反向
						f_sourced			: 	f_sourced,//是否有源
						f_node_type			: 	f_node_type,//DI点  类型
						f_description		: 	f_description,//DI 描 述
						f_work_mode			: 	f_work_mode,//工作模式
						f_alarm_enable		: 	f_alarm_enable,//报警使能
						f_alarm_type		: 	f_alarm_type,//报警类型
						f_close_state		: 	f_close_state,//闭合状态
						f_fault_state		: 	f_fault_state,//故障状态
						f_alarm_priority	: 	f_alarm_priority,//报警优先级
						f_node_attribution	: 	f_node_attribution,//所属系统
						f_channel_index		: 	f_channel_index,//通道索引
						f_di_state					: "0"//初始修改默认未同步
					}),
					beforeSend: function () {
						showLoad();
					},
					success: function (result) {
						if (result.status == '2') {
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						} else {
							basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
						}
						if (result.status == "0") {
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "warning",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
						} else if (result.status == '1') {
							swal({
								title: result.msg,// 展示的标题
								text: "",// 内容
								type: "success",
								showCloseButton: false, // 展示关闭按钮
								allowOutsideClick: false,
								showConfirmButton: false,
								timer: 1000,
							});
							basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							$("#saveBtn").attr("disabled", true); //【保存】设置为无效
							basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
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
		},
		//同步逻辑点DI
		synPoint: function () {

			var f_sys_name = $("#f_sys_name").val();
			var old_f_sys_name = $("#f_sys_name_old").val();
			var f_id = $("#f_id").val();

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
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					type: "post",
					data: JSON.stringify({
						tabname			: 	"bes_digit_input",
						f_id			: 	f_id,
						f_sbid			: 	$("#hidden_f_sbid").val(),//设备树id
						old_f_sys_name	: 	old_f_sys_name,//DO系统名称,下发到下位机的名称
						f_node_type		: 	$("#f_node_type").val(),//DO点  类型
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


		handleLowerData: function (data) {
			if (null == data) {
				return;
			}

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

			var active = data.active;

			if (active === 1) {
				active = '使能';
			} else if (active === 0) {
				active = '不使能';
			} else {
				active = '';
			}

			var pointType = data.pointType;
			if (pointType === 0) {
				pointType = "AI";
			} else if (pointType === 1) {
				pointType = "AO";
			} else if (pointType === 2) {
				pointType = "DI";
			} else if (pointType === 3) {
				pointType = "DO";
			} else {
				pointType = '';
			}

			var workMode = data.workMode;
			if (workMode === 0) {
				workMode = "自动";
			} else if (workMode === 1) {
				workMode = "手动";
			} else {
				workMode = '';
			}

			var polarity = data.polarity;
			if (polarity === 0) {
				polarity = "正向";
			} else if (polarity === 1) {
				polarity = "反向";
			} else {
				polarity = '';

			}

			var alarmActive = data.alarmActive;
			if (alarmActive === 0) {
				alarmActive = "禁用";
			} else if (alarmActive === 1) {
				alarmActive = "启用";
			} else {
				alarmActive = "";
			}

			var alarmType = data.alarmType;
			if (alarmType === 0) {
				alarmType = "不报警";
			} else if (alarmType === 1) {
				alarmType = "标准报警";
			} else if (alarmType === 2) {
				alarmType = "增强报警";
			} else {
				alarmType = "";
			}

			var alarmPriority = data.alarmPriority;
			if (alarmPriority === 0) {
				alarmPriority = "一般";
			} else if (alarmPriority === 1) {
				alarmPriority = "较大";
			} else if (alarmPriority === 2) {
				alarmPriority = "重大";
			} else {
				alarmPriority = "";
			}

			var alarmTrigger = data.alarmTrigger;
			if (alarmTrigger === 0) {
				alarmTrigger = "闭合";
			} else if (alarmTrigger === 1) {
				alarmTrigger = "断开";
			} else {
				alarmTrigger = "";
			}

			var activePassive = data.activePassive;
			if (activePassive === 0) {
				activePassive = "无源";
			} else if (activePassive === 1) {
				activePassive = "有源";
			} else {
				activePassive = "";
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
			$("#under_F_ActivePassive").val(activePassive);

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
			($("#under_F_ActivePassive").val() != $("#local_F_ActivePassive").val() ) ? $("#local_F_ActivePassive").attr('style','color:red') : $("#local_F_ActivePassive").removeAttr('style');
		},

        /*点值配置*/
        pointValueConfig() {
            pointValueConfigSetDI();
        },

        /*关闭点配置模态框*/
        closeBoxLayer: function(){
            closeBoxLayer();
        },
        pointValueConfigSubmitDI: function(){ //当配置好提交时，首先校验数据。至少要有一条。完成验证后，将页面"设置"的下拉框更新为配置数据
            //Step1.取得数据，验证数据后，存入map；
            var map = {};//页面所用 // eg：key1:开机,val1:255,key2:关机,val2:0
            var configMap = {};//传到前台，eg： 开机:255,关机=0；
            for(let i=1;i<=num;i++){//获取每一行提示和数值的值，传入map
                let keyValue = $("#key"+i).val();
                let valValue = $("#val"+i).val();
                if(keyValue==""||keyValue==null||valValue==""||valValue==null){//若取得的数据为空。报错提示
                    layer.alert("数据不能为空值");
                    return;
                }
                map["key"+i]= keyValue;
                map["val"+i]= valValue;
                configMap[keyValue]=valValue;//传至后台的map的赋值
            }

            //Step3. TODO 将数据插入节点配置设置表 bes_node_config_setting   step3 暂时注释掉
            //先查询bes_node_config_setting表中有无此系统名称对应的数据，若无，则插入，若有，则更改
            //a.查询表中有无此系统对应的数据
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
                type : "post",
                data : {
                    f_sys_name : $("#f_sys_name1").val(),//DO节点系统名称
                    tableName : "BES_NODE_CONFIG_SETTING",//DO表名
                },
                success : function(result) {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.fill_localDI(function (data) {
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

        fill_localDI:function(callback){

            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfo1",
                type : "post",
                data : {
                    f_nick_name : $("#f_nick_name").val(),
                    f_guid:$("#f_guid").val(),
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

    };

})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->