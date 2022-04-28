
<div class="Information_area"
	 style="height: calc(100% - 0px) !important; background: url('static/images/zmjk/rgzn_1_7_F.png'); position: relative;background-size: 100% 100%; background-repeat: no-repeat;">
	<!-- 左上按钮区域 -->
	<div class="zm-left-top">
		<ul style="height: 10%;">
			<li></li>
			<li>
				<select id="mainswitch1FBTD" control="IP1MT201,IP1MT300,IP1MT3502" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointListSel('IP1MT201,IP1MT300,IP1MT3502',this)"
						style="margin-left: 70vh; margin-top: 55vh;">
					<option value="0">北厅东-关</option>
					<option value="2">北厅东-低</option>
					<option value="5">北厅东-中</option>
					<option value="8">北厅东-高</option>
					<option value="10">北厅东-最高</option>
				</select>
				<select id="mainswitch1FBTX" control="IP1MT301,IP1MT301,IP1MT3500" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointListSel('IP1MT301,IP1MT301,IP1MT3500', this)"
						style="margin-left: 50vh; margin-top: 55vh;">
					<option value="0">北厅西-关</option>
					<option value="2">北厅西-低</option>
					<option value="5">北厅西-中</option>
					<option value="8">北厅西-高</option>
					<option value="10">北厅西-最高</option>
				</select>
				<button id="IP1MT200" control="IP1MT200" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 50vh; margin-top: 60vh;">北走廊</button>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="mainswitch1FMTDD" control="IP1M8103,IP1M4301" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPointList('IP1M8103,IP1M4301',this)"
						style="margin-left: 140vh; margin-top: 60vh;">门厅灯带</button>
				<button id="IP1M8104" control="IP1M8104" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 150vh; margin-top: 60vh;">门厅顶灯</button>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="IP1M8101" control="IP1M8101" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 92vh; margin-top: 70vh;">中厅西北灯</button>
				<button id="IP1M8102" control="IP1M8102" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 105vh; margin-top: 65vh;">中厅东灯</button>
				<button id="IP1M8107" control="IP1M8107" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 105vh; margin-top: 70vh;">中厅西南灯</button>
				<select id="IP1MT3601" control="IP1MT3601" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointSel(this)"
						style="margin-left: 150vh; margin-top: 41vh;">
					<option value="0">服务台-关</option>
					<option value="2">服务台-低</option>
					<option value="5">服务台-中</option>
					<option value="8">服务台-高</option>
					<option value="10">服务台-最高</option>
				</select>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="IP1M4201" control="IP1M4201" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 120vh; margin-top: 30vh;">南厅射灯</button>
				<select id="mainswitch1FNTX" control="IP1MT3600,IP1MT3701" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointListSel('IP1MT3600,IP1MT3701', this)"
						style="margin-left: 130vh; margin-top: 30vh;">
					<option value="0">南厅西-关</option>
					<option value="2">南厅西-低</option>
					<option value="5">南厅西-中</option>
					<option value="8">南厅西-高</option>
					<option value="10">南厅西-最高</option>
				</select>
				<select id="mainswitch1FNTD" control="IP1MT3700,IP1MT3702" type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointListSel('IP1MT3700,IP1MT3702', this)"
						style="margin-left: 130vh; margin-top: 35vh;">
					<option value="0">南厅东-关</option>
					<option value="2">南厅东-低</option>
					<option value="5">南厅东-中</option>
					<option value="8">南厅东-高</option>
					<option value="10">南厅东-最高</option>
				</select>
			</li>

		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="mainswitch1FQDD" control="IP1M8100,IP1M8106,IP1M4300" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPointList('IP1M8100,IP1M8106,IP1M4300',this)"
						style="margin-left: 60vh; margin-top: 60vh;">墙带灯</button>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="mainswitch1FDT" control="IP1M4200,IP1M4303" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPointList('IP1M4200,IP1M4303',this)"
						style="margin-left: 95vh; margin-top: 43vh;">电梯间</button>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="IP1M4203" control="IP1M4203" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 110vh; margin-top: 35vh;">洗刷间</button>
			</li>
		</ul>
		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="IP1M8105" control="IP1M8105" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPoint(this)"
						style="margin-left: 18vh; margin-top: 70vh;">西北屋</button>
			</li>
		</ul>

		<ul style="height: 10%;">
			<li></li>
			<li>
				<button id="mainswitch1FZKKG" control="IP1M8103,IP1M4301,IP1M8104,IP1M8101,IP1M8102,IP1M8107,IP1M4201,IP1M8100,IP1M8106,IP1M4300,
					IP1M4200,IP1M4303,IP1M4203,IP1M8105" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPointList('IP1M8103,IP1M4301,IP1M8104,IP1M8101,IP1M8102,IP1M8107,IP1M4201,IP1M8100,IP1M8106,IP1M4300,IP1M4200,IP1M4303,IP1M4203,IP1M8105',this)"
						style="margin-left: 35vh; margin-top: 40vh;">开关总控</button>
				<#--<button id="mainswitch1FZKTG" control="IP4M8100,IP4M8101,IP4M8102,IP4M12200,IP4M12201,IP4M12202,IP4M12203,IP4M12204,IP4M12205,IP4M12206
						,IP4M12207,IP4M12208,IP4M12209,IP4M122010,IP4M122011" type="button" value="0" class="zm-switch-button switch-button online"
						onclick="dataAnalysis_zmjk.setLampPointList('IP4M8100,IP4M8101,IP4M8102,IP4M12200,IP4M12201,IP4M12202,IP4M12203,IP4M12204,IP4M12205,IP4M12206,IP4M12207,IP4M12208,IP4M12209,IP4M122010,IP4M122011',this)"
						style="">调光总控</button>-->
				<select id="mainswitch1FZKTG" control="IP1MT200,IP1MT201,IP1MT300,IP1MT301,IP1MT302,IP1MT3500,IP1MT3502,IP1MT3600,IP1MT3601,IP1MT3602,IP1MT3700,IP1MT3701,IP1MT3702"
						type="button" value="0" class="zm-switch-button switch-button online"
						onchange="dataAnalysis_zmjk.setLampPointListSel('IP1MT200,IP1MT201,IP1MT300,IP1MT301,IP1MT302,IP1MT3500,IP1MT3502,IP1MT3600,IP1MT3601,IP1MT3602,IP1MT3700,IP1MT3701,IP1MT3702', this)"
						style="margin-left: 50vh; margin-top: 40vh;">
					<option value="0">调光总控-关</option>
					<option value="2">调光总控-低</option>
					<option value="5">调光总控-中</option>
					<option value="8">调光总控-高</option>
					<option value="10">调光总控-最高</option>
				</select>
			</li>
		</ul>

	</div>
</div>
