<#--新增按钮弹窗-->
<div id="design_addEnergyEfficiencyWin" style="display: none" class="designWin">
	<div class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">按钮名称</label>
			<div class="layui-input-block">
				<input type="text" id="design_add_energyEfficiency_btn_name" placeholder="请输入按钮名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">按钮宽度</label>
			<div class="layui-input-block">
				<input type="text" id="design_add_energyEfficiency_btn_width" value="50" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">按钮高度</label>
			<div class="layui-input-block">
				<input type="text" id="design_add_energyEfficiency_btn_height" value="26" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="design_win_btn_div">
			<button class="btn btn-md btn-primary" type="button" onclick="AddEnergyEfficiency.addBtn()"><strong>确定</strong></button>
			<button class="btn btn-white m-l-sm" type="button" onclick="AddEnergyEfficiency.closeAddWin()">取消</button>
		</div>
	</div>
</div>
<#--修改按钮弹窗-->
<div id="design_edit_energyEfficiency_btn_win" style="display: none" class="designWin">
	<div class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">按钮名称</label>
			<div class="layui-input-block">
				<input type="text" id="design_edit_energyEfficiency_btn_name" placeholder="请输入按钮名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">按钮宽度</label>
			<div class="layui-input-block">
				<input type="text" id="design_edit_energyEfficiency_btn_width" value="50" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">按钮高度</label>
			<div class="layui-input-block">
				<input type="text" id="design_edit_energyEfficiency_btn_height" value="26" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="design_win_btn_div">
			<button class="btn btn-md btn-primary" type="button" onclick="AddEnergyEfficiency.editBtn()"><strong>确定</strong></button>
			<button class="btn btn-white m-l-sm" type="button" onclick="AddEnergyEfficiency.closeEditWin()">取消</button>
		</div>
	</div>
                                       </div>

<#--按钮关联点弹窗-->
<div id="design_energyEfficiency_btn_relative" style="display: none" class="design_relative_win">
	<div class="layui-form" style = "width:100%">
		<div class="layui-form-item">
			<label class="layui-form-label">点名称</label>
			<div class="layui-input-block">
				<input type="hidden" id="design_energyEfficiency_btn_relative_id">
				<input type="text" id="design_energyEfficiency_btn_relative_name" placeholder="请输入点名称" readonly="readonly" style = "background:#cccccc" name="design_energyEfficiency_btn_relative_name"  placeholder="" autocomplete="off" class="layui-input relativePointName">
				<button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="AddEnergyEfficiency.choosePoint()">选择点</button>
				<button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="AddEnergyEfficiency.removePoint()">删除</button>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">关联配置</label>
			<table id = "inputPointTable">

			</table>
		</div>
		<label class="layui-form-label">计算公式</label>
		<input type="text" id="expression"  placeholder="请输入计算公式,以$代表参数  例:$1+$2+$3" autocomplete="off" class="layui-input" style = "width: 230px; margin-left: 86px;">
		<div class="design_win_btn_area">
			<button class="btn btn-md btn-primary" type="button" id = "btnRelative" style = "display:inline;margin-top: 30px;" onclick="AddEnergyEfficiency.relativeBtnClick()"><strong>关联</strong></button>
			<button class="btn btn-white m-l-sm" type="button" id = "btnCancel" style = "display:inline;margin-top: 30px;" onclick="AddEnergyEfficiency.closeRelativeWin()">取消</button>
			<button class="btn btn-md btn-primary" type="button" id = "pieRelative" style = "display:inline;margin-top: 30px;" onclick="EnergyEfficiencyEvent.relativeBtnClick()"><strong>关联</strong></button>
			<button class="btn btn-white m-l-sm" type="button" id = "pieCancel" style = "display:inline;margin-top: 30px;" onclick="EnergyEfficiencyEvent.closeRelativeWin()">取消</button>
		</div>
	</div>
</div>

<style>
	.design_win_btn_area button{
		text-align: center;
		display: block;
		width: 4.5vw;
	}
</style>



