<style>
    Edit in JSFiddle
    Result
    HTML
    CSS
    body {
        font-size: 11px;
        font-family: 'Open Sans', sans-serif;
        color: #4A4A4A ;
        text-align: center;
    }
    .energyEfficiencyAssessmentTotal {
        display: flex;
        align-items: flex-start;
    }
    .energyEfficiencyAssessmentTotal1 {
        display: flex;
        align-items: center;
    }
    .energyEfficiencyAssessmentLeft {
        width: 65%;
        height: 100%;
    }
    .energyEfficiencyAssessmentRight {
        width: 35%;
        height: 100%;
    }
    .energyEfficiencyAssessmentBox7{
        margin: 20px auto;
        margin-right: 60px;
        min-height: 300px;
        padding: 10px;
        position: relative;
        background: -webkit-gradient(linear, 0% 20%, 0% 92%, from(#fff), to(#f3f3f3), color-stop(.1,#fff));
        border-top: 1px solid #ccc;
        border-right: 1px solid #ccc;
        border-left: 1px solid #ccc;
        -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.2);
    }

    .energyEfficiencyAssessmentBox7:before{
        content: '';
        position:absolute;
        width: 130px;
        height: 30px;
        border-left: 1px dashed rgba(0, 0, 0, 0.1);
        border-right: 1px dashed rgba(0, 0, 0, 0.1);
        background: -webkit-gradient(linear, 555% 20%, 0% 92%, from(rgba(0, 0, 0, 0.1)), to(rgba(0, 0, 0, 0.0)), color-stop(.1,rgba(0, 0, 0, 0.2)));
        -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.2);
        -webkit-transform:translate(-50px,10px)
        skew(10deg,10deg)
        rotate(-50deg)
    }
    .energyEfficiencyAssessmentBox7:after{
        content: '';
        position:absolute;
        right:0;
        bottom:0;
        width: 130px;
        height: 30px;
        background: -webkit-gradient(linear, 555% 20%, 0% 92%, from(rgba(0, 0, 0, 0.1)), to(rgba(0, 0, 0, 0.0)), color-stop(.1,rgba(0, 0, 0, 0.2)));
        border-left: 1px dashed rgba(0, 0, 0, 0.1);
        border-right: 1px dashed rgba(0, 0, 0, 0.1);
        -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.2);
        -webkit-transform: translate(50px,-20px)
        skew(10deg,10deg)
        rotate(-50deg)
    }

    .energyEfficiencyAssessmentBox7 p{
        margin-top: 15px;
        text-align: justify;
    }

    .energyEfficiencyAssessmentBox7 h1{
        font-size: 20px;
        font-weight: bold;
        margin-top: 5px;
        text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
    }

</style>
<div class="">
	<div id="energyEfficiencyAssessmentDiv">
		<div class="">
			<span class="">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;能效评估信息>>>
	    	</span>
		</div>
	</div>
	<div class="energyEfficiencyAssessmentTotal">
	    <div class="energyEfficiencyAssessmentLeft" style="margin-left:10px;">
	        <div class="energyEfficiencyAssessmentTotal1" style="width: 80%;height:100px;">
	            <div style="background-color:#91C7AE;width: 32px;height:16px; "></div>&nbsp;节能优异&nbsp;
	            <div style="background-color:#63869E;width: 32px;height:16px; "></div>&nbsp;一般&nbsp;
	            <div style="background-color: #C23531;width: 32px;height:16px;"></div>&nbsp;差&nbsp;
	        </div>
	        <div class="COPEnergyEfficiencyAssessment" id="COPEnergyEfficiencyAssessment" style="width:80%;height:80%;" sequence="27"></div>
	    </div>
	    <div class="energyEfficiencyAssessmentRight">
	        <div class="energyEfficiencyAssessmentBox7">
	            <h1 style="color: #0c0c0c">冷水机房评价标准（ASHRAE）</h1>
	            <p style="color: #0c0c0c">
	                ASHRAE（American Society of Heating, Refrigerating and Air-Conditioning Engineers）
	                ，成立于1894年，是建筑技术的协会，全球拥有超过54,000名成员。协会及其成员专注于建筑系统，能源效率，
	                室内空气质量，制冷和行业内的可持续性。通过调研，标准编写，出版和继续教育，ASHRAE形状今天明天的内置环境。
	                ASHRAE形成作为美国采暖，制冷与空调工程师学会美国暖气和空调工程师学会（ASHAE）成立于1894年，
	                美国制冷工程师学会（ASRE）于1959年合并成立于1904年。
	            </p>
	            <br />
	        </div>
	    </div>
	</div>
</div>


<div class="layui-fluid">
	<form class="layui-form " style="display: none;" id="DBClickAssessmentForm">
		<input type="hidden">
		<div class="layui-form-item" style = "margin-top:2vh;">
			<div class="layui-row">
				<div class="layui-col-xs2">
					<label class="layui-form-label">所属DDC：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configDBClickAssessmentFormSysName" name="configDBClickAssessmentFormSysName" lay-filter="ddcOption" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
				</div>
				<div class="layui-col-xs2">
					<label class="layui-form-label">所属点位：</label>
				</div>
				<div class="layui-col-xs3">
					<select id="configDBClickAssessmentFormFid" name="configDBClickAssessmentFormFid" lay-search="" lay-verify=""><option value="">选择或搜索</option></select>
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
					<button type="button" class="layui-btn layui-btn-primary" onclick="COPEnergyEfficiencyAssessment.closeAssessmentForm()">取消</button>
				</div>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">

	var form=layui.form;

	var COPEnergyEfficiencyAssessment=(function($, window, document, undefined) {
			var timer;
		   	var divHeightSum = $(window).height();//网页窗口可见区域总体高度
			var divWidthSum = $(window).width();//网页窗口可见区域总宽度
			var pageDivSequence; //div序号
	        $("#COPEnergyEfficiencyAssessment").css({"height":divHeightSum*0.7});
			$(".energyEfficiencyAssessmentRight").css({"margin-top":divHeightSum*0.2,"margin-right":divWidthSum*0.1});//设置冷水机房评价标准网页位置
			$(".energyEfficiencyAssessmentBox7").css("width",divWidthSum*0.38);
			
	    //展示能效评估图表
	    function showCOPEnergy(initVal,unit){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('COPEnergyEfficiencyAssessment'),'light');

			option = {
				tooltip: {
					formatter: '{a} <br/>{b} : {c}%'
				},
				toolbox: {
					feature: {
						restore: {},
						saveAsImage: {}
					}
				},
				series: [
					{
						name: unit,
						type: 'gauge',
						detail: {formatter: '{value}%'},
						data: [{value: initVal, name: 'COP'}]
					}
				]
			};
            myChart.setOption(option);
		}


		//给div设置双击弹出配置框设置
		$("#COPEnergyEfficiencyAssessment").dblclick(function(obj){
			pageDivSequence=$(this).attr("sequence");//点击时，将其序号获得
			form.on('select(ddcOption)',function (data) {
				const configDDCSysName = data.value;
				loadPointLocations(configDDCSysName);
			})
			index = layer.open({
				tytle:'配置',
				type:1,
				area:['40vw','60vh'],
				maxmin:true,
				content:$("#DBClickAssessmentForm"),
			});
		})


		$(function(){
			loadOptions();
			initAssessment();
		})

		function loadOptions(){
			//configColdHeatSourceDdcFSysName  所属ddc
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadDDCOption',
				dataType : "json",
				data : {},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configDBClickAssessmentFormSysName').append(new Option(item.f_nick_name,item.f_sys_name));
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


		function loadPointLocations(configDDCSysName){
			//configColdHeatSourcePointLocationFid  所属ddc下的点位信息
			$.ajax({
				type : "get",
				url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadPointLocationOption',
				dataType : "json",
				data : {f_sys_name:$('#configDBClickAssessmentFormSysName').val()},
				success : function(result) {
					if(result.code == '0'){
						$.each(result.data,function(index,item){
							$('#configDBClickAssessmentFormFid').append(new Option(item.f_nick_name,item.f_sys_name));

						});
						/* 若有传入配置的系统名称，说明为已配置的div，为回显，做以下处理 */
						if(configDDCSysName!=null&&configDDCSysName!=""){
							$("#configDBClickAssessmentFormFid select[name='configDBClickAssessmentFormFid'] option[value="+configDDCSysName+"]").prop("selected","selected");
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

		var f_sysName_list = "";

		function initAssessment(){
			$.ajax({
				type : "post",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/selectAssessment',
				dataType : "json",
				contentType:'application/json;charset=UTF-8',
				data : JSON.stringify({
				}),
				success : function (result){
					if(result.code == '1'){
						f_sysName_list = result.data[0].f_sys_name;
						selectAssessmentData(f_sysName_list);
					}else{//
						layer.msg(result.msg);
					}
				},
				error: function (result) {
					layer.msg(result.msg);
				}
			})
		}


		//添加配置的点位信息
		form.on('submit(saveCommonConfig)',function(data){
			confirmCommonConfig(pageDivSequence,index);
		})

		function confirmCommonConfig() {
	    	//
			$.ajax({
				type : "post",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/insertAssessment',
				dataType : "json",
				contentType:'application/json;charset=UTF-8',
				data : JSON.stringify({
					f_sys_name	   : $('#configDBClickAssessmentFormFid').val(),//配置的DDC所属点位信息
					f_ddc_sys_name : $('#configDBClickAssessmentFormSysName').val(),
					f_seq 		   : pageDivSequence,//所点击的div序号
					f_type_id	   : "6",
					f_desc :$("#DBClickAssessmentForm input[name='commonConfigDesc']").val()//描述
				}),
				success : function (result){
					if(result.code == '1'){
						layer.close(index);
						index = 0;
						f_sysName_list = $('#configDBClickAssessmentFormFid').val();
						selectAssessmentData(f_sysName_list);//添加成功后查询数据
					}else{//
						layer.msg(result.msg);
					}
				},
				error: function (result) {
					layer.msg(result.msg);
				}
			})
		}
		
		//查询配置的数据
		function selectAssessmentData(f_sysName_list) {
			$.ajax({
				type : "post",
				url : _ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
				dataType : "json",
				contentType:'application/json;charset=UTF-8',
				data : JSON.stringify({
					f_sysName_list : [f_sysName_list]
				}),
				success:function (result) {
					if(result.code !== '0' || result.data == null || result.data == ""){
						layer.alert("此点位无数据")
						return;
					}else {
						var initVal = result.data[0].f_init_val;
						var unit = result.data[0].f_engineer_unit;
						showCOPEnergy(initVal,unit);
					}
				}
			})
		}


	return{
		closeAssessmentForm: function () {
			layer.close(index);
			index = 0;
		},
		pageInit:function(){
    	    var data='';
            showCOPEnergy();
            //show1(data);
		}
	}
    })(jQuery, window, document);
    COPEnergyEfficiencyAssessment.pageInit();
</script>


