var TabClose = {};
layui.use('layer', function(){
    var layer = layui.layer;
    var nthTabs = $("#editor-tabs").nthTabs();

    $(function(){
        initEvent();
    });

    function initEvent(){
        //tab页点击监听事件
        $("#tabs-Cont").click(function (event) {
            if(event.target.classList.contains("tab-close")){
                //是关闭tab图标
                // var activeIndex = $("ul#tabs-Cont li.active").index();
                var tabActive = $("#contentView div.tab-pane.active");
                var designArray= tabActive.find("#design_mode");
                var exist = designArray.length;
                // 判断是否是有#design_code并且是设计模式的tab页
                if(exist === 1 && $(designArray[0]).css("display") === "none"){
                    event.preventDefault();
                    event.stopPropagation();
                    //是设计器页面并且在设计模式下
                    TabClose.saveDesignContent(event.target);
                }

                var menuid = event.target.parentNode.parentNode.getAttribute('menuid');

                // 关闭页面取消当前页面订阅事件
                PubSub.unsubscribePage(menuid);

            }
            return true;
        });
    }


    /**
     * 切换之前是否保存页面
     */
    TabClose.saveDesignContent = function(target){
        layer.confirm('是否保存当前页面?', {title:' 保存提示'}, function(index){
            SaveDesign.saveContent($("#areaId").val());
            $.when(SaveDesign.method).done(function () {
                //要执行的操作
                //关闭当前选项卡
                nthTabs.delTab();
            });
            layer.close(index);
        },function(index){
            layer.close(index);
            //关闭当前选项卡
            nthTabs.delTab();
        });
    }

    /**
     * 切换frame路径
     */
    TabClose.changeFrame = function(){
        var $frame = $("#design_frame_one");
        // var areaDom = $frame.contents().find("#areaId")[0];
        // var areaId = $(areaDom).val();
        var areaId = $("#designFrameBody #areaId").val();
        $frame.prop("src",_ctx + "/view/pageDesign/view?areaId="+areaId);
    }

});