
var Transform = {
    restoreBtn:"",
    scaleNum:1
};
/**
 *     页面鼠标滚轮缩放效果
 * @type {jQuery|HTMLElement}
 */

layui.use(['layer'], function(){


// var $content = $("#design_frame_one").contents();
// var restoreBtn = $content.find("#restoreBtn");

//以鼠标位置为中心的页面滚动放大缩小


    //
    // $(function(){
    //
    //
    // });

    Transform.transformEvent = function(){
        Transform.restoreBtn = $(window.parent.document.getElementById("restoreBtn"));
        Transform.restoreBtn.click(restoreTransform);
        // var scaleNum = 1;
        var explorer = navigator.userAgent;

        if(explorer.indexOf("Firefox") >= 0){
            $("#designDiv").on("DOMMouseScroll",function(e){

                var event = window.event;
                var detail = event.detail;
                var x = event.clientX;
                var y = event.clientY;
                var num = detail < 0 ? 0.1 : -0.1;
                Transform.scaleNum += num;
                if(Transform.scaleNum < 0.2 || Transform.scaleNum > 10){
                    return;
                }
                this.style = "transform:scale("+Transform.scaleNum+","+Transform.scaleNum+");transform-origin:"+x +"px " +y+ "px;";

                Transform.restoreBtn.show();
            });
        }else {
            //if(explorer.indexOf("Chrome") >= 0)
            mouseWheelEvent();
        }


    }

    function mouseWheelEvent(){
        $(document).on("mousewheel","#designDiv",function(event){
            var oImg = this;
            var ev = event || window.event;//返回WheelEvent
            // ev.preventDefault();
            var delta = window.event.wheelDelta;
            // var delta = ev.detail ? ev.detail > 0 : ev.wheelDelta < 0;
            var ratioL = (ev.clientX - oImg.offsetLeft) / oImg.offsetWidth,
                ratioT = (ev.clientY - oImg.offsetTop) / oImg.offsetHeight,
                // ratioDelta = !delta ? 1 + 0.1 : 1 - 0.1,
                ratioDelta = delta > 0 ? 1 + 0.1 : 1 - 0.1,
                w = parseInt(oImg.offsetWidth * ratioDelta),
                h = parseInt(oImg.offsetHeight * ratioDelta),
                l = Math.round(ev.clientX - (w * ratioL)),
                t = Math.round(ev.clientY - (h * ratioT));

            var x = ev.clientX;
            var y = ev.clientY;
            // var x = ev.offsetX;
            // var y = ev.offsetY;
            var num = delta > 0 ? 0.1 : -0.1;
            Transform.scaleNum += num;
            // with(oImg.style) {
            //     // width = w +'px';
            //     // height = h +'px';
            //     left = l +'px';
            //     top = t +'px';
            // }
            if(Transform.scaleNum < 0.2 || Transform.scaleNum > 10){
                return;
            }
            // oImg.style = "transform:scale("+Transform.scaleNum+","+Transform.scaleNum+");transform-origin:"+x +"px " +y+ "px;";
            $("#designDiv").css("transform","scale("+Transform.scaleNum+","+Transform.scaleNum+")")
                .css("transform-origin",x +"px " +y+ "px");
            // if(scaleNum !== 1){
            Transform.restoreBtn.show();
            // }else{
            //     restoreBtn.hide();
            // }
        });
    }






//还原缩放
    function restoreTransform(){
        // var $content = $("#design_frame_one").contents();
        // var dom = $content.find("#designDiv")[0];
        var dom = document.getElementById("designDiv");
        dom.style.transform="";
        dom.style.transformOrigin = "";
        Transform.restoreBtn.hide()
    }

});



// var scrollFunc = function (e) {
//     e = e || window.event;
//     if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件
//         if (e.wheelDelta > 0) { //当滑轮向上滚动时
//             //事件
//         }
//         if (e.wheelDelta < 0) { //当滑轮向下滚动时
//             //事件
//         }
//     } else if (e.detail) {  //Firefox滑轮事件
//         if (e.detail> 0) { //当滑轮向上滚动时
//             //事件
//         }
//         if (e.detail< 0) { //当滑轮向下滚动时
//             //事件
//         }
//     }
// }
// //给页面绑定滑轮滚动事件
// if (document.addEventListener) {//firefox
//     document.addEventListener('DOMMouseScroll', scrollFunc, false);
// }
// //滚动滑轮触发scrollFunc方法  //ie 谷歌
// window.onmousewheel = document.onmousewheel = scrollFunc;