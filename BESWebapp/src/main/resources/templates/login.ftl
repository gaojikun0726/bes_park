<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>综合能源管控平台</title>

<link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/css/bootstrap.css"/>
<link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/Font-Awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/css/build.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/plugins/checkbox/css/default.css">
<link rel="stylesheet" href="${ctx}/static/css/style2.0.css">
    <link rel="shortcut icon" href="${ctx}/favicon.ico"/>
    <#--<link rel="icon" href="${ctx}/static/images/top/logo.jpg" type="image/x-icon" />-->
<style type="text/css">
	ul li{font-size: 30px;color:#2ec0f6;}
	.tyg-div{z-index:-1000;float:left;position:absolute;left:5%;top:20%;}
	.tyg-p{
		font-size: 14px;
	    font-family: 'microsoft yahei';
	    position: absolute;
	    top: 135px;
	    left: 60px;
	}
	.tyg-div-denglv{
		z-index: 1000;
	    float: right;
	    position: absolute;
	    right: 10%;
   		top: 33%;
	}
	.tyg-div-form{
		width: 50%;
	    height: auto;
	    margin-left: 33%;
	    margin-top: 3%;
	}
	.tyg-div-form form {padding:10px;}
	.tyg-div-form form input[type="text"]{
		width: 228px;
    	font-size: 0.8vw;
    	margin-left: 2.5vw;
	}
	.tyg-div-form form input[type="password"]{
		width: 228px;
	    font-size: 0.8vw;
	    margin-left: 2.5vw;
	}
	.tyg-div-form form button {
	        cursor: pointer;
    width: 37%;
    height: 31px;
    margin-top: 8px;
    margin-left: 54%;
    padding: 0;
    background: rgb(5, 119, 179);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 0.9vw;
    font-weight: 700;
    color: #ffffff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
}
.srk1{
	background: url(${ctx}/static/images/loginimg/usersrk.png) no-repeat;
	position: relative;
	background-size: 100% 100% !important;
    width: 15.5vw !important;
    height: 5vh !important;
    display: flex;
    align-items: center;
}
.srk2{
	background: url(${ctx}/static/images/loginimg/pwdsrk.png) no-repeat;
	position: relative;
	background-size: 100% 100% !important;
    width: 15.5vw !important;
    height: 5vh !important;
    display: flex;
    align-items: center;
}
.inp{
    color: #33b8d8;
    outline: none;
    border: none;
    background: rgba(0, 206, 255, 0);

}
.styled{
	float: left;
    margin-top: -16px;
    width: 50px;
}
.login_qljt{
	width: 785px;
	margin-top: 11px;
}
.login-error{
    float: left;
    margin-top: -34px;
    color: rgb(224, 0, 0);
}
label.error {
    color: rgb(255, 255, 255);
    float: right;
    margin-top: 12px;
    margin-right: -63px;
}
.checkbox checkbox-primary{
	position: absolute;
    left: 25%;
    top: 76%;
}
.login_jzmm{
	margin-left: 36px;
	margin-top: -19px;
}
.logo_background{
	margin-top: -24%;
    margin-bottom: -22%;
    margin-left: 25%;
    height: auto;
    width: 38%;
}
.bes_logo{
    margin-top: -38%;
    margin-bottom: -22%;
    margin-left: 31%;
    height: auto;
    width: 27%;
}
.font_logo{
	margin-bottom: 9%;
	margin-top: 13%;
	margin-left: -5%;
	font-family: 黑体;
	font-size: 35px;
	font-weight: 900;
	word-break: keep-all;
    white-space: nowrap;
}
    /*调整不输入账号、密码的提示信息的样式*/
    .srk1 label,.srk2 label{
        margin-left:35px;
    }
</style>
</head>
<object id="AuthIE" name="AuthIE" width="0px" height="0px" style="display: none;"
        codebase="DogAuth.CAB#version=2,3,1,58083"
        classid="CLSID:05C384B0-F45D-46DB-9055-C72DC76176E3">
</object>

<script src="${ctx}/static/js/SuperDog/Func.js"></script><!-- 加密狗js -->

<body style="background-image: url(${ctx}/static/images/loginimg/bes_backgroundimg.jpg)">


<div class="tyg-div-denglv" style="width: 664px;height: 386px;margin: -2% 25% 0 0;">
	<img alt="" src="${ctx}/static/images/loginimg/loginbackgroundimg.png" style="position: absolute;top: -38%;z-index:-1" >
	<div class="tyg-div-form" style="z-index:1">
		<form action="${ctx }/login" name="login" class="form-signin" id="userForm" method="POST" >
			<div >
				<img src="${ctx}/static/images/loginimg/logobackground.png"class="logo_background">
				<img src="${ctx}/static/images/top/logo.jpg" class="bes_logo">
			</div>
			<div>
				<h1 class="font_logo">综合能源管控平台</h1>
			</div><p class="tyg-p"></p>
			<div class="srk1" >
				<input type="text"id="login_f_yhbh" name="f_yhbh" class="inp"  placeholder="请输入账号..."value="" autocomplete="off"/>
			</div>
			<div class="srk2" >
				<input type="password"id="login_f_pass" name="f_pass" class="inp" placeholder="请输入密码..."value="" autocomplete="off"/>
			</div>
			<button  type="submit" id="mouse"class="mouse" onclick="return login_click()">登<span style="width:20px;"></span>录</button>
			<label class="login-error" id="errorinfo">${message}</label>
			<#if message??>
          <label class="login-error"  id="errorinfo">${message}</label></#if>
          <div class="checkbox checkbox-primary"style="color:white">
		       <input id="checkbox2" class="styled" type="checkbox" checked>
		           <label for="checkbox2">记住密码</label>
		  </div>
		</form>
	</div>
</div>


<!--[if IE 6]>
<script language="javascript" type="text/javascript" src="./script/ie6_png.js"></script>
<script language="javascript" type="text/javascript">
DD_belatedPNG.fix(".png");
</script>
<![endif]-->
<script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>  <!---表单验证--->
<script  src="${ctx}/static/js/login_animation.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			start();
	    });
	    //验证用户密码是否为空
		function login_click(){
			var aa=$("#userForm").validate({
			rules:{
				f_yhbh:{
					 required: true,
					 rangelength: [1, 20],
				},
				f_pass:{
					required: true,
					rangelength: [1, 20],
				}
			},
			messages:{
				 f_yhbh: {
			         required: "请输入账号",
			      },
			       f_pass: {
			         required: "请输入密码",
			      }
			},
		});
		return validateLogin();
		}
	</script>

<script language="javascript">


    var dogNotPresent = false;
    var authCode = "";
    var superdogState = 0;// 0.关闭  1.开启

    // 加密狗验证开启
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

    // 登录验证
    function validateLogin(){
    	if(superdogState == 1){

    	}else{
    		return true;
    	}

        var challenge = "";
        var stat = "";
        var objAuth = "";
        var dogID = "";
        var digest = "";
        var scope = "<dogscope/>";
        // 账号
        var name = $("#login_f_yhbh").val();
        // 密码
        var pwd = $("#login_f_pass").val();

        if(window.ActiveXObject || "ActiveXObject" in window) { //IE
            //Add onfocus event
            var obj = document.getElementById("password");
            if (Object.hasOwnProperty.call(window, "ActiveXObject") && !window.ActiveXObject){
                // is IE11
                obj.addEventListener("onfocus", clearInfo, false);
            }else{
                obj.attachEvent("onfocus", clearInfo);
            }
        }

        //Get Object
        objAuth = getAuthObject();

        //Get Auth Code
        if ("" == authCode) {
            authCode = getAuthCode();
        }

        if ( (navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0) ) {  //Chrome or Firefox
            //Get challenge string
            challenge = getChallenge();
            if(challenge.toString().length < 32){
                if(challenge == "001"){
                    reportStatus(916);
                }else if(challenge == "002"){
                    reportStatus(917);
                }else{
                    reportStatus(918);
                }
                return false;
            }

            //Generate digest
            objAuth.GetDigestEx(scope, authCode, pwd, challenge);
            return false;
        }

        //Open the dog
        stat = objAuth.Open(scope, authCode);
        if(stat != 0){
            reportStatus(stat);
            return false;
        }

        //Verify the password
        stat = objAuth.VerifyUserPin(pwd);
        if(stat != 0){
            objAuth.Close();
            reportStatus(stat);
            return false;
        }

        //Get the DogID
        stat = objAuth.GetDogID();
        if(stat != 0){
            objAuth.Close();
            reportStatus(stat);
            return false;
        }

        //Save the DogID
        dogID = objAuth.DogIdStr;
        document.getElementById("dogid").value = dogID;

        challenge = getChallenge();
        if(challenge.toString().length < 32){
            if(challenge == "001"){
                reportStatus(916);
            }
            else if(challenge == "002"){
                reportStatus(917);
            }else{
                reportStatus(918);
            }
            window.objAuth.Close();
            return false;
        }

        //Generate digest
        stat = objAuth.GetDigest(challenge);
        if(stat != 0){
            objAuth.Close();
            reportStatus(stat);
            return false;
        }

        digest = objAuth.DigestStr;
        document.getElementById("response").value = digest;

        //Do authenticate
        stat = doAuth(dogID, digest);
        if(stat != 0){
            objAuth.Close();
            reportStatus(stat);
            return false;
        }

        objAuth.Close();
        return true;
    }

    //
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
            objAuth.GetUserNameEx(scope, authCode);
        }
        else {
            //Open Dog
            stat = objAuth.Open(scope, authCode);
            if(0 != stat){
                dogNotPresent = true;
                reportStatus(stat);
            }else{
                if (dogNotPresent == true) {
                    dogNotPresent = false;
                    window.location.href = "Login.jsp";
                }
            }
        }

        //Execute the check again after 2 seconds
        setTimeout(checkDog, 2000);
    }

    function loadFunc(){


        var objAuth;

        //Get object
        objAuth = getAuthObject();

        if ( (navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0) ){  //Chrome or Firefox
            window.parent.addEventListener("message", function (event) {
                if (event.data.type == "SNTL_FROM_HOST") {
                    var ReturnText = event.data.text;
                    if ("GetUserNameEx" == ReturnText.InvokeMethod) {
                        if (0 == ReturnText.Status) {
                            //填充账号信息
                            $("#login_f_yhbh").val(ReturnText.UserNameStr);
                            lastStatus = 0;
                            if (dogNotPresent) {
                                dogNotPresent = false;
                                clearInfo();
                            }
                            return;
                        }else {
                            $("#login_f_yhbh").attr("readonly","readonly");
                            reportStatus(parseInt(ReturnText.Status));
                            lastStatus = ReturnText.Status;
                            if (false == dogNotPresent) {
                                dogNotPresent = true;
                            }
                            return;
                        }
                    }else if ("GetDigestEx" == ReturnText.InvokeMethod) {
                        if (0 == ReturnText.Status) {
                            //alert("检测到加密狗,允许登录!");
                            //暂时先不做后台验证加密狗--验证加密狗在线直接登录
                            document.forms["login"].submit();
                        }else {
//                         	alertStatus(parseInt(ReturnText.Status));
                            reportStatus(parseInt(ReturnText.Status));
                            return;
                        }
                    }else {
                        return;
                    }
                }
            }, false);

            setTimeout(checkDog, 1000);
        } else if (window.ActiveXObject || "ActiveXObject" in window){  //IE
            setTimeout(checkDog, 1000);
        }else{
            setTimeout(checkDog, 1000);
        }
    }

</script>
</body>
</html>
