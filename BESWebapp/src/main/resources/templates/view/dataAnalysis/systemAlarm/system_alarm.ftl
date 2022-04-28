<style>
  .search_system_alarm_div_style{
	display: flex;
    align-items: center;
    margin-right: 3vh;
    float:right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 7px 0px 1px 0px;
	 }
</style>
<!-- tabs样式 -->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
 <ul id="system_alarm_tab" class="nav tabs">
 <li class='active nocancel' ><a data-toggle='tab' href='#' >支路参数报警</a></li>
 <li  ><a data-toggle='tab' href='#' >分户能耗报警</a></li>
 <li  ><a data-toggle='tab' href='#' >设备报警</a></li>
 <li  ><a data-toggle='tab' href='#' >系统报警</a></li>
 </ul>
 
<div class="leftarea information_left" style="width:18.4%!important;height:96%!important" >
	<div class="information-model">
			<span class="tree_Subtitle"> <i class="fa fa-share-alt"
				aria-hidden="true"></i>&nbsp;筛选条件>>>
			</span>
		</div>
	<div class="search_qstjfx_style">
	
		<div class="search_system_alarm_div_style">
		 <span class="zl_sxtj_span">所选园区:</span>
			<div id='park_group'></div>
		</div>
		
		<div class="search_system_alarm_div_style">
		<span class="zl_sxtj_span">开始时间:</span>
			<input id="system_alarm_start_time" class="input-datecheck" class="input-datecheck"
				 onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
		</div>
		
		
		<div class="search_system_alarm_div_style" >
		<span class="zl_sxtj_span">结束时间:</span>
			<input id="system_alarm_end_time"  class="input-datecheck"
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" />
		</div>
		
		<div class="search_system_alarm_div_style">
		    <span class="zl_sxtj_span">处理状态:</span>
			<div id="deal_state_group"  ></div>	
		</div>
		
		
		<div style="float: right;margin-right: 3vh;" >
		<button type="button" class="btn btn-sm btn-primary no-margins toLeft" 
						onclick="dataannlysis_fh_qstjfh.getTableData()">
						<i class="fa fa-filter"></i>&nbsp;筛选
					</button>
					<button type="button" class="btn btn-sm btn-primary no-margins toLeft"
						onclick="dataannlysis_fh_qstjfh.reset()">
						<i class="fa fa-filter"></i>&nbsp;重置
					</button>
		</div>
   </div>
 </div>
<!-- 右侧内容模块start -->
<div class="information_right" style="width:81.5%!important;height:96%!important">
  <div class="information_size" style="height:95%">
  	<div id="system_alarm_table" style="height:80%" class="Information_area" ></div>  
  </div>
</div>
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
;
var dataannlysis_system_alarm = (function($, window, document, undefined) {
	
	$(function(){
		var park_id=['0002'];
		var park_val=['花园小区'];
		park_group_select('#park_group',park_id,park_val);
		
		var deal_state_group_id=['0','1','2'];
		var deal_state_group_val=['全部','已处理','未处理'];
		deal_state_group_select('#deal_state_group',deal_state_group_id,deal_state_group_val);
		
		//获取当前时间
		var date = getCurrentDate();
		//起始时间和默认时间
		$('#system_alarm_start_time').val(getFormatDate(date));
		$('#system_alarm_end_time').val(getFormatDate(date));   
	});
	
	//所属园区selected
	function park_group_select(id,idArray,valArray){
		//选择电表下拉列表 空间选择
		$(id).ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0px',//margin-left属性
			isHasData:true,
			idArray:idArray,//id
			valArray:valArray,//txt
			isNoSelectedText:false, //是否设置未选中提示文本
			callBack: "",
		});
	}
		
		//处理转台selected
		function deal_state_group_select(id,idArray,valArray){
			//选择电表下拉列表 空间选择
			$(id).ISSPSpinnerBox({
				width:'9vw',//下拉列表宽度
				height: '2.9vh',//下拉列表高度
				margLeft:'0px',//margin-left属性
				isHasData:true,
				idArray:idArray,//id
				valArray:valArray,//txt
				isNoSelectedText:false, //是否设置未选中提示文本
				callBack: "",
			});
		}
		
})(jQuery, window, document);   
	
	
				
</script>