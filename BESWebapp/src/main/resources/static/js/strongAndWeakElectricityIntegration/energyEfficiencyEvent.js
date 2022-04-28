/**
 * 标签运行模式
 */


var EnergyEfficiencyEvent = {
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var pieRelativeIndex,pieRelativeEnergyEfficiencyWinData;
    var relativePoint,expression,id,name;
    var copData= "";
    /**
     * 系统能耗按钮--初始化方法
     */
    EnergyEfficiencyEvent.initEnergyEfficiencyBtn = function(){
        var xtzll = "0";var xthdl = "0";var lwb = "0";var lrb = "0";
        var btnText;
        var $btn = $("#design_area_demo").find(".design_energyEfficiency_btn");
         var  designFormulas = "";//计算公式
        for(var i = 0; i < $btn.length; i++){
            var sysnameArray = [];
            var dataId = $($btn[i]).attr("data-config");//关联点
            designFormulas = $($btn[i]).attr("design-formulas");//计算公式
            var id = $($btn[i]).attr("id");//计算公式
            sysnameArray.push(dataId);
            sysnameArray.push(id);
            sysnameArray.push(designFormulas);

            if(dataId){
                $.ajax({
                    url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryEnergyEfficiencyListInfo',
                    type    : "post",
                    data: JSON.stringify(sysnameArray),
                    async: false,
                    contentType: 'application/json;charset=UTF-8',
                    success : function(result) {
                        if(result && result.map){
                            if(id==="536a-628b-1ac4-4653-7144"){//系统制冷量
                                xtzll = result.map.typeAllData;
                                btnText = '系统制冷量'+'<br>'+xtzll+"kw";
                            }else if(id==="55da-b4b2-00df-4b3f-0467"){//系统耗电量
                                xthdl = result.map.typeAllData;
                                btnText = '系统耗电量'+'<br>'+xthdl+"kw";
                            }else if(id==="c46d-476f-492d-66bb-e95b"){//冷温泵
                                lwb = result.map.typeAllData;
                                btnText = '冷温泵总耗电量'+'<br>'+lwb+"kw";
                            }else if(id==="2078-7c07-fb75-7890-2d99"){//冷热泵
                                lrb = result.map.typeAllData;
                                btnText =  '冷热泵总耗电量'+'<br>'+lrb+"kw";
                            }
                            document.getElementById(id).innerHTML=btnText;
                        }
                    }
                })
            }
        }
        if(xtzll!=""&&xthdl!=""&&typeof(xtzll)!="undefined"&&typeof (xthdl)!="undefined"&&xthdl>0){
            var cop = parseFloat(xtzll/xthdl).toFixed(2);
            document.getElementById("42a9-1224-1a73-6610-34eb").innerHTML="COP"+"<br>"+cop;
            copData = cop;//获取cop的值
        }
        if(xtzll!=""&&lwb!=""&&typeof(xtzll)!="undefined"&&typeof (lwb)!="undefined"&&lwb>0){
            var chw = parseFloat(xtzll/lwb).toFixed(2);
            document.getElementById("15fc-79dc-829c-45b6-9bff").innerHTML="WTFchw"+"<br>"+chw;
        }
        if(xtzll!=""&&lrb!=""&&typeof(xtzll)!="undefined"&&typeof (lrb)!="undefined"&&lrb>0){
            var cw = parseFloat(xtzll/lrb).toFixed(2);
            document.getElementById("e2a6-0c65-3baf-964b-3f36").innerHTML="WTFcw"+"<br>"+cw;
        }
        EnergyEfficiencyEvent.showSystemEnergyEfficiencyCop(copData);
    }

    //显示COP
    EnergyEfficiencyEvent.showSystemEnergyEfficiencyCop=function(copData){
        var dom = document.getElementById("systemEnergyEfficiencyCopLine");
        var myChart2 = echarts.init(dom, 'light');
        var option_inter = {
            title: {
                text: '系统COP',
                textStyle : {
                    fontSize: '16',
                    color:'#000000',
                },
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#ffbe08'
                    }
                }
            },
            legend: {
                data:['COP'],
                x : '70',
            },
            toolbox: {
                show: true,
                feature: {//功能
                }
            },
            grid: {
                left: '7%',
                right: '0%',
                bottom: '10%',
                containLabel: true
            },
            dataZoom: {
                show: false,
                start: 0,
                end: 100
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#212122'
                        }
                    },
                    data: (function (){
                        var now = new Date();
                        var res = [];
                        var len = 10;
                        while (len--) {
                            res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                            now = new Date(now - 2000);
                        }
                        return res;
                    })()
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    scale: true,
                    name: '数值',
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#212122'
                        }
                    },
                    min: 0,
                    boundaryGap: [0.2, 0.2]
                }
            ],
            series: [
                {
                    name:'COP',
                    type:'line',
                    smooth:true,
                    data:[copData],
                    /*data:(function (){
                        var res = [];
                        var len = 0;
                        while (len < 10) {
                            res.push((Math.random()*1.5+0.6).toFixed(1) - 0);
                            len++;
                        }
                        return res;
                    })()*/
                }
            ]
        }
        //先展示 后填充数据
        myChart2.setOption(option_inter);


    }



    /* 设备能耗占比 */
    EnergyEfficiencyEvent.systemEnergyEfficiencyRenderChartBar=function(flag){
        var num ,num1,num2;
        var myCharts = echarts.init(document.getElementById("equipmentEnergyConsumptionRatio"));
        if(flag=="1"){//运行模式
            num ="0";
            num1="0";
            num2="0";
            var piePointArray = ["1","2","3"];
            if(piePointArray.length > 0) {
                $.ajax({
                    url: _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryAllPieRelativeInfo',
                    type: "post",
                    data: JSON.stringify(piePointArray),
                    async: false,
                    contentType: 'application/json;charset=UTF-8',
                    success: function (result) {
                        if (result && result.map) {
                            var resultList = result.map.allPiePointData;
                            for(var i = 0;i<resultList.length;i++){
                                var sysnameArray = [];
                                if(resultList[i].relativePoint!=""&&resultList[i].expression!=""){
                                    sysnameArray.push(resultList[i].relativePoint);
                                    sysnameArray.push(resultList[i].id);
                                    sysnameArray.push(resultList[i].expression);
                                    if(sysnameArray.length > 0){
                                        $.ajax({
                                            url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryEnergyEfficiencyListInfo',
                                            type    : "post",
                                            data: JSON.stringify(sysnameArray),
                                            async: false,
                                            contentType: 'application/json;charset=UTF-8',
                                            success : function(result) {
                                                if(result && result.map){
                                                    if(resultList[i].id=="1"){//冷却泵能耗
                                                        num = result.map.typeAllData;
                                                    }if(resultList[i].id=="2"){//冷温泵能耗
                                                        num1 = result.map.typeAllData;
                                                    }if(resultList[i].id=="3"){//主机能耗
                                                        num2 = result.map.typeAllData;
                                                    }
                                                }
                                            }
                                        })
                                    }
                                }
                            }
                        }
                    }
                })

            }
        }else{//编辑模式
            num ="1",num1="1",num2="1";
        }
        var numAll = Number(num)+Number(num1)+Number(num2);
        option = {
            title: [
                {
                    top:0,//此处是指title（设备能耗占比）距离上边距的距离为0px;
                    text: '设备能耗占比',
                    subtext: '',
                    x: 'left',//设备能耗占比在div中的位置
                    textStyle: {
                        color: 'black',
                        fontSize:17
                    }
                },
                {
                    top:110,//此处是多加一条title用间距顶到圆形中央显示
                    left:170,
                    text: [
                        '总累计',
                        numAll ,
                    ].join('\n'),
                    subtext: '',
                    x: 'left',//设备能耗占比在div中的位置
                    textStyle: {
                        color: 'blue',
                        fontSize:15
                    }
                },

            ],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            series: [{
                name: '累计总能耗',
                type: 'pie',
                silent:false,
                //selectedMode: 'single',
                radius: ['0%', '25%'],

                label: {
                    normal: {
                        show:true,
                        position: 'inner',
                        formatter:'{b}\n{c}'
                    }
                },
                data: [
                    {
                        value: numAll,
                        name: '总累计',
                        itemStyle:{
                            color:'#fff' //00BFFF  中部背景颜色（圆），此处设置为了白色是为了和字体颜色一致。否则不同后会显示两个。
                        }

                    }
                ]
            },
                {
                    name: '能耗:',
                    type: 'pie',
                    radius: ['40%', '55%'],
                    label: {
                        normal: {
                            formatter: '{b|{b}}{abg|}\n {hr|}\n {c}  {per|{d}%} ',
                            //backgroundColor: '#9f9f9f',//此处背景色为动态后长方形背景色
                            backgroundColor: '#c8c8c8',
                            borderColor: '#aaa',
                            borderWidth: 1,//边宽
                            borderRadius: 4,//背景方框周边添加弧度为4px;
                            rich: {
                                hr: {
                                    borderColor: '#aaa', //长方形中心线的颜色
                                    width: '90%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    top:10,
                                    align: 'center',
                                    lineHeight: 10
                                },
                                per: {
                                    color: '#fff',//数据百分比的颜色
                                    backgroundColor: '#6b6b6b',//数据百分比的背景色
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        }
                    },
                    data: [{
                        value: num,
                        name: '冷却泵能耗',
                        itemStyle:{
                            color:'#ea12c9',
                            normal:{
                                label:{
                                    show: true,
                                    formatter:function (params) { if(params.value==0){//为0时不显示
                                        return ' '
                                    }else{
                                        return params.value }
                                    }
                                },
                               /* labelLine: {
                                    show: true
                                }*/
                            }
                        },
                    },
                        {
                            value: num1,
                            name: '冷温泵能耗',
                            itemStyle:{
                                color:'#4ec710',
                                normal:{
                                    label:{
                                        show: false,
                                        formatter:function (params) { if(params.value==0){//为0时不显示
                                            return ' '
                                        }else{
                                            return  '' }
                                        }
                                    },
                                  /*  labelLine: {
                                        show: true
                                    }*/
                                }
                            },

                        },
                        {
                            value: num2,
                            name: '主机能耗',
                            itemStyle:{
                                color:'#e09e0b',
                                normal:{
                                    label:{
                                        show: true,
                                        formatter:function (params) { if(params.value==0){//为0时不显示
                                            return ' '
                                        }else{
                                            return params.value }
                                        }
                                    },
                                   /* labelLine: {
                                        show: true
                                    }*/
                                }
                            },

                        },

                    ]
                }
            ]
        };
        /*var opt = option.series[1];
        lineHide(opt);
        //数据为零时隐藏线段
        function lineHide(opt) {
            jQuery.each(opt.data, function (i, item) {
                if (item.value === 0) {
                    item.itemStyle.normal.labelLine.show = false;
                    item.itemStyle.normal.label.show = false;
                }
            });
        }*/
        myCharts.setOption(option);
        myCharts.on('click', function (params) {
            name = params.name;
            $.ajax({
                url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryPieRelativeInfo',
                type    : "post",
                dataType:'json',
                contentType : "application/json; charset=utf-8",
                async:false,
                data:JSON.stringify({name:name}),
                success : function(result) {
                    if(result.status=="1"){//存在的情况---取出来
                        relativePoint = result.list[0].relativePoint;//关联点
                        expression = result.list[0].expression;//计算公式
                        id = result.list[0].id;
                     }else{//不存在的情况---往数据库里面存
                        $.ajax({
                            url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/addPieRelativeInfo',
                            type    : "post",
                            dataType: 'json',
                            async:false,
                            contentType : "application/json; charset=utf-8",
                            data    : JSON.stringify({
                                name:name,
                                expression:"",
                                relativePoint:"",
                            }),
                            success : function(result) {
                                if(result.status=="1"){
                                   /* layer.msg("保存成功",{icon:1});*/
                                }else{
                                    /*layer.msg("保存失败",{icon:1});*/
                                }
                            }
                        })
                    }
                }
            })

            EnergyEfficiencyEvent.openRelativeWin(name);
        });//在饼图上添加点击事件



    }



    //编辑模式下点击饼图弹出点位关联弹窗
    EnergyEfficiencyEvent.openRelativeWin =function(name){
        document.getElementById("btnRelative").style.display = "none";
        document.getElementById("btnCancel").style.display = "none";
        document.getElementById("pieRelative").style.display = "inline ";
        document.getElementById("pieCancel").style.display = "inline ";
        pieRelativeIndex = layer.open({
            type: 1,
            title:"饼图点位关联",
            area:['450px','350px'],
            content: $('#design_energyEfficiency_btn_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                //AddEnergyEfficiency.closeRelativeWin();
                return false;
            },
            success:function () {
                $("#design_energyEfficiency_btn_relative_name").val(name);//点位名称
                $("#expression").val(expression)
                var singlePoint="";
                if(typeof (relativePoint)!=undefined){
                        singlePoint = relativePoint.split(',');
                }
                if(singlePoint && singlePoint.length > 0){
                    AddEnergyEfficiency.initConfigTable(singlePoint);
                }
            }
        });
    }





    EnergyEfficiencyEvent.relativeBtnClick = function(){
        var count=($("#expression").val().split('$')).length-1;//获取计算公式中需要输入的参数个数
        var $table = $("#inputPointTable");
        var len = $table.find("tr").length ;
        if($("#design_energyEfficiency_btn_relative_name").val()==""){
            layer.msg("点名称不能为空",{icon:0});
            return;
        }
        if(count!=len){
            layer.msg("计算公式参数与关联点位个数不匹配，请重新输入",{icon:0});
            $("#expression").val("")
            return;
        }
        if(len === 0){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        var configData = "";
        var nameData =  "";
        for(var i = 0; i <= len-1; i++){
            var sysname = $table.find("tr:eq("+i+") td:eq(0) input ").val();
            var item = "sysname:"+sysname+";";
            configData += item;
            nameData += sysname + ",";
        }
        if(nameData.length > 1){
            nameData = nameData.substring(0,nameData.length-1);//关联点
        }

        //修改饼图点位关联及计算公式信息
        $.ajax({
            url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/addPieRelativeInfo',
            type    : "post",
            dataType:'json',
            contentType : "application/json; charset=utf-8",
            data:JSON.stringify({
                id:id,
                name:name,
                relativePoint:nameData,
                expression:$("#expression").val(),
            }),
            success : function(result) {
                if(result.status=="1"){
                    /*layer.msg("更新成功",{icon:1});*/
                }else{
                    /*layer.msg("更新失败",{icon:1});*/
                }
            }
        })

        EnergyEfficiencyEvent.closeRelativeWin();
    }

    EnergyEfficiencyEvent.closeRelativeWin = function(){
        layer.close(pieRelativeIndex);
        $("#design_energyEfficiency_btn_relative_id").val("");
        $("#design_energyEfficiency_relative_name").val("");
        $("#expression").val("")
        $("#inputPointTable").empty();
    }




    EnergyEfficiencyEvent.initTableListDataInfo = function(){
        var $label = $(".design_table").find(".design_label");
        for(var i = 0; i < $label.length; i++){
            var dataId = $($label[i]).attr("data-id");
            if(dataId){
                $.ajax({
                    url     : _ctx + '/view/strongAndWeakElectricityIntegration/energyEfficiencyAssessment/queryLableRelativeInfo',
                    type    : "post",
                    dataType:'json',
                    contentType : "application/json; charset=utf-8",
                    async:false,
                    data:JSON.stringify({sysName:dataId}),
                    success : function(result) {
                        if(result.status=="1"){//存在的情况---取出来
                            if(result.list!=null&&result.list.length>0){
                                $($label[i]).parent().parent().css("text-align","center");
                                $($label[i]).parent().parent().children('td').eq(1).html(result.list[0].F_INIT_VAL);
                                if(result.list[0].hasOwnProperty("F_ENGINEER_UNIT")){
                                    $($label[i]).parent().parent().children('td').eq(2).html(result.list[0].F_ENGINEER_UNIT);
                                }
                                $($label[i]).text(result.list[0].F_NICK_NAME);
                               /* $($label[i]).parent().parent().children('td').eq(0).html(result.list[0].F_NICK_NAME);*/
                            }
                        }else{//不存在的情况---往数据库里面存

                        }
                    }
                })
/*



                $($label[i]).parent().parent().children('td').eq(1).html('未消费');
                $($label[i]).parent().parent().children('td').eq(1).css("text-align","center");*/
            }
        }
    }






});