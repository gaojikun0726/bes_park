<#--新增表格弹窗-->
<div id="design_add_table_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">表格名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_table_name" placeholder="请输入表格名称" autocomplete="off" class="layui-input" maxlength="50">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">行数</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_table_rows_num" placeholder="请输入表格行数" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="2">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">列数</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_table_columns_num" placeholder="请输入表格列数" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="2">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">列宽</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_table_columns_width" placeholder="请输入表格列宽" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="200">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">行高</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_table_rows_width" placeholder="请输入表格行高" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="30">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddTable.addConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddTable.closeAddWin()">取消</button>
        </div>
    </div>
</div>

<#--修改表格弹窗-->
<div id="design_edit_table_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">表格名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_table_name" placeholder="请输入表格名称" autocomplete="off" class="layui-input" maxlength="50">
            </div>
        </div>
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">行数</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_edit_table_rows_num" placeholder="请输入表格行数" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="2">-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">列数</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_edit_table_columns_num" placeholder="请输入表格列数" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="2">-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">列宽</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_edit_table_columns_width" placeholder="请输入表格列宽" lay-verify="number" autocomplete="off" class="layui-input" maxlength="50" value="200">-->
            <#--</div>-->
        <#--</div>-->
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddTable.editConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddTable.closeEditWin()">取消</button>
        </div>
    </div>
</div>




