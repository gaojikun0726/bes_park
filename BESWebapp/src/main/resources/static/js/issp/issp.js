/*
//hash全局变量
var GLOBAL = {};
// str :表示要生成的命名空间，例如: GLOBAL.A.name 或是 A.name
GLOBAL.namespace = function(str) {
	var arr = str.split(".");
	var o = GLOBAL;
	var i = (arr[0] == "GLOBAL" ? 1 : 0); // 如果str中的第一部分为GLOBAL的话，命名空间从第二个开始计算
	for (; i < arr.length; i++) {
		o[arr[i]] = o[arr[i]] || {};
		o = o[arr[i]];
	}
}
*/

$(function(){
	initEvent();
});

function initEvent(){
	//tab页点击监听事件
	/*
	 * tab页点击事件
	 */

	$("#tabs-Cont").on("click", "li", function() {
		var tabLi = $(this).closest('li');
		isspGlobal.getLeftMeuns(tabLi);
		setTimeout(function() {
			// $(window).resize();
			$(window).trigger('resize');
		}, 1);
	});
}
// 全屏
function fullscreen(docElm) {
	if (docElm == null) {
		docElm = document.documentElement;
	}
	if (docElm.requestFullscreen) {
		docElm.requestFullscreen();
	} else if (docElm.mozRequestFullScreen) {
		docElm.mozRequestFullScreen();
	} else if (docElm.webkitRequestFullScreen) {
		docElm.webkitRequestFullScreen();
	} else if (docElm.msRequestFullscreen) {
		docElm.msRequestFullscreen();
	}
}

// 退出全屏
function exitFullscreen() {
	if (document.exitFullscreen) {
		document.exitFullscreen();
	} else if (document.mozCancelFullScreen) {
		document.mozCancelFullScreen();
	} else if (document.webkitCancelFullScreen) {
		document.webkitCancelFullScreen();
	} else if (document.msExitFullscreen) {
		document.msExitFullscreen();
	}
}

// 监听是否全屏
window.onload = function() {
	var elem = document.getElementById('fullScreenText');
	document.addEventListener('fullscreenchange', function() {
		var isFull = document.fullscreen ? 'yes' : 'no';
		sessionStorage.setItem("isFullScreen", isFull);
		if(isFull == "no"){
			$("#contentView").css("height","95%");
		}
	}, false);
	document.addEventListener('mozfullscreenchange', function() {
		var isFull = document.mozFullScreen ? 'yes' : 'no';
		sessionStorage.setItem("isFullScreen", isFull);
		if(isFull == "no"){
			$("#contentView").css("height","95%");
		}
	}, false);
	document.addEventListener('webkitfullscreenchange', function() {
		var isFull = document.webkitIsFullScreen ? 'yes' : 'no';
		sessionStorage.setItem("isFullScreen", isFull);
		if(isFull == "no"){
			$("#contentView").css("height","95%");
		}
	}, false);
	document.addEventListener('msfullscreenchange', function() {
		var isFull = document.msFullscreenElement ? 'yes' : 'no';
		sessionStorage.setItem("isFullScreen", isFull);
		if(isFull == "no"){
			$("#contentView").css("height","95%");
		}
	}, false);
}

/* 
 * AJAX请求显示、关闭‘正在加载’样式 开始
 */
function showLoad() {
	// 根据class获取当前活动窗口div的id
	var divID = $(".tab-pane.active").attr('id');
	var div = document.createElement('div');
	div.id = "load" + divID;
	div.innerHTML = '<img id="img'+ divID + '" class="request-loading"  src="images/loading5.gif" style=""/>'
//			+'<div style="position:absolute;left:39%; top:30%; background:#9db1cf2b; width:24%; height:38%;"></div> '
//			+'<button onclick="closeLoad()" style="z-index:999;top:30%;right:37%;color:#44c5f5;width:3vh;height:3vh;border-radius: 5px;background: #0f689085;position:absolute;display:block;"id="close'
//			+ divID + '" type="button"><i class="fa fa-times" aria-hidden="true"></i></button>'
	$("#" + divID).append(div);
	// setTimeout(function(){
	// $('#close'+divID).css('display','block');
	// },3000)
}

function closeLoad() {
	var divID = $(".tab-pane.active").attr('id');
	$('#load' + divID).css('display', 'none');
}

/*
 * 移除‘正在加载’样式
 */
function hiddenLoad() {
	var divID = $(".tab-pane.active").attr('id');
	$('#load' + divID).remove();
}
/* AJAX请求显示、关闭‘正在加载’样式 结束*/
/**
 * 判断一个tab页是否活动
 * @param divId
 * @returns
 */
function judgeActive(divId){
	if($("#"+ divId).parents(".tab-pane").eq(0).hasClass("active")){
		return true;
	}else{
		return false;
	}
}


/*设置模态框可拖动*/
$(document).on("show.bs.modal", ".modal", function(){
    $(this).draggable({
		handle:".modal-header"
	});
    //$(this).css("overflow-y", "scroll");   
    // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
});

/* 方式一：自己组织生成树结构 先不用这种方式 开始
function loadMenu(moduleId) {
	$('#leftMenu').empty();
	$.ajax({
		url : webroot + "/issp_left/" + moduleId,
		type : "get",
		success : function(data) {
			// $("#leftMenu").html(data);
			var showlist = $("<ul id='side-menu'></ul>");
			createTree(data.menu_session, showlist);
			$('#leftMenu')
					.append(
							'<div class="navissp-top"><div id="mini" style="border-bottom:1px solid rgba(255,255,255,.1)"><img src="images/mini.png" style="height: 43px;"></div></div>');
			$('#leftMenu').append(showlist);
		}
	});
}

function createTree(menu_list, parent) {
	var menu;
	for (var i = 0; i < menu_list.length; i++) {
		menu = menu_list[i];
		if (menu.children != null && menu.children.length > 0) {
			// 创建一个子节点li
			var li = $("<li class='navissp-item' menuId='" + menu.id
					+ "' url='" + menu.url + "' opentype='" + menu.openType
					+ "' fullwithMenu='" + menu.fullScreen
					+ "'><a href='#'><i class='fa " + menu.cssClass
					+ "'></i> <span class='navissp-label'>" + menu.name
					+ "</span><i class='my-icon navissp-more'></a></li>");
			// 将li的文本设置好，并马上添加一个空白的ul子节点，并且将这个li添加到父亲节点中
			$(li).append("<ul></ul>").appendTo(parent);
			// 将空白的ul作为下一个递归遍历的父亲节点传入
			createTree(menu.children, $(li).children().eq(1));
		} else {
			$(
					"<li menuId='"
							+ menu.id
							+ "' url='"
							+ menu.url
							+ "' opentype='"
							+ menu.openType
							+ "' fullwithMenu='"
							+ menu.fullScreen
							+ "'><a href='#'><span class='navissp-label'><i class='fa "
							+ menu.cssClass + "'></i>&nbsp;&nbsp;" + menu.name
							+ "</span></a></li>").appendTo(parent);
		}
	}
}
方式一：自己组织生成树结构 先不用这种方式 结束*/
;
var isspGlobal = (function($, window, document, undefined) {
	var webroot = getRootPath();
	var contextPath = getContextPath();
	var nthTabs = $("#editor-tabs").nthTabs();
	var moduleId;// 顶部 模块Id
	var menuId;// 左侧 菜单Id
	var showLeftMenus;
	var left_cols_mark=1;//首页左侧悬浮侧边栏标记  关闭状态

	// /*
	//  *	点击顶部菜单执行
	//  */
	// $('li', $('#topMenu')).on('click', function(e) {
	// 	$(this).addClass("selected").siblings().removeClass("selected");
	// 	// e.stopPropagation();
	// 	var lis = $(this).closest('li');
	// 	moduleId = $(lis).attr('moduleId');
	// 	showLeftMenus = $(lis).attr('showLeftMenus');
	// 	loadLeftMenus();
	// 	setTimeout(function() {
	// 		$(window).trigger('resize');
	// 	}, 100);
	// });
    loadLeftMenus();
	// 方式二
	function loadLeftMenus() {
		// nthTabs.delAllTabIgnoreCloseable();
		// sessionStorage.clear();
		$.ajax({
			url : webroot + "/issp_left",
			type : "get",
			success : function(data) {
				$("#leftMenu").html(data);
				initFun();
				openFirst();
			}
		});
	}

	/*
	 *  默认打开左侧第一个菜单 
	 */
	function openFirst() {
		var Lilength = $("#side-menu li").length;
		if (Lilength == 0) {
			alert("请配置功能菜单！！");
			return false;
		}
		//根据菜单配置，是否显示左侧菜单
		showLeft(showLeftMenus);
		//获取第一个菜单
		// var fristLi = $("#side-menu li")[0];
		var firstLi = $("#side-menu li:first");
		menuId = $(firstLi).attr('menuId');
		var menuTitle = $(firstLi).attr('menuTitle');
		var url = $(firstLi).attr('url');
		var isfullwithMenu = $(firstLi).attr('fullwithMenu');
		var tabCloseable = $(firstLi).attr('tabCloseable') == 1 ? true : false;
		//打开功能菜单
		openTab(menuId, menuTitle, tabCloseable, isfullwithMenu, url);
		//添加选中样式
		$("#" + moduleId).addClass("selected").siblings().removeClass("selected");
		$("#left-" + menuId).addClass("selected").siblings().removeClass("selected");

        $(".navissp").find(".selected").addClass("border");
	}
	
	/*
	 *	根据菜单配置，是否显示左侧菜单 
	 */
	function showLeft(showLeftMenus) {
		// if (showLeftMenus == "0") {
		// 	$(".navissp").css("width", "0px");
		// 	$('#mainContet').css("width", "100%");
		// } else {
		// 	if (!$('.navissp').hasClass('navissp-mini')) {
		// 		$('.navissp').removeClass('navissp-mini');
		// 		$('#mainContet').css("width", "88.5%");
		// 		$(".navissp").css("width", "11.5%");
		// 	} else {
		// 		$('.navissp-item.navissp-show').removeClass('navissp-show');
		// 		$(".navissp-item").find('li').each(function(i){
		// 			 $(this).removeClass('navissp-item');
		// 			 $(this).next("ul").css("display","none");
		// 			 $(this).find(".navissp-more").css("margin-right","10px");
		// 		});
		// 		$('.navissp').addClass('navissp-mini');
		// 		$('#mainContet').css("width", "calc(100% - 60px)");
		// 		$(".navissp").css("width", "60px");
		// 		$(".navissp-item").find('li').each(function(i){
		// 			 $(this).find(".navissp-more").css("margin-right","20px");
		// 		});
		// 	}
		// }
	}

	/*
	 * id,标题，是否可关闭，全屏是否带菜单，URL
	 */
	function openTab(menuId, menuTitle, allowClose, isfullwithMenu, url) {
		if (url == contextPath) {
			// alert("未找到菜单URL，请重新配置！");
			return false;
		}
		if (sessionStorage.getItem("#" + menuId) == null
				|| sessionStorage.getItem("#" + menuId) == "null") {
			var mydiv = document.createElement("div");
			var divattr = document.createAttribute("class");
			divattr.value = "tab-pane active";
			var divattrid = document.createAttribute("id");
			divattrid.value = menuId;
			var divattrstyle = document.createAttribute("style");
			divattrstyle.value = "height: 100%";
			mydiv.setAttributeNode(divattr);
			mydiv.setAttributeNode(divattrid);
			mydiv.setAttributeNode(divattrstyle);
			// 首先去掉tab页和内容的active状态
			$("#tabContent").find("li").removeClass("active");
			$("#contentView").find("div").removeClass("active");
			nthTabs.addTab({
				id : menuId,
				title : menuTitle,
				content : mydiv,
				allowClose : allowClose,
				active : true,
				fullwithMenu : isfullwithMenu,
				showLeftMenus : showLeftMenus,
				moduleId : moduleId,
			});
			nthTabs.locationTab();
			// 打开的菜单存到sessionStorage中，下次点击不再打开直接定位
			sessionStorage.setItem("#" + menuId, menuId);

			$("#" + menuId).load(url, function(response, status, xhr) {
				// 出错跳转错误页面
				if (status == "error") {
					$("#" + menuId).load(webroot + "/issperror");
				}
			});
            var liArray = $("#tabContent").find(".nav-tabs li");
            if(liArray.length > 6){
            	//关掉除首页外的第一个
				var closeId = $(liArray[1]).attr("menuid");
                nthTabs.delTab(closeId);
			}
		} else {
			// 定位到打开的tab
			nthTabs.setActTab(menuId);
			nthTabs.locationTab();
			//再次点击会重新给所需页面附active
			$("#contentView").find("div").find(".nocancel").addClass("active");
		}
	}

	// 增加此方法，在ajax数据完成之后调用
	function initFun() {
		//点击左侧菜单执行
		$('li', $('#side-menu')).on('click', function(e) {
            $(".navissp").find(".selected").removeClass("border");

			var level =$(this).parent().attr("level");//level=1级菜单	level=2级菜单
			$(this).addClass("selected").siblings().removeClass("selected");
			var a = $(this).find("ul").eq(0).attr("level");//2级节点
			var b = $(this).next("ul").attr("level");//3级节点
			if(level=='3'){//3级菜单
				$(this).parent().prev("li").removeClass('selected');
				$(".navissp").find(".selected").removeClass("selected");//点击3级 清空所有选中
				$(this).addClass("selected")//重新赋选中样式
			}else{
				$(this).parent().parent().removeClass('selected');
			}
			if(a!='2'&&b!='3'){
				$(".navissp").find(".selected").removeClass("selected");
				$(this).addClass("selected")//重新赋选中样式
			}
			e.stopPropagation();
			var lis = $(this).closest('li');
			var childLen = lis.context.children.length;
			if (childLen == 2)
				return false;
			menuId = $(lis).attr('menuId');
			var url = $(lis).attr('url');
			var isfullwithMenu = $(lis).attr('fullwithMenu');
			var tabCloseable = $(lis).attr('tabCloseable') == 1 ? true : false;
			var opentype = $(lis).attr('opentype');
			var menuTitle = $(lis).attr('menuTitle');
			if (opentype == "1") {
				window.open(url);
			} else {
				openTab(menuId, menuTitle, tabCloseable, isfullwithMenu, url);
			}
			setTimeout(function() {
				$(window).trigger('resize');
			}, 10);

            if(typeof a === "undefined" && typeof b === "undefined"){
            	//如果没有下级菜单，添加 .border
                $(".navissp").find(".selected").addClass("border");
			}
		});

		// nav收缩展开
		$('.navissp-item>a').on('click',function() {
			var level =$(this).parent().parent().attr("level");//level=1级菜单	level=2级菜单
			if (!$('.navissp').hasClass('navissp-mini')) {
				//展开
				if ($(this).next().css('display') == "none"&&level=="1") {
					$('.navissp-item').children('ul').slideUp(300);
					$(this).next('ul').slideDown(300);
					$(this).parent('li').addClass('navissp-show').siblings('li').removeClass('navissp-show');
					$(this).parent('li').find(".navissp-more").eq(0).css("transform","rotate(270deg)");
					$(this).parent('li').siblings('li').find(".navissp-more").css("transform","rotate(90deg)");
					$(this).parent('li').siblings('li').find('ul').find("ul").css("display","none");
				}else if($(this).parent('li').next().css('display') == "none"&&level=="2"){
					$(this).parent('li').next('ul').slideDown(300);
					$(this).parent('li').next("ul").siblings('ul').slideUp(300);
					$(this).parent('li').siblings('li').find(".navissp-more").css("transform","rotate(90deg)");
					$(this).find(".navissp-more").css("transform","rotate(270deg)");
				//关闭
				}else if($(this).next().css('display') != "none"&&level=="2"){
					$(this).parent('li').next('ul').slideUp(300);
					$(this).parent('li').next().removeClass('navissp-show');
					$(this).find(".navissp-more").css("transform","rotate(90deg)");
				} else if(level === "1" || level === "2") {
					$(this).next('ul').slideUp(300);
					$('.navissp-item.navissp-show').removeClass('navissp-show');
					$(this).parent('li').find(".navissp-more").eq(0).css("transform","rotate(90deg)");
				}
			}else{
				if ($(this).parent('li').next().css('display') == "none"&&level=="2") {//展开
					$(this).parent("li").find(".navissp-more").eq(0).css("transform","rotate(270deg)");
					$(this).parent("li").siblings('li').find(".navissp-more").css("transform","rotate(90deg)");
					$(this).parent('li').next("ul").css({"display":"block","position":"initial"}).siblings('ul').css("display","none");
					$(this).parent('li').next('ul').slideDown(300);
					$(this).parent('li').next("ul").siblings('ul').slideUp(300);
				} else if(level === "2"){//关闭
					$(this).parent('li').next('ul').slideUp(300);
					$(this).parent('li').next().removeClass('navissp-show');
					$(this).parent("li").find(".navissp-more").eq(0).css("transform","rotate(90deg)");
				}
			}

			if(level === '3'){
                if ($(this).parent('li').next().css('display') === "none" && level === "3") {//展开
                    $(this).parent("li").find(".navissp-more").eq(0).css("transform","rotate(270deg)");
                    $(this).parent("li").siblings('li').find(".navissp-more").css("transform","rotate(90deg)");
                    $(this).parent('li').next("ul").css({"display":"block","position":"initial"}).siblings('ul').css("display","none");
                    $(this).parent('li').next('ul').slideDown(300);
                    $(this).parent('li').next("ul").siblings('ul').slideUp(300);
                }else{//关闭
                    $(this).parent('li').next('ul').slideUp(300);
                    $(this).parent('li').next().removeClass('navissp-show');
                    $(this).parent("li").find(".navissp-more").eq(0).css("transform","rotate(90deg)");
                }
			}
		});
		// nav-mini切换
		$('#mini').on('click', function() {
			if (!$('.navissp').hasClass('navissp-mini')) {
				$('.navissp').addClass('navissp-mini');
				$('.navissp-item.navissp-show').removeClass('navissp-show');
				$(".navissp-item").find('li').each(function(i){
					$(this).removeClass('navissp-item');
					$(this).next("ul").css("display","none");
					$(this).find(".navissp-more").css("margin-right","10px");
				});
				$('#mainContet').css("width", "calc(100% - 60px)");
				$(".navissp").css("width", "60px");
				var tabLi = $("#tabs-Cont").find(".active");
				isspGlobal.getLeftMeuns(tabLi);
			} else {
				$('.navissp').removeClass('navissp-mini');
				$('#mainContet').css("width", "88.5%");
				$(".navissp").css("width", "11.5%");
				$(".navissp-item").find('li').each(function(i){
					 $(this).find(".navissp-more").css("margin-right","20px");
				});
				var tabLi = $("#tabs-Cont").find(".active");
				isspGlobal.getLeftMeuns(tabLi);
			}
		});
	}

	/*$("#tabs-Cont").on("click", "li", function() {
		var tabLi = $(this).closest('li');
		isspGlobal.getLeftMeuns(tabLi);
		setTimeout(function() {
			// $(window).resize();
			$(window).trigger('resize');
		}, 10);
	});*/

	return {
		getNthTabs : function() {
			return nthTabs;
		},
		/* 
		 * 监控首页左侧组织机构树收缩方法
		 */
		monitorRightClos_left:function (leftMark){
			if(leftMark != undefined){
			left_cols_mark = leftMark;
			}
			if (left_cols_mark == 0) {
				$('.leftarea_left').css("width", "0px");
				left_cols_mark = 1
				$('#map_Tips').removeClass('fa fa-angle-left').addClass('fa fa-angle-right');//替换成一个新class
			} else{
				if(left_cols_mark == 1){
					$('.leftarea_left').css("width","260px");
					left_cols_mark = 0
					$('#map_Tips').removeClass('fa fa-angle-right').addClass('fa fa-angle-left');//替换成一个新class
				}else{
					$('.leftarea_left').css("width", "0px");
					left_cols_mark = 1
					$('#map_Tips').removeClass('fa fa-angle-left').addClass('fa fa-angle-right');//替换成一个新class
				}
			}
			monitoringhomepage_monitoringhome.getmarkinfo(left_cols_mark);
			},
		/*
		 * index首页初始化完成后，默认打开第一个功能模块菜单
		 */
		loadMenusFromIndex : function() {
			// var fristLi = $("#topMenu li")[0];
			// if (fristLi == null) {
			// 	alert("请配置系统功能模块菜单！！");
			// 	return false;
			// }
			// moduleId = $(fristLi).attr('moduleId');
			// showLeftMenus = $(fristLi).attr('showLeftMenus');
			loadLeftMenus();
		},
		getLeftMeuns : function (tabLi) {
			showLeftMenus = $(tabLi).attr('showLeftMenus');
			menuId = $(tabLi).attr('menuId');
			moduleId = $(tabLi).attr('moduleId');
			if (moduleId == null || moduleId == "") {
				// alert("moduleId为空");
				return false;
			}
			$.ajax({
				url : webroot + "/issp_left",
				type : "get",
				success : function(data) {
					$("#leftMenu").html(data);
					var level =$("#left-" + menuId).parent().attr("level");//获取树节点
					initFun();
					showLeft(showLeftMenus);
					// 选中模块
					$("#" + moduleId).addClass("selected").siblings().removeClass("selected");
					// 打开一级菜单并选中相应菜单
					if (!$('.navissp').hasClass('navissp-mini')) {
						if(level=='3'){//有3级菜单
							$("#left-" + menuId).parent().parent().parent().addClass('navissp-show').siblings().removeClass('navissp-show');
							$("#left-" + menuId).parent().parent().parent().find(".navissp-more").eq(0).css("transform","rotate(0deg)");
							$("#left-" + menuId).parent().parent("li").addClass('navissp-show').siblings().removeClass('navissp-show');
							$("#left-" + menuId).parent().prev("li").find(".navissp-more").eq(0).css("transform","rotate(0deg)");
							$("#left-" + menuId).parent().prev("li").addClass("selected");
							$("#left-" + menuId).parent().css({"display":"block","position":"initial"});
							$("#left-" + menuId).addClass("selected").siblings().removeClass("selected");
						}else if(level=='2'){
							$("#left-" + menuId).parent().parent().addClass('navissp-show').siblings().removeClass('navissp-show');
							$("#left-" + menuId).parent().parent().find(".navissp-more").eq(0).css("transform","rotate(0deg)");
							$("#left-" + menuId).addClass("selected").siblings().removeClass("selected");
							$("#left-" + menuId).removeClass("navissp-item").siblings("li").removeClass("navissp-item");
						}else{
							$("#left-" + menuId).addClass("selected").siblings().removeClass("selected");
						}
					}
				}
			});
		}
	}
})(jQuery, window, document);
