/*
 * 功能：设备树定义属性页面共通方法
 * 日期：2018年06月08日
 * 作者：huangxianbo
 */
;
	var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common = (function($,window, document, undefined) {
		var isAddInfo = false;//默认属性修改页面
		//新增节点，修改属性保存时
		$(function() {
			if (basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()) {
				isAddInfo = true;
				$('input[name=f_sys_name]').removeAttr("readonly");
				$("#f_allpath").html($("#pre_f_allpath").val() + ">" + basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextVar());//设置当前路径
				$("#saveBtn").text("新增");
				if (!(typeof ($('#tree_sbdy').treeview('search',basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon())[0]) == 'undefined'))
					$("#sel_f_type").val($('#tree_sbdy').treeview('search',basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextVar())[0].nodeType);//设置节点类型
				$("#sel_img_url").val(basedatamanage_eqmanage_eqconfiguration_sbdy.getIdMap().get("off_" + $("#sel_f_type").val()));//设置图url
				try{
//					basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.addDifferSet();
				}catch(e){
				}
			}
			basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
			refreshSubmitFlg();
		});
		$("#saveBtn").attr("disabled", true);//【应用】按钮默认无效
		basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();//设置页面可关闭
		function refreshSubmitFlg(){
			var submitFlg = true;
			if ($("#saveBtn").text() == "新增")
				submitFlg = false;
			$("#saveBtn").attr("name", submitFlg);
		};
		//新增节点，属性内容修改时
		$("#f_sys_name").bind('input porpertychange',function() {
			if (!isAddInfo)
				return;

			var f_sys_name = $("#f_sys_name").val();
            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeName = f_sys_name;
			$("#f_nick_name").val(f_sys_name);
			$("#f_allpath").html($("#pre_f_allpath").val() + ">" + f_sys_name);
			$("#saveBtn").attr("disabled", false);
			//var selId=treeSelNode = $("#tree_sbdy").treeview("getSelected")[0].id;
			var selId = $("#tree_sbdy").treeview("getSelected")[0].id;
			$("#" + selId).children("span[class='text']").text($("#f_nick_name").val());
			basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.refreshSubmitFlg();
			//验证系统名称不能为中文
			if($("#errText").length>0)
				$("#errText").remove();
//			var myReg = /^[a-zA-Z0-9]{1,20}$/;//1~20位的数字和英文字母
			var myReg = /^[\u4e00-\u9fa5\__a-zA-Z0-9]{1,30}$/;//1~30位中文，英文，数字和_
			if (!myReg.test($("#f_sys_name").val())) {
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText1");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText1").remove();
				oSpan.innerText = "必须是1~30位中文,英文,数字或_";
				$("#f_sys_name").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				return false;
			} else {
				$("#errText1").remove();
				$("#saveBtn").attr("disabled", false);
			}
		});
		$("#f_nick_name").bind('input porpertychange',function() {

			var f_nick_name = $("#f_nick_name").val();

            basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeName = f_nick_name;

			//var selId=treeSelNode = $("#tree_sbdy").treeview("getSelected")[0].id;
			var selId = $("#tree_sbdy").treeview("getSelected")[0].id;
			$("#" + selId).children("span[class='text']").text(f_nick_name);
			$("#f_allpath").html($("#pre_f_allpath").val() + ">" + f_nick_name);
			refreshSubmitFlg();
			if($("#errText").length>0)
				$("#errText").remove();
		});
		
		/*$("#f_ip_addr").bind('input porpertychange',function() {//验证网络地址
			if($("#errText_f_ip_addr").length>0)
				$("#errText_f_ip_addr").remove();
			var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
			if ($("#f_ip_addr").val().match(exp)==null) {
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText_f_ip_addr");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText_f_ip_addr").remove();
				oSpan.innerText = "网络地址格式不正确！";
				$("#f_ip_addr").after(oSpan);
				$("#saveBtn").attr("disabled", true);
			}else{
				$("#saveBtn").attr("disabled", false);
			}
		});*/
		/**
		 * 网络地址插件开始 
		 */
		var goal = ".ip-input";
		// ip输入框的最大值
		var ip_max = 255;
		$(goal).bind("keydown",function(event) {// 监听键盘输入事件
				// console.log($(this).attr("id"))
				var code = event.keyCode;// 只能输入数字键、删除键、小数点，tab键，其他的都不能输入
				if ((code < 48 && 8 != code && 37 != code && 39 != code && 9 != code)
					|| (code > 57 && code < 96) || (code > 105 && 110 != code && 190 != code)) {
					return false;
				}
				// 如果输入了点 (.)，则直接跳转到下一个输入框
				if (code == 110 || code == 190) {
					$(this).next().focus();
					return false;
				}
		})
		// 监听键盘离开事件
		$(goal).bind("keyup", function(event) { // 判断当前输入框的值
			var value = $(this).val();
			if (value != null && value != '' && parseInt(value) > ip_max) {// 如果输入的值大于ip最大值，则去掉最后一位数字
				value = value.substring(0, value.length - 1);
				$(this).val(value);
				return false;
			}
			if (value != null && value != '' && parseInt(value) > 100 && parseInt(value) <= ip_max) {// 如果输入框的值大于100，并且符合规则，则跳转到下一个输入框
				$(this).next().focus();
				return false;
			}
			if (value != null && value != '' && parseInt(value) != 0) {// 判断是否是0开头的不规范数字
				// 如果当前输入的是0开头，则把0去掉,方法是直接转数字即可
				value = parseInt(value);
				if (isNaN(value)) {
					$(this).val("");
				} else {
					$(this).val("" + value);
				}
			}
		})
		$(goal).bind("blur", function() {// 失去焦点事件
			var value = $(this).val();
			// 如果失去焦点，当前的值为0，则加上红色边框，否则去掉红色边框
			if (value == null || value == '' || value == undefined) {
				$(this).css("border-color", "#F08080");
			} else {
				$(this).css("border-color", "");
			}
			if($("#errText_f_ip_addr").length>0)
				$("#errText_f_ip_addr").remove();
			var hidIp = $("#hidden_f_ip_addr").val();
			var ipTxt = $("#ip_b_1").val()+"."+ $("#ip_b_2").val()+"."+$("#ip_b_3").val()+"."+ $("#ip_b_4").val();
			if(hidIp!= ipTxt)
			/*Start delete by xiepufeng at 2020年5月18日 reason ip 地址变化时才可以保存*/
			// $("#saveBtn").attr("disabled", false);
            /*End delete by xiepufeng at 2020年5月18日 reason ip 地址变化时才可以保存*/
			basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.ipIsIllegal();
		})
		//网络地址插件结束
		
		return {
			getIsAddInfo : function() {
				return isAddInfo;
			},
			setIsAddInfo : function(addInfo) {
				isAddInfo = addInfo;
			},
			saveBtnIsEffective : function() { //信息变更后【应用】按钮有效
				basedatamanage_eqmanage_eqconfiguration_sbdy.webNotSaveWithMsg();
				$("#saveBtn").attr("disabled", false);
				if($("#errText").length>0)
				$("#errText").remove();
			},
			refreshSubmitFlg : function(){
				var submitFlg = true;
				if ($("#saveBtn").text() == "新增")
					submitFlg = false;
				$("#saveBtn").attr("name", submitFlg);
			},
			//判断报警优先级是否有效
			alarmIsEffective : function(){
				if ($('input[name="f_alarm_type"]:checked').val() == '1') {
					$('input[name="f_alarm_priority"]').attr("disabled", false);//将按钮设为有效
					$('input[name="f_close_state"]').attr("disabled", false);//将按钮设为有效
					$('input[name="f_fault_state"]').attr("disabled", false);//将按钮设为有效
					if ($("#h_f_alarm_priority").val() == '0')//报警优先级单选按钮状态设置(危及人身安全【0】严重【1】危及安全【2】)
						$("input[name='f_alarm_priority']:eq(0)").attr("checked", 'checked');
					else if ($("#h_f_alarm_priority").val() == '1')
						$("input[name='f_alarm_priority']:eq(1)").attr("checked", 'checked');
					else if ($("#h_f_alarm_priority").val() == '2')
						$("input[name='f_alarm_priority']:eq(2)").attr("checked", 'checked');

					if ($("#h_f_close_state").val() == '0')//闭合状态(不闭合【0】闭合【1】
						$("input[name='f_close_state']:eq(0)").attr("checked", 'checked');
					else if ($("#h_f_close_state").val() == '1')
						$("input[name='f_close_state']:eq(1)").attr("checked", 'checked');
					// else
					// 	$("input[name='f_close_state']:eq(2)").attr("checked", 'checked');

					if ($("#h_f_fault_state").val() == '1')
						$("input[name='f_fault_state']:eq(0)").attr("checked", 'checked');
					else
						$("input[name='f_fault_state']:eq(1)").attr("checked", 'checked');
					$('input[name=f_high_limit]').removeAttr("readonly");//高限报警
					$('input[name=f_low_limit]').removeAttr("readonly");//低限报警
                    if ($("#hidden_f_fault_state").val() == '1')
					{
                        $("input[name='f_fault_state']:eq(0)").attr("checked", 'checked');
					}
                    else
					{
                        $("input[name='f_fault_state']:eq(1)").attr("checked", 'checked');
					}
				} else {
					$('input[name="f_fault_state"]').attr("disabled", true);
					$('input[name="f_close_state"]').attr("disabled", true);
					$('input[name="f_fault_state"]').attr("disabled", true);
					$('input[name="f_alarm_priority"]').attr("disabled", true);
					$('input[name=f_high_limit]').attr("readonly", "readonly");//高限报警
					$('input[name=f_low_limit]').attr("readonly", "readonly");//低限报警
				}
			},
			inputIdNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "输入的系统名称已存在！请重新输入！";
				$("#f_sys_name").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			//根据是否作为能耗统计判断统计时间是否有效
			staticsTimeIsEffective:function(){
				if ($('input[name="f_energyStatics"]:checked').val() == '1'){//不做能耗统计
					$('input[name="f_staticsTime"]').attr("disabled", true);//按钮设为无效
				}else{
					$('input[name="f_staticsTime"]').attr("disabled", false);//按钮设为有效
				}
			},
			//tianjiangwei  判断最大值最小值
			inputValNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "最大值不得小于最小值！请重新输入！";
				$("#f_max_val").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			//tianjiangwei  ddc端口号过大时
			inputPortNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "端口号不得超过9999！请重新输入！";
				$("#f_port").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			//tianjiangwei  do点初始值为空时
			inputInitValNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "初始值不能为空！请重新输入！";
				$("#f_init_val").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},

            // 虚点 告警高限
            inputAlarmHighNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "高限报警不能为空！请重新输入！";
				$("#f_high_limit").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},

			// 虚点 告警低限
            inputAlarmLowNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "低限报警不能为空！请重新输入！";
				$("#f_low_limit").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},

			// 虚点 单位不能为空
            inputUnitNotEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "单位不能为空！请重新输入！";
				$("#f_engineer_unit").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
            // 虚点 描述不能为空
            inputDescriptionNotEffective : function(){
                var oSpan = document.createElement('span');
                oSpan.setAttribute("id", "errText");
                oSpan.setAttribute("style", "color:#F00");
                $("#errText").remove();
                oSpan.innerText = "描述不能为空！请重新输入！";
                $("#f_description").after(oSpan);
                $("#saveBtn").attr("disabled", true);
                basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
            },
            // 虚点 别名不能为空
            inputAliasNotEffective : function(){
                var oSpan = document.createElement('span');
                oSpan.setAttribute("id", "errText");
                oSpan.setAttribute("style", "color:#F00");
                $("#errText").remove();
                oSpan.innerText = "别名不能为空！请重新输入！";
                $("#f_nick_name").after(oSpan);
                $("#saveBtn").attr("disabled", true);
                basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
            },
			//tianjiangwei  验证初始值只能为整数
			inputInitValNotIntEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "初始值只能为数值！请重新输入！";
				$("#f_init_val").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},

			// 验证高限报警只能为数字
            inputAlarmHighNotNumberEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "高限报警只能为数字！请重新输入！";
				$("#f_high_limit").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			// 验证低限报警只能为数字
            inputAlarmLowNotNumberEffective : function(){
				var oSpan = document.createElement('span');
				oSpan.setAttribute("id", "errText");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText").remove();
				oSpan.innerText = "低限报警只能为数字！请重新输入！";
				$("#f_low_limit").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			//wanghongjie 如果重复的话,做出提示
			inputIdNotEffectiveByIP : function(){
				var oSpan = document.createElement('div');
				oSpan.setAttribute("id", "errText_f_ip_addr");
				oSpan.setAttribute("style", "color:#F00");
				$("#errText_f_ip_addr").remove();
				oSpan.innerText = "ip重复！！";
				$("#ip_b_4").after(oSpan);
				$("#saveBtn").attr("disabled", true);
				basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(2);
			},
			nodeAddFalse : function(returnObject){
				var remNode = $('#tree_sbdy').treeview('search', basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon());//获取刚添加的节点
				if(remNode.length>0)
				$("#tree_sbdy").treeview("removeNode",remNode[0]);
				// $('#sbdyInfo').html("");//属性区域清空
				swal({
		        	title : returnObject.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "error",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1500,
		   		});
			},
			ipIsIllegal : function(){
				var ipTxt = $("#ip_b_1").val()+"."+ $("#ip_b_2").val()+"."+$("#ip_b_3").val()+"."+ $("#ip_b_4").val();
				var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;

							if (ipTxt.match(exp)==null) {
								var oSpan = document.createElement('div');
								oSpan.setAttribute("id", "errText_f_ip_addr");
								oSpan.setAttribute("style", "color:#F00");
								$("#errText_f_ip_addr").remove();
								oSpan.innerText = "网络地址格式不正确！";
								$("#ip_b_4").after(oSpan);
								$("#saveBtn").attr("disabled", true);
								return false;
							}else{
								$("#saveBtn").attr("disabled", false);
								return true;
							}
					},
            nodeName: ''
		};

	})(jQuery, window, document);