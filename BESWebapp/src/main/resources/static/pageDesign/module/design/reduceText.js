/**
 * 字体放大
 */
var ReduceTextLabel = {};
layui.use(['layer','form'], function(){

    /**
     * 缩小选中组件的字号（新）
     */
    ReduceTextLabel.reduceTextNew = function(){
        var $dom = TextSettingLabel.changeTextControl;
        if($dom){
            var fontSize = $dom.css("font-size");
            fontSize = fontSize.replace("px","");
            fontSize = Number(fontSize);
            fontSize--;
            fontSize = fontSize + "px";
            $dom.css("font-size",fontSize);
        }else{
            layer.msg("请先选中文本");
        }
    }

    /**
     * 文本放大图标放大选中文本字号
     */
    ReduceTextLabel.reduceTextWin = function(){
        var textElement = $("#designTextValue").val();
        var idElement = $("#designIdValue").val();
        var ele = document.getElementById(idElement);
        if(typeof(textElement) != undefined&&textElement!=""&&idElement!=""&&idElement!="design_area_demo"){
            var size =window.getComputedStyle(ele).fontSize;
            size =  size.substring(0,size.length-2);
            size--;
            size = size+"px";
            $('#'+idElement).css('font-size',size);
        }else{
            layer.msg("请先选中文本");
        }
    }






});