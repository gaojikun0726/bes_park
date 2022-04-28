<div style=" width:100%;height:100%;" id="operationBasisDiv" >
	<div style="margin-left:15px;">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;运行依据>>>
	</div>
	<!-- 雷达图-->
	<div class="divOperationBasisBottomConfig commonChangeColorDarker"style="width:100%;height:97%;">
    	<div  style="margin-left:20px;margin-top:20px;">
        	<div id="operationBasisChart" style="width: 500px; height: 500px;"></div>
    	</div>
	</div>
</div>
<script type="text/javascript">
    ;
    var divOperationBasis = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var server_Interval=5*1000;//定时器 5秒钟
        //var divHeightSum = $(window).height();//网页窗口可见区域总体高度
		//var divWidthSum = $(window).width();//网页窗口可见区域总宽度
		$(function(){
        	showEnergyEfficiency();
        })
        //显示雷达图的信息
        function showEnergyEfficiency(){
            var dom = document.getElementById("operationBasisChart");
            var myChart1 = echarts.init(dom, 'light');
            var option = {
            	    title: {
            	        text: '雷达图',
            	        /* subtext: '(最大值：1500)',
    			        left: 'left', */
            	    },
            	    tooltip: {},
            	    legend: {
            	    	color: '#fff',
            	        data: ['预算分配', '实际开销']
            	    },
            	    radar: {
            	        // shape: 'circle',
            	        name: {
            	            textStyle: {
            	                color: '#fff',   //雷达图顶点(eg:销售)字体颜色：
            	                backgroundColor: '#999',//雷达图顶点(eg:销售)背景色
            	                borderRadius: 3, //周边弧度
            	                padding: [3, 5]  //字体距离边框上下3px,左右5px
            	           }
            	        },
            	        indicator: [
            	           { name: '销售', max: 1500},
            	           { name: '管理', max: 1500},
            	           { name: '信息技术', max: 1500},
            	           { name: '客服', max: 1500},
            	           { name: '研发', max: 1500},
            	           { name: '市场', max: 1500}
            	        ]
            	    }, 
            	    toolbox: {
    			        feature: {
    			            dataView: {}, //展现当前图表所有的数据
    			            saveAsImage: {},//把图表保存为图片
    			        }
    			    },
            	    series: [{
            	        name: '预算 vs 开销',
            	        type: 'radar',
            	        //symbol:"cicle",   // 拐点的样式，还可以取值'rect','angle'等
            	        // areaStyle: {normal: {}},
            	        data : [
            	            {
            	                value : [300, 1000, 820, 500, 500, 1000],
            	                name : '预算分配',
            	              	//label:{} 作用为可以在雷达图上显示具体点的具体数值
                    	        label: {
        				            show: true,
        				            color: '#00FF0C',  //预算分配图形顶点数值颜色   （#39A1D9:蓝色）
        				            fontSize: 12,
        			        	},
            	                itemStyle: {
                                    // 线和折点的颜色
                                    normal: {
                                        color: "#00FF0C", // 图表中各个图区域的边框线拐点颜色 （"#F15A24":红色  #00FF0C:绿色）
                                        lineStyle: {
                                            color: "#00FF0C"  // 图表中各个图区域的边框线颜色
                                        }
                                    }
                                }

            	            },
            	             {
            	                value : [1200, 1400, 1300, 1250, 1300, 1450],
            	                name : '实际开销',
            	                //label:{} 作用为可以在雷达图上显示具体点的具体数值
                    	        label: {
        				            show: true,
        				            color: '#FFD85C',  //实际开销图形顶点数值颜色 （#FFD85C:黄色）
        				            fontSize: 12,
        			        	},
        			        	itemStyle: {
                                    // 线和折点的颜色
                                    normal: {
                                        color: "#FFD85C", // 图表中各个图区域的边框线拐点颜色 （"#F15A24":红色）
                                        lineStyle: {
                                            color: "#FFD85C"  // 图表中各个图区域的边框线颜色
                                        }
                                    }
                                }
            	            }
            	        ]
            	    }]
            	};
            //先展示 后填充数据
            myChart1.setOption(option);
            
            var num11,num12,num13,num14,num15,num16,
            	num21=1200,num22=1400,num23=1300,num24=1250,num25=1300,num26=1450;
            //每15秒生成一组数据
            var getNum2=setInterval(function(){
            	//parseInt()向下取整 eg:parseInt(2.922)=2
            	num21=parseInt(Math.random()*500+1000,10);
            	num22=parseInt(Math.random()*500+1000,10);
            	num23=parseInt(Math.random()*500+1000,10);
            	num24=parseInt(Math.random()*500+1000,10);
            	num25=parseInt(Math.random()*500+1000,10);
            	num26=parseInt(Math.random()*500+1000,10);
            },15*1000);

            //定时器 定时刷新
            var operationBasisInterval=setInterval(function (){
            	var value1 = [];
            	var value2=[];
            	num11 = (Math.random() *700+800).toFixed(0);
                num12 = (Math.random() *700+800).toFixed(0);
                num13 = (Math.random() *700+800).toFixed(0);
                num14 = (Math.random() *700+800).toFixed(0);
                num15 = (Math.random() *700+800).toFixed(0);
                num16 = (Math.random() *700+800).toFixed(0);
            	value1.push(num11,num12,num13,num14,num15,num16);
            	value2.push(num21, num22, num23, num24, num25, num26);
            	myChart1.setOption({
            		 series:{
            			 type: 'radar',
            			 data:[
            				 {
            					 name : '预算分配', 
            					 value:value1,
            					 //label:{} 作用为可以在雷达图上显示具体点的具体数值
                     	         label: {
         				            show: true,
         				            color: '#00FF0C',
         				            fontSize: 12,
         			        	 },
         			        	itemStyle: {
                                    // 线和折点的颜色
                                    normal: {
                                        color: "#00FF0C", // 图表中各个图区域的边框线拐点颜色 （"#F15A24":红色 #00FF0C:绿色）
                                        lineStyle: {
                                            color: "#00FF0C"  // 图表中各个图区域的边框线颜色
                                        }
                                    }
                                }
         			        	 
            				 },
            				 {
            					 name : '实际开销',
            					 value:value2,
            					 //label:{} 作用为可以在雷达图上显示具体点的具体数值
                     	         label: {
         				            show: true,
         				            color: '#FFD85C',
         				            fontSize: 12,
         			        	 },
         			        	itemStyle: {
                                    // 线和折点的颜色
                                    normal: {
                                        color: "#FFD85C", // 图表中各个图区域的边框线拐点颜色 （"#F15A24":红色）
                                        lineStyle: {
                                            color: "#FFD85C"  // 图表中各个图区域的边框线颜色
                                        }
                                    }
                                }         			        	 
         			        	 
            				 }
            			 ]
            		 }
            	 });
            	//判断页面是否活跃，如果不活跃，关闭定时器
            	//judgeActive()此方法为issp.js封装
      			if(judgeActive("operationBasisDiv")){  
      			}else{
      				clearInterval(operationBasisInterval);
      				clearInterval(getNum2);
      			}
            },server_Interval);
        }
        return {

        }
    })(jQuery, window, document);
</script>
