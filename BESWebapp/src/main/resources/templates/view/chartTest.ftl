<div id="chartTest3" style="width: 100%;height: 50%;"></div>
<div id="chartTest" style="width: 100%;height: 50%;"></div>
<div id="chartTest2" style="width: 100%;height: 50%;"></div>


<script type="text/javascript">

	$.ajax({
	  url:  "${ctx}/view/chartData",
	  type: "post",
	  success: function(data) {	  	
	  	var m = new Map();
		m.put("数量", "F_DATA");
		m.put("总金额", "F_ALL_MONEY");
		m.put("总金额22", "F_COAL_AMOUNT");

		var keyVal = "1523443346000"; //用于饼图展示数据项的标志，根据情况用<input><select>等标签输入,1523443346000
		
		$('#chartTest3').setPieData({
	        'title': 'ALVIN 测试',
	        'keyWords': 'F_CJSJ',
	        'legendMap': m,
	        'keyVal' : keyVal
	    },data);
		
	  }
	});
	
	$(function(){
		var m = new Map();
		m.put("数量", "F_DATA");
		m.put("总金额", "F_ALL_MONEY");
		
	  	$('#chartTest').isspLine({
	        'ajaxUrl': '/view/chartData',
	        'title': 'TEST JQUERY Plugin',
	        'xData': 'F_CJSJ',
	        'legendMap': m
	    });
	})



	$.ajax({
	  url:  "${ctx}/view/chartData",
	  type: "post",
	  success: function(data) {
	  	var m = new Map();
		m.put("数量2", "F_DATA");
		m.put("总金额", "F_ALL_MONEY");

		$('#chartTest2').setBarData({
	        'title': 'ALVIN 测试',
	        'xData': 'F_CJSJ',
	        'legendMap': m
	    },data);
		
	  }
	});
	

</script>