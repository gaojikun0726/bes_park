


/**
 * 公共方法
 */
var CommonMethod = {};

layui.use('layer', function(){
    var layer = layui.layer;

    /**
     * 获取当前时间 00:00
     */
   CommonMethod.getCurrentTime =  function(){
        var now = new Date();
        var hours = now.getHours();
        var minutes = now.getMinutes();
        if(hours < 10){
            hours = "0" + hours;
        }
        if(minutes < 10){
            minutes = "0" + minutes;
        }
        return hours +":" + minutes;
    }


    /**
     * 不足位数以0补足位数
     * 自定义函数名：PrefixZero
     * @param num： 被操作数
     * @param n： 固定的总位数
     */
    CommonMethod.prefixZero = function(num, n) {
        return (Array(n).join(0) + num).slice(-n);
    }

});