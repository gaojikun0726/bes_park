var Top = {};


$(function(){
    Top.initEvent();
});

/**
 * 加载左侧菜单栏折叠展开实践
 */
Top.initEvent = function(){
    $(".top_control_menu").click(function(){
        if($("#expandIcon").css("display") === "none"){
            //目前是折叠状态，展开菜单栏
            $("#leftMenu").show();
            $("#mainContet").css("width","calc(100% - 240px)");

            $("#expandIcon").show();
            $("#foldIcon").hide();
        }else{
            $("#leftMenu").hide();
            $("#mainContet").css("width","100%");

            $("#expandIcon").hide();
            $("#foldIcon").show();
        }

    });
};


//fullScreen()和exitScreen()有多种实现方式，此处只使用了其中一种
//全屏
function topFullScreen() {
    var element = document.documentElement;
    if (element.requestFullscreen) {
        element.requestFullscreen();
    } else if (element.msRequestFullscreen) {
        element.msRequestFullscreen();
    } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
    } else if (element.webkitRequestFullscreen) {
        element.webkitRequestFullscreen();
    }

    $("#exitTopFullScreen").css("display","inline-block");
    $("#topFullScreen").css("display","none");
}

//退出全屏
function exitTopFullscreen() {
    if (document.exitFullscreen) {
        document.exitFullscreen();
    } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
    } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    } else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen();
    }

    $("#exitTopFullScreen").css("display","none");
    $("#topFullScreen").css("display","inline-block");
}

//监听window是否全屏，并进行相应的操作,支持esc键退出
window.onresize = function() {
    var isFull=!!(document.webkitIsFullScreen || document.mozFullScreen ||
        document.msFullscreenElement || document.fullscreenElement
    );//!document.webkitIsFullScreen都为true。因此用!!
    if (isFull==false) {
        $("#exitTopFullScreen").css("display","none");
        $("#topFullScreen").css("display","inline-block");
    }else{
        $("#exitTopFullScreen").css("display","inline-block");
        $("#topFullScreen").css("display","none");
    }
}