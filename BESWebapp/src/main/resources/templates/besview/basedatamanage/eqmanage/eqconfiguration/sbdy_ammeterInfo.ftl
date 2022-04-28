   <div class="attrInfo" id="attrInfo" >
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">系统名称</label>
                                <div class="col-sm-4">
                                	<input  type="hidden" id="f_id" name="f_id"  value="${returnObject.data.F_SBID}">
									<input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
									<input id="f_guid" value="${returnObject.data.F_GUID}" TYPE="hidden">
									<input id="f_id" value="${returnObject.data.F_ID}" TYPE="hidden">
                                    <input id="f_sys_name" name="f_sys_name"  value="${returnObject.data.F_SYS_NAME_OLD}" type="text" class="form-control" readonly="readonly" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">电表别名</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name"  value="${returnObject.data.F_NICK_NAME}" class="form-control"
										   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                                <label class="col-sm-2 control-label">电表类型</label>
                                <div class="col-sm-4">
                                   <input type="hidden" id="hidden_f_blxbh"  value="${returnObject.data.F_BLXBH}">
                                    <select id="f_blxbh_group" name="f_blxbh_group" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                  </div>
                            </div>

                            <div class="has-success">
                                <label class="col-sm-2 control-label">使能状态</label>
                                <input type="hidden" id="hidden_f_enabled"  value="${returnObject.data.F_ENABLED}">
                                <div class="col-sm-4">
                                    <input type="radio" id="f_enabled_yes" name="f_enabled" value="1" checked="checked" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
                                    <input type="radio" id="f_enabled_no" name="f_enabled" value="0" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
                                </div>
                            </div>

                         </div>

                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                             <div class="has-success">
                                 <label class="col-sm-2 control-label">采集方案</label>
                                 <div class="col-sm-4">
                                     <input type="hidden" id="hidden_f_cjfabh" value="${returnObject.data.F_CJFABH}">
                                     <select id="f_cjfabh_group" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                 </div>
                             </div>

                             <div class="has-success">
                                <label class="col-sm-2 control-label">通信协议</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="hidden_f_protocol_type"  value="${returnObject.data.F_PROTOCOL_TYPE}">
                                    <select id="f_protocol_type_group" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                </div>
                            </div>
                         </div>

                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                             <div class="has-success">
                                 <label class="col-sm-2 control-label">数据位</label>
                                 <div class="col-sm-4">
                                     <input type="hidden" id="hidden_f_com_data_bit" value="${returnObject.data.F_COM_DATA_BIT}">
                                     <select id="f_com_data_bit" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                         <option value="0">Data5</option>
                                         <option value="1">Data6</option>
                                         <option value="2">Data7 </option>
                                         <option selected value="3">Data8 </option>
									 </select>
                                 </div>
                             </div>

                             <div class="has-success">
                                <label class="col-sm-2 control-label">校验位</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="hidden_f_com_parity_bit"  value="${returnObject.data.F_COM_PARITY_BIT}">
                                    <select id="f_com_parity_bit" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                        <option value="0">无校验</option>
                                        <option value="1">偶校验</option>
                                        <option value="2">奇校验</option>
                                        <option value="3">空格校验</option>
                                        <option value="4">mark校验</option>
									</select>
                                </div>
                            </div>
                         </div>

                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                             <div class="has-success">
                                 <label class="col-sm-2 control-label">停止位</label>
                                 <div class="col-sm-4">
                                     <input type="hidden" id="hidden_f_com_stop_bit" value="${returnObject.data.F_COM_STOP_BIT}">
                                     <select id="f_com_stop_bit" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                         <option value="0">1 bit</option>
                                         <option value="1">1.5 bit</option>
                                         <option value="2">2 bit</option>
									 </select>
                                 </div>
                             </div>

                             <div class="has-success">
                                <label class="col-sm-2 control-label">功能码</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="hidden_f_function_code"  value="${returnObject.data.F_FUNCTION_CODE}">
                                    <select id="f_function_code" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                        <option value="0">0x03</option>
                                        <option value="1">0x04</option>
									</select>
                                </div>
                            </div>
                         </div>

                          <div  class="vertical-timeline-block eqTreeAttrLineWidth">
<!--                                <div class="has-success">
                                    <label class="col-sm-2 control-label">型  号</label>
                                     <input type="hidden" id="f_sblxbh_selected"  value="${returnObject.data.F_SBLXBH}" class="form-control">
                                      <input type="hidden" id="f_type_selected"  value="${returnObject.data.F_TYPE}" class="form-control">
                                       <div id="seldiv"></div>
                                </div>
                               -->

                              <div class="has-success">
                                  <label class="col-sm-2 control-label">波特率</label>
                                  <div class="col-sm-4">
                                      <input type="hidden" id="hidden_f_comm_rate" value="${returnObject.data.F_COMM_RATE}">
                                      <select id="f_comm_rate_group" class="selector" style="width: 13em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                                  </div>
                              </div>

                              <div class="has-success">
                                  <label class="col-sm-2 control-label">物理地址</label>
                                  <div class="col-sm-4">
                                      <input type="text" id="f_wldz"  value="${returnObject.data.F_WLDZ}" oninput="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.physicalAddressOninput(this)" class="form-control"
											 onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                  </div>
                              </div>

                           </div>
                           <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                               <div class="has-success">
                                   <label class="col-sm-2 control-label">通信端口</label>
                                   <div class="col-sm-4">
                                       <input type="text" readonly id="f_communication_port"  value="${returnObject.data.F_PORT}"  class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                   </div>
                               </div>

                                <div class="has-success">
                                 <label class="col-sm-2 control-label">变比</label>
                                  <div class="col-sm-4">
                                      <input type="text" id="hide_f_percentage" style="display: none" value="${returnObject.data.F_PERCENTAGE}"  class="form-control">
                                      <input type="text" id="f_percentage" value="${returnObject.data.F_PERCENTAGE}"  class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                  </div>
                               </div>
                           </div>
                            <div  class="vertical-timeline-block eqTreeAttrLineWidth">

                                <div class="has-success">
                                    <label class="col-sm-2 control-label">安装位置</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="f_azwz" value="${returnObject.data.F_AZWZ}"  class="form-control"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                    </div>
                                </div>

                                <div class="has-success">
                                    <label class="col-sm-2 control-label">描  述</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="f_description"  value="${returnObject.data.F_DESCRIPTION}" class="form-control"
											   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                    </div>
                                </div>
                           </div>
                           <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                               <#--<div class="has-success">-->
                                   <#--<label class="col-sm-2 control-label">资产编码</label>-->
                                   <#--<div class="col-sm-4">-->
                                       <#--<input type="text" id="meter_assets_code"  value="${returnObject.data.ASSETS_CODE}" class="form-control"-->
                                              <#--onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">-->
                                   <#--</div>-->
                               <#--</div>-->
                           <div class="has-success">
                               <label class="col-sm-2 control-label">状态</label>
						      	 <div class="col-sm-4">
						        	 <input type="checkbox" id="f_ammeter_state" disabled name="f_ammeter_state" value="${returnObject.data.F_AMMETER_STATE}" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
						        	 <a id="ammeter_state"></a>
						        	<#-- <input type="checkbox" id="f_online_state" disabled name="f_online_state" value="${returnObject.data.F_ONLINE_STATE}" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
						        	 <a id="ammeter_onlinestate"></a>-->
						    	</div>
                            </div>

                           </div>
                                  <div style="margin-left: 13.5vw;padding-right: 15px;">
                                   <span>
                                      <button id="synBtn" class="btn sbtreeNodeBtn"  onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.syncMeter()">同步数据</button>
                                      <button id="contrastBtn" class="btn sbtreeNodeBtn" data-toggle="modal">数据对比</button>
                                      <button id="accEnergyDataBtn" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#queryElectricData">获取能耗数据</button>
                                      <button id="accHisEnergyDataBtn" class="btn sbtreeNodeBtn" data-toggle="modal" data-target="#queryElectricHisData">获取历史能耗数据</button>
                                      <button id="saveBtn" style="margin-left: 10px;" class="btn sbtreeNodeBtn" type="submit" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
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

<!----采集器信息对比--->
<div class="modal fade" id="compareAmmeterInfo" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class="fa fa-compress">上/下位机电表信息比对</i></h4>
            </div>
            <div class="modal-body" style="height:655px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机电表信息</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机电表信息</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:600px; overflow-y: scroll; overflow-x:hidden;">
					<form id="localAmmeterInfo" name="localAmmeterInfo" class="form-horizontal">

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表ID<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_SBID" name="localAmmeter_F_SBID" class="form-control" readonly="readonly">
							</div>
						</div>

						<#--<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表名称<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_SYS_NAME" name="localAmmeter_F_SYS_NAME" class="form-control" readonly="readonly">
							</div>
						</div>-->

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表别名<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_NICK_NAME" name="localAmmeter_F_NICK_NAME" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">电表类型<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_SBLXBH" name="localAmmeter_F_SBLXBH" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_ENABLED" name="localAmmeter_F_ENABLED" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">采集方案<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_CJFABH" name="localAmmeter_F_CJFABH" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">通信协议<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_PROTOCOL_TYPE" name="localAmmeter_F_PROTOCOL_TYPE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">数据位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_COM_DATA_BIT" name="localAmmeter_F_COM_DATA_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">校验位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_COM_PARITY_BIT" name="localAmmeter_F_COM_PARITY_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">停止位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_COM_STOP_BIT" name="localAmmeter_F_COM_STOP_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">功能码<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_FUNCTION_CODE" name="localAmmeter_F_FUNCTION_CODE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">波特率<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_COMM_RATE" name="localAmmeter_F_COMM_RATE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">物理地址<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_WLDZ" name="localAmmeter_F_WLDZ" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">通信端口<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_COMMUNICATION_PORT" name="localAmmeter_F_COMMUNICATION_PORT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">安装位置<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_AZWZ" name="localAmmeter_F_AZWZ" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="localAmmeter_F_DESCRIPTION" name="localAmmeter_F_DESCRIPTION" class="form-control" readonly="readonly">
							</div>
						</div>

					</form>
            	</div>
            	<!----上位机信息结束--->

            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:400px;height:600px;margin:5px 0 0 36px;  overflow-y: scroll; overflow-x:hidden;">
					<form id="underAmmeterInfo" name="underAmmeterInfo" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="underAmmeter_F_SBID" name="underAmmeter_F_SBID" class="form-control" readonly="readonly">
                            </div>
                        </div>

						<#--<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表名称<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_SYS_NAME" name="underAmmeter_F_SYS_NAME" class="form-control" readonly="readonly">
							</div>
						</div>-->

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">电表别名<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_NICK_NAME" name="underAmmeter_F_NICK_NAME" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">电表类型<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_SBLXBH" name="underAmmeter_F_SBLXBH" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_ENABLED" name="underAmmeter_F_ENABLED" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">采集方案<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_CJFABH" name="underAmmeter_F_CJFABH" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">通信协议<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_PROTOCOL_TYPE" name="underAmmeter_F_PROTOCOL_TYPE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">数据位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_COM_DATA_BIT" name="underAmmeter_F_COM_DATA_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">校验位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_COM_PARITY_BIT" name="underAmmeter_F_COM_PARITY_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">停止位<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_COM_STOP_BIT" name="underAmmeter_F_COM_STOP_BIT" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">功能码<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_FUNCTION_CODE" name="underAmmeter_F_FUNCTION_CODE" class="form-control" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">波特率<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_COMM_RATE" name="underAmmeter_F_COMM_RATE" class="form-control" readonly="readonly">
							</div>
						</div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">物理地址<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="underAmmeter_F_WLDZ" name="underAmmeter_F_WLDZ" class="form-control" readonly="readonly">
                            </div>
                        </div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">通信端口<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_COMMUNICATION_PORT" name="underAmmeter_F_COMMUNICATION_PORT" class="form-control" readonly="readonly">
							</div>
						</div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">安装位置<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="underAmmeter_F_AZWZ" name="underAmmeter_F_AZWZ" class="form-control" readonly="readonly">
                            </div>
                        </div>

						<div class="form-group">
							<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span class="text-comparecoll">:</span></label>
							<div class="col-sm-8" style="width:50%">
								<input type="text" id="underAmmeter_F_DESCRIPTION" name="underAmmeter_F_DESCRIPTION" class="form-control" readonly="readonly">
							</div>
						</div>

					</form>
            	</div>
            	<!----下位机信息结束--->

            </div>
            <div class="modal-footer">
                <div class="col-xs-6">
                    <button type="button" class="btn btn-white m-l-sm" style="hei" data-toggle="modal" data-target="#compareElectricParams"><i class="fa fa-search"> 查看电能参数</i></button>
				</div>
				<div class="col-xs-6">
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				</div>
       		</div>
        </div>
    </div>
</div>
<!-- 采集器信息比对结束 -->

<!----电能参数信息对比--->
<div class="modal fade" id="compareElectricParams" style="margin-left:-35%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1400px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title "><i class="fa fa-compress">上/下位机电能参数比对</i></h4>
            </div>
            <div class="modal-body" style="height:655px;margin-button:10px;">
            	<div style="float:left;width:51%"><button class="btn btn-md" style="cursor:default"><span>上位机电能参数</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机电能参数</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:650px;height:600px;">
					<div id="UppermachineTab" style="overflow: auto;"></div>
            	</div>
            	<!----上位机信息结束--->

            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:650px;height:600px;margin:5px 0 0 36px;">
					<div id="UndermachineTab" style="overflow: auto;"></div>
            	</div>
            	<!----下位机信息结束--->

            </div>
            <div class="modal-footer">
        		<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
       		</div>
        </div>
    </div>
</div>
<!-- 电能参数信息比对结束 -->

<!----获取能耗数据--->
<div class="modal fade" id="queryElectricData" style="margin-left:-25%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1160px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title "><i class="fa fa-compress">获取能耗数据</i></h4>
            </div>
			<div class="modal-body" style="height: 655px; margin-button: 0px;">
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">电表名称:&nbsp;<span id="span_fSysname" class="text-comparecoll">${returnObject.data.F_SYS_NAME}</span></label>
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">电表别名:&nbsp;<span id="span_fNickname" class="text-comparecoll">${returnObject.data.F_NICK_NAME}</span></label>
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">电能参数个数:&nbsp;<span id="span_fNum" class="text-comparecoll"></span></label>
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">采集时间:&nbsp;<span id="span_fDate" class="text-comparecoll"></span></label>
				<!----能耗数据展示--->
				<div class="notIncludeCss"
					style="width: 100%; height: 97%; margin-top: 2%;">
					<div id="ElectricDataTab" style="margin-top:-10px"></div>
				</div>
				<!----上位机信息结束--->
			</div>
			<div class="modal-footer">
        		<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
       		</div>
        </div>
    </div>
</div>
<!-- 获取能耗数据结束 -->
<!----获取能耗历史数据--->
<div class="modal fade" id="queryElectricHisData" style="margin-left:-25%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:1160px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title "><i class="fa fa-compress">获取能耗历史数据</i></h4>
            </div>
			<div class="modal-body" style="height: 655px; margin-button: 10px;">
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">电表名称:&nbsp;<span id="his_span_fSysname" class="text-comparecoll">${returnObject.data.F_SYS_NAME}</span></label>
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">电表别名:&nbsp;<span id="his_span_fNickname" class="text-comparecoll">${returnObject.data.F_NICK_NAME}</span></label>
				<label class="col-sm-3 control-label"
					for="edit_fRatedPower">小时个数:<span id="his_span_fNum" class="text-comparecoll"></span></label>
					<label class="col-sm-3 control-label" style="width:7%" for="edit_fRatedPower">请选择</label>
                    <select id="select_day" name="select_day" style="width:8%;height:5%;margin-top:-5px;display: inline" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getHistoryData(this.value);">
                    </select>
				<!----能耗历史数据展示--->
				<div class="notIncludeCss"
					style="width: 100%; height: 97%; margin-top: 2%;">
					<div id="ElectricHisDataTab" style="margin-top:-10px"></div>
				</div>
			</div>
			<div class="modal-footer">
        		<button type="button" class="btn btn-white"  data-dismiss="modal">关闭</button>
       		</div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/issp/issp-helpcombobox.js"></script>
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
	var _ctx = '${ctx}';
	var i = 0;
	var ElectricName = 0;
	var OffsetAddr = 0;
	var DataLength = 0;
	var DataEncodeType = 0;
	var UintType = 0;
	var PointLocation = 0;

	var collectionParamList = [];

	var dataEncodeTypeObject = {};

	//加载下拉列表的值
	function getSelectInfos(colIdN, colN, tabN, selectId,selVal){
		$.ajax({
			type:"POST",
			url:_ctx + '/view/basedatamanage/eqmanage/sbdy_getInfoByColumnName',
			data:{
				colIdName : colIdN,
				colName : colN,
				tabName : tabN,
			},
			success : function(data){
				var ids = data.map['ids'];
				var names = data.map['names'];
				$("#"+selectId).empty();
				var x = document.getElementById(selectId);
				for(var i= 0;i<ids.length;i++){
					var obj = document.createElement("option");
					obj.value = ids[i];
					obj.text = names[i];
					x.appendChild(obj);
					if (obj.value == selVal) //设置当前选中值
						obj.selected = true;
				}
			},
			error:function(data){
				swal(data.msg,"", "error");
			}
		});
	}
	$(function() {
		getSelectInfos('F_RATE_BH','F_COMM_RATE','bes_rate','f_comm_rate_group',$("#hidden_f_comm_rate").val());//获取通信波特率信息
		getSelectInfos('F_TXXYBH','F_TYPE','bes_protocol','f_protocol_type_group',$("#hidden_f_protocol_type").val());//获取通信协议信息
		getSelectInfos('F_LXBH','F_LXMC','bes_ammeter_type','f_blxbh_group',$("#hidden_f_blxbh").val());//获取电表类型信息
		getSelectInfos('F_CJFABH','F_CJFAMC','bes_coll_method','f_cjfabh_group',$("#hidden_f_cjfabh").val());//获取采集方案信息
		getCurrentEquipmentType();//获取默认设备型号
		setCheckbox();//设置状态

		// 数据位下拉框赋值
		if ($('#hidden_f_com_data_bit').val())
		{
			$('#f_com_data_bit').val($('#hidden_f_com_data_bit').val());
		}

        // 校验位下拉框赋值
		if ($('#hidden_f_com_parity_bit').val())
		{
			$('#f_com_parity_bit').val($('#hidden_f_com_parity_bit').val());
		}

        // 停止位下拉框赋值
		if ($('#hidden_f_com_stop_bit').val())
		{
			$('#f_com_stop_bit').val($('#hidden_f_com_stop_bit').val());
		}

        // 功能码下拉框赋值
		if ($('#hidden_f_function_code').val())
		{
			$('#f_function_code').val($('#hidden_f_function_code').val());
		}

        getfbmgz();

		$('#contrastBtn').click(function ()
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
                $('#compareAmmeterInfo').modal('show')
            }
        });


		// 获取采集参数信息
		getCollectionParamInfo($("#hidden_f_cjfabh").val(), function (data)
        {

            var list = data || data.list;

            if (Array.isArray(list))
			{
                collectionParamList = list
			}
        });

	});

	// 获取编码规则配置信息
    function getfbmgz(){
        $.ajax({
            type: "POST",
            url: _ctx + '/view/basedatamanage/enegrycollectionmanage/getfbmgz',
            data:"",
            success: function(result){
				var list = result && result.data;
				if (!Array.isArray(list))
				{
					return;
				}

				for (var i = 0; i < list.length; i++)
				{
                    dataEncodeTypeObject[list[i].F_BMGZ_ID] = list[i].F_BMGZ_MC;
				}

            }
        });
    }
	//新增修改判断
	if(basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){
		f_xh_type="${f_xh_type}";
	}else{
		f_xh_type="${besSbPzStruct.f_xh_type}";
	}
	function get_equipmentTypetree() {//获取设备类型和设备型号
		$.ajax({
			type : "post",
			url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getEquipmentModuleTree",
			data:{ftype:f_xh_type},
			dataType : "json",
			beforeSend: function () {
				showLoad();
	 		},
			success : function(result) {
				$("#seldiv").setComboBoxData({
					data:result.list,
				});
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			}
		});
	};
	function getCurrentEquipmentType(){//获取默认设备型号
		var _sblxbh = $("#f_sblxbh_selected").val();
		var _type = $("#f_type_selected").val();
		if(_sblxbh!='' && _type!=''){
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getCurrentEquipmentType",
				contentType : "application/json; charset=utf-8",
				type : "get",
				data : {
					sblxbh : _sblxbh,
					type : _type,
				},
				beforeSend: function () {
					showLoad();
		 		},
				success : function(result) {
					if(result.status=='1'){
						var etempMap = result.map;//
						var eMap = new Map();
						for ( var key in etempMap) {
							eMap.put(key, etempMap[key]);
						};
						$("#selInput").val(eMap.get($("#f_sblxbh_selected").val())
						+ '->' + eMap.get($("#f_type_selected").val()));
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
	};
	//状态设置
	function setCheckbox(){
		//同步状态

		if($("#f_ammeter_state").val() == "1"){
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
		if($("#hidden_f_enabled").val() == "1"){
			$("#f_enabled_yes").prop("checked",true);
			$("#hidden_f_enabled").val("1");
		}else if ($("#hidden_f_enabled").val() == "0")
        {
			$("#f_enabled_no").prop("checked",true);
			$("#hidden_f_enabled").val("0");
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
            $("#f_ammeter_state").prop("checked",true);
            $("#ammeter_state").text("已同步");
            $("#ammeter_state").attr("style","color: #00ff2d");
        } else
        {
            $("#f_ammeter_state").prop("checked",false);
            $("#ammeter_state").text("未同步");
            $("#ammeter_state").attr("style","color: #ff0000");
        }
    }

    /* End add by xiepufeng at 2020年5月26日 */
 	//居中显示（采集器信息对比）
 	$('#compareAmmeterInfo').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#compareAmmeterInfo .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	});
	//电表信息对比模态框出现前加载(可拖动)
	$("#compareAmmeterInfo").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	    fill_localAmmeter();
	    fill_underAmmeter();
	});
	//电能参数对比模态框出现前加载(可拖动)
	$("#compareElectricParams").on('show.bs.modal', function(event) {
		$(this).css('display', 'block');
	        var modalHeight=$(window).height() / 2 - $('#compareElectricParams .modal-dialog').height() / 2;
	        $(this).find('.modal-dialog').css({
	            'margin-top': modalHeight
	        });

	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

        loadUppermachineTab();

	    // loadUndermachineTab();
	});
	//获取能耗数据模态框出现前加载(可拖动)
	$("#queryElectricData").on('show.bs.modal', function(event) {
		 $(this).css('display', 'block');
	        var modalHeight=$(window).height() / 2 - $('#queryElectricData .modal-dialog').height() / 2;
	        $(this).find('.modal-dialog').css({
	            'margin-top': modalHeight
	        });

	    var button = $(event.relatedTarget);
	    var id = button.data("id");
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

	    // basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.loadElectricDataTab();

		// 获取电表实时数据
        getRealTimeData();

	});


	$("#queryElectricData").on('hidden.bs.modal', function(event) {
        $("#ElectricDataTab").tabulator("clearData");
        $('#span_fNum').text('');
        $('#span_fDate').text('');
	});

	//获取能耗历史数据模态框出现前加载(可拖动)
	$("#queryElectricHisData").on('show.bs.modal', function(event) {
		 $(this).css('display', 'block');
	        var modalHeight=$(window).height() / 2 - $('#queryElectricHisData .modal-dialog').height() / 2;
	        $(this).find('.modal-dialog').css({
	            'margin-top': modalHeight
	        });

	    var button = $(event.relatedTarget);
	    var id = button.data("id");
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	    loadSelectDay();

        var day = new Date().getDate();

	    $("#select_day").val(day);
        // basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.loadElectricDataTab('0');

        // 获取电表历史数据
        getHistoryData(day);
	});
	$("#queryElectricHisData").on('hidden.bs.modal', function(event) {

        $("#ElectricHisDataTab").tabulator("clearData");

	    $('#his_span_fNum').text('');

	});
	//关闭模态框清空表单值
    $("#compareElectricParams").on('hidden.bs.modal', function (event) {
        $("#UppermachineTab").tabulator("clearData");
        i = 0;
    });
  //关闭模态框清空表单值
	$("#compareAmmeterInfo").on('hidden.bs.modal',function(event) {
        $("#UndermachineTab").tabulator("clearData");
		$(this).find("input").val("");
	});
	//加载天数下拉框
	function loadSelectDay(){
        var x = document.getElementById("select_day");//轮询周期

		// 获取当月多少天
		var date = new Date();
        var day = new Date(date.getFullYear(), date.getMonth(), 0).getDate();   //最后一个参数为0,意为获取2018年10月一共多少天

		for (var i = 1; i <= day; i++) {
			var obj = document.createElement("option");
			obj.value = i;
			obj.text = i + "日";
			x.appendChild(obj);
		}
	}
	//加载上位机电能参数
	function loadUppermachineTab(){

        queryElectricParams(function (result)
        {
            $("#UppermachineTab").tabulator("setData", result.data);
        })
	}

	// 获取电能参数
	function queryElectricParams(callback)
    {
        if (typeof callback !== 'function')
		{
		    return;
		}

        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/queryElectricParamsByAmmeter",
            type : "post",
            data : {
                f_sys_name : $("#f_sys_name").val()
            },
            beforeSend: function () {
                showLoad();
            },
            success : function(result) {
                callback(result);

            },
            complete: function () {
                hiddenLoad();
            },
            error : function(result) {
                swal(result.msg, "", "error");
            },
        });

    }

    function getRealTimeData()
    {
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterRealTimeData",
            type : "get",
            data : {
                f_sys_name : $("#f_sys_name").val()
            },
            beforeSend: function () {
                showLoad();
            },
            success : function(result) {

                if(result.status == '1'){
                    swal({
                        title: result.msg,// 展示的标题
                        text: "",// 内容
                        type: "success",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000,
                    });
                }else
                {
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
            error : function(result) {
                swal(result.msg, "", "error");
            },
        });
    }

    function getHistoryData(selectDay)
    {
        $.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterHistoryData",
            type : "get",
            data : {
                fSysName : $("#f_sys_name").val(),
                selectDay: selectDay
            },
            beforeSend: function () {
                showLoad();
            },
            success : function(result) {

                if(result.status == '1'){
                    swal({
                        title: result.msg,// 展示的标题
                        text: "",// 内容
                        type: "success",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000,
                    });
                }else
				{
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
            error : function(result) {
                swal(result.msg, "", "error");
            },
        });
    }
	//加载下位机电能参数
	/*function loadUndermachineTab(){
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/compareEnectric",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val()
				},
			beforeSend: function () {
				showLoad();
			},
			success : function(result) {
				$("#UndermachineTab").tabulator("setData", result.data);
				//加载上位机电能参数
				loadUppermachineTab();
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			},
		});
	};*/

	//填充上位机电表信息
	function fill_localAmmeter(){
		$("#localAmmeter_F_SBID").val($("#f_id").val());
		// $("#localAmmeter_F_SYS_NAME").val($("#f_sys_name").val());

		$("#localAmmeter_F_SBLXBH").val($("#f_blxbh_group option[value='" + $('#f_blxbh_group').val() + "']").text());
		$("#localAmmeter_F_NICK_NAME").val($("#f_nick_name").val());
		$("#localAmmeter_F_COMM_RATE").val($("#f_comm_rate_group option[value='" + $('#f_comm_rate_group').val() + "']").text());
		$("#localAmmeter_F_PROTOCOL_TYPE").val( $("#f_protocol_type_group option[value='" + $('#f_protocol_type_group').val() + "']").text());
		$("#localAmmeter_F_COMMUNICATION_PORT").val($("#f_communication_port").val());
		$("#localAmmeter_F_CJFABH").val($("#f_cjfabh_group option[value='" + $('#f_cjfabh_group').val() + "']").text());
		$("#localAmmeter_F_WLDZ").val($("#f_wldz").val());
		$("#localAmmeter_F_DESCRIPTION").val($("#f_description").val());
		$("#localAmmeter_F_AZWZ").val($("#f_azwz").val());
		($("#hidden_f_enabled").val() == "1" ) ? $("#localAmmeter_F_ENABLED").val("是") : $("#localAmmeter_F_ENABLED").val("否");
        $("#localAmmeter_F_COM_DATA_BIT").val($("#f_com_data_bit option[value='" + $('#f_com_data_bit').val() + "']").text()); // 数据位
        $("#localAmmeter_F_COM_PARITY_BIT").val($("#f_com_parity_bit option[value='" + $('#f_com_parity_bit').val() + "']").text()); // 校验位
        $("#localAmmeter_F_COM_STOP_BIT").val($("#f_com_stop_bit option[value='" + $('#f_com_stop_bit').val() + "']").text()); // 停止位
        $("#localAmmeter_F_FUNCTION_CODE").val($("#f_function_code option[value='" + $('#f_function_code').val() + "']").text()); // 功能码

	}
	//填充下位机电表信息
	/*function fill_underAmmeter(){
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/compareAmmeter",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val(),
				f_psys_name : basedatamanage_eqmanage_eqconfiguration_sbdy.getNodepid(),//父系统名称
				nodeLevel : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//选中节点在树上的级数
				f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
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
				$("#underAmmeter_F_SYS_NAME").val(result.data.MeterID);
				$("#underAmmeter_F_SBLXBH").val(result.data.MeterType);
				$("#underAmmeter_F_NICK_NAME").val(result.data.Alias);
				$("#underAmmeter_F_COMM_RATE").val(result.data.ComRate);//通信波特率
				$("#underAmmeter_F_PROTOCOL_TYPE").val(result.data.ComAgreementType);//通信协议
				$("#underAmmeter_F_COMMUNICATION_PORT").val(result.data.ComPort);//通信端口
				$("#underAmmeter_F_CJFABH").val(result.data.acquisition_scheme_name_xwj);//采集方案
				$("#underAmmeter_F_WLDZ").val(result.data.PhyAddr);
				$("#underAmmeter_F_DESCRIPTION").val(result.data.Description);
				$("#underAmmeter_F_AZWZ").val(result.data.Location);
// 				$("#underAmmeter_F_PERCENTAGE").val(result.data.Description);
				$("#underAmmeter_F_ENABLED").val(result.data.Active);
				//判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
				($("#underAmmeter_F_SYS_NAME").val() != $("#localAmmeter_F_SYS_NAME").val() ) ? $("#localAmmeter_F_SYS_NAME").attr('style','color:red') : $("#localAmmeter_F_SYS_NAME").removeAttr('style');
				($("#underAmmeter_F_SBLXBH").val() != $("#localAmmeter_F_SBLXBH").val() ) ? $("#localAmmeter_F_SBLXBH").attr('style','color:red') : $("#localAmmeter_F_SBLXBH").removeAttr('style');
				($("#underAmmeter_F_NICK_NAME").val() != $("#localAmmeter_F_NICK_NAME").val() ) ? $("#localAmmeter_F_NICK_NAME").attr('style','color:red') : $("#localAmmeter_F_NICK_NAME").removeAttr('style');
				($("#underAmmeter_F_COMM_RATE").val() != $("#localAmmeter_F_COMM_RATE").val() ) ? $("#localAmmeter_F_COMM_RATE").attr('style','color:red') : $("#localAmmeter_F_COMM_RATE").removeAttr('style');
				($("#underAmmeter_F_PROTOCOL_TYPE").val() != $("#localAmmeter_F_PROTOCOL_TYPE").val() ) ? $("#localAmmeter_F_PROTOCOL_TYPE").attr('style','color:red') : $("#localAmmeter_F_PROTOCOL_TYPE").removeAttr('style');
				($("#underAmmeter_F_COMMUNICATION_PORT").val() != $("#localAmmeter_F_COMMUNICATION_PORT").val() ) ? $("#localAmmeter_F_COMMUNICATION_PORT").attr('style','color:red') : $("#localAmmeter_F_COMMUNICATION_PORT").removeAttr('style');
				($("#underAmmeter_F_CJFABH").val() != $("#localAmmeter_F_CJFABH").val() ) ? $("#localAmmeter_F_CJFABH").attr('style','color:red') : $("#localAmmeter_F_CJFABH").removeAttr('style');
				($("#underAmmeter_F_WLDZ").val() != $("#localAmmeter_F_WLDZ").val() ) ? $("#localAmmeter_F_WLDZ").attr('style','color:red') : $("#localAmmeter_F_WLDZ").removeAttr('style');
				($("#underAmmeter_F_DESCRIPTION").val() != $("#localAmmeter_F_DESCRIPTION").val() ) ? $("#localAmmeter_F_DESCRIPTION").attr('style','color:red') : $("#localAmmeter_F_DESCRIPTION").removeAttr('style');
				($("#underAmmeter_F_AZWZ").val() != $("#localAmmeter_F_AZWZ").val() ) ? $("#localAmmeter_F_AZWZ").attr('style','color:red') : $("#localAmmeter_F_AZWZ").removeAttr('style');
				($("#underAmmeter_F_ENABLED").val() != $("#local_F_COLL_CYCLE").val() ) ? $("#local_F_COLL_CYCLE").attr('style','color:red') : $("#local_F_COLL_CYCLE").removeAttr('style');
				}
				},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			},
		});
	}*/

	function fill_underAmmeter(){
		$.ajax({
            url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getAmmeterParam",
			type : "post",
			data : {
				f_sys_name : $("#f_sys_name").val()
				},
			beforeSend: function () {
				showLoad();
			},
			success : function(result)
            {
                if (result.status !== '1')
                {
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
			error : function(result) {
				swal(result.msg, "", "error");
			},
		});
	}

	//创建并设置“上位机电能参数”table属性
	$("#UppermachineTab").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		movableColumns:true,
		columns:[
		// {title:"序号",width:30,formatter:"rownum",frozen:false,sorter:"number",align:"center",headerSort:false},
		{title:"能耗ID", field:"F_DNBH", width:70,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var ElectricIDVal = noinclude_branch_Rows[i].getData().electricID;
			        if(cell.getValue() != ElectricIDVal.toString()){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    },},
		{title:"能耗名称", field:"F_DNMC", width:80,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var ElectricNameVal= noinclude_branch_Rows[i].getData().electricName;
			        if(cell.getValue() != ElectricNameVal){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    }},
		{title:"偏移地址", field:"F_PYDZ",width:72,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var OffsetAddrVal= noinclude_branch_Rows[i].getData().offsetAddr;
			        if(cell.getValue() != OffsetAddrVal){
			            return "<span style='color:red; font-weight:bold;'>" + Number(cell.getValue()).toString(16) + "</span>";
			        }else{
			            return Number(cell.getValue()).toString(16);
			        }
		       }else{
		    	   return Number(cell.getValue()).toString(16);
		       }
		    }},
		{title:"数据长度", field:"F_DATA_LENGTH",width:72,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var DataLengthVal= noinclude_branch_Rows[i].getData().dataLength;
			        if(cell.getValue() != DataLengthVal.toString()){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    }},
		{title:"编码规则", field:"F_BMGZ_MC",width:72,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var DataEncodeTypeVal= dataEncodeTypeObject[noinclude_branch_Rows[i].getData().dataEncodeType];
			        if(cell.getValue() != DataEncodeTypeVal){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    }},
		{title:"单位", field:"F_UNIT",width:50,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var UintTypeVal= noinclude_branch_Rows[i].getData().unitType;
			        if(cell.getValue() != UintTypeVal){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    }},
		{title:"小数点位置", field:"F_SCALING_POSITION",width:80,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
		       var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
		       if(undefined != noinclude_branch_Rows[i]){
		    	   var PointLocationVal= noinclude_branch_Rows[i].getData().pointLocation;
			        if(cell.getValue() != PointLocationVal.toString()){
			            return "<span style='color:red; font-weight:bold;'>" + cell.getValue() + "</span>";
			        }else{
			            return cell.getValue();
			        }
		       }else{
		    	   return cell.getValue();
		       }
		    }},
		{title:"数据类型", field:"F_DATA_TYPE",width:72,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
                var value = dataTypeSwitch(cell.getValue());

                var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
                if(undefined != noinclude_branch_Rows[i]){
                    var PointLocationVal = noinclude_branch_Rows[i].getData().dataType;
                    if(cell.getValue() != PointLocationVal.toString()){
                        return "<span style='color:red; font-weight:bold;'>" + value + "</span>";
                    }else{
                        return value;
                    }
                }else{
                    return value;
                }

		    }},
		{title:"解码顺序", field:"F_CODE_SEQ",width:72,sorter:"string",editor:false,align:"center",headerSort:false, formatter:function(cell, formatterParams){
                var value = codeSeqSwitch(cell.getValue());
                var noinclude_branch_Rows = $("#UndermachineTab").tabulator("getRows");
                if(undefined != noinclude_branch_Rows[i]){
                    var PointLocationVal = noinclude_branch_Rows[i].getData().codeSeq;
                    i++;
                    if(cell.getValue() != PointLocationVal.toString()){
                        return "<span style='color:red; font-weight:bold;'>" + value + "</span>";
                    }else{
                        return value;
                    }
                }else{
                    return value;
                }
            }},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});

	function dataTypeSwitch(value)
	{
        switch (value)
        {
            case '0':
                return 'int';
            case '1':
                return 'float';
            case '2':
                return 'double';
        }
	}

	function codeSeqSwitch(value)
	{

        switch (value)
        {
            case '0':
                return '12';
            case '1':
                return '21';
            case '2':
                return '1234';
            case '3':
                return '4321';
            case '4':
                return '2143';
            case '5':
                return '3412';
            case '6':
                return '123456';
            case '7':
                return '12345678';
        }
	}

	//创建并设置“下位机电能参数”table属性
	$("#UndermachineTab").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
			// {title:"序号",width:50,formatter:"rownum",frozen:false,sorter:"number",align:"center",headerSort:false},
			{title:"能耗ID", field:"electricID", width:72,sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"能耗名称", field:"electricName", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"偏移地址", field:"offsetAddr",width:72,sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return Number(cell.getValue()).toString(16);
                }},
			{title:"数据长度", field:"dataLength",width:72,sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"编码规则", field:"dataEncodeType",width:72,sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return dataEncodeTypeObject[cell.getValue()];
                }},
			{title:"单位", field:"unitType",width:50,sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"小数点位置", field:"pointLocation",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"数据类型", field:"dataType",width:72,sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return dataTypeSwitch(cell.getValue() + '')
                }},
			{title:"解码顺序", field:"codeSeq",width:72,sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return codeSeqSwitch(cell.getValue() + '');
                }},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});
	//创建并设置“获取电能参数数据”table属性
	$("#ElectricDataTab").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		movableColumns:true,
		// groupBy:"time",//按time列分组
		groupToggleElement:"header",
		columns:[
			// {title:"序号",formatter:"rownum",frozen:false,sorter:"number",align:"center",headerSort:false},
			{title:"能耗ID", field:"F_DNBH", sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"能耗名称", field:"F_DNMC", sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"偏移地址", field:"F_PYDZ",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return Number(cell.getValue()).toString(16);
                }},
			{title:"数据长度", field:"F_DATA_LENGTH",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"编码规则", field:"F_BMGZ_MC",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"单位", field:"F_UNIT",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"小数点位置", field:"F_SCALING_POSITION",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"数据类型", field:"F_DATA_TYPE",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"解码顺序", field:"F_CODE_SEQ",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"数据值", field:"rawdata",sorter:"string",editor:false,align:"center",headerSort:false, formatter: function (cell)
                {
                    var val = parseInt(cell.getValue());
                    var data = cell.cell.row.data;
                    var decimals = parseInt(data.F_SCALING_POSITION);
                    var fIsRate = data.F_IS_RATE
                    var rate = parseInt($("#hide_f_percentage").val());

                    if ('1' == fIsRate)
					{
                        return val * rate / (Math.pow(10, (decimals)));
					}else
					{
                        return val  / (Math.pow(10, (decimals)));
					}

                }},
			// {title:"采集时间", field:"time",width:200,sorter:"string",editor:false,align:"center",headerSort:false},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
    	/*groupHeader:function(value, count, data, group){
    	    return "采集时间："+ value + "<span style='color:#d00; margin-left:10px;'>(数量" + count + ")</span>";
    	},*/
	});
	//创建并设置“获取电能参数历史数据”table属性
	$("#ElectricHisDataTab").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		movableColumns:true,
		// groupBy:"time",//按time列分组
		groupToggleElement:"header",
		columns:[
			// {title:"序号",formatter:"rownum",frozen:false,sorter:"number",align:"center",headerSort:false},
            {title:"能耗ID", field:"F_DNBH", width:72,sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"能耗名称", field:"F_DNMC", sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"偏移地址", field:"F_PYDZ",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
                {
                    return Number(cell.getValue()).toString(16);
                }},
			{title:"数据长度", field:"F_DATA_LENGTH",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"编码规则", field:"F_BMGZ_MC",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"单位", field:"F_UNIT",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"小数点位置", field:"F_SCALING_POSITION",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"数据类型", field:"F_DATA_TYPE",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"解码顺序", field:"F_CODE_SEQ",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"数据值", field:"rawdata",sorter:"string",editor:false,align:"center",headerSort:false, formatter: function (cell)
                {
                    var val = parseInt(cell.getValue());
                    var data = cell.cell.row.data;
                    var decimals = parseInt(data.F_SCALING_POSITION);
                    var rate = parseInt($("#hide_f_percentage").val());
                    var fIsRate = data.F_IS_RATE

                    if ('1' == fIsRate)
                    {
                        return val * rate / (Math.pow(10, (decimals)));
                    }else
                    {
                        return val / (Math.pow(10, (decimals)));
                    }

                }},
			{title:"采集时间", field:"time",width:200,sorter:"string",editor:false,align:"center",headerSort:false},
		],
		rowClick:function(e, not_row){
    	},
    	/*groupHeader:function(value, count, data, group){

    	    return "采集时间："+ value + "<span style='color:#d00; margin-left:10px;'>(数量" + count + ")</span>";
    	},*/
	});

    /**
	 * 获取采集参数信息
     */
	function getCollectionParamInfo(num, callback)
    {
        if (!num || typeof callback !== 'function')
		{
		    return;
		}

        $.ajax({
            url: _ctx + "/view/basedatamanage/enegrycollectionmanage/loadIncludeCollM",
            type: "post",
            data:({
                fCjfabh: num,
            }),
            success: function(data) {
                callback(callback);
            }
        });

    }

	return {
		addDifferSet : function(){
			if(basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){
                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
			}
		},
		//wanghongjie
		fill_localDO11:function(callback){//从数据库中查询未修改之前的点位数据,取出来

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
					var qqqq=result.data.F_SYS_NAME;
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
		infoChanged : function() {

			var attrTabName = "BES_AMMETER";
			var attr_f_sys_name = $("#f_sys_name").val();//名称
			var attr_f_nick_name = $("#f_nick_name").val();//别名
			var attr_f_enabled = $('input[name="f_enabled"]:checked').val();//使能状态
			var attr_f_blxbh = $("#f_blxbh_group option:selected").val();//电表类型编号
			var attr_f_blxmc = $("#f_blxbh_group option:selected").text();//电表类型名称
			var attr_f_communication_port = $("#f_communication_port").val();//通信端口
			var attr_f_comm_rate = $("#f_comm_rate_group option:checked").val();//通信波特率编号
			var attr_f_comm_rate_mc = $("#f_comm_rate_group option:checked").text();//通信波特率名称
			var attr_f_protocol_type = $("#f_protocol_type_group option:checked").val();//通信协议编号
			var attr_f_protocol_type_mc = $("#f_protocol_type_group option:checked").text();//通信协议名称
			var attr_f_wldz = $("#f_wldz").val();//物理地址
			var attr_f_azwz = $("#f_azwz").val();//安装位置
			var attr_f_cjfabh = $("#f_cjfabh_group option:checked").val();//采集方案编号
			var attr_f_cjfamc = $("#f_cjfabh_group option:checked").text();//采集方案名称
			var attr_f_percentage = $("#f_percentage").val();//变比
			var attr_f_sblxbh = $("#selPid").val();//设备类型编号
			var attr_f_description = $("#f_description").val();//描述
			var tabName = "BES_SBPZ_STRUCT";//表名
			var old_f_sys_name = $("#f_sys_name_old").val();//新增节点的时候,默认的系统名称
			var f_allpath = $("#f_allpath").text();//全路径
			var f_status = $('input[name="f_status"]:checked').val();//状态
			var f_type = $("#sel_f_type").val();//节点类型
			var f_psys_name = $("#pre_f_sys_name").val(); //父系统名称
			var f_yqbh = basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh();//所属园区
			var f_com_data_bit = $('#f_com_data_bit').val();// 数据位
			var f_com_parity_bit = $('#f_com_parity_bit').val();// 校验位
			var f_com_stop_bit = $('#f_com_stop_bit').val();// 停止位
			var f_function_code = $('#f_function_code').val();// 功能码
            var f_guid = $("#f_guid").val();
            var selId = $("#selId").val(); //型号
            // var assetsCode = $("#meter_assets_code").val();//资产编码

			if (isNaN(attr_f_communication_port))
			{
                swal({
                    title : "通信值必须为数字",// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });

                return;
			}

			if (isNaN(attr_f_percentage))
			{
                swal({
                    title : "变比必须为数字",// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
			};

			//判断系统名称字符串第一个字符是否为数字,是的话不能添加
			var  nameSub = Number(attr_f_sys_name.substring(0,1));
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
			if (/.*[\u4e00-\u9fa5]+.*$/.test(attr_f_sys_name)){
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
			// if(!assetsCode || !assetsCode.trim()){
            //     swal({
            //         title : '资产编码不可为空!',// 展示的标题
            //         text : "",// 内容
            //         type : "warning",
            //         showCloseButton : false, // 展示关闭按钮
            //         allowOutsideClick : false,
            //         showConfirmButton : false,
            //         timer : 1000
            //     });
			//     return;
            // }

			if (eval($("#saveBtn").attr("name"))) {
                $.issp_ajax({
                    // url: _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
                    url: _ctx + "/view/basedatamanage/eqmanage/updateAmmeter",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    type: "post",
                    data: JSON.stringify({
                        tabName: attrTabName,//电表表名
                        f_sys_name: attr_f_sys_name,//电表ID
                        myInput_f_sys_name :f_guid,//点位的自增id
                        f_guid:f_guid,
                        f_nick_name: attr_f_nick_name,//电表别名
                        f_blxbh: attr_f_blxbh,//电表类型编号
                        f_blxmc: attr_f_blxmc,//电表类型名称
                        f_communication_port: attr_f_communication_port,//通信端口
                        f_comm_rate: attr_f_comm_rate,//通信波特率编号
                        f_comm_rate_mc: attr_f_comm_rate_mc,//通信波特率名称
                        f_protocol_type: attr_f_protocol_type,//通信协议编号
                        f_protocol_type_mc: attr_f_protocol_type_mc,//通信协议名称
                        f_wldz: attr_f_wldz,//物理地址
                        f_azwz: attr_f_azwz,//安装位置
                        f_cjfabh: attr_f_cjfabh,//采集方案编号
                        f_cjfamc: attr_f_cjfamc,//采集方案名称
                        f_percentage: attr_f_percentage,//变比
                        f_sblxbh: attr_f_sblxbh,//设备类型编号
                        f_type: selId,//型号
                        f_description: attr_f_description,//描述
// 						f_status : f_status,//状态
                        f_enabled: attr_f_enabled,//使能状态
                        f_com_data_bit: f_com_data_bit,// 数据位
                        f_com_parity_bit: f_com_parity_bit,// 校验位
                        f_com_stop_bit: f_com_stop_bit,// 停止位
                        f_function_code: f_function_code// 功能码
                        // ,assets_code:assetsCode.trim() //资产编码
                    }),
                    beforeSend: function () {
                        showLoad();
                    },
                    success : function(result) {
                        $("#saveBtn").attr("disabled", true); //【保存】设置为无效
                        basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();

                        if (result.status == '1')
                        { //保存成功时

                            swal({
                                title: result.msg,// 展示的标题
                                text: "",// 内容
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1000
                            });


                            $("#sbdyInfo").load(_ctx +"/view/basedatamanage/eqmanage/sbdy_ammeterInfo",
                                    {
                                        "f_sys_name" : attr_f_sys_name,
                                        "nodeTabName" : attrTabName,
                                        "f_type":'28'
                                    });//跳转所选节点属性页面

                        }else
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
                    complete: function () {
                        hiddenLoad();
                    },
                    error: function (result) {
                        swal(result.msg, "", "error");
                    }
                });
            } else {
				$("#ch_f_sys_name").val(attr_f_sys_name);
				$("#ch_f_nick_name").val(attr_f_nick_name);
				$("#ch_f_allpath").val(f_allpath);
				$("#ch_f_type").val(f_type);
				$("#ch_f_psys_name").val(f_psys_name);

				$.ajax({//新增属性节点时
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addAmmeter",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						attrTabName : attrTabName,
						attr_f_sys_name : attr_f_sys_name,//名称
						attr_f_nick_name : attr_f_nick_name,//别名
						attr_f_enabled : attr_f_enabled,//使能状态
						attr_f_blxbh : attr_f_blxbh,//电表类型编号
						attr_f_blxmc : attr_f_blxmc,//电表类型名称
						attr_f_communication_port : attr_f_communication_port,//通信端口
						attr_f_comm_rate : attr_f_comm_rate,//通信波特率编号
						attr_f_comm_rate_mc : attr_f_comm_rate_mc,//通信波特率名称
						attr_f_protocol_type : attr_f_protocol_type,//通信协议编号
						attr_f_protocol_type_mc : attr_f_protocol_type_mc,//通信协议名称
						attr_f_wldz : attr_f_wldz,//物理地址
						attr_f_azwz : attr_f_azwz,//安装位置
						attr_f_cjfabh : attr_f_cjfabh,//采集方案编号
						attr_f_cjfamc : attr_f_cjfamc,//采集方案名称
						attr_f_percentage : attr_f_percentage,//变比
						attr_f_sblxbh : attr_f_sblxbh,//设备类型编号
						attr_f_type : attr_f_blxbh,//型号
						attr_f_description : attr_f_description,//描述
						tabName : tabName,//表名
						f_sys_name : attr_f_sys_name,//系统名称
						old_f_sys_name:old_f_sys_name,//新增节点的时候,默认的系统名称
						f_nick_name : attr_f_nick_name,//别 名
						f_allpath : f_allpath,//全路径
						f_status : f_status,//状态
						f_description : attr_f_description,//描 述
						f_type : f_type,//节点类型
						f_psys_name : f_psys_name, //父系统名称
						f_yqbh : f_yqbh,//所属园区
                        f_com_data_bit: f_com_data_bit,// 数据位
                        f_com_parity_bit: f_com_parity_bit,// 校验位
                        f_com_stop_bit: f_com_stop_bit,// 停止位
                        f_function_code: f_function_code// 功能码
                        // ,assets_code:assetsCode.trim() //资产编码
					}),
					beforeSend: function () {
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

							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), { exactMatch : true } ]);//获取刚修改属性的节点

							var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							addeNodeId = $("#ch_f_sys_name").val();
							addedNodeText = $("#ch_f_nick_name").val();
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
							// basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							$("#tree_sbdy").treeview("selectNode", changeNode);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							$("#f_sys_name").attr("readonly", "readonly");//系统名称不可修改
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
                            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
						} else {//保存失败时
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

						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
					},
					complete: function () {
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
				$("#synBtn").attr("disabled", true);
				$("#contrastBtn").attr("disabled", true);
				$("#accEnergyDataBtn").attr("disabled", true);
				$("#accHisEnergyDataBtn").attr("disabled", true);
				submitFlg = false;
			} else {
				$("#synBtn").attr("disabled", false);
				$("#contrastBtn").attr("disabled", false);
				$("#accEnergyDataBtn").attr("disabled", false);
				$("#accHisEnergyDataBtn").attr("disabled", false);
				submitFlg = true;
			}
			$("#saveBtn").attr("name", submitFlg);
		},
		//同步电表
		syncMeter : function(){
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
				url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/synAmmeter",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val(),
					f_psys_name : basedatamanage_eqmanage_eqconfiguration_sbdy.getNodepid(),//父系统名称
					nodeLevel : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeLevel(),//模块/电表在树上的级数
					f_node_attribution : basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					if (result.status == '0') {
						swal({
							title : result.msg,// 展示的标题
							text : "",// 内容
							type : "warning",
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
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
			}
		},
		//加载能耗数据
		/*loadElectricDataTab : function (Selectday){
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/queryMeterHisData",
				type : "post",
				data : {
					f_sys_name : $("#f_sys_name").val(),
					selectday : Selectday,
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
						if ((Selectday == '' || Selectday == null) && Selectday != '0') {
							$.ajax({
								url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/queryMeterHisDataGrid",
								type : "post",
								data : {
									ElectricData : result.data.ElectricData,
									meteruuid : $("#f_sys_name").val(),
									histime : result.data.histime,
									type : 'noselectday',
									},
								beforeSend: function () {
									showLoad();
								},
								success : function(data) {

									if(data.status == '0'){
										swal({
								        	title : "系统提示",// 展示的标题
								   			text : data.msg,// 内容
								   			type: "error",
								   			showCloseButton : false, // 展示关闭按钮
								   			allowOutsideClick : false,
								   			showConfirmButton : false,
								   			timer: 1000,
								   		});
									}else{
									$("#ElectricDataTab").tabulator("setData", data.data);
									$("#span_fSysname").text($("#f_sys_name").val());
									$("#span_fNickname").text($("#f_nick_name").val());
									$("#span_fNum").text(data.data.length);
									$("#span_fDate").text(data.data[0].time);
									}
								},
								complete: function () {
									hiddenLoad();
								},
								error : function(data) {
									swal(data.msg, "", "error");
								},
							});
						} else {
							$.ajax({
								url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/queryMeterHisDataGrid",
								type : "post",
								data : {
									gridata : result.data.griddata,
									meteruuid : $("#f_sys_name").val(),
									type : 'selectday',
									},
								beforeSend: function () {
									showLoad();
								},
								success : function(data) {

									$("#ElectricHisDataTab").tabulator("setData", data.data);
									$("#his_span_fSysname").text($("#f_sys_name").val());
									$("#his_span_fNickname").text($("#f_nick_name").val());
									$("#his_span_fNum").text(result.data.HisDataHourCount);
// 									$("#span_fDate").text(data.data[0].time);
								},
								complete: function () {
									hiddenLoad();
								},
								error : function(data) {
									swal(data.msg, "", "error");
								},
							});
						}
					}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				},
			});

		},*/
        setSyncPage,

        handleLowerData: function (data)
        {
            if (null == data)
            {
                return;
            }

            var meterParameter = data.meterParameter;
            var electricDataCollectMethod = data.electricDataCollectMethod;

            if (null == meterParameter || null == electricDataCollectMethod)
			{
			    return;
			}


            var active = meterParameter.active;

            if (active === 1)
            {
                active = '是';
            }else if (active === 0)
            {
                active = '否';
            }else {
                active = '';
            }


            var fSblxbh = $("#f_blxbh_group option[value='" + meterParameter.meterType + "']").text();

            var fCjfabh = '';

            var cjfabhOption = $("#f_cjfabh_group option");

            for(var i = 0; i < cjfabhOption.length; i++)
			{
                if (electricDataCollectMethod.collectMethodID == Number(cjfabhOption[i].value))
				{
                    fCjfabh = cjfabhOption[i].value;
				}
			}

            var fCjfabh = $("#f_cjfabh_group option[value='" + fCjfabh + "']").text()
			var fProtocolType = $("#f_protocol_type_group option[value='" + meterParameter.comAgreementType + "']").text();
            var fCommRate = $("#f_comm_rate_group option[value='" + meterParameter.comRate + "']").text();
			var fComDataBit = $("#f_com_data_bit option[value='" + meterParameter.comDataBit + "']").text();
			var fComParityBit = $("#f_com_parity_bit option[value='" + meterParameter.comParityBit + "']").text();
			var fComStopBit = $("#f_com_stop_bit option[value='" + meterParameter.comStopBit + "']").text();
			var fFunctionCode = $("#f_function_code option[value='" + meterParameter.functionCode + "']").text();

            $("#underAmmeter_F_SBID").val(meterParameter.meterID);
            $("#underAmmeter_F_NICK_NAME").val(meterParameter.alias); // 电表别名
            $("#underAmmeter_F_SBLXBH").val(fSblxbh); // 电表类型
            $("#underAmmeter_F_ENABLED").val(active); // 使能
            $("#underAmmeter_F_CJFABH").val(fCjfabh); //采集方案
            $("#underAmmeter_F_PROTOCOL_TYPE").val(fProtocolType); // 通信协议
            $("#underAmmeter_F_COM_DATA_BIT").val(fComDataBit); // 数据位
            $("#underAmmeter_F_COM_PARITY_BIT").val(fComParityBit); // 校验位
            $("#underAmmeter_F_COM_STOP_BIT").val(fComStopBit); // 停止位
            $("#underAmmeter_F_FUNCTION_CODE").val(fFunctionCode); // 功能码
            $("#underAmmeter_F_COMM_RATE").val(fCommRate); // 波特率
            $("#underAmmeter_F_WLDZ").val(meterParameter.phyAddr); // 物理地址
            $("#underAmmeter_F_COMMUNICATION_PORT").val(meterParameter.comPort); // 通信端口
            $("#underAmmeter_F_AZWZ").val(meterParameter.location); // 安装位置
            $("#underAmmeter_F_DESCRIPTION").val(meterParameter.description); // 描述信息

            //判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
            // ($("#underAmmeter_F_SYS_NAME").val() != $("#localAmmeter_F_SYS_NAME").val() ) ? $("#localAmmeter_F_SYS_NAME").attr('style','color:red') : $("#localAmmeter_F_SYS_NAME").removeAttr('style');
            ($("#underAmmeter_F_SBID").val() != $("#localAmmeter_F_SBID").val() )
					? $("#localAmmeter_F_SBID").attr('style','color:red')
					: $("#localAmmeter_F_SBID").removeAttr('style');

            ($("#underAmmeter_F_NICK_NAME").val() != $("#localAmmeter_F_NICK_NAME").val() )
					? $("#localAmmeter_F_NICK_NAME").attr('style','color:red')
					: $("#localAmmeter_F_NICK_NAME").removeAttr('style');

            ($("#underAmmeter_F_SBLXBH").val() != $("#localAmmeter_F_SBLXBH").val() )
					? $("#localAmmeter_F_SBLXBH").attr('style','color:red')
					: $("#localAmmeter_F_SBLXBH").removeAttr('style');

            ($("#underAmmeter_F_ENABLED").val() != $("#localAmmeter_F_ENABLED").val() )
					? $("#localAmmeter_F_ENABLED").attr('style','color:red')
					: $("#localAmmeter_F_ENABLED").removeAttr('style');

            ($("#underAmmeter_F_CJFABH").val() != $("#localAmmeter_F_CJFABH").val() )
					? $("#localAmmeter_F_CJFABH").attr('style','color:red')
					: $("#localAmmeter_F_CJFABH").removeAttr('style');

            ($("#underAmmeter_F_PROTOCOL_TYPE").val() != $("#localAmmeter_F_PROTOCOL_TYPE").val() )
					? $("#localAmmeter_F_PROTOCOL_TYPE").attr('style','color:red')
					: $("#localAmmeter_F_PROTOCOL_TYPE").removeAttr('style');

            ($("#underAmmeter_F_COM_DATA_BIT").val() != $("#localAmmeter_F_COM_DATA_BIT").val() )
					? $("#localAmmeter_F_COM_DATA_BIT").attr('style','color:red')
					: $("#localAmmeter_F_COM_DATA_BIT").removeAttr('style');

            ($("#underAmmeter_F_COM_PARITY_BIT").val() != $("#localAmmeter_F_COM_PARITY_BIT").val() )
					? $("#localAmmeter_F_COM_PARITY_BIT").attr('style','color:red')
					: $("#localAmmeter_F_COM_PARITY_BIT").removeAttr('style');

            ($("#underAmmeter_F_COM_STOP_BIT").val() != $("#localAmmeter_F_COM_STOP_BIT").val() )
					? $("#localAmmeter_F_COM_STOP_BIT").attr('style','color:red')
					: $("#localAmmeter_F_COM_STOP_BIT").removeAttr('style');

            ($("#underAmmeter_F_FUNCTION_CODE").val() != $("#localAmmeter_F_FUNCTION_CODE").val() )
					? $("#localAmmeter_F_FUNCTION_CODE").attr('style','color:red')
					: $("#localAmmeter_F_FUNCTION_CODE").removeAttr('style');

            ($("#underAmmeter_F_COMM_RATE").val() != $("#localAmmeter_F_COMM_RATE").val() )
					? $("#localAmmeter_F_COMM_RATE").attr('style','color:red')
					: $("#localAmmeter_F_COMM_RATE").removeAttr('style');

            ($("#underAmmeter_F_WLDZ").val() != $("#localAmmeter_F_WLDZ").val() )
					? $("#localAmmeter_F_WLDZ").attr('style','color:red')
					: $("#localAmmeter_F_WLDZ").removeAttr('style');

            ($("#underAmmeter_F_COMMUNICATION_PORT").val() != $("#localAmmeter_F_COMMUNICATION_PORT").val() )
					? $("#localAmmeter_F_COMMUNICATION_PORT").attr('style','color:red')
					: $("#localAmmeter_F_COMMUNICATION_PORT").removeAttr('style');

            ($("#underAmmeter_F_AZWZ").val() != $("#localAmmeter_F_AZWZ").val() )
					? $("#localAmmeter_F_AZWZ").attr('style','color:red')
					: $("#localAmmeter_F_AZWZ").removeAttr('style');

            ($("#underAmmeter_F_DESCRIPTION").val() != $("#localAmmeter_F_DESCRIPTION").val() )
					? $("#localAmmeter_F_DESCRIPTION").attr('style','color:red')
					: $("#localAmmeter_F_DESCRIPTION").removeAttr('style');

            var electricDataInfo = data.electricDataInfo;

            if (!Array.isArray(electricDataInfo))
			{
			    return;
			}

            $("#UndermachineTab").tabulator("setData", electricDataInfo);

        },
		// 接收实时数据
        receiveAmmeterRealTimeData: function (data)
        {
            if (!data)
			{
			    return;
			}

			var collectCount = data.collectCount;

            if (collectCount <= 0)
			{
				return;
			}

			$('#span_fNum').text(collectCount);

            var dateDay = data.dateDay;
            var dateMonth = data.dateMonth;
            var dateYear = data.dateYear;
            var timeHour = data.timeHour + '';

            if (timeHour.length === 1)
            {
                timeHour = '0' + timeHour
            }
            var timeMinute = data.timeMinute  + '';

            if (timeMinute.length === 1)
            {
                timeMinute = '0' + timeMinute
            }

            var timeSecond = data.timeSecond  + '';

            if (timeSecond.length === 1)
            {
                timeSecond = '0' + timeSecond
            }

            var time = '20' + dateYear + '-' + dateMonth + '-' + dateDay + ' ' + timeHour + ':' + timeMinute + ':' + timeSecond;

            $('#span_fDate').text(time);

            var electricData = data.electricData;

            if (!electricData)
			{
			    return;
			}

            electricData = electricData.split(',');

            queryElectricParams(function (result)
            {
                if (!result)
				{
					return;
				}

                var list = result.data;

                if (!Array.isArray(list))
				{
				    return;
				}

				var length = list.length;

                if (collectCount != length)
				{
                    swal({
                        title : '上位机与下位机电能参数个数不一致，请同步数据',// 展示的标题
                        text : "",// 内容
                        type : "warning",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000,
                    });
                    return;
				}

                for (var i = 0; i < length; i++)
				{
                    list[i].rawdata = electricData[i];
                    list[i].F_DATA_TYPE = dataTypeSwitch(list[i].F_DATA_TYPE);
                    list[i].F_CODE_SEQ = codeSeqSwitch(list[i].F_CODE_SEQ);
				}

                $("#ElectricDataTab").tabulator("setData", list);

            });

        },
		// 接收电表历史数据
        receiveAmmeterHistoryData: function (data)
        {
            if (!data)
			{
			    return;
			}

			var hisDataHourCount = data.hisDataHourCount;

            $('#his_span_fNum').text(hisDataHourCount);

            var meterElectricHisData = data.meterElectricHisData;

            if (!Array.isArray(meterElectricHisData))
			{
			    return;
			}

            queryElectricParams(function (result)
            {
                if (!result)
                {
                    return;
                }

                var list = result.data;

                if (!Array.isArray(list))
                {
                    return;
                }

                var tableData = [];

                for (var i = 0; i < meterElectricHisData.length; i++)
				{
				    var item = meterElectricHisData[i];

                    var dateDay = item.dateDay;
                    var dateMonth = item.dateMonth;
                    var dateYear = item.dateYear;
                    var timeHour = item.timeHour  + '';

                    if (timeHour.length === 1)
					{
                        timeHour = '0' + timeHour
					}
                    var timeMinute = item.timeMinute  + '';

                    if (timeMinute.length === 1)
                    {
                        timeMinute = '0' + timeMinute
                    }

                    var timeSecond = item.timeSecond  + '';

                    if (timeSecond.length === 1)
                    {
                        timeSecond = '0' + timeSecond
                    }

                    var time = '20' + dateYear + '-' + dateMonth + '-' + dateDay + ' ' + timeHour + ':' + timeMinute + ':' + timeSecond;

                    var electricData = item.electricData;

                    if (!electricData)
                    {
                        return;
                    }

                    electricData = electricData.split(',');

                    for (var j = 0; j < electricData.length; j++)
                    {
                        var copyData = Object.assign({}, list[j]);

                        copyData.rawdata = electricData[j];
                        copyData.F_DATA_TYPE = dataTypeSwitch(copyData.F_DATA_TYPE);
                        copyData.F_CODE_SEQ = codeSeqSwitch(copyData.F_CODE_SEQ);
                        copyData.time = time;

                        tableData.push(copyData);

                    }
                }

                $("#ElectricHisDataTab").tabulator("setData", tableData);

            })

        },
        getHistoryData,

		/* 物理地址,固定长度14位，长度不够前面补0, 大于14删除前面0 */
        physicalAddressOninput: function (obj)
        {
            var value = obj.value.split('');
            var length = value.length;
            var totalLenght = 14;

            if (length < totalLenght)
			{
				for (var i = 0; i < totalLenght - length; i++)
				{
                    value.unshift('0');
				}

			}else
			{
                for (var j = 0; j < length - totalLenght; j++)
                {
                    value.shift();
                }
			}

            obj.value = value.join('');
        }

    }
})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
