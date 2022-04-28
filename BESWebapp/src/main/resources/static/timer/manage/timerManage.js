/**
 * 定时任务管理
 */

var TimerManage = {
    //任务状态字典数据
    statusMap:{},
    statusList:[],
    //任务类型字典数据
    typeList:[],
    typeMap:{},
    //计划错误执行策略-字典数据
    strategyMap:{},
    strategyList:[],
    //任务并发执行-字典数据
    concurrentMap:{},
    concurrentList:[],
    //选中行数据
    selectedRow:{},
    //是否是修改
    editFlag:false
};

layui.use(['layer','form','jquery','table','element'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;

    //弹窗
    var addIndex;

    /**
     * 加载表格数据
     */
    TimerManage.initTable = function(){
        var taskName = $("input[name=task_name]").val();
        var taskType = $("select[name=task_type]").val();
        var taskStatus = $("select[name=task_status]").val();

        //todo js执行时css还未渲染是怎么回事？
        $(".timer_manage_content_div").css("height",'100%');
        //$("#timerManageQueryForm").outerHeight()
        var height = $(".timer_manage_content_div").outerHeight() - 80 - 80;

        //第一个实例
        table.render({
            elem: '#timer_manage_table'
            ,height: height
            ,method:"post"
            ,url: _ctx + '/view/timer/manage/queryList' //数据接口
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
                // ,field: obj.field //排序字段
                // ,order: obj.type //排序方式
            } //如果无需传递额外参数，可不加该参数
            ,page: true //开启分页
            ,limit:15
            ,autoSort:false //禁用前端自动排序
            // ,initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
            ,id:'jobId'
            ,cols: [[
                {
                    type:'checkbox'
                },
                {type: 'numbers', title: '序号',width:80},
                {
                    field: 'jobId',
                    title: 'jobId'
                    ,hide:true
                },
                {
                    field: 'jobName',
                    title: '任务名称'
                    ,width:120
                    // ,sort:true
                    ,templet:'<div><span title="{{d.jobName}}">{{d.jobName || "" }} </span></div>'
                },
                {
                    field: 'jobGroup',
                    title: '任务类型'
                    ,width:120
                    // ,sort:true
                    // ,templet:'<div><span title="{{d.jobGroup}}">{{d.jobGroup || "" }} </span></div>'
                    , templet: function (d) {
                        var value = d.jobGroup;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerManage.typeMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                //
                {
                    field: 'invokeTarget',
                    title: '调用目标字符串'
                },
                {
                    field: 'cronExpression',
                    title: 'cron表达式'
                },
                {
                    field: 'misfirePolicy',
                    title: '计划执行错误策略'
                    ,width:150
                    // ,sort:true
                    // ,templet:'<div><span title="{{d.misfirePolicy}}">{{d.misfirePolicy || "" }} </span></div>'
                    , templet: function (d) {
                        var value = d.misfirePolicy;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerManage.strategyMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                {
                    field: 'concurrent',
                    title: '并发执行'
                    , templet: function (d) {
                        var value = d.concurrent;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerManage.concurrentMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                 {
                    field: 'status',
                    title: '任务状态'
                    , templet: function (d) {
                        var value = d.status;
                        if(!value && value !== 0){
                            return "";
                        }
                        var map = TimerManage.statusMap;
                        var name = map[value];
                        if(!name){
                            return "";
                        }
                        return '<div><span title="'+name+'">'+name+'</span></div>';
                    }
                },
                {
                    field: 'remark',
                    title: '备注说明'
                    , templet: function (d) {
                        var value = d.remark;
                        if(!value && value !== 0){
                            return "";
                        }
                        return '<div><span title="'+value+'">'+value+' </span></div>';
                    }
                },
                {
                    field: 'createTime',
                    title: '创建时间'
                    // ,width:100
                },
                {
                    field: 'updateTime',
                    title: '修改时间'
                    // ,width:100
                }
                , {
                    field: "operation",
                    title: '操作'
                    , width: 160
                    , templet: function (d) {
                        return '<div class="btn-group">'
                            + '<button class="btn btn-white btn-sm include" data-id="'+d.jobId+'" onclick="TimerManage.executeConfirm(\''+d.jobId+'\')"><i class="fa fa-th-list"></i> 立即执行</button>'
                            // + '<button class="btn btn-white btn-sm include" data-id="'+d.jobId+'" onclick="TimerManage.getLog(\''+d.jobId+'\')"><i class="fa fa-th-list"></i> 查看日志</button>'
                            + '</div>';
                    }
                }
                ]]
            ,done: function(res, page, count){
            }
        });

    };

    //确认是否立即执行
    TimerManage.executeConfirm = function(jobId){
        layer.confirm('确定立即执行吗?', {title:'操作提示'}, function(index) {
            TimerManage.executeTask(jobId);
        });
    };

    //立即执行
    TimerManage.executeTask = function(jobId){
        $.ajax({
            url: _ctx + '/view/timer/manage/run',
            type: "post",
            // dataType : "json",
            data: {
                jobId: jobId
            },
            success : function(data) {
                if(data && data.result +"" === "true"){
                    layer.msg("操作成功",{icon:1});
                    // TimerManage.search();
                }else if(data && data.msg){
                    layer.msg(data.msg,{icon:2});
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    };


    //查看日志
    TimerManage.getLog = function(jobId){

    };

    /**
     * 查询
     */
    TimerManage.search = function(){
        TimerManage.initTable();
    };

    /**
     * 重置
     */
    TimerManage.reset = function(){
        $("select[name=task_status]").val("");
        $("select[name=task_type]").val("");
        $("input[name=task_name]").val("");

        TimerManage.initTable();
    };


    /**
     * 查询字典数据--任务状态
     */
    TimerManage.initTaskStatusDict = function(){
        return new Promise(function(resolve, reject) {
            //任务状态
            DictCommon.getDictByType("task_status",function(dictData){
                if(dictData && dictData.list){
                    DictCommon.initQuerySelect("select[name='task_status']",dictData.list);
                    TimerManage.statusMap = dictData.map;
                    TimerManage.statusList = dictData.list;
                    form.render();
                }
                resolve();
            });
        });
    };

    /**
     * 查询字典数据--任务类型
     */
    TimerManage.initTaskTypeDict = function(){
        return new Promise(function(resolve, reject) {
            //任务类型
            DictCommon.getDictByType("task_type", function (dictData) {
                if (dictData && dictData.list) {
                    DictCommon.initQuerySelect("select[name='task_type']", dictData.list);
                    TimerManage.typeMap = dictData.map;
                    TimerManage.typeList = dictData.list;
                    form.render();
                }
                resolve();
            });
        });

    };

    /**
     * 查询字典数据--计划错误执行策略
     */
    TimerManage.initErrorStrategyDict = function(){
        return new Promise(function(resolve, reject) {
            //计划错误执行策略
            DictCommon.getDictByType("task_error_strategy",function(dictData){
                if(dictData && dictData.list){
                    TimerManage.strategyMap = dictData.map;
                    TimerManage.strategyList = dictData.list;
                }
                resolve();
            });
        });

    };

    /**
     * 查询字典数据--任务并发执行
     */
    TimerManage.initTaskConcurrentDict = function(){
        return new Promise(function(resolve, reject) {
            //任务并发执行
            DictCommon.getDictByType("task_concurrent",function(dictData){
                if(dictData && dictData.list){
                    TimerManage.concurrentMap = dictData.map;
                    TimerManage.concurrentList = dictData.list;
                }
                resolve();
            });
        });

    };


    TimerManage.initEvent = function(){
        //居中显示
        $('#addTaskManageModal').on('show.bs.modal', function () {
            $(this).draggable({
                handle:".modal-header"
            });
            $(this).css("overflow","hidden");
            $(this).css('display', 'block');
            var modalHeight=$(window).height() / 2 - $('#addTaskManageModal .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });

            //加载下拉框
            DictCommon.initCommonSelect("select[id='taskStatus']",TimerManage.statusList);
            DictCommon.initCommonSelect("select[id='taskType']",TimerManage.typeList);
            DictCommon.initCommonSelect("select[id='concurrent']",TimerManage.concurrentList);
            DictCommon.initCommonSelect("select[id='errorStrategy']",TimerManage.strategyList);
            //加载修改数据
            if(TimerManage.editFlag){
                //禁止修改能源类型
                $("#energyType").attr("disabled",true);
                var selectedId = table.checkStatus('jobId');
                var jobId = selectedId.data[0].jobId;
                TimerManage.getJobInfoById(jobId);
            }
        })
        //关闭模态框清空表单值
       .on('hidden.bs.modal', function (event) {
            $(this).find("input").val("");
            $(this).find("select").val("");
           $("#energyType").attr("disabled",false);
            formValidate.resetForm();
            TimerManage.editFlag = false;
        });


        //添加组织机构表单验证
        var formValidate = $("#addTaskManageForm").validate({
            rules: {
                taskName: {
                    required: true,
                    rangelength: [1, 60],
                    isNormal: true
                },
                taskType: {
                    required: true
                },
                invokeTarget:{
                    required: true
                },
                cronExpression:{
                    required:true,
                    remote: {
                        url:  _ctx + "/view/timer/manage/checkCronExpressionIsValid",
                        type: "post",
                        dataType: "json",
                        data: {
                            "cronExpression": function() {
                                return $.trim($("#cronExpression").val());
                            }
                        },
                        dataFilter: function(data, type) {
                            return data;
                        }
                    }
                },
                errorStrategy:{
                    required:true
                },
                concurrent:{
                    required:true
                },
                taskStatus:{
                    required:true
                },
                taskRemark:{
                    required:false,
                    rangelength: [1, 200]
                }

            },
            messages: {
                taskName: {
                    required: "请输入任务名称",
                    rangelength: jQuery.validator.format("输入长度必须介于{0}和{1}之间")
                },
                taskType: {
                    required: "请选择任务类型"
                },
                invokeTarget:{
                    required: "请输入调用目标字符串"
                },
                cronExpression: {
                    required: "请输入cron表达式",
                    remote: "cron表达式不正确"
                },
                errorStrategy:{
                    required: "请选择错误策略"
                },
                concurrent:{
                    required: "请选择是否并发"
                },
                taskStatus:{
                    required: "请选择任务状态"
                },
                taskRemark:{
                    required: "请输入备注说明",
                    rangelength: jQuery.validator.format("输入长度必须介于{0}和{1}之间")
                }
            },
            submitHandler: function (form) {
                TimerManage.saveTask(form);
            }
        });

    };

    //查询获取单个参数的信息
    TimerManage.getJobInfoById = function(jobId){
        $.ajax({
            url: _ctx + "/view/timer/manage/getJobInfoById",
            type: "post",
            data:({
                jobId:jobId
            }),
            success: function(data) {

               if(data){
                   $("#taskId").val(data.jobId);
                   $("#taskName").val(data.jobName);
                   $("#taskType").val(data.jobGroup);
                   $("#invokeTarget").val(data.invokeTarget);
                   $("#cronExpression").val(data.cronExpression);
                   $("#errorStrategy").val(data.misfirePolicy);
                   $("#taskStatus").val(data.status);
                   $("#concurrent").val(data.concurrent);
                   $("#taskRemark").val(data.remark);
               }
            },
            error: function(data) {

            }
        });
    };


    /**
     * 修改参数,打开弹窗
     */
    TimerManage.edit = function(){
        //判断是否勾选一条记录
        var selectedId = table.checkStatus('jobId');
        if(selectedId.data.length === 1){
            //修改标记
            TimerManage.editFlag = true;
            $("#addTaskManageModal").modal("show");
        }else{
            layer.msg("请先选择一条记录",{icon:0});
        }
    };

    /**
     * 删除参数
     */
    TimerManage.delete = function(){
        //判断是否勾选一条记录
        var selectedId = table.checkStatus('jobId');
        if(selectedId.data.length >= 1){
            layer.confirm('确定删除吗?', {title:'删除提示'}, function(index) {
                TimerManage.deleteExecute();
            });
        }else{
            layer.msg("请至少选择一条记录",{icon:0});
        }
    };

    /**
     * 执行删除事件
     */
    TimerManage.deleteExecute = function(){
        var selectedId = table.checkStatus('jobId');
        var data = selectedId.data;
        var idArray = [];
        for(var i = 0; i < data.length; i++){
            idArray.push(data[i].jobId);
        }

        $.ajax({
            url: _ctx + '/view/timer/manage/delete',
            type: "post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(idArray),
            success : function(data) {
                if(data && data.result + "" === "true"){
                    layer.msg("操作成功",{icon:1});
                    TimerManage.search();
                }else if(data && data.msg){
                    layer.msg(data.msg,{icon:2});
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    };

    /**
     * 新增或修改
     */
    TimerManage.saveTask = function(){
        var taskId = $("#taskId").val();
        var taskName = $("#taskName").val();
        var taskType = $("#taskType").val();
        var invokeTarget = $("#invokeTarget").val();
        var cronExpression = $("#cronExpression").val();
        var errorStrategy = $("#errorStrategy").val();
        var concurrent = $("#concurrent").val();
        var taskStatus = $("#taskStatus").val();
        var taskRemark = $("#taskRemark").val();
        $.ajax({
            url: _ctx + '/view/timer/manage/add',
            type: "post",
            // dataType : "json",
            data: {
                jobId: taskId,
                jobName:taskName,
                jobGroup:taskType,
                invokeTarget:invokeTarget,
                cronExpression:cronExpression,
                misfirePolicy:errorStrategy,
                concurrent:concurrent,
                status:taskStatus,
                remark:taskRemark
            },
            success : function(data) {
                if(data && data.result +"" === "true"){
                    layer.msg("操作成功",{icon:1});
                    $('#addTaskManageModal').modal('hide');//关闭弹窗
                    TimerManage.search();
                }else if(data && data.msg){
                    layer.msg(data.msg,{icon:2});
                }else{
                    layer.msg("操作失败",{icon:2});
                }
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    };


    $(function(){
        //查询任务状态、任务类型字典数据
        // TimerManage.initDict().then(function(){
        //     TimerManage.initTable();
        // });

        Promise.all([TimerManage.initTaskStatusDict(),TimerManage.initTaskTypeDict(),
                    TimerManage.initErrorStrategyDict(),TimerManage.initTaskConcurrentDict()])
            .then(function(){
                TimerManage.initTable();
        });

        TimerManage.initEvent();
        form.render();
    });
});
