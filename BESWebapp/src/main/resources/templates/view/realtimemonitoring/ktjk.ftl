<!-- 左侧设备树start -->
<div id="ktjk_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;请选择空调监控位置>>>
		</span>
	</div>
	<div id="sbdy_ktjk_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
				aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
		</div>
		<div id="ktjk_page" style="height:96%;width:100%"></div>
	</div>
</div>
<!-- 右侧监控界面End -->
<!-- 模式 模态框start -->
<div class="modal fade" id="modal-form-ms" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">       
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;模式设定</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="debugms" name="debugms" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">系统名称<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <input type="text" id="ms_f_sys_name" name="f_sys_name" class="form-control" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设置<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                        	<select id="ms_f_init_val" name="f_init_val"class="form-control">
								<option value=0>除湿</option>
								<option value=50>制热</option>
								<option value=100>自动</option>
								<option value=150>制冷</option>
								<option value=200>送风</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">工作模式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="radio" name="ms_f_work_mode" value="0"  />自动
							<input type="radio" name="ms_f_work_mode" value="1"  />手动
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="button" onclick="dataAnalysis_ktjk.setCrossPoint('ms','')"><strong>执行</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!-- 模式 模态框end -->

<!-- 风速 模态框start -->
<div class="modal fade" id="modal-form-fs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">       
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;风速设定</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="debugfs" name="debugfs" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">系统名称<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fs_f_sys_name" name="f_sys_name" class="form-control" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设置<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                        	<select id="fs_f_init_val" name="f_init_val"class="form-control">
								<option value=200>高速</option>
								<option value=100>中速</option>
								<option value=0>低速</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">工作模式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="radio" name="fs_f_work_mode" value="0"  />自动
							<input type="radio" name="fs_f_work_mode" value="1"  />手动
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="button" onclick="dataAnalysis_ktjk.setCrossPoint('fs','')"><strong>执行</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!-- 风速 模态框end -->

<!-- 温度 模态框start -->
<div class="modal fade" id="modal-form-wd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">       
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;温度设定</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="debugwd" name="debugwd" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">系统名称<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <input type="text" id="wd_f_sys_name" name="f_sys_name" class="form-control" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设置<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="wd_f_init_val" digits=true value="" class="form-control">
                        </div>
                        <label style="margin-left: -1em">℃</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">工作模式<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="radio" name="wd_f_work_mode" value="0"  />自动
							<input type="radio" name="wd_f_work_mode" value="1"  />手动
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="button" onclick="dataAnalysis_ktjk.setCrossPoint('wd','')"><strong>执行</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!-- 温度 模态框end -->
<style>
.div-border-first{
	top: 25%;
    height: 75%;
    width: 76%;
	border:0px solid #fafbf8;
	position: fixed;
}
.div-border-kt{
	height: 30%;
	width: 25%;
	border:3px solid #fafbf8;
	position: absolute;
}
.div-border-kt-first{
	height: 92%;
	width: 54%;
	border:3px solid #fafbf8;
	position: absolute;
	left: 44%;
    top: 3%;
}
.div-border-kt-second{
	height: 28%;
	width: 51%;
	border:3px solid #fafbf8;
	position: absolute;
	left: 5%;
    top: 6%;
}
.model-button{
	background-color: #ffffff;
	color: #000000;
	width: 50px;
    height: 20px;
    align-items: center;
    position: absolute;
}
.fs-button{
	background-color: #ffffff;
	color: #000000;
	width: 50px;
    height: 20px;
    align-items: center;
    position: absolute;
}
.spanStyle{
	font-size:20px;
	display: block;
	margin-left:3%;
	color: #000000;
}
.span-shiwen{
	font-size:20px;
	display: block;
	margin-left:28%;
	margin-top: 3%;
	color: #000000;
}
.span-fuhao{
	font-size:20px;
	display: block;
	margin-left:67%;
	margin-top: 9%;
	color: #000000;
}
.span-shezhi-num{
	font-size:40px;
	display: block;
	margin-left:38%;
	margin-top: 8%;
	color: #000000;
}
.span-shezhi{
	font-size:20px;
	display: block;
	margin-left:6%;
	margin-top: 8%;
	color: #000000;
}
.circle-button{
	border-radius: 20px;
    width: 40px;
    height: 40px;
    align-items: center;
    position: absolute;
    left:15%;
    top:50%;
}
.circleonline{
	background-color: #11de3d;
}
</style>
<script type="text/javascript">
;
var dataAnalysis_ktjk = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var selected_treeview = null;//treeview上选中的节点
		var f_sys_name = "";//当前要调试的点的f_sys_name
		var clock = '';//定时任务返回id
		clock = window.setInterval("dataAnalysis_ktjk.refresh()",100000);
		//模式模态框
	 	$('#modal-form-ms').on('show.bs.modal', function (event) {
	        $(this).css('display', 'block');  
	        var modalHeight=$(window).height() / 2 - $('#modal-form-ms .modal-dialog').height() / 2;  
	        $(this).find('.modal-dialog').css({  
	            'margin-top': modalHeight  
	        }); 
	        var button = $(event.relatedTarget);
	        f_sys_name = button.data("id");
			//查询该逻辑点信息
			$.ajax({
		 	       url: _ctx + "/view/realtimemonitoring/BESKtjz/getPointInfo",
		 	       type: "post", 
		 	       data:{     
		 	 			f_sys_name : f_sys_name,
		 	 			tabname : "bes_digit_ouput",
		 	 		},
		 	success: function(result) {
		 	         $("#ms_f_sys_name").val(result.data.F_SYS_NAME);
		 	         $("#ms_f_init_val").val(result.data.F_INIT_VAL);
					 (result.data.F_WORK_MODE == 0 ) ? $("input[name='ms_f_work_mode']:eq(0)").attr("checked", 'checked') : $("input[name='ms_f_work_mode']:eq(1)").attr("checked", 'checked');
		 	},
		    }); 
			
		})
		//关闭包含模态框清空表单值
		$("#modal-form-ms").on('hidden.bs.modal', function (event) {
			$(this).find("input[name='f_sys_name']").val("");
		});
		//风速模态框
		$('#modal-form-fs').on('show.bs.modal', function (event) {
	        $(this).css('display', 'block');  
	        var modalHeight=$(window).height() / 2 - $('#modal-form-fs .modal-dialog').height() / 2;  
	        $(this).find('.modal-dialog').css({  
	            'margin-top': modalHeight  
	        }); 
	        var button = $(event.relatedTarget);
			f_sys_name = button.data("id");
			//查询该逻辑点信息
			$.ajax({
		 	       url: _ctx + "/view/realtimemonitoring/BESKtjz/getPointInfo",
		 	       type: "post", 
		 	       data:{     
		 	 			f_sys_name : f_sys_name,
		 	 			tabname : "bes_digit_ouput",
		 	 		},
		 	success: function(result) {
		 	         $("#fs_f_sys_name").val(result.data.F_SYS_NAME);
		 	         $("#fs_f_init_val").val(result.data.F_INIT_VAL);
					 (result.data.F_WORK_MODE == 0 ) ? $("input[name='fs_f_work_mode']:eq(0)").attr("checked", 'checked') : $("input[name='fs_f_work_mode']:eq(1)").attr("checked", 'checked');
		 	},
		    }); 
			
		})
		$("#modal-form-fs").on('hidden.bs.modal', function (event) {
			$(this).find("input[name='f_sys_name']").val("");
		});
		//温度模态框
		$('#modal-form-wd').on('show.bs.modal', function (event) {
	        $(this).css('display', 'block');  
	        var modalHeight=$(window).height() / 2 - $('#modal-form-wd .modal-dialog').height() / 2;  
	        $(this).find('.modal-dialog').css({  
	            'margin-top': modalHeight  
	        }); 
	        var button = $(event.relatedTarget);
			f_sys_name = button.data("id");
			//查询该逻辑点信息
			$.ajax({
		 	       url: _ctx + "/view/realtimemonitoring/BESKtjz/getPointInfo",
		 	       type: "post", 
		 	       data:{     
		 	 			f_sys_name : f_sys_name,
		 	 			tabname : "bes_analog_ouput",
		 	 		},
		 	success: function(result) {
		 	         $("#wd_f_sys_name").val(result.data.F_SYS_NAME);
		 	         $("#wd_f_init_val").val(result.data.F_INIT_VAL);
					 (result.data.F_WORK_MODE == 0 ) ? $("input[name='wd_f_work_mode']:eq(0)").attr("checked", 'checked') : $("input[name='wd_f_work_mode']:eq(1)").attr("checked", 'checked');
		 	},
		    }); 
			
		})
		$("#modal-form-wd").on('hidden.bs.modal', function (event) {
			$(this).find("input[name='f_sys_name']").val("");
		});
		//加载树
		$(function() {
			var treeList = new Array();
			var treechildList = new Array();
			treechildList.push({nodeTreeId:"0002",pid:"01",text:"Kt1",id:"0002",level:"1",leaf:true,nodePage:"kt1",},
							   {nodeTreeId:"0003",pid:"01",text:"Kt2",id:"0003",level:"1",leaf:true,nodePage:"kt2",},
							   {nodeTreeId:"0004",pid:"01",text:"Kt3",id:"0004",level:"1",leaf:true,nodePage:"kt3",},
			 				   {nodeTreeId:"0005",pid:"01",text:"Kt4",id:"0005",level:"1",leaf:true,nodePage:"kt4",},
	 	 					   {nodeTreeId:"0006",pid:"01",text:"Kt5",id:"0006",level:"1",leaf:true,nodePage:"kt5",},
							   {nodeTreeId:"0007",pid:"01",text:"Kt6",id:"0007",level:"1",leaf:true,nodePage:"kt6",});
			treeList.push({nodeTreeId:"01",pid:"",text:"多联机集中控制",id:"01",rootId:"01",leaf:false,nodes:treechildList});
			
			
						$('#sbdy_ktjk_tree').treeview({
							data : treeList, // 数据源
							highlightSelected : true, //是否高亮选中
							levels : 4,
							enableLinks : true,//必须在节点属性给出href属性
							wrapNodeText : true,
							showImage : false,
							color : "#4a4747",
							onNodeSelected : function(event, nodeData) {//节点选中事件	
								nodePage = nodeData.nodePage;
								//加载节点对应的监控界面
								loadJkPage(nodePage);			
								selected_treeview = $('#sbdy_ktjk_tree').treeview('getSelected');	
							},
						});
						if(selected_treeview == null){	
			            	 var firstNode = $("#sbdy_ktjk_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
					        	$("#sbdy_ktjk_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
				        };


		});
	 	function loadJkPage(nodePage){
			var variableUrl = nodePage;
			$.ajax({//加载树节点信息
				type : "post",
				url : _ctx + "/view/realtimemonitoring/BESKtjz/" + variableUrl,
				beforeSend : function() {
					showLoad();
				},
				success : function(returnObject) {
					$("#ktjk_page").html(returnObject);
					dataAnalysis_ktjk.refreshIcon();
				},
				complete : function() {
					hiddenLoad();
				},
				error : function(returnObject) {
					swal(returnObject.msg, "", "error");
				}
			});
		}
		return {
			//逻辑点调试
			setCrossPoint : function(type,obj) {
				var f_sys_name = "";
				var init_val_text = "";
				var f_init_val = "";
				if(obj != "" && obj.type == "button"){
					f_sys_name = obj.id;
					var f_work_mode =  "0";
					if(obj.value == "0"){
						f_init_val = "255";
					}else if(obj.value == "255"){
						f_init_val = "0";
					};
				}else{
					f_sys_name = $("#"+type+"_f_sys_name").val();
					var f_work_mode =  $("input[name='"+type+"_f_work_mode']:checked").val();
					f_init_val = $("#"+type+"_f_init_val").val();
					init_val_text = $("#"+type+"_f_init_val").find("option:selected").text();
				};
								
				$.issp_ajax({
					url : _ctx + "/api/debugPointInfo",
					type : "post",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					data : JSON.stringify({
						f_sys_name : f_sys_name,
						f_work_mode : f_work_mode,
						f_init_val : f_init_val,
						nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
						f_node_attribution : "1",
					}),
					success : function(result) {
						if (result.status == '0') {											
							$("#modal-form-"+type).modal('hide');
						}else{
							swal({
								title : "系统提示",// 展示的标题
								text : result.msg,// 内容
								type : "success",
								showCloseButton : false, // 展示关闭按钮
								allowOutsideClick : false,
								showConfirmButton : false,
								timer : 1000,
							});	
							if(type == "kg"){
								if(obj.value == "0"){
									$("#" + f_sys_name +".circle-button").addClass("circleonline");
									$("#" + f_sys_name +".circle-button").val("255");
								}else if(obj.value == "255"){
									$("#" + f_sys_name +".circle-button").removeClass("circleonline");
									$("#" + f_sys_name +".circle-button").val("0");
								};
							}else if(type == "ms"){
								$("#" + f_sys_name +".model-button").html(init_val_text);
							}else if(type == "fs"){
								$("#" + f_sys_name +".fs-button").html(init_val_text);
							}else if(type == "wd"){
								$("#sz" + f_sys_name ).html(f_init_val);
							}
							$("#modal-form-"+type).modal('hide');
						};						
					},
					error : function(result) {
						swal(result.msg, "", "error");
					},
				});
			},
			//刷新逻辑点信息
			refresh : function() {
				if(judgeActive("ktjk_div")){//页面活动时
					$.ajax({//加载树节点信息
						type : "post",
						url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
						success : function(returnObject) {
							if(returnObject.status == '0'){
								swal({
									title : returnObject.msg,// 展示的标题
									text : "",// 内容
									type : "error",
									showCloseButton : false, // 展示关闭按钮
									allowOutsideClick : false,
									showConfirmButton : false,
									timer : 1000,
								});																
							} else {
							if(returnObject.hasOwnProperty('list')){
								var updateInfo = returnObject.list;
								if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){					
									for (var i = 0; i < updateInfo.length; i++) {
										if (!updateInfo[i] == '' || !updateInfo[i] == null) {
											if(updateInfo[i].f_type == "10"){//AI点
												$("#" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
											}else if(updateInfo[i].f_type == "11"){//AO点
												$("#sz" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
											}else if(updateInfo[i].f_type == "12"){//DI点
												
											}else if(updateInfo[i].f_type == "13"){//DO点										
												if(updateInfo[i].channelIndex == "0"){//空调开关
													if(updateInfo[i].f_init_val == "0"){//关闭
														$("#" + updateInfo[i].f_sys_name +".circle-button").removeClass("circleonline");
														$("#" + updateInfo[i].f_sys_name +".circle-button").val("0");
													}else{//打开
														$("#" + updateInfo[i].f_sys_name +".circle-button").addClass("circleonline");
														$("#" + updateInfo[i].f_sys_name +".circle-button").val("255");
													};
												}else if(updateInfo[i].channelIndex == "1"){//空调模式
													if(updateInfo[i].f_init_val == "0"){
														$("#" + updateInfo[i].f_sys_name +".model-button").html("除湿");
													}else if(updateInfo[i].f_init_val == "50"){
														$("#" + updateInfo[i].f_sys_name +".model-button").html("制热");
													}else if(updateInfo[i].f_init_val == "100"){
														$("#" + updateInfo[i].f_sys_name +".model-button").html("自动");
													}else if(updateInfo[i].f_init_val == "150"){
														$("#" + updateInfo[i].f_sys_name +".model-button").html("制冷");
													}else if(updateInfo[i].f_init_val == "200"){
														$("#" + updateInfo[i].f_sys_name +".model-button").html("送风");
													};											
												}else if(updateInfo[i].channelIndex == "2"){//空调风速
													if(updateInfo[i].f_init_val == "0"){
														$("#" + updateInfo[i].f_sys_name +".fs-button").html("低速");
													}else if(updateInfo[i].f_init_val == "100"){
														$("#" + updateInfo[i].f_sys_name +".fs-button").html("中速");
													}else if(updateInfo[i].f_init_val == "200"){
														$("#" + updateInfo[i].f_sys_name +".fs-button").html("高速");
													};
												};
											};
										};
									};
								};
							}
							};
						},
						error : function(returnObject) {
							swal(returnObject.msg, "", "error");
						},
					});
				};
				
			},
			//初始加载状态信息
			refreshIcon : function(){
				$.issp_ajax({//加载树节点信息
					type : "post",
					url : _ctx + "/view/basedatamanage/eqmanage/getAllPointInfo",
					success : function(returnObject) {
						if(returnObject.status == '1'){
						var updateInfo = returnObject.data;
						if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){					
							for (var i = 0; i < updateInfo.length; i++) {
								if (!updateInfo[i] == '' || !updateInfo[i] == null) {
									if(updateInfo[i].F_NODE_TYPE == "10"){//AI点
										$("#" + updateInfo[i].F_SYS_NAME).html(updateInfo[i].F_INIT_VAL);
									}else if(updateInfo[i].F_NODE_TYPE == "11"){//AO点
										$("#sz" + updateInfo[i].F_SYS_NAME).html(updateInfo[i].F_INIT_VAL);
									}else if(updateInfo[i].F_NODE_TYPE == "12"){//DI点
										
									}else if(updateInfo[i].F_NODE_TYPE == "13"){//DO点										
										if(updateInfo[i].F_CHANNEL_INDEX == "0"){//空调开关
											if(updateInfo[i].F_INIT_VAL == "0"){//关闭
												$("#" + updateInfo[i].F_SYS_NAME +".circle-button").removeClass("circleonline");
											}else{//打开
												$("#" + updateInfo[i].F_SYS_NAME +".circle-button").addClass("circleonline");
											};
										}else if(updateInfo[i].F_CHANNEL_INDEX == "1"){//空调模式
											if(updateInfo[i].F_INIT_VAL == "0"){
												$("#" + updateInfo[i].F_SYS_NAME +".model-button").html("除湿");
											}else if(updateInfo[i].F_INIT_VAL == "50"){
												$("#" + updateInfo[i].F_SYS_NAME +".model-button").html("制热");
											}else if(updateInfo[i].F_INIT_VAL == "100"){
												$("#" + updateInfo[i].F_SYS_NAME +".model-button").html("自动");
											}else if(updateInfo[i].F_INIT_VAL == "150"){
												$("#" + updateInfo[i].F_SYS_NAME +".model-button").html("制冷");
											}else if(updateInfo[i].F_INIT_VAL == "200"){
												$("#" + updateInfo[i].F_SYS_NAME +".model-button").html("送风");
											};											
										}else if(updateInfo[i].F_CHANNEL_INDEX == "2"){//空调风速
											if(updateInfo[i].F_INIT_VAL == "0"){
												$("#" + updateInfo[i].F_SYS_NAME +".fs-button").html("低速");
											}else if(updateInfo[i].F_INIT_VAL == "100"){
												$("#" + updateInfo[i].F_SYS_NAME +".fs-button").html("中速");
											}else if(updateInfo[i].F_INIT_VAL == "200"){
												$("#" + updateInfo[i].F_SYS_NAME +".fs-button").html("高速");
											};
										};
									};
								};
							};
						};
						};
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					},
				});
			},
			pageInit : function(){
				dataAnalysis_ktjk.refreshIcon();
				
			}
		}

	})(jQuery, window, document);
dataAnalysis_ktjk.pageInit();
</script>