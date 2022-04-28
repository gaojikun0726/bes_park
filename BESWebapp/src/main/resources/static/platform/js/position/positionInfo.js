/**
 * 区域管理
 */
var PositionInfo = {
    $tree:$("#positionInfoTree"),
    //区域类型的json格式数据
    positionTypeMap:{},
    //打开电表关联配置时的房间id
    positionId:"",
    //树点击选中的节点id
    parentId:""
};

layui.use(['layer','form','jquery','transfer'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    var transfer = layui.transfer;

    //新增弹窗index
    var addIndex;

    /**
     * 加载树
     */
    PositionInfo.initTree = function(){
        this.$tree.jstree({
            core: {
                check_callback: true,
                data: function (obj, callback) {
                    $.ajax({
                        url: _ctx+'/platform/position/queryPositionTree',
                        type: 'post',
                        dataType: 'json',
                        async:false,
                        success:function (data) {
                            callback.call(this, data);
                        },
                        error:function () {

                        }
                    });

                },
                themes: {
                    // "stripes": true,
                    "dots":false,
                    "ellipsis":true
                },
            },
            // search : DesignArea.treeSearch(),
            // contextmenu:{
            //     select_node:true,
            //     show_at_node:true,
            //     'items' : PositionInfo.contextmenu
            // },
            plugins: ['types','search'],
            // plugins: ['types'],
            types: {
                "floor":{'icon':_ctx+"/static/pageDesign/icon/tree/house_16.png"},
                // "root":{'icon':"/manage/images/jstree/root3.png"},
                // "leaf": {'icon': "/manage/images/jstree/leaf2.png"},
                // "root":{"icon": "fa fa-folder icon-state-success"}
            }
        });
        this.$tree.bind('activate_node.jstree', function (obj, e) {
            //点击选中树节点关联查询表格数据
            if(e.node.parent === "#"){
                PositionInfo.parentId = "";
            }else{
                PositionInfo.parentId = e.node.id;
            }

            PositionInfo.initTable();
        });

        // 所有节点都加载完后
        this.$tree.on("ready.jstree", function (event, data) {
            // data.instance.open_all();
            // var obj = data.instance.get_node(event.target.firstChild.firstChild.lastChild);
            // data.instance.select_node(obj);

            // var rootID = data.instance.get_node(event.target.firstChild.firstChild.lastChild).id;
            data.instance.open_node(1); // 展开root节点
            // data.instance.clear_state(); // <<< 这句清除jstree保存的选中状态
            // $tree.jstree("deselect_all", true);
        });
    };

    /**
     * 刷新树
     */
    PositionInfo.refreshTree = function(){
        this.$tree.jstree(true).refresh();
    }

    /**
     * 加载表格数据
     */
    PositionInfo.initTable = function(){
        var position_code = $("input[name=position_code]").val();
        var position_name = $("input[name=position_name]").val();
        var position_type = $("select[name=position_type]").val();

        //todo js执行时css还未渲染是怎么回事？
        $(".postion_content_div").css("height",'100%');
        var height = $(".postion_content_div").outerHeight() - 100;

        var obj = {
            //field中的字段名必须是存在于cols中
            field: 'sort' //排序字段，对应 cols 设定的各字段名
            ,type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
        };
        //第一个实例
        table.render({
            elem: '#ammeter_position_table'
            ,height: height
            ,method:"post"
            ,url: _ctx + '/platform/position/queryList' //数据接口
            ,request: {
                //那么请求数据时的参数将会变为：?curr=1&nums=30
                pageName: 'pageNumber' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,where: {
                parent_id:PositionInfo.parentId
                ,position_code:position_code
                ,position_name:position_name
                ,position_type:position_type
                ,field: obj.field //排序字段
                ,order: obj.type //排序方式
            } //如果无需传递额外参数，可不加该参数
            ,page: true //开启分页
            ,limit:10
            ,autoSort:false //禁用前端自动排序
            // ,initSort: {
            //     field: 'fSbb' //排序字段，对应 cols 设定的各字段名
            //     ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            // }
            ,initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            ,id:'id'
            ,cols: [[
                // {
                //     type:'checkbox'
                // },
                {type: 'numbers', title: '序号',width:80},
                {
                    field: 'id'
                    ,hide:true
                },
                //字段暂停使用
                // {
                //     field: 'code',
                //     title: '区域编号'
                //     // ,width:120
                //     ,sort:true
                //     ,templet:'<div><span title="{{d.code}}">{{d.code || "" }} </span></div>'
                // },
                {
                    field: 'name',
                    title: '区域名称'
                    // ,width:130
                    ,sort:true
                    ,templet:'<div><span title="{{d.name}}"> {{d.name || "" }} </span></div>'

                },
                {
                    field: 'address_desc',
                    title: '地址描述'
                    // ,width:100
                },
                {
                    field: 'max_floor',
                    title: '最高层数'
                    // ,width:100
                    // ,sort:true
                    // ,templet:'<div><span title="{{d.fBrandmc}}">{{d.fBrandmc}} </span></div>'
                    , templet: function (d) {
                        var value = d.max_floor;
                        if(!value && value !== 0){
                            return "";
                        }
                        return '<div><span title="'+value+'">'+value+' </span></div>';
                    }
                },
                {
                    field: 'floor_num',
                    title: '层数'
                    // ,width:100
                    // ,hide:true
                },
                {
                    field: 'type',
                    title: '区域类型'
                    // ,width:100
                    // ,hide:true
                    , templet: function (d) {
                        var value = d.type;
                        if(!value && value !== 0){
                            return "";
                        }
                        var typeMap = PositionInfo.positionTypeMap;
                        var typeName = typeMap[value];
                        if(!typeName){
                            return "";
                        }
                        return '<div><span title="'+typeName+'">'+typeName+' </span></div>';
                    }
                },
                {
                    field: 'area_uses',
                    title: '区域用途'
                    // ,width:100
                    // ,hide:true
                },
                {
                    field: 'usable_area',
                    title: '使用面积'
                    // ,width:130
                    // ,sort:true
                },
                {
                    field: 'built_area',
                    title: '建筑面积'
                    // ,width:130
                    // ,sort:true
                },
                {
                    field: 'pool_area',
                    title: '公摊面积'
                    // ,width:130
                    // ,sort:true
                }
                ,{
                    field: 'sort',
                    title: '排序'
                    // ,width:130
                    ,hide:true
                }

                , {
                    // fixed: 'right',
                    field: "operation",
                    title: '操作'
                    , width: 120
                    , templet: function (d) {
                        return "<div class='btn-group '>"
                            + "<button class='btn btn-white btn-sm include' data-id='"+d.id+"' data-toggle='modal' data-target='#position_ammeter_config'><i class='fa fa-user'></i> 电表配置</button>"
                            // + "<button class='btn btn-white btn-sm include' onclick='PositionInfo.openConfigDiv("+d.id+")'><i class='fa fa-user'></i> 电表配置</button>"
                            + "</div>"
                    }
                }
            ]]
        });
    }

    /**
     * 监听事件
     */
    PositionInfo.initEvent = function(){

        //监听排序事件
        table.on('sort(ammeter_position_table)', function(obj) { //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

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


        $('#position_ammeter_config').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            PositionInfo.positionId = button.data("id");			//获取用户组编号
            // $(this).draggable({
            //     handle: ".modal-header"     	// 只能点击头部拖动
            // });
            // $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
            // 模态框打开时触发事件
            PositionInfo.initContainTable();
            PositionInfo.initRemainTable();
            PositionInfo.queryContainAmmeter();
            PositionInfo.queryRemainAmmeter();
        }).on('hide.bs.modal', function () {
            // 模态框隐藏时触发事件
            PositionInfo.positionId = "";
            $("#remainAmmeterKeywords").val("");
            $("#containAmmeterKeywords").val("");
        })
    }

    /**
     * 查询区域类型数据
     */
    PositionInfo.getPositionTypeData = function(){
        $("select[name='position_type']").empty();
        $.ajax({
            url : _ctx + '/platform/position/queryPositionType',
            type : "post",
            dataType : "json",
            success : function(result){
                var content = "<option value=''>全部</option>";
               if(result && result.list && result.list.length > 0){
                   PositionInfo.positionTypeMap = result.map;
                   var list = result.list;
                   for(var i = 0; i < list.length; i++){
                       content += '<option value="'+list[i].value+'">'+list[i].name+'</option>';
                   }
               }
                $("select[name='position_type']").html(content);
               form.render();
            },error:function () {
            }
        });
    }

    /**
     * 查询
     */
    PositionInfo.search = function(){
        PositionInfo.initTable();
    }

    /**
     * 重置
     */
    PositionInfo.reset = function(){
        $("input[name=position_code]").val("");
        $("input[name=position_name]").val("");
        $("select[name=position_type]").val("");
        PositionInfo.initTable();
    }

    // /**
    //  * 点击打开配置弹窗
    //  */
    // PositionInfo.openConfigDiv = function(positionId){
    //     addIndex = layer.open({
    //         type: 1
    //         ,title:"关联电表配置"
    //         ,area:['600px','480px']
    //         ,content: $('#position_ammeter_config') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    //         ,cancel: function(index, layero){
    //             PositionInfo.closeClearWin();
    //             return false;
    //         }
    //         ,success:function(){
    //             console.log(positionId);
    //             // PositionInfo.initTransfer(positionId);
    //         }
    //     });
    // }
    //
    // PositionInfo.closeClearWin = function(){
    //     layer.close(addIndex);
    // }
    // /**
    //  * 加载穿梭框
    //  */
    // PositionInfo.initTransfer = function(){
    //     //渲染
    //     transfer.render({
    //         elem: '#test1'  //绑定元素
    //         ,title: ['未选择', '已选择']
    //         ,data: [
    //             {"value": "1", "title": "李白", "disabled": "", "checked": ""}
    //             ,{"value": "2", "title": "杜甫", "disabled": "", "checked": ""}
    //             ,{"value": "3", "title": "贤心", "disabled": "", "checked": ""}
    //         ]
    //         ,id: 'demo1' //定义索引
    //     });
    // }

    //加载未包含电表
    PositionInfo.queryRemainAmmeter = function(){
        var keywords = $("#remainAmmeterKeywords").val();
        $.ajax({
            url: _ctx + "/platform/position/queryRemainAmmeter",
            type: "post",
            data:({
                positionId: PositionInfo.positionId,
                keywords: keywords
            }),
            success: function(data) {
                //填充“未选择”数据
                if(data && data.length > 0){
                    $("#remain_ammeter_table").tabulator("setData", data)
                }else{
                    $("#remain_ammeter_table").tabulator("setData", []);
                }
            },
            error: function(data) {
                // swal("加载未包含失败!", data.msg, "error");
            }
        });
    }

    //加载已包含电表
    PositionInfo.queryContainAmmeter = function(){
        var keywords = $("#containAmmeterKeywords").val();
        $.ajax({
            url: _ctx + "/platform/position/queryContainAmmeter",
            type: "post",
            data:({
                positionId: PositionInfo.positionId,
                keywords: keywords
            }),
            success: function(data) {
                //填充“已选择”数据
                if(data && data.length > 0){
                    $("#contain_ammeter_table").tabulator("setData", data)
                }else{
                    $("#contain_ammeter_table").tabulator("setData", []);
                }
            },
            error: function(data) {
                // swal("加载已包含失败!", data.msg, "error");
            }
        });
    }


    PositionInfo.initContainTable =function(){
        //创建并设置“已选择”table属性
        $("#contain_ammeter_table").tabulator({
            height:"93.3%",
            layout:"fitColumns",
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:true,
            //selectable:true,
            //placeholder:"无数据，请添加用户",
            //movableColumns:true,
            columns:[
                {title:"序号", field:"F_GUID",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
                // {title:"电表id",field:"F_GUID"},
                // {title:"关联关系id",field:"RELATIVE_ID"},
                {title:"电表编号", field:"F_SYS_NAME", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"电表名称", field:"F_NICK_NAME",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
                // {title:"运算符", field:"fOperator",width:80,align:"center",formatter:tabselect,
// 			onClick:function(e, cell, val, row){
// 				alert("打印行数据: " + row.name)
// 				}
//                     headerSort:false},
                {title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:backToRemain,headerSort:false},
            ],
            rowClick:function(e, in_row){
            },
        });
    }

    PositionInfo.initRemainTable = function(){
        //创建并设置“未选择”table属性
        $("#remain_ammeter_table").tabulator({
            height:"93.3%",
            layout:"fitColumns",//fitColumns  fitDataFill
            columnVertAlign:"bottom", //align header contents to bottom of cell
            tooltips:true,
            selectable:true,
            //placeholder:"无数据，请添加用户",
            movableColumns:true,
            columns:[
                {title:"序号",field:"F_GUID",width:50,formatter:"rownum",frozen:false,sorter:"number",headerSort:false},
                // {title:"电表id",field:"F_GUID",hide:true},
                {title:"电表编号", field:"F_SYS_NAME", width:85,sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"电表名称", field:"F_NICK_NAME",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:moveToContain,headerSort:false},
            ],
            rowClick:function(e, not_row){
            },
        });
    }

    /**
     * 点击添加关联
     * 关联弹窗中的电表关联按钮点击事件
     */
    PositionInfo.relativeAmmeterBtn = function(ammeterId){
        //房间关联电表
            $.ajax({
                url : _ctx + '/platform/position/addPositionAmmeterConfig',
                type: "post",
                data:({
                    positionId:PositionInfo.positionId,
                    ammeterId:ammeterId
                }),
                success: function(data) {
                    if (data.status === '1') {
                        PositionInfo.queryRemainAmmeter();
                        PositionInfo.queryContainAmmeter();
                        // layer.msg("关联成功");
                    }
                    else{
                        layer.msg("关联失败",{icon:2});
                    }
                },
                error: function(data) {
                    layer.msg("关联失败",{icon:2});
                }
            });
        // });

    }

    /**
     * 点击取消关联
     */
    PositionInfo.deleteRelativeBtn = function(ammeterId){
        // //房间移除关联的电表
            $.ajax({
                url : _ctx + '/platform/position/deletePositionAmmeterConfig',
                type: "post",
                data:({
                    positionId:PositionInfo.positionId,
                    ammeterId:ammeterId
                }),
                success: function(data) {
                    if (data.status === '1') {
                        PositionInfo.queryRemainAmmeter();
                        PositionInfo.queryContainAmmeter();
                    }
                    else{
                        layer.msg("关联失败",{icon:2});
                    }
                },
                error: function(data) {
                    layer.msg("关联失败",{icon:2});
                }
            });
        // });
    }


    //设置“未选择”格式
    var moveToContain = function(cell){
        var ammeterId = cell.getRow().getData().F_GUID;
        return "<button class='btn btn-white btn-sm relative_ammeter' data-id=" + ammeterId + " onclick='PositionInfo.relativeAmmeterBtn(\""+ammeterId+"\")'><i class='fa fa-arrow-circle-right'></i></button>"
    };
    //设置“已选择”格式
    var backToRemain = function(cell){
        var ammeterId = cell.getRow().getData().F_GUID;
        return '<button class="btn btn-white btn-sm delete_relative" data-id="' + ammeterId + '" onclick="PositionInfo.deleteRelativeBtn(\''+ammeterId+'\')"><i class="fa fa-trash"></i></button>'
    };


    //全部右移:把符合条件的未关联的电表全部关联
    PositionInfo.moveRight = function (){
        var keywords = $("#remainAmmeterKeywords").val();
            $.ajax({
                url: _ctx + "/platform/position/addRelativeByCondition",
                type: "post",
                data:({
                    positionId:PositionInfo.positionId,
                    keywords: keywords
                }),
                success: function(data) {
                    if (data.status === '1') {
                        PositionInfo.queryContainAmmeter();
                        PositionInfo.queryRemainAmmeter();
                    } else{
                        // swal(data.msg,"", "error");
                    }
                },
                error: function(data) {
                    // swal( data.msg,"", "error");
                }
            });
    }

    //全部左移:把符合条件的已关联的电表全部取消关联
    PositionInfo.moveLeft = function (){
        var keywords = $("#containAmmeterKeywords").val();
            $.ajax({
                url: _ctx + "/platform/position/deleteRelativeByCondition",
                type: "post",
                data:({
                    positionId:PositionInfo.positionId,
                    keywords: keywords
                }),
                success: function(data) {
                    if (data.status === '1') {
                        PositionInfo.queryContainAmmeter();
                        PositionInfo.queryRemainAmmeter();
                    } else{
                        // swal(data.msg,"", "error");
                    }
                },
                error: function(data) {
                    // swal( data.msg,"", "error");
                }
            });
    }


    $(function () {
        PositionInfo.getPositionTypeData();
        PositionInfo.initTree();
        PositionInfo.initTable();
        PositionInfo.initEvent();
    });



});