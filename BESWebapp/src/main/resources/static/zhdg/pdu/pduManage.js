var PduManage = {
    //PDU开关状态字典数据
    switchStatusMap:{},
    switchStatusList:[],
    //PDU在线状态字典数据
    onlineStatusList:[],
    onlineStatusMap:{}
};



PduManage.initTable = function(){
    $('#pdu_manage_table').bootstrapTable({
        url: _ctx + '/zhdg/pole/pdu/queryList',
        //method: 'post',
        // data:data,
        sidePagination: '',
        pagination: false,
        // treeView: true,
        // treeId: "f_gnbh",//节点id
        // treeField: "f_gnmc",//节点名称
        // parentId: "f_parentid", //父节点字段ID
        columns:[
            // {title:"序号", field:"number", sorter:"string",editor:false,align:"center",headerSort:false,
            //     formatter :function(value, row, index) {
            //       return index+1;
            //     }
            // },
            {title:"PDU IP地址", field:"pduIp",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false
            },
            {title:"PDU在线状态", field:"online_status",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return '<div><span title="离线" class="pdu_offline">离线</span></div>';
                    }
                    var map = PduManage.onlineStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "pdu_online" : "pdu_offline";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';
                }
            },
            {title:"大灯状态", field:"big_light",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                //     var checked = value + "" === "1" ? "checked = 'checked'" : "";
                // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"小灯状态", field:"small_light",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }

                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"显示屏状态", field:"screen",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"音柱状态", field:"sound_post",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"摄像头状态", field:"camera",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"wifi状态", field:"wifi",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';

                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"气象状态", field:"weather",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';
                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            },
            {title:"一键报警状态", field:"alarm",tooltip : false,align:"center",tooltipsHeader : false,headerSort:false,
                formatter :function(value, row, index) {
                    if(!value && value !== 0){
                        return "";
                    }
                    var map = PduManage.switchStatusMap;
                    var name = map[value];
                    if(!name){
                        return "";
                    }
                    var style = value+"" === "1" ? "switch_on" : "";
                    return '<div><span class="'+style+'" title="'+name+'">'+name+'</span></div>';
                    // var checked = value + "" === "1" ? "checked = 'checked'" : "";
                    // return '<input type="checkbox" class="switch_checkbox" '+checked+'/>';
                }
            }

        ],
        onLoadSuccess:function(){
            // PduManage.initSwitchBtn();
        }

    });




};

// PduManage.initSwitchBtn = function(){
//     $(".switch_checkbox").bootstrapSwitch({
//         onText : "开",      // 设置ON文本
//         offText : "关",    // 设置OFF文本
//         onColor : "success",// 设置ON文本颜色     (info/success/warning/danger/primary)
//         offColor : "danger",  // 设置OFF文本颜色        (info/success/warning/danger/primary)
//         size : "mini",    // 设置控件大小,从小到大  (mini/small/normal/large)
//         handleWidth:"10",//设置控件宽度
//         onInit:function(event,state){
//
//         },
//         // 当开关状态改变时触发
//         onSwitchChange : function(event, state) {
//             var ProductId = event.target.defaultValue;
//             if (state == true) {
//                 //上线
//                 /* alert("ON"); */
//             } else {
//                 //下线
//                 /* alert("OFF"); */
//             }
//         }
//     });
// };

/**
 * 查询字典数据-开关状态
 */
PduManage.initSwitchStatusDict = function(){

    return new Promise(function(resolve, reject){
        //开关状态
        DictCommon.getDictByType("pdu_switch_status",function(dictData){
            if(dictData && dictData.list){
                // DictCommon.initQuerySelect("select[name='pdu_switch_status']",dictData.list);
                PduManage.switchStatusMap = dictData.map;
                PduManage.switchStatusList = dictData.list;
            }
            resolve();
        });
    });
};

/**
 * 查询字典数据-在线状态
 */
PduManage.initOnlineStatusDict = function(){
    return new Promise(function(resolve, reject){
       //在线状态
        DictCommon.getDictByType("pdu_online_status",function(dictData){
            if(dictData && dictData.list){
                PduManage.onlineStatusMap = dictData.map;
                PduManage.onlineStatusList = dictData.list;
            }
            resolve();
        });
    });
};

// PduManage.refresh = function(){
//     $('#pdu_manage_table').bootstrapTable("refresh");
// };

$(function(){
    // PduManage.initDict().then(function(){
    //     PduManage.initTable();
    // });

    // 使用 Promise.all 以在数组中所有接口均异步成功后，执行then方法
    Promise.all([PduManage.initSwitchStatusDict(),PduManage.initOnlineStatusDict()]).then(function(){
        PduManage.initTable();
    });
    //页面定时刷新
    // setInterval(PduManage.refresh,5000);
});
