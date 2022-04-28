<link href="${ctx}/static/css/zmjk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="zmjk_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
										aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
	</div>
	<div id="sbdy_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
									   aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
			<a id="addControlInfo" href="javascript:void(-1);" onclick="dataAnalysis_zmjk.getControlInfo()" class="btn btn-primary toLeft">
				<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>自动导入
			</a>
		</div>
		<div id="zmjk_page" style="height:96%;width:100%"></div>

	</div>
</div>
<!-- 右侧监控界面End -->

<script type="text/javascript">
	;
	var dataAnalysis_zmjk = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var clock = '';//定时任务返回id
		var selected_treeview = null;//treeview上选中的节点
		var tabId = "";//当前tab页的ID
		var nodePage = "";//节点对应的页面
		clock = window.setInterval("dataAnalysis_zmjk.refresh()",3000);

		//加载树
		$(function() {
			//配置楼宇tree
			var treeList = new Array();
			var treechildList = new Array();
			treechildList.push({nodeTreeId:"0002",pid:"01",text:"2F智能照明系统",id:"0002",level:"1",leaf:true,nodePage:"zmjk2F",},
					{nodeTreeId:"0003",pid:"01",text:"3F智能照明系统",id:"0003",level:"1",leaf:true,nodePage:"zmjk3F",},
					{nodeTreeId:"0004",pid:"01",text:"4F智能照明系统",id:"0004",level:"1",leaf:true,nodePage:"zmjk4F",},
					{nodeTreeId:"0005",pid:"01",text:"5F智能照明系统",id:"0005",level:"1",leaf:true,nodePage:"zmjk5F",},
					{nodeTreeId:"00018",pid:"01",text:"18F智能照明系统",id:"00018",level:"1",leaf:true,nodePage:"zmjk18F",},
					{nodeTreeId:"00019",pid:"01",text:"19F智能照明系统",id:"00019",level:"1",leaf:true,nodePage:"zmjk19F",},
					{nodeTreeId:"00020",pid:"01",text:"20F智能照明系统",id:"00020",level:"1",leaf:true,nodePage:"zmjk20F",},
					{nodeTreeId:"00021",pid:"01",text:"21F智能照明系统",id:"00021",level:"1",leaf:true,nodePage:"zmjk21F",},
					{nodeTreeId:"00022",pid:"01",text:"22F智能照明系统",id:"00022",level:"1",leaf:true,nodePage:"zmjk22F",},
					{nodeTreeId:"00023",pid:"01",text:"23F智能照明系统",id:"00023",level:"1",leaf:true,nodePage:"zmjk23F",},
					{nodeTreeId:"00025",pid:"01",text:"25F智能照明系统",id:"00025",level:"1",leaf:true,nodePage:"zmjk25F",},
					{nodeTreeId:"00026",pid:"01",text:"26F智能照明系统",id:"00026",level:"1",leaf:true,nodePage:"zmjk26F",}
			);
			treeList.push({nodeTreeId:"01",pid:"",text:"智能照明系统",id:"01",rootId:"01",leaf:false,nodes:treechildList});
			$('#sbdy_tree').treeview({
				data : treeList, // 数据源
				highlightSelected : true, //是否高亮选中
				levels : 2,
				enableLinks : true,//必须在节点属性给出href属性
				wrapNodeText : true,
				showImage : false,
				color : "#4a4747",
				onNodeSelected : function(event, nodeData) {//节点选中事件
					nodePage = nodeData.nodePage;
					//加载节点对应的监控界面
					loadJkPage(nodePage);
					selected_treeview = $('#sbdy_tree').treeview('getSelected');
				}
			});
			if(selected_treeview == null){
				var firstNode = $("#sbdy_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
				$("#sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
			}
		});
		function loadJkPage(nodePage){
			var variableUrl = nodePage;
			$.issp_ajax({//加载树节点信息
				isShowLoader : false,
				type : "post",
				url : _ctx + "/view/realtimemonitoring/BESZmjk/" + variableUrl,
				success : function(returnObject) {
					$("#zmjk_page").html(returnObject);
					dataAnalysis_zmjk.refreshIcon();
				},
				error : function(returnObject) {
					swal(returnObject.msg, "", "error");
				}
			});
		}
		return {
			//先查看数据库中是否已保存当前页面信息
			getControlInfo : function(){
				$.issp_ajax({
					isShowLoader : false,
					type : "post",
					url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/getControlInfo",
					data : {
						"nodePage":nodePage
					},
					success : function(result) {

						if(result.status == 1){
							swal({
								title : "本页信息已存在",
								text : "是否更新？",
								type : "warning",
								showCancelButton : true,
								confirmButtonColor : "#1ab394",
								confirmButtonText : "更新",
								cancelButtonText : "取消",
								closeOnConfirm : true,
								closeOnCancel : true
							}, function(isConfirm) {
								if (isConfirm) {
									dataAnalysis_zmjk.importControlInfo();
								} else {
								}
							});
						}else{
							dataAnalysis_zmjk.importControlInfo();
						}
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					}
				});
			},
			//控制信息导入数据库
			importControlInfo : function(){
				var buttonArray = $("[control]");
				var f_sys_name = new Array();
				var f_button_name = new Array();
				var buttonName = "";
				for(var i=0;i<buttonArray.length;i++){
					f_sys_name.push({f_sys_name:buttonArray[i].getAttribute("control")});
					if("" == buttonArray[i].innerText || null == buttonArray[i].innerText){
						buttonName = buttonArray[i].getAttribute("button-name");
					}else{
						buttonName = buttonArray[i].innerText;
					}
					f_button_name.push({f_button_name:buttonName});
				}
				$.issp_ajax({//加载树节点信息
					isShowLoader : false,
					type : "post",
					url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/importControlInfo",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify({
						f_sys_name : f_sys_name,
						f_button_name :f_button_name,
						nodePage : nodePage,
					}),
					success : function(result) {
						if(result.status == 1){
							swal({
								title : result.msg,// 展示的标题
								text : "",// 内容
								type: "success",
								showCloseButton : false, // 展示关闭按钮
								allowOutsideClick : false,
								showConfirmButton : false,
								timer: 1000
							});
						}
					},
					error : function(returnObject) {
						swal(result.msg, "", "error");
					}
				});
			},
			//逻辑点调试
			setLampPoint : function(obj) {
				var f_init_val = "";
				var pointID = obj.id;
				if(obj.value == "0"){
					f_init_val = "255";
				}else if(obj.value == "255"){
					f_init_val = "0";
				}
				$.issp_ajax({
					url : _ctx + "/api/debugPointInfo",
					type : "post",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify({
						f_sys_name : pointID,
						f_work_mode : "0",//模式“0”自动
						f_init_val : f_init_val,
						tabname : "bes_digit_ouput",
						nodeLevel : "5",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
						f_node_attribution : "2",
					}),
					success : function(result) {
						if(obj.value == "0"){
							$("#"+ pointID ).val("255");
							$("#"+ pointID +".switch-button").removeClass("offline");
							$("#"+ pointID +".switch-button").addClass("online");
							$("#"+ pointID +".circle-button").removeClass("circleoffline");
							$("#"+ pointID +".circle-button").addClass("circleonline");

						}else if(obj.value == "255"){
							$("#"+ pointID ).val("0");
							$("#"+ pointID +".switch-button").removeClass("online");
							$("#"+ pointID +".circle-button").removeClass("circleonline");
							$("#"+ pointID +".circle-button").addClass("circleoffline");
							$("#"+ pointID +".switch-button").addClass("offline");
						}
					},
					error : function(result) {
						swal(result.msg, "", "error");
					}
				});
			},
			//开关按钮调试
			setLampPointList : function(fSysnames,obj) {
// 				alert(obj.getAttribute("control"));
				var f_init_val = "";
				var pointID = obj.id;
				if(obj.value == "0"){
					f_init_val = "255";
				}else if(obj.value == "255"){
					f_init_val = "0";
				}
				$.issp_ajax({
					url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/debugPointList",
					type : "post",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify({
						f_sys_name : fSysnames,
						f_work_mode : "0",//模式“0”自动
						f_init_val : f_init_val,
						tabname : "bes_digit_ouput",
						nodeLevel : "",//选中节点在树上的级数(楼控系统为7，照明系统为5)
						f_node_attribution : "2",
					}),
					success : function(result) {
						if(result.status == 1){
							if(obj.value == "0"){
								$("#"+ pointID ).val("255");
								$("#"+ pointID ).removeClass("offline");
								$("#"+ pointID ).addClass("online");
							}else if(obj.value == "255"){
								$("#"+ pointID ).val("0");
								$("#"+ pointID ).removeClass("online");
								$("#"+ pointID ).addClass("offline");
							}
						}
					},
					error : function(result) {
						swal(result.msg, "", "error");
					}
				});
			},
			//刷新开关状态
			refresh : function() {
				if(judgeActive("zmjk_page")){//页面活动时
					$.issp_ajax({//加载树节点信息
						type : "post",
						url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
						isShowLoader : false,
						success : function(returnObject) {
							//每个开关控制的逻辑点
							var mainswitch03dn = new Array("F3M12104","F3M12105");
							var mainswitch04xjc = new Array("F4M8100","F4M8101","F4M8102","F4M8103","F4M8104","F4M8105","F4M8106","F4M8107");
							var mainswitch04djc = new Array("F4M8200","F4M8201","F4M8202","F4M8203","F4M8204","F4M8205");
							var mainswitch1901 = new Array("M8190119DNJ00","M8190119DNJ01");
							var mainswitch1902 = new Array("M4190200","M4190201","M4190202","M4190203");
							var mainswitch1906 = new Array("M8190601","M8190602","M8190603","M8190604","M8190605","M8190606","M8190607");
							var mainswitch1908 = new Array("M8190800","M8190801","M8190802","M8190803","M8190804","M8190805","M8190806","M8190807");
							var mainswitch1909 = new Array("M4190900","M4190901","M4190902");
							var mainswitch1910 = new Array("M4191000","M4191001","M4191002","M4191003");
							var mainswitch1911 = new Array("M4191100","M4191101","M4191102","M4191103");
							var mainswitch1916 = new Array("M81916DBJ00","M81916DBJ01","M81916DBJ02","M81916DBJ03","M81916DBJ04");
							var mainswitch1919 = new Array("M8190119DNJ02","M8190119DNJ03");
							var mainswitch2600 = new Array("F26M8200","F26M8201","F26M8202","F26M8203","F26M8204","F26M8207");

								var mainswitch02_btqdd = new Array("IP2M12100","IP2M12105","IP2M12108","IP2M12109","IP2M8204");
								var mainswitch02_btsd  = new Array("IP2M12107","IP2M8201","IP2M8202");
								var mainswitch02_btztd = new Array("IP2M121010","IP2M8200");
								var mainswitch02_ztddd = new Array("IP2M12102","IP2M12507","IP2M12508");
								var mainswitch02_zttd  = new Array("IP2M12106","IP2M4302","IP2M125010");
								var mainswitch02_ntsd  = new Array("IP2M12502","IP2M12503");
								var mainswitch02_ntqdd = new Array("IP2M8404","IP2M8405","IP2M8406","IP2M8407");
								var mainswitch02_ntddd = new Array("IP2M12504","IP2M12505");
								var mainswitch02_dtjd  = new Array("IP2M4300","IP2M4301");
								var mainswitch02_zkzd  = new Array("IP2M12100","IP2M12101","IP2M12102","IP2M12103","IP2M12104","IP2M12105","IP2M12106","IP2M1210","IP2M12108","IP2M12109","IP2M121010","IP2M121011","IP2M8200","IP2M8201","IP2M8202","IP2M8203","IP2M8204","IP2M8205","IP2M8206","IP2M8207","IP2M4300","IP2M4301","IP2M4302","IP2M4303","IP2M8400","IP2M8401","IP2M8402","IP2M8403","IP2M8404","IP2M8405","IP2M8406","IP2M8407","IP2M12500","IP2M12501","IP2M12502","IP2M12503","IP2M12504","IP2M12505","IP2M12506","IP2M12507","IP2M12508","IP2M12509","IP2M125010","IP2M125011");
								var mainswitch03_btqdd = new Array("IP3M8104","IP3M12200","IP3M12201","IP3M12205","IP3M12206");
								var mainswitch03_btsd  = new Array("IP3M8101","IP3M8102","IP3M12207");
								var mainswitch03_btzdd = new Array("IP3M8100","IP3M122010");
								var mainswitch03_ntqdd = new Array("IP3M12309","IP3M123010","IP3M123011");
								var mainswitch03_td    = new Array("IP3M12402","IP3M12405");
								var mainswitch03_dtk   = new Array("IP3M12300","IP3M12301");
								var mainswitch03_zkzd  = new Array("IP3M8100","IP3M8101","IP3M8102","IP3M8103","IP3M8104","IP3M8105","IP3M8106","IP3M8107","IP3M12200","IP3M12201","IP3M12202","IP3M12203","IP3M12204","IP3M12205","IP3M12206","IP3M12207","IP3M12208","IP3M12209","IP3M122010","IP3M122011","IP3M12300","IP3M12301","IP3M12302","IP3M12303","IP3M12304","IP3M12305","IP3M12306","IP3M12307","IP3M12308","IP3M12309","IP3M123010","IP3M123011","IP3M12400","IP3M12401","IP3M12402","IP3M12403","IP3M12404","IP3M12405","IP3M12406","IP3M12407","IP3M12408","IP3M12409","IP3M124010","IP3M124011");

								var mainswitch7AL01  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906","IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011");
								var mainswitch7AL03  = new Array("IP7M4100","IP7M4101","IP7M4102","IP7M4103");
								var mainswitch7AL04  = new Array("IP7M4200","IP7M4201","IP7M4202","IP7M4203");
								var mainswitch7AL05  = new Array("IP7M4400","IP7M4401","IP7M4402","IP7M4403");
								var mainswitch7AL06  = new Array("IP7M8300","IP7M8301","IP7M8302","IP7M8303");
								var mainswitch7AL07  = new Array("IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500");
								var mainswitch7AL08  = new Array("IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
								var mainswitch7F  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906",
																"IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011","IP7M4100","IP7M4101","IP7M4102","IP7M4103","IP7M4200","IP7M4201","IP7M4202","IP7M4203","IP7M4400",
																"IP7M4401","IP7M4402","IP7M4403","IP7M8300","IP7M8301","IP7M8302","IP7M8303","IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500",
																"IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
								var mainswitch4FDT  = new Array("IP4M8100","IP4M12201");
								var mainswitch4F  = new Array("IP4M8100","IP4M8101","IP4M8102","IP4M12200","IP4M12201","IP4M12202","IP4M12203","IP4M12204","IP4M12205","IP4M12206","IP4M12207","IP4M12208","IP4M12209","IP4M122010","IP4M122011");
								//var mainswitch5FZK1  = new Array("IP5M8100","IP5M8101","IP5M8102","IP5M8103","IP5M8104","IP5M8105","IP5M8106","IP5M8107");
								//var mainswitch5FZK2  = new Array("IP5M12200","IP5M12201","IP5M12202","IP5M12203","IP5M12204","IP5M12205","IP5M12206","IP5M12207","IP5M12208","IP5M12209","IP5M122010","IP5M122011");
								var mainswitch6FDT  = new Array("IP6M12201","IP6M12209");
								var mainswitch6F  = new Array("IP6M12200","IP6M12201","IP6M12202","IP6M12203","IP6M12204","IP6M12205","IP6M12206","IP6M12207","IP6M12208","IP6M12209","IP6M122010");
								var mainswitch1FMTDD  = new Array("IP1M8103","IP1M4301");
								var mainswitch1FDT  = new Array("IP1M4200","IP1M4303");
								var mainswitch1FQDD  = new Array("IP1M8100","IP1M8106","IP1M4300");
								var mainswitch1FZKKG  = new Array("IP1M8103","IP1M4301","IP1M8104","IP1M8101","IP1M8102","IP1M8107","IP1M4201","IP1M8100","IP1M8106","IP1M4300","IP1M4200","IP1M4303","IP1M4203","IP1M8105");

								var mainswitch1FBTD  = new Array("IP1MT201","IP1MT300","IP1MT3502");
								var mainswitch1FBTX  = new Array("IP1MT301","IP1MT301","IP1MT3500");
								var mainswitch1FNTX  = new Array("IP1MT3600","IP1MT3701");
								var mainswitch1FNTD  = new Array("IP1MT3700","IP1MT3702");
								var mainswitch1FZKTG  = new Array("IP1MT200","IP1MT201","IP1MT300","IP1MT301","IP1MT302","IP1MT3500","IP1MT3502","IP1MT3600","IP1MT3601","IP1MT3602","IP1MT3700","IP1MT3701","IP1MT3702");



								var pointList = [
								    {"mainswitch02_btqdd":mainswitch02_btqdd},
								    {"mainswitch02_btsd":mainswitch02_btsd},
								    {"mainswitch02_btztd":mainswitch02_btztd},
								    {"mainswitch02_ztddd":mainswitch02_ztddd},
								    {"mainswitch02_zttd":mainswitch02_zttd},
								    {"mainswitch02_ntsd":mainswitch02_ntsd},
								    {"mainswitch02_ntqdd":mainswitch02_ntqdd},
								    {"mainswitch02_ntddd":mainswitch02_ntddd},
								    {"mainswitch02_dtjd":mainswitch02_dtjd},
								    {"mainswitch02_zkzd":mainswitch02_zkzd},
								    {"mainswitch03_btqdd":mainswitch03_btqdd},
								    {"mainswitch03_btsd":mainswitch03_btsd},
								    {"mainswitch03_btzdd":mainswitch03_btzdd},
								    {"mainswitch03_ntqdd":mainswitch03_ntqdd},
								    {"mainswitch03_td":mainswitch03_td},
								    {"mainswitch03_dtk":mainswitch03_dtk},
								    {"mainswitch03_zkzd":mainswitch03_zkzd},
								    {"mainswitch7AL01":mainswitch7AL01},
								    {"mainswitch7AL03":mainswitch7AL03},
								    {"mainswitch7AL04":mainswitch7AL04},
								    {"mainswitch7AL05":mainswitch7AL05},
								    {"mainswitch7AL06":mainswitch7AL06},
								    {"mainswitch7AL07":mainswitch7AL07},
								    {"mainswitch7AL08":mainswitch7AL08},
								    {"mainswitch7F":mainswitch7F},
								    {"mainswitch4FDT":mainswitch4FDT},
								    {"mainswitch4F":mainswitch4F},
								    //{"mainswitch5FZK1":mainswitch5FZK1},
								    //{"mainswitch5FZK2":mainswitch5FZK2},
								    {"mainswitch6FDT":mainswitch6FDT},
								    {"mainswitch6F":mainswitch6F},
								    {"mainswitch1FMTDD":mainswitch1FMTDD},
								    {"mainswitch1FDT":mainswitch1FDT},
								    {"mainswitch1FQDD":mainswitch1FQDD},
								    {"mainswitch1FZKKG":mainswitch1FZKKG},
								    {"mainswitch1FBTD":mainswitch1FBTD},
								    {"mainswitch1FBTX":mainswitch1FBTX},
								    {"mainswitch1FNTX":mainswitch1FNTX},
								    {"mainswitch1FNTD":mainswitch1FNTD},
								    {"mainswitch1FZKTG":mainswitch1FZKTG}
											 ];
								if(returnObject.hasOwnProperty('list')){
									var updateInfo = returnObject.list;
									for (var i = 0; i < updateInfo.length; i++) {
										if (updateInfo[i] != '' || updateInfo[i] != null) {
											if (updateInfo[i].f_init_val == "0"){//关闭
												$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("online");
												$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("offline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleonline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleoffline");
												$("#" + updateInfo[i].f_sys_name).val("0");
											}else{
												$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("offline");
												$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("online");
												$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleonline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleoffline");
												$("#" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);
											}
											//遍历所有开关按钮
											for(var x=0;x<pointList.length;x++){
												//取开关关联的所有点
												for(var key in pointList[x]){
													var keyname = key;
													var points = pointList[x][key];
													//遍历该开关的所有点
													for(var j=0;j<pointList[x][key].length;j++){
														if(updateInfo[i].f_sys_name == points[j]){
															pointList[x][key][j] = updateInfo[i].f_init_val;
														}
								var mainswitch02_btqdd = new Array("IP2M12100","IP2M12105","IP2M12108","IP2M12109","IP2M8204");
								var mainswitch02_btsd  = new Array("IP2M12107","IP2M8201","IP2M8202");
								var mainswitch02_btztd = new Array("IP2M121010","IP2M8200");
								var mainswitch02_ztddd = new Array("IP2M12102","IP2M12507","IP2M12508");
								var mainswitch02_zttd  = new Array("IP2M12106","IP2M4302","IP2M125010");
								var mainswitch02_ntsd  = new Array("IP2M12502","IP2M12503");
								var mainswitch02_ntqdd = new Array("IP2M8404","IP2M8405","IP2M8406","IP2M8407");
								var mainswitch02_ntddd = new Array("IP2M12504","IP2M12505");
								var mainswitch02_dtjd  = new Array("IP2M4300","IP2M4301");
								var mainswitch02_zkzd  = new Array("IP2M12100","IP2M12101","IP2M12102","IP2M12103","IP2M12104","IP2M12105","IP2M12106","IP2M1210","IP2M12108","IP2M12109","IP2M121010","IP2M121011","IP2M8200","IP2M8201","IP2M8202","IP2M8203","IP2M8204","IP2M8205","IP2M8206","IP2M8207","IP2M4300","IP2M4301","IP2M4302","IP2M4303","IP2M8400","IP2M8401","IP2M8402","IP2M8403","IP2M8404","IP2M8405","IP2M8406","IP2M8407","IP2M12500","IP2M12501","IP2M12502","IP2M12503","IP2M12504","IP2M12505","IP2M12506","IP2M12507","IP2M12508","IP2M12509","IP2M125010","IP2M125011");
								var mainswitch03_btqdd = new Array("IP3M8104","IP3M12200","IP3M12201","IP3M12205","IP3M12206");
								var mainswitch03_btsd  = new Array("IP3M8101","IP3M8102","IP3M12207");
								var mainswitch03_btzdd = new Array("IP3M8100","IP3M122010");
								var mainswitch03_ntqdd = new Array("IP3M12309","IP3M123010","IP3M123011");
								var mainswitch03_td    = new Array("IP3M12402","IP3M12405");
								var mainswitch03_dtk   = new Array("IP3M12300","IP3M12301");
								var mainswitch03_zkzd  = new Array("IP3M8100","IP3M8101","IP3M8102","IP3M8103","IP3M8104","IP3M8105","IP3M8106","IP3M8107","IP3M12200","IP3M12201","IP3M12202","IP3M12203","IP3M12204","IP3M12205","IP3M12206","IP3M12207","IP3M12208","IP3M12209","IP3M122010","IP3M122011","IP3M12300","IP3M12301","IP3M12302","IP3M12303","IP3M12304","IP3M12305","IP3M12306","IP3M12307","IP3M12308","IP3M12309","IP3M123010","IP3M123011","IP3M12400","IP3M12401","IP3M12402","IP3M12403","IP3M12404","IP3M12405","IP3M12406","IP3M12407","IP3M12408","IP3M12409","IP3M124010","IP3M124011");

								var mainswitch7AL01  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906","IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011");
								var mainswitch7AL03  = new Array("IP7M4100","IP7M4101","IP7M4102","IP7M4103");
								var mainswitch7AL04  = new Array("IP7M4200","IP7M4201","IP7M4202","IP7M4203");
								var mainswitch7AL05  = new Array("IP7M4400","IP7M4401","IP7M4402","IP7M4403");
								var mainswitch7AL06  = new Array("IP7M8300","IP7M8301","IP7M8302","IP7M8303");
								var mainswitch7AL07  = new Array("IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500");
								var mainswitch7AL08  = new Array("IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
								var mainswitch7F  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906",
																"IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011","IP7M4100","IP7M4101","IP7M4102","IP7M4103","IP7M4200","IP7M4201","IP7M4202","IP7M4203","IP7M4400",
																"IP7M4401","IP7M4402","IP7M4403","IP7M8300","IP7M8301","IP7M8302","IP7M8303","IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500",
																"IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
								var mainswitch4FDT  = new Array("IP4M8100","IP4M12201");
								var mainswitch4F  = new Array("IP4M8100","IP4M8101","IP4M8102","IP4M12200","IP4M12201","IP4M12202","IP4M12203","IP4M12204","IP4M12205","IP4M12206","IP4M12207","IP4M12208","IP4M12209","IP4M122010","IP4M122011");
								var mainswitch5FZK1  = new Array("IP5M8100","IP5M8101","IP5M8102","IP5M8103","IP5M8104","IP5M8105","IP5M8106","IP5M8107");
								var mainswitch5FZK2  = new Array("IP5M12200","IP5M12201","IP5M12202","IP5M12203","IP5M12204","IP5M12205","IP5M12206","IP5M12207","IP5M12208","IP5M12209","IP5M122010","IP5M122011");
								var mainswitch6FDT  = new Array("IP6M12201","IP6M12209");
								var mainswitch6F  = new Array("IP6M12200","IP6M12201","IP6M12202","IP6M12203","IP6M12204","IP6M12205","IP6M12206","IP6M12207","IP6M12208","IP6M12209","IP6M122010");
								var mainswitch1FMTDD  = new Array("IP1M8103","IP1M4301");
								var mainswitch1FDT  = new Array("IP1M4200","IP1M4303");
								var mainswitch1FQDD  = new Array("IP1M8100","IP1M8106","IP1M4300");
								var mainswitch1FZKKG  = new Array("IP1M8103","IP1M4301","IP1M8104","IP1M8101","IP1M8102","IP1M8107","IP1M4201","IP1M8100","IP1M8106","IP1M4300","IP1M4200","IP1M4303","IP1M4203","IP1M8105");

								var mainswitch1FBTD  = new Array("IP1MT201","IP1MT300","IP1MT3502");
								var mainswitch1FBTX  = new Array("IP1MT301","IP1MT301","IP1MT3500");
								var mainswitch1FNTX  = new Array("IP1MT3600","IP1MT3701");
								var mainswitch1FNTD  = new Array("IP1MT3700","IP1MT3702");
								var mainswitch1FZKTG  = new Array("IP1MT200","IP1MT201","IP1MT300","IP1MT301","IP1MT302","IP1MT3500","IP1MT3502","IP1MT3600","IP1MT3601","IP1MT3602","IP1MT3700","IP1MT3701","IP1MT3702");



								var pointList = [
								    {"mainswitch02_btqdd":mainswitch02_btqdd},
								    {"mainswitch02_btsd":mainswitch02_btsd},
								    {"mainswitch02_btztd":mainswitch02_btztd},
								    {"mainswitch02_ztddd":mainswitch02_ztddd},
								    {"mainswitch02_zttd":mainswitch02_zttd},
								    {"mainswitch02_ntsd":mainswitch02_ntsd},
								    {"mainswitch02_ntqdd":mainswitch02_ntqdd},
								    {"mainswitch02_ntddd":mainswitch02_ntddd},
								    {"mainswitch02_dtjd":mainswitch02_dtjd},
								    {"mainswitch02_zkzd":mainswitch02_zkzd},
								    {"mainswitch03_btqdd":mainswitch03_btqdd},
								    {"mainswitch03_btsd":mainswitch03_btsd},
								    {"mainswitch03_btzdd":mainswitch03_btzdd},
								    {"mainswitch03_ntqdd":mainswitch03_ntqdd},
								    {"mainswitch03_td":mainswitch03_td},
								    {"mainswitch03_dtk":mainswitch03_dtk},
								    {"mainswitch03_zkzd":mainswitch03_zkzd},
								    {"mainswitch7AL01":mainswitch7AL01},
								    {"mainswitch7AL03":mainswitch7AL03},
								    {"mainswitch7AL04":mainswitch7AL04},
								    {"mainswitch7AL05":mainswitch7AL05},
								    {"mainswitch7AL06":mainswitch7AL06},
								    {"mainswitch7AL07":mainswitch7AL07},
								    {"mainswitch7AL08":mainswitch7AL08},
								    {"mainswitch7F":mainswitch7F},
								    {"mainswitch4FDT":mainswitch4FDT},
								    {"mainswitch4F":mainswitch4F},
								    {"mainswitch5FZK1":mainswitch5FZK1},
								    {"mainswitch5FZK2":mainswitch5FZK2},
								    {"mainswitch6FDT":mainswitch6FDT},
								    {"mainswitch6F":mainswitch6F},
								    {"mainswitch1FMTDD":mainswitch1FMTDD},
								    {"mainswitch1FDT":mainswitch1FDT},
								    {"mainswitch1FQDD":mainswitch1FQDD},
								    {"mainswitch1FZKKG":mainswitch1FZKKG},
								    {"mainswitch1FBTD":mainswitch1FBTD},
								    {"mainswitch1FBTX":mainswitch1FBTX},
								    {"mainswitch1FNTX":mainswitch1FNTX},
								    {"mainswitch1FNTD":mainswitch1FNTD},
								    {"mainswitch1FZKTG":mainswitch1FZKTG}
											 ];
								if(returnObject.hasOwnProperty('list')){
									var updateInfo = returnObject.list;
									for (var i = 0; i < updateInfo.length; i++) {
										if (updateInfo[i] != '' || updateInfo[i] != null) {
											if (updateInfo[i].f_init_val == "0"){//关闭
												$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("online");
												$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("offline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleonline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleoffline");
												$("#" + updateInfo[i].f_sys_name).val("0");
											}else{
												$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("offline");
												$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("online");
												$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleonline");
												$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleoffline");
												$("#" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);
											}
											//遍历所有开关按钮
											for(var x=0;x<pointList.length;x++){
												//取开关关联的所有点
												for(var key in pointList[x]){
													var keyname = key;
													var points = pointList[x][key];
													//遍历该开关的所有点
													for(var j=0;j<pointList[x][key].length;j++){
														if(updateInfo[i].f_sys_name == points[j]){
															pointList[x][key][j] = updateInfo[i].f_init_val;
														}
							var pointList = [{"mainswitch03dn": mainswitch03dn},{"mainswitch04xjc": mainswitch04xjc},{"mainswitch04djc": mainswitch04djc},
								{"mainswitch1901": mainswitch1901},{"mainswitch1902": mainswitch1902},{"mainswitch1906": mainswitch1906},
								{"mainswitch1908": mainswitch1908},{"mainswitch1909": mainswitch1909},{"mainswitch1910": mainswitch1910},
								{"mainswitch1911": mainswitch1911},{"mainswitch1916": mainswitch1916},{"mainswitch1919": mainswitch1919},{"mainswitch2600": mainswitch2600}];
							if(returnObject.hasOwnProperty('list')){
								var updateInfo = returnObject.list;
								for (var i = 0; i < updateInfo.length; i++) {
									if (!updateInfo[i] == '' || !updateInfo[i] == null) {
										if (updateInfo[i].f_init_val == "0"){//关闭
											$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("online");
											$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("offline");
											$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleonline");
											$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleoffline");
											$("#" + updateInfo[i].f_sys_name).val("0");
										}else{
											$("#" + updateInfo[i].f_sys_name +".switch-button").removeClass("offline");
											$("#" + updateInfo[i].f_sys_name +".switch-button").addClass("online");
											$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleonline");
											$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleoffline");
											$("#" + updateInfo[i].f_sys_name).val("255");
										}
										//遍历所有开关按钮
										for(var x=0;x<pointList.length;x++){
											//取开关关联的所有点
											for(var key in pointList[x]){
												var keyname = key;
												var points = pointList[x][key];
												//遍历该开关的所有点
												for(var j=0;j<pointList[x][key].length;j++){
													if(updateInfo[i].f_sys_name == points[j]){
														pointList[x][key][j] = updateInfo[i].f_init_val;
													}
												}
											}
										}
									}
								}
								//判断开关按钮的状态
								for(var m=0;m<pointList.length;m++){
									for(var key in pointList[m]){
										var keyname = key;
										var points = pointList[m][key];
										for(var n=0;n<points.length;n++){
											if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== "255"){
												$("#" + keyname).val("255");
												$("#" + keyname+".switch-button").removeClass("offline");
												$("#" + keyname+".switch-button").removeClass("partonline");
												$("#" + keyname+".switch-button").addClass("online");
												$("#" + keyname+".circle-button").removeClass("circleoffline");
												$("#" + keyname+".circle-button").removeClass("circlepartonline");
												$("#" + keyname+".circle-button").addClass("circleonline");
											}else if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== "0"){
												$("#" + keyname).val("0");
												$("#" + keyname+".switch-button").removeClass("online");
												$("#" + keyname+".switch-button").removeClass("partonline");
												$("#" + keyname+".switch-button").addClass("offline");
												$("#" + keyname+".circle-button").removeClass("circleonline");
												$("#" + keyname+".circle-button").removeClass("circlepartonline");
												$("#" + keyname+".circle-button").addClass("circleoffline");
											}else{
												$("#" + keyname).val("0");
												$("#" + keyname+".switch-button").removeClass("online");
												$("#" + keyname+".switch-button").removeClass("offline");
												$("#" + keyname+".switch-button").addClass("partonline");
												$("#" + keyname+".circle-button").removeClass("circleonline");
												$("#" + keyname+".circle-button").removeClass("circleoffline");
												$("#" + keyname+".circle-button").addClass("circlepartonline");
											}
										}
									}

								}
							}
						},
						error : function(returnObject) {
							swal(returnObject.msg, "", "error");
						}
					});
				}

			},
			//加载逻辑点状态信息
			refreshIcon : function() {
				$.ajax({//加载树节点信息
					type : "post",
					url : _ctx + "/view/basedatamanage/eqmanage/getAllDOInfo",
					success : function(returnObject) {
						//每个开关控制的逻辑点
						var mainswitch03dn = new Array("F3M12104","F3M12105");
						var mainswitch04xjc = new Array("F4M8100","F4M8101","F4M8102","F4M8103","F4M8104","F4M8105","F4M8106","F4M8107");
						var mainswitch04djc = new Array("F4M8200","F4M8201","F4M8202","F4M8203","F4M8204","F4M8205");
						var mainswitch1901 = new Array("M8190119DNJ00","M8190119DNJ01");
						var mainswitch1902 = new Array("M4190200","M4190201","M4190202","M4190203");
						var mainswitch1906 = new Array("M8190601","M8190602","M8190603","M8190604","M8190605","M8190606","M8190607");
						var mainswitch1908 = new Array("M8190800","M8190801","M8190802","M8190803","M8190804","M8190805","M8190806","M8190807");
						var mainswitch1909 = new Array("M4190900","M4190901","M4190902");
						var mainswitch1910 = new Array("M4191000","M4191001","M4191002","M4191003");
						var mainswitch1911 = new Array("M4191100","M4191101","M4191102","M4191103");
						var mainswitch1916 = new Array("M81916DBJ00","M81916DBJ01","M81916DBJ02","M81916DBJ03","M81916DBJ04");
						var mainswitch1919 = new Array("M8190119DNJ02","M8190119DNJ03");
						var mainswitch2600 = new Array("F26M8200","F26M8201","F26M8202","F26M8203","F26M8204","F26M8207");

						var mainswitch7AL01  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906","IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011");
						var mainswitch7AL03  = new Array("IP7M4100","IP7M4101","IP7M4102","IP7M4103");
						var mainswitch7AL04  = new Array("IP7M4200","IP7M4201","IP7M4202","IP7M4203");
						var mainswitch7AL05  = new Array("IP7M4400","IP7M4401","IP7M4402","IP7M4403");
						var mainswitch7AL06  = new Array("IP7M8300","IP7M8301","IP7M8302","IP7M8303");
						var mainswitch7AL07  = new Array("IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500");
						var mainswitch7AL08  = new Array("IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
						var mainswitch7F  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906",
														"IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011","IP7M4100","IP7M4101","IP7M4102","IP7M4103","IP7M4200","IP7M4201","IP7M4202","IP7M4203","IP7M4400",
														"IP7M4401","IP7M4402","IP7M4403","IP7M8300","IP7M8301","IP7M8302","IP7M8303","IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500",
														"IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
						var mainswitch4FDT  = new Array("IP4M8100","IP4M12201");
						var mainswitch4F  = new Array("IP4M8100","IP4M8101","IP4M8102","IP4M12200","IP4M12201","IP4M12202","IP4M12203","IP4M12204","IP4M12205","IP4M12206","IP4M12207","IP4M12208","IP4M12209","IP4M122010","IP4M122011");
						//var mainswitch5FZK1  = new Array("IP5M8100","IP5M8101","IP5M8102","IP5M8103","IP5M8104","IP5M8105","IP5M8106","IP5M8107");
						//var mainswitch5FZK2  = new Array("IP5M12200","IP5M12201","IP5M12202","IP5M12203","IP5M12204","IP5M12205","IP5M12206","IP5M12207","IP5M12208","IP5M12209","IP5M122010","IP5M122011");
						var mainswitch6FDT  = new Array("IP6M12201","IP6M12209");
						var mainswitch6F  = new Array("IP6M12200","IP6M12201","IP6M12202","IP6M12203","IP6M12204","IP6M12205","IP6M12206","IP6M12207","IP6M12208","IP6M12209","IP6M122010");
						var mainswitch1FMTDD  = new Array("IP1M8103","IP1M4301");
						var mainswitch1FDT  = new Array("IP1M4200","IP1M4303");
						var mainswitch1FQDD  = new Array("IP1M8100","IP1M8106","IP1M4300");
						var mainswitch1FZKKG  = new Array("IP1M8103","IP1M4301","IP1M8104","IP1M8101","IP1M8102","IP1M8107","IP1M4201","IP1M8100","IP1M8106","IP1M4300","IP1M4200","IP1M4303","IP1M4203","IP1M8105");

						var mainswitch1FBTD  = new Array("IP1MT201","IP1MT300","IP1MT3502");
						var mainswitch1FBTX  = new Array("IP1MT301","IP1MT301","IP1MT3500");
						var mainswitch1FNTX  = new Array("IP1MT3600","IP1MT3701");
						var mainswitch1FNTD  = new Array("IP1MT3700","IP1MT3702");
						var mainswitch1FZKTG  = new Array("IP1MT200","IP1MT201","IP1MT300","IP1MT301","IP1MT302","IP1MT3500","IP1MT3502","IP1MT3600","IP1MT3601","IP1MT3602","IP1MT3700","IP1MT3701","IP1MT3702");
						var selectArray = ['mainswitch1FBTD','mainswitch1FBTX','mainswitch1FNTX','mainswitch1FNTD','mainswitch1FZKTG'];

						var pointList = [
                            {"mainswitch02_btqdd":mainswitch02_btqdd},
                            {"mainswitch02_btsd":mainswitch02_btsd},
                            {"mainswitch02_btztd":mainswitch02_btztd},
                            {"mainswitch02_ztddd":mainswitch02_ztddd},
                            {"mainswitch02_zttd":mainswitch02_zttd},
                            {"mainswitch02_ntsd":mainswitch02_ntsd},
                            {"mainswitch02_ntqdd":mainswitch02_ntqdd},
                            {"mainswitch02_ntddd":mainswitch02_ntddd},
                            {"mainswitch02_dtjd":mainswitch02_dtjd},
                            {"mainswitch02_zkzd":mainswitch02_zkzd},
                            {"mainswitch03_btqdd":mainswitch03_btqdd},
                            {"mainswitch03_btsd":mainswitch03_btsd},
                            {"mainswitch03_btzdd":mainswitch03_btzdd},
                            {"mainswitch03_ntqdd":mainswitch03_ntqdd},
                            {"mainswitch03_td":mainswitch03_td},
                            {"mainswitch03_dtk":mainswitch03_dtk},
                            {"mainswitch03_zkzd":mainswitch03_zkzd},
							{"mainswitch7AL01":mainswitch7AL01},
							{"mainswitch7AL03":mainswitch7AL03},
							{"mainswitch7AL04":mainswitch7AL04},
							{"mainswitch7AL05":mainswitch7AL05},
							{"mainswitch7AL06":mainswitch7AL06},
							{"mainswitch7AL07":mainswitch7AL07},
							{"mainswitch7AL08":mainswitch7AL08},
							{"mainswitch7F":mainswitch7F},
							{"mainswitch4FDT":mainswitch4FDT},
							{"mainswitch4F":mainswitch4F},
							//{"mainswitch5FZK1":mainswitch5FZK1},
							//{"mainswitch5FZK2":mainswitch5FZK2},
							{"mainswitch6FDT":mainswitch6FDT},
							{"mainswitch6F":mainswitch6F},
							{"mainswitch1FMTDD":mainswitch1FMTDD},
							{"mainswitch1FDT":mainswitch1FDT},
							{"mainswitch1FQDD":mainswitch1FQDD},
							{"mainswitch1FZKKG":mainswitch1FZKKG},
							{"mainswitch1FBTD":mainswitch1FBTD},
							{"mainswitch1FBTX":mainswitch1FBTX},
							{"mainswitch1FNTX":mainswitch1FNTX},
							{"mainswitch1FNTD":mainswitch1FNTD},
							{"mainswitch1FZKTG":mainswitch1FZKTG}
                                     ];
						var mainswitch7AL01  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906","IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011");
						var mainswitch7AL03  = new Array("IP7M4100","IP7M4101","IP7M4102","IP7M4103");
						var mainswitch7AL04  = new Array("IP7M4200","IP7M4201","IP7M4202","IP7M4203");
						var mainswitch7AL05  = new Array("IP7M4400","IP7M4401","IP7M4402","IP7M4403");
						var mainswitch7AL06  = new Array("IP7M8300","IP7M8301","IP7M8302","IP7M8303");
						var mainswitch7AL07  = new Array("IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500");
						var mainswitch7AL08  = new Array("IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
						var mainswitch7F  = new Array("IP7M8800","IP7M8801","IP7M8802","IP7M8803","IP7M8804","IP7M8805","IP7M8806","IP7M12900","IP7M12901","IP7M12902","IP7M12903","IP7M12904","IP7M12905","IP7M12906",
														"IP7M12907","IP7M12908","IP7M12909","IP7M129010","IP7M129011","IP7M4100","IP7M4101","IP7M4102","IP7M4103","IP7M4200","IP7M4201","IP7M4202","IP7M4203","IP7M4400",
														"IP7M4401","IP7M4402","IP7M4403","IP7M8300","IP7M8301","IP7M8302","IP7M8303","IP7M8501","IP7M8502","IP7M8503","IP7M8504","IP7M8505","IP7M8506","IP7M8507","IP7M8500",
														"IP7M8600","IP7M8601","IP7M8602","IP7M8603","IP7M8604","IP7M8605","IP7M8606","IP7M8607","IP7M4700","IP7M4701","IP7M4702","IP7M4703");
						var mainswitch4FDT  = new Array("IP4M8100","IP4M12201");
						var mainswitch4F  = new Array("IP4M8100","IP4M8101","IP4M8102","IP4M12200","IP4M12201","IP4M12202","IP4M12203","IP4M12204","IP4M12205","IP4M12206","IP4M12207","IP4M12208","IP4M12209","IP4M122010","IP4M122011");
						var mainswitch5FZK1  = new Array("IP5M8100","IP5M8101","IP5M8102","IP5M8103","IP5M8104","IP5M8105","IP5M8106","IP5M8107");
						var mainswitch5FZK2  = new Array("IP5M12200","IP5M12201","IP5M12202","IP5M12203","IP5M12204","IP5M12205","IP5M12206","IP5M12207","IP5M12208","IP5M12209","IP5M122010","IP5M122011");
						var mainswitch6FDT  = new Array("IP6M12201","IP6M12209");
						var mainswitch6F  = new Array("IP6M12200","IP6M12201","IP6M12202","IP6M12203","IP6M12204","IP6M12205","IP6M12206","IP6M12207","IP6M12208","IP6M12209","IP6M122010");
						var mainswitch1FMTDD  = new Array("IP1M8103","IP1M4301");
						var mainswitch1FDT  = new Array("IP1M4200","IP1M4303");
						var mainswitch1FQDD  = new Array("IP1M8100","IP1M8106","IP1M4300");
						var mainswitch1FZKKG  = new Array("IP1M8103","IP1M4301","IP1M8104","IP1M8101","IP1M8102","IP1M8107","IP1M4201","IP1M8100","IP1M8106","IP1M4300","IP1M4200","IP1M4303","IP1M4203","IP1M8105");

						var mainswitch1FBTD  = new Array("IP1MT201","IP1MT300","IP1MT3502");
						var mainswitch1FBTX  = new Array("IP1MT301","IP1MT301","IP1MT3500");
						var mainswitch1FNTX  = new Array("IP1MT3600","IP1MT3701");
						var mainswitch1FNTD  = new Array("IP1MT3700","IP1MT3702");
						var mainswitch1FZKTG  = new Array("IP1MT200","IP1MT201","IP1MT300","IP1MT301","IP1MT302","IP1MT3500","IP1MT3502","IP1MT3600","IP1MT3601","IP1MT3602","IP1MT3700","IP1MT3701","IP1MT3702");
						var selectArray = ['mainswitch1FBTD','mainswitch1FBTX','mainswitch1FNTX','mainswitch1FNTD','mainswitch1FZKTG'];

						var pointList = [
                            {"mainswitch02_btqdd":mainswitch02_btqdd},
                            {"mainswitch02_btsd":mainswitch02_btsd},
                            {"mainswitch02_btztd":mainswitch02_btztd},
                            {"mainswitch02_ztddd":mainswitch02_ztddd},
                            {"mainswitch02_zttd":mainswitch02_zttd},
                            {"mainswitch02_ntsd":mainswitch02_ntsd},
                            {"mainswitch02_ntqdd":mainswitch02_ntqdd},
                            {"mainswitch02_ntddd":mainswitch02_ntddd},
                            {"mainswitch02_dtjd":mainswitch02_dtjd},
                            {"mainswitch02_zkzd":mainswitch02_zkzd},
                            {"mainswitch03_btqdd":mainswitch03_btqdd},
                            {"mainswitch03_btsd":mainswitch03_btsd},
                            {"mainswitch03_btzdd":mainswitch03_btzdd},
                            {"mainswitch03_ntqdd":mainswitch03_ntqdd},
                            {"mainswitch03_td":mainswitch03_td},
                            {"mainswitch03_dtk":mainswitch03_dtk},
                            {"mainswitch03_zkzd":mainswitch03_zkzd},
							{"mainswitch7AL01":mainswitch7AL01},
							{"mainswitch7AL03":mainswitch7AL03},
							{"mainswitch7AL04":mainswitch7AL04},
							{"mainswitch7AL05":mainswitch7AL05},
							{"mainswitch7AL06":mainswitch7AL06},
							{"mainswitch7AL07":mainswitch7AL07},
							{"mainswitch7AL08":mainswitch7AL08},
							{"mainswitch7F":mainswitch7F},
							{"mainswitch4FDT":mainswitch4FDT},
							{"mainswitch4F":mainswitch4F},
							{"mainswitch5FZK1":mainswitch5FZK1},
							{"mainswitch5FZK2":mainswitch5FZK2},
							{"mainswitch6FDT":mainswitch6FDT},
							{"mainswitch6F":mainswitch6F},
							{"mainswitch1FMTDD":mainswitch1FMTDD},
							{"mainswitch1FDT":mainswitch1FDT},
							{"mainswitch1FQDD":mainswitch1FQDD},
							{"mainswitch1FZKKG":mainswitch1FZKKG},
							{"mainswitch1FBTD":mainswitch1FBTD},
							{"mainswitch1FBTX":mainswitch1FBTX},
							{"mainswitch1FNTX":mainswitch1FNTX},
							{"mainswitch1FNTD":mainswitch1FNTD},
							{"mainswitch1FZKTG":mainswitch1FZKTG}
                                     ];
						var pointList = [{"mainswitch03dn": mainswitch03dn},{"mainswitch04xjc": mainswitch04xjc},{"mainswitch04djc": mainswitch04djc},
							{"mainswitch1901": mainswitch1901},{"mainswitch1902": mainswitch1902},{"mainswitch1906": mainswitch1906},
							{"mainswitch1908": mainswitch1908},{"mainswitch1909": mainswitch1909},{"mainswitch1910": mainswitch1910},
							{"mainswitch1911": mainswitch1911},{"mainswitch1916": mainswitch1916},{"mainswitch1919": mainswitch1919},{"mainswitch2600": mainswitch2600}];
						var updateInfo = returnObject.list;
						if(updateInfo.length != null && updateInfo.length != ""){
							for (var i = 0; i < updateInfo.length; i++) {
								if (!updateInfo[i] == '' || !updateInfo[i] == null) {
									if (updateInfo[i].F_INIT_VAL == 0){//关闭
										$("#" + updateInfo[i].F_SYS_NAME +".switch-button").removeClass("online");
										$("#" + updateInfo[i].F_SYS_NAME +".switch-button").addClass("offline");
										$("#" + updateInfo[i].F_SYS_NAME +".circle-button").removeClass("circleonline");
										$("#" + updateInfo[i].F_SYS_NAME +".circle-button").addClass("circleoffline");
										$("#" + updateInfo[i].F_SYS_NAME).val("0");
									}else{
										$("#" + updateInfo[i].F_SYS_NAME +".switch-button").removeClass("offline");
										$("#" + updateInfo[i].F_SYS_NAME +".switch-button").addClass("online");
										$("#" + updateInfo[i].F_SYS_NAME +".circle-button").addClass("circleonline");
										$("#" + updateInfo[i].F_SYS_NAME +".circle-button").removeClass("circleoffline");
										$("#" + updateInfo[i].F_SYS_NAME).val("255");
									}
									//遍历所有开关按钮
									for(var x=0;x<pointList.length;x++){
										//取开关关联的所有点
										for(var key in pointList[x]){
											var keyname = key;
											var points = pointList[x][key];
											//遍历该开关的所有点
											for(var j=0;j<pointList[x][key].length;j++){
												if(updateInfo[i].F_SYS_NAME == points[j]){
													pointList[x][key][j] = updateInfo[i].F_INIT_VAL;
												}
											}
										}
									}
								}
							}
							//判断开关按钮的状态
							for(var m=0;m<pointList.length;m++){
								for(var key in pointList[m]){
									var keyname = key;
									var points = pointList[m][key];
									for(var n=0;n<points.length;n++){
										if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== 255){
											$("#" + keyname).val("255");
											$("#" + keyname+".switch-button").removeClass("offline");
											$("#" + keyname+".switch-button").removeClass("partonline");
											$("#" + keyname+".switch-button").addClass("online");
											$("#" + keyname+".circle-button").removeClass("circleoffline");
											$("#" + keyname+".circle-button").removeClass("circlepartonline");
											$("#" + keyname+".circle-button").addClass("circleonline");
										}else if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== 0){
											$("#" + keyname).val("0");
											$("#" + keyname+".switch-button").removeClass("online");
											$("#" + keyname+".switch-button").removeClass("partonline");
											$("#" + keyname+".switch-button").addClass("offline");
											$("#" + keyname+".circle-button").removeClass("circleonline");
											$("#" + keyname+".circle-button").removeClass("circlepartonline");
											$("#" + keyname+".circle-button").addClass("circleoffline");
										}else{
											$("#" + keyname).val("0");
											$("#" + keyname+".switch-button").removeClass("online");
											$("#" + keyname+".switch-button").removeClass("offline");
											$("#" + keyname+".switch-button").addClass("partonline");
											$("#" + keyname+".circle-button").removeClass("circleonline");
											$("#" + keyname+".circle-button").removeClass("circleoffline");
											$("#" + keyname+".circle-button").addClass("circlepartonline");
										}
									}

								}

							}

						}
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					}
				});
			},
			pageInit : function(){
				dataAnalysis_zmjk.refreshIcon();

			}
		}

	})(jQuery, window, document);
	dataAnalysis_zmjk.pageInit();
</script>