/**
 * 公共校验方法
 */
var CommonCheck = {};

layui.use('layer', function(){
    var layer = layui.layer;


    /**
     * 校验是否为空
     * @param value 需要校验的数据值
     * @param name 提示名称
     * @returns {boolean}
     */
    CommonCheck.checkEmpty = function(value,name){
        if((!value) || (!value.trim())){
            layer.msg(name + "不能为空",{icon:0});
            return false;
        }
        return true;
    }


    /**
     * 校验是否为数字（非负整数）
     * @param value 需要校验的数据值
     * @param name 提示名称
     * @returns {boolean}
     */
    CommonCheck.checkNumber = function(value,name){
        if(!name){
            name = "";
        }
        var reg = /^\d+$/;　　　
        if(value && !reg.test(value)){
            layer.msg(name + "请输入数字",{icon:0});
            return false;
        }　
        return true;
    }

    /**
     * 校验整数、小数、负数等数字
     * @param value
     * @param name
     * @returns {boolean}
     */
    CommonCheck.verifyRealNumber = function(value,name){
        if(!name){
            name = "";
        }
        var reg = /^-?((0(\.\d+)?)|[1-9]\d*(\.\d+)?)$/;
        // var reg = /^-?(0|([1-9]\d*))\.\d+|[1-9][0-9]*$/;
        if(value && !reg.test(value)){
            layer.msg(name + "请输入数字",{icon:0});
            return false;
        }
        return true;
    }


    /**
     * 校验数据长度
     * @param value 需要校验的数据值
     * @param name 提示名称
     * @param maxlength  最大长度
     * @returns {boolean}
     */
    CommonCheck.checkLength = function(value,name,maxlength){
        if(value && maxlength && value.length > maxlength){
            layer.msg(name + "长度超出最大值：" + maxlength,{icon:0});
            return false;
        }
        return true;
    }


    /**
     * 检验特殊字符
     * 匹配所有键盘上可见的非字母和数字的符号
     * @param value 需要校验的数据
     */
    CommonCheck.verifySpecialChar = function(value,name){
        if(!name){
            name = "";
        }
        // 这个是输入框防止特殊字符输入验证，包括键盘上所有特殊字符的英文和中文状态。
        // var patrn = /^[$]+$/;
        var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·！￥…（）—《》？：“”【】、；‘’，。]/im;
        // var patrn = /[`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘’，。、]/im;
        if (patrn.test(value)) {
            // 如果包含特殊字符返回false
            layer.msg(name + "不可包含特殊符号",{icon:0});
            return false;
        }
        return true;
    }







});