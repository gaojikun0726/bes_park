/**
 * 放大设计页面
 */
var enlargePage = {};

layui.use(['layer','element','form','slider'], function(){
    var layer = layui.layer;
    var form = layui.form;
    var slider = layui.slider;

    var enlargeZoomLast = 1;
    var reduceZoomLast = 1;

    $(function(){
        // enlargePage.initEnlargeSlide();
        // enlargePage.initReduceSlide();
    });

    /**
     * 加载放大滑块
     */
    enlargePage.initEnlargeSlide = function(){
        var $enlargeNum = $("#design_area_demo .design_zoom_enlarge_num");
        var initZoom = 0;
        if($enlargeNum.length > 0){
            var init = $enlargeNum.text();
            if(init){
                initZoom = Number(init);
                enlargeZoomLast = initZoom;
                initZoom = (initZoom - 1) * 10;

            }
        }
        //渲染
        slider.render({
            elem: '#enlargeSlide',  //绑定元素
            min:0,
            max:10,
            step:1
            ,value:initZoom
            ,change: function(value){
            // console.log(value) //动态获取滑块数值
            //do something
                var realZoom = Number(value) / 10 + 1;
                enlargePage.enlargeAll(realZoom);
                enlargeZoomLast = realZoom;
                var $enlargeNum = $("#design_area_demo .design_zoom_enlarge_num");
                if($enlargeNum.length === 0){
                    //添加头部子元素
                    $("#design_area_demo").prepend('<div class="design_zoom_enlarge_num">'+realZoom+'</div>');
                }else{
                    $enlargeNum.text(realZoom);
                }

                /**
                 *  todo 保存放大倍数，便于还原放大，最好是整数倍，这样可以减少计算误差
                 *  缩小不合适，因为浏览器font-size最小支持8px,有诸多限制，所以只计算放大和还原。
                 */

        }
        });
    }

    /**
     * 加载缩小滑块
     */
    enlargePage.initReduceSlide = function(){
        //渲染
        slider.render({
            elem: '#reduceSlide',  //绑定元素
            min:0,
            max:10,
            step:1
            ,change: function(value){
                // console.log(value) //动态获取滑块数值
                //do something
                // var realZoom = Number(value) + 1;
                var realZoom = 1 + Number(value) / 10;
                if(realZoom === 0){
                    return;
                }
                // console.log("***************");
                // console.log("realZoom:" + realZoom);
                // console.log("reduceZoomLast:" + reduceZoomLast);
                // var realZoom = 2;
                enlargePage.reduceAll(realZoom);
                reduceZoomLast = realZoom;
            }
        });
    }

    /**
     * 点击放大图标显示弹窗
     */
    enlargePage.showEnlargeWin = function () {
        $(".reduce_win").hide();
        $(".enlarge_win").toggle();
    }

    /**
     * 点击缩小图标显示缩小滑块窗口
     */
    enlargePage.showReduceWin = function(){
        $(".enlarge_win").hide();
        $(".reduce_win").toggle();
    }

    /**
     * 所有组件加载放大或缩小效果
     */
    enlargePage.enlargeAll = function(zoomValue){
        enlargePage.enlargeBtn(zoomValue,".design_debug_btn");
        enlargePage.enlargeBtn(zoomValue,".design_channel_btn");
        enlargePage.enlargeBtn(zoomValue,".design_scene_btn");
        enlargePage.enlargeBtn(zoomValue,".design_point_btn");
        enlargePage.enlargeBtn(zoomValue,".design_img");
        enlargePage.enlargeBtn(zoomValue,".design_textbox");
        enlargePage.enlargeBtn(zoomValue,".design_label");
        enlargePage.enlargeBtn(zoomValue,".design_cad");
        enlargePage.enlargeBtn(zoomValue,".design_low_conditioner.icon_img");
        enlargePage.enlargeBtn(zoomValue,".design_middle_conditioner.icon_img");
        enlargePage.enlargeBtn(zoomValue,".design_table_div");
        enlargePage.enlargeBtn(zoomValue,".area");
        enlargePage.enlargeConditioner(zoomValue);

    }

    /**
     * 按钮、图片、标签等组件放大或缩小效果
     * @param realZoom
     * @param selector
     */
    enlargePage.enlargeBtn = function(realZoom,selector){
        // var realZoom = Number(zoomValue) / 10 + 1;
        var $parent = $("#design_area_demo");
        var $btn = $parent.find(selector);
        for(var i = 0; i < $btn.length; i++){
            var $item = $($btn[i]);
            var width = $item.css("width");
            var height = $item.css("height");
            var font_size = $item.css("font-size");
            var top = $item.css("top");
            var left = $item.css("left");
            width = width.replace("px","");
            height = height.replace("px","");
            font_size = font_size.replace("px","");
            top = top.replace("px","");
            left = left.replace("px","");

            var w = Number(width);
            var h = Number(height);
            var fs = Number(font_size);
            var tp = Number(top);
            var lt = Number(left);

            w = w / enlargeZoomLast * realZoom ;
            h = h / enlargeZoomLast * realZoom;
            fs = fs / enlargeZoomLast * realZoom;
            tp = tp / enlargeZoomLast * realZoom;
            lt = lt / enlargeZoomLast * realZoom;

            $item.css("width",w + "px");
            $item.css("height",h + "px");
            $item.css("font-size",fs + "px");
            $item.css("top",tp + "px");
            $item.css("left",lt + "px");
        }
    }


    /**
     * 温控器放大或缩小效果
     * @param realZoom
     */
    enlargePage.enlargeConditioner = function(realZoom){
        // var realZoom = Number(zoomValue) / 10 + 1;
        var $parent = $("#design_area_demo");
        var $btn = $parent.find(".design_low_conditioner.list_img,.design_middle_conditioner.list_img");
        for(var i = 0; i < $btn.length; i++){
            var $item = $($btn[i]);
            // var width = $item.css("width");
            // var height = $item.css("height");
            // var font_size = $item.css("font-size");
            var top = $item.css("top");
            var left = $item.css("left");
            // width = width.replace("px","");
            // height = height.replace("px","");
            // font_size = font_size.replace("px","");
            top = top.replace("px","");
            left = left.replace("px","");

            // var w = Number(width);
            // var h = Number(height);
            // var fs = Number(font_size);
            var tp = Number(top);
            var lt = Number(left);

            // w = w / enlargeZoomLast * realZoom ;
            // h = h / enlargeZoomLast * realZoom;
            // fs = fs / enlargeZoomLast * realZoom;
            tp = tp / enlargeZoomLast * realZoom;
            lt = lt / enlargeZoomLast * realZoom;

            // $item.css("width",w + "px");
            // $item.css("height",h + "px");
            // $item.css("font-size",fs + "px");
            $item.css("top",tp + "px");
            $item.css("left",lt + "px");

        }
    }


    /**
     * 所有组件加载放大或缩小效果
     */
    enlargePage.reduceAll = function(zoomValue){
        enlargePage.reduceBtn(zoomValue,".design_debug_btn");
        enlargePage.reduceBtn(zoomValue,".design_channel_btn");
        enlargePage.reduceBtn(zoomValue,".design_scene_btn");
        enlargePage.reduceBtn(zoomValue,".design_point_btn");
        enlargePage.reduceBtn(zoomValue,".design_img");
        enlargePage.reduceBtn(zoomValue,".design_textbox");
        enlargePage.reduceBtn(zoomValue,".design_label");
        enlargePage.reduceBtn(zoomValue,".design_cad");
        enlargePage.reduceBtn(zoomValue,".design_low_conditioner.icon_img");
        enlargePage.reduceBtn(zoomValue,".design_middle_conditioner.icon_img");
        enlargePage.reduceConditioner(zoomValue);

    }

    /**
     * 按钮、图片、标签等组件放大或缩小效果
     * @param realZoom
     * @param selector
     */
    enlargePage.reduceBtn = function(realZoom,selector){
        // var realZoom = Number(zoomValue) / 10 + 1;
        var $parent = $("#design_area_demo");
        var $btn = $parent.find(selector);
        for(var i = 0; i < $btn.length; i++){
            var $item = $($btn[i]);
            var width = $item.css("width");
            var height = $item.css("height");
            var font_size = $item.css("font-size");
            var top = $item.css("top");
            var left = $item.css("left");
            width = width.replace("px","");
            height = height.replace("px","");
            font_size = font_size.replace("px","");
            top = top.replace("px","");
            left = left.replace("px","");

            var w = Number(width);
            var h = Number(height);
            var fs = Number(font_size);
            var tp = Number(top);
            var lt = Number(left);

            w = w * reduceZoomLast / realZoom ;
            h = h * reduceZoomLast / realZoom;
            fs = fs * reduceZoomLast / realZoom;
            tp = tp * reduceZoomLast / realZoom;
            lt = lt * reduceZoomLast / realZoom;

            $item.css("width",w + "px");
            $item.css("height",h + "px");
            $item.css("font-size",fs + "px");
            $item.css("top",tp + "px");
            $item.css("left",lt + "px");
        }
    }


    /**
     * 温控器放大或缩小效果
     * @param realZoom
     */
    enlargePage.reduceConditioner = function(realZoom){
        // var realZoom = Number(zoomValue) / 10 + 1;
        var $parent = $("#design_area_demo");
        var $btn = $parent.find(".design_low_conditioner.list_img,.design_middle_conditioner.list_img");
        for(var i = 0; i < $btn.length; i++){
            var $item = $($btn[i]);
            // var width = $item.css("width");
            // var height = $item.css("height");
            // var font_size = $item.css("font-size");
            var top = $item.css("top");
            var left = $item.css("left");
            // width = width.replace("px","");
            // height = height.replace("px","");
            // font_size = font_size.replace("px","");
            top = top.replace("px","");
            left = left.replace("px","");

            // var w = Number(width);
            // var h = Number(height);
            // var fs = Number(font_size);
            var tp = Number(top);
            var lt = Number(left);

            // w = w / enlargeZoomLast * realZoom ;
            // h = h / enlargeZoomLast * realZoom;
            // fs = fs / enlargeZoomLast * realZoom;
            tp = tp * reduceZoomLast / realZoom;
            lt = lt * reduceZoomLast / realZoom;

            // $item.css("width",w + "px");
            // $item.css("height",h + "px");
            // $item.css("font-size",fs + "px");
            $item.css("top",tp + "px");
            $item.css("left",lt + "px");

        }
    }

});