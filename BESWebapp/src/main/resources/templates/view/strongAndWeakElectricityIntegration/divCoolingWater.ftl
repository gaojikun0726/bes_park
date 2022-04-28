<style>
.divCoolingWaterCanvasFather canvas{
	width:100%;
	height:100%;
}
/*冷却水查看页面*/
</style>
<script src="${ctx}/static/js/integration.js"></script>
<!-- coolingWater父类弹出框配置 -->
	<div class="layui-container">
	<form class="layui-form " style="display: none;" id="integrationCoolingWaterDBClickDivLeaderConfigForm">
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
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="savePageDivNumConfigLqs">提交</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="integration_divCoolingWater.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
<#--wanghongjie 数量配置的弹出框-->
<div class="layui-container">
	<form class="layui-form " style="display: none;" id="integrationCoolingWaterDBClickDivLeaderConfigFormleft">
		<input type="hidden" id="" name="">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">数量配置:</label>
				</div>
				<div class="layui-col-xs4">
					<input type="number" name="commonConfigDivMaxNumleft" value="" placeholder="div显示最大数量"  lay-verify="" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-xs9">
				<div class="layui-input-block" style="float:right;">
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="savePageDivNumConfigleftLqs">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="integration_divCoolingWater.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

	<!-- coolingWater弹出配置框 -->
	<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationCoolingWaterDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3">
			        	<select id="configCoolingWaterDdcFSysName" name="configCoolingWaterDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2">
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3">
			        	<select id="configCoolingWaterPointLocationFid" name="configCoolingWaterPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
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
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfigLqs">提交</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="integration_divCoolingWater.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>

<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationCoolingWaterDBClickCommonFormOne">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configCoolingWaterDdcFSysName1" name="configCoolingWaterDdcFSysName1" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2" style="width: 22.666667%;">
					<label class="layui-form-label">所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configCoolingWaterPointLocationFid1" name="configCoolingWaterPointLocationFid1" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">公式：</label>
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
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveEchartsOneConfigLqs">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="integration_divCoolingWater.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="layui-fluid"><#--wanghongjie 左侧三个设置div的top-->
	<form class="layui-form " style="display: none;" id="integrationCoolingWaterDBClickCommonFormtop">
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
					<button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfigtopLqs">提交</button>
					<button type="button" class="layui-btn layui-btn-primary" onclick="integration_divCoolingWater.closeCommonConfigInfos()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div  class="strongAndWeakElectricityIntegrationCommon" id="divCoolingWaterDiv">
	<div>
		<button class="orangebtn btn btn-default" id="returnCoolingWaterButton" style="width:60px;  height: 25px; margin-left:40px">返回</button>
	</div>
	<div class="divCoolingWaterTopConfig">
		<!-- Start by 数据div -->
		<#--wanghongjie 修改左侧三个可配置的div的class-->
		<div style="width: 15%; float: left; height: 100%; margin-left:20px; " class="divCoolingWaterTopConfigleft">

			<div style="height:30%;display:none;" class="commonChangeColorCoolingWater1">
				<div class="commonChangeColorLighter  integrationDBClickCommonCollingWatertop commonChangeColorCoolingWater71" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="7"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater8" style="width:50%;height:70%;float: left;text-align: center;" sequence="8">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater81"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater82"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater83"></div>
					<div style="display: none" class="commonChangeColorCoolingWater84"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater9" style="width:50%;height:70%;float: left;text-align: center;" sequence="9">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater91"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater92"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater93"></div>
					<div style="display: none" class="commonChangeColorCoolingWater94"></div>
				</div>
			</div>

			<div style="height:30%;margin-top:20px;display:none;" class="commonChangeColorCoolingWater2">
				<div class="commonChangeColorLighter integrationDBClickCommonCollingWatertop commonChangeColorCoolingWater101" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="10"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater30" style="width:50%;height:70%;float: left;text-align: center;" sequence="30">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater301"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater302"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater303"></div>
					<div style="display: none" class="commonChangeColorCoolingWater304"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater15" style="width:50%;height:70%;float: left;text-align: center;" sequence="15">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater151"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater152"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater153"></div>
					<div style="display: none" class="commonChangeColorCoolingWater154"></div>
				</div>
			</div>

			<div style="height:30%;margin-top:20px;display:none;" class="commonChangeColorCoolingWater3">
				<div class="commonChangeColorLighter integrationDBClickCommonCollingWatertop commonChangeColorCoolingWater131" style="width:100%;height:30%;text-align: center;line-height:35px;" sequence="13"><b></b></div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater16" style="width:50%;height:70%;float: left;text-align: center;" sequence="16">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater161"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater162"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater163"></div>
					<div style="display: none" class="commonChangeColorCoolingWater164"></div>
				</div>
				<div class="commonChangeColorDarker integrationDBClickCommonCollingWater commonChangeColorCoolingWater17" style="width:50%;height:70%;float: left;text-align: center;" sequence="17">
					<div style="height:25%;line-height:40px;" class="commonChangeColorCoolingWater171"></div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;" class="commonChangeColorCoolingWater172"></div>
					<div style="height:25%" class="commonChangeColorCoolingWater173"></div>
					<div style="display: none" class="commonChangeColorCoolingWater174"></div>
				</div>
			</div>
		</div>
		<!-- End by 数据div -->
		<!-- 图片div -->
		<div class="testphoto" style="width: 45%; float:left;height:100%;background:url(${ctx}/static/images/strongAndWeakElectricityIntegration/waterPumpGreen.png);background-repeat:no-repeat;background-size:100%; ">
			<div class="divCoolingWaterCanvasFather" style="height:85%;text-align: center;">
				<div style="text-align:center;">
                    <input type="hidden" id="SBQT_lqs" value="${f_ID}">
                    <input type="hidden" id="electric_meter_number_hidden_lqs" value="${f_Electric_meter_number}">
					<span style="color:white;"><span style="font-size: 18px;">名称：</span><span id="SBQD_lq" style="font-size: 18px;">${f_NAME}</span></span>
					<span id="SBQD_VALUE_lq" style="color:#4dd807;">&nbsp;&nbsp;&nbsp;&nbsp;${f_SBQD}</span>
				</div>
				<div id="divGifDisplayOrHide_coolingWeter" style="position: absolute; left: 42%;top: 31.4%; width: 10%;height:10%;background:url(${ctx}/static/images/strongAndWeakElectricityIntegration/watergif.gif) no-repeat 18.5% 17%; ">
<#--					<img src="${ctx}/static/images/strongAndWeakElectricityIntegration/watergif.gif" width="64" >-->
				</div>
				<!-- Start by 画布画斜线 -->
				<canvas id="coolingWatermcanvas" style="height:80%;"></canvas>
				<!-- End by 画布画斜线 -->
			</div>
			<#--<div style="height:15%;text-align: center;margin-top:10px;">
				<button id="showMoreCoolingWaterData" class="orangebtn btn btn-default" >更多属性</button>
			</div>-->
		</div>
		<!-- End by图片div -->
		<!-- Start by 圆圈div -->
		<div class="integrationDBClickDivLeaderConfig" style="width: 20%; float: left; height: 100%;">
			<div>
				<div class="greendiv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow1" style="display:none;" sequence="1">
					<div style="line-height:38px;" class="coolingWaterPageDivShow11"></div>
					<div style="font-size:23px;" class="coolingWaterPageDivShow12"></div>
					<div class="coolingWaterPageDivShow13"></div>
					<div style="display: none" class="coolingWaterPageDivShow14"></div>
				</div>
				<div class="greendiv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow2" style="display:none;" sequence="2">
					<div style="line-height:38px;" class="coolingWaterPageDivShow21"></div>
					<div style="font-size:23px;" class="coolingWaterPageDivShow22"></div>
					<div class="coolingWaterPageDivShow23"></div>
					<div style="display: none" class="coolingWaterPageDivShow24"></div>
				</div>
			</div>
			<div>
				<div class="bluediv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow3" style="margin-top:25px; display:none;"sequence="3">
					<div style="line-height:38px;"class="coolingWaterPageDivShow31"></div>
					<div style="font-size:23px;"class="coolingWaterPageDivShow32"></div>
					<div class="coolingWaterPageDivShow33"></div>
					<div style="display: none" class="coolingWaterPageDivShow34"></div>
				</div>
				<div class="bluediv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow4" style="margin-top:25px; display:none;"sequence="4">
					<div style="line-height:38px;" class="coolingWaterPageDivShow41"></div>
					<div style="font-size:23px;"class="coolingWaterPageDivShow42"></div>
					<div class="coolingWaterPageDivShow43"></div>
					<div style="display: none" class="coolingWaterPageDivShow44"></div>
				</div>
			</div>
			<div>
				<div class="orangediv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow5" style="margin-top:25px; display:none;"sequence="5">
					<div style="line-height:38px;"class="coolingWaterPageDivShow51"></div>
					<div style="font-size:23px;"class="coolingWaterPageDivShow52"></div>
					<div class="coolingWaterPageDivShow53"></div>
					<div style="display: none" class="coolingWaterPageDivShow54"></div>
				</div>
				<div class="orangediv circle integrationDBClickCommonCollingWater coolingWaterPageDivShow6" style="margin-top:25px; display:none;"sequence="6">
					<div style="line-height:38px;"class="coolingWaterPageDivShow61"></div>
					<div style="font-size:23px;"class="coolingWaterPageDivShow62"></div>
					<div class="coolingWaterPageDivShow63"></div>
					<div style="display: none" class="coolingWaterPageDivShow64"></div>
				</div>
			</div>


		</div>
		<!-- End by 圆圈div -->

		<!-- Start by 基本参数 -->
		<div class="commonChangeColorLighter" style="width: 15%; float: left; height: 100%;margin-left:20px;">
			<table class="divCoolingWaterParamTable" style="width:100%;margin-left:10px;">
                <caption style="text-align: center;color:white;"><b>电表参数</b></caption>
                <!--wanghongjie 增加基本参数的id -->
                <tr>
                    <td>所属机柜</td><td class="cabinetName"></td>
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
	<!-- 下方折线图 (当前运行频率、当前功率、能效比)-->
	<div class="divCoolingWaterBottomConfig">
    	<div class="commonChangeColorDarker integrationDBClickCommonCollingWaterOne coolingWaterPageDivShow18" style="width: 32%; float: left; height: 95%;margin-left:20px;"sequence="18">
        	<div id="coolingWaterCurrentOperatingFrequencyLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coolingWaterPageDivShow184"></div>
    	</div>
    	<div class="commonChangeColorDarker integrationDBClickCommonCollingWaterOne coolingWaterPageDivShow19" style="width: 32%; float: left; height: 95%;margin-left:10px;"sequence="19">
        	<div id="coolingWaterCurrentPowerLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coolingWaterPageDivShow194"></div>
    	</div>
    	<div class="commonChangeColorDarker integrationDBClickCommonCollingWaterOne coolingWaterPageDivShow20" style="width: 32%; float: left; height: 95%;margin-left:10px;"sequence="20">
        	<div id="coolingWaterEnergyConsumptionRatioLine" style="width: 90%; height: 100%;"></div>
			<div style="display: none"  class="coolingWaterPageDivShow204"></div>
    	</div>
	</div>
	<!-- 隐藏框--更多属性 -->
	<div  class="commonChangeColorLighter" id="showMoreCoolingWaterDataHiddenDiv" style="display:none;width:500px;height:100%;text-align:center;">
		<table class="hidentable"style="width:100%; height:100%;"  >
			<tr>
				<td style="width:20%;height: 5vh;"><b>属性名</b></td><td style="width:15%"><b>属性值</b></td><td style="width:15%"><b>单位</b></td><td style="width:25%"><b>操作</b></td>
			</tr>
			<#--<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >水阀开</td><td id="lqshow1_sfk_1">0</td><td id="lqshow1_sfk_2">/</td><td>
					<select id="lqshow1_sfk_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >水阀关</td><td id="lqshow1_sfg_1">0</td><td id="lqshow1_sfg_2">/</td><td>
					<select id="lqshow1_sfg_3" class="lqshow1_sfg_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >水泵启动</td><td id="lqshow1_sbqd_1">0</td><td id="lqshow1_sbqd_2">/</td><td>
					<select id="lqshow1_sbqd_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;" >水泵停止</td><td id="lqshow1_sbtz_1">0</td><td id="lqshow1_sbtz_2">/</td><td>
					<select id="lqshow1_sbtz_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">运行指示灯</td><td id="lqshow1_yxzsd_1">0</td><td id="lqshow1_yxzsd_2">/</td><td>
					<select id="lqshow1_yxzsd_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">故障指示灯</td><td id="lqshow1_gzzsd_1">0</td><td id="lqshow1_gzzsd_2">/</td><td>
					<select id="lqshow1_gzzsd_3" onchange="integration_divCoolingWater.setLampVPoint(this)">
						<option value="255">开</option>
						<option value="0">关</option>
					</select>
				</td>
			</tr>-->
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">水阀开关状态</td><td id="lqshow1_sfkgzt_1">0</td><td id="lqshow1_sfkgzt_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">变频器状态</td><td id="lqshow1_bpqzt_1">0</td><td id="lqshow1_bpqyxzt_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">水流状态</td><td id="lqshow1_slzt_1">0</td><td id="lqshow1_slzt_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">水阀故障状态</td><td id="lqshow1_sfgzzt_1">0</td><td id="lqshow1_sfgzzt_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">水泵运行状态</td><td id="lqshow1_sbyxzt_1">0</td><td id="lqshow1_sbyxzt_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">运行信号</td><td id="lqshow1_yxxh_1">0</td><td id="lqshow1_yxxh_2">/</td><td>/</td>
			</tr>
			<tr class="hidentabletr1"  ><#--可以往下位机发送状态,目前不知道具体的值,暂时不写-->
				<td style="text-align:left;color:#3d42f3;height: 5vh;">变频器频率设定</td><td id="lqshow1_bpqplsd_1">0</td><td id="lqshow1_bpqplsd_2">/</td>
				<td style="text-align: left;" >
					<input type="number" id="lqshow1_bpqplsd_3" class="form-control number" style="width:40%;display: inherit;" >%
					<span class="input-group-btn" style="display: inline-block;">
                        <button type="button" class="btn btn-primary" id="lqshow1_bpqplsd_4" onclick="integration_divCoolingWater.setWaterPoint(this)">设置</button></span>
				</td>
			</tr>
			<tr class="hidentabletr1">
				<td style="text-align:left;color:#3d42f3;height: 5vh;">变频器频率反馈</td><td id="lqshow1_bpqplfk_1">0</td><td id="lqshow1_bpqplfk_2"></td><td>/</td>
			</tr>

		</table>
	</div>
</div>
<script type="text/javascript">
    ;
    var integration_divCoolingWater = (function($, window, document, undefined) {
				var _ctx = '${ctx}';

				var index = 0;//默认弹框index为0

				var form; //只有执行了这一步，部分表单元素才会自动修饰成功
				var layer;
				//var index = 0;//默认弹框index为0
				var ddcSysName;
				var pointLocations	 = document.getElementById('configCoolingWaterPointLocationFid');
				var pointLocations1  = document.getElementById('configCoolingWaterPointLocationFid1');
				var f_equipment_id;
				var pageConfigShowNum;//配置的页面显示div数目
				var pageConfigShowNumLeft;//配置的页面显示div数目 左侧
				var pageDivSequence;//所配置展示div的序号
				var originalData={};

				var myChart1;
				var myChart2;
				var myChart4;

        var ammeterConfigParam = {};
        var coolingWaterWithBPQYXZT = {};

        var divHeightSum = $(window).height();//网页窗口可见区域总体高度
				var divCoolingWaterTopHeight= divHeightSum*0.55;//上半部高度
				var divWidthSum = $(window).width();//网页窗口可见区域总宽度
        $(".divCoolingWaterTopConfig").css("height",divCoolingWaterTopHeight);
        $(".divCoolingWaterBottomConfig").css({"height":divHeightSum*0.3,"margin-top":divHeightSum*0.015});
        $(".divCoolingWaterParamTable").css({"height":divCoolingWaterTopHeight*0.8});

        /* 点击左上方返回按钮，返回主页面 */
        $("#returnCoolingWaterButton").click(function() {
        	//返回主页面时，将此副页面开启的定时器关闭
        	//location.reload();view/strongAndWeakElectricityIntegration/coolingWater/showInitPage
        	$("#waterCoolantDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/coolingWater/showInitPage');
        });

        /* Start for 点击更多属性按钮展示弹出框 */
        $("#showMoreCoolingWaterData").click(function(){
        	layer.open({
    			tytle:'测试',
    			type:1,
    			area:['500px','600px'],
    			maxmin:true,//最大最小化
    			content:$('#showMoreCoolingWaterDataHiddenDiv'),
    		});
        });
        /* End 点击更多属性按钮展示弹出框 */

        /* Start by 画图片上斜线指示 */
        var coolingWatermcanvas  = document.getElementById("coolingWatermcanvas");    //获得画布

        var mcontext = coolingWatermcanvas.getContext("2d");    //获得上下文

        coolingWatermcanvas.width = 1000;     //重新设置标签的属性宽高
        coolingWatermcanvas.height = 1000;    //千万不要用 canvas.style.height
        //coolingWatermcanvas.style.border = "1px solid #f7f7f7";    //设置canvas的边

        //绘制三角形
        /*mcontext.strokeStyle = '#D2D2D2';
        mcontext.beginPath();        //开始路径
        mcontext.moveTo(0,100);    //三角形，左顶点
        //mcontext.lineTo(300, 100);   //右顶点
        mcontext.lineTo(300, 400);   //底部的点
        mcontext.closePath();        //结束路径
        mcontext.stroke();           //描边路径*/
        /* End by 画图片上斜线指示 */

        $(function(){
        	f_equipment_id = $("#waterCoolantHiddenInput").val();//取得主页面进入查看页面所传的设备id

        	/* Start add by yangjx at 20191227 */
        	loadOptions();//给下拉菜单加载选项
            integrationInitHtml(function ()
            {
                showCurrentOperatingFrequency();
                showCurrentPower();
                showEnergyConsumptionRatio();
            });//初始化页面
			/*queryCoolingWaterUnit({}, function (data) {
				originalData = $.extend(true, originalData, data);
				timer = setInterval(function () {

					if($('#divCoolingWaterDiv').length === 0){
						clearInterval(timer);
					}

					dataConverter($.extend(true, {}, originalData), function (list) {
						var sbpzlist=originalData;
						for (var i=0;i<list.data.length;i++) {
							if (f_equipment_id == list.data[i].f_ID && f_equipment_id == sbpzlist.data[i].f_ID){
								$('#SBQD_lq').html(list.data[i].f_NAME);
								$('#lqshow1_sbqd_3').attr('flag',originalData.data[i].f_SBQD);
								$('#lqshow1_sbqd_3').val(list.data[i].f_SBQD);
								$('#lqshow1_sfk_3').attr('flag',originalData.data[i].f_SFK);
								$('#lqshow1_sfk_3').val(list.data[i].f_SFK);
								$('#lqshow1_sfg_3').attr('flag',originalData.data[i].f_SFG);
								$('#lqshow1_sfg_3').val(list.data[i].f_SFG);
								$('#lqshow1_sbtz_3').attr('flag',originalData.data[i].f_SBTZ);
								$('#lqshow1_sbtz_3').val(list.data[i].f_SBTZ);
								$('#lqshow1_yxzsd_3').attr('flag',originalData.data[i].f_YXZSD);
								$('#lqshow1_yxzsd_3').val(list.data[i].f_YXZSD);
								$('#lqshow1_gzzsd_3').attr('flag',originalData.data[i].f_GZZSD);
								$('#lqshow1_gzzsd_3').val(list.data[i].f_GZZSD);
								$('#a_phase_voltage_lq').html(list.data[i].a_phase_voltage);
								$('#b_phase_voltage_lq').html(list.data[i].b_phase_voltage);
								$('#c_phase_voltage_lq').html(list.data[i].c_phase_voltage);
								$('#a_phase_current_lq').html(list.data[i].a_phase_current);
								$('#b_phase_current_lq').html(list.data[i].b_phase_current);
								$('#c_phase_current_lq').html(list.data[i].c_phase_current);

								/!*if (list.data[i].f_SBQD==255)?$('#SBQD_VALUE_lq').html('运行');$('#lqshow1_sbqd_1').html("开"):
								$('#SBQD_VALUE_lq').html('停止');$('#lqshow1_sbqd_1').html("关");*!/
								if (list.data[i].f_SBQD==255){
									$('#SBQD_VALUE_lq').html('运行');$('#lqshow1_sbqd_1').html("开");
								}else {
									$('#SBQD_VALUE_lq').html('停止');$('#lqshow1_sbqd_1').html("关");
								}
								if(list.data[i].f_SFK==255 ){
									$('#lqshow1_sfk_1').html("开");
								}else {
									$('#lqshow1_sfk_1').html("关");
								}
								if (list.data[i].f_SFG==255) {
									$('#lqshow1_sfg_1').html("开");
								}else {
									$('#lqshow1_sfg_1').html("关");
								}if (list.data[i].f_SBTZ==255) {
									$('#lqshow1_sbtz_1').html("开");
								}else {
									$('#lqshow1_sbtz_1').html("关");
								}
								if (list.data[i].f_YXZSD==255) {
									$('#lqshow1_yxzsd_1').html("开");
								}else {
									$('#lqshow1_yxzsd_1').html("关");
								}
								if (list.data[i].f_GZZSD==255) {
									$('#lqshow1_gzzsd_1').html("开");
								}else {
									$('#lqshow1_gzzsd_1').html("关");
								}
								if (list.data[i].f_SFKDW==255 || list.data[i].f_SFGDW==0){
									$('#lqshow1_sfkgzt_1').html("开");
								}else {
									$('#lqshow1_sfkgzt_1').html("关");
								}
								if (list.data[i].f_BPYXZT==255 || list.data[i].f_BPGZZT==0){
									$('#lqshow1_bpqzt_1').html("运行");
								}else {
									$('#lqshow1_bpqzt_1').html("故障");
								}
								if (list.data[i].f_SLZT==255){
									$('#lqshow1_slzt_1').html("流动");
								}else {
									$('#lqshow1_slzt_1').html("静止");
								}
								if (list.data[i].f_SFGZZT==255){
									$('#lqshow1_sfgzzt_1').html("故障");
								}else {
									$('#lqshow1_sfgzzt_1').html("正常");
								}
								if (list.data[i].f_SBYXZT==255){
									$('#lqshow1_sbyxzt_1').html("运行");
								}else {
									$('#lqshow1_sbyxzt_1').html("静止");
								}
								if (list.data[i].f_QDXH==255 || list.data[i].f_TZXH==0){
									$('#lqshow1_yxxh_1').html("启动");
								}else {
									$('#lqshow1_yxxh_1').html("停止");
								}
								for (var unit_id=0;unit_id<list.DDCLIST.length;unit_id++){
									if (list.data[i].f_BPQPLFK_alias==list.DDCLIST[unit_id].f_nick_name) {//变频器频率反馈
										$('#lqshow1_bpqplfk_1').html(list.data[i].f_BPQPLFK);
										$('#lqshow1_bpqplfk_2').html(list.DDCLIST[unit_id].unit);
									}
									if (list.data[i].f_BPQPLSD_alias==list.DDCLIST[unit_id].f_nick_name) {//变频器频率设定
										$('#lqshow1_bpqplsd_4').attr('flag',originalData.data[i].f_BPQPLSD);
										//$('#lqshow1_bpqplsd_3').attr('flag2',originalData.data[i].f_BPQPLSD);
										$('#lqshow1_bpqplsd_1').html(list.data[i].f_BPQPLSD);
										$('#lqshow1_bpqplsd_2').html(list.DDCLIST[unit_id].unit);
									}
								}
							}

						}
					});
				}, 1000)
			})*/
            /* End add by yangjx at 20191227 */

            // Start add by yangjx at 20200110
     			// 当关闭此标签页面时，消除此定时器(依据：当未关闭此标签页面时，能根据id获取到信息，关闭后，无法根据id获得信息，则关闭定时器)

			var sysNameList = $(".coolingWaterPageDivShow1").attr("fsysnameList");
			integrationCommonDataTimedRefresh(sysNameList,".coolingWaterPageDivShow");
			/*wanghongjie 左侧配置的数据实时刷新 start*/
			var sysNameListbyleft = $(".commonChangeColorCoolingWater1").attr("fsysnamelistbyleft");
			integrationCommonDataTimedRefreshByLeft(sysNameListbyleft,".commonChangeColorCoolingWater");
				/*end*/
            // End add by yangjx at 20200110

            getAmmeterConfigParam();
					//对冷却水的变频器运行状态的值做判断,255的时候,图片上的gif图层显示,如果没有配置变频器运行状态或者为0,则隐藏
					getCoolingWeterWithF_BPYXZT(f_equipment_id);
        })

        /* Start add by yangjx at 20191227 */
        //给所展示的div的父类div设置双击事件，当点击时，可设置所展示的div的数量，最大展示数量为6；

			var sbgl = $("#leftMenu").text();
			if (sbgl.indexOf("设备管理") != -1) {

        $(".integrationDBClickDivLeaderConfig").dblclick(function(e){

        	if (e.target.className == "integrationDBClickDivLeaderConfig") {//点击子div时，不弹出父div配置框
        		index = layer.open({
            		tytle:'配置',
            		type:1,
            		area:['30vw','25vh'],
            		maxmin:true,
            		content:$("#integrationCoolingWaterDBClickDivLeaderConfigForm"),
            	});
            }
        });

		//wanghongjie 给左侧所展示的div的父类div设置双击事件，当点击时，可设置所展示的div的数量，最大展示数量为3；
		$(".divCoolingWaterTopConfigleft").dblclick(function(e){

			if (e.target.className == "divCoolingWaterTopConfigleft") {//点击子div时，不弹出父div配置框
				index = layer.open({
					tytle:'配置',
					type:1,
					area:['30vw','25vh'],
					maxmin:true,
					content:$("#integrationCoolingWaterDBClickDivLeaderConfigFormleft"),
				});
			}
		});
		$(".integrationDBClickCommonCollingWater").dblclick(function(obj){

			var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
			var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationCoolingWaterDBClickCommonForm"),
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
      	//给div设置双击弹出配置框设置
        $(".integrationDBClickCommonCollingWaterOne").dblclick(function(obj){
        	var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
        	var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
        	pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
        	index = layer.open({
        		tytle:'配置',
        		type:1,
        		area:['40vw','60vh'],
        		maxmin:true,
        		content:$("#integrationCoolingWaterDBClickCommonFormOne"),
        	});
					var  formula1;
        	if(configDDCSysName!=null&&configDDCSysName!=""){//有数据，说明已配置，则回显数据，否则，不操作
        		configDDCSysName=configDDCSysName.value;
        		configFSysName = configFSysName.value;

				if (typeof (obj.currentTarget.attributes.formula53) == "undefined") {
					formula1 = "";
				}else {
					formula1 = obj.currentTarget.attributes.formula53.value;//获取回显展示公式DIV的id
				}
        		//进行回显:↓
        		formLoadDataOne(configDDCSysName,formula1);
        		pointLocations1.options.length=0;//将存储的ddc点位信息清空
				loadPointLocationsOne(configFSysName);
        	} else {
				configDDCSysName = "";
				formula1 = '';
				formLoadDataOneNone(configDDCSysName,formula1);
				pointLocations1.options.length=0;//将存储的ddc点位信息清空
				loadPointLocationsOne(configFSysName);
			}

        });

		//wanghongjie 给div设置双击弹出配置框设置 top
		$(".integrationDBClickCommonCollingWatertop").dblclick(function(obj){
			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationCoolingWaterDBClickCommonFormtop"),
			});
		});

			}

        function formLoadData(configDDCSysName,commonConfigDesc,commonConfigFormula){
			form.render();//更新全部
			$("#integrationCoolingWaterDBClickCommonForm select[name='configCoolingWaterDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
			$("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			$("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigformula']").val(commonConfigFormula);
			form.render();
		}
		function formLoadDataOne(configDDCSysName,commonConfigFormula){
			form.render();//更新全部
			$("#integrationCoolingWaterDBClickCommonFormOne select[name='configCoolingWaterDdcFSysName1'] option[value="+configDDCSysName+"]").prop("selected","selected");
			$("#integrationCoolingWaterDBClickCommonFormOne input[name='commonConfigformula1']").val(commonConfigFormula);
			form.render();
		}
		function formLoadDataNone(configDDCSysName,commonConfigDesc,commonConfigFormula){
			form.render();//更新全部
			$("#integrationCoolingWaterDBClickCommonForm select[name='configCoolingWaterDdcFSysName'] option[value= '']").prop("selected","selected");
			$("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			$("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigformula']").val(commonConfigFormula);
			form.render();
		}
		function formLoadDataOneNone(configDDCSysName,commonConfigFormula){
			form.render();//更新全部
			$("#integrationCoolingWaterDBClickCommonFormOne select[name='configCoolingWaterDdcFSysName1'] option[value='']").prop("selected","selected");
			$("#integrationCoolingWaterDBClickCommonFormOne input[name='commonConfigformula1']").val('');
			form.render();
		}
        // 获取电表配置参数
        function getAmmeterConfigParam()
        {

            var id = $('#electric_meter_number_hidden_lqs').val();

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

                                PubSub.subscribe(f_sys_name, integration_divCoolingWater.realTimeMeterData);

                                switch (f_sys_name)
                                {
                                    case ammeterConfigParam.instantEnergy:
                                        $('.commonChangeColorLighter .instantEnergy').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.totalEnergy:
                                        $('.commonChangeColorLighter .totalEnergy').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.aPhaseVoltage:
                                        $('.commonChangeColorLighter .aPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.bPhaseVoltage:
                                        $('.commonChangeColorLighter .bPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.cPhaseVoltage:
                                        $('.commonChangeColorLighter .cPhaseVoltage').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.aPhaseCurrent:
                                        $('.commonChangeColorLighter .aPhaseCurrent').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.bPhaseCurrent:
                                        $('.commonChangeColorLighter .bPhaseCurrent').text(f_init_val);
                                        break;
                                    case ammeterConfigParam.cPhaseCurrent:
                                        $('.commonChangeColorLighter .cPhaseCurrent').text(f_init_val);
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

			//对冷却水的变频器运行状态的值做判断,255的时候,图片上的gif图层显示,如果没有配置变频器运行状态或者为0,则隐藏
			function getCoolingWeterWithF_BPYXZT(data) {
				if (typeof (data) == 'undefined') {
					return;
				}

				$.ajax({
					type    : "POST",
					url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/query_F_BPYXZT",
					dataType: "json",
					data    :  {
						id: data
					},
					success: function (result) {
						var data = result && result.data && result.data[0];

						if (!data)
						{
							return;
						}

						if (data.f_bpqyxzt == null) {
							$("#divGifDisplayOrHide_coolingWeter").hide()
							return;
						}
						coolingWaterWithBPQYXZT = {
							f_bpqyxzt : data.f_bpqyxzt
						};

						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
							contentType:'application/json;charset=UTF-8',
							traditional:true,
							data:JSON.stringify({
								f_sysName_list : Object.values(coolingWaterWithBPQYXZT)
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

									PubSub.subscribe(f_sys_name, integration_divCoolingWater.F_BPYXZTRealTimeDataShowGIFCallback_coolingWater);

									switch (f_sys_name)
									{
										case coolingWaterWithBPQYXZT.f_bpqyxzt:
											if (f_init_val == "255") {
												$("#divGifDisplayOrHide_coolingWeter").show()
											}else {
												$("#divGifDisplayOrHide_coolingWeter").hide()
											}
											break;
									}


								}

							},

						});
					}
				});
			}

        function integrationInitHtml(callback){
        	layui.use('form', function(){
          	  form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
          	  layer = layui.layer;
          	  //但是，如果你的HTML是动态生成的，自动渲染就会失效
          	  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
          	  form.on('select(ddcOption)', function (data) {//监听选择ddc后,点位选项根据选中的ddc信息来进行动态生成
          		  //触发内容
          		ddcSysName=$('#configCoolingWaterDdcFSysName').val();

				  switch (data.elem.id){
					  case 'configCoolingWaterDdcFSysName':
						  //触发切换相应ddc对应的点位信息时，先将之前存储的信息清空，再去获取所属DDC配置的点位；
						  pointLocations.options.length=0;//将存储的ddc点位信息清空
						  //获取所属DDC配置的点位
						  loadPointLocations();
						  break;
					  case 'configCoolingWaterDdcFSysName1':
						  loadPointLocationsOne();
						  pointLocations1.options.length=0;//将存储的ddc点位信息清空
						  break;
				  }
              });

          	  form.render();
          });
        	searchIntegrationPageDivConfigNum(f_equipment_id,".coolingWaterPageDivShow","2");//查询页面div配置展示数目，进行展示
					searchIntegrationPageDivConfigNumByLeft(f_equipment_id,".commonChangeColorCoolingWater","2");//查询页面左侧div配置展示数目，进行展示
        	loadPageData(f_equipment_id,".coolingWaterPageDivShow","2", callback);//查询页面配置的展示div所应展示的点位信息
					loadPageDatatop(f_equipment_id,".commonChangeColorCoolingWater","2");//查询页面配置的展示div所应展示的点位信息 top
					loadPageDataUnder(f_equipment_id,".commonChangeColorCoolingWater","2");//查询 左侧页面配置的展示div所应展示的点位信息
        };

        /*添加和编辑的关闭按钮  */
    	function closeLayerHost(){
    		layer.close(index);
    		index = 0;
    	}

    	 /* form的监听事件,当提交时 */
	    form.on('submit(savePageDivNumConfigLqs)',function(data){//监听配置页面div最大数目
	    	confirmDivNumConfig();
	    })
		/* form的监听事件,当提交时 */
		form.on('submit(savePageDivNumConfigleftLqs)',function(data){//监听左侧配置页面div最大数目
			confirmDivNumConfigleft();
		})
		/* form的监听事件,当提交时 */
		form.on('submit(saveCommonConfigtopLqs)',function(data){//监听左侧配置页面div的top描述
			confirmCommonConfigtop(pageDivSequence,index);
		})
    	 /* form的监听事件,当提交时 */
	    form.on('submit(saveCommonConfigLqs)',function(data){
	    	confirmCommonConfig(pageDivSequence,index);
	    })
		form.on('submit(saveEchartsOneConfigLqs)',function(data){
			confirmCommonConfig1(pageDivSequence,index);
		})

	    /*添加展示div最大数目配置保存,若已有配置，则进行更新操作  */
 		function confirmDivNumConfig(){
    		 var confirmDivNum = $("#integrationCoolingWaterDBClickDivLeaderConfigForm input[name='commonConfigDivMaxNum']").val();
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
	            data : {f_equipment_id : f_equipment_id,
	            		f_type_id      : '2'}, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
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
	      	  		        	f_equipment_id : f_equipment_id,//配置的设备id TODO
	      	  		        	f_div_num      : confirmDivNum,//div序号
	      	  		        	f_type_id      : '2'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
	      	  		        }),
	      	  		        success: function (result) {
	      	  		        	if(result.code == '0'){//更新配置div数目成功
		      	  		        	pageConfigShowNum=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
	      	  		        		layer.close(index);
	      	  		        		for(var i=1; i<=confirmDivNum;i++){//将配置展示的div显示
	      								$(".coolingWaterPageDivShow"+i).css('display','');
	      							}
	      	  		        		for(var i=parseInt(confirmDivNum)+1;i<=6;i++){//将未配置显示的div隐藏
	      	  		        			$(".coolingWaterPageDivShow"+i).css('display','none');
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
	      	  		        	f_equipment_id : f_equipment_id,//配置的设备id
	      	  		        	f_div_num      : confirmDivNum,//div序号
	      	  		        	f_type_id      : '2'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
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
	      								$(".coolingWaterPageDivShow"+i).css('display','');
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

		/*wanghongjie 添加展示div最大数目配置保存,若已有配置，则进行更新操作  */
		function confirmDivNumConfigleft(){
			var confirmDivNum = $("#integrationCoolingWaterDBClickDivLeaderConfigFormleft input[name='commonConfigDivMaxNumleft']").val();
			if(confirmDivNum>3||confirmDivNum<0){//校验验证配置的div展示数目应在1~6之间
				layer.alert("配置的展示数目应在0~3之间");
				return;
			}
			//提交之前，先验证相应的信息有没有，若表中无此相关配置，则进行插入操作，若有数据，则进行更新操作
			//1.根据设备id验证div数目配置表中有无此页面div配置信息20191219
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkDivNumDataInfoByLeft',
				dataType : "json",
				data : {f_equipment_id : f_equipment_id,
					f_type_id      : '2'}, //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后，如果div展示数目有变动，应刷新div展示数目
						if(pageConfigShowNumLeft==confirmDivNum){//若现在设置的div数目与之前配置的一样，无需执行更新操作，直接退出
							layer.close(index);
							return;
						}
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationDivNumConfigByLeft',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id : f_equipment_id,//配置的设备id TODO
								f_div_num      : confirmDivNum,//div序号
								f_type_id      : '2'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置div数目成功
									pageConfigShowNumLeft=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.close(index);
									for(var i=1; i<=confirmDivNum;i++){//将配置展示的div显示
										$(".commonChangeColorCoolingWater"+i).css('display','');
									}
									for(var i=parseInt(confirmDivNum)+1;i<=6;i++){//将未配置显示的div隐藏
										$(".commonChangeColorCoolingWater"+i).css('display','none');
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
								f_equipment_id : f_equipment_id,//配置的设备id
								f_div_num      : confirmDivNum,//div序号
								f_type_id      : '2'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {//插入配置div展示数据成功

								if(result.code == '0'){
									pageConfigShowNumLeft=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									for(var i=2; i<=confirmDivNum;i++){//将配置展示的div显示
										$(".commonChangeColorCoolingWater"+i).css('display','');
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

		/*添加信息的 保存  */
		function confirmCommonConfig(){
			/*wanghongjie 添加之前验证此点位是否在左六个配置点位中已添加过,如果已经添加过,弹出提示框,没有的话继续修改或添加*/
			$.ajax({
				type : "get",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfigRepetition',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_sys_name		: $('#configCoolingWaterPointLocationFid').val(),//配置的DDC所属点位信息
					f_seq 		   : pageDivSequence,//所点击的div序号
					f_type_id	   : "2" //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				},
				success : function (result) {
						if (result.code == '0') {
							layer.alert("此点位已配置");
							return;
						} else {
							//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
							//pageDivSequence//取得所点击div的序号
							$.ajax({
								type : "get",
								url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
								dataType : "json",
								data : {
									f_equipment_id 	: f_equipment_id,
									f_seq 			: pageDivSequence,//所点击的div序号
									f_type_id		: '2',   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
									f_desc			: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
									f_formula		: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigformula']").val(),//公式
								},//TODO   将实际的设备id传入
								success : function(result) {
									if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
										$.ajax({
											type: "post",
											url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
											contentType:'application/json;charset=UTF-8',
											data:JSON.stringify({
												f_equipment_id	: f_equipment_id,//配置的设备id
												f_ddc_sys_name	: $('#configCoolingWaterDdcFSysName').val(),//配置的DIV所处DDC的系统名称信息
												f_sys_name		: $('#configCoolingWaterPointLocationFid').val(),//配置的DDC所属点位信息
												f_desc			: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
												f_formula		: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigformula']").val(),//公式
												f_seq			: pageDivSequence,//div序号
												f_type_id		: "2"  //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
											}),
											success: function (result) {
												if(result.code == '0'){//更新配置成功
													layer.close(index);
													index = 0;

                          if (pageDivSequence == 18)
                          {
                              integrationInitHtml(showCurrentOperatingFrequency);//刷新初始化页面
                          }else if (pageDivSequence == 19)
                          {
                              integrationInitHtml(showCurrentPower);//刷新初始化页面
                          }else if (pageDivSequence == 20){
                              integrationInitHtml(showEnergyConsumptionRatio);//刷新初始化页面

                          }else {
                              integrationInitHtml();//刷新初始化页面
                          }

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
												f_equipment_id	: f_equipment_id,//配置的设备id
												f_ddc_sys_name	: $('#configCoolingWaterDdcFSysName').val(),//配置的DIV所处DDC的系统名称信息
												f_sys_name		: $('#configCoolingWaterPointLocationFid').val(),//配置的DDC所属点位信息
												f_desc			: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
												f_formula		: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigformula']").val(),//公式
												//f_seq: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
												f_seq			: pageDivSequence,//div序号
												f_type_id		: "2"
											}),
											success: function (result) {
												if(result.code == '0'){
													layer.msg(result.msg);
													if(index != 0){
														layer.close(index);
														index = 0;
													}
													//如果配置插入成功，数据展示为所配置的信息:  TODO
														if (pageDivSequence == 18)
														{
																integrationInitHtml(showCurrentOperatingFrequency);//刷新初始化页面
														}else if (pageDivSequence == 19)
														{
																integrationInitHtml(showCurrentPower);//刷新初始化页面
														}else if (pageDivSequence == 20){
																integrationInitHtml(showEnergyConsumptionRatio);//刷新初始化页面

														}else {
																integrationInitHtml();//刷新初始化页面
														}
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

		function confirmCommonConfig1(){
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
				dataType : "json",
				data : {
					f_equipment_id : f_equipment_id,
					f_seq 		   : pageDivSequence,//所点击的div序号
					f_type_id	   : "2" //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
				},//TODO   将实际的设备id传入
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id	: f_equipment_id,//配置的设备id
								f_ddc_sys_name	: $("#configCoolingWaterDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name		: $('#configCoolingWaterPointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula      	: $("#integrationCoolingWaterDBClickCommonFormOne input[name='commonConfigformula1']").val(),//公式
								f_seq			: pageDivSequence,//div序号
								f_type_id		: "2" //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {
								if(result.code == '0'){//更新配置成功
									layer.close(index);
									index = 0;

									if (pageDivSequence == 18)
									{
										integrationInitHtml(showCurrentOperatingFrequency);//刷新初始化页面
									}else if (pageDivSequence == 19)
									{
										integrationInitHtml(showCurrentPower);//刷新初始化页面
									}else if (pageDivSequence == 20){
										integrationInitHtml(showEnergyConsumptionRatio);//刷新初始化页面

									}else {
										integrationInitHtml();//刷新初始化页面
									}


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
								f_equipment_id	: f_equipment_id,//配置的设备id
								f_ddc_sys_name	: $("#configCoolingWaterDdcFSysName1").val(),//配置展示DIV的DDC系统名称
								f_sys_name		: $('#configCoolingWaterPointLocationFid1').val(),//配置的DDC所属点位信息
								f_formula      	: $("#integrationCoolingWaterDBClickCommonFormOne input[name='commonConfigformula1']").val(),//公式
								//f_seq: $("#integrationColdWarmWaterDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
								f_seq			: pageDivSequence,//div序号
								f_type_id		: "2" //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息:  TODO
									if (pageDivSequence == 18)
									{
										integrationInitHtml(showCurrentOperatingFrequency);//刷新初始化页面
									}else if (pageDivSequence == 19)
									{
										integrationInitHtml(showCurrentPower);//刷新初始化页面
									}else if (pageDivSequence == 20){
										integrationInitHtml(showEnergyConsumptionRatio);//刷新初始化页面

									}else {
										integrationInitHtml();//刷新初始化页面
									}
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

		/*wanghongjie 添加左侧div的top信息的 保存  */
		function confirmCommonConfigtop(){
			//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
			//pageDivSequence//取得所点击div的序号
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfigtop',
				dataType : "json",
				data : {
					f_equipment_id 	: f_equipment_id,
					f_seq 			: pageDivSequence,//所点击的div序号
					f_type_id		: '2'   //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
				},//TODO   将实际的设备id传入
				success : function(result) {
					if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfigtop',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id	: f_equipment_id,//配置的设备id
								f_desc			: $("#integrationCoolingWaterDBClickCommonFormtop input[name='commonConfigDesctop']").val(),//描述
								f_seq			: pageDivSequence,//div序号
								f_type_id		: "2"  //f_type_id:  1：冷冻水  2：冷却水  3：冷热机组  4：冷却塔  5：电表;
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
					} else if(result.code="2"){//若无数据，执行插入操作
						//layer.msg(result.msg);
						$.ajax({
							type: "post",
							url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfigtop',
							contentType:'application/json;charset=UTF-8',
							data:JSON.stringify({
								f_equipment_id	: f_equipment_id,//配置的设备id
								f_desc			: $("#integrationCoolingWaterDBClickCommonFormtop input[name='commonConfigDesctop']").val(),//描述
								//f_seq: $("#integrationCoolingWaterDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
								f_seq			: pageDivSequence,//div序号
								f_type_id		: "2"
							}),
							success: function (result) {
								if(result.code == '0'){
									layer.msg(result.msg);
									if(index != 0){
										layer.close(index);
										index = 0;
									}
									//如果配置插入成功，数据展示为所配置的信息:  TODO
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

		function loadPointLocations(configFSysName){
        	//configCoolingWaterPointLocationFid  所属ddc下的点位信息
	   		$.ajax({
	   			type : "get",
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
	            dataType : "json",
	            data : {f_sys_name:$('#configCoolingWaterDdcFSysName').val()},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configCoolingWaterPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
	               		});
	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
  	               		if(configFSysName!=null&&configFSysName!=""){
	  	               		$("#integrationCoolingWaterDBClickCommonForm select[name='configCoolingWaterPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
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
		function loadPointLocationsOne(configFSysName){
			//configCoolingWaterPointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configCoolingWaterDdcFSysName1').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configCoolingWaterPointLocationFid1').append(new Option(item.f_nick_name,item.f_sys_name));
						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configFSysName!=null&&configFSysName!=""){
							$("#integrationCoolingWaterDBClickCommonFormOne select[name='configCoolingWaterPointLocationFid1'] option[value="+configFSysName+"]").prop("selected","selected");
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
		// 查询设备信息
		function queryCoolingWaterUnit(obj, callback) {
			if(typeof callback !== 'function'){
				return;
			}
			obj.F_TYPE_ID="2";
			obj = obj || {};


			$.ajax({
				type    : "POST",
				url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/queryColdCooling",
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



		function loadOptions(){
        	//configCoolingWaterDdcFSysName  所属ddc
	   		$.ajax({
	   			type : "get",
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
	            dataType : "json",
	            data : {},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configCoolingWaterDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
							$('#configCoolingWaterDdcFSysName1').append(new Option(item.f_nick_name,item.f_sys_name));
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
        /* End add by yangjx at 20191227 */


        //关闭定时器通用方法，传入定时器名称，监听当页面不活动时，关闭定时器
        function clearAllInterval(intervalNam){
			//判断页面是否活跃，如果不活跃，关闭定时器。judgeActive()此方法为issp.js封装
        	//Start replace by yangjx at 20200108
        	/* //判断页面是否活跃，如果不活跃，关闭定时器。judgeActive()此方法为issp.js封装
        	if(judgeActive("divCoolingWaterDiv")){
        	}else{
	        	clearInterval(intervalNam);
        	} */
        	//remark:↑     replace:↓
        	if(document.getElementById('returnCoolingWaterButton')===null){
        		clearInterval(intervalNam);
        	}
        	//End replace by yangjx at 20200108 for 优化定时器关闭

        }

        //显示当前运行频率的信息
        function showCurrentOperatingFrequency(){
            var dom = document.getElementById("coolingWaterCurrentOperatingFrequencyLine");
            myChart1 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '当前运行频率',
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
                    data:['当前运行频率'],
                    x : '120',
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
                        name: 'Hz',
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
                        name:'当前运行频率',
                        type:'line',
                        smooth:false,
                        data:[]
                    }
                ]
            };
			//先展示 后填充数据
			myChart1.setOption(option_inter);

            var fSysName = $(".coolingWaterPageDivShow18").attr("fSysName");

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

                    var f_sys_name = result.data[0].f_sys_name || '';
                    var f_sys_name_old = result.data[0].f_sys_name_old || '';
                    var f_init_val = result.data[0].f_init_val || '';
                    // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';
                    var f_formula;
                    var initByFourReplace;

                    if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                    {
                        f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                    }

                    PubSub.subscribe(f_sys_name, integration_divCoolingWater.currentOperatingFrequency);


                    if (f_sys_name === fSysName)
                    {
												f_formula = $("#" + (".coolingWaterPageDivShow"+"184").replace(".","")).html();
												if (typeof f_formula != "undefined" && f_formula != "") {
													initByFourReplace = f_formula.replace("$1",f_init_val);

													f_init_val = eval(initByFourReplace);
												}
												f_init_val = Math.round(f_init_val*100)/100;
                        data_inter_out.push(f_init_val);
                    }
                    option_inter.xAxis[0].data.push(axisData);

                    myChart1.setOption(option_inter);

                },

            });
        }

        //显示当前功率
        function showCurrentPower(){
            var dom = document.getElementById("coolingWaterCurrentPowerLine");
            myChart2 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '当前功率',
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
                    data:['当前功率'],
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
                        name:'当前功率',
                        type:'line',
                        smooth:true,
                        data:[]
                    }
                ]
            }
            //先展示 后填充数据
            myChart2.setOption(option_inter);

            var fSysName = $(".coolingWaterPageDivShow19").attr("fSysName");

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

                    var f_sys_name = result.data[0].f_sys_name || '';
                    var f_sys_name_old = result.data[0].f_sys_name_old || '';
                    var f_init_val = result.data[0].f_init_val || '';
                    // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';
										var f_formula;
										var initByFourReplace;

                    if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                    {
                        f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                    }

                    PubSub.subscribe(f_sys_name, integration_divCoolingWater.currentPower);

                    if (f_sys_name === fSysName)
                    {
												f_formula = $("#" + (".coolingWaterPageDivShow"+"194").replace(".","")).html();
												if (typeof f_formula != "undefined" && f_formula != "") {
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


        //显示能耗比
        function showEnergyConsumptionRatio(){
            var dom = document.getElementById("coolingWaterEnergyConsumptionRatioLine");
            myChart4 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '能耗比',
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
                    data:['能耗比'],
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
                        name: '%',
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
                        name:'能耗比',
                        type:'line',
                        smooth:false,
                        data:[]
                    }
                ]
            };
            //先展示 后填充数据
            myChart4.setOption(option_inter);

            var fSysName = $(".coolingWaterPageDivShow20").attr("fSysName");

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

                    var f_sys_name = result.data[0].f_sys_name || '';
                    var f_sys_name_old = result.data[0].f_sys_name_old || '';
                    var f_init_val = result.data[0].f_init_val || '';
                    // var f_engineer_unit = result.data[i - 1].f_engineer_unit || '';
										var f_formula;
										var initByFourReplace;

                    if (pointValueConfig[f_sys_name_old] && pointValueConfig[f_sys_name_old][f_init_val])
                    {
                        f_init_val = pointValueConfig[f_sys_name_old][f_init_val];
                    }

                    PubSub.subscribe(f_sys_name, integration_divCoolingWater.energyConsumption);

                    if (f_sys_name === fSysName)
                    {
												f_formula = $("#" + (".coolingWaterPageDivShow"+"204").replace(".","")).html();
												if (typeof f_formula != "undefined" && f_formula != "") {
													initByFourReplace = f_formula.replace("$1",f_init_val);

													f_init_val = eval(initByFourReplace);
												}
												f_init_val = Math.round(f_init_val*100)/100;
                        data_inter_out.push(f_init_val);
                    }
                    option_inter.xAxis[0].data.push(axisData);

                    myChart4.setOption(option_inter);

                },

            });

        }

        return {
        	closeCommonConfigInfos :function(){
    			closeLayerHost();
    		} ,
			setLampVPoint: function(obj){
				var flagData = obj.attributes.flag.value;


				dataConverter($.extend(true, {}, originalData),function (list) {
					var pointID = flagData;
					var f_init_val = "";
					if(obj.value != "0"){
						f_init_val = "255";
					}else{
						f_init_val = "0";
					}
					$.issp_ajax({
						url : _ctx + "/api/debugPointInfo",
						type : "post",
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						//isShowLoader : true,
						data : JSON.stringify({
							f_sys_name : pointID,
							f_work_mode : "1",//模式“0”自动
							f_init_val : f_init_val,
							tabname : "bes_digit_ouput",
							nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
							f_node_attribution : "1",
						}),
						success : function(result) {
							if(result.status == '1'){
								//swal(result.msg, "操作成功", "success");
								$("#lqshow1_sfk_3,#lqshow1_sfg_3,#lqshow1_sbqd_3,#lqshow1_sbtz_3,#lqshow1_yxzsd_3,#lqshow1_gzzsd_3").val(obj.value);
							}else{
								$("#lqshow1_sfk_3,#lqshow1_sfg_3,#lqshow1_sbqd_3,#lqshow1_sbtz_3,#lqshow1_yxzsd_3,#lqshow1_gzzsd_3" ).val(f_init_val);
								//swal(result.msg, "", "error");
							}
						},
						error : function(result) {
                            layer.msg("失败");
						}
					});
				});

			},
			setWaterPoint: function(obj){
				var flagData = obj.attributes.flag.value;


				dataConverter($.extend(true, {}, originalData),function (list) {
					var pointID = flagData;
					var f_init_val = $("#lqshow1_bpqplsd_3").val();
					$.issp_ajax({
						url : _ctx + "/api/debugPointInfo",
						type : "post",
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						//isShowLoader : true,
						data : JSON.stringify({
							f_sys_name : pointID,
							f_work_mode : "1",//模式“0”自动
							f_init_val : f_init_val,
							tabname : "bes_analog_ouput",
							nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
							f_node_attribution : "1",
						}),
						success : function(result) {
							if(result.status == '1'){
								//swal(result.msg, "操作成功", "success");
								$("lqshow1_bpqplsd_3").val(obj.value);
							}else{
								$("lqshow1_bpqplsd_3" ).val(f_init_val);
								//swal(result.msg, "", "error");
							}
						},
						error : function(result) {
							layer.msg("失败");
						}
					});
				});
			},
            // 冷却水，左边方形实时数据显示
            commonChangeColorCoolingWater(data){

                if (!data)
                {
                    return;
                }

                var f_divClassNameCommon = '.commonChangeColorCoolingWater';
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
										if (!reg.test(f_init_val)) {

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

										if (!reg.test(f_init_val)) {

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
            // 冷却水，右边圆角方形实时数据显示
            coolingWaterPageDivShowCallback(data){
                if (!data){
                    return;
                }

                var f_divClassNameCommon = '.coolingWaterPageDivShow';
                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';
                var f_engineer_unit = data.unit || '';
                var sysNameOld = data.sysNameOld;
								var initByFour;
								var initByFourRe
								var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");

                if (pointValueConfig[f_sys_name] && pointValueConfig[f_sys_name][f_init_val])
                {
                    f_init_val = pointValueConfig[f_sys_name][f_init_val];
                }

                if($(f_divClassNameCommon+"1").attr("fSysName") == f_sys_name){

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
                if($(f_divClassNameCommon+"2").attr("fSysName") == f_sys_name) {

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon + "24").replace(".", "")).html();
											if (initByFour != "") {
												initByFourRe = initByFour.replace("$1", f_init_val);

												f_init_val = eval(initByFourRe);
											}
											f_init_val = Math.round(f_init_val * 100) / 100;
										}

										$(f_divClassNameCommon + "22").text(f_init_val);
										$(f_divClassNameCommon + "23").text(f_engineer_unit);
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
                }

            },
            // 电表参数实时数据显示
            realTimeMeterData(data)
            {
                if (!data){
                    return;
                }


                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';

                switch (f_sys_name)
                {
                    case ammeterConfigParam.instantEnergy:
                        $('.commonChangeColorLighter .instantEnergy').text(f_init_val);
                        break;
                    case ammeterConfigParam.totalEnergy:
                        $('.commonChangeColorLighter .totalEnergy').text(f_init_val);
                        break;
                    case ammeterConfigParam.aPhaseVoltage:
                        $('.commonChangeColorLighter .aPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.bPhaseVoltage:
                        $('.commonChangeColorLighter .bPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.cPhaseVoltage:
                        $('.commonChangeColorLighter .cPhaseVoltage').text(f_init_val);
                        break;
                    case ammeterConfigParam.aPhaseCurrent:
                        $('.commonChangeColorLighter .aPhaseCurrent').text(f_init_val);
                        break;
                    case ammeterConfigParam.bPhaseCurrent:
                        $('.commonChangeColorLighter .bPhaseCurrent').text(f_init_val);
                        break;
                    case ammeterConfigParam.cPhaseCurrent:
                        $('.commonChangeColorLighter .cPhaseCurrent').text(f_init_val);
                        break;
                }
            },
					//对冷却水的变频器运行状态的值做判断,255的时候,图片上的gif图层显示,如果没有配置变频器运行状态或者为0,则隐藏
					F_BPYXZTRealTimeDataShowGIFCallback_coolingWater(data) {
						if (!data){
							return;
						}
						var f_sys_name = data.name || '';
						var f_init_val = data.value || '';

						switch (f_sys_name)
						{
							case coolingWaterWithBPQYXZT.f_bpqyxzt:
								if (f_init_val == "255") {
									$("#divGifDisplayOrHide_coolingWeter").show()
								}else {
									$("#divGifDisplayOrHide_coolingWeter").hide()
								}
								break;

						}
					},

            // 冷却水，左边曲线实时数据显示
            currentOperatingFrequency(data)
            {
                if (!data || !myChart1){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';

                var option_inter = myChart1.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName = $(".coolingWaterPageDivShow18").attr("fSysName");
								var f_formula;
								var initByFourReplace;

                if (!fSysName)
                {
                    return;
                }

                if (f_sys_name == fSysName)
                {
										f_formula = $("#" + (".coolingWaterPageDivShow"+"184").replace(".","")).html();
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
                    myChart1.setOption(option_inter);

                }
            },
            // 冷却水，中间曲线实时数据显示
            currentPower(data)
            {
                if (!data || !myChart2){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';

                var option_inter = myChart2.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName = $(".coolingWaterPageDivShow19").attr("fSysName");
								var f_formula;
								var initByFourReplace;

                if (!fSysName)
                {
                    return;
                }

                if (f_sys_name == fSysName)
                {
										f_formula = $("#" + (".coolingWaterPageDivShow"+"194").replace(".","")).html();
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
            // 冷却水，右边曲线实时数据显示
            energyConsumption (data)
            {
                if (!data || !myChart4){
                    return;
                }

                var f_sys_name = data.name || '';
                var f_init_val = data.value || '';

                var option_inter = myChart4.getOption();
                var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                var fSysName = $(".coolingWaterPageDivShow20").attr("fSysName");
								var f_formula;
								var initByFourReplace;

                if (!fSysName)
                {
                    return;
                }

                if (f_sys_name == fSysName)
                {
										f_formula = $("#" + (".coolingWaterPageDivShow"+"204").replace(".","")).html();
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
                    myChart4.setOption(option_inter);

                }
            },
        }
    })(jQuery, window, document);
</script>
