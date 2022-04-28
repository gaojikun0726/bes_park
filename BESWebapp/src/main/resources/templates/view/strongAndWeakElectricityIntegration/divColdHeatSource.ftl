<style>
	/* 1280分辨率更改 */
	/* .circle {

        float:left;margin-left:20px;text-align:center;width: 90px;height: 100px;-moz-border-radius: 100px / 50px;-webkit-border-radius: 100px / 50px;border-radius: 100px / 50px;
    } */
	/* 1920分辨率 */
	/* .circle {

        float:left;margin-left:25px;text-align:center;width: 100px;height: 100px;-moz-border-radius: 100px / 50px;-webkit-border-radius: 100px / 50px;border-radius: 100px / 50px;
    }*/
	/*冷热机组*/

	.divColdHeatSourceCanvasFather canvas{
		width: 100% !important;
		height: 100% !important;
	}
</style>
<script src="${ctx}/static/js/integration.js"></script>
<!-- coldHeatSource父类弹出框配置 -->
<div class="layui-container">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickDivLeaderConfigForm">
		<input type="hidden" id="" name="">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">数量配置:</label>
				</div>
				<div class="layui-col-xs4">
					<input type="number" name="commonConfigDivMaxNum" value="" placeholder="div显示最大数量"  lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="savePageDivNumConfigLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>
<!--wanghongjie  左侧数量配置的弹出框-->
<div class="layui-container">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickDivLeaderConfigFormByLeft">
		<input type="hidden" id="" name="">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">数量配置:</label>
				</div>
				<div class="layui-col-xs4">
					<input type="number" name="commonConfigDivMaxNumByLeft" value="" placeholder="div显示最大数量"  lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="savePageDivNumConfigByLeftLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<!-- coldHeatSource弹出配置框 -->
<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickCommonForm">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourceDdcFSysName" name="configColdHeatSourceDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2">
					<label class="layui-form-label">所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourcePointLocationFid" name="configColdHeatSourcePointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-row">
				<div class="layui-col-md2">
					<label class="layui-form-label">描&emsp;&emsp;述：</label>
				</div>
				<div class="layui-col-md3">
					<input type="text"  name="commonConfigDesc" value="" placeholder="描述"   lay-verify="" class="layui-input">
				</div>
				<div class="layui-col-xs2">
					<label class="layui-form-label">公式：</label>
				</div>
				<div class="layui-col-xs3">
					<input type="text"  name="commonConfigformula" value="" placeholder="公式"   lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfigLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<!--wanghongjie coldHeatSource弹出配置框 top -->
<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickCommonFormtop">
		<input type="hidden">
		<div class="layui-form-item">
			<div class="layui-row">
				<div class="layui-col-md2">
					<label class="layui-form-label">描&emsp;&emsp;述：</label>
				</div>
				<div class="layui-col-md3">
					<input type="text"  name="commonConfigDesctop" value="" placeholder="描述"   lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfigtopLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<!-- coldHeatSource弹出配置框 -->
<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickEchartsTwoForm">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前制冷量所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourceDdcFSysName21" name="configColdHeatSourceDdcFSysName21" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前制冷量所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourcePointLocationFid21" name="configColdHeatSourcePointLocationFid21" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">当前制冷量公式：</label>
				</div>
				<div class="layui-col-xs3" style="left: 6%">
					<div style="display: none" class="commonConfigformula21"></div>
					<input type="text"  name="commonConfigformula21" value="" placeholder="公式"   lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前能耗所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourceDdcFSysName22" name="configColdHeatSourceDdcFSysName22" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前能耗所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourcePointLocationFid22" name="configColdHeatSourcePointLocationFid22" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">当前能耗公式：</label>
				</div>
				<div class="layui-col-xs3" style="left: 6%">
					<div style="display: none" class="commonConfigformula22"></div>
					<input type="text"  name="commonConfigformula22" value="" placeholder="公式"   lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveEchartsTwoConfigLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- coldHeatSource弹出配置框 -->
<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickEchartsOneForm">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前COP所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourceDdcFSysName1" name="configColdHeatSourceDdcFSysName1" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">当前COP所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configColdHeatSourcePointLocationFid1" name="configColdHeatSourcePointLocationFid1" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">当前制冷量公式：</label>
				</div>
				<div class="layui-col-xs3" style="left: 6%">
					<div style="display: none" class="commonConfigformula1"></div>
					<input type="text"  name="commonConfigformula1" value="" placeholder="公式"   lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveEchartsOneConfigLrjz">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- coldHeatSource弹出配置框 -->
<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationColdHeatSourceDBClickEchartsFourForm">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2"style="width: 24.666667%;">
					<label class="layui-form-label" style="padding: 9px 5px;">注意：请先分别选择所属DDC后，再分别去选择所属点位信息!</label>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷冻水供水温度所属DDC：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourceDdcFSysName41" name="configColdHeatSourceDdcFSysName41" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷冻水供水温度所属点位：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourcePointLocationFid41" name="configColdHeatSourcePointLocationFid41" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">
						<label class="layui-form-label">冷冻水供水温度公式：</label>
					</div>
					<div class="layui-col-xs3" style="left: 8%" >
						<div style="display: none" class="commonConfigformula41"></div>
						<input type="text"  name="commonConfigformula41" value="" placeholder="公式"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水出口温度所属DDC：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourceDdcFSysName42" name="configColdHeatSourceDdcFSysName42" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水出口温度所属点位：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourcePointLocationFid42" name="configColdHeatSourcePointLocationFid42" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">
						<label class="layui-form-label">冷却水出口温度公式：</label>
					</div>
					<div class="layui-col-xs3" style="left: 8%">
						<div style="display: none" class="commonConfigformula42"></div>
						<input type="text"  name="commonConfigformula42" value="" placeholder="公式"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水供水温度所属DDC：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourceDdcFSysName43" name="configColdHeatSourceDdcFSysName43" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水供水温度所属点位：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourcePointLocationFid43" name="configColdHeatSourcePointLocationFid43" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">
						<label class="layui-form-label">冷却水供水温度公式：</label>
					</div>
					<div class="layui-col-xs3" style="left: 8%">
						<div style="display: none" class="commonConfigformula43"></div>
						<input type="text"  name="commonConfigformula43" value="" placeholder="公式"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水回水温度所属DDC：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourceDdcFSysName44" name="configColdHeatSourceDdcFSysName44" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
					<div class="layui-col-xs2"style="width: 24.666667%;">
						<label class="layui-form-label" style="padding: 9px 5px;">冷却水回水温度所属点位：</label>
					</div>
					<div class="layui-col-xs3">
						<select id="configColdHeatSourcePointLocationFid44" name="configColdHeatSourcePointLocationFid44" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">
						<label class="layui-form-label">冷却水回水温度公式：</label>
					</div>
					<div class="layui-col-xs3" style="left: 8%">
						<div style="display: none" class="commonConfigformula44"></div>
						<input type="text"  name="commonConfigformula44" value="" placeholder="公式"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
					<div class="layui-input-block" style="float:right;">
						<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveEchartsFourConfigLrjz">提交</button>
						<button type="button" class="layui-btn layui-btn-primary" onclick="divColdHeatSource.closeCommonConfigInfos()">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div  class="strongAndWeakElectricityIntegrationCommon" id="divColdHeatSourceDiv">
	<div>
		<button class="orangebtn btn btn-default" id="coldHeatSourceReturnBtn" style="width:60px;  height: 25px; margin-left:40px">返回</button>
	</div>
	<div class="divColdHeatSourceTopConfig">
		<!-- Start by 数据div -->
		<!--wanghongjie 左侧三个可配置的div 修改其class-->
		<div style="width: 15%; float: left; height: 100%; margin-left:20px; " class="divColdHeatSourceTopConfig1">
			<div style="height:30%; display:none;" class="commonChangeColorColdHeatSource1">
				<div class="commonChangeColorLighter integrationDBClickCommonColdHeatSourcetop commonChangeColorColdHeatSource71" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="7"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource8" style="width:50%;height:70%;float: left;text-align: center;" sequence="8">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource81"></div>
					<div style="height:50%;font-size:24px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource82"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource83"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource84"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource9" style="width:50%;height:70%;float: left;text-align: center;"sequence="9">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource91"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource92"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource93"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource94"></div>
				</div>
			</div>
			<div style="height:30%;margin-top:20px;display:none;" class="commonChangeColorColdHeatSource2">
				<div class="commonChangeColorLighter integrationDBClickCommonColdHeatSourcetop commonChangeColorColdHeatSource101" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="10"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource30" style="width:50%;height:70%;float: left;text-align: center;" sequence="30">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource301"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource302"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource303"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource304"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource15" style="width:50%;height:70%;float: left;text-align: center;" sequence="15">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource151"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource152"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource153"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource154"></div>
				</div>
			</div>
			<div style="height:30%;margin-top:20px;display:none;" class="commonChangeColorColdHeatSource3">
				<div class="commonChangeColorLighter integrationDBClickCommonColdHeatSourcetop commonChangeColorColdHeatSource131" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="13"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource16" style="width:50%;height:70%;float: left;text-align: center;" sequence="16">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource161"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource162"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource163"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource164"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonColdHeatSource commonChangeColorColdHeatSource17" style="width:50%;height:70%;float: left;text-align: center;" sequence="17">
					<div style="height:25%;line-height:40px;" class="commonChangeColorColdHeatSource171"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorColdHeatSource172"></div>
					<div style="height:25%" class="commonChangeColorColdHeatSource173"></div>
					<div style="display: none" class="commonChangeColorColdHeatSource174"></div>
				</div>
			</div>
		</div>
		<!-- End by 数据div -->
		<!-- 图片div -->
		<div class="testphoto" style="width: 45%; float: left; height: 100%;background:url(${ctx}/static/images/strongAndWeakElectricityIntegration/zhileng2.png);background-repeat:no-repeat;background-size:100%;background-position: right; ">
			<div class="divColdHeatSourceCanvasFather" style="height:85%;text-align: center;">
				<div style="text-align:center;">
				<!--wanghongjie 增加名称和状态的id-->
					<input type="hidden" id="SBQT_lrjz" value="${id}">
					<input type="hidden" id="electric_meter_number_hidden_lrjz" value="${electric_meter_number}">
					<span style="color:white;font-size: 18px;">名称：${name}</span>
					<span style="color:#4dd807;" id="SBQD_VALUE_lrjz">&nbsp;&nbsp;&nbsp;&nbsp;${currentState}</span>
				</div>
				<!-- Start by 画布画斜线 -->
				<canvas id="mcanvas" style="height:80%;"></canvas>
				<!-- End by 画布画斜线 -->
			</div>
			<#--<div style="height:15%;text-align: center;margin-top:10px;">
				<button id="showMoreData" class="orangebtn btn btn-default" >更多属性</button>
			</div>-->
		</div>
		<!-- End by图片div -->
		<!-- Start by 圆圈div -->
		<div class="integrationDBClickDivLeaderConfig" style="width: 20%; float: left; height: 100%;">
			<div>
				<div class="greendiv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow1" style="display:none;" sequence="1">
					<div style="line-height:38px;" class="coldHeatSourcePageDivShow11"></div>
					<div style="font-size:23px;" class="coldHeatSourcePageDivShow12"></div>
					<div class="coldHeatSourcePageDivShow13"></div>
					<div style="display: none" class="coldHeatSourcePageDivShow14"></div>
				</div>
				<div class="greendiv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow2" style="display:none;" sequence="2">
					<div style="line-height:38px;" class="coldHeatSourcePageDivShow21"></div>
					<div style="font-size:23px;" class="coldHeatSourcePageDivShow22"></div>
					<div class="coldHeatSourcePageDivShow23"></div>
					<div style="display: none" class="coldHeatSourcePageDivShow24"></div>
				</div>
			</div>
			<div>
				<div class="bluediv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow3" style="margin-top:25px; display:none;"sequence="3">
					<div style="line-height:38px;"class="coldHeatSourcePageDivShow31"></div>
					<div style="font-size:23px;"class="coldHeatSourcePageDivShow32"></div>
					<div class="coldHeatSourcePageDivShow33"></div>
					<div style="display: none" class="coldHeatSourcePageDivShow34"></div>
				</div>
				<div class="bluediv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow4" style="margin-top:25px; display:none;"sequence="4">
					<div style="line-height:38px;" class="coldHeatSourcePageDivShow41"></div>
					<div style="font-size:23px;"class="coldHeatSourcePageDivShow42"></div>
					<div class="coldHeatSourcePageDivShow43"></div>
					<div style="display: none" class="coldHeatSourcePageDivShow44"></div>
				</div>
			</div>
			<div>
				<div class="orangediv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow5" style="margin-top:25px; display:none;"sequence="5">
					<div style="line-height:38px;"class="coldHeatSourcePageDivShow51"></div>
					<div style="font-size:23px;"class="coldHeatSourcePageDivShow52"></div>
					<div class="coldHeatSourcePageDivShow53"></div>
					<div style="display: none"  class="coldHeatSourcePageDivShow54"></div>
				</div>
				<div class="orangediv circle integrationDBClickCommonColdHeatSource coldHeatSourcePageDivShow6" style="margin-top:25px; display:none;"sequence="6">
					<div style="line-height:38px;"class="coldHeatSourcePageDivShow61"></div>
					<div style="font-size:23px;"class="coldHeatSourcePageDivShow62"></div>
					<div class="coldHeatSourcePageDivShow63"></div>
					<div style="display: none"  class="coldHeatSourcePageDivShow64"></div>
				</div>
			</div>


		</div>
		<!-- End by 圆圈div -->

		<!-- Start by 基本参数 -->
		<div class="commonChangeColorLighter" style="width: 16%; float: left; height: 100%;margin-left:20px;">
			<table id="divColdHeatSourceParamTable" style="width:100%;margin-left:10px;">
				<caption style="text-align: center;color:white;"><b>电表参数</b></caption>
				<!--wanghongjie 增加基本参数的id -->
                <tr>
                    <td>所属机柜</td><td class="cabinetName" style="width: 110px"></td>
                </tr>
				<tr>
					<td>瞬时能耗</td><td class="instantEnergy"></td><td>kW</td>
				</tr>
				<tr>
					<td>累计能耗</td><td class="totalEnergy"></td><td>kWh</td>
				</tr>
				<tr>
					<td>A相电压</td><td class="aPhaseVoltage"></td><td>V</td>
				</tr>
				<tr>
					<td>B相电压</td><td class="bPhaseVoltage"></td><td>V</td>
				</tr>
				<tr>
					<td>C相电压</td><td class="cPhaseVoltage"></td><td>V</td>
				</tr>
				<tr>
					<td>A相电流</td><td class="aPhaseCurrent"></td><td>A</td>
				</tr>
				<tr>
					<td>B相电流</td><td class="bPhaseCurrent"></td><td>A</td>
				</tr>
				<tr>
					<td>C相电流</td><td class="cPhaseCurrent"></td><td>A</td>
				</tr>
			</table>
		</div>
		<!-- End by 基本参数 -->
	</div>
	<!-- 下方折线图 (能效、当前COP、温度曲线)-->
	<div class="divColdHeatSourceBottomConfig">
		<div class="commonChangeColorDarker integrationDBClickEchartsTwo coldHeatSourcePageDivShow18" style="width: 32%; float: left; height: 95%;margin-left:20px;" sequence="18">
			<div id="energyEfficiencyLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow184"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow185"></div>
		</div>
		<div class="commonChangeColorDarker integrationDBClickEchartsOne coldHeatSourcePageDivShow19" style="width: 32%; float: left; height: 95%;margin-left:10px;" sequence="19">
			<div id="copLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow194"></div>
		</div>
		<div class="commonChangeColorDarker integrationDBClickEchartsFour coldHeatSourcePageDivShow20" style="width: 32%; float: left; height: 95%;margin-left:10px;" sequence="20">
			<div id="temperatureLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow204"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow205"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow206"></div>
			<div style="display: none"  class="coldHeatSourcePageDivShow207"></div>
		</div>
	</div>
	<!-- 隐藏框--更多属性 -->
	<div  class="commonChangeColorLighter" id="showMoreDataHiddenDiv" style="display:none;width:500px;height:100%;text-align:center;">
		<table class="hidentable"style="width:100%; height:100%;">
			<tr>
				<td style="width:20%;height: 5vh;"><b>属性名</b></td><td style="width:15%"><b>属性值</b></td><td style="width:15%"><b>单位</b></td><td style="width:25%"><b>操作</b></td>
			</tr>
			<#--<tr class="hidentabletr1">
                <td style="text-align:left;color:#3d42f3;height: 5vh;" >机组启停</td><td id="lrjzshow1_jzqt_1">0</td><td id="lrjzshow1_jzqt_2">/</td><td>
                    <select id="lrjzshow1_jzqt_3" onchange="integration_coolingTower.setLampVPoint(this)">
                        <option value="255">开</option>
                        <option value="0">关</option>
                    </select>
                </td>
            </tr>-->
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >冷冻水供水温度</td><td id="lrjzshow1_ldsgswd_1">0</td><td id="lrjzshow1_ldsgswd_2">℃</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >冷冻水回水温度</td><td id="lrjzshow1_ldshswd_1">0</td><td id="lrjzshow1_ldshswd_2">℃</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >冷却水供水温度</td><td id="lrjzshow1_lqsgswd_1">0</td><td id="lrjzshow1_lqsgswd_2">℃</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">冷却水回水温度</td><td id="lrjzshow1_lqshswd_1">0</td><td id="lrjzshow1_lqshswd_2">℃</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">当前流量</td><td id="lrjzshow1_dqll_1">0</td><td id="lrjzshow1_dqll_2">m³/h</td><td>/</td>
			</tr>

		</table>
	</div>
</div>
<script type="text/javascript">
	;
	var divColdHeatSource = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var server_Interval=5*1000;//定时器 5秒钟

		var form; //只有执行了这一步，部分表单元素才会自动修饰成功
		var layer;
		var index = 0;//默认弹框index为0
		var ddcSysName;
		var pointLocations=document.getElementById('configColdHeatSourcePointLocationFid');
		var pointLocations1=document.getElementById('configColdHeatSourcePointLocationFid1');
		var pointLocations21=document.getElementById('configColdHeatSourcePointLocationFid21');
		var pointLocations22=document.getElementById('configColdHeatSourcePointLocationFid22');
		var pointLocations41=document.getElementById('configColdHeatSourcePointLocationFid41');
		var pointLocations42=document.getElementById('configColdHeatSourcePointLocationFid42');
		var pointLocations43=document.getElementById('configColdHeatSourcePointLocationFid43');
		var pointLocations44=document.getElementById('configColdHeatSourcePointLocationFid44');

		var f_equipment_id;
		var pageConfigShowNum;//配置的页面显示div数目
		var pageConfigShowNumLeft;//配置的页面显示div数目 左侧
		var pageDivSequence;//所配置展示div的序号
		var originalData={};
		var myChart1; // 能效echarts
		var myChart2; // 当前cop echarts
		var myChart4; // 温度曲线 echarts

		var ammeterConfigParam = {};

		var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		var divColdHeatSourceTopHeight= divHeightSum*0.55;//上半部高度
		var divWidthSum = $(window).width();//网页窗口可见区域总宽度
		$(".divColdHeatSourceTopConfig").css("height",divColdHeatSourceTopHeight);
		$(".divColdHeatSourceBottomConfig").css({"height":divHeightSum*0.3,"margin-top":divHeightSum*0.015});
		$("#divColdHeatSourceParamTable").css({"height":divColdHeatSourceTopHeight*0.8});

		/* 点击左上方返回按钮，返回主页面 */
		$("#coldHeatSourceReturnBtn").click(function() {
			//返回主页面时，将此副页面开启的定时器关闭
			//location.reload();
			$("#coldHeatSourceDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/coldHeatSource/showInitPage');
		});

		/* Start for 点击更多属性按钮展示弹出框 */
		$("#showMoreData").click(function(){
			layer.open({
				type:1,
				title:'属性',
				area:['500px','600px'],
				maxmin:false,//最大最小化
				content:$('#showMoreDataHiddenDiv'),
				//yes:function(index,layero){
				//	layer.close(index);
				//}
			});
		});
		/* End 点击更多属性按钮展示弹出框 */

		/* Start by 画图片上斜线指示 */
		var mcanvas  = document.getElementById("mcanvas");    //获得画布

		var mcontext = mcanvas.getContext("2d");    //获得上下文

		mcanvas.width = 1000;     //重新设置标签的属性宽高
		mcanvas.height = 1000;    //千万不要用 canvas.style.height
		//mcanvas.style.border = "1px solid #f7f7f7";    //设置canvas的边

		//绘制三角形
		/*mcontext.strokeStyle = '#D2D2D2';
		mcontext.beginPath();        //开始路径
		mcontext.moveTo(0,100);    //三角形，左顶点
		//mcontext.lineTo(300, 100);   //右顶点
		mcontext.lineTo(300, 400);   //底部的点
		mcontext.moveTo(0,500);
		mcontext.lineTo(300,500);
		mcontext.moveTo(0,900);
		mcontext.lineTo(300,600);
		mcontext.closePath();        //结束路径
		mcontext.stroke();  */         //描边路径

		/* End by 画图片上斜线指示 */
		/* Start add by yangjx at 20191226 */
		//给所展示的div的父类div设置双击事件，当点击时，可设置所展示的div的数量，最大展示数量为6；

		//判断是否为用户模式,如果是,则设置双击无效
		var sbgl = $("#leftMenu").text();
		if (sbgl.indexOf("设备管理") != -1) {

			$(".integrationDBClickDivLeaderConfig").dblclick(function(e){
				if (e.target.className == "integrationDBClickDivLeaderConfig") {//点击子div时，不弹出父div配置框
					index = layer.open({
						tytle:'配置',
						type:1,
						area:['30vw','25vh'],
						maxmin:true,
						content:$("#integrationColdHeatSourceDBClickDivLeaderConfigForm"),
					});
				}
			});

			<!--wanghongjie 左侧div的配置数量-->
			$(".divColdHeatSourceTopConfig1").dblclick(function(e){
				if (e.target.className == "divColdHeatSourceTopConfig1") {//点击子div时，不弹出父div配置框
					index = layer.open({
						tytle:'配置',
						type:1,
						area:['30vw','25vh'],
						maxmin:true,
						content:$("#integrationColdHeatSourceDBClickDivLeaderConfigFormByLeft"),
					});
				}
			});

		//给div设置双击弹出配置框设置
		$(".integrationDBClickCommonColdHeatSource").dblclick(function(obj){
			var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
			var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationColdHeatSourceDBClickCommonForm"),
			});
			if(configDDCSysName!=null&&configDDCSysName!=""){//有数据，说明已配置，则回显数据，否则，不操作
				var descId = obj.currentTarget.attributes.fdescid.value;//获取回显展示描述DIV的id
				var formula = obj.currentTarget.attributes.formula.value;//获取回显展示公式DIV的id
				var commonConfigDesc = $("#"+descId).html();//取得描述信息
				var commonConfigFormula = $("#"+formula).html();//取得公式信息
				configDDCSysName=configDDCSysName.value;
				configFSysName = configFSysName.value;
				//进行回显:↓
				formLoadData(configDDCSysName,commonConfigDesc,commonConfigFormula);
				pointLocations.options.length=0;//将存储的ddc点位信息清空
				loadPointLocations(configFSysName);
			} else {
				configDDCSysName = "";
				commonConfigDesc = '';
				commonConfigFormula = '';
				formLoadDataNone(configDDCSysName,commonConfigDesc,commonConfigFormula);
				pointLocations.options.length=0;//将存储的ddc点位信息清空
				loadPointLocations(configFSysName);
			}

		});
		//wanghongjie  给div设置双击弹出配置框设置 top
		$(".integrationDBClickCommonColdHeatSourcetop").dblclick(function(obj){
			//var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
			//var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationColdHeatSourceDBClickCommonFormtop"),
			});
			/*if(configDDCSysName!=null&&configDDCSysName!=""){//有数据，说明已配置，则回显数据，否则，不操作
				var descId = obj.currentTarget.attributes.fdescid.value;//获取回显展示描述DIV的id
				var commonConfigDesc = $("#"+descId).html();//取得描述信息
				configDDCSysName=configDDCSysName.value;
				configFSysName = configFSysName.value;
				//进行回显:↓
				formLoadData(configDDCSysName,commonConfigDesc);
				loadPointLocations(configFSysName);
			}*/

		});

		//给echarts图表获取双击事件
		$(".integrationDBClickEchartsTwo").dblclick(function(obj){

			var configDDCSysName21 = obj.currentTarget.attributes.fddcsysname21;//获得所点击div配置相应的ddc系统名称
			var configFSysName21 = obj.currentTarget.attributes.fsysname21;//获得所点击div的配置的系统名称数据
			var configDDCSysName22 = obj.currentTarget.attributes.fddcsysname22;//获得所点击div配置相应的ddc系统名称
			var configFSysName22 = obj.currentTarget.attributes.fsysname22;//获得所点击div的配置的系统名称数据
			var formula21;
			var formula22;

			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationColdHeatSourceDBClickEchartsTwoForm"),
			});
			if(configDDCSysName21!=null&&configDDCSysName21!=""){ //有数据，说明已配置，则回显数据，否则，不操作
				configDDCSysName21=configDDCSysName21.value;
				configFSysName21 = configFSysName21.value;
				if (typeof (obj.currentTarget.attributes.formula21) == "undefined") {
					formula21 = "";
				}else {
					formula21 = obj.currentTarget.attributes.formula21.value;//获取回显展示公式DIV的id
					$(".commonConfigformula21").text(formula21);
				}
				configDDCSysName22=configDDCSysName22.value;
				configFSysName22 = configFSysName22.value;

				if (typeof (obj.currentTarget.attributes.formula22) == "undefined") {
					formula22 = "";
				}else {
					formula22 = obj.currentTarget.attributes.formula22.value;//获取回显展示公式DIV的id
					$(".commonConfigformula22").text(formula22);
				}
				//进行回显:↓
				formLoadDataEchartsTwo(configDDCSysName21,configDDCSysName22,formula21,formula22);
				pointLocations22.options.length=0;//将存储的ddc点位信息清空
				pointLocations21.options.length=0;//将存储的ddc点位信息清空
				loadPointLocations21(configFSysName21);
				loadPointLocations22(configFSysName22);
			}

		});
		//给echarts图表获取双击事件
		$(".integrationDBClickEchartsOne").dblclick(function(obj){
			var configDDCSysName1 = obj.currentTarget.attributes.fddcsysname1;//获得所点击div配置相应的ddc系统名称
			var configFSysName1 = obj.currentTarget.attributes.fsysname1;//获得所点击div的配置的系统名称数据

			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationColdHeatSourceDBClickEchartsOneForm"),
			});
			if(configDDCSysName1!=null&&configDDCSysName1!=""){//有数据，说明已配置，则回显数据，否则，不操作
				configDDCSysName1=configDDCSysName1.value;
				configFSysName1 = configFSysName1.value;
    			var  formula1;
				if (typeof (obj.currentTarget.attributes.formula1) == "undefined") {
					formula1 = "";
				}else {
					formula1 = obj.currentTarget.attributes.formula1.value;//获取回显展示公式DIV的id
				}
				// var commonConfigFormula1 = $("#"+formula1).html();//取得公式信息
				//进行回显:↓
				formLoadDataEchartsOne(configDDCSysName1,formula1);
				pointLocations1.options.length=0;//将存储的ddc点位信息清空
				loadPointLocationsOne(configFSysName1);
			}

		});
		//给echarts图表获取双击事件
		$(".integrationDBClickEchartsFour").dblclick(function(obj){
			var configDDCSysName41 = obj.currentTarget.attributes.fddcsysname41;//获得所点击div配置相应的ddc系统名称
			var configFSysName41 = obj.currentTarget.attributes.fsysname41;//获得所点击div的配置的系统名称数据
			var configDDCSysName42 = obj.currentTarget.attributes.fddcsysname42;//获得所点击div配置相应的ddc系统名称
			var configFSysName42 = obj.currentTarget.attributes.fsysname42;//获得所点击div的配置的系统名称数据
			var configDDCSysName43 = obj.currentTarget.attributes.fddcsysname43;//获得所点击div配置相应的ddc系统名称
			var configFSysName43 = obj.currentTarget.attributes.fsysname43;//获得所点击div的配置的系统名称数据
			var configDDCSysName44 = obj.currentTarget.attributes.fddcsysname44;//获得所点击div配置相应的ddc系统名称
			var configFSysName44 = obj.currentTarget.attributes.fsysname44;//获得所点击div的配置的系统名称数据
			var  formula41;
			var  formula42;
			var  formula43;
			var  formula44;

			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationColdHeatSourceDBClickEchartsFourForm"),
			});
			if(configDDCSysName41!=null&&configDDCSysName41!=""){//有数据，说明已配置，则回显数据，否则，不操作
				configDDCSysName41=configDDCSysName41.value;
				configFSysName41 = configFSysName41.value;
				if (typeof (obj.currentTarget.attributes.formula41) == "undefined") {
					formula41 = "";
				}else {
					formula41 = obj.currentTarget.attributes.formula41.value;//获取回显展示公式DIV的id
				}
				configDDCSysName42=configDDCSysName42.value;
				configFSysName42 = configFSysName42.value;
				if (typeof (obj.currentTarget.attributes.formula42) == "undefined") {
					formula42 = "";
				}else {
					formula42 = obj.currentTarget.attributes.formula42.value;//获取回显展示公式DIV的id
				}
				configDDCSysName43=configDDCSysName43.value;
				configFSysName43 = configFSysName43.value;
				if (typeof (obj.currentTarget.attributes.formula43) == "undefined") {
					formula43 = "";
				}else {
					formula43 = obj.currentTarget.attributes.formula43.value;//获取回显展示公式DIV的id
				}
				configDDCSysName44=configDDCSysName44.value;
				configFSysName44 = configFSysName44.value;
				if (typeof (obj.currentTarget.attributes.formula44) == "undefined") {
					formula44 = "";
				}else {
					formula44 = obj.currentTarget.attributes.formula44.value;//获取回显展示公式DIV的id
				}
				//进行回显:↓
				formLoadDataEchartsFour(configDDCSysName41,configDDCSysName42,configDDCSysName43,configDDCSysName44,formula41,formula42,formula43,formula44);
				pointLocations41.options.length=0;//将存储的ddc点位信息清空
				pointLocations42.options.length=0;//将存储的ddc点位信息清空
				pointLocations43.options.length=0;//将存储的ddc点位信息清空
				pointLocations44.options.length=0;//将存储的ddc点位信息清空
				loadPointLocations41(configFSysName41);
				loadPointLocations42(configFSysName42);
				loadPointLocations43(configFSysName43);
				loadPointLocations44(configFSysName44);
			}

		});
		}
		/* End add by yangjx at 20191226 */
		function formLoadData(configDDCSysName,commonConfigDesc,commonConfigFormula){
			form.render();//更新全部
			$("#integrationColdHeatSourceDBClickCommonForm select[name='configColdHeatSourceDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			$("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigformula']").val(commonConfigFormula);
			form.render();
		}
		function formLoadDataNone(configDDCSysName,commonConfigDesc,commonConfigFormula){
			form.render();//更新全部
			$("#integrationColdHeatSourceDBClickCommonForm select[name='configColdHeatSourceDdcFSysName'] option[value= '']").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			$("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigformula']").val(commonConfigFormula);
			form.render();
		}
		function formLoadDataEchartsOne(configDDCSysName1,commonConfigFormula1){
			form.render();//更新全部
			$("#integrationColdHeatSourceDBClickEchartsOneForm select[name='configColdHeatSourceDdcFSysName1'] option[value="+configDDCSysName1+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsOneForm input[name='commonConfigformula1']").val(commonConfigFormula1);
			form.render();
		}
		function formLoadDataEchartsTwo(configDDCSysName21,configDDCSysName22,formula21,formula22){
			form.render();//更新全部
			$("#integrationColdHeatSourceDBClickEchartsTwoForm select[name='configColdHeatSourceDdcFSysName21'] option[value="+configDDCSysName21+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsTwoForm select[name='configColdHeatSourceDdcFSysName22'] option[value="+configDDCSysName22+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula21']").val(formula21);
			$("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula22']").val(formula22);
			form.render();
		}
		function formLoadDataEchartsFour(configDDCSysName41,configDDCSysName42,configDDCSysName43,configDDCSysName44,formula41,formula42,formula43,formula44){
			form.render();//更新全部
			$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourceDdcFSysName41'] option[value="+configDDCSysName41+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourceDdcFSysName42'] option[value="+configDDCSysName42+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourceDdcFSysName43'] option[value="+configDDCSysName43+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourceDdcFSysName44'] option[value="+configDDCSysName44+"]").prop("selected","selected");
			$("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula41']").val(formula41);
			$("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(formula42);
			$("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula43']").val(formula43);
			$("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula44']").val(formula44);
			form.render();
		}
		$(function(){
			f_equipment_id = $("#coldHeatSourceHiddenInput").val();//取得主页面进入查看页面所传的设备id

			/*Start add by yangjx at 20191226 */
			loadOptions();//给下拉菜单加载选项
			integrationInitHtml(function ()
            {
                showCop();
                showTemperature();
                showEnergyEfficiency();
            });//初始化页面
            getAmmeterConfigParam();


			<!--wanghongjie 状态显示 -->
			/*queryColdHeatSource({ id: $('#SBQT_lrjz').val()}, function (data) {
				originalData = $.extend(true, originalData, data);

                dataConverter($.extend(true, {}, originalData), function (list) {
                    var sbpzlist=originalData;
                    for (var i=0;i<list.data.length;i++) {
                        if (f_equipment_id == list.data[i].id && f_equipment_id == sbpzlist.data[i].id){

                            $('#lrjzshow1_jzqt_3').attr('flag',originalData.data[i].currentState);
                            $('#lrjzshow1_jzqt_3').val(list.data[i].currentState);
                            $('#a_phase_voltage_lrjz').html(list.data[i].a_phase_voltage);
                            $('#b_phase_voltage_lrjz').html(list.data[i].b_phase_voltage);
                            $('#c_phase_voltage_lrjz').html(list.data[i].c_phase_voltage);
                            $('#a_phase_current_lrjz').html(list.data[i].a_phase_current);
                            $('#b_phase_current_lrjz').html(list.data[i].b_phase_current);
                            $('#c_phase_current_lrjz').html(list.data[i].c_phase_current);

                            $('#lrjzshow1_ldsgswd_1').html(list.data[i].freezeSupplyWaterTemperature);
                            $('#lrjzshow1_ldshswd_1').html(list.data[i].freezeReturnWaterTemperature);
                            $('#lrjzshow1_lqsgswd_1').html(list.data[i].coolingSupplyWaterTemperature);
                            $('#lrjzshow1_lqshswd_1').html(list.data[i].coolingReturnWaterTemperature);
                            $('#lrjzshow1_dqll_1').html(list.data[i].currentFlow);

                            /!*if (list.data[i].f_SBQD==255)?$('#SBQD_VALUE_lqt').html('运行');$('#lrjzshow1_ldshswd_1').html("开"):
                            $('#SBQD_VALUE_lqt').html('停止');$('#lrjzshow1_ldshswd_1').html("关");*!/
                            if (list.data[i].fanSwitch==255){
                                $('#SBQD_VALUE_lrjz').html('运行');//$('#lrjzshow1_jzqt_1').html("开");
                            }else {
                                $('#SBQD_VALUE_lrjz').html('停止');//$('#lrjzshow1_jzqt_1').html("关");
                            }

                        }

                    }
                });
			})*/
			/* End add by yangjx at 20191226 */


            // var sysNameList = $(".coldHeatSourcePageDivShow1").attr("fsysnameList");
            // integrationCommonDataTimedRefresh(sysNameList,".coldHeatSourcePageDivShow");
            /*waghongjie 左侧的配置数据实时刷新 start */
            // var sysNameListByLeft = $(".commonChangeColorColdHeatSource1").attr("fsysnamelistbyleft");
            // integrationCommonDataTimedRefreshByLeft(sysNameListByLeft,".commonChangeColorColdHeatSource");
            /* end */

		});

		// 获取电表配置参数
		function getAmmeterConfigParam()
        {
            var id = $('#electric_meter_number_hidden_lrjz').val();

            if (!id)
            {
                return;
            }

            $.ajax({
                type    : "POST",
                url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/query",
                dataType: "json",
                data    :  {
                    id: id
				},
                success: function (result) {

                    var data = result && result.data && result.data[0];

                    if (!data)
					{
						return;
					}

					$('.cabinetName').text(data.cabinetName);

                    ammeterConfigParam = {
                        instantEnergy: data.instantEnergy,
                        totalEnergy: data.totalEnergy,
                        aPhaseVoltage: data.aPhaseVoltage,
                        bPhaseVoltage: data.bPhaseVoltage,
                        cPhaseVoltage: data.cPhaseVoltage,
                        aPhaseCurrent: data.aPhaseCurrent,
                        bPhaseCurrent: data.bPhaseCurrent,
                        cPhaseCurrent: data.cPhaseCurrent
					};

                    $.ajax({
                        type: "post",
                        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
                        contentType:'application/json;charset=UTF-8',
                        traditional:true,
                        data:JSON.stringify({
                            f_sysName_list : Object.values(ammeterConfigParam)
                        }),
                        success: function (result) {

                            var list = result && result.data;

                            if (!Array.isArray(list))
							{
							    return;
							}

                            for (var key in list)
                            {

                                var item = list[key];
                                var f_sys_name = item.f_sys_name;
                                var f_init_val = item.f_init_val;

                                PubSub.subscribe(f_sys_name, divColdHeatSource.commonChangeColorLighter);

                                switch (f_sys_name)
								{
                                    case ammeterConfigParam.instantEnergy:
                                        $('#divColdHeatSourceParamTable .instantEnergy').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.totalEnergy:
                                        $('#divColdHeatSourceParamTable .totalEnergy').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.aPhaseVoltage:
                                        $('#divColdHeatSourceParamTable .aPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.bPhaseVoltage:
                                        $('#divColdHeatSourceParamTable .bPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.cPhaseVoltage:
                                        $('#divColdHeatSourceParamTable .cPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.aPhaseCurrent:
                                        $('#divColdHeatSourceParamTable .aPhaseCurrent').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.bPhaseCurrent:
                                        $('#divColdHeatSourceParamTable .bPhaseCurrent').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.cPhaseCurrent:
                                        $('#divColdHeatSourceParamTable .cPhaseCurrent').text(f_init_val);
                                        break;
								}


                            }

                        },

                    });
                },
                error: function (result) {

                    console.warn(result)
                }
            });

        }

		//wanghongjie 查询设备信息
		function queryColdHeatSource(obj, callback) {
			if(typeof callback !== 'function'){
				return;
			}
			obj = obj || {};


			$.ajax({
				type    : "POST",
				url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/coolingHeatingUnit",
				dataType: "json",
				data    :  obj,
				success: function (result) {

					callback(result);

				},
				error: function (result) {

					console.warn(result)
				}
			});
		}


		/* Start add by yangjx at 20191226 */
		function integrationInitHtml(callback){
			layui.use('form', function(){
				form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
				layer = layui.layer;
				//但是，如果你的HTML是动态生成的，自动渲染就会失效
				//因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
				form.on('select(ddcOption)', function (data) {//监听选择ddc后,点位选项根据选中的ddc信息来进行动态生成
                    //触发内容
                    ddcSysName=$('#configColdHeatSourceDdcFSysName').val();

				    switch (data.elem.id){
						case 'configColdHeatSourceDdcFSysName':
                            pointLocations.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations();
							break;
						case 'configColdHeatSourceDdcFSysName21':
                            pointLocations21.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations21();
							break;
						case 'configColdHeatSourceDdcFSysName22':
                            pointLocations22.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations22();
                            break;
						case 'configColdHeatSourceDdcFSysName1':
                            loadPointLocationsOne();
                            pointLocations1.options.length=0;//将存储的ddc点位信息清空
                            break;
						case 'configColdHeatSourceDdcFSysName41':
                            pointLocations41.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations41();
							break;
						case 'configColdHeatSourceDdcFSysName42':
                            pointLocations42.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations42();
                            break;
						case 'configColdHeatSourceDdcFSysName43':
                            pointLocations43.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations43();
                            break;
						case 'configColdHeatSourceDdcFSysName44':
                            pointLocations44.options.length=0;//将存储的ddc点位信息清空
                            loadPointLocations44();
                            break;

                    }

				});

				form.render();
			});
			searchIntegrationPageDivConfigNum(f_equipment_id,".coldHeatSourcePageDivShow","3");//查询页面div配置展示数目，进行展示
			searchIntegrationPageDivConfigNumByLeft(f_equipment_id,".commonChangeColorColdHeatSource","3");//查询页面div配置展示数目，进行展示 左侧
			loadPageData(f_equipment_id,".coldHeatSourcePageDivShow","3", callback);//查询页面配置的展示div所应展示的点位信息
			loadPageDatatop(f_equipment_id,".commonChangeColorColdHeatSource","3");//查询页面配置的展示div所应展示的点位信息 top
			loadPageDataUnder(f_equipment_id,".commonChangeColorColdHeatSource","3");//查询页面配置的展示div所应展示的点位信息

			// loadPageDataTwo(f_equipment_id,".commonConfigformula","3", callback);//查询页面配置的展示div所应展示的点位信息
		};

		/* form的监听事件,当提交时 */
		form.on('submit(savePageDivNumConfigLrjz)',function(data){//监听配置页面div最大数目
			confirmDivNumConfig();
		})
		/* form的监听事件,当提交时 */
		form.on('submit(saveCommonConfigLrjz)',function(data){
			confirmCommonConfig(pageDivSequence,index);
		})
		/*wanghongjie form的监听事件,当提交时 top */
		form.on('submit(saveCommonConfigtopLrjz)',function (data) {
			confirmCommonConfigtop(pageDivSequence,index);
		})
		/*wanghongjie form的监听事件,当提交时 left */
		form.on('submit(savePageDivNumConfigByLeftLrjz)',function (data) {
			confirmDivNumConfigByLeft();
		})
		/* form的监听事件,当echarts图表只有一个DDC提交时 */
		form.on('submit(saveEchartsOneConfigLrjz)',function(data){
			saveEchartsOneConfig(pageDivSequence,index);
		})
		/* form的监听事件,当echarts图表有两个DDC提交时 */
		form.on('submit(saveEchartsTwoConfigLrjz)',function(data){
			saveEchartsTwoConfig(pageDivSequence,index);
		})
		/* form的监听事件,当echarts图表有四个DDC提交时 */
		form.on('submit(saveEchartsFourConfigLrjz)',function(data){
			saveEchartsFourConfig(pageDivSequence,index);
		})
		/*wanghongjie 左侧div的数量配置 */
		function confirmDivNumConfigByLeft(){
			var confirmDivNumByLeft = $("#integrationColdHeatSourceDBClickDivLeaderConfigFormByLeft input[name='commonConfigDivMaxNumByLeft']").val();
			if (confirmDivNumByLeft>3 || confirmDivNumByLeft < 0){
				if (confirmDivNumByLeft != ""){
					layer.alert("配置的展示数目应在0~3之间");
					return;
				}
			}
			//提交之前，先验证相应的信息有没有，若表中无此相关配置，则进行插入操作，若有数据，则进行更新操作
			//1.根据设备id验证div数目配置表中有无此页面div配置信息20191219
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkDivNumDataInfoByLeft',
				dataType : "json",
				data : {f_equipment_id:f_equipment_id,
					f_type_id : "3"
				},//TODO   将实际的设备id传入
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后，如果div展示数目有变动，应刷新div展示数目
						if(pageConfigShowNumLeft==confirmDivNumByLeft){//若现在设置的div数目与之前配置的一样，无需执行更新操作，直接退出
							layer.close(index);
							return;
						}
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationDivNumConfigByLeft',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id TODO
								f_div_num: confirmDivNumByLeft,//div序号
								f_type_id : "3"
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置div数目成功
									pageConfigShowNumLeft=confirmDivNumByLeft;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.close(index);
									for(var i=1; i<=confirmDivNumByLeft;i++){//将配置展示的div显示
										$(".commonChangeColorColdHeatSource"+i).css('display','');
									}
									for(var i=parseInt(confirmDivNumByLeft)+1;i<=6;i++){//将未配置显示的div隐藏
										$(".commonChangeColorColdHeatSource"+i).css('display','none');
									}
									integrationInitHtml();//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{//若无数据，执行插入操作
						//执行插入配置div最大数目方法
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/confirmIntegrationDivNumConfigByLeft',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_div_num: confirmDivNumByLeft,//div序号
								f_type_id : "3"
							}),
							success: function (result) {//插入配置div展示数据成功
								if(result.code == '0'){
									pageConfigShowNumLeft=confirmDivNumByLeft;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									for(var i=1; i<=confirmDivNumByLeft;i++){//将配置展示的div显示
										$(".commonChangeColorColdHeatSource"+i).css('display','');
									}
									integrationInitHtml();//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}
		/*添加展示div最大数目配置保存,若已有配置，则进行更新操作  */
		function confirmDivNumConfig(){
			var confirmDivNum = $("#integrationColdHeatSourceDBClickDivLeaderConfigForm input[name='commonConfigDivMaxNum']").val();
			if(confirmDivNum>6||confirmDivNum<0){//校验验证配置的div展示数目应在1~6之间
				layer.alert("配置的展示数目应在0~6之间");
				return;
			}
			//提交之前，先验证相应的信息有没有，若表中无此相关配置，则进行插入操作，若有数据，则进行更新操作
			//1.根据设备id验证div数目配置表中有无此页面div配置信息20191219
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkDivNumDataInfo',
				dataType : "json",
				data : {f_equipment_id:f_equipment_id,
					f_type_id      :"3"}, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后，如果div展示数目有变动，应刷新div展示数目
						if(pageConfigShowNum==confirmDivNum){//若现在设置的div数目与之前配置的一样，无需执行更新操作，直接退出
							layer.close(index);
							return;
						}
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationDivNumConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id TODO
								f_div_num      : confirmDivNum,//div序号
								f_type_id      : '3'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置div数目成功
									pageConfigShowNum=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.close(index);
									for(var i=1; i<=confirmDivNum;i++){//将配置展示的div显示
										$(".coldHeatSourcePageDivShow"+i).css('display','');
									}
									for(var i=parseInt(confirmDivNum)+1;i<=6;i++){//将未配置显示的div隐藏
										$(".coldHeatSourcePageDivShow"+i).css('display','none');
									}
									integrationInitHtml();//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{//若无数据，执行插入操作
						//执行插入配置div最大数目方法
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/confirmIntegrationDivNumConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_div_num	   : confirmDivNum,//div序号
								f_type_id      : '3'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {//插入配置div展示数据成功
								if(result.code == '0'){
									pageConfigShowNum=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									for(var i=1; i<=confirmDivNum;i++){//将配置展示的div显示
										$(".coldHeatSourcePageDivShow"+i).css('display','');
									}
									integrationInitHtml();//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}
		
		function add(callback) {
			$.ajax({
				type : "get",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfigRepetition',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_sys_name		: $('#configColdHeatSourcePointLocationFid').val(),//配置的DDC所属点位信息
					f_seq 		   : pageDivSequence,//所点击的div序号
					f_type_id	   : "3" //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				},
				success : function (result) {
					if (result.code == '0') {
						layer.alert("此点位已配置");
						return;
					} else {
						callback(result);
					}
				},
				error : function(result) {
					console.log(result)
				}
			});
		}

		/*添加信息的 保存  */
		function confirmCommonConfig(){
			/*wanghongjie 添加之前验证此点位是否在左六个配置点位中已添加过,如果已经添加过,弹出提示框,没有的话继续修改或添加*/
			$.ajax({
				type : "get",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfigRepetition',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_sys_name		: $('#configColdHeatSourcePointLocationFid').val(),//配置的DDC所属点位信息
					f_seq 		   : pageDivSequence,//所点击的div序号
					f_type_id	   : "3", //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
					f_desc         : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
					f_formula      : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigformula']").val()//公式
				},
				success : function (result) {
					if (result.code == '0'){
						layer.alert("此点位已配置");
						return;
					}else {
						//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
						//pageDivSequence//取得所点击div的序号
						$.ajax({
							type : "get",
							url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
							dataType : "json",
							data : {
								f_equipment_id : f_equipment_id,
								f_seq          : pageDivSequence,//所点击的div序号
								f_type_id	   : "3" }, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							success : function(result) {
								if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
									$.ajax({
										type: "post",
										url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
										contentType:'application/json;charset=UTF-8',
										data:JSON.stringify({
											f_equipment_id: f_equipment_id,//配置的设备id
											f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName").val(),//配置展示DIV的DDC系统名称
											f_sys_name     : $('#configColdHeatSourcePointLocationFid').val(),//配置的DDC所属点位信息
											f_desc         : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
											f_formula      : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigformula']").val(),//公式
											f_seq          : pageDivSequence,//div序号
											f_type_id	   : "3"
										}),
										success: function (result) {
											if(result.code == '0'){ //更新配置成功
												layer.close(index);
												index = 0;
												integrationInitHtml();//刷新初始化页面

											}else{//更新配置失败
												layer.msg(result.msg);
											}
										},
										error: function (result) {
											layer.msg(result.msg);
										}
									});
								} else if(result.code="2"){//若无数据，执行插入操作
									//layer.msg(result.msg);
									$.ajax({
										type: "post",
										url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfig',
										contentType:'application/json;charset=UTF-8',
										data:JSON.stringify({
											f_equipment_id : f_equipment_id,//配置的设备id
											f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName").val(),//配置展示DIV的DDC系统名称
											f_sys_name     : $('#configColdHeatSourcePointLocationFid').val(),//配置的DDC所属点位信息
											f_desc		   : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
											f_formula      : $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigformula']").val(),//公式
											//f_seq: $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
											f_seq		   : pageDivSequence,//div序号
											f_type_id	   : "3"
										}),
										success: function (result) {
											if(result.code == '0'){
												layer.msg(result.msg);
												if(index != 0){
													layer.close(index);
													index = 0;
												}
												//如果配置插入成功，数据展示为所配置的信息
												integrationInitHtml();//刷新初始化页面
											}else{
												layer.msg(result.msg);
											}
										},
										error: function (result) {
											layer.msg(result.msg);
										}
									});
								}else{
									layer.msg(result.msg);
								}
							},
							error : function() {
								layer.msg("失败");
							}
						});
					}
				}
			});

		}

		/*wanghongjie 添加的信息 保存,top*/
		function confirmCommonConfigtop() {
			//保存之前,先校验表strongandweakelectricityintegration_divnumconfigbyleft中有没有ID对应的数据.若有,则更新,若无,则插入
			//pageDivSequence,取得所点击的div的序号
			$.ajax({
				type : "get",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfigtop',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_seq : pageDivSequence,//所点击的div序号
					f_type_id : "3"//f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
				},
				success : function (result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfigtop',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_desc: $("#integrationColdHeatSourceDBClickCommonFormtop input[name='commonConfigDesctop']").val(),//描述
								f_seq: pageDivSequence//div序号
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置成功
									layer.close(index);
									index = 0;
									integrationInitHtml();//刷新初始化页面

								}else{//更新配置失败
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else if(result.code="2"){//若无数据，执行插入操作
						//layer.msg(result.msg);
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfigtop',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_type_id : "3",//f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
								f_desc: $("#integrationColdHeatSourceDBClickCommonFormtop input[name='commonConfigDesctop']").val(),//描述
								//f_seq: $("#integrationColdHeatSourceDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
								f_seq: pageDivSequence,//div序号
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息
									integrationInitHtml();//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}

		function saveEchartsOneConfig(){
			//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
			//pageDivSequence//取得所点击div的序号
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_seq          : pageDivSequence,//所点击的div序号
					f_type_id	   : "3" }, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsOneForm input[name='commonConfigformula1']").val(),//公式
								f_ddc_sys_name1 : $("#configColdHeatSourceDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name1     : $('#configColdHeatSourcePointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula1      : $("#integrationColdHeatSourceDBClickEchartsOneForm input[name='commonConfigformula1']").val(),//公式
								f_seq          : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置成功
									layer.close(index);
									index = 0;
									integrationInitHtml(showCop);//刷新初始化页面

								}else{//更新配置失败
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					} else if(result.code="2"){//若无数据，执行插入操作
						//layer.msg(result.msg);
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id: f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsOneForm input[name='commonConfigformula1']").val(),//公式
								f_ddc_sys_name1 : $("#configColdHeatSourceDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name1     : $('#configColdHeatSourcePointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula1      : $("#integrationColdHeatSourceDBClickEchartsOneForm input[name='commonConfigformula1']").val(),//公式
								f_seq		   : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息
									integrationInitHtml(showCop);//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}
		function saveEchartsTwoConfig(){

			//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
			//pageDivSequence//取得所点击div的序号
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,//TODO   将实际的设备id传入
					f_seq          : pageDivSequence,//所点击的div序号
					f_type_id	   : "3" }, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id : f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName21").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid21').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula21']").val(),//公式
								f_ddc_sys_name21 : $("#configColdHeatSourceDdcFSysName21").val(),//配置展示DIV的DDC系统名称
								f_sys_name21     : $('#configColdHeatSourcePointLocationFid21').val(),//配置的DDC所属点位信息
								f_formula21      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula21']").val(),//公式
								f_ddc_sys_name22 : $("#configColdHeatSourceDdcFSysName22").val(),//配置展示DIV的DDC系统名称
								f_sys_name22     : $('#configColdHeatSourcePointLocationFid22').val(),//配置的DDC所属点位信息
								f_formula22      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula22']").val(),//公式

								f_seq          : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置成功
									layer.close(index);
									index = 0;
									integrationInitHtml(showEnergyEfficiency);//刷新初始化页面

								}else{//更新配置失败
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					} else if(result.code="2"){//若无数据，执行插入操作
						//layer.msg(result.msg);
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id : f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName21").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid21').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula21']").val(),//公式
								f_ddc_sys_name21 : $("#configColdHeatSourceDdcFSysName21").val(),//配置展示DIV的DDC系统名称
								f_sys_name21     : $('#configColdHeatSourcePointLocationFid21').val(),//配置的DDC所属点位信息
								f_formula21      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula21']").val(),//公式
								f_ddc_sys_name22 : $("#configColdHeatSourceDdcFSysName22").val(),//配置展示DIV的DDC系统名称
								f_sys_name22     : $('#configColdHeatSourcePointLocationFid22').val(),//配置的DDC所属点位信息
								f_formula22      : $("#integrationColdHeatSourceDBClickEchartsTwoForm input[name='commonConfigformula22']").val(),//公式
								f_seq		   : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息
									integrationInitHtml(showEnergyEfficiency);//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}
		function saveEchartsFourConfig(){
			//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
			//pageDivSequence//取得所点击div的序号
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,//TODO   将实际的设备id传入
					f_seq          : pageDivSequence,//所点击的div序号
					f_type_id	   : "3" }, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id : f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName41").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid41').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula41']").val(),//公式
								f_ddc_sys_name41 : $("#configColdHeatSourceDdcFSysName41").val(),//配置展示DIV的DDC系统名称
								f_sys_name41     : $('#configColdHeatSourcePointLocationFid41').val(),//配置的DDC所属点位信息
								f_formula41      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula41']").val(),//公式
								f_ddc_sys_name42 : $("#configColdHeatSourceDdcFSysName42").val(),//配置展示DIV的DDC系统名称
								f_sys_name42     : $('#configColdHeatSourcePointLocationFid42').val(),//配置的DDC所属点位信息
								f_formula42      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(),//公式
								f_ddc_sys_name43 : $("#configColdHeatSourceDdcFSysName43").val(),//配置展示DIV的DDC系统名称
								f_sys_name43     : $('#configColdHeatSourcePointLocationFid43').val(),//配置的DDC所属点位信息
								f_formula43      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(),//公式
								f_ddc_sys_name44 : $("#configColdHeatSourceDdcFSysName44").val(),//配置展示DIV的DDC系统名称
								f_sys_name44     : $('#configColdHeatSourcePointLocationFid44').val(),//配置的DDC所属点位信息
								f_formula44      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula44']").val(),//公式
								f_seq          : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置成功
									layer.close(index);
									index = 0;
									integrationInitHtml(showTemperature);//刷新初始化页面

								}else{//更新配置失败
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					} else if(result.code="2"){//若无数据，执行插入操作
						//layer.msg(result.msg);
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id : f_equipment_id,//配置的设备id
								f_ddc_sys_name : $("#configColdHeatSourceDdcFSysName41").val(),//配置展示DIV的DDC系统名称
								f_sys_name     : $('#configColdHeatSourcePointLocationFid41').val(),//配置的DDC所属点位信息
								f_formula      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula41']").val(),//公式
								f_ddc_sys_name41 : $("#configColdHeatSourceDdcFSysName41").val(),//配置展示DIV的DDC系统名称
								f_sys_name41     : $('#configColdHeatSourcePointLocationFid41').val(),//配置的DDC所属点位信息
								f_formula41      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula41']").val(),//公式
								f_ddc_sys_name42 : $("#configColdHeatSourceDdcFSysName42").val(),//配置展示DIV的DDC系统名称
								f_sys_name42     : $('#configColdHeatSourcePointLocationFid42').val(),//配置的DDC所属点位信息
								f_formula42      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(),//公式
								f_ddc_sys_name43 : $("#configColdHeatSourceDdcFSysName43").val(),//配置展示DIV的DDC系统名称
								f_sys_name43     : $('#configColdHeatSourcePointLocationFid43').val(),//配置的DDC所属点位信息
								f_formula43      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(),//公式
								f_ddc_sys_name44 : $("#configColdHeatSourceDdcFSysName44").val(),//配置展示DIV的DDC系统名称
								f_sys_name44     : $('#configColdHeatSourcePointLocationFid44').val(),//配置的DDC所属点位信息
								f_formula44      : $("#integrationColdHeatSourceDBClickEchartsFourForm input[name='commonConfigformula42']").val(),//公式
								f_seq		   : pageDivSequence,//div序号
								f_type_id	   : "3"
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息
									integrationInitHtml(showTemperature);//刷新初始化页面
								}else{
									layer.msg(result.msg);
								}
							},
							error: function (result) {
								layer.msg(result.msg);
							}
						});
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});
		}
		function loadPointLocations(configFSysName){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName!=null&&configFSysName!=""){
							$("#integrationColdHeatSourceDBClickCommonForm select[name='configColdHeatSourcePointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocationsOne(configFSysName1){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName1').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid1').append(new Option(item.f_nick_name,item.f_sys_name));
						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName1!=null&&configFSysName1!=""){
							$("#integrationColdHeatSourceDBClickEchartsOneForm select[name='configColdHeatSourcePointLocationFid1'] option[value="+configFSysName1+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations21(configFSysName21){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				// data:JSON.stringify({
				// 	f_sysName_list : sysNameList
				// }),
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName21').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid21').append(new Option(item.f_nick_name,item.f_sys_name));
						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName21!=null&&configFSysName21!=""){
							$("#integrationColdHeatSourceDBClickEchartsTwoForm select[name='configColdHeatSourcePointLocationFid21'] option[value="+configFSysName21+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations22(configFSysName22){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				// data:JSON.stringify({
				// 	f_sysName_list : sysNameList
				// }),
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName22').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid22').append(new Option(item.f_nick_name,item.f_sys_name));
						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName22!=null&&configFSysName22!=""){
							$("#integrationColdHeatSourceDBClickEchartsTwoForm select[name='configColdHeatSourcePointLocationFid22'] option[value="+configFSysName22+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations41(configFSysName41){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName41').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid41').append(new Option(item.f_nick_name,item.f_sys_name));

						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName41!=null&&configFSysName41!=""){
							$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourcePointLocationFid41'] option[value="+configFSysName41+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations42(configFSysName42){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName42').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid42').append(new Option(item.f_nick_name,item.f_sys_name));

						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName42!=null&&configFSysName42!=""){
							$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourcePointLocationFid42'] option[value="+configFSysName42+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations44(configFSysName44){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName44').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid44').append(new Option(item.f_nick_name,item.f_sys_name));

						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName44!=null&&configFSysName44!=""){
							$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourcePointLocationFid44'] option[value="+configFSysName44+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadPointLocations43(configFSysName43){

			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configColdHeatSourceDdcFSysName43').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourcePointLocationFid43').append(new Option(item.f_nick_name,item.f_sys_name));

						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName43!=null&&configFSysName43!=""){
							$("#integrationColdHeatSourceDBClickEchartsFourForm select[name='configColdHeatSourcePointLocationFid43'] option[value="+configFSysName43+"]").prop("selected","selected");
						}
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		function loadOptions(){
			//configColdHeatSourceDdcFSysName  所属ddc
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
				dataType : "json",
				data : {},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configColdHeatSourceDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName1').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName21').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName22').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName41').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName42').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName43').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configColdHeatSourceDdcFSysName44').append(new Option(item.f_nick_name,item.f_sys_name));
						})
						form.render();
					}else{
						layer.msg(result.msg);
					}
				},
				error : function() {
					layer.msg("失败");
				}
			});

		}
		/* End add by yangjx at 20191226 */

		//显示能效的信息
		function showEnergyEfficiency(){
			var dom = document.getElementById("energyEfficiencyLine");
			myChart1 = echarts.init(dom, 'light');
			var option_inter = {
				title: {
					text: '能效',
					textStyle : {
						fontSize: '16'
					},
				},
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'cross',
						label: {
							backgroundColor: '#ffbe08'
						}
					}
				},
				legend: {
					data:['当前制冷量','当前能耗'],
					x : '70',
				},
				toolbox: {
					show: true,
				},
				grid: {
					left: '7%',
					right: '0%',
					bottom: '10%',
					containLabel: true
				},
				dataZoom: {
					show: false,
					start: 0,
					end: 100
				},
				xAxis: [
					{
						type: 'category',
						boundaryGap: true,
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						data: []
					}
				],
				yAxis: [
					{
						type: 'value',
						scale: true,
						name: 'kW',
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						min: 0,
						boundaryGap: [0.2, 0.2]
					}
				],
				series: [
					{
						name:'当前制冷量',
						type:'line',
						connectNulls: true,
                        smooth: true,
						data:[]
					},
					{
						name:'当前能耗',
						type:'line',
						connectNulls: true,
						smooth:true,
						data:[]
					}
				]
			};
			//先展示 后填充数据
			myChart1.setOption(option_inter);


			// 当前制冷量所属点位系统名称
            var fSysName = $(".coldHeatSourcePageDivShow18").attr("fSysName");

            // 当前能耗所属点位系统名称
			var fSysName22 = $(".coldHeatSourcePageDivShow18").attr("fSysName22");

			var formula21 = $(".coldHeatSourcePageDivShow18").attr("formula21");
			var formula22 = $(".coldHeatSourcePageDivShow18").attr("formula22");

            if (!fSysName || !fSysName22)
            {
                return;
            }

            $.ajax({
                type: "post",
                url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
                contentType:'application/json;charset=UTF-8',
                traditional:true,
                data:JSON.stringify({
                    f_sysName_list : [fSysName, fSysName22]
                }),
                success: function (result) {

                    if(result.code !== '0')
                    {
						return;
                    }
                    var axisData = new Date().toLocaleTimeString().replace(/^\D*/,'');
                    var data_inter_out = option_inter.series[0].data;
                    var data_inter_in = option_inter.series[1].data;
					var f_formula;
					var initByFourReplace;


					for(var i = 1;i <= result.count;i++)
                    {

                        var f_sys_name = result.data[i - 1].f_sys_name || '';
                        var f_sys_name_old = result.data[i - 1].f_sys_name_old || '';

                        var f_init_val = result.data[i - 1].f_init_val || '';

                        // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';

                        if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                        {
                            f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                        }

                        PubSub.subscribe(f_sys_name, divColdHeatSource.energyEfficiencyLine);

						// (function (f_sys_name) {
						// 	setTimeout(function () {
						// 		divColdHeatSource.energyEfficiencyLine({value: 65, name: f_sys_name})
						// 	}, 5000)
						// }(f_sys_name));

                        if (f_sys_name == fSysName)
                        {
							 f_formula = formula21;
							if (typeof f_formula != "undefined" && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
                            data_inter_out.push(f_init_val);

                        } else if (f_sys_name == fSysName22)
						{
							 f_formula = formula22;
							if (typeof f_formula != "undefined"  && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
                            data_inter_in.push(f_init_val);
						}

					}
                    option_inter.xAxis[0].data.push(axisData);

                    myChart1.setOption(option_inter);

                },

            });

		}

		//显示COP
		function showCop(){
			var dom = document.getElementById("copLine");
			myChart2 = echarts.init(dom, 'light');
			var option_inter = {
				title: {
					text: '当前COP',
					textStyle : {
						fontSize: '16'
					},
				},
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'cross',
						label: {
							backgroundColor: '#ffbe08'
						}
					}
				},
				legend: {
					data:['COP'],
					x : '70',
				},
				toolbox: {
					show: true,
					feature: {//功能
					}
				},
				grid: {
					left: '7%',
					right: '0%',
					bottom: '10%',
					containLabel: true
				},
				dataZoom: {
					show: false,
					start: 0,
					end: 100
				},
				xAxis: [
					{
						type: 'category',
						boundaryGap: true,
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						data: []
					}
				],
				yAxis: [
					{
						type: 'value',
						scale: true,
						name: '数值',
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						min: 0,
						boundaryGap: [0.2, 0.2]
					}
				],
				series: [
					{
						name:'COP',
						type:'line',
						smooth:true,
						data:[]
					}
				]
			};
			//先展示 后填充数据
			myChart2.setOption(option_inter);

            var fSysName = $(".coldHeatSourcePageDivShow19").attr("fSysName");

            if (!fSysName)
            {
                return;
            }

            $.ajax({
                type: "post",
                url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
                contentType:'application/json;charset=UTF-8',
                traditional:true,
                data:JSON.stringify({
                    f_sysName_list : [fSysName]
                }),
                success: function (result) {

                    if(result.code !== '0')
                    {
                        return;
                    }

                    if (!Array.isArray(result.data) || result.data.length === 0)
                    {
                        return;
                    }

                    var axisData = new Date().toLocaleTimeString().replace(/^\D*/,'');
                    var data_inter_out = option_inter.series[0].data;
					var f_formula;
					var initByFourReplace;

                    var f_sys_name = result.data[0].f_sys_name || '';
                    var f_sys_name_old = result.data[0].f_sys_name_old || '';
                    var f_init_val = result.data[0].f_init_val || '';
                    // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';

                    if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                    {
                        f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                    }

                    PubSub.subscribe(f_sys_name, divColdHeatSource.copLine);

                    if (f_sys_name === fSysName)
                    {
						f_formula = $("#" + (".coldHeatSourcePageDivShow"+"194").replace(".","")).html();
						if (typeof f_formula != "undefined"  && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
                        data_inter_out.push(f_init_val);
                    }
                    option_inter.xAxis[0].data.push(axisData);

                    myChart2.setOption(option_inter);

                },

            });

		}

		//显示温度
		function showTemperature(){
			var dom = document.getElementById("temperatureLine");
			myChart4 = echarts.init(dom, 'light');
			var option_inter = {
				title: {
					text: '温度曲线',
					textStyle : {
						fontSize: '16'
					},
				},
				tooltip: {
					trigger: 'axis',//此处能够当鼠标移动到曲线位置时，自动出一个数据框展示数据
					axisPointer: {	//此处作用为鼠标移动到曲线上，会自动展示x,y轴数据提示
						type: 'cross',
						label: {
							backgroundColor: '#ffbe08'  //此处是设置的鼠标移动至曲线时，曲线x,y两处提示的背景色
						}
					}
				},
				legend: {
					data:['冷冻水供水温度','冷却水出口温度','冷却水供水温度','冷却水回水温度'],
					x : '70',
				},
				toolbox: {
					show: true,
					feature: {//功能
					}
				},
				grid: {
					left: '7%',
					right: '0%',
					bottom: '10%',
					containLabel: true
				},
				dataZoom: {
					show: false,
					start: 0,
					end: 100
				},
				xAxis: [
					{
						type: 'category',
						boundaryGap: true,
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						data: []
					}
				],
				yAxis: [
					{
						type: 'value',
						scale: true,
						name: '℃',
						axisLabel: {
							show: true,
							textStyle: {
								color: '#212122'
							}
						},
						min: 0,
						boundaryGap: [0.2, 0.2,0.2,0.2]
					}
				],
				series: [
					{
						name:'冷冻水供水温度',
						type:'line',
						connectNulls: true,
						smooth:false,
						data:[]
					},
					{
						name:'冷却水出口温度',
						type:'line',
						connectNulls: true,
						smooth:false,
						data:[]
					},
					{
						name:'冷却水供水温度',
						type:'line',
						connectNulls: true,
						smooth:false,
						data:[]
					},
					{
						name:'冷却水回水温度',
						type:'line',
						connectNulls: true,
						smooth:false,
						data:[]
					}
				]
			};

            //先展示 后填充数据
            myChart4.setOption(option_inter);

			var fSysName41 = $(".coldHeatSourcePageDivShow20").attr("fSysName41");
            var fSysName42 = $(".coldHeatSourcePageDivShow20").attr("fSysName42");
            var fSysName43 = $(".coldHeatSourcePageDivShow20").attr("fSysName43");
            var fSysName44 = $(".coldHeatSourcePageDivShow20").attr("fSysName44");

			var formula41 = $(".coldHeatSourcePageDivShow20").attr("formula41");
			var formula42 = $(".coldHeatSourcePageDivShow20").attr("formula42");
			var formula43 = $(".coldHeatSourcePageDivShow20").attr("formula43");
            var formula44 = $(".coldHeatSourcePageDivShow20").attr("formula44");

            $.ajax({
                type: "post",
                url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
                contentType:'application/json;charset=UTF-8',
                traditional:true,
                data:JSON.stringify({
                    f_sysName_list : [fSysName41, fSysName42, fSysName43, fSysName44]
                }),
                success: function (result) {

                    if(result.code !== '0')
                    {
                        return;
                    }

                    var axisData = new Date().toLocaleTimeString().replace(/^\D*/,'');
                    var data_inter_out = option_inter.series[0].data;
                    var data_inter_in = option_inter.series[1].data;
                    var data_inter_three = option_inter.series[2].data;
                    var data_inter_four = option_inter.series[3].data;
					var f_formula;
                    var initByFourReplace;

                    for(var i = 1;i <= result.count;i++)
                    {

                        var f_sys_name = result.data[i - 1].f_sys_name || '';
                        var f_sys_name_old = result.data[i - 1].f_sys_name_old || '';
                        var f_init_val = result.data[i - 1].f_init_val || '';
                        // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';

                        if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                        {
                            f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                        }

                        PubSub.subscribe(f_sys_name, divColdHeatSource.temperatureLine);

                        if (f_sys_name == fSysName41)
                        {
							f_formula = formula41;
							if (typeof f_formula != "undefined" && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
                            data_inter_out.push(f_init_val);

                        } else if (f_sys_name == fSysName42)
                        {
							 f_formula = formula42;
							if (typeof f_formula != "undefined" && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
                            data_inter_in.push(f_init_val);
                        }else if (f_sys_name == fSysName43)
						{
							 f_formula = formula43;
							if (typeof f_formula != "undefined" && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
                            data_inter_three.push(f_init_val);
						}else if (f_sys_name == fSysName44)
                        {
							 f_formula = formula44;
							if (typeof f_formula != "undefined" && f_formula != "") {
								initByFourReplace = f_formula.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
                            data_inter_four.push(f_init_val);
                        }

                    }
                    option_inter.xAxis[0].data.push(axisData);

                    myChart4.setOption(option_inter);

                },

            });
		}
		//关闭定时器通用方法，传入定时器名称，监听当页面不活动时，关闭定时器
		function clearAllInterval(intervalNam){
			//Start replace by yangjx at 20200108 for 优化定时器关闭
			//判断页面是否活跃，如果不活跃，关闭定时器。judgeActive()此方法为issp.js封装
			/* if(judgeActive("divColdHeatSourceDiv")){
            }else{
                clearInterval(intervalNam);
            } */
			//remark:↑     replace:↓
			if(document.getElementById('coldHeatSourceReturnBtn')===null){
				clearInterval(intervalNam);
			}
			//End replace by yangjx at 20200108 for 优化定时器关闭
		}
		/*添加和编辑的关闭按钮  */
		function closeLayerHost(){
			layer.close(index);
			index = 0;
		}
		return {
			closeCommonConfigInfos :function(){
				closeLayerHost();
			},
			// 冷热机组，左边方形实时数据显示
            commonChangeColorColdHeatSource(data){

			    if (!data)
				{
					return;
				}
			    var f_divClassNameCommon = '.commonChangeColorColdHeatSource';
			    var f_sys_name = data.name || '';
			    var f_init_val = data.value || '';
			    var f_engineer_unit = data.unit || '';
			    var sysNameOld = data.sysNameOld;
				var initByFour;
				var initByFourRe;
				var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");

			    if (pointValueConfig[f_sys_name] && pointValueConfig[f_sys_name][f_init_val])
				{
                    f_init_val = pointValueConfig[f_sys_name][f_init_val];
				}

                if($(f_divClassNameCommon + "8").attr("fSysName") == f_sys_name){

					if(!reg.test(f_init_val))
					{
						initByFour = $("#" + (f_divClassNameCommon+"84").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "82").text(f_init_val);
                    $(f_divClassNameCommon + "83").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon + "9").attr("fSysName") == f_sys_name){

					if(!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"94").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "92").text(f_init_val);
                    $(f_divClassNameCommon + "93").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon + "30").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"304").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "302").text(f_init_val);
                    $(f_divClassNameCommon + "303").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon + "15").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"154").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "152").text(f_init_val);
                    $(f_divClassNameCommon + "153").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon + "16").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"164").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "162").text(f_init_val);
                    $(f_divClassNameCommon + "163").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon + "17").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"174").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon + "172").text(f_init_val);
                    $(f_divClassNameCommon + "173").text(f_engineer_unit);
                }

			},
            // 冷热机组，右边圆角方形实时数据显示
            coldHeatSourcePageDivShowCallback(data){

                if (!data){
                    return;
                }

                var f_divClassNameCommon = '.coldHeatSourcePageDivShow';
                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';
                var f_engineer_unit = data.unit || '';
                var sysNameOld = data.sysNameOld;
								var initByFour;
								var initByFourRe;
								var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");

                if (pointValueConfig[f_sys_name] && pointValueConfig[f_sys_name][f_init_val])
                {
                    f_init_val = pointValueConfig[f_sys_name][f_init_val];
                }

                if($(f_divClassNameCommon+"1").attr("fSysName") == f_sys_name)
				{
                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"14").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"12").text(f_init_val);
                    $(f_divClassNameCommon+"13").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon+"2").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"24").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"22").text(f_init_val);
                    $(f_divClassNameCommon+"23").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon+"3").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"34").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"32").text(f_init_val);
                    $(f_divClassNameCommon+"33").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon+"4").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"44").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"42").text(f_init_val);
                    $(f_divClassNameCommon+"43").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon+"5").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"54").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"52").text(f_init_val);
                    $(f_divClassNameCommon+"53").text(f_engineer_unit);
                    return;
                }
                if($(f_divClassNameCommon+"6").attr("fSysName") == f_sys_name){

                	if (!reg.test(f_init_val)) {

						initByFour = $("#" + (f_divClassNameCommon+"64").replace(".","")).html();
						if (initByFour != "") {
							initByFourRe = initByFour.replace("$1",f_init_val);

							f_init_val = eval(initByFourRe);
						}
						f_init_val = Math.round(f_init_val*100)/100;
					}

                    $(f_divClassNameCommon+"62").text(f_init_val);
                    $(f_divClassNameCommon+"63").text(f_engineer_unit);
                    return;
                }
               /* if($(f_divClassNameCommon+"7").attr("fSysName21") == f_sys_name){
                    $(f_divClassNameCommon+"7").attr("val21",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"7").attr("fSysName22") == f_sys_name){
                    $(f_divClassNameCommon+"7").attr("val22",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"8").attr("fSysName1") == f_sys_name){
                    $(f_divClassNameCommon+"8").attr("val1",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"9").attr("fSysName41") == f_sys_name){
                    $(f_divClassNameCommon+"9").attr("val41",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"9").attr("fSysName42") == f_sys_name){
                    $(f_divClassNameCommon+"9").attr("val42",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"9").attr("fSysName43") == f_sys_name){
                    $(f_divClassNameCommon+"9").attr("val43",f_init_val);
                    return;
                }
                if($(f_divClassNameCommon+"9").attr("fSysName44") == f_sys_name){
                    $(f_divClassNameCommon+"9").attr("val44",f_init_val);
                }*/

			},
			// 电表参数实时数据显示
            commonChangeColorLighter(data)
			{
                if (!data){
                    return;
                }


                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';

                switch (f_sys_name)
                {
                    case ammeterConfigParam.instantEnergy:
                        $('#divColdHeatSourceParamTable .instantEnergy').text(f_init_val);
                        break;
                    case ammeterConfigParam.totalEnergy:
                        $('#divColdHeatSourceParamTable .totalEnergy').text(f_init_val);
                        break;
                    case ammeterConfigParam.aPhaseVoltage:
                        $('#divColdHeatSourceParamTable .aPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.bPhaseVoltage:
                        $('#divColdHeatSourceParamTable .bPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.cPhaseVoltage:
                        $('#divColdHeatSourceParamTable .cPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.aPhaseCurrent:
                        $('#divColdHeatSourceParamTable .aPhaseCurrent').text(f_init_val);
                        break;
                    case ammeterConfigParam.bPhaseCurrent:
                        $('#divColdHeatSourceParamTable .bPhaseCurrent').text(f_init_val);
                        break;
                    case ammeterConfigParam.cPhaseCurrent:
                        $('#divColdHeatSourceParamTable .cPhaseCurrent').text(f_init_val);
                        break;
                }
			},
            // 冷热机组，左边曲线实时数据显示
            energyEfficiencyLine(data)
			{
                if (!data || !myChart1){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';
				var f_formula;
				var initByFourReplace;

                var option_inter = myChart1.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName = $(".coldHeatSourcePageDivShow18").attr("fSysName");
				var formula21 = $(".coldHeatSourcePageDivShow18").attr("formula21");
				var formula22 = $(".coldHeatSourcePageDivShow18").attr("formula22");

                if (!fSysName)
                {
                    return;
                }

				var xdata = 	option_inter.xAxis[0].data;
                if (axisData != xdata.slice(-1)) {
					option_inter.xAxis[0].data.push(axisData);

					if (f_sys_name == fSysName)
					{
						f_formula = formula21;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[0].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}

					} else {

						let data_inter_out = option_inter.series[0].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.push(axisData);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}

					}
					// option_inter.xAxis[0].data.push(axisData);
					// myChart1.setOption(option_inter);

					// 当前能耗所属点位系统名称
					var fSysName22 = $(".coldHeatSourcePageDivShow18").attr("fSysName22");

					if (f_sys_name == fSysName22)
					{
						f_formula = formula22;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[1].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					} else {

						var data_inter_in = option_inter.series[1].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}


				} else {

					if (f_sys_name == fSysName)
					{
						f_formula = formula21;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[0].data;
						var data_inter_out_end = data_inter_out.pop();
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}

					}

					// 当前能耗所属点位系统名称
					var fSysName22 = $(".coldHeatSourcePageDivShow18").attr("fSysName22");

					if (f_sys_name == fSysName22)
					{
						f_formula = formula22;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[1].data;
						var data_inter_in_end = data_inter_in.pop();
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}
				}

				myChart1.setOption(option_inter);
			},
            // 冷热机组，中间曲线实时数据显示
            copLine(data)
			{
                if (!data || !myChart2){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';
				var f_formula;
				var initByFourReplace;

                var option_inter = myChart2.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName = $(".coldHeatSourcePageDivShow19").attr("fSysName");

                if (!fSysName)
                {
                    return;
                }

                if (f_sys_name == fSysName)
                {
					f_formula = $("#" + (".coldHeatSourcePageDivShow"+"194").replace(".","")).html();
					if (typeof f_formula != "undefined" && f_formula != "") {
						initByFourReplace = f_formula.replace("$1",f_init_val);

						f_init_val = eval(initByFourReplace);
					}

					f_init_val = Math.round(f_init_val*100)/100;
                    var data_inter_out = option_inter.series[0].data;

                    if(data_inter_out.length <= 10 ){
                        data_inter_out.push(f_init_val);
                        option_inter.xAxis[0].data.push(axisData);
                    }else{
                        data_inter_out.shift();
                        data_inter_out.push(f_init_val);
                        option_inter.xAxis[0].data.shift();
                        option_inter.xAxis[0].data.push(axisData);
                    }
                    myChart2.setOption(option_inter);

                }
			},
            // 冷热机组，右边曲线实时数据显示
            temperatureLine(data)
			{
                if (!data || !myChart4){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';
				var f_formula;
				var initByFourReplace;

                var option_inter = myChart4.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName41 = $(".coldHeatSourcePageDivShow20").attr("fSysName41");
				var fSysName42 = $(".coldHeatSourcePageDivShow20").attr("fSysName42");
				var fSysName43 = $(".coldHeatSourcePageDivShow20").attr("fSysName43");
				var fSysName44 = $(".coldHeatSourcePageDivShow20").attr("fSysName44");

				var formula41 = $(".coldHeatSourcePageDivShow20").attr("formula41");
				var formula42 = $(".coldHeatSourcePageDivShow20").attr("formula42");
				var formula43 = $(".coldHeatSourcePageDivShow20").attr("formula43");
				var formula44 = $(".coldHeatSourcePageDivShow20").attr("formula44");

				var xdata = 	option_inter.xAxis[0].data;
				if (axisData != xdata.slice(-1)) {
					option_inter.xAxis[0].data.push(axisData);

					if (f_sys_name == fSysName41)
					{
						f_formula = formula41;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[0].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}


					} else {

						let data_inter_out = option_inter.series[0].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.push(axisData);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName42)
					{
						f_formula = formula42;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[1].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}


					} else {

						let data_inter_in = option_inter.series[1].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.push(axisData);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName43)
					{
						f_formula = formula43;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[2].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}


					} else {

						let data_inter_out = option_inter.series[2].data;
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.push(axisData);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName44)
					{
						f_formula = formula44;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[3].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}


					} else {

						let data_inter_in = option_inter.series[3].data;
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.push(axisData);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(null);
							// option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

				} else {

					if (f_sys_name == fSysName41)
					{
						f_formula = formula41;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[0].data;
						var data_inter_out_end = data_inter_out.pop();
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName42)
					{
						f_formula = formula42;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[1].data;
						var data_inter_in_end = data_inter_in.pop();
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName43)
					{
						f_formula = formula43;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_out = option_inter.series[2].data;
						var data_inter_out_end = data_inter_out.pop();
						if(data_inter_out.length <= 10 ){
							data_inter_out.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_out.shift();
							data_inter_out.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

					if (f_sys_name == fSysName44)
					{
						f_formula = formula44;
						if (typeof f_formula != "undefined" && f_formula != "") {
							initByFourReplace = f_formula.replace("$1",f_init_val);

							f_init_val = eval(initByFourReplace);
						}
						f_init_val = Math.round(f_init_val*100)/100;
						var data_inter_in = option_inter.series[3].data;
						var data_inter_in_end = data_inter_in.pop();
						if(data_inter_in.length <= 10 ){
							data_inter_in.push(f_init_val);
							// option_inter.xAxis[0].data.push(axisData);
						}else{
							data_inter_in.shift();
							data_inter_in.push(f_init_val);
							option_inter.xAxis[0].data.shift();
							// option_inter.xAxis[0].data.push(axisData);
						}
					}

				}

				myChart4.setOption(option_inter);


			}
		}
	})(jQuery, window, document);
</script>
