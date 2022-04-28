/**
 * @author xiepufeng
 * WebSocket
 */
var socket = (function () {

    var sessionId;
    var userId;
    var password;
    var id = 0;
    var ws;       //WebSocket 对象
    var url;      //WebSocket url
    var protocols;//协议
    var keepalive;//WebSocket 连接超时时长

    function _socket( options ) {

        if( _socket.state !== 0 ){
            console.warn( '已存在一个 socket 连接，请不要重复创建连接！' );
        }

        if( options ){
            password = options.password;
            sessionId = options.sessionId;
            userId = options.userId;
            url = options.url;
            protocols = options.protocols;
            keepalive = options.keepalive
        }

        if( !password || !sessionId || !userId || !url || isNaN( keepalive ) ){
            console.warn( '参数 account、password、sessionId、userId、url、keepalive 都必须有值，并且 keepalive 必须是数字！' );
            return ;
        }

        if( typeof url !== 'string' || url.search( /ws:\/\/|wss:\/\// ) !== 0 ){
            console.warn( '无效的URL！' );
            return;
        }

        try{

            ws = new WebSocket( url, protocols );

        }catch ( error ){

            if( error === 'SECURITY_ERR' ){
                console.warn( '试图连接的端口被屏蔽！' );
            }else {
                console.error( error );
            }

            swal({
                title: 'WebSocket 连接中断，请尝试重新登录',
                text: "",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "确定",
                cancelButtonText: "取消"
            },function(isConfirm){
                if (isConfirm) {
                    window.location.href= _ctx + "/logout";
                }
            });
        }

        //建立连接的事件回调
        ws.onopen = function () {
            console.log( 'WebSocket 连接成功。' );
            _socket.state = 1;
            var loginInfo = {
                jsonrpc : '2.0',
                id : 1,
                method : 'login',
                params : {
                    sessionId : sessionId,
                    userId : userId,
                    password : password
                }
            };

            loginInfo = JSON.stringify( loginInfo );

            try {
                this.send( loginInfo );
            }catch ( error ){
                console.warn( 'WebSocket 登录失败，可能是连接被关闭，错误：看下一行' );
                console.warn( error );
            }
        };

        ws.onmessage = function ( event ) {

            var data = JSON.parse( event.data );

            var error = data.error;

            if( error ){

                console.error( 'code:' + error.code + ';' + 'message:' + error.message );

            }

            if( _socket.state === 1 ){
                afterLinkHandle( data );
                return ;
            }

            var messageHandle = socket.onmessage;

            if( _socket.state === 2 && typeof messageHandle === 'function' ){
                messageHandle( data );
            }

        };

        ws.onclose = function ( event ) {
            swal({
                title: 'WebSocket 连接中断，请尝试重新登录',
                text: "",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "确定",
                cancelButtonText: "取消"
            },function(isConfirm){
                if (isConfirm) {
                    window.location.href= _ctx + "/logout";
                }
            });
            console.log( '连接被关闭' );
            _socket.state = 0;

            var close = socket.onclose;

            if( typeof close === 'function' ){
                close( event );
            }
        };

        ws.onerror = function ( event ) {

            var readyState = ws.readyState;

            if( readyState === 3 ){
                _socket.state = 0;
                ws = null;
                setTimeout(function () {
                    console.warn( '连接已经断开并尝试重新连接登录！' );
                    _socket();
                }, 10 * 1000)
            }
        };

        function afterLinkHandle ( data ) {

            if( !data ){
                console.warn( '没有有效参数！' );
                return;
            }

            try {

                if( data.result ){
                    var result = data.result;
                    if( result ){
                        console.log( 'WebSocket 登录成功！');
                        _socket.state = 2;
                        id = 1;
                        //心跳
                        var circulateId = setInterval(function () {
                            var data = {
                                method : 'heartbeat',
                                params : {
                                    sessionId : sessionId
                                }
                            };
                            if( !socket.send( data ) ){
                                clearInterval( circulateId );
                            }
                        }, keepalive * 0.8 );
                    }
                }else if( data.error ){
                    var error = data.error;
                    var code = error.code;
                    if( code === -10001 ){
                        console.log( '密码错误！' );
                    }else if( code === -10002 ){
                        swal({
                            title: 'session 超时请重新登录',
                            text: "",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#1ab394",
                            confirmButtonText: "确定",
                            cancelButtonText: "取消"
                        },function(isConfirm){
                            if (isConfirm) {
                                window.location.href= _ctx + "/logout";
                            }
                        });
                        console.log( 'HTTP session超期或者不存在！' );
                    }else if(  code === -10003 ){
                        console.warn( 'HTTP session没有绑定！' );
                    }
                }

            }catch ( error ){
                console.error( error );
            }
        }
    }

    /**
     * 一个用于消息事件的事件监听器，
     * 这一事件当有消息到达的时候该事件会触发。
     * @type {null}
     */
    _socket.onmessage = null;
    /**
     * 用于监听连接关闭事件监听器
     * @type {null}
     */
    _socket.onclose = null;
    /**
     * 通过WebSocket连接向服务器发送数据
     * @param data
     * @returns {boolean}
     */
    _socket.send  = function ( data ) {

        if( !data ){
            console.warn( 'send: 参数 data 不能为空！' );
            return false;
        }

        if( _socket.state === 0 ){
            console.warn( 'send: 连接已关闭！' );
            return false;
        }

        if( _socket.state === 1 ){
            console.warn( 'send: WebSocket 没有登录！' );
            return false;
        }

        try {
            data.jsonrpc = '2.0';
            data.id = ++id;
            ws.send( JSON.stringify( data ) );
            return true;
        }catch ( error ){

            if( error === 'INVALID_STATE_ERR' ){
                console.warn( 'send: 当前连接的状态不是OPEN！' );
            }else if( error === 'SYNTAX_ERR' ){
                console.warn( 'send: 数据是一个包含unpaired surrogates的字符串！' );
            }
        }
        return false;
    };

    /**
     * 关闭WebSocket连接或停止正在进行的连接请求。如果连接的状态已经是closed，这个方法不会有任何效果
     * @param code 可选 ，一个数字值表示关闭连接的状态号，表示连接被关闭的原因
     * @param reason 可选 ， 一个可读的字符串，表示连接被关闭的原因
     */
    _socket.close = function ( code, reason ) {
        if( ws && _socket.state !== 0 ){
            try {
                ws.close( code, reason );
                _socket.state = 0;
                ws = null;
                id = 0;

            }catch ( error ){

                if( error === 'INVALID_ACCESS_ERR' ){
                    console.warn( 'close: 选定了无效的code！' );
                }else if( error === 'SYNTAX_ERR' ){
                    console.warn( 'close: reason 字符串太长或者含有unpaired surrogates！' );
                }
            }
        }else {
            console.warn( 'close: 连接已经关闭' );
        }
    };


    /**
     * webSocket连接状态
     * 0 未连接、1 连接成功、2 登录成功
     * @type {number}
     */
    _socket.state = 0;

    return _socket;

}());
