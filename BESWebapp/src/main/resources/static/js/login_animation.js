/*登录页面动画用js
 * 创建人：sunhao
 * */

/*回车事件*/
$(function(){ 
	$(document).keydown(function(event){ 
	if(event.keyCode==13){ 
	$("#mouse").click(); 
	} 
	}); 
	$("#mouse").click(function(){ 
	
	}); 
}) 
//光标锁定事件
function start(){
	var login_yhbh=$("#login_f_yhbh").val();
	if(login_yhbh==null||login_yhbh==""){
		$("#login_f_yhbh").focus();        
	}else{
		$("#login_f_pass").focus();
	}
}

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
         required: "请输入账户",
      },
       f_pass: {
         required: "请输入密码",
      }
},
});
}
//记住密码start
window.onload = function(){
    var oForm = document.getElementById('userForm');
    var oUser = document.getElementById('login_f_yhbh');
    var oPswd = document.getElementById('login_f_pass');
    var oRemember = document.getElementById('checkbox2');
    //页面初始化时，如果帐号密码cookie存在则填充
    if(getCookie('f_yhbh') && getCookie('f_pass')){
      oUser.value = getCookie('f_yhbh');
      oPswd.value = getCookie('f_pass');
      oRemember.checked = true;
    }
    //复选框勾选状态发生改变时，如果未勾选则清除cookie
    oRemember.onchange = function(){
      if(!this.checked){
        delCookie('f_yhbh');
        delCookie('f_pass');
      }
    };
    //表单提交事件触发时，如果复选框是勾选状态则保存cookie
    oForm.onsubmit = function(){
      if(checkbox2.checked){ 
        setCookie('f_yhbh',oUser.value,7); //保存帐号到cookie，有效期7天
        setCookie('f_pass',oPswd.value,7); //保存密码到cookie，有效期7天
      }
    };
  };
  //设置cookie
  function setCookie(name,value,day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    document.cookie = name + '=' + value + ';expires='+ date;
  };
  //获取cookie
  function getCookie(name){
    var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
      return arr[1];
    }else{
      return '';
    }
  };
  //删除cookie
  function delCookie(name){
    setCookie(name,null,-1);
  };
 //记住密码end
  
