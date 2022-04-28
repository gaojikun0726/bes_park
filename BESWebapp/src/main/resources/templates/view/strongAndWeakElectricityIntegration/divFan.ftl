<style>
.divFanCanvasFather canvas{
	width:100%;
	height:100%;
}
</style>

<script src="${ctx}/static/js/integration.js"></script>
<!-- fan父类弹出框配置 -->
	<div class="layui-container">
	<form class="layui-form " style="display: none;" id="integrationFanDBClickDivLeaderConfigForm">
		<input type="hidden" id="" name="">
			<div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2"> 
			         	<label class="layui-form-label">数量配置:</label>
		         	</div>
					<div class="layui-col-xs4"> 
			            <input type="number" name="commonConfigDivMaxNum" value="" placeholder="div显示最大数量"  lay-verify="" class="layui-input">
			        </div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
				    <div class="layui-input-block" style="float:right;">
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="savePageDivNumConfig">提交</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="integration_fan.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
	
	<!-- fan弹出配置框 -->
	<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationFanDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">      
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3"> 
			        	<select id="configFanDdcFSysName" name="configFanDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2"> 
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3"> 
			        	<select id="configFanPointLocationFid" name="configFanPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>      
			    </div>
		    </div>
			<div class="layui-form-item">
				<div class="layui-row">
					<div class="layui-col-md2"> 
				        <label class="layui-form-label">描&emsp;&emsp;述：</label>
			        </div>
			        <div class="layui-col-md3"> 
		            	<input type="text"  name="commonConfigDesc" value="" placeholder="描述"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
				    <div class="layui-input-block" style="float:right;">
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfig">提交</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="integration_fan.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
<div class="strongAndWeakElectricityIntegrationCommon" id="divFanDiv">
	<div>
		<button class="orangebtn btn btn-default" id="returnFanButton" style="width:60px;  height: 25px; margin-left:40px">返回</button>
	</div>
	<div class="divFanTopConfig">
		<!-- Start by 数据div -->
		<div style="width: 15%; float: left; height: 100%; margin-left:20px; ">
			<div style="height:30%;margin-top:20px;">
				<div class="commonChangeColorLighter" style="width:100%;height:30%;text-align: center;line-height:35px;"><b>能耗</b></div>
				<div class="commonChangeColorDarker" style="width:50%;height:70%;float: left;text-align: center;">
					<div style="height:25%;line-height:40px;">瞬时</div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;">0</div>
					<div style="height:25%">kW</div>
				</div>
				<div class="commonChangeColorDarker" style="width:50%;height:70%;float: left;text-align: center;">
					<div style="height:25%;line-height:40px;">累计值</div>
					<div style="height:50%;font-size:23px;line-height:50px;color:#459ef6;">0</div>
					<div style="height:25%">kW·h</div>
				</div>
			</div>
		</div>
		<!-- End by 数据div -->
		<!-- 图片div -->
		<div class="testphoto" style="width: 45%; float: left; height: 100%;background:url(${ctx}/static/images/strongAndWeakElectricityIntegration/fanAndCoolingTower.png);background-repeat:no-repeat;background-size:100%; ">
			<div class="divFanCanvasFather" style="height:85%;text-align: center;">
				<div style="text-align:left;">
					<span style="color:#f70707;"><span>名称：</span><span>一号风机</span></span>
					<span style="color:#4dd807;">&nbsp;&nbsp;&nbsp;&nbsp;运行</span>
				</div>
				<!-- Start by 画布画斜线 -->
				<canvas id="fanmcanvas" style="height:80%;"></canvas>
				<!-- End by 画布画斜线 -->
			</div>
			<div style="height:15%;text-align: center;margin-top:10px;">
				<button id="showMoreFanData" class="orangebtn btn btn-default" >更多属性</button>
			</div>
		</div>
		<!-- End by图片div -->
		<!-- Start by 圆圈div -->
		<div class="integrationDBClickDivLeaderConfig" style="width: 20%; float: left; height: 100%;">
			<div>
				<div class="greendiv circle integrationDBClickCommon fanPageDivShow1" sequence="1">
					<div style="line-height:38px;" class="fanPageDivShow11"></div>
					<div style="font-size:23px;" class="fanPageDivShow12"></div>
					<div class="fanPageDivShow13"></div>
				</div>
				<div class="greendiv circle integrationDBClickCommon fanPageDivShow2" style="display:none;" sequence="2">
					<div style="line-height:38px;" class="fanPageDivShow21"></div>
					<div style="font-size:23px;" class="fanPageDivShow22"></div>
					<div class="fanPageDivShow23"></div>
				</div>
			</div>
			<div>
				<div class="bluediv circle integrationDBClickCommon fanPageDivShow3" style="margin-top:25px; display:none;"sequence="3">
					<div style="line-height:38px;"class="fanPageDivShow31"></div>
					<div style="font-size:23px;"class="fanPageDivShow32"></div>
					<div class="fanPageDivShow33"></div>
				</div>
				<div class="bluediv circle integrationDBClickCommon fanPageDivShow4" style="margin-top:25px; display:none;"sequence="4">
					<div style="line-height:38px;" class="fanPageDivShow41"></div>
					<div style="font-size:23px;"class="fanPageDivShow42"></div>
					<div class="fanPageDivShow43"></div>
				</div>
			</div>
			<div>
				<div class="orangediv circle integrationDBClickCommon fanPageDivShow5" style="margin-top:25px; display:none;"sequence="5">
					<div style="line-height:38px;"class="fanPageDivShow51"></div>
					<div style="font-size:23px;"class="fanPageDivShow52"></div>
					<div class="fanPageDivShow53"></div>
				</div>
				<div class="orangediv circle integrationDBClickCommon fanPageDivShow6" style="margin-top:25px; display:none;"sequence="6">
					<div style="line-height:38px;"class="fanPageDivShow61"></div>
					<div style="font-size:23px;"class="fanPageDivShow62"></div>
					<div class="fanPageDivShow63"></div>
				</div>
			</div>
		</div>
		<!-- End by 圆圈div -->
		
		<!-- Start by 基本参数 -->
		<div class="commonChangeColorLighter" style="width: 15%; float: left; height: 100%;margin-left:20px;">
			<table style="height:320px ;width:100%;margin-left:10px;">
				<caption style="text-align: center;color:red;"><b>基本参数</b></caption>
				<tr>
					<td style="width:45%">型号</td><td style="width:35%">数量</td><td style="width:20%">单位</td>
				</tr>
				<tr>
					<td>额定制冷量</td><td>0</td><td>0</td>
				</tr>
				<tr>
					<td>额定功率</td><td>7.5</td><td>kW</td>
				</tr>
				<tr>
					<td>A相电压</td><td>0</td><td>V</td>
				</tr>
				<tr>
					<td>B相电压</td><td>0</td><td>V</td>
				</tr>
				<tr>
					<td>C相电压</td><td>0</td><td>V</td>
				</tr>
				<tr>
					<td>A相电流</td><td>0</td><td>A</td>
				</tr>
				<tr>
					<td>B相电流</td><td>0</td><td>A</td>
				</tr>
				<tr>
					<td>C相电流</td><td>0</td><td>A</td>
				</tr>
			</table>
		</div>
		<!-- End by 基本参数 -->
	</div>
	<!-- 下方折线图 (当前运行频率、当前功率、能效比)-->
	<div class="divFanBottomConfig">
    	<div class="commonChangeColorDarker" style="width: 32%; float: left; height: 95%;margin-left:20px;">
        	<div id="fanCurrentOperatingFrequencyLine" style="width: 90%; height: 100%;"></div>
    	</div>
    	<div class="commonChangeColorDarker" style="width: 32%; float: left; height: 95%;margin-left:10px;">
        	<div id="fanCurrentPowerLine" style="width: 90%; height: 100%;"></div>
    	</div>
    	<div class="commonChangeColorDarker" style="width: 32%; float: left; height: 95%;margin-left:10px;">
        	<div id="fanEnergyConsumptionRatioLine" style="width: 90%; height: 100%;"></div>
    	</div>
	</div>
	<!-- 隐藏框--更多属性 -->
	<div  class="commonChangeColorLighter" id="showMoreFanDataHiddenDiv" style="display:none;width:500px;height:100%;text-align:center;">
			<table class="hidentable"style="width:100%; height:100%;">
				<tr>
					<td style="width:33%;"><b>属性名</b></td><td style="width:33%"><b>属性值</b></td><td style="width:33%"><b>单位</b></td>
				</tr>
				<tr class="hidentabletr1">
					<td style="text-align:left;color:#3d42f3">当前状态</td><td>3</td><td>NULL</td>
				</tr>
				<tr>
					<td style="text-align:left;color:#3d42f3">当前功率</td><td>0</td><td>kW</td>
				</tr>
				<tr class="hidentabletr1">
					<td style="text-align:left;color:#3d42f3">累计能耗</td><td>0</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;color:#3d42f3">当前累计能耗</td><td>0</td><td>kW·h</td>
				</tr>
				<tr class="hidentabletr1">
					<td style="text-align:left;color:#3d42f3">累计运行时间</td><td>5266.8</td><td>NULL</td>
				</tr>
			</table>
	</div> 
</div>
<script type="text/javascript">
    ;
    var integration_fan = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var server_Interval=5*1000;//定时器 5秒钟
        
        var form; //只有执行了这一步，部分表单元素才会自动修饰成功
    	var layer;
    	//var index = 0;//默认弹框index为0
    	var ddcSysName;
    	var pointLocations=document.getElementById('configFanPointLocationFid');
    	var f_equipment_id;
    	var pageConfigShowNum;//配置的页面显示div数目
    	var pageDivSequence;//所配置展示div的序号
    	
    	var fanShowCurrentOperatingFrequencyInterval;
    	var fanShowCurrentPowerInterval;
    	var fanShowEnergyConsumptionRatioInterval;
    	var fanTimer;
        
        var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		var divFanTopHeight= divHeightSum*0.55;//上半部高度
		var divWidthSum = $(window).width();//网页窗口可见区域总宽度
        $(".divFanTopConfig").css("height",divFanTopHeight);
        $(".divFanBottomConfig").css({"height":divHeightSum*0.3,"margin-top":divHeightSum*0.015});
        $("#divFanParamTable").css({"height":divFanTopHeight*0.8});
		
        /* 点击左上方返回按钮，返回主页面 */
        $("#returnFanButton").click(function() {
        	//返回主页面时，将此副页面开启的定时器关闭
        	clearInterval(fanTimer);
        	clearInterval(fanShowCurrentOperatingFrequencyInterval);
        	clearInterval(fanShowCurrentPowerInterval);
        	clearInterval(fanShowEnergyConsumptionRatioInterval);
        	//location.reload();
        	$("#fanDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/fan/showInitPage');
        });
        
        /* Start for 点击更多属性按钮展示弹出框 */
        $("#showMoreFanData").click(function(){
        	layer.open({
    			tytle:'测试',
    			type:1,
    			area:['500px','600px'],
    			maxmin:true,//最大最小化
    			content:$('#showMoreFanDataHiddenDiv'),
    		});
        });
        /* End 点击更多属性按钮展示弹出框 */
        
        /* Start by 画图片上斜线指示 */
        var fanmcanvas  = document.getElementById("fanmcanvas");    //获得画布

        var mcontext = fanmcanvas.getContext("2d");    //获得上下文

        fanmcanvas.width = 1000;     //重新设置标签的属性宽高
        fanmcanvas.height = 1000;    //千万不要用 canvas.style.height
        //fanmcanvas.style.border = "1px solid #f7f7f7";    //设置canvas的边

        //绘制三角形
        mcontext.strokeStyle = '#D2D2D2';
        mcontext.beginPath();        //开始路径
        mcontext.moveTo(0,100);    //三角形，左顶点
        //mcontext.lineTo(300, 100);   //右顶点
        mcontext.lineTo(300, 400);   //底部的点
        mcontext.closePath();        //结束路径
        mcontext.stroke();           //描边路径
        /* End by 画图片上斜线指示 */
        
        $(function(){
        	f_equipment_id = $("#fanHiddenInput").val();//取得主页面进入查看页面所传的设备id
        	showCurrentOperatingFrequency();
        	showCurrentPower();
        	showEnergyConsumptionRatio();
        	/* Start add by yangjx at 20191227 */
        	loadOptions();//给下拉菜单加载选项
            integrationInitHtml();//初始化页面
            /* End add by yangjx at 20191227 */
             
            // Start add by yangjx at 20200110
            fanTimer = setInterval(function () {
     			// 当关闭此标签页面时，消除此定时器(依据：当未关闭此标签页面时，能根据id获取到信息，关闭后，无法根据id获得信息，则关闭定时器) 
     			if(document.getElementById('returnFanButton')===null){
     				clearInterval(fanTimer);
     				return;
     			}
     			var sysNameList = $(".fanPageDivShow1").attr("fsysnameList");
               	integrationCommonDataTimedRefresh(sysNameList,".fanPageDivShow");
           	  }, 30000);
            // End add by yangjx at 20200110
            
        })
        
        /* Start add by yangjx at 20191227 */
        //给所展示的div的父类div设置双击事件，当点击时，可设置所展示的div的数量，最大展示数量为6；
        $(".integrationDBClickDivLeaderConfig").dblclick(function(e){

        	if (e.target.className == "integrationDBClickDivLeaderConfig") {//点击子div时，不弹出父div配置框
        		index = layer.open({
            		tytle:'配置',
            		type:1,
            		area:['30vw','25vh'],
            		maxmin:true,
            		content:$("#integrationFanDBClickDivLeaderConfigForm"),
            	});
            }
        });

        //给div设置双击弹出配置框设置
        $(".integrationDBClickCommon").dblclick(function(obj){
        	var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
        	var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
        	pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
        	index = layer.open({
        		tytle:'配置',
        		type:1,
        		area:['40vw','60vh'],
        		maxmin:true,
        		content:$("#integrationFanDBClickCommonForm"),
        	});
        	if(configDDCSysName!=null&&configDDCSysName!=""){//有数据，说明已配置，则回显数据，否则，不操作
        		var descId = obj.currentTarget.attributes.fdescid.value;//获取回显展示描述DIV的id
				var commonConfigDesc = $("#"+descId).html();//取得描述信息
        		configDDCSysName=configDDCSysName.value;
        		configFSysName = configFSysName.value;
        		//进行回显:↓
        		formLoadData(configDDCSysName,commonConfigDesc);
        		loadPointLocations(configFSysName);
        	}
        	
        });
        function formLoadData(configDDCSysName,commonConfigDesc){
			form.render();//更新全部
			$("#integrationFanDBClickCommonForm select[name='configFanDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
			$("#integrationFanDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			form.render();
		}
        
        
        
        function integrationInitHtml(){

        	layui.use('form', function(){
          	  form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
          	  layer = layui.layer;
          	  //但是，如果你的HTML是动态生成的，自动渲染就会失效
          	  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
          	  form.on('select(ddcOption)', function (data) {//监听选择ddc后,点位选项根据选中的ddc信息来进行动态生成
          		  //触发内容
          		ddcSysName=$('#configFanDdcFSysName').val();
          	  	console.log("ddcSysName:"+ddcSysName);
          	  	//触发切换相应ddc对应的点位信息时，先将之前存储的信息清空，再去获取所属DDC配置的点位；
              	pointLocations.options.length=0;//将存储的ddc点位信息清空
          		//获取所属DDC配置的点位
          	  	loadPointLocations();
              });
          	  
          	  form.render();
          });
        	searchIntegrationPageDivConfigNum(f_equipment_id,".fanPageDivShow");//查询页面div配置展示数目，进行展示
        	loadPageData(f_equipment_id,".fanPageDivShow");//查询页面配置的展示div所应展示的点位信息
        };  
        
        /*添加和编辑的关闭按钮  */
    	function closeLayerHost(){
    		layer.close(index);
    		index = 0;
    	} 
        
    	 /* form的监听事件,当提交时 */
	    form.on('submit(savePageDivNumConfig)',function(data){//监听配置页面div最大数目
	    	confirmDivNumConfig();
	    })
    	 /* form的监听事件,当提交时 */
	    form.on('submit(saveCommonConfig)',function(data){
	    	confirmCommonConfig(pageDivSequence,index);
	    })
	    
	    /*添加展示div最大数目配置保存,若已有配置，则进行更新操作  */  
 		function confirmDivNumConfig(){
    		 var confirmDivNum = $("#integrationFanDBClickDivLeaderConfigForm input[name='commonConfigDivMaxNum']").val();
    		 if(confirmDivNum>6||confirmDivNum<1){//校验验证配置的div展示数目应在1~6之间
    			 layer.alert("配置的展示数目应在1~6之间");
    			 return;
    		 }
    		//提交之前，先验证相应的信息有没有，若表中无此相关配置，则进行插入操作，若有数据，则进行更新操作
    		//1.根据设备id验证div数目配置表中有无此页面div配置信息20191219
    		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkDivNumDataInfo',
	            dataType : "json",
	            data : {f_equipment_id:f_equipment_id},//TODO   将实际的设备id传入
	            success : function(result) {
	            	if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后，如果div展示数目有变动，应刷新div展示数目
	            		if(pageConfigShowNum==confirmDivNum){//若现在设置的div数目与之前配置的一样，无需执行更新操作，直接退出
	            			layer.close(index);
	            			return;
	            		}
	            		$.ajax({
	      	  		        type: "post",
	      	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationDivNumConfig',
	      	  		        contentType:'application/json;charset=UTF-8',
	      	  		        data:JSON.stringify({
	      	  		        	f_equipment_id: f_equipment_id,//配置的设备id TODO
	      	  		        	f_div_num: confirmDivNum//div序号
	      	  		        }),
	      	  		        success: function (result) {
	      	  		        	if(result.code == '0'){//更新配置div数目成功
		      	  		        	pageConfigShowNum=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
	      	  		        		layer.close(index);
	      	  		        		for(var i=2; i<=confirmDivNum;i++){//将配置展示的div显示
	      								$(".fanPageDivShow"+i).css('display','');
	      							}
	      	  		        		for(var i=parseInt(confirmDivNum)+1;i<=6;i++){//将未配置显示的div隐藏
	      	  		        			$(".fanPageDivShow"+i).css('display','none');
	      	  		        		}
	      	  		        		integrationInitHtml();//刷新初始化页面
	      	  		        	}else{
	      	  		        		layer.msg(result.msg);
	      	  		        	}
	      	  		        },
	      	  		        error: function (result) {
	      	  		        	layer.msg(result.msg);
	      	  		        }
	      	  			});
	      	        }else{//若无数据，执行插入操作
	      	        	//执行插入配置div最大数目方法

	      	  			$.ajax({
	      	  		        type: "post",
	      	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/confirmIntegrationDivNumConfig',
	      	  		        contentType:'application/json;charset=UTF-8',
	      	  		        data:JSON.stringify({
	      	  		        	f_equipment_id: f_equipment_id,//配置的设备id
	      	  		        	f_div_num: confirmDivNum//div序号
	      	  		        }),
	      	  		        success: function (result) {//插入配置div展示数据成功

	      	  		        	if(result.code == '0'){
	      	  		        		pageConfigShowNum=confirmDivNum;//将更新后的div数目重新赋值给pageConfigShowNum
	      	  		        		layer.msg(result.msg);
	      	  		        		if(index != 0){
	      	  		        			layer.close(index);
	      	  		        			index = 0;
	      	  		        		}
		      	  		        	for(var i=2; i<=confirmDivNum;i++){//将配置展示的div显示
	      								$(".fanPageDivShow"+i).css('display','');
	      							} 
		      	  		        	integrationInitHtml();//刷新初始化页面
	      	  		        	}else{
	      	  		        		layer.msg(result.msg);
	      	  		        	}
	      	  		        },
	      	  		        error: function (result) {
	      	  		        	layer.msg(result.msg);
	      	  		        }
	      	  			});
	      	       	}
	               },
	               error : function() {
	               	layer.msg("失败");
	               }
	           });
		} 
    	 
	    /*添加信息的 保存  */
		function confirmCommonConfig(){
	    	//保存之前，先校验表strongandweakelectricityintegration_commonconfig中有无id对应的数据，若无，则更新，若有，则插入
	    	//pageDivSequence//取得所点击div的序号
	    	$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkPageCommonConfig',
	            dataType : "json",
	            data : {
	            		f_equipment_id : f_equipment_id,
	            		f_seq : pageDivSequence//所点击的div序号
	            		},//TODO   将实际的设备id传入
	            success : function(result) {
	            	if(result.code == '0'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
	            		$.ajax({
	      	  		        type: "post",
	      	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationPageCommonConfig',
	      	  		        contentType:'application/json;charset=UTF-8',
	      	  		        data:JSON.stringify({
		      	  		        f_equipment_id: f_equipment_id,//配置的设备id
		      	  		    	f_ddc_sys_name: $("#configFanDdcFSysName").val(),//配置展示DIV的DDC系统名称
	        		        	f_sys_name: $('#configFanPointLocationFid').val(),//配置的DDC所属点位信息
	        		        	f_desc: $("#integrationFanDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
	        		        	f_seq: pageDivSequence//div序号
	      	  		        }),
	      	  		        success: function (result) {
	      	  		        	if(result.code == '0'){//更新配置成功
		      	  		        	layer.close(index);
	    		        			index = 0; 
	    		        			integrationInitHtml();//刷新初始化页面
	    		        			
	      	  		        	}else{//更新配置失败
	      	  		        		layer.msg(result.msg);
	      	  		        	}
	      	  		        },
	      	  		        error: function (result) {
	      	  		        	layer.msg(result.msg);
	      	  		        }
	      	  			});
	            	} else if(result.code="2"){//若无数据，执行插入操作
      	        		//layer.msg(result.msg);
	            		$.ajax({
	        		        type: "post",
	        		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertPageCommonConfig',
	        		        contentType:'application/json;charset=UTF-8',
	        		        data:JSON.stringify({
	        		        	f_equipment_id: f_equipment_id,//配置的设备id
	        		        	f_ddc_sys_name: $("#configFanDdcFSysName").val(),//配置展示DIV的DDC系统名称
	        		        	f_sys_name: $('#configFanPointLocationFid').val(),//配置的DDC所属点位信息
	        		        	f_desc: $("#integrationFanDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
	        		        	//f_seq: $("#integrationFanDBClickCommonForm input[name='commonConfigSeq']").val()//div序号
	        		        	f_seq: pageDivSequence//div序号
	        		        }),
	        		        success: function (result) {
	        		        	if(result.code == '0'){
	        		        		layer.msg(result.msg);
	        		        		if(index != 0){
	        		        			layer.close(index);
	        		        			index = 0;
	        		        		} 
	        		        		//如果配置插入成功，数据展示为所配置的信息:  TODO
	        		        		integrationInitHtml();//刷新初始化页面
	        		        	}else{
	        		        		layer.msg(result.msg);
	        		        	}
	        		        },
	        		        error: function (result) {
	        		        	layer.msg(result.msg);
	        		        }
	        			});
	      	       	}else{
	      	       		layer.msg(result.msg);
	      	       	}
	               },
	               error : function() {
	               	layer.msg("失败");
	               }
	           });
		} 
	    
    	function loadPointLocations(configFSysName){
        	//configFanPointLocationFid  所属ddc下的点位信息
	   		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
	            dataType : "json",
	            data : {f_sys_name:$('#configFanDdcFSysName').val()},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configFanPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
	               		})
	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
  	               		if(configFSysName!=null&&configFSysName!=""){
	  	               		$("#integrationFanDBClickCommonForm select[name='configFanPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
  	               		}
	               		form.render();
	      	        	}else{
	      	        		layer.msg(result.msg);
	      	        	}
	               },
	               error : function() {
	               	layer.msg("失败");
	               }
	           });
        	
        }
		function loadOptions(){
        	//configFanDdcFSysName  所属ddc
	   		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
	            dataType : "json",
	            data : {},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configFanDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
	               		})
	               		form.render();
	      	        	}else{
	      	        		layer.msg(result.msg);
	      	        	}
	               },
	               error : function() {
	               	layer.msg("失败");
	               }
	           });
        	
        }  
        /* End add by yangjx at 20191227 */
        
        
		
        //关闭定时器通用方法，传入定时器名称，监听当页面不活动时，关闭定时器
        function clearAllInterval(intervalNam){
        	//Start replace by yangjx at 20200108
        	/* //判断页面是否活跃，如果不活跃，关闭定时器。judgeActive()此方法为issp.js封装
        	if(judgeActive("divFanDiv")){
        	}else{
	        	clearInterval(intervalNam);
        	} */
        	//remark:↑     replace:↓
        	if(document.getElementById('returnFanButton')===null){
        		clearInterval(intervalNam);
        	}
        	//End replace by yangjx at 20200108 for 优化定时器关闭
			
        }
        
        //显示当前运行频率的信息
        function showCurrentOperatingFrequency(){
            var dom = document.getElementById("fanCurrentOperatingFrequencyLine");
            var myChart1 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '当前运行频率',
                    textStyle : {
                        fontSize: '16'
                    },
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#ffbe08'
                        }
                    }
                },
                legend: {
                    data:['当前运行频率'],
                    x : '120',
                },
                toolbox: {
                    show: true,
                },
                grid: {
                    left: '7%',
                    right: '0%',
                    bottom: '10%',
                    containLabel: true
                },
                dataZoom: {
                    show: false,
                    start: 0,
                    end: 100
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        data: (function (){
                            var now = new Date();
                            var res = [];
                            var len = 10;
                            while (len--) {
                                res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                                now = new Date(now - 2000);
                            }
                            return res;
                        })()
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        scale: true,
                        name: 'Hz',
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        min: 0,
                        boundaryGap: [0.2, 0.2]
                    }
                ],
                series: [
                    {
                        name:'当前运行频率',
                        type:'line',
                        smooth:false,
                        data:(function (){
                            var res = [];
                            var len = 0;
                            while (len < 10) {
                                res.push((Math.random()*10 + 5).toFixed(1) - 0);
                                len++;
                            }
                            return res;
                        })()
                    }
                ]
            }
            //先展示 后填充数据
            myChart1.setOption(option_inter);
            //定时器 定时刷新
            fanShowCurrentOperatingFrequencyInterval = setInterval(function (){
                axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                var out_data = (Math.random() * 10 + 5).toFixed(1) - 0;
                var data_inter_out = option_inter.series[0].data;
                if(data_inter_out.length<=10){
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.push(axisData);
                }else{
                    data_inter_out.shift();
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.shift();
                    option_inter.xAxis[0].data.push(axisData);
                }
                myChart1.setOption(option_inter);
                clearAllInterval(fanShowCurrentOperatingFrequencyInterval);//监听，页面不活动时，关闭定时器
            },server_Interval);
        }

        //显示当前功率
        function showCurrentPower(){
            var dom = document.getElementById("fanCurrentPowerLine");
            var myChart2 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '当前功率',
                    textStyle : {
                        fontSize: '16'
                    },
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#ffbe08'
                        }
                    }
                },
                legend: {
                    data:['当前功率'],
                    x : '70',
                },
                toolbox: {
                    show: true,
                    feature: {//功能
                    }
                },
                grid: {
                    left: '7%',
                    right: '0%',
                    bottom: '10%',
                    containLabel: true
                },
                dataZoom: {
                    show: false,
                    start: 0,
                    end: 100
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        data: (function (){
                            var now = new Date();
                            var res = [];
                            var len = 10;
                            while (len--) {
                                res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                                now = new Date(now - 2000);
                            }
                            return res;
                        })()
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        scale: true,
                        name: 'kW',
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        min: 0,
                        boundaryGap: [0.2, 0.2]
                    }
                ],
                series: [
                    {
                        name:'当前功率',
                        type:'line',
                        smooth:true,
                        data:(function (){
                            var res = [];
                            var len = 0;
                            while (len < 10) {
                                res.push((Math.random()*10 + 5).toFixed(1) - 0);
                                len++;
                            }
                            return res;
                        })()
                    }
                ]
            }
            //先展示 后填充数据
            myChart2.setOption(option_inter);
            //定时器 定时刷新
            fanShowCurrentPowerInterval = setInterval(function (){
                axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                var out_data = (Math.random() * 10 + 5).toFixed(1) - 0;//实时数据
                var data_inter_out = option_inter.series[0].data;
                if(data_inter_out.length<=10){
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.push(axisData);
                }else{
                    data_inter_out.shift();
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.shift();
                    option_inter.xAxis[0].data.push(axisData);
                }
                myChart2.setOption(option_inter);
                clearAllInterval(fanShowCurrentPowerInterval);//监听，页面不活动时，关闭定时器
            },server_Interval);
        }


        //显示能耗比
        function showEnergyConsumptionRatio(){
            var dom = document.getElementById("fanEnergyConsumptionRatioLine");
            var myChart4 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '能耗比',
                    textStyle : {
                        fontSize: '16'
                    },
                },
                tooltip: {
                	trigger: 'axis',//此处能够当鼠标移动到曲线位置时，自动出一个数据框展示数据
                    axisPointer: {	//此处作用为鼠标移动到曲线上，会自动展示x,y轴数据提示
                        type: 'cross',
                        label: {
                            backgroundColor: '#ffbe08'  //此处是设置的鼠标移动至曲线时，曲线x,y两处提示的背景色
                        }
                    }
                },
                legend: {
                    data:['能耗比'],
                    x : '70',
                },
                toolbox: {
                    show: true,
                    feature: {//功能
                    }
                },
                grid: {
                    left: '7%',
                    right: '0%',
                    bottom: '10%',
                    containLabel: true
                },
                dataZoom: {
                    show: false,
                    start: 0,
                    end: 100
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        data: (function (){
                            var now = new Date();
                            var res = [];
                            var len = 10;
                            while (len--) {
                                res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                                now = new Date(now - 2000);
                            }
                            return res;
                        })()
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        scale: true,
                        name: '%',
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#212122'
                            }
                        },
                        min: 0,
                        boundaryGap: [0.2, 0.2,0.2,0.2]
                    }
                ],
                series: [
                    {
                        name:'能耗比',
                        type:'line',
                        smooth:false,
                        data:(function (){
                            var res = [];
                            var len = 0;
                            while (len < 10) {
                                res.push((Math.random()*10 + 5).toFixed(1) - 0);
                                len++;
                            }
                            return res;
                        })()
                    }
                ]
            }
            //先展示 后填充数据
            myChart4.setOption(option_inter);
            //定时器 定时刷新
            fanShowEnergyConsumptionRatioInterval = setInterval(function (){
                axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                var out_data = (Math.random() * 10 + 5).toFixed(1) - 0;
                var data_inter_out = option_inter.series[0].data;
                if(data_inter_out.length<=10){
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.push(axisData);
                }else{
                    data_inter_out.shift();
                    data_inter_out.push(out_data);
                    option_inter.xAxis[0].data.shift();
                    option_inter.xAxis[0].data.push(axisData);
                }
                myChart4.setOption(option_inter);
                clearAllInterval(fanShowEnergyConsumptionRatioInterval);//监听，页面不活动时，关闭定时器
            },server_Interval);
        }

        return {
        	closeCommonConfigInfos :function(){
    			closeLayerHost();
    		} 
        }
    })(jQuery, window, document);
</script>
