<div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-3 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-3 control-label">系统名称</label>
                                <div class="col-sm-4">
                                <input id="f_guid" value="${returnObject.data.F_GUID}" TYPE="hidden">
                                <input id="f_sbid" value="${returnObject.data.F_SBID}" TYPE="hidden">
                                <input id="f_id" value="${returnObject.data.F_ID}" TYPE="hidden">
                                <input id="f_accuracy" value="${returnObject.data.F_ACCURACY}" TYPE="hidden">
                                <input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                                	<#--<input  type="hidden" id="f_id" name="f_id"  value="${returnObject.data.F_SBID}">-->
                                    	<input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME}" readonly="readonly" type="text" class="form-control"
                                               onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-3 control-label">别     名</label>
                                <div class="col-sm-4">
                                    <input id="f_nick_name" name="f_nick_name" value="${returnObject.data.F_NICK_NAME}" type="text" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div class="vertical-timeline-block eqTreeAttrLineWidth" >

                            <div class="has-success">
                                <label class="col-sm-3 control-label">虚点类型</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_point_type_selected"  value="${returnObject.data.F_NODE_TYPE}">
                                    <input type="hidden" id="f_point_type_isInput">
                                    <select id="f_point_type_group" class="selector" style="width: 15.5em;height: 2em;"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective(),
                                            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.varInfo();"></select>
                                </div>
                            </div>

                            <div class="has-success">
                                <label class="col-sm-3 control-label">使能状态</label>
                                <input type="hidden" id="hidden_radio_f_enabled"  value="${returnObject.data.F_ENABLED}">
                                <div class="col-sm-4">
                                    <input type="radio" name="f_enabled" checked="checked" value="1"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
                                    <input type="radio" name="f_enabled" value="0"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
                                </div>
                            </div>

                        </div>
                        <div id="unit_accuracy_container" style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth">

                            <div class="has-success">
                                <label class="col-sm-3 control-label">单位</label>
                                <div class="col-sm-4">
                                    <input id="f_engineer_unit" name="f_engineer_unit" value="${returnObject.data.F_ENGINEER_UNIT}" type="text" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>

                            <div class="has-success">
                                <label class="col-sm-3 control-label">精度</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_accuracy_selected"  value="${returnObject.data.F_ACCURACY}" class="form-control">
                                    <select id="f_accuracy_group" name="f_accuracy_group" class="selector" style="width: 15.5em;height: 2em;"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                </div>
                            </div>

                         </div>
                          <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                              <div class="has-success">
                                  <label class="col-sm-3 control-label">初始值</label>
                                  <div class="col-sm-4">
                                      <input type="text" id="f_init_val" digits=true required maxlength="9" value="${returnObject.data.F_INIT_VAL}"  class="form-control"
                                             onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                  </div>
                              </div>

                              <div class="has-success">
                                  <label class="col-sm-3 control-label">描述</label>
                                  <div class="col-sm-4">
                                      <input id="f_description"  value="${returnObject.data.F_DESCRIPTION}" type="text" class="form-control"
                                             onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                  </div>
                              </div>

                           </div>
                           <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                               <label class="col-sm-3 control-label">报警使能</label>
						      	 <div class="col-sm-4">
						       		 <input type="radio" name="f_alarm_enable"  value="1"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
						        	 <input type="radio" name="f_alarm_enable" checked="checked" value="0" onchange="saveBtnIsEffective()"/>否
						    	</div>
                             </div>
                             <div class="has-success">
                               <label class="col-sm-3 control-label">报警类型</label>
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
                         <div style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth" id="div_high_low_limit">
                              <div class="has-success">
                                <label class="col-sm-3 control-label">高限报警</label>
                                <div class="col-sm-4">
                                    <input id="f_high_limit" name="f_high_limit" value="${returnObject.data.F_HIGH_LIMIT}" type="text" class="form-control" readonly="readonly"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-3 control-label">低限报警</label>
                                <div class="col-sm-4">
                                    <input id="f_low_limit" name="f_low_limit" value="${returnObject.data.F_LOW_LIMIT}" type="text" class="form-control" readonly="readonly"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                         	  <div class="has-success" id="div_f_close_state" hidden="hidden">
                                <label class="col-sm-3 control-label">闭合状态</label>
                                <div class="col-sm-4" id="BoxGroup_vDODI">
                                	 <input type="hidden" id="h_f_close_state" value="${returnObject.data.F_CLOSE_STATE}"/>
                                      <input type="checkbox" name="f_close_state" value="0" disabled="disabled"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>闭合
						        	                <input type="checkbox" name="f_close_state" value="1" disabled="disabled"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>断开
                                </div>
                              </div>
                              <div class="has-success" id="alarm_priority_container">
                               <label class="col-sm-3 control-label">报警优先级</label>
						      	 <div class="col-sm-4">
						      	     <input type="hidden" id="h_f_alarm_priority" value="${returnObject.data.F_ALARM_PRIORITY}"/> 
						       		 <input type="radio" name="f_alarm_priority" checked="checked" value="0"  disabled="disabled"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>一般
						        	 <input type="radio" name="f_alarm_priority" value="1"  disabled="disabled"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>较大
						        	 <input type="radio" name="f_alarm_priority" value="2"  disabled="disabled"
                                            onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>重大
						    	</div>
                             </div>
                              
                         </div>

                    <div id="energy_collect_options" class="vertical-timeline-block eqTreeAttrLineWidth"  style = "display:none;">

                        <div class="has-success">
                            <label class="col-sm-3 control-label">能耗采集:</label>
                            <div class="col-sm-4">
                                <input type="radio" id = "f_energyStatics"  name="f_energyStatics" checked="checked" value="0"
                                       onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective() ,
                                       basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.staticsTimeIsEffective()" />是
                                <input type="radio" id = "f_energyStatics" name="f_energyStatics" value="1"
                                       onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective() ,
                                       basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.staticsTimeIsEffective()" />否
                            </div>
                        </div>

                        <div class="has-success">
                            <label class="col-sm-3 control-label">能源类型</label>
                            <div class="col-sm-4">
                                <input type="hidden" id="hidden_f_energy_type"  value="${returnObject.data.F_ENERGY_TYPE}" class="form-control">
                                <select id="f_energy_type" name="f_energy_type" class="selector" style="width: 15.5em;height: 2em;"
                                        onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                            </div>
                        </div>

                    </div>

							<div class="vertical-timeline-block eqTreeAttrLineWidth"  style = "display:block;">

                                <div class="has-success">
                                    <label class="col-sm-3 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <input type="checkbox" id="f_virtual_point_state" disabled name="f_virtual_point_state" value="${returnObject.data.F_SYNC_STATE}"
                                               onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                                        <a id="virtual_point_state"></a>
                                    </div>
                                </div>

                                <div class="has-success" id="div_f_fault_state" style="display: none">
                                    <label class="col-sm-3 control-label">故障状态</label>
                                    <div class="col-sm-4">
                                        <input type="hidden" id="hidden_f_fault_state" value="${returnObject.data.F_FAULT_STATE}"/>
                                        <input type="radio" name="f_fault_state" value="1" disabled="disabled" checked="checked"
                                               onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
                                        <input type="radio" name="f_fault_state" value="0" disabled="disabled"
                                               onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
                                    </div>
                                </div>

							</div>
                                <div style="margin-left: 233px;padding-right: 15px;">
                                   <span>
                                    <button id="pointValueConfigVirtual" style="display: none" class="btn sbtreeNodeBtn" data-toggle="modal">点值配置</button>
									<button id="debugAO" class="btn sbtreeNodeBtn" data-toggle="modal">调试</button>
									<button id="debugDO" class="btn sbtreeNodeBtn"  data-toggle="modal">调试</button>
                                	<button id="synDataBtn" class="btn sbtreeNodeBtn"
                                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synPoint();">同步数据</button>
                                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal">数据对比</button>
                                    <#--<button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#compareVPoint">数据对比</button>-->
                                    <#--<button id="reStartBtn" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.reSet()">重置</button>-->
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
              <input type="hidden" id="ch_f_psys_name"/>
              <input type="hidden" id="hidden_id" value="${returnObject.data.F_GUID}">
              <input type="hidden" id="hidden_f_sbid" value="${besSbPzStruct.f_sbid}">
</div>
<!----逻辑虚点信息对比--->
<div class="modal fade" id="compareVPoint" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机点信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机点信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机点信息</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:480px; overflow-y: scroll; overflow-x:hidden;">
					<form id="local_virtual_Info" name="local_virtual_Info" class="form-horizontal">

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_NODE_TYPE">点类型<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_NODE_TYPE" name="local_F_NODE_TYPE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_SBID">设备ID<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_SBID" name="local_F_SBID" class="form-control" readonly="readonly">
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_ENABLED">使能状态<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_ENABLED" name="local_F_ENABLED" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_SYS_NAME">名称<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_SYS_NAME" name="local_F_SYS_NAME" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_NICK_NAME">别名<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_NICK_NAME" name="local_F_NICK_NAME" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_DESCRIPTION">描述信息<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_DESCRIPTION" name="local_F_DESCRIPTION" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_INIT_VAL">初始值<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_INIT_VAL" digits=true required maxlength="9" name="local_F_INIT_VAL" class="form-control" readonly="readonly">
							</div>
						</div>
						<div class="form-group" style="display: none" id="local_f_engineer_unit">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_ENGINEER_UNIT">单位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_ENGINEER_UNIT" name="local_F_ENGINEER_UNIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group" style="display: none" id = "local_f_accuracy">
							<label class="col-sm-3 control-label" style="width:40%" for="local_F_ACCURACY">精度<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="local_F_ACCURACY" name="local_F_ACCURACY" class="form-control" readonly="readonly">
							</div>
						</div>
					</form>
            	</div>
            	<!----上位机信息结束--->
                   	
            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:400px;height:480px;margin:5px 0 0 36px;overflow-y: scroll; overflow-x:hidden;">
					<form id="under_virtual_Info" name="under_virtual_Info" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="local_F_NODE_TYPE">点类型<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_NODE_TYPE" name="under_F_NODE_TYPE" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_SBID">设备ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_SBID" name="under_F_SBID" class="form-control" readonly="readonly">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_ENABLED">使能状态<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_ENABLED" name="under_F_ENABLED" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_SYS_NAME">名称<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_SYS_NAME" name="under_F_SYS_NAME" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_NICK_NAME">别名<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_NICK_NAME" name="under_F_NICK_NAME" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_DESCRIPTION">描述信息<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_DESCRIPTION" name="under_F_DESCRIPTION" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_INIT_VAL">初始值<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_INIT_VAL" digits=true required maxlength="9" name="under_F_INIT_VAL" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group" style="display: none" id="under_f_engineer_unit">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_ENGINEER_UNIT">单位<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_ENGINEER_UNIT" name="under_F_ENGINEER_UNIT" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group" style="display: none" id = "under_f_accuracy">
                            <label class="col-sm-3 control-label" style="width:40%" for="under_F_ACCURACY">精度<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_ACCURACY" name="under_F_ACCURACY" class="form-control" readonly="readonly">
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

<div  class="modal fade"  id="debugeAOPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" >
	<div class="modal-dialog" style="margin-top: 8%;">
		<div class="modal-content" style="width: 400px;height: 350px">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal-title">AO点调试</h4>
			</div>
			<form role="form" style="width: 100%;height: 500px">
				<div style="margin-top: 50px">
					<div class="has-success">
						<label class="col-sm-3 control-label " style="font-size: 16px;margin-left: 50px">系统名称</label>
						<div class="col-sm-4">
							<input id="f_sys_name_ao" value="${returnObject.data.F_SYS_NAME_OLD}" type="text"
								   class="form-control" readonly="readonly" style="width: 100px">
						</div>
					</div>

				</div>
				<br/>

				<div style="margin-top: 50px;" id="initInputShow">
					<div class="has-success">
						<label class="col-sm-3 control-label" style="font-size: 16px;margin-left: 50px">设置</label>
						<div class="col-sm-2">
                            <input type="hidden" id="hidden_f_struct_idAO" value="${returnObject.data.F_SBID}">
							<input type="text" id="f_init_val_ao" style="width: 100px;margin-left: -18px;"
								   digits=true value="${returnObject.data.F_INIT_VAL}" class="form-control">
						</div>
						<label id="engineer_unit_debug" style="margin-left: 14px; font-size: 20px;">${returnObject.data.F_ENGINEER_UNIT}</label>
					</div>
				</div>


                <div style="margin-top: 50px;display: none" id="selectShow">
                    <div class="has-success">
                        <label class="col-sm-3 control-label" style="font-size: 16px;margin-left: 50px">设置</label>
                        <div class="col-sm-2">
                            <select id="f_init_val_aoSz" class="selector" style="width: 100px;margin-left: -18px;">
                                <option value="255">开机</option>
                                <option value="0">关机</option>
                            </select>
                        </div>
                    </div>
                </div>
				<br/>


				<div style="margin-top: 80px;margin-left: 130px;padding-right: 15px;">
					<span>
						<button id="sysBtn" class="btn sbtreeNodeBtn"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.executeAO()" type="button">执行</button>
						<button id="qxBtn" class="btn sbtreeNodeBtn" data-dismiss="modal" type="button">返回</button>
					    <button id="SZPZ" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#debugAoSZPZPoint"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setVAOEquipmentConfig()" type="button">设置配置</button>
                    </span>
				</div>
			</form>
			</div>
	</div>
</div>


<div  class="modal fade"  id="debugeDOPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" >
	<div class="modal-dialog" style="margin-top: 8%;">
		<div class="modal-content" style="width: 400px;height: 350px">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal-title">DO点调试</h4>
			</div>
			<form role="form" style="width: 100%;height: 500px">

				<div style="margin-top: 50px;margin-left: 70px">
					<div class="has-success">
						<label class="col-sm-3 control-label" style="font-size: 16px">系统名称</label>
						<div class="col-sm-4" style="margin-left: 30px">
                            <input type="hidden" id="hidden_f_struct_idDO" value="${returnObject.data.F_SBID}">
							<input id="f_sys_name_do" value="${returnObject.data.F_SYS_NAME_OLD}" type="text"
								   class="form-control" readonly="readonly" style="width: 100px">
						</div>
					</div>
				</div>
				<br/>

				<div style="margin-top: 50px;margin-left: 70px;" >
					<div class="has-success">
						<label class="col-sm-2 control-label" style="font-size: 15px;margin-left: 15px">设置</label>
						<div class="col-sm-4" style="margin-left: 2px;">
							<select id="f_init_val_do" class="selector" style="width: 103px;height: 30px;border:1px solid green;margin-left: 15px;">
								<option value="255">开机</option>
								<option value="0">关机</option>
							</select>
						</div>
					</div>
				</div>
				<br/>

				<div style="margin-top: 100px;margin-left: 80px;padding-right: 15px;">
					<span>
						<button id="sysBtn" class="btn sbtreeNodeBtn"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.executeDO()" type="button">执行</button>
						<button id="qxBtn" class="btn sbtreeNodeBtn" data-dismiss="modal">返回</button>
						<button id="SZPZ" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#debugSZPZPoint"
                                onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setVDOEquipmentConfig()" type="button">设置配置</button>
					</span>
				</div>
			</form>
		</div>
	</div>
</div>


<div class="modal fade"  id="pointValueConfModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">

    <div class="modal-dialog remainDiv " style="margin-top: 8%;">
        <div class="modal-content remainDiv" style="width: 750px;height: 500px">
            <div class="modal-header bg-primary remainDiv" id="close">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">点值配置</h4>
            </div>
            <form role="form" style="width: 100%;height: 500px">
                <div style="margin-top: 20px" class="remainDiv" >
                    <div id="0" class="form-group remainDiv">
                        <div class="col-sm-10 remainDiv" id="aidiSzpzPoint">
                            <input type="text" value="提示" readonly class="form-control valid" style="text-align:center; width:200px; float:left;">
                            <input type="text" value="数值" readonly class="form-control valid" style="text-align:center; width:200px; float:left;margin-left:10px;">
                            <i id="addAIDI" class="fa fa-plus btn btn-default" style="float:left; margin-left:10px;margin-top:5px;"></i>
                            <i id="popAIDI" class="fa fa-minus btn btn-default" style="margin-left:10px;margin-top:5px;"></i>
                        </div>

                        <div id="close" class="remainDiv">
                            <input id="equipmentConfigSubmit" type="button" value="确定" class="btn btn-default" style="margin-top:5px;"
                                   onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.aidiCustomValue();"/>
                            <input type="button" value="取消" class="btn btn-default" style="margin-top:5px;margin-right:5px;" data-dismiss="modal"/>
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
            </form>
        </div>
    </div>
</div>

<!-- TEST ADD EDIT BEGIN-->
<div class="modal fade"  id="debugSZPZPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">

	<div class="modal-dialog remainDiv " style="margin-top: 8%;">
		<div class="modal-content remainDiv" style="width: 750px;height: 500px">
			<div class="modal-header bg-primary remainDiv" id="close">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal-title">DO点调试</h4>
			</div>
			<form role="form" style="width: 100%;height: 500px">
				<div style="margin-top: 20px" class="remainDiv" >
					<div id="0" class="form-group remainDiv">
						<div class="col-sm-10 remainDiv" id="doSzpzPoint">
							<input type="text" value="提示" readonly class="form-control valid" style="text-align:center; width:200px; float:left;">
							<input type="text" value="数值" readonly class="form-control valid" style="text-align:center; width:200px; float:left;margin-left:10px;">
							<i id="add" class="fa fa-plus btn btn-default" style="float:left; margin-left:10px;margin-top:5px;"></i>
							<i id="pop" class="fa fa-minus btn btn-default" style="margin-left:10px;margin-top:5px;"></i>
						</div>

						<div id="close" class="remainDiv">
							<input id="equipmentConfigSubmit" type="button" value="确定" class="btn btn-default" style="margin-top:5px;"
							onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.debugCustomValue();"/>
							<input type="button" value="取消" class="btn btn-default" style="margin-top:5px;margin-right:5px;" data-dismiss="modal"
							/>
						</div>
					</div>
				</div>
				<br/>
				<br/>
			</form>
		</div>
	</div>
</div>

<!-- TEST ADD EDIT BEGIN-->
<div class="modal fade"  id="debugAoSZPZPoint" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">

    <div class="modal-dialog remainDiv" style="margin-top: 8%;">
        <div class="modal-content remainDiv" style="width: 750px;height: 500px">
            <div class="modal-header bg-primary remainDiv" id="close">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">AO点调试</h4>
            </div>
            <form role="form" style="width: 100%;height: 500px">
                <div style="margin-top: 20px" class="remainDiv">
                    <div id="0" class="form-group remainDiv">
                        <div class="col-sm-10 remainDiv" id="aoSzpzPoint">
                            <input type="text" value="提示" readonly class="form-control valid" style="text-align:center; width:200px; float:left;">
                            <input type="text" value="数值" readonly class="form-control valid" style="text-align:center; width:200px; float:left;margin-left:10px;">
                            <i id="addAO" class="fa fa-plus btn btn-default" style="float:left; margin-left:10px;margin-top:5px;"></i>
                            <i id="popAO" class="fa fa-minus btn btn-default" style="margin-left:10px;margin-top:5px;"></i>
                        </div>
                        <div id="close" class="remainDiv">
                            <input id="equipmentConfigSubmit" type="button" value="确定" class="btn btn-default" style="margin-top:5px;"
                                   onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.debugAoCustomValue();"/>
                            <input type="button" value="取消" class="btn btn-default" style="margin-top:5px;margin-right:5px;" data-dismiss="modal"
                            />
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
            </form>
        </div>
    </div>
</div>

<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
<script type="text/javascript">

	var num =0;
	var data1 = {};
	$("i[id='add']").click(add);
	$("i[id='pop']").click(pop);
	var momEle1 = document.getElementById("doSzpzPoint");
    var momEle2 = document.getElementById("aoSzpzPoint");
    var momEle3 = document.getElementById("aidiSzpzPoint");
	function add() {
		num++;
		var divEle1 = document.createElement("div");
		divEle1.setAttribute("class", "form-group");
		divEle1.setAttribute("id", num);
		$(document).off('focusin.model');
		var inner1= `<div class="col-sm-10" style="margin-top:10px;margin-left: -17px"><input type="text" value="" id=`+"key"+num +` class="form-control" style="text-align:center;width:200px; float:left;">
							  <input type="number" value="" id=`+"val"+num +` class="form-control" style="text-align:center;width:200px;margin-left:210px;"</div>`;
		divEle1.innerHTML = inner1;
		momEle1.appendChild(divEle1);
	}
	function pop() {
		if (num != 0) {
			var current_dom = document.getElementById(num);//获取最下面的一行数据
			momEle1.removeChild(current_dom);//将最下面的一行数据删除
			num--
		} else {  //若仅有一行，还要继续点击“-”号进行删除操作时，报错提示
			layer.alert("已无数据删除");
		}
	}

    $("i[id='addAO']").click(addAO);
    $("i[id='popAO']").click(popAO);
    function addAO() {
        num++;
        var divEleAO = document.createElement("div");
        divEleAO.setAttribute("class", "form-group");
        divEleAO.setAttribute("id", num);
        $(document).off('focusin.model');
        var innerAO = `<div class="col-sm-10" style="margin-top:10px;margin-left: -17px"><input type="text" value="" id=`+"key"+num +` class="form-control" style="text-align:center;width:200px; float:left;">
							  <input type="number" value="" id=`+"val"+num +` class="form-control" style="text-align:center;width:200px;margin-left:210px;"</div>`;
        divEleAO.innerHTML = innerAO;
        momEle2.appendChild(divEleAO);
    }
    function popAO() {
        if (num != 0) {
            var current_dom = document.getElementById(num);//获取最下面的一行数据
            momEle2.removeChild(current_dom);//将最下面的一行数据删除
            num--
        } else {  //若仅有一行，还要继续点击“-”号进行删除操作时，报错提示
            layer.alert("已无数据删除");
        }
    }

    $("i[id='addAIDI']").click(addAIDI);
    $("i[id='popAIDI']").click(popAIDI);
    function addAIDI() {
        num++;
        var divEleAIDI = document.createElement("div");
        divEleAIDI.setAttribute("class", "form-group");
        divEleAIDI.setAttribute("id", num);
        $(document).off('focusin.model');
        var innerAIDI = `<div class="col-sm-10" style="margin-top:10px;margin-left: -17px"><input type="text" value="" id=`+"key"+num +` class="form-control" style="text-align:center;width:200px; float:left;">
							  <input type="number" value="" id=`+"val"+num +` class="form-control" style="text-align:center;width:200px;margin-left:210px;"</div>`;
        divEleAIDI.innerHTML = innerAIDI;
        momEle3.appendChild(divEleAIDI);
    }
    function popAIDI() {
        if (num != 0) {
            var current_dom = document.getElementById(num);//获取最下面的一行数据
            momEle3.removeChild(current_dom);//将最下面的一行数据删除
            num--
        } else {  //若仅有一行，还要继续点击“-”号进行删除操作时，报错提示
            layer.alert("已无数据删除");
        }
    }

	function attention(){
		$.ajax({
			url : _ctx +"/view/basedatamanage/eqmanage/selectNodesConfigSetting",
			type : "post",
			data : {
				f_sys_name :$("#f_sys_name").val()
			},
			success : function(result){
				var resultList = result.list;
				if (!resultList){
				    return;
                }
				var dataNum = resultList.length;
				if(dataNum>0){//如果对do节点进行了设置配置，进行处理
					$("#f_init_val_do").empty();//首先清空下拉列表中页面默认的选项
					for(var i=0;i<dataNum;i++){//将下拉框设置为配置的数据
						var selectSettingValueLeaderConfig = document.getElementById("f_init_val_do");
						var configSetting=document.createElement("option");
						var text = result.list[i].F_DESC;
						configSetting.text = result.list[i].F_DESC;
						configSetting.value=result.list[i].F_VALUE;
						selectSettingValueLeaderConfig.appendChild(configSetting);
						$("#saveBtn").attr("disabled", true); //【保存】设置为无效
					}
				}
			},
			error : function(result){
				swal(result.msg, "", "error");
			}
		});
	}

    function attentionAo(){
        $.ajax({
            url : _ctx +"/view/basedatamanage/eqmanage/selectNodesConfigSetting",
            type : "post",
            data : {
                f_sys_name :$("#f_sys_name").val()
            },
            success : function(result){
                var resultList = result.list;
                var dataNum = resultList.length;
                var selectDiv = document.getElementById("selectShow");
                var inputDiv = document.getElementById("initInputShow");
                //如果设置配置了数据那么显示设置配置的下拉框,如果没有进行设置配置，那么显示设置百分比
                if (dataNum == 0 || dataNum == null || dataNum == undefined || dataNum == ""){
                    inputDiv.style.display = "block";
                    selectDiv.style.display = "none";
                }else if(dataNum>0){//如果对do节点进行了设置配置，进行处理
                    inputDiv.style.display = "none";
                    selectDiv.style.display = "block";
                    $("#f_init_val_aoSz").empty();//首先清空下拉列表中页面默认的选项
                    for(var i=0;i<dataNum;i++){//将下拉框设置为配置的数据
                        var selectSettingValueLeaderConfig = document.getElementById("f_init_val_aoSz");
                        var configSetting=document.createElement("option");
                        var text = result.list[i].F_DESC;
                        configSetting.text = result.list[i].F_DESC;
                        configSetting.value=result.list[i].F_VALUE;
                        selectSettingValueLeaderConfig.appendChild(configSetting);
                        $("#saveBtn").attr("disabled", true); //【保存】设置为无效
                    }
                }
            },
            error : function(result){
                swal(result.msg, "", "error");
            }
        });
    }
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {

	//能耗统计单选按钮状态设置
	<#if returnObject.data.F_ENERGYSTATICS == '1'>//能耗统计(是【0】 否【1】)
	$("input[name='f_energyStatics']:eq(1)").attr("checked", 'checked');
	$('#f_energy_type').attr("disabled", 'disabled');
	/*$("input[name='f_staticsTime']:eq('0')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('1')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('2')").attr("disabled", true);*/
	<#else >
	<#if returnObject.data.F_ENERGYSTATICS == '0'>
	$("input[name='f_energyStatics']:eq(0)").attr("checked", 'checked');
	$('#f_energy_type').removeAttr("checked");
	<#else>
	$("input[name='f_energyStatics']:eq(1)").attr("checked", 'checked');
	/*$("input[name='f_staticsTime']:eq('0')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('1')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('2')").attr("disabled", true);*/
	</#if >
	</#if>
	//能耗统计周期按钮状态设置
	<#--<#if returnObject.data.F_STATICSTIME == '0'>//能耗统计(15分钟【0】 1小时【1】1天【2】)
	$("input[name='f_staticsTime']:eq(0)").attr("checked", 'checked');
	<#else >
	<#if returnObject.data.F_STATICSTIME == '1'>
	$("input[name='f_staticsTime']:eq(1)").attr("checked", 'checked');
	<#else>
	<#if returnObject.data.F_STATICSTIME == '2'>
	$("input[name='f_staticsTime']:eq(2)").attr("checked", 'checked');
	<#else>
	$("input[name='f_staticsTime']:eq('0')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('1')").attr("disabled", true);
	$("input[name='f_staticsTime']:eq('2')").attr("disabled", true);
	</#if>
	</#if >
	</#if>-->
	//使能状态
	<#if returnObject.data.F_ENABLED == '1'>//使能状态(不使能【0】 使能【1】)
		$("input[name='f_enabled']:eq(0)").attr("checked", 'checked');
	</#if>
	<#if returnObject.data.F_ENABLED == '0'>
		$("input[name='f_enabled']:eq(1)").attr("checked", 'checked');
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
		</#if>
		<#if returnObject.data.F_ALARM_TYPE == '2'>
			$("input[name='f_alarm_type']:eq(2)").attr("checked", 'checked');
		</#if>
	</#if>


	//虚点类型控制调试按钮
	//5是AO,AO显示调试按钮
	<#if returnObject.data.F_NODE_TYPE== '5'>
	$("#debugAO").show();
	<#else>
	$("#debugAO").hide();
	</#if>
	//7是DO,DO显示调试按钮
	<#if returnObject.data.F_NODE_TYPE == '7'>
	$("#debugDO").show();
	<#else>
	$("#debugDO").hide();
	</#if>
	var _ctx = '${ctx}';

    $("#f_init_val_ao").val($("#f_accuracy_selected").val());
    $("#f_init_val_aoSz").val($("#f_accuracy_selected").val());

	function loadVpointTypeInfo(){
		$.ajax({ //虚点类型下拉列表获取虚点类型信息
			url: _ctx + "/view/basedatamanage/eqmanage/sbdy_getVpointTypeInfo",
			contentType: "application/json; charset=utf-8",
			type: "get",
			beforeSend: function () {
				showLoad();
			},
			success: function(result) {

				if(result.status == "1"){
					//虚点类型下拉列表设置
					var pointTypes = result.list;
					for (var i = 0; i < pointTypes.length; i++) {
						var x = document.getElementById("f_point_type_group");
						var obj = document.createElement("option");
						obj.value = pointTypes[i].id;
						obj.text = pointTypes[i].fVpointType;
						obj.setAttribute("isInput", pointTypes[i].fIsInput);
						x.appendChild(obj);
						if ($("#f_point_type_selected").val() == pointTypes[i].id) {//设置当前选中的设备型号
							obj.selected = true;
							$("#f_point_type_isInput").val(pointTypes[i].fIsInput);
							// BtnDisplayAndBlock(pointTypes)
						}
					}
				}
			},
			complete: function () {
				hiddenLoad();
			},
			error: function(result) {
				alert("获取虚点类型失败！");
			}
		});
	};

	function loadEnergyType(){
		$.ajax({ //虚点类型下拉列表获取虚点类型信息
            type : "post",
            url : _ctx + '/view/basedatamanage/energyinformation/getgllist',
            data:{
                f_yqbh: basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh(),//所属园区
            },
			success: function(result) {

				var list = result.list;

				if (!Array.isArray(list))
				{
                    return;
				}

				var html = [];

				var fEnergyType = $('#hidden_f_energy_type').val();

                for (var i = 0; i < list.length; i++)
				{
				    var item = list[i];
				    var fNybh = item.F_NYBH;
				    var fNymc = item.F_NYMC;

				    if (fEnergyType === fNybh)
					{
                        html.push('<option selected value="'+ fNybh +'">'+ fNymc +'</option>');
                        continue;
					}

				    html.push('<option value="'+ fNybh +'">'+ fNymc +'</option>');
				}

                $('#f_energy_type').html(html.join(''));

			},
			error: function(result) {
				alert("获取虚点类型失败！");
			}
		});
	};

	//精度下拉列表设置（默认选中由数据库决定）
	function addAccuracyGroup() {

	//填充精度下拉框
	    $("#f_accuracy_group").empty();
		for (var i = 0; i < 7; i++) {
			var x = document.getElementById("f_accuracy_group");
			var obj = document.createElement("option");
			obj.value = i;
			obj.text = i;
			x.appendChild(obj);
			if ($("#f_accuracy_selected").val() == i) {//设置当前选中的设备型号
				obj.selected = true;
			}
		}
	};
	$(function() {
		loadVpointTypeInfo();//填充虚点类型下拉列表
		//addAccuracyGroup();//填充精度下拉列表
		basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.alarmIsEffective();//加载完页面后根据报警类型判断其他是否效
		setTimeout(function() {
		    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.varInfo();
		    if ($('#hidden_f_fault_state').val() == '0')
			{
                $("input[name='f_fault_state'][value='0']").prop('checked', true);

			}else
			{
                $("input[name='f_fault_state'][value='1']").prop('checked', true);
			}
		    }, 100);

    $("#BoxGroup_vDODI").find('input[type=checkbox]').bind('click',function () {
      $(this).attr("checked",true);
      $("#BoxGroup_vDODI").find('input[type=checkbox]').not(this).attr("checked",false);
    });

        setCheckbox();//设置状态

        loadEnergyType();

	});

    $('#dataContrast').click(function ()
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
            $('#compareVPoint').modal('show')
        }
    });

    $('#debugAO').click(function ()
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
            $('#debugeAOPoint').modal('show');
            $('#engineer_unit_debug').text($('#f_engineer_unit').val());
            $('#f_init_val_ao').val($('#f_init_val').val());
            //对AO点位下拉框的设置
            attentionAo();
        }
    });

    $('#debugDO').click(function ()
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
            $('#debugeDOPoint').modal('show');
            attention();
        }
    });

    $('#pointValueConfigVirtual').click(function ()
    {
        equipmentConfigSetVAIDI();
        $('#pointValueConfModal').modal('show');
    });

    $('#pointValueConfModal').on('hide.bs.modal', function () {
        $('#aidiSzpzPoint .form-group').remove();
    });

    $('#debugSZPZPoint').on('hide.bs.modal', function () {
        $('#doSzpzPoint .form-group').remove();
    });

    $('#debugAoSZPZPoint').on('hide.bs.modal', function () {
        $('#aoSzpzPoint .form-group').remove();
    });

	//居中显示（逻辑点信息对比）
 	$('#compareVPoint').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#compareVPoint .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });
	})
	//逻辑点信息对比模态框出现前加载(可拖动)
	$("#compareVPoint").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    fill_localVPoint();

	});

	$('input[name=f_alarm_type]').click(function (obj)
    {

        var value = obj.target.value;

        var isInput =  $("#f_point_type_group option:checked").attr("isInput");
        
        if ("1" === value && "1" === isInput)
		{
			$('#div_high_low_limit').show();
		}else
		{
            $('#div_high_low_limit').hide();
		}

    });

    //状态设置
    function setCheckbox(){
        //同步状态

        if($("#f_virtual_point_state").val() == "1"){
            setSyncPage(true);
        }else{
            setSyncPage(false);
        }

    }

    /* Start add by xiepufeng at 2020年5月26日 */
    function setSyncPage(state)
    {

        if (typeof state !== 'boolean')
        {
            return;
        }

        if (state)
        {
            $("#f_virtual_point_state").prop("checked",true);
            $("#virtual_point_state").text("已同步");
            $("#virtual_point_state").attr("style","color: #00ff2d");
        } else
        {
            $("#f_virtual_point_state").prop("checked",false);
            $("#virtual_point_state").text("未同步");
            $("#virtual_point_state").attr("style","color: #ff0000");
        }
    }

	function fill_localVPoint(){
		$.issp_ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getVirtualPointInfo",
			type : "get",
			data : {
				f_sys_name : $("#f_sys_name").val(),
			},
			success : function(result) {

			    if (!result)
				{
					return;
				}

				if(result.status === '0'){
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

			        var data = result.data;

			        if (!data)
					{
						return;
					}

					var f_node_type = data.F_NODE_TYPE;
			        var f_sbid = data.F_SBID;
			        var f_enabled = data.F_ENABLED;
			        var f_sys_name = data.F_SYS_NAME;
			        var f_nick_name = data.F_NICK_NAME;
			        var f_description = data.F_DESCRIPTION;
			        // var f_init_val = data.F_INIT_VAL /(Math.pow(10, (data.F_ACCURACY)));
			        var f_init_val = data.F_INIT_VAL;
			        /*var f_alarm_enable = data.F_ALARM_ENABLE;
			        var f_alarm_type = data.F_ALARM_TYPE;
			        var f_alarm_priority = data.F_ALARM_PRIORITY;*/
			        switch (f_node_type)
					{
						case "4":
                            f_node_type = 'VAI';

                            // 单位
                            $('#local_f_engineer_unit').show();
                            $('#under_f_engineer_unit').show();

                            $('#local_F_ENGINEER_UNIT').val(data.F_ENGINEER_UNIT);
                            // 精度
                            $('#local_f_accuracy').show();
                            $('#under_f_accuracy').show();

                            $('#local_F_ACCURACY').val(data.F_ACCURACY);

                            /*
                            // 高限告警
                            $('#local_f_high_limit').show();
                            $('#under_f_high_limit').show();

                            $('#local_F_HIGH_LIMIT').val(data.F_HIGH_LIMIT);

                            // 底限报警
                            $('#local_f_low_limit').show();
                            $('#under_f_low_limit').show();

                            $('#local_F_LOW_LIMIT').val(data.F_LOW_LIMIT);

                            // 告警闭合状态
                            $('#local_f_close_state').hide();
                            $('#under_f_close_state').hide();*/

						    break;
						case "5":
                            f_node_type = 'VAO';

                            // 单位
                            $('#local_f_engineer_unit').show();
                            $('#under_f_engineer_unit').show();

                            $('#local_F_ENGINEER_UNIT').val(data.F_ENGINEER_UNIT);
                            // 精度
                            $('#local_f_accuracy').show();
                            $('#under_f_accuracy').show();

                            $('#local_F_ACCURACY').val(data.F_ACCURACY);

                            // 高限告警
                            /*$('#local_f_high_limit').show();
                            $('#under_f_high_limit').show();

                            $('#local_F_HIGH_LIMIT').val(data.F_HIGH_LIMIT);

                            // 底限报警
                            $('#local_f_low_limit').show();
                            $('#under_f_low_limit').show();

                            $('#local_F_LOW_LIMIT').val(data.F_LOW_LIMIT);

                            // 告警闭合状态
                            $('#local_f_close_state').hide();
                            $('#under_f_close_state').hide();*/
						    break;
						case "6":
                            f_node_type = 'VDI';
                            // 单位
                            $('#local_f_engineer_unit').hide();
                            $('#under_f_engineer_unit').hide();
                            // 精度
                            $('#local_f_accuracy').hide();
                            $('#under_f_accuracy').hide();

                            // 高限告警
                            /*$('#local_f_high_limit').hide();
                            $('#under_f_high_limit').hide();

                            // 底限报警
                            $('#local_f_low_limit').hide();
                            $('#under_f_low_limit').hide();

                            // 告警闭合状态
                            $('#local_f_close_state').show();
                            $('#under_f_close_state').show();

                            $('#local_F_CLOSE_STATE').val(data.F_CLOSE_STATE);*/
						    break;
						case "7":
                            f_node_type = 'VDO';
                            // 单位
                            $('#local_f_engineer_unit').hide();
                            $('#under_f_engineer_unit').hide();
                            // 精度
                            $('#local_f_accuracy').hide();
                            $('#under_f_accuracy').hide();

                            // 高限告警
                           /* $('#local_f_high_limit').hide();
                            $('#under_f_high_limit').hide();

                            // 底限报警
                            $('#local_f_low_limit').hide();
                            $('#under_f_low_limit').hide();

                            // 告警闭合状态
                            $('#local_f_close_state').show();
                            $('#under_f_close_state').show();*/
						    break;
					}

                    $("#local_F_NODE_TYPE").val(f_node_type);
                    $("#local_F_SBID").val(f_sbid);

                    if (f_enabled == 1)
					{
                        f_enabled = '是';
					}else
					{
                        f_enabled = '否';
					}

                    $("#local_F_ENABLED").val(f_enabled);
                    $("#local_F_SYS_NAME").val(f_sys_name);
                    $("#local_F_NICK_NAME").val(f_nick_name);
                    $("#local_F_DESCRIPTION").val(f_description);
                    $("#local_F_INIT_VAL").val(f_init_val);
				}

                fill_underVPoint();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			}
		});
	}

	function fill_underVPoint(){
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/compareVirtualPoint",
			type : "get",
			data : {
				f_sys_name : $("#f_sys_name").val()
			},
			success : function(result) {
				if(result.status === '1'){

					swal({
						title : result.msg,// 展示的标题
						text : "",// 内容
						type: "success",
						showCloseButton : false, // 展示关闭按钮
						allowOutsideClick : false,
						showConfirmButton : false,
						timer: 1000,
					});

					}else{

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
				}
			});
		}

		function debuggerButtonIsShow(pointType)
        {
            if (!pointType)
			{
				return;
			}

            if (pointType == '5'){
                $("#debugAO").show();
                $("#debugDO").hide();
            }else if (pointType == '7'){
                $("#debugDO").show();
                $("#debugAO").hide();
            }else{
                $("#debugDO").hide();
                $("#debugAO").hide();
            }
        }

    /* Start add for 设备配置弹出框 */
    function equipmentConfigSetVDO(){
        var $mom = $("#debugSZPZPoint");
        $mom.find("div:not('.remainDiv')").remove();
        queryConfigDataVDO();
    }
    /* Start add for 设备配置弹出框 */
    function equipmentConfigSetVAO(){
        var $mom = $("#debugAoSZPZPoint");
        $mom.find("div:not('.remainDiv')").remove();
        queryConfigDataVAO();
    }

    function equipmentConfigSetVAIDI(){
        var $mom = $("#pointValueConfModal");
        $mom.find("div:not('.remainDiv')").remove();
        queryConfigDataVAIDI();
    }

    /**
     * 查询配置数据用于回显
     * **/
    function queryConfigDataVDO(){
        num = 0;
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/selectNodesConfigSetting",
            type : "post",
            data : {
                f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
            },
            success : function(result) {
                if(result && result.list){
                    var list = result.list;
                    var momEle = document.getElementById("doSzpzPoint");
                    for(var i = 0; i < list.length; i++){
                        num++;
                        var divEle = document.createElement("div");
                        divEle.setAttribute("class", "form-group");
                        divEle.setAttribute("id", num);
                        var inner = '<div class="col-sm-10" style="margin-top:10px; padding-left: 0px">'+
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

    /**
     * 查询配置数据用于回显
     * **/
    function queryConfigDataVAO(){
        num = 0;
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/selectNodesConfigSetting",
            type : "post",
            data : {
                f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
            },
            success : function(result) {
                if(result && result.list){
                    var list = result.list;
                    var momEle = document.getElementById("aoSzpzPoint");
                    for(var i = 0; i < list.length; i++){
                        num++;
                        var divEle = document.createElement("div");
                        divEle.setAttribute("class", "form-group");
                        divEle.setAttribute("id", num);
                        var inner = '<div class="col-sm-10" style="margin-top:10px; padding-left: 0px">'+
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
    /**
     * 查询配置数据用于回显
     * **/
    function queryConfigDataVAIDI(){
        num = 0;
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/selectNodesConfigSetting",
            type : "post",
            data : {
                f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
            },
            success : function(result) {
                if(result && result.list){
                    var list = result.list;
                    var momEle = document.getElementById("aidiSzpzPoint");
                    for(var i = 0; i < list.length; i++){
                        num++;
                        var divEle = document.createElement("div");
                        divEle.setAttribute("class", "form-group");
                        divEle.setAttribute("id", num);
                        var inner = '<div class="col-sm-10" style="margin-top:10px; padding-left: 0px">'+
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
        setVDOEquipmentConfig:function(){
            equipmentConfigSetVDO();
        },
        setVAOEquipmentConfig:function(){
            equipmentConfigSetVAO();
        },
        debugCustomValue:function() {
			//Step1.取得数据，验证数据后，存入map；
			var map = {};//页面所用 // eg：key1:开机,val1:255,key2:关机,val2:0
			var configMap = {};//传到前台，eg： 开机:255,关机=0；

            for(var i=1;i<=num;i++){ //获取每一行提示和数值的值，传入map
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
			$("#f_init_val_do").empty();//首先清空下拉列表中的选项
			var mapLength = (Object.keys(map).length)/2; //取得map的长度的一半(reason:key和val 都占一份)
			for(var j=1;j<=mapLength;j++){
				var selectSettingValueLeader = document.getElementById("f_init_val_do");
				var configSettingObj=document.createElement("option");
				var keyJ = map["key"+j];
				configSettingObj.text = keyJ;
				var valJ = map["val"+j];
				configSettingObj.value=valJ;
				selectSettingValueLeader.appendChild(configSettingObj);
			}
			//Step3. TODO 将数据插入节点配置设置表 bes_node_config_setting   step3 暂时注释掉
			//先查询bes_node_config_setting表中有无此系统名称对应的数据，若无，则插入，若有，则更改
			//a.查询表中有无此系统对应的数据
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
					tableName : "BES_NODE_CONFIG_SETTING",//DO表名
				},
				success : function(result) {
						if(result>0){//如果返回数据，表名表中已有此条系统名称相关的数据，则将节点配置设置表的数据更新(删除后插入)；
							$.ajax({
								url : _ctx+"/view/basedatamanage/eqmanage/updateNodesConfigSetting",
								type : "post",
								contentType : "application/json; charset=utf-8",
								dataType : "json",
								data : JSON.stringify({
									/*f_sys_name : $("#f_sys_name").val(),*/
									f_sys_name : $("#f_sys_name").val(),
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

                                        $('#f_init_val_do').html('<option value="255">开机</option>' +
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
									f_sys_name :$("#f_sys_name").val(),
									map: map,
									config_num: num
								}),
								success: function (result) {
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
								error: function (result) {
									layer.alert("设置失败");
								}
							});

						}
				},
				error : function(result) {
					swal(result.msg, "", "error");
				},
			});
			//Step4. 点击确定验证成功后，自动关闭弹出框
			$('#debugSZPZPoint').modal('hide')
			// $('#debugeDOPoint').modal('hide')
		},

//Ao点的设置配置
        debugAoCustomValue:function() {
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
            $("#f_init_val_aoSz").empty();//首先清空下拉列表中的选项
            var inputDiv = document.getElementById("initInputShow");
            inputDiv.style.display = "none";
            var selectDiv = document.getElementById("selectShow");
            selectDiv.style.display = "block";
            var mapLength = (Object.keys(map).length)/2; //取得map的长度的一半(reason:key和val 都占一份)
            for(var j=1;j<=mapLength;j++){
                var selectSettingValueLeader = document.getElementById("f_init_val_aoSz");
                var configSettingObj=document.createElement("option");
                var keyJ = map["key"+j];
                configSettingObj.text = keyJ;
                var valJ = map["val"+j];
                configSettingObj.value=valJ;
                selectSettingValueLeader.appendChild(configSettingObj);
            }
            //Step3. TODO 将数据插入节点配置设置表 bes_node_config_setting   step3 暂时注释掉
            //先查询bes_node_config_setting表中有无此系统名称对应的数据，若无，则插入，若有，则更改
            //a.查询表中有无此系统对应的数据
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
                type : "post",
                data : {
                    f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
                    tableName : "BES_NODE_CONFIG_SETTING",//DO表名
                },
                success : function(result) {
                    if(result>0){//如果返回数据，表名表中已有此条系统名称相关的数据，则将节点配置设置表的数据更新(删除后插入)；
                        $.ajax({
                            url : _ctx+"/view/basedatamanage/eqmanage/updateNodesConfigSetting",
                            type : "post",
                            contentType : "application/json; charset=utf-8",
                            dataType : "json",
                            data : JSON.stringify({
                                /*f_sys_name : $("#f_sys_name").val(),*/
                                f_sys_name : $("#f_sys_name").val(),
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
                                    $('#initInputShow').show();
                                    $('#selectShow').hide();
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
                                f_sys_name :$("#f_sys_name").val(),
                                map: map,
                                config_num: num
                            }),
                            success: function (result) {
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
                            error: function (result) {
                                layer.alert("设置失败");
                            }
                        });

                    }
                },
                error : function(result) {
                    swal(result.msg, "", "error");
                },
            });
            //Step4. 点击确定验证成功后，自动关闭弹出框
            $('#debugAoSZPZPoint').modal('hide')
            // $('#debugeDOPoint').modal('hide')
        },
        aidiCustomValue:function() {
            //Step1.取得数据，验证数据后，存入map；
            var map = {};//页面所用 // eg：key1:开机,val1:255,key2:关机,val2:0
            var configMap = {};//传到前台，eg： 开机:255,关机=0；

            for(var i=1;i<=num;i++){ //获取每一行提示和数值的值，传入map
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

            //Step3. TODO 将数据插入节点配置设置表 bes_node_config_setting   step3 暂时注释掉
            //先查询bes_node_config_setting表中有无此系统名称对应的数据，若无，则插入，若有，则更改
            //a.查询表中有无此系统对应的数据
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/getModuleNodeInfoWhenEntryPage",
                type : "post",
                data : {
                    f_sys_name : $("#f_sys_name").val(),//DO节点系统名称
                    tableName : "BES_NODE_CONFIG_SETTING",//DO表名
                },
                success : function(result) {
                    if(result>0){//如果返回数据，表名表中已有此条系统名称相关的数据，则将节点配置设置表的数据更新(删除后插入)；
                        $.ajax({
                            url : _ctx+"/view/basedatamanage/eqmanage/updateNodesConfigSetting",
                            type : "post",
                            contentType : "application/json; charset=utf-8",
                            dataType : "json",
                            data : JSON.stringify({
                                /*f_sys_name : $("#f_sys_name").val(),*/
                                f_sys_name : $("#f_sys_name").val(),
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
                                f_sys_name :$("#f_sys_name").val(),
                                map: map,
                                config_num: num
                            }),
                            success: function (result) {
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
                            error: function (result) {
                                layer.alert("设置失败");
                            }
                        });

                    }
                },
                error : function(result) {
                    swal(result.msg, "", "error");
                },
            });
            //Step4. 点击确定验证成功后，自动关闭弹出框
            $('#pointValueConfModal').modal('hide')
            // $('#debugeDOPoint').modal('hide')
        },

		//信息发生变更时
		infoChanged : function(){
            var initValue = $("#f_init_val").val();
            var pointType = $('#f_point_type_group').val(); // 点类型
            var active = $("input[name='f_enabled']:checked").val(); // 使能状态
            var name = $('#f_sys_name').val(); // 点名称
            var pSysName = $("#pre_f_sys_name").val();//父系统名称
            var alias = $('#f_nick_name').val(); // 点别名
            var description = $('#f_description').val(); // 描述
            var alarmActive = $('input[name="f_alarm_enable"]:checked').val(); // 告警使能
            var alarmType = $('input[name="f_alarm_type"]:checked').val(); // 告警类型
            var alarmPriority = $('input[name="f_alarm_priority"]:checked').val(); // 报警优先级

            // AI AO 虚点AI 虚点AO
            var unit = $('#f_engineer_unit').val(); // [12]工程单位，如℉、℃、KWh
                    // AI AO 虚点AI 虚点AO
            var precision = $('#f_accuracy_group').val();     // 精度
            // AI AO 虚点AI 虚点AO
            var alarmHighValue = $('#f_high_limit').val(); // 高限报警值
            if( alarmHighValue && alarmHighValue != 'undefined' && alarmHighValue != 'null' ){
                alarmHighValue = alarmHighValue.toString();
                alarmHighValue = alarmHighValue.replace(/,/gi, '');
            }
                    // AI AO 虚点AI 虚点AO
            var alarmLowValue = $('#f_low_limit').val();  // 低限报警值
            if( alarmLowValue && alarmLowValue != 'undefined' && alarmLowValue != 'null' ){
                alarmLowValue = alarmLowValue.toString();
                alarmLowValue = alarmLowValue.replace(/,/gi, '');
            }

             // DO 虚点DI 虚点DO
            var alarmTrigger = $('input[name="f_close_state"]:checked').val();//闭合状态
            if (typeof alarmTrigger == 'undefined') {
              alarmTrigger = "2";
            }
            // 虚点 AI
            var energyStatics = $('input[name="f_energyStatics"]:checked').val(); // 能耗采集状态
                    // 虚点 AI
            var energyType = $('#f_energy_type').val(); // 能源类型
            var allPath = $("#f_allpath").text();
            var faultState = $('input[name="f_fault_state"]:checked').val(); // 故障状态

            //判断系统名称字符串第一个字符是否为数字,是的话不能添加
            var  nameSub = Number(name.substring(0,1));
            if (!isNaN(nameSub)){
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
            if (/.*[\u4e00-\u9fa5]+.*$/.test(name)){
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

            if($("#f_nick_name").val().length < 1){
                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputAliasNotEffective();
                return;
            }

			if(initValue.length<1){
				basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputInitValNotEffective();
				return;
			}

			if(isNaN(initValue)){
				basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputInitValNotIntEffective();
				return;
			}

			if (!$("#div_high_low_limit").is(':hidden'))
			{
                if($("#f_high_limit").val().length < 1){
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputAlarmHighNotEffective();
                    return;
                }


                if(isNaN($("#f_high_limit").val())){
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputAlarmHighNotNumberEffective();
                    return;
                }

                if($("#f_low_limit").val().length < 1){
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputAlarmLowNotEffective();
                    return;
                }

                if(isNaN($("#f_low_limit").val())){
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputAlarmLowNotNumberEffective();
                    return;
                }
			}

			if (!$("#unit_accuracy_container").is(':hidden'))
			{
                if($("#f_engineer_unit").val().length < 1){
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputUnitNotEffective();
                    return;
                }
			}

            if($("#f_description").val().length < 1){
                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputDescriptionNotEffective();
                return;
            }
            
            if ((alarmHighValue - 0) < (alarmLowValue - 0))
			{
                swal({
                    title : '低限报警值不能大于高限报警值',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });

                return;
			}

			/*if($('input[name="f_energyStatics"]:checked').val()=='1'){
				$('input[name="f_staticsTime"]:checked').val("") ;
			}*/


			if (eval($("#saveBtn").attr("name"))) {

				/* Start replace by yangjx at 20191212 for 将页面虚点类型由VAI更新为VDO时（程序保存的sql做不到对于将已有数据更正为空）问题修复 */
				//此次更新仅仅为更新不再用通用方法，只是对BES_VPOINT表进行操作更新。
				$.ajax({//虚点属相更改时
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_updateVirtualPoint",
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_updateTableBesVpoint",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
                        // guid: $('#f_guid').val(),
                        pointType: pointType, // 点类型
                        active: active, // 使能状态
                        name: name, // 点名称
                        pSysName : pSysName,//父系统名称
                        alias: alias, // 点别名
                        description: description, // 描述
                        initValue: initValue, // 初始值
                        alarmActive: alarmActive, // 告警使能
                        alarmType: alarmType, // 告警类型
                        alarmPriority: alarmPriority, // 报警优先级

                        // AI AO 虚点AI 虚点AO
                        unit: unit, // [12]工程单位，如℉、℃、KWh
                        // AI AO 虚点AI 虚点AO
                        precision: precision,     // 精度
                        // AI AO 虚点AI 虚点AO
                        alarmHighValue: alarmHighValue, // 高限报警值
                        // AI AO 虚点AI 虚点AO
                        alarmLowValue: alarmLowValue,  // 低限报警值

                        // DO 虚点DI 虚点DO
                        alarmTrigger: alarmTrigger,//闭合状态
                        // 虚点 AI
                        energyStatics: energyStatics, // 能耗采集状态
                        // 虚点 AI
                        energyType: energyType, // 能源类型
                        allPath: allPath,
                        faultState: faultState // 故障状态
					}),
					beforeSend : function() {
						showLoad();
					},
					success : function(result) {

                        if (result.status == '1') {
                            swal({
                                title : result.msg,// 展示的标题
                                text : "",// 内容
                                type : "success",
                                showCloseButton : false, // 展示关闭按钮
                                allowOutsideClick : false,
                                showConfirmButton : false,
                                timer : 1000
                            });

                            debuggerButtonIsShow(pointType);

                        }else
                        {
                            swal({
                                title : result.msg,// 展示的标题
                                text : "",// 内容
                                type : "warning",
                                showCloseButton : false, // 展示关闭按钮
                                allowOutsideClick : false,
                                showConfirmButton : false,
                                timer : 1000
                            });
                        }


						$("#saveBtn").attr("disabled", true); //【保存】设置为无效
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
						basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
					},
					complete : function() {
						hiddenLoad();
					},
					error : function(result) {
						swal(result.msg, "", "error");
					}
				});
			} else {//新增虚点时
				$("#ch_f_sys_name").val($("#f_sys_name").val());
				$("#ch_f_nick_name").val($("#f_nick_name").val());
				$("#ch_f_allpath").val(allPath);
				$("#ch_f_type").val($("#sel_f_type").val());
				$("#ch_f_psys_name").val(pSysName);
				$.ajax({//新增属性节点时
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addVirtualPoint",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
            parkNumber: basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh(),//所属园区
            pointType: pointType, // 点类型
						active: active, // 使能状态
						name: name, // 点名称
            pSysName : pSysName,//父系统名称
						alias: alias, // 点别名
						description: description, // 描述
						initValue: initValue, // 初始值
						alarmActive: alarmActive, // 告警使能
						alarmType: alarmType, // 告警类型
						alarmPriority: alarmPriority, // 报警优先级

            // AI AO 虚点AI 虚点AO
            unit: unit, // [12]工程单位，如℉、℃、KWh
            // AI AO 虚点AI 虚点AO
            precision: precision,     // 精度
            // AI AO 虚点AI 虚点AO
            alarmHighValue: alarmHighValue, // 高限报警值
            // AI AO 虚点AI 虚点AO
            alarmLowValue: alarmLowValue,  // 底限报警值

            // DO 虚点DI 虚点DO
            alarmTrigger: alarmTrigger,//闭合状态
						// 虚点 AI
            energyStatics: energyStatics, // 能耗采集状态
            // 虚点 AI
            energyType: energyType, // 能源类型
            allPath: allPath,
            faultState: faultState // 故障状态

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
						if (returnObject.status == '1') { //保存成功时

                            swal({
                                title : returnObject.msg,// 展示的标题
                                text : "",// 内容
                                type : "success",
                                showCloseButton : false, // 展示关闭按钮
                                allowOutsideClick : false,
                                showConfirmButton : false,
                                timer : 1000
                            });

                            debuggerButtonIsShow(pointType);

							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), { exactMatch : true } ]);//
							var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							addeNodeId = $("#f_sys_name").val();
							addedNodeText = $("#f_nick_name").val();

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

                            PubSub.subscribe(addeNodeId, basedatamanage_eqmanage_eqconfiguration_sbdy.refreshTreeSingle);
                            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							$("#tree_sbdy").treeview("selectNode", changeNode);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							$("#f_sys_name").attr("readonly", "readonly");
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
						} else {//保存失败时
                            $("#saveBtn").attr("disabled", false); //【新增】设置为无效

                            swal({
                                title : returnObject.msg,// 展示的标题
                                text : "",// 内容
                                type : "warning",
                                showCloseButton : false, // 展示关闭按钮
                                allowOutsideClick : false,
                                showConfirmButton : false,
                                timer : 1000
                            });
							return;
							// basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeAddFalse(returnObject);
						}
						$("#saveBtn").text("保存");
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
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
		refreshSubmitFlg : function() {//更新按钮状态
			var submitFlg = true;
			if ($("#saveBtn").text() == "新增") {
				$("#setDateBtn").attr("disabled", true);
				$("#getDateBtn").attr("disabled", true);
				$("#reSetBtn").attr("disabled", true);
				$("#reStartBtn").attr("disabled", true);
				$("#RemoteUpBtn").attr("disabled", true);
				submitFlg = false;
			} else {
				$("#setDateBtn").attr("disabled", false);
				$("#getDateBtn").attr("disabled", false);
				$("#reSetBtn").attr("disabled", false);
				$("#reStartBtn").attr("disabled", false);
				$("#RemoteUpBtn").attr("disabled", false);
				submitFlg = true;
			}
			$("#saveBtn").attr("name", submitFlg);
		},
		varInfo : function() {//虚点类型不同，虚点的属性不同

            var type = $('#f_point_type_group').val();
            var f_guid = $('#f_guid').val();

            switch (type) {
                case '4': // AI
                    $('#debugAO').hide();
                    $('#debugDO').hide();
                    $('#energy_collect_options').show();
                    if (f_guid)
                    {
                        $('#pointValueConfigVirtual').show();
                    }
                    break;
                case '5': // AO
                    if (f_guid)
                    {
                        $('#debugAO').show();
                    }
                    $('#pointValueConfigVirtual').hide();
                    $('#debugDO').hide();
                    $('#energy_collect_options').hide();
                    break;
                case '6': // DI
                    $('#debugDO').hide();
                    $('#debugAO').hide();
                    $('#energy_collect_options').hide();
                    if (f_guid)
                    {
                        $('#pointValueConfigVirtual').show();
                    }
                    break;
                case '7': // DO
                    if (f_guid)
                    {
                        $('#debugDO').show();
                    }
                    $('#pointValueConfigVirtual').hide();
                    $('#debugAO').hide();
                    $('#energy_collect_options').hide();
                    break;
            }

			var isInput =  $("#f_point_type_group option:checked").attr("isInput");
			if(isInput == '0'){
			    $('#unit_accuracy_container').hide();
				$("#f_engineer_unit").attr("readonly", "readonly");
				$("#f_engineer_unit").val('');
				$("#f_accuracy_group").empty();//清空下拉列表
				$("#f_accuracy_group").prop("disabled", "disabled");
				$("#div_high_low_limit").hide();
				//$("#div_high_low_data").hide();
				$("#div_f_close_state").show();
				$("#div_f_fault_state").show();
				//$("#div_select_data").hide();
				$("#under_F_Precision_div").hide();
				$("#local_F_Precision_div").hide();
				$("input[name=f_alarm_enable]").attr("disabled", false);//报警使能可用
				$("input[name=f_alarm_type]").attr("disabled", false);//报警类型可用
			}else if(isInput == '1'){//是否属于输入： 0：否 1：是 [ AI、AO是1； DI、DO是0] +虚点能耗类型 changed by tianjiangwei
                $('#unit_accuracy_container').show();
			    $('input[name=f_engineer_unit]').removeAttr("readonly");
				$("#f_accuracy_group").removeAttr("disabled");
 				addAccuracyGroup();//填充下拉列表
				$("#div_f_close_state").hide();
				$("#div_f_fault_state").hide();
				if ($('input[name="f_alarm_type"]:checked').val() === '1')
				{
                    $("#div_high_low_limit").show();
				}
				$("#under_F_Precision_div").show();
				$("#local_F_Precision_div").show();
				$("input[name=f_alarm_enable]").attr("disabled", false);//报警使能可用
				$("input[name=f_alarm_type]").attr("disabled", false);//报警类型可用
				//$("#div_high_low_data").show();
				//$("#div_select_data").show();

			}
		},
        //同步逻辑虚点
        synPoint: function()
		{
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
                    url: _ctx + "/view/basedatamanage/eqmanage/synVirtualPoint",
                    type: "post",
                    data: {
                        f_sys_name: $("#f_sys_name").val()
                    },
                    beforeSend: function () {
                        showLoad();
                    },
                    success: function (result) {
                        if (result.status == '0') {
                            swal({
                                title: result.msg,// 展示的标题
                                text: "",// 内容
                                type: "warning",
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
                    }
                });
            }
		},

		//重置逻辑点(对应下位机删除逻辑点)
		reSet : function(){
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/reSetPoint",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val(),
					f_psys_name : basedatamanage_eqmanage_eqconfiguration_sbdy.getNodepid(),//父系统名称
					f_sbid : $("#f_id").val(),
					f_id : $("#hidden_f_sbid").val(),
					nodeLevel : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数 
					f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
				},
				beforeSend : function() {
					showLoad();
				},
				success : function(result) {
					if (result.status == '0') {
						swal({
							title : result.msg,// 展示的标题
							text : "",// 内容
							type : "error",
							showCloseButton : false, // 展示关闭按钮
							allowOutsideClick : false,
							showConfirmButton : false,
							timer : 1000,
						});
					} else {
						swal({
							title : result.msg,// 展示的标题
							text : "",// 内容
							type : "success",
							showCloseButton : false, // 展示关闭按钮
							allowOutsideClick : false,
							showConfirmButton : false,
							timer : 1000
						});
					}
				},
				complete : function() {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				},
			});
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
        staticsTimeIsEffective:function(){
            if ($('input[name="f_energyStatics"]:checked').val() == '1'){//不做能耗统计
                $('#f_energy_type').attr("disabled", true);
                $('#f_energy_type option:first').prop('selected', 'selected');
            }else{
                $('#f_energy_type').attr("disabled", false);//按钮设为有效
            }
        },
        setSyncPage,
        handleLowerData: function (data)
        {

            if (!data)
            {
                return;
            }

            var f_node_type = data.pointType;
            var f_sbid = data.id;
            var f_enabled = data.active;
            var f_sys_name = data.name;
            var f_nick_name = data.alias;
            var f_description = data.description;
            var f_init_val = data.initValue;

            switch (f_node_type)
            {
                case 4:
                    f_node_type = 'VAI';

                    $('#under_F_ENGINEER_UNIT').val(data.unit);
                    $('#under_F_ACCURACY').val(data.precision);
                    $("#under_F_INIT_VAL").val(f_init_val / (Math.pow(10, (data.precision))));

                    var local_f_engineer_unit =  $("#local_F_ENGINEER_UNIT").val();

                    if (local_f_engineer_unit != data.unit)
                    {
                        $("#local_F_ENGINEER_UNIT").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_ENGINEER_UNIT").removeAttr('style');
                    }

                    var local_f_init_val =  $("#local_F_INIT_VAL").val();

                    if (local_f_init_val != (f_init_val / (Math.pow(10, (data.precision)))))
                    {
                        $("#local_F_INIT_VAL").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_INIT_VAL").removeAttr('style');
                    }

                    var local_f_accuracy =  $("#local_F_ACCURACY").val();

                    if (local_f_accuracy != data.precision)
                    {
                        $("#local_F_ACCURACY").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_ACCURACY").removeAttr('style');
                    }

                    break;
                case 5:
                    f_node_type = 'VAO';
                    $('#under_F_ENGINEER_UNIT').val(data.unit);
                    $('#under_F_ACCURACY').val(data.precision);
                    $("#under_F_INIT_VAL").val(f_init_val / (Math.pow(10, (data.precision))));

                    var local_f_engineer_unit =  $("#local_F_ENGINEER_UNIT").val();

                    if (local_f_engineer_unit != data.unit)
                    {
                        $("#local_F_ENGINEER_UNIT").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_ENGINEER_UNIT").removeAttr('style');
                    }

                    var local_f_init_val =  $("#local_F_INIT_VAL").val();

                    if (local_f_init_val != (f_init_val / (Math.pow(10, (data.precision)))))
                    {
                        $("#local_F_INIT_VAL").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_INIT_VAL").removeAttr('style');
                    }

                    var local_f_accuracy =  $("#local_F_ACCURACY").val();

                    if (local_f_accuracy != data.precision)
                    {
                        $("#local_F_ACCURACY").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_ACCURACY").removeAttr('style');
                    }


                    break;
                case 6:
                    f_node_type = 'VDI';

                    $("#under_F_INIT_VAL").val(f_init_val);

                    var local_f_init_val =  $("#local_F_INIT_VAL").val();

                    if (local_f_init_val != f_init_val)
                    {
                        $("#local_F_INIT_VAL").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_INIT_VAL").removeAttr('style');
                    }

                    break;
                case 7:
                    f_node_type = 'VDO';

                    $("#under_F_INIT_VAL").val(f_init_val);

                    var local_f_init_val =  $("#local_F_INIT_VAL").val();

                    if (local_f_init_val != f_init_val)
                    {
                        $("#local_F_INIT_VAL").attr('style','color:red');
                    }
                    else
                    {
                        $("#local_F_INIT_VAL").removeAttr('style');
                    }

                    break;
            }

            $("#under_F_NODE_TYPE").val(f_node_type);
            $("#under_F_SBID").val(f_sbid);

            if (f_enabled === 1)
            {
                f_enabled = '是';
            }else
            {
                f_enabled = '否';
            }

            $("#under_F_ENABLED").val(f_enabled);
            $("#under_F_SYS_NAME").val(f_sys_name);
            $("#under_F_NICK_NAME").val(f_nick_name);
            $("#under_F_DESCRIPTION").val(f_description);


            var local_f_enabled = $("#local_F_ENABLED").val();

            if (local_f_enabled != f_enabled)
			{
                $("#local_F_ENABLED").attr('style','color:red');
			}else
			{
                $("#local_F_ENABLED").removeAttr('style');
			}

            var local_f_sys_name = $("#local_F_SYS_NAME").val();

            if (local_f_sys_name != f_sys_name)
			{
                $("#local_F_SYS_NAME").attr('style','color:red');
			}
			else
			{
                $("#local_F_SYS_NAME").removeAttr('style');
			}


            var local_f_nick_name = $("#local_F_NICK_NAME").val();

            if (local_f_nick_name != f_nick_name)
            {
                $("#local_F_NICK_NAME").attr('style','color:red');
            }
            else
            {
                $("#local_F_NICK_NAME").removeAttr('style');
            }


            var local_f_description = $("#local_F_DESCRIPTION").val();

            if (local_f_description != f_description)
            {
                $("#local_F_DESCRIPTION").attr('style','color:red');
            }
            else
            {
                $("#local_F_DESCRIPTION").removeAttr('style');
            }
        },
        executeAO: function ()
        {
            var aoVal;
            var f_accuracy = $('#f_accuracy').val();

            // if ($("#initInputShow").is(":hidden")) {
            //     aoVal = $('#f_init_val_aoSz').val() * (Math.pow(10, (f_accuracy)));
            // } else {
            //     aoVal = $('#f_init_val_ao').val() * (Math.pow(10, (f_accuracy)));
            // }

                if ($("#initInputShow").is(":hidden")) {
                    aoVal = $('#f_init_val_aoSz').val() ;
                } else {
                    aoVal = $('#f_init_val_ao').val();
                }

            var sysName = $('#f_sys_name_ao').val();
            var hidden_f_struct_id = $('#hidden_f_struct_idAO').val();

            
            /*if (!aoVal || isNaN(aoVal))
			{
                swal({
                    title : '请输入整数',// 展示的标题
                    text : "",// 内容
                    type: "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer: 1000,
                });

                return;
			}*/

            $.ajax({
                url : _ctx + "/api/debugPointInfo",
                // url : _ctx + "/view/basedatamanage/eqmanage/sbdy_put_up_point",
                type : "post",
                contentType : "application/json; charset=utf-8",
                dataType : "json",
                data : JSON.stringify({
                    f_sys_name 		: sysName,
                    f_init_val 		: aoVal,
                    f_struct_id : hidden_f_struct_id,
                }),
                success : function(result) {
                    if(result.status === '0'){
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
                }
            });


        },
        executeDO: function ()
        {
            var sysName = $('#f_sys_name_do').val();
            var doVal = $('#f_init_val_do').val();
            var hidden_f_struct_id = $('#hidden_f_struct_idDO').val();
            $.ajax({
                url : _ctx + "/api/debugPointInfo",
                // url : _ctx + "/view/basedatamanage/eqmanage/sbdy_put_up_point",
                type : "post",
                contentType : "application/json; charset=utf-8",
                dataType : "json",
                data : JSON.stringify({
                    f_sys_name 		: sysName,
                    f_struct_id : hidden_f_struct_id,
                    f_init_val 		: doVal,

                }),
                success : function(result) {
                    if(result.status === '0'){
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
                }
            });

        }
	};


})(jQuery, window, document);
</script>

