var ws;
var url;
// var command =   JSON.stringify({cmdType:"read_all"});
var command;



$(function () {
    command = getCmd();
    console.log("开始执行websocket.js的时间：" + new Date());
    url = getUrl();
    connectWS(url);

});

function getCmd(){
    var json = {cmdType:"read_all"};
    var names = [];
    var numberDivArray = $(".numberDiv");
    for(var i = 0; i < numberDivArray.length; i++){
        var sysname = numberDivArray[i].dataset.name;
        names.push(sysname);
    }
    json.names = names;
   // command = JSON.stringify(json);
    return JSON.stringify(json);
}

function getUrl(){
    // // 当前地址
    // var path = window.location;

    // 当前主机
    // var hostaddress = window.location.host+ctxPath;
	var hostaddress = "111.14.222.51:9001/energy/"; //高速云可用
	//var hostaddress = "10.168.74.54:8082/energy/";  //本地直接访问
    // 后台wb控制器url
    var target = "screen/wb/pdu";

    // 将http协议换成ws
    //if (window.location.protocol === 'http:') {
        target = 'ws://' + hostaddress + target;
    //} else {
    //    target = 'wss://' + hostaddress + target;
    //}
    url = target;
    console.log('WebSocketServer地址：'+target);
    return url;
}

function connectWS(url) {

    //创建一个针对控制器的 webSocket 对象
    if ('WebSocket' in window) {
        ws = new WebSocket(url);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(url);
    } else {
        console.log("您的浏览器不支持 WebSocket！");
        return;
    }

    // 如果没有ws对象 直播状态为2 设置对应按钮
    if(ws==null){
        console.log("WebSocket创建失败...")
    }

    // 开启WS
    ws.onopen = function () {
        //向后台发送指令
        startsent(command);
        //心跳检测重置
        heartCheck.reset().start();
        console.log('发送控制命令');
        console.log("发送建立连接请求时间："+new Date());
    };


    // WS的返回信息
    ws.onmessage = function (event) {
        var json = JSON.parse(event.data);
        if(json.msg === "heartbeat"){
            console.log("heartbeat");
        }else{
			console.log("接收数据："+json);
            //渲染数据
            var statusData = json.statusData;
            var onlineData = json.onlineData;
            var $table = $("#pdu_manage_table");
            if($table[0]){
                //表格存在
                var tableDataArray = $table.bootstrapTable("getData");
                var tableData = tableDataArray.concat();
                var totalRows = $table.bootstrapTable('getOptions').totalRows;

                    for(var k = 0; k < tableData.length; k++){
                        var ip = tableData[k].pduIp;
                        if(statusData.hasOwnProperty(ip)){
                            var sData = statusData[ip];
                            for(var key in sData){
                                if(sData.hasOwnProperty(key)){
                                    tableData[k][key] =  sData[key];

                                    // var rows = {
                                    //     id : ip, //更新列所在行的索引
                                    //     field : key, //要更新列的field
                                    //     value : sData[key] //要更新列的数据
                                    // };
                                    // $table.bootstrapTable("updateByUniqueId",rows);
                                }
                            }
                        }else{
                            tableData[k]["big_light"] =  "0";
                            tableData[k]["small_light"] =  "0";
                            tableData[k]["screen"] =  "0";
                            tableData[k]["sound_post"] =  "0";
                            tableData[k]["camera"] =  "0";
                            tableData[k]["wifi"] =  "0";
                            tableData[k]["weather"] =  "0";
                            tableData[k]["alarm"] =  "0";
                            // for(key in tableData[k]){
                            //     if(tableData[k].hasOwnProperty(key)){
                            //         tableData[k][key] =  "0";
                            //
                            //         // var rows = {
                            //         //     id : ip, //更新列所在行的索引
                            //         //     field : key, //要更新列的field
                            //         //     value : "0" //要更新列的数据
                            //         // };
                            //         // $table.bootstrapTable("updateByUniqueId",rows);
                            //     }
                            // }
                        }
                        if(onlineData.hasOwnProperty(ip)){
                            tableData[k]["online_status"] = onlineData[ip];

                            // var rows = {
                            //     id : ip, //更新列所在行的索引
                            //     field : "online_status", //要更新列的field
                            //     value : onlineData[ip] //要更新列的数据
                            // };
                            // $table.bootstrapTable("updateByUniqueId",rows);
                        }else{
                            tableData[k]["online_status"] = "0";
                        }
                    }
                $table.bootstrapTable("load",{rows:tableData,total:totalRows});
            }




            // var oldData = layui.table.cache["pduIp"]; //获取表格当前的缓存数据 #id为table的id
            // oldData.forEach(function(item,i){
            //     var ip = item.pduIp;
            //     var sData = statusData[ip];
            //     if(sData){
            //         item.big_light = sData.big_light;
            //         item.small_light = sData.small_light;
            //         item.weather = sData.weather;
            //         item.screen = sData.screen;
            //         item.alarm = sData.alarm;
            //         item.sound_post = sData.sound_post;
            //         item.wifi = sData.wifi;
            //         item.camera = sData.camera;
            //     }
            //     var oData = onlineData[ip];
            //     if(oData){
            //         item.online_status = oData;
            //     }
            //
            // });
            //
            // layui.table.reload("pduIp",{
            //     data:oldData   // 将新数据重新载入表格
            // })



        }

        //如果获取到消息，心跳检测重置
        //拿到任何消息都说明当前连接是正常的
        heartCheck.reset().start();

    };

    // WS关闭
    ws.onclose = function (event) {
        console.log('WS已关闭:' + event.data  + "时间：" + new Date());
        //断线重连
        url = getUrl();
        connectWS(url);
    };

    ws.onerror = function () {

    };

}




//心跳检测
var heartCheck = {
    timeout: 60000,//60秒
    timeoutObj: null,
    reset: function(){
        clearTimeout(this.timeoutObj);
        return this;
    },
    start: function(){
        this.timeoutObj = setTimeout(function(){
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
            ws.send("HeartBeat");
            console.log("HeartBeat");
        }, this.timeout)
    }
}

function startsent(command){
    if (ws != null && ws.readyState !== 0) {
        // 控制台打印
        console.log('开始发送Wb指令');
        console.log("发送Wb指令的时间：" + new Date());
        // 推送信息
        ws.send(command);
    } else {
        console.log("WebSocket 连接建立失败，请重新连接");
    }
}


