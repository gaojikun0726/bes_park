<style>
/* Start 让文本在页面上左右两侧对齐 */
.systemEnergyEfficiencyTopP{
	text-align: justify;
	-moz-text-align-last: justify;
	-webkit-text-align-last: justify; 
}
/* End 让文本在页面左右两侧对齐 */

/*  */
.systemEnergyEfficiencyTable tr{
	border:solid 1px #f7f7f7;
}

/* 长方形，四周边相同弧度15px */
.rectangle15{
	width: 150px; height: 80px;border-radius:20px;
	box-shadow: 6px 6px 0px rgba(0,0,0,.2);/* 增加阴影，向左下放6px，模糊2px 透明度:.2*/
}
.rectangle25{
	width: 160px; height: 70px;border-radius:35px;
	box-shadow: 6px 6px 0px rgba(0,0,0,.2);/* 增加阴影，向左下放6px，模糊2px 透明度:.2*/
	
}
.systemEnergyEfficiencyCircle {
	float:left;
	margin-left:25px;
	text-align:center;
	width: 100px;height: 80px;
	-moz-border-radius: 100px / 50px;
	-webkit-border-radius: 100px / 50px;
	border-radius: 100px / 50px;
	box-shadow: 6px 6px 0px rgba(0,0,0,.2);/* 增加阴影，向左下放6px，模糊2px 透明度:.2*/
	
}
/* 橘色圆圈div渐变设置 */
.systemEnergyEfficiencyOrangeDiv {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#e87903), to(#f9ba44));
	background: -moz-linear-gradient(top,  #e87903,  #f9ba44);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#e87903', endColorstr='#f9ba44');
	border-bottom: 6px solid #EB870F;/* 设置边框下部底色,伪装成背影，增加立体感 */
	border-right: 4px solid #EB870F;
}
/* 绿色圆圈div渐变设置 */
.systemEnergyEfficiencyGreenDiv {
	color:#fef4e9;
	border: solid 1px #45af05;
	background: -webkit-gradient(linear, left top, left bottom, from(#45af05), to(#8ef152));
	background: -moz-linear-gradient(top, #45af05,  #8ef152);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#45af05', endColorstr='#8ef152');
	border-bottom: 6px solid #69CD2D;/* 设置边框下部底色,伪装成背影，增加立体感 */
	border-right:4px solid #69CD2D;
}
/* 蓝色圆圈div渐变设置 */
.systemEnergyEfficiencyBlueDiv {
	color:#fef4e9;
	border: solid 1px #1b49f9;
	background: -webkit-gradient(linear, left top, left bottom, from(#1b49f9), to(#539dde));
	background: -moz-linear-gradient(top, #1b49f9,  #539dde);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#1b49f9', endColorstr='#539dde');
	border-bottom: 6px solid #326EEB;/* 设置边框下部底色,伪装成背影，增加立体感 */
	border-right: 4px solid #326EEB;
}
</style>
<div class="" id="systemEnergyEfficiencyDiv" style="border:solid 1px black;height:100%;">
	<!-- 页面上半部分：空调系统能耗，图-->
	<div class="systemEnergyEfficiencyTopDiv">
		<!-- Start 空调系统能耗 -->
		<div class="kongtiaoyunxingnenghao commonChangeColorLighter" style="float:left;width:20%;hight:100%;margin-left:40px;margin-top:20px;">
			<h1 style="text-align: center;line-height:50px;">空调运行能耗</h1>
			<p class="systemEnergyEfficiencyTopP" style="line-height:20px;margin:10px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了调节室内空气参数和空气品质，空调系统必须向空调房间输送带有
			新风的冷(或热)空气，为此，必须向空调系统中的空气处理设备以及输送空气和水的动力设备如风机、水泵等投入能量。
			在夏季，为了制取低温干燥的冷风，需要借助制冷设备提供冷源(冷冻水或者制冷剂)；
			在冬季，为了制备温风，必须使用空气加热器或者采用热泵方式制暖。如果对室内湿度有要求，还要配备空气加湿系统。
			这样，在空调运行时，这些有关设备、机器也将同时工作，这些投入的能量(如电、蒸汽、热水等)就构成运行能耗。
			</p>
		</div>
		<!-- End 空调系统能耗 -->
		
		<!-- Start 上半部页面图形 -->
		<div class="systemEnergyEfficiencyTopDiv commonChangeColorLighter" style="float:left;width:70%;margin-left:55px;">
			<div class="systemEnergyEfficiencyCanvasFather" style="position:relative; text-align:center;">
				<canvas id="systemEnergyEfficiencyCanvas"></canvas>
				<!-- rectangle:长方形 -->
				<div id="systemEnergyEfficiencyParam1"  sequence="1" class=" systemEnergyEfficiencyDBClick rectangle15 systemEnergyEfficiencyOrangeDiv systemEnergyEfficiencyPageDivShow1" style="position:absolute;text-align: center;">
					<div style="margin-top:10px;" class="systemEnergyEfficiencyPageDivShow11">系统制冷量</div>
					<div>
						<span style="font-size:23px;" class="systemEnergyEfficiencyPageDivShow12">538.2 </span>
						<span class="systemEnergyEfficiencyPageDivShow13">kw</span>
					</div>
				</div>
				<!--  -->
				<div id="systemEnergyEfficiencyParam2" sequence="2" class="systemEnergyEfficiencyDBClick systemEnergyEfficiencyCircle systemEnergyEfficiencyBlueDiv systemEnergyEfficiencyPageDivShow2"style="position:absolute;">
					<div style="margin-top:20px;" class="systemEnergyEfficiencyPageDivShow21">COP</div>
					<div>
						<span id="systemEnergyEfficiencyCopNum" style="font-size:20px;" class="systemEnergyEfficiencyPageDivShow22">1.7 </span>
						<span class="systemEnergyEfficiencyPageDivShow23"></span>
					</div>
				</div>
				<div id="systemEnergyEfficiencyParam3" sequence="3" class="systemEnergyEfficiencyDBClick systemEnergyEfficiencyCircle systemEnergyEfficiencyBlueDiv systemEnergyEfficiencyPageDivShow3" style="position:absolute;">
					<div style="margin-top:20px;" class="systemEnergyEfficiencyPageDivShow31">WTFcw</div>
					<div>
						<span style="font-size:20px;" class="systemEnergyEfficiencyPageDivShow32">13.9</span>
						<span class="systemEnergyEfficiencyPageDivShow33"></span>
					</div>
				</div>
				<div id="systemEnergyEfficiencyParam4" sequence="4" class="systemEnergyEfficiencyDBClick systemEnergyEfficiencyCircle systemEnergyEfficiencyOrangeDiv systemEnergyEfficiencyPageDivShow4" style="position:absolute;">
					<div style="margin-top:20px;"class="systemEnergyEfficiencyPageDivShow41">WTFchw</div>
					<div style="font-size:20px;">
						<span style="font-size:20px;" class="systemEnergyEfficiencyPageDivShow42">10.3</span>
						<span class="systemEnergyEfficiencyPageDivShow43"></span>
					</div>
				</div>
				<div id="systemEnergyEfficiencyParam5" sequence="5" class="systemEnergyEfficiencyDBClick rectangle25 systemEnergyEfficiencyGreenDiv systemEnergyEfficiencyPageDivShow5" style="position:absolute;">
					<div style="margin-top:10px;" class="systemEnergyEfficiencyPageDivShow51">系统耗电量</div>
					<div>
						<span style="font-size:23px;" class="systemEnergyEfficiencyPageDivShow52">321.4</span>
						<span class="systemEnergyEfficiencyPageDivShow53">kw</span>
					</div>
				</div>
				<div id="systemEnergyEfficiencyParam6" sequence="6" class="systemEnergyEfficiencyDBClick rectangle25 systemEnergyEfficiencyGreenDiv systemEnergyEfficiencyPageDivShow6" style="position:absolute;">
					<div style="margin-top:10px;"class="systemEnergyEfficiencyPageDivShow61">冷温泵总耗电量</div>
					<div>
						<span style="font-size:23px;" class="systemEnergyEfficiencyPageDivShow62">52.4</span>
						<span class="systemEnergyEfficiencyPageDivShow63">kw</span>
					</div>
				</div>
				<div id="systemEnergyEfficiencyParam7" sequence="7" class="systemEnergyEfficiencyDBClick rectangle25 systemEnergyEfficiencyGreenDiv systemEnergyEfficiencyPageDivShow7" style="position:absolute;">
					<div style="margin-top:10px;" class="systemEnergyEfficiencyPageDivShow71">冷却泵总耗电量</div>
					<div>
						<span style="font-size:23px;" class="systemEnergyEfficiencyPageDivShow72">38.7 </span>
						<span class="systemEnergyEfficiencyPageDivShow73">kw</span>
					</div>
				</div>
			</div>
		
		</div>
		<!-- End 上半部页面图形 -->
	</div>
	
	<!-- 页面下半部分：设备能耗占比，系统COP，其他属性 -->
	<div style="height:35%;margin-top:20px;">
		<!-- 系统COP -->
		<div class="systemEnergyEfficiencyBottomDiv commonChangeColorDarker" style="float:left;margin-left:40px;">
			<div id="systemEnergyEfficiencyCopLine" style="width: 90%; height: 100%;">12</div>
		</div>
		<div class="systemEnergyEfficiencyBottomDiv commonChangeColorDarker" style="float:left;margin-left:40px;">
			<!--设备能耗占比-->
			<div id="equipmentEnergyConsumptionRatio" style="width: 100%; height: 100%;">23</div>
		</div>
		<div class="systemEnergyEfficiencyBottomDiv commonChangeColorDarker" style="float:left;margin-left:40px;">
			<table class="systemEnergyEfficiencyTable" style="height:80%;width:90%;margin-left:20px;">
				<caption style="text-align: left;color:black;font-size:16px"><b>其他属性</b></caption>
				<tr>
					<td style="width:45%">型号</td><td style="width:35%">数量</td><td style="width:20%">单位</td>
				</tr>
				<tr>
					<td>总负荷率</td><td>23.5</td><td>%</td>
				</tr>
				<tr>
					<td>总负载率</td><td>42.5</td><td>%</td>
				</tr>
				<tr>
					<td>室外温度</td><td>30</td><td>℃</td>
				</tr>
				<tr>
					<td>室外湿度</td><td>66</td><td>%</td>
				</tr>
				<tr>
					<td>制冷状态</td><td>1</td><td></td>
				</tr>
				<tr>
					<td>机房运行状态</td><td>0</td><td></td>
				</tr>
			</table>
		</div>
	
	</div>

	<!-- systemEnergyEfficiency弹出配置框 Start-->
	<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationSystemEnergyEfficiencyDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">      
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3"> 
			        	<select id="configSystemEnergyEfficiencyDdcFSysName" name="configSystemEnergyEfficiencyDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2"> 
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3"> 
			        	<select id="configSystemEnergyEfficiencyPointLocationFid" name="configSystemEnergyEfficiencyPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
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
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveEnergyEfficiencyConfig">提交</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="systemEnergyEfficiency.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
	<!-- systemEnergyEfficiency弹出配置框 End-->
</div>


<script type="text/javascript">
    ;
    var systemEnergyEfficiency = (function($, window, document, undefined) {
    	var server_Interval=5*1000;//定时器 5秒钟
    	
    	var pageDivSequence;//所配置展示div的序号
    	var ddcSysName;
    	var pointLocations=document.getElementById('configSystemEnergyEfficiencyPointLocationFid');
    	
		var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		var systemEnergyEfficiencyTopDivHeight= divHeightSum*0.5;//上半部高度
		var divWidthSum = $(window).width();//网页窗口可见区域总宽度
		var systemEnergyEfficiencyBottomDivWidth = divWidthSum*0.26//下半部各区域宽度
		var systemEnergyEfficiencyBottomDivHeight= divHeightSum*0.35;//下半部div高度
		$(".systemEnergyEfficiencyTopDiv").css("height",systemEnergyEfficiencyTopDivHeight);//设置上半部div高度
		$(".systemEnergyEfficiencyTopDiv").css("height",systemEnergyEfficiencyTopDivHeight);//设置上半部div高度
		$(".systemEnergyEfficiencyBottomDiv").css("width",systemEnergyEfficiencyBottomDivWidth)//设置下半部分各区域div宽度
		$(".systemEnergyEfficiencyBottomDiv").css("height",systemEnergyEfficiencyBottomDivHeight)//设置下半部分各区域div宽度
		
		$(".kongtiaoyunxingnenghao").css("height",systemEnergyEfficiencyTopDivHeight*0.9)//设置左上部分空调运行能耗高度,避免突兀
		
		/* Start by 画图片上斜线指示 */
        var mcanvas  = document.getElementById("systemEnergyEfficiencyCanvas");    //获得画布
        var mcontext = mcanvas.getContext("2d");    //获得上下文
        mcanvas.width = 1000;     //重新设置标签的属性宽高
        mcanvas.height = systemEnergyEfficiencyTopDivHeight;    //千万不要用 canvas.style.height
        //mcontext.canvas.height=systemEnergyEfficiencyTopDivHeight;
        //mcanvas.style.border = "1px solid #f7f7f7";    //设置canvas的边
        //绘制三角形
        mcontext.strokeStyle = '#ff0000';
        mcontext.beginPath();        //开始路径
        //Start 上部左侧斜线
        //mcontext.moveTo(200,systemEnergyEfficiencyTopDivHeight*0.5);    
        mcontext.moveTo(divWidthSum*0.05,systemEnergyEfficiencyTopDivHeight*0.5);    
        mcontext.lineTo(divWidthSum*0.24,systemEnergyEfficiencyTopDivHeight*0.25);   
        //End 上部左侧斜线
        //Start 上部右侧横线
        mcontext.moveTo(divWidthSum*0.26,systemEnergyEfficiencyTopDivHeight*0.25); 
        mcontext.lineTo(divWidthSum*0.5,systemEnergyEfficiencyTopDivHeight*0.25); 
        //End 上部右侧横线
        //Start 底部左侧斜线
//        mcontext.moveTo(200,systemEnergyEfficiencyTopDivHeight*0.5);
        mcontext.moveTo(divWidthSum*0.05,systemEnergyEfficiencyTopDivHeight*0.5);
        mcontext.lineTo(divWidthSum*0.24,systemEnergyEfficiencyTopDivHeight*0.75);
        //End 底部左侧斜线
        //Start 底部右方横线
        mcontext.moveTo(divWidthSum*0.26,systemEnergyEfficiencyTopDivHeight*0.75);
        mcontext.lineTo(divWidthSum*0.5,systemEnergyEfficiencyTopDivHeight*0.75);
        //End 底部右方横线
        //Start 中部左侧横线
        mcontext.moveTo(divWidthSum*0.1,systemEnergyEfficiencyTopDivHeight*0.5);
        mcontext.lineTo(divWidthSum*0.3,systemEnergyEfficiencyTopDivHeight*0.5);
        //End 中部左侧横线
        //Start 中部右侧横线
        mcontext.moveTo(divWidthSum*0.32,systemEnergyEfficiencyTopDivHeight*0.5);	
        mcontext.lineTo(divWidthSum*0.56,systemEnergyEfficiencyTopDivHeight*0.5);
        //End 中部右侧横线
        mcontext.closePath();        //结束路径
        mcontext.stroke();           //描边路径
        /* End by 画图片上斜线指示 */
        
        /* Start 设置数据div相对移动数值 */
		$("#systemEnergyEfficiencyParam1").css({"left":divWidthSum*0.04,"top":divHeightSum*0.205});
		$("#systemEnergyEfficiencyParam2").css({"left":divWidthSum*0.21,"top":divHeightSum*0.08});
		$("#systemEnergyEfficiencyParam3").css({"left":divWidthSum*0.21,"top":divHeightSum*0.325});
		$("#systemEnergyEfficiencyParam4").css({"left":divWidthSum*0.26,"top":divHeightSum*0.2});
		$("#systemEnergyEfficiencyParam5").css({"left":divWidthSum*0.44,"top":divHeightSum*0.08});
		$("#systemEnergyEfficiencyParam6").css({"left":divWidthSum*0.48,"top":divHeightSum*0.205});
		$("#systemEnergyEfficiencyParam7").css({"left":divWidthSum*0.44,"top":divHeightSum*0.33});
		/* End 设置数据div相对移动数值 */
        
        
		
        $(function(){
        	loadOptions();//给下拉菜单加载选项
        	integrationInitHtml();//初始化页面
        	showSystemEnergyEfficiencyCop();
        	systemEnergyEfficiencyRenderChartBar();
        });
        
        //关闭定时器通用方法，传入定时器名称，监听当页面不活动时，关闭定时器
        function clearAllInterval(intervalNam){
			//判断页面是否活跃，如果不活跃，关闭定时器。judgeActive()此方法为issp.js封装
        	if(judgeActive("systemEnergyEfficiencyDiv")){
        	}else{
	        	clearInterval(intervalNam);
        	}
        }
        
        function integrationInitHtml(){
	      	layui.use('form', function(){
	        	  form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
	        	  layer = layui.layer;
	        	  //但是，如果你的HTML是动态生成的，自动渲染就会失效
	        	  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
	        	  form.on('select(ddcOption)', function (data) {//监听选择ddc后,点位选项根据选中的ddc信息来进行动态生成
	        		//触发内容
	        		ddcSysName=$('#configSystemEnergyEfficiencyDdcFSysName').val();
	        	  	console.log("ddcSysName:"+ddcSysName);
	        	  	//触发切换相应ddc对应的点位信息时，先将之前存储的信息清空，再去获取所属DDC配置的点位；
	            	pointLocations.options.length=0;//将存储的ddc点位信息清空
	        		//获取所属DDC配置的点位
	        	  	loadPointLocations(ddcSysName,"");
	            });
        	  form.render();
          });
      	//查询页面所配置的div展示信息进行展示
      	loadEnergyEfficiencyPageConfigData();
        };  
        
        /* 查询页面配置信息并将信息展示 */
    	function loadEnergyEfficiencyPageConfigData(){
    		$.ajax({//查询页面配置的展示div所应展示的点位信息
    				type : "get",			
    	        url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchEnergyEfficiencyPageConfigData',
    	        dataType : "json",
    	        data : {},//TODO   此处需要得到进入页面后的设备id
    	        success : function(result) {
    	        	if(result.code == '1'){
    	        		//数据查询成功，根据页面配置信息在页面展示相应的数据
    	        		var sysNameList = new Array();
    	        		for(var i=1;i<=result.count;i++){//将所配置的div相应的描述展示
    	        			var seqConfigNum = result.data[i-1].f_seq;
    	        			$('.systemEnergyEfficiencyPageDivShow'+seqConfigNum+"1").text(result.data[i-1].f_desc);
    	        			$('.systemEnergyEfficiencyPageDivShow'+seqConfigNum).attr("fddcsysname",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
    						$('.systemEnergyEfficiencyPageDivShow'+seqConfigNum).attr("fSysName",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
    	        			sysNameList.push(result.data[i-1].f_sys_name);
    	        		}
    	        		$(".systemEnergyEfficiencyPageDivShow1").attr("fsysnamelist",sysNameList);//给第一个div上设置记录全部系统名称，以备回显用
    	        		if(sysNameList.length!=0){
    	        			showEnergyEfficiencyPageConfigData(sysNameList);//加载所配置的数据
    	        		}
    	        	}else{
    	        		layer.msg(result.msg);
    	        	}
    	       	},
    	        error : function() {
    	           	layer.msg("失败");
    	        }
    	       });
    	}
        
    	/* 根据传入的系统名称，获取对应的数据加载至页面展示 */
    	function showEnergyEfficiencyPageConfigData(sysNameList){
    		$.ajax({
    			        type: "post",
    			        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
    			        contentType:'application/json;charset=UTF-8',
    			        traditional:true,
    			        data:JSON.stringify({
    			        	f_sysName_list : sysNameList
    			        }),
    			        success: function (result) {
    			        	console.log("vcadsffda");
    			        	if(result.code=='0'){
    			        		for(var i=1;i<=result.count;i++){
    			        			if($('.systemEnergyEfficiencyPageDivShow1').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow12').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow13').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow2').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow22').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow23').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow3').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow32').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow33').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow4').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow42').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow43').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow5').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow52').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow53').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow6').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow62').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow63').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
    			        			if($('.systemEnergyEfficiencyPageDivShow7').attr("fSysName")==result.data[i-1].f_sys_name){
    			        				$('.systemEnergyEfficiencyPageDivShow72').text(result.data[i-1].f_init_val);
    			        				$('.systemEnergyEfficiencyPageDivShow73').text(result.data[i-1].f_engineer_unit);
    			        				continue;
    			        			}
        			        	}
    			        		
    			        	}else{
    			        		layer.msg(result.msg);
    			        	}
    			        },
    			        error: function (result) {
    			        	layer.msg(result.msg);
    			        }
    				});
    	}
        
        
      //给div设置双击弹出配置框设置
        $(".systemEnergyEfficiencyDBClick").dblclick(function(obj){
        	var configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
        	var configFSysName = obj.currentTarget.attributes.fsysname;//获得所点击div的配置的系统名称数据
        	pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
        	index = layer.open({
        		tytle:'配置',
        		type:1,
        		area:['40vw','60vh'],
        		maxmin:true,
        		content:$("#integrationSystemEnergyEfficiencyDBClickCommonForm"),
        	});
        	if(configDDCSysName!=null&&configDDCSysName!=""){//有数据，说明已配置，则回显数据，否则，不操作
        		var commonConfigDesc = $(".systemEnergyEfficiencyPageDivShow"+pageDivSequence+"1").html();
        		configDDCSysName = configDDCSysName.value;
        		configFSysName = configFSysName.value;
        		//进行回显:↓
        		formLoadData(configDDCSysName,commonConfigDesc);
        		loadPointLocations(configFSysName);
        		/* var descId = obj.currentTarget.attributes.fdescid.value;//获取回显展示描述DIV的id
				var commonConfigDesc = $("#"+descId).html();//取得描述信息
        		configDDCSysName=configDDCSysName.value;
        		configFSysName = configFSysName.value;
        		//进行回显:↓
        		formLoadData(configDDCSysName,commonConfigDesc);
        		loadPointLocations(configFSysName); */
        	} 
        	
        });
      
        function formLoadData(configDDCSysName,commonConfigDesc){
			form.render();//更新全部
			$("#integrationSystemEnergyEfficiencyDBClickCommonForm select[name='configSystemEnergyEfficiencyDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
			$("#integrationSystemEnergyEfficiencyDBClickCommonForm input[name='commonConfigDesc']").val(commonConfigDesc);
			form.render();
		} 
      
        /* form的监听事件,当提交时 */
  	    form.on('submit(saveEnergyEfficiencyConfig)',function(data){
  	    	//confirmCommonConfig(pageDivSequence,index);
  	    	confirmCommonConfig(pageDivSequence);
  	    })
        
  	  /*添加信息的 保存  */
  		function confirmCommonConfig(pageDivSequence){
  	    	//保存之前，先校验表strongandweakelectricityintegration_energyEfficiency中有无id对应的数据，若无，则插入，若有，则更新
  	    	//pageDivSequence//取得所点击div的序号。。。remark  delete
  	    	$.ajax({
  	   			type : "get",			
  	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkEnergyEfficiencyConfig',
  	            dataType : "json",
  	            data : {
  	            		f_seq : pageDivSequence//所点击的div序号
  	            		},
  	            success : function(result) {
  	            	if(result.code == '1'){//如果有，则进行更新操作,注意：更新完之后,div展示变动，应刷新div展示（TODO）
  	            		$.ajax({
  	      	  		        type: "post",
  	      	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateIntegrationEnergyEfficiencyConfig',
  	      	  		        contentType:'application/json;charset=UTF-8',
  	      	  		        data:JSON.stringify({
  		      	  		        f_ddc_sys_name: $("#configSystemEnergyEfficiencyDdcFSysName").val(),//配置展示DIV的DDC系统名称
  	        		        	f_sys_name: $('#configSystemEnergyEfficiencyPointLocationFid').val(),//配置的DDC所属点位信息
  	        		        	f_desc: $("#integrationSystemEnergyEfficiencyDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
  	        		        	f_seq: pageDivSequence//div序号
  	      	  		        }),
  	      	  		        success: function (result) {
  	      	  		        	if(result.code == '1'){//更新配置成功
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
  	            		$.ajax({
  	        		        type: "post",
  	        		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertEnergyEfficiencyConfig',
  	        		        contentType:'application/json;charset=UTF-8',
  	        		        data:JSON.stringify({
  	        		        	f_ddc_sys_name: $("#configSystemEnergyEfficiencyDdcFSysName").val(),//配置展示DIV的DDC系统名称
  	        		        	f_sys_name: $('#configSystemEnergyEfficiencyPointLocationFid').val(),//配置的DDC所属点位信息
  	        		        	f_desc: $("#integrationSystemEnergyEfficiencyDBClickCommonForm input[name='commonConfigDesc']").val(),//描述
  	        		        	f_seq: pageDivSequence//div序号
  	        		        }),
  	        		        success: function (result) {
  	        		        	if(result.code == '1'){//添加成功
  	        		        		layer.msg(result.msg);
  	        		        		if(index != 0){
  	        		        			layer.close(index);
  	        		        			index = 0;
  	        		        		} 
  	        		        		//如果配置插入成功，数据展示为所配置的信息
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
	   		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
	            dataType : "json",
	            data : {f_sys_name:$('#configSystemEnergyEfficiencyDdcFSysName').val()},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configSystemEnergyEfficiencyPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
	               		});
	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
	               		if(configFSysName!=null&&configFSysName!=""){
	  	               		$("#integrationSystemEnergyEfficiencyDBClickCommonForm select[name='configSystemEnergyEfficiencyPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
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
	   		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
	            dataType : "json",
	            data : {},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configSystemEnergyEfficiencyDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
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
        
        
		
        //显示COP
        function showSystemEnergyEfficiencyCop(){
            var dom = document.getElementById("systemEnergyEfficiencyCopLine");
            var myChart2 = echarts.init(dom, 'light');
            var option_inter = {
                title: {
                    text: '系统COP',
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
                    data:['COP'],
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
                        name: '数值',
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
                        name:'COP',
                        type:'line',
                        smooth:true,
                        data:(function (){
                            var res = [];
                            var len = 0;
                            while (len < 10) {
                                res.push((Math.random()*1.5+0.6).toFixed(1) - 0);
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
            var systemEnergyEfficiencyCopInterval = setInterval(function (){
                axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                var out_data = (Math.random()*1.5+0.6).toFixed(1) - 0;//实时数据
                $("#systemEnergyEfficiencyCopNum").html(out_data);//实时更新上半部COP数值
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
                clearAllInterval(systemEnergyEfficiencyCopInterval);//页面不活动时关闭定时器；
            },server_Interval);
        }
        
        /* 设备能耗占比 */
        function systemEnergyEfficiencyRenderChartBar(){
    	  	var myCharts = echarts.init(document.getElementById("equipmentEnergyConsumptionRatio"));
    	  	var num = Math.floor(Math.random()*10000); 
       		var num1 = Math.floor(Math.random()*10000); 
       		var num2 = Math.floor(Math.random()*10000); 
       		var numAll = (num+num1+num2); 
    	    option = {
    			   title: [
    				   {
	       		            top:0,//此处是指title（设备能耗占比）距离上边距的距离为0px;
	       		            text: '设备能耗占比',
	       		            subtext: '',
	       		            x: 'left',//设备能耗占比在div中的位置
	       		            textStyle: {
	       		                color: 'black',
	       		                 fontSize:17
	       		            	}
       		        	},
       		        	{
	    		            top:110,//此处是多加一条title用间距顶到圆形中央显示
	    	   				left:170,
	    		            text: [
	    	   					'总累计',
	    	   	                numAll ,
	    	   	            ].join('\n'),
	    		            subtext: '',
	    		            x: 'left',//设备能耗占比在div中的位置
	    		            textStyle: {
	    		                color: 'blue',
	    		                 fontSize:15
	    		            	}
    		        	},
    		        
    		        ],    
    			    tooltip: {
    			        trigger: 'item',
    			        formatter: "{a} <br/>{b}: {c} ({d}%)"
    			    },
    				series: [{
    					name: '累计总能耗',
    					type: 'pie',
    					//selectedMode: 'single',
    					radius: ['0%', '25%'],

    					label: {
    						normal: {
    							show:true,
    							position: 'inner',
    							formatter:'{b}\n{c}' 
    						}
    					},
    					data: [
    						{	
    							value: numAll,
    							name: '总累计',
    							itemStyle:{
    								color:'#fff' //00BFFF  中部背景颜色（圆），此处设置为了白色是为了和字体颜色一致。否则不同后会显示两个。
    							}
    							
    						}
    					]
    				},
    				{
    					name: '能耗:',
    					type: 'pie',
    					radius: ['40%', '55%'],
    					label: {
    						normal: {
    							formatter: '{b|{b}}{abg|}\n {hr|}\n {c}  {per|{d}%} ',
    							//backgroundColor: '#9f9f9f',//此处背景色为动态后长方形背景色
    							backgroundColor: '#c8c8c8',
    							borderColor: '#aaa',
    							borderWidth: 1,//边宽
    							borderRadius: 4,//背景方框周边添加弧度为4px;
    							rich: {
    								hr: {
    									 borderColor: '#aaa', //长方形中心线的颜色
    			                         width: '90%',
    			                         borderWidth: 0.5,
    			                         height: 0
    								},
    								b: {
    									 top:10,
    		                             align: 'center',
    		                            lineHeight: 10
    								},
    								per: {
    									color: '#fff',//数据百分比的颜色
    									backgroundColor: '#6b6b6b',//数据百分比的背景色
    									padding: [2, 4],
    									borderRadius: 2
    								}
    							}
    						}
    					},
    					data: [{
    							value: num,
    							name: '冷却泵能耗',
    							itemStyle:{
    								color:'#ea12c9'
    							}
    						},
    						{
    							value: num1,
    							name: '冷温泵能耗',
    							itemStyle:{
    								color:'#4ec710'
    							}
    						},
    						{
    							value: num2,
    							name: '主机能耗',
    							itemStyle:{
    								color:'#e09e0b'
    							}
    						},

    					]
    			        }
    			    ]
    			};
    	   myCharts.setOption(option);
    	   clearAllInterval(clock_Infosystem);//页面不活动时关闭定时器；
    	}
        var clock_Infosystem = window.setInterval(systemEnergyEfficiencyRenderChartBar,5000);
        
        /*添加和编辑的关闭按钮  */
    	function closeLayerHost(){
    		layer.close(index);
    		index = 0;
    	} 
        return {
        	closeCommonConfigInfos :function(){
    			closeLayerHost();
    		} 
        }
    })(jQuery, window, document);
</script>
