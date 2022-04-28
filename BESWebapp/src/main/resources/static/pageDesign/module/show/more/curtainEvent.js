var CurtainEvent = {};

layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var slider = layui.slider;

    var index;
    var curtainSlider;

    /**
     * 加载所有的图标状态
     */
    CurtainEvent.initAll = function(){
       CurtainEvent.initAllIcon();
       CurtainEvent.initClickEvent();
       CurtainEvent.initMouseEvent();
    };

    /**
     * 加载所有图标状态
     */
    CurtainEvent.initAllIcon = function(){
        var $curtains = $(".design_curtain");
        var idArray = [];
        for(var i = 0; i < $curtains.length; i++){
            var dataId = $($curtains[i]).attr("data-id");
            if(dataId){
                idArray.push(dataId);
            }
        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryTextboxListInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map && result.map.allData){
                        var typeData = result.map.allData;
                        for(var i = 0; i < $curtains.length; i++){
                            var dataId = $($curtains[i]).attr("data-id");
                            if(dataId){
                                // AO类型窗帘
                                for(var n = 0; n < typeData.length; n++){
                                    var nodeData = typeData[n];
                                    var sysname = nodeData.F_SYS_NAME;
                                    var init_val = nodeData.F_INIT_VAL;
                                    if(dataId === sysname){
                                        CurtainEvent.iconToggle($($curtains[i]),init_val);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    };

    /**
     * 图标切换--行程100为窗帘全开
     */
    CurtainEvent.iconToggle = function($curtain,switch_value){
        if(switch_value === "0"){
            //关
            $curtain.find(".blue_img").hide();
            $curtain.find(".gray_img").show();
        }else{
            // if(switch_value === "100"){
            //开
            $curtain.find(".blue_img").show();
            $curtain.find(".gray_img").hide();
        }
    };

    /**
     * 加载窗帘图标的点击事件
     */
    CurtainEvent.initClickEvent = function(){
        var $curtains = $(".design_curtain");
        $curtains.unbind("click").click(function(){
            var $curtain = $(this);
            var dataId = $curtain.attr("data-id");
            if(!dataId){
                layer.msg("请关联模块");
                return;
            }
            $("#electric_curtain_sysname").val(dataId);
            index = layer.open({
                type: 1,
                title:"",
                // area:['34vh','14vh'],
                // area:['54vh','14vh'],
                area:['48vh','14vh'],
                content: $('#electric_curtain_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){

                },
                success:function(){

                    CurtainEvent.getData($curtain);
                }
            });


        });
    };

    /**
     * 加载滑块
     */
    CurtainEvent.initSlide = function(initZoom){
        //渲染
        curtainSlider = slider.render({
            theme:"#4e8ae7",
            elem: '#electric_curtain_slide',  //绑定元素
            min:0,
            max:100,
            step:1,
            tips:true,
            input:true
            ,setTips: function(value){ //自定义提示文本
                return value + '%';
            }
            ,value:initZoom
            ,change: function(value){
            }
        });
        CurtainEvent.slideEvent();


        // var elem = document.querySelector('.js-min-max-start');//选择input元素
        // var init = new Powerange(elem, { min: 0, max: 100, start: initZoom });//实例化powerange类并且初始化参数
        // elem.onchange = function() {
        //     $('#js-display-change').val(elem.value);
        // };
    };


    /**
     * 查询获取单个窗帘的数据
     */
    CurtainEvent.getData = function($curtain){
        var idArray = [];
        var dataId = $curtain.attr("data-id");
        if(dataId){
            idArray.push(dataId);
        }
        if(idArray.length > 0){
            $.ajax({
                url     : _ctx + '/btnEventController/queryTextboxListInfo',
                type    : "post",
                data: JSON.stringify(idArray),
                async: false,
                contentType: 'application/json;charset=UTF-8',
                success : function(result) {
                    if(result && result.map && result.map.allData){
                        var typeData = result.map.allData;
                        var dataId = $curtain.attr("data-id");
                        if(dataId){
                            // AO类型窗帘
                            for(var n = 0; n < typeData.length; n++){
                                var nodeData = typeData[n];
                                var sysname = nodeData.F_SYS_NAME;
                                var init_val = nodeData.F_INIT_VAL;
                                if(dataId === sysname){
                                   //初始化滑块
                                    var value = Number(init_val);
                                    CurtainEvent.initSlide(value);
                                }
                            }
                        }
                        //注册回调事件
                        CurtainEvent.curtainWinRegister();
                    }
                }
            });
        }
    };

    /**
     * 窗帘弹窗--注册回调事件
     */
    CurtainEvent.curtainWinRegister = function(){
        var dataId = $("#electric_curtain_sysname").val();
        if(dataId){
            parent.PubSub.subscribe( dataId,CurtainEvent.curtainWinCallback, 'curtainWinCallback');
        }
    };

    /**
     * 窗帘弹窗--回调事件
     */
    CurtainEvent.curtainWinCallback = function(data){
        if(data) {
            var name = data.name;
            var f_init_val = data.value;
            var dataId = $("#electric_curtain_sysname").val();
            if(dataId === name){
                var val = Number(f_init_val);
                curtainSlider.setValue(val);
            }
        }
    };

    /**
     * 鼠标拖动滑块事件
     * 将这个方法单独拿出来，只执行一次，避免多次打开弹窗执行多次
     */
    CurtainEvent.initMouseEvent = function(){
        //滑块拖动或点击事件
        var dragFlag = false;
        $(document).mousedown(function(event){
            if(event.target.className === "layui-slider-wrap-btn"){
                //如果拖动的是滑块
                dragFlag = true;
            }else{
                dragFlag = false;
            }
        }).mouseup(function(){
            if(dragFlag){
                //如果拖动的是滑块，松开鼠标时触发下发指令事件
                CurtainEvent.orderEvent();
            }
        });
    };

    /**
     * 拖动滑块松开鼠标之后再下发指令
     */
    CurtainEvent.slideEvent = function(){

        //输入框失去焦点--下发指令
        $("#electric_curtain_slide .layui-slider-input input").blur(function(){
            CurtainEvent.orderEvent();
        }).keyup(function(){ //按键按下校验数字输入

            // var reg = /^[1-9]\d{0,2}|0$/g;
            var reg2 = /\D/;
            // if(!reg.test(this.value)){
            //
            // }
            this.value = this.value.replace(reg2,'');

            var val = Number(this.value);
                if(this.value.length > 3 || val > 100){
                    this.value = this.value.substring(0,this.value.length - 1);
                }
        });



        $(".layui-slider ").click(function(){
            //如果点击滑轨，触发事件
            CurtainEvent.orderEvent();
        });

        //加一按钮点击事件
        $(".electric_curtain_div .layui-icon-up").click(function(){
            CurtainEvent.orderEvent();
        });

        //减一按钮点击事件
        $(".electric_curtain_div .layui-icon-down").click(function(){
            CurtainEvent.orderEvent();
        });

    };


    /**
     * 下发窗帘行程
     */
    CurtainEvent.orderEvent = function(){
        var val = $("#electric_curtain_slide .layui-slider-input-txt input").val();
        var sysname = $("#electric_curtain_sysname").val();
        // console.log("val:"+val);
        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : sysname,
                // f_work_mode : $('input[name="work_mode"]:checked').val(),
                f_init_val : val
                // f_node_attribution : $("#f_node_attribution").val()
            }),
            success : function(result) {

                if(result.status === '0'){
                    if(result.msg){
                        layer.msg(result.msg,{icon:2});
                    }else{
                        layer.msg("下发失败",{icon:2});
                    }
                }else{
                    if(result.msg){
                        layer.msg(result.msg,{icon:1});
                    }else{
                        // layer.msg("下发成功",{icon:1});
                    }
                    DebugEvent.closeDebugWin();
                }
            },
            error : function(result) {
                layer.msg("下发失败",{icon:2});
            }
        });
    };


});


