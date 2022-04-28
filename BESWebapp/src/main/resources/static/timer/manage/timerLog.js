/**
 * 定时任务日志管理
 */

var TimerLog = {
    //执行状态字典数据
    statusMap:{},
    statusList:[],
    //任务类型字典数据
    typeList:[],
    typeMap:{},
    //选中行数据
    selectedRow:{},
    //是否是修改
    editFlag:false
};

layui.use(['layer','form','jquery','table','element','laydate'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;

    //弹窗
    var addIndex;

    /**
     * 加载表格数据
     */
    TimerLog.initTable = function(){

        var taskName = $("input[name=task_name_log]").val();
        var taskType = $("select[name=task_type_log]").val();
        var taskStatus = $("select[name=task_status_log]").val();
        var rangeTime = $("#create_time_log").val();
        if(rangeTime){
            var timeArray = rangeTime.split(" - ");
            var startTime = timeArray[0];
            var endTime = timeArray[1];
        }

        //todo js执行时css还未渲染是怎么回事？
        $(".timer_log_content_div").css("height",'100%');
        //$("#timerLogQueryForm").outerHeight()
        var height = $(".timer_log_content_div").outerHeight() - 40 - 80;

        //第一个实例
        table.render({
            elem: '#timer_log_table'
            ,height: height
            ,method:"post"
            ,url: _ctx + '/view/timer/log/queryList' //数据接口
            // ,contentType: 'application/json'
            ,request: {
                //那么请求数据时的参数将会变为：?curr=1&nums=30
                pageName: 'pageNumber' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,where: {
                taskName:taskName
                ,taskType:taskType
                ,taskStatus:taskStatus
                ,startTime:startTime
                ,endTime:endTime
                // ,field: obj.field //排序字段
                // ,order: obj.type //排序方式
            } //如果无需传递额外参数，可不加该参数
            ,page: true //开启分页
            ,limit:15
            ,autoSort:false //禁用前端自动排序
            // ,initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            ,id:'jobLogId'
            ,cols: [[
                {
                    type:'checkbox'
                },
                {type: 'numbers', title: '序号',width:80},
                {
                    field: 'jobLogId',
                    title: 'jobLogId'
                    ,hide:true
                },
                {
                    field: 'jobName',
                    title: '任务名称'
                    ,width:200
                    // ,sort:true
                    ,templet:'<div><span title="{{d.jobName}}">{{d.jobName || "" }} </span></div>'
                },
                {
                    field: 'jobGroup',
                    title: '任务类型'
                    ,width:200
                    // ,sort:true
                    // ,templet:'<div><span title="{{d.jobGroup}}">{{d.jobGroup || "" }} </span></div>'
                    , templet: function (d) {
                        var value = d.jobGroup;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerLog.typeMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                {
                    field: 'invokeTarget',
                    title: '调用目标字符串'
                },
                {
                    field: 'jobMessage',
                    title: '日志信息'
                    ,width:400
                    // ,sort:true
                    ,templet:'<div><span title="{{d.jobMessage}}">{{d.jobMessage || "" }} </span></div>'
                },
                 {
                    field: 'status',
                    title: '执行状态'
                     ,width:100
                    , templet: function (d) {
                        var value = d.status;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerLog.statusMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                {
                    field: 'createTime',
                    title: '执行时间'
                    ,width:200
                }
                // ,{
                //     field: "operation",
                //     title: '操作'
                //     , width: 160
                //     , templet: function (d) {
                //         return '<div class="btn-group">'
                //             + '<button class="btn btn-white btn-sm include" data-id="'+d.jobLogId+'" onclick="TimerLog.executeConfirm(\''+d.jobLogId+'\',\''+d.energy_type+'\')"><i class="fa fa-th-list"></i> 立即执行</button>'
                //             + '</div>';
                //     }
                // }
                ]]
            ,done: function(res, page, count){
            }
        });

    };


    /**
     * 查询
     */
    TimerLog.search = function(){
        TimerLog.initTable();
    };

    /**
     * 重置
     */
    TimerLog.reset = function(){
        $("select[name=task_status_log]").val("");
        $("select[name=task_type_log]").val("");
        $("input[name=task_name_log]").val("");
        $("#input[name=create_time_log]").val("");
        TimerLog.initTable();
    };


    /**
     * 查询字典数据-执行状态
     */
    TimerLog.initStatusDict = function(){

        return new Promise(function(resolve, reject) {
            //执行状态
            DictCommon.getDictByType("task_execute_status", function (dictData) {
                if (dictData && dictData.list) {
                    DictCommon.initQuerySelect("select[name='task_status_log']", dictData.list);
                    TimerLog.statusMap = dictData.map;
                    TimerLog.statusList = dictData.list;
                    form.render();
                }
                resolve();
            });
        });
    };

    /**
     * 查询字典数据-任务类型
     */
    TimerLog.initTypeDict = function(){

        return new Promise(function(resolve, reject) {
            //任务类型
            DictCommon.getDictByType("task_type", function (dictData) {
                if (dictData && dictData.list) {
                    DictCommon.initQuerySelect("select[name='task_type_log']", dictData.list);
                    TimerLog.typeMap = dictData.map;
                    TimerLog.typeList = dictData.list;
                    form.render();
                }
                resolve();
            });
        });
    };

    TimerLog.initEvent = function(){

        //执行时间
        laydate.render({
            elem: '#create_time_log'
            , type: 'date'
            , range: true
            , format: 'yyyy-MM-dd'
        });

    };

    $(function(){
        //查询执行状态、任务类型字典数据
        // TimerLog.initDict().then(function(){
        //     TimerLog.initTable();
        // });

        //这种方法不可用，无法确保先后顺序
        // $.when(TimerLog.initStatusDict(),TimerLog.initTypeDict()).done(function(){
        //     TimerLog.initTable();
        // });

        // 使用 Promise.all 以在数组中所有接口均异步成功后，执行then方法
        Promise.all([TimerLog.initStatusDict(),TimerLog.initTypeDict()]).then(function(){
            TimerLog.initTable();
        });

        TimerLog.initEvent();
        form.render();
    });
});
