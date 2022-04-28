var CopyPaste = {
    //复制的组件
    clone:{
        //复制的元素类型
        type:"",
        //复制的元素html
        html:"",
        //复制元素的关联点
        dataId:"",
        //关联配置
        config:"",
        //场景元素的关联配置数据
        configscene:"",
        //图片元素显示的图片
        src:"",
        //复制元素的高度，宽度
        width:"",
        height:"",
        //字体大小
        fontSize:"",
        //字体颜色
        fontColor:"",
        //字体粗细
        fontWeight:"",
        //字体
        fontFamily:"",
        //字体下划线等样式
        textDecoration:"",
        //斜体
        fontStyle:"",
        //复制元素的名称
        name:"",
        //鼠标悬浮显示title
        title:"",
        //粘贴的坐标位置
        x:"",
        y:""
    },
};
/**
 * 复制粘贴按钮、图片、文本框、标签等常用工具
 */
layui.use('layer', function(){
    var layer = layui.layer;


    $(function () {
        CopyPaste.initEvent();
    });

    /**
     * 页面加载完成后注册事件
     */
    CopyPaste.initEvent = function(){
        $("#design_area_demo").click(function(e){
            if(CopyPaste.clone.html){
                CopyPaste.clone.x = e.offsetX;
                CopyPaste.clone.y = e.offsetY;
            }
        });

        // $("#design_area_demo").on("focus",function(e){
            // if(CopyPaste.clone.html){
            //     CopyPaste.clone.x = e.offsetX;
            //     CopyPaste.clone.y = e.offsetY;
            // }
            $("#design_area_demo").unbind("keydown").keydown(function(e) { // 在页面任意位置点击而触发此事件
                if(CopyPaste.clone.html){
                    if(event.ctrlKey && event.keyCode !== 17){
                        if(event.keyCode === 86){
                            // alert("你使用了Ctrl+V组合键功能！");
                            // event.keyCode = 0;
                            if(CopyPaste.clone.type === "debug"){
                                CopyPaste.pasteDebug();
                            }
                            if(CopyPaste.clone.type === "channel"){
                                CopyPaste.pasteChannel();
                            }
                            if(CopyPaste.clone.type === "scene"){
                                CopyPaste.pasteScene();
                            }
                            if(CopyPaste.clone.type === "point"){
                                CopyPaste.pastePoint();
                            }
                            if(CopyPaste.clone.type === "img"){
                                CopyPaste.pasteImg();
                            }
                            if(CopyPaste.clone.type === "label"){
                                CopyPaste.pasteLabel();
                            }
                            if(CopyPaste.clone.type === "textbox"){
                                CopyPaste.pasteTextbox();
                            }

                        }
                        event.preventDefault();
                        event.stopPropagation();

                        $("#design_svg").show();
                        //复制出来的组件可以继续复制
                        CopyPaste.allCopyEvent();
                    }
                }
            });
        // });
    }

    /**
     * 复制粘贴的特殊处理
     */
    CopyPaste.specialHandle = function(){

        // $("#design_svg").hide();

        //设置需要复制、粘贴的元素可以选中（支持focus事件）
        $("#design_area_demo").attr("tabindex","0");

        $(".design_debug_btn").attr("tabindex","0");
        $(".design_channel_btn").attr("tabindex","0");
        $(".design_scene_btn").attr("tabindex","0");
        $(".design_point_btn").attr("tabindex","0");
        $(".design_img").attr("tabindex","0");
        $(".design_textbox").attr("tabindex","0");
        $(".design_label").attr("tabindex","0");
    }

    /**
     * 所有的复制事件
     */
    CopyPaste.allCopyEvent = function(){
        CopyPaste.copyDebug();
        CopyPaste.copyChannel();
        CopyPaste.copyScene();
        CopyPaste.copyPoint();
        CopyPaste.copyImg();
        CopyPaste.copyTextbox();
        CopyPaste.copyLabel();

        CopyPaste.specialHandle();
    }

    /**
     * 复制公共数据部分
     */
    CopyPaste.copyCommon = function($dom){
        //隐藏svg标签，避免影响下层div元素复制粘贴
        $("#design_svg").hide();

        CopyPaste.clone.title = $dom.attr("title") || "";
        CopyPaste.clone.html = $dom[0].outerHTML;
        CopyPaste.clone.dataId = $dom.attr("data-id") || "";
        CopyPaste.clone.name = $dom.text() || "";
        CopyPaste.clone.width = $dom.css('width');
        CopyPaste.clone.height = $dom.css('height');
        CopyPaste.clone.fontSize = $dom.css("font-size");
        CopyPaste.clone.fontColor = $dom.css("color");
        CopyPaste.clone.fontWeight = $dom.css("font-weight");
        CopyPaste.clone.fontFamily = $dom.css("font-family");
        CopyPaste.clone.textDecoration = $dom.css("text-decoration");
        CopyPaste.clone.fontStyle = $dom.css("font-style");
    }


    /**
     * 调试按钮--复制方法
     */
    CopyPaste.copyDebug = function(){
        $(".design_debug_btn").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "debug";
                        CopyPaste.copyCommon($(this));
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }

    /**
     * 单通道按钮--复制方法
     */
    CopyPaste.copyChannel = function(){
        $(".design_channel_btn").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "channel";
                        CopyPaste.copyCommon($(this));
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }
    /**
     * 场景按钮--复制方法
     */
    CopyPaste.copyScene = function(){
        $(".design_scene_btn").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "scene";
                        CopyPaste.copyCommon($(this));

                        //不要使用data("config")获取配置数据，容易取缓存中的值，页面dom属性发生改变容易出错。推荐使用attr("data-config")
                        CopyPaste.clone.config = $(this).attr("data-config") || "";
                        CopyPaste.clone.configscene = $(this).attr("data-configscene") || "";
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }
    /**
     * 点位置按钮--复制方法
     */
    CopyPaste.copyPoint = function(){
        $(".design_point_btn").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "point";
                        CopyPaste.copyCommon($(this));
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }
    /**
     * 图片--复制方法
     */
    CopyPaste.copyImg = function(){
        $(".design_img").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "img";
                        CopyPaste.copyCommon($(this));

                        CopyPaste.clone.config = $(this).attr("data-config") || "";
                        CopyPaste.clone.src = $(this).attr("src") || "";


                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }
    /**
     * 文本框--复制方法
     */
    CopyPaste.copyTextbox = function(){
        $(".design_textbox").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "textbox";
                        CopyPaste.copyCommon($(this));

                        CopyPaste.clone.config = $(this).attr("data-config") || "";
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    }
    /**
     * 标签--复制方法
     */
    CopyPaste.copyLabel = function(){
        $(".design_label").unbind("focus").on("focus",function(){
            $(this).unbind("keydown").keydown(function(e) {
                if(event.ctrlKey && event.keyCode !== 17){
                    if(event.keyCode === 67){
                        CopyPaste.clone.type = "label";
                        CopyPaste.copyCommon($(this));

                        CopyPaste.clone.config = $(this).attr("data-config") || "";
                    }
                    event.preventDefault();
                    event.stopPropagation();
                }
            })
        });
    };


    /**
     * 拼接粘贴元素的style属性
     */
    CopyPaste.getStyle = function(){
        var style = 'style="width:'+CopyPaste.clone.width+';height:'+CopyPaste.clone.height
            +';top:'+CopyPaste.clone.y+'px;left:'+CopyPaste.clone.x+'px;font-size:'+ CopyPaste.clone.fontSize+';color:'
            +CopyPaste.clone.fontColor+';font-weight:'+CopyPaste.clone.fontWeight+';font-family:'
            +CopyPaste.clone.fontFamily+';text-decoration:'+CopyPaste.clone.textDecoration+';font-style:'
            +CopyPaste.clone.fontStyle+';"';
        return style;
    }

    /**
     * 调试按钮--粘贴方法
     */
    CopyPaste.pasteDebug = function(){
       var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<a class="btn btn-primary design_initial_position design_debug_btn" '+style
            +' data-id="'+CopyPaste.clone.dataId+'" title="'+CopyPaste.clone.title+'">'+CopyPaste.clone.name+'</a>');
        $( ".design_area .design_debug_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_debug_btn").attr("tabindex","0");
    }


    /**
     * 单通道按钮--粘贴方法
     */
    CopyPaste.pasteChannel = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<a class="design_initial_position design_channel_btn gray" '+style+' type="button"'
            +' data-id="'+CopyPaste.clone.dataId
        +'"  title="'+CopyPaste.clone.title
            +'" >'+CopyPaste.clone.name+'</a>');
        $( ".design_area .design_channel_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_channel_btn").attr("tabindex","0");
    }

    /**
     * 场景按钮--粘贴方法
     */
    CopyPaste.pasteScene = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<a class="design_initial_position design_scene_btn gray" '+style
            +' type="button" '+' data-id="'+CopyPaste.clone.dataId +'" data-config="'+CopyPaste.clone.config
            +'"  data-configscene="'+CopyPaste.clone.configscene
            +'"  title="'+CopyPaste.clone.title
            +'" >'+CopyPaste.clone.name+'</a>');
        $( ".design_area .design_scene_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_scene_btn").attr("tabindex","0");
    }

    /**
     * 点位置按钮--粘贴方法
     */
    CopyPaste.pastePoint = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<a class=" design_initial_position design_point_btn gray" '+style
            +' data-id="'+CopyPaste.clone.dataId
            +'"  title="'+CopyPaste.clone.title
            +'" >'+CopyPaste.clone.name+'</a>');
        $( ".design_area .design_point_btn" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_point_btn").attr("tabindex","0");
    }

    /**
     * 图片--粘贴方法
     */
    CopyPaste.pasteImg = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<img class="design_img" src="'+ CopyPaste.clone.src
            +'" data-id="'+CopyPaste.clone.dataId +'" data-config="'+CopyPaste.clone.config
            +'"  title="'+CopyPaste.clone.title
            +'" '+style+'>');
        $("#design_area_demo .design_img").unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_img").attr("tabindex","0");
    }
    /**
     * 文本框--粘贴方法
     */
    CopyPaste.pasteTextbox = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<div class="design_initial_position design_textbox"'+style
            +' data-id="'+CopyPaste.clone.dataId +'" data-config="'+CopyPaste.clone.config
            +'"  title="'+CopyPaste.clone.title
            +'"></div>');
        $( ".design_area .design_textbox" ).unbind("click").click(AttributeWin.clickEvent).draggable().resizable({handles:"all"});
        CopyPaste.clone = {};
        $(".design_textbox").attr("tabindex","0");
    }
    /**
     * 标签--粘贴方法
     */
    CopyPaste.pasteLabel = function(){
        var style = CopyPaste.getStyle();
        $("#design_area_demo").append('<span class="design_initial_position design_label"'+style
            +' data-id="'+CopyPaste.clone.dataId +'" '
            +' data-config="'+CopyPaste.clone.config +'" '
            +' title="'+CopyPaste.clone.title
            +'" >'+CopyPaste.clone.name+'</span>');
        $( ".design_area .design_label" ).unbind("click").click(AttributeWin.clickEvent).draggable();
        CopyPaste.clone = {};
        $(".design_label").attr("tabindex","0");
    }




    // $("#design_area_demo").on("keypress",function(e) { // 在页面任意位置点击而触发此事件
    //     if(CopyPaste.clone.html){
    //         if(event.ctrlKey && event.keyCode !== 17){
    //             if(event.keyCode === 86){
    //                 alert("你使用了Ctrl+V组合键功能！");
    //                 // event.keyCode = 0;
    //                 CopyPaste.cloneDebugBtn(x,y);
    //             }
    //             event.preventDefault();
    //             event.stopPropagation();
    //         }
    //     }
    // })




});
