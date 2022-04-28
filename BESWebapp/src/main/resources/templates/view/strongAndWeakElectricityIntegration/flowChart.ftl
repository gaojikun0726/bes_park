<style>
	/* 页面颜色渐变 */
	#flowChartDiv{
		background: -webkit-gradient(linear, left top, right bottom, from(#FFFFFF), to(#EBEBEB));/* 从左上到右下颜色渐变 */
		background: -moz-linear-gradient(top,  #FFFFFF,  #EBEBEB);
		filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#FFFFFF', endColorstr='#EBEBEB');
	}
	.draggable{
		font-size:20px;
		color:orange;
		width:60px;
		height:25px;
		border:1px;
	}
	
</style>
<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<div id="flowChartDiv" class="strongAndWeakElectricityIntegrationCommon" style="height:100%;width:100%;" >
	<div id='configLeaderDiv' class="divtest"style="width:100%;height:100%;background:url(${ctx}/static/images/strongAndWeakElectricityIntegration/flowChart6.jpg); background-repeat: no-repeat;background-size: 100% 100%;">
		<div>
			<button class="orangebtn btn btn-default" id="flowChartConfigButton" onclick="flowChartPage.configShowData();" style="width:60px;  height: 20px; margin-left:5px">配置</button>
		</div>
		<h1 style="text-align:center;padding-top:10px;margin:0;color:#000000;font-size:40px"><b>中央空调节能控制系统管理中心</b></h1>
		<!-- <div  id='configLeaderDiv' class='draggable flowChatShowDataCommonConfig'></div> -->
	</div>
</div>
	<!-- flowChart活动DIV配置框 -->
	<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationFlowChartDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">      
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3"> 
			        	<select id="configFlowChartDdcFSysName" name="configFlowChartDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2"> 
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3"> 
			        	<select id="configFlowChartPointLocationFid" name="configFlowChartPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>      
			    </div>
		    </div>
			<div class="layui-form-item">
				<div class="layui-row">
					<div class="layui-col-md2"> 
				        <label class="layui-form-label">序&emsp;&emsp;号：</label>
			        </div>
			        <div class="layui-col-md3"> 
		            	<input type="number"  id="commonConfigSequence" name="commonConfigSequence" value="" placeholder="配置DIV序号"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
				    <div class="layui-input-block" style="float:right;">
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfig" >提交</button>
				        <button type="button" class="layui-btn layui-btn-warm" onclick="flowChartPage.deleteCommonConfigInfos()">删除</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="flowChartPage.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>

<script type="text/javascript">
	;
	var flowChartPage = (function($, window, document, undefined) {

		var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		var divWidthSum = $(window).width();//网页窗口可见区域总宽度
		
    	var index = 0;//默认弹框index为0
    	var sequence;
    	var pointLocations=document.getElementById('configFlowChartPointLocationFid');
    	var f_move_left_data;//div相对向左移动px距离
    	var f_move_top_data;//div相对向右移动px距离
    	var configSequenceValue=""; //配置的div的sequence的值。已配置的有数据，刚生成未配置的无此数据
    	var sysNameList = new Array();
    	var seqList = new Array();
    	var timer;
		$(function(){
			//dbClickShowDataDiv();
			loadOptions();//给下拉菜单加载选项
			integrationInitHtml();//初始化页面
			timer = setInterval(function () {
				/* 当关闭此标签页面时，消除此定时器(依据：当未关闭此标签页面时，能根据id获取到信息，关闭后，无法根据id获得信息，则关闭定时器) */
				if(document.getElementById('flowChartDiv')===null){
					clearInterval(timer);
					return;
				}
	            dataConverter(sysNameList,seqList);
	        }, 5000000);
		}); 
    	
		
		
		function dataConverter(sysNameList,seqList){

			if(sysNameList!=""&&sysNameList!=null){
				$.ajax({
					type    : "POST",
					url     : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
					dataType: "json",
					data:{
						sysName: sysNameList.join(',')
					},
					success: function (result) {

						if(result.list!='undifined'&&result.list!=null){//有传过来的值
							//TODO  将获取的数据添加到相应的DIV进行展示

							for(var m in seqList){
								for(var n=0;n<result.list.length;n++){
									if($("#aliveDiv"+seqList[m]).attr("fsysname")==result.list[n].f_sys_name){
										if(result.list[n].unit=="null"||result.list[n].unit==null){
											result.list[n].unit=" ";
										}
										$("#aliveDiv"+seqList[m]).text(result.list[n].f_init_val+" "+result.list[n].unit);
										break;
									}
								}
							}

						}
					},
					error: function (result) {
						layer.msg("错误:"+result.msg);
					}
				});
			}
		}

		function loadOptions(){
          	//configFlowChartDdcFSysName  所属ddc
  	   		$.ajax({
  	   			type : "get",			
  	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
  	            dataType : "json",
  	            data : {},
  	            success : function(result) {
  	               	if(result.code == '0'){
  	               		$.each(result.data,function(index,item){
  	               			$('#configFlowChartDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
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
    	
		/* form的监听事件,当提交时 */
  	    form.on('submit(saveCommonConfig)',function(data){//监听配置页面div最大数目
  	    	confirmAliveDivCommonConfig(data);
  	    })
		
  	    function confirmAliveDivCommonConfig(data){
			//保存提交配置信息时，先查看有无此数据库配置，若有，则更新，若无，则插入。
			//查看form表单提交的sequence 序号值是否在数据库中已经有此数据，若有，则提示更新

			//若元素中的序号值无数据，说明此次提交的div配置信息为刚生成的div进行的配置，执行插入操作，若有数据，则进行更新操作
			if(configSequenceValue==""||configSequenceValue==null){
				//执行插入操作前，先校验提交的数据中，是否有此序号。若有，则提示更改序号信息。
				$.ajax({
	  	   			type : "get",			
	  	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/checkConfigDivSequenceInfo',
	  	            dataType : "json",
	  	            data : {
	  	            		f_div_seq : data.field.commonConfigSequence//所配置的div序号
	  	            },
	  	            success : function(result) {
	  	            	if(result.code=="0"){//此序号未配置活动div信息,可以进行插入操作
	  	            		//执行插入操作
	  	            		$.ajax({
	  	  	  		        type: "post",
	  	  	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertAliveDivCommonConfig',
	  	  	  		        contentType:'application/json;charset=UTF-8',
	  	  	  		        data:JSON.stringify({
	  	  	  		        	f_ddc_sys_name : data.field.configFlowChartDdcFSysName,
	  	  	  		        	f_sys_name     : data.field.configFlowChartPointLocationFid,
	  	  	  		        	f_div_seq      : data.field.commonConfigSequence,
	  	  	  		        	f_move_top     : f_move_top_data,
	  	  	  		        	f_move_left    : f_move_left_data
	  	  	  		        }),
	  	  	  		        success: function (result) {
	  	  	  		        	if(result.code=="0"){//插入配置div展示数据成功
	  	  	  		        		layer.close(index);
	  	  	  		        		index = 0;
	  	  	  		        		layer.msg(result.msg);
	  	  	  		        		clearInterval(timer);//关闭定时器
	  	  	  		        		$("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
	  	  	  		        	}else{
	  	  	  		        		layer.msg(result.msg);
	  	  	  		        	}
	  	  	  		        },
	  	  	  		        error: function (result) {
	  	  	  		        	layer.msg(result.msg);
	  	  	  		        }
	  	  	  			});
	  	            	}else if(result.code=="2"){//此序号已配置活动div信息，提示其将序号进行更改
	  	            		layer.close(index);
	  	          			index = 0;
	  	            		layer.msg("此DIV序号:"+data.field.commonConfigSequence+"已应用,请重新配置");
	  	            		return;//退出此方法
	  	            	}else{//查询活动div信息出错
	  	            		layer.msg(result.msg);
	  	            	}
	  	            },
	  	            error : function() {
	  	               	layer.msg("失败");
	  	            }
	  	           });
			}else{//若元素中的序号值有数据，说明此次提交的div配置信息为双击页面配置的的div进行的配置，进行更新操作
				var fDdcSysName = data.field.configFlowChartDdcFSysName;
				var fSysName = data.field.configFlowChartPointLocationFid;
				var fDivSeq = $("#commonConfigSequence").val();
				updateAliveDivCommonConfig(fDdcSysName,fSysName,fDivSeq,f_move_top_data,f_move_left_data);
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
	        		ddcSysName=$('#configFlowChartDdcFSysName').val();
	        	  	console.log("ddcSysName:"+ddcSysName);
	        	  	//触发切换相应ddc对应的点位信息时，先将之前存储的信息清空，再去获取所属DDC配置的点位；
	            	pointLocations.options.length=0;//将存储的ddc点位信息清空
	        		//获取所属DDC配置的点位
	        	  	loadPointLocations(ddcSysName,"");
	            });
        	  form.render();
          });
      	//查询页面所配置的div数目及展示信息进行展示
      	searchIntegrationAliveDivPageConfig();
        };  
        
        /* 更新活动DIV配置信息 */
        function updateAliveDivCommonConfig(f_ddc_sys_name,f_sys_name,f_div_seq,f_move_top_data,f_move_left_data){
        	$.ajax({
	  		        type: "post",
	  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateAliveDivCommonConfig',
	  		        contentType:'application/json;charset=UTF-8',
	  		        data:JSON.stringify({
	  		        	f_ddc_sys_name : f_ddc_sys_name,
	  	  		        f_sys_name     : f_sys_name,
	  	  		       	f_div_seq      : f_div_seq,
	  	  		       	f_move_top     : f_move_top_data,
	  	  		       	f_move_left    : f_move_left_data
	  		        }),
	  		        success: function (result) {

	  		        	if(result.code == '0'){//更新配置div数目成功
	  		        		layer.close(index);
	  		        		index = 0;
	  		        		clearInterval(timer);//关闭定时器
	  		        		//刷新初始化页面   //integrationInitHtml();此方法不可用，TODO:更新为重新进入此页面
	  		        		$("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
	  		        	}else{
	  		        		layer.msg(result.msg);
	  		        	}
	  		        },
	  		        error: function (result) {
	  		        	layer.msg(result.msg);
	  		        }
	  			});
        }
        //查询页面所配置的活动div数目及展示信息
        function searchIntegrationAliveDivPageConfig(){
        	$.ajax({
  	   			type : "get",			
  	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchIntegrationAliveDivPageConfig',
  	            dataType : "json",
  	            data : {},//扩展后可用?page页面名字，将表strongandweakelectricityintegration_alivedivconfig中的备用字段用上
  	            success : function(result) {
  	               	if(result.code == '0'){
  	               		//如果表中有所配置的数据，则生成相应的div并进行展示
  	               	 	var divLeader=$("#configLeaderDiv");
  	               		
  	               		// " draggable='true' ondragend= 'dragEnd(event)'"+
  	               		for(var i=0;i<result.count;i++){
  	               		 	divLeader.append("<div class='draggable flowChatShowDataCommonConfig' sequence="+ result.data[i].f_div_seq + 
  	               		 					 " id= aliveDiv"+result.data[i].f_div_seq+" fddcsysname="+result.data[i].f_ddc_sys_name+" fsysname="+result.data[i].f_sys_name+
	               							 " style= left:"+result.data[i].f_move_left+"px"+ ";top:"+result.data[i].f_move_top+"px;></div>");
  	               			sysNameList.push(result.data[i].f_sys_name);	
  	               			seqList.push(result.data[i].f_div_seq);
  	               			//$("#aliveDiv"+result.data[i].f_div_seq).addClass("aliveDiv"+result.data[i].f_div_seq);
  	               		}
  	               		$(".draggable").draggable({
  	      					stop: function(event, ui) {//已配置完成的活动DIV，当拖拽停止时，自动更新记录其拖动后的位置并展示

  	      					    var draggableDivFDdcSysName = ui.helper.context.attributes.fddcsysname.value;
  	      					    var draggableFSysName = ui.helper.context.attributes.fsysname.value
  	      						var draggableEndSequence= ui.helper.context.attributes.sequence.value;
  	      						var draggableEndTop = ui.position.top;
  	      						var draggableEndLeft = ui.position.left;
  	      						updateAliveDivCommonConfig(draggableDivFDdcSysName,draggableFSysName,draggableEndSequence,draggableEndTop,draggableEndLeft);
  	      					}
  	               		});//新增的draggable类也渲染一下 
  	               		/* $(".draggable").draggable({
  	               			//4.onStopDrag 拖动停止后发生
  	               			onStopDrag : function (e) {
  	               				console.log("拖动停止");
             		        	alert('在拖动停止时触发！');
             		    	} 
  	               		}); */ //:——>此方法无效
  	               		dbClickShowDataDiv();//给生成的配置div设置双击弹出配置框设置,设置回显操作，双击时回显先前所配置的数据
  	               		//将所需展示的数据信息和单位填入活动div展示
    	        		if(sysNameList.length!=0){
    	            		loadInitValAndEngineerUnit(sysNameList,seqList);
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
    	function loadInitValAndEngineerUnit(sysNameList,seqList){
    		$.ajax({
    			        type: "post",
    			        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
    			        contentType:'application/json;charset=UTF-8',
    			        traditional:true,
    			        data:JSON.stringify({
    			        	f_sysName_list : sysNameList
    			        }),
    			        success: function (result) {
    			        	if(result.code == '0'){
    			        		for(var m in seqList){
    			        			for(var n=0;n<result.count;n++){
    			        				if($("#aliveDiv"+seqList[m]).attr("fsysname")==result.data[n].f_sys_name){
    			        					if(result.data[n].f_engineer_unit==null){
    			        						result.data[n].f_engineer_unit=" ";
    			        					}
    			        					$("#aliveDiv"+seqList[m]).text(result.data[n].f_init_val+" "+result.data[n].f_engineer_unit);
    			        					break;
    			        				}
    			        			}
    			        		}
    			        		/* for(var i=0;i<result.count;i++){// 此方法，如果配置了两个相同的ddc点位，则只能显示一个数据，更改为上：↑
    			        			for(var j in seqList){
    			        				console.log(seqList[j]+":666666");
    			        				if($("#aliveDiv"+seqList[j]).attr("fsysname")==result.data[i].f_sys_name){
    			        					if(result.data[i].f_engineer_unit==null){
    			        						result.data[i].f_engineer_unit=" ";
    			        					}
    			        					$("#aliveDiv"+seqList[j]).text(result.data[i].f_init_val+" "+result.data[i].f_engineer_unit);
    			        					break;
    			        				}
    			        			} 
    			        		} */
    			        	}else{
    			        		layer.msg(result.msg);
    			        	}
    			        },
    			        error: function (result) {
    			        	layer.msg(result.msg);
    			        }
    				});
    	}
        
        /*加载弹框信息*/
        function loadPointLocations(ddcSysName,configFSysName){
          	//configFlowChartPointLocationFid  所属ddc下的点位信息
  	   		$.ajax({
  	   			type : "get",			
  	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
  	            dataType : "json",
  	            data : {f_sys_name:ddcSysName},
  	            success : function(result) {
  	               	if(result.code == '0'){
  	               		$.each(result.data,function(index,item){
  	               			$('#configFlowChartPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
  	               		})
  	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
  	               		if(configFSysName!=null&&configFSysName!=""){
	  	               		$("#integrationFlowChartDBClickCommonForm select[name='configFlowChartPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
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
    	
		/*点击配置按钮后生成配置div进行配置展示*/
		function generateNewConfigDiv (){

			var divLeader = document.getElementById("configLeaderDiv");
			var divEle = document.createElement("div");
			divEle.setAttribute("class", "draggable flowChatShowDataCommonConfig");
			divEle.setAttribute("fddcsysname","");
			divEle.setAttribute("fsysname","");
			divEle.style.backgroundColor="red";
			index++;
			divLeader.appendChild(divEle);
			$(".draggable").draggable();//新增的draggable类也渲染一下
			dbClickShowDataDiv();
			
		}
		//给生成的配置div设置双击弹出配置框设置
		function dbClickShowDataDiv(obj){
			$('.flowChatShowDataCommonConfig').dblclick(function(obj){
				f_move_top_data="";//点击时先将之前得到的上移和左移的值清空
				f_move_left_data="";//点击时先将之前得到的上移和左移的值清空
	        	var configDDCSysName = obj.currentTarget.attributes.fddcsysname.value;//获得所点击div配置相应的ddc系统名称
	        	var configFSysName = obj.currentTarget.attributes.fsysname.value;//获得所点击div的配置的系统名称数据
	        	var configSequence = obj.currentTarget.attributes.sequence;//TODO
	        	console.log(configSequence);
	        	if(configSequence!='undefined'&&configSequence!=null){
	        		configSequenceValue = configSequence.value;
	        	}
	        	var aliveDivStyle = obj.currentTarget.attributes.style.value;
	        	var splitStyle = aliveDivStyle.split(";");
	        	for(var styleIndex in splitStyle){
	        		if(splitStyle[styleIndex].indexOf(" top:")!=-1){//如果按";"分割的数据中，有 top 的位置，说明为 top 数据
	        			f_move_top_data = splitStyle[styleIndex].substring(splitStyle[styleIndex].indexOf(":")+1).replace("px","").replace(" ","");
	        		}
	        		if(splitStyle[styleIndex].indexOf("left:")!=-1){//如果按";"分割的数据中，有 left 的位置，说明为 left 数据
	        			//将left数据提取出来
	        			f_move_left_data = splitStyle[styleIndex].substring(splitStyle[styleIndex].indexOf(":")+1).replace("px","").replace(" ","");
	        		}
	        	}
	        	console.log(configDDCSysName+":"+configFSysName);
	        	pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
	        	index = layer.open({
	        		tytle:'配置',
	        		type:1,
	        		area:['40vw','60vh'],
	        		maxmin:true,
	        		content:$("#integrationFlowChartDBClickCommonForm"),
	        	});
	        	//若为已配置数据的div点击，则根据所配置的系统名称回显数据
	        	if(configFSysName!=""&&configFSysName!=null){//有数据，说明已配置，则回显数据，否则，不操作
	        		loadPointLocations(configDDCSysName,configFSysName);
	        		formLoadData(configDDCSysName,configFSysName,configSequenceValue);
	        	}
	        });
		}
		
		/* 编辑前的信息的展示 */
		function formLoadData(configDDCSysName,configFSysName,configSequenceValue){
			form.render();//更新全部
			$("#integrationFlowChartDBClickCommonForm select[name='configFlowChartDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
			//$("#integrationFlowChartDBClickCommonForm select[name='configFlowChartPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
			$("#integrationFlowChartDBClickCommonForm input[name='commonConfigSequence']").val(configSequenceValue);
			$("#commonConfigSequence").attr("readonly","readonly");//将回显的序号设置为只读属性
			form.render();
		}
		
        /*添加和编辑的关闭按钮  */
    	function closeLayerHost(){
    		layer.close(index);
    		index = 0;
    	} 
		function deleteAliveCommonConfigInfo(){
			//提示删除操作;
			layer.open({
			  content: '删除'
			  ,btn: ['确定', '取消']
			  ,yes: function(index, layero){//确定删除
				  //按钮【确定】的回调:执行删除操作
				  $.ajax({
					  type : "get",			
		  	          url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/deleteAliveCommonConfigInfo',
		  	          dataType : "json",
		  	          data : {
		  	        	  f_div_seq : $("#commonConfigSequence").val(),
		  	          },
		  	          success : function(result) {
		  	        	  if(result.code=="0"){
		  	        		  layer.msg(result.msg);
		  	        		  //layer.msg("删除配置成功");
			        		  //刷新初始化页面   //integrationInitHtml();此方法不可用，TODO:更新为重新进入此页面
			        	      $("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
			        	      layer.close(index);
			        	      index = 0;
		  	        	  }else if(result.code=="2"){
		  	        		  layer.msg(result.msg);
		  	        	  }else{
		  	        		  layer.msg(result.msg);
		  	        	  }
		  	          },
		  	          error : function() {
		  	               	layer.msg("删除配置失败");
		  	          }
		  	       });
			    //return false 开启该代码可禁止点击该按钮关闭
			  }
			  ,btn2: function(index, layero){//取消删除
			    //按钮【取消】的回调
			    //return false 开启该代码可禁止点击该按钮关闭
			  }
			});
		}
		return {
			configShowData : function(){
				generateNewConfigDiv();
			},
			closeCommonConfigInfos :function(){
    			closeLayerHost();
    		},
    		deleteCommonConfigInfos : function(){
    			deleteAliveCommonConfigInfo();
    		}
		}
	})(jQuery, window, document);
</script>
