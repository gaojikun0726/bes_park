/**
 * 字典数据公共方法
 */
var DictCommon = {};

layui.use(['layer','form','jquery','table','element'], function() {
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var element = layui.element;

    /**
     * 按照类型获取字典数据
     * @param type 字典类型
     * @param callback 回调
     */
    DictCommon.getDictByType = function (type, callback) {
        $.ajax({
            url: _ctx + '/view/platform/dictInfo/getDictByType',
            type: "post",
            // dataType : "json",
            data: {
                type: type
            },
            success: function (result) {
                // if(result && result.list){
                callback(result);
                // }
            }, error: function () {
            }
        });
    };


    /**
     * 加载查询下拉框：包含全部
     * @param selector 下拉框选择器
     * @param dictList 字典数据
     */
    DictCommon.initQuerySelect = function (selector, dictList) {
        $(selector).empty();
        var content = "<option value=''>全部</option>";
        if (dictList && dictList.length > 0) {
            for (var i = 0; i < dictList.length; i++) {
                content += '<option value="' + dictList[i].value + '">' + dictList[i].name + '</option>';
            }
        }
        $(selector).html(content);
        form.render();
    };


    /**
     * 加载普通下拉框
     * @param selector 下拉框选择器
     * @param dictList 字典数据
     */
    DictCommon.initCommonSelect = function (selector, dictList) {
        $(selector).empty();
        var content = "<option value=''>请选择</option>";
        if (dictList && dictList.length > 0) {
            for (var i = 0; i < dictList.length; i++) {
                content += '<option value="' + dictList[i].value + '">' + dictList[i].name + '</option>';
            }
        }
        $(selector).html(content);
        form.render();
    };


});
