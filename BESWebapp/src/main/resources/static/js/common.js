
function getContextPath(){
	var pathName = document.location.pathname;   
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+2);   
	return result;  
}  

//js获取项目根路径，如： http://localhost:8083/uimcardprj  
function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPaht=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/uimcardprj  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return(localhostPaht+projectName);  
}  

/********处理顶部模块菜单，当模块太多显示不开时增加左右移动按钮 BEGIN*******/
$(document).ready(function() {
	$(window).bind("resize", function() {
		processTopModel();
	});
})

function processTopModel() {
	//获取所有li宽度
	var litotalWidth = getAllLiWidth()+100;
	//获取中间区域宽度
	var top_centerWidth = getTopCeterWidth();
	
	if (top_centerWidth < litotalWidth) {
		$('#jt_left').addClass('fa fa-backward');
		$('#jt_right').addClass('fa fa-forward');
	} else {
		$('#jt_left').removeClass('fa fa-backward');
		$('#jt_right').removeClass('fa fa-forward');
		reduction();
	}
}

function getAllLiWidth(){
	var litotalWidth = 0;
	$('#issp_top_top>ul>li').each(function() {
		litotalWidth += parseFloat($(this).width())
	})
	return litotalWidth;
}

function getTopCeterWidth(){
	var totalWidth = $(window).width();
	/*var top_leftWidth = $('.logo_top').width();
	var photoWidth = $('#issp_top_top').width()*0.61;*/
	var top_centerWidth = totalWidth*0.65;
	return top_centerWidth;
}

function left_click() {
    var j = $("#topMenu").css("marginLeft").replace("px", "");
    var k = parseFloat(j) + getTopCeterWidth()/2 + 40;
    $("#topMenu").css("margin-left", k > 40 ? 40 : k)
}

function right_click() {

    var j = $("#topMenu").css("marginLeft").replace("px", "");
    var k = parseFloat(j) - getTopCeterWidth()/2;
    if (getAllLiWidth() - Math.abs(j) <= getTopCeterWidth()-160) {
        return false
    }
    $("#topMenu").css("margin-left", k);
}

function reduction() {
	$("#jt_left").removeClass("fa fa-backward", function() {
		$("#topMenu").css("margin-left", "36px");
	})
}
/********处理顶部模块菜单，当模块太多显示不开时增加左右移动按钮 END*******/