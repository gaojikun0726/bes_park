<#--
描述： 分户绩效考核能源绩效考核
作者： liuzhen
-->
<!-- tab样式 -->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<ul id="energy_plan_Tab" class="nav tabs"  ></ul>

<!-- 分项树模块start -->
<div class="leftarea information_left" style="width:18.4%!important;height:96%!important" >
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;请选择分项支路>>>
		</span>
	</div>
	<div id="tree_energy_plan" class="Information_area"
		style="height: height:60%!important"></div>
</div>
<!-- 分项树模块end -->



<!-- 右侧内容模块模块start -->
<div class="information_right" style="width:81.5%!important;height:96%!important" >
	
	<!-- 上方tab页start -->
	<!-- 上方tab页end -->
	<div class="information_size" style="height:99%">
		<div class="information-model" style="padding-top:15px;padding-bottom:15px;">
			<span class="tree_Subtitle"><i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;&nbsp;数据列表>>>
			</span>
		<#--打印按钮-->
            <a href="javascript:void(-1);" onclick="dataannlysis_energy_plan.print()" class="btn btn-primary toLeft">
                <i class="fa fa-print"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
            </a>
			<button id="energy_plan_housedata" class='btn btn-white btn-sm edit' style="margin-left:20px;display:none;" data-id='' data-toggle='modal' data-target='#editEnergyHouseholdForm'><i class='fa fa-pencil' ></i> 基本配置信息</button>
			<div style="width:80%;text-align:right;margin-right:10px;">
				<label style="margin-left: 15px;visibility:hidden;">报警方式:</label>
				<div style="display: inline-block; margin-left: 15px;margin-top:3px;display:none;">
					<input id="alarm_type" class="input-datecheck" readonly="true">
				</div>
				<label  style="display: inline-block;margin-left: 40px;">年份:</label>
				<div style="display: inline-block; margin-left: 10px;margin-top:3px">
					<input id="energy_plan_year"  class="input-datecheck"
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy',firstDayOfWeek:1,readOnly:true,onpicked:dataannlysis_energy_plan.getHouseholdPlanData})">
				</div>
			</div>
		</div>
		<div id="household_plan_table"  class="Information_area" ></div>
 	</div>
	  
</div>	
<!-- 右侧内容模块模块end -->

<!----基本配置信息--->
<div class="modal fade" id="editEnergyHouseholdForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button  aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 分户基本信息配置</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_houseHoldForm"  class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="fperson_nums">人数 <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="number" id="fperson_nums"   required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="farea">面积<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="number" id="farea" name="edit_fFxmc"  required class="form-control">
					</div>
				</div>
				
                <div class="form-group">
                        <label class="col-sm-3 control-label">报警方式:<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="alerttype"  required class="form-control">
                            <option value="0">短信</option>
                            <option value="1">邮件</option>
                            <option value="2">短信和邮件</option>
                            <option value="3">无</option>
                            </select>
                        </div>
                </div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4 display_flex">
						<button class="btn btn-md btn-primary " type="submit">
						<strong>确定</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
				</form>
            </div>
        </div>
    </div>
</div>			


<!---编辑能源配置信息开始-----> 
<div class="modal fade" id="modal_form_energy_plan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:900px;height:480px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; <span id="plan_month"></span>月份能耗计划配置</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="energy_plan_form" name="add" class="form-horizontal">
                   <div class="form-group">
                        <label class="col-sm-2 control-label">最新价格<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input style="padding-left:10px" type="text" id="f_price" name="f_price"  readonly="readonly"  required class="form-control">
                        </div>
                        
                        <label class="col-sm-2 control-label">二氧化碳<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input style="padding-left:10px" type="text" id="f_co2" name="f_co2" readonly="readonly" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">人数<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input style="padding-left:10px" type="text" id="person_amount" name="person_amount" readonly="readonly" class="form-control">
                        </div> 
                        
                        <label class="col-sm-2 control-label">面积<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input style="padding-left:10px" type="text" id="energy_paln_area" name="energy_paln_area"  readonly="readonly"  class="form-control">
                        </div>  
                    </div>
                    
                    
              
                     <div class="form-group">
				        <label class="col-sm-2 control-label">能源类型<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input style="padding-left:10px" type="text" id="energy_paln_type" name="energy_paln_type"   readonly="readonly" class="form-control">
                        </div>
				        
				          <label class="col-sm-2 control-label">耗煤量<span class="text-danger">*</span></label>
				        <div class="col-sm-4">
				        	<input style="padding-left:10px" type="text" id="energy_paln_coal" name="energy_paln_coal"   readonly="readonly" class="form-control">

				        </div> 
				        
                    </div>
                    <div class="form-group" style ="margin-top:50px">
                        <label class="col-sm-2 control-label">总能耗<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input onchange="dataannlysis_energy_plan.inputCal('1')" style="padding-left:10px" type="number" id="all_energy" name="all_energy" placeholder="请输入总能"  required class="form-control" > 
                        </div>
                        
                        <label  class="col-sm-2 control-label">人均能耗<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input onchange="dataannlysis_energy_plan.inputCal('2')" style="padding-left:10px" type="number" id="perman_energy" name="perman_energy" placeholder="请输入人均能耗" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">总金额<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input onchange="dataannlysis_energy_plan.inputCal('3')" style="padding-left:10px" type="number" id="all_money" name="all_money" placeholder="请输入总金额" class="form-control">
                        </div> 
                        
                        <label class="col-sm-2 control-label">人均金额<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input onchange="dataannlysis_energy_plan.inputCal('4')" style="padding-left:10px" type="number" id="perman_money" name="perman_money" placeholder="请输入人均金额" class="form-control">
                        </div>  
                    </div>
                    
              
                     <div class="form-group">
				        <label class="col-sm-2 control-label">单位面积能耗<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input onchange="dataannlysis_energy_plan.inputCal('5')" style="padding-left:10px" type="number" id="unitarea_data" name="unitarea_data"  placeholder="请输入单位面积能耗" class="form-control">
                        </div>
				        
				          <label class="col-sm-2 control-label">单位面积金额<span class="text-danger">*</span></label>
				        <div class="col-sm-4">
				        	<input onchange="dataannlysis_energy_plan.inputCal('6')" style="padding-left:10px" type="number" id="unitarea_money" name="unitarea_money"  placeholder="请输入负单位面积金额" class="form-control">

				        </div> 
				        
                    </div>
                    
                     <div class="form-group">
				        <label class="col-sm-2 control-label">二氧化碳<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                        	<input onchange="dataannlysis_energy_plan.inputCal('7')" type="number" style="padding-left:10px"  id="energy_plan_co2" name="energy_plan_co2"  placeholder="请输入二氧化碳" class="form-control">
                        </div>
				        
				          <label  class="col-sm-2 control-label">准煤量<span class="text-danger">*</span></label>
				        <div class="col-sm-4">
				        	<input onchange="dataannlysis_energy_plan.inputCal('8')" style="padding-left:10px" type="number" id="coal_amount" name="coal_amount"  placeholder="请输入准煤量" class="form-control">
				        </div> 
                    </div>    
                    <div class="form-group">
						<input style="margin-left:20%;width:15px;height:15px;vertical-align:middle;" id="fenable"  type="checkbox" value="" /><span style="margin-left:5px;vertical-align:middle;">使能</span>
                    </div>
                    
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;保存</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;关闭</button>
                        </div>
                   </div>
                </form>
            </div>
        </div>
    </div>
</div>



<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
;
var dataannlysis_energy_plan = (function($, window, document, undefined) {
	
	var _fnybh='';//能源类型	
	var _ctx = '${ctx}';
	var _fFhbh = '';//分户编号
	var fGuid ="";//记录数据ID
	$(function(){
		//获取当前时间
		var date = getCurrentDate();
		//默认年份
		$('#energy_plan_year').val(getFormatDate(date).split("-")[0]);
		
		getTabs();
		qstjfx_tree();
	})
	
	//设置表格操作格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var energyId = cell.getRow().getData().fguid;
		return "<div class='btn-group '>"
				+"<button  class='btn btn-white btn-sm edit' data-id='"+energyId+"' data-toggle='modal' data-target='#modal_form_energy_plan'><i class='fa fa-pencil' ></i> 操作</button>"
			
	};
	
	//表单验证
	$("#edit_houseHoldForm").validate({
  	 submitHandler: function(form) {
    	 editHouseholdForm(form);
  	 }
 	});
	
	
	//编辑配置信息
	function editHouseholdForm(form) {
   		$.ajax({
	        url:"${ctx}/view/dataAnalysises/houseHoldAppraisal/updateHouseholdData",
     		type: "post",
     		data:({
     			fFhbh: _fFhbh,
     			fPersonNums: $("#fperson_nums").val(),
     			fArea: $("#farea").val(),
     			fAlertType: $("#alerttype").val(),
     		}),
     		success: function(data) {
				if (data.status == '1') {
					swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
	            	  dataannlysis_energy_plan.getHouseholdPlanData();

					$('#editHouseholdForm').modal('hide');//关闭编辑窗口
	         		
         		} else{
             		swal("修改配置信息失败!", data.msg, "error");
         		}
    		},
    		error: function(data) {
         	 	swal("修改配置信息失败!", data.msg, "error");
    		}
   		});
	}
	
	
	//编辑能源计划配置表单验证
	$("#energy_plan_form").validate({
  	 submitHandler: function(form) {
  		editHouseholdPlanForm(form);
  	 }
 	});
		


	//编辑能源计划配置信息
	function editHouseholdPlanForm(form) {
		
		if($("#fperson_nums").val() == "" || $("#farea").val() == "")
		{
			swal("修改能耗计划信息失败!", "", "warning");	
		}
		//获取使能	
		var fenable="";
		if($("#fenable").prop("checked"))
		{
			fenable='0';	
		}
		else
		{
			fenable='1';
		}
	
   		$.ajax({
	        url:"${ctx}/view/dataAnalysises/houseHoldAppraisal/updateHouseholdPlanData",
     		type: "post",
     		data:({
     			fCo2: $("#energy_plan_co2").val(),
     			fCoalAmount: $("#coal_amount").val(),
     			fAllEnegry: $("#all_energy").val(),
                fPermanMoney: $("#perman_money").val(),
     			fAllMoney: $("#all_money").val(),
     			fPermanData: $("#perman_energy").val(),
     			fUnitareaData: $("#unitarea_data").val(),
     			fUnitareaMoney: $("#unitarea_money").val(),
     			fEnable:fenable,
     			fGuid:fGuid
     		}),
     		success: function(data) {
				if (data.status == '1') {
					swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
					$('#modal_form_energy_plan').modal('hide');//关闭编辑窗口
					dataannlysis_energy_plan.getHouseholdPlanData();
         		} else{
             		swal("修改能耗计划信息失败!", data.msg, "error");
         		}
    		},
    		error: function(data) {
         	 	swal("修改能耗计划信息失败!", data.msg, "error");
    		}
   		});
	}
	
	//获取tab页,tab页文字为能源类型
	function getTabs(){
	    $.ajax({
	        type: "post",
	        url:"${ctx}/view/basedatamanage/energyinformation/getenrrgylist",
	        dataType: "json",
	        async: false,
	        data:({     
	        	f_yqbh:'0000'
			}),
	        success: function (result) {
	        	
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
                        _fnybh = result.list[0].fnybh;//默认能源类型
                        for(var i = 0; i<result.list.length; i++){
	            		var treeNode = result.list[i];
	            		if(i == 0){ 
	            			$("#energy_plan_Tab").append("<li class='active nocancel' ><a  data-toggle='tab' href='#'  data='"+treeNode.fnybh+"'>"+treeNode.fnymc+"</a></li>");
	            		}else{
	            			$("#energy_plan_Tab").append("<li ><a  data-toggle='tab' href='#'   data='"+treeNode.fnybh+"'>"+treeNode.fnymc+"</a></li>");
	            		}
	            			
	            	}	            		
	            }
	                //点击tab,支路树变化 
	            	$("#energy_plan_Tab a").click(function(){
	            		$(this.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
	            		_fnybh = $(this).attr("data");
	            		 qstjfx_tree();          		 
	            	});
	            }
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	
	
	
	//生成分项名称树
	function qstjfx_tree(){
	    $.ajax({
	        type: "post",
	        url:"${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
	        dataType: "json",
	        data:({     
	        	fNybh:_fnybh
			}),
	        success: function (result) {
	            //初始加载根节点
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_energy_plan').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) 
	               	{
	                	
	            	   _fFhbh = nodeData.nodeTreeId;
	            	
	            	   dataannlysis_energy_plan.getHouseholdPlanData();
	                         
	                }
	            });
	            //增加根节点为默认节点
				if(result.hasOwnProperty("list")&&result.list.length>0)
				{
					var firstNode = $("#tree_energy_plan").treeview('findNodes',[result.list[0].id,'id']);
					$("#tree_energy_plan").treeview("selectNode", firstNode);
					dataannlysis_energy_plan.getHouseholdPlanData();

				}
			        	
	              }
	            }else{
                    swal( "当前能源下暂无支路配置","", "warning");
	            	$('#tree_energy_plan').treeview({
		                data:"[]" ,         // 数据源		                
		            });
	            }
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}

	//居中显示（基本配置信息）
 	$('#editEnergyHouseholdForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editEnergyHouseholdForm .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	});
	
	//创建并设置table属性
	$("#household_plan_table").tabulator({
		height:"95%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		columns:[
		{title:"节能名称", field:"fefficientName",sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"总能耗(Kwh)", field:"fallEnegry",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"总金额(元)", field:"fallMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"人均能耗(Kwh)", field:"fpermanData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"人均金额(元)", field:"fpermanMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"二氧化碳(ppm)", field:"fco2",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"单位面积能耗(Kwh)", field:"funitareaData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"单位面积金额(元)", field:"funitareaMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"耗煤量(吨)", field:"fcoalAmount",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", tooltip:false,tooltipsHeader:false,width:80,align:"center",headerSort:false,formatter:optIconFunction},
		],
    	rowClick:function(e, row){
        	
    	},
	});
	
	$(window).resize(function() {
		$("#household_plan_table").tabulator("redraw");
	});
	
	
	
	//验证在模态框出现前加载编辑
 	$("#modal_form_energy_plan").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		 fGuid = button.data("id");//获取用户组编号
   		$.ajax({
	        url:"${ctx}/view/dataAnalysises/houseHoldAppraisal/getHouseholdPlanData",
	       type: "post", 
	       data:{     
	 			"fGuid":fGuid,
	 			"fNybh":_fnybh
	 		},                            

	       success: function(result)
	       {
 				$("#energy_plan_co2").val(result.list[0].fco2);
 				$("#coal_amount").val(result.list[0].fcoalAmount);
 				$("#all_energy").val(result.list[0].fallEnegry);
 				$("#perman_money").val(result.list[0].fpermanMoney);
 				$("#all_money").val(result.list[0].fallMoney);
 				$("#perman_energy").val(result.list[0].fpermanData);
 				$("#unitarea_data").val(result.list[0].funitareaData);
 				$("#unitarea_money").val(result.list[0].funitareaMoney);
 				$("#person_amount").val(result.list[0].fpersonNums);    
 				$("#energy_paln_area").val(result.list[0].farea);
 				$("#f_price").val(result.list[0].fprice);
 				$("#f_co2").val(result.list[0].feco2);
 				$("#energy_paln_type").val(result.list[0].fnymc);
 				$("#energy_paln_coal").val(result.list[0].fecoalAmount);
 				$("#plan_month").text(result.list[0].fpmonth);
 				if(result.list[0].fenable=='使能')
 				{
 					$("#fenable").attr("checked",true);
 					
 				}
 				else
				{
 					$("#fenable").attr("checked",false);	
				}
 				
	        }
   		});  
 	});
	
	
 	return{
		//获取能源计划数据
		getHouseholdPlanData:function()
		{
			//每次先将数据清空
			$("#household_plan_table").tabulator('setData',[]);
			 $.ajax({
			        type: "post",
			        url:"${ctx}/view/dataAnalysises/houseHoldAppraisal/getHouseholdPlanData",
			        dataType: "json",
			        traditional: true,
			        beforeSend: function () {
			        	showLoad();	             
			            },
			        data:{     
			        	"fPyear":$("#energy_plan_year").val(),
                        "fFhbh":_fFhbh,
					},
			        success: function (result) 
			        {
			        	if(result.hasOwnProperty("list"))
			        	{
			        		if(result.list.length>0)
			        		{
			        			$("#household_plan_table").tabulator('setData',result.list);	
			        			$("#fperson_nums").val(result.list[0].fpersonNums);
			        			$("#farea").val(result.list[0].farea);
			        			$("#alerttype").val(result.list[0].falertType);
			        			$("#alarm_type").val($("#alerttype").find("option:selected").text());
			        			
			        		}
			        	}
			        }, 
			        complete: function () {
			        	hiddenLoad();
			        },
			        error: function(result) {
		          	    swal( result.msg,"", "error");
		            }
			 });	
		},
		//编辑时自动计算 制作
		inputCal: function(index)
		{
			    var coal_amount = $("#coal_amount").val();
			    var energy_plan_co2 = $("#energy_plan_co2").val();
			    var unitarea_money = $("#unitarea_money").val();
				var unitarea_data = $("#unitarea_data").val();
				var perman_money = $("#perman_money").val();
				var perman_energy =  $("#perman_energy").val();
				var all_money = $("#all_money").val();
				var all_energy =$("#all_energy").val();
				var f_price = $("#f_price").val();
				var f_co2 = $("#f_co2").val();
				var energy_paln_coal = $("#energy_paln_coal").val();
				var energy_paln_area = $("#energy_paln_area").val();
				var person_amount = $("#person_amount").val();
				//点击不同输入框，做出不同的变化
				if(index == '1'){
					//总金额
					if(perman_energy != null && f_price != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy*f_price;
						$("#all_money").val(num.toFixed(2));
					}


					//人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}
                    all_money = $("#all_money").val();
                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{

						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						all_money = $("#all_money").val();
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
					
				}else if(index == '2')
				{


					//总能耗
					if(perman_energy != null && perman_energy != "" &&
							person_amount != null && person_amount != "")
					{  
						var num =person_amount*perman_energy;
						$("#all_energy").val(num.toFixed(2));
					}


                    all_energy =$("#all_energy").val();
                    //总金额
					if(f_price != null && f_price != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy*f_price;
						$("#all_money").val(num.toFixed(2));
					}
                    all_money = $("#all_money").val();

                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						all_money = $("#all_money").val();
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}

                    //耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				else if(index == '3')
				{
					//总能耗
					if(all_money != null && all_money != "" &&
                            f_price != null && f_price != "")
					{  
						var num =all_money/f_price;
						$("#all_energy").val(num.toFixed(2));
					}
                    all_money = $("#all_money").val();

                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
                    all_energy =$("#all_energy").val();

                    //人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}
				
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						all_energy =$("#all_energy").val();
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				
				else if(index == '4')
				{
					
					//总金额
					if(perman_money != null && perman_money != "" &&
							person_amount != null && person_amount != "")
					{  
						var num = perman_money*person_amount;
						$("#all_money").val(num.toFixed(2));
					}
                    all_money = $("#all_money").val();

                    //总能耗
					if(all_money != null && all_money != "" &&
                            f_price != null && f_price != "")
					{  
						var num =all_money/f_price
						$("#all_energy").val(num.toFixed(2));
					}

                    all_energy =$("#all_energy").val();

                    //人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}

					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}


                    //单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
                            all_money != null && all_money != "")
					{  
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				else if(index == '5')
				{
					//总能耗
					if(unitarea_data != null && unitarea_data != "" &&
							energy_paln_area != null && energy_paln_area != "")
					{  
						var num =unitarea_data*energy_paln_area;
						$("#all_energy").val(num.toFixed(2));
					}
                    all_energy =$("#all_energy").val();
                    //总金额
					if(f_price != null && f_price != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy*f_price;
						$("#all_money").val(num.toFixed(2));
					}
					
					//人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}

                    all_money = $("#all_money").val();
                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				else if(index == '6')
				{
					
					//总金额
					if(unitarea_money != null && unitarea_money != "" &&
							energy_paln_area != null && energy_paln_area != "")
					{  
						var num =unitarea_money*energy_paln_area;
						$("#all_money").val(num.toFixed(2));
					}
                    all_money = $("#all_money").val();

                    //总能耗
					if(all_money != null && all_money != "" &&
                            f_price != null && f_price != "")
					{
						var num =all_money/f_price
						$("#all_energy").val(num.toFixed(2));
					}
                    all_energy =$("#all_energy").val();
                    //人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}
					
					
					//人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				
				else if(index == '7')
				{
					//总能耗
					if(f_co2 != null && f_co2 != "" &&
							energy_plan_co2 != null && energy_plan_co2 != "")
					{  
						var num =energy_plan_co2/f_co2;
						$("#all_energy").val(num.toFixed(2));
					}
                    all_energy =$("#all_energy").val();

                    //总金额
					if(f_price != null && f_price != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy*f_price;
						$("#all_money").val(num.toFixed(2));
					}
					
					//人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}

                    all_money = $("#all_money").val();
                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}
					
						
					//耗煤量
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =energy_paln_coal*all_energy;
						$("#coal_amount").val(num.toFixed(2));
					}
						
				}
				else if(index == '8') 
				{
					//总能耗
					if(energy_paln_coal != null && energy_paln_coal != "" &&
							coal_amount != null && coal_amount != "")
					{  
						var num =coal_amount/energy_paln_coal;
						$("#all_energy").val(num.toFixed(2));
					}
                    all_energy =$("#all_energy").val();
                    //总金额
					if(f_price != null && f_price != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy*f_price;
						$("#all_money").val(num.toFixed(2));
					}
					//人均能耗
					if(person_amount != null && person_amount != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/person_amount;
						$("#perman_energy").val(num.toFixed(2));
					}

                    all_money = $("#all_money").val();

                    //人均金额
					if(person_amount != null && person_amount != "" &&
							all_money != null && all_money != "")
					{  
						
						var num =all_money/person_amount;
						$("#perman_money").val(num.toFixed(2));
					}
					
					//单位面积能耗
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =all_energy/energy_paln_area;
						$("#unitarea_data").val(num.toFixed(2));
					}
					
					//单位面积金额
					if(energy_paln_area != null && energy_paln_area != "" &&
							all_money != null && all_money != "")
					{  
						
						var num =all_money/energy_paln_area;
						$("#unitarea_money").val(num.toFixed(2));
					}

					//co2
					if(f_co2 != null && f_co2 != "" &&
							all_energy != null && all_energy != "")
					{  
						var num =f_co2*all_energy;
						$("#energy_plan_co2").val(num.toFixed(2));
					}
						
						
				}
				
		},
        //打印按钮
        print :function() {
            $("#household_plan_table").printThis({});
        }
		
	}
	
})(jQuery, window, document);



</script>