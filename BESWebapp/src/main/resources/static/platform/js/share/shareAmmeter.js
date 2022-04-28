/**
 * 公摊仪表
 */

var ShareAmmeter = {
    //仪表类型json数据
    meterTypeMap:{}
};

layui.use(['layer','form','jquery','table','element'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var element = layui.element;

    //弹窗
    var addIndex;

    /**
     * 加载表格数据
     */
    ShareAmmeter.initTable = function(){
        var meter_name = $("input[name=share_ammeter_name]").val();
        var meter_type = $("select[name=share_ammeter_type]").val();
        var meter_area = $("select[name=share_meter_area]").val();

        //todo js执行时css还未渲染是怎么回事？
        $(".share_ammeter_content_div").css("height",'100%');
        var height = $(".share_ammeter_content_div").outerHeight() - 100;
        // var height = $(".share_ammeter_content_div").outerHeight() - $(".layui_content_div .layui-form").outerHeight();

        // var obj = {
        //     field: 'fSbb' //排序字段，对应 cols 设定的各字段名
        //     ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
        // };
        //第一个实例
        table.render({
            elem: '#share_ammeter_table'
            ,height: height
            ,method:"post"
            ,url: _ctx + '/platform/share/queryList' //数据接口
            // ,contentType: 'application/json'
            ,request: {
                //那么请求数据时的参数将会变为：?curr=1&nums=30
                pageName: 'pageNumber' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,where: {
                meter_name:meter_name
                ,meter_type:meter_type
                ,meter_area:meter_area
                // ,field: obj.field //排序字段
                // ,order: obj.type //排序方式
            } //如果无需传递额外参数，可不加该参数
            ,page: true //开启分页
            ,limit:15
            ,autoSort:false //禁用前端自动排序
            // ,initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            ,id:'F_GUID'
            ,cols: [[
                {
                    type:'checkbox'
                },
                {type: 'numbers', title: '序号',width:80},
                {
                    field: 'F_GUID',
                    title: 'ID'
                    ,hide:true
                },
                {
                    field: 'F_NICK_NAME',
                    title: '仪表名称'
                    ,width:120
                    // ,sort:true
                    ,templet:'<div><span title="{{d.F_NICK_NAME}}">{{d.F_NICK_NAME || "" }} </span></div>'
                },
                {
                    field: 'F_SSQY',
                    title: '所属区域'
                    ,width:130
                    // ,sort:true
                    ,templet:'<div><span title="{{d.F_SSQY}}"> {{d.F_SSQY || "" }} </span></div>'

                },
                {
                    field: 'F_AZWZ',
                    title: '安装位置'
                    // ,width:100
                },

                {
                    field: 'F_BLXBH',
                    title: '仪表类型'
                    // ,width:100
                    // ,hide:true
                    , templet: function (d) {
                        var value = d.F_BLXBH;
                        if(!value && value !== 0){
                            return "";
                        }
                        var typeMap = ShareAmmeter.meterTypeMap;
                        var typeName = typeMap[value];
                        if(!typeName){
                            return "";
                        }
                        return '<div><span title="'+typeName+'">'+typeName+' </span></div>';
                    }
                },
                {
                    field: 'F_DESCRIPTION',
                    title: '描述'
                    , templet: function (d) {
                        var value = d.f_description;
                        if(!value && value !== 0){
                            return "";
                        }
                        return '<div><span title="'+value+'">'+value+' </span></div>';
                    }
                },{
                    filed:"SHARE_METER",
                    title:"是否是公摊仪表"
                    , templet: function (d) {
                        var value = d.SHARE_METER;
                        if(value === "1"){
                            return "是"
                            // return '<input type="checkbox" name="'+d.F_GUID+'" lay-skin="switch" lay-text="是|否" checked>'
                        }else{
                            // return '<input type="checkbox" name="'+d.F_GUID+'" lay-skin="switch" lay-text="是|否">'
                            return "否"
                        }
                    }
                }
                ]]
        });
    }


    /**
     * 监听事件
     */
    ShareAmmeter.initEvent = function(){

        //监听排序事件
        table.on('sort(share_ammeter_table)', function(obj) { //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

            //尽管我们的 table 自带排序功能，但并没有请求服务端。
            //有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
            table.reload('fId', {
                initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                , where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                    field: obj.field //排序字段
                    , order: obj.type //排序方式
                }
            });
        });
    }


    /**
     * 查询
     */
    ShareAmmeter.search = function(){
        ShareAmmeter.initTable();
    }

    /**
     * 重置
     */
    ShareAmmeter.reset = function(){
        $("input[name=share_ammeter_name]").val("");
        $("select[name=share_ammeter_type]").val("");
        $("select[name=share_meter_area]").val("");

        ShareAmmeter.initTable();
    }


    /**
     * 配置为公摊仪表
     */
    ShareAmmeter.shareConfig = function(){
        var selectData =table.checkStatus('F_GUID').data;
        if(selectData.length === 0){
            layer.msg("请至少选择一条记录",{icon:0});
            return;
        }
        var idArray = [];
        for(var i = 0; i < selectData.length; i++){
            idArray.push(selectData[i].F_GUID);
        }
        $.ajax({
            url     : _ctx + '/platform/share/queryMeterState',
            type    : "post",
            dataType : "json",
            data: {
                meterIds:JSON.stringify(idArray),
                meterState:'0'
            },
            // contentType: 'application/json;charset=UTF-8',
            success : function(result) {
                if(result && result.status === "1"){
                    ShareAmmeter.executeShareConfig(idArray);
                }else if(result && result.num){
                    layer.msg("选中数据中存在"+result.num+"条公摊仪表数据，请重新选择",{icon:2});
                }else{
                    layer.msg("选中数据不符合要求，请重新选择",{icon:2});
                }
            },
            error:function(){
                layer.msg("配置失败",{icon:2});
            }
        });


    }

    /**
     * 执行配置为公摊仪表
     */
    ShareAmmeter.executeShareConfig = function(idArray){
        layer.confirm('确定配置为公摊仪表吗?', {title:'配置提示'}, function(index){
            $.ajax({
                url     : _ctx + '/platform/share/shareMeterConfig',
                type    : "post",
                data: JSON.stringify(idArray),
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.status === "1"){
                        layer.msg("配置成功",{icon:1});
                        ShareAmmeter.search();
                    }else if(result && result.msg){
                        layer.msg(result.msg,{icon:2});
                    }else{
                        layer.msg("配置失败",{icon:2});
                    }
                },
                error:function(){
                    layer.msg("配置失败",{icon:2});
                }
            });
            layer.close(index);
        });
    }

    /**
     * 配置为普通仪表
     */
    ShareAmmeter.plainConfig = function(){
        var selectData =table.checkStatus('F_GUID').data;
        if(selectData.length === 0){
            layer.msg("请至少选择一条记录",{icon:0});
            return;
        }
        var idArray = [];
        for(var i = 0; i < selectData.length; i++){
            idArray.push(selectData[i].F_GUID);
        }
        $.ajax({
            url     : _ctx + '/platform/share/queryMeterState',
            type    : "post",
            dataType : "json",
            data: {
                meterIds:JSON.stringify(idArray),
                meterState:'1'
            },
            // contentType: 'application/json;charset=UTF-8',
            success : function(result) {
                if(result && result.status === "1"){
                    ShareAmmeter.executePlainConfig(idArray);
                }else if(result && result.num){
                    layer.msg("选中数据中存在"+result.num+"条普通仪表数据，请重新选择",{icon:2});
                }else{
                    layer.msg("选中数据不符合要求，请重新选择",{icon:2});
                }
            },
            error:function(){
                layer.msg("配置失败",{icon:2});
            }
        });



    }

    /**
     * 执行配置为普通仪表
     */
    ShareAmmeter.executePlainConfig = function(idArray){
        layer.confirm('确定配置为普通仪表吗?', {title:'配置提示'}, function(index){
            $.ajax({
                url     : _ctx + '/platform/share/plainMeterConfig',
                type    : "post",
                data: JSON.stringify(idArray),
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.status === "1"){
                        layer.msg("配置成功",{icon:1});
                        ShareAmmeter.search();
                    }else if(result && result.msg){
                        layer.msg(result.msg,{icon:2});
                    }else{
                        layer.msg("配置失败",{icon:2});
                    }
                },
                error:function(){
                    layer.msg("配置失败",{icon:2});
                }
            });
            layer.close(index);
        });
    }

    /**
     * 查询电表类型数据
     */
    ShareAmmeter.getAmmeterTypeData = function(){
        $("select[name='share_ammeter_type']").empty();
        $.ajax({
            url : _ctx + '/platform/share/queryAmmeterType',
            type : "post",
            dataType : "json",
            success : function(result){
                var content = "<option value=''>全部</option>";
                if(result && result.list && result.list.length > 0){
                    ShareAmmeter.meterTypeMap = result.map;
                    var list = result.list;
                    for(var i = 0; i < list.length; i++){
                        content += '<option value="'+list[i].value+'">'+list[i].name+'</option>';
                    }
                }
                $("select[name='share_ammeter_type']").html(content);
                form.render();
            },error:function () {
            }
        });
    }




    $(function(){
        ShareAmmeter.getAmmeterTypeData();
        ShareAmmeter.initTable();
        ShareAmmeter.initEvent();
        form.render();
    });
});