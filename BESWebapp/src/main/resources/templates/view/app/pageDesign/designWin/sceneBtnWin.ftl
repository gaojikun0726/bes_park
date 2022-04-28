<#--新增按钮弹窗-->
<div id="design_add_scene_btn_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">按钮名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_scene_btn_name" placeholder="请输入按钮名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_scene_btn_width" value="50" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_scene_btn_height" value="26" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="SceneBtn.addBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="SceneBtn.closeAddWin()">取消</button>
        </div>
    </div>
</div>
<#--修改按钮弹窗-->
<div id="design_edit_scene_btn_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">按钮名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_scene_btn_name" placeholder="请输入按钮名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_scene_btn_width" value="50" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_scene_btn_height" value="26" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="SceneBtn.editBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="SceneBtn.closeEditWin()">取消</button>
        </div>
    </div>
</div>

<#--按钮关联点弹窗-->
<div id="design_scene_btn_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_scene_btn_relative_id">
                <input type="text" id="design_scene_btn_relative_name" name="design_scene_btn_relative_name" readonly placeholder="" autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="SceneBtn.choosePoint()">选择点</button>
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="SceneBtn.removePoint()">删除</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">关联配置</label>
            <table>
                <thead>
                    <tr>
                        <td>点名称</td>
                        <td>状态</td>
                    </tr>
                </thead>
               <tbody id = "inputTable">

               </tbody>

            </table>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="SceneBtn.relativeBtnClick()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="SceneBtn.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>