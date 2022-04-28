/**
 * 设备列表
 */

var EquipmentList = {};

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
    EquipmentList.initTable = function(){
        var device_code = $("input[name=device_code]").val();
        var device_name = $("input[name=device_name]").val();
        var device_type_name = $("input[name=device_type_name]").val();
        var device_status = $("select[name=device_status]").val();
        var device_type_code = $("input[name=device_type_code]").val();

        //todo js执行时css还未渲染是怎么回事？
        $(".content_div").css("height",'100%');
        var height = $(".content_div").outerHeight() - 100;
        // var height = $(".content_div").outerHeight() - $(".layui_content_div .layui-form").outerHeight();

        var obj = {
            field: 'fSbb' //排序字段，对应 cols 设定的各字段名
            ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
        };
        //第一个实例
        table.render({
            elem: '#equipment_list_table'
            // ,width:3000
            ,height: height
            ,method:"post"
            ,url: _ctx + '/equipmentList/queryList' //数据接口
            // ,contentType: 'application/json'
            ,request: {
                //那么请求数据时的参数将会变为：?curr=1&nums=30
                pageName: 'pageNumber' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,where: {
                device_code:device_code
                ,device_name:device_name
                ,device_status:device_status
                ,device_type_code:device_type_code
                ,device_type_name:device_type_name
                ,field: obj.field //排序字段
                ,order: obj.type //排序方式
            } //如果无需传递额外参数，可不加该参数
            ,page: true //开启分页
            ,limit:15
            ,autoSort:false //禁用前端自动排序
            // ,initSort: {
            //     field: 'fSbb' //排序字段，对应 cols 设定的各字段名
            //     ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            // }
            ,initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            ,id:'fId'
            ,cols: [[
                {
                    // checkbox: true
                    type:'checkbox'
                },
                {type: 'numbers', title: '序号',width:80},
                {
                    field: 'fId',
                    title: 'ID'
                    ,hide:true
                },
                {
                    field: 'fSbb',
                    title: '设备编号'
                    ,width:120
                    ,sort:true
                    ,templet:'<div><span title="{{d.fSbb}}">{{d.fSbb || "" }} </span></div>'
                },
                {
                    field: 'fSbmc',
                    title: '设备名称'
                    ,width:130
                    ,sort:true
                    ,templet:'<div><span title="{{d.fSbmc}}"> {{d.fSbmc || "" }} </span></div>'

                },
                {
                    field: 'fBrandid',
                    title: '品牌编号'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fBrandmc',
                    title: '品牌名称'
                    ,width:100
                    ,sort:true
                    // ,templet:'<div><span title="{{d.fBrandmc}}">{{d.fBrandmc}} </span></div>'
                    , templet: function (d) {
                        var value = d.fBrandmc;
                        if(!value && value !== 0){
                            return "";
                        }
                        return '<div><span title="'+value+'">'+value+' </span></div>';
                    }
                },
                {
                    field: 'fXlid',
                    title: '线路id'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fXlmc',
                    title: '线路名称'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fSn',
                    title: '设备标识名称 /设备真实标签名'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fSblxType',
                    title: '设备类型编号'
                    ,width:130
                    ,sort:true
                },
                {
                    field: 'fSblemc',
                    title: '设备类型名称'
                    ,width:130
                    ,sort:true
                },
                {
                    field: 'fSbxhType',
                    title: '设备型号编号'
                    ,width:130
                    ,sort:true
                },
                {
                    field: 'fSbxhmc',
                    title: '设备型号名称'
                    ,width:130
                    ,sort:true
                    ,templet:'<div><span title="{{d.fSbxhmc}}">{{d.fSbxhmc || "" }} </span></div>'
                },
                {
                    field: 'fRkdId',
                    title: '入库单ID /FK()'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fSsck',
                    title: '所属仓库'
                    ,width:130
                    // ,sort:true
                },
                {
                    // room前缀对应-监控室房间base_room-所属监控室base_monitorroom
                    field: 'fLacation',
                    title: '物理位置'
                    ,width:130
                    ,templet:'<div><span title="{{d.fLacation}}">{{d.fLacation || "" }} </span></div>'
                    // , templet: function (d) {
                    //    return d.fLacation;
                    // }
            },
                {
                    field: 'fSydw',
                    title: '使用单位/组织机构表'
                    ,width:130
                },
                {
                    field: 'fJbr',
                    title: '经办人/用户表'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLong',
                    title: '经度'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLat',
                    title: '纬度'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fScs',
                    title: '生产商'
                    ,width:100
                },
                {
                    field: 'fGmrq',
                    title: '购买日期'
                    ,width:130
                },
                {
                    field: 'fBxnx',
                    title: '保修年限'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fUnitPrice',
                    title: '单价'
                    ,width:100
                },
                {
                    //(0:正常 1:报警 2:离线)
                    field: 'fStatus',
                    title: '设备状态'
                    ,width:100
                    ,sort:true
                    , templet: function (d) {
                        var result = "";
                        var value = d.fStatus;
                        // if(!value && value !== 0){
                        //     return "";
                        // }
                        if(value === "0"){
                            result = "正常";
                        }
                        if(value === "1"){
                            result = "报警";
                        }
                        if(value === "2"){
                            result = "离线";
                        }
                        return result;
                        // return '<div><span title="'+value+'">'+value+' </span></div>';
                    }
                },
                {
                    field: 'fLastGxsj',
                    title: '最后更新时间'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLastGxr',
                    title: '最后更新人'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fYwmbh',
                    title: '一维码编号'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fEwmbh',
                    title: '二维码编号'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fAzsj',
                    title: '安装时间'
                    ,width:100
                },
                {
                    field: 'fWhs',
                    title: '维护商'
                    ,width:100
                },
                {
                    field: 'fCkdid',
                    title: '出库单/FK()'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLyjg',
                    title: '领用机构 /组织机构表'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLyr',
                    title: '领用人/用户表'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fZh',
                    title: '桩号'
                    ,hide:true
                },
                {
                    field: 'fGldw',
                    title: '管理单位/组织机构表'
                    ,hide:true
                },
                {
                    field: 'fScrq',
                    title: '生产日期'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fBxdqr',
                    title: '保修到期日'
                    ,width:100
                },
                {
                    field: 'fCzrq',
                    title: '操作日期'
                    ,hide:true
                },
                {
                    field: 'fSynx',
                    title: '使用年限'
                    ,hide:true
                },
                {
                    field: 'fRksj',
                    title: '入库时间'
                    ,width:100
                },
                {
                    // 0不监控 1监控
                    field: 'fSfjk',
                    title: '是否监控'
                    ,width:100
                    ,hide:true
                },
                {
                    field: 'fLastStatus',
                    title: '上一状态'
                    ,hide:true
                },
                {
                    field: 'fUnit',
                    title: '数量单位'
                    ,width:100
                },
                {
                    field: 'fCrdate',
                    title: '创建日期'
                    ,width:120
                    ,sort:true
                    ,hide:false
                },
                {
                    field: 'fChdate',
                    title: '修改日期'
                    ,width:120
                    ,sort:true
                    ,hide:true
                }
                // ,{
                //     title: '操作',
                //     align: 'center',
                //     formatter: function(value, row, index) {
                //         var actions = [];
                //         actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="javascript:void(0)" onclick="$.operate.edit(\'' + row.fId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                //         actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="javascript:void(0)" onclick="$.operate.remove(\'' + row.fId + '\')"><i class="fa fa-remove"></i>删除</a>');
                //         return actions.join('');
                //     }
                // }
                ]]
        });
    }


    /**
     * 监听事件
     */
    EquipmentList.initEvent = function(){

        //监听排序事件
        table.on('sort(equipment_list_table)', function(obj) { //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

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
    EquipmentList.search = function(){
        EquipmentList.initTable();
    }

    /**
     * 重置
     */
    EquipmentList.reset = function(){
        $("input[name=device_code]").val("");
        $("input[name=device_name]").val("");
        $("input[name=device_type_name]").val("");
        $("select[name=device_status]").val("");
        $("input[name=device_type_code]").val("");
        EquipmentList.initTable();
    }

    /**
     * 点击新增
     */
    EquipmentList.add = function(){
        addIndex = layer.open({
            type: 1
            ,title:"新增设备信息"
            ,area:['28vw','45vh']
            ,content: $('.equipment_add_div') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            ,cancel: function(index, layero){
                EquipmentList.closeClearWin();
                return false;
            }
            ,success:function(){

            }
        });
    }


    /**
     * 查询设备信息
     */
    EquipmentList.queryDevice = function(deviceId){
        // var device_id = $("input[name=device_id]").val();
        $.ajax({
            url : _ctx + '/equipmentList/queryEquipment',
            type : "post",
            dataType : "json",
            data : {
                deviceId : deviceId
            },
            success : function(result){
                if(result){
                    $("input[name=device_id]").val(result.fId);
                    $("input[name=device_code_add]").val(result.fSbb);
                    $("input[name=device_name_add]").val(result.fSbmc);
                    $("input[name=device_type_name_add]").val(result.fSblemc);
                    // $("select[name=device_status_add]").val("");
                    $("input[name=device_type_code_add]").val(result.fSblxType);
                }
            },error:function () {
            }
        });
    }

    /**
     * 点击修改
     */
    EquipmentList.edit = function(){
        var selectData =table.checkStatus('fId').data;
        if(selectData.length !== 1){
            layer.msg("请选择一条记录",{icon:0});
            return;
        }
        var deviceId = selectData[0].fId;
        addIndex = layer.open({
            type: 1
            ,title:"修改设备信息"
            ,area:['28vw','45vh']
            ,content: $('.equipment_add_div') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            ,cancel: function(index, layero){
                EquipmentList.closeClearWin();
                return false;
            }
            ,success:function(){
                EquipmentList.queryDevice(deviceId);
            }
        });
    }

    /**
     * 新增/修改--保存
     */
    EquipmentList.addEquipment = function(){
        var device_id = $("input[name=device_id]").val();
        var device_code = $("input[name=device_code_add]").val();
        var device_name = $("input[name=device_name_add]").val();
        var device_type_name = $("input[name=device_type_name_add]").val();
        // var device_status = $("select[name=device_status_add]").val();
        var device_type_code = $("input[name=device_type_code_add]").val();


        $.ajax({
            url : _ctx + '/equipmentList/addOrEditEquipment',
            type : "post",
            data : JSON.stringify({
                device_id:device_id
                ,device_code:device_code
                ,device_name:device_name
                ,device_type_name:device_type_name
                ,device_type_code:device_type_code
            }),
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            success : function(result){
                if(result && result.status === "1"){
                    layer.msg("保存成功",{icon:1});
                    EquipmentList.closeClearWin();
                    EquipmentList.search();
                }else{
                    layer.msg("保存失败",{icon:2});
                }
            },error:function () {
                layer.msg("保存失败",{icon:2});
            }
        });

    }

    EquipmentList.delete =function(){
        var selectData =table.checkStatus('fId').data;
        if(selectData.length === 0){
            layer.msg("请至少选择一条记录",{icon:0});
            return;
        }
        var idArray = [];
        for(var i = 0; i < selectData.length; i++){
            idArray.push(selectData[i].fId);
        }

        $.ajax({
            url     : _ctx + '/equipmentList/deleteManyEquipment',
            type    : "post",
            data: JSON.stringify(idArray),
            async: false,
            contentType: 'application/json;charset=UTF-8',
            success : function(result) {
                if(result && result.status === "1"){
                    layer.msg("删除成功",{icon:1});
                    EquipmentList.search();
                }else{
                    layer.msg("删除失败",{icon:2});
                }
            },
            error:function(){
                layer.msg("删除失败",{icon:2});
            }
        });
    }

    /**
     * 关闭并清空弹窗
     */
    EquipmentList.closeClearWin = function(){
        $("input[name=device_id]").val("");
        $("input[name=device_code_add]").val("");
        $("input[name=device_name_add]").val("");
        $("input[name=device_type_name_add]").val("");
        // $("select[name=device_status_add]").val("");
        $("input[name=device_type_code_add]").val("");

        layer.close(addIndex);
    }

    /**
     * 点击--显示更多
     */
    EquipmentList.showMore = function(){
        $(".moreQueryDiv").show();
        $("#showMore").hide();
        $("#hideMore").show();
    }

    /**
     * 点击--收起
     */
    EquipmentList.hideMore = function(){
        $(".moreQueryDiv").hide();
        $("#showMore").show();
        $("#hideMore").hide();
    }


    $(function(){
        EquipmentList.initTable();
        EquipmentList.initEvent();
        form.render();
    });
});