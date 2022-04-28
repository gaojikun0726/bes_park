<style>
	.sensorDivTableClass td{
		/* border: solid 1px #ababab; */
		border: solid 1px #f7f7f7;
	}
</style>
<link href="${ctx}/static/css/smartMenu.css" rel="stylesheet">
<script src="${ctx}/static/js/jquery-smartMenu.js"></script>
<div class="" id="sensorDiv">
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;传感器信息>>>
    	</span> 
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon sensorLeaderDiv">
	<div class="sensorDivTableClass " style="float:left;margin-top:40px;margin-left:60px;width:25%;text-align:center;">
			<div>
				<div style="float:left;text-align:left;margin:0px 10px;">流量计</div>
				<a class="sensorTableDataConfig" tablemark="0" style="float:right;text-align:right;margin:0px 10px;">增加</a>
			</div>
			<table id="flowDataLeader" class="commonChangeColorLighter" style="width:95%;margin:0px 10px;">
				<!-- <caption style="text-align: left;color:#eb25ef;margin-left:10px;"><b>流量计</b></caption> -->
				<tr style="height:40px;">
					<td style="width:60%">名称</td><td style="width:35%">当前值</td>
				</tr>
			</table>
		</div>
		<div class="sensorDivTableClass" style="float:left;margin-top:40px;margin-left:60px;width:25%;text-align:center;">
			<div>
				<div style="float:left;text-align:left;margin:0px 10px;">温度计</div>
				<a class="sensorTableDataConfig" tablemark="1" style="float:right;text-align:right;margin:0px 10px;">增加</a>
			</div>
			<table id="thermometerDataLeader"class="commonChangeColorLighter" style="width:95%; height:100%;margin:0px 10px;">
				<tr style="height:40px;">
					<td style="width:60%;"><b>名称</b></td><td style="width:35%"><b>当前值</b></td>
				</tr>
			</table>
		</div>
		<div class="sensorDivTableClass" style="float:left;margin-top:40px;margin-left:60px;width:25%;text-align:center;">
			<div>
				<div style="float:left;text-align:left;margin:0px 10px;">压差计、压力计、负荷计</div>
				<a class="sensorTableDataConfig" tablemark="2" style="float:right;text-align:right;margin:0px 10px;">增加</a>
			</div>
			<table id="loadMeterDataLeader"class="commonChangeColorLighter" style="width:95%;margin:0px 10px;">
				<tr style="height:40px;">
					<td style="width:60%">名称</td><td style="width:35%">当前值</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- sensor DIV配置框 -->
	<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="integrationSensorDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">      
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3"> 
			        	<select id="configSensorDdcFSysName" name="configSensorDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2"> 
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3"> 
			        	<select id="configSensorPointLocationFid" name="configSensorPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
					</div>      
			    </div>
		    </div>
			<div class="layui-form-item">
				<div class="layui-row">
					<div class="layui-col-xs2"> 
				        <label class="layui-form-label">描&emsp;&emsp;述：</label>
			        </div>
			        <div class="layui-col-xs3"> 
		            	<input type="text"  name="commonConfigDesc" value="" placeholder="描述"   lay-verify="" class="layui-input">
					</div>
					<div class="layui-col-xs2"> 
				        <label class="layui-form-label">序&emsp;&emsp;号：</label>
			        </div>
			        <div class="layui-col-xs3"> 
		            	<input type="number"  id="commonSensorConfigSequence" name="commonSensorConfigSequence" value="" placeholder="配置DIV序号"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
				    <div class="layui-input-block" style="float:right;">
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfig" >提交</button>
				        <button type="button" class="layui-btn layui-btn-warm" onclick="view_sensorData_page.deleteCommonConfigInfos()">删除</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="view_sensorData_page.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
;
var view_sensorData_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var sensorTimer;
	var tableTdIndex=0;//自动生成表数据的下标
	var sysNameList = new Array();
	var tableTdIndexList= new Array();
	var sensorIndex = 0;//默认弹框sensorIndex为0
	var pointLocations=document.getElementById('configSensorPointLocationFid');
	var configDDCSysName;
	var configDDCSysNameValue="";
	var configFsysNameValue="";
	var configDescValue="";
	var configFSeqValue="";
	var fMark;
	var fId;
	
	/* var divSensorHeightSum = $(window).height();//网页窗口可见区域总体高度
	var divSensorWidthSum = $(window).width();//网页窗口可见区域总宽度
	$(".sensorLeaderDiv").css("height",divSensorHeightSum);//设置div高度
	$(".sensorLeaderDiv").css("width",divSensorWidthSum);//设置div宽度 */
	
	$(function(){
		loadOptions();//给下拉菜单加载选项
		integrationInitHtml();//初始化页面
		clickTalbeDataConfig();
		/* sensorTimer = setInterval(function () {
			//当关闭此标签页面时，消除此定时器(依据：当未关闭此标签页面时，能根据id获取到信息，关闭后，无法根据id获得信息，则关闭定时器)
			if(document.getElementById('sensorDiv')===null){
				clearInterval(sensorTimer);
				return;
			}
			loadInitVal(sysNameList,tableTdIndexList);
        }, 5000); */
	}); 
	
 	//点击配置按钮后生成配置tr进行表数据展示
 	function generateNewConfigTr(trLeaderId,tableMark){
		console.log(trLeaderId);
		if(document.getElementById("generateSensorDiv")!=null){
			layer.msg("请先配置待配置的数据行");
			return;
		}
	 	var trLeader = $(trLeaderId);
	 	trLeader.append("<tr id='generateSensorDiv' class='sensorTableDataCommonConfig' fddcsysname='' fseq='' fmark="+tableMark+" tdindex=''>"+
	 					"<td style='text-align:left;'>待配置</td><td id='' fsysname=''>待显示</td></tr>");
	 	dbClickShowDataDiv2();//给新增的tr设置双击配置事件
 	}
	//单机表格上方配置，根据其不同表格标记，在不同的表格下添加展示配置
	function clickTalbeDataConfig(obj){
		$(".sensorTableDataConfig").click(function(obj){
			console.log(obj);
			var tableMark;//表格标记
			tableMark=obj.currentTarget.attributes.tablemark.value;
			if(tableMark=="0"){//流量计
				generateNewConfigTr("#flowDataLeader","0")
			}else if(tableMark=="1"){//温度计
				generateNewConfigTr("#thermometerDataLeader","1");
			}else if(tableMark=="2"){//压力压差负荷计
				generateNewConfigTr("#loadMeterDataLeader","2");
			}
		});
	}
	//给增加的div设置双击弹出配置框设置
	function dbClickShowDataDiv2(obj){
 		$('#generateSensorDiv').dblclick(function(obj){
 			sensorIndex=0;
			
			configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
			fId = obj.currentTarget.attributes.fid;
			fMark  = obj.currentTarget.attributes.fmark;
			if(configDDCSysName!='undefined'&&configDDCSysName!=null){
				configDDCSysNameValue = configDDCSysName.value;
			}
			if(fMark!='undefined'&&fMark!=null){
				fMark = fMark.value;
			}
			sensorIndex = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#integrationSensorDBClickCommonForm"),
			});
			$("#integrationSensorDBClickCommonForm select[name='configSensorDdcFSysName'] option[value='']").prop("selected","selected");
			//$("#integrationSensorDBClickCommonForm select[name='configSensorPointLocationFid'] option[value='']").prop("selected",true);
			$("#configSensorPointLocationFid").val("");
			$("#integrationSensorDBClickCommonForm input[name='commonConfigDesc']").val("");//填写描述数据回显
			$("#integrationSensorDBClickCommonForm input[name='commonSensorConfigSequence']").val("");//填写序号数据回显
			form.render();
		});
	}
	//给生成的配置div设置双击弹出配置框设置
	function dbClickShowDataDiv(obj){
		$('.sensorTableDataCommonConfig').dblclick(function(obj){
			//layer.close(sensorIndex);
			sensorIndex=0;
			configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
			var configFsysName   = obj.currentTarget.attributes.fsysname;
			var configSeq        = obj.currentTarget.attributes.fseq;
			var configDesc       = obj.currentTarget.attributes.fdesc;
			fId = obj.currentTarget.attributes.fid;
			fMark  = obj.currentTarget.attributes.fmark;
        	if(configDDCSysName!='undefined'&&configDDCSysName!=null){
        		configDDCSysNameValue = configDDCSysName.value;
        	}
        	if(configFsysName!="undefined"&&configFsysName!=null){
        		configFsysNameValue = configFsysName.value;
        	}
        	if(configSeq!="undefined"&&configSeq!=null){
        		configFSeqValue = configSeq.value;
        	}
        	if(configDesc!='undefined'&&configDesc!=null){
        		configDescValue = configDesc.value;
        	}
			if(fId!='undefined'&&fId!=null){
				fId = fId.value;
			}
        	if(fMark!='undefined'&&fMark!=null){
        		fMark = fMark.value;
        	}
			sensorIndex = layer.open({
        		tytle:'配置',
        		type:1,
        		area:['40vw','60vh'],
        		maxmin:true,
        		content:$("#integrationSensorDBClickCommonForm"),
        	});
			//若为已配置数据的tr点击，则根据所配置的系统名称回显数据
        	if(configFsysNameValue!=""&&configFsysNameValue!=null){//有数据，说明已配置，则回显数据，否则，不操作
        		loadPointLocations(configDDCSysNameValue,configFsysNameValue);
        		pointLocations.options.length=0;//将存储的ddc点位信息清空
        		formLoadData(configDDCSysNameValue,configFsysNameValue);
        	}
			
			
			
        });
		form.render();
	}
	
	/* 编辑前的信息的展示 */
	function formLoadData(configDDCSysName,configFSysName){
		form.render();//更新全部
		$("#integrationSensorDBClickCommonForm select[name='configSensorDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
		$("#integrationSensorDBClickCommonForm input[name='commonConfigDesc']").val(configDescValue);//填写描述数据回显
		$("#integrationSensorDBClickCommonForm input[name='commonSensorConfigSequence']").val(configFSeqValue);//填写序号数据回显
		form.render();
	}
	
	
	
	//给下拉菜单加载选项
	function loadOptions(){
   		$.ajax({
   			type : "get",			
            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
            dataType : "json",
            data : {},
            success : function(result) {
               	if(result.code == '0'){
               		$.each(result.data,function(index,item){
               			$('#configSensorDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
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
	
	 function integrationInitHtml(){
		 
      	layui.use('form', function(){
        	  form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        	  layer = layui.layer;
        	  //但是，如果你的HTML是动态生成的，自动渲染就会失效
        	  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
        	  form.on('select(ddcOption)', function (data) {//监听选择ddc后,点位选项根据选中的ddc信息来进行动态生成
        		//触发内容
        		ddcSysName=$('#configSensorDdcFSysName').val();
        	  	console.log("ddcSysName:"+ddcSysName);
        	  	//触发切换相应ddc对应的点位信息时，先将之前存储的信息清空，再去获取所属DDC配置的点位；
            	pointLocations.options.length=0;//将存储的ddc点位信息清空
        		//获取所属DDC配置的点位
        	  	loadPointLocations(ddcSysName,"");
            });
     		form.render();
       	});
   		//查询页面所配置的表数据展示信息并进行展示
   		searchIntegrationTableDataConfig();
     };   
     /*加载弹框信息*/
     function loadPointLocations(ddcSysName,configFSysName){
       	//configSensorPointLocationFid  所属ddc下的点位信息
	   		$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
	            dataType : "json",
	            data : {f_sys_name:ddcSysName},
	            success : function(result) {
	               	if(result.code == '0'){
	               		$.each(result.data,function(index,item){
	               			$('#configSensorPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
	               		})
	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
	               		if(configFSysName!=null&&configFSysName!=""){
	  	               		$("#integrationSensorDBClickCommonForm select[name='configSensorPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
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
     
     
     
   //查询页面所配置的活动div数目及展示信息
     function searchIntegrationTableDataConfig(){
     	$.ajax({
	   			type : "get",			
	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchIntegrationTableDataConfig',
	            dataType : "json",
	            data : {f_page_name:"sensor",
	            		f_device_id:""},
	            success : function(result) {
	               	if(result.code == '0'){
	               		//如果表中有所配置的数据，则根据相应表格标记在生成不同表格展示数据
	               		var flowDataLeader=$("#flowDataLeader");//流量计表数据
	               		var thermometerDataLeader=$("#thermometerDataLeader");//温度计表数据
	               		var loadMeterDataLeader=$("#loadMeterDataLeader");
	               		for(var data of result.data){
	               			console.log(data);
	               			tableTdIndex++;
	               			sysNameList.push(data.f_sys_name);//将系统名称存储记录
	               			tableTdIndexList.push(tableTdIndex);//将下标记录存储
	               			if(data.f_mark=="0"){//如果表数据标记为0，则代表为流量计
	               				flowDataLeader.append("<tr class='sensorTableDataCommonConfig' fddcsysname="+data.f_ddc_sys_name+" fsysname="+data.f_sys_name+" fid="+data.f_id+" fseq="+data.f_seq
	               						+" fdesc="+data.f_desc+ " fmark="+data.f_mark+ " tdindex="+tableTdIndex+"><td style='text-align:left;'>"
	               						+data.f_desc+"</td><td id=sensor"+tableTdIndex+" fsysname="+data.f_sys_name+">"+6+"</td></tr>");
	               				continue;
	               			}
	               			if(data.f_mark=="1"){
	               				thermometerDataLeader.append("<tr class='sensorTableDataCommonConfig' fddcsysname="+data.f_ddc_sys_name+" fsysname="+data.f_sys_name+" fid="+data.f_id+" fseq="+data.f_seq
	               						+" fdesc="+data.f_desc+" fmark="+data.f_mark+ " tdindex="+tableTdIndex+"><td style='text-align:left;'>"
	               						+data.f_desc+"</td><td id=sensor"+tableTdIndex+" fsysname="+data.f_sys_name+">"+6+"</td></tr>");
	               				continue;
	               			}
	               			if(data.f_mark=="2"){
	               				loadMeterDataLeader.append("<tr class='sensorTableDataCommonConfig' fddcsysname="+data.f_ddc_sys_name+" fsysname="+data.f_sys_name+" fid="+data.f_id+" fseq="+data.f_seq
	               						+" fdesc="+data.f_desc+" fmark="+data.f_mark+ " tdindex="+tableTdIndex+"><td style='text-align:left;'>"
	               						+data.f_desc+"</td><td id=sensor"+tableTdIndex+" fsysname="+data.f_sys_name+">"+6+"</td></tr>");
	               				continue;
	               			}
	               		}
	               		dbClickShowDataDiv();//给生成的配置tr设置双击弹出配置框，TODO:设置回显操作，双击时回显先前所配置的数据
	               		if(sysNameList.length!=0){
	               			loadInitVal(sysNameList,tableTdIndexList);//将所需要展示的数据信息填入相应的表格中进行展示
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
   
     function loadInitVal(sysNameList,tableTdIndexList){
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
						//TODO  将获取的数据添加到相应的表数据中进行展示
						for(var m of tableTdIndexList ){
							for(var n=0;n<result.list.length;n++){
								if($("#sensor"+m).attr("fsysname")==result.list[n].f_sys_name){
									$("#sensor"+m).text(result.list[n].f_init_val);
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
     
     /* form的监听事件,当提交时 */
	 form.on('submit(saveCommonConfig)',function(data){//监听配置页面提交事件
	   	 confirmTableDataConfig(data);
	 })
	 
	 function confirmTableDataConfig(data){
		//若元素中的自定义属性中，没有ddcSysName属性，则代表为新生成的tr进行的配置，执行插入操作。若有数据，则进行更新操作
		if(configDDCSysNameValue==""||configDDCSysNameValue==null){
			//执行插入操作 
			$.ajax({
  		        type: "post",
  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertTableDataCommonConfig',
  		        contentType:'application/json;charset=UTF-8',
  		        data:JSON.stringify({
  		        	f_ddc_sys_name : data.field.configSensorDdcFSysName,
  		        	f_sys_name     : data.field.configSensorPointLocationFid,
  		        	f_desc         : data.field.commonConfigDesc,
  		        	f_seq          : data.field.commonSensorConfigSequence,
  		        	f_mark         : fMark,
  		        	f_page_name	   : 'sensor',
  		        	f_device_id    :''
  		        }),
  		        success: function (result) {
  		        	if(result.code=="1"){//插入配置div展示数据成功
  		        		layer.close(sensorIndex);
  		        		sensorIndex = 0;
  		        		layer.msg(result.msg);
  		        		//clearInterval(timer);//关闭定时器
  		        		//$("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
  		        		$("#sensorDiv").load(_ctx+'//view/strongAndWeakElectricityIntegration/sensor/showInitPage');
  		        	}else{
  		        		layer.msg(result.msg);
  		        	}
  		        },
  		        error: function (result) {
  		        	layer.msg(result.msg);
  		        }
  			});
			
		}else{//执行更新操作
			$.ajax({
  		        type: "post",
  		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/updateTableDataCommonConfig',
  		        contentType:'application/json;charset=UTF-8',
  		        data:JSON.stringify({
  		        	f_ddc_sys_name : data.field.configSensorDdcFSysName,
  	  		        f_sys_name     : data.field.configSensorPointLocationFid,
  	  		       	f_desc         : data.field.commonConfigDesc,
  	  		       	f_seq	       : data.field.commonSensorConfigSequence,
  	  		       	//f_mark    	   : fMark,
					f_id		   : fId
  		        }),
  		        success: function (result) {
  		        	if(result.code == '1'){//更新配置div数目成功
  		        		layer.close(sensorIndex);
  		        		sensorIndex = 0;
  		        		//clearInterval(timer);//关闭定时器
  		        		//刷新初始化页面   //integrationInitHtml();此方法不可用，TODO:更新为重新进入此页面
  		        		//$("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
  		        		$("#sensorDiv").load(_ctx+'//view/strongAndWeakElectricityIntegration/sensor/showInitPage');
  		        	}else{
  		        		layer.msg(result.msg);
  		        	}
  		        },
  		        error: function (result) {
  		        	layer.msg(result.msg);
  		        }
  			});
			
		}
     }
	 function deleteSensorTableDataCommonConfig(){
		//提示删除操作;
			layer.open({
			  content: '删除'
			  ,btn: ['确定', '取消']
			  ,yes: function(index, layero){//确定删除
				  //按钮【确定】的回调:执行删除操作
				  //若为新增的DIV，进行删除的时候进行判断
				  if(fId=='undefined'||fId==null){
					  layer.close(sensorIndex);
					  layer.close(index);
					  sensorIndex = 0;
					  $("#sensorDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/sensor/showInitPage');
					  return;
				  }
				  $.ajax({
					  type : "get",			
		  	          url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/deleteTableDataCommonConfig',
		  	          dataType : "json",
		  	          data : {
		  	        	  f_id : fId,
		  	          },
		  	          success : function(result) {
		  	        	  if(result.code=="1"){
		  	        		  layer.msg(result.msg);
			        	      //$("#flowChartDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/flowChart/showInitPage');
			        	      layer.close(sensorIndex);
			        	      sensorIndex = 0;
			        	      $("#sensorDiv").load(_ctx+'//view/strongAndWeakElectricityIntegration/sensor/showInitPage');
		  	        	  }else{
		  	        		  layer.msg(result.msg);
		  	        	  }
		  	          },
		  	          error : function() {
		  	               	layer.msg("删除配置失败");
		  	          }
		  	       });
			  }
			  ,btn2: function(index, layero){//取消删除
			    //按钮【取消】的回调
			    //return false 开启该代码可禁止点击该按钮关闭
			  }
			});
	 }
	 
		
     //添加和编辑的关闭按钮
     function closeLayerHost(){
    	 layer.close(sensorIndex);
    	 sensorIndex=0;
     }
     
     
     return{
    	 	configShowTableData : function(){
				generateNewConfigTr();
			},
			closeCommonConfigInfos :function(){
    			closeLayerHost();
    		},
    		deleteCommonConfigInfos : function(){
    			deleteSensorTableDataCommonConfig();
    		}
     }
})(jQuery, window, document);
</script>


