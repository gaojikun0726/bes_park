<style>
    #ddcbc-import-form-control-btn .form-control {
        height: 28px !important;
        line-height: 1.8;
    }

    #ddcbc-import-form-control-btn .btn-primary {
        width: 6.5vw !important;
        height: 3.85vh !important;
    }

</style>
   <div class="attrInfo" id="attrInfo">
       <div class="frist_attr">
           <div class="has-success">
               <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
               <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
           </div>
           <form role="form" id="sbdy_DDCform" name="sbdy_DDCform" class="form-horizontal">
               <div class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">名 称:</label>
                       <div class="col-sm-4">
                           <input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}"
                                  type="text" class="form-control" readonly="readonly"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                           <input type="hidden" id="f_guid" name="f_guid" value="${returnObject.data.F_GUID}">
                           <input type="hidden" id="f_sbid" name="f_sbid" value="${returnObject.data.F_SBID}">
                           <input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                       </div>
                   </div>
                   <div class="has-success">
                       <label class="col-sm-2 control-label">别 名:</label>
                       <div class="col-sm-4">
                           <input type="text" id="f_nick_name" value="${returnObject.data.F_NICK_NAME}"
                                  class="form-control"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                       </div>
                   </div>
               </div>
               <div class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">IP地址:</label>
                       <div class="col-sm-4">
                           <input type="hidden" id="hidden_f_ip_addr" value="${returnObject.data.F_IP_ADDR}">
                           <input type="text" name="ip_b_1" id="ip_b_1" class="ip-input"/>.
                           <input type="text" name="ip_b_2" id="ip_b_2" class="ip-input"/>.
                           <input type="text" name="ip_b_3" id="ip_b_3" class="ip-input"/>.
                           <input type="text" name="ip_b_4" id="ip_b_4" class="ip-input"/>
                       </div>
                       &nbsp;<img id="ip_warn_hint_img" data-value="${returnObject.data.F_CHANNEL_ID}" title="ip地址已更新请及时重启，原ip地址：${returnObject.data.F_CHANNEL_ID}" style="height: 20px; display: none" src="images/warn-circle.png">
                   </div>
               <#-- <div class="has-success">
                   <label class="col-sm-2 control-label">端口:</label>
                   <div class="col-sm-4">
                       <input type="text" id="f_port"  value="${returnObject.data.F_PORT}" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                   </div>
               </div>-->
                   <div class="has-success">
                       <label class="col-sm-2 control-label">使能状态:</label>
                       <input type="hidden" id="hidden_radio_f_enabled" value="${returnObject.data.F_ENABLED}">
                       <div class="col-sm-4">
                           <input type="radio" id="f_enabled_yes" name="f_enabled" value="1" checked="checked"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>是
                           <input type="radio" id="f_enabled_no" name="f_enabled" value="0"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>否
                       </div>
                   </div>
               </div>

               <div id="network_config_slave" style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">默认网关:</label>
                       <div class="col-sm-4">
                           <input type="hidden" id="hidden_f_gateway" value="${returnObject.data.F_GATEWAY}">
                           <input type="text" name="gateway_1" id="gateway_1" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="gateway_2" id="gateway_2" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="gateway_3" id="gateway_3" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="gateway_4" id="gateway_4" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                       </div>
                   </div>

                   <div class="has-success">
                       <label class="col-sm-2 control-label">子网掩码:</label>
                       <div class="col-sm-4">
                           <input type="hidden" id="hidden_f_mask" value="${returnObject.data.F_MASK}">
                           <input type="text" name="mask_1" id="mask_1" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="mask_2" id="mask_2" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="mask_3" id="mask_3" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="mask_4" id="mask_4" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                       </div>
                   </div>

               </div>
               <div class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">安装位置:</label>
                       <div class="col-sm-4">
                           <input type="text" id="f_azwz" value="${returnObject.data.F_AZWZ}" class="form-control"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                       </div>
                   </div>
                   <div class="has-success">
                       <label class="col-sm-2 control-label">采集周期:</label>
                       <!--
                                <div class="col-sm-4">
                                    <input type="text" id="f_coll_cycle"  value="${returnObject.data.F_COLL_CYCLE}" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div> -->
                       <div class="col-sm-4">
                           <input type="hidden" id="f_coll_cycle_selected" value="${returnObject.data.F_COLL_CYCLE}"
                                  class="form-control">
                           <select id="f_coll_cycle_time_group" class="selector"
                                   style="width: 15.5em;height: 2em;border:1px solid green;"
                                   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                       </div>
                   </div>
               </div>
               <div class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">保存周期:</label>
                       <!--
                                <div class="col-sm-4">
                                    <input type="text" id="f_his_data_save_cycle"  value="${returnObject.data.F_HIS_DATA_SAVE_CYCLE}"  class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div> -->
                       <div class="col-sm-4">
                           <input type="hidden" id="f_his_data_save_cycle_selected"
                                  value="${returnObject.data.F_HIS_DATA_SAVE_CYCLE}" class="form-control">
                           <select id="f_his_data_save_cycle_group" class="selector"
                                   style="width: 13em;height: 2em;border:1px solid green;"
                                   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                       </div>
                   </div>
                   <div class="has-success">
                       <label class="col-sm-2 control-label">上传周期:</label>
                       <!--
                                <div class="col-sm-4">
                                    <input type="text" id="f_loop_time" value="${returnObject.data.F_LOOP_TIME}" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>  -->
                       <div class="col-sm-4">
                           <input type="hidden" id="f_loop_time_selected" value="${returnObject.data.F_UPLOAD_CYCLE}"
                                  class="form-control">
                           <select id="f_loop_time_group" class="selector"
                                   style="width: 15.5em;height: 2em;border:1px solid green;"
                                   onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
                       </div>
                   </div>
               </div>
               <div class="vertical-timeline-block eqTreeAttrLineWidth">
                   <#--<div class="has-success">
                       <label class="col-sm-2 control-label">型号:</label>
                       <!--
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_type_selected"  value="${returnObject.data.F_TYPE}" class="form-control">
                                     <select id="f_type_group" class="selector" style="width: 15.5em;height: 2em;" onchange="saveBtnIsEffective()"></select>
                                </div>   &ndash;&gt;
                       <input type="hidden" id="f_sblxbh_selected" value="${returnObject.data.F_SBLXBH}"
                              class="form-control">
                       <input type="hidden" id="f_type_selected" value="${returnObject.data.F_TYPE}"
                              class="form-control">
                       <button id="btn_equipment_type" style="width: 100%;" type="button" class="btn btn-primary"
                               data-toggle="modal" data-target='#modal-equipment-type-tree'>请选择型号&nbsp;
                           <i class="fa fa-angle-double-right"></i></button>
                   </div>-->
                   <div class="has-success">
                       <label class="col-sm-2 control-label" style="margin-left:5px;">归属区域:</label>
                       <div class="col-sm-4">
                           <input type="text" id="f_ssqy" value="${returnObject.data.F_SSQY}" class="form-control"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                       </div>
                   </div>

                       <div class="has-success">
                           <label class="col-sm-2 control-label">描述:</label>
                           <div class="col-sm-4">
                               <input type="text" id="f_description" value="${returnObject.data.F_DESCRIPTION}"
                                      class="form-control"
                                      onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                           </div>
                       </div>

               </div>

               <div id="network_config_master" style="display: none" class="vertical-timeline-block eqTreeAttrLineWidth">
                   <div class="has-success">
                       <label class="col-sm-2 control-label">主机IP:</label>
                       <div class="col-sm-4">
                           <input type="hidden" id="hidden_f_ip_master" value="${returnObject.data.F_IP_MASTER}">
                           <input type="text" name="ip_master_1" id="ip_master_1" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="ip_master_2" id="ip_master_2" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="ip_master_3" id="ip_master_3" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>.
                           <input type="text" name="ip_master_4" id="ip_master_4" class="ip-input" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
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

               <div class="vertical-timeline-block eqTreeAttrLineWidth">

                   <div class="has-success">
                       <label class="col-sm-2 control-label">状 态:</label>
                       <div class="col-sm-4">
                           <input type="checkbox" id="f_collector_state" disabled name="f_collector_state"
                                  value="${returnObject.data.F_COLLECTOR_STATE}"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                           <a id="collector_state"></a>
                           <input type="checkbox" id="f_onlinestate" disabled name="f_onlinestate"
                                  value="${returnObject.data.F_ONLINE_STATE}"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"/>
                           <a id="collector_onlinestate"></a>
                       </div>
                   </div>

                   <div class="has-success">
                       <label class="col-sm-2 control-label">设备时间:</label>
                       <div class="col-sm-4">
                           <input type="text" readonly id="f_collector_time" value="" class="form-control"
                                  onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                       </div>
                   </div>
               </div>

           </form>
           <div style="margin-left: 17px;margin-top: 3em; padding-right: 10px;;">
                                   <span>
									<button id="setDateBtn" class="btn sbtreeNodeBtn"
                            <#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operCollectController(2);">设置时间</button>-->
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.setTime();">设置时间</button>
                    <button id="getDateBtn" class="btn sbtreeNodeBtn"
                            <#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getCollectorTime(5);">获取时间</button>-->
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.getTimeEdc();">获取时间</button>
                    <button id="reStartBtn" class="btn sbtreeNodeBtn"
                            <#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operCollectController(3);">重启</button>-->
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.restart();">重启</button>
                    <button id="reSetBtn" class="btn sbtreeNodeBtn"
                            <#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.reSet_validation('4');">重置</button>-->
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.resetEdc();">重置</button>
                    <#--<button id="synMoreDataBtn" class="btn sbtreeNodeBtn"
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synCollector('1');">批量同步数据</button>-->
								    <button id="synDataBtn" class="btn sbtreeNodeBtn"
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.synCollector('0');">同步数据</button>
                    <button id="dataContrast" class="btn sbtreeNodeBtn" data-toggle="modal"
                            data-target="#compareCollector">数据对比</button>
                    <button id="remoteUpBtn" class="btn sbtreeNodeBtn"
                            <#--onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operCollectController(10);">远程升级</button>-->
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.remoteUpgrade();">远程升级</button>
                    <#--wanghongjie 能耗远程升级 start 2020/05/09-->
									<button id="collectorRemoteUpgrades" class="btn sbtreeNodeBtn"
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.collector_impReport()">下发程序</button>
                                    <#--wanghongjie 能耗远程升级 end-->
									<button id="saveBtn" style="margin-left: 10px;" class="btn sbtreeNodeBtn"
                                            type="submit"
                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                                    </span>
           </div>
           <input type="hidden" id="hidden_collector_id" value="${besSbPzStruct.f_sys_name}"/>
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
<!-- 设备类型树 -->
<div class="modal fade" id="modal-equipment-type-tree" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:380px">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">设备类型</h4>
            </div>
            <div class="modal-body" style="height:600px;overflow-y: auto;">
                <div id="tree_equipment_type"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                        onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<!--wanghongjie 能耗上传模态框 -->
<div class="modal fade import-Model" id="collector_import_Model" tabindex="-1" role="dialog" data-backdrop="false"
     aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ImportmyModalLabel">导入文件</h4>
            </div>
            <div class="modal-body">
                <div class="row" id="ddcbc-import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="collector_RemoteUpgrades">
                            <input id="collector_RemoteUpgradesFile" type="file" name="file" class="file-loading">
                            <textarea class='form-control' name="ddcbcInfoText" placeholder="请输入文本"
                                      style='display: none;'></textarea>
                            <input type="text" id="f_ip" name="f_ip" value="${returnObject.data.F_CHANNEL_ID}"
                                   TYPE="hidden" style="display: none;">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.collectorUpload('#collector_RemoteUpgrades')">
                    下发程序
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!----采集器信息对比--->
<div class="modal fade" id="compareCollector" style="margin-left:-16%" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:900px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机采集器信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
                <div style="float:left;width:52%">
                    <button class="btn btn-md" style="cursor:default"><span>上位机采集器信息</span></button>
                </div>
                <div style="float:left;width:40%">
                    <button class="btn btn-md" style="cursor:default"><span>下位机采集器信息</span></button>
                </div>
                <!----上位机信息开始--->
                <div class="notIncludeCss" style="width:400px;height:500px; overflow-y: scroll; overflow-x:hidden;">
                    <form id="local_collectorInfo" name="local_collectorInfo" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">采集器ID<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_GUID" name="local_F_GUID" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                       <#-- <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">IP地址<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_IP_ADDR" name="local_F_IP_ADDR" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">默认网关<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_GATEWAY" name="local_F_GATEWAY" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">子网掩码<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_MASK" name="local_F_MASK" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>-->

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">采集器名称<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_SYS_NAME" name="local_F_SYS_NAME" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">采集器别名<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_NICK_NAME" name="local_F_NICK_NAME" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">安装位置<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_AZWZ" name="local_F_AZWZ" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属区域<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_SSQY" name="local_F_SSQY" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_ENABLED" name="local_F_ENABLED" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">采集周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_COLL_CYCLE" name="local_F_COLL_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">保存周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_HIS_DATA_SAVE_CYCLE" name="local_F_HIS_DATA_SAVE_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">上传周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_UPLOAD_CYCLE" name="local_F_UPLOAD_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_DESCRIPTION" name="local_F_DESCRIPTION"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>

                       <#-- <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">主机IP<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_IP_MASTER" name="local_F_IP_MASTER" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">主机端口<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="local_F_PORT_MASTER" name="local_F_PORT_MASTER" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>-->
                    </form>
                </div>
                <!----上位机信息结束--->

                <!----下位机信息开始--->
                <div class="includeCss" style="width:400px;height:500px;margin:5px 0 0 36px; overflow-y: scroll; overflow-x:hidden;">
                    <form id="under_collectorInfo" name="under_collectorInfo" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">采集器ID<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_GUID" name="under_F_GUID" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>


                      <#--  <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">IP地址<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_IP_ADDR" name="under_F_IP_ADDR" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">默认网关<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_GATEWAY" name="under_F_GATEWAY" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">子网掩码<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_MASK" name="under_F_MASK" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>-->

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zmc">采集器名称<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_SYS_NAME" name="under_F_SYS_NAME" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_RealBh">采集器别名<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_NICK_NAME" name="under_F_NICK_NAME" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">安装位置<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_AZWZ" name="under_F_AZWZ" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">所属区域<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_SSQY" name="under_F_SSQY" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_ENABLED" name="under_F_ENABLED" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">采集周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_COLL_CYCLE" name="under_F_COLL_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">保存周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_HIS_DATA_SAVE_CYCLE" name="under_F_HIS_DATA_SAVE_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">上传周期<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_UPLOAD_CYCLE" name="under_F_UPLOAD_CYCLE"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">描述信息<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_DESCRIPTION" name="under_F_DESCRIPTION"
                                       class="form-control" readonly="readonly">
                            </div>
                        </div>

                      <#--  <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">主机IP<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_IP_MASTER" name="under_F_IP_MASTER" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">主机端口<span
                                    class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="under_F_PORT_MASTER" name="under_F_PORT_MASTER" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>-->
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
<#include '/besview/basedatamanage/eqmanage/eqconfiguration/sbdy_setInfo.ftl'>  <!--引入属性设置页面-->
<script type="text/javascript">
    ;
    var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function ($, window, document, undefined)
    {
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

	<#if returnObject.data.F_ENABLED == '0'>//使能状态(不使用【0】 使用【1】)
	$("input[name='f_enabled']:eq(1)").attr("checked", 'checked');
    <#else>
	$("input[name='f_enabled']:eq(0)").attr("checked", 'checked');
    </#if>
        var _ctx = '${ctx}';
        var eMap = new Map();
        var f_xh_type;
        //新增修改判断
        if (basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd())
        {
            f_xh_type = "${f_xh_type}";
        } else
        {
            f_xh_type = "${besSbPzStruct.f_xh_type}";
        }


        function get_equipmentTypetree()
        {
            $.ajax({
                type: "post",
                url: _ctx + "/view/basedatamanage/eqmanage/sbdy_getEquipmentModuleTree",
                data: {ftype: f_xh_type},
                dataType: "json",
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (result)
                {
                    var etempMap = result.map;//
                    for (var key in etempMap)
                    {
                        eMap.put(key, etempMap[key]);
                    }
                    ;
                    $('#tree_equipment_type').treeview(
                            {
                                data: result.list, // 数据源
                                highlightSelected: true, //是否高亮选中
                                levels: 4,
                                enableLinks: true,//必须在节点属性给出href属性
                                color: "#4a4747",
                                onNodeSelected: function (event, nodeData)
                                {
                                    $('#tree_equipment_type').treeview('clearSearch');//清除搜索选中高亮
                                    var selNode = $("#tree_equipment_type").treeview('getSelected');
                                    var selPnodeText = $("#tree_equipment_type").treeview('getParents', selNode);
                                    if (selPnodeText.length > 0)
                                    {//
                                        $("#btn_equipment_type").text(selPnodeText[0].text + '->' + nodeData.text);
                                        $("#tree_f_sblxbh").val(selPnodeText[0].id);
                                        $("#tree_f_type").val(nodeData.id);
                                    } else
                                    {
                                        $("#btn_equipment_type").text(nodeData.text);
                                        $("#tree_f_sblxbh").val(nodeData.id);
                                        $("#tree_f_type").val('');
                                    }
                                }
                            });
                },
                complete: function ()
                {
                    hiddenLoad();
                },
                error: function (nodeData)
                {
                    swal(nodeData.msg, "", "error");
                }
            });
        };

        /*Start delete by xiepufeng at 2020年5月11日*/
        /*function getCollectorLoopTime() {//获取当前采集器轮询周期
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getCollectorLoopTime",
                contentType : "application/json; charset=utf-8",
                type : "get",
                data : {
                    f_sys_name : $("#hidden_collector_id").val(), //系统名称
                },
                beforeSend: function () {
                    showLoad();
                },
                success : function(result) {
                    if(result.status =="1"){//设置当前采集器轮询周期
                        $("#f_loop_time_selected").val(result.data);
                        $("#f_loop_time_group option[value="+result.data+"]").attr('selected','selected');
                    }
                },
                complete: function () {
                    hiddenLoad();
                },
                error : function(result) {
                    swal(result.msg, "", "error");
                }
            });
        };*/

        /*End delete by xiepufeng at 2020年5月11日*/
        function getCollectorLoopTime()
        {//获取当前采集器轮询周期

            $("#f_loop_time_group option[value=" + ${returnObject.data.F_UPLOAD_CYCLE} +"]").attr('selected', 'selected');

        };

        function setDefaultCollectorSpinner()
        {//填充采集器周期下拉列表
            for (var i = 1; i < 125; i += 1)
            {
                var x = document.getElementById("f_loop_time_group");//轮询周期
                var obj = document.createElement("option");
                obj.value = i;
                obj.text = i + "分钟";
                x.appendChild(obj);
                var x1 = document.getElementById("f_his_data_save_cycle_group");//历史保存周期
                var obj1 = document.createElement("option");
                obj1.value = i;
                obj1.text = i + "小时";
                x1.appendChild(obj1);
                var x2 = document.getElementById("f_coll_cycle_time_group");//采集周期
                var obj2 = document.createElement("option");
                obj2.value = i;
                obj2.text = i + "分钟";
                x2.appendChild(obj2);
            }
        };


        /* Start add by xiepufeng at 2020年5月13日 */
        function setSyncPage(state)
        {

            if (typeof state !== 'boolean')
            {
                return;
            }

            if (state)
            {
                $("#f_collector_state").prop("checked", true);
                $("#collector_state").text("已同步");
                $("#collector_state").attr("style", "color: #00ff2d");
            } else
            {
                $("#f_collector_state").prop("checked", false);
                $("#collector_state").text("未同步");
                $("#collector_state").attr("style", "color: #ff0000");
            }
        }

        /* End add by xiepufeng at 2020年5月13日 */

        //状态设置
        function setCheckbox()
        {
            //同步状态
            if ($("#f_collector_state").val() == "1")
            {
                setSyncPage(true);
            } else
            {
                setSyncPage(false);
            }
            //在线状态
            if ($("#f_onlinestate").val() == "1")
            {
                $("#f_onlinestate").prop("checked", true);
                $("#collector_onlinestate").text("在线");
                $("#collector_onlinestate").attr("style", "color: #00ff2d");
            } else
            {
                $("#f_onlinestate").prop("checked", false);
                $("#collector_onlinestate").text("离线");
                $("#collector_onlinestate").attr("style", "color: #ff0000");
            }
            //使能状态
            if ($("#hidden_radio_f_enabled").val() == "1")
            {
                $("#f_enabled_yes").prop("checked", true);
                $("#hidden_radio_f_enabled").val("1");
            } else if ($("#hidden_radio_f_enabled").val() == "0")
            {
                $("#f_enabled_no").prop("checked", true);
                $("#hidden_radio_f_enabled").val("0");
            }
        }

        //填充上位机采集器信息
        function fill_localcollector()
        {
            $("#local_F_GUID").val($("#f_sbid").val());
            $("#local_F_SYS_NAME").val($("#f_sys_name").val());
            $("#local_F_NICK_NAME").val($("#f_nick_name").val());
            $("#local_F_AZWZ").val($("#f_azwz").val());
            $("#local_F_SSQY").val($("#f_ssqy").val());
            ($("#hidden_radio_f_enabled").val() == "1") ? $("#local_F_ENABLED").val("是") : $("#local_F_ENABLED").val("否");
            $("#local_F_COLL_CYCLE").val($("#f_coll_cycle_time_group option:selected").text());
            $("#local_F_HIS_DATA_SAVE_CYCLE").val($("#f_his_data_save_cycle_group option:selected").text());
            $("#local_F_UPLOAD_CYCLE").val($("#f_loop_time_group option:selected").text());
            $("#local_F_DESCRIPTION").val($("#f_description").val());
            $("#local_F_IP_ADDR").val($("#ip_b_1").val() + "." + $("#ip_b_2").val() + "." + $("#ip_b_3").val() + "." + $("#ip_b_4").val());
            $("#local_F_MASK").val( $("#mask_1").val() + "." + $("#mask_2").val() + "." + $("#mask_3").val() + "." + $("#mask_4").val());
            $("#local_F_GATEWAY").val($("#gateway_1").val() + "." + $("#gateway_2").val() + "." + $("#gateway_3").val() + "." + $("#gateway_4").val());
            $("#local_F_IP_MASTER").val($("#ip_master_1").val() + "." + $("#ip_master_2").val() + "." + $("#ip_master_3").val() + "." + $("#ip_master_4").val());
            $("#local_F_PORT_MASTER").val($("#f_port_master").val());
        }

        /*Strat replace by xiepufeng at 2020年5月21日 reason 上位机与下位机通信升级*/
        //填充下位机采集器信息
        /*function fill_undercollector()
        {
            $.ajax({
                url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/compareDDC",
                type: "post",
                data: {
                    fIp: $("#hidden_f_ip_addr").val(),
                    fPort: $("#f_port").val(),
                    fSysName: $("#f_sys_name").val(),
                    f_guid: $("#f_guid").val(),
                    fNodeType: basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNodeType(),//节点类型
                    f_node_attribution: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
                },
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (result)
                {
                    if (result.status == '0')
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
                    } else
                    {
                        $("#under_F_GUID").val(result.data.ID);
                        $("#under_F_SYS_NAME").val(result.data.Name);
                        $("#under_F_NICK_NAME").val(result.data.Alias);
                        $("#under_F_AZWZ").val(result.data.Location);
                        $("#under_F_SSQY").val(result.data.Zone);//所属区域
                        $("#under_F_ENABLED").val(result.data.Active);//使能
                        $("#under_F_COLL_CYCLE").val(result.data.SamplePeriod);//采集周期
                        $("#under_F_HIS_DATA_SAVE_CYCLE").val(result.data.HisdataSavePeriod);
                        $("#under_F_DESCRIPTION").val(result.data.Description);
                        //判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
                        ($("#under_F_GUID").val() != $("#local_F_GUID").val()) ? $("#local_F_GUID").attr('style', 'color:red') : $("#local_F_GUID").removeAttr('style');
                        ($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val()) ? $("#local_F_SYS_NAME").attr('style', 'color:red') : $("#local_F_SYS_NAME").removeAttr('style');
                        ($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val()) ? $("#local_F_NICK_NAME").attr('style', 'color:red') : $("#local_F_NICK_NAME").removeAttr('style');
                        ($("#under_F_AZWZ").val() != $("#local_F_AZWZ").val()) ? $("#local_F_AZWZ").attr('style', 'color:red') : $("#local_F_AZWZ").removeAttr('style');
                        ($("#under_F_SSQY").val() != $("#local_F_SSQY").val()) ? $("#local_F_SSQY").attr('style', 'color:red') : $("#local_F_SSQY").removeAttr('style');
                        ($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val()) ? $("#local_F_ENABLED").attr('style', 'color:red') : $("#local_F_ENABLED").removeAttr('style');
                        ($("#under_F_HIS_DATA_SAVE_CYCLE").val() != $("#local_F_HIS_DATA_SAVE_CYCLE").val()) ? $("#local_F_HIS_DATA_SAVE_CYCLE").attr('style', 'color:red') : $("#local_F_HIS_DATA_SAVE_CYCLE").removeAttr('style');
                        ($("#under_F_DESCRIPTION").val() != $("#local_F_DESCRIPTION").val()) ? $("#local_F_DESCRIPTION").attr('style', 'color:red') : $("#local_F_DESCRIPTION").removeAttr('style');
                        ($("#under_F_COLL_CYCLE").val() != $("#local_F_COLL_CYCLE").val()) ? $("#local_F_COLL_CYCLE").attr('style', 'color:red') : $("#local_F_COLL_CYCLE").removeAttr('style');
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
        }*/
        /*End*/

        /*给下位机下发指令，获取控制器参数*/
        /*Strat update by xiepufeng at 2020年5月21日 reason 上位机与下位机通信升级*/
        function fill_undercollector()
        {
            $.ajax({
                url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getControllerParam",
                type: "post",
                data: {
                    fSysName: $("#f_sys_name").val()
                },
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (result)
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
        /*End update by xiepufeng at 2020年5月21日 reason 上位机与下位机通信升级*/

        $(function ()
        {
            get_equipmentTypetree();//生成设备型号树
            var hidIp = $("#hidden_f_ip_addr").val();
            if (hidIp != null && hidIp != "")
            {//初始化ip地址
                var ips = hidIp.split(".");
                $("#ip_b_1").val(ips[0]);
                $("#ip_b_2").val(ips[1]);
                $("#ip_b_3").val(ips[2]);
                $("#ip_b_4").val(ips[3]);
            }

            // 默认网关
            var gateway = $("#hidden_f_gateway").val();
            if (gateway != null && gateway != "")
            {
                var gatewaySplit = gateway.split(".");
                $("#gateway_1").val(gatewaySplit[0]);
                $("#gateway_2").val(gatewaySplit[1]);
                $("#gateway_3").val(gatewaySplit[2]);
                $("#gateway_4").val(gatewaySplit[3]);
            }

            // 子网掩码
            var mask = $("#hidden_f_mask").val();
            if (mask != null && mask != "")
            {
                var maskSplit = mask.split(".");
                $("#mask_1").val(maskSplit[0]);
                $("#mask_2").val(maskSplit[1]);
                $("#mask_3").val(maskSplit[2]);
                $("#mask_4").val(maskSplit[3]);
            }

            // 主机ip
            var ipMaster = $("#hidden_f_ip_master").val();
            if (ipMaster != null && ipMaster != "")
            {
                var ipMasterSplit = ipMaster.split(".");
                $("#ip_master_1").val(ipMasterSplit[0]);
                $("#ip_master_2").val(ipMasterSplit[1]);
                $("#ip_master_3").val(ipMasterSplit[2]);
                $("#ip_master_4").val(ipMasterSplit[3]);
            }

            setTimeout(function ()
            { //设置设备型号树默认选中
                var f_sblxbhId = $("#f_sblxbh_selected").val();
                var f_typeId = $("#f_type_selected").val();
                var tex1 = '';
                var tex2 = '';
                if (!(eMap.get(f_sblxbhId) == null || eMap.get(f_sblxbhId) == ''))
                    tex1 = eMap.get(f_sblxbhId);
                if (!(eMap.get(f_typeId) == null || eMap.get(f_typeId) == ''))
                    tex2 = eMap.get(f_typeId);
                if (!(tex1 == null || tex1 == ''))
                {
                    $("#btn_equipment_type").text(tex1 + '->' + tex2);
                } else
                {
                    if (!(tex2 == null || tex2 == ''))
                        $("#btn_equipment_type").text(tex2);
                }
            }, 100);
            setDefaultCollectorSpinner();//填充采集器周期下拉列表
            getCollectorLoopTime();//获取当前采集器轮询周期
            setCheckbox();//设置状态
            //设置当前历史保存周期
            if ($("#f_his_data_save_cycle_selected").val() != "")
                $("#f_his_data_save_cycle_group option[value=" + $("#f_his_data_save_cycle_selected")
                        .val() + "]").attr('selected', 'selected');
            //设置当前采集周期
            if ($("#f_coll_cycle_selected").val() != "")
                $("#f_coll_cycle_time_group option[value=" + $("#f_coll_cycle_selected")
                        .val() + "]").attr('selected', 'selected');
        });

        //采集器信息对比模态框出现前加载(可拖动)
        $("#compareCollector").on('show.bs.modal', function (event)
        {
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#compareCollector .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight,
            });

            var button = $(event.relatedTarget);
            var id = button.data("id");			//获取用户组编号
            $(this).draggable({
                handle: ".modal-header",   	// 只能点击头部拖动
            });
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

            fill_localcollector();
            fill_undercollector();
        });
        //关闭模态框清空表单值
        $("#compareCollector").on('hidden.bs.modal', function (event)
        {
            $(this).find("input").val("");
        });

        //fileinput 上传插件初始化
        function initFileinput()
        {
            $("#collector_RemoteUpgradesFile").fileinput({
                language: 'zh', //设置语言
                allowedFileExtensions: ['BIN'],//接收的文件后缀,
                showUpload: true, //是否显示上传按钮
                showCaption: true,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                maxFileSize: 3072,//最大单个文件上传大小
                maxFileCount: 1,//最大上传个数
                showUpload: false,//是否显示上传按钮
                showBrowse: true,//是否显示浏览按钮
                showPreview: false,//是否显示预览区域
                autoReplace: true,//是否自动替换
                showRemove: true,//是否显示移除按钮
                uploadExtraData: function (id, index)
                {
                    var importExcel = $("#collector_RemoteUpgradesFile").val();
                    return {"fold": importExcel};
                },//区分不同的模块：fold：文件夹
                //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
            }).on("fileuploaded", function ()
            {
                clearForm();//清空form表单
            }).on("filebatchuploadsuccess", function ()
            {
                clearForm();//清空form表单
            });
        }

        return {
            //wanghongjie 能耗远程升级
            collectorUpload: function (formId)
            {
                var form = new FormData($(formId)[0]);     //通过id获取表单的数据
                if ($("#collector_RemoteUpgradesFile").val() == "")
                {
                    swal("请选择文件", "", "warning");
                    return;
                }

                $.ajax({
                    type: "POST",//请求的类型
                    url: "${ctx}/view/basedatamanage/eqmanage/collectorUpload",//请求的路径
                    data: form, //请求的参数
                    //async: false,//异步请求
                    //cache: false,//false的话会在url后面加一个时间缀，让它跑到服务器获取结果。cache只有GET方式的时候有效
                    contentType: false,
                    processData: false,
                    beforeSend: function ()
                    {
                        showLoad();
                    },
                    success: function (data)
                    { //成功返回触发的方法
                        if (data.data.data == true)
                        {//如果后台返回下发程序成功,为true
                            swal(data.data.msg, "", "success");
                        } else if (data.data.data == false)
                        {//如果后台返回下发程序失败,为false
                            swal(data.data.msg, "", "error");
                        }else if(data.data == false){
                            swal(data.msg, "", "error");
                        }
                        $("#collector_import_Model").modal("hide");

                    },
                    complete: function ()
                    {
                        hiddenLoad();
                    },
                    //请求失败触发的方法
                    error: function ()
                    {
                        console.log("ajax请求失败");
                    }
                });
            },
            collector_impReport: function ()
            {
                //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成
                $("#collector_import_Model").modal("show");
            },
            addDifferSet: function ()
            {
                if (basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd())
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
                }
            },
            fill_localDO11: function (callback)
            {

                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getPointInfo1",
                    type: "post",
                    data: {
                        f_nick_name: $("#f_nick_name").val(),
                        f_guid: $("#f_guid").val(),
                        f_node_type: $("#f_node_type").val(),
                        //f_id:$("#f_id").val(),
                    },
                    beforeSend: function ()
                    {
                        showLoad();
                    },
                    success: function (result)
                    {
                        callback(result)
                        var qqqq = result.data.F_SYS_NAME;
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
                /*var aa=result_data.data.F_SYS_NAME;
                return aa;*/

            },
            //信息发生变更时
            infoChanged: function ()
            {
                if (!basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.ipIsIllegal())
                {//验证IP地址
                    return;
                }

                var attrTabName = "BES_COLLECTOR";
                var attr_f_sys_name = $("#f_sys_name").val();//名称
                var attr_f_nick_name = $("#f_nick_name").val();//别 名
                var attr_f_ip_addr = $("#ip_b_1").val() + "." + $("#ip_b_2").val() + "." + $("#ip_b_3").val() + "." + $("#ip_b_4").val();//采集器IP地址
                var attr_f_port = $("#f_port").val();//采集器端口
                var attr_f_node_type = $("#sel_f_type").val();//节点类型
                var attr_f_type = $("#tree_f_type").val();//型号
                var attr_f_sblxbh = $("#tree_f_sblxbh").val();//设备类型编号
                var attr_f_ssqy = $("#f_ssqy").val();//归属区域
                var attr_f_azwz = $("#f_azwz").val(); //安装位置
                var attr_f_description = $("#f_description").val();//描述
                var attr_f_enabled = $('input[name="f_enabled"]:checked').val();//使能状态
                var attr_f_coll_cycle = $("#f_coll_cycle_time_group option:checked").val();//采集周期
                var attr_f_his_data_save_cycle = $("#f_his_data_save_cycle_group option:checked").val();//历史保存周期
                var attr_f_loop_time = $("#f_loop_time_group option:checked").val();//轮询周期
                var tabName = "BES_SBPZ_STRUCT";//表名
                var f_status = $('input[name="f_status"]:checked').val();
                var f_allpath = $("#f_allpath").text();//全路径
                var f_psys_name = $("#pre_f_sys_name").val(); //父系统名称
                // var f_yqbh = basedatamanage_eqmanage_eqconfiguration_sbdy.getyqbh();//所属园区
                var f_gateway = $("#gateway_1").val() + "." + $("#gateway_2").val() + "." + $("#gateway_3").val() + "." + $("#gateway_4").val();//默认网关
                var f_mask = $("#mask_1").val() + "." + $("#mask_2").val() + "." + $("#mask_3").val() + "." + $("#mask_4").val();//子网掩码
                var f_ip_master = $("#ip_master_1").val() + "." + $("#ip_master_2").val() + "." + $("#ip_master_3").val() + "." + $("#ip_master_4").val();//主机ip
                var f_port_master = $("#f_port_master").val(); //主机端口
                var f_guid = $("#f_guid").val();//GUID
                var f_sys_name = $("#hidden_collector_id").val();


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

                if (eval($("#saveBtn").attr("name")))
                {
                    if (f_port_master == "" || isNaN(f_port_master))
                    {
                        swal({
                            title: "端口必须是数字",// 展示的标题
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
                        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editCollector",
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        type: "post",
                        data: JSON.stringify({
                            attrTabName: attrTabName,//表名
                            f_guid: f_guid,//GUID
                            attr_f_sys_name: attr_f_sys_name,//手动输入系统名称
                            attr_f_nick_name: attr_f_nick_name,//别 名
                            //attr_f_ip_addr : $("#f_ip_addr").val(),//采集器IP地址
                            attr_f_ip_addr: attr_f_ip_addr,//采集器IP地址
                            attr_f_port: attr_f_port,//采集器端口
                            attr_f_type: attr_f_type,//型号
                            attr_f_sblxbh: attr_f_sblxbh,//设备类型编号
                            attr_f_ssqy: attr_f_ssqy,//归属区域
                            attr_f_azwz: attr_f_azwz, //安装位置
                            //attr_f_enabled : attr_f_enabled,//使能状态
                            f_enabled: attr_f_enabled,//使能状态
                            attr_f_coll_cycle: attr_f_coll_cycle,//采集周期
                            attr_f_his_data_save_cycle: attr_f_his_data_save_cycle,//历史保存周期
                            attr_f_loop_time: attr_f_loop_time,//轮询周期
                            tabName: tabName,//表名
                            f_sys_name: f_sys_name,
                            //f_status : f_status,
                            //f_allpath: f_allpath,//全路径
                            f_nick_name: attr_f_nick_name,//别 名
                            attr_f_description: attr_f_description,//描述
                            f_gateway: f_gateway,//默认网关
                            f_mask: f_mask,//子网掩码
                            f_ip_master: f_ip_master,//主机ip
                            f_port_master: f_port_master //主机端口
                        }),
                        beforeSend: function ()
                        {
                            showLoad();
                        },
                        success: function (result)
                        {

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
                            } else if (result.status === "1")
                            {
                                $('#hidden_f_ip_addr').val(attr_f_ip_addr);

                                if (attr_f_ip_addr !== $('#ip_warn_hint_img').attr('data-value'))
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

                                $("#sbdyInfo").load(_ctx +"/view/basedatamanage/eqmanage/sbdy_collector",
                                        {
                                            "f_sys_name" : attr_f_sys_name,
                                            "nodeTabName" : attrTabName,
                                            "f_type":'26'
                                        });//跳转所选节点属性页面


                            } else if (result.status === "3")//wanghongjie 验证ip是否重复,如果重复,则提交不了
                            {
                                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffectiveByIP();
                            } else
                            {
                                $("#saveBtn").attr("disabled", true); //【保存】设置为无效
                                basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
                            }

                        },
                        complete: function ()
                        {
                            hiddenLoad();
                        },
                        error: function (result)
                        {
                            swal(result.msg, "", "error");
                        },
                    });
                } else
                {

                    $("#ch_f_sys_name").val(attr_f_sys_name);
                    $("#ch_f_nick_name").val(attr_f_nick_name);
                    $("#ch_f_allpath").val(f_allpath);
                    $("#ch_f_type").val(attr_f_node_type);
                    $("#ch_f_psys_name").val(f_psys_name);
                    $.ajax({//新增属性节点时
                        url: _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addCollector",
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        type: "post",
                        data: JSON.stringify({
                            attrTabName: attrTabName,
                            attr_f_sys_name: attr_f_sys_name,//名称
                            attr_f_nick_name: attr_f_nick_name,//别 名
                            //attr_f_ip_addr : $("#f_ip_addr").val(),//采集器IP地址
                            attr_f_ip_addr: attr_f_ip_addr,//采集器IP地址
                            attr_f_port: attr_f_port,//采集器端口
                            attr_f_node_type: attr_f_node_type,//
                            attr_f_type: attr_f_type,//型号
                            attr_f_sblxbh: attr_f_sblxbh,//设备类型编号
                            attr_f_ssqy: attr_f_ssqy,//归属区域
                            attr_f_azwz: attr_f_azwz, //安装位置
                            attr_f_description: attr_f_description,//描述
                            attr_f_enabled: attr_f_enabled,//使能状态
                            attr_f_coll_cycle: attr_f_coll_cycle,//采集周期
                            attr_f_his_data_save_cycle: attr_f_his_data_save_cycle,//历史保存周期
                            attr_f_loop_time: attr_f_loop_time,//轮询周期
                            tabName: tabName,//表名
                            f_sys_name: attr_f_sys_name,
                            f_nick_name: attr_f_nick_name,//别 名
                            f_status: f_status,
                            f_allpath: f_allpath,//全路径
                            f_description: attr_f_description,//描 述
                            f_type: attr_f_node_type,//节点类型
                            f_psys_name: f_psys_name, //父系统名称
                            // f_yqbh: f_yqbh,//所属园区
                        }),
                        beforeSend: function ()
                        {
                            showLoad();
                        },
                        success: function (returnObject)
                        {
                            if (returnObject.status == '2')
                            {
                                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
                                return;
                            } else if (returnObject.status == '3')
                            {//wanghongjie 验证ip是否重复,如果重复,则提交不了
                                basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffectiveByIP();
                                return;
                            } else
                            {
                                basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
                            }

                            if (returnObject.status == '1')
                            {//保存成功时

                                swal({
                                    title: returnObject.msg,// 展示的标题
                                    text: "",// 内容
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                });

                                var changeNode = $('#tree_sbdy').treeview('search', [basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), {exactMatch: true}]);//获取刚修改属性的节点
                                var PNodes = $('#tree_sbdy').treeview('getParents', changeNode);
                                addeNodeId = $("#ch_f_sys_name").val();
                                addedNodeText = $("#ch_f_nick_name").val();
                                //更新新保存的节点
                                $('#tree_sbdy').treeview('updateNode', [changeNode, {
                                    nodeTreeId: addeNodeId,
                                    id: addeNodeId,
                                    text: addedNodeText,
                                    nodeType: changeNode[0].nodeType,
                                    pid: changeNode[0].pid,
                                    image: changeNode[0].image,
                                    nodeAttribution: PNodes[0].nodeAttribution,
                                    state: {selected: false}
                                }]);
                                basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
                                $("#tree_sbdy").treeview("selectNode", changeNode);
                                basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
                                $("#saveBtn").attr("disabled", true); //【新增】设置为无效
                                $("#f_sys_name").attr("readonly", "readonly");
                                basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);


                            } else
                            {//保存失败时
                                // basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeAddFalse(returnObject);

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
                            $("#saveBtn").text("保存");
                            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.refreshSubmitFlg();
                            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
                            basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
                        },
                        complete: function ()
                        {
                            hiddenLoad();
                        },
                        error: function (returnObject)
                        {
                            swal(returnObject.msg, "", "error");
                        },
                    });
                }
            },
            refreshSubmitFlg: function ()
            {//更新按钮状态
                var submitFlg = true;
                if ($("#saveBtn").text() == "新增")
                {
                    $("#setDateBtn").attr("disabled", true);
                    $("#getDateBtn").attr("disabled", true);
                    $("#reSetBtn").attr("disabled", true);
                    $("#reStartBtn").attr("disabled", true);
                    $("#remoteUpBtn").attr("disabled", true);
                    $("#dataContrast").attr("disabled", true);
                    $("#synDataBtn").attr("disabled", true);
                    $("#synMoreDataBtn").attr("disabled", true);
                    submitFlg = false;
                } else
                {
                    $("#setDateBtn").attr("disabled", false);
                    $("#getDateBtn").attr("disabled", false);
                    $("#reSetBtn").attr("disabled", false);
                    $("#reStartBtn").attr("disabled", false);
                    $("#remoteUpBtn").attr("disabled", false);
                    $("#dataContrast").attr("disabled", false);
                    $("#synDataBtn").attr("disabled", false);
                    $("#synMoreDataBtn").attr("disabled", false);
                    submitFlg = true;
                }
                $("#saveBtn").attr("name", submitFlg);
            },
            //恢复出厂设置前验证
           /* reSet_validation: function (index)
            {
                swal({
                    title: "您确定要恢复出厂设置吗?",
                    text: "会清空当前采集器下所有的总线及电表！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#1ab394",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                }, function ()
                {
                    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.operCollectController(index);
                });
            },*/
            //采集器远程操作，设置时间/重启/远程升级
           /* operCollectController: function (index)
            {
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/operDDCController",
                    type: "post",
                    data: {
                        fSysName: $("#f_sys_name").val(),
                        fIp: $("#hidden_f_ip_addr").val(),
                        fPort: $("#f_port").val(),
                        fIndex: index,
                        fNodeType: basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNodeType(),//节点类型
                        f_node_attribution: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
                    },
                    beforeSend: function ()
                    {
                        showLoad();
                    },
                    success: function (result)
                    {
                        if (result.status == '0')
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
                        } else
                        {
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
                    complete: function ()
                    {
                        hiddenLoad();
                    },
                    error: function (result)
                    {
                        swal(result.msg, "", "error");
                    }
                });
            },*/

            /* Start add by xiepufeng at 2020年5月18日 remark 能耗采集器远程升级指令下发 reason 功能独立减少耦合*/
            // 远程升级
            remoteUpgrade: function()
            {

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
                        url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/remoteUpgradeEdc",
                        type: "post",
                        data: {
                            fSysName: $("#f_sys_name").val()
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
            },

            // 设置时间
            setTime: function()
            {
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/setTimeEdc",
                    type: "post",
                    data: {
                        fSysName: $("#f_sys_name").val()
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
            },

            // 重启能耗采集器
            restart: function()
            {

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
                        url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/restartEdc",
                        type: "post",
                        data: {
                            fSysName: $("#f_sys_name").val()
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


            },
            /* End add by xiepufeng at 2020年5月18日 remark 能耗采集器远程升级指令下发 reason 功能独立减少耦合*/

            //采集器，获取时间
            /*getCollectorTime: function (index)
            {
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getDDCTime",
                    type: "post",
                    data: {
                        fSysName: $("#f_sys_name").val(),
                        fIp: $("#hidden_f_ip_addr").val(),
                        fPort: $("#f_port").val(),
                        fIndex: index,
                        fNodeType: basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNodeType(),//节点类型
                        f_node_attribution: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
                    },
                    beforeSend: function ()
                    {
                        showLoad();
                    },
                    success: function (result)
                    {
                        if (result.status == '0')
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
                        } else
                        {
                            swal({
                                title: result.msg,// 展示的标题
                                text: "",// 内容
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1000
                            });
                            $("#f_collector_time").val(result.data);
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
            },*/

            /*Strat add by xiepufeng remark 获取能耗采集器时间*/
            getTimeEdc: function()
            {
                $.ajax({
                    url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getTimeEdc",
                    type: "post",
                    data: {
                        fSysName: $("#f_sys_name").val()
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
            },
            /*End add by xiepufeng remark 获取能耗采集器时间*/

            /*Start add by xiepufeng at 2020年5月19日*/
            // 重置能耗采集器
            resetEdc: function()
            {

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
                        url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/resetEdc",
                        type: "post",
                        data: {
                            fSysName: $("#f_sys_name").val()
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
            },
            /*End add by xiepufeng at 2020年5月19日*/

            //同步采集器
            synCollector: function (isMoreFlag)
            {
                if (!($("#saveBtn").prop("disabled")))
                {
                    swal({
                        title: "数据已修改，请先保存",// 展示的标题
                        text: "",// 内容
                        type: "warning",
                        showCloseButton: true, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                } else
                {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/synDDC",
                        type: "post",
                        data: {
                            f_sys_name: $("#f_sys_name").val(),
                            fNodeType: basedatamanage_eqmanage_eqconfiguration_sbdy.getSelectedNodeType(),//节点类型
                            f_node_attribution: basedatamanage_eqmanage_eqconfiguration_sbdy.getnodeAttribution(),//所属系统
                            isMoreFlag: isMoreFlag,
                        },
                        beforeSend: function ()
                        {
                            showLoad();
                        },
                        success: function (result)
                        {
                            if (result.status == '0')
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
                            } else
                            {
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

            pageInit: function ()
            {
                initFileinput();
            },
            setSyncPage,
            handleLowerData: function (data)
            {
                if (null == data)
                {
                    return;
                }

                var active = data.active;

                if (active === 1)
                {
                    active = '是';
                }else if (active === 0)
                {
                    active = '否';
                }else {
                    active = '';
                }

                $("#under_F_GUID").val(data.id);
                $("#under_F_SYS_NAME").val(data.name);
                $("#under_F_NICK_NAME").val(data.alias);
                $("#under_F_AZWZ").val(data.location);
                $("#under_F_SSQY").val(data.zone);//所属区域
                $("#under_F_ENABLED").val(active);//使能
                $("#under_F_COLL_CYCLE").val(data.samplePeriod + '分钟');//采集周期
                $("#under_F_HIS_DATA_SAVE_CYCLE").val(data.hisDataSavePeriod + '小时');
                $("#under_F_UPLOAD_CYCLE").val(data.upDataSamplePeriod + '分钟');
                $("#under_F_DESCRIPTION").val(data.description);
                $("#under_F_IP_ADDR").val(data.ip);
                $("#under_F_MASK").val(data.mask);
                $("#under_F_GATEWAY").val(data.gateWay);
                $("#under_F_IP_MASTER").val(data.serverIP);
                $("#under_F_PORT_MASTER").val(data.serverPort);
                //判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
                ($("#under_F_GUID").val() != $("#local_F_GUID").val()) ? $("#local_F_GUID").attr('style', 'color:red') : $("#local_F_GUID").removeAttr('style');
                ($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val()) ? $("#local_F_SYS_NAME").attr('style', 'color:red') : $("#local_F_SYS_NAME").removeAttr('style');
                ($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val()) ? $("#local_F_NICK_NAME").attr('style', 'color:red') : $("#local_F_NICK_NAME").removeAttr('style');
                ($("#under_F_AZWZ").val() != $("#local_F_AZWZ").val()) ? $("#local_F_AZWZ").attr('style', 'color:red') : $("#local_F_AZWZ").removeAttr('style');
                ($("#under_F_SSQY").val() != $("#local_F_SSQY").val()) ? $("#local_F_SSQY").attr('style', 'color:red') : $("#local_F_SSQY").removeAttr('style');
                ($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val()) ? $("#local_F_ENABLED").attr('style', 'color:red') : $("#local_F_ENABLED").removeAttr('style');
                ($("#under_F_HIS_DATA_SAVE_CYCLE").val() != $("#local_F_HIS_DATA_SAVE_CYCLE").val()) ? $("#local_F_HIS_DATA_SAVE_CYCLE").attr('style', 'color:red') : $("#local_F_HIS_DATA_SAVE_CYCLE").removeAttr('style');
                ($("#under_F_UPLOAD_CYCLE").val() != $("#local_F_UPLOAD_CYCLE").val()) ? $("#local_F_UPLOAD_CYCLE").attr('style', 'color:red') : $("#local_F_UPLOAD_CYCLE").removeAttr('style');
                ($("#under_F_DESCRIPTION").val() != $("#local_F_DESCRIPTION").val()) ? $("#local_F_DESCRIPTION").attr('style', 'color:red') : $("#local_F_DESCRIPTION").removeAttr('style');
                ($("#under_F_COLL_CYCLE").val() != $("#local_F_COLL_CYCLE").val()) ? $("#local_F_COLL_CYCLE").attr('style', 'color:red') : $("#local_F_COLL_CYCLE").removeAttr('style');
                ($("#under_F_IP_ADDR").val() != $("#local_F_IP_ADDR").val()) ? $("#local_F_IP_ADDR").attr('style', 'color:red') : $("#local_F_IP_ADDR").removeAttr('style');
                ($("#under_F_GATEWAY").val() != $("#local_F_GATEWAY").val()) ? $("#local_F_GATEWAY").attr('style', 'color:red') : $("#local_F_GATEWAY").removeAttr('style');
                ($("#under_F_IP_MASTER").val() != $("#local_F_IP_MASTER").val()) ? $("#local_F_IP_MASTER").attr('style', 'color:red') : $("#local_F_IP_MASTER").removeAttr('style');
                ($("#under_F_PORT_MASTER").val() != $("#local_F_PORT_MASTER").val()) ? $("#local_F_PORT_MASTER").attr('style', 'color:red') : $("#local_F_PORT_MASTER").removeAttr('style');

            },
            showCollectorTime: function (data)
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

                $('#f_collector_time').val(time);
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
                    $("#f_onlinestate").val("0");
                    $("#f_onlinestate").prop("checked", false);
                    $("#collector_onlinestate").text("离线");
                    $("#collector_onlinestate").attr("style", "color: #ff0000");
                    
                }else if (state === 1)
                {
                    $("#f_onlinestate").val("1");
                    $("#f_onlinestate").prop("checked", true);
                    $("#collector_onlinestate").text("在线");
                    $("#collector_onlinestate").attr("style", "color: #00ff2d");
                }
                
            }

        }

    })(jQuery, window, document);
    basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.pageInit();
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->