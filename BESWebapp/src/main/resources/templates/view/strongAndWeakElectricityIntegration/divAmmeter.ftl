<style>
/* 设置隐藏表格td边界 */
.hidentable td{
	border: solid 1px #ffffff;
}

/* 奇数行设置背景色 */
/* .hidentable tr:nth-child(odd){
	background: #ccc;
} */
/* 偶数行设置背景色 */
.hidentable tr:nth-child(even){
background: #f4f8f8;

}

</style>
<div style="">
	<div>
		<button class="orangebtn btn btn-default" id="returnAmmeterButton" style="width:60px;  height: 25px; margin-left:40px">返回</button>
		&nbsp;&nbsp;&nbsp;&nbsp;1001电表
	</div>
	<!-- Start by 基本参数 -->
	<div class="commonChangeColorLighter strongAndWeakElectricityIntegrationCommonTable" style="width: 35%; float: left; height: 100%;margin-left:20px;">
		<div>
			<a class="ammeterTableDataConfig" tablemark="3" style="float:right;text-align:right;margin:0px 10px;">增加</a>
		</div>
		<table  class="hidentable"style="width:100%; height:100%;text-align: center;">
			<caption style="text-align: center;color:red;"><b>基本参数</b></caption>
			<thead>
				<td style="width:33%;"><b>属性名</b></td><td style="width:33%"><b>属性值</b></td><td style="width:33%"><b>单位</b></td>
			</thead>
			<!-- 下方tbody只是暂时展示用，需删除：↓ -->
			<tbody>
				<tr>
					<td style="text-align:left;">当前瞬时电能</td><td>0.192</td><td>kW</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前总电能</td><td>140.71万</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前尖电能</td><td>0</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前峰电能</td><td>0</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前平电能</td><td>14070.80万</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前谷电能</td><td>0</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">相关设备系统ID</td><td></td><td></td>
				</tr>
				<tr>
					<td style="text-align:left;">当前实际总电能</td><td>140.71万</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">当前校对底数</td><td>0</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">本月累计电能</td><td>4.84万</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">本月抄表底数</td><td>135.87万</td><td>kW·h</td>
				</tr>
				<tr>
					<td style="text-align:left;">A相电流</td><td>1.4</td><td>A</td>
				</tr>
				<tr>
					<td style="text-align:left;">B相电流</td><td>0.256</td><td>A</td>
				</tr>
				<tr>
					<td style="text-align:left;">C相电流</td><td>1.4</td><td>A</td>
				</tr>
				<tr>
					<td style="text-align:left;">A相电压</td><td>224.1</td><td>V</td>
				</tr>
				<tr>
					<td style="text-align:left;">B相电压</td><td>222.3</td><td>V</td>
				</tr>
				<tr>
					<td style="text-align:left;">C相电压</td><td>223.7</td><td>V</td>
				</tr>
			</tbody>
			<!-- 上方的tbody只是暂时展示用，需删除：↑ -->
			<tbody id="basicParametersTableLeader">
			</tbody>
			
		</table>
	</div>
	<!-- End by 基本参数 -->	
	<!-- Start by 右侧表格 -->
	<div class="commonChangeColorLighter strongAndWeakElectricityIntegrationCommonTable" style="width: 35%; float: left; height: 100%;margin-left:20px;">
		<div>
			<a class="ammeterTableDataConfig" tablemark="4" style="float:right;text-align:right;margin:0px 10px;">增加</a>
		</div>
		<table class="hidentable"style="width:100%; height:100%;text-align: center;">
			<caption style="text-align: center;color:red;">
				<button class="orangebtn btn btn-default" id="ammeterNowMonthButton" style="width:70px;  height: 25px;">本&nbsp;&nbsp;&nbsp;&nbsp;月</button>
				<button class="orangebtn btn btn-default" id="ammeterLastMonthButton" style="width:70px;  height: 25px; margin-left:40px">上一月</button>
				<button class="orangebtn btn btn-default" id="ammeterNextMonthButton" style="width:70px;  height: 25px; margin-left:40px">下一月</button>
			</caption>
			<thead>
				<td style="width:33%;"><b>校对时间</b></td><td style="width:33%"><b>校对底数</b></td><td style="width:33%"><b>校对类型</b></td>
			</thead>
			<!-- TODO:下方的tbody为暂时展示所用，待删除：↓ -->
			<tbody>
				<tr>
					<td style="text-align:left;">A相电流</td><td>1.4</td><td>A</td>
				</tr>
				<tr >
					<td style="text-align:left;">B相电流</td><td>0.256</td><td>A</td>
				</tr>
				<tr>
					<td style="text-align:left;">C相电流</td><td>1.4</td><td>A</td>
				</tr>
				<tr>
					<td style="text-align:left;">A相电压</td><td>224.1</td><td>V</td>
				</tr>
				<tr>
					<td style="text-align:left;">B相电压</td><td>222.3</td><td>V</td>
				</tr>
				<tr>
					<td style="text-align:left;">C相电压</td><td>223.7</td><td>V</td>
				</tr>
			</tbody>
			<!-- TODO:上方的tbody为暂时展示所用，待删除：↑ -->
			<tbody id="proofreaderTableLeader">
			</tbody>
			
		</table>
	</div>
	<!-- End by 右侧表格 -->
	
	
	<!-- ammeter DIV配置框 -->
	<div class="layui-fluid">
		<form class="layui-form " style="display: none;" id="integrationAmmeterDBClickCommonForm">
		<input type="hidden">
		    <div class="layui-form-item" style = "margin-top:2vh;">
				<div class="layui-row">
					<div class="layui-col-xs2">      
			        	<label class="layui-form-label">所属DDC：</label>
	        		</div>
					<div class="layui-col-xs3"> 
			        	<select id="configAmmeterDdcFSysName" name="configAmmeterDdcFSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
			        </div>
			       <div class="layui-col-xs2"> 
				        <label class="layui-form-label">所属点位：</label>
			        </div>
					<div class="layui-col-xs3"> 
			        	<select id="configAmmeterPointLocationFid" name="configAmmeterPointLocationFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
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
		            	<input type="number"  id="commonAmmeterConfigSequence" name="commonAmmeterConfigSequence" value="" placeholder="配置DIV序号"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-row">
					<div class="layui-col-xs2"> 
				        <label class="layui-form-label">单&emsp;&emsp;位：</label>
			        </div>
			        <div class="layui-col-xs3"> 
		            	<input type="text"  name="commonUnitDesc" value="" placeholder="单位"   lay-verify="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-col-xs9">
				    <div class="layui-input-block" style="float:right;">
				        <button type="button" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveCommonConfig" >提交</button>
				        <button type="button" class="layui-btn layui-btn-warm" onclick="view_ammeterData_page.deleteCommonConfigInfos()">删除</button>
				        <button type="button" class="layui-btn layui-btn-primary" onclick="view_ammeterData_page.closeCommonConfigInfos()">取消</button>
			        </div>
		     	</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
    ;
    var view_ammeterData_page = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var server_Interval=5*1000;//定时器 5秒钟
        
        var f_ammeter_id;
        var pointLocations=document.getElementById('configAmmeterPointLocationFid');
        var tableTdIndex=0;//自动生成表数据的下标
        var sysNameList = new Array();
    	var tableTdIndexList= new Array();
    	var configFsysNameValue="";
        var configDDCSysName;
		var configDDCSysNameValue="";
		var fMark;
		var fId;
    	
        /* 点击左上方返回按钮，返回主页面 */
        $("#returnAmmeterButton").click(function() {
        	//location.reload();
        	$("#ammeterDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/ammeter/showInitPage');
        });
        
        $(function(){
        	f_ammeter_id = $("#ammeterHiddenInput").val();//取得主页面进入查看页面所传的设备id
        	loadOptions();//给下拉菜单加载选项
    		integrationInitHtml();//初始化页面
    		clickTalbeDataConfig();//点击增加时，相应表格向下增添数据行
        })
        
      	//单机表格上方配置，根据其不同表格标记，在不同的表格下添加展示配置
    	function clickTalbeDataConfig(obj){
    		$(".ammeterTableDataConfig").click(function(obj){
    			var tableMark;//表格标记
    			tableMark=obj.currentTarget.attributes.tablemark.value;
    			if(tableMark=="3"){//流量计
    				generateNewConfigTr("#basicParametersTableLeader","3")
    			}else if(tableMark=="4"){//温度计
    				generateNewConfigTr("#proofreaderTableLeader","4");
    			}
    		});
    	}
      	//点击配置按钮后生成配置tr进行表数据展示
     	function generateNewConfigTr(trLeaderId,tableMark){
    	 	var trLeader = $(trLeaderId);
			if(document.getElementById("generateAmmeterDiv")!=null){
				layer.msg("请先配置待配置的数据行");
				return;
			}
    	 	/* trLeader.append("<tr class='ammeterTableDataCommonConfig' fddcsysname='' fseq='' fmark="+tableMark+" tdindex=''>"+
    	 					"<td style='text-align:left;'>待配置</td><td id='' fsysname=''>待显示</td></tr>"); */
    	 	
    	 	trLeader.append("<tr id='generateAmmeterDiv' class='ammeterTableDataCommonConfig' fddcsysname='' fmark="+tableMark+">"+
    	 				"<td>待配置</td><td id='' fsysname=''>待显示</td><td>待配置</td></tr>");
    	 	dbClickShowDataDiv2();//给新增的tr设置双击配置事件
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
                   			$('#configAmmeterDdcFSysName').append(new Option(item.f_nick_name,item.f_sys_name));
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
            		ddcSysName=$('#configAmmeterDdcFSysName').val();
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
    	   		$.ajax({
    	   			type : "get",			
    	            url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
    	            dataType : "json",
    	            data : {f_sys_name:ddcSysName},
    	            success : function(result) {
    	               	if(result.code == '0'){
    	               		$.each(result.data,function(index,item){
    	               			$('#configAmmeterPointLocationFid').append(new Option(item.f_nick_name,item.f_sys_name));
    	               		})
    	               		/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
    	               		if(configFSysName!=null&&configFSysName!=""){
    	  	               		$("#integrationAmmeterDBClickCommonForm select[name='configAmmeterPointLocationFid'] option[value="+configFSysName+"]").prop("selected","selected");
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
    	            data : {f_page_name:"ammeter",
    	            		//f_device_id:f_ammeter_id
    	            		f_device_id:"888888"
    	            		},//TODO : test:111
    	            success : function(result) {

    	               	if(result.code == '0'){
    	               		//如果表中有所配置的数据，则根据相应表格标记在生成不同表格展示数据
    	               		var basicParametersTableLeader=$("#basicParametersTableLeader");//基本参数表数据
    	               		var proofreaderTableLeader=$("#proofreaderTableLeader");//校对数据表数据
    	               		for(var data of result.data){
    	               			tableTdIndex++;
    	               			sysNameList.push(data.f_sys_name);//将系统名称存储记录
    	               			tableTdIndexList.push(tableTdIndex);//将下标记录存储
    	               			var f_unit = data.f_unit;
    	               			if(f_unit==undefined){//若未配置单位，则显示为空。避免显示为'undefined';
    	               				f_unit="";
    	               			}
    	               			if(data.f_mark=="3"){//如果表数据标记为3，则代表为电表中的基础参数表
    	               				/* <tr>
    	            					<td style="text-align:left;">当前瞬时电能</td><td>0.192</td><td>kW</td>
    	            				</tr> */
    	               				basicParametersTableLeader.append("<tr class='ammeterTableDataCommonConfig' fddcsysname="+data.f_ddc_sys_name+" fsysname="+data.f_sys_name+
    	               						" fid="+data.f_id+" fseq="+data.f_seq+" fdesc="+data.f_desc+" fmark="+data.f_mark+" funit="+data.f_unit+" tdindex="+tableTdIndex+"><td>"+data.f_desc+
    	               						"</td><td id=ammeter"+tableTdIndex+" fsysname="+data.f_sys_name+">888</td><td id=ammeterUnit"+tableTdIndex+">"+f_unit+"</td></tr>");
    	               				continue;
    	               			}
    	               			if(data.f_mark=="4"){//电表中的校对参数表
    	               				proofreaderTableLeader.append("<tr class='ammeterTableDataCommonConfig' fddcsysname="+data.f_ddc_sys_name+" fsysname="+data.f_sys_name+
    	               						" fid="+data.f_id+" fseq="+data.f_seq+" fdesc="+data.f_desc+" fmark="+data.f_mark+" funit="+data.f_unit+" tdindex="+tableTdIndex+"><td>"+data.f_desc+
    	               						"</td><td id=ammeter"+tableTdIndex+" fsysname="+data.f_sys_name+">888</td><td id=ammeterUnit"+tableTdIndex+">"+f_unit+"</td></tr>");
    	               				continue;
    	               			}
    	               		}
    	               		dbClickShowDataDiv();//给生成的配置tr设置双击弹出配置框
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

		//给增加的div设置双击弹出配置框设置
		function dbClickShowDataDiv2(obj){
			$('#generateAmmeterDiv').dblclick(function(obj){
				ammeterIndex=0;
                configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
				fId = obj.currentTarget.attributes.fid;
				fMark  = obj.currentTarget.attributes.fmark;
                if(configDDCSysName!='undefined'&&configDDCSysName!=null){
                    configDDCSysNameValue = configDDCSysName.value;
                }
				if(fMark!='undefined'&&fMark!=null){
					fMark = fMark.value;
				}
				ammeterIndex = layer.open({
					tytle:'配置',
					type:1,
					area:['40vw','60vh'],
					maxmin:true,
					content:$("#integrationAmmeterDBClickCommonForm"),
				});
				$("#integrationAmmeterDBClickCommonForm select[name='configAmmeterDdcFSysName'] option[value='']").prop("selected","selected");
				//$("#integrationAmmeterDBClickCommonForm select[name='configAmmeterPointLocationFid'] option[value='']").prop("selected","selected");
				$("#configAmmeterPointLocationFid").val("");
				$("#integrationAmmeterDBClickCommonForm input[name='commonConfigDesc']").val("");//填写描述数据回显
				$("#integrationAmmeterDBClickCommonForm input[name='commonUnitDesc']").val("");//填写单位数据回显
				$("#integrationAmmeterDBClickCommonForm input[name='commonAmmeterConfigSequence']").val("");//填写序号数据回显
				form.render();
			});
		}


       //给生成的配置div设置双击弹出配置框设置
     	function dbClickShowDataDiv(obj){
     		$('.ammeterTableDataCommonConfig').dblclick(function(obj){
     			configDDCSysName = obj.currentTarget.attributes.fddcsysname;//获得所点击div配置相应的ddc系统名称
     			var configFsysName   = obj.currentTarget.attributes.fsysname;
     			var configSeq        = obj.currentTarget.attributes.fseq;
     			var configDesc       = obj.currentTarget.attributes.fdesc;
     			var configUnit       = obj.currentTarget.attributes.funit;
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
             	if(configUnit!='undefined'&&configUnit!=null){
             		configUnitValue = configUnit.value;
             		if(configUnitValue=='undefined'){//避免未设置单位时显示为'undefined'
             			configUnitValue = "";
             		}
             	}
     			if(fId!='undefined'&&fId!=null){
     				fId = fId.value;
     			}
             	if(fMark!='undefined'&&fMark!=null){
             		fMark = fMark.value;
             	}
     			ammeterIndex = layer.open({
             		tytle:'配置',
             		type:1,
             		area:['40vw','60vh'],
             		maxmin:true,
             		content:$("#integrationAmmeterDBClickCommonForm"),
             	});
     			//若为已配置数据的tr点击，则根据所配置的系统名称回显数据
             	if(configFsysNameValue!=""&&configFsysNameValue!=null){//有数据，说明已配置，则回显数据，否则，不操作
             		loadPointLocations(configDDCSysNameValue,configFsysNameValue);
             		pointLocations.options.length=0;//将存储的ddc点位信息清空
             		formLoadData(configDDCSysNameValue,configUnitValue,configFsysNameValue);
             	}
             });
     		form.render();
     	}
         
     	/* 编辑前的信息的展示 */
    	function formLoadData(configDDCSysName,configUnitValue,configFSysName){
    		form.render();//更新全部
    		$("#integrationAmmeterDBClickCommonForm select[name='configAmmeterDdcFSysName'] option[value="+configDDCSysName+"]").prop("selected","selected");
    		$("#integrationAmmeterDBClickCommonForm input[name='commonConfigDesc']").val(configDescValue);//填写描述数据回显
    		$("#integrationAmmeterDBClickCommonForm input[name='commonUnitDesc']").val(configUnitValue);//填写单位数据回显
    		$("#integrationAmmeterDBClickCommonForm input[name='commonAmmeterConfigSequence']").val(configFSeqValue);//填写序号数据回显
    		form.render();
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
    								if($("#ammeter"+m).attr("fsysname")==result.list[n].f_sys_name){
    									$("#ammeter"+m).text(result.list[n].f_init_val);
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
	 			$.ajax({
	   		        type: "post",
	   		        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertTableDataCommonConfig',
	   		        contentType:'application/json;charset=UTF-8',
	   		        data:JSON.stringify({
	   		        	f_ddc_sys_name : data.field.configAmmeterDdcFSysName,
	   		        	f_sys_name     : data.field.configAmmeterPointLocationFid,
	   		        	f_desc         : data.field.commonConfigDesc,
	   		        	f_seq          : data.field.commonAmmeterConfigSequence,
	   		        	f_unit		   : data.field.commonUnitDesc,
	   		        	f_mark         : fMark,
	   		        	f_page_name	   : 'ammeter',
	   		        	//f_device_id    : f_ammeter_id
	   		        	f_device_id    : '888888'  //TODO:将此处的设备id即电表id替换为真正的id；↑
	   		        }),
	   		        success: function (result) {
	   		        	if(result.code=="1"){//插入配置div展示数据成功
	   		        		layer.close(ammeterIndex);
	   		        		ammeterIndex = 0;
	   		        		layer.msg(result.msg);
	   		        		//$("#table tr").slice(1,4).remove();//这句是删除第二到第四行 数字按自己的需要自己修改
	   		        		//var size = $("#basicParametersTableLeader tr").length();
	   		        		//$("#basicParametersTableLeader tr").slice(3,size).remove();
	   		        		$("#basicParametersTableLeader").find('tr').remove();//将之前自动生成的tr删除
	   		        		$("#proofreaderTableLeader").find('tr').remove();//经之前自动生成的tr删除
	   		        		integrationInitHtml();//刷新页面
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
	   		        	f_ddc_sys_name : data.field.configAmmeterDdcFSysName,
	   	  		        f_sys_name     : data.field.configAmmeterPointLocationFid,
	   	  		       	f_desc         : data.field.commonConfigDesc,
	   	  		       	f_seq	       : data.field.commonAmmeterConfigSequence,
	   	  		       	f_unit         : data.field.commonUnitDesc,
	 					f_id		   : fId
	   		        }),
	   		        success: function (result) {
	   		        	if(result.code == '1'){//更新配置div数目成功
	   		        		layer.close(ammeterIndex);
	   		        		ammeterIndex = 0;
	   		        		$("#basicParametersTableLeader").find('tr').remove();//将之前自动生成的tr删除
	   		        		$("#proofreaderTableLeader").find('tr').remove();//经之前自动生成的tr删除
	   		        		integrationInitHtml();//刷新页面
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
    	
	   	function deleteAmmeterTableDataCommonConfig(){
			//提示删除操作;
				layer.open({
				  content: '删除'
				  ,btn: ['确定', '取消']
				  ,yes: function(index, layero){//确定删除
					  //按钮【确定】的回调:执行删除操作
					  // 若为新增的DIV，进行删除的时候进行判断
					  if(fId=='undefined'||fId==null){
							layer.close(ammeterIndex);
							layer.close(index);
							ammeterIndex = 0;
							$("#basicParametersTableLeader").find('tr').remove();//将之前自动生成的tr删除
							$("#proofreaderTableLeader").find('tr').remove();//经之前自动生成的tr删除
							integrationInitHtml();//刷新页面
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
				        	      layer.close(ammeterIndex);
				        	      ammeterIndex = 0;
				        	      $("#basicParametersTableLeader").find('tr').remove();//将之前自动生成的tr删除
			   		        	  $("#proofreaderTableLeader").find('tr').remove();//经之前自动生成的tr删除
			   		        	  integrationInitHtml();//刷新页面
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
	    	 layer.close(ammeterIndex);
	    	 ammeterIndex=0;
	     } 
	   	 
	   	 
	   	 
        return {
        	closeCommonConfigInfos :function(){
    			closeLayerHost();
    		},
    		deleteCommonConfigInfos : function(){
    			deleteAmmeterTableDataCommonConfig();
    		}
        }
    })(jQuery, window, document);
</script>
