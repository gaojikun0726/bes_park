		<div class="Information_area" style="height: calc(100% - 0px) !important;position: relative;
			 background: url('static/images/zmjk/lhcf_2_19.png'); background-size: 100% 100%; background-repeat: no-repeat;">
			 <!-- 左上 -->
			 <div class="left-top">
			 	<div style=""></div>
			 	<button id="M8190800" control="M8190800" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:18%;top:21%;">西北一灯</button>
			 	<input type="hidden" id="h_point" name="f_guid" value="">	
			 	<button id="M8190802" control="M8190802" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:56%;top:21%;">西北二灯</button>
			 	<button id="M8190803" control="M8190803" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:18%;top:40%;">西北三灯</button>
				<button id="M8190804" control="M8190804" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:56%;top:40%;">东北一灯</button>
				<button id="M8190807" control="M8190807" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:18%;top:59%;">东北二灯</button>
				<button id="M8190801" control="M8190801" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:56%;top:59%;">东北三灯</button>
				<button id="M8190806" control="M8190806" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:18%;top:76%;">南一路灯</button>
				<button id="M8190805" control="M8190805" type="button" class="switch-button online" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:56%;top:76%;">南二路灯</button>
					
				<button id="mainswitch1908" control="M8190800,M8190801,M8190802,M8190803,M8190804,M8190805,M8190806,M8190807" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('M8190800,M8190801,M8190802,M8190803,M8190804,M8190805,M8190806,M8190807',this)"
					style="left:30%;bottom:-5%;">1908室</button>
			 </div>
			 <div class="center-top">
			 	<button id="mainswitch1909" control="M4190900,M4190901,M4190902" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('M4190900,M4190901,M4190902',this)"
					style="left:10%;bottom:41%;">1909室</button>
<!-- 			 	<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:4%;bottom:-3%;">开关</button> -->
				<button id="mainswitch1910" control="M4191000,M4191001,M4191002,M4191003" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('M4191000,M4191001,M4191002,M4191003',this)"
					style="left:43%;bottom:42%;">1910室</button>
<!-- 				<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:40%;bottom:-3%;">开关</button> -->
				<button id="mainswitch1911" control="M4191100,M4191101,M4191102,M4191103" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('M4191100,M4191101,M4191102,M4191103',this)"
					style="left:76%;bottom:41%;">1911室</button>
<!-- 				<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:70%;bottom:-3%;">开关</button> -->
			 </div >
			 <div class="center-center">
			 	<button id="" type="button" class="corridor-button" style="left:7%;top:7%;">北走廊西</button>
			 		<button id="F19M8102" control="F19M8102" button-name="北走廊西" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)" 
			 			style="left:20%;top:5%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:45%;top:7%;">射灯</button>
			 		<button id="F19M8105" control="F19M8105" button-name="射灯" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
			 			style="left:52%;top:5%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:78%;top:7%;">北走廊东</button>
			 		<button id="F19M8104" control="F19M8104" button-name="北走廊东" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
			 			style="left:92%;top:5%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:-4%;bottom:43%;background-size: 50px 20px;">西走廊</button>
			 		<button id="F19M8100" control="F19M8100" button-name="西走廊" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
			 			style="left:2%;top:40%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:53%;bottom:44%;background-size: 54px 20px;">电梯间</button>
			 		<button id="F19M8101" control="F19M8101" button-name="电梯间" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
			 			style="left:59%;top:40%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:7%;bottom:6%;">南走廊西</button>
			 		<button id="F19M8106" control="F19M8106" button-name="南走廊西" type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint(this)"
			 			style="left:22%;top:88%;"></button>
			 	<button id="" type="button" class="corridor-button" style="left:77%;bottom:5%;">南走廊东</button>
			 		<button id="F19M8103" control="F19M8103" button-name="南走廊东 type="button" class="circle-button circleonline" value="0" onclick="dataAnalysis_zmjk.setLampPoint('this)"
			 			style="left:93%;top:88%;"></button>
			 	<button control="F19M8102,F19M8105,F19M8104,F19M8100,F19M8101,F19M8106,F19M8103" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('F19M8102,F19M8105,F19M8104,F19M8100,F19M8101,F19M8106,F19M8103',this)"
					style="left:30%;bottom:-5%;">走廊</button>
			 </div>
			 <div class="left-bottom">
<!-- 			 	<button id="" type="button" class="small-button"  -->
<!-- 					style="margin:0% 0% 0% 75%;">开关</button> -->
				<button id="M8190600" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:28%;top:5%;">会议室</button>
				<button id="mainswitch1906" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('20,21,22,23,24,25,26',this)"
					style="left:28%;top:32%;">1906室</button>
			 	<button id="M8190604" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:14%;top:47%;">西南一灯</button>
			 	<input type="hidden" id="h_point" name="f_guid" value="">	
			 	<button id="M8190601" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:50%;top:47%;">西南二灯</button>
			 	<button id="M8190606" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)" 
					style="left:14%;top:61%;">西南三灯</button>
				<button id="M8190603" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:50%;top:61%;">东南一灯</button>
				<button id="M8190605" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:14%;top:74%;">东南二灯</button>
				<button id="M8190602" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:50%;top:74%;">东南三灯</button>
				<button id="M8190607" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:14%;top:86%;">北两灯</button>
			 </div>	
			 <div class="buttom-buttom">
<!-- 			 	<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:3%;top:3%;">开关</button> -->
				<button id="M41905190300" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:2%;top:39%;">1905室</button>
<!-- 				<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:16%;top:3%;">开关</button> -->
				<button id="M41905190301" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPoint(this)"
					style="left:19%;top:38%;">1903室</button>
<!-- 				<button id="" type="button" class="small-button" -->
<!-- 					style="left:36%;top:3%;">开关</button> -->
				<button id="mainswitch1902" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('M4190200,M4190201,M4190202,M4190203',this)"
					style="left:42%;top:37%;">1902室</button>
<!-- 				<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:67%;top:3%;">开关</button> -->
				<button id="mainswitch1901" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('35,36',this)"
					style="left:72%;top:35%;">1901室</button>
			 </div>	
			 <div class="right">
<!-- 			 	<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:-8%;top:34.5%;">开关</button> -->
				<button id="mainswitch1916" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('43,44,45,46,47',this)"
					style="left:46%;top:32%;">1916室</button>
<!-- 				<button id="" type="button" class="small-button" onclick="" -->
<!-- 					style="left:9%;bottom:9%;">开关</button> -->
				<button id="mainswitch1919" type="button" class="switch-button online" onclick="dataAnalysis_zmjk.setLampPointList('37,38',this)"
					style="left:33%;bottom:5.4%;">1919室</button>
			 </div>				
		</div>