<object id="AuthIE" name="AuthIE" width="0px" height="0px" style="display: none;"
        codebase="DogAuth.CAB#version=2,3,1,58083"
        classid="CLSID:05C384B0-F45D-46DB-9055-C72DC76176E3">
</object>
<script src="${ctx}/static/js/SuperDog/Func.js"></script><!-- 加密狗js -->
<div id="top" class="page-tabs">

	<div class="logo">
		<a style="display: flex;align-items: center;"><img class="top_image" src="${ctx }/static/images/top/logo.jpg" style="height: 20%;margin-left: -1%;margin-top: -1%; width: 10%;">
			<p class="top_name">综合能源管控平台</p></a>
	</div>

	<div class="top_control_menu">
        <#--<i class="fa fa-outdent" aria-hidden="true"></i>-->
		<img id="expandIcon" src="${ctx}/static/uniform/image/expand.png">
			<img id="foldIcon" src="${ctx}/static/uniform/image/fold.png" style="display:none">
	</div>
	
	<#--<div class="jt_left">-->
	  <#--<a href="#" class="roll-nav roll-nav-left" id="goLeft" style="font-size: 1.5vw;" onclick="left_click();">-->
	    <#--<span id="jt_left"></span>-->
	  <#--</a>-->
	<#--</div>-->
	
	<!-- div 取两个class，警报图片放css里，在js里切换class(正常：earlyWarning_blue;报警：earlyWarning_red)-->
		<#--<a href="#">
			<div id="bes_bjxx" class="earlyWarning_red" >
				<p class="earlyWarning_num"style=" margin: 10px 18px 10px;color: #f6fd93;font-size: 13px;">10</p>
			</div>
		</a>-->
	

	
	  <#--<div class="jt_right"> -->
	 	<#--<a href="#" class="roll-nav roll-nav-right" id="goRight" style="font-size: 1.5vw;" onclick="right_click();">-->
	    	<#--<span id="jt_right"></span>-->
	  	<#--</a>-->
	<#--</div>-->


	
	<div class="top_right_menu">
        <a class="right_menu_item" href="#"  title="实时告警">
            <div id="warningIcon" class="earlyWarning_blue" >
                <p id="warningInfoCount" class="earlyWarning_num"></p>
            </div>
        </a>
        <i id="topFullScreen" class="right_menu_item fa fa-arrows-alt" aria-hidden="true" onclick="topFullScreen()"></i>
        <img id="exitTopFullScreen" class="cancelFullScreen" src="${ctx}/static/uniform/image/cancelFullScreen.png" onclick="exitTopFullscreen()"/>
		<ul class="nav navbar-top-links navbar-right notification-menu" id="issp_photo">
		  <li class="user-dropdown">
		    <a href="#" class="btn  dropdown-toggle right_user_div" data-toggle="dropdown">
		      <#--<img src="${ctx}/static/images/photos/user-avatar.png" alt="" width="20">-->
                  你好 <@shiro.principal />
		      <span class="caret"></span>
		    </a>
		    <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
		      <#--<li>-->
		        <#--<a href="#">-->
		          <#--<i class="fa fa-user"></i>个人中心</a>-->
		      <#--</li>-->
		      <#--<li>-->
		        <#--<a  data-toggle="modal" data-target="#top_myModal">-->
		          <#--<i class="fa fa-cog"></i>皮肤设置</a>-->
		      <#--</li>-->
		      <li>
		        <a href="${ctx}/logout">
		          <i class="fa fa-sign-out"></i>退出</a>
		      </li>
		    </ul>
		  </li>
		</ul>
	</div>
	
	<div class="modal fade" id="top_myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						
						
					</button>
					<h4 class="modal-title" id="myModalLabel">
						皮肤设置
					</h4>
				</div>
				<div class="modal-body">
					<h5>皮肤选择器</h5>
		          	<button class="skinSelector" style="background-color:#001b3a" onclick="js_changeStyle.changeStyle('blue');"/>
		          	<button class="skinSelector" style="background-color:#a9d4ff" onclick="js_changeStyle.changeStyle('white');"/>
				</div>
					<ul class="skin_name">
						<li><p style="color:#001b3a">科技蓝</p></li>
						<li><p style="color:#a9d4ff">天空蓝</p></li>
					</ul>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
</div>

<script type="text/javascript">
;
var bes_view_issp_top = (function($, window, document, undefined) {
    // 加密狗
    var dogNotPresent = false;
    var authCode = "";
    var superdogState = 0;

    // 启动加密验证
     $.ajax({
		url: "${ctx}/SuperDog/superdog",
		type: "post",
		data: ({
		}),
		success: function(data) {
	    	superdogState = data.status ;
	    	if(superdogState == 1){
	    		loadFunc();
	    	}
		}
	});

    function checkDog(){

        var stat = "";
        var scope = "<dogscope/>";

        //Get Auth Code
        if ("" == authCode) {
            authCode = getAuthCode();
        }

        //Get object
        objAuth = getAuthObject();
        if ( (navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0) ) {
            objAuth.GetUserNameEx(scope,authCode);
        }else{
            //判断是否安装控件
            if(objAuth.hasOwnProperty("Open")==false){
                TextExit("请先安装控件！");
            }
            //Open
            stat = objAuth.Open(scope,authCode);
            if (0 != stat) {
                TextExit("未检测到加密狗,即将退出系统！");
            }
        }
        //Execute the check again after 2 seconds
        setTimeout(checkDog, 2000);
    }

    function TextExit(Msg){
        swal({
            title : Msg,
            text : "",
            type : "warning",
            showCloseButton : false,
            allowOutsideClick : false,
            showConfirmButton : false,
            timer : 2000
        });
        setTimeout(function(){
            window.location.href= "/BESServer/logout";
        }, 2200);
    }


    function loadFunc(){

        var objAuth = "";

        embedTag();

        //Get object
        objAuth = getAuthObject();

        if ( (navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0) ) { //Chrome or Firefox
            window.parent.addEventListener("message", function (event) {
                if (event.data.type == "SNTL_FROM_HOST") {
                    var ReturnText = event.data.text;
                    if ("GetUserNameEx" == ReturnText.InvokeMethod) {
                        if (0 == ReturnText.Status) {
                            dogNotPresent = false;
                            return;
                        }else{
                            if (false == dogNotPresent) {
                                dogNotPresent = true;
                                TextExit("未检测到加密狗,即将退出系统！");
                            }
                            return;
                        }
                    }
                    else{
                        return;
                    }
                }
            }, false);

            setTimeout(checkDog, 1000);
        }
        else if (window.ActiveXObject || "ActiveXObject" in window) {  //IE
            setTimeout(checkDog, 1000);
        }
        else {
            setTimeout(checkDog, 1000);
        }
    }
})(jQuery, window, document);
</script>
