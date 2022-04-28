<#--描述 : 加密狗设置
作者 : 杨超-->
<div class="row">
    <div class="ibox-content">
        <form role="form" id="ChangePinForm" name="ChangePinForm"  class="form-horizontal" >
            <div class="form-group">
                <label class="col-sm-1 control-label" style="padding-top: 7px!important;">账号<span class="text-danger">*</span></label>
                <div class="col-sm-3">
                    <input type="text" readonly=readonly id="SuperDog_Username"  name="SuperDog_Username" placeholder="账号" class="form-control" min="0">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label" style="padding-top: 7px!important;">旧密码<span class="text-danger">*</span></label>
                <div class="col-sm-3">
                    <input type="password" id="SuperDog_oldPwd" name="SuperDog_oldPwd" placeholder="请输入密码" class="form-control" min="0">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label" style="padding-top: 7px!important;">新密码<span class="text-danger">*</span></label>
                <div class="col-sm-3">
                    <input type="password" id="SuperDog_newPwd" name="SuperDog_newPwd" placeholder="请输入密码" class="form-control" min="0">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label" style="padding-top: 7px!important;">确认新密码<span class="text-danger">*</span></label>
                <div class="col-sm-3">
                    <input type="password" id="SuperDog_retypePwd" name="SuperDog_retypePwd" placeholder="请输入密码" class="form-control" min="0">
                </div>
            </div>
            <div class="form-group">
                <a id="faultType_add" href="javascript:void(-1);" onclick="return view_set_sysetmSet_SuperDogSetting.pageInit()" class="btn btn-primary toLeft" style="margin-left: 25.4vw;"> 确定
                </a>
            </div>
            
        </form>
    </div>
</div>
<script type="text/javascript">
;var view_set_sysetmSet_SuperDogSetting = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    
    var superdogState = 0 ; //加密狗状态 默认不启用
    var dogNotPresent = false;
    var lastStatus;
    var authCode = "";
    
    var SuperDog_oldPwd = "";
    var SuperDog_newPwd = "";

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
    
    // 检查狗
    function checkDog() {
        var scope = "<dogscope/>";
        var objAuth = null;

        if ("" == authCode) {
            authCode = getAuthCode();
        }

        objAuth = getAuthObject();

        objAuth.GetUserNameEx(scope, authCode);

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

    function loadFunc() {
        // if(superdogState == '0'){
        //     return false;
        // }
    	if ( (navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0) ) {
            window.parent.addEventListener("message", function (event) {
                if (event.data.type == "SNTL_FROM_HOST") {
                    var ReturnText = event.data.text;
                    if ("GetUserNameEx" == ReturnText.InvokeMethod) {
                        // return from GetUserNameEx
                        if (0 == ReturnText.Status) {
                        	//填充账号信息
                            $("#SuperDog_Username").val(ReturnText.UserNameStr);
                            dogNotPresent = false;
                            lastStatus = 0;
                            return;
                        }else {
                            lastStatus = ReturnText.Status;
                            if (false == dogNotPresent) {
                                dogNotPresent = true;
                                TextExit("未检测到加密狗,即将退出系统！");
                            }
                        }
                    }
                    else if ("ChangeUserPinEx" == ReturnText.InvokeMethod) {
                        if (0 == ReturnText.Status) {
                        	changePwd();//修改项目密码
                            return;
                        }
                        else {
                            alertStatus(parseInt(ReturnText.Status));
                            return;
                        }
                    }
                    else {
                        return;
                    }
                }
            }, false);

            setTimeout(checkDog, 1000);
        }
        embedTag();
    }
    
    //修改系统项目密码
    function changePwd(){
        var rybh = $("#SuperDog_Username").val();
		$.ajax({
		   url: _ctx + "/view/user/SuperDogUptPwd",
		   type: "post",
		   data: ({
		       f_yhbh: rybh,
		       f_pass: SuperDog_newPwd,
		   }),
		   success: function(data) {
		       if (data.status == '1') {
		    	   TextExit("您的密码修改成功");
		       } else {
		           alert(data.msg);
		       }
		   },
		   error: function (data) {
			   alert(data.msg);
		   }
		 });
    }

    /**********************************************************************************************
    Function: onOK
    Parameters: none
    Return: none
    Description: Check input info modify the R/W info 
    ***********************************************************************************************/
    function onOK() {
        var result = false;
        var stat = 0;
        var objAuth = "";
        var scope = "<dogscope/>";
        if (window.ActiveXObject || "ActiveXObject" in window) { //IE
            //Add onfocus event
            var obj = document.getElementById("SuperDog_oldPwd");
            if (Object.hasOwnProperty.call(window, "ActiveXObject") && !window.ActiveXObject) { // is IE11  
                obj.addEventListener("onfocus", clearInfo, false);
            } else {
                obj.attachEvent("onfocus", clearInfo);
            }
        }
        if(validateChangeForm()){
            if(confirm("您确定要修改密码吗?")){
            	SuperDog_oldPwd = $("#SuperDog_oldPwd").val();
                SuperDog_newPwd = $("#SuperDog_newPwd").val();
                objAuth = getAuthObject();
                //Get Auth Code
                if ("" == authCode) {
                    authCode = getAuthCode();
                }
                
                if ((navigator.userAgent.indexOf("Chrome") > 0) || (navigator.userAgent.indexOf("Firefox") > 0)) { //Chrome or Firefox
                    if (dogNotPresent) {
                        //return if dog not present
                        alertStatus(parseInt(lastStatus));
                        return false;
                    }
                    //Modify the pin --修改密码-
                    objAuth.ChangeUserPinEx(scope, authCode, SuperDog_oldPwd, SuperDog_newPwd);
                    $("#SuperDog_oldPwd").val("");
                    $("#SuperDog_newPwd").val("");
                    $("#SuperDog_retypePwd").val("");
                    return true;
                }
                //Open the SuperDog--IE修改方式
                stat = objAuth.Open(scope, authCode);
                if (stat != 0) {
                    alertStatus(stat);
                    return false;
                }

                //Verify the pin
                stat = objAuth.VerifyUserPin(SuperDog_oldPwd);
                if (stat != 0) {
                    alertStatus(stat);
                    return false;
                }

                //Modify the pin
                stat = objAuth.ChangeUserPin(SuperDog_newPwd);
                if (stat == 0) {
                    TextExit("密码修改成功！");
                    objAuth.Close();
                    return true;
                } else {
                    alertStatus(stat);
                    objAuth.Close();
                    return false;
                }
            }
        }else{
        	$("#SuperDog_oldPwd").val("");
            $("#SuperDog_newPwd").val("");
            $("#SuperDog_retypePwd").val("");
            return false;
        }    
    }

    return {
        pageInit: function () {
        	onOK();
        }
    }
})(jQuery, window, document);
</script>