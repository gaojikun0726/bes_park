/**
 * 字体放大
 */
var TextSettingLabel = {
    changeTextControl:""
};
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;
    //字体设置弹窗
    var textSettingIndex;
    var currentIdEle;

    //可以调整字体的所有组件class
    var controlArray = ['design_debug_btn','design_channel_btn','design_scene_btn','design_point_btn','design_label','design_textbox'];


    /**
     * 添加点击事件--选中组件改变字体
     * @param target 被选中的组件
     */
    TextSettingLabel.clickEvent = function(target){

        $(".design_change_text").removeClass("design_change_text");
        var flag = false;
        for(var i = 0; i < controlArray.length; i++){
            if(target.classList.contains(controlArray[i])){
                $(target).addClass("design_change_text");
                TextSettingLabel.changeTextControl = $(target);
                flag = true;
                break;
            }
        }
        if(!flag){
            TextSettingLabel.changeTextControl = "";
        }
    }

    /**
     * 字体设置弹窗（新）
     */
    TextSettingLabel.openTextSettingWinNew = function(){
        var $dom = TextSettingLabel.changeTextControl;
        if($dom){
        textSettingIndex = layer.open({
            type: 1,
            title:"字体设置",
            area:['40vw','60vh'],
            // area:['400px','300px'],
            content: $('#design_text_setting_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                TextSettingLabel.closeTextSettingWin();
                return false;
            },
            success:function(){

                    var fontSize = $dom.css("font-size");
                    var fontFamily = $dom.css("font-family");
                    var fontWeight = $dom.css("font-weight");
                    var fontStyle = $dom.css("font-style");
                    var color = $dom.css("color");
                    var decoration = $dom.css("text-decoration");

                    var sizeValue = fontSize;
                    var familyValue = fontFamily;
                    var colorValue = "";
                    if(color.indexOf("rgba") >= 0){
                        colorValue = rgba2hex(color);
                    }else if(color.indexOf("rgb") >= 0){
                        colorValue = rgb2hex(color);
                    }

                    var fontValue = "";
                    var deleteValue = false;
                    var underValue = false;
                    //400 等同于 normal，而 700 等同于 bold。
                    if(fontWeight === "400" || fontWeight === "normal"){
                        fontValue = "normal";
                        if(fontStyle === "oblique" || fontStyle === "italic"){
                            //斜体
                            fontValue = "normal oblique";
                        }
                    }

                    if(fontWeight > "400" || fontWeight === "bold"){
                        //粗体
                        fontValue = "bold";
                        if(fontStyle === "oblique" || fontStyle === "italic"){
                            //粗体 斜体
                            fontValue = "bold oblique";
                        }
                    }

                    if(decoration.indexOf("line-through") >= 0){
                        deleteValue = true;
                    }
                    if(decoration.indexOf("underline") >= 0){
                        underValue = true;
                    }

                    $("#textSize").val(sizeValue);
                    $("#textFont").val(fontValue);
                    $("#textForm").val(familyValue);
                    $("#textColor").val(colorValue);
                    $("#deleteLine").prop("checked",deleteValue);
                    $("#underLine").prop("checked",underValue);
                    form.render();
            }
        });

        }else{
            layer.msg("请先选中文本");
        }
    }

    /**
     * 确定按钮点击事件
     */
    TextSettingLabel.settingConfirmNew = function(){
        var textForm = $("#textForm option:selected").val();   /*字体*/
        var textFont = $("#textFont option:selected").val();   /*字形*/
        var textSize = $("#textSize option:selected").val();   /*大小*/
        var textColor = $("#textColor option:selected").val();   /*颜色*/
        var deleteValue = $("#deleteLine").prop("checked");
        var underValue = $("#underLine").prop("checked");

        var $dom = TextSettingLabel.changeTextControl;
        if($dom){
            $dom.css("font-family",textForm);
            $dom.css("font-size",textSize);
            if(textFont.indexOf("oblique") >= 0){
                $dom.css("font-style","oblique");
            }
            if(textFont.indexOf("bold") >= 0){
                $dom.css("font-weight","bold");
            }
            if(textFont === "" || textFont === "normal"){
                $dom.css("font-weight","400");
                $dom.css("font-style","normal");
            }
            $dom.css("color",textColor);

            var decorationValue = "";
            if(deleteValue){
                decorationValue += "line-through" +"  ";
            }
            if(underValue){
                decorationValue += "underline";
            }
            if(decorationValue){
                $dom.css("text-decoration",decorationValue);
            }else{
                $dom.css("text-decoration","none");
            }
        }
        TextSettingLabel.closeTextSettingWin();
    }

    /**
     * rgba格式值转成16进制颜色值
     * @param color
     * @returns {string}
     */
    function rgba2hex(color) {
        var values = color
            .replace(/rgba?\(/, '')
            .replace(/\)/, '')
            .replace(/[\s+]/g, '')
            .split(',');
        var a = parseFloat(values[3] || 1),
            r = Math.floor(a * parseInt(values[0]) + (1 - a) * 255),
            g = Math.floor(a * parseInt(values[1]) + (1 - a) * 255),
            b = Math.floor(a * parseInt(values[2]) + (1 - a) * 255);
        return "#" +
            ("0" + r.toString(16)).slice(-2) +
            ("0" + g.toString(16)).slice(-2) +
            ("0" + b.toString(16)).slice(-2);
    }


    /**
     * 字体设置弹窗
     */
    TextSettingLabel.openTextSettingWin = function(){
        var textElement = $("#designTextValue").val();
        currentIdEle = $("#designIdValue").val();
        var currentEle = document.getElementById(currentIdEle);
        var textSize =document.getElementById(currentIdEle).style.fontSize;
        $("#textSize").val(textSize);
       var textForm = document.getElementById(currentIdEle).style.fontFamily;//字体
       $("#textForm").val(textForm);
        var textFont = document.getElementById(currentIdEle).style.fontWeight;//字形
        var textStyle = document.getElementById(currentIdEle).style.fontStyle;//倾斜
        if(textStyle != ""&&textFont!=""){//字形为倾斜 粗体
            var fontStyle = textFont+" "+textStyle;
            $("#textFont").val(fontStyle);
        }else if(textStyle != ""&&textFont ==""){//字形不倾斜
            $("#textFont").val(textStyle);
        }else{
            $("#textFont").val(textFont);
        }
        $("#textForm").val(textForm);
        var textColor = rgb2hex(document.getElementById(currentIdEle).style.color);//颜色
        $("#textColor").val(textColor);
        //复选框赋值
        var str = document.getElementById(currentIdEle).style.textDecoration;
        var lineStyle=str.split(" ");
        var boxes = document.getElementsByName("textLine");
        for(i=0;i<boxes.length;i++){
            for(j=0;j<lineStyle.length;j++){
                if(boxes[i].value == lineStyle[j]){
                    boxes[i].checked = true;
                    break
                }
            }
        }

        if(typeof(textElement) != undefined&&textElement!=""&&currentIdEle!=""&&currentIdEle!="design_area_demo"){
            textSettingIndex = layer.open({
                type: 1,
                title:"字体设置",
                area:['400px','300px'],
                content: $('#design_text_setting_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    TextSettingLabel.closeTextSettingWin();
                    return false;
                }
            });
            form.render();
        }else{
            layer.msg("请先选中文本");
        }

    }
/*rgb颜色转换*/
    function rgb2hex(rgb) {
        if(rgb != ""){
            rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
            function hex(x) {
                return ("0" + parseInt(x).toString(16)).slice(-2);
            }
            return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
        }

    }


    //取消按钮--关闭添加弹窗
    TextSettingLabel.closeTextSettingWin = function(){
        layer.close(textSettingIndex);
        $("#textSize").val("");
        $("#textForm").val("");
        $("#textFont").val("");
        $("#textColor").val("");
        $("input[name='textLine']").removeAttr("checked","checked");
        form.render();
    }

    TextSettingLabel.settingConfirm = function(){    /*text-decoration:underline*/
        var underlineStyle = "";
        var textForm = $("#textForm option:selected").val();   /*字体*/
        var textFont = $("#textFont option:selected").val();   /*字形*/
        var textSize = $("#textSize option:selected").val();   /*大小*/
        var textColor = $("#textColor option:selected").val();   /*颜色*/
        var lineValueList = $('input[name="textLine"]:checked');
        for (var i = 0; i < lineValueList.length; i++) {
            underlineStyle += $(lineValueList[i]).val()+"  ";
        }
        if(typeof(textFont)!="undefined"){
            if(textFont.indexOf("oblique") != -1&&textFont.indexOf("bold") == -1){//倾斜
                $('#'+currentIdEle).css('font-style',"oblique");
            }else if (textFont.indexOf("oblique") != -1&&textFont.indexOf("bold") != -1){ //倾斜 粗体
                $('#'+currentIdEle).css('font-style',"oblique");
                $('#'+currentIdEle).css('font-weight',"bold");
            }else{
                $('#'+currentIdEle).css('font-style',"");
                $('#'+currentIdEle).css('font-weight',textFont);
            }
        }

        $('#'+currentIdEle).css('text-decoration',underlineStyle);
        $('#'+currentIdEle).css('font-family',textForm);
        $('#'+currentIdEle).css('font-size',textSize);
        $('#'+currentIdEle).css('color',textColor);
        layer.close(textSettingIndex);

    }


});