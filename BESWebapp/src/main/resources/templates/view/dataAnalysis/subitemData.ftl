<!-----内容区域---->
<div class="tab-wrapper" style="height:90%;padding: 10px 10px 0px 10px;">
		<div class="input-group"  style="height:40px;width:100%">
        <div class="row">
            <div class="col-sm-4 col-lg-2 col-md-3 m-t-xs">
              <select class="input-sm form-control input-s-sm inline">
                <option value="0">济南分公司</option>
                <option value="1">泰安分公司</option>
                <option value="2">莱芜分公司</option>
                <option value="3">聊城分公司</option>
                <option value="4">德州分公司</option></select>
            </div>
            <div class="col-sm-4 col-lg-2 col-md-3 m-t-xs">
              <select class="input-sm form-control input-s-sm inline">
                <option value="0">收费站</option>
                <option value="1">隧道</option>
                <option value="2">运营中心</option>
                <option value="3">服务区</option></select>
            </div>
            <div class="col-sm-4 col-lg-3 m-t-xs m-b-none">
	              <div class="input-daterange input-group">
	              	<span class="input-group-addon">开始:</span>
	                <input id="start_time" type="text" class="input-sm form-control" name="start" onchange="dataAnalysis_subitemData.check()">
	                <span class="input-group-addon">结束:</span>
	                <input id="end_time" type="text" class="input-sm form-control" name="end" onchange="dataAnalysis_subitemData.check()">
	                <span class="input-group-btn">
	                  <button type="button" class="btn btn-sm btn-primary no-margins" onclick="dataAnalysis_subitemData.searchSubitemData()">查询</button>
	                </span>
	              </div>
            </div>
            <div style="float: right;position: relative;padding-right: 20px;padding-left: 15px;">
	            <div class="input-group">
		            <a id="exportSubitemData" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addSubitemData">
	                	导出 <i class="fa fa-plus"></i>
	            	</a>
	            	<a id="PrintSubitemData" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addSubitemData">
	                	打印 <i class="fa fa-plus"></i>
	            	</a>
	            </div>
            </div>
       </div>
	   </div>
			<div id="getSubitemData" style="height:50%;width:100%"></div>	
			<div style="height:55%">
				<div id="subitemDateTable">	</div>
   			</div>		
	</div>

								
								
								

<!-----内容结束----->
<div class="modal fade" id="modal-form-test123" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加设备型号信息</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="addEquipmenttype" name="addEquipmenttype" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用电类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_type" name="f_type" placeholder="请输入1-12位中文字符"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">数值<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="f_sbmc" name="f_sbmc" placeholder="请输入1-12位中文字符" class="form-control">
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3">
                            <button class="btn btn-md btn-primary" type="submit"><strong>提交</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<script src="${ctx}/static/js/tab_index.js"></script>
<script type="text/javascript">
;
var dataAnalysis_subitemData = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	
	$("#subitemDateTable").tabulator({
		//ajaxURL:_ctx+"/view/equipmentManage/equipmentType?pgnum="+pgnum,
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		//date:tableDate,
		
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",headerSort:false}, //frozen column
		{title:"分项编号", field:"fFxmc",sorter:"string",editor:false,align:"center"}, //never hide this column
		{title:"电能编号", field:"fDnbh",sorter:"string",editor:false,align:"center"}, //hide this column first 
		{title:"采集时间", field:"fCjsj",sorter:"string",editor:false,align:"center"},
		{title:"数据值", field:"fData",sorter:"string",editor:false,align:"center"},
		{title:"总金额", field:"fAllMoney",sorter:"string",editor:false,align:"center"},
		{title:"耗煤量", field:"fCoalAmount",sorter:"string",editor:false,align:"center"},
		//{title:"创建时间", field:"f_crdate",sorter:"string",align:"center",editor:false},
		//{title:"修改时间", field:"f_chdate",sorter:"string",align:"center",editor:false},
		],

	});
	
	var now = new Date(); //当前日期
	var month = now.getMonth() + 1;
    var strDate = now.getDate();
	if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
	var currentdate = now.getFullYear() + "-" + month + "-" + strDate;
	var monthStartDate = now.getFullYear() + "-" + month + "-" + "01" 

    	$('#start_time').datepicker({
    		format:"yyyy-mm-dd",
			autoclose:true,
			todayHighlight: true,
	 		endDate: new Date()
		}).on('changeDate',function(e){  
		    var startTime = e.date;  
		    $('#end_time').datepicker('setStartDate',startTime);  
		});
        $('#end_time').datepicker({
        	format:"yyyy-mm-dd",
   			autoclose:true ,
   			todayHighlight: true,
			endDate: new Date()
		}).on('changeDate',function(e){  
		    var endTime = e.date;  
		    $('#start_time').datepicker('setEndDate',endTime);  
		});      


	//搜索
	function getSubitemDataList(){
		var start_time = $("#start_time").val();
		var end_time = $("#end_time").val();        
		$.ajax({
	    url: _ctx+"/view/getSubitemDataList",
	    type: "post",
	    data: {
			  fCjsj_start:start_time,
			  fCjsj_end:end_time
	    },
			success: function(data) {
				$("#subitemDateTable").tabulator("setData",data);

        },
        error: function(data) {
      	    swal( data.msg,"", "error");
        }
	   });
	}

	
	function clickevent(value){
		
		$('#modal-form-test123').modal('show');
		$('#f_type').val(value.name);
		$('#f_sbmc').val(value.value);
		  	
	}	


	return {
		searchSubitemData : function (){
			getSubitemDataList();
			var start_time = $("#start_time").val();
			var end_time = $("#end_time").val();
			$.ajax({
				  url:  _ctx+"/view/getSubitemData",
				  type: "post",
				  data:{
					  fCjsj_start:start_time,
					  fCjsj_end:end_time
				  },
				  success: function(data) {	  
				  	var m = new Map();
					m.put("照明插座用电", "F_FXBH_SN");
					m.put("空调用电", "F_FXBH_SW");
					m.put("动力用电", "F_FXBH_ZLYJ");
					m.put("特殊用电", "F_FXBH_BGSB");

					$('#getSubitemData').setLineData({
				        'title': '部门用电数据分析',
				        'title_fontSize':24,
				        'xData': 'F_CJSJ',
				        'legendMap': m,
				        'barGap' : 0,
				        'title_x' : 'left',
				        'trigger' : 'axis',
				        'areaStyle' : null,//区域填充
				        'showSeriesLable' : false,//折点上是否显示数据
				        'boundaryGap' : false,
				        'line_color' : ['#fe5958','#ff80fd','#fdfe1f','#2eddcb'],//折线颜色
				        'callBacks': clickevent  //点击图形触发的函数
				    },data);				
				  }
				});
		},
		//时间校验
		check : function (){
			var start_time = $("#start_time").val();
			var end_time = $("#end_time").val();
			if(start_time > end_time & end_time != ""){
				swal({ 
	        		title: "请重新选择时间",
	        		text: "经检测，开始时间大于结束时间!",
	        		type: "warning",
	        		showCancelButton: false,
	        		confirmButtonColor: "#1ab394",
	        		confirmButtonText: "关闭",
	        		closeOnConfirm: false
	    		});
			}
		},
		pageInit : function(){
			dataAnalysis_subitemData.searchSubitemData();
		}
		}
})(jQuery, window, document);

dataAnalysis_subitemData.pageInit();	

</script>