<style>

.managementRecordRrectangle15{
	width: 80px; height: 30px;border-radius:15px;margin-left:10px;margin-top:20px;text-align:center;line-height:30px;
}
/* 橘色圆圈div渐变设置 */
.managementRecordOrangediv {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#e87903), to(#f9ba44));
	background: -moz-linear-gradient(top,  #e87903,  #f9ba44);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#e87903', endColorstr='#f9ba44');
}
</style>
<div class="strongAndWeakElectricityIntegrationCommon" style="height:100%;">
	<!-- Start 顶部按钮 -->
	<div class="managementRecordDivtop">
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;margin-left:40px;">维护记录</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">告警记录</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">本日</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">上一日</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">下一日</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">本月</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">上一月</div>
		<div class="managementRecordRrectangle15 managementRecordOrangediv" style="float:left;border:solid 1px orange;">下一月</div>
	</div>
	<!-- End 顶部按钮 -->
	
	<!-- Start 数据部分 -->
	<div class="managementRecordDivLeader strongAndWeakElectricityIntegrationCommon"style="margin:10px;">
		
	</div>
	<!-- End 数据部分 -->
</div>

 
<script type="text/javascript">
    ;
    var hostLinkage = (function($, window, document, undefined) {
        
    	var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		var hostLinkageTopDivHeight= divHeightSum*0.7;
		var divWidthSum = $(window).width();//网页窗口可见区域总宽度
		$(".managementRecordDivtop").css("height",divHeightSum*0.08);
		$(".managementRecordDivLeader").css("height",hostLinkageTopDivHeight);
		
        return {
			
        }
    })(jQuery, window, document);
</script>
