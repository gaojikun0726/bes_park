<!-- 计划任务树模块 -->
<div class="leftarea information_left" id="leftscheduleconf" style="width: 22.4%;">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择计划任务>>>
		</span>
	</div>
	<div id="tree_schedule" class="Information_area" ></div>
</div>
<!-- 计划任务树模块end -->
<!-- 信息表格模块 -->
<div class="information_right partLittle" style="width:77.5%;">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;计划任务列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addschedule" href="javascript:void(-1);" onclick="sysmanage_scheduletask_scheduleConfig.show_addscheduleTask();"
			class="btn btn-primary toLeft"> 增加 <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="schedule_keywords" name="schedule_keywords" placeholder="计划名称、编号">
				  <button id="searchScheduleTask" onclick="sysmanage_scheduletask_scheduleConfig.searchScheduleTask()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div class="ibox" id="schedule_ibox" style="height:85.1%">
         		<#include "/view/sysmanage/scheduletask/ScheduleConfig_page.ftl"/>
		</div>
	</div>
</div>
<!-- 信息表格模块end -->


<!---添加计划类型开始----->
<div class="modal fade" id="modal-form-addschedule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加计划类型</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addscheduleform" name="addscheduleform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">计划类型名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fName" name="fName" placeholder="请输入计划类型名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <#--<button class="col-sm-6 col-sm-push-4 display_flex" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>-->
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!---添加计划任务详情开始----->
<div class="modal fade" id="modal-form-addscheduleTask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加计划</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addscheduleTaskform" name="addscheduleTaskform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">计划名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fSysName" name="fSysName" placeholder="请输入计划名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">别名<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fNickName" name="fNickName" placeholder="请输入别名"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input  id="fStartDate" type="text"  name="start"
                            onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                            class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input  id="fEndDate" type="text"  name="end"
                             onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                            class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使能<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="f_enabled_yes" checked="checked" name="f_enabled" value="1"/>是
						    <input type="radio" id="f_enabled_no" name="f_enabled" value="0"/>否
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">替代日<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="f_isholidays_yes"  name="f_isholidays" value="1"/>是
						    <input type="radio" id="f_isholidays_no" checked="checked" name="f_isholidays" value="0"/>否
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执行方式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="f_isspan_yes" checked="checked" name="f_isspan" value="1"/>每天执行
						    <input type="radio" id="f_isspan_no" name="f_isspan" value="0"/>持续执行
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执行频率<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="checkbox" id="f_weekmask_1" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周一
						    <input type="checkbox" id="f_weekmask_2" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周二
						    <input type="checkbox" id="f_weekmask_3" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周三
						    <input type="checkbox" id="f_weekmask_4" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周四
						    <input type="checkbox" id="f_weekmask_5" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周五
						    <input type="checkbox" id="f_weekmask_6" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周六
						    <input type="checkbox" id="f_weekmask_7" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周日
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">场景<span class="text-danger">*</span></label>
                        <input type="hidden" id="hidden_fZoneId">
                        <div id="fZoneId" class="display_flex" style="margin-left: 28%;"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">模式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="fZoneModeId" name="fZoneModeId" class="form-control">

                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!---添加计划任务详情结束----->

<!---编辑计划任务详情开始----->
<div class="modal fade" id="modal-form-editscheduleTask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;编辑计划</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="editscheduleTaskform" name="editscheduleTaskform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">计划名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fSysName" name="edit_fSysName" readonly  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">别名<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fNickName" name="edit_fNickName" placeholder="请输入别名"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input  id="edit_fStartDate" type="text"  name="edit_start"
                            onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                            class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input  id="edit_fEndDate" type="text"  name="edit_end"
                             onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                            class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使能<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="edit_f_enabled_yes" name="edit_f_enabled" value="1"/>是
						    <input type="radio" id="edit_f_enabled_no" name="edit_f_enabled" value="0"/>否
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">替代日<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="edit_f_isholidays_yes" name="edit_f_isholidays" value="1"/>是
						    <input type="radio" id="edit_f_isholidays_no" name="edit_f_isholidays" value="0"/>否
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执行方式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="radio" id="edit_f_isspan_yes" name="edit_f_isspan" value="1"/>每天执行
						    <input type="radio" id="edit_f_isspan_no" name="edit_f_isspan" value="0"/>持续执行
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执行频率<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="checkbox" id="edit_f_weekmask_1" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周一
						    <input type="checkbox" id="edit_f_weekmask_2" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周二
						    <input type="checkbox" id="edit_f_weekmask_3" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周三
						    <input type="checkbox" id="edit_f_weekmask_4" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周四
						    <input type="checkbox" id="edit_f_weekmask_5" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周五
						    <input type="checkbox" id="edit_f_weekmask_6" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周六
						    <input type="checkbox" id="edit_f_weekmask_7" value="0" onchange="sysmanage_scheduletask_scheduleConfig.changeVal(this)"/>周日
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">场景<span class="text-danger">*</span></label>
                        <input type="hidden" id="edit_hidden_fZoneId">
                    	<div id="edit_fZoneId" class="display_flex" style="margin-left: 28%;"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">模式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fZoneModeId" name="edit_fZoneModeId" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!----计划详情信息对比--->
<div class="modal fade" id="comparescheduleTask" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机计划详情信息比对</h4>
            </div>
            <div class="modal-body" style="height:550px;margin-button:10px;">
            	<div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机</span></button></div>
            	<!----上位机信息开始--->
            	<div class="notIncludeCss" style="width:400px;height:450px;overflow-y:auto;overflow-x: hidden;">
				<form id="local_scheduleTask" name="local_scheduleTask" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ID" name="local_F_ID" class="form-control" readonly="readonly">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ENABLED" name="local_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">替代日<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ISHOLIDAYS" name="local_F_ISHOLIDAYS" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">执行方式<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ISSPAN" name="local_F_ISSPAN" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">执行频率<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_WEEKMASK" name="local_F_WEEKMASK" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">开始时间<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_START_DATE" name="local_F_START_DATE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">结束时间<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_END_DATE" name="local_F_END_DATE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">场景ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ZONEID" name="local_F_ZONEID" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">场景类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ZONE_TYPE" name="local_F_ZONE_TYPE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">模式ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="local_F_ZONEMODE_ID" name="local_F_ZONEMODE_ID" class="form-control" readonly="readonly">
					</div>
				</div>
				</form>
            	</div>
            	<!----上位机信息结束--->

            	<!----下位机信息开始--->
            	<div class="includeCss" style="width:400px;height:450px;margin:5px 0 0 36px; overflow-y:auto;overflow-x: hidden;">
				<form id="under_AIInfo" name="under_AIInfo" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_zbh">ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ID" name="under_F_ID" class="form-control" readonly="readonly">
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
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">使能状态<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ENABLED" name="under_F_ENABLED" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">替代日<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ISHOLIDAYS" name="under_F_ISHOLIDAYS" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">执行方式<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ISSPAN" name="under_F_ISSPAN" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">执行频率<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_WEEKMASK" name="under_F_WEEKMASK" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">开始时间<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_START_DATE" name="under_F_START_DATE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">结束时间<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_END_DATE" name="under_F_END_DATE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_f_Location">场景ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ZONEID" name="under_F_ZONEID" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">场景类型<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ZONE_TYPE" name="under_F_ZONE_TYPE" class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" style="width:40%" for="edit_fRatedPower">模式ID<span class="text-comparecoll">:</span></label>
					<div class="col-sm-8" style="width:50%">
						<input type="text" id="under_F_ZONEMODE_ID" name="under_F_ZONEMODE_ID" class="form-control" readonly="readonly">
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

<script type="text/javascript">
;
var  sysmanage_scheduletask_scheduleConfig = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var selectedNode;
	var zoneid = "";
	//添加计划验证
	var addscheduleValidator =$("#addscheduleform").validate({
		rules : {
			fName : {
				required : true,
				rangelength : [ 1, 8 ],
			}
		},
		messages : {
			fName : {
				required : "请输入计划类型名称",
				rangelength : jQuery.validator.format("请输入1-8位"),
			}
		},
		submitHandler : function(form) {
			addschedule();
		}
	});
	//添加计划详情验证
	var addscheduleTaskValidator =$("#addscheduleTaskform").validate({
		rules : {
			fSysName : {
				required : true,
				rangelength : [ 1, 8 ],
			}
		},
		messages : {
			fSysName : {
				required : "请输入计划名称",
				rangelength : jQuery.validator.format("请输入1-8位"),
			}
		},
		submitHandler : function(form) {
			addscheduleTask(form);
		}
	});
    //表单验证
    $("#editscheduleTaskform").validate({
      submitHandler: function(form) {
    	  editscheduleTask(form);
      }
    });
	//居中显示-添加计划类型
 	$('#modal-form-addschedule').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
    	$(this).css('display', 'block');
   	 	var modalHeight=$(window).height() / 2 - $('#modal-form-addschedule .modal-dialog').height() / 2;
    	$(this).find('.modal-dialog').css({
           'margin-top': modalHeight
    	});
    })
    //关闭模态框清空表单值-添加计划类型
    $("#modal-form-addschedule").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        addscheduleValidator.resetForm();
    });
	//居中显示（计划详情信息对比）
 	$('#comparescheduleTask').on('show.bs.modal', function (event) {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#comparescheduleTask .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });

	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	    fill_localscheduleTask(id);
	    fill_underscheduleTask(id);
	})
	//填充上位机计划详情信息
	function fill_localscheduleTask(id){
		$.ajax({
			url : _ctx + "/view/sysmanage/get_scheduleTask",
			type : "post",
			data : {
				"fSysName" : id
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
					$("#local_F_ID").val(result.data.fId);
					$("#local_F_SYS_NAME").val(result.data.fSysName);
					$("#local_F_NICK_NAME").val(result.data.fNickName);
					$("#local_F_ENABLED").val(result.data.fEndDate);
					$("#local_F_ISHOLIDAYS").val(result.data.fIsholidays);
					$("#local_F_ISSPAN").val(result.data.fIsspan);
					$("#local_F_WEEKMASK").val(result.data.fWeekmask);
// 					(result.data.fEnabled == "1" ) ? $("#local_F_ENABLED").val("是") : $("#local_F_ENABLED").val("否");
					$("#local_F_START_DATE").val(result.data.fStartDate);
					$("#local_F_END_DATE").val(result.data.fEndDate);
					$("#local_F_ZONEID").val(result.data.fZoneid);
					$("#local_F_ZONE_TYPE").val(result.data.fZoneType);
					$("#local_F_ZONEMODE_ID").val(result.data.fZonemodeId);
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
	//填充下位机逻辑点信息
	function fill_underscheduleTask(id){
		$.ajax({
			url : _ctx + "/view/sysmanage/get_underscheduleTask",
			type : "post",
			data : {
				fSysName : id
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
					$("#under_F_ID").val(result.data.ID);
					$("#under_F_SYS_NAME").val(result.data.Name);
					$("#under_F_NICK_NAME").val(result.data.Alias);
					$("#under_F_ENABLED").val(result.data.Active);//使能
					$("#under_F_ISHOLIDAYS").val(result.data.PointType);
					$("#under_F_ISSPAN").val(result.data.ModuleID);//所属模块id
					$("#under_F_WEEKMASK").val(result.data.ChannelIndex);
					$("#under_F_START_DATE").val(result.data.WorkMode);
					$("#under_F_END_DATE").val(result.data.Polarity);
					$("#under_F_ZONEID").val(result.data.InitValue);
					$("#under_F_ZONE_TYPE").val(result.data.AlarmActive);//报警是否启用
					$("#under_F_ZONEMODE_ID").val(result.data.AlarmType);//报警类型


					//判断上位机和下位机信息是否相同，不同的在上位机信息栏标红
					($("#under_F_ID").val() != $("#local_ID").val() ) ? $("#local_ID").attr('style','color:red') : $("#local_ID").removeAttr('style');
					($("#under_F_SYS_NAME").val() != $("#local_F_SYS_NAME").val() ) ? $("#local_F_SYS_NAME").attr('style','color:red') : $("#local_F_SYS_NAME").removeAttr('style');
					($("#under_F_NICK_NAME").val() != $("#local_F_NICK_NAME").val() ) ? $("#local_F_NICK_NAME").attr('style','color:red') : $("#local_F_NICK_NAME").removeAttr('style');
					($("#under_F_ENABLED").val() != $("#local_F_ENABLED").val() ) ? $("#local_F_ENABLED").attr('style','color:red') : $("#local_F_ENABLED").removeAttr('style');
					($("#under_F_ISHOLIDAYS").val() != $("#local_F_ISHOLIDAYS").val() ) ? $("#local_F_ISHOLIDAYS").attr('style','color:red') : $("#local_F_ISHOLIDAYS").removeAttr('style');
					($("#under_F_ISSPAN").val() != $("#local_F_ISSPAN").val() ) ? $("#local_F_ISSPAN").attr('style','color:red') : $("#local_F_ISSPAN").removeAttr('style');

					($("#under_F_WEEKMASK").val() != $("#local_F_WEEKMASK").val() ) ? $("#local_F_WEEKMASK").attr('style','color:red') : $("#local_F_WEEKMASK").removeAttr('style');
					($("#under_F_START_DATE").val() != $("#local_F_START_DATE").val() ) ? $("#local_F_START_DATE").attr('style','color:red') : $("#local_F_START_DATE").removeAttr('style');
					($("#under_F_END_DATE").val() != $("#local_F_END_DATE").val() ) ? $("#local_F_END_DATE").attr('style','color:red') : $("#local_F_END_DATE").removeAttr('style');
					($("#under_F_ZONEID").val() != $("#local_F_ZONEID").val() ) ? $("#local_F_ZONEID").attr('style','color:red') : $("#local_F_ZONEID").removeAttr('style');
					($("#under_F_ZONE_TYPE").val() != $("#local_F_ZONE_TYPE").val() ) ? $("#local_F_ZONE_TYPE").attr('style','color:red') : $("#local_F_ZONE_TYPE").removeAttr('style');
					($("#under_F_ZONEMODE_ID").val() != $("#local_F_ZONEMODE_ID").val() ) ? $("#local_F_ZONEMODE_ID").attr('style','color:red') : $("#local_F_ZONEMODE_ID").removeAttr('style');

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
	//居中显示-添加计划详情
 	$('#modal-form-addscheduleTask').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
    	$(this).css('display', 'block');
   	 	var modalHeight=$(window).height() / 2 - $('#modal-form-addscheduleTask .modal-dialog').height() / 2;
    	$(this).find('.modal-dialog').css({
           'margin-top': modalHeight
    	});
    })
    //关闭模态框清空表单值-添加计划详情
    $("#modal-form-addscheduleTask").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#fZoneModeId").empty();
        $("#f_enabled_yes").prop("checked",true);
        $("#f_isholidays_no").prop("checked",true);
        $("#f_isspan_yes").prop("checked",true);
        $("[type='checkbox']").removeAttr("checked");
        addscheduleTaskValidator.resetForm();
    });
    //场景帮助框 (添加模态框)
    $("#fZoneId").ISSPHelpComboBox({
    			title:'请选择场景',
    			inputWidth:'18vw',
    			inputHeight:'3.5vh',
    			inputEditable:true,
    			inputPromMsg:'请选择场景',
    			getDataFun: getZoneData,//请求场景数据需要执行的方法
    			isMultistage:true,
    			searchDisCxt:'请选择场景',
    			callBacks: search_zoneid,  //自定义选择弹框元素后执行的事件
    		});
 	//加载场景树
	function getZoneData(){
		$.ajax({
			type: "post",
			url: _ctx + "/view/basedatamanage/eqmanage/cjpz_tree",
			dataType: "json",
			success: function (result) {
				if (result.status == '1') {
					zonenode = $("#fZoneId").setComboBoxData({
						data:result.list,
					});
				}
			},
			error: function (result) {
				swal(result.msg,"", "error");
			},
		});
	}
	function search_zoneid(node){
		   var saveMap = new Map();
			 if(node == ""){
					swal({
			            title: "请先选择场景  ",
			            text: "经检测，您还未选择场景!",
			            type: "warning",
			            showCancelButton: false,
			            confirmButtonColor: "#1ab394",
			            confirmButtonText: "关闭",
			            closeOnConfirm: false,
			        });
			}else{
				    var nodesStr ;
				    var	nodeList = zonenode.data('treeview').getChildsArrayByNode(node[0]);
					if(nodeList.length>0){
						for (var i = 0; i < nodeList.length; i++) {
							var non = nodeList[i].substring(2);
							nodeList[i] = "part_"+non;
						}
						nodesStr = nodeList.join(",");
					}else{
						nodesStr=  node[0].id;
					}
					  zoneid = nodesStr;//场景赋值
					  loadZoneMode(zoneid);//加载模式下拉框
				}
	   }
    //场景帮助框 (编辑模态框)
    $("#edit_fZoneId").ISSPHelpComboBox({
    			title:'请选择场景',
    			inputWidth:'18vw',
    			inputHeight:'3.5vh',
    			inputEditable:true,
    			inputPromMsg:'请选择场景',
    			getDataFun: getZoneData_edit,//请求场景数据需要执行的方法
    			isMultistage:true,
    			searchDisCxt:'请选择场景',
    			callBacks: search_zoneid,  //自定义选择弹框元素后执行的事件
    		});
 	//加载场景树
	function getZoneData_edit(){
		$.ajax({
			type: "post",
			url: _ctx + "/view/basedatamanage/eqmanage/cjpz_tree",
			dataType: "json",
			success: function (result) {
				if (result.status == '1') {
					zonenode = $("#edit_fZoneId").setComboBoxData({
						data:result.list,
					});
				}
			},
			error: function (result) {
				swal(result.msg,"", "error");
			},
		});
	}
	//加载场景模式下拉框
	function loadZoneMode(zoneid){
		$.ajax({
			url: _ctx + "/view/sysmanage/loadZoneMode",
			type: "post",
 	        data:{
 	 			"fZoneid":zoneid
 	 		},
			success: function (result) {

				var ops="<option value='00'>选择模式</option>";
		 	    if(result.hasOwnProperty("list")){
		 	    	for(var i=0;i<result.list.length;i++){
					 	var obj=result.list[i];
					 	ops+='<option value="'+obj.modeID+'">';
					 	ops+=obj.modeval;
					 	ops+='</option>';
				 	};
		 	    }
		 	   $("#fZoneModeId").empty().append(ops);
			},
			error: function (result) {
				swal(result.msg,"", "error");
			}
		});
	}
	//加载场景模式下拉框(编辑模态框)
	function loadZoneMode_edit(zoneid,zonetype){
		$.ajax({
			url: _ctx + "/view/sysmanage/loadZoneMode",
			type: "post",
 	        data:{
 	 			"fZoneid":zoneid
 	 		},
			success: function (result) {
				var ops="<option value='00'>选择模式</option>";
		 	    if(result.hasOwnProperty("list")){
		 	    	for(var i=0;i<result.list.length;i++){
					 	var obj=result.list[i];
					 	ops+='<option value="'+obj.modeID+'">';
					 	ops+=obj.modeval;
					 	ops+='</option>';
				 	};
		 	    }
		 	   $("#edit_fZoneModeId").append(ops);
			},
			error: function (result) {
				swal(result.msg,"", "error");
			}
		});
	}
    //关闭模态框清空表单值-添加计划详情
    // $("#modal-form-addscheduleTask").on('hidden.bs.modal', function (event) {
    //     $(this).find("input").val("");
    //     $("#fName").empty();
    //     addscheduleTaskValidator.resetForm();
    // });
	//居中显示-编辑计划详情
 	$('#modal-form-editscheduleTask').on('show.bs.modal', function (event) {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
    	$(this).css('display', 'block');
   	 	var modalHeight=$(window).height() / 2 - $('#modal-form-editscheduleTask .modal-dialog').height() / 2;
    	$(this).find('.modal-dialog').css({
           'margin-top': modalHeight
    	});

    	var button = $(event.relatedTarget);
        var fSysName = button.data("id");//获取用户组编号
        $.ajax({
    	       url: _ctx + "/view/sysmanage/get_scheduleTask",
    	       type: "post",
    	       data:{
    	 			"fSysName":fSysName
    	 		},
    	       success: function(result) {
    	    	 loadZoneMode_edit(result.data.fZoneid);
    	         $("#edit_fSysName").val(result.data.fSysName);
    	         $("#edit_fNickName").val(result.data.fNickName);
    	         $("#edit_fStartDate").val(result.data.fStartDate);
         	     $("#edit_fEndDate").val(result.data.fEndDate);
         	     (result.data.fEnabled == "1" ) ? $("#edit_f_enabled_yes").prop("checked",true) : $("#edit_f_enabled_no").prop("checked",true);
         	     (result.data.fIsholidays == "1" ) ? $("#edit_f_isholidays_yes").prop("checked",true) : $("#edit_f_isholidays_no").prop("checked",true);
         	     (result.data.fIsspan== "1" ) ? $("#edit_f_isspan_yes").prop("checked",true) : $("#edit_f_isspan_no").prop("checked",true);
         	     (result.data.fWeekmask.substring(0,1) == "1" ) ? $("#edit_f_weekmask_1").prop("checked",true) : $("#edit_f_weekmask_1").prop("checked",false);
         	     (result.data.fWeekmask.substring(1,2) == "1" ) ? $("#edit_f_weekmask_2").prop("checked",true) : $("#edit_f_weekmask_2").prop("checked",false);
         	     (result.data.fWeekmask.substring(2,3) == "1" ) ? $("#edit_f_weekmask_3").prop("checked",true) : $("#edit_f_weekmask_3").prop("checked",false);
         	     (result.data.fWeekmask.substring(3,4) == "1" ) ? $("#edit_f_weekmask_4").prop("checked",true) : $("#edit_f_weekmask_4").prop("checked",false);
         	     (result.data.fWeekmask.substring(4,5) == "1" ) ? $("#edit_f_weekmask_5").prop("checked",true) : $("#edit_f_weekmask_5").prop("checked",false);
         	     (result.data.fWeekmask.substring(5,6) == "1" ) ? $("#edit_f_weekmask_6").prop("checked",true) : $("#edit_f_weekmask_6").prop("checked",false);
         	     (result.data.fWeekmask.substring(6,7) == "1" ) ? $("#edit_f_weekmask_7").prop("checked",true) : $("#edit_f_weekmask_7").prop("checked",false);
         	     $("#edit_fZoneId").val(result.data.fZoneid);
  	         	 $("#edit_fZoneModeId").val(result.data.fZonemodeId);

    	         }
        });
    })
    //删除数据
    $(document).on('click','#scheduleTable button.delete', function () {
        var id=$(this).data("id");
		swal(
				{
				title : "您确定要删除吗?",
				text : "信息删除后将不可恢复!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#1ab394",
				confirmButtonText : "确定",
				closeOnConfirm : false
				},
			function() {
				setTimeout(function() {
				$.ajax({
					url : _ctx + "/view/sysmanage/del_scheduletask",
					type : "post",
					data : {
						"fSysName" : id
					},
			        beforeSend: function () {
			        	showLoad();
			            },
					success : function(data) {
						if (data.status == '1') {
							swal({
					        	title : data.msg,// 展示的标题
					   			text : "",// 内容
					   			type: "success",
					   			showCloseButton : false, // 展示关闭按钮
					   			allowOutsideClick : false,
					   			showConfirmButton : false,
					   			timer: 1000
					   		});
						// window.location.href="${ctx }/view/user/user";
						//重新加载列表及分页
			            sysmanage_scheduletask_scheduleConfig.searchScheduleTask();
					} else {
						swal("删除失败!",data.msg,"error");
					}
					},
		            complete: function () {
		            	hiddenLoad();
		            },
						error : function(data) {
						swal("删除失败!",data.msg,"error");
					}
					});
					}, 100)
					});

	});
	//数据同步
 	$(document).on('click','#scheduleTable button.syn', function () {
 		var id=$(this).data("id");
		$.ajax({
			url : _ctx + "/view/sysmanage/syn_scheduletask",
			type : "post",
			data : {
				"fSysName" : id
			},
	        beforeSend: function () {
	        	showLoad();
	            },
			success : function(data) {
				if (data.status == '1') {
					swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});

			} else {
				swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "error",
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
				error : function(data) {
				swal("同步失败!",data.msg,"error");
			}
			});
 	});
	//选中节点后为设备树节点添加菜单
	function addFucMenu(selectedNode) {
		if(selectedNode.nodeType == "0"){
			var oSpan = "<span id='schedulespanMenu' class='schedulespanMenu'>"
			       	  + "<button class='btn sbtreeNodeBtn' value='新建类型' nodetype='0' data-toggle='modal' href='#modal-form-addschedule'>新建类型</button></span>";
		}else if(selectedNode.nodeType == "1"){
			var oSpan = "<span id='schedulespanMenu' class='schedulespanMenu'>"
		       	  + "<button class='btn sbtreeNodeBtn' value='新建类型' nodetype='1' data-toggle='modal' href='#modal-form-addschedule'>新建类型</button>"
			      + "<button class='btn sbtreeNodeBtn' value='删除'  onclick='sysmanage_scheduletask_scheduleConfig.delschedule()'>删除</button></span>";
		}
		if ($(".schedulespanMenu").length > 0){
			$(".schedulespanMenu").empty();//新建一个菜单前将上次创建的菜单移除
		}
		$(".node-selected").append(oSpan);//根据所选节点添加按钮
	}
    //新增计划类型
	function addschedule() {
	     $.ajax({
	       url: _ctx + "/view/sysmanage/add_schedule",
	       type: "post",
	       data:{
	    	   fName : $("#fName").val(),
	    	   fParentid : selectedNode.id,
	     	   fType : selectedNode.nodeType,
	       },
	       success: function(data) {
	    	   if (data.status == '1') {
	        	 swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
	        	 $('#modal-form-addschedule').modal('hide');//关闭编辑窗口
	        	 //重新加载树
	        	 sysmanage_scheduletask_scheduleConfig.loadtree_schedule();
	        } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
 	}
    //新增计划详情
    function addscheduleTask(form){

    	var fWeekmask = $("#f_weekmask_7").val() + $("#f_weekmask_6").val() + $("#f_weekmask_5").val() + $("#f_weekmask_4").val() +
                $("#f_weekmask_3").val() + $("#f_weekmask_2").val() + $("#f_weekmask_1").val() ;

    	// alert(fWeekmask)
		// return false;
    	$.ajax({
		       url: _ctx + "/view/sysmanage/add_scheduleTask",
		       type: "post",
		       contentType: "application/json; charset=utf-8",
		       data:JSON.stringify({
		    	   fSysName :$("#fSysName").val(),
		    	   fNickName :$("#fNickName").val(),
		    	   fStartDate : $("#fStartDate").val(),
		    	   fEndDate : $("#fEndDate").val(),
		    	   fEnabled :$('input[name="f_enabled"]:checked').val() ,//使能状态
		    	   fIsholidays : $('input[name="f_isholidays"]:checked').val() ,//是否假期
		    	   fWeekmask : fWeekmask,//周掩码
		    	   fIsspan : $('input[name="f_isspan"]:checked').val(),//是否跨天
		    	   fZoneid : zoneid,
		    	   fZonemodeId : $("#fZoneModeId").val(),
		    	   fScheduleinfoId : selectedNode.id,
		 		}),
		       success: function(data) {
		    	   if (data.status == '1') {
		        	 swal({
				        	title : data.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
		        	 $('#modal-form-addscheduleTask').modal('hide');//关闭编辑窗口
		        	//在表格中添加数据
			         $('#scheduleTable').tabulator("addRow", data.data);
		        } else{
		           swal( data.msg, "", "error");
		           $('#modal-form-addscheduleTask').modal('hide');//关闭编辑窗口
		        	//在表格中添加数据
			       $('#scheduleTable').tabulator("addRow", data.data);
		         }
		       },
		       error: function(data) {
		       	 swal( data.msg,"", "error");
		       }
		     });
    }
    function deleteschedule(selectedNode){
	    swal({
	            title: "您确定要删除吗?",
	            text: "信息删除后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function () {
            	setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/sysmanage/del_schedule",
			          type: "post",
			          data:{
			        	  "fId":selectedNode.id
			    	  },
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		swal({
					        	title : data.msg,// 展示的标题
					   			text : "",// 内容
					   			type: "success",
					   			showCloseButton : false, // 展示关闭按钮
					   			allowOutsideClick : false,
					   			showConfirmButton : false,
					   			timer: 1000
					   		});
		              		$("#tree_schedule").treeview("removeNode", selectedNode, { silent: true } );
			            } else{
			                swal(data.msg,"", "error");
			            }
			          },
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
                },100
            )
        });
        }
    //更新计划详情
    function editscheduleTask(){
	     $.ajax({
		       url: _ctx + "/view/sysmanage/editscheduleTask",
		       type: "post",
		       contentType: "application/json; charset=utf-8",
		       data:JSON.stringify({
		    	   fSysName :$("#edit_fSysName").val(),
		    	   fNickName :$("#edit_fNickName").val(),
		    	   fStartDate : $("#edit_fStartDate").val(),
		    	   fEndDate : $("#edit_fEndDate").val(),
		    	   fEnabled :$('input[name="edit_f_enabled"]:checked').val() ,//使能状态
		    	   fIsholidays : $('input[name="edit_f_isholidays"]:checked').val() ,
		    	   fZoneid : $("#edit_fZoneId").val(),
		    	   fZonemodeId : $("#edit_fZoneModeId").val(),
		    	   fScheduleinfoId : selectedNode.id,
		 		}),
		       success: function(data) {
		    	   if (data.status == '1') {
		        	 swal({
				        	title : data.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
		        	 $('#modal-form-editscheduleTask').modal('hide');//关闭编辑窗口
		        	//在表格中添加数据
			         $('#scheduleTable').tabulator("updateRow",sysmanage_scheduletask_scheduleConfig_page.get_curRow(), data.data);
		        } else{
		           swal( data.msg, "", "error");
		         }
		       },
		       error: function(data) {
		       	 swal( data.msg,"", "error");
		       }
		     });
    }
	return {
		delschedule : function(){
			var id=selectedNode.id;
		    if(selectedNode.nodes != null && selectedNode.nodes.length>0){
							swal({
					            title: "请您先删除用户组下的子组！",
					            text: "经检测，您要删除的用户组包含子组!",
					            type: "warning",
					            showCancelButton: false,
					            confirmButtonColor: "#1ab394",
					            confirmButtonText: "关闭",
					            closeOnConfirm: false
					        });
			}else{
			    deleteschedule(selectedNode);
		    }

		},
		//加载计划类型树
		loadtree_schedule : function(){
			$.ajax({
				type : "post",
				url : _ctx + "/view/sysmanage/getSchedule",
				dataType : "json",
				beforeSend : function() {
					showLoad();
				},
				success : function(result) {
					if(result.status =='1') {
						$('#tree_schedule').treeview({
							data : result.list, // 数据源
							highlightSelected : true, //是否高亮选中
							levels : 4,
							enableLinks : true,//必须在节点属性给出href属性
							color : "#4a4747",
							onNodeSelected : function(event,nodeData) {//节点选中事件
								nodeIndex = nodeData.index;//选中节点时，获取该节点的索引值
								pid = nodeData.pid;
								selectedNode = nodeData;//当前选中节点的属性
								nodeLevel = nodeData.level;//当前选中节点在树上的级数
								// addFucMenu(selectedNode);
								sysmanage_scheduletask_scheduleConfig.searchScheduleTask();
							}
						});
						 if(result.hasOwnProperty("list")&&result.list.length>0)
	                        {
	                            var firstNode = $("#tree_schedule").treeview('findNodes',[result.list[0].nodes[0].id,'id']);
	                            $("#tree_schedule").treeview("selectNode", firstNode);

	                        }
						sysmanage_scheduletask_scheduleConfig.searchScheduleTask();
					}
				},
				complete : function() {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
		},
 		//验证增加模态框是否弹出
 		show_addscheduleTask : function () {
 			if(selectedNode != null && selectedNode.level == 1){
 				swal({
 	        		title: "无法在根目录新增计划",
 	        		text: "请先选择计划类型!",
 	        		type: "warning",
 	        		showCancelButton: false,
 	        		confirmButtonColor: "#1ab394",
 	        		confirmButtonText: "关闭",
 	        		closeOnConfirm: false
 	    		});
 			}else if(selectedNode == null){
 				swal({
 	        		title: "请先选择计划类型！",
 	        		text: "不可选择根目录!",
 	        		type: "warning",
 	        		showCancelButton: false,
 	        		confirmButtonColor: "#1ab394",
 	        		confirmButtonText: "关闭",
 	        		closeOnConfirm: false
 	    		});
 			}else{
 				$('#modal-form-addscheduleTask').modal('show');
 			}
 	   },
 	  searchScheduleTask : function (){
 		 var fId = "";
 		  if(selectedNode != null){
 			fId = selectedNode.id;
 		  }
 	    	$.ajax({
 					url : _ctx + '/view/sysmanage/getScheduleList',
 					type : "post",
 					data : ({
 						"keywords" : $("#schedule_keywords").val(),
 						"fId": fId
 				 		}),
 			        beforeSend: function () {
 			        	showLoad();
 			            },
 					success : function(data) {
 						$('#schedule_ibox').html(data);
 					},
 		            complete: function () {
 		            	hiddenLoad();
 		            },
 					error : function(XMLHttpRequest,textStatus, errorThrown) {
 						toastr.error('', '查询失败');
 					}
 					});
 	    },
 	   //点击复选框时改变value
 	   changeVal : function(event){
 		   var aa= event;
 		   if(event.value == "0"){
 			   $("#"+event.id).val("1");
 		   }else{
 			  $("#"+event.id).val("0");
 		   }
 	   },
		pageInit : function(){
			sysmanage_scheduletask_scheduleConfig.loadtree_schedule();

		}
	};

})(jQuery, window, document);
	sysmanage_scheduletask_scheduleConfig.pageInit();
</script>