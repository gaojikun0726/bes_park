/**
 * @author xiepufeng
 * 工具
 */

var utils = ( function () {

    var utils = {};

    /**
     * @param date { Date }
     * @param format { string } 日期格式
     * @returns { string }
     */
    utils.dateFormat =  function ( date, format ) {

        if( ! ( date instanceof Date )){
            console.warn( '不是日期对象！' );
            return '';
        }

        if( date.toString() === 'Invalid Date' ){

            console.warn( '无效的日期（Invalid Date）！' );
            console.warn( date );
            return '';
        }

        if( !format ){
            format = 'yyyy-MM-dd';
        }

        var form = {
            'M+' : date.getMonth() + 1,                      //月份
            'd+' : date.getDate(),                           //日
            'h+' : date.getHours(),                          //小时
            'm+' : date.getMinutes(),                        //分
            's+' : date.getSeconds(),                        //秒
            'q+' : Math.floor(( date.getMonth() + 3 ) / 3 ), //季度
            'S'  : date.getMilliseconds()                    //毫秒
        };
        if ( /(y+)/.test( format )) {
            format = format.replace( RegExp.$1, ( date.getFullYear() + '' )
                .substr( date.getFullYear().toString().length - RegExp.$1.length ));
        }

        for ( var k in form ){
            if ( new RegExp( '(' + k + ')' ).test( format )){
                format = format.replace( RegExp.$1, ( RegExp.$1.length == 1 ) ? ( form[ k ]) : (( '00' + form[ k ] ).substr(( '' + form[ k ]).length )));
            }
        }
        return format;
    };

    /**
     * url操作
     */
    utils.UrlParam =  function() { // url参数
        var data, index;
        ( function init() {
            data = [];
            index = {};
            var u = location.search.substr( 1 );
            if (u != '') {
                var params = decodeURIComponent( u ).split( '&' );
                for (var i = 0, len = params.length; i < len; i++) {
                    if ( params[i] != '' ) {
                        var p = params[i].split("=");
                        if (p.length == 1 || (p.length == 2 && p[1] == '')) {
                            data.push([ '' ]);
                            index[p[0]] = data.length - 1;
                        } else if (typeof(p[0]) == 'undefined' || p[0] == '') {
                            data[0] = [p[1]];
                        } else if (typeof(index[p[0]]) == 'undefined') {
                            data.push([p[1]]);
                            index[p[0]] = data.length - 1;
                        } else {// c=aaa
                            data[index[p[0]]].push(p[1]);
                        }
                    }
                }
            }
        })();
        return {

            // 获得参数,类似request.getParameter()
            param : function(o) { // o: 参数名或者参数次序
                try {
                    return (typeof(o) == 'number' ? data[o][0] : data[index[o]][0]);
                } catch (e) {
                }
            },
            //获得参数组, 类似request.getParameterValues()
            paramValues : function(o) { //  o: 参数名或者参数次序
                try {
                    return (typeof(o) == 'number' ? data[o] : data[index[o]]);
                } catch (e) {}
            },
            //是否含有parmName参数
            hasParam : function(parmName) {
                return typeof(parmName) == 'string' ? typeof(index[parmName]) != 'undefined' : false;
            },
            // 获得参数Map ,类似request.getParameterMap()
            paramMap : function() {
                var map = {};
                try {
                    for (var p in index) {  map[p] = data[index[p]];  }
                } catch (e) {}
                return map;
            }
        }
    }();

    /**
     * 判断是否为整数
     * @param number
     * @returns {boolean}
     */
    utils.isInteger = function ( number ) {

        if( number == null || number === '' ){
            return false;
        }


        if( number%1 === 0 ){
            return true;
        }

        return false;
        
    };

    Object.freeze( utils );

    return utils;

}());




